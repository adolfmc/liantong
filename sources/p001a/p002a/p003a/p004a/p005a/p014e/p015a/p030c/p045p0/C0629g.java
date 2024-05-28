package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p045p0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.c.p0.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0629g implements InterfaceC0643x {

    /* renamed from: a */
    public final InterfaceC0500a f1953a;

    /* renamed from: b */
    public final InterfaceC0605o f1954b;

    /* renamed from: c */
    public boolean f1955c;

    public C0629g(InterfaceC0500a interfaceC0500a, InterfaceC0605o interfaceC0605o) {
        this.f1953a = interfaceC0500a;
        this.f1954b = interfaceC0605o;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22637a(boolean z, InterfaceC0542i interfaceC0542i) {
        C0557a c0557a;
        this.f1955c = z;
        if (interfaceC0542i instanceof C0578k0) {
            c0557a = (C0557a) ((C0578k0) interfaceC0542i).m22801a();
        } else {
            c0557a = (C0557a) interfaceC0542i;
        }
        if (z && !c0557a.m22845a()) {
            throw new IllegalArgumentException("signing requires private key");
        }
        if (!z && c0557a.m22845a()) {
            throw new IllegalArgumentException("verification requires public key");
        }
        mo22635b();
        this.f1953a.mo22898a(z, interfaceC0542i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: b */
    public boolean mo22634b(byte[] bArr) {
        if (!this.f1955c) {
            byte[] bArr2 = new byte[this.f1954b.mo22743c()];
            this.f1954b.mo22746a(bArr2, 0);
            try {
                return C0669a.m22466c(this.f1953a.mo22897a(bArr, 0, bArr.length), bArr2);
            } catch (Exception unused) {
                return false;
            }
        }
        throw new IllegalStateException("GenericSigner not initialised for verification");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: b */
    public void mo22635b() {
        this.f1954b.mo22744b();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22638a(byte b) {
        this.f1954b.mo22747a(b);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public void mo22636a(byte[] bArr, int i, int i2) {
        this.f1954b.mo22745a(bArr, i, i2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0643x
    /* renamed from: a */
    public byte[] mo22639a() {
        if (this.f1955c) {
            int mo22743c = this.f1954b.mo22743c();
            byte[] bArr = new byte[mo22743c];
            this.f1954b.mo22746a(bArr, 0);
            return this.f1953a.mo22897a(bArr, 0, mo22743c);
        }
        throw new IllegalStateException("GenericSigner not initialised for signature generation.");
    }
}
