package com.baidu.p120ar.bus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.baidu.ar.bus.CallBack */
/* loaded from: E:\10201592_dexfile_execute.dex */
public @interface CallBack {
    ThreadMode threadMode() default ThreadMode.POSTING;
}
