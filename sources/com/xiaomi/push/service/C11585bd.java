package com.xiaomi.push.service;

import android.content.Context;
import android.util.Log;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11480s;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.service.XMPushService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.xiaomi.push.service.bd */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11585bd implements XMPushService.InterfaceC11513n {

    /* renamed from: a */
    private static Context f23667a;

    /* renamed from: a */
    private static final boolean f23669a = Log.isLoggable("UNDatas", 3);

    /* renamed from: a */
    private static final Map<Integer, Map<String, List<String>>> f23668a = new HashMap();

    public C11585bd(Context context) {
        f23667a = context;
    }

    @Override // com.xiaomi.push.service.XMPushService.InterfaceC11513n
    /* renamed from: a */
    public void mo2566a() {
        if (f23668a.size() > 0) {
            synchronized (f23668a) {
                m2563b();
            }
        }
    }

    /* renamed from: b */
    private static void m2563b() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(f23668a);
        if (hashMap.size() > 0) {
            for (Integer num : hashMap.keySet()) {
                Map map = (Map) hashMap.get(num);
                if (map != null && map.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (String str : map.keySet()) {
                        sb.append(str);
                        sb.append(":");
                        List list = (List) map.get(str);
                        if (!C11480s.m2921a(list)) {
                            for (int i = 0; i < list.size(); i++) {
                                if (i != 0) {
                                    sb.append(",");
                                }
                                sb.append((String) list.get(i));
                            }
                        }
                        sb.append(";");
                    }
                    C11430he m2564a = m2564a(null, C11541aj.m2703a(), EnumC11414gp.NotificationRemoved.f22745a, null);
                    m2564a.m3343a("removed_reason", String.valueOf(num));
                    m2564a.m3343a("all_delete_msgId_appId", sb.toString());
                    AbstractC11049b.m5274b("UNDatas upload all removed messages reason: " + num + " allIds: " + sb.toString());
                    m2565a(f23667a, m2564a);
                }
                f23668a.remove(num);
            }
        }
    }

    /* renamed from: a */
    private static void m2565a(Context context, final C11430he c11430he) {
        if (f23669a) {
            AbstractC11049b.m5274b("UNDatas upload message notification:" + c11430he);
        }
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.push.service.bd.1
            @Override // java.lang.Runnable
            public void run() {
                byte[] m3085a = C11441hp.m3085a(C11632w.m2349a(C11430he.this.m3329d(), C11430he.this.m3337b(), C11430he.this, EnumC11404gf.Notification));
                if (C11585bd.f23667a instanceof XMPushService) {
                    ((XMPushService) C11585bd.f23667a).m2866a(C11430he.this.m3329d(), m3085a, true);
                } else {
                    AbstractC11049b.m5282a("UNDatas UploadNotificationDatas failed because not xmsf");
                }
            }
        });
    }

    /* renamed from: a */
    private static C11430he m2564a(String str, String str2, String str3, String str4) {
        C11430he c11430he = new C11430he();
        if (str3 != null) {
            c11430he.m3331c(str3);
        }
        if (str != null) {
            c11430he.m3335b(str);
        }
        if (str2 != null) {
            c11430he.m3344a(str2);
        }
        if (str4 != null) {
            c11430he.m3327d(str4);
        }
        c11430he.m3340a(false);
        return c11430he;
    }
}
