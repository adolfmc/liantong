package com.mob.commons.p231cc;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.cc.n */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5808n implements InterfaceC5812q<ServiceConnectionC5807m> {
    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(ServiceConnectionC5807m serviceConnectionC5807m, Class<ServiceConnectionC5807m> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if ("setHandler".equals(str) && objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof C5802j)) {
            serviceConnectionC5807m.m12434a((C5802j) objArr[0]);
            return true;
        }
        return false;
    }
}
