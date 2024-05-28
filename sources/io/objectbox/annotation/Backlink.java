package io.objectbox.annotation;

import io.objectbox.annotation.apihint.Beta;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Beta
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: E:\11617560_dexfile_execute.dex */
public @interface Backlink {
    /* renamed from: to */
    String m1960to() default "";
}
