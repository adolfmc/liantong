package com.mob.tools.gui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PullToRequestView extends RelativeLayout {
    private static final int FAULT_TOLERANCE_RANGE = 10;
    private static final long MIN_REF_TIME = 1000;
    private PullToRequestAdatper adapter;
    private View bodyView;
    private float downY;
    private int footerHeight;
    private View footerView;
    private int headerHeight;
    private View headerView;
    private long pullTime;
    private boolean pullingDownLock;
    private boolean pullingUpLock;
    private int state;
    private Runnable stopAct;
    private int top;

    public PullToRequestView(Context context) {
        super(context);
        init();
    }

    public PullToRequestView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public PullToRequestView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.stopAct = new Runnable() { // from class: com.mob.tools.gui.PullToRequestView.1
            @Override // java.lang.Runnable
            public void run() {
                PullToRequestView.this.reversePulling();
            }
        };
    }

    public void setAdapter(PullToRequestAdatper pullToRequestAdatper) {
        this.adapter = pullToRequestAdatper;
        removeAllViews();
        this.bodyView = (View) pullToRequestAdatper.getBodyView();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(9);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        addView(this.bodyView, layoutParams);
        this.headerView = pullToRequestAdatper.getHeaderView();
        this.headerView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.headerView.measure(0, 0);
        this.headerHeight = this.headerView.getMeasuredHeight();
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, this.headerHeight);
        layoutParams2.addRule(9);
        layoutParams2.addRule(11);
        layoutParams2.addRule(10);
        layoutParams2.topMargin = -this.headerHeight;
        addView(this.headerView, layoutParams2);
        this.footerView = pullToRequestAdatper.getFooterView();
        this.footerView.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.footerView.measure(0, 0);
        this.footerHeight = this.footerView.getMeasuredHeight();
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, this.headerHeight);
        layoutParams3.addRule(9);
        layoutParams3.addRule(11);
        layoutParams3.addRule(12);
        layoutParams3.bottomMargin = -this.headerHeight;
        addView(this.footerView, layoutParams3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int i;
        int i2;
        int i3;
        int i4;
        switch (motionEvent.getAction()) {
            case 0:
                this.downY = motionEvent.getY();
                break;
            case 1:
            case 3:
                switch (this.state) {
                    case -1:
                        this.top = -this.footerHeight;
                        scrollTo(0, -this.top);
                        break;
                    case 0:
                        int i5 = this.top;
                        int i6 = this.headerHeight;
                        if (i5 > i6) {
                            this.top = i6;
                            scrollTo(0, -this.top);
                            PullToRequestAdatper pullToRequestAdatper = this.adapter;
                            if (pullToRequestAdatper != null) {
                                pullToRequestAdatper.onPullDown(100);
                            }
                            performFresh();
                            motionEvent = getCancelEvent(motionEvent);
                            break;
                        } else {
                            int i7 = this.footerHeight;
                            if (i5 >= (-i7)) {
                                if (i5 != 0) {
                                    scrollTo(0, 0);
                                    PullToRequestAdatper pullToRequestAdatper2 = this.adapter;
                                    if (pullToRequestAdatper2 != null) {
                                        if (this.top > 0) {
                                            pullToRequestAdatper2.onPullDown(0);
                                        } else {
                                            pullToRequestAdatper2.onPullUp(0);
                                        }
                                    }
                                    this.top = 0;
                                    break;
                                }
                            } else {
                                this.top = -i7;
                                scrollTo(0, -this.top);
                                PullToRequestAdatper pullToRequestAdatper3 = this.adapter;
                                if (pullToRequestAdatper3 != null) {
                                    pullToRequestAdatper3.onPullUp(100);
                                }
                                performRequestNext();
                                motionEvent = getCancelEvent(motionEvent);
                                break;
                            }
                        }
                        break;
                    case 1:
                        this.top = this.headerHeight;
                        scrollTo(0, -this.top);
                        break;
                }
            case 2:
                float y = motionEvent.getY();
                int i8 = this.state;
                if (i8 == -1) {
                    this.top = (int) (this.top + ((y - this.downY) / 2.0f));
                    if (this.top > 0) {
                        this.top = 0;
                    }
                    scrollTo(0, -this.top);
                    motionEvent = getCancelEvent(motionEvent);
                } else if (i8 == 1) {
                    this.top = (int) (this.top + ((y - this.downY) / 2.0f));
                    if (this.top < 0) {
                        this.top = 0;
                    }
                    scrollTo(0, -this.top);
                    motionEvent = getCancelEvent(motionEvent);
                } else {
                    int i9 = this.top;
                    if (i9 > 0) {
                        this.top = (int) (i9 + ((y - this.downY) / 2.0f));
                        if (this.top < 0) {
                            this.top = 0;
                        }
                        scrollTo(0, -this.top);
                        PullToRequestAdatper pullToRequestAdatper4 = this.adapter;
                        if (pullToRequestAdatper4 != null && (i4 = this.headerHeight) != 0) {
                            pullToRequestAdatper4.onPullDown((this.top * 100) / i4);
                        }
                        motionEvent = getCancelEvent(motionEvent);
                    } else if (i9 < 0) {
                        this.top = (int) (i9 + ((y - this.downY) / 2.0f));
                        if (this.top > 0) {
                            this.top = 0;
                        }
                        scrollTo(0, -this.top);
                        PullToRequestAdatper pullToRequestAdatper5 = this.adapter;
                        if (pullToRequestAdatper5 != null && (i3 = this.footerHeight) != 0) {
                            pullToRequestAdatper5.onPullUp(((-this.top) * 100) / i3);
                        }
                        motionEvent = getCancelEvent(motionEvent);
                    } else {
                        float f = this.downY;
                        if (y - f > 10.0f) {
                            if (canPullDown()) {
                                this.top = (int) (this.top + ((y - this.downY) / 2.0f));
                                scrollTo(0, -this.top);
                                PullToRequestAdatper pullToRequestAdatper6 = this.adapter;
                                if (pullToRequestAdatper6 != null && (i2 = this.headerHeight) != 0) {
                                    pullToRequestAdatper6.onPullUp(((-this.top) * 100) / i2);
                                }
                                motionEvent = getCancelEvent(motionEvent);
                            }
                        } else if (f - y > 10.0f && canPullUp()) {
                            this.top = (int) (this.top + ((y - this.downY) / 2.0f));
                            scrollTo(0, -this.top);
                            PullToRequestAdatper pullToRequestAdatper7 = this.adapter;
                            if (pullToRequestAdatper7 != null && (i = this.footerHeight) != 0) {
                                pullToRequestAdatper7.onPullUp(((-this.top) * 100) / i);
                            }
                            motionEvent = getCancelEvent(motionEvent);
                        }
                    }
                }
                this.downY = y;
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private MotionEvent getCancelEvent(MotionEvent motionEvent) {
        return MotionEvent.obtain(motionEvent.getDownTime(), motionEvent.getEventTime(), 3, motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
    }

    public void performPullingDown(boolean z) {
        this.top = this.headerHeight;
        scrollTo(0, -this.top);
        if (z) {
            performFresh();
        }
    }

    protected void performFresh() {
        this.pullTime = System.currentTimeMillis();
        this.state = 1;
        PullToRequestAdatper pullToRequestAdatper = this.adapter;
        if (pullToRequestAdatper != null) {
            pullToRequestAdatper.onRefresh();
        }
    }

    public void performPullingUp(boolean z) {
        this.top = -this.footerHeight;
        scrollTo(0, -this.top);
        if (z) {
            performRequestNext();
        }
    }

    private void performRequestNext() {
        this.pullTime = System.currentTimeMillis();
        this.state = -1;
        PullToRequestAdatper pullToRequestAdatper = this.adapter;
        if (pullToRequestAdatper != null) {
            pullToRequestAdatper.onRequestNext();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reversePulling() {
        this.top = 0;
        scrollTo(0, 0);
        this.state = 0;
        PullToRequestAdatper pullToRequestAdatper = this.adapter;
        if (pullToRequestAdatper != null) {
            pullToRequestAdatper.onReversed();
        }
    }

    public void stopPulling() {
        long currentTimeMillis = System.currentTimeMillis() - this.pullTime;
        if (currentTimeMillis < 1000) {
            postDelayed(this.stopAct, 1000 - currentTimeMillis);
        } else {
            post(this.stopAct);
        }
    }

    public void lockPullingDown() {
        this.pullingDownLock = true;
    }

    public void lockPullingUp() {
        this.pullingUpLock = true;
    }

    public void releasePullingDownLock() {
        this.pullingDownLock = false;
    }

    public void releasePullingUpLock() {
        this.pullingUpLock = false;
    }

    private boolean canPullDown() {
        return !this.pullingDownLock && this.adapter.isPullDownReady() && this.state == 0;
    }

    private boolean canPullUp() {
        return !this.pullingUpLock && this.adapter.isPullUpReady() && this.state == 0;
    }
}
