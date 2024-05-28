package com.mob.commons;

import android.os.SystemClock;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5829d {
    /* renamed from: a */
    public static boolean m12332a() {
        return m12331a(C5868q.m12203b("003*dcchTc"), true) && CSCenter.getInstance().isOaidEnable();
    }

    /* renamed from: b */
    public static boolean m12330b() {
        return m12331a(C5868q.m12203b("003cfc"), true);
    }

    /* renamed from: c */
    public static boolean m12329c() {
        return m12331a(C5868q.m12203b("003Qeech.c"), true);
    }

    /* renamed from: d */
    public static boolean m12328d() {
        return m12331a(C5868q.m12203b("0038ee5fc"), true);
    }

    /* renamed from: e */
    public static boolean m12327e() {
        return m12331a(C5868q.m12203b("002fc"), true) && CSCenter.getInstance().isLocationDataEnable();
    }

    /* renamed from: f */
    public static boolean m12326f() {
        return m12331a(C5868q.m12203b("003$dcIfc"), true) && CSCenter.getInstance().isLocationDataEnable();
    }

    /* renamed from: g */
    public static boolean m12325g() {
        return m12331a(C5868q.m12203b("003=dedc$c"), true) && CSCenter.getInstance().isLocationDataEnable();
    }

    /* renamed from: h */
    public static boolean m12324h() {
        return m12331a(C5868q.m12203b("003+edeg]c"), true);
    }

    /* renamed from: i */
    public static boolean m12323i() {
        return m12331a("na", true);
    }

    /* renamed from: a */
    private static boolean m12331a(String str, boolean z) {
        List list = (List) C5747b.m12583a(C5868q.m12203b("003%egdc6b"), (Object) null);
        boolean z2 = list == null || list.contains(str);
        return z ? z2 && m12322j() : z2;
    }

    /* renamed from: j */
    private static boolean m12322j() {
        return SystemClock.elapsedRealtime() - C5855l.m12246a().m12232c() <= ((long) ((Integer) C5747b.m12583a(C5868q.m12203b("0031egchWh"), 600)).intValue()) * 1000;
    }
}
