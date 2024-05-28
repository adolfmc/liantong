package org.p415a.p418b;

import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.an */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12423an extends InputStream {

    /* renamed from: a */
    private final C12575y f25126a;

    /* renamed from: b */
    private boolean f25127b = true;

    /* renamed from: c */
    private InputStream f25128c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12423an(C12575y c12575y) {
        this.f25126a = c12575y;
    }

    @Override // java.io.InputStream
    public int read() {
        InterfaceC12566q interfaceC12566q;
        if (this.f25128c == null) {
            if (!this.f25127b || (interfaceC12566q = (InterfaceC12566q) this.f25126a.m1602a()) == null) {
                return -1;
            }
            this.f25127b = false;
            this.f25128c = interfaceC12566q.mo1631b();
        }
        while (true) {
            int read = this.f25128c.read();
            if (read >= 0) {
                return read;
            }
            InterfaceC12566q interfaceC12566q2 = (InterfaceC12566q) this.f25126a.m1602a();
            if (interfaceC12566q2 == null) {
                this.f25128c = null;
                return -1;
            }
            this.f25128c = interfaceC12566q2.mo1631b();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        InterfaceC12566q interfaceC12566q;
        int i3 = 0;
        if (this.f25128c == null) {
            if (!this.f25127b || (interfaceC12566q = (InterfaceC12566q) this.f25126a.m1602a()) == null) {
                return -1;
            }
            this.f25127b = false;
            this.f25128c = interfaceC12566q.mo1631b();
        }
        while (true) {
            int read = this.f25128c.read(bArr, i + i3, i2 - i3);
            if (read >= 0) {
                i3 += read;
                if (i3 == i2) {
                    return i3;
                }
            } else {
                InterfaceC12566q interfaceC12566q2 = (InterfaceC12566q) this.f25126a.m1602a();
                if (interfaceC12566q2 == null) {
                    this.f25128c = null;
                    if (i3 < 1) {
                        return -1;
                    }
                    return i3;
                }
                this.f25128c = interfaceC12566q2.mo1631b();
            }
        }
    }
}
