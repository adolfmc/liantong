package com.baidu.location.p137b;

import android.annotation.TargetApi;
import android.location.GnssNavigationMessage;
import android.text.TextUtils;
import com.baidu.location.p140e.AbstractC2729f;
import com.baidu.location.p140e.C2721b;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.x */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2681x {

    /* renamed from: a */
    private C2683b f5439a;

    /* renamed from: b */
    private long f5440b = 0;

    /* renamed from: c */
    private long f5441c = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.x$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2682a {

        /* renamed from: a */
        private static C2681x f5442a = new C2681x();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.x$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2683b extends AbstractC2729f {

        /* renamed from: d */
        private boolean f5446d = false;

        /* renamed from: e */
        private String f5447e = null;

        /* renamed from: a */
        public boolean f5443a = false;

        /* renamed from: b */
        public long f5444b = 0;

        public C2683b() {
            this.f5727j = new HashMap();
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19077a() {
            String m19091b = C2721b.m19096a().m19091b();
            if (m19091b != null) {
                m19091b = m19091b + "&gnsst=" + this.f5444b;
            }
            String m19418a = C2655n.m19419a().m19418a(m19091b);
            String replaceAll = !TextUtils.isEmpty(m19418a) ? m19418a.trim().replaceAll("\r|\n", "") : "null";
            String m19418a2 = C2655n.m19419a().m19418a(this.f5447e);
            String replaceAll2 = !TextUtils.isEmpty(m19418a2) ? m19418a2.trim().replaceAll("\r|\n", "") : "null";
            try {
                this.f5727j.put("info", URLEncoder.encode(replaceAll, "utf-8"));
                this.f5727j.put("enl", URLEncoder.encode(replaceAll2, "utf-8"));
            } catch (Exception unused) {
            }
        }

        /* renamed from: a */
        public void m19301a(String str, long j) {
            if (this.f5446d) {
                return;
            }
            this.f5446d = true;
            this.f5447e = str;
            this.f5444b = j;
            ExecutorService m19308c = C2678w.m19310a().m19308c();
            if (m19308c != null) {
                m19076a(m19308c, "https://ofloc.map.baidu.com/locnu");
            } else {
                m19073b("https://ofloc.map.baidu.com/locnu");
            }
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19074a(boolean z) {
            if (z && this.f5726i != null) {
                try {
                    new JSONObject(this.f5726i);
                    this.f5443a = true;
                } catch (Throwable unused) {
                }
            }
            if (this.f5727j != null) {
                this.f5727j.clear();
            }
            this.f5446d = false;
        }

        /* renamed from: b */
        public boolean m19300b() {
            return this.f5446d;
        }
    }

    /* renamed from: a */
    public static C2681x m19305a() {
        return C2682a.f5442a;
    }

    @TargetApi(24)
    /* renamed from: a */
    public void m19304a(GnssNavigationMessage gnssNavigationMessage, long j) {
        C2671s.m19349a().m19348a(gnssNavigationMessage, j);
        this.f5440b = System.currentTimeMillis();
        this.f5441c = j;
    }

    /* renamed from: b */
    public void m19303b() {
        ArrayList<String> m19347b;
        if (this.f5440b == 0 || Math.abs(System.currentTimeMillis() - this.f5440b) >= 20000) {
            return;
        }
        if (this.f5439a == null) {
            this.f5439a = new C2683b();
        }
        C2683b c2683b = this.f5439a;
        if (c2683b == null || c2683b.m19300b() || (m19347b = C2671s.m19349a().m19347b()) == null || m19347b.size() <= 0) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        Iterator<String> it = m19347b.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            i++;
            if (i != m19347b.size()) {
                stringBuffer.append(";");
            }
        }
        this.f5439a.m19301a(stringBuffer.toString(), this.f5441c);
    }
}
