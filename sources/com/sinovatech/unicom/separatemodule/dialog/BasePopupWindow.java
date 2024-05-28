package com.sinovatech.unicom.separatemodule.dialog;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.p083v4.content.ContextCompat;
import android.support.p083v4.widget.PopupWindowCompat;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction;
import com.sinovatech.unicom.separatemodule.dialog.action.AnimAction;
import com.sinovatech.unicom.separatemodule.dialog.action.ClickAction;
import com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction;
import com.sinovatech.unicom.separatemodule.dialog.action.KeyboardAction;
import com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BasePopupWindow extends PopupWindow implements PopupWindow.OnDismissListener, ActivityAction, AnimAction, ClickAction, HandlerAction, KeyboardAction {
    private final Context mContext;
    private List<OnDismissListener> mDismissListeners;
    private PopupBackground mPopupBackground;
    private List<OnShowListener> mShowListeners;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnClickListener<V extends View> {
        void onClick(BasePopupWindow basePopupWindow, V v);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnCreateListener {
        void onCreate(BasePopupWindow basePopupWindow);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnDismissListener {
        void onDismiss(BasePopupWindow basePopupWindow);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnShowListener {
        void onShow(BasePopupWindow basePopupWindow);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction
    public /* synthetic */ Activity getActivity() {
        return ActivityAction.CC.$default$getActivity(this);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction
    public /* synthetic */ Handler getHandler() {
        Handler handler;
        handler = HandlerAction.HANDLER;
        return handler;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.KeyboardAction
    public /* synthetic */ void hideKeyboard(View view) {
        KeyboardAction.CC.$default$hideKeyboard(this, view);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction, android.view.View.OnClickListener
    public /* synthetic */ void onClick(View view) {
        ClickAction.CC.$default$onClick(this, view);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction
    public /* synthetic */ boolean post(Runnable runnable) {
        boolean postDelayed;
        postDelayed = postDelayed(runnable, 0L);
        return postDelayed;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction
    public /* synthetic */ boolean postAtTime(Runnable runnable, long j) {
        boolean postAtTime;
        postAtTime = HandlerAction.HANDLER.postAtTime(runnable, this, j);
        return postAtTime;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction
    public /* synthetic */ boolean postDelayed(Runnable runnable, long j) {
        return HandlerAction.CC.$default$postDelayed(this, runnable, j);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction
    public /* synthetic */ void removeCallbacks() {
        HandlerAction.HANDLER.removeCallbacksAndMessages(this);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction
    public /* synthetic */ void removeCallbacks(Runnable runnable) {
        HandlerAction.HANDLER.removeCallbacks(runnable);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
    public /* synthetic */ void setOnClickListener(@Nullable View.OnClickListener onClickListener, @IdRes int... iArr) {
        ClickAction.CC.$default$setOnClickListener(this, onClickListener, iArr);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
    public /* synthetic */ void setOnClickListener(@Nullable View.OnClickListener onClickListener, View... viewArr) {
        ClickAction.CC.$default$setOnClickListener(this, onClickListener, viewArr);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
    public /* synthetic */ void setOnClickListener(@IdRes int... iArr) {
        setOnClickListener(this, iArr);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
    public /* synthetic */ void setOnClickListener(View... viewArr) {
        setOnClickListener(this, viewArr);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.KeyboardAction
    public /* synthetic */ void showKeyboard(View view) {
        KeyboardAction.CC.$default$showKeyboard(this, view);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction
    public /* synthetic */ void startActivity(Intent intent) {
        ActivityAction.CC.$default$startActivity(this, intent);
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction
    public /* synthetic */ void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(getContext(), cls));
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.KeyboardAction
    public /* synthetic */ void toggleSoftInput(View view) {
        KeyboardAction.CC.$default$toggleSoftInput(this, view);
    }

    public BasePopupWindow(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction, com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
    public Context getContext() {
        return this.mContext;
    }

    @Override // android.widget.PopupWindow
    @Deprecated
    public void setOnDismissListener(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        if (onDismissListener == null) {
            return;
        }
        addOnDismissListener(new DismissListenerWrapper(onDismissListener));
    }

    public void addOnShowListener(@Nullable OnShowListener onShowListener) {
        if (this.mShowListeners == null) {
            this.mShowListeners = new ArrayList();
        }
        this.mShowListeners.add(onShowListener);
    }

    public void addOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        if (this.mDismissListeners == null) {
            this.mDismissListeners = new ArrayList();
            super.setOnDismissListener(this);
        }
        this.mDismissListeners.add(onDismissListener);
    }

    public void removeOnShowListener(@Nullable OnShowListener onShowListener) {
        List<OnShowListener> list = this.mShowListeners;
        if (list == null) {
            return;
        }
        list.remove(onShowListener);
    }

    public void removeOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        List<OnDismissListener> list = this.mDismissListeners;
        if (list == null) {
            return;
        }
        list.remove(onDismissListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnShowListeners(@Nullable List<OnShowListener> list) {
        this.mShowListeners = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnDismissListeners(@Nullable List<OnDismissListener> list) {
        super.setOnDismissListener(this);
        this.mDismissListeners = list;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        List<OnDismissListener> list = this.mDismissListeners;
        if (list == null) {
            return;
        }
        for (OnDismissListener onDismissListener : list) {
            onDismissListener.onDismiss(this);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (isShowing() || getContentView() == null) {
            return;
        }
        List<OnShowListener> list = this.mShowListeners;
        if (list != null) {
            for (OnShowListener onShowListener : list) {
                onShowListener.onShow(this);
            }
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void showAtLocation(View view, int i, int i2, int i3) {
        if (isShowing() || getContentView() == null) {
            return;
        }
        List<OnShowListener> list = this.mShowListeners;
        if (list != null) {
            for (OnShowListener onShowListener : list) {
                onShowListener.onShow(this);
            }
        }
        super.showAtLocation(view, i, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        super.dismiss();
        removeCallbacks();
    }

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
    public <V extends View> V findViewById(@IdRes int i) {
        return (V) getContentView().findViewById(i);
    }

    @Override // android.widget.PopupWindow
    public void setWindowLayoutType(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.setWindowLayoutType(i);
        } else {
            PopupWindowCompat.setWindowLayoutType(this, i);
        }
    }

    @Override // android.widget.PopupWindow
    public int getWindowLayoutType() {
        if (Build.VERSION.SDK_INT >= 23) {
            return super.getWindowLayoutType();
        }
        return PopupWindowCompat.getWindowLayoutType(this);
    }

    @Override // android.widget.PopupWindow
    public void setOverlapAnchor(boolean z) {
        if (Build.VERSION.SDK_INT >= 23) {
            super.setOverlapAnchor(z);
        } else {
            PopupWindowCompat.setOverlapAnchor(this, z);
        }
    }

    public void setBackgroundDimAmount(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        float f2 = 1.0f - f;
        if (isShowing()) {
            setActivityAlpha(f2);
        }
        if (this.mPopupBackground == null && f2 != 1.0f) {
            this.mPopupBackground = new PopupBackground();
            addOnShowListener(this.mPopupBackground);
            addOnDismissListener(this.mPopupBackground);
        }
        PopupBackground popupBackground = this.mPopupBackground;
        if (popupBackground != null) {
            popupBackground.setAlpha(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActivityAlpha(float f) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        final WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(attributes.alpha, f);
        ofFloat.setDuration(300L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.dialog.-$$Lambda$BasePopupWindow$vWFj-8F6WrRL4WSL0fcXeB4FG7I
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                BasePopupWindow.lambda$setActivityAlpha$0(attributes, activity, valueAnimator);
            }
        });
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setActivityAlpha$0(WindowManager.LayoutParams layoutParams, Activity activity, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (floatValue != layoutParams.alpha) {
            layoutParams.alpha = floatValue;
            activity.getWindow().setAttributes(layoutParams);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Builder<B extends Builder<?>> implements ActivityAction, ClickAction, KeyboardAction, ResourcesAction {
        private static final int DEFAULT_ANCHORED_GRAVITY = 8388659;
        private final Activity mActivity;
        private int mAnimations;
        private float mBackgroundDimAmount;
        private SparseArray<OnClickListener<? extends View>> mClickArray;
        private View mContentView;
        private final Context mContext;
        private OnCreateListener mCreateListener;
        private final List<OnDismissListener> mDismissListeners;
        private boolean mFocusable;
        private int mGravity;
        private int mHeight;
        private boolean mOutsideTouchable;
        private BasePopupWindow mPopupWindow;
        private final List<OnShowListener> mShowListeners;
        private boolean mTouchable;
        private int mWidth;
        private int mXOffset;
        private int mYOffset;

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction
        public /* synthetic */ Activity getActivity() {
            return ActivityAction.CC.$default$getActivity(this);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
        @ColorInt
        public /* synthetic */ int getColor(@ColorRes int i) {
            int color;
            color = ContextCompat.getColor(getContext(), i);
            return color;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
        public /* synthetic */ Drawable getDrawable(@DrawableRes int i) {
            Drawable drawable;
            drawable = ContextCompat.getDrawable(getContext(), i);
            return drawable;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
        public /* synthetic */ Resources getResources() {
            Resources resources;
            resources = getContext().getResources();
            return resources;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
        public /* synthetic */ String getString(@StringRes int i) {
            String string;
            string = getContext().getString(i);
            return string;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
        public /* synthetic */ String getString(@StringRes int i, Object... objArr) {
            String string;
            string = getResources().getString(i, objArr);
            return string;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
        public /* synthetic */ <S> S getSystemService(@NonNull Class<S> cls) {
            Object systemService;
            systemService = ContextCompat.getSystemService(getContext(), cls);
            return (S) systemService;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.KeyboardAction
        public /* synthetic */ void hideKeyboard(View view) {
            KeyboardAction.CC.$default$hideKeyboard(this, view);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction, android.view.View.OnClickListener
        public /* synthetic */ void onClick(View view) {
            ClickAction.CC.$default$onClick(this, view);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
        public /* synthetic */ void setOnClickListener(@Nullable View.OnClickListener onClickListener, @IdRes int... iArr) {
            ClickAction.CC.$default$setOnClickListener(this, onClickListener, iArr);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
        public /* synthetic */ void setOnClickListener(@Nullable View.OnClickListener onClickListener, View... viewArr) {
            ClickAction.CC.$default$setOnClickListener(this, onClickListener, viewArr);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
        public /* synthetic */ void setOnClickListener(@IdRes int... iArr) {
            setOnClickListener(this, iArr);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
        public /* synthetic */ void setOnClickListener(View... viewArr) {
            setOnClickListener(this, viewArr);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.KeyboardAction
        public /* synthetic */ void showKeyboard(View view) {
            KeyboardAction.CC.$default$showKeyboard(this, view);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction
        public /* synthetic */ void startActivity(Intent intent) {
            ActivityAction.CC.$default$startActivity(this, intent);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction
        public /* synthetic */ void startActivity(Class<? extends Activity> cls) {
            startActivity(new Intent(getContext(), cls));
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.KeyboardAction
        public /* synthetic */ void toggleSoftInput(View view) {
            KeyboardAction.CC.$default$toggleSoftInput(this, view);
        }

        public Builder(Activity activity) {
            this((Context) activity);
        }

        public Builder(Context context) {
            this.mAnimations = -1;
            this.mWidth = -2;
            this.mHeight = -2;
            this.mGravity = DEFAULT_ANCHORED_GRAVITY;
            this.mTouchable = true;
            this.mFocusable = true;
            this.mOutsideTouchable = false;
            this.mShowListeners = new ArrayList();
            this.mDismissListeners = new ArrayList();
            this.mContext = context;
            this.mActivity = getActivity();
        }

        public B setContentView(@LayoutRes int i) {
            return setContentView(LayoutInflater.from(this.mContext).inflate(i, (ViewGroup) new FrameLayout(this.mContext), false));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setContentView(View view) {
            int i;
            if (view == null) {
                throw new IllegalArgumentException("are you ok?");
            }
            this.mContentView = view;
            if (isCreated()) {
                this.mPopupWindow.setContentView(view);
                return this;
            }
            ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
            if (layoutParams != null && this.mWidth == -2 && this.mHeight == -2) {
                setWidth(layoutParams.width);
                setHeight(layoutParams.height);
            }
            if (this.mGravity == DEFAULT_ANCHORED_GRAVITY) {
                if (layoutParams instanceof FrameLayout.LayoutParams) {
                    int i2 = ((FrameLayout.LayoutParams) layoutParams).gravity;
                    if (i2 != -1) {
                        setGravity(i2);
                    }
                } else if ((layoutParams instanceof LinearLayout.LayoutParams) && (i = ((LinearLayout.LayoutParams) layoutParams).gravity) != -1) {
                    setGravity(i);
                }
                if (this.mGravity == 0) {
                    setGravity(17);
                }
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setAnimStyle(@StyleRes int i) {
            this.mAnimations = i;
            if (isCreated()) {
                this.mPopupWindow.setAnimationStyle(i);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setWidth(int i) {
            this.mWidth = i;
            if (isCreated()) {
                this.mPopupWindow.setWidth(i);
                return this;
            }
            View view = this.mContentView;
            ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.width = i;
                this.mContentView.setLayoutParams(layoutParams);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setHeight(int i) {
            this.mHeight = i;
            if (isCreated()) {
                this.mPopupWindow.setHeight(i);
                return this;
            }
            View view = this.mContentView;
            ViewGroup.LayoutParams layoutParams = view != null ? view.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.height = i;
                this.mContentView.setLayoutParams(layoutParams);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setGravity(int i) {
            this.mGravity = Gravity.getAbsoluteGravity(i, getResources().getConfiguration().getLayoutDirection());
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setXOffset(int i) {
            this.mXOffset = i;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setYOffset(int i) {
            this.mYOffset = i;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setTouchable(boolean z) {
            this.mTouchable = z;
            if (isCreated()) {
                this.mPopupWindow.setTouchable(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setFocusable(boolean z) {
            this.mFocusable = z;
            if (isCreated()) {
                this.mPopupWindow.setFocusable(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setOutsideTouchable(boolean z) {
            this.mOutsideTouchable = z;
            if (isCreated()) {
                this.mPopupWindow.setOutsideTouchable(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setBackgroundDimAmount(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
            this.mBackgroundDimAmount = f;
            if (isCreated()) {
                this.mPopupWindow.setBackgroundDimAmount(f);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setOnCreateListener(@NonNull OnCreateListener onCreateListener) {
            this.mCreateListener = onCreateListener;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B addOnShowListener(@NonNull OnShowListener onShowListener) {
            this.mShowListeners.add(onShowListener);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B addOnDismissListener(@NonNull OnDismissListener onDismissListener) {
            this.mDismissListeners.add(onDismissListener);
            return this;
        }

        public B setText(@IdRes int i, @StringRes int i2) {
            return setText(i, getString(i2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setText(@IdRes int i, CharSequence charSequence) {
            ((TextView) findViewById(i)).setText(charSequence);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setTextColor(@IdRes int i, @ColorInt int i2) {
            ((TextView) findViewById(i)).setTextColor(i2);
            return this;
        }

        public B setHint(@IdRes int i, @StringRes int i2) {
            return setHint(i, getString(i2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setHint(@IdRes int i, CharSequence charSequence) {
            ((TextView) findViewById(i)).setHint(charSequence);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setVisibility(@IdRes int i, int i2) {
            findViewById(i).setVisibility(i2);
            return this;
        }

        public B setBackground(@IdRes int i, @DrawableRes int i2) {
            return setBackground(i, ContextCompat.getDrawable(this.mContext, i2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setBackground(@IdRes int i, Drawable drawable) {
            findViewById(i).setBackground(drawable);
            return this;
        }

        public B setImageDrawable(@IdRes int i, @DrawableRes int i2) {
            return setBackground(i, ContextCompat.getDrawable(this.mContext, i2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setImageDrawable(@IdRes int i, Drawable drawable) {
            ((ImageView) findViewById(i)).setImageDrawable(drawable);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setOnClickListener(@IdRes int i, @NonNull OnClickListener<?> onClickListener) {
            View findViewById;
            if (this.mClickArray == null) {
                this.mClickArray = new SparseArray<>();
            }
            this.mClickArray.put(i, onClickListener);
            if (isCreated() && (findViewById = this.mPopupWindow.findViewById(i)) != null) {
                findViewById.setOnClickListener(new ViewClickWrapper(onClickListener));
            }
            return this;
        }

        @SuppressLint({"RtlHardcoded"})
        public BasePopupWindow create() {
            if (this.mContentView == null) {
                throw new IllegalArgumentException("are you ok?");
            }
            if (isShowing()) {
                dismiss();
            }
            if (this.mGravity == DEFAULT_ANCHORED_GRAVITY) {
                this.mGravity = 17;
            }
            if (this.mAnimations == -1) {
                int i = this.mGravity;
                if (i == 3) {
                    this.mAnimations = 2131951869;
                } else if (i == 5) {
                    this.mAnimations = 2131951893;
                } else if (i == 48) {
                    this.mAnimations = 2131952067;
                } else if (i == 80) {
                    this.mAnimations = 2131951837;
                } else {
                    this.mAnimations = -1;
                }
            }
            this.mPopupWindow = createPopupWindow(this.mContext);
            this.mPopupWindow.setContentView(this.mContentView);
            this.mPopupWindow.setWidth(this.mWidth);
            this.mPopupWindow.setHeight(this.mHeight);
            this.mPopupWindow.setAnimationStyle(this.mAnimations);
            this.mPopupWindow.setFocusable(this.mFocusable);
            this.mPopupWindow.setTouchable(this.mTouchable);
            this.mPopupWindow.setOutsideTouchable(this.mOutsideTouchable);
            int i2 = 0;
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.mPopupWindow.setOnShowListeners(this.mShowListeners);
            this.mPopupWindow.setOnDismissListeners(this.mDismissListeners);
            this.mPopupWindow.setBackgroundDimAmount(this.mBackgroundDimAmount);
            while (true) {
                SparseArray<OnClickListener<? extends View>> sparseArray = this.mClickArray;
                if (sparseArray == null || i2 >= sparseArray.size()) {
                    break;
                }
                View findViewById = this.mContentView.findViewById(this.mClickArray.keyAt(i2));
                if (findViewById != null) {
                    findViewById.setOnClickListener(new ViewClickWrapper(this.mClickArray.valueAt(i2)));
                }
                i2++;
            }
            Activity activity = this.mActivity;
            if (activity != null) {
                PopupWindowLifecycle.with(activity, this.mPopupWindow);
            }
            OnCreateListener onCreateListener = this.mCreateListener;
            if (onCreateListener != null) {
                onCreateListener.onCreate(this.mPopupWindow);
            }
            return this.mPopupWindow;
        }

        public void showAsDropDown(View view) {
            Activity activity = this.mActivity;
            if (activity == null || activity.isFinishing() || this.mActivity.isDestroyed()) {
                return;
            }
            if (!isCreated()) {
                create();
            }
            this.mPopupWindow.showAsDropDown(view, this.mXOffset, this.mYOffset, this.mGravity);
        }

        public void showAtLocation(View view) {
            Activity activity = this.mActivity;
            if (activity == null || activity.isFinishing() || this.mActivity.isDestroyed()) {
                return;
            }
            if (!isCreated()) {
                create();
            }
            this.mPopupWindow.showAtLocation(view, this.mGravity, this.mXOffset, this.mYOffset);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction, com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
        public Context getContext() {
            return this.mContext;
        }

        public boolean isCreated() {
            return this.mPopupWindow != null;
        }

        public boolean isShowing() {
            return isCreated() && this.mPopupWindow.isShowing();
        }

        public void dismiss() {
            BasePopupWindow basePopupWindow;
            Activity activity = this.mActivity;
            if (activity == null || activity.isFinishing() || this.mActivity.isDestroyed() || (basePopupWindow = this.mPopupWindow) == null) {
                return;
            }
            basePopupWindow.dismiss();
        }

        @NonNull
        protected BasePopupWindow createPopupWindow(Context context) {
            return new BasePopupWindow(context);
        }

        public View getContentView() {
            return this.mContentView;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ClickAction
        public <V extends View> V findViewById(@IdRes int i) {
            View view = this.mContentView;
            if (view == null) {
                throw new IllegalStateException("are you ok?");
            }
            return (V) view.findViewById(i);
        }

        @Nullable
        public BasePopupWindow getPopupWindow() {
            return this.mPopupWindow;
        }

        public final void post(Runnable runnable) {
            if (isShowing()) {
                this.mPopupWindow.post(runnable);
            } else {
                addOnShowListener(new ShowPostWrapper(runnable));
            }
        }

        public final void postDelayed(Runnable runnable, long j) {
            if (isShowing()) {
                this.mPopupWindow.postDelayed(runnable, j);
            } else {
                addOnShowListener(new ShowPostDelayedWrapper(runnable, j));
            }
        }

        public final void postAtTime(Runnable runnable, long j) {
            if (isShowing()) {
                this.mPopupWindow.postAtTime(runnable, j);
            } else {
                addOnShowListener(new ShowPostAtTimeWrapper(runnable, j));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class PopupWindowLifecycle implements Application.ActivityLifecycleCallbacks, OnDismissListener, OnShowListener {
        private Activity mActivity;
        private BasePopupWindow mPopupWindow;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NonNull Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NonNull Activity activity) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void with(Activity activity, BasePopupWindow basePopupWindow) {
            new PopupWindowLifecycle(activity, basePopupWindow);
        }

        private PopupWindowLifecycle(Activity activity, BasePopupWindow basePopupWindow) {
            this.mActivity = activity;
            basePopupWindow.addOnShowListener(this);
            basePopupWindow.addOnDismissListener(this);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
            if (this.mActivity != activity) {
                return;
            }
            unregisterActivityLifecycleCallbacks();
            this.mActivity = null;
            BasePopupWindow basePopupWindow = this.mPopupWindow;
            if (basePopupWindow == null) {
                return;
            }
            basePopupWindow.removeOnShowListener(this);
            this.mPopupWindow.removeOnDismissListener(this);
            if (this.mPopupWindow.isShowing()) {
                this.mPopupWindow.dismiss();
            }
            this.mPopupWindow = null;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow basePopupWindow) {
            this.mPopupWindow = basePopupWindow;
            registerActivityLifecycleCallbacks();
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow.OnDismissListener
        public void onDismiss(BasePopupWindow basePopupWindow) {
            this.mPopupWindow = null;
            unregisterActivityLifecycleCallbacks();
        }

        private void registerActivityLifecycleCallbacks() {
            if (this.mActivity == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                this.mActivity.registerActivityLifecycleCallbacks(this);
            } else {
                this.mActivity.getApplication().registerActivityLifecycleCallbacks(this);
            }
        }

        private void unregisterActivityLifecycleCallbacks() {
            if (this.mActivity == null) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                this.mActivity.unregisterActivityLifecycleCallbacks(this);
            } else {
                this.mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class PopupBackground implements OnDismissListener, OnShowListener {
        private float mAlpha;

        private PopupBackground() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlpha(float f) {
            this.mAlpha = f;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow basePopupWindow) {
            basePopupWindow.setActivityAlpha(this.mAlpha);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow.OnDismissListener
        public void onDismiss(BasePopupWindow basePopupWindow) {
            basePopupWindow.setActivityAlpha(1.0f);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class DismissListenerWrapper extends SoftReference<PopupWindow.OnDismissListener> implements OnDismissListener {
        private DismissListenerWrapper(PopupWindow.OnDismissListener onDismissListener) {
            super(onDismissListener);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow.OnDismissListener
        public void onDismiss(BasePopupWindow basePopupWindow) {
            if (get() == null) {
                return;
            }
            get().onDismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class ViewClickWrapper implements View.OnClickListener {
        private final BasePopupWindow mBasePopupWindow;
        @Nullable
        private final OnClickListener mListener;

        private ViewClickWrapper(BasePopupWindow basePopupWindow, @Nullable OnClickListener onClickListener) {
            this.mBasePopupWindow = basePopupWindow;
            this.mListener = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            OnClickListener onClickListener = this.mListener;
            if (onClickListener == null) {
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
            onClickListener.onClick(this.mBasePopupWindow, view);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ShowPostWrapper implements OnShowListener {
        private final Runnable mRunnable;

        private ShowPostWrapper(Runnable runnable) {
            this.mRunnable = runnable;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow basePopupWindow) {
            if (this.mRunnable == null) {
                return;
            }
            basePopupWindow.removeOnShowListener(this);
            basePopupWindow.post(this.mRunnable);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ShowPostDelayedWrapper implements OnShowListener {
        private final long mDelayMillis;
        private final Runnable mRunnable;

        private ShowPostDelayedWrapper(Runnable runnable, long j) {
            this.mRunnable = runnable;
            this.mDelayMillis = j;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow basePopupWindow) {
            if (this.mRunnable == null) {
                return;
            }
            basePopupWindow.removeOnShowListener(this);
            basePopupWindow.postDelayed(this.mRunnable, this.mDelayMillis);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ShowPostAtTimeWrapper implements OnShowListener {
        private final Runnable mRunnable;
        private final long mUptimeMillis;

        private ShowPostAtTimeWrapper(Runnable runnable, long j) {
            this.mRunnable = runnable;
            this.mUptimeMillis = j;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BasePopupWindow.OnShowListener
        public void onShow(BasePopupWindow basePopupWindow) {
            if (this.mRunnable == null) {
                return;
            }
            basePopupWindow.removeOnShowListener(this);
            basePopupWindow.postAtTime(this.mRunnable, this.mUptimeMillis);
        }
    }
}
