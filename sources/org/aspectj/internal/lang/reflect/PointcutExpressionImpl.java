package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.PointcutExpression;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PointcutExpressionImpl implements PointcutExpression {
    private String expression;

    public PointcutExpressionImpl(String str) {
        this.expression = str;
    }

    @Override // org.aspectj.lang.reflect.PointcutExpression
    public String asString() {
        return this.expression;
    }

    public String toString() {
        return asString();
    }
}
