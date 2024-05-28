package com.sinovatech.unicom.separatemodule.playdetails.channel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.StaggeredGridLayoutManager;
import android.support.p086v7.widget.helper.ItemTouchHelper;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyItemTouchHandler extends ItemTouchHelper.Callback {
    ItemTouchAdapterImpl adapter;
    int frozenCount;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static abstract class ItemTouchAdapterImpl extends RecyclerView.Adapter {
        protected boolean autoOpenDrag() {
            return true;
        }

        protected boolean autoOpenSwipe() {
            return true;
        }

        public abstract void onItemMove(int i, int i2);

        public abstract void onItemRemove(int i);
    }

    public MyItemTouchHandler(@NonNull ItemTouchAdapterImpl itemTouchAdapterImpl, int i) {
        this.adapter = itemTouchAdapterImpl;
        this.frozenCount = i;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getAdapterPosition() < this.frozenCount) {
            return makeMovementFlags(0, 0);
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        return makeMovementFlags(((layoutManager instanceof GridLayoutManager) || (layoutManager instanceof StaggeredGridLayoutManager)) ? 15 : 3, 0);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (viewHolder.getItemViewType() == viewHolder2.getItemViewType()) {
            int adapterPosition = viewHolder.getAdapterPosition();
            int adapterPosition2 = viewHolder2.getAdapterPosition();
            int i = this.frozenCount;
            if (adapterPosition < i || adapterPosition2 < i) {
                return false;
            }
            if (adapterPosition < adapterPosition2) {
                int i2 = adapterPosition;
                while (i2 < adapterPosition2) {
                    int i3 = i2 + 1;
                    this.adapter.onItemMove(i2, i3);
                    i2 = i3;
                }
            } else {
                for (int i4 = adapterPosition; i4 > adapterPosition2; i4--) {
                    this.adapter.onItemMove(i4, i4 - 1);
                }
            }
            this.adapter.notifyItemMoved(adapterPosition, adapterPosition2);
            return true;
        }
        return false;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        this.adapter.onItemRemove(viewHolder.getAdapterPosition());
        this.adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
        if (i == 1) {
            viewHolder.itemView.setAlpha(1.0f - (Math.abs(f) / viewHolder.itemView.getWidth()));
            viewHolder.itemView.setTranslationX(f);
        }
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 2) {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#fff5f5f5"));
        } else if (i == 1) {
            viewHolder.itemView.setBackgroundColor(-65536);
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setBackgroundColor(0);
        super.clearView(recyclerView, viewHolder);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return this.adapter.autoOpenDrag();
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return this.adapter.autoOpenSwipe();
    }
}
