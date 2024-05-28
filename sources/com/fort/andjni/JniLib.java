package com.fort.andjni;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class JniLib {
    /* renamed from: cB */
    public static native byte m15926cB(Object... objArr);

    /* renamed from: cC */
    public static native char m15925cC(Object... objArr);

    /* renamed from: cD */
    public static native double m15924cD(Object... objArr);

    /* renamed from: cF */
    public static native float m15923cF(Object... objArr);

    /* renamed from: cI */
    public static native int m15922cI(Object... objArr);

    /* renamed from: cJ */
    public static native long m15921cJ(Object... objArr);

    /* renamed from: cL */
    public static native Object m15920cL(Object... objArr);

    /* renamed from: cS */
    public static native short m15919cS(Object... objArr);

    /* renamed from: cV */
    public static native void m15918cV(Object... objArr);

    /* renamed from: cZ */
    public static native boolean m15917cZ(Object... objArr);

    static {
        try {
            System.loadLibrary("dexjni");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static Object InvokeObject(Object... args) throws Throwable {
        Object thisObj = args[0];
        Method method = (Method) args[1];
        Object[] newArgs = new Object[args.length - 2];
        for (int i = 0; i < args.length - 2; i++) {
            newArgs[i] = args[i + 2];
        }
        try {
            return method.invoke(thisObj, newArgs);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        } catch (Exception e2) {
            throw e2;
        }
    }
}
