package com.baidu.p120ar.mdl;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.mdl.MdlConfigParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MdlConfigParams {
    private static final String ASSET_PATH_SCHEME = "file:///android_asset/";
    private static final String CONFIG_FILE_NAME = "dl_config.json";
    private SparseArray<MdlConfig> configs = new SparseArray<>();

    public SparseArray<MdlConfig> getConfigs() {
        return this.configs;
    }

    public void setMdlConfigPath(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith("file:///android_asset/")) {
            String substring = str.substring(22);
            if (!substring.endsWith("/")) {
                substring = substring + "/";
            }
            updateConfigs(readAssetConfigFile(context, substring + "dl_config.json"), substring, true);
            return;
        }
        updateConfigs(readConfigFile(new File(str, "dl_config.json")), str, false);
    }

    private void updateConfigs(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                int i2 = jSONObject.getInt("type");
                JSONArray jSONArray2 = jSONObject.getJSONArray("models");
                int length2 = jSONArray2.length();
                MdlConfig mdlConfig = new MdlConfig();
                mdlConfig.type = i2;
                mdlConfig.isFromAsset = z;
                mdlConfig.modelPaths = new String[length2];
                for (int i3 = 0; i3 < length2; i3++) {
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                    int optInt = jSONObject2.optInt("model_type", i3);
                    File file = new File(str2, jSONObject2.getString("dir"));
                    String optString = jSONObject2.optString("design_model", "");
                    String absolutePath = (!TextUtils.isEmpty(optString) ? new File(file, optString) : file).getAbsolutePath();
                    if (z) {
                        absolutePath = absolutePath.substring(1);
                    }
                    mdlConfig.modelPaths[optInt] = absolutePath;
                }
                try {
                    this.configs.put(i2, mdlConfig);
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                    return;
                }
            }
        } catch (JSONException e2) {
            e = e2;
        }
    }

    private static String readAssetConfigFile(Context context, String str) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open(str), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    inputStreamReader.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x003f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String readConfigFile(java.io.File r3) {
        /*
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2c
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2c
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2c
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            r3.<init>()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
        L10:
            java.lang.String r2 = r1.readLine()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            if (r2 == 0) goto L1a
            r3.append(r2)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            goto L10
        L1a:
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3c
            r1.close()     // Catch: java.io.IOException -> L22
            goto L26
        L22:
            r0 = move-exception
            r0.printStackTrace()
        L26:
            return r3
        L27:
            r3 = move-exception
            goto L2e
        L29:
            r3 = move-exception
            r1 = r0
            goto L3d
        L2c:
            r3 = move-exception
            r1 = r0
        L2e:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L3c
            if (r1 == 0) goto L3b
            r1.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r3 = move-exception
            r3.printStackTrace()
        L3b:
            return r0
        L3c:
            r3 = move-exception
        L3d:
            if (r1 == 0) goto L47
            r1.close()     // Catch: java.io.IOException -> L43
            goto L47
        L43:
            r0 = move-exception
            r0.printStackTrace()
        L47:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.mdl.MdlConfigParams.readConfigFile(java.io.File):java.lang.String");
    }
}
