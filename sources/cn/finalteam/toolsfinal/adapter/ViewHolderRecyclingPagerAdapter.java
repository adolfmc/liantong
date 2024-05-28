package cn.finalteam.toolsfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.finalteam.toolsfinal.adapter.ViewHolderRecyclingPagerAdapter.ViewHolder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ViewHolderRecyclingPagerAdapter<VH extends ViewHolder, T> extends RecyclingPagerAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mList;

    public abstract void onBindViewHolder(VH vh, int i);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

    public ViewHolderRecyclingPagerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.finalteam.toolsfinal.adapter.RecyclingPagerAdapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = onCreateViewHolder(viewGroup, i);
            viewHolder.view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        onBindViewHolder(viewHolder, i);
        return viewHolder.view;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.mList.size();
    }

    public View inflate(int i, ViewGroup viewGroup) {
        return this.mInflater.inflate(i, viewGroup, false);
    }

    public List<T> getDatas() {
        return this.mList;
    }

    public Context getContext() {
        return this.mContext;
    }

    public LayoutInflater getLayoutInflater() {
        return this.mInflater;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ViewHolder {
        View view;

        public ViewHolder(View view) {
            this.view = view;
        }
    }
}
