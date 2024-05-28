package szorg.mp4parser.aspectj.lang;

import szorg.mp4parser.aspectj.runtime.internal.AroundClosure;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ProceedingJoinPoint extends JoinPoint {
    Object proceed();

    Object proceed(Object[] objArr);

    void set$AroundClosure(AroundClosure aroundClosure);
}
