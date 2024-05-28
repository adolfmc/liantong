package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.DeclareErrorOrWarning;
import org.aspectj.lang.reflect.PointcutExpression;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DeclareErrorOrWarningImpl implements DeclareErrorOrWarning {
    private AjType declaringType;
    private boolean isError;
    private String msg;

    /* renamed from: pc */
    private PointcutExpression f26280pc;

    public DeclareErrorOrWarningImpl(String str, String str2, boolean z, AjType ajType) {
        this.f26280pc = new PointcutExpressionImpl(str);
        this.msg = str2;
        this.isError = z;
        this.declaringType = ajType;
    }

    @Override // org.aspectj.lang.reflect.DeclareErrorOrWarning
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // org.aspectj.lang.reflect.DeclareErrorOrWarning
    public String getMessage() {
        return this.msg;
    }

    @Override // org.aspectj.lang.reflect.DeclareErrorOrWarning
    public PointcutExpression getPointcutExpression() {
        return this.f26280pc;
    }

    @Override // org.aspectj.lang.reflect.DeclareErrorOrWarning
    public boolean isError() {
        return this.isError;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("declare ");
        stringBuffer.append(isError() ? "error : " : "warning : ");
        stringBuffer.append(getPointcutExpression().asString());
        stringBuffer.append(" : ");
        stringBuffer.append("\"");
        stringBuffer.append(getMessage());
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }
}
