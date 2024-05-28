package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;
import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@TypeQualifier(applicableTo = String.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: E:\11617560_dexfile_execute.dex */
public @interface MatchesPattern {
    int flags() default 0;

    @RegEx
    String value();

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class Checker implements TypeQualifierValidator<MatchesPattern> {
        @Override // javax.annotation.meta.TypeQualifierValidator
        public When forConstantValue(MatchesPattern matchesPattern, Object obj) {
            if (Pattern.compile(matchesPattern.value(), matchesPattern.flags()).matcher((String) obj).matches()) {
                return When.ALWAYS;
            }
            return When.NEVER;
        }
    }
}
