package com.sinovatech.unicom.basic.p315ui.fuwu.adapter;

import android.support.p086v7.widget.RecyclerView;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.ItemTouchHelperAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface ItemTouchHelperAdapter {
    void onItemClear(RecyclerView.ViewHolder viewHolder);

    void onItemDissmiss(RecyclerView.ViewHolder viewHolder);

    void onItemMove(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

    void onItemSelect(RecyclerView.ViewHolder viewHolder);
}
