package com.mahc.custombottomsheetbehavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.p083v4.content.ContextCompat;
import android.support.p083v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import java.lang.ref.WeakReference;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ScrollingAppBarLayoutBehavior extends AppBarLayout.ScrollingViewBehavior {
    private static final String TAG = "ScrollingAppBarLayoutBehavior";
    private ValueAnimator mAppBarYValueAnimator;
    private WeakReference<BottomSheetBehaviorGoogleMapsLike> mBottomSheetBehaviorRef;
    private Context mContext;
    private boolean mInit;
    private boolean mVisible;

    public ScrollingAppBarLayoutBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInit = false;
        this.mVisible = true;
        this.mContext = context;
    }

    @Override // android.support.design.widget.AppBarLayout.ScrollingViewBehavior, android.support.design.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view2 instanceof NestedScrollView) {
            try {
                BottomSheetBehaviorGoogleMapsLike.from(view2);
                return true;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // android.support.design.widget.AppBarLayout.ScrollingViewBehavior, android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (!this.mInit) {
            return init(coordinatorLayout, view, view2);
        }
        WeakReference<BottomSheetBehaviorGoogleMapsLike> weakReference = this.mBottomSheetBehaviorRef;
        if (weakReference == null || weakReference.get() == null) {
            getBottomSheetBehavior(coordinatorLayout);
        }
        setAppBarVisible((AppBarLayout) view, view2.getY() >= ((float) (view2.getHeight() - this.mBottomSheetBehaviorRef.get().getPeekHeight())));
        return true;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, view), this.mVisible);
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, view, savedState.getSuperState());
        this.mVisible = savedState.mVisible;
    }

    private boolean init(CoordinatorLayout coordinatorLayout, View view, View view2) {
        getBottomSheetBehavior(coordinatorLayout);
        WeakReference<BottomSheetBehaviorGoogleMapsLike> weakReference = this.mBottomSheetBehaviorRef;
        if (weakReference == null || weakReference.get() == null) {
            getBottomSheetBehavior(coordinatorLayout);
        }
        this.mVisible = view2.getY() >= ((float) (view2.getHeight() - this.mBottomSheetBehaviorRef.get().getPeekHeight()));
        setStatusBarBackgroundVisible(this.mVisible);
        if (!this.mVisible) {
            view.setY((((int) view.getY()) - view.getHeight()) - getStatusBarHeight());
        }
        this.mInit = true;
        return !this.mVisible;
    }

    public void setAppBarVisible(final AppBarLayout appBarLayout, final boolean z) {
        float y;
        if (z == this.mVisible) {
            return;
        }
        ValueAnimator valueAnimator = this.mAppBarYValueAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            float[] fArr = new float[2];
            fArr[0] = (int) appBarLayout.getY();
            if (z) {
                y = ((int) appBarLayout.getY()) + appBarLayout.getHeight() + getStatusBarHeight();
            } else {
                y = (((int) appBarLayout.getY()) - appBarLayout.getHeight()) - getStatusBarHeight();
            }
            fArr[1] = y;
            this.mAppBarYValueAnimator = ValueAnimator.ofFloat(fArr);
            this.mAppBarYValueAnimator.setDuration(this.mContext.getResources().getInteger(17694720));
            this.mAppBarYValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.mahc.custombottomsheetbehavior.ScrollingAppBarLayoutBehavior.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    appBarLayout.setY(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                }
            });
            this.mAppBarYValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.mahc.custombottomsheetbehavior.ScrollingAppBarLayoutBehavior.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    if (z) {
                        ScrollingAppBarLayoutBehavior.this.setStatusBarBackgroundVisible(true);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (!z) {
                        ScrollingAppBarLayoutBehavior.this.setStatusBarBackgroundVisible(false);
                    }
                    ScrollingAppBarLayoutBehavior.this.mVisible = z;
                    super.onAnimationEnd(animator);
                }
            });
            this.mAppBarYValueAnimator.start();
        }
    }

    private int getStatusBarHeight() {
        int identifier = this.mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.mContext.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatusBarBackgroundVisible(boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (z) {
                Window window = ((Activity) this.mContext).getWindow();
                window.clearFlags(67108864);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(ContextCompat.getColor(this.mContext, C5284R.C5285color.colorPrimaryDark));
                return;
            }
            Window window2 = ((Activity) this.mContext).getWindow();
            window2.clearFlags(Integer.MIN_VALUE);
            window2.addFlags(67108864);
            window2.setStatusBarColor(ContextCompat.getColor(this.mContext, 17170445));
        }
    }

    private void getBottomSheetBehavior(@NonNull CoordinatorLayout coordinatorLayout) {
        for (int i = 0; i < coordinatorLayout.getChildCount(); i++) {
            View childAt = coordinatorLayout.getChildAt(i);
            if (childAt instanceof NestedScrollView) {
                try {
                    this.mBottomSheetBehaviorRef = new WeakReference<>(BottomSheetBehaviorGoogleMapsLike.from(childAt));
                    return;
                } catch (IllegalArgumentException unused) {
                    continue;
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.mahc.custombottomsheetbehavior.ScrollingAppBarLayoutBehavior.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        final boolean mVisible;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mVisible = parcel.readByte() != 0;
        }

        public SavedState(Parcelable parcelable, boolean z) {
            super(parcelable);
            this.mVisible = z;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.mVisible ? (byte) 1 : (byte) 0);
        }
    }
}
