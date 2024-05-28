package com.sinovatech.unicom.basic.p315ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.sinovatech.unicom.basic.p314po.CitySelectEntity;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.SortGroupMemberAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SortGroupMemberAdapter extends BaseAdapter implements SectionIndexer {
    private boolean isFilt;
    private List<CitySelectEntity> list;
    private List<CitySelectEntity> list2 = new ArrayList();
    private Context mContext;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.SectionIndexer
    public Object[] getSections() {
        return null;
    }

    public SortGroupMemberAdapter(Context context, List<CitySelectEntity> list) {
        this.list = null;
        this.mContext = context;
        this.list = list;
    }

    public void updateListView(List<CitySelectEntity> list, boolean z) {
        this.list = list;
        this.isFilt = z;
        notifyDataSetChanged();
    }

    public void updateListView(List<CitySelectEntity> list) {
        updateListView(list, false);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        View currentFocus = ((Activity) this.mContext).getCurrentFocus();
        if (currentFocus != null) {
            currentFocus.clearFocus();
        }
        CitySelectEntity citySelectEntity = this.list.get(i);
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = LayoutInflater.from(this.mContext).inflate(2131493038, (ViewGroup) null);
            viewHolder.tvTitle = (TextView) view2.findViewById(2131298785);
            viewHolder.tvLetter = (TextView) view2.findViewById(2131296580);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i == getPositionForSection(getSectionForPosition(i))) {
            viewHolder.tvLetter.setVisibility(0);
            viewHolder.tvLetter.setText(citySelectEntity.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(8);
        }
        viewHolder.tvTitle.setText(this.list.get(i).getCityName());
        if (this.isFilt) {
            viewHolder.tvLetter.setVisibility(8);
        }
        return view2;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.SortGroupMemberAdapter$ViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;

        ViewHolder() {
        }
    }

    @Override // android.widget.SectionIndexer
    public int getSectionForPosition(int i) {
        return this.list.get(i).getSortLetters().charAt(0);
    }

    @Override // android.widget.SectionIndexer
    public int getPositionForSection(int i) {
        for (int i2 = 0; i2 < getCount(); i2++) {
            if (this.list.get(i2).getSortLetters().toUpperCase().charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }

    private String getAlpha(String str) {
        String upperCase = str.trim().substring(0, 1).toUpperCase();
        return upperCase.matches("[A-Z]") ? upperCase : "#";
    }
}
