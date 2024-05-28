package com.bytedance.applog;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.r */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3677r extends AbstractC3692t {

    /* renamed from: e */
    public Context f8784e;

    public C3677r(Context context) {
        super(true, false);
        this.f8784e = context;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        try {
            Bundle bundle = this.f8784e.getPackageManager().getApplicationInfo(this.f8784e.getPackageName(), 128).metaData;
            if (bundle == null || TextUtils.isEmpty("UMENG_APPKEY")) {
                return true;
            }
            jSONObject.put("appkey", bundle.getString("UMENG_APPKEY"));
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }
}
