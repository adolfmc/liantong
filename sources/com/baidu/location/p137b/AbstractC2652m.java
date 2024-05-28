package com.baidu.location.p137b;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.location.Jni;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p136a.C2621a;
import com.baidu.location.p138c.C2688a;
import com.baidu.location.p138c.C2689b;
import com.baidu.location.p138c.C2697f;
import com.baidu.location.p138c.C2710k;
import com.baidu.location.p138c.C2711l;
import com.baidu.location.p140e.AbstractC2729f;
import com.baidu.location.p140e.C2725d;
import com.baidu.location.p140e.C2735k;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC2652m {

    /* renamed from: c */
    public static String f5307c;

    /* renamed from: a */
    public C2710k f5308a = null;

    /* renamed from: b */
    public C2688a f5309b = null;

    /* renamed from: e */
    private boolean f5311e = true;

    /* renamed from: f */
    private boolean f5312f = true;

    /* renamed from: g */
    private boolean f5313g = false;

    /* renamed from: h */
    private long f5314h = 0;

    /* renamed from: d */
    final Handler f5310d = new HandlerC2653a();

    /* renamed from: i */
    private String f5315i = null;

    /* renamed from: j */
    private String f5316j = null;

    /* renamed from: k */
    private boolean f5317k = false;

    /* renamed from: l */
    private long f5318l = 0;

    /* renamed from: m */
    private int f5319m = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.m$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class HandlerC2653a extends Handler {
        public HandlerC2653a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (ServiceC2737f.isServing) {
                int i = message.what;
                if (i == 21) {
                    AbstractC2652m.this.mo19388a(message);
                    return;
                }
                switch (i) {
                    case 62:
                    case 63:
                        AbstractC2652m.this.mo19389a();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.m$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2654b extends AbstractC2729f {

        /* renamed from: a */
        String f5321a = null;

        /* renamed from: b */
        String f5322b = null;

        /* renamed from: c */
        long f5323c = 0;

        /* renamed from: d */
        long f5324d = 0;

        public C2654b() {
            this.f5727j = new HashMap();
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19077a() {
            if ((C2735k.f5815g || C2735k.f5817i) && AbstractC2652m.this.f5315i != null && AbstractC2652m.this.f5316j != null) {
                this.f5322b += String.format(Locale.CHINA, "&ki=%s&sn=%s", AbstractC2652m.this.f5315i, AbstractC2652m.this.f5316j);
            }
            if (C2655n.m19419a().m19417b()) {
                this.f5322b += "&enc=2";
            }
            String encodeTp4 = Jni.encodeTp4(this.f5322b);
            this.f5322b = null;
            if (this.f5321a == null) {
                this.f5321a = C2684y.m19288b();
            }
            this.f5727j.put(BaseLocationBox.TYPE, encodeTp4);
            if (this.f5321a != null) {
                this.f5727j.put("up", this.f5321a);
            }
            this.f5727j.put("trtm", String.format(Locale.CHINA, "%d", Long.valueOf(System.currentTimeMillis())));
        }

        /* renamed from: a */
        public void m19420a(String str, long j) {
            this.f5322b = str;
            this.f5324d = System.currentTimeMillis();
            this.f5323c = j;
            ExecutorService m19309b = C2678w.m19310a().m19309b();
            if (C2735k.m19056b()) {
                m19075a(m19309b, false, null);
            } else if (m19309b != null) {
                m19076a(m19309b, C2725d.f5705c);
            } else {
                m19073b(C2725d.f5705c);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:55:0x011a  */
        /* JADX WARN: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void mo19074a(boolean r11) {
            /*
                Method dump skipped, instructions count: 288
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.AbstractC2652m.C2654b.mo19074a(boolean):void");
        }
    }

    /* renamed from: a */
    public String m19423a(String str) {
        C2710k c2710k;
        String m19114l;
        if (this.f5315i == null) {
            this.f5315i = C2621a.m19572b(ServiceC2737f.getServiceContext());
        }
        if (this.f5316j == null) {
            this.f5316j = C2621a.m19571c(ServiceC2737f.getServiceContext());
        }
        C2688a c2688a = this.f5309b;
        if (c2688a == null || !c2688a.m19269a()) {
            this.f5309b = C2689b.m19259a().m19237f();
        }
        C2710k c2710k2 = this.f5308a;
        if (c2710k2 == null || !c2710k2.m19138j()) {
            this.f5308a = C2711l.m19133a().m19111o();
        }
        Location m19171g = C2697f.m19228a().m19164j() ? C2697f.m19228a().m19171g() : null;
        C2688a c2688a2 = this.f5309b;
        if ((c2688a2 == null || c2688a2.m19265d() || this.f5309b.m19266c()) && (((c2710k = this.f5308a) == null || c2710k.m19156a() == 0) && m19171g == null)) {
            return null;
        }
        String m19422b = m19422b();
        if (C2650l.m19439a().m19428d() == -2) {
            m19422b = m19422b + "&imo=1";
        }
        int m19055b = C2735k.m19055b(ServiceC2737f.getServiceContext());
        if (m19055b >= 0) {
            m19422b = m19422b + "&lmd=" + m19055b;
            if (Build.VERSION.SDK_INT >= 28 && !this.f5317k) {
                this.f5317k = true;
                try {
                    if (ServiceC2737f.getServiceContext().getPackageManager().hasSystemFeature("android.hardware.wifi.rtt")) {
                        m19422b = m19422b + "&rtt=1";
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
        C2710k c2710k3 = this.f5308a;
        if ((c2710k3 == null || c2710k3.m19156a() == 0) && (m19114l = C2711l.m19133a().m19114l()) != null) {
            m19422b = m19114l + m19422b;
        }
        String str2 = m19422b;
        if (this.f5312f) {
            this.f5312f = false;
            return C2735k.m19063a(this.f5309b, this.f5308a, m19171g, str2, 0, true);
        }
        return C2735k.m19064a(this.f5309b, this.f5308a, m19171g, str2, 0);
    }

    /* renamed from: a */
    public abstract void mo19389a();

    /* renamed from: a */
    public abstract void mo19388a(Message message);

    /* renamed from: b */
    public String m19422b() {
        String m19548c = C2628b.m19560a().m19548c();
        String format = C2711l.m19133a().m19117i() ? "&cn=32" : String.format(Locale.CHINA, "&cn=%d", Integer.valueOf(C2689b.m19259a().m19238e()));
        long currentTimeMillis = System.currentTimeMillis() - this.f5318l;
        if (Build.VERSION.SDK_INT >= 18 && currentTimeMillis > 60000) {
            this.f5318l = System.currentTimeMillis();
            String m19051c = C2735k.m19051c();
            if (!TextUtils.isEmpty(m19051c)) {
                format = format + "&qcip6c=" + m19051c;
            }
        }
        if (this.f5311e) {
            this.f5311e = false;
            int i = Build.VERSION.SDK_INT;
        } else if (!this.f5313g) {
            String m19282e = C2684y.m19282e();
            if (m19282e != null) {
                format = format + m19282e;
            }
            this.f5313g = true;
        }
        return format + m19548c;
    }
}
