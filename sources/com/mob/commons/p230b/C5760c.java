package com.mob.commons.p230b;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.mob.commons.C5869r;
import com.mob.tools.utils.C6152DH;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.b.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5760c {

    /* renamed from: a */
    private static AbstractC5764e f14196a;

    /* renamed from: a */
    public static synchronized void m12530a(Context context) {
        synchronized (C5760c.class) {
            if (f14196a != null) {
                return;
            }
            EnumC5762a m12529a = m12529a(Build.MANUFACTURER, Build.BRAND);
            if (m12529a == EnumC5762a.UNSUPPORT) {
                return;
            }
            switch (m12529a) {
                case XIAOMI:
                case REDMI:
                case MEITU:
                case BLACKSHARK:
                    f14196a = new C5779l(context);
                    break;
                case VIVO:
                    f14196a = new C5777k(context);
                    break;
                case HUA_WEI:
                case HORNOR:
                    f14196a = new C5763d(context);
                    break;
                case OPPO:
                case ONEPLUS:
                case REALME:
                    f14196a = new C5773i(context);
                    break;
                case MOTO:
                case ZUK:
                case LENOVO:
                    f14196a = new C5771g(context);
                    break;
                case ASUS:
                    f14196a = new C5758a(context);
                    break;
                case SAMSUNG:
                    f14196a = new C5776j(context);
                    break;
                case MEIZU:
                case MBLU:
                case ALPS:
                    f14196a = new C5768f(context);
                    break;
                case NUBIA:
                    f14196a = new C5772h(context);
                    break;
                case ZTE:
                case FERRMEOS:
                case SSUI:
                    f14196a = new C5780m(context);
                    break;
            }
        }
    }

    /* renamed from: b */
    public static boolean m12527b(Context context) {
        m12530a(context);
        AbstractC5764e abstractC5764e = f14196a;
        if (abstractC5764e != null) {
            return abstractC5764e.mo12495e();
        }
        return false;
    }

    /* renamed from: c */
    public static String m12526c(Context context) {
        m12530a(context);
        AbstractC5764e abstractC5764e = f14196a;
        if (abstractC5764e != null) {
            return abstractC5764e.mo12514d();
        }
        return null;
    }

    /* renamed from: a */
    public static EnumC5762a m12529a(String str, String str2) {
        EnumC5762a[] values;
        if (!TextUtils.isEmpty(str)) {
            for (EnumC5762a enumC5762a : EnumC5762a.values()) {
                if (enumC5762a.f14223y.equalsIgnoreCase(str) || enumC5762a.f14223y.equalsIgnoreCase(str2)) {
                    return enumC5762a;
                }
            }
        }
        if (m12531a() || m12528b()) {
            return EnumC5762a.ZTE;
        }
        return EnumC5762a.UNSUPPORT;
    }

    /* renamed from: a */
    private static boolean m12531a() {
        String systemProperties = C6152DH.SyncMtd.getSystemProperties(C5869r.m12200a("021Hdjedfdfedgdi0g1dcfdefdjWffDdf7f_fdCgdRfe_fg"));
        return !TextUtils.isEmpty(systemProperties) && systemProperties.equalsIgnoreCase(C5869r.m12200a("008.flghgggghcggglej"));
    }

    /* renamed from: b */
    private static boolean m12528b() {
        String systemProperties = C6152DH.SyncMtd.getSystemProperties(C5869r.m12200a("015)djedfdfhfhdgdifdAj)djeddcdg'ci"));
        return (TextUtils.isEmpty(systemProperties) || systemProperties.equalsIgnoreCase(C5869r.m12200a("007KdgZeHdl;eCedff=e"))) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.commons.b.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum EnumC5762a {
        UNSUPPORT(-1, C5869r.m12200a("009Adg%e5fhdg'jj=eddj3i")),
        HUA_WEI(0, C5869r.m12200a("006Hfjegelgeggei")),
        XIAOMI(1, C5869r.m12200a("006Jgfdi6d@eddfdi")),
        VIVO(2, C5869r.m12200a("0043dddidded")),
        OPPO(3, C5869r.m12200a("004Oed8jjEed")),
        MOTO(4, C5869r.m12200a("008%dfedCi<eddjedHgd")),
        LENOVO(5, C5869r.m12200a("006gfe eddded")),
        ASUS(6, C5869r.m12200a("004d(fhdgfh")),
        SAMSUNG(7, C5869r.m12200a("0078fhBdOdffhdg1eSee")),
        MEIZU(8, C5869r.m12200a("0057dfUf(digcdg")),
        ALPS(9, C5869r.m12200a("004dgjAfh")),
        NUBIA(10, C5869r.m12200a("005eSdgfedi=d")),
        ONEPLUS(11, C5869r.m12200a("007.ed<efjg:dgfh")),
        BLACKSHARK(12, C5869r.m12200a("010!fe3gdcFdlfh'hd4djdl")),
        ZTE(13, C5869r.m12200a("003Qgc_if")),
        FERRMEOS(14, C5869r.m12200a("008*efdj?ffWdf1fBedfh")),
        SSUI(15, C5869r.m12200a("004Tfhfhdgdi")),
        HORNOR(16, "HONOR"),
        REALME(17, "REALME"),
        REDMI(18, "REDMI"),
        MEITU(19, "MEITU"),
        ZUK(20, "ZUK"),
        MBLU(21, "MBLU");
        

        /* renamed from: x */
        private int f14222x;

        /* renamed from: y */
        private String f14223y;

        EnumC5762a(int i, String str) {
            this.f14222x = i;
            this.f14223y = str;
        }
    }
}
