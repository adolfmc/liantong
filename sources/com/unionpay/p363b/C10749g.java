package com.unionpay.p363b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.p364mi.UPTsmAddon;
import com.unionpay.tsmservice.p364mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.utils.C10915b;
import com.unionpay.utils.C10923j;
import com.unionpay.utils.UPUtils;

/* renamed from: com.unionpay.b.g */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10749g {

    /* renamed from: a */
    private Context f20666a;

    /* renamed from: b */
    private UPQuerySEPayInfoCallback f20667b;

    /* renamed from: c */
    private UPTsmAddon f20668c;

    /* renamed from: f */
    private boolean f20671f;

    /* renamed from: g */
    private QueryVendorPayStatusRequestParams f20672g;

    /* renamed from: d */
    private String f20669d = "";

    /* renamed from: e */
    private String f20670e = "";

    /* renamed from: h */
    private final Handler.Callback f20673h = new C10750h(this);

    /* renamed from: i */
    private final Handler f20674i = new Handler(this.f20673h);

    /* renamed from: j */
    private final UPTsmAddon.UPTsmConnectionListener f20675j = new C10751i(this);

    public C10749g(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback) {
        this.f20671f = false;
        this.f20666a = context;
        this.f20667b = uPQuerySEPayInfoCallback;
        this.f20671f = true;
        if (this.f20671f) {
            System.loadLibrary("entryexpro");
            String m5870a = UPUtils.m5870a(this.f20666a, "mode");
            m5870a = m5870a == null ? "" : m5870a;
            try {
                Integer.decode(C10915b.m5847d(m5870a) ? m5870a : "02").intValue();
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m5934a(C10749g c10749g, int i, String str) {
        if (i != 4000) {
            return;
        }
        c10749g.m5930a(c10749g.f20669d, c10749g.f20670e, UPSEInfoResp.ERROR_NOT_SUPPORT, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m5933a(C10749g c10749g, Bundle bundle) {
        Context context;
        c10749g.f20669d = bundle.getString("vendorPayName");
        c10749g.f20670e = bundle.getString("vendorPayAliasType");
        int i = bundle.getInt("vendorPayStatus");
        String string = bundle.getString("errorDesc");
        int i2 = bundle.getInt("cardNumber", 0);
        if (!TextUtils.isEmpty(c10749g.f20670e) && (context = c10749g.f20666a) != null) {
            UPUtils.m5869a(context, c10749g.f20670e, "se_type");
        }
        switch (i) {
            case 0:
                if (i2 <= 0) {
                    c10749g.m5930a(c10749g.f20669d, c10749g.f20670e, UPSEInfoResp.ERROR_NOT_READY, "card number 0");
                    return;
                }
                String str = c10749g.f20669d;
                String str2 = c10749g.f20670e;
                c10749g.m5927c();
                UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback = c10749g.f20667b;
                if (uPQuerySEPayInfoCallback != null) {
                    uPQuerySEPayInfoCallback.onResult(str, str2, i2, bundle);
                    return;
                }
                return;
            case 1:
                c10749g.m5930a(c10749g.f20669d, c10749g.f20670e, UPSEInfoResp.ERROR_NOT_READY, "not ready");
                return;
            case 2:
            case 3:
            case 4:
                c10749g.m5930a(c10749g.f20669d, c10749g.f20670e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
                return;
            default:
                c10749g.m5930a(c10749g.f20669d, c10749g.f20670e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m5930a(String str, String str2, String str3, String str4) {
        m5927c();
        UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback = this.f20667b;
        if (uPQuerySEPayInfoCallback != null) {
            uPQuerySEPayInfoCallback.onError(str, str2, str3, str4);
        }
    }

    /* renamed from: a */
    private boolean m5931a(String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.f20666a.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | Exception unused) {
        }
        if (packageInfo != null) {
            C10923j.m5830a("tsm-client", "tsm version code=" + packageInfo.versionCode);
            return packageInfo.versionCode >= 8;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ UPQuerySEPayInfoCallback m5928b(C10749g c10749g) {
        c10749g.f20667b = null;
        return null;
    }

    /* renamed from: c */
    private void m5927c() {
        UPTsmAddon uPTsmAddon = this.f20668c;
        if (uPTsmAddon != null) {
            uPTsmAddon.removeConnectionListener(this.f20675j);
            this.f20668c.unbind();
        }
    }

    /* renamed from: a */
    public final int m5936a() {
        String str;
        String str2;
        String str3;
        String str4;
        if (this.f20666a == null || this.f20667b == null) {
            return UPSEInfoResp.PARAM_ERROR;
        }
        if (m5931a("com.unionpay.tsmservice.mi")) {
            this.f20668c = UPTsmAddon.getInstance(this.f20666a);
            this.f20668c.addConnectionListener(this.f20675j);
            C10923j.m5828c("uppay-spay", "type se  bind service");
            UPTsmAddon uPTsmAddon = this.f20668c;
            if (uPTsmAddon == null || uPTsmAddon.isConnected()) {
                UPTsmAddon uPTsmAddon2 = this.f20668c;
                if (uPTsmAddon2 != null && uPTsmAddon2.isConnected()) {
                    C10923j.m5828c("uppay", "tsm service already connected");
                    m5929b();
                }
            } else {
                C10923j.m5828c("uppay", "bind service");
                if (!this.f20668c.bind()) {
                    str = this.f20669d;
                    str2 = this.f20670e;
                    str3 = UPSEInfoResp.ERROR_NONE;
                    str4 = "Tsm service bind fail";
                }
            }
            return UPSEInfoResp.SUCCESS;
        } else if (C10915b.m5848d(this.f20666a, "com.unionpay.tsmservice.mi")) {
            str = this.f20669d;
            str2 = this.f20670e;
            str3 = UPSEInfoResp.ERROR_NOT_SUPPORT;
            str4 = "Mi Tsm service apk version is low";
        } else {
            str = this.f20669d;
            str2 = this.f20670e;
            str3 = UPSEInfoResp.ERROR_TSM_UNINSTALLED;
            str4 = "Mi Tsm service apk is not installed";
        }
        m5930a(str, str2, str3, str4);
        return UPSEInfoResp.SUCCESS;
    }

    /* renamed from: b */
    public final boolean m5929b() {
        try {
            C10923j.m5828c("uppay", "getVendorPayStatus()");
            if (this.f20672g == null) {
                this.f20672g = new QueryVendorPayStatusRequestParams();
            }
            if (this.f20668c.queryVendorPayStatus(this.f20672g, new BinderC10752j(this.f20674i)) == 0) {
                this.f20674i.sendMessageDelayed(Message.obtain(this.f20674i, 4, 4000, 0, ""), 5000L);
                return true;
            }
            C10923j.m5828c("uppay", "ret != 0");
            m5930a(this.f20669d, this.f20670e, UPSEInfoResp.ERROR_NOT_SUPPORT, "Mi Tsm service apk version is low");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
