package com.ourflettership.anstice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity{

    public static final int 項目長度 = 7;

    // 資料寄放 Intent 所需要的 KEY
    public static final String KEY_名稱 = "名稱";
    public static final String KEY_描述 = "描述";
    public static final String KEY_DRAWABLE_ID = "圖片";
    public static final String KEY_份 = "份";
    public static final String KEY_材料 = "材料";
    public static final String KEY_重量單位 = "重量單位";
    public static final String KEY_總重量單位 = "總重量單位";
    public static final String KEY_重量 = "重量";
    public static final String KEY_總重量 = "總重量";

    // 食譜資料
    protected String name;
    protected String describe;
    protected int drawableId;
    protected String copy;         // 為什麼不能打Integer
    protected String[] ingredients = new String[項目長度];
    protected String[] wUnits = new String[項目長度];
    protected String[] tUnits = new String[項目長度];
    protected String[] weights = new String[項目長度];
    protected String[] totals = new String[項目長度];

    protected EditText m_ed_名稱;
    protected EditText m_ed_描述;
    protected ImageButton m_ib_圖片;
    protected EditText m_ed_份;
    protected EditText[] mEd材料 = new EditText[項目長度];
    protected EditText[] mEd重量單位 = new EditText[項目長度];
    protected EditText[] mEd總重量單位 = new EditText[項目長度];
    protected EditText[] mEd重量 = new EditText[項目長度];
    protected EditText[] mEd總重量 = new EditText[項目長度];

    public static final int 未選圖預設值 = -1;
    private static final int 請求碼 = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();

        // 給 KEY 才能取能內容  (回想 map)
        if (savedInstanceState != null){
            name = savedInstanceState.getString(KEY_名稱);
            describe = savedInstanceState.getString(KEY_描述);
            drawableId = savedInstanceState.getInt(KEY_DRAWABLE_ID);
            m_ib_圖片.setImageResource(drawableId);                // Maybe 是圖檔資源較大, 需要 setImageResource
            copy = savedInstanceState.getString(KEY_份);                    // 為什麼不能打 getInt

            ingredients = savedInstanceState.getStringArray(KEY_材料);
        }



    }

    // 初始化
    private void init() {
        m_ed_名稱 = findViewById(R.id.ed_名稱);
        m_ed_描述 = findViewById(R.id.ed_描述);
        m_ib_圖片 = findViewById(R.id.ib_圖片);
        m_ed_份 = findViewById(R.id.ed_份);
        drawableId = 未選圖預設值;

        if(drawableId == 未選圖預設值){
            drawableId = R.drawable.no_image;                   // 設定預設圖片
        }

        int[] ingre = {R.id.ed_ingredient1, R.id.ed_ingredient2, R.id.ed_ingredient3, R.id.ed_ingredient4, R.id.ed_ingredient5, R.id.ed_ingredient6, R.id.ed_ingredient7};
        for (int i=0; i<ingredients.length; i++){
            mEd材料[i] = findViewById(ingre[i]);
        }

        int[] wU = {R.id.ed_wUnit1, R.id.ed_wUnit2, R.id.ed_wUnit3, R.id.ed_wUnit4, R.id.ed_wUnit5, R.id.ed_wUnit6, R.id.ed_wUnit7};
        for (int i=0; i<wUnits.length; i++){
            mEd重量單位[i] = findViewById(wU[i]);
        }

        int[] tU = {R.id.ed_tUnit1, R.id.ed_tUnit2, R.id.ed_tUnit3, R.id.ed_tUnit4, R.id.ed_tUnit5, R.id.ed_tUnit6, R.id.ed_tUnit7};
        for (int i=0; i<tUnits.length; i++){
            mEd總重量單位[i] = findViewById(tU[i]);
        }

        int[] wei = {R.id.ed_weight1, R.id.ed_weight2, R.id.ed_weight3, R.id.ed_weight4, R.id.ed_weight5, R.id.ed_weight6, R.id.ed_weight7};
        for (int i=0; i<weights.length; i++){
            mEd重量[i] = findViewById(wei[i]);
        }

        int[] tot = {R.id.ed_total1, R.id.ed_total2, R.id.ed_total3, R.id.ed_total4, R.id.ed_total5, R.id.ed_total6, R.id.ed_total7};
        for (int i=0; i<totals.length; i++){
            mEd總重量[i] = findViewById(tot[i]);
        }

    }

       // 暫存資料
    @Override
    protected void onSaveInstanceState (Bundle outState){
        outState.putString(KEY_名稱, name);
        outState.putString(KEY_描述, describe);
        outState.putInt(KEY_DRAWABLE_ID, drawableId);
        outState.putString(KEY_份, copy);
        outState.putStringArray(KEY_材料, ingredients);
        outState.putStringArray(KEY_重量單位, wUnits);
        outState.putStringArray(KEY_總重量單位, tUnits);
        outState.putStringArray(KEY_重量, weights);
        outState.putStringArray(KEY_總重量, totals);

        super.onSaveInstanceState(outState);
    }

    // 往 ChooseListActivity
    public void clickib(View view) {
        Intent intent = new Intent(this,ChooseListActivity.class);
        startActivityForResult(intent, 請求碼);
    }

    // 獲得圖片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 請求碼 && resultCode == RESULT_OK){
            int resId = data.getIntExtra(ChooseListActivity.KEY, 未選圖預設值);  // 取得圖片id
            if(resId != 未選圖預設值){
                m_ib_圖片.setImageResource(resId);                   // 設定圖片
                drawableId = resId;
            }

        }

    }



    // 確認、取消
    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                name = m_ed_名稱.getText().toString();
                describe = m_ed_描述.getText().toString();
                copy = m_ed_份.getText().toString();

                for (int i=0; i<ingredients.length; i++) {
                    ingredients[i] = mEd材料[i].getText().toString();
                }
                for (int i=0; i<wUnits.length; i++) {
                    wUnits[i] = mEd重量單位[i].getText().toString();
                }
                for (int i=0; i<tUnits.length; i++) {
                    tUnits[i] = mEd總重量單位[i].getText().toString();
                }
                for (int i=0; i<weights.length; i++) {
                    weights[i] = mEd重量[i].getText().toString();
                }
                for (int i=0; i<totals.length; i++) {
                    totals[i] = mEd總重量[i].getText().toString();
                }




                Intent intent = getIntent();
                intent.putExtra(KEY_名稱, name);
                intent.putExtra(KEY_描述, describe);
                intent.putExtra(KEY_份, copy);
                intent.putExtra(KEY_DRAWABLE_ID, drawableId);
                intent.putExtra(KEY_材料, ingredients);
                intent.putExtra(KEY_重量單位, wUnits);
                intent.putExtra(KEY_總重量單位, tUnits);
                intent.putExtra(KEY_重量, weights);
                intent.putExtra(KEY_總重量, totals);
                setResult(RESULT_OK, intent);
                break;

            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }

    public void clickCal(View view) {
        for (int i=0; i<wUnits.length; i++) {
            wUnits[i] = mEd重量單位[i].getText().toString();
            mEd總重量單位[i].setText(wUnits[i]);
        }

        copy = m_ed_份.getText().toString();
        float f = Float.parseFloat(copy);
        float[] ftotals = new float[項目長度];
        String[] stotals = new String[項目長度];
        String s = ".";

        for (int i=0; i<weights.length; i++) {
            weights[i] = mEd重量[i].getText().toString();
            if (weights[i] != null && weights[i].length() > 0){
                if (weights[i].matches(".")){
                    mEd總重量[i].setText(weights[i]);
                } else {
                    ftotals[i] = Float.parseFloat(weights[i]) * f;
                    stotals[i] = Float.toString(ftotals[i]);
                    mEd總重量[i].setText(stotals[i]);
                }

            }

        }


    }


    // 選取圖片後呼叫此函式
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
//        if (requestCode == 請求 && data != null)
//        {
//            //取得照片路徑uri
//            Uri uri = data.getData();
//            //外界的程式訪問ContentProvider所提供數據 可以通過ContentResolver介面
//            ContentResolver cr = this.getContentResolver();
//
//            try {
//                //由抽象資料接口轉換圖檔路徑為Bitmap
//                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
//                // 將Bitmap設定到ImageView
//                m_ib_圖片.setImageBitmap(bitmap);
//            } catch (FileNotFoundException e) {
//                Log.e("Exception", e.getMessage(),e);
//            }
//
//        }
//
//
//    }


}
