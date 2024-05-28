package org.aspectj.lang.reflect;

import java.lang.annotation.Annotation;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface DeclareAnnotation {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum Kind {
        Field,
        Method,
        Constructor,
        Type
    }

    Annotation getAnnotation();

    String getAnnotationAsText();

    AjType<?> getDeclaringType();

    Kind getKind();

    SignaturePattern getSignaturePattern();

    TypePattern getTypePattern();
}
