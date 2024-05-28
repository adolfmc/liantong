package com.bytedance.applog;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.k1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3614k1 {

    /* renamed from: a */
    public static IExtraParams f8534a;

    /* renamed from: b */
    public static final String[] f8535b = {"ab_version", "device_brand", "language", "os_api", "resolution", "google_aid", "carrier", "install_id", "app_name", "app_version", "device_model", "density_dpi", "mcc_mnc", "sim_region", "device_id", "openudid", "clientudid", "aid", "bd_did", "sdk_version_code"};

    /* renamed from: c */
    public static final String[] f8536c = {"ab_version", "device_brand", "language", "os_api", "resolution", "google_aid", "carrier", "iid", "app_name", "version_name", "device_type", "dpi", "mcc_mnc", "sim_region", "device_id", "openudid", "clientudid", "aid", "bd_did", "sdk_version_code"};

    @Nullable
    /* renamed from: a */
    public static <T> T m17251a(JSONObject jSONObject, String str, T t) {
        if (jSONObject == null) {
            return (T) AppLog.getHeaderValue(str, t);
        }
        T t2 = (T) jSONObject.opt(str);
        if (t2 == null) {
            t2 = null;
        }
        return t2 == null ? t : t2;
    }

    /* renamed from: a */
    public static String m17254a(Context context, JSONObject jSONObject, StringBuilder sb, boolean z) {
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return sb2;
        }
        HashMap hashMap = new HashMap(f8535b.length + 10);
        m17253a(context, jSONObject, z, hashMap);
        if (sb2.indexOf(63) < 0) {
            sb.append('?');
        }
        for (String str : hashMap.keySet()) {
            String m17077a = C3712v2.m17077a(str, "UTF-8");
            String str2 = (String) hashMap.get(str);
            String m17077a2 = str2 != null ? C3712v2.m17077a(str2, "UTF-8") : "";
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(m17077a);
            sb.append("=");
            sb.append(m17077a2);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static void m17253a(Context context, JSONObject jSONObject, boolean z, Map<String, String> map) {
        String str;
        String num;
        int i = 0;
        while (true) {
            String[] strArr = f8535b;
            if (i >= strArr.length) {
                break;
            } else if ("os_api".equals(strArr[i]) || "density_dpi".equals(f8535b[i]) || "sdk_version_code".equals(f8535b[i])) {
                Integer num2 = (Integer) m17251a(jSONObject, f8535b[i], (Object) null);
                if (num2 != null) {
                    str = f8536c[i];
                    num = num2.toString();
                    map.put(str, num);
                    i++;
                } else {
                    i++;
                }
            } else {
                num = (String) m17251a(jSONObject, f8535b[i], (Object) null);
                if (TextUtils.isEmpty(num)) {
                    i++;
                } else {
                    str = f8536c[i];
                    map.put(str, num);
                    i++;
                }
            }
        }
        String str2 = (String) m17251a(jSONObject, "mc", (Object) null);
        if (!TextUtils.isEmpty(str2)) {
            map.put("mac_address", str2);
        }
        String str3 = (String) m17251a(jSONObject, "udid", (Object) null);
        if (!TextUtils.isEmpty(str3)) {
            map.put("uuid", str3);
        }
        String str4 = (String) m17251a(jSONObject, "build_serial", (Object) null);
        if (!TextUtils.isEmpty(str4)) {
            map.put("build_serial", str4);
        }
        String str5 = (String) m17251a(jSONObject, "aliyun_uuid", (Object) null);
        if (!TextUtils.isEmpty(str5)) {
            map.put("aliyun_uuid", str5);
        }
        try {
            HashMap<String, String> extraParams = f8534a == null ? null : f8534a.getExtraParams();
            if (extraParams != null && !extraParams.isEmpty()) {
                for (Map.Entry<String, String> entry : extraParams.entrySet()) {
                    if (entry != null) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                            map.put(key, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
        }
        if (z) {
            map.put("ssmix", "a");
        }
        String m17033a = C3730x2.m17033a(context);
        if (!TextUtils.isEmpty(m17033a)) {
            map.put("ac", m17033a);
        }
        String str6 = (String) AppLog.getHeaderValue("tweaked_channel", "");
        if (TextUtils.isEmpty(str6)) {
            str6 = (String) AppLog.getHeaderValue("channel", "");
        }
        if (!TextUtils.isEmpty(str6)) {
            map.put("channel", str6);
        }
        String str7 = (String) m17251a(jSONObject, "os_version", (Object) null);
        if (str7 != null && str7.length() > 10) {
            str7 = str7.substring(0, 10);
        }
        map.put("os_version", str7);
        map.put("_rticket", String.valueOf(System.currentTimeMillis()));
        map.put("device_platform", "android");
        String abSdkVersion = AppLog.getAbSdkVersion();
        if (!TextUtils.isEmpty(abSdkVersion)) {
            map.put("ab_version", abSdkVersion);
        }
        int intValue = ((Integer) AppLog.getHeaderValue("version_code", -1)).intValue();
        if (intValue != -1) {
            map.put("version_code", String.valueOf(intValue));
        }
        int intValue2 = ((Integer) AppLog.getHeaderValue("manifest_version_code", -1)).intValue();
        if (intValue2 != -1) {
            map.put("manifest_version_code", String.valueOf(intValue2));
        }
        int intValue3 = ((Integer) AppLog.getHeaderValue("update_version_code", -1)).intValue();
        if (intValue3 != -1) {
            map.put("update_version_code", String.valueOf(intValue3));
        }
        String str8 = (String) m17251a(jSONObject, "cdid", (Object) null);
        if (TextUtils.isEmpty(str8)) {
            return;
        }
        map.put("cdid", str8);
    }

    /* renamed from: a */
    public static String[] m17252a(C3591h c3591h, JSONObject jSONObject, boolean z) {
        UriConfig m17287c = c3591h.m17287c();
        String[] realUris = z ? m17287c.getRealUris() : m17287c.getSendUris();
        int length = realUris.length;
        String[] strArr = new String[length];
        boolean encryptAndCompress = AppLog.getEncryptAndCompress();
        for (int i = 0; i < length; i++) {
            strArr[i] = realUris[i];
            if (encryptAndCompress) {
                strArr[i] = C3535a.m17348a(new StringBuilder(), strArr[i], "?tt_data=a");
            }
            strArr[i] = m17254a((Context) c3591h.f8464c, jSONObject, new StringBuilder(strArr[i]), true);
            strArr[i] = C3607j1.m17262a(strArr[i], C3607j1.f8524d);
        }
        return strArr;
    }
}
