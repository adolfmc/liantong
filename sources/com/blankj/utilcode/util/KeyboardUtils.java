package com.blankj.utilcode.util;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class KeyboardUtils {
    private static final int TAG_ON_GLOBAL_LAYOUT_LISTENER = -8;
    private static long millis;
    private static int sDecorViewDelta;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int i);
    }

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void showSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void showSoftInput(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (isSoftInputVisible(activity)) {
            return;
        }
        toggleSoftInput();
    }

    public static void showSoftInput(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        showSoftInput(view, 0);
    }

    public static void showSoftInput(@NonNull View view, int i) {
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, i, new ResultReceiver(new Handler()) { // from class: com.blankj.utilcode.util.KeyboardUtils.1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i2, Bundle bundle) {
                if (i2 == 1 || i2 == 3) {
                    KeyboardUtils.toggleSoftInput();
                }
            }
        });
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void hideSoftInput(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        hideSoftInput(activity.getWindow());
    }

    public static void hideSoftInput(@NonNull Window window) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View currentFocus = window.getCurrentFocus();
        if (currentFocus == null) {
            View decorView = window.getDecorView();
            View findViewWithTag = decorView.findViewWithTag("keyboardTagView");
            if (findViewWithTag == null) {
                EditText editText = new EditText(window.getContext());
                editText.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(editText, 0, 0);
                currentFocus = editText;
            } else {
                currentFocus = findViewWithTag;
            }
            currentFocus.requestFocus();
        }
        hideSoftInput(currentFocus);
    }

    public static void hideSoftInput(@NonNull View view) {
        if (view == null) {
            throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftInputByToggle(Activity activity) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (Math.abs(elapsedRealtime - millis) > 500 && isSoftInputVisible(activity)) {
            toggleSoftInput();
        }
        millis = elapsedRealtime;
    }

    public static void toggleSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 0);
    }

    public static boolean isSoftInputVisible(@NonNull Activity activity) {
        if (activity != null) {
            return getDecorViewInvisibleHeight(activity.getWindow()) > 0;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getDecorViewInvisibleHeight(@NonNull Window window) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View decorView = window.getDecorView();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - rect.bottom));
        int abs = Math.abs(decorView.getBottom() - rect.bottom);
        if (abs <= UtilsBridge.getNavBarHeight() + UtilsBridge.getStatusBarHeight()) {
            sDecorViewDelta = abs;
            return 0;
        }
        return abs - sDecorViewDelta;
    }

    public static void registerSoftInputChangedListener(@NonNull Activity activity, @NonNull OnSoftInputChangedListener onSoftInputChangedListener) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (onSoftInputChangedListener == null) {
            throw new NullPointerException("Argument 'listener' of type OnSoftInputChangedListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        registerSoftInputChangedListener(activity.getWindow(), onSoftInputChangedListener);
    }

    public static void registerSoftInputChangedListener(@NonNull final Window window, @NonNull final OnSoftInputChangedListener onSoftInputChangedListener) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (onSoftInputChangedListener == null) {
            throw new NullPointerException("Argument 'listener' of type OnSoftInputChangedListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if ((window.getAttributes().flags & 512) != 0) {
            window.clearFlags(512);
        }
        FrameLayout frameLayout = (FrameLayout) window.findViewById(16908290);
        final int[] iArr = {getDecorViewInvisibleHeight(window)};
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.blankj.utilcode.util.KeyboardUtils.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int decorViewInvisibleHeight = KeyboardUtils.getDecorViewInvisibleHeight(window);
                if (iArr[0] != decorViewInvisibleHeight) {
                    onSoftInputChangedListener.onSoftInputChanged(decorViewInvisibleHeight);
                    iArr[0] = decorViewInvisibleHeight;
                }
            }
        };
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        frameLayout.setTag(-8, onGlobalLayoutListener);
    }

    public static void unregisterSoftInputChangedListener(@NonNull Window window) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        View findViewById = window.findViewById(16908290);
        if (findViewById == null) {
            return;
        }
        Object tag = findViewById.getTag(-8);
        if (!(tag instanceof ViewTreeObserver.OnGlobalLayoutListener) || Build.VERSION.SDK_INT < 16) {
            return;
        }
        findViewById.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver.OnGlobalLayoutListener) tag);
    }

    public static void fixAndroidBug5497(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        fixAndroidBug5497(activity.getWindow());
    }

    public static void fixAndroidBug5497(@NonNull final Window window) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        window.setSoftInputMode(window.getAttributes().softInputMode & (-17));
        FrameLayout frameLayout = (FrameLayout) window.findViewById(16908290);
        final View childAt = frameLayout.getChildAt(0);
        final int paddingBottom = childAt.getPaddingBottom();
        final int[] iArr = {getContentViewInvisibleHeight(window)};
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.blankj.utilcode.util.KeyboardUtils.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int contentViewInvisibleHeight = KeyboardUtils.getContentViewInvisibleHeight(window);
                if (iArr[0] != contentViewInvisibleHeight) {
                    View view = childAt;
                    view.setPadding(view.getPaddingLeft(), childAt.getPaddingTop(), childAt.getPaddingRight(), paddingBottom + KeyboardUtils.getDecorViewInvisibleHeight(window));
                    iArr[0] = contentViewInvisibleHeight;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getContentViewInvisibleHeight(Window window) {
        View findViewById = window.findViewById(16908290);
        if (findViewById == null) {
            return 0;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getContentViewInvisibleHeight: " + (findViewById.getBottom() - rect.bottom));
        int abs = Math.abs(findViewById.getBottom() - rect.bottom);
        if (abs <= UtilsBridge.getStatusBarHeight() + UtilsBridge.getNavBarHeight()) {
            return 0;
        }
        return abs;
    }

    public static void fixSoftInputLeaks(@NonNull Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        fixSoftInputLeaks(activity.getWindow());
    }

    public static void fixSoftInputLeaks(@NonNull Window window) {
        if (window == null) {
            throw new NullPointerException("Argument 'window' of type Window (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) Utils.getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        for (String str : new String[]{"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"}) {
            try {
                Field declaredField = InputMethodManager.class.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                Object obj = declaredField.get(inputMethodManager);
                if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                    declaredField.set(inputMethodManager, null);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void clickBlankArea2HideSoftInput() {
        Log.i("KeyboardUtils", "Please refer to the following code.");
    }
}
