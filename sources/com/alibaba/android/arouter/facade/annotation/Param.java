package com.alibaba.android.arouter.facade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
@Deprecated
/* loaded from: E:\10201592_dexfile_execute.dex */
public @interface Param {
    String desc() default "No desc.";

    String name() default "";
}
