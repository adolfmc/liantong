package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0151g1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0166k;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.x2.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0456b extends AbstractC0174m {

    /* renamed from: v3 */
    public C0166k f1491v3;

    public C0456b(C0166k c0166k) {
        if (c0166k != null) {
            this.f1491v3 = c0166k;
            return;
        }
        throw new IllegalArgumentException("'y' cannot be null");
    }

    /* renamed from: a */
    public static C0456b m23055a(AbstractC0494y abstractC0494y, boolean z) {
        return m23054a(C0151g1.m24148a(abstractC0494y, z));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1491v3;
    }

    /* renamed from: i */
    public C0166k m23053i() {
        return this.f1491v3;
    }

    /* renamed from: a */
    public static C0456b m23054a(Object obj) {
        if (obj != null && !(obj instanceof C0456b)) {
            if (obj instanceof C0166k) {
                return new C0456b((C0166k) obj);
            }
            throw new IllegalArgumentException("Invalid DHPublicKey: " + obj.getClass().getName());
        }
        return (C0456b) obj;
    }
}
