package com.huawei.hms.hatool;

import android.text.TextUtils;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesGcmKS;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.w0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5033w0 {
    /* renamed from: a */
    public static void m14443a(String str, String str2) {
        C5009n0 c5009n0;
        String replace = "{url}/getPublicKey?keytype=2".replace("{url}", AbstractC4966a1.m14812f(str, str2));
        String m14532f = AbstractC5020q0.m14532f();
        HashMap hashMap = new HashMap();
        hashMap.put("App-Id", m14532f);
        try {
            c5009n0 = AbstractC5031w.m14448a(replace, new byte[0], hashMap);
        } catch (Exception e) {
            C5029v.m14452e("GetPublicKey", "get pubKey response Exception :" + e.getMessage());
            c5009n0 = null;
        }
        if (c5009n0 == null) {
            C5029v.m14452e("GetPublicKey", "get pubKey response is null");
        } else if (c5009n0.m14588b() == 200) {
            if (TextUtils.isEmpty(c5009n0.m14589a())) {
                return;
            }
            m14441c(c5009n0.m14589a(), str2);
        } else {
            C5029v.m14452e("GetPublicKey", "get pubKey fail HttpCode :" + c5009n0.m14588b());
        }
    }

    /* renamed from: a */
    public static boolean m14444a() {
        String m14542a = AbstractC5020q0.m14542a();
        if (TextUtils.isEmpty(m14542a)) {
            m14542a = C4975d.m14767a(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_time_interval", "");
            AbstractC5020q0.m14531f(m14542a);
        }
        String m14522m = AbstractC5020q0.m14522m();
        if (TextUtils.isEmpty(m14522m)) {
            m14522m = C4975d.m14767a(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_time_last", "");
            AbstractC5020q0.m14537c(m14522m);
        }
        if (TextUtils.isEmpty(m14542a) || TextUtils.isEmpty(m14522m)) {
            return true;
        }
        try {
            return System.currentTimeMillis() - Long.parseLong(m14522m) > ((long) Integer.parseInt(m14542a));
        } catch (NumberFormatException e) {
            C5029v.m14452e("GetPublicKey", "checkCachePubKey NumberFormatException :" + e.getMessage());
            return true;
        }
    }

    /* renamed from: b */
    public static String m14442b(String str, String str2) {
        String m14520o;
        String m14538c = AbstractC5020q0.m14538c();
        if (TextUtils.isEmpty(m14538c)) {
            m14538c = C4975d.m14767a(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_version", "");
            AbstractC5020q0.m14529g(m14538c);
        }
        if ("maint".equals(str2)) {
            m14520o = AbstractC5020q0.m14521n();
            if (TextUtils.isEmpty(m14520o)) {
                m14520o = AesGcmKS.decrypt("HiAnalytics_Sdk_Public_Sp_Key", C4975d.m14767a(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_maint", ""));
                AbstractC5020q0.m14535d(m14520o);
            }
        } else {
            m14520o = AbstractC5020q0.m14520o();
            if (TextUtils.isEmpty(m14520o)) {
                m14520o = AesGcmKS.decrypt("HiAnalytics_Sdk_Public_Sp_Key", C4975d.m14767a(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_oper", ""));
                AbstractC5020q0.m14533e(m14520o);
            }
        }
        if (TextUtils.isEmpty(m14520o) || TextUtils.isEmpty(m14538c) || m14444a()) {
            C4968b0.m14796a().m14795a(new C4996j1(str, str2));
            return null;
        }
        return m14520o;
    }

    /* renamed from: c */
    private static void m14441c(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("publicKey");
            String optString2 = jSONObject.optString("publicKeyOM");
            String optString3 = jSONObject.optString("pubkey_version");
            String str3 = System.currentTimeMillis() + "";
            String optString4 = jSONObject.optString("timeInterval");
            C4975d.m14763b(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_oper", AesGcmKS.encrypt("HiAnalytics_Sdk_Public_Sp_Key", optString));
            C4975d.m14763b(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_maint", AesGcmKS.encrypt("HiAnalytics_Sdk_Public_Sp_Key", optString2));
            C4975d.m14763b(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_time_interval", optString4);
            C4975d.m14763b(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_version", optString3);
            C4975d.m14763b(AbstractC5020q0.m14526i(), "Privacy_MY", "public_key_time_last", str3);
            AbstractC5020q0.m14533e(optString);
            AbstractC5020q0.m14535d(optString2);
            AbstractC5020q0.m14529g(optString3);
            AbstractC5020q0.m14537c(str3);
            AbstractC5020q0.m14531f(optString4);
        } catch (JSONException e) {
            C5029v.m14452e("GetPublicKey", "get pubKey parse json JSONException :" + e.getMessage());
        }
    }
}
