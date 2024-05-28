package org.p415a.p418b;

import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.ao */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12424ao extends AbstractC12405a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public C12424ao(boolean z, int i, byte[] bArr) {
        super(z, i, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12405a, org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.m1627a(this.f25104a ? 96 : 64, this.f25105b, this.f25106c);
    }

    public String toString() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        if (mo1611a()) {
            stringBuffer.append("CONSTRUCTED ");
        }
        stringBuffer.append("APPLICATION ");
        stringBuffer.append(Integer.toString(m1740b()));
        stringBuffer.append("]");
        if (this.f25106c != null) {
            stringBuffer.append(" #");
            str = C12964f.m418a(this.f25106c);
        } else {
            str = " #null";
        }
        stringBuffer.append(str);
        stringBuffer.append(" ");
        return stringBuffer.toString();
    }
}
