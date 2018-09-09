package com.ourflettership.anstice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
            implements AdapterView.OnItemClickListener{

    private static final int 新增請求碼 = 0;
    private static final int 更新請求碼 = 1;

    public static final String RECIPE_KEY = "recipe";
    public static final String LIST_KEY = "list";

    private ListView m_listView;
    private MainListAdapter m_adapter;

    // List 介面不是可序列化，ArrayList 才可序列化
    private ArrayList<Recipe> m_list = new ArrayList<>();

    private int itemIndex;          // 修改的是第幾項

    private final String TAG = this.getClass().getSimpleName();     // 取得類別名稱
    private static final String FILENAME = "UseAnswers.data";       // 存檔名稱



    // 重現資料 savedInstanceState
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 新增請求碼);
            }
        });


        if (savedInstanceState != null) {
            // 重現暫存資料
            m_list = (ArrayList)savedInstanceState.getSerializable(LIST_KEY);
            } else {
            // 重現被儲存的資料
            reStoreData();
        };

        // 初始化
        initListView();
    }

    private void initListView(){
        m_listView = findViewById(R.id.lv_item);
        m_adapter = new MainListAdapter(this, m_list);

        m_listView.setEmptyView(findViewById(R.id.tv_empty));
        m_listView.setAdapter(m_adapter);
        m_listView.setOnItemClickListener(this);

    }

    // Bundle bundle = data.getExtras();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 新增請求碼 && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();

            String 名稱 = bundle.getString(AddActivity.KEY_名稱);
            String 描述 = bundle.getString(AddActivity.KEY_描述);
            int 圖片 = bundle.getInt(AddActivity.KEY_DRAWABLE_ID);
            String 份 = bundle.getString(AddActivity.KEY_份);
            String[] 材料 = bundle.getStringArray(AddActivity.KEY_材料);
            String[] 重量單位 = bundle.getStringArray(AddActivity.KEY_重量單位);
            String[] 總重量單位 = bundle.getStringArray(AddActivity.KEY_總重量單位);
            String[] 重量 = bundle.getStringArray(AddActivity.KEY_重量);
            String[] 總重量 = bundle.getStringArray(AddActivity.KEY_總重量);

            Recipe recipe = new Recipe(名稱, 描述, 圖片, 份, 材料, 重量單位, 總重量單位, 重量, 總重量);       // 建立資料庫
            m_list.add(recipe);
            Log.d(TAG, "返回 MainActivity");
            m_adapter.notifyDataSetChanged();



        }else if(requestCode == 更新請求碼 && resultCode == RESULT_OK){
            Recipe recipe = (Recipe) data.getSerializableExtra(MainActivity.RECIPE_KEY);
            // 重新設定 陣列中的寵物資料
            m_list.set(itemIndex, recipe);
            m_adapter.notifyDataSetChanged();
        }
    }

    // 暫存資料
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(LIST_KEY, m_list);
        super.onSaveInstanceState(outState);
    }

    // 儲存資料
    public void saveData(){

        // openFileOutput() 繼承自 Context，Activity 繼承自 Context
        try{
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);  // MODE_PRIVATE 私有檔案，禁止其他 App 使用
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(m_list);
            oos.close();
        } catch (IOException e){
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    // 呼叫被儲存的資料
    public void reStoreData(){

        // openFileInput() 繼承自 Context，Activity 繼承自 Context
        try{
            FileInputStream fis = openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            m_list = (ArrayList) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e){
            Log.d(TAG, e.toString());
            e.printStackTrace();
        }
    }

    // APP被關掉，要執行saveData();
    @Override
    protected  void onStop(){
        super.onStop();
        saveData();
    }



    // 點一下 執行 AlertDialog.Builder
    @Override                       // 想要 .remove(index) 需改為 final int index
    public void onItemClick(AdapterView<?> adapterView, View view, final int index, long l) {
        String s = "第" + index + "項 被點選了";
        Log.d("MainActivity", s);

        // 記錄目前點選的項目
        itemIndex = index;

        new AlertDialog.Builder(this)
                .setMessage(R.string.ad_message)
                .setPositiveButton(R.string.ad_view, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                        intent.putExtra(MainActivity.RECIPE_KEY, m_list.get(index));
                        startActivityForResult(intent, 更新請求碼);
                    }
                })
                .setNegativeButton(R.string.ad_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNeutralButton(R.string.ad_delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        m_list.remove(index);               // 刪除陣列中的 index 項目
                        m_adapter.notifyDataSetChanged();   // 通知 ListView 畫面需要刷新
                    }
                })
                .show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
