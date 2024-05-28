package io.reactivex.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: E:\11617560_dexfile_execute.dex */
public @interface SchedulerSupport {
    public static final String COMPUTATION = "io.reactivex:computation";
    public static final String CUSTOM = "custom";

    /* renamed from: IO */
    public static final String f24396IO = "io.reactivex:io";
    public static final String NEW_THREAD = "io.reactivex:new-thread";
    public static final String NONE = "none";
    @Experimental
    public static final String SINGLE = "io.reactivex:single";
    public static final String TRAMPOLINE = "io.reactivex:trampoline";

    String value();
}
