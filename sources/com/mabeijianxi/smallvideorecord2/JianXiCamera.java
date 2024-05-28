package com.mabeijianxi.smallvideorecord2;

import android.text.TextUtils;
import com.mabeijianxi.smallvideorecord2.jniinterface.FFmpegBridge;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JianXiCamera {
    public static final String FFMPEG_LOG_FILENAME_TEMP = "jx_ffmpeg.log";
    private static int mAppVersionCode;
    private static String mAppVersionName;
    private static String mPackageName;
    private static String mVideoCachePath;

    public static void initialize(boolean z, String str) {
        if (z && TextUtils.isEmpty(str)) {
            str = mVideoCachePath + "/jx_ffmpeg.log";
        } else if (!z) {
            str = null;
        }
        FFmpegBridge.initJXFFmpeg(z, str);
    }

    public static String getVideoCachePath() {
        return mVideoCachePath;
    }

    public static void setVideoCachePath(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        mVideoCachePath = str;
    }
}
