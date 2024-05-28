package org.p415a.p418b;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.am */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12422am implements InterfaceC12409aa {

    /* renamed from: a */
    private boolean f25123a;

    /* renamed from: b */
    private int f25124b;

    /* renamed from: c */
    private C12575y f25125c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12422am(boolean z, int i, C12575y c12575y) {
        this.f25123a = z;
        this.f25124b = i;
        this.f25125c = c12575y;
    }

    @Override // org.p415a.p418b.InterfaceC12460bt
    /* renamed from: e */
    public AbstractC12570t mo1593e() {
        return this.f25125c.m1599a(this.f25123a, this.f25124b);
    }

    @Override // org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public AbstractC12570t mo1617h() {
        try {
            return mo1593e();
        } catch (IOException e) {
            throw new C12569s(e.getMessage());
        }
    }
}
