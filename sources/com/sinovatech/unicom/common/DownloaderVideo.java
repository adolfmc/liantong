package com.sinovatech.unicom.common;

import com.megvii.livenesslib.util.SDCardUtil;
import com.sinovatech.unicom.p318ui.App;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DownloaderVideo extends Thread {
    public static boolean isComplete;
    private String advCode;
    private String savePath;
    private String srcUrl;

    public DownloaderVideo(String str, String str2) {
        this.savePath = SDCardUtil.getMediaFileUrl("KPGG") + str + ".mp4";
        this.srcUrl = str2;
        this.advCode = str;
    }

    public boolean isCached() {
        if (new File(this.savePath).exists()) {
            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            if (sharePreferenceUtil.getBoolean("cacheload_complete" + this.advCode)) {
                return true;
            }
        }
        return false;
    }

    public String getPaht() {
        return this.savePath;
    }

    public static void clearVideo() {
        File[] listFiles;
        try {
            File file = new File(SDCardUtil.getMediaFileUrl("KPGG"));
            if (!file.exists() || (listFiles = file.listFiles()) == null) {
                return;
            }
            for (File file2 : listFiles) {
                file2.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(this.srcUrl).openStream());
            if (new File(this.savePath).exists()) {
                return;
            }
            FileTools.createPath(this.savePath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.savePath));
            byte[] bArr = new byte[8192];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read != -1) {
                    bufferedOutputStream.write(bArr, 0, read);
                } else {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                    sharePreferenceUtil.putBoolean("cacheload_complete" + this.advCode, true);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
