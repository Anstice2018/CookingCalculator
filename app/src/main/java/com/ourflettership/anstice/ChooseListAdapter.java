package com.ourflettership.anstice;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anstice on 2018/9/6.
 */

public class ChooseListAdapter extends BaseAdapter {

    // 圖片資源 id 陣列
    private int[] drawableIds ={
            R.drawable.no_image, R.drawable.cake, R.drawable.cookies, R.drawable.doughnut,
            R.drawable.icecream, R.drawable.pie1, R.drawable.pie2
    };
    private Activity activity;



    // Constructor
    public ChooseListAdapter(Activity activity) {
        this.activity = activity;
    }

    // getter
    public int[] getDrawableIds() {
        return drawableIds;
    }




    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override       // position 代表目前 ListView 要索取的是第幾項
    public View getView(int position, View convertView, ViewGroup parent) {

        // 跟 Activity 借打氣筒，將 Layout 灌入 View
        View v = activity.getLayoutInflater().inflate(R.layout.listview_choose_layout, null);

        TextView m_tv_itemId = v.findViewById(R.id.tv_itemId);
        ImageView m_iv_itemImage = v.findViewById(R.id.iv_item);

        m_tv_itemId.setText(String.valueOf(position));
        m_iv_itemImage.setImageResource(drawableIds[position]);

        return v;}
}
