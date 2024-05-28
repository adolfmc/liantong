package com.sinovatech.unicom.separatemodule.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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
import android.support.p086v7.app.AppCompatDialog;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.dialog.BaseDialog;
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
public class BaseDialog extends AppCompatDialog implements LifecycleOwner, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener, DialogInterface.OnShowListener, ActivityAction, AnimAction, ClickAction, HandlerAction, KeyboardAction, ResourcesAction {
    @Nullable
    private List<OnCancelListener> mCancelListeners;
    @Nullable
    private List<OnDismissListener> mDismissListeners;
    private final LifecycleRegistry mLifecycle;
    private final ListenersWrapper<BaseDialog> mListeners;
    @Nullable
    private List<OnShowListener> mShowListeners;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnCancelListener {
        void onCancel(BaseDialog baseDialog);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnClickListener<V extends View> {
        void onClick(BaseDialog baseDialog, V v);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnCreateListener {
        void onCreate(BaseDialog baseDialog);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnDismissListener {
        void onDismiss(BaseDialog baseDialog);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnKeyListener {
        boolean onKey(BaseDialog baseDialog, KeyEvent keyEvent);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnShowListener {
        void onShow(BaseDialog baseDialog);
    }

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

    @Override // com.sinovatech.unicom.separatemodule.dialog.action.HandlerAction
    public /* synthetic */ Handler getHandler() {
        Handler handler;
        handler = HandlerAction.HANDLER;
        return handler;
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

    public BaseDialog(Context context) {
        this(context, 2131951835);
    }

    public BaseDialog(Context context, @StyleRes int i) {
        super(context, i);
        this.mListeners = new ListenersWrapper<>(this);
        this.mLifecycle = new LifecycleRegistry(this);
    }

    public View getContentView() {
        View findViewById = findViewById(16908290);
        if (findViewById instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) findViewById;
            if (viewGroup.getChildCount() == 1) {
                return viewGroup.getChildAt(0);
            }
        }
        return findViewById;
    }

    public void setWidth(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = i;
        window.setAttributes(attributes);
    }

    public void setHeight(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = i;
        window.setAttributes(attributes);
    }

    public void setXOffset(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = i;
        window.setAttributes(attributes);
    }

    public void setYOffset(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.y = i;
        window.setAttributes(attributes);
    }

    public int getGravity() {
        Window window = getWindow();
        if (window == null) {
            return 0;
        }
        return window.getAttributes().gravity;
    }

    public void setGravity(int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setGravity(i);
    }

    public void setWindowAnimations(@StyleRes int i) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setWindowAnimations(i);
    }

    public int getWindowAnimations() {
        Window window = getWindow();
        if (window == null) {
            return -1;
        }
        return window.getAttributes().windowAnimations;
    }

    public void setBackgroundDimEnabled(boolean z) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        if (z) {
            window.addFlags(2);
        } else {
            window.clearFlags(2);
        }
    }

    public void setBackgroundDimAmount(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        window.setDimAmount(f);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        removeCallbacks();
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            ((InputMethodManager) getSystemService(InputMethodManager.class)).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
        super.dismiss();
    }

    @Override // android.arch.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnShowListener(@Nullable DialogInterface.OnShowListener onShowListener) {
        if (onShowListener == null) {
            return;
        }
        addOnShowListener(new ShowListenerWrapper(onShowListener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnCancelListener(@Nullable DialogInterface.OnCancelListener onCancelListener) {
        if (onCancelListener == null) {
            return;
        }
        addOnCancelListener(new CancelListenerWrapper(onCancelListener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener onDismissListener) {
        if (onDismissListener == null) {
            return;
        }
        addOnDismissListener(new DismissListenerWrapper(onDismissListener));
    }

    @Override // android.app.Dialog
    @Deprecated
    public void setOnKeyListener(@Nullable DialogInterface.OnKeyListener onKeyListener) {
        super.setOnKeyListener(onKeyListener);
    }

    public void setOnKeyListener(@Nullable OnKeyListener onKeyListener) {
        super.setOnKeyListener(new KeyListenerWrapper(onKeyListener));
    }

    public void addOnShowListener(@Nullable OnShowListener onShowListener) {
        if (this.mShowListeners == null) {
            this.mShowListeners = new ArrayList();
            super.setOnShowListener(this.mListeners);
        }
        this.mShowListeners.add(onShowListener);
    }

    public void addOnCancelListener(@Nullable OnCancelListener onCancelListener) {
        if (this.mCancelListeners == null) {
            this.mCancelListeners = new ArrayList();
            super.setOnCancelListener(this.mListeners);
        }
        this.mCancelListeners.add(onCancelListener);
    }

    public void addOnDismissListener(@Nullable OnDismissListener onDismissListener) {
        if (this.mDismissListeners == null) {
            this.mDismissListeners = new ArrayList();
            super.setOnDismissListener(this.mListeners);
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

    public void removeOnCancelListener(@Nullable OnCancelListener onCancelListener) {
        List<OnCancelListener> list = this.mCancelListeners;
        if (list == null) {
            return;
        }
        list.remove(onCancelListener);
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
        super.setOnShowListener(this.mListeners);
        this.mShowListeners = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnCancelListeners(@Nullable List<OnCancelListener> list) {
        super.setOnCancelListener(this.mListeners);
        this.mCancelListeners = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnDismissListeners(@Nullable List<OnDismissListener> list) {
        super.setOnDismissListener(this.mListeners);
        this.mDismissListeners = list;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialogInterface) {
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        if (this.mShowListeners == null) {
            return;
        }
        for (int i = 0; i < this.mShowListeners.size(); i++) {
            this.mShowListeners.get(i).onShow(this);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        if (this.mCancelListeners == null) {
            return;
        }
        for (int i = 0; i < this.mCancelListeners.size(); i++) {
            this.mCancelListeners.get(i).onCancel(this);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        if (this.mDismissListeners == null) {
            return;
        }
        for (int i = 0; i < this.mDismissListeners.size(); i++) {
            this.mDismissListeners.get(i).onDismiss(this);
        }
    }

    @Override // android.support.p086v7.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    @Override // android.support.p086v7.app.AppCompatDialog, android.app.Dialog
    public void onStop() {
        super.onStop();
        this.mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Builder<B extends Builder<?>> implements ActivityAction, ClickAction, KeyboardAction, ResourcesAction {
        private final Activity mActivity;
        private int mAnimStyle;
        private float mBackgroundDimAmount;
        private boolean mBackgroundDimEnabled;
        private final List<OnCancelListener> mCancelListeners;
        private boolean mCancelable;
        private boolean mCanceledOnTouchOutside;
        private SparseArray<OnClickListener<?>> mClickArray;
        private View mContentView;
        private final Context mContext;
        private OnCreateListener mCreateListener;
        private BaseDialog mDialog;
        private final List<OnDismissListener> mDismissListeners;
        private int mGravity;
        private int mHeight;
        private OnKeyListener mKeyListener;
        private final List<OnShowListener> mShowListeners;
        private int mThemeId;
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
            this.mThemeId = 2131951835;
            this.mAnimStyle = -1;
            this.mWidth = -2;
            this.mHeight = -2;
            this.mGravity = 0;
            this.mCancelable = true;
            this.mCanceledOnTouchOutside = true;
            this.mBackgroundDimEnabled = true;
            this.mBackgroundDimAmount = 0.5f;
            this.mShowListeners = new ArrayList();
            this.mCancelListeners = new ArrayList();
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
                this.mDialog.setContentView(view);
                return this;
            }
            ViewGroup.LayoutParams layoutParams = this.mContentView.getLayoutParams();
            if (layoutParams != null && this.mWidth == -2 && this.mHeight == -2) {
                setWidth(layoutParams.width);
                setHeight(layoutParams.height);
            }
            if (this.mGravity == 0) {
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
        public B setThemeStyle(@StyleRes int i) {
            this.mThemeId = i;
            if (isCreated()) {
                throw new IllegalStateException("are you ok?");
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setAnimStyle(@StyleRes int i) {
            this.mAnimStyle = i;
            if (isCreated()) {
                this.mDialog.setWindowAnimations(i);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setWidth(int i) {
            this.mWidth = i;
            if (isCreated()) {
                this.mDialog.setWidth(i);
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
                this.mDialog.setHeight(i);
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
            if (isCreated()) {
                this.mDialog.setGravity(i);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setXOffset(int i) {
            this.mXOffset = i;
            if (isCreated()) {
                this.mDialog.setXOffset(i);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setYOffset(int i) {
            this.mYOffset = i;
            if (isCreated()) {
                this.mDialog.setYOffset(i);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setCancelable(boolean z) {
            this.mCancelable = z;
            if (isCreated()) {
                this.mDialog.setCancelable(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setCanceledOnTouchOutside(boolean z) {
            this.mCanceledOnTouchOutside = z;
            if (isCreated() && this.mCancelable) {
                this.mDialog.setCanceledOnTouchOutside(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setBackgroundDimEnabled(boolean z) {
            this.mBackgroundDimEnabled = z;
            if (isCreated()) {
                this.mDialog.setBackgroundDimEnabled(z);
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setBackgroundDimAmount(@FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
            this.mBackgroundDimAmount = f;
            if (isCreated()) {
                this.mDialog.setBackgroundDimAmount(f);
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
        public B addOnCancelListener(@NonNull OnCancelListener onCancelListener) {
            this.mCancelListeners.add(onCancelListener);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B addOnDismissListener(@NonNull OnDismissListener onDismissListener) {
            this.mDismissListeners.add(onDismissListener);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setOnKeyListener(@NonNull OnKeyListener onKeyListener) {
            this.mKeyListener = onKeyListener;
            if (isCreated()) {
                this.mDialog.setOnKeyListener(onKeyListener);
            }
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
            if (isCreated() && (findViewById = this.mDialog.findViewById(i)) != null) {
                findViewById.setOnClickListener(new ViewClickWrapper(onClickListener));
            }
            return this;
        }

        @SuppressLint({"RtlHardcoded"})
        public BaseDialog create() {
            if (this.mContentView == null) {
                throw new IllegalArgumentException("are you ok?");
            }
            if (isShowing()) {
                dismiss();
            }
            if (this.mGravity == 0) {
                this.mGravity = 17;
            }
            if (this.mAnimStyle == -1) {
                int i = this.mGravity;
                if (i == 3) {
                    this.mAnimStyle = 2131951869;
                } else if (i == 5) {
                    this.mAnimStyle = 2131951893;
                } else if (i == 48) {
                    this.mAnimStyle = 2131952067;
                } else if (i == 80) {
                    this.mAnimStyle = 2131951837;
                } else {
                    this.mAnimStyle = -1;
                }
            }
            this.mDialog = createDialog(this.mContext, this.mThemeId);
            this.mDialog.setContentView(this.mContentView);
            this.mDialog.setCancelable(this.mCancelable);
            if (this.mCancelable) {
                this.mDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);
            }
            this.mDialog.setOnShowListeners(this.mShowListeners);
            this.mDialog.setOnCancelListeners(this.mCancelListeners);
            this.mDialog.setOnDismissListeners(this.mDismissListeners);
            this.mDialog.setOnKeyListener(this.mKeyListener);
            Window window = this.mDialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = this.mWidth;
                attributes.height = this.mHeight;
                attributes.gravity = this.mGravity;
                attributes.x = this.mXOffset;
                attributes.y = this.mYOffset;
                attributes.windowAnimations = this.mAnimStyle;
                if (this.mBackgroundDimEnabled) {
                    window.addFlags(2);
                    window.setDimAmount(this.mBackgroundDimAmount);
                } else {
                    window.clearFlags(2);
                }
                window.setAttributes(attributes);
            }
            int i2 = 0;
            while (true) {
                SparseArray<OnClickListener<?>> sparseArray = this.mClickArray;
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
                DialogLifecycle.with(activity, this.mDialog);
            }
            OnCreateListener onCreateListener = this.mCreateListener;
            if (onCreateListener != null) {
                onCreateListener.onCreate(this.mDialog);
            }
            return this.mDialog;
        }

        public void show() {
            Activity activity = this.mActivity;
            if (activity == null || activity.isFinishing() || this.mActivity.isDestroyed()) {
                return;
            }
            if (!isCreated()) {
                create();
            }
            if (isShowing()) {
                return;
            }
            this.mDialog.show();
        }

        public void dismiss() {
            BaseDialog baseDialog;
            Activity activity = this.mActivity;
            if (activity == null || activity.isFinishing() || this.mActivity.isDestroyed() || (baseDialog = this.mDialog) == null) {
                return;
            }
            baseDialog.dismiss();
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.action.ActivityAction, com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction
        public Context getContext() {
            return this.mContext;
        }

        public boolean isCreated() {
            return this.mDialog != null;
        }

        public boolean isShowing() {
            return isCreated() && this.mDialog.isShowing();
        }

        @NonNull
        protected BaseDialog createDialog(Context context, @StyleRes int i) {
            return new BaseDialog(context, i);
        }

        public final void post(Runnable runnable) {
            if (isShowing()) {
                this.mDialog.post(runnable);
            } else {
                addOnShowListener(new ShowPostWrapper(runnable));
            }
        }

        public final void postDelayed(Runnable runnable, long j) {
            if (isShowing()) {
                this.mDialog.postDelayed(runnable, j);
            } else {
                addOnShowListener(new ShowPostDelayedWrapper(runnable, j));
            }
        }

        public final void postAtTime(Runnable runnable, long j) {
            if (isShowing()) {
                this.mDialog.postAtTime(runnable, j);
            } else {
                addOnShowListener(new ShowPostAtTimeWrapper(runnable, j));
            }
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

        public BaseDialog getDialog() {
            return this.mDialog;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class DialogLifecycle implements Application.ActivityLifecycleCallbacks, OnDismissListener, OnShowListener {
        private Activity mActivity;
        private BaseDialog mDialog;
        private int mDialogAnim;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
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
        public static void with(Activity activity, BaseDialog baseDialog) {
            new DialogLifecycle(activity, baseDialog);
        }

        private DialogLifecycle(Activity activity, BaseDialog baseDialog) {
            this.mActivity = activity;
            baseDialog.addOnShowListener(this);
            baseDialog.addOnDismissListener(this);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NonNull Activity activity) {
            BaseDialog baseDialog;
            if (this.mActivity == activity && (baseDialog = this.mDialog) != null && baseDialog.isShowing()) {
                this.mDialog.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.dialog.-$$Lambda$BaseDialog$DialogLifecycle$nfXzPeWwoMyIa2c57eDPQ16fClU
                    @Override // java.lang.Runnable
                    public final void run() {
                        BaseDialog.DialogLifecycle.lambda$onActivityResumed$0(BaseDialog.DialogLifecycle.this);
                    }
                }, 100L);
            }
        }

        public static /* synthetic */ void lambda$onActivityResumed$0(DialogLifecycle dialogLifecycle) {
            BaseDialog baseDialog = dialogLifecycle.mDialog;
            if (baseDialog == null || !baseDialog.isShowing()) {
                return;
            }
            dialogLifecycle.mDialog.setWindowAnimations(dialogLifecycle.mDialogAnim);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NonNull Activity activity) {
            BaseDialog baseDialog;
            if (this.mActivity == activity && (baseDialog = this.mDialog) != null && baseDialog.isShowing()) {
                this.mDialogAnim = this.mDialog.getWindowAnimations();
                this.mDialog.setWindowAnimations(0);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NonNull Activity activity) {
            if (this.mActivity != activity) {
                return;
            }
            unregisterActivityLifecycleCallbacks();
            this.mActivity = null;
            BaseDialog baseDialog = this.mDialog;
            if (baseDialog == null) {
                return;
            }
            baseDialog.removeOnShowListener(this);
            this.mDialog.removeOnDismissListener(this);
            if (this.mDialog.isShowing()) {
                this.mDialog.dismiss();
            }
            this.mDialog = null;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            this.mDialog = baseDialog;
            registerActivityLifecycleCallbacks();
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnDismissListener
        public void onDismiss(BaseDialog baseDialog) {
            this.mDialog = null;
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
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class ListenersWrapper<T extends DialogInterface.OnShowListener & DialogInterface.OnCancelListener & DialogInterface.OnDismissListener> extends SoftReference<T> implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
        private ListenersWrapper(T t) {
            super(t);
        }

        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            if (get() == 0) {
                return;
            }
            ((DialogInterface.OnShowListener) get()).onShow(dialogInterface);
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            if (get() == 0) {
                return;
            }
            ((DialogInterface.OnCancelListener) ((DialogInterface.OnShowListener) get())).onCancel(dialogInterface);
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (get() == 0) {
                return;
            }
            ((DialogInterface.OnDismissListener) ((DialogInterface.OnShowListener) get())).onDismiss(dialogInterface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class ViewClickWrapper implements View.OnClickListener {
        private final BaseDialog mDialog;
        @Nullable
        private final OnClickListener mListener;

        private ViewClickWrapper(BaseDialog baseDialog, @Nullable OnClickListener onClickListener) {
            this.mDialog = baseDialog;
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
            onClickListener.onClick(this.mDialog, view);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ShowListenerWrapper extends SoftReference<DialogInterface.OnShowListener> implements OnShowListener {
        private ShowListenerWrapper(DialogInterface.OnShowListener onShowListener) {
            super(onShowListener);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            if (get() == null) {
                return;
            }
            get().onShow(baseDialog);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class CancelListenerWrapper extends SoftReference<DialogInterface.OnCancelListener> implements OnCancelListener {
        private CancelListenerWrapper(DialogInterface.OnCancelListener onCancelListener) {
            super(onCancelListener);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnCancelListener
        public void onCancel(BaseDialog baseDialog) {
            if (get() == null) {
                return;
            }
            get().onCancel(baseDialog);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class DismissListenerWrapper extends SoftReference<DialogInterface.OnDismissListener> implements OnDismissListener {
        private DismissListenerWrapper(DialogInterface.OnDismissListener onDismissListener) {
            super(onDismissListener);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnDismissListener
        public void onDismiss(BaseDialog baseDialog) {
            if (get() == null) {
                return;
            }
            get().onDismiss(baseDialog);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class KeyListenerWrapper implements DialogInterface.OnKeyListener {
        private final OnKeyListener mListener;

        private KeyListenerWrapper(OnKeyListener onKeyListener) {
            this.mListener = onKeyListener;
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            OnKeyListener onKeyListener = this.mListener;
            if (onKeyListener == null || !(dialogInterface instanceof BaseDialog)) {
                return false;
            }
            return onKeyListener.onKey((BaseDialog) dialogInterface, keyEvent);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ShowPostWrapper implements OnShowListener {
        private final Runnable mRunnable;

        private ShowPostWrapper(Runnable runnable) {
            this.mRunnable = runnable;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable == null) {
                return;
            }
            baseDialog.removeOnShowListener(this);
            baseDialog.post(this.mRunnable);
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

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable == null) {
                return;
            }
            baseDialog.removeOnShowListener(this);
            baseDialog.postDelayed(this.mRunnable, this.mDelayMillis);
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

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnShowListener
        public void onShow(BaseDialog baseDialog) {
            if (this.mRunnable == null) {
                return;
            }
            baseDialog.removeOnShowListener(this);
            baseDialog.postAtTime(this.mRunnable, this.mUptimeMillis);
        }
    }
}
