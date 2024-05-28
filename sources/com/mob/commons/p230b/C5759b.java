package com.mob.commons.p230b;

import android.content.Context;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5829d;
import com.mob.commons.C5855l;
import com.mob.commons.CSCenter;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.b.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5759b {

    /* renamed from: a */
    private static final String f14195a = C5855l.m12238a("005;hffhhjPfJhj");

    /* renamed from: a */
    public static synchronized HashMap<String, Object> m12535a(Context context) {
        synchronized (C5759b.class) {
            HashMap<String, Object> hashMap = new HashMap<>();
            HashMap<String, Object> m12536a = m12536a();
            boolean z = m12536a != null && m12536a.size() > 0;
            if (z) {
                HashMap hashMap2 = new HashMap();
                if (m12536a.containsKey(C5855l.m12238a("004[fifefkfe"))) {
                    m12536a.put(C5855l.m12238a("005fAfifefkfe"), m12536a.remove(C5855l.m12238a("0049fifefkfe")));
                }
                if (m12536a.containsKey(C5855l.m12238a("009JhjfiTll3gfflGkh,fe"))) {
                    m12536a.put(C5855l.m12238a("011:fkfeglfi4ll5gffl2khXfe"), m12536a.remove(C5855l.m12238a("009Nhjfi[llMgffl,kh(fe")));
                }
                hashMap2.putAll(m12536a);
                hashMap.put(C5855l.m12238a("009 ghfkfehjil fejh"), hashMap2);
            }
            String m12532c = m12532c(context);
            if (z || !TextUtils.isEmpty(m12532c)) {
                boolean m12533b = m12533b(context);
                hashMap.put(C5855l.m12238a("004QgfSf:fkfe"), m12532c);
                hashMap.put(C5855l.m12238a("0113fkfeglfi0llDgfflGkh'fe"), Boolean.valueOf(m12533b));
                m12534a(m12532c, m12533b);
                return hashMap;
            }
            return null;
        }
    }

    /* renamed from: b */
    public static boolean m12533b(Context context) {
        if (C5829d.m12332a()) {
            return C5760c.m12527b(context);
        }
        return false;
    }

    /* renamed from: c */
    public static String m12532c(Context context) {
        if (CSCenter.getInstance().isOaidEnable()) {
            if (C5829d.m12332a()) {
                return C5760c.m12526c(context);
            }
            return null;
        }
        return CSCenter.getInstance().getOaid();
    }

    /* renamed from: a */
    private static HashMap<String, Object> m12536a() {
        HashMap<String, Object> hashMap;
        File file = new File(MobSDK.getContext().getFilesDir().getAbsolutePath() + C5855l.m12238a("005nHjegfhg>n"), f14195a);
        if (file.exists()) {
            hashMap = (HashMap) ResHelper.readObjectFromFile(file.getAbsolutePath());
            C5741aa.m12650a().m12633b("all_ds", hashMap);
            file.delete();
        } else {
            hashMap = null;
        }
        return (hashMap == null || hashMap.isEmpty()) ? (HashMap) C5741aa.m12650a().m12627c("all_ds", null) : hashMap;
    }

    /* renamed from: a */
    private static void m12534a(String str, boolean z) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put(C5855l.m12238a("004>gf5f:fkfe"), str);
        }
        hashMap.put(C5855l.m12238a("009Ehjfi$ll2gfflWkhPfe"), Boolean.valueOf(z));
        C5741aa.m12650a().m12633b("all_ds", hashMap);
    }
}
