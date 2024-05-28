package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11408gj;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.InterfaceC11403ge;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.service.o */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11612o implements InterfaceC11403ge {

    /* renamed from: a */
    private final XMPushService f23724a;

    public C11612o(XMPushService xMPushService) {
        this.f23724a = xMPushService;
    }

    @Override // com.xiaomi.push.InterfaceC11403ge
    /* renamed from: a */
    public void mo2439a(final List<C11408gj> list, final String str, final String str2) {
        this.f23724a.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.service.o.1
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "Send tiny data.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                String m2440a = C11612o.this.m2440a(str);
                ArrayList<C11430he> m2593a = C11577az.m2593a(list, str, m2440a, 32768);
                if (m2593a != null) {
                    Iterator<C11430he> it = m2593a.iterator();
                    while (it.hasNext()) {
                        C11430he next = it.next();
                        next.m3343a("uploadWay", "longXMPushService");
                        C11427hb m2349a = C11632w.m2349a(str, m2440a, next, EnumC11404gf.Notification);
                        if (!TextUtils.isEmpty(str2) && !TextUtils.equals(str, str2)) {
                            if (m2349a.m3388a() == null) {
                                C11417gs c11417gs = new C11417gs();
                                c11417gs.m3552a("-1");
                                m2349a.m3382a(c11417gs);
                            }
                            m2349a.m3388a().m3542b("ext_traffic_source_pkg", str2);
                        }
                        C11612o.this.f23724a.m2866a(str, C11441hp.m3085a(m2349a), true);
                    }
                    return;
                }
                AbstractC11049b.m5268d("TinyData LongConnUploader.upload Get a null XmPushActionNotification list when TinyDataHelper.pack() in XMPushService.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m2440a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.f23724a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }
}
