package org.aspectj.internal.lang.reflect;

import java.lang.annotation.Annotation;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclareAnnotation;
import org.aspectj.lang.reflect.SignaturePattern;
import org.aspectj.lang.reflect.TypePattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DeclareAnnotationImpl implements DeclareAnnotation {
    private String annText;
    private AjType<?> declaringType;
    private DeclareAnnotation.Kind kind;
    private SignaturePattern signaturePattern;
    private Annotation theAnnotation;
    private TypePattern typePattern;

    public DeclareAnnotationImpl(AjType<?> ajType, String str, String str2, Annotation annotation, String str3) {
        DeclareAnnotation.Kind kind;
        this.declaringType = ajType;
        if (str.equals("at_type")) {
            kind = DeclareAnnotation.Kind.Type;
        } else if (str.equals("at_field")) {
            kind = DeclareAnnotation.Kind.Field;
        } else if (str.equals("at_method")) {
            kind = DeclareAnnotation.Kind.Method;
        } else if (!str.equals("at_constructor")) {
            throw new IllegalStateException("Unknown declare annotation kind: " + str);
        } else {
            kind = DeclareAnnotation.Kind.Constructor;
        }
        this.kind = kind;
        if (this.kind == DeclareAnnotation.Kind.Type) {
            this.typePattern = new TypePatternImpl(str2);
        } else {
            this.signaturePattern = new SignaturePatternImpl(str2);
        }
        this.theAnnotation = annotation;
        this.annText = str3;
    }

    @Override // org.aspectj.lang.reflect.DeclareAnnotation
    public Annotation getAnnotation() {
        return this.theAnnotation;
    }

    @Override // org.aspectj.lang.reflect.DeclareAnnotation
    public String getAnnotationAsText() {
        return this.annText;
    }

    @Override // org.aspectj.lang.reflect.DeclareAnnotation
    public AjType<?> getDeclaringType() {
        return this.declaringType;
    }

    @Override // org.aspectj.lang.reflect.DeclareAnnotation
    public DeclareAnnotation.Kind getKind() {
        return this.kind;
    }

    @Override // org.aspectj.lang.reflect.DeclareAnnotation
    public SignaturePattern getSignaturePattern() {
        return this.signaturePattern;
    }

    @Override // org.aspectj.lang.reflect.DeclareAnnotation
    public TypePattern getTypePattern() {
        return this.typePattern;
    }

    public String toString() {
        String asString;
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("declare @");
        switch (getKind()) {
            case Type:
                stringBuffer.append("type : ");
                asString = getTypePattern().asString();
                stringBuffer.append(asString);
                stringBuffer.append(" : ");
                stringBuffer.append(getAnnotationAsText());
                return stringBuffer.toString();
            case Method:
                str = "method : ";
                stringBuffer.append(str);
                asString = getSignaturePattern().asString();
                stringBuffer.append(asString);
                stringBuffer.append(" : ");
                stringBuffer.append(getAnnotationAsText());
                return stringBuffer.toString();
            case Field:
                str = "field : ";
                stringBuffer.append(str);
                asString = getSignaturePattern().asString();
                stringBuffer.append(asString);
                stringBuffer.append(" : ");
                stringBuffer.append(getAnnotationAsText());
                return stringBuffer.toString();
            case Constructor:
                str = "constructor : ";
                stringBuffer.append(str);
                asString = getSignaturePattern().asString();
                stringBuffer.append(asString);
                stringBuffer.append(" : ");
                stringBuffer.append(getAnnotationAsText());
                return stringBuffer.toString();
            default:
                stringBuffer.append(" : ");
                stringBuffer.append(getAnnotationAsText());
                return stringBuffer.toString();
        }
    }
}
