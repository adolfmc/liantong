package com.sinovatech.unicom.separatemodule.dialog.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class AppAdapter<T> extends BaseAdapter<BaseAdapter<?>.ViewHolder> {
    private List<T> mDataSet;
    private boolean mLastPage;
    private int mPageNumber;
    private Object mTag;

    public AppAdapter(@NonNull Context context) {
        super(context);
        this.mPageNumber = 1;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getCount();
    }

    public int getCount() {
        List<T> list = this.mDataSet;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(@Nullable List<T> list) {
        this.mDataSet = list;
        notifyDataSetChanged();
    }

    @Nullable
    public List<T> getData() {
        return this.mDataSet;
    }

    public void addData(List<T> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        List<T> list2 = this.mDataSet;
        if (list2 == null || list2.size() == 0) {
            setData(list);
            return;
        }
        this.mDataSet.addAll(list);
        notifyItemRangeInserted(this.mDataSet.size() - list.size(), list.size());
    }

    public void clearData() {
        List<T> list = this.mDataSet;
        if (list == null || list.size() == 0) {
            return;
        }
        this.mDataSet.clear();
        notifyDataSetChanged();
    }

    public boolean containsItem(@IntRange(from = 0) int i) {
        return containsItem((AppAdapter<T>) getItem(i));
    }

    public boolean containsItem(T t) {
        List<T> list = this.mDataSet;
        if (list == null || t == null) {
            return false;
        }
        return list.contains(t);
    }

    public T getItem(@IntRange(from = 0) int i) {
        List<T> list = this.mDataSet;
        if (list == null) {
            return null;
        }
        return list.get(i);
    }

    public void setItem(@IntRange(from = 0) int i, @NonNull T t) {
        if (this.mDataSet == null) {
            this.mDataSet = new ArrayList();
        }
        this.mDataSet.set(i, t);
        notifyItemChanged(i);
    }

    public void addItem(@NonNull T t) {
        if (this.mDataSet == null) {
            this.mDataSet = new ArrayList();
        }
        addItem(this.mDataSet.size(), t);
    }

    public void addItem(@IntRange(from = 0) int i, @NonNull T t) {
        if (this.mDataSet == null) {
            this.mDataSet = new ArrayList();
        }
        if (i < this.mDataSet.size()) {
            this.mDataSet.add(i, t);
        } else {
            this.mDataSet.add(t);
            i = this.mDataSet.size() - 1;
        }
        notifyItemInserted(i);
    }

    public void removeItem(@NonNull T t) {
        int indexOf = this.mDataSet.indexOf(t);
        if (indexOf != -1) {
            removeItem(indexOf);
        }
    }

    public void removeItem(@IntRange(from = 0) int i) {
        this.mDataSet.remove(i);
        notifyItemRemoved(i);
    }

    public int getPageNumber() {
        return this.mPageNumber;
    }

    public void setPageNumber(@IntRange(from = 0) int i) {
        this.mPageNumber = i;
    }

    public boolean isLastPage() {
        return this.mLastPage;
    }

    public void setLastPage(boolean z) {
        this.mLastPage = z;
    }

    @Nullable
    public Object getTag() {
        return this.mTag;
    }

    public void setTag(@NonNull Object obj) {
        this.mTag = obj;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public final class SimpleHolder extends BaseAdapter<BaseAdapter<?>.ViewHolder>.ViewHolder {
        @Override // com.sinovatech.unicom.separatemodule.dialog.adapter.BaseAdapter.ViewHolder
        public void onBindView(int i) {
        }

        public SimpleHolder(int i) {
            super(AppAdapter.this, i);
        }

        public SimpleHolder(View view) {
            super(view);
        }
    }
}
