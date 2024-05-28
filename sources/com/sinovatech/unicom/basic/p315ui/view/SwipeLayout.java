package com.sinovatech.unicom.basic.p315ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.support.p083v4.view.ViewCompat;
import android.support.p083v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

@SuppressLint({"ClickableViewAccessibility"})
/* renamed from: com.sinovatech.unicom.basic.ui.view.SwipeLayout */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SwipeLayout extends FrameLayout {
    private View backView;
    private ViewDragHelper.Callback callback;
    private ViewDragHelper dragHelper;
    private View frontView;
    private int height;
    private boolean isOpen;
    private int range;
    private Status status;
    private OnSwipeChangeListener swipeChangeListener;
    private int width;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.SwipeLayout$OnSwipeChangeListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnSwipeChangeListener {
        void onClose(SwipeLayout swipeLayout);

        void onDraging(SwipeLayout swipeLayout);

        void onOpen(SwipeLayout swipeLayout);

        void onStartClose(SwipeLayout swipeLayout);

        void onStartOpen(SwipeLayout swipeLayout);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.SwipeLayout$Status */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum Status {
        OPEN,
        CLOSE,
        DRAGING
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwipeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.status = Status.CLOSE;
        this.isOpen = false;
        this.callback = new ViewDragHelper.Callback() { // from class: com.sinovatech.unicom.basic.ui.view.SwipeLayout.1
            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int getViewHorizontalDragRange(View view) {
                return 1;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                return 1;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i2) {
                return true;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i2, int i3) {
                if (view == SwipeLayout.this.frontView) {
                    if (i2 > 0) {
                        return 0;
                    }
                    if (i2 < (-SwipeLayout.this.range)) {
                        return -SwipeLayout.this.range;
                    }
                } else if (view == SwipeLayout.this.backView) {
                    if (i2 > SwipeLayout.this.width) {
                        return SwipeLayout.this.width;
                    }
                    if (i2 < SwipeLayout.this.width - SwipeLayout.this.range) {
                        return SwipeLayout.this.width - SwipeLayout.this.range;
                    }
                }
                return i2;
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
                if (view == SwipeLayout.this.frontView) {
                    SwipeLayout.this.backView.offsetLeftAndRight(i4);
                } else if (view == SwipeLayout.this.backView) {
                    SwipeLayout.this.frontView.offsetLeftAndRight(i4);
                }
                SwipeLayout.this.dispatchSwipeEvent();
                SwipeLayout.this.invalidate();
            }

            @Override // android.support.p083v4.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f, float f2) {
                if (f == 0.0f && SwipeLayout.this.frontView.getLeft() < (-SwipeLayout.this.range) * 0.2f) {
                    SwipeLayout.this.open();
                } else if (f < 0.0f) {
                    SwipeLayout.this.open();
                } else {
                    SwipeLayout.this.close();
                }
            }
        };
        this.dragHelper = ViewDragHelper.create(this, this.callback);
    }

    protected void dispatchSwipeEvent() {
        Status status = this.status;
        this.status = updateStatus();
        OnSwipeChangeListener onSwipeChangeListener = this.swipeChangeListener;
        if (onSwipeChangeListener != null) {
            onSwipeChangeListener.onDraging(this);
        }
        Status status2 = this.status;
        if (status == status2 || this.swipeChangeListener == null) {
            return;
        }
        if (status2 == Status.CLOSE) {
            this.swipeChangeListener.onClose(this);
        } else if (this.status == Status.OPEN) {
            this.swipeChangeListener.onOpen(this);
        } else if (this.status == Status.DRAGING) {
            if (status == Status.CLOSE) {
                this.swipeChangeListener.onStartOpen(this);
            } else if (status == Status.OPEN) {
                this.swipeChangeListener.onStartClose(this);
            }
        }
    }

    private Status updateStatus() {
        int left = this.frontView.getLeft();
        if (left == 0) {
            return Status.CLOSE;
        }
        if (left == (-this.range)) {
            return Status.OPEN;
        }
        return Status.DRAGING;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void open() {
        open(true);
    }

    public void open(boolean z) {
        int i = -this.range;
        if (z) {
            if (this.dragHelper.smoothSlideViewTo(this.frontView, i, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
            }
        } else {
            layoutContent(true);
        }
        this.isOpen = true;
    }

    public void close() {
        close(true);
        this.isOpen = false;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void close(boolean z) {
        if (z) {
            if (this.dragHelper.smoothSlideViewTo(this.frontView, 0, 0)) {
                ViewCompat.postInvalidateOnAnimation(this);
                return;
            }
            return;
        }
        layoutContent(false);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        layoutContent(false);
    }

    private void layoutContent(boolean z) {
        Rect computeFrontViewRect = computeFrontViewRect(z);
        this.frontView.layout(computeFrontViewRect.left, computeFrontViewRect.top, computeFrontViewRect.right, computeFrontViewRect.bottom);
        Rect computeBackViewRect = computeBackViewRect(computeFrontViewRect);
        this.backView.layout(computeBackViewRect.left, computeBackViewRect.top, computeBackViewRect.right, computeBackViewRect.bottom);
    }

    private Rect computeBackViewRect(Rect rect) {
        int i = rect.right;
        return new Rect(i, 0, this.range + i, this.height);
    }

    private Rect computeFrontViewRect(boolean z) {
        int i = z ? -this.range : 0;
        return new Rect(i, 0, this.width + i, this.height);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() < 2) {
            throw new IllegalStateException("you need 2 children view");
        }
        if (!(getChildAt(0) instanceof ViewGroup) || !(getChildAt(1) instanceof ViewGroup)) {
            throw new IllegalArgumentException("your children must be instance of ViewGroup");
        }
        this.backView = getChildAt(0);
        this.frontView = getChildAt(1);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.height = this.frontView.getMeasuredHeight();
        this.width = this.frontView.getMeasuredWidth();
        this.range = this.backView.getMeasuredWidth();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.dragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.dragHelper.processTouchEvent(motionEvent);
        return true;
    }

    public void setSwipeChangeListener(OnSwipeChangeListener onSwipeChangeListener) {
        this.swipeChangeListener = onSwipeChangeListener;
    }
}
