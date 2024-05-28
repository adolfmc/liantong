package com.sinovatech.unicom.separatemodule.gamedistribution;

import android.os.Environment;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.dispatcher.DownloadDispatcher;
import com.sinovatech.unicom.p318ui.App;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GameDownloadUtils {
    public static File createDic() throws Exception {
        String absolutePath;
        if (Environment.getExternalStorageState().equals("mounted")) {
            absolutePath = App.getInstance().getExternalFilesDir(null).getAbsolutePath();
        } else {
            absolutePath = App.getInstance().getFilesDir().getAbsolutePath();
        }
        File file = new File(absolutePath + File.separator + "gamedistribution");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static synchronized DownloadTask createDownloadTask(File file, String str, String str2) {
        synchronized (GameDownloadUtils.class) {
            if (App.gameDownloadTaskMap.containsKey(str) && App.gameDownloadTaskMap.get(str) != null) {
                return App.gameDownloadTaskMap.get(str);
            }
            try {
                DownloadDispatcher.setMaxParallelRunningCount(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String absolutePath = file.getAbsolutePath();
            DownloadTask build = new DownloadTask.Builder(str2, absolutePath, str + ".apk").setMinIntervalMillisCallbackProcess(1000).setPassIfAlreadyCompleted(false).setAutoCallbackToUIThread(true).setConnectionCount(1).setPreAllocateLength(false).build();
            App.gameDownloadTaskMap.put(str, build);
            return build;
        }
    }
}
