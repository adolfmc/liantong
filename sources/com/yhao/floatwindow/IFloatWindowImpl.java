package com.yhao.floatwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import com.yhao.floatwindow.FloatWindow;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class IFloatWindowImpl extends IFloatWindow {
    private static final String TAG = "IFloatWindowImpl";
    public static boolean isShow = false;
    public static boolean once = true;
    private float downX;
    private float downY;
    private ValueAnimator mAnimator;

    /* renamed from: mB */
    private FloatWindow.C11689B f23824mB;
    private boolean mClick = false;
    private TimeInterpolator mDecelerateInterpolator;
    private FloatLifecycle mFloatLifecycle;
    private FloatView mFloatView;
    private int mSlop;
    private int screenHeight;
    private int screenWidth;
    private float upX;
    private float upY;

    private IFloatWindowImpl() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IFloatWindowImpl(FloatWindow.C11689B c11689b) {
        this.f23824mB = c11689b;
        this.screenWidth = Util.getScreenWidth(this.f23824mB.mApplicationContext);
        this.screenHeight = Util.getScreenHeight(this.f23824mB.mApplicationContext);
        if (this.f23824mB.mMoveType == 0) {
            if (Build.VERSION.SDK_INT >= 25) {
                this.mFloatView = new FloatPhone(c11689b.mApplicationContext, this.f23824mB.mPermissionListener);
            } else {
                this.mFloatView = new FloatToast(c11689b.mApplicationContext);
            }
        } else {
            this.mFloatView = new FloatPhone(c11689b.mApplicationContext, this.f23824mB.mPermissionListener);
            initTouchEvent();
        }
        this.mFloatView.setSize(this.f23824mB.mWidth, this.f23824mB.mHeight);
        this.mFloatView.setGravity(8388659, 0, 0);
        this.mFloatView.setView(this.f23824mB.mView);
        this.mFloatLifecycle = new FloatLifecycle(this.f23824mB.mApplicationContext, this.f23824mB.mShow, this.f23824mB.mActivities, new LifecycleListener() { // from class: com.yhao.floatwindow.IFloatWindowImpl.1
            @Override // com.yhao.floatwindow.LifecycleListener
            public void onShow() {
                IFloatWindowImpl.this.show();
            }

            @Override // com.yhao.floatwindow.LifecycleListener
            public void onHide() {
                IFloatWindowImpl.this.hide();
            }

            @Override // com.yhao.floatwindow.LifecycleListener
            public void onBackToDesktop() {
                if (!IFloatWindowImpl.this.f23824mB.mDesktopShow) {
                    IFloatWindowImpl.this.hide();
                }
                if (IFloatWindowImpl.this.f23824mB.mViewStateListener != null) {
                    IFloatWindowImpl.this.f23824mB.mViewStateListener.onBackToDesktop();
                }
            }
        });
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void show() {
        if (once) {
            this.mFloatView.init();
            once = false;
            isShow = true;
        } else {
            getView().setVisibility(0);
            once = false;
            isShow = true;
        }
        if (this.f23824mB.mViewStateListener != null) {
            this.f23824mB.mViewStateListener.onShow();
        }
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void hide() {
        if (once || !isShow) {
            return;
        }
        getView().setVisibility(4);
        isShow = false;
        if (this.f23824mB.mViewStateListener != null) {
            this.f23824mB.mViewStateListener.onHide();
        }
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public boolean isShowing() {
        return isShow;
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void dismiss() {
        this.mFloatView.dismiss();
        if (this.f23824mB.mViewStateListener != null) {
            this.f23824mB.mViewStateListener.onDismiss();
        }
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void updateX(int i) {
        checkMoveType();
        this.f23824mB.xOffset = i;
        this.mFloatView.updateX(i);
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void updateY(int i) {
        checkMoveType();
        this.f23824mB.yOffset = i;
        this.mFloatView.updateY(i);
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void updateX(int i, float f) {
        int screenHeight;
        checkMoveType();
        FloatWindow.C11689B c11689b = this.f23824mB;
        if (i == 0) {
            screenHeight = Util.getScreenWidth(c11689b.mApplicationContext);
        } else {
            screenHeight = Util.getScreenHeight(c11689b.mApplicationContext);
        }
        c11689b.xOffset = (int) (screenHeight * f);
        this.mFloatView.updateX(this.f23824mB.xOffset);
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void updateY(int i, float f) {
        int screenHeight;
        checkMoveType();
        FloatWindow.C11689B c11689b = this.f23824mB;
        if (i == 0) {
            screenHeight = Util.getScreenWidth(c11689b.mApplicationContext);
        } else {
            screenHeight = Util.getScreenHeight(c11689b.mApplicationContext);
        }
        c11689b.yOffset = (int) (screenHeight * f);
        this.mFloatView.updateY(this.f23824mB.yOffset);
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public int getX() {
        return this.mFloatView.getX();
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public int getY() {
        return this.mFloatView.getY();
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public View getView() {
        this.mSlop = ViewConfiguration.get(this.f23824mB.mApplicationContext).getScaledTouchSlop();
        return this.f23824mB.mView;
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void updateSize(int i, int i2) {
        this.mFloatView.updateSize(i, i2);
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public void setDrag(boolean z) {
        this.f23824mB.isDrag = z;
    }

    @Override // com.yhao.floatwindow.IFloatWindow
    public boolean isDrag() {
        return this.f23824mB.isDrag;
    }

    private void checkMoveType() {
        if (this.f23824mB.mMoveType == 0) {
            throw new IllegalArgumentException("FloatWindow of this tag is not allowed to move!");
        }
    }

    private void initTouchEvent() {
        if (this.f23824mB.mMoveType != 1) {
            getView().setOnTouchListener(new View.OnTouchListener() { // from class: com.yhao.floatwindow.IFloatWindowImpl.2
                float changeX;
                float changeY;
                float lastX;
                float lastY;
                int newX;
                int newY;

                @Override // android.view.View.OnTouchListener
                @SuppressLint({"ClickableViewAccessibility"})
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (IFloatWindowImpl.this.f23824mB.isDrag) {
                        switch (motionEvent.getAction()) {
                            case 0:
                                IFloatWindowImpl.this.downX = motionEvent.getRawX();
                                IFloatWindowImpl.this.downY = motionEvent.getRawY();
                                this.lastX = motionEvent.getRawX();
                                this.lastY = motionEvent.getRawY();
                                IFloatWindowImpl.this.cancelAnimator();
                                break;
                            case 1:
                                IFloatWindowImpl.this.upX = motionEvent.getRawX();
                                IFloatWindowImpl.this.upY = motionEvent.getRawY();
                                IFloatWindowImpl iFloatWindowImpl = IFloatWindowImpl.this;
                                iFloatWindowImpl.mClick = Math.abs(iFloatWindowImpl.upX - IFloatWindowImpl.this.downX) > ((float) IFloatWindowImpl.this.mSlop) || Math.abs(IFloatWindowImpl.this.upY - IFloatWindowImpl.this.downY) > ((float) IFloatWindowImpl.this.mSlop);
                                switch (IFloatWindowImpl.this.f23824mB.mMoveType) {
                                    case 3:
                                        int x = IFloatWindowImpl.this.mFloatView.getX();
                                        int screenWidth = (x * 2) + view.getWidth() > Util.getScreenWidth(IFloatWindowImpl.this.f23824mB.mApplicationContext) ? (Util.getScreenWidth(IFloatWindowImpl.this.f23824mB.mApplicationContext) - view.getWidth()) - IFloatWindowImpl.this.f23824mB.mSlideRightMargin : IFloatWindowImpl.this.f23824mB.mSlideLeftMargin;
                                        int y = IFloatWindowImpl.this.mFloatView.getY();
                                        if (y < 0 || y > IFloatWindowImpl.this.screenHeight - view.getHeight()) {
                                            IFloatWindowImpl.this.mAnimator = ObjectAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofInt("x", x, screenWidth), PropertyValuesHolder.ofInt("y", y, (y * 2) + view.getHeight() > IFloatWindowImpl.this.screenHeight ? (IFloatWindowImpl.this.screenHeight - view.getHeight()) - IFloatWindowImpl.this.f23824mB.mSlideRightMargin : IFloatWindowImpl.this.f23824mB.mSlideLeftMargin));
                                            IFloatWindowImpl.this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yhao.floatwindow.IFloatWindowImpl.2.1
                                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                    int intValue = ((Integer) valueAnimator.getAnimatedValue("x")).intValue();
                                                    int intValue2 = ((Integer) valueAnimator.getAnimatedValue("y")).intValue();
                                                    IFloatWindowImpl.this.mFloatView.updateXY(intValue, intValue2);
                                                    if (IFloatWindowImpl.this.f23824mB.mViewStateListener != null) {
                                                        IFloatWindowImpl.this.f23824mB.mViewStateListener.onPositionUpdate(intValue, intValue2);
                                                    }
                                                }
                                            });
                                        } else {
                                            IFloatWindowImpl.this.mAnimator = ObjectAnimator.ofInt(x, screenWidth);
                                            IFloatWindowImpl.this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yhao.floatwindow.IFloatWindowImpl.2.2
                                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                    int intValue = ((Integer) IFloatWindowImpl.this.mAnimator.getAnimatedValue()).intValue();
                                                    IFloatWindowImpl.this.mFloatView.updateX(intValue);
                                                    if (IFloatWindowImpl.this.f23824mB.mViewStateListener != null) {
                                                        IFloatWindowImpl.this.f23824mB.mViewStateListener.onPositionUpdate(intValue, (int) IFloatWindowImpl.this.upY);
                                                    }
                                                }
                                            });
                                        }
                                        IFloatWindowImpl.this.startAnimator();
                                        break;
                                    case 4:
                                        IFloatWindowImpl.this.mAnimator = ObjectAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofInt("x", IFloatWindowImpl.this.mFloatView.getX(), IFloatWindowImpl.this.f23824mB.xOffset), PropertyValuesHolder.ofInt("y", IFloatWindowImpl.this.mFloatView.getY(), IFloatWindowImpl.this.f23824mB.yOffset));
                                        IFloatWindowImpl.this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.yhao.floatwindow.IFloatWindowImpl.2.3
                                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                int intValue = ((Integer) valueAnimator.getAnimatedValue("x")).intValue();
                                                int intValue2 = ((Integer) valueAnimator.getAnimatedValue("y")).intValue();
                                                IFloatWindowImpl.this.mFloatView.updateXY(intValue, intValue2);
                                                if (IFloatWindowImpl.this.f23824mB.mViewStateListener != null) {
                                                    IFloatWindowImpl.this.f23824mB.mViewStateListener.onPositionUpdate(intValue, intValue2);
                                                }
                                            }
                                        });
                                        IFloatWindowImpl.this.startAnimator();
                                        break;
                                }
                            case 2:
                                this.changeX = motionEvent.getRawX() - this.lastX;
                                this.changeY = motionEvent.getRawY() - this.lastY;
                                if (this.changeX <= 0.0f ? IFloatWindowImpl.this.mFloatView.getX() > -120 : IFloatWindowImpl.this.mFloatView.getX() + view.getWidth() < IFloatWindowImpl.this.screenWidth + 120) {
                                    if (this.changeY <= 0.0f ? IFloatWindowImpl.this.mFloatView.getY() > -120 : IFloatWindowImpl.this.mFloatView.getY() + view.getHeight() < IFloatWindowImpl.this.screenHeight + 120) {
                                        Log.d(IFloatWindowImpl.TAG, "onTouch: ");
                                        this.newX = (int) (IFloatWindowImpl.this.mFloatView.getX() + this.changeX);
                                        this.newY = (int) (IFloatWindowImpl.this.mFloatView.getY() + this.changeY);
                                        IFloatWindowImpl.this.mFloatView.updateXY(this.newX, this.newY);
                                        if (IFloatWindowImpl.this.f23824mB.mViewStateListener != null) {
                                            IFloatWindowImpl.this.f23824mB.mViewStateListener.onPositionUpdate(this.newX, this.newY);
                                        }
                                        this.lastX = motionEvent.getRawX();
                                        this.lastY = motionEvent.getRawY();
                                        break;
                                    }
                                }
                                break;
                        }
                        return IFloatWindowImpl.this.mClick;
                    }
                    return false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnimator() {
        if (this.f23824mB.mInterpolator == null) {
            if (this.mDecelerateInterpolator == null) {
                this.mDecelerateInterpolator = new DecelerateInterpolator();
            }
            this.f23824mB.mInterpolator = this.mDecelerateInterpolator;
        }
        this.mAnimator.setInterpolator(this.f23824mB.mInterpolator);
        this.mAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.yhao.floatwindow.IFloatWindowImpl.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                IFloatWindowImpl.this.mAnimator.removeAllUpdateListeners();
                IFloatWindowImpl.this.mAnimator.removeAllListeners();
                IFloatWindowImpl.this.mAnimator = null;
                if (IFloatWindowImpl.this.f23824mB.mViewStateListener != null) {
                    IFloatWindowImpl.this.f23824mB.mViewStateListener.onMoveAnimEnd();
                }
            }
        });
        this.mAnimator.setDuration(this.f23824mB.mDuration).start();
        if (this.f23824mB.mViewStateListener != null) {
            this.f23824mB.mViewStateListener.onMoveAnimStart();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAnimator() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.mAnimator.cancel();
    }
}
