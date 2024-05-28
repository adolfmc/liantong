package com.huawei.secure.android.common.webview;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import com.huawei.secure.android.common.util.LogsUtil;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UriUtil {

    /* renamed from: a */
    private static final String f12186a = "UriUtil";

    /* renamed from: a */
    private static String m13762a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return !URLUtil.isNetworkUrl(str) ? str : getHostByURI(str);
        }
        LogsUtil.m13813i("UriUtil", "whiteListUrl is null");
        return null;
    }

    @TargetApi(9)
    public static String getHostByURI(String str) {
        if (TextUtils.isEmpty(str)) {
            LogsUtil.m13813i("UriUtil", "url is null");
            return str;
        }
        try {
            if (!URLUtil.isNetworkUrl(str)) {
                LogsUtil.m13819e("UriUtil", "url don't starts with http or https");
                return "";
            }
            return new URL(str.replaceAll("[\\\\#]", "/")).getHost();
        } catch (MalformedURLException e) {
            LogsUtil.m13819e("UriUtil", "getHostByURI error  MalformedURLException : " + e.getMessage());
            return "";
        }
    }

    public static boolean isUrlHostAndPathInWhitelist(String str, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str2 : strArr) {
                if (isUrlHostAndPathMatchWhitelist(str, str2)) {
                    return true;
                }
            }
            return false;
        }
        LogsUtil.m13819e("UriUtil", "whitelist is null");
        return false;
    }

    public static boolean isUrlHostAndPathMatchWhitelist(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!str.contains("..") && !str.contains("@")) {
                if (!str2.equals(str)) {
                    if (!str.startsWith(str2 + "?")) {
                        if (!str.startsWith(str2 + "#")) {
                            if (str2.endsWith("/")) {
                                if (Uri.parse(str).getPathSegments().size() - Uri.parse(str2).getPathSegments().size() != 1) {
                                    return false;
                                }
                                return str.startsWith(str2);
                            }
                            return false;
                        }
                    }
                }
                return true;
            }
            Log.e("UriUtil", "url contains unsafe char");
        }
        return false;
    }

    public static boolean isUrlHostInWhitelist(String str, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str2 : strArr) {
                if (isUrlHostMatchWhitelist(str, str2)) {
                    return true;
                }
            }
            return false;
        }
        LogsUtil.m13819e("UriUtil", "whitelist is null");
        return false;
    }

    public static boolean isUrlHostMatchWhitelist(String str, String str2) {
        String hostByURI = getHostByURI(str);
        if (!TextUtils.isEmpty(hostByURI) && !TextUtils.isEmpty(str2)) {
            String m13762a = m13762a(str2);
            if (TextUtils.isEmpty(m13762a)) {
                Log.e("UriUtil", "whitelist host is null");
                return false;
            } else if (m13762a.equals(hostByURI)) {
                return true;
            } else {
                if (hostByURI.endsWith(m13762a)) {
                    try {
                        String substring = hostByURI.substring(0, hostByURI.length() - m13762a.length());
                        if (substring.endsWith(".")) {
                            return substring.matches("^[A-Za-z0-9.-]+$");
                        }
                        return false;
                    } catch (IndexOutOfBoundsException e) {
                        LogsUtil.m13819e("UriUtil", "IndexOutOfBoundsException" + e.getMessage());
                    } catch (Exception e2) {
                        LogsUtil.m13819e("UriUtil", "Exception : " + e2.getMessage());
                        return false;
                    }
                }
                return false;
            }
        }
        LogsUtil.m13819e("UriUtil", "url or whitelist is null");
        return false;
    }

    public static boolean isUrlHostSameWhitelist(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return TextUtils.equals(getHostByURI(str), m13762a(str2));
        }
        Log.e("UriUtil", "isUrlHostSameWhitelist: url or host is null");
        return false;
    }

    public static boolean isUrlHostSameWhitelist(String str, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str2 : strArr) {
                if (isUrlHostSameWhitelist(str, str2)) {
                    return true;
                }
            }
            return false;
        }
        LogsUtil.m13819e("UriUtil", "whitelist is null");
        return false;
    }
}
