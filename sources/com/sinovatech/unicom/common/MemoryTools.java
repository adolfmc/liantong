package com.sinovatech.unicom.common;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import com.megvii.livenesslib.util.RootUtil;
import com.sinovatech.unicom.p318ui.App;
import com.tydic.tydic_tracker.app.TYApplication;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MemoryTools {
    public static final String MEMFREE = "MemFree";
    public static final String MEMTOTAL = "MemTotal";
    private static final String MEM_INFO_PATH = "/proc/meminfo";
    private static final String TAG = "com.sinovatech.unicom.common.MemoryTools";

    public static String getTotalMemory(Context context) {
        return getMemInfoIype(context, "MemTotal");
    }

    public static String getMemoryFree(Context context) {
        return getMemInfoIype(context, "MemFree");
    }

    public static String getMemInfoIype(Context context, String str) {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 4096);
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
            } while (!readLine.contains(str));
            bufferedReader.close();
            return Formatter.formatFileSize(context, Integer.valueOf(readLine.split("\\s+")[1]).intValue() * 1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getInternalToatalSpace(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        long blockSize = statFs.getBlockSize();
        statFs.getAvailableBlocks();
        return Formatter.formatFileSize(context, statFs.getBlockCount() * blockSize);
    }

    public static JSONObject getBasic() throws JSONException {
        long currentTimeMillis = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cpu_used", TYApplication.dev_val);
        jSONObject.put("mem_used", getTotalMemory(App.getInstance()));
        jSONObject.put("mem_free", getMemoryFree(App.getInstance()));
        if (RootUtil.isDeviceRooted()) {
            jSONObject.put("root", 1);
        } else {
            jSONObject.put("root", 0);
        }
        jSONObject.put("orientation", "1");
        jSONObject.put("device_type", TYApplication.dev_val);
        jSONObject.put("dump_energy", TYApplication.dev_val);
        jSONObject.put("useful_space", "");
        jSONObject.put("cpu_model", "");
        jSONObject.put("os", "Android");
        jSONObject.put("branch", DeviceHelper.getDeviceOSVersion());
        jSONObject.put("device_name", DeviceHelper.getDeviceBrand());
        jSONObject.put("device_version", DeviceHelper.getDeviceModel());
        jSONObject.put("operator", DeviceHelper.getOperator());
        jSONObject.put("screen", UIUtils.getScreenWidth(App.getInstance()) + "x" + UIUtils.getFullScreenHeight(App.getInstance()));
        jSONObject.put("os_enum", "2");
        jSONObject.put("current_time", currentTimeMillis);
        return jSONObject;
    }
}
