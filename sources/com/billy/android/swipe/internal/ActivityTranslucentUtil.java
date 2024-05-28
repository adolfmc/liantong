package com.billy.android.swipe.internal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeWrapper;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressLint({"PrivateApi"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ActivityTranslucentUtil {
    private static WeakReference<Activity> convertingActivity;
    private static boolean mInitialedConvertFromTranslucent;
    private static boolean mInitialedConvertToTranslucent;
    private static Method mMethodConvertFromTranslucent;
    private static Method mMethodConvertToTranslucent;
    private static Method mMethodGetActivityOptions;
    private static Class mTranslucentConversionListenerClass;
    private MessageQueue.IdleHandler convertActivityToTranslucentIdleHandler = new MessageQueue.IdleHandler() { // from class: com.billy.android.swipe.internal.ActivityTranslucentUtil.1
        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            ActivityTranslucentUtil.this.convertActivityToTranslucent();
            return false;
        }
    };
    private long convertTranslucentTimeStamp;
    private Activity mActivity;
    private boolean mIsTranslucent;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface TranslucentCallback {
        void onTranslucentCallback(boolean z);
    }

    public ActivityTranslucentUtil(Activity activity) {
        this.mActivity = activity;
    }

    public static void convertWindowToTranslucent(Activity activity) {
        if (activity != null) {
            View findViewById = activity.findViewById(16908290);
            if (findViewById.getBackground() == null) {
                TypedArray obtainStyledAttributes = activity.getTheme().obtainStyledAttributes(new int[]{16842836});
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                obtainStyledAttributes.recycle();
                if (resourceId != 0) {
                    findViewById.setBackgroundResource(resourceId);
                }
            }
            Window window = activity.getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.getDecorView().setBackgroundDrawable(null);
            SmartSwipeWrapper peekWrapperFor = SmartSwipe.peekWrapperFor(activity);
            if (peekWrapperFor != null) {
                peekWrapperFor.setBackgroundColor(0);
            }
        }
    }

    public void convertActivityToTranslucent() {
        convertActivityToTranslucent(true);
    }

    public void convertActivityToTranslucent(final boolean z) {
        if (this.mIsTranslucent || this.mActivity == null) {
            return;
        }
        if (convertingActivity != null) {
            Looper.myQueue().addIdleHandler(this.convertActivityToTranslucentIdleHandler);
            return;
        }
        this.convertTranslucentTimeStamp = SystemClock.elapsedRealtime();
        final long j = this.convertTranslucentTimeStamp;
        convertActivityToTranslucent(this.mActivity, new TranslucentCallback() { // from class: com.billy.android.swipe.internal.ActivityTranslucentUtil.2
            @Override // com.billy.android.swipe.internal.ActivityTranslucentUtil.TranslucentCallback
            public void onTranslucentCallback(boolean z2) {
                if (j == ActivityTranslucentUtil.this.convertTranslucentTimeStamp) {
                    if (!z || z2) {
                        ActivityTranslucentUtil.this.setTranslucent(z2);
                    } else {
                        ActivityTranslucentUtil.this.convertActivityToTranslucent(false);
                    }
                }
            }
        });
    }

    public void convertActivityFromTranslucent() {
        this.convertTranslucentTimeStamp = SystemClock.elapsedRealtime();
        convertActivityFromTranslucent(this.mActivity);
        setTranslucent(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTranslucent(boolean z) {
        this.mIsTranslucent = z;
    }

    public boolean isTranslucent() {
        return this.mIsTranslucent;
    }

    public static void convertActivityToTranslucent(Activity activity, final TranslucentCallback translucentCallback) {
        Class<?>[] declaredClasses;
        convertingActivity = new WeakReference<>(activity);
        try {
            if (mTranslucentConversionListenerClass == null) {
                for (Class<?> cls : Activity.class.getDeclaredClasses()) {
                    if (cls.getSimpleName().contains("TranslucentConversionListener")) {
                        mTranslucentConversionListenerClass = cls;
                    }
                }
            }
            Object newProxyInstance = mTranslucentConversionListenerClass != null ? Proxy.newProxyInstance(mTranslucentConversionListenerClass.getClassLoader(), new Class[]{mTranslucentConversionListenerClass}, new InvocationHandler() { // from class: com.billy.android.swipe.internal.ActivityTranslucentUtil.3
                @Override // java.lang.reflect.InvocationHandler
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    boolean z = false;
                    if (objArr != null && objArr.length == 1) {
                        z = ((Boolean) objArr[0]).booleanValue();
                    }
                    ActivityTranslucentUtil.convertCallback(TranslucentCallback.this, z);
                    return null;
                }
            }) : null;
            if (mMethodConvertToTranslucent == null && mInitialedConvertToTranslucent) {
                convertCallback(translucentCallback, false);
                return;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (mMethodConvertToTranslucent == null) {
                    mInitialedConvertToTranslucent = true;
                    Method declaredMethod = Activity.class.getDeclaredMethod("getActivityOptions", new Class[0]);
                    declaredMethod.setAccessible(true);
                    mMethodGetActivityOptions = declaredMethod;
                    Method declaredMethod2 = Activity.class.getDeclaredMethod("convertToTranslucent", mTranslucentConversionListenerClass, ActivityOptions.class);
                    declaredMethod2.setAccessible(true);
                    mMethodConvertToTranslucent = declaredMethod2;
                }
                mMethodConvertToTranslucent.invoke(activity, newProxyInstance, mMethodGetActivityOptions.invoke(activity, new Object[0]));
            } else {
                if (mMethodConvertToTranslucent == null) {
                    mInitialedConvertToTranslucent = true;
                    Method declaredMethod3 = Activity.class.getDeclaredMethod("convertToTranslucent", mTranslucentConversionListenerClass);
                    declaredMethod3.setAccessible(true);
                    mMethodConvertToTranslucent = declaredMethod3;
                }
                mMethodConvertToTranslucent.invoke(activity, newProxyInstance);
            }
            if (newProxyInstance == null) {
                convertCallback(translucentCallback, false);
            }
        } catch (Throwable unused) {
            convertCallback(translucentCallback, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void convertCallback(TranslucentCallback translucentCallback, boolean z) {
        if (translucentCallback != null) {
            translucentCallback.onTranslucentCallback(z);
        }
        convertingActivity = null;
    }

    public static void convertActivityFromTranslucent(Activity activity) {
        if (activity == null) {
            return;
        }
        WeakReference<Activity> weakReference = convertingActivity;
        if (weakReference != null && weakReference.get() == activity) {
            convertingActivity = null;
        }
        try {
            if (mMethodConvertFromTranslucent == null) {
                if (mInitialedConvertFromTranslucent) {
                    return;
                }
                mInitialedConvertFromTranslucent = true;
                Method declaredMethod = Activity.class.getDeclaredMethod("convertFromTranslucent", new Class[0]);
                declaredMethod.setAccessible(true);
                mMethodConvertFromTranslucent = declaredMethod;
            }
            mMethodConvertFromTranslucent.invoke(activity, new Object[0]);
        } catch (Throwable unused) {
        }
    }
}
