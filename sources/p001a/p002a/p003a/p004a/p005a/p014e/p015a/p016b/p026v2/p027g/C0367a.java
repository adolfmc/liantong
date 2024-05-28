package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.p027g;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0363c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.InterfaceC0366f;

/* renamed from: a.a.a.a.a.e.a.b.v2.g.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0367a extends C0368b {

    /* renamed from: M */
    public static final InterfaceC0366f f942M = new C0367a();

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.p027g.C0368b, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.InterfaceC0366f
    /* renamed from: a */
    public boolean mo23521a(C0364d c0364d, C0364d c0364d2) {
        C0363c[] m23535j = c0364d.m23535j();
        C0363c[] m23535j2 = c0364d2.m23535j();
        if (m23535j.length != m23535j2.length) {
            return false;
        }
        for (int i = 0; i != m23535j.length; i++) {
            if (!m23523a(m23535j[i], m23535j2[i])) {
                return false;
            }
        }
        return true;
    }
}
