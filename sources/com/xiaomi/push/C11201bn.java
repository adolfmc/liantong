package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11213bw;
import com.xiaomi.push.service.C11537ah;
import com.xiaomi.push.service.C11577az;
import java.lang.ref.WeakReference;

/* renamed from: com.xiaomi.push.bn */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11201bn {

    /* renamed from: a */
    private static volatile C11201bn f21613a;

    /* renamed from: a */
    private Context f21614a;

    /* renamed from: a */
    private InterfaceC11221bx f21616a;

    /* renamed from: a */
    private InterfaceC11222by f21617a;

    /* renamed from: e */
    private String f21624e;

    /* renamed from: f */
    private String f21625f;

    /* renamed from: a */
    private final String f21618a = "push_stat_sp";

    /* renamed from: b */
    private final String f21620b = "upload_time";

    /* renamed from: c */
    private final String f21622c = "delete_time";

    /* renamed from: d */
    private final String f21623d = "check_time";

    /* renamed from: a */
    private C11134ae.AbstractRunnableC11137a f21615a = new C11134ae.AbstractRunnableC11137a() { // from class: com.xiaomi.push.bn.1
        @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
        /* renamed from: a */
        public String mo2289a() {
            return "10052";
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC11049b.m5270c("exec== mUploadJob");
            if (C11201bn.this.f21617a != null) {
                C11201bn.this.f21617a.m4661a(C11201bn.this.f21614a);
                C11201bn.this.m4693b("upload_time");
            }
        }
    };

    /* renamed from: b */
    private C11134ae.AbstractRunnableC11137a f21619b = new C11134ae.AbstractRunnableC11137a() { // from class: com.xiaomi.push.bn.2
        @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
        /* renamed from: a */
        public String mo2289a() {
            return "10054";
        }

        @Override // java.lang.Runnable
        public void run() {
            AbstractC11049b.m5270c("exec== DbSizeControlJob");
            C11213bw.m4683a(C11201bn.this.f21614a).m4680a(new RunnableC11206bp(C11201bn.this.m4692c(), new WeakReference(C11201bn.this.f21614a)));
            C11201bn.this.m4693b("check_time");
        }
    };

    /* renamed from: c */
    private C11134ae.AbstractRunnableC11137a f21621c = new C11134ae.AbstractRunnableC11137a() { // from class: com.xiaomi.push.bn.3
        @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
        /* renamed from: a */
        public String mo2289a() {
            return "10053";
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C11201bn.this.f21617a != null) {
                C11201bn.this.f21617a.m4660b(C11201bn.this.f21614a);
                C11201bn.this.m4693b("delete_time");
            }
        }
    };

    private C11201bn(Context context) {
        this.f21614a = context;
    }

    /* renamed from: a */
    public static C11201bn m4703a(Context context) {
        if (f21613a == null) {
            synchronized (C11201bn.class) {
                if (f21613a == null) {
                    f21613a = new C11201bn(context);
                }
            }
        }
        return f21613a;
    }

    /* renamed from: a */
    private boolean m4704a() {
        return C11537ah.m2715a(this.f21614a).m2716a(EnumC11409gk.StatDataSwitch.m3637a(), true);
    }

    /* renamed from: a */
    public void m4698a(C11213bw.AbstractRunnableC11215a abstractRunnableC11215a) {
        C11213bw.m4683a(this.f21614a).m4682a(abstractRunnableC11215a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m4693b(String str) {
        SharedPreferences.Editor edit = this.f21614a.getSharedPreferences("push_stat_sp", 0).edit();
        edit.putLong(str, System.currentTimeMillis());
        C11476p.m2938a(edit);
    }

    /* renamed from: a */
    public void m4695a(String str, String str2, Boolean bool) {
        if (this.f21616a != null) {
            if (bool.booleanValue()) {
                this.f21616a.m4663a(this.f21614a, str2, str);
            } else {
                this.f21616a.m4662b(this.f21614a, str2, str);
            }
        }
    }

    /* renamed from: a */
    public String m4705a() {
        return this.f21624e;
    }

    /* renamed from: b */
    public String m4694b() {
        return this.f21625f;
    }

    /* renamed from: a */
    public void m4696a(String str) {
        if (m4704a() && !TextUtils.isEmpty(str)) {
            m4697a(C11223bz.m4659a(this.f21614a, str));
        }
    }

    /* renamed from: a */
    public void m4697a(C11408gj c11408gj) {
        if (m4704a() && C11577az.m2595a(c11408gj.m3648e())) {
            m4698a(C11210bt.m4687a(this.f21614a, m4692c(), c11408gj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public String m4692c() {
        return this.f21614a.getDatabasePath(C11205bo.f21631a).getAbsolutePath();
    }
}
