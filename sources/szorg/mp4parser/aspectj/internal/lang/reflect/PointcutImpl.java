package szorg.mp4parser.aspectj.internal.lang.reflect;

import java.lang.reflect.Method;
import java.util.StringTokenizer;
import szorg.mp4parser.aspectj.lang.reflect.AjType;
import szorg.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import szorg.mp4parser.aspectj.lang.reflect.Pointcut;
import szorg.mp4parser.aspectj.lang.reflect.PointcutExpression;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class PointcutImpl implements Pointcut {
    private final Method baseMethod;
    private final AjType declaringType;
    private final String name;
    private String[] parameterNames;

    /* renamed from: pc */
    private final PointcutExpression f27685pc;

    /* JADX INFO: Access modifiers changed from: protected */
    public PointcutImpl(String str, String str2, Method method, AjType ajType, String str3) {
        this.parameterNames = new String[0];
        this.name = str;
        this.f27685pc = new PointcutExpressionImpl(str2);
        this.baseMethod = method;
        this.declaringType = ajType;
        this.parameterNames = splitOnComma(str3);
    }

    private String[] splitOnComma(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        String[] strArr = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = stringTokenizer.nextToken().trim();
        }
        return strArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.Pointcut
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.Pointcut
    public int getModifiers() {
        return this.baseMethod.getModifiers();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.Pointcut
    public String getName() {
        return this.name;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.Pointcut
    public String[] getParameterNames() {
        return this.parameterNames;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.Pointcut
    public AjType<?>[] getParameterTypes() {
        Class<?>[] parameterTypes = this.baseMethod.getParameterTypes();
        AjType<?>[] ajTypeArr = new AjType[parameterTypes.length];
        for (int i = 0; i < ajTypeArr.length; i++) {
            ajTypeArr[i] = AjTypeSystem.getAjType(parameterTypes[i]);
        }
        return ajTypeArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.Pointcut
    public PointcutExpression getPointcutExpression() {
        return this.f27685pc;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getName());
        stringBuffer.append("(");
        AjType<?>[] parameterTypes = getParameterTypes();
        int i = 0;
        while (i < parameterTypes.length) {
            stringBuffer.append(parameterTypes[i].getName());
            String[] strArr = this.parameterNames;
            if (strArr != null && strArr[i] != null) {
                stringBuffer.append(" ");
                stringBuffer.append(this.parameterNames[i]);
            }
            i++;
            if (i < parameterTypes.length) {
                stringBuffer.append(",");
            }
        }
        stringBuffer.append(") : ");
        stringBuffer.append(getPointcutExpression().asString());
        return stringBuffer.toString();
    }
}
