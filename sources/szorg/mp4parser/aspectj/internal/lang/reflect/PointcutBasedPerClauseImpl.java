package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.PerClauseKind;
import szorg.mp4parser.aspectj.lang.reflect.PointcutBasedPerClause;
import szorg.mp4parser.aspectj.lang.reflect.PointcutExpression;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class PointcutBasedPerClauseImpl extends PerClauseImpl implements PointcutBasedPerClause {
    private final PointcutExpression pointcutExpression;

    public PointcutBasedPerClauseImpl(PerClauseKind perClauseKind, String str) {
        super(perClauseKind);
        this.pointcutExpression = new PointcutExpressionImpl(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.PointcutBasedPerClause
    public PointcutExpression getPointcutExpression() {
        return this.pointcutExpression;
    }

    @Override // szorg.mp4parser.aspectj.internal.lang.reflect.PerClauseImpl
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
