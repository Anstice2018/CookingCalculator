package com.ourflettership.anstice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// extends AddActivity
public class UpdateActivity extends AddActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update);

        init();
    }

    // 初始化
    private void init(){
        // 取得 MainActivity 傳來 食譜序列化資料
        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra(MainActivity.RECIPE_KEY);

        // 將資料顯示 (下面所使用的欄位是繼承來的，在父類別 AddActivity 宣告欄位需使用 protected)
        m_ed_名稱.setText(recipe.getName());
        m_ed_描述.setText(recipe.getDescribe());
        drawableId = recipe.getDrawableId();
        m_ib_圖片.setImageResource(drawableId);
        m_ed_份.setText(recipe.getCopy());

        ingredients = recipe.getIngredients();
        for (int i=0; i<ingredients.length; i++) {
            mEd材料[i].setText(ingredients[i]);
        }
        wUnits = recipe.getwUnits();
        for (int i=0; i<wUnits.length; i++) {
            mEd重量單位[i].setText(wUnits[i]);
        }
        tUnits = recipe.gettUnits();
        for (int i=0; i<tUnits.length; i++) {
            mEd總重量單位[i].setText(tUnits[i]);
        }

        weights = recipe.getWeights();
        for (int i=0; i<weights.length; i++) {
            mEd重量[i].setText(weights[i]);
        }
        totals = recipe.getTotals();
        for (int i=0; i<totals.length; i++) {
            mEd總重量[i].setText(totals[i]);
        }



    }

    // 確認、取消
    public void click(View view) {
        switch (view.getId()){
            case R.id.btn_confirm:
                // 建立食譜資料
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
                Recipe recipe = new Recipe(name, describe, drawableId, copy, ingredients, wUnits, tUnits, weights, totals);

                // 更新 Intent 的食譜資料
                Intent intent = getIntent();
                intent.putExtra(MainActivity.RECIPE_KEY, recipe);
                setResult(RESULT_OK, intent);
                break;

            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }



}

