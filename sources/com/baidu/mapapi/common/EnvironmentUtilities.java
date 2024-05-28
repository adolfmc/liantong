package com.baidu.mapapi.common;

import android.content.Context;
import com.baidu.mapsdkplatform.comapi.util.StorageSettings;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EnvironmentUtilities {

    /* renamed from: a */
    static String f5856a;

    /* renamed from: b */
    static String f5857b;

    /* renamed from: c */
    static String f5858c;

    /* renamed from: d */
    static int f5859d;

    /* renamed from: e */
    static int f5860e;

    /* renamed from: f */
    static int f5861f;

    /* renamed from: g */
    static int f5862g;

    /* renamed from: h */
    private static StorageSettings f5863h;

    public static void initAppDirectory(Context context) {
        if (f5863h == null) {
            f5863h = StorageSettings.m18149a();
            f5863h.m18148a(context);
        }
        String str = f5856a;
        if (str != null && str.length() > 0) {
            f5857b = f5856a + File.separator + "BaiduMapSDKNew" + File.separator + "cache";
        } else {
            f5856a = f5863h.m18145b().m18153a();
            f5857b = f5863h.m18145b().m18151c();
        }
        f5858c = f5863h.m18145b().m18150d();
        f5859d = 52428800;
        f5860e = 52428800;
        f5861f = 5242880;
        f5862g = 52428800;
    }

    public static String getSDCardPath() {
        return f5856a;
    }

    public static void setSDCardPath(String str) {
        f5856a = str;
    }

    public static String getAppSDCardPath() {
        String str = f5856a + "/BaiduMapSDKNew";
        if (str.length() != 0) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return str;
    }

    public static String getAppCachePath() {
        return f5857b;
    }

    public static String getAppSecondCachePath() {
        return f5858c;
    }

    public static int getMapTmpStgMax() {
        return f5859d;
    }

    public static int getDomTmpStgMax() {
        return f5860e;
    }

    public static int getItsTmpStgMax() {
        return f5861f;
    }

    public static int getSsgTmpStgMax() {
        return f5862g;
    }
}
