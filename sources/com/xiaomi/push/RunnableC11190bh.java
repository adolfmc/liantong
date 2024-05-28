package com.xiaomi.push;

import android.app.NotificationChannel;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.sdk.p285a.C6960d;
import com.xiaomi.push.service.C11520a;
import com.xiaomi.push.service.C11533af;
import com.xiaomi.push.service.C11577az;
import com.xiaomi.push.service.C11579ba;
import com.xiaomi.push.service.C11591f;
import com.xiaomi.push.service.C11615q;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;

/* renamed from: com.xiaomi.push.bh */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RunnableC11190bh implements Runnable {
    private RunnableC11190bh() {
    }

    /* renamed from: a */
    public static void m4731a(Context context) {
        if (context == null || !"com.xiaomi.xmsf".equals(context.getPackageName())) {
            return;
        }
        C11193a c11193a = new C11193a(context);
        if (c11193a.m4724a()) {
            new Thread(new RunnableC11190bh()).start();
            c11193a.m4725a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.bh$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11193a {

        /* renamed from: a */
        public SharedPreferences f21605a;

        /* renamed from: a */
        private final String f21606a = "dc_job_result_time_26";

        public C11193a(Context context) {
            this.f21605a = context.getSharedPreferences("mipush_extra", 0);
            long j = this.f21605a.getLong("dc_job_result_time_26", 0L);
            if (j <= 0 || j - System.currentTimeMillis() > 259200000) {
                this.f21605a.edit().putLong("dc_job_result_time_26", m4726a()).apply();
            }
        }

        private C11193a() {
        }

        /* renamed from: a */
        private long m4726a() {
            long currentTimeMillis = System.currentTimeMillis();
            Random random = new Random(currentTimeMillis);
            return (((currentTimeMillis / 86400000) + 1) * 86400000) + (random.nextInt(3) * 86400000) + random.nextInt(46800000);
        }

        /* renamed from: a */
        public boolean m4724a() {
            return System.currentTimeMillis() - this.f21605a.getLong("dc_job_result_time_26", 0L) > 0;
        }

        /* renamed from: a */
        public void m4725a() {
            long j = this.f21605a.getLong("dc_job_result_time_26", 0L);
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (currentTimeMillis >= 0) {
                this.f21605a.edit().putLong("dc_job_result_time_26", j + (((currentTimeMillis / 259200000) + 1) * 259200000)).apply();
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Exception exc;
        List<NotificationChannel> m2769a;
        final Context m2934a = C11479r.m2934a();
        if (m2934a != null) {
            C11186bd c11186bd = new C11186bd();
            C11185bc c11185bc = new C11185bc(50L, 1000L);
            try {
                Map<String, ?> all = C11479r.m2934a().getSharedPreferences("pref_registered_pkg_names", 0).getAll();
                if (all != null && !all.isEmpty()) {
                    Set<String> keySet = all.keySet();
                    boolean z = true;
                    c11186bd.m4738a(keySet.contains("com.xiaomi.xmsf") ? keySet.size() - 1 : keySet.size());
                    C11189bg c11189bg = new C11189bg();
                    c11189bg.put("c", c11186bd.m4740a());
                    Set<Map.Entry<String, ?>> entrySet = all.entrySet();
                    C11188bf c11188bf = new C11188bf();
                    for (Map.Entry<String, ?> entry : entrySet) {
                        final String key = entry.getKey();
                        String str = (String) entry.getValue();
                        if (!TextUtils.isEmpty(key) && !"com.xiaomi.xmsf".equals(key) && !TextUtils.isEmpty(str)) {
                            C11189bg c11189bg2 = new C11189bg();
                            c11189bg2.put("a", str);
                            c11189bg2.put("s", (String) c11185bc.m4745a(new Callable<String>() { // from class: com.xiaomi.push.bh.1
                                @Override // java.util.concurrent.Callable
                                /* renamed from: a */
                                public String call() {
                                    return String.valueOf(C11520a.m2824a(m2934a, key));
                                }
                            }));
                            if (Build.VERSION.SDK_INT >= 26 && (m2769a = C11533af.m2760a(m2934a, key).m2769a()) != null && !m2769a.isEmpty()) {
                                C11188bf c11188bf2 = new C11188bf();
                                c11186bd.m4736b(m2769a.size());
                                for (final NotificationChannel notificationChannel : m2769a) {
                                    String id = notificationChannel.getId();
                                    C11189bg c11189bg3 = new C11189bg();
                                    if (id.startsWith("mipush_")) {
                                        String replace = id.replace("mipush_" + key + "_", "");
                                        c11189bg3.put("t", 1);
                                        c11189bg3.put("c", replace);
                                    } else if (id.startsWith("mipush|")) {
                                        String replace2 = id.replace("mipush|" + key + "|", "");
                                        c11189bg3.put("t", 2);
                                        c11189bg3.put("c", replace2);
                                    }
                                    c11189bg3.put("s", (String) c11185bc.m4745a(new Callable() { // from class: com.xiaomi.push.bh.2
                                        @Override // java.util.concurrent.Callable
                                        /* renamed from: a */
                                        public String call() {
                                            return String.valueOf(C11591f.m2554a(m2934a, key, notificationChannel));
                                        }
                                    }));
                                    c11188bf2.put(c11189bg3);
                                    z = true;
                                }
                                c11189bg2.put("c", c11188bf2);
                            }
                            c11188bf.put(c11189bg2);
                            c11189bg.put(C6960d.f18019d, c11188bf);
                        }
                        if (c11189bg.mo4732a() > 30720) {
                            c11186bd.m4739a();
                            c11186bd.m4734c(c11189bg.mo4732a());
                            m4730a(m2934a, c11189bg, c11186bd);
                            C11189bg c11189bg4 = new C11189bg();
                            c11189bg4.put("c", c11186bd.m4740a());
                            c11188bf = new C11188bf();
                            c11189bg = c11189bg4;
                        }
                        z = true;
                    }
                    if (c11188bf.length() > 0) {
                        c11186bd.m4739a();
                        c11186bd.m4734c(c11189bg.mo4732a());
                        m4730a(m2934a, c11189bg, c11186bd);
                    }
                }
                exc = null;
            } catch (Exception e) {
                exc = e;
            }
            m4729a(c11186bd, c11185bc, exc);
        }
    }

    /* renamed from: a */
    private void m4729a(C11186bd c11186bd, C11185bc c11185bc, Exception exc) {
        HashMap hashMap = new HashMap();
        String m2429a = C11615q.m2429a(C11479r.m2934a());
        if (!TextUtils.isEmpty(m2429a)) {
            hashMap.put("uuid", m2429a);
        }
        hashMap.put("appCount", Long.valueOf(c11186bd.m4740a()));
        hashMap.put("channels", Long.valueOf(c11186bd.m4737b()));
        hashMap.put("packCount", Long.valueOf(c11186bd.m4735c()));
        hashMap.put("totalSize", Long.valueOf(c11186bd.m4733d()));
        hashMap.put("isBatch", Integer.valueOf(c11186bd.m4741a()));
        hashMap.put("maxCallTime", Long.valueOf(c11185bc.m4746a()));
        hashMap.put("minCallTime", Long.valueOf(c11185bc.m4744b()));
        hashMap.put("callAvg", Long.valueOf(c11185bc.m4743c()));
        hashMap.put("duration", Long.valueOf(c11185bc.m4742d()));
        if (exc != null) {
            hashMap.put("exception", exc.toString());
        }
        C11321eh.m4047a().mo4045a("app_switch_upload", hashMap);
    }

    /* renamed from: a */
    private void m4730a(Context context, C11189bg c11189bg, C11186bd c11186bd) {
        C11408gj c11408gj = new C11408gj();
        c11408gj.m3649d("category_app_channel_info");
        c11408gj.m3653c("app_channel_info");
        c11408gj.m3657b(c11189bg.toString());
        c11408gj.m3662a(false);
        c11408gj.m3668a(1L);
        c11408gj.m3665a("xmsf_channel");
        c11408gj.m3658b(System.currentTimeMillis());
        c11408gj.m3642g("com.xiaomi.xmsf");
        c11408gj.m3646e("com.xiaomi.xmsf");
        c11408gj.m3644f(C11577az.m2598a());
        C11579ba.m2584a(context, c11408gj);
    }
}
