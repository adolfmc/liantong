package com.billy.android.swipe.consumer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.internal.ScrimView;
import com.billy.android.swipe.internal.SwipeHelper;
import com.billy.android.swipe.internal.SwipeUtil;
import com.billy.android.swipe.internal.ViewCompat;
import com.billy.android.swipe.listener.SwipeListener;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DrawerConsumer extends SwipeConsumer implements View.OnClickListener {

    /* renamed from: b */
    protected int f8211b;

    /* renamed from: l */
    protected int f8212l;
    protected View mCurDrawerView;
    protected ScrimView mScrimView;
    protected int mShadowSize;
    protected boolean mShowScrimAndShadowOutsideContentView;

    /* renamed from: r */
    protected int f8213r;

    /* renamed from: t */
    protected int f8214t;
    protected final View[] mDrawerViews = new View[4];
    protected int mScrimColor = 0;
    protected int mShadowColor = 0;
    protected boolean mDrawerViewRequired = true;

    public DrawerConsumer() {
        setReleaseMode(3);
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onAttachToWrapper(SmartSwipeWrapper smartSwipeWrapper, SwipeHelper swipeHelper) {
        super.onAttachToWrapper(smartSwipeWrapper, swipeHelper);
        for (int i = 0; i < this.mDrawerViews.length; i++) {
            attachDrawerView(i);
        }
        if (this.mShadowSize == 0) {
            this.mShadowSize = SmartSwipe.dp2px(10, smartSwipeWrapper.getContext());
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void initChildrenFormXml() {
        SmartSwipeWrapper smartSwipeWrapper = this.mWrapper;
        int childCount = smartSwipeWrapper.getChildCount();
        View contentView = smartSwipeWrapper.getContentView();
        for (int i = 0; i < childCount; i++) {
            View childAt = smartSwipeWrapper.getChildAt(i);
            if (childAt != contentView && (childAt.getLayoutParams() instanceof SmartSwipeWrapper.LayoutParams)) {
                int i2 = ((SmartSwipeWrapper.LayoutParams) childAt.getLayoutParams()).gravity;
                if (this.mDrawerViews[0] == null && (i2 & 1) == 1) {
                    setLeftDrawerView(childAt);
                    this.mWrapper.consumeInflateFromXml();
                }
                if (this.mDrawerViews[1] == null && (i2 & 2) == 2) {
                    setRightDrawerView(childAt);
                    this.mWrapper.consumeInflateFromXml();
                }
                if (this.mDrawerViews[2] == null && (i2 & 4) == 4) {
                    setTopDrawerView(childAt);
                    this.mWrapper.consumeInflateFromXml();
                }
                if (this.mDrawerViews[3] == null && (i2 & 8) == 8) {
                    setBottomDrawerView(childAt);
                    this.mWrapper.consumeInflateFromXml();
                }
            }
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDetachFromWrapper() {
        View[] viewArr;
        super.onDetachFromWrapper();
        if (this.mScrimView != null) {
            this.mWrapper.removeView(this.mScrimView);
            this.mScrimView.setOnClickListener(null);
            this.mScrimView = null;
        }
        for (View view : this.mDrawerViews) {
            if (view != null) {
                this.mWrapper.removeView(view);
            }
        }
        this.mCurDrawerView = null;
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onOpened() {
        super.onOpened();
        ScrimView scrimView = this.mScrimView;
        if (scrimView == null || this.mShowScrimAndShadowOutsideContentView) {
            return;
        }
        scrimView.setOnClickListener(this);
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onClosed() {
        super.onClosed();
        if (this.mCurDrawerView != null) {
            changeDrawerViewVisibility(4);
        }
        ScrimView scrimView = this.mScrimView;
        if (scrimView != null) {
            scrimView.setOnClickListener(null);
            this.mScrimView.setClickable(false);
            this.mScrimView.setFocusable(false);
            this.mScrimView.setVisibility(8);
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public boolean tryAcceptMoving(int i, float f, float f2, float f3, float f4) {
        boolean tryAcceptMoving = super.tryAcceptMoving(i, f, f2, f3, f4);
        if (tryAcceptMoving && this.mCachedSwipeDistanceX == 0 && this.mCachedSwipeDistanceY == 0 && this.mDrawerViewRequired && getDrawerView(this.mDirection) == null) {
            return false;
        }
        return tryAcceptMoving;
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onSwipeAccepted(int i, boolean z, float f, float f2) {
        if (this.mCachedSwipeDistanceX == 0 && this.mCachedSwipeDistanceY == 0) {
            changeDrawerViewVisibility(4);
            this.mCurDrawerView = getDrawerView(this.mDirection);
            changeDrawerViewVisibility(0);
        }
        int i2 = this.mWidth;
        int i3 = this.mHeight;
        View view = this.mCurDrawerView;
        if (view != null) {
            i2 = view.getMeasuredWidth();
            i3 = this.mCurDrawerView.getMeasuredHeight();
        } else if (this.mDrawerViewRequired) {
            return;
        }
        if (!this.mOpenDistanceSpecified) {
            if ((this.mDirection & 3) > 0) {
                this.mOpenDistance = i2;
            } else {
                this.mOpenDistance = i3;
            }
        }
        calculateDrawerDirectionInitPosition(this.mDirection, i2, i3);
        changeDrawerViewVisibility(0);
        initScrimView();
        layoutChildren();
        orderChildren();
        super.onSwipeAccepted(i, z, f, f2);
    }

    protected void changeDrawerViewVisibility(int i) {
        View view = this.mCurDrawerView;
        if (view != null) {
            view.setVisibility(i);
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void setCurrentStateAsClosed() {
        this.mCurDrawerView = null;
        super.setCurrentStateAsClosed();
    }

    protected void initScrimView() {
        if (this.mScrimColor != 0 || (this.mShadowColor != 0 && this.mShadowSize > 0)) {
            if (this.mScrimView == null) {
                this.mScrimView = new ScrimView(this.mWrapper.getContext());
                this.mWrapper.addView(this.mScrimView);
            }
            this.mScrimView.setScrimColor(this.mScrimColor);
            if (this.mShadowColor != 0 && this.mShadowSize > 0) {
                this.mScrimView.setDirection(this.mDirection, this.mShadowColor, this.mShowScrimAndShadowOutsideContentView ? SwipeUtil.getReverseDirection(this.mDirection) : this.mDirection, this.mShadowSize, this.mWidth, this.mHeight);
            }
            this.mScrimView.setVisibility(0);
        }
    }

    protected void calculateDrawerDirectionInitPosition(int i, int i2, int i3) {
        if (i == 4) {
            this.f8212l = 0;
            this.f8213r = this.mWidth;
            this.f8214t = -i3;
            this.f8211b = this.f8214t + i3;
        } else if (i != 8) {
            switch (i) {
                case 1:
                    this.f8212l = -i2;
                    this.f8213r = this.f8212l + i2;
                    this.f8214t = 0;
                    this.f8211b = i3;
                    return;
                case 2:
                    this.f8212l = this.mWidth;
                    this.f8213r = this.f8212l + i2;
                    this.f8214t = 0;
                    this.f8211b = i3;
                    return;
                default:
                    return;
            }
        } else {
            this.f8212l = 0;
            this.f8213r = this.mWidth;
            this.f8214t = this.mHeight;
            this.f8211b = this.f8214t + i3;
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public boolean onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mWrapper != null) {
            layoutChildren();
            return true;
        }
        return false;
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
        View view = this.mCurDrawerView;
        if (view == null || view.getParent() != this.mWrapper) {
            return;
        }
        if ((this.mDirection & 3) > 0) {
            ViewCompat.offsetLeftAndRight(view, i3);
        } else {
            ViewCompat.offsetTopAndBottom(view, i4);
        }
        layoutScrimView();
    }

    protected void orderChildren() {
        View view = this.mCurDrawerView;
        if (view != null) {
            view.bringToFront();
        }
        ScrimView scrimView = this.mScrimView;
        if (scrimView != null) {
            scrimView.bringToFront();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void layoutChildren() {
        layoutContentView(this.mWrapper.getContentView());
        layoutDrawerView();
        layoutScrimView();
    }

    protected void layoutContentView(View view) {
        if (view != null) {
            view.layout(0, 0, this.mWidth, this.mHeight);
        }
    }

    protected void layoutDrawerView() {
        View view = this.mCurDrawerView;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.mCurDrawerView.layout(this.f8212l + this.mCurDisplayDistanceX, this.f8214t + this.mCurDisplayDistanceY, this.f8213r + this.mCurDisplayDistanceX, this.f8211b + this.mCurDisplayDistanceY);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void layoutScrimView() {
        /*
            r7 = this;
            com.billy.android.swipe.internal.ScrimView r0 = r7.mScrimView
            if (r0 == 0) goto L66
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L66
            int r0 = r7.mWidth
            int r1 = r7.mHeight
            boolean r2 = r7.mShowScrimAndShadowOutsideContentView
            r3 = 8
            r4 = 4
            r5 = 0
            if (r2 == 0) goto L33
            int r2 = r7.mDirection
            if (r2 == r4) goto L2f
            if (r2 == r3) goto L2b
            switch(r2) {
                case 1: goto L27;
                case 2: goto L20;
                default: goto L1f;
            }
        L1f:
            goto L3c
        L20:
            int r2 = r7.mCurDisplayDistanceX
            int r2 = r2 + r0
            r6 = r5
            r5 = r2
            r2 = r6
            goto L50
        L27:
            int r0 = r7.mCurDisplayDistanceX
            r2 = r5
            goto L50
        L2b:
            int r2 = r7.mCurDisplayDistanceY
            int r2 = r2 + r1
            goto L50
        L2f:
            int r1 = r7.mCurDisplayDistanceY
            r2 = r5
            goto L50
        L33:
            int r2 = r7.mDirection
            if (r2 == r4) goto L4e
            if (r2 == r3) goto L49
            switch(r2) {
                case 1: goto L43;
                case 2: goto L3e;
                default: goto L3c;
            }
        L3c:
            r2 = r5
            goto L50
        L3e:
            int r2 = r7.mCurDisplayDistanceX
            int r0 = r0 + r2
            r2 = r5
            goto L50
        L43:
            int r2 = r7.mCurDisplayDistanceX
            r6 = r5
            r5 = r2
            r2 = r6
            goto L50
        L49:
            int r2 = r7.mCurDisplayDistanceY
            int r1 = r1 + r2
            r2 = r5
            goto L50
        L4e:
            int r2 = r7.mCurDisplayDistanceY
        L50:
            com.billy.android.swipe.internal.ScrimView r3 = r7.mScrimView
            r3.layout(r5, r2, r0, r1)
            com.billy.android.swipe.internal.ScrimView r0 = r7.mScrimView
            boolean r1 = r7.mShowScrimAndShadowOutsideContentView
            if (r1 == 0) goto L61
            r1 = 1065353216(0x3f800000, float:1.0)
            float r2 = r7.mProgress
            float r1 = r1 - r2
            goto L63
        L61:
            float r1 = r7.mProgress
        L63:
            r0.setProgress(r1)
        L66:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.billy.android.swipe.consumer.DrawerConsumer.layoutScrimView():void");
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void notifySwipeStart() {
        View view = this.mCurDrawerView;
        if (view instanceof SwipeListener) {
            ((SwipeListener) view).onSwipeStart(this.mWrapper, this, this.mDirection);
        }
        super.notifySwipeStart();
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void notifySwipeProgress(boolean z) {
        View view = this.mCurDrawerView;
        if (view instanceof SwipeListener) {
            ((SwipeListener) view).onSwipeProcess(this.mWrapper, this, this.mDirection, z, this.mProgress);
        }
        super.notifySwipeProgress(z);
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public void notifySwipeRelease(float f, float f2) {
        View view = this.mCurDrawerView;
        if (view instanceof SwipeListener) {
            ((SwipeListener) view).onSwipeRelease(this.mWrapper, this, this.mDirection, this.mProgress, f, f2);
        }
        super.notifySwipeRelease(f, f2);
    }

    public View getDrawerView(int i) {
        char c;
        if (i == 4) {
            c = 2;
        } else if (i != 8) {
            switch (i) {
                case 1:
                    c = 0;
                    break;
                case 2:
                    c = 1;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            c = 3;
        }
        if (c < 0) {
            return null;
        }
        return this.mDrawerViews[c];
    }

    public DrawerConsumer setLeftDrawerView(View view) {
        return setDrawerView(1, view);
    }

    public DrawerConsumer setRightDrawerView(View view) {
        return setDrawerView(2, view);
    }

    public DrawerConsumer setTopDrawerView(View view) {
        return setDrawerView(4, view);
    }

    public DrawerConsumer setBottomDrawerView(View view) {
        return setDrawerView(8, view);
    }

    public DrawerConsumer setHorizontalDrawerView(View view) {
        return setDrawerView(3, view);
    }

    public DrawerConsumer setVerticalDrawerView(View view) {
        return setDrawerView(12, view);
    }

    public DrawerConsumer setAllDirectionDrawerView(View view) {
        return setDrawerView(15, view);
    }

    public DrawerConsumer setDrawerView(int i, View view) {
        enableDirection(i, view != null);
        if ((i & 1) > 0) {
            setOrUpdateDrawerView(0, view);
        }
        if ((i & 2) > 0) {
            setOrUpdateDrawerView(1, view);
        }
        if ((i & 4) > 0) {
            setOrUpdateDrawerView(2, view);
        }
        if ((i & 8) > 0) {
            setOrUpdateDrawerView(3, view);
        }
        return this;
    }

    private void setOrUpdateDrawerView(int i, View view) {
        View[] viewArr = this.mDrawerViews;
        if (viewArr[i] == view) {
            return;
        }
        viewArr[i] = view;
        attachDrawerView(i);
    }

    private void attachDrawerView(int i) {
        View view = this.mDrawerViews[i];
        SmartSwipeWrapper smartSwipeWrapper = this.mWrapper;
        if (view == null || smartSwipeWrapper == null || view.getParent() == smartSwipeWrapper) {
            return;
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        int indexOfChild = smartSwipeWrapper.indexOfChild(smartSwipeWrapper.getContentView());
        if (indexOfChild >= 0) {
            if (view.getLayoutParams() == null) {
                int i2 = -1;
                int i3 = -2;
                switch (i) {
                    case 0:
                    case 1:
                        break;
                    case 2:
                    case 3:
                        i3 = -1;
                        i2 = -2;
                        break;
                    default:
                        i2 = -2;
                        break;
                }
                view.setLayoutParams(new FrameLayout.LayoutParams(i3, i2));
            }
            smartSwipeWrapper.addView(view, indexOfChild);
            view.setVisibility(4);
        }
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public int getOpenDistance() {
        if (this.mCurDrawerView == null) {
            return super.getOpenDistance();
        }
        if ((this.mDirection & 3) > 0) {
            return this.mCurDrawerView.getMeasuredWidth();
        }
        return this.mCurDrawerView.getMeasuredHeight();
    }

    public DrawerConsumer setScrimColor(int i) {
        this.mScrimColor = i;
        return this;
    }

    public DrawerConsumer setShadowColor(int i) {
        this.mShadowColor = i;
        return this;
    }

    public int getShadowSize() {
        return this.mShadowSize;
    }

    public DrawerConsumer setShadowSize(int i) {
        this.mShadowSize = i;
        return this;
    }

    public boolean isDrawerViewRequired() {
        return this.mDrawerViewRequired;
    }

    public DrawerConsumer setDrawerViewRequired(boolean z) {
        this.mDrawerViewRequired = z;
        return this;
    }

    public boolean isScrimAndShadowOutsideContentView() {
        return this.mShowScrimAndShadowOutsideContentView;
    }

    public DrawerConsumer showScrimAndShadowOutsideContentView() {
        this.mShowScrimAndShadowOutsideContentView = true;
        return this;
    }

    public DrawerConsumer showScrimAndShadowInsideContentView() {
        this.mShowScrimAndShadowOutsideContentView = false;
        return this;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (getDragState() == 0 && !this.mShowScrimAndShadowOutsideContentView && view == this.mScrimView) {
            smoothClose();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // com.billy.android.swipe.SwipeConsumer
    public boolean canChildScroll(ViewGroup viewGroup, int i, int i2, float f, float f2, float f3, float f4) {
        if (this.mDirection == 0 || this.mWrapper.getContentView() != findTopChildUnder(viewGroup, (int) f, (int) f2)) {
            return super.canChildScroll(viewGroup, i, i2, f, f2, f3, f4);
        }
        return false;
    }
}
