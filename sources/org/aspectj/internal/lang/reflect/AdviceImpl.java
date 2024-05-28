package org.aspectj.internal.lang.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.reflect.Advice;
import org.aspectj.lang.reflect.AdviceKind;
import org.aspectj.lang.reflect.AjType;
import org.aspectj.lang.reflect.AjTypeSystem;
import org.aspectj.lang.reflect.PointcutExpression;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AdviceImpl implements Advice {
    private static final String AJC_INTERNAL = "org.aspectj.runtime.internal";
    private final Method adviceMethod;
    private AjType[] exceptionTypes;
    private Type[] genericParameterTypes;
    private boolean hasExtraParam;
    private final AdviceKind kind;
    private AjType[] parameterTypes;
    private PointcutExpression pointcutExpression;

    /* JADX INFO: Access modifiers changed from: protected */
    public AdviceImpl(Method method, String str, AdviceKind adviceKind) {
        this.hasExtraParam = false;
        this.kind = adviceKind;
        this.adviceMethod = method;
        this.pointcutExpression = new PointcutExpressionImpl(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AdviceImpl(Method method, String str, AdviceKind adviceKind, String str2) {
        this(method, str, adviceKind);
        this.hasExtraParam = true;
    }

    @Override // org.aspectj.lang.reflect.Advice
    public AjType getDeclaringType() {
        return AjTypeSystem.getAjType(this.adviceMethod.getDeclaringClass());
    }

    @Override // org.aspectj.lang.reflect.Advice
    public AjType<?>[] getExceptionTypes() {
        if (this.exceptionTypes == null) {
            Class<?>[] exceptionTypes = this.adviceMethod.getExceptionTypes();
            this.exceptionTypes = new AjType[exceptionTypes.length];
            for (int i = 0; i < exceptionTypes.length; i++) {
                this.exceptionTypes[i] = AjTypeSystem.getAjType(exceptionTypes[i]);
            }
        }
        return this.exceptionTypes;
    }

    @Override // org.aspectj.lang.reflect.Advice
    public Type[] getGenericParameterTypes() {
        if (this.genericParameterTypes == null) {
            Type[] genericParameterTypes = this.adviceMethod.getGenericParameterTypes();
            int i = 0;
            int i2 = 0;
            for (Type type : genericParameterTypes) {
                if ((type instanceof Class) && ((Class) type).getPackage().getName().equals("org.aspectj.runtime.internal")) {
                    i2++;
                }
            }
            this.genericParameterTypes = new Type[genericParameterTypes.length - i2];
            while (true) {
                Type[] typeArr = this.genericParameterTypes;
                if (i >= typeArr.length) {
                    break;
                }
                if (genericParameterTypes[i] instanceof Class) {
                    typeArr[i] = AjTypeSystem.getAjType((Class) genericParameterTypes[i]);
                } else {
                    typeArr[i] = genericParameterTypes[i];
                }
                i++;
            }
        }
        return this.genericParameterTypes;
    }

    @Override // org.aspectj.lang.reflect.Advice
    public AdviceKind getKind() {
        return this.kind;
    }

    @Override // org.aspectj.lang.reflect.Advice
    public String getName() {
        String name = this.adviceMethod.getName();
        if (name.startsWith("ajc$")) {
            AdviceName adviceName = (AdviceName) this.adviceMethod.getAnnotation(AdviceName.class);
            return adviceName != null ? adviceName.value() : "";
        }
        return name;
    }

    @Override // org.aspectj.lang.reflect.Advice
    public AjType<?>[] getParameterTypes() {
        if (this.parameterTypes == null) {
            Class<?>[] parameterTypes = this.adviceMethod.getParameterTypes();
            int i = 0;
            int i2 = 0;
            for (Class<?> cls : parameterTypes) {
                if (cls.getPackage().getName().equals("org.aspectj.runtime.internal")) {
                    i2++;
                }
            }
            this.parameterTypes = new AjType[parameterTypes.length - i2];
            while (true) {
                AjType[] ajTypeArr = this.parameterTypes;
                if (i >= ajTypeArr.length) {
                    break;
                }
                ajTypeArr[i] = AjTypeSystem.getAjType(parameterTypes[i]);
                i++;
            }
        }
        return this.parameterTypes;
    }

    @Override // org.aspectj.lang.reflect.Advice
    public PointcutExpression getPointcutExpression() {
        return this.pointcutExpression;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00aa A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.aspectj.internal.lang.reflect.AdviceImpl.toString():java.lang.String");
    }
}
