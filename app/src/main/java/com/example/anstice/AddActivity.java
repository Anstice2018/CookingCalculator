package com.example.anstice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity {

    // 資料寄放 Intent 所需要的 KEY
    public static final String KEY_名稱 = "名稱";
    public static final String KEY_描述 = "描述";
    public static final String KEY_DRAWABLE_ID = "圖片";
    public static final String KEY_份 = "份";


    // 資料
    protected String 名稱;
    protected String 描述;
    protected int 圖片;
    protected String 份;         // 為什麼不能打Integer


    private EditText m_ed_名稱;
    private EditText m_ed_描述;
    private ImageButton m_ib_圖片;
    private EditText m_ed_份;


    public static final int 未選圖預設值 = -1;

    private static final int 圖片請求 = 1;

    public static final String TAG = "AddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();

        /**
        // 給 KEY 才能取能內容  (回想 map)
        if (savedInstanceState != null){
            名稱 = savedInstanceState.getString(KEY_名稱);
            描述 = savedInstanceState.getString(KEY_描述);
            圖片 = savedInstanceState.getInt(KEY_DRAWABLE_ID);
            m_ib_圖片.setImageResource(圖片);                // Maybe 是圖檔資源較大, 需要 setImageResource
            份 = savedInstanceState.getString(KEY_份);                    // 為什麼不能打 getInt
        }
         */

    }

    private void init() {
        m_ed_名稱 = findViewById(R.id.ed_名稱);
        m_ed_描述 = findViewById(R.id.ed_描述);
        m_ib_圖片 = findViewById(R.id.ib_圖片);
        m_ed_份 = findViewById(R.id.ed_份);
        圖片 = 未選圖預設值;





    }

    /**
    @Override
    protected void onSaveInstanceState (Bundle outState){
        outState.putString(KEY_名稱, 名稱);
        outState.putString(KEY_描述, 描述);
        outState.putInt(KEY_DRAWABLE_ID, 圖片);
        outState.putString(KEY_份, 份);

        super.onSaveInstanceState(outState);
    }
    */

    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                名稱 = m_ed_名稱.getText().toString();
                Intent intent = getIntent();
                intent.putExtra(KEY_名稱, 名稱);
                intent.putExtra(KEY_DRAWABLE_ID, 圖片);
                setResult(RESULT_OK, intent);
                Log.d(TAG, "按下確定");
                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
