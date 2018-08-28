package com.example.anstice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    // 資料寄放 Intent 所需要的 KEY
    public static final String KEY_名稱 = "名稱";
    public static final String KEY_描述 = "描述";
    public static final String KEY_份 = "份";
    public static final String KEY_材料1 = "材料1";
    public static final String KEY_材料2 = "材料2";
    public static final String KEY_材料3 = "材料3";
    public static final String KEY_WEIGHT1 = "weight1";
    public static final String KEY_WEIGHT2 = "weight2";
    public static final String KEY_WEIGHT3 = "weight3";
    public static final String KEY_TOTAL1 = "total1";
    public static final String KEY_TOTAL2 = "total2";
    public static final String KEY_TOTAL3 = "total3";

    // 資料
    protected String 名稱;
    protected String 描述;
    protected Integer 份;
    protected String 材料1;
    protected String 材料2;
    protected String 材料3;
    protected String weight1;
    protected String weight2;
    protected String weight3;
    protected String total1;
    protected String total2;
    protected String total3;

    private EditText m_ed_份;
    private EditText m_ed_weight1;
    private EditText m_ed_weight2;
    private EditText m_ed_weight3;
    private EditText m_ed_total1;
    private EditText m_ed_total2;
    private EditText m_ed_total3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();

        // 給 KEY 才能取能內容  (回想 map)
        if (savedInstanceState != null){
            名稱 = savedInstanceState.getString(KEY_名稱);
            描述 = savedInstanceState.getString(KEY_描述);
            份 = savedInstanceState.getInt(KEY_份);
            材料1 = savedInstanceState.getString(KEY_材料1);
            材料2 = savedInstanceState.getString(KEY_材料2);
            材料3 = savedInstanceState.getString(KEY_材料3);
            weight1 = savedInstanceState.getString(KEY_WEIGHT1);
            weight2 = savedInstanceState.getString(KEY_WEIGHT2);
            weight3 = savedInstanceState.getString(KEY_WEIGHT3);
            total1 = savedInstanceState.getString(KEY_TOTAL1);
            total2 = savedInstanceState.getString(KEY_TOTAL2);
            total3 = savedInstanceState.getString(KEY_TOTAL3);
        }

    }

    private void init() {
        m_ed_份 = findViewById(R.id.ed_份);
        m_ed_weight1 = findViewById(R.id.ed_weight1);
        m_ed_weight2 = findViewById(R.id.ed_weight2);
        m_ed_weight3 = findViewById(R.id.ed_weight3);
        m_ed_total1 = findViewById(R.id.ed_total1);
        m_ed_total2 = findViewById(R.id.ed_total2);
        m_ed_total3 = findViewById(R.id.ed_total3);




    }

    @Override
    protected void onSaveInstanceState (Bundle outState){
        outState.putString(KEY_名稱, 名稱);
        outState.putString(KEY_描述, 描述);
        outState.putInt(KEY_份, 份);
        outState.putString(KEY_材料1, 材料1);
        outState.putString(KEY_材料2, 材料2);
        outState.putString(KEY_材料3, 材料3);
        outState.putString(KEY_WEIGHT1, weight1);
        outState.putString(KEY_WEIGHT2, weight2);
        outState.putString(KEY_WEIGHT3, weight3);
        outState.putString(KEY_TOTAL1, total1);
        outState.putString(KEY_TOTAL2, total2);
        outState.putString(KEY_TOTAL3, total3);

        super.onSaveInstanceState(outState);
    }

    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:


                int int_份 = Integer.parseInt(m_ed_份.getText().toString());
                int int_weight1 = Integer.parseInt(m_ed_weight1.getText().toString());
                int int_weight2 = Integer.parseInt(m_ed_weight2.getText().toString());
                int int_weight3 = Integer.parseInt(m_ed_weight3.getText().toString());
                int int_total1 = int_份 * int_weight1;
                int int_total2 = int_份 * int_weight2;
                int int_total3 = int_份 * int_weight3;
                String s_total1 = String.valueOf(int_total1);
                String s_total2 = String.valueOf(int_total2);
                String s_total3 = String.valueOf(int_total3);
                m_ed_total1.setText(s_total1);
                m_ed_total2.setText(s_total2);
                m_ed_total3.setText(s_total3);
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
