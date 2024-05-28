package szorg.mp4parser.aspectj.lang.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: E:\9227576_dexfile_execute.dex */
public @interface SuppressAjWarnings {
    String[] value() default {""};
}
