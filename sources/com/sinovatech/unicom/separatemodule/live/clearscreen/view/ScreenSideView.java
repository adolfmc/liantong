package com.sinovatech.unicom.separatemodule.live.clearscreen.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.sinovatech.unicom.separatemodule.live.clearscreen.Constants;
import com.sinovatech.unicom.separatemodule.live.clearscreen.IClearEvent;
import com.sinovatech.unicom.separatemodule.live.clearscreen.IClearRootView;
import com.sinovatech.unicom.separatemodule.live.clearscreen.IPositionCallBack;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScreenSideView extends LinearLayout implements IClearRootView {
    private final int LEFT_SIDE_X;
    private final int MIN_SCROLL_SIZE;
    private final int RIGHT_SIDE_X;
    private boolean isCanSrcoll;
    private int mDownX;
    private ValueAnimator mEndAnimator;
    private int mEndX;
    private IClearEvent mIClearEvent;
    private IPositionCallBack mIPositionCallBack;
    private Constants.Orientation mOrientation;

    @Override // com.sinovatech.unicom.separatemodule.live.clearscreen.IClearRootView
    public void setIPositionCallBack(IPositionCallBack iPositionCallBack) {
        this.mIPositionCallBack = iPositionCallBack;
    }

    @Override // com.sinovatech.unicom.separatemodule.live.clearscreen.IClearRootView
    public void setIClearEvent(IClearEvent iClearEvent) {
        this.mIClearEvent = iClearEvent;
    }

    public ScreenSideView(Context context) {
        this(context, null);
    }

    public ScreenSideView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScreenSideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.MIN_SCROLL_SIZE = 30;
        this.LEFT_SIDE_X = 0;
        this.RIGHT_SIDE_X = getResources().getDisplayMetrics().widthPixels;
        this.mEndAnimator = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(200L);
        this.mEndAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.live.clearscreen.view.ScreenSideView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ScreenSideView.this.mIPositionCallBack.onPositionChange((int) (ScreenSideView.this.mDownX + ((ScreenSideView.this.mEndX - ScreenSideView.this.mDownX) * ((Float) valueAnimator.getAnimatedValue()).floatValue())), 0);
            }
        });
        this.mEndAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.sinovatech.unicom.separatemodule.live.clearscreen.view.ScreenSideView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!ScreenSideView.this.mOrientation.equals(Constants.Orientation.RIGHT) || ScreenSideView.this.mEndX != ScreenSideView.this.RIGHT_SIDE_X) {
                    if (ScreenSideView.this.mOrientation.equals(Constants.Orientation.LEFT) && ScreenSideView.this.mEndX == 0) {
                        ScreenSideView.this.mIClearEvent.onRecovery();
                        ScreenSideView.this.mOrientation = Constants.Orientation.RIGHT;
                    }
                } else {
                    ScreenSideView.this.mIClearEvent.onClearEnd();
                    ScreenSideView.this.mOrientation = Constants.Orientation.LEFT;
                }
                ScreenSideView screenSideView = ScreenSideView.this;
                screenSideView.mDownX = screenSideView.mEndX;
                ScreenSideView.this.isCanSrcoll = false;
            }
        });
    }

    @Override // com.sinovatech.unicom.separatemodule.live.clearscreen.IClearRootView
    public void setClearSide(Constants.Orientation orientation) {
        this.mOrientation = orientation;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        switch (motionEvent.getAction()) {
            case 0:
                if (isScrollFromSide(x)) {
                    this.isCanSrcoll = true;
                    return true;
                }
                if (isGreaterThanMinSize(x) && this.isCanSrcoll) {
                    this.mIPositionCallBack.onPositionChange(getRealTimeX(x), 0);
                    return true;
                }
                return super.onTouchEvent(motionEvent);
            case 1:
                if (isGreaterThanMinSize(x) && this.isCanSrcoll) {
                    this.mDownX = getRealTimeX(x);
                    fixPostion();
                    this.mEndAnimator.start();
                }
                return super.onTouchEvent(motionEvent);
            case 2:
                if (isGreaterThanMinSize(x)) {
                    this.mIPositionCallBack.onPositionChange(getRealTimeX(x), 0);
                    return true;
                }
                return super.onTouchEvent(motionEvent);
            default:
                return super.onTouchEvent(motionEvent);
        }
    }

    private int getRealTimeX(int i) {
        return ((!this.mOrientation.equals(Constants.Orientation.RIGHT) || this.mDownX <= this.RIGHT_SIDE_X / 3) && (!this.mOrientation.equals(Constants.Orientation.LEFT) || this.mDownX <= (this.RIGHT_SIDE_X * 2) / 3)) ? i - 30 : i + 30;
    }

    private void fixPostion() {
        if (this.mOrientation.equals(Constants.Orientation.RIGHT)) {
            int i = this.mDownX;
            int i2 = this.RIGHT_SIDE_X;
            if (i > i2 / 3) {
                this.mEndX = i2;
                return;
            }
        }
        if (!this.mOrientation.equals(Constants.Orientation.LEFT) || this.mDownX >= (this.RIGHT_SIDE_X * 2) / 3) {
            return;
        }
        this.mEndX = 0;
    }

    private boolean isGreaterThanMinSize(int i) {
        return Math.abs(this.mDownX - i) > 30;
    }

    private boolean isScrollFromSide(int i) {
        if (i > 30 || !this.mOrientation.equals(Constants.Orientation.RIGHT)) {
            return i > this.RIGHT_SIDE_X - 30 && this.mOrientation.equals(Constants.Orientation.LEFT);
        }
        return true;
    }
}
