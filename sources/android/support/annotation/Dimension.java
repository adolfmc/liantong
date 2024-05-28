package android.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: E:\10201592_dexfile_execute.dex */
public @interface Dimension {

    /* renamed from: DP */
    public static final int f2447DP = 0;

    /* renamed from: PX */
    public static final int f2448PX = 1;

    /* renamed from: SP */
    public static final int f2449SP = 2;

    int unit() default 1;
}
