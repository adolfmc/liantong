package com.bytedance.applog;

import com.bytedance.applog.profile.UserProfileCallback;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.n */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3632n extends AbstractC3579f {

    /* renamed from: f */
    public int f8598f;

    /* renamed from: com.bytedance.applog.n$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3633a implements UserProfileCallback {

        /* renamed from: a */
        public final /* synthetic */ JSONObject f8599a;

        public C3633a(JSONObject jSONObject) {
            this.f8599a = jSONObject;
        }

        @Override // com.bytedance.applog.profile.UserProfileCallback
        public void onFail(int i) {
            C3704u2.m17108a("sync error: " + i, (Throwable) null);
        }

        @Override // com.bytedance.applog.profile.UserProfileCallback
        public void onSuccess() {
            C3632n.this.f8598f = this.f8599a.hashCode();
        }
    }

    public C3632n(C3591h c3591h) {
        super(c3591h);
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: c */
    public boolean mo17162c() {
        try {
            C3735y c3735y = this.f8439a.f8469h;
            JSONObject header = AppLog.getHeader();
            if (c3735y.m17014c() == 0 || header == null || this.f8598f == header.hashCode()) {
                return true;
            }
            C3581f1.m17300a(this.f8439a, 1, new JSONObject(), new C3633a(header), null, true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: d */
    public String mo17161d() {
        return "up";
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: e */
    public long[] mo17160e() {
        return new long[60000];
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: g */
    public boolean mo17159g() {
        return true;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: h */
    public long mo17158h() {
        return 60000L;
    }
}
