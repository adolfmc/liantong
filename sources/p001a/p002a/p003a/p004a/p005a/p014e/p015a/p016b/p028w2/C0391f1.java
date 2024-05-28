package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0138d1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0454x1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;

/* renamed from: a.a.a.a.a.e.a.b.w2.f1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0391f1 extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: v3 */
    public AbstractC0258r f1064v3;

    public C0391f1(AbstractC0258r abstractC0258r) {
        if (!(abstractC0258r instanceof C0454x1) && !(abstractC0258r instanceof C0138d1)) {
            throw new IllegalArgumentException("unknown object passed to Time");
        }
        this.f1064v3 = abstractC0258r;
    }

    /* renamed from: a */
    public static C0391f1 m23394a(AbstractC0494y abstractC0494y, boolean z) {
        return m23393a(abstractC0494y.m23004m());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this.f1064v3;
    }

    /* renamed from: i */
    public Date m23392i() {
        try {
            if (this.f1064v3 instanceof C0454x1) {
                return ((C0454x1) this.f1064v3).m23067m();
            }
            return ((C0138d1) this.f1064v3).m24178m();
        } catch (ParseException e) {
            throw new IllegalStateException("invalid date string: " + e.getMessage());
        }
    }

    /* renamed from: j */
    public String m23391j() {
        AbstractC0258r abstractC0258r = this.f1064v3;
        if (abstractC0258r instanceof C0454x1) {
            return ((C0454x1) abstractC0258r).m23066n();
        }
        return ((C0138d1) abstractC0258r).m24177n();
    }

    public String toString() {
        return m23391j();
    }

    /* renamed from: a */
    public static C0391f1 m23393a(Object obj) {
        if (obj != null && !(obj instanceof C0391f1)) {
            if (obj instanceof C0454x1) {
                return new C0391f1((C0454x1) obj);
            }
            if (obj instanceof C0138d1) {
                return new C0391f1((C0138d1) obj);
            }
            throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
        }
        return (C0391f1) obj;
    }

    public C0391f1(Date date) {
        SimpleDateFormat simpleDateFormat;
        SimpleTimeZone simpleTimeZone = new SimpleTimeZone(0, "Z");
        new SimpleDateFormat("yyyyMMddHHmmss").setTimeZone(simpleTimeZone);
        String str = simpleDateFormat.format(date) + "Z";
        int parseInt = Integer.parseInt(str.substring(0, 4));
        if (parseInt >= 1950 && parseInt <= 2049) {
            this.f1064v3 = new C0454x1(str.substring(2));
        } else {
            this.f1064v3 = new C0138d1(str);
        }
    }
}
