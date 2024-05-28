package org.p415a.p418b;

import java.io.IOException;
import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bv */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12462bv implements Enumeration {

    /* renamed from: a */
    private C12559k f25237a;

    /* renamed from: b */
    private Object f25238b = m1700a();

    public C12462bv(byte[] bArr) {
        this.f25237a = new C12559k(bArr, true);
    }

    /* renamed from: a */
    private Object m1700a() {
        try {
            return this.f25237a.m1647d();
        } catch (IOException e) {
            throw new C12569s("malformed DER construction: " + e, e);
        }
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.f25238b != null;
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        Object obj = this.f25238b;
        this.f25238b = m1700a();
        return obj;
    }
}
