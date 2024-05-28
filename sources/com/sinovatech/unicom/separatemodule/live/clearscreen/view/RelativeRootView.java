package com.sinovatech.unicom.separatemodule.live.clearscreen.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.sinovatech.unicom.separatemodule.live.clearscreen.Constants;
import com.sinovatech.unicom.separatemodule.live.clearscreen.IClearEvent;
import com.sinovatech.unicom.separatemodule.live.clearscreen.IClearRootView;
import com.sinovatech.unicom.separatemodule.live.clearscreen.IPositionCallBack;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RelativeRootView extends RelativeLayout implements IClearRootView {
    private final int LEFT_SIDE_X;
    private final int MIN_SCROLL_SIZE;
    private final int RIGHT_SIDE_X;
    private boolean isCanSrcoll;
    private boolean isTouchWithAnimRuning;
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

    public RelativeRootView(Context context) {
        this(context, null);
    }

    public RelativeRootView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RelativeRootView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.MIN_SCROLL_SIZE = 50;
        this.LEFT_SIDE_X = 0;
        this.RIGHT_SIDE_X = getResources().getDisplayMetrics().widthPixels;
        this.mEndAnimator = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(200L);
        this.mEndAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.live.clearscreen.view.RelativeRootView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                RelativeRootView.this.mIPositionCallBack.onPositionChange((int) (RelativeRootView.this.mDownX + ((RelativeRootView.this.mEndX - RelativeRootView.this.mDownX) * ((Float) valueAnimator.getAnimatedValue()).floatValue())), 0);
            }
        });
        this.mEndAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.sinovatech.unicom.separatemodule.live.clearscreen.view.RelativeRootView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!RelativeRootView.this.mOrientation.equals(Constants.Orientation.RIGHT) || RelativeRootView.this.mEndX != RelativeRootView.this.RIGHT_SIDE_X) {
                    if (RelativeRootView.this.mOrientation.equals(Constants.Orientation.LEFT) && RelativeRootView.this.mEndX == 0) {
                        RelativeRootView.this.mIClearEvent.onRecovery();
                        RelativeRootView.this.mOrientation = Constants.Orientation.RIGHT;
                    }
                } else {
                    RelativeRootView.this.mIClearEvent.onClearEnd();
                    RelativeRootView.this.mOrientation = Constants.Orientation.LEFT;
                }
                RelativeRootView relativeRootView = RelativeRootView.this;
                relativeRootView.mDownX = relativeRootView.mEndX;
                RelativeRootView.this.isCanSrcoll = false;
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
        int i = x - this.mDownX;
        int action = motionEvent.getAction() & 255;
        if (action != 5) {
            switch (action) {
                case 2:
                    if (isGreaterThanMinSize(this.mDownX, x) && this.isCanSrcoll) {
                        this.mIPositionCallBack.onPositionChange(getPositionChangeX(i), 0);
                        return true;
                    }
                    break;
            }
            return super.onTouchEvent(motionEvent);
        }
        if (isGreaterThanMinSize(this.mDownX, x) && this.isCanSrcoll) {
            this.mDownX = getPositionChangeX(i);
            fixPostion(i);
            this.mEndAnimator.start();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.isTouchWithAnimRuning = this.mEndAnimator.isRunning();
            this.mDownX = x;
        } else if (action == 2 && isGreaterThanMinSize(this.mDownX, x) && !this.isTouchWithAnimRuning) {
            this.isCanSrcoll = true;
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private int getPositionChangeX(int i) {
        int abs = Math.abs(i);
        return this.mOrientation.equals(Constants.Orientation.RIGHT) ? abs - 50 : this.RIGHT_SIDE_X - (abs - 50);
    }

    private void fixPostion(int i) {
        int abs = Math.abs(i);
        if (this.mOrientation.equals(Constants.Orientation.RIGHT)) {
            int i2 = this.RIGHT_SIDE_X;
            if (abs > i2 / 3) {
                this.mEndX = i2;
                return;
            }
        }
        if (!this.mOrientation.equals(Constants.Orientation.LEFT) || abs <= this.RIGHT_SIDE_X / 3) {
            return;
        }
        this.mEndX = 0;
    }

    public boolean isGreaterThanMinSize(int i, int i2) {
        return this.mOrientation.equals(Constants.Orientation.RIGHT) ? i2 - i > 50 : i - i2 > 50;
    }
}
