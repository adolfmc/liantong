package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.DeclareSoft;
import org.aspectj.lang.reflect.PointcutExpression;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DeclareSoftImpl implements DeclareSoft {
    private AjType<?> declaringType;
    private AjType<?> exceptionType;
    private String missingTypeName;
    private PointcutExpression pointcut;

    public DeclareSoftImpl(AjType<?> ajType, String str, String str2) {
        this.declaringType = ajType;
        this.pointcut = new PointcutExpressionImpl(str);
        try {
            this.exceptionType = AjTypeSystem.getAjType(Class.forName(str2, false, ajType.getJavaClass().getClassLoader()));
        } catch (ClassNotFoundException unused) {
            this.missingTypeName = str2;
        }
    }

    @Override // org.aspectj.lang.reflect.DeclareSoft
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // org.aspectj.lang.reflect.DeclareSoft
    public PointcutExpression getPointcutExpression() {
        return this.pointcut;
    }

    @Override // org.aspectj.lang.reflect.DeclareSoft
    public AjType getSoftenedExceptionType() {
        String str = this.missingTypeName;
        if (str == null) {
            return this.exceptionType;
        }
        throw new ClassNotFoundException(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("declare soft : ");
        String str = this.missingTypeName;
        if (str != null) {
            str = this.exceptionType.getName();
        }
        stringBuffer.append(str);
        stringBuffer.append(" : ");
        stringBuffer.append(getPointcutExpression().asString());
        return stringBuffer.toString();
    }
}
