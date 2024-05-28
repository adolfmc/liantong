package com.vivo.push;

import android.text.TextUtils;
import com.vivo.push.p369c.CoreConfigManager;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.p377b.IPushRely;
import com.vivo.push.util.LogUtil;

/* compiled from: SubscribeImpl.java */
/* renamed from: com.vivo.push.z */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10992z implements ISubscribe {

    /* renamed from: a */
    private RequestFrequencyControl f21255a = new RequestFrequencyControl();

    /* renamed from: b */
    private RequestFrequencyControl f21256b = new RequestFrequencyControl();

    /* renamed from: c */
    private CoreConfigManager f21257c;

    /* renamed from: d */
    private volatile String f21258d;

    /* renamed from: e */
    private IPushRely f21259e;

    public C10992z(CoreConfigManager coreConfigManager, IPushRely iPushRely) {
        this.f21257c = coreConfigManager;
        this.f21259e = iPushRely;
    }

    @Override // com.vivo.push.ISubscribe
    /* renamed from: a */
    public final void mo5316a(IPushActionListener iPushActionListener, String str, String str2) {
        if (!this.f21257c.m5755c() && iPushActionListener != null) {
            iPushActionListener.onStateChanged(8012);
        } else {
            PushClientManager.m5648a().m5628b(iPushActionListener, str, str2);
        }
    }

    @Override // com.vivo.push.ISubscribe
    /* renamed from: a */
    public final int mo5317a() {
        return m5310c();
    }

    @Override // com.vivo.push.ISubscribe
    /* renamed from: b */
    public final String mo5312b() {
        if (!TextUtils.isEmpty(this.f21258d)) {
            return this.f21258d;
        }
        String m5308d = m5308d();
        if (TextUtils.isEmpty(m5308d)) {
            m5308d = this.f21259e.mo5530f();
            PushClientThread.m5480c(new SubscribeImpl(this, m5308d));
        }
        this.f21258d = m5308d;
        LogUtil.m5341d("SubscribeImpl", "getRegidByCoreSdk code = ".concat(String.valueOf(m5308d)));
        return m5308d;
    }

    @Override // com.vivo.push.ISubscribe
    /* renamed from: a */
    public final void mo5313a(String str, String str2, String str3) {
        m5307d(str);
        this.f21259e.mo5539a(str2);
        this.f21259e.mo5537b(str3);
    }

    @Override // com.vivo.push.ISubscribe
    /* renamed from: a */
    public final void mo5314a(String str) {
        m5307d(str);
        this.f21259e.mo5534d();
        this.f21259e.mo5538b();
    }

    @Override // com.vivo.push.ISubscribe
    /* renamed from: b */
    public final void mo5311b(String str) {
        m5307d(str);
        PushClientManager.m5648a().m5617e();
        this.f21259e.mo5528h();
        this.f21259e.mo5534d();
        this.f21259e.mo5538b();
    }

    @Override // com.vivo.push.ISubscribe
    /* renamed from: c */
    public final void mo5309c(String str) {
        m5307d(str);
    }

    /* renamed from: d */
    private void m5307d(String str) {
        this.f21258d = str;
        this.f21259e.mo5535c(this.f21258d);
    }

    /* renamed from: c */
    private int m5310c() {
        if (this.f21257c.m5754d()) {
            if (this.f21256b.m5318a()) {
                LogUtil.m5341d("SubscribeImpl", "isAppSubscribe 两秒内重复调用  ");
                return 1002;
            }
            int i = 1;
            try {
                String mo5508a = new ClientSdkQueryParemeterDS(1, PushClientController.m5593a().m5591b().getPackageName(), "", "", PushClientController.m5593a().m5588e().mo5530f()).mo5508a();
                LogUtil.m5341d("SubscribeImpl", "isAppSubscribe parameter = ".concat(String.valueOf(mo5508a)));
                String m5757a = CoreConfigManager.m5757a(PushClientController.m5593a().m5591b(), mo5508a);
                LogUtil.m5341d("SubscribeImpl", "isAppSubscribe isSubscribe = ".concat(String.valueOf(m5757a)));
                if (!TextUtils.isEmpty(m5757a)) {
                    i = 1 ^ (Boolean.parseBoolean(ClientSdkQueryResultDS.f21014a.mo5507a(m5757a).m5665b()) ? 1 : 0);
                }
            } catch (Exception e) {
                LogUtil.m5353a("SubscribeImpl", "isAppSubscribe", e);
            }
            LogUtil.m5341d("SubscribeImpl", "isAppSubscribe code = ".concat(String.valueOf(i)));
            return i;
        }
        return 8013;
    }

    /* renamed from: d */
    private String m5308d() {
        String str = "";
        if (!this.f21257c.m5754d()) {
            LogUtil.m5341d("SubscribeImpl", "getRegidByCoreSdk 系统不支持查询regid  ");
            return "";
        } else if (this.f21255a.m5318a()) {
            LogUtil.m5341d("SubscribeImpl", "getRegidByCoreSdk 两秒内重复调用  ");
            return "";
        } else {
            try {
                String mo5508a = new ClientSdkQueryParemeterDS(2, PushClientController.m5593a().m5591b().getPackageName(), "", "", PushClientController.m5593a().m5588e().mo5530f()).mo5508a();
                LogUtil.m5341d("SubscribeImpl", "getRegidByCoreSdk parameter = ".concat(String.valueOf(mo5508a)));
                String m5757a = CoreConfigManager.m5757a(PushClientController.m5593a().m5591b(), mo5508a);
                LogUtil.m5341d("SubscribeImpl", "getRegidByCoreSdk isSubscribe = ".concat(String.valueOf(m5757a)));
                if (!TextUtils.isEmpty(m5757a)) {
                    str = ClientSdkQueryResultDS.f21014a.mo5507a(m5757a).m5665b();
                }
            } catch (Exception e) {
                LogUtil.m5353a("SubscribeImpl", "getRegidByCoreSdk", e);
            }
            LogUtil.m5341d("SubscribeImpl", "getRegidByCoreSdk code = ".concat(String.valueOf(str)));
            return str;
        }
    }
}
