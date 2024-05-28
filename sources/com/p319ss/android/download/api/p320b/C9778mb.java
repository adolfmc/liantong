package com.p319ss.android.download.api.p320b;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.b.mb */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C9778mb {
    /* renamed from: mb */
    public static boolean m7972mb(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            return false;
        }
        if (C9940x.m7364lz().optInt("market_url_opt", 1) == 0) {
            return "market".equals(scheme);
        }
        return "market".equals(scheme) || "appmarket".equals(scheme) || "oaps".equals(scheme) || "oppomarket".equals(scheme) || "mimarket".equals(scheme) || "vivomarket".equals(scheme) || "vivoMarket".equals(scheme) || "gomarket".equals(scheme) || "goMarket".equals(scheme) || "mstore".equals(scheme) || "samsungapps".equals(scheme);
    }

    /* renamed from: ox */
    public static String m7971ox(@NonNull Uri uri) {
        String scheme = uri.getScheme();
        List<String> pathSegments = uri.getPathSegments();
        if (C9940x.m7364lz().optInt("market_scheme_opt") == 1 && C10152hj.m6563u() && "samsungapps".equals(scheme) && pathSegments != null && pathSegments.size() == 1) {
            return pathSegments.get(0);
        }
        return C9779ox.m7966mb(uri.getQueryParameter("id"), uri.getQueryParameter("packagename"), uri.getQueryParameter("pkg"), uri.getQueryParameter("package_name"), uri.getQueryParameter("appId"));
    }
}
