package com.google.gson.internal.reflect;

import com.google.gson.internal.JavaVersion;
import java.lang.reflect.AccessibleObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class ReflectionAccessor {
    private static final ReflectionAccessor instance;

    public abstract void makeAccessible(AccessibleObject accessibleObject);

    static {
        instance = JavaVersion.getMajorJavaVersion() < 9 ? new PreJava9ReflectionAccessor() : new UnsafeReflectionAccessor();
    }

    public static ReflectionAccessor getInstance() {
        return instance;
    }
}
