package cn.finalteam.toolsfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.finalteam.toolsfinal.adapter.ViewHolderAdapter.ViewHolder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ViewHolderAdapter<VH extends ViewHolder, T> extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mList;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public abstract void onBindViewHolder(VH vh, int i);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup, int i);

    public ViewHolderAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // android.widget.Adapter
    public T getItem(int i) {
        return this.mList.get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
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
