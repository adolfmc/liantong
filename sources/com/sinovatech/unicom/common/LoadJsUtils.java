package com.sinovatech.unicom.common;

import android.content.Context;
import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LoadJsUtils {
    public static String streamToString(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return new String(byteArrayOutputStream.toByteArray());
                }
            }
        } catch (Exception e) {
            Log.e("LoadJsUtils", e.toString());
            return null;
        }
    }

    public static String returnJsConfig(String str, String str2, String str3, String str4, String str5) {
        return "    var options = {\n        \"scale\":" + str5 + ",\n        \"theme\": [\n            { \"name\": \"中国红\", \"skinId\": \"chinaRed\", \"backgroundColor\": \"red\", \"color\": \"yellow\", \"link\": \"yellow\" },\n            { \"name\": \"深蓝\", \"skinId\": \"deepblue\", \"backgroundColor\": \"#00000000\", \"color\": \"yellow\", \"link\": \"yellow\" },\n            { \"name\": \"高对比度\", \"skinId\": \"gaoduibi\", \"backgroundColor\": \"#333333\", \"color\": \"white\", \"link\": \"yellow\" }\n        ],\n        \"defaultTheme\":\"" + str + "\",\n        \"voice\": { \n        \t\t\"open\": " + str2 + ",        \t\t\"delay\":" + str3 + ", \n          \t\t\"rate\":" + str4 + "\t\n            }\n    }; wzaSdk.init(options);wzaSdk.start();";
    }

    public static String getFromAssets(Context context, String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(str)));
            String str2 = "";
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return str2;
                }
                str2 = str2 + readLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
