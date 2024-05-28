package com.huawei.hms.opendevice;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.aaid.encrypt.PushEncrypter;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.support.log.HMSLog;
import java.util.Map;

/* renamed from: com.huawei.hms.opendevice.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PushClientSp extends PushPreferences {

    /* renamed from: c */
    private static final String f11550c = "j";

    /* renamed from: b */
    private Context f11551b;

    private PushClientSp(Context context) {
        super(context, "push_client_self_info");
        this.f11551b = context;
    }

    /* renamed from: a */
    public static PushClientSp m14368a(Context context) {
        return new PushClientSp(context);
    }

    /* renamed from: b */
    public String m14365b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return m14367a("token_info_v2");
            }
            return m14367a(str);
        } catch (Exception e) {
            String str2 = f11550c;
            HMSLog.m14112e(str2, "getSecureData" + e.getMessage());
            return "";
        }
    }

    /* renamed from: c */
    public boolean m14363c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return removeKey("token_info_v2");
            }
            return removeKey(str);
        } catch (Exception e) {
            String str2 = f11550c;
            HMSLog.m14112e(str2, "removeToken" + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public String m14367a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return PushEncrypter.decrypter(this.f11551b, getString(str));
        } catch (Exception e) {
            String str2 = f11550c;
            HMSLog.m14112e(str2, "getSecureData" + e.getMessage());
            return "";
        }
    }

    /* renamed from: b */
    public boolean m14364b(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return m14366a("token_info_v2", str2);
            }
            return m14366a(str, str2);
        } catch (Exception e) {
            String str3 = f11550c;
            HMSLog.m14112e(str3, "saveSecureData" + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public boolean m14366a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return saveString(str, PushEncrypter.encrypter(this.f11551b, str2));
        } catch (Exception e) {
            String str3 = f11550c;
            HMSLog.m14112e(str3, "saveSecureData" + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public void m14369a() {
        Map<String, ?> all = getAll();
        if (all.isEmpty() || all.keySet().isEmpty()) {
            return;
        }
        for (String str : all.keySet()) {
            if (!"push_kit_auto_init_enabled".equals(str) && !"_proxy_init".equals(str)) {
                removeKey(str);
            }
        }
    }
}
