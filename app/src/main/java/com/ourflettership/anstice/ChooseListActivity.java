package com.ourflettership.anstice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ChooseListActivity extends AppCompatActivity
                    implements AdapterView.OnItemClickListener{

    public static final String KEY = "圖片id";

    private ListView mLvItem;
    private ChooseListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_list);

        initListView();
    }

    // 初始化 ListView()
    private void initListView() {
        mLvItem = findViewById(R.id.lv_item);
        mListAdapter = new ChooseListAdapter(this);

        mLvItem.setEmptyView(findViewById(R.id.tv_empty));    // 設定
        mLvItem.setAdapter(mListAdapter);                        // 設定 資料轉接
        mLvItem.setOnItemClickListener(this);                 // 設定 處理點選項目 外包商
    }

    // 實作 onItemClick
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = "第" + position + "項 被點選了";
        Log.d("ListActivity", s);

        Intent intent = getIntent();
        int[] drawableIds = mListAdapter.getDrawableIds();     // Adapter 需提供 getter
        intent.putExtra(KEY, drawableIds[position]);               // 放入所選圖片id
        setResult(RESULT_OK, intent);                       // 設定結果

        finish();                                           // 結束目前畫面，返回前一個畫面
    }


}
