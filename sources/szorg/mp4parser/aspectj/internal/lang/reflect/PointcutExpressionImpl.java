package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.PointcutExpression;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PointcutExpressionImpl implements PointcutExpression {
    private String expression;

    public PointcutExpressionImpl(String str) {
        this.expression = str;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.PointcutExpression
    public String asString() {
        return this.expression;
    }

    public String toString() {
        return asString();
    }
}
