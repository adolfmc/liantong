package com.gmrz.appsdk.util;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HwUtil {
    public static final String ACCEPTED = "accepted";
    public static final String APKHASH = "apk-hash";
    public static final String FILENAME = "hwfidoconfig";
    public static final String FILEPATHNAME = "/system/emui/base/fido/hwfidoconfig.json";
    public static final String FILEPATHROOT = "/system/emui";
    public static final String PACKNAME = "packName";
    public static String TAG = "FidoUtil";
    public static final String WHILELIST = "whiteList";

    /* renamed from: in */
    private static BufferedReader f10355in;

    public static String getPackageName(Context context) {
        if (context != null) {
            return context.getPackageName();
        }
        return null;
    }

    private static JSONObject getWhileJSON(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception unused) {
            Log.e(TAG, "file to json is error!");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String readContent(java.io.File r3) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
            com.gmrz.appsdk.util.HwUtil.f10355in = r3     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
        L17:
            java.io.BufferedReader r3 = com.gmrz.appsdk.util.HwUtil.f10355in     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
            java.lang.String r3 = r3.readLine()     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
            if (r3 == 0) goto L23
            r0.append(r3)     // Catch: java.lang.Throwable -> L27 java.lang.Exception -> L2a
            goto L17
        L23:
            r2.close()     // Catch: java.io.IOException -> L3b
            goto L42
        L27:
            r3 = move-exception
            r1 = r2
            goto L48
        L2a:
            r1 = r2
            goto L2e
        L2c:
            r3 = move-exception
            goto L48
        L2e:
            java.lang.String r3 = com.gmrz.appsdk.util.HwUtil.TAG     // Catch: java.lang.Throwable -> L47
            java.lang.String r2 = "IO error!"
            android.util.Log.e(r3, r2)     // Catch: java.lang.Throwable -> L47
            if (r1 == 0) goto L42
            r1.close()     // Catch: java.io.IOException -> L3b
            goto L42
        L3b:
            java.lang.String r3 = com.gmrz.appsdk.util.HwUtil.TAG
            java.lang.String r1 = "read file is error!"
            android.util.Log.e(r3, r1)
        L42:
            java.lang.String r3 = r0.toString()
            return r3
        L47:
            r3 = move-exception
        L48:
            if (r1 == 0) goto L55
            r1.close()     // Catch: java.io.IOException -> L4e
            goto L55
        L4e:
            java.lang.String r0 = com.gmrz.appsdk.util.HwUtil.TAG
            java.lang.String r1 = "read file is error!"
            android.util.Log.e(r0, r1)
        L55:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.appsdk.util.HwUtil.readContent(java.io.File):java.lang.String");
    }

    private static String readWhileFile() {
        File file = new File("/system/emui/base/fido/hwfidoconfig.json");
        String str = "";
        if (file.exists()) {
            return readContent(file);
        }
        List<File> searchFile = searchFile(new File("/system/emui"), "hwfidoconfig");
        if (searchFile.size() > 0) {
            for (int i = 0; i < searchFile.size(); i++) {
                str = readContent(searchFile.get(i));
                if (str.length() > 0) {
                    return str;
                }
            }
            return str;
        }
        Log.e(TAG, "can't find white paper");
        return "";
    }

    public static List<File> searchFile(File file, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (!file2.isDirectory()) {
                        if (file2.getName().contains(str)) {
                            arrayList.add(file2);
                        }
                    } else {
                        arrayList.addAll(searchFile(file2, str));
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "searchFile failed e:" + e.getMessage());
        }
        return arrayList;
    }

    private static boolean verifyAppIDAndApkHash(JSONArray jSONArray, boolean z, String str, String str2) {
        String optString;
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString2 = jSONObject.optString("packName");
                if (optString2 != null && optString2.trim().length() != 0 && str2.equalsIgnoreCase(optString2) && (!z || ((optString = jSONObject.optString("apk-hash")) != null && optString.trim().length() != 0 && str.equalsIgnoreCase(optString)))) {
                    return true;
                }
            } catch (Exception e) {
                Logger.m15757e(TAG, "verifyAppIDAndApkHash failed e:" + e.getMessage());
            }
        }
        return false;
    }

    public static boolean verifyAuth(String str, String str2) {
        JSONObject whileJSON;
        if (str2 != null) {
            try {
                if (str2.trim().length() != 0 && str != null && str.trim().length() != 0) {
                    String readWhileFile = readWhileFile();
                    if (readWhileFile == null || readWhileFile.length() == 0 || (whileJSON = getWhileJSON(readWhileFile)) == null) {
                        return true;
                    }
                    boolean booleanValue = ((Boolean) whileJSON.get("whiteList")).booleanValue();
                    JSONArray jSONArray = whileJSON.getJSONArray("accepted");
                    boolean verifyAppIDAndApkHash = verifyAppIDAndApkHash(jSONArray, booleanValue, str, str2);
                    if (booleanValue) {
                        if (jSONArray == null) {
                            return false;
                        }
                        return verifyAppIDAndApkHash;
                    } else if (jSONArray == null) {
                        return true;
                    } else {
                        return !verifyAppIDAndApkHash;
                    }
                }
            } catch (Exception unused) {
                Log.e(TAG, "get json object is error!");
            }
        }
        return false;
    }
}
