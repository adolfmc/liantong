package com.huawei.hms.hatool;

import android.text.TextUtils;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.e1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4980e1 {
    /* renamed from: a */
    public static String m14743a(String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str2)) {
            return m14744a(str, str2, str3) ? str2 : str4;
        }
        C5029v.m14451f("hmsSdk", "checkStrParameter() Parameter verification failure! Parameter:" + str);
        return str4;
    }

    /* renamed from: a */
    public static boolean m14746a(String str) {
        return !m14745a("eventId", str, 256);
    }

    /* renamed from: a */
    public static boolean m14745a(String str, String str2, int i) {
        StringBuilder sb;
        String str3;
        if (TextUtils.isEmpty(str2)) {
            sb = new StringBuilder();
            str3 = "checkString() Parameter is empty : ";
        } else if (str2.length() <= i) {
            return true;
        } else {
            sb = new StringBuilder();
            str3 = "checkString() Failure of parameter length check! Parameter:";
        }
        sb.append(str3);
        sb.append(str);
        C5029v.m14451f("hmsSdk", sb.toString());
        return false;
    }

    /* renamed from: a */
    public static boolean m14744a(String str, String str2, String str3) {
        StringBuilder sb;
        String str4;
        if (TextUtils.isEmpty(str2)) {
            sb = new StringBuilder();
            str4 = "checkString() Parameter is null! Parameter:";
        } else if (Pattern.compile(str3).matcher(str2).matches()) {
            return true;
        } else {
            sb = new StringBuilder();
            str4 = "checkString() Parameter verification failure! Parameter:";
        }
        sb.append(str4);
        sb.append(str);
        C5029v.m14451f("hmsSdk", sb.toString());
        return false;
    }

    /* renamed from: a */
    public static boolean m14742a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            C5029v.m14451f("hmsSdk", "onEvent() mapValue has not data.so,The data will be empty");
            return false;
        } else if (map.size() == 1 && (map.get("constants") != null || map.get("_constants") != null)) {
            C5029v.m14451f("hmsSdk", "checkMap() the key can't be constants or _constants");
            return false;
        } else if (map.size() > 2048 || map.toString().length() > 204800) {
            C5029v.m14451f("hmsSdk", "checkMap Map data is too big! size: " + map.size() + " length: " + map.toString().length());
            return false;
        } else {
            return true;
        }
    }
}
