package com.bytedance.pangle.flipped;

import android.annotation.SuppressLint;
import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: com.bytedance.pangle.flipped.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3832b implements InterfaceC3833c {
    @Override // com.bytedance.pangle.flipped.InterfaceC3833c
    @SuppressLint({"DiscouragedPrivateApi"})
    public final void invokeHiddenApiRestrictions() {
        try {
            Method declaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            Method method = (Method) declaredMethod.invoke(cls, "getRuntime", new Class[0]);
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[0]);
            Method method2 = (Method) declaredMethod.invoke(cls, "setHiddenApiExemptions", new Class[]{String[].class});
            method2.setAccessible(true);
            method2.invoke(invoke, new String[]{"L"});
        } catch (Exception e) {
            Log.e("FlippedV1Impl", "V1 invokeHiddenApiRestrictions fail: " + Log.getStackTraceString(e));
        }
    }
}
