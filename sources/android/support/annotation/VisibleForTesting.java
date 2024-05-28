package android.support.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Retention(RetentionPolicy.CLASS)
/* loaded from: E:\10201592_dexfile_execute.dex */
public @interface VisibleForTesting {
    public static final int NONE = 5;
    public static final int PACKAGE_PRIVATE = 3;
    public static final int PRIVATE = 2;
    public static final int PROTECTED = 4;

    int otherwise() default 2;
}
