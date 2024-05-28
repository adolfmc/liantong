package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0;

import java.security.SecureRandom;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.o0.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0606a implements InterfaceC0611e {

    /* renamed from: a */
    public final SecureRandom f1898a;

    /* renamed from: b */
    public final boolean f1899b;

    /* renamed from: a.a.a.a.a.e.a.c.o0.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0607a implements InterfaceC0610d {

        /* renamed from: a */
        public final /* synthetic */ int f1900a;

        public C0607a(int i) {
            this.f1900a = i;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0610d
        /* renamed from: a */
        public boolean mo22734a() {
            return C0606a.this.f1899b;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0610d
        /* renamed from: b */
        public int mo22733b() {
            return this.f1900a;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0610d
        /* renamed from: c */
        public byte[] mo22732c() {
            return C0606a.this.f1898a.generateSeed((this.f1900a + 7) / 8);
        }
    }

    public C0606a(SecureRandom secureRandom, boolean z) {
        this.f1898a = secureRandom;
        this.f1899b = z;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0611e
    /* renamed from: a */
    public InterfaceC0610d mo22731a(int i) {
        return new C0607a(i);
    }
}
