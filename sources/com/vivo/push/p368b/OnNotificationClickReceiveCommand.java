package com.vivo.push.p368b;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.vivo.push.BundleWapper;
import com.vivo.push.PushCommand;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.util.MessageConvertUtil;

/* renamed from: com.vivo.push.b.p */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnNotificationClickReceiveCommand extends PushCommand {

    /* renamed from: a */
    private String f20899a;

    /* renamed from: b */
    private String f20900b;

    /* renamed from: c */
    private byte[] f20901c;

    /* renamed from: d */
    private long f20902d;

    /* renamed from: e */
    private InsideNotificationItem f20903e;

    /* renamed from: f */
    private String f20904f;

    /* renamed from: g */
    private String f20905g;

    /* renamed from: h */
    private Uri f20906h;

    /* renamed from: i */
    private String f20907i;

    /* renamed from: j */
    private Bundle f20908j;

    @Override // com.vivo.push.PushCommand
    public final String toString() {
        return "OnNotificationClickCommand";
    }

    public OnNotificationClickReceiveCommand(String str, long j, InsideNotificationItem insideNotificationItem) {
        super(5);
        this.f20899a = str;
        this.f20902d = j;
        this.f20903e = insideNotificationItem;
    }

    public OnNotificationClickReceiveCommand() {
        super(5);
    }

    /* renamed from: d */
    public final String m5782d() {
        return this.f20899a;
    }

    /* renamed from: e */
    public final long m5780e() {
        return this.f20902d;
    }

    /* renamed from: f */
    public final InsideNotificationItem m5778f() {
        return this.f20903e;
    }

    /* renamed from: g */
    public final String m5777g() {
        return this.f20904f;
    }

    /* renamed from: b */
    public final void m5784b(String str) {
        this.f20904f = str;
    }

    /* renamed from: h */
    public final String m5776h() {
        return this.f20905g;
    }

    /* renamed from: c */
    public final void m5783c(String str) {
        this.f20905g = str;
    }

    /* renamed from: i */
    public final String m5775i() {
        return this.f20907i;
    }

    /* renamed from: d */
    public final void m5781d(String str) {
        this.f20907i = str;
    }

    /* renamed from: j */
    public final Uri m5774j() {
        return this.f20906h;
    }

    /* renamed from: a */
    public final void m5785a(Uri uri) {
        this.f20906h = uri;
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        bundleWapper.m5730a("package_name", this.f20899a);
        bundleWapper.m5732a("notify_id", this.f20902d);
        bundleWapper.m5730a("notification_v1", MessageConvertUtil.m5337b(this.f20903e));
        bundleWapper.m5730a("open_pkg_name", this.f20900b);
        bundleWapper.m5727a("open_pkg_name_encode", this.f20901c);
        bundleWapper.m5730a("notify_action", this.f20904f);
        bundleWapper.m5730a("notify_componet_pkg", this.f20905g);
        bundleWapper.m5730a("notify_componet_class_name", this.f20907i);
        Uri uri = this.f20906h;
        if (uri != null) {
            bundleWapper.m5730a("notify_uri_data", uri.toString());
        }
    }

    /* renamed from: e */
    private static Uri m5779e(String str) {
        try {
            return Uri.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        this.f20899a = bundleWapper.m5734a("package_name");
        this.f20902d = bundleWapper.m5723b("notify_id", -1L);
        this.f20900b = bundleWapper.m5734a("open_pkg_name");
        this.f20901c = bundleWapper.m5725b("open_pkg_name_encode");
        this.f20904f = bundleWapper.m5734a("notify_action");
        this.f20905g = bundleWapper.m5734a("notify_componet_pkg");
        this.f20907i = bundleWapper.m5734a("notify_componet_class_name");
        String m5734a = bundleWapper.m5734a("notification_v1");
        if (!TextUtils.isEmpty(m5734a)) {
            this.f20903e = MessageConvertUtil.m5338a(m5734a);
        }
        InsideNotificationItem insideNotificationItem = this.f20903e;
        if (insideNotificationItem != null) {
            insideNotificationItem.setMsgId(this.f20902d);
        }
        String m5734a2 = bundleWapper.m5734a("notify_uri_data");
        if (!TextUtils.isEmpty(m5734a2)) {
            this.f20906h = m5779e(m5734a2);
        }
        this.f20908j = bundleWapper.m5726b();
    }

    /* renamed from: k */
    public final Bundle m5773k() {
        Bundle bundle = this.f20908j;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        try {
            bundle2.remove("command_type");
            bundle2.remove("security_avoid_pull");
            bundle2.remove("security_avoid_pull_rsa");
            bundle2.remove("security_avoid_rsa_public_key");
            bundle2.remove("security_avoid_rsa_public_key");
            bundle2.remove("notify_action");
            bundle2.remove("notify_componet_pkg");
            bundle2.remove("notify_componet_class_name");
            bundle2.remove("notification_v1");
            bundle2.remove("command");
            bundle2.remove("package_name");
            bundle2.remove("method");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bundle2;
    }
}
