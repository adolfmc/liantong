package org.p415a.p424c.p426b;

import java.util.Date;
import org.p415a.p424c.C12582ad;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.b.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12602i extends C12582ad {
    public C12602i(boolean z, boolean z2, byte[] bArr) {
        super(2, z, z2, bArr);
    }

    /* renamed from: a */
    public Date m1570a() {
        return new Date((((this.f25565d[0] & 255) << 24) | ((this.f25565d[1] & 255) << 16) | ((this.f25565d[2] & 255) << 8) | (this.f25565d[3] & 255)) * 1000);
    }
}
