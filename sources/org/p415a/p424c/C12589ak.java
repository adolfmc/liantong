package org.p415a.p424c;

import java.io.OutputStream;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.ak */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12589ak {

    /* renamed from: a */
    int f25574a;

    /* renamed from: b */
    protected byte[] f25575b;

    /* renamed from: c */
    private boolean f25576c;

    /* JADX INFO: Access modifiers changed from: protected */
    public C12589ak(int i, boolean z, byte[] bArr) {
        this.f25574a = i;
        this.f25576c = z;
        this.f25575b = bArr;
    }

    /* renamed from: a */
    public void m1580a(OutputStream outputStream) {
        byte b;
        int length = this.f25575b.length + 1;
        if (length >= 192 || this.f25576c) {
            if (length > 8383 || this.f25576c) {
                outputStream.write(255);
                outputStream.write((byte) (length >> 24));
                outputStream.write((byte) (length >> 16));
                b = (byte) (length >> 8);
            } else {
                length -= 192;
                b = (byte) (((length >> 8) & 255) + 192);
            }
            outputStream.write(b);
        }
        outputStream.write((byte) length);
        outputStream.write(this.f25574a);
        outputStream.write(this.f25575b);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C12589ak) {
            C12589ak c12589ak = (C12589ak) obj;
            return this.f25574a == c12589ak.f25574a && C12957a.m438a(this.f25575b, c12589ak.f25575b);
        }
        return false;
    }

    public int hashCode() {
        return this.f25574a ^ C12957a.m441a(this.f25575b);
    }
}
