package org.p415a.p424c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12609f {

    /* renamed from: a */
    private int f25590a = 11994318;

    /* renamed from: a */
    public int m1560a() {
        return this.f25590a;
    }

    /* renamed from: a */
    public void m1559a(int i) {
        this.f25590a = (i << 16) ^ this.f25590a;
        for (int i2 = 0; i2 < 8; i2++) {
            this.f25590a <<= 1;
            int i3 = this.f25590a;
            if ((16777216 & i3) != 0) {
                this.f25590a = i3 ^ 25578747;
            }
        }
    }

    /* renamed from: b */
    public void m1558b() {
        this.f25590a = 11994318;
    }
}
