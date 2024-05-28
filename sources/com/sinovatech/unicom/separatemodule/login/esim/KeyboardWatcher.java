package com.sinovatech.unicom.separatemodule.login.esim;

import android.app.Activity;
import android.app.Application;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class KeyboardWatcher implements Application.ActivityLifecycleCallbacks, ViewTreeObserver.OnGlobalLayoutListener {
    private Activity mActivity;
    private View mContentView;
    @Nullable
    private SoftKeyboardStateListener mListeners;
    private boolean mSoftKeyboardOpened;
    private int mStatusBarHeight;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface SoftKeyboardStateListener {
        void onSoftKeyboardClosed();

        void onSoftKeyboardOpened(int i);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {
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

    public static KeyboardWatcher with(Activity activity) {
        return new KeyboardWatcher(activity);
    }

    private KeyboardWatcher(Activity activity) {
        this.mActivity = activity;
        this.mContentView = activity.findViewById(16908290);
        if (Build.VERSION.SDK_INT >= 29) {
            this.mActivity.registerActivityLifecycleCallbacks(this);
        } else {
            this.mActivity.getApplication().registerActivityLifecycleCallbacks(this);
        }
        this.mContentView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        int identifier = this.mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            this.mStatusBarHeight = this.mActivity.getResources().getDimensionPixelSize(identifier);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        Rect rect = new Rect();
        this.mContentView.getWindowVisibleDisplayFrame(rect);
        int height = this.mContentView.getRootView().getHeight() - (rect.bottom - rect.top);
        if (!this.mSoftKeyboardOpened && height > this.mContentView.getRootView().getHeight() / 4) {
            this.mSoftKeyboardOpened = true;
            if (this.mListeners == null) {
                return;
            }
            if ((this.mActivity.getWindow().getAttributes().flags & 1024) != 1024) {
                this.mListeners.onSoftKeyboardOpened(height - this.mStatusBarHeight);
            } else {
                this.mListeners.onSoftKeyboardOpened(height);
            }
        } else if (!this.mSoftKeyboardOpened || height >= this.mContentView.getRootView().getHeight() / 4) {
        } else {
            this.mSoftKeyboardOpened = false;
            SoftKeyboardStateListener softKeyboardStateListener = this.mListeners;
            if (softKeyboardStateListener == null) {
                return;
            }
            softKeyboardStateListener.onSoftKeyboardClosed();
        }
    }

    public void setListener(@Nullable SoftKeyboardStateListener softKeyboardStateListener) {
        this.mListeners = softKeyboardStateListener;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull Activity activity) {
        if (this.mActivity == activity) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.mActivity.unregisterActivityLifecycleCallbacks(this);
            } else {
                this.mActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
            }
            this.mContentView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.mActivity = null;
            this.mContentView = null;
            this.mListeners = null;
        }
    }
}
