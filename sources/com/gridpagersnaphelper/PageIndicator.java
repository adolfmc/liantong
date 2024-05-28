package com.gridpagersnaphelper;

import android.support.p086v7.widget.RecyclerView;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface PageIndicator extends OnPageChangeListener {
    void notifyDataSetChanged();

    void setCurrentItem(int i);

    void setOnPageChangeListener(OnPageChangeListener onPageChangeListener);

    void setPageColumn(int i);

    void setRecyclerView(RecyclerView recyclerView);

    void setRecyclerView(RecyclerView recyclerView, int i);
}
