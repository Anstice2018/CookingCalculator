package com.example.anstice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int 新增請求碼 = 0;
    private static final int 更新請求碼 = 1;

    public static final String RECIPE_KEY = "recipe";
    public static final String LIST_KEY = "list";

    private ListView m_listView;
    private  MainListAdapter m_adapter;

    private ArrayList<Recipe> m_list = new ArrayList<>();

    private int itemIndex;          // 修改的是第幾項

    private final String TAG = this.getClass().getSimpleName();     // 取得類別名稱
    private static final String FILENAME = "UseAnswers.data";       // 存檔名稱



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

        init();
    }

    private void init(){
        m_listView = findViewById(R.id.lv_item);
        //m_adapter = new MainListAdapter(this, m_list);

        m_listView.setEmptyView(findViewById(R.id.tv_empty));
        m_listView.setAdapter(m_adapter);
        //m_listView.setOnItemClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 新增請求碼 && requestCode == RESULT_OK){
            Bundle bundle = data.getExtras();

            String 名稱 = bundle.getString(AddActivity.KEY_名稱);
            String 描述 = bundle.getString(AddActivity.KEY_描述);
            int 圖片 = bundle.getInt(AddActivity.KEY_DRAWABLE_ID);
            String 份 = bundle.getString(AddActivity.KEY_份);

            Recipe recipe = new Recipe(名稱, 圖片);       // 建立資料庫
            m_list.add(recipe);
            m_adapter.notifyDataSetChanged();
        }else if(requestCode == 更新請求碼 && resultCode == RESULT_OK){
            Recipe recipe = (Recipe) data.getSerializableExtra(MainActivity.RECIPE_KEY);
            // 重新設定 陣列中的寵物資料
            m_list.set(itemIndex, recipe);
            m_adapter.notifyDataSetChanged();
        }
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
