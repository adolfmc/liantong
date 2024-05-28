package com.vivo.push.util;

import android.content.Context;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ContextDelegate {
    private static final String TAG = "ContextDelegate";
    private static Context mContext;
    private static Method mCreateCredentialProtectedStorageContext;
    private static Method mCreateDeviceProtectedStorageContext;
    private static boolean mDelegateEnable;
    private static Boolean mIsFbeProject;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.vivo.push.util.ContextDelegate$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C10988a {

        /* renamed from: a */
        private static ContextDelegate f21173a = new ContextDelegate();
    }

    public static ContextDelegate getInstance() {
        return C10988a.f21173a;
    }

    public static void setEnable(boolean z) {
        mDelegateEnable = z;
        setAppContext();
    }

    public static Context getContext(Context context) {
        if (!isFBEProject() || context == null) {
            return context;
        }
        Context context2 = mContext;
        if (context2 != null) {
            return context2;
        }
        setContext(context);
        return mContext;
    }

    private static void setContext(Context context) {
        if (!mDelegateEnable) {
            mContext = createCredentialProtectedStorageContext(context);
        } else {
            mContext = createDeviceProtectedStorageContext(context);
        }
    }

    private static void setAppContext() {
        Context context = mContext;
        if (context == null) {
            return;
        }
        setContext(context);
    }

    private static Context createDeviceProtectedStorageContext(Context context) {
        try {
            if (mCreateDeviceProtectedStorageContext == null) {
                mCreateDeviceProtectedStorageContext = Context.class.getMethod("createDeviceProtectedStorageContext", new Class[0]);
            }
            return (Context) mCreateDeviceProtectedStorageContext.invoke(context, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return context;
        }
    }

    private static Context createCredentialProtectedStorageContext(Context context) {
        try {
            if (mCreateCredentialProtectedStorageContext == null) {
                mCreateCredentialProtectedStorageContext = Context.class.getMethod("createCredentialProtectedStorageContext", new Class[0]);
            }
            return (Context) mCreateCredentialProtectedStorageContext.invoke(context, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return context;
        }
    }

    public static boolean isFBEProject() {
        if (mIsFbeProject == null) {
            try {
                mIsFbeProject = Boolean.valueOf("file".equals(Device.m5384a("ro.crypto.type", "unknow")));
                LogUtil.m5346b("ContextDelegate", "mIsFbeProject = " + mIsFbeProject.toString());
            } catch (Exception e) {
                LogUtil.m5354a("ContextDelegate", "mIsFbeProject = " + e.getMessage());
            }
        }
        Boolean bool = mIsFbeProject;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }
}
