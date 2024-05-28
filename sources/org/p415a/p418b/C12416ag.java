package org.p415a.p418b;

import java.io.IOException;
import java.io.InputStream;
import org.p415a.p448g.p450b.C12967a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ag */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12416ag implements InterfaceC12566q {

    /* renamed from: a */
    private C12575y f25120a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12416ag(C12575y c12575y) {
        this.f25120a = c12575y;
    }

    @Override // org.p415a.p418b.InterfaceC12566q
    /* renamed from: b */
    public InputStream mo1631b() {
        return new C12423an(this.f25120a);
    }

    @Override // org.p415a.p418b.InterfaceC12460bt
    /* renamed from: e */
    public AbstractC12570t mo1593e() {
        return new C12414af(C12967a.m405a(mo1631b()));
    }

    @Override // org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public AbstractC12570t mo1617h() {
        try {
            return mo1593e();
        } catch (IOException e) {
            throw new C12569s("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }
}
