package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@TypeQualifier(applicableTo = Number.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: E:\11617560_dexfile_execute.dex */
public @interface Nonnegative {
    When when() default When.ALWAYS;

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class Checker implements TypeQualifierValidator<Nonnegative> {
        @Override // javax.annotation.meta.TypeQualifierValidator
        public When forConstantValue(Nonnegative nonnegative, Object obj) {
            if (!(obj instanceof Number)) {
                return When.NEVER;
            }
            Number number = (Number) obj;
            boolean z = true;
            if (number instanceof Long) {
                if (number.longValue() >= 0) {
                    z = false;
                }
            } else if (number instanceof Double) {
                if (number.doubleValue() >= 0.0d) {
                    z = false;
                }
            } else if (number instanceof Float) {
                if (number.floatValue() >= 0.0f) {
                    z = false;
                }
            } else if (number.intValue() >= 0) {
                z = false;
            }
            if (z) {
                return When.NEVER;
            }
            return When.ALWAYS;
        }
    }
}
