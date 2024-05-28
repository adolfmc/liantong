package com.sinovatech.unicom.separatemodule.Log;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LogFileUploadUtils {
    public static void upload(Context context) {
        File[] listFiles;
        try {
            ConfigManager configManager = new ConfigManager(context);
            if (configManager.getLogfileUploadKey().equals("1")) {
                File file = new File(FileTools.getLoginErrorLog());
                if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length <= 0) {
                    return;
                }
                upload(configManager, listFiles, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void upload(final ConfigManager configManager, final File[] fileArr, final int i) {
        final File file;
        if (i >= fileArr.length || (file = fileArr[i]) == null || !file.exists()) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(file.getName(), file);
        App.getAsyncHttpClient(20, 20, 20).uploadFile(configManager.getLoginLogKey(), hashMap, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.Log.LogFileUploadUtils.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i2, String str) {
                super.onSuccess(i2, str);
                if (App.isSuccessful(i2)) {
                    file.delete();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str) {
                super.onFailure(th, str);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                LogFileUploadUtils.upload(configManager, fileArr, i + 1);
            }
        });
    }

    private static String getProxyInfo() {
        try {
            String property = System.getProperty("http.proxyHost");
            String property2 = System.getProperty("http.proxyPort");
            return property + ":" + property2;
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static void writeLoginErrorLog(Context context, String str, String str2, String str3, String str4, Throwable th) {
        try {
            if (new ConfigManager(context).getLogfileUploadKey().equals("1") && SystemServiceUtils.netIsAvailable()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("##");
                if (th != null) {
                    stringBuffer.append("Fail");
                    stringBuffer.append("$$");
                } else {
                    stringBuffer.append("Success");
                    stringBuffer.append("$$");
                }
                String uuid = UUID.randomUUID().toString();
                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                stringBuffer.append(uuid);
                stringBuffer.append("$$");
                stringBuffer.append(format);
                stringBuffer.append("$$");
                stringBuffer.append(str);
                stringBuffer.append("$$");
                stringBuffer.append(DeviceHelper.getDeviceID(true));
                stringBuffer.append("$$");
                stringBuffer.append(context.getResources().getString(2131886969));
                stringBuffer.append("$$");
                stringBuffer.append(str2);
                stringBuffer.append("$$");
                stringBuffer.append(DeviceHelper.getNETType(context));
                stringBuffer.append("$$");
                stringBuffer.append(DeviceHelper.getDeviceBrand());
                stringBuffer.append("$$");
                stringBuffer.append(DeviceHelper.getDeviceOSVersionCode());
                stringBuffer.append("$$");
                stringBuffer.append(str3);
                if (!TextUtils.isEmpty(str4)) {
                    stringBuffer.append("$$");
                    stringBuffer.append("接口返回数据:" + str4.replace("\n", " ").replace("\r", " "));
                }
                stringBuffer.append("\n\r");
                FileTools.writeFile(FileTools.getLoginErrorLog() + ((Object) DateFormat.format("yyyy-MM-dd", System.currentTimeMillis())) + "_info.log", stringBuffer.toString().getBytes(Charset.forName("UTF-8")), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast("保存登录登录日志异常" + e.getMessage());
        }
    }
}
