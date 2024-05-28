package com.megvii.lv5;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;
import com.huawei.hms.framework.common.hianalytics.HianalyticsBaseData;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5418e {

    /* renamed from: a */
    public Context f12537a;

    public C5418e(Context context) {
        this.f12537a = context;
    }

    /* renamed from: a */
    public final String m13559a() {
        try {
            return this.f12537a.getPackageManager().getPackageInfo(this.f12537a.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public Map<String, Object> m13558a(String str) {
        HashMap hashMap = new HashMap();
        Context context = this.f12537a;
        String str2 = (String) C5527o2.m13252a(context, "megvii_liveness_uuid", "");
        if ("".equals(str2)) {
            str2 = Base64.encodeToString(UUID.randomUUID().toString().getBytes(), 0);
            C5527o2.m13235b(context, "megvii_liveness_uuid", str2);
        }
        hashMap.put("zid", str2.replaceAll("\r|\n| ", ""));
        hashMap.put("user_brand", Build.BRAND);
        hashMap.put("user_model", Build.MODEL);
        hashMap.put("user_os", "Android_" + Build.VERSION.RELEASE);
        hashMap.put("sdk_version", "MegLiveStill 5.6.4A");
        hashMap.put(HianalyticsBaseData.SDK_NAME, "MegLiveStill 5.6.4A");
        hashMap.put("sdk_type", "MegLiveStill 5.6.4A");
        hashMap.put("log_id", 1);
        hashMap.put("sdk_language", (String) C5527o2.m13252a(this.f12537a, "megvii_liveness_language", ""));
        ((Integer) C5527o2.m13252a(this.f12537a, "megvii_liveness_platform", (Object) 1)).intValue();
        hashMap.put("platform", "faceid");
        hashMap.put("host_app", m13559a());
        hashMap.put("host_app_version", m13556c());
        hashMap.put("biz_token", str);
        return hashMap;
    }

    /* renamed from: b */
    public String m13557b() {
        return "MegVii-SDK/MegLiveStill 5.6.4A/" + m13559a() + "/" + m13556c() + "/" + Locale.getDefault().getLanguage();
    }

    /* renamed from: c */
    public final String m13556c() {
        try {
            return this.f12537a.getPackageManager().getPackageInfo(this.f12537a.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
