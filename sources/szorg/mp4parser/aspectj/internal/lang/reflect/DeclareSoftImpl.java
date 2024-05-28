package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.AjType;
import szorg.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import szorg.mp4parser.aspectj.lang.reflect.DeclareSoft;
import szorg.mp4parser.aspectj.lang.reflect.PointcutExpression;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
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

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclareSoft
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclareSoft
    public PointcutExpression getPointcutExpression() {
        return this.pointcut;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclareSoft
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
