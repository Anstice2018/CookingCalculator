package com.example.anstice;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anstice on 2018/8/29.
 */

public class MainListAdapter extends BaseAdapter {

    private Activity activity;
    private List<Recipe> list;

    public static final String TAG = "MainListAdapter";

    public MainListAdapter(MainActivity activity, ArrayList<Recipe> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "這是 size ＝ " + list.size());
        return list.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 跟 Activity 借打氣筒，將 layout 灌入 View
        View v = activity.getLayoutInflater().inflate(R.layout.listview_layout, null);

        TextView m_tv_itemName = v.findViewById(R.id.tv_itemName);
        ImageView m_iv_itemImage = v.findViewById(R.id.iv_itemPicture);

        Recipe recipe = list.get(position);     // 取得陣列第 i 項物件

        m_tv_itemName.setText(recipe.getName());
        //m_iv_itemImage.setImageResource(recipe.getDrawableId());
        return v;
    }
}
