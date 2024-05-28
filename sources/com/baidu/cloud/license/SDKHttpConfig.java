package com.baidu.cloud.license;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SDKHttpConfig implements INotProguard {
    private static String HOST = "";
    public static final String PLATFORM = "Android";
    public static final int RESPONSE_EXPIRE = 5012;
    public static final int RESPONSE_SUCCESS = 3010;
    private static String appId = "";
    private static int arVersion = 0;
    private static String packageName = "";
    private static String sdkVersion = "";

    public static String getHOST() {
        return HOST;
    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String str) {
        appId = str;
    }

    public static String getSdkVersion() {
        return sdkVersion;
    }

    public static void setSdkVersion(String str) {
        sdkVersion = str;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static void setPackageName(String str) {
        packageName = str;
    }

    public static int getArVersion() {
        return arVersion;
    }

    public static void setArVersion(int i) {
        arVersion = i;
    }

    public static String getSignStr() {
        return getAppId() + "#" + getSdkVersion() + "#" + getPackageName() + "#Android";
    }

    public static String getFaceSignStr(long j) {
        return getAppId() + "#" + j + "#" + getSdkVersion() + "#" + getPackageName() + "#Android";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void init(android.content.Context r4) {
        /*
            if (r4 != 0) goto Lb
            java.lang.String r4 = "Config"
            java.lang.String r0 = "配置信息加载失败 context is null!"
            android.util.Log.e(r4, r0)
            return
        Lb:
            r0 = 0
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L67
            java.lang.String r1 = "sdkcore.properties"
            r2 = 3
            java.io.InputStream r4 = r4.open(r1, r2)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L67
            java.util.Properties r1 = new java.util.Properties     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            r1.<init>()     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            r1.load(r4)     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            java.lang.String r2 = "host"
            java.lang.String r0 = r1.getProperty(r2, r0)     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            com.baidu.cloud.license.SDKHttpConfig.HOST = r0     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            if (r0 == 0) goto L58
            java.lang.String r0 = com.baidu.cloud.license.SDKHttpConfig.HOST     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            java.lang.String r1 = "/"
            boolean r0 = r0.endsWith(r1)     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            if (r0 == 0) goto L44
            java.lang.String r0 = com.baidu.cloud.license.SDKHttpConfig.HOST     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            r1 = 0
            java.lang.String r2 = com.baidu.cloud.license.SDKHttpConfig.HOST     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            int r2 = r2.length()     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            int r2 = r2 + (-1)
            java.lang.String r0 = r0.substring(r1, r2)     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            com.baidu.cloud.license.SDKHttpConfig.HOST = r0     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
        L44:
            r4.close()     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            if (r4 == 0) goto L57
            r4.close()     // Catch: java.io.IOException -> L4d
            goto L57
        L4d:
            r4 = move-exception
            java.lang.String r0 = "Config"
            java.lang.String r1 = "配置文件关闭失败"
            android.util.Log.e(r0, r1, r4)
            return
        L57:
            return
        L58:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            java.lang.String r1 = "host can not be null, please check sdkcore.properties"
            r0.<init>(r1)     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
            throw r0     // Catch: java.io.IOException -> L60 java.lang.Throwable -> L79
        L60:
            r0 = move-exception
            goto L6b
        L62:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L7a
        L67:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
        L6b:
            java.lang.String r1 = "Config"
            java.lang.String r2 = "配置信息加载失败"
            android.util.Log.e(r1, r2, r0)     // Catch: java.lang.Throwable -> L79
            if (r4 == 0) goto L78
            r4.close()     // Catch: java.io.IOException -> L4d
        L78:
            return
        L79:
            r0 = move-exception
        L7a:
            if (r4 == 0) goto L89
            r4.close()     // Catch: java.io.IOException -> L80
            goto L89
        L80:
            r4 = move-exception
            java.lang.String r1 = "Config"
            java.lang.String r2 = "配置文件关闭失败"
            android.util.Log.e(r1, r2, r4)
        L89:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.license.SDKHttpConfig.init(android.content.Context):void");
    }
}
