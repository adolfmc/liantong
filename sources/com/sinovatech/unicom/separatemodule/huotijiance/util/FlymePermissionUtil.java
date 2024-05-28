package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.content.Context;
import android.media.AudioRecord;
import android.os.Build;
import android.os.Environment;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FlymePermissionUtil {
    /* JADX WARN: Removed duplicated region for block: B:8:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isCameraCanUse() {
        /*
            android.hardware.Camera r0 = android.hardware.Camera.open()     // Catch: java.lang.Exception -> Ld
            android.hardware.Camera$Parameters r1 = r0.getParameters()     // Catch: java.lang.Exception -> Le
            r0.setParameters(r1)     // Catch: java.lang.Exception -> Le
            r1 = 1
            goto Lf
        Ld:
            r0 = 0
        Le:
            r1 = 0
        Lf:
            if (r0 == 0) goto L14
            r0.release()
        L14:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.huotijiance.util.FlymePermissionUtil.isCameraCanUse():boolean");
    }

    public static boolean isHasPermission(Context context) {
        int read;
        AudioRecord audioRecord = new AudioRecord(1, 44100, 12, 2, AudioRecord.getMinBufferSize(44100, 12, 2));
        try {
            audioRecord.startRecording();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        if (audioRecord.getRecordingState() == 3 && (read = audioRecord.read(new byte[1024], 0, 1024)) != -3 && read > 0) {
            if (audioRecord.getRecordingState() == 1) {
                return true;
            }
            audioRecord.stop();
            audioRecord.release();
            return true;
        }
        return false;
    }

    public static boolean checkStoragePermission(Context context) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/temp/");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isSpecialDevice() {
        return Build.BRAND.toLowerCase().equals("smartisan") || Build.BRAND.toLowerCase().equals("xiaomi") || Build.BRAND.toLowerCase().equals("oppo") || Build.BRAND.toLowerCase().equals("vivo") || Build.BRAND.toLowerCase().equals("lenovo") || Build.BRAND.toLowerCase().equals("meizu");
    }
}
