package com.vivo.push.p368b;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.BundleWapper;
import com.vivo.push.PushCommand;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushPackageUtils;

/* renamed from: com.vivo.push.b.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BaseAppCommand extends PushCommand {

    /* renamed from: a */
    private String f20874a;

    /* renamed from: b */
    private String f20875b;

    /* renamed from: c */
    private long f20876c;

    /* renamed from: d */
    private int f20877d;

    /* renamed from: e */
    private int f20878e;

    /* renamed from: f */
    private String f20879f;

    /* renamed from: g */
    private String f20880g;

    /* renamed from: h */
    private String f20881h;

    @Override // com.vivo.push.PushCommand
    public String toString() {
        return "BaseAppCommand";
    }

    public BaseAppCommand(int i, String str) {
        super(i);
        this.f20876c = -1L;
        this.f20877d = -1;
        this.f20874a = null;
        this.f20875b = str;
    }

    /* renamed from: d */
    public final int m5806d() {
        return this.f20878e;
    }

    /* renamed from: b */
    public final void m5809b(int i) {
        this.f20878e = i;
    }

    /* renamed from: e */
    public final void m5804e() {
        this.f20879f = null;
    }

    /* renamed from: a */
    public final int m5810a(Context context) {
        if (this.f20877d == -1) {
            String str = this.f20875b;
            if (TextUtils.isEmpty(str)) {
                LogUtil.m5354a("BaseAppCommand", "pkg name is null");
                str = m5330a();
                if (TextUtils.isEmpty(str)) {
                    LogUtil.m5354a("BaseAppCommand", "src is null");
                    return -1;
                }
            }
            this.f20877d = PushPackageUtils.m5466b(context, str);
            if (!TextUtils.isEmpty(this.f20879f)) {
                this.f20877d = 2;
            }
        }
        return this.f20877d;
    }

    /* renamed from: f */
    public final String m5803f() {
        return this.f20874a;
    }

    /* renamed from: b */
    public final void m5808b(String str) {
        this.f20874a = str;
    }

    /* renamed from: c */
    public final void m5807c(String str) {
        this.f20881h = str;
    }

    /* renamed from: d */
    public final void m5805d(String str) {
        this.f20880g = str;
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public void mo5322c(BundleWapper bundleWapper) {
        bundleWapper.m5730a("req_id", this.f20874a);
        bundleWapper.m5730a("package_name", this.f20875b);
        bundleWapper.m5732a("sdk_version", 341L);
        bundleWapper.m5733a("PUSH_APP_STATUS", this.f20877d);
        if (!TextUtils.isEmpty(this.f20879f)) {
            bundleWapper.m5730a("BaseAppCommand.EXTRA__HYBRIDVERSION", this.f20879f);
        }
        bundleWapper.m5730a("BaseAppCommand.EXTRA_APPID", this.f20881h);
        bundleWapper.m5730a("BaseAppCommand.EXTRA_APPKEY", this.f20880g);
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: d */
    public void mo5321d(BundleWapper bundleWapper) {
        this.f20874a = bundleWapper.m5734a("req_id");
        this.f20875b = bundleWapper.m5734a("package_name");
        this.f20876c = bundleWapper.m5723b("sdk_version", 0L);
        this.f20877d = bundleWapper.m5724b("PUSH_APP_STATUS", 0);
        this.f20879f = bundleWapper.m5734a("BaseAppCommand.EXTRA__HYBRIDVERSION");
        this.f20881h = bundleWapper.m5734a("BaseAppCommand.EXTRA_APPID");
        this.f20880g = bundleWapper.m5734a("BaseAppCommand.EXTRA_APPKEY");
    }
}
