package com.chinaunicon.jtwifilib.core.crashreport;

import android.os.Process;
import android.util.Log;
import androidx.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DumpSysCollector {
    private static final int DEFAULT_BUFFER_SIZE_IN_BYTES = 8192;
    private static final String LOG_TAG = "com.chinaunicon.jtwifilib.core.crashreport.DumpSysCollector";

    @Nullable
    public static String collectMemInfo() {
        StringBuilder sb = new StringBuilder();
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add("dumpsys");
            arrayList.add("meminfo");
            arrayList.add(Integer.toString(Process.myPid()));
            Process exec = Runtime.getRuntime().exec((String[]) arrayList.toArray(new String[arrayList.size()]));
            exec.waitFor();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()), 8192);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "BumpSysCollector.meminfo could not retrieve data", e);
        } catch (InterruptedException e2) {
            Log.e(LOG_TAG, "BumpSysCollector.meminfo could not retrieve data", e2);
        }
        return sb.toString();
    }
}
