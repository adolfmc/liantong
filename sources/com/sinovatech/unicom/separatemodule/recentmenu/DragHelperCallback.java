package com.sinovatech.unicom.separatemodule.recentmenu;

import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import android.util.Property;
import android.view.View;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DragHelperCallback extends ItemTouchHelper.Callback {
    private View collectArea;
    private View delArea;
    private MiniGridAdpater mAdapter;
    private DragListener mDragListener;
    private RecyclerView.ViewHolder tempHolder;
    private boolean mIsInside = false;
    private boolean mIsCollectInside = false;
    private int delPos = -1;
    private float mScale = 1.2f;
    private float mAlpha = 1.0f;
    private float mInsideScale = 0.86f;
    private float mInsideAlpha = 0.3f;
    private float mMoveScale = this.mScale;
    private boolean mIsCanMove = true;
    private ScaleProperty scaleProperty = new ScaleProperty("scale");

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface DragListener {
        void onCollectAreaChange(boolean z, boolean z2);

        void onCollectFinish(boolean z, int i);

        void onDragAreaChange(boolean z, boolean z2);

        void onDragFinish(boolean z, int i);

        void onDragStart(int i);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public DragHelperCallback(@NonNull MiniGridAdpater miniGridAdpater, View view, View view2) {
        this.mAdapter = miniGridAdpater;
        this.delArea = view;
        this.collectArea = view2;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(15, 0);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        List<RecentMiniEntity> list;
        if (viewHolder.getItemViewType() == viewHolder2.getItemViewType() && this.mIsCanMove && (list = this.mAdapter.getList()) != null && list.size() >= 2) {
            int adapterPosition = viewHolder.getAdapterPosition();
            int adapterPosition2 = viewHolder2.getAdapterPosition();
            this.delPos = adapterPosition2;
            Collections.swap(list, adapterPosition, adapterPosition2);
            this.mAdapter.notifyItemMoved(adapterPosition, adapterPosition2);
            return true;
        }
        return false;
    }

    public void setmIsCanMove(boolean z) {
        this.mIsCanMove = z;
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public long getAnimationDuration(RecyclerView recyclerView, int i, float f, float f2) {
        if (this.mIsInside || this.mIsCollectInside) {
            return 0L;
        }
        return super.getAnimationDuration(recyclerView, i, f, f2);
    }

    private void startActivatingAnim(View view, float f, float f2, long j) {
        if (view.getTag() instanceof ObjectAnimator) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, this.scaleProperty, f, f2);
        ofFloat.setDuration(j);
        ofFloat.start();
        view.setTag(ofFloat);
    }

    private boolean isActivatingAniming(View view) {
        Object tag = view.getTag();
        return (tag instanceof ObjectAnimator) && ((ObjectAnimator) tag).isRunning();
    }

    private void clearActivatingAnim(View view) {
        Object tag = view.getTag();
        if (tag instanceof ObjectAnimator) {
            ((ObjectAnimator) tag).cancel();
            view.setTag(null);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ScaleProperty extends Property<View, Float> {
        public ScaleProperty(String str) {
            super(Float.class, str);
        }

        @Override // android.util.Property
        public Float get(View view) {
            return Float.valueOf(view.getScaleX());
        }

        @Override // android.util.Property
        public void set(View view, Float f) {
            view.setScaleX(f.floatValue());
            view.setScaleY(f.floatValue());
        }
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i != 0) {
            clearActivatingAnim(viewHolder.itemView);
            startActivatingAnim(viewHolder.itemView, 1.0f, this.mScale, 200L);
            viewHolder.itemView.setAlpha(this.mAlpha);
            this.delPos = viewHolder.getAdapterPosition();
            this.tempHolder = viewHolder;
            DragListener dragListener = this.mDragListener;
            if (dragListener != null) {
                dragListener.onDragStart(this.delPos);
            }
        } else {
            DragListener dragListener2 = this.mDragListener;
            if (dragListener2 != null) {
                dragListener2.onDragFinish(this.mIsInside, this.delPos);
                this.mDragListener.onCollectFinish(this.mIsCollectInside, this.delPos);
            }
            this.delPos = -1;
            this.tempHolder = null;
        }
        super.onSelectedChanged(viewHolder, i);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        if (this.delArea == null || isActivatingAniming(viewHolder.itemView)) {
            super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
            return;
        }
        int width = this.delArea.getWidth();
        int height = this.delArea.getHeight();
        int[] iArr = new int[2];
        this.delArea.getLocationInWindow(iArr);
        int i2 = iArr[0];
        int i3 = iArr[1];
        int width2 = viewHolder.itemView.getWidth();
        int height2 = viewHolder.itemView.getHeight();
        int[] iArr2 = new int[2];
        viewHolder.itemView.getLocationInWindow(iArr2);
        int i4 = iArr2[0];
        int i5 = iArr2[1];
        float f3 = this.mMoveScale;
        int i6 = i4 + (((int) (width2 * f3)) / 2);
        int i7 = i5 + (((int) (height2 * f3)) / 2);
        boolean z2 = i7 > i3 && i7 < i3 + height && i6 > i2 && i6 < i2 + width;
        if (z2 != this.mIsInside) {
            if (this.tempHolder != null) {
                if (z2) {
                    this.mMoveScale = this.mInsideScale;
                    clearActivatingAnim(viewHolder.itemView);
                    startActivatingAnim(viewHolder.itemView, this.mScale, this.mInsideScale, 150L);
                    viewHolder.itemView.setAlpha(this.mInsideAlpha);
                } else {
                    this.mMoveScale = this.mScale;
                    clearActivatingAnim(viewHolder.itemView);
                    startActivatingAnim(viewHolder.itemView, this.mInsideScale, this.mScale, 150L);
                    viewHolder.itemView.setAlpha(this.mAlpha);
                }
            }
            DragListener dragListener = this.mDragListener;
            if (dragListener != null) {
                dragListener.onDragAreaChange(z2, this.tempHolder == null);
            }
        }
        this.mIsInside = z2;
        int width3 = this.collectArea.getWidth();
        int height3 = this.collectArea.getHeight();
        int[] iArr3 = new int[2];
        this.collectArea.getLocationInWindow(iArr3);
        int i8 = iArr3[0];
        int i9 = iArr3[1];
        boolean z3 = i7 > i9 && i7 < i9 + height3 && i6 > i8 && i6 < i8 + width3;
        if (z3 != this.mIsCollectInside) {
            if (this.tempHolder != null) {
                if (z3) {
                    this.mMoveScale = this.mInsideScale;
                    clearActivatingAnim(viewHolder.itemView);
                    startActivatingAnim(viewHolder.itemView, this.mScale, this.mInsideScale, 150L);
                    viewHolder.itemView.setAlpha(this.mInsideAlpha);
                } else {
                    this.mMoveScale = this.mScale;
                    clearActivatingAnim(viewHolder.itemView);
                    startActivatingAnim(viewHolder.itemView, this.mInsideScale, this.mScale, 150L);
                    viewHolder.itemView.setAlpha(this.mAlpha);
                }
            }
            DragListener dragListener2 = this.mDragListener;
            if (dragListener2 != null) {
                dragListener2.onCollectAreaChange(z3, this.tempHolder == null);
            }
        }
        this.mIsCollectInside = z3;
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
    }

    @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        clearActivatingAnim(viewHolder.itemView);
        startActivatingAnim(viewHolder.itemView, this.mScale, 1.0f, 150L);
        viewHolder.itemView.setAlpha(1.0f);
        super.clearView(recyclerView, viewHolder);
    }

    public void setDragListener(DragListener dragListener) {
        this.mDragListener = dragListener;
    }

    public void setScale(float f) {
        this.mScale = f;
        this.mMoveScale = this.mScale;
    }

    public void setAlpha(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        this.mAlpha = f;
    }
}
