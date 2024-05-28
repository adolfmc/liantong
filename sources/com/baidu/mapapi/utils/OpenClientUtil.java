package com.baidu.mapapi.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OpenClientUtil {
    public static int getBaiduMapVersion(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            String str = context.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 0).versionName;
            if (str != null && str.length() > 0) {
                return Integer.valueOf(str.trim().replace(".", "").trim()).intValue();
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static void getLatestBaiduMapApp(Context context) {
        if (context == null) {
            return;
        }
        String m18600b = MapOpenUtil.m18600b(context);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://map.baidu.com/zt/client/index/?fr=sdk_[" + m18600b + "]"));
        context.startActivity(intent);
    }
}
