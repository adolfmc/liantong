package com.mob.tools.utils;

import android.text.TextUtils;
import com.mob.commons.C5855l;
import com.mob.tools.utils.C6152DH;

/* renamed from: com.mob.tools.utils.f */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6213f {

    /* renamed from: a */
    private static C6213f f15326a;

    private C6213f() {
    }

    /* renamed from: a */
    public static C6213f m11046a() {
        if (f15326a == null) {
            synchronized (C6213f.class) {
                if (f15326a == null) {
                    f15326a = new C6213f();
                }
            }
        }
        return f15326a;
    }

    /* renamed from: b */
    public String m11044b() {
        String m11045a;
        switch (m11043c()) {
            case MIUI:
                m11045a = m11045a(C5855l.m12238a("023GflgfhffhfkfifkhffifkhfffSh.flhjfkgf+g$hf(gfPfhIh"));
                break;
            case EMUI:
                m11045a = m11045a(C5855l.m12238a("021DflgfhfhgfifkLiZfehfff?h9flhjfkgf=g,hfVh9fhfifk"));
                break;
            case AMIGO:
            case FLYME:
                m11045a = m11045a(C5855l.m12238a("019Sflgfhfhgfifk9iIfehffefkhj.lifLfmhffkfe"));
                break;
            case LENOVO:
            case ONEUI:
                m11045a = m11045a(C5855l.m12238a("0285flgfhfhgfifk-i?fehfffWhDflhjfkgfUgBhffk;geGfl]h7fh5hgkfi"));
                break;
            case COLOR_OS:
                m11045a = m11045a(C5855l.m12238a("024Dflgfhfhgfifk0iRfehfff0h8flhjfkgf g6hfgfLll)gfflgffh"));
                break;
            case FUNTOUCH_OS:
                m11045a = m11045a(C5855l.m12238a("027VflgfhffffkffgfhfgfhjhfhgfifkCiXfehffefkhjGlifYfmhffkfe"));
                if (TextUtils.isEmpty(m11045a)) {
                    m11045a = m11045a(C5855l.m12238a("018:flgfhffffkffgfhfgfhjhfffEh<flhjfkgfVg"));
                    break;
                }
                break;
            case EUI:
                m11045a = m11045a(C5855l.m12238a("023+flgfhf'ihk*ffhffl;hihf^hj7hUhfff5h@flhjfkgfEg"));
                break;
            case SENSE:
                m11045a = m11045a(C5855l.m12238a("022;flgfhfhgfifk+i$fehfhj4hgXhjMh!hfffQhHflhjfkgf.g"));
                break;
            case GOOGLE:
                m11045a = m11045a(C5855l.m12238a("024^flgfhfhgfifkTi(fehfff$h*flhjfkgf[gYhffl_hihf=hjFh"));
                break;
            case SMARTISAN:
                m11045a = m11045a(C5855l.m12238a("020-flgfhfhjfhYf3fl2k;fkhjMfgMhfff8hMflhjfkgf3g"));
                break;
            case ONEPLUS:
                m11045a = m11045a(C5855l.m12238a("014>flgfhfflgffhhfff)h5flhjfkgfWg"));
                break;
            case YUNOS:
                m11045a = m11045a(C5855l.m12238a("0209flgfhfWekfThffmfiLg5gfhjhfffGhCflhjfkgfMg"));
                break;
            case QIHOO:
                m11045a = m11045a(C5855l.m12238a("018_flgfhfhgfifkLiJfehffifkff^h8flhjfkgf:g"));
                break;
            case NUBIA:
                m11045a = m11045a(C5855l.m12238a("023Yflgfhfhgfifk'iHfehfLg;fihgfk-f>hfflgffhhfLe@gffe8h"));
                if (TextUtils.isEmpty(m11045a)) {
                    m11045a = m11045a(C5855l.m12238a("015Qflgfhfhgfifk;iJfehfflgffhhffkfe"));
                    break;
                }
                break;
            case LGE:
                m11045a = m11045a(C5855l.m12238a("021>hjfmhjhfHi)gg9hAhfRi@ggfhfefhfjff.hEflhjfkgfBg"));
                break;
            default:
                m11045a = m11045a(C5855l.m12238a("019!flgfhfhgfifk)iQfehffefkhj:lifUfmhffkfe"));
                break;
        }
        return TextUtils.isEmpty(m11045a) ? m11045a(C5855l.m12238a("019 flgfhfhgfifkPiHfehffefkhjPlif.fmhffkfe")) : m11045a;
    }

    /* renamed from: c */
    private EnumC6215a m11043c() {
        EnumC6215a[] values;
        if (!TextUtils.isEmpty(m11045a("ro.miui.ui.version.code")) || !TextUtils.isEmpty(m11045a(C5855l.m12238a("023Yflgfhffhfkfifkhffifkhfff0h8flhjfkgf<g]hfQgf_fh%h"))) || !TextUtils.isEmpty(m11045a("ro.miui.internal.storage"))) {
            return EnumC6215a.MIUI;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("021^flgfhfhgfifkJi%fehfff%h=flhjfkgf_gBhf1h'fhfifk"))) || !TextUtils.isEmpty(m11045a("ro.build.hw_emui_api_level")) || !TextUtils.isEmpty(m11045a("ro.confg.hw_systemversion"))) {
            return EnumC6215a.EMUI;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("026lhWflhjfkhjVk'hfhjfmhjhffihj>hEhfghPi9fmfh=hThffk+eAgfBg"))) || !TextUtils.isEmpty(m11045a(C5855l.m12238a("026;flgfhffh)hRfkiefihfhjZhk[fi%l*hhfkie6f?flfehfgh$iBfmfhPh"))) || !TextUtils.isEmpty(m11045a(C5855l.m12238a("018Cflgfhfgh:i7fmfh4h:hfYlNfihg4iGfkhjKjh fe")))) {
            return EnumC6215a.FLYME;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("024eAgffhhfhj1f$fhhjfi-g.gghfhjKlh=gghffefkhj^fQhgXih"))) || !TextUtils.isEmpty(m11045a("init.svc.health-hal-2-1-samsung"))) {
            return EnumC6215a.ONEUI;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("0242flgfhfhgfifk6i0fehfffKh0flhjfkgfMg!hfgf%ll gfflgffh")))) {
            return EnumC6215a.COLOR_OS;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("027Mflgfhffffkffgfhfgfhjhfhgfifk!iAfehffefkhjOlif%fmhffkfe"))) || !TextUtils.isEmpty(m11045a(C5855l.m12238a("018>flgfhffffkffgfhfgfhjhfff(h9flhjfkgf-g")))) {
            return EnumC6215a.FUNTOUCH_OS;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("023+flgfhfDihk?ffhfflRhihf=hjIh=hfff8h:flhjfkgf9g")))) {
            return EnumC6215a.EUI;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("022Nflgfhfhgfifk_i=fehfhjIhg7hj@hWhfffWh>flhjfkgf6g")))) {
            return EnumC6215a.SENSE;
        }
        if (C5855l.m12238a("014fg.feflgffkfejmgggfgfggQih").equals(m11045a(C5855l.m12238a("026<flgfhf?e:gffhhfgggfgfggNihBhf+ei$fkJhgk.fkfehgGf)hjXh")))) {
            return EnumC6215a.GOOGLE;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("020>flgfhfhjfh@fUflNk^fkhj fg'hfff5h4flhjfkgf@g")))) {
            return EnumC6215a.SMARTISAN;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("014)flgfhfflgffhhfffKh]flhjfkgf g")))) {
            return EnumC6215a.ONEPLUS;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("020Eflgfhf,ekfChffmfi+gVgfhjhfffFh:flhjfkgf6g")))) {
            return EnumC6215a.YUNOS;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("0181flgfhfhgfifk0iFfehffifkffXhXflhjfkgf*g")))) {
            return EnumC6215a.QIHOO;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("0230flgfhfhgfifkDi1fehf>g-fihgfk5fKhfflgffhhf]e>gffeOh"))) || !TextUtils.isEmpty(m11045a(C5855l.m12238a("015DflgfhfhgfifkWi=fehfflgffhhffkfe")))) {
            return EnumC6215a.NUBIA;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("021OhjfmhjhfEi!gg?h3hf0i?ggfhfefhfjffGh:flhjfkgfHg")))) {
            return EnumC6215a.LGE;
        }
        if (!TextUtils.isEmpty(m11045a(C5855l.m12238a("019*flgfhfhgfifk_i;fehffefkhjMlifYfmhffkfe"))) && m11045a(C5855l.m12238a("019Mflgfhfhgfifk2i4fehffefkhj:lif8fmhffkfe")).matches("amigo([\\d.]+)[a-zA-Z]*")) {
            return EnumC6215a.AMIGO;
        }
        for (EnumC6215a enumC6215a : EnumC6215a.values()) {
            if (enumC6215a.m11042a().equalsIgnoreCase(C6152DH.SyncMtd.getManufacturer())) {
                return enumC6215a;
            }
        }
        return EnumC6215a.OTHER;
    }

    /* renamed from: a */
    private String m11045a(String str) {
        return C6152DH.SyncMtd.getSystemProperties(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.f$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6215a {
        MIUI(C5855l.m12238a("006Sgefk2f?gffhfk")),
        EMUI(C5855l.m12238a("006jUfiHfOhhOh2fk")),
        FLYME(C5855l.m12238a("005Cfh3h<fkiefi")),
        ONEUI(C5855l.m12238a("007QhjGfBfhhjfiIgHgg")),
        COLOR_OS(C5855l.m12238a("004<gfKllAgf")),
        FUNTOUCH_OS(C5855l.m12238a("004Offfkffgf")),
        EUI(C5855l.m12238a("004ihkCff")),
        SENSE(C5855l.m12238a("003jke")),
        GOOGLE(C5855l.m12238a("006,gggfgfggEih")),
        LENOVO(C5855l.m12238a("006ihgVgfffgf")),
        SMARTISAN(C5855l.m12238a("006ejBfifkiefk")),
        ONEPLUS(C5855l.m12238a("007Hgf(ghliCfihj")),
        YUNOS(C5855l.m12238a("005Mfmfi;g^gfhj")),
        QIHOO(C5855l.m12238a("0056fgfkPjYgfgf")),
        NUBIA(C5855l.m12238a("005gWfihgfkNf")),
        LGE(C5855l.m12238a("002iFgg")),
        AMIGO(C5855l.m12238a("005?jjfk-gi'fk")),
        OTHER("");
        

        /* renamed from: s */
        private String f15347s;

        EnumC6215a(String str) {
            this.f15347s = str;
        }

        /* renamed from: a */
        public String m11042a() {
            return this.f15347s;
        }
    }
}
