package com.sinovatech.unicom.common;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ReadAssetsUtils {
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
        } catch (Exception unused) {
            return null;
        }
    }
}
