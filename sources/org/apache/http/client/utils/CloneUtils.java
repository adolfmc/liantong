package org.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class CloneUtils {
    public static Object clone(Object obj) throws CloneNotSupportedException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Cloneable) {
            try {
                Class[] clsArr = null;
                try {
                    Object[] objArr = null;
                    return obj.getClass().getMethod("clone", null).invoke(obj, null);
                } catch (IllegalAccessException e) {
                    throw new IllegalAccessError(e.getMessage());
                } catch (InvocationTargetException e2) {
                    Throwable cause = e2.getCause();
                    if (cause instanceof CloneNotSupportedException) {
                        throw ((CloneNotSupportedException) cause);
                    }
                    throw new Error("Unexpected exception", cause);
                }
            } catch (NoSuchMethodException e3) {
                throw new NoSuchMethodError(e3.getMessage());
            }
        }
        throw new CloneNotSupportedException();
    }

    private CloneUtils() {
    }
}
