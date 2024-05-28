package com.xiaomi.push.service;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.AbstractC11356fa;
import com.xiaomi.push.C11169au;
import com.xiaomi.push.C11228cc;
import com.xiaomi.push.C11232cg;
import com.xiaomi.push.C11287do;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.C11333eo;
import com.xiaomi.push.C11336ep;
import com.xiaomi.push.C11389fx;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.EnumC11324ei;
import com.xiaomi.push.InterfaceC11231cf;
import com.xiaomi.push.service.C11571ax;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.ao */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11556ao extends C11571ax.AbstractC11573a implements C11232cg.InterfaceC11235a {

    /* renamed from: a */
    private long f23601a;

    /* renamed from: a */
    private XMPushService f23602a;

    @Override // com.xiaomi.push.service.C11571ax.AbstractC11573a
    /* renamed from: a */
    public void mo2609a(C11287do.C11288a c11288a) {
    }

    /* renamed from: a */
    public static void m2659a(XMPushService xMPushService) {
        C11556ao c11556ao = new C11556ao(xMPushService);
        C11571ax.m2625a().m2621a(c11556ao);
        synchronized (C11232cg.class) {
            C11232cg.m4567a(c11556ao);
            C11232cg.m4568a(xMPushService, null, new C11557a(), "0", "push", "2.2");
        }
    }

    C11556ao(XMPushService xMPushService) {
        this.f23602a = xMPushService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.ao$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11557a implements C11232cg.InterfaceC11236b {
        C11557a() {
        }

        @Override // com.xiaomi.push.C11232cg.InterfaceC11236b
        /* renamed from: a */
        public String mo2658a(String str) {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            buildUpon.appendQueryParameter("sdkver", String.valueOf(48));
            buildUpon.appendQueryParameter("osver", String.valueOf(Build.VERSION.SDK_INT));
            buildUpon.appendQueryParameter("os", C11389fx.m3748a(Build.VERSION.INCREMENTAL));
            buildUpon.appendQueryParameter("mi", String.valueOf(C11479r.m2935a()));
            String builder = buildUpon.toString();
            AbstractC11049b.m5270c("fetch bucket from : " + builder);
            URL url = new URL(builder);
            int port = url.getPort() == -1 ? 80 : url.getPort();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                String m4845a = C11169au.m4845a(C11479r.m2934a(), url);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                C11336ep.m3974a(url.getHost() + ":" + port, (int) currentTimeMillis2, null);
                return m4845a;
            } catch (IOException e) {
                C11336ep.m3974a(url.getHost() + ":" + port, -1, e);
                throw e;
            }
        }
    }

    /* renamed from: com.xiaomi.push.service.ao$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class C11558b extends C11232cg {
        protected C11558b(Context context, InterfaceC11231cf interfaceC11231cf, C11232cg.InterfaceC11236b interfaceC11236b, String str) {
            super(context, interfaceC11231cf, interfaceC11236b, str);
        }

        @Override // com.xiaomi.push.C11232cg
        /* renamed from: a */
        public String mo2657a(ArrayList<String> arrayList, String str, String str2, boolean z) {
            try {
                if (C11333eo.m3990a().m3988a()) {
                    str2 = C11571ax.m2624a();
                }
                return super.mo2657a(arrayList, str, str2, z);
            } catch (IOException e) {
                C11336ep.m3977a(0, EnumC11324ei.GSLB_ERR.m4043a(), 1, null, C11169au.m4835b(f21695a) ? 1 : 0);
                throw e;
            }
        }
    }

    @Override // com.xiaomi.push.service.C11571ax.AbstractC11573a
    /* renamed from: a */
    public void mo2608a(C11289dp.C11291b c11291b) {
        C11228cc m4557b;
        if (c11291b.m4271b() && c11291b.m4275a() && System.currentTimeMillis() - this.f23601a > 3600000) {
            AbstractC11049b.m5282a("fetch bucket :" + c11291b.m4275a());
            this.f23601a = System.currentTimeMillis();
            C11232cg m4574a = C11232cg.m4574a();
            m4574a.m4571a();
            m4574a.m4558b();
            AbstractC11356fa m2902a = this.f23602a.m2902a();
            if (m2902a == null || (m4557b = m4574a.m4557b(m2902a.m3898a().m3863c())) == null) {
                return;
            }
            ArrayList<String> m4604a = m4557b.m4604a();
            boolean z = true;
            Iterator<String> it = m4604a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next().equals(m2902a.mo3853a())) {
                    z = false;
                    break;
                }
            }
            if (!z || m4604a.isEmpty()) {
                return;
            }
            AbstractC11049b.m5282a("bucket changed, force reconnect");
            this.f23602a.m2894a(0, (Exception) null);
            this.f23602a.m2865a(false);
        }
    }

    @Override // com.xiaomi.push.C11232cg.InterfaceC11235a
    /* renamed from: a */
    public C11232cg mo2660a(Context context, InterfaceC11231cf interfaceC11231cf, C11232cg.InterfaceC11236b interfaceC11236b, String str) {
        return new C11558b(context, interfaceC11231cf, interfaceC11236b, str);
    }
}
