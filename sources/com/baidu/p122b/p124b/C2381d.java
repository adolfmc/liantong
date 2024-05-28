package com.baidu.p122b.p124b;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.baidu.p122b.C2426h;
import com.baidu.p122b.p124b.AbstractC2372a;
import com.baidu.p122b.p131e.C2419a;
import com.baidu.p122b.p132f.C2423b;
import java.io.File;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.b.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2381d extends AbstractC2372a {

    /* renamed from: d */
    C2419a.C2420a f4144d;

    /* renamed from: e */
    private C2382a f4145e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.d$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2382a {

        /* renamed from: c */
        private long f4148c;

        /* renamed from: d */
        private C2426h.C2427a f4149d;

        /* renamed from: e */
        private boolean f4150e;

        /* renamed from: g */
        private int f4152g;

        /* renamed from: b */
        private C2423b f4147b = new C2423b();

        /* renamed from: f */
        private boolean f4151f = true;

        C2382a() {
        }

        /* renamed from: a */
        private boolean m20339a(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    this.f4148c = jSONObject.getLong("pub_lst_ts");
                    this.f4149d = C2426h.m20164a(jSONObject.getString("pub_info"));
                    this.f4152g = jSONObject.getInt("d_form_ver");
                    this.f4150e = false;
                    return true;
                } catch (Exception unused) {
                }
            }
            return false;
        }

        /* renamed from: a */
        public long m20341a() {
            return this.f4148c;
        }

        /* renamed from: a */
        public boolean m20340a(PackageInfo packageInfo) {
            String m20195a = C2381d.this.f4144d.m20198a(new File(packageInfo.applicationInfo.dataDir)).m20195a("pub.dat", true);
            this.f4151f = false;
            return m20339a(m20195a);
        }

        /* renamed from: b */
        public C2426h.C2427a m20338b() {
            return this.f4149d;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.d$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2383b extends AbstractC2372a.AbstractC2374b {

        /* renamed from: b */
        private int f4154b;

        /* renamed from: c */
        private String f4155c;

        /* renamed from: d */
        private long f4156d;

        /* renamed from: e */
        private long f4157e;

        /* renamed from: f */
        private long f4158f;

        /* renamed from: g */
        private C2426h.C2427a f4159g;

        public C2383b(String str) {
            super(C2381d.this.f4144d, str);
        }

        /* renamed from: a */
        public void m20336a(C2382a c2382a) {
            m20335a(c2382a.m20338b());
            m20332b(c2382a.m20341a());
        }

        @Override // com.baidu.p122b.p124b.AbstractC2372a.AbstractC2374b
        /* renamed from: a */
        public void mo20333a(JSONObject jSONObject) {
            this.f4155c = jSONObject.getString("pkg");
            this.f4157e = jSONObject.getInt("tar_pkg_lst_pub_ts");
            this.f4156d = jSONObject.getLong("last_fe_ts");
            this.f4159g = C2426h.m20164a(jSONObject.getString("info"));
            this.f4158f = jSONObject.getLong("tar_pkg_lst_up_ts");
            this.f4154b = jSONObject.getInt("d_form_ver");
        }

        /* renamed from: a */
        public boolean m20337a(long j) {
            if (this.f4156d != j) {
                this.f4156d = j;
                m20350a(true);
                return true;
            }
            return false;
        }

        /* renamed from: a */
        public boolean m20335a(C2426h.C2427a c2427a) {
            if (c2427a.equals(this.f4159g)) {
                return false;
            }
            this.f4159g = c2427a;
            m20350a(true);
            return true;
        }

        /* renamed from: a */
        public boolean m20334a(String str) {
            if (str.equals(this.f4155c)) {
                return false;
            }
            this.f4155c = str;
            m20350a(true);
            return true;
        }

        @Override // com.baidu.p122b.p124b.AbstractC2372a.AbstractC2374b
        /* renamed from: b */
        public void mo20331b(JSONObject jSONObject) {
            jSONObject.put("pkg", this.f4155c);
            jSONObject.put("last_fe_ts", this.f4156d);
            jSONObject.put("tar_pkg_lst_pub_ts", this.f4157e);
            jSONObject.put("info", this.f4159g.m20160a());
            jSONObject.put("tar_pkg_lst_up_ts", this.f4158f);
            jSONObject.put("d_form_ver", 1);
        }

        /* renamed from: b */
        public boolean m20332b(long j) {
            if (this.f4157e != j) {
                this.f4157e = j;
                m20350a(true);
                return true;
            }
            return false;
        }

        /* renamed from: c */
        public String m20330c() {
            return this.f4155c;
        }

        /* renamed from: c */
        public boolean m20329c(long j) {
            if (this.f4158f != j) {
                this.f4158f = j;
                m20350a(true);
                return true;
            }
            return false;
        }

        /* renamed from: d */
        public C2426h.C2427a m20328d() {
            return this.f4159g;
        }

        /* renamed from: e */
        public long m20327e() {
            return this.f4158f;
        }
    }

    public C2381d() {
        super("isc", 8000000L);
        this.f4145e = new C2382a();
    }

    @Override // com.baidu.p122b.p124b.AbstractC2372a
    /* renamed from: a */
    public AbstractC2372a.C2377e mo20321a(String str, AbstractC2372a.C2376d c2376d) {
        PackageInfo packageInfo;
        C2426h.C2427a m20338b;
        C2383b c2383b = null;
        try {
            packageInfo = this.f4128a.f4132a.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return AbstractC2372a.C2377e.m20347a(-2);
        }
        if (c2376d.f4138a) {
            c2383b = new C2383b(str);
            c2383b.m20351a();
            if (str.equals(c2383b.m20330c()) && packageInfo.lastUpdateTime == c2383b.m20327e()) {
                m20338b = c2383b.m20328d();
                return AbstractC2372a.C2377e.m20346a(m20338b);
            }
        }
        C2382a c2382a = new C2382a();
        if (c2382a.m20340a(packageInfo)) {
            if (c2376d.f4138a && c2383b != null) {
                c2383b.m20336a(c2382a);
                c2383b.m20337a(System.currentTimeMillis());
                c2383b.m20329c(packageInfo.lastUpdateTime);
                c2383b.m20334a(str);
                c2383b.m20349b();
            }
            m20338b = c2382a.m20338b();
            return AbstractC2372a.C2377e.m20346a(m20338b);
        }
        return AbstractC2372a.C2377e.m20347a(-2);
    }

    @Override // com.baidu.p122b.p124b.AbstractC2372a
    /* renamed from: a */
    public void mo20326a(AbstractC2372a.C2375c c2375c) {
        this.f4144d = this.f4129b.m20197a("isc");
    }
}
