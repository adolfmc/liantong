package org.aspectj.runtime.internal;

import org.aspectj.runtime.CFlow;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CFlowPlusState extends CFlow {
    private Object[] state;

    public CFlowPlusState(Object[] objArr) {
        this.state = objArr;
    }

    public CFlowPlusState(Object[] objArr, Object obj) {
        super(obj);
        this.state = objArr;
    }

    @Override // org.aspectj.runtime.CFlow
    public Object get(int i) {
        return this.state[i];
    }
}
