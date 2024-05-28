package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0298t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0358v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0497z;

/* renamed from: a.a.a.a.a.e.a.b.o2.s */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0221s {

    /* renamed from: a */
    public InterfaceC0298t f364a;

    /* renamed from: b */
    public C0166k f365b;

    /* renamed from: c */
    public InterfaceC0136d f366c;

    /* renamed from: d */
    public boolean f367d;

    public C0221s(InterfaceC0298t interfaceC0298t) {
        this.f364a = interfaceC0298t;
        this.f365b = C0151g1.m24147a(interfaceC0298t.mo23612a());
    }

    /* renamed from: a */
    public C0215p m23904a() {
        if (this.f366c == null) {
            this.f366c = this.f364a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f366c;
        if (interfaceC0136d != null) {
            this.f366c = null;
            return new C0215p((InterfaceC0298t) interfaceC0136d);
        }
        return null;
    }

    /* renamed from: b */
    public C0190c0 m23903b() {
        this.f367d = true;
        if (this.f366c == null) {
            this.f366c = this.f364a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f366c;
        if ((interfaceC0136d instanceof InterfaceC0497z) && ((InterfaceC0497z) interfaceC0136d).mo22994f() == 0) {
            this.f366c = null;
            return C0190c0.m24055a(((InterfaceC0298t) ((InterfaceC0497z) this.f366c).mo22995a(16, false)).mo23015d());
        }
        return null;
    }

    /* renamed from: c */
    public InterfaceC0358v m23902c() {
        if (!this.f367d) {
            m23903b();
        }
        if (this.f366c == null) {
            this.f366c = this.f364a.mo23612a();
        }
        InterfaceC0358v interfaceC0358v = (InterfaceC0358v) this.f366c;
        this.f366c = null;
        return interfaceC0358v;
    }

    /* renamed from: d */
    public InterfaceC0358v m23901d() {
        if (this.f366c == null) {
            this.f366c = this.f364a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f366c;
        if (interfaceC0136d != null) {
            this.f366c = null;
            return (InterfaceC0358v) ((InterfaceC0497z) interfaceC0136d).mo22995a(17, false);
        }
        return null;
    }

    /* renamed from: e */
    public C0166k m23900e() {
        return this.f365b;
    }
}
