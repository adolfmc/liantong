package com.mabeijianxi.smallvideorecord2;

import com.mabeijianxi.smallvideorecord2.jniinterface.FFmpegBridge;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FFMpegUtils {
    public static boolean captureThumbnails(String str, String str2, String str3) {
        return FFmpegBridge.jxFFmpegCMDRun(getCaptureThumbnailsCMD(str, str2, str3)) == 0;
    }

    public static String getCaptureThumbnailsCMD(String str, String str2, String str3) {
        String str4;
        if (str3 == null) {
            str4 = "";
        } else {
            str4 = " -ss " + str3;
        }
        return String.format("ffmpeg  -i  %s  %s  -vframes 1  %s ", str, str4, str2);
    }

    public static boolean captureThumbnails(String str, String str2, String str3, String str4) {
        String str5;
        FileUtils.deleteFile(str2);
        if (str4 == null) {
            str5 = "";
        } else {
            str5 = " -ss " + str4;
        }
        return FFmpegBridge.jxFFmpegCMDRun(String.format("ffmpeg -d stdout -loglevel verbose -i \"%s\"%s -s %s -vframes 1 \"%s\"", str, str5, str3, str2)) == 0;
    }
}
