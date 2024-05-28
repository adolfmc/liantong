package com.networkbench.agent.impl.crash.p249a;

import android.content.SharedPreferences;
import android.util.Log;
import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.harvest.p261b.C6458a;
import com.networkbench.agent.impl.util.C6638h;
import java.io.File;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6313c {

    /* renamed from: a */
    public static int f15854a = 5242880;

    /* renamed from: b */
    private static final String f15855b = "nbs_custom_data_store_" + ConfigurationName.processName;

    /* renamed from: c */
    private static final String f15856c = "nbs_custom_data_store_copy" + ConfigurationName.processName;

    /* renamed from: a */
    public static synchronized void m10477a(String str, String str2) {
        synchronized (C6313c.class) {
            try {
                System.currentTimeMillis();
                if (m10470g() < f15854a) {
                    SharedPreferences.Editor edit = C6638h.m8963w().m9076K().getSharedPreferences(f15855b, 0).edit();
                    edit.putString(str, str2);
                    edit.commit();
                }
            } catch (Throwable unused) {
                C6458a.m9944a(C6458a.f16322a, 0);
            }
        }
    }

    /* renamed from: b */
    public static synchronized void m10475b(String str, String str2) {
        synchronized (C6313c.class) {
            try {
                if (m10469h() < f15854a) {
                    SharedPreferences.Editor edit = C6638h.m8963w().m9076K().getSharedPreferences(f15856c, 0).edit();
                    edit.putString(str, str2);
                    edit.commit();
                }
            } catch (Throwable unused) {
                C6458a.m9944a(C6458a.f16322a, 0);
                Log.d("NBSAgent", "content==null, BusinessData error!");
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m10478a() {
        synchronized (C6313c.class) {
            SharedPreferences.Editor edit = C6638h.m8963w().m9076K().getSharedPreferences(f15856c, 0).edit();
            edit.clear();
            edit.commit();
        }
    }

    /* renamed from: b */
    public static synchronized int m10476b() {
        int size;
        synchronized (C6313c.class) {
            size = C6638h.m8963w().m9076K().getSharedPreferences(f15856c, 0).getAll().size();
        }
        return size;
    }

    /* renamed from: c */
    public static synchronized int m10474c() {
        int size;
        synchronized (C6313c.class) {
            size = C6638h.m8963w().m9076K().getSharedPreferences(f15855b, 0).getAll().size();
        }
        return size;
    }

    /* renamed from: d */
    public static synchronized Map<String, ?> m10473d() {
        Map<String, ?> all;
        synchronized (C6313c.class) {
            all = C6638h.m8963w().m9076K().getSharedPreferences(f15855b, 0).getAll();
        }
        return all;
    }

    /* renamed from: e */
    public static synchronized Map<String, ?> m10472e() {
        Map<String, ?> all;
        synchronized (C6313c.class) {
            all = C6638h.m8963w().m9076K().getSharedPreferences(f15856c, 0).getAll();
        }
        return all;
    }

    /* renamed from: f */
    public static synchronized void m10471f() {
        synchronized (C6313c.class) {
            SharedPreferences.Editor edit = C6638h.m8963w().m9076K().getSharedPreferences(f15855b, 0).edit();
            edit.clear();
            edit.commit();
        }
    }

    /* renamed from: g */
    private static synchronized long m10470g() {
        synchronized (C6313c.class) {
            File file = new File(m10468i());
            if (file.exists()) {
                return file.length();
            }
            return -1L;
        }
    }

    /* renamed from: h */
    private static synchronized long m10469h() {
        synchronized (C6313c.class) {
            File file = new File(m10467j());
            if (file.exists()) {
                return file.length();
            }
            return -1L;
        }
    }

    /* renamed from: i */
    private static synchronized String m10468i() {
        String str;
        synchronized (C6313c.class) {
            str = "/data/data/" + C6638h.m8963w().m9076K().getPackageName() + "/shared_prefs/" + f15855b + ".xml";
        }
        return str;
    }

    /* renamed from: j */
    private static synchronized String m10467j() {
        String str;
        synchronized (C6313c.class) {
            str = "/data/data/" + C6638h.m8963w().m9076K().getPackageName() + "/shared_prefs/" + f15856c + ".xml";
        }
        return str;
    }
}
