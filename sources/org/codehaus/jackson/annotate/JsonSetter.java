package org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Target({ElementType.METHOD})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: E:\9227576_dexfile_execute.dex */
public @interface JsonSetter {
    String value() default "";
}
