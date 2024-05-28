package com.vivo.push.restructure.p377b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.vivo.push.PushConfig;
import com.vivo.push.model.PushPackageInfo;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.ConcurrentUtils;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushClientSdkAppSp;
import com.vivo.push.util.PushPackageUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.vivo.push.restructure.b.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class PushRelyImpl implements IPushRely {

    /* renamed from: a */
    private static Map<String, PushAppParemBean> f21113a = new ConcurrentHashMap();

    /* renamed from: b */
    private String f21114b;

    /* renamed from: c */
    private PushClientSdkAppSp f21115c;

    /* renamed from: d */
    private volatile PushConfig f21116d;

    public PushRelyImpl(PushClientSdkAppSp pushClientSdkAppSp) {
        this.f21115c = pushClientSdkAppSp;
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: a */
    public final String mo5542a(Context context, String str) {
        if (!TextUtils.isEmpty(this.f21114b)) {
            return this.f21114b;
        }
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.m5354a("PushRelyImpl", "getReceiverClassName() params error, context = " + context + ", action = " + str);
            return "";
        }
        String packageName = context.getPackageName();
        this.f21114b = m5541a(context, packageName, str);
        if (TextUtils.isEmpty(this.f21114b)) {
            LogUtil.m5341d("PushRelyImpl", " reflectReceiver error: receiver for: " + str + " not found, package: " + packageName);
        }
        return this.f21114b;
    }

    /* renamed from: a */
    private static String m5541a(Context context, String str, String str2) {
        List<ResolveInfo> queryBroadcastReceivers;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 64)) == null || queryBroadcastReceivers.size() <= 0) {
                return null;
            }
            return queryBroadcastReceivers.get(0).activityInfo.name;
        } catch (Exception e) {
            LogUtil.m5354a("PushRelyImpl", "error  " + e.getMessage());
            return null;
        }
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: a */
    public final String mo5543a() {
        PushAppParemBean pushAppParemBean = f21113a.get(PushClientController.m5593a().m5591b().getPackageName());
        if (pushAppParemBean != null) {
            String m5547a = pushAppParemBean.m5547a();
            if (!TextUtils.isEmpty(m5547a)) {
                return m5547a;
            }
        }
        String m5332b = this.f21115c.m5332b();
        if (!TextUtils.isEmpty(m5332b)) {
            if (pushAppParemBean == null) {
                pushAppParemBean = new PushAppParemBean();
            }
            pushAppParemBean.m5546a(m5332b);
            f21113a.put(PushClientController.m5593a().m5591b().getPackageName(), pushAppParemBean);
        }
        return m5332b;
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: b */
    public final void mo5538b() {
        mo5539a("");
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: c */
    public final String mo5536c() {
        PushAppParemBean pushAppParemBean = f21113a.get(PushClientController.m5593a().m5591b().getPackageName());
        if (pushAppParemBean != null) {
            String m5545b = pushAppParemBean.m5545b();
            if (!TextUtils.isEmpty(m5545b)) {
                return m5545b;
            }
        }
        String m5331c = this.f21115c.m5331c();
        if (!TextUtils.isEmpty(m5331c)) {
            if (pushAppParemBean == null) {
                pushAppParemBean = new PushAppParemBean();
            }
            pushAppParemBean.m5544b(m5331c);
            f21113a.put(PushClientController.m5593a().m5591b().getPackageName(), pushAppParemBean);
        }
        return m5331c;
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: a */
    public final void mo5539a(String str) {
        this.f21115c.m5417a("APP_APPID", str);
        PushAppParemBean pushAppParemBean = f21113a.get(PushClientController.m5593a().m5591b().getPackageName());
        if (pushAppParemBean == null) {
            pushAppParemBean = new PushAppParemBean();
        }
        pushAppParemBean.m5546a(str);
        f21113a.put(PushClientController.m5593a().m5591b().getPackageName(), pushAppParemBean);
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: b */
    public final void mo5537b(String str) {
        this.f21115c.m5417a("APP_APIKEY", str);
        PushAppParemBean pushAppParemBean = f21113a.get(PushClientController.m5593a().m5591b().getPackageName());
        if (pushAppParemBean == null) {
            pushAppParemBean = new PushAppParemBean();
        }
        pushAppParemBean.m5544b(str);
        f21113a.put(PushClientController.m5593a().m5591b().getPackageName(), pushAppParemBean);
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: d */
    public final void mo5534d() {
        mo5537b("");
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: e */
    public final void mo5532e() {
        this.f21115c.m5423a();
        f21113a.clear();
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: f */
    public final String mo5530f() {
        return this.f21115c.m5411b("APP_TOKEN", (String) null);
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: c */
    public final void mo5535c(String str) {
        this.f21115c.m5417a("APP_TOKEN", str);
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: g */
    public final String mo5529g() {
        return this.f21115c.m5411b("APP_TAGS", (String) null);
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: d */
    public final void mo5533d(String str) {
        this.f21115c.m5417a("APP_TAGS", str);
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: h */
    public final void mo5528h() {
        this.f21115c.m5420a("APP_TAGS");
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: i */
    public final String mo5527i() {
        return this.f21115c.m5411b("APP_ALIAS", (String) null);
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: e */
    public final void mo5531e(String str) {
        this.f21115c.m5417a("APP_ALIAS", str);
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: j */
    public final void mo5526j() {
        this.f21115c.m5420a("APP_ALIAS");
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: k */
    public final String mo5525k() {
        PushPackageInfo m5470a = PushPackageUtils.m5470a(PushClientController.m5593a().m5591b(), PushClientController.m5593a().m5587f());
        if (m5470a == null || m5470a.m5595c()) {
            return null;
        }
        return m5470a.m5602a();
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: l */
    public final PushConfig mo5524l() {
        if (this.f21116d != null) {
            return this.f21116d;
        }
        int b = this.f21115c.m5413b("PUSH_CLIENT_CONFIG", 1) & 1;
        return new PushConfig.Builder().agreePrivacyStatement(b != 0).openMultiUserMode(b != 0).build();
    }

    @Override // com.vivo.push.restructure.p377b.IPushRely
    /* renamed from: a */
    public final void mo5540a(PushConfig pushConfig) {
        if (pushConfig == null) {
            return;
        }
        this.f21116d = null;
        Context m5591b = PushClientController.m5593a().m5591b();
        this.f21115c.m5419a("PUSH_CLIENT_CONFIG", pushConfig == null ? 1 : pushConfig.isAgreePrivacyStatement() | pushConfig.isOpenMultiUser());
        ConcurrentUtils.m5404a().execute(new RunnableC10972e(this, m5591b, pushConfig));
        this.f21116d = pushConfig;
    }
}
