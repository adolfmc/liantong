package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.io.IOException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0138d1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0146f1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0180n1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0496y1;

/* renamed from: a.a.a.a.a.e.a.b.w2.o1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0418o1 extends AbstractC0433t1 {
    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2.AbstractC0433t1
    /* renamed from: a */
    public AbstractC0258r mo23148a(C0178n c0178n, String str) {
        if (str.length() != 0 && str.charAt(0) == '#') {
            try {
                return m23146a(str, 1);
            } catch (IOException unused) {
                throw new RuntimeException("can't recode value for oid " + c0178n.m24113n());
            }
        }
        if (str.length() != 0 && str.charAt(0) == '\\') {
            str = str.substring(1);
        }
        if (!c0178n.equals(C0430s1.f1346f4) && !c0178n.equals(C0430s1.f1350j4)) {
            if (c0178n.equals(C0430s1.f1336V3)) {
                return new C0138d1(str);
            }
            if (!c0178n.equals(C0430s1.f1317C3) && !c0178n.equals(C0430s1.f1322H3) && !c0178n.equals(C0430s1.f1334T3) && !c0178n.equals(C0430s1.f1344d4)) {
                return new C0496y1(str);
            }
            return new C0180n1(str);
        }
        return new C0146f1(str);
    }
}
