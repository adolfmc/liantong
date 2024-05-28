package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download;

import android.os.Environment;
import android.text.TextUtils;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.dispatcher.DownloadDispatcher;
import com.megvii.livenesslib.util.SDCardUtil;
import com.sinovatech.unicom.p318ui.App;
import io.objectbox.Box;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FileDownloadUtils {
    private static final String FileDownloadDir = "ChinaUnicomEdopDownload";
    private static Box<FileDownloadTask> boxInstance;

    public static Box<FileDownloadTask> getBox() {
        synchronized (FileDownloadTask.class) {
            if (boxInstance == null) {
                boxInstance = App.getBoxStore().boxFor(FileDownloadTask.class);
            }
        }
        return boxInstance;
    }

    public static FileDownloadTask getFileDownTaskByUrl(String str) {
        return getBox().query().equal(FileDownloadTask_.url, str).build().findFirst();
    }

    public static File createDic(String str, String str2) throws Exception {
        String externalDir = SDCardUtil.getExternalDir(Environment.DIRECTORY_DOWNLOADS, "ChinaUnicomEdopDownload");
        try {
            URL url = new URL(str2);
            if (TextUtils.isEmpty(str)) {
                str = url.getHost();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(str)) {
            externalDir = externalDir + str;
        }
        File file = new File(externalDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static synchronized DownloadTask createFileDownloadTask(File file, String str, String str2, JSONObject jSONObject) {
        DownloadTask build;
        synchronized (FileDownloadUtils.class) {
            try {
                DownloadDispatcher.setMaxParallelRunningCount(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            DownloadTask.Builder builder = new DownloadTask.Builder(str, file);
            if (!TextUtils.isEmpty(str2)) {
                builder.setFilename(str2);
            }
            builder.setMinIntervalMillisCallbackProcess(500).setPassIfAlreadyCompleted(false).setAutoCallbackToUIThread(true).setConnectionCount(1).setPreAllocateLength(false);
            try {
                if (jSONObject != null) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        String optString = jSONObject.optString(next);
                        if (!TextUtils.isEmpty(optString)) {
                            builder.addHeader(next, optString);
                        }
                    }
                } else {
                    DownloadTask downloadTask = App.fileDownlaodTaskMap.get(str);
                    if (downloadTask != null && downloadTask.getHeaderMapFields() != null && downloadTask.getHeaderMapFields().size() > 0) {
                        builder.setHeaderMapFields(downloadTask.getHeaderMapFields());
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            build = builder.build();
            App.fileDownlaodTaskMap.put(str, build);
        }
        return build;
    }
}
