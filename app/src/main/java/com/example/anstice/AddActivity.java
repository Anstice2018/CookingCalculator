package com.example.anstice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

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
