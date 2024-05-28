package com.unionpay.p363b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.nfc.sdk.service.HwOpenPayTask;
import com.unionpay.UPQuerySEPayInfoCallback;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams;
import com.unionpay.utils.C10915b;
import com.unionpay.utils.C10923j;
import com.unionpay.utils.UPUtils;

/* renamed from: com.unionpay.b.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10744b {

    /* renamed from: a */
    private Context f20651a;

    /* renamed from: b */
    private UPQuerySEPayInfoCallback f20652b;

    /* renamed from: c */
    private UPTsmAddon f20653c;

    /* renamed from: f */
    private boolean f20656f;

    /* renamed from: h */
    private QueryVendorPayStatusRequestParams f20658h;

    /* renamed from: d */
    private String f20654d = "";

    /* renamed from: e */
    private String f20655e = "";

    /* renamed from: g */
    private boolean f20657g = false;

    /* renamed from: i */
    private final Handler.Callback f20659i = new C10745c(this);

    /* renamed from: j */
    private final Handler f20660j = new Handler(this.f20659i);

    /* renamed from: k */
    private final UPTsmAddon.UPTsmConnectionListener f20661k = new C10747e(this);

    public C10744b(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback) {
        this.f20656f = false;
        this.f20651a = context;
        this.f20652b = uPQuerySEPayInfoCallback;
        this.f20656f = true;
        if (this.f20656f) {
            System.loadLibrary("entryexpro");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m5955a(C10744b c10744b, int i, String str) {
        if (i != 4000) {
            return;
        }
        c10744b.m5950a(c10744b.f20654d, c10744b.f20655e, UPSEInfoResp.ERROR_NOT_SUPPORT, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m5954a(C10744b c10744b, Bundle bundle) {
        Context context;
        c10744b.f20654d = bundle.getString("vendorPayName");
        c10744b.f20655e = bundle.getString("vendorPayAliasType");
        int i = bundle.getInt("vendorPayStatus");
        String string = bundle.getString("errorDesc");
        int i2 = bundle.getInt("cardNumber", 0);
        if (!TextUtils.isEmpty(c10744b.f20655e) && (context = c10744b.f20651a) != null) {
            UPUtils.m5869a(context, c10744b.f20655e, "se_type");
        }
        switch (i) {
            case 0:
                if (i2 > 0) {
                    c10744b.m5951a(c10744b.f20654d, c10744b.f20655e, i2, bundle);
                    return;
                } else {
                    c10744b.m5950a(c10744b.f20654d, c10744b.f20655e, UPSEInfoResp.ERROR_NOT_READY, "card number 0");
                    return;
                }
            case 1:
                c10744b.m5950a(c10744b.f20654d, c10744b.f20655e, UPSEInfoResp.ERROR_NOT_READY, "not ready");
                return;
            case 2:
            case 3:
            case 4:
                c10744b.m5950a(c10744b.f20654d, c10744b.f20655e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
                return;
            default:
                c10744b.m5950a(c10744b.f20654d, c10744b.f20655e, UPSEInfoResp.ERROR_NOT_SUPPORT, string);
                return;
        }
    }

    /* renamed from: a */
    private void m5951a(String str, String str2, int i, Bundle bundle) {
        m5943d();
        UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback = this.f20652b;
        if (uPQuerySEPayInfoCallback != null) {
            uPQuerySEPayInfoCallback.onResult(str, str2, i, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m5950a(String str, String str2, String str3, String str4) {
        m5943d();
        UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback = this.f20652b;
        if (uPQuerySEPayInfoCallback != null) {
            uPQuerySEPayInfoCallback.onError(str, str2, str3, str4);
        }
    }

    /* renamed from: a */
    private boolean m5952a(String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.f20651a.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | Exception unused) {
        }
        if (packageInfo != null) {
            C10923j.m5830a("tsm-client", "tsm version code=" + packageInfo.versionCode);
            return packageInfo.versionCode >= 18;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ void m5947b(C10744b c10744b, Bundle bundle) {
        if (bundle != null) {
            c10744b.f20654d = "Huawei Pay";
            c10744b.f20655e = "04";
            if (!"0000".equals(bundle.getString("resultCode"))) {
                c10744b.m5950a(c10744b.f20654d, c10744b.f20655e, UPSEInfoResp.ERROR_NOT_READY, "not ready");
                return;
            }
            c10744b.m5951a(c10744b.f20654d, c10744b.f20655e, bundle.getInt("cardNumber"), bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static /* synthetic */ UPQuerySEPayInfoCallback m5945c(C10744b c10744b) {
        c10744b.f20652b = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m5946c() {
        String str;
        String str2;
        String str3;
        String str4;
        if (!m5952a("com.unionpay.tsmservice")) {
            if (C10915b.m5848d(this.f20651a, "com.unionpay.tsmservice")) {
                str = this.f20654d;
                str2 = this.f20655e;
                str3 = UPSEInfoResp.ERROR_NOT_SUPPORT;
                str4 = "Tsm service apk version is low";
            } else {
                str = this.f20654d;
                str2 = this.f20655e;
                str3 = UPSEInfoResp.ERROR_TSM_UNINSTALLED;
                str4 = "Tsm service apk is not installed";
            }
            m5950a(str, str2, str3, str4);
            return;
        }
        this.f20653c = UPTsmAddon.getInstance(this.f20651a);
        this.f20653c.addConnectionListener(this.f20661k);
        C10923j.m5828c("uppay-spay", "type se  bind service");
        UPTsmAddon uPTsmAddon = this.f20653c;
        if (uPTsmAddon != null && !uPTsmAddon.isConnected()) {
            C10923j.m5828c("uppay", "bind service");
            if (this.f20653c.bind()) {
                return;
            }
            m5950a(this.f20654d, this.f20655e, UPSEInfoResp.ERROR_NONE, "Tsm service bind fail");
            return;
        }
        UPTsmAddon uPTsmAddon2 = this.f20653c;
        if (uPTsmAddon2 == null || !uPTsmAddon2.isConnected()) {
            return;
        }
        C10923j.m5828c("uppay", "tsm service already connected");
        m5949b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static /* synthetic */ void m5944c(C10744b c10744b, Bundle bundle) {
        if (bundle != null) {
            c10744b.f20654d = "Huawei Pay";
            c10744b.f20655e = "04";
            String string = bundle.getString("errorCode");
            c10744b.m5950a(c10744b.f20654d, c10744b.f20655e, "0002".equals(string) ? UPSEInfoResp.ERROR_NOT_READY : UPSEInfoResp.ERROR_NOT_SUPPORT, bundle.getString("errorDesc"));
        }
    }

    /* renamed from: d */
    private void m5943d() {
        UPTsmAddon uPTsmAddon = this.f20653c;
        if (uPTsmAddon != null) {
            uPTsmAddon.removeConnectionListener(this.f20661k);
            this.f20653c.unbind();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static /* synthetic */ void m5941e(C10744b c10744b) {
        HwOpenPayTask hwOpenPayTask = new HwOpenPayTask(c10744b.f20651a);
        C10923j.m5828c("uppay", "queryHwPayStatus start");
        c10744b.f20660j.sendEmptyMessageDelayed(4003, 3000L);
        hwOpenPayTask.getUnionOnlinePayStatus(new C10748f(c10744b));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public static /* synthetic */ boolean m5939g(C10744b c10744b) {
        c10744b.f20657g = true;
        return true;
    }

    /* renamed from: a */
    public final int m5957a() {
        if (this.f20651a == null || this.f20652b == null) {
            return UPSEInfoResp.PARAM_ERROR;
        }
        this.f20657g = false;
        if (C10915b.m5854b()) {
            HwOpenPayTask hwOpenPayTask = new HwOpenPayTask(this.f20651a);
            C10923j.m5828c("uppay", "supportCapacity");
            this.f20660j.sendEmptyMessageDelayed(4005, 2000L);
            hwOpenPayTask.supportCapacity("UNIONONLINEPAY", new C10746d(this));
        } else {
            m5946c();
        }
        return UPSEInfoResp.SUCCESS;
    }

    /* renamed from: b */
    public final boolean m5949b() {
        try {
            C10923j.m5828c("uppay", "getVendorPayStatus()");
            if (this.f20658h == null) {
                this.f20658h = new QueryVendorPayStatusRequestParams();
            }
            if (this.f20653c.queryVendorPayStatus(this.f20658h, new BinderC10743a(this.f20660j)) == 0) {
                this.f20660j.sendMessageDelayed(Message.obtain(this.f20660j, 4, 4000, 0, ""), 5000L);
                return true;
            }
            C10923j.m5828c("uppay", "ret != 0");
            m5950a(this.f20654d, this.f20655e, UPSEInfoResp.ERROR_NOT_SUPPORT, "Tsm service apk version is low");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
