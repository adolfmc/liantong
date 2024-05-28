package com.xiaomi.push.service;

import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11413go;
import com.xiaomi.push.C11415gq;
import com.xiaomi.push.C11428hc;
import com.xiaomi.push.C11429hd;
import com.xiaomi.push.C11651z;
import com.xiaomi.push.EnumC11410gl;
import com.xiaomi.push.EnumC11411gm;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.ai */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11539ai {
    /* renamed from: a */
    public static void m2705a(C11537ah c11537ah, C11429hd c11429hd) {
        AbstractC11049b.m5271b("OnlineConfigHelper", "-->updateNormalConfigs(): onlineConfig=", c11537ah, ", configMessage=", c11429hd);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (C11413go c11413go : c11429hd.m3357a()) {
            arrayList.add(new Pair<>(c11413go.m3621a(), Integer.valueOf(c11413go.m3622a())));
            List<Pair<Integer, Object>> m2704a = m2704a(c11413go.f22682a, false);
            if (!C11651z.m2259a(m2704a)) {
                arrayList2.addAll(m2704a);
            }
        }
        c11537ah.m2709a(arrayList, arrayList2);
        c11537ah.m2708b();
    }

    /* renamed from: a */
    public static void m2706a(C11537ah c11537ah, C11428hc c11428hc) {
        AbstractC11049b.m5271b("OnlineConfigHelper", "-->updateCustomConfigs(): onlineConfig=", c11537ah, ", configMessage=", c11428hc);
        c11537ah.m2710a(m2704a(c11428hc.m3362a(), true));
        c11537ah.m2708b();
    }

    /* renamed from: a */
    public static int m2707a(C11537ah c11537ah, EnumC11410gl enumC11410gl) {
        int i = 0;
        switch (enumC11410gl) {
            case MISC_CONFIG:
                i = 1;
                break;
        }
        return c11537ah.m2712a(enumC11410gl, i);
    }

    /* renamed from: a */
    private static List<Pair<Integer, Object>> m2704a(List<C11415gq> list, boolean z) {
        Pair pair;
        if (C11651z.m2259a(list)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (C11415gq c11415gq : list) {
            int m3613a = c11415gq.m3613a();
            EnumC11411gm m3634a = EnumC11411gm.m3634a(c11415gq.m3605b());
            if (m3634a != null) {
                if (z && c11415gq.f22758a) {
                    arrayList.add(new Pair(Integer.valueOf(m3613a), null));
                } else {
                    switch (m3634a) {
                        case INT:
                            pair = new Pair(Integer.valueOf(m3613a), Integer.valueOf(c11415gq.m3602c()));
                            break;
                        case LONG:
                            pair = new Pair(Integer.valueOf(m3613a), Long.valueOf(c11415gq.m3612a()));
                            break;
                        case STRING:
                            pair = new Pair(Integer.valueOf(m3613a), c11415gq.m3611a());
                            break;
                        case BOOLEAN:
                            pair = new Pair(Integer.valueOf(m3613a), Boolean.valueOf(c11415gq.m3593g()));
                            break;
                        default:
                            pair = null;
                            break;
                    }
                    arrayList.add(pair);
                }
            }
        }
        return arrayList;
    }
}
