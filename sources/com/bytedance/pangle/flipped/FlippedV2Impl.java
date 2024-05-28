package com.bytedance.pangle.flipped;

import android.support.annotation.Keep;
import android.util.Log;
import java.lang.reflect.Method;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FlippedV2Impl implements InterfaceC3833c {
    private static final String TAG = "FlippedV2Impl";

    private native Method getDeclaredMethod(Object obj, String str, Class<?>[] clsArr);

    static {
        System.loadLibrary("pangleflipped");
    }

    @Override // com.bytedance.pangle.flipped.InterfaceC3833c
    public void invokeHiddenApiRestrictions() {
        try {
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            Method declaredMethod = getDeclaredMethod(cls, "getRuntime", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Method declaredMethod2 = getDeclaredMethod(cls, "setHiddenApiExemptions", new Class[]{String[].class});
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(invoke, new String[]{"L"});
        } catch (Exception e) {
            Log.e(TAG, "V2 invokeHiddenApiRestrictions fail: " + Log.getStackTraceString(e));
        }
    }
}
