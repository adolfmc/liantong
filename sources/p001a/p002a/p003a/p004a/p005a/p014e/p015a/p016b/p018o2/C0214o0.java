package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p018o2;

import java.io.IOException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0338u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0298t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0358v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0497z;

/* renamed from: a.a.a.a.a.e.a.b.o2.o0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0214o0 {

    /* renamed from: a */
    public InterfaceC0298t f339a;

    /* renamed from: b */
    public C0166k f340b;

    /* renamed from: c */
    public Object f341c;

    /* renamed from: d */
    public boolean f342d;

    /* renamed from: e */
    public boolean f343e;

    public C0214o0(InterfaceC0298t interfaceC0298t) {
        this.f339a = interfaceC0298t;
        this.f340b = (C0166k) interfaceC0298t.mo23612a();
    }

    /* renamed from: a */
    public static C0214o0 m23940a(Object obj) {
        if (obj instanceof AbstractC0263s) {
            return new C0214o0(((AbstractC0263s) obj).m23746n());
        }
        if (obj instanceof InterfaceC0298t) {
            return new C0214o0((InterfaceC0298t) obj);
        }
        throw new IOException("unknown object encountered: " + obj.getClass().getName());
    }

    /* renamed from: b */
    public InterfaceC0358v m23939b() {
        if (this.f342d) {
            this.f343e = true;
            if (this.f341c == null) {
                this.f341c = this.f339a.mo23612a();
            }
            Object obj = this.f341c;
            if ((obj instanceof InterfaceC0497z) && ((InterfaceC0497z) obj).mo22994f() == 1) {
                InterfaceC0358v interfaceC0358v = (InterfaceC0358v) ((InterfaceC0497z) this.f341c).mo22995a(17, false);
                this.f341c = null;
                return interfaceC0358v;
            }
            return null;
        }
        throw new IOException("getCerts() has not been called.");
    }

    /* renamed from: c */
    public InterfaceC0358v m23938c() {
        InterfaceC0136d mo23612a = this.f339a.mo23612a();
        if (mo23612a instanceof AbstractC0338u) {
            return ((AbstractC0338u) mo23612a).m23577n();
        }
        return (InterfaceC0358v) mo23612a;
    }

    /* renamed from: d */
    public C0209m m23937d() {
        return new C0209m((InterfaceC0298t) this.f339a.mo23612a());
    }

    /* renamed from: e */
    public InterfaceC0358v m23936e() {
        if (this.f342d && this.f343e) {
            if (this.f341c == null) {
                this.f341c = this.f339a.mo23612a();
            }
            return (InterfaceC0358v) this.f341c;
        }
        throw new IOException("getCerts() and/or getCrls() has not been called.");
    }

    /* renamed from: f */
    public C0166k m23935f() {
        return this.f340b;
    }

    /* renamed from: a */
    public InterfaceC0358v m23941a() {
        this.f342d = true;
        InterfaceC0136d mo23612a = this.f339a.mo23612a();
        this.f341c = mo23612a;
        if ((mo23612a instanceof InterfaceC0497z) && ((InterfaceC0497z) mo23612a).mo22994f() == 0) {
            InterfaceC0358v interfaceC0358v = (InterfaceC0358v) ((InterfaceC0497z) this.f341c).mo22995a(17, false);
            this.f341c = null;
            return interfaceC0358v;
        }
        return null;
    }
}
