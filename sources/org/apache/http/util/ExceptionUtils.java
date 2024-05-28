package org.apache.http.util;

import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class ExceptionUtils {
    private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();

    private static Method getInitCauseMethod() {
        try {
            return Throwable.class.getMethod("initCause", Throwable.class);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static void initCause(Throwable th, Throwable th2) {
        Method method = INIT_CAUSE_METHOD;
        if (method != null) {
            try {
                method.invoke(th, th2);
            } catch (Exception e) {
            }
        }
    }

    private ExceptionUtils() {
    }
}
