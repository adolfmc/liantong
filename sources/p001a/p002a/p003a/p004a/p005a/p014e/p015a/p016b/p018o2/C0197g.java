package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0298t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0358v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0497z;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.C0377b;

/* renamed from: a.a.a.a.a.e.a.b.o2.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0197g {

    /* renamed from: a */
    public InterfaceC0298t f273a;

    /* renamed from: b */
    public C0166k f274b;

    /* renamed from: c */
    public InterfaceC0136d f275c;

    /* renamed from: d */
    public boolean f276d;

    public C0197g(InterfaceC0298t interfaceC0298t) {
        this.f273a = interfaceC0298t;
        this.f274b = C0151g1.m24147a(interfaceC0298t.mo23612a());
    }

    /* renamed from: a */
    public InterfaceC0358v m24013a() {
        if (this.f275c == null) {
            this.f275c = this.f273a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f275c;
        if (interfaceC0136d instanceof InterfaceC0497z) {
            this.f275c = null;
            return (InterfaceC0358v) ((InterfaceC0497z) interfaceC0136d).mo22995a(17, false);
        }
        return null;
    }

    /* renamed from: b */
    public C0377b m24012b() {
        if (this.f275c == null) {
            this.f275c = this.f273a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f275c;
        if (interfaceC0136d instanceof InterfaceC0497z) {
            C0377b m23461a = C0377b.m23461a((AbstractC0494y) interfaceC0136d.mo23015d(), false);
            this.f275c = null;
            return m23461a;
        }
        return null;
    }

    /* renamed from: c */
    public C0209m m24011c() {
        if (this.f275c == null) {
            this.f275c = this.f273a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f275c;
        if (interfaceC0136d != null) {
            this.f275c = null;
            return new C0209m((InterfaceC0298t) interfaceC0136d);
        }
        return null;
    }

    /* renamed from: d */
    public AbstractC0182o m24010d() {
        if (this.f275c == null) {
            this.f275c = this.f273a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f275c;
        this.f275c = null;
        return AbstractC0182o.m24089a((Object) interfaceC0136d.mo23015d());
    }

    /* renamed from: e */
    public C0377b m24009e() {
        if (this.f275c == null) {
            this.f275c = this.f273a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f275c;
        if (interfaceC0136d != null) {
            this.f275c = null;
            return C0377b.m23460a(((InterfaceC0298t) interfaceC0136d).mo23015d());
        }
        return null;
    }

    /* renamed from: f */
    public C0190c0 m24008f() {
        this.f276d = true;
        if (this.f275c == null) {
            this.f275c = this.f273a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f275c;
        if ((interfaceC0136d instanceof InterfaceC0497z) && ((InterfaceC0497z) interfaceC0136d).mo22994f() == 0) {
            this.f275c = null;
            return C0190c0.m24055a(((InterfaceC0298t) ((InterfaceC0497z) this.f275c).mo22995a(16, false)).mo23015d());
        }
        return null;
    }

    /* renamed from: g */
    public InterfaceC0358v m24007g() {
        if (!this.f276d) {
            m24008f();
        }
        if (this.f275c == null) {
            this.f275c = this.f273a.mo23612a();
        }
        InterfaceC0358v interfaceC0358v = (InterfaceC0358v) this.f275c;
        this.f275c = null;
        return interfaceC0358v;
    }

    /* renamed from: h */
    public InterfaceC0358v m24006h() {
        if (this.f275c == null) {
            this.f275c = this.f273a.mo23612a();
        }
        InterfaceC0136d interfaceC0136d = this.f275c;
        if (interfaceC0136d != null) {
            this.f275c = null;
            return (InterfaceC0358v) ((InterfaceC0497z) interfaceC0136d).mo22995a(17, false);
        }
        return null;
    }

    /* renamed from: i */
    public C0166k m24005i() {
        return this.f274b;
    }
}
