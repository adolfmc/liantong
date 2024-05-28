package com.sinovatech.unicom.basic.p315ui.fuwu.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import com.sinovatech.unicom.basic.p315ui.fuwu.adapter.ItemTouchHelperAdapter;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.utils.SimpleItemTouchHelperCallback */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter mAdapter;

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    }

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.mAdapter = itemTouchHelperAdapter;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int i;
        int i2 = 0;
        if (FuWuConstant.isBianJiState) {
            if (FuWuUtils.isTrue(viewHolder.getAdapterPosition())) {
                i2 = 15;
                i = 48;
            } else {
                i = 0;
            }
            return makeMovementFlags(i2, i);
        }
        return makeMovementFlags(0, 0);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
        this.mAdapter.onItemMove(viewHolder, viewHolder2);
        return true;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
        super.onSelectedChanged(viewHolder, i);
        if (i != 0) {
            this.mAdapter.onItemSelect(viewHolder);
        }
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (recyclerView.isComputingLayout()) {
            return;
        }
        this.mAdapter.onItemClear(viewHolder);
    }
}
