package org.aspectj.lang;

import org.aspectj.runtime.internal.AroundClosure;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface ProceedingJoinPoint extends JoinPoint {
    Object proceed();

    Object proceed(Object[] objArr);

    void set$AroundClosure(AroundClosure aroundClosure);
}
