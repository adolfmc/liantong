package com.vivo.push;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.vivo.push.util.LogUtil;

/* renamed from: com.vivo.push.v */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class PushCommand {

    /* renamed from: a */
    private int f21246a;

    /* renamed from: b */
    private String f21247b;

    /* renamed from: c */
    protected abstract void mo5322c(BundleWapper bundleWapper);

    /* renamed from: c */
    public boolean mo5323c() {
        return false;
    }

    /* renamed from: d */
    protected abstract void mo5321d(BundleWapper bundleWapper);

    public PushCommand(int i) {
        this.f21246a = -1;
        if (i < 0) {
            throw new IllegalArgumentException("PushCommand: the value of command must > 0.");
        }
        this.f21246a = i;
    }

    /* renamed from: a */
    public final String m5330a() {
        return this.f21247b;
    }

    /* renamed from: a */
    public final void m5327a(String str) {
        this.f21247b = str;
    }

    /* renamed from: b */
    public final int m5326b() {
        return this.f21246a;
    }

    /* renamed from: a */
    public final void m5329a(Intent intent) {
        BundleWapper m5735a = BundleWapper.m5735a(intent);
        if (m5735a == null) {
            LogUtil.m5346b("PushCommand", "bundleWapper is null");
            return;
        }
        m5328a(m5735a);
        Bundle m5726b = m5735a.m5726b();
        if (m5726b != null) {
            intent.putExtras(m5726b);
        }
    }

    /* renamed from: a */
    public final void m5328a(BundleWapper bundleWapper) {
        String m5319a = PushConstants.m5319a(this.f21246a);
        if (m5319a == null) {
            m5319a = "";
        }
        bundleWapper.m5730a("method", m5319a);
        m5320e(bundleWapper);
    }

    /* renamed from: b */
    public final void m5325b(Intent intent) {
        BundleWapper m5735a = BundleWapper.m5735a(intent);
        if (m5735a == null) {
            LogUtil.m5346b("PushCommand", "bundleWapper is null");
            return;
        }
        m5735a.m5733a("method", this.f21246a);
        m5320e(m5735a);
        Bundle m5726b = m5735a.m5726b();
        if (m5726b != null) {
            intent.putExtras(m5726b);
        }
    }

    /* renamed from: e */
    private void m5320e(BundleWapper bundleWapper) {
        bundleWapper.m5733a("command", this.f21246a);
        bundleWapper.m5730a("client_pkgname", this.f21247b);
        mo5322c(bundleWapper);
    }

    /* renamed from: b */
    public final void m5324b(BundleWapper bundleWapper) {
        String m5736a = bundleWapper.m5736a();
        if (!TextUtils.isEmpty(m5736a)) {
            this.f21247b = m5736a;
        } else {
            this.f21247b = bundleWapper.m5734a("client_pkgname");
        }
        mo5321d(bundleWapper);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
