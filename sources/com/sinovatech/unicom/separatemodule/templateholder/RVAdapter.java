package com.sinovatech.unicom.separatemodule.templateholder;

import android.app.Activity;
import android.support.p086v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RVAdapter extends RecyclerView.Adapter<RVItemViewHolder> {
    private static final String TAG = "RVAdapter";
    private Activity activityContext;
    private List<RVItemEntity> dataSource;
    private OnItemClickListener listener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public RVAdapter(Activity activity, List<RVItemEntity> list) {
        this.activityContext = activity;
        this.dataSource = list;
    }

    public void changAdapter(List<RVItemEntity> list) {
        this.dataSource = list;
        notifyDataSetChanged();
    }

    public void setOnItemListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        Log.d(TAG, "getItemCount() called==" + this.dataSource.size());
        return this.dataSource.size();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.dataSource.get(i) == null) {
            return -1;
        }
        int viewTypeByTemplate = RVItemViewHolderFactory.getFactory(this.activityContext).getViewTypeByTemplate(this.dataSource.get(i).templateName);
        Log.d(TAG, "getItemViewType() called with: type = [" + viewTypeByTemplate + "]");
        return viewTypeByTemplate;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public RVItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return RVItemViewHolderFactory.getFactory(this.activityContext).createViewHolderByViewType(this.activityContext, viewGroup, i);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RVItemViewHolder rVItemViewHolder, int i) {
        if (this.dataSource.get(i) == null) {
            return;
        }
        rVItemViewHolder.bindData(this.dataSource.get(i));
    }
}
