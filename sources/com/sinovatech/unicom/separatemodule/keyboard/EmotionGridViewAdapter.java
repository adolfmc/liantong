package com.sinovatech.unicom.separatemodule.keyboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EmotionGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> emotionNames;
    private int emotion_map_type;
    private int itemWidth;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public EmotionGridViewAdapter(Context context, List<String> list, int i, int i2) {
        this.context = context;
        this.emotionNames = list;
        this.itemWidth = i;
        this.emotion_map_type = i2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.emotionNames.size() + 1;
    }

    @Override // android.widget.Adapter
    public String getItem(int i) {
        return this.emotionNames.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(this.context);
        int i2 = this.itemWidth;
        imageView.setPadding(i2 / 8, i2 / 8, i2 / 8, i2 / 8);
        int i3 = this.itemWidth;
        imageView.setLayoutParams(new AbsListView.LayoutParams(i3, i3));
        if (i == getCount() - 1) {
            imageView.setImageResource(2131231107);
        } else {
            imageView.setImageResource(EmotionUtils.getImgByName(this.emotion_map_type, this.emotionNames.get(i)));
        }
        return imageView;
    }
}
