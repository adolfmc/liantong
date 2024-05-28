package com.uber.autodispose;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.TYPE})
@Inherited
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: E:\11617560_dexfile_execute.dex */
@interface DoNotMock {
    String value() default "Create a real instance instead";
}
