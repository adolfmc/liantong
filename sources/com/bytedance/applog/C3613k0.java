package com.bytedance.applog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.k0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3613k0 extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8533e;

    public C3613k0(Context context) {
        super(true, false);
        this.f8533e = context;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        PackageInfo packageInfo;
        Signature[] signatureArr;
        Signature signature;
        String str = null;
        try {
            packageInfo = this.f8533e.getPackageManager().getPackageInfo(this.f8533e.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            packageInfo = null;
        }
        if (packageInfo != null && (signatureArr = packageInfo.signatures) != null && signatureArr.length > 0 && (signature = signatureArr[0]) != null) {
            str = C3609j2.m17256a(signature.toByteArray());
        }
        if (str != null) {
            jSONObject.put("sig_hash", str);
            return true;
        }
        return true;
    }
}
