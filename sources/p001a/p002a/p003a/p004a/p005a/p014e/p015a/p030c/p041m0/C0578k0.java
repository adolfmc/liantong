package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.k0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0578k0 implements InterfaceC0542i {

    /* renamed from: a */
    public SecureRandom f1842a;

    /* renamed from: b */
    public InterfaceC0542i f1843b;

    public C0578k0(InterfaceC0542i interfaceC0542i, SecureRandom secureRandom) {
        this.f1842a = secureRandom;
        this.f1843b = interfaceC0542i;
    }

    /* renamed from: a */
    public InterfaceC0542i m22801a() {
        return this.f1843b;
    }

    /* renamed from: b */
    public SecureRandom m22800b() {
        return this.f1842a;
    }

    public C0578k0(InterfaceC0542i interfaceC0542i) {
        this(interfaceC0542i, new SecureRandom());
    }
}
