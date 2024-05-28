package com.mahc.custombottomsheetbehavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.p083v4.content.ContextCompat;
import android.support.p083v4.widget.NestedScrollView;
import android.support.p086v7.app.ActionBar;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MergedAppBarLayoutBehavior extends AppBarLayout.ScrollingViewBehavior {
    private static final String TAG = "MergedAppBarLayoutBehavior";
    private FrameLayout.LayoutParams mBackGroundLayoutParams;
    private View mBackground;
    private WeakReference<BottomSheetBehaviorGoogleMapsLike> mBottomSheetBehaviorRef;
    private Context mContext;
    private int mCurrentTitleAlpha;
    private boolean mInit;
    private float mInitialY;
    private View.OnClickListener mOnNavigationClickListener;
    private ValueAnimator mTitleAlphaValueAnimator;
    private TextView mTitleTextView;
    private Toolbar mToolbar;
    private String mToolbarTitle;
    private boolean mVisible;

    public MergedAppBarLayoutBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInit = false;
        this.mVisible = false;
        this.mCurrentTitleAlpha = 0;
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
            init(coordinatorLayout, view);
        }
        if (isDependencyYBelowAnchorPoint(coordinatorLayout, view2)) {
            return setToolbarVisible(false, view);
        }
        if (isDependencyYBetweenAnchorPointAndToolbar(coordinatorLayout, view, view2)) {
            boolean toolbarVisible = setToolbarVisible(true, view);
            setFullBackGroundColor(17170445);
            setPartialBackGroundHeight(0);
            return toolbarVisible;
        } else if (isDependencyYBelowToolbar(view, view2) && !isDependencyYReachTop(view2)) {
            boolean toolbarVisible2 = setToolbarVisible(true, view);
            if (isStatusBarVisible()) {
                setStatusBarBackgroundVisible(false);
            }
            if (isTitleVisible()) {
                setTitleVisible(false);
            }
            setFullBackGroundColor(17170445);
            setPartialBackGroundHeight((int) ((view.getHeight() + view.getY()) - view2.getY()));
            return toolbarVisible2;
        } else if (isDependencyYBelowStatusToolbar(view, view2) || isDependencyYReachTop(view2)) {
            boolean toolbarVisible3 = setToolbarVisible(true, view);
            if (!isStatusBarVisible()) {
                setStatusBarBackgroundVisible(true);
            }
            if (!isTitleVisible()) {
                setTitleVisible(true);
            }
            setFullBackGroundColor(C5284R.C5285color.colorPrimary);
            setPartialBackGroundHeight(0);
            return toolbarVisible3;
        } else {
            return false;
        }
    }

    private void init(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view) {
        if (!(view instanceof MergedAppBarLayout)) {
            throw new IllegalArgumentException("The view is not a MergedAppBarLayout");
        }
        MergedAppBarLayout mergedAppBarLayout = (MergedAppBarLayout) view;
        if (Build.VERSION.SDK_INT >= 21) {
            mergedAppBarLayout.setOutlineProvider(ViewOutlineProvider.BACKGROUND);
        }
        this.mToolbar = mergedAppBarLayout.toolbar;
        this.mBackground = mergedAppBarLayout.background;
        this.mBackGroundLayoutParams = (FrameLayout.LayoutParams) this.mBackground.getLayoutParams();
        getBottomSheetBehavior(coordinatorLayout);
        this.mTitleTextView = findTitleTextView(this.mToolbar);
        if (this.mTitleTextView == null) {
            return;
        }
        this.mInitialY = view.getY();
        view.setVisibility(this.mVisible ? 0 : 4);
        setFullBackGroundColor((this.mVisible && this.mCurrentTitleAlpha == 1) ? C5284R.C5285color.colorPrimary : 17170445);
        setPartialBackGroundHeight(0);
        this.mTitleTextView.setText(this.mToolbarTitle);
        this.mTitleTextView.setAlpha(this.mCurrentTitleAlpha);
        this.mInit = true;
        setToolbarVisible(false, view);
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

    private boolean isDependencyYBelowAnchorPoint(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view) {
        WeakReference<BottomSheetBehaviorGoogleMapsLike> weakReference = this.mBottomSheetBehaviorRef;
        if (weakReference == null || weakReference.get() == null) {
            getBottomSheetBehavior(coordinatorLayout);
        }
        return view.getY() > ((float) this.mBottomSheetBehaviorRef.get().getAnchorPoint());
    }

    private boolean isDependencyYBetweenAnchorPointAndToolbar(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View view, @NonNull View view2) {
        WeakReference<BottomSheetBehaviorGoogleMapsLike> weakReference = this.mBottomSheetBehaviorRef;
        if (weakReference == null || weakReference.get() == null) {
            getBottomSheetBehavior(coordinatorLayout);
        }
        return view2.getY() <= ((float) this.mBottomSheetBehaviorRef.get().getAnchorPoint()) && view2.getY() > view.getY() + ((float) view.getHeight());
    }

    private boolean isDependencyYBelowToolbar(@NonNull View view, @NonNull View view2) {
        return view2.getY() <= view.getY() + ((float) view.getHeight()) && view2.getY() > view.getY();
    }

    private boolean isDependencyYBelowStatusToolbar(@NonNull View view, @NonNull View view2) {
        return view2.getY() <= view.getY();
    }

    private boolean isDependencyYReachTop(@NonNull View view) {
        return view.getY() == 0.0f;
    }

    private void setPartialBackGroundHeight(int i) {
        FrameLayout.LayoutParams layoutParams = this.mBackGroundLayoutParams;
        layoutParams.height = i;
        this.mBackground.setLayoutParams(layoutParams);
    }

    private void setFullBackGroundColor(@ColorRes int i) {
        this.mToolbar.setBackgroundColor(ContextCompat.getColor(this.mContext, i));
    }

    private TextView findTitleTextView(Toolbar toolbar) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View childAt = toolbar.getChildAt(i);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (textView.getText() != null && textView.getText().toString().contentEquals(this.mContext.getResources().getString(C5284R.string.key_binding_default_toolbar_name))) {
                    return textView;
                }
            }
        }
        return null;
    }

    private boolean setToolbarVisible(boolean z, final View view) {
        if (z && !this.mVisible) {
            view.setY((-view.getHeight()) / 3);
            ViewPropertyAnimator duration = view.animate().setDuration(this.mContext.getResources().getInteger(17694720));
            duration.setListener(new AnimatorListenerAdapter() { // from class: com.mahc.custombottomsheetbehavior.MergedAppBarLayoutBehavior.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    view.setVisibility(0);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ((AppCompatActivity) MergedAppBarLayoutBehavior.this.mContext).setSupportActionBar(MergedAppBarLayoutBehavior.this.mToolbar);
                    MergedAppBarLayoutBehavior.this.mToolbar.setNavigationOnClickListener(MergedAppBarLayoutBehavior.this.mOnNavigationClickListener);
                    ActionBar supportActionBar = ((AppCompatActivity) MergedAppBarLayoutBehavior.this.mContext).getSupportActionBar();
                    if (supportActionBar != null) {
                        supportActionBar.setDisplayHomeAsUpEnabled(true);
                    }
                    MergedAppBarLayoutBehavior.this.mVisible = true;
                }
            });
            duration.alpha(1.0f).y(this.mInitialY).start();
            return true;
        }
        if (!z && this.mVisible) {
            ViewPropertyAnimator duration2 = view.animate().setDuration(this.mContext.getResources().getInteger(17694720));
            duration2.setListener(new AnimatorListenerAdapter() { // from class: com.mahc.custombottomsheetbehavior.MergedAppBarLayoutBehavior.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    view.setVisibility(4);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ((AppCompatActivity) MergedAppBarLayoutBehavior.this.mContext).setSupportActionBar(null);
                    MergedAppBarLayoutBehavior.this.mVisible = false;
                }
            });
            duration2.alpha(0.0f).start();
        }
        return false;
    }

    private boolean isTitleVisible() {
        return this.mTitleTextView.getAlpha() == 1.0f;
    }

    private void setTitleVisible(boolean z) {
        if (z && this.mTitleTextView.getAlpha() == 1.0f) {
            return;
        }
        if (z || this.mTitleTextView.getAlpha() != 0.0f) {
            ValueAnimator valueAnimator = this.mTitleAlphaValueAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                this.mToolbar.setTitle(this.mToolbarTitle);
                int i = !z ? 1 : 0;
                this.mCurrentTitleAlpha = z ? 1 : 0;
                this.mTitleAlphaValueAnimator = ValueAnimator.ofFloat(i, z ? 1.0f : 0.0f);
                this.mTitleAlphaValueAnimator.setDuration(this.mContext.getResources().getInteger(17694720));
                this.mTitleAlphaValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.mahc.custombottomsheetbehavior.MergedAppBarLayoutBehavior.3
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        MergedAppBarLayoutBehavior.this.mTitleTextView.setAlpha(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                    }
                });
                this.mTitleAlphaValueAnimator.start();
            }
        }
    }

    private boolean isStatusBarVisible() {
        return Build.VERSION.SDK_INT < 21 || ((Activity) this.mContext).getWindow().getStatusBarColor() == ContextCompat.getColor(this.mContext, C5284R.C5285color.colorPrimaryDark);
    }

    private void setStatusBarBackgroundVisible(boolean z) {
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

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        this.mOnNavigationClickListener = onClickListener;
    }

    public void setToolbarTitle(String str) {
        this.mToolbarTitle = str;
        Toolbar toolbar = this.mToolbar;
        if (toolbar != null) {
            toolbar.setTitle(str);
        }
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, view), this.mVisible, this.mToolbarTitle, this.mCurrentTitleAlpha);
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, view, savedState.getSuperState());
        this.mVisible = savedState.mVisible;
        this.mToolbarTitle = savedState.mToolbarTitle;
        this.mCurrentTitleAlpha = savedState.mTitleAlpha;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.mahc.custombottomsheetbehavior.MergedAppBarLayoutBehavior.SavedState.1
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
        final int mTitleAlpha;
        final String mToolbarTitle;
        final boolean mVisible;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mVisible = parcel.readByte() != 0;
            this.mToolbarTitle = parcel.readString();
            this.mTitleAlpha = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, boolean z, String str, int i) {
            super(parcelable);
            this.mVisible = z;
            this.mToolbarTitle = str;
            this.mTitleAlpha = i;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.mVisible ? (byte) 1 : (byte) 0);
            parcel.writeString(this.mToolbarTitle);
            parcel.writeInt(this.mTitleAlpha);
        }
    }

    public static <V extends View> MergedAppBarLayoutBehavior from(V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (!(layoutParams instanceof CoordinatorLayout.LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
        if (!(behavior instanceof MergedAppBarLayoutBehavior)) {
            throw new IllegalArgumentException("The view is not associated with MergedAppBarLayoutBehavior");
        }
        return (MergedAppBarLayoutBehavior) behavior;
    }
}
