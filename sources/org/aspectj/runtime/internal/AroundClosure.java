package org.aspectj.runtime.internal;

import org.aspectj.lang.ProceedingJoinPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class AroundClosure {
    protected int bitflags = 1048576;
    protected Object[] preInitializationState;
    protected Object[] state;

    public AroundClosure() {
    }

    public AroundClosure(Object[] objArr) {
        this.state = objArr;
    }

    public int getFlags() {
        return this.bitflags;
    }

    public Object[] getPreInitializationState() {
        return this.preInitializationState;
    }

    public Object[] getState() {
        return this.state;
    }

    public ProceedingJoinPoint linkClosureAndJoinPoint() {
        Object[] objArr = this.state;
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) objArr[objArr.length - 1];
        proceedingJoinPoint.set$AroundClosure(this);
        return proceedingJoinPoint;
    }

    public ProceedingJoinPoint linkClosureAndJoinPoint(int i) {
        Object[] objArr = this.state;
        ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) objArr[objArr.length - 1];
        proceedingJoinPoint.set$AroundClosure(this);
        this.bitflags = i;
        return proceedingJoinPoint;
    }

    public abstract Object run(Object[] objArr);
}
