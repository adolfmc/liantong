package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.PerClauseKind;
import org.aspectj.lang.reflect.PointcutBasedPerClause;
import org.aspectj.lang.reflect.PointcutExpression;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PointcutBasedPerClauseImpl extends PerClauseImpl implements PointcutBasedPerClause {
    private final PointcutExpression pointcutExpression;

    public PointcutBasedPerClauseImpl(PerClauseKind perClauseKind, String str) {
        super(perClauseKind);
        this.pointcutExpression = new PointcutExpressionImpl(str);
    }

    @Override // org.aspectj.lang.reflect.PointcutBasedPerClause
    public PointcutExpression getPointcutExpression() {
        return this.pointcutExpression;
    }

    @Override // org.aspectj.internal.lang.reflect.PerClauseImpl
    public String toString() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        switch (getKind()) {
            case PERCFLOW:
                str = "percflow(";
                break;
            case PERCFLOWBELOW:
                str = "percflowbelow(";
                break;
            case PERTARGET:
                str = "pertarget(";
                break;
            case PERTHIS:
                str = "perthis(";
                break;
            default:
                stringBuffer.append(this.pointcutExpression.asString());
                stringBuffer.append(")");
                return stringBuffer.toString();
        }
        stringBuffer.append(str);
        stringBuffer.append(this.pointcutExpression.asString());
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
