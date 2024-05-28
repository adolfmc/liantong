package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.data.C2006a;
import com.alipay.sdk.data.C2009c;
import com.alipay.sdk.packet.impl.C2025a;
import com.alipay.sdk.protocol.C2031b;
import com.alipay.sdk.protocol.EnumC2030a;
import com.alipay.sdk.sys.C2032a;
import com.alipay.sdk.sys.C2033b;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.util.C2042e;
import com.alipay.sdk.util.C2050l;
import com.alipay.sdk.util.C2052n;
import com.alipay.sdk.widget.C2058a;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthTask {

    /* renamed from: a */
    static final Object f3529a = C2042e.class;

    /* renamed from: b */
    private Activity f3530b;

    /* renamed from: c */
    private C2058a f3531c;

    public AuthTask(Activity activity) {
        this.f3530b = activity;
        C2033b.m20772a().m20771a(this.f3530b, C2009c.m20855b());
        C2000a.m20902a(activity);
        this.f3531c = new C2058a(activity, "去支付宝授权");
    }

    /* renamed from: a */
    private C2042e.InterfaceC2043a m20957a() {
        return new C1988a(this);
    }

    /* renamed from: b */
    private void m20953b() {
        C2058a c2058a = this.f3531c;
        if (c2058a != null) {
            c2058a.m20619b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m20951c() {
        C2058a c2058a = this.f3531c;
        if (c2058a != null) {
            c2058a.m20617c();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        return C2050l.m20679a(auth(str, z));
    }

    public synchronized String auth(String str, boolean z) {
        String m20910c;
        Activity activity;
        if (z) {
            m20953b();
        }
        C2033b.m20772a().m20771a(this.f3530b, C2009c.m20855b());
        m20910c = C1998j.m20910c();
        C1997i.m20917a("");
        try {
            m20910c = m20956a(this.f3530b, str);
            C2006a.m20868g().m20879a(this.f3530b);
            m20951c();
            activity = this.f3530b;
        } catch (Exception e) {
            C2040c.m20715a(e);
            C2006a.m20868g().m20879a(this.f3530b);
            m20951c();
            activity = this.f3530b;
        }
        C2000a.m20895b(activity, str);
        return m20910c;
    }

    /* renamed from: a */
    private String m20956a(Activity activity, String str) {
        String m20780a = new C2032a(this.f3530b).m20780a(str);
        List<C2006a.C2007a> m20869f = C2006a.m20868g().m20869f();
        if (!C2006a.m20868g().f3732q || m20869f == null) {
            m20869f = C1997i.f3583a;
        }
        if (C2052n.m20659b(this.f3530b, m20869f)) {
            String m20700a = new C2042e(activity, m20957a()).m20700a(m20780a);
            if (!TextUtils.equals(m20700a, "failed") && !TextUtils.equals(m20700a, "scheme_failed")) {
                return TextUtils.isEmpty(m20700a) ? C1998j.m20910c() : m20700a;
            }
            C2000a.m20899a("biz", "LogBindCalledH5", "");
            return m20952b(activity, m20780a);
        }
        C2000a.m20899a("biz", "LogCalledH5", "");
        return m20952b(activity, m20780a);
    }

    /* renamed from: b */
    private String m20952b(Activity activity, String str) {
        EnumC1999k enumC1999k;
        m20953b();
        try {
            try {
                List<C2031b> m20784a = C2031b.m20784a(new C2025a().mo20793a(activity, str).m20813c().optJSONObject("form").optJSONObject("onload"));
                m20951c();
                for (int i = 0; i < m20784a.size(); i++) {
                    if (m20784a.get(i).m20783b() == EnumC2030a.WapPay) {
                        return m20954a(m20784a.get(i));
                    }
                }
            } catch (IOException e) {
                EnumC1999k m20903b = EnumC1999k.m20903b(EnumC1999k.NETWORK_ERROR.m20907a());
                C2000a.m20896a("net", e);
                m20951c();
                enumC1999k = m20903b;
            } catch (Throwable th) {
                C2000a.m20898a("biz", "H5AuthDataAnalysisError", th);
            }
            m20951c();
            enumC1999k = null;
            if (enumC1999k == null) {
                enumC1999k = EnumC1999k.m20903b(EnumC1999k.FAILED.m20907a());
            }
            return C1998j.m20914a(enumC1999k.m20907a(), enumC1999k.m20904b(), "");
        } finally {
            m20951c();
        }
    }

    /* renamed from: a */
    private String m20954a(C2031b c2031b) {
        String[] m20781c = c2031b.m20781c();
        Bundle bundle = new Bundle();
        bundle.putString("url", m20781c[0]);
        Intent intent = new Intent(this.f3530b, H5AuthActivity.class);
        intent.putExtras(bundle);
        this.f3530b.startActivity(intent);
        synchronized (f3529a) {
            try {
                f3529a.wait();
            } catch (InterruptedException unused) {
                return C1998j.m20910c();
            }
        }
        String m20915a = C1998j.m20915a();
        return TextUtils.isEmpty(m20915a) ? C1998j.m20910c() : m20915a;
    }
}
