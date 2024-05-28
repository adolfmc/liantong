package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.AjType;
import szorg.mp4parser.aspectj.lang.reflect.DeclareErrorOrWarning;
import szorg.mp4parser.aspectj.lang.reflect.PointcutExpression;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DeclareErrorOrWarningImpl implements DeclareErrorOrWarning {
    private AjType declaringType;
    private boolean isError;
    private String msg;

    /* renamed from: pc */
    private PointcutExpression f27684pc;

    public DeclareErrorOrWarningImpl(String str, String str2, boolean z, AjType ajType) {
        this.f27684pc = new PointcutExpressionImpl(str);
        this.msg = str2;
        this.isError = z;
        this.declaringType = ajType;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclareErrorOrWarning
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclareErrorOrWarning
    public String getMessage() {
        return this.msg;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclareErrorOrWarning
    public PointcutExpression getPointcutExpression() {
        return this.f27684pc;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclareErrorOrWarning
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
