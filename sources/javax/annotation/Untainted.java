package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.When;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@TypeQualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: E:\11617560_dexfile_execute.dex */
public @interface Untainted {
    When when() default When.ALWAYS;
}
