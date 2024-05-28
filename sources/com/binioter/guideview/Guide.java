package com.binioter.guideview;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.binioter.guideview.GuideBuilder;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Guide implements View.OnKeyListener, View.OnTouchListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int SLIDE_THRESHOLD = 30;
    private Component[] mComponents;
    private Configuration mConfiguration;
    private MaskView mMaskView;
    private GuideBuilder.OnSlideListener mOnSlideListener;
    private GuideBuilder.OnVisibilityChangedListener mOnVisibilityChangedListener;
    private boolean mShouldCheckLocInWindow = true;
    float startY = -1.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConfiguration(Configuration configuration) {
        this.mConfiguration = configuration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setComponents(Component[] componentArr) {
        this.mComponents = componentArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCallback(GuideBuilder.OnVisibilityChangedListener onVisibilityChangedListener) {
        this.mOnVisibilityChangedListener = onVisibilityChangedListener;
    }

    public void setOnSlideListener(GuideBuilder.OnSlideListener onSlideListener) {
        this.mOnSlideListener = onSlideListener;
    }

    public void show(Activity activity) {
        show(activity, null);
    }

    public void show(Activity activity, ViewGroup viewGroup) {
        this.mMaskView = onCreateView(activity, viewGroup);
        if (viewGroup == null) {
            viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        }
        if (this.mMaskView.getParent() != null || this.mConfiguration.mTargetView == null) {
            return;
        }
        viewGroup.addView(this.mMaskView);
        if (this.mConfiguration.mEnterAnimationId != -1) {
            Animation loadAnimation = AnimationUtils.loadAnimation(activity, this.mConfiguration.mEnterAnimationId);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.binioter.guideview.Guide.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    if (Guide.this.mOnVisibilityChangedListener != null) {
                        Guide.this.mOnVisibilityChangedListener.onShown();
                    }
                }
            });
            this.mMaskView.startAnimation(loadAnimation);
            return;
        }
        GuideBuilder.OnVisibilityChangedListener onVisibilityChangedListener = this.mOnVisibilityChangedListener;
        if (onVisibilityChangedListener != null) {
            onVisibilityChangedListener.onShown();
        }
    }

    public void clear() {
        ViewGroup viewGroup;
        MaskView maskView = this.mMaskView;
        if (maskView == null || (viewGroup = (ViewGroup) maskView.getParent()) == null) {
            return;
        }
        viewGroup.removeView(this.mMaskView);
        onDestroy();
    }

    public void dismiss() {
        final ViewGroup viewGroup;
        MaskView maskView = this.mMaskView;
        if (maskView == null || (viewGroup = (ViewGroup) maskView.getParent()) == null) {
            return;
        }
        if (this.mConfiguration.mExitAnimationId != -1) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.mMaskView.getContext(), this.mConfiguration.mExitAnimationId);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.binioter.guideview.Guide.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    viewGroup.removeView(Guide.this.mMaskView);
                    if (Guide.this.mOnVisibilityChangedListener != null) {
                        Guide.this.mOnVisibilityChangedListener.onDismiss();
                    }
                    Guide.this.onDestroy();
                }
            });
            this.mMaskView.startAnimation(loadAnimation);
            return;
        }
        viewGroup.removeView(this.mMaskView);
        GuideBuilder.OnVisibilityChangedListener onVisibilityChangedListener = this.mOnVisibilityChangedListener;
        if (onVisibilityChangedListener != null) {
            onVisibilityChangedListener.onDismiss();
        }
        onDestroy();
    }

    public void setShouldCheckLocInWindow(boolean z) {
        this.mShouldCheckLocInWindow = z;
    }

    private MaskView onCreateView(Activity activity, ViewGroup viewGroup) {
        int i;
        int i2;
        if (viewGroup == null) {
            viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        }
        MaskView maskView = new MaskView(activity);
        maskView.setFullingColor(activity.getResources().getColor(this.mConfiguration.mFullingColorId));
        maskView.setFullingAlpha(this.mConfiguration.mAlpha);
        maskView.setHighTargetCorner(this.mConfiguration.mCorner);
        maskView.setPadding(this.mConfiguration.mPadding);
        maskView.setPaddingLeft(this.mConfiguration.mPaddingLeft);
        maskView.setPaddingTop(this.mConfiguration.mPaddingTop);
        maskView.setPaddingRight(this.mConfiguration.mPaddingRight);
        maskView.setPaddingBottom(this.mConfiguration.mPaddingBottom);
        maskView.setHighTargetGraphStyle(this.mConfiguration.mGraphStyle);
        maskView.setOverlayTarget(this.mConfiguration.mOverlayTarget);
        maskView.setOnKeyListener(this);
        if (viewGroup != null) {
            int[] iArr = new int[2];
            viewGroup.getLocationInWindow(iArr);
            i = iArr[0];
            i2 = iArr[1];
        } else {
            i = 0;
            i2 = 0;
        }
        if (this.mConfiguration.mTargetView != null) {
            maskView.setTargetRect(Common.getViewAbsRect(this.mConfiguration.mTargetView, i, i2));
        } else {
            View findViewById = activity.findViewById(this.mConfiguration.mTargetViewId);
            if (findViewById != null) {
                maskView.setTargetRect(Common.getViewAbsRect(findViewById, i, i2));
            }
        }
        if (this.mConfiguration.mOutsideTouchable) {
            maskView.setClickable(false);
        } else {
            maskView.setOnTouchListener(this);
        }
        for (Component component : this.mComponents) {
            maskView.addView(Common.componentToView(activity.getLayoutInflater(), component));
        }
        return maskView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDestroy() {
        this.mConfiguration = null;
        this.mComponents = null;
        this.mOnVisibilityChangedListener = null;
        this.mOnSlideListener = null;
        this.mMaskView.removeAllViews();
        this.mMaskView = null;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Configuration configuration;
        if (i == 4 && keyEvent.getAction() == 1 && (configuration = this.mConfiguration) != null && configuration.mAutoDismiss) {
            dismiss();
            return true;
        }
        return false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        GuideBuilder.OnSlideListener onSlideListener;
        if (motionEvent.getAction() == 0) {
            this.startY = motionEvent.getY();
        } else if (motionEvent.getAction() == 1) {
            if (this.startY - motionEvent.getY() > DimenUtil.dp2px(view.getContext(), 30.0f)) {
                GuideBuilder.OnSlideListener onSlideListener2 = this.mOnSlideListener;
                if (onSlideListener2 != null) {
                    onSlideListener2.onSlideListener(GuideBuilder.SlideState.UP);
                }
            } else if (motionEvent.getY() - this.startY > DimenUtil.dp2px(view.getContext(), 30.0f) && (onSlideListener = this.mOnSlideListener) != null) {
                onSlideListener.onSlideListener(GuideBuilder.SlideState.DOWN);
            }
            Configuration configuration = this.mConfiguration;
            if (configuration != null && configuration.mAutoDismiss) {
                dismiss();
            }
        }
        return true;
    }
}
