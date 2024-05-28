package com.mob.commons.p231cc;

import android.database.ContentObserver;
import java.util.ArrayList;

/* renamed from: com.mob.commons.cc.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5800h extends ContentObserver implements InterfaceC5812q<C5800h> {

    /* renamed from: a */
    private C5802j f14281a;

    public C5800h() {
        super(null);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        if (this.f14281a != null) {
            ArrayList<Object> arrayList = new ArrayList<>(1);
            arrayList.add(Boolean.valueOf(z));
            this.f14281a.m12441a("onChange", arrayList);
        }
    }

    /* renamed from: a */
    public void m12442a(C5802j c5802j) {
        this.f14281a = c5802j;
    }

    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(C5800h c5800h, Class<C5800h> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if ("setHandler".equals(str) && objArr.length == 1 && objArr[0] != null && (objArr[0] instanceof C5802j)) {
            c5800h.m12442a((C5802j) objArr[0]);
            return true;
        }
        return false;
    }
}
