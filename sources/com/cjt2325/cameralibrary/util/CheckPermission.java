package com.cjt2325.cameralibrary.util;

import android.hardware.Camera;
import android.media.AudioRecord;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CheckPermission {
    public static final int STATE_NO_PERMISSION = -2;
    public static final int STATE_RECORDING = -1;
    public static final int STATE_SUCCESS = 1;

    public static int getRecordState() {
        int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
        AudioRecord audioRecord = new AudioRecord(0, 44100, 16, 2, minBufferSize * 100);
        short[] sArr = new short[minBufferSize];
        try {
            audioRecord.startRecording();
            if (audioRecord.getRecordingState() != 3) {
                audioRecord.stop();
                audioRecord.release();
                return -1;
            } else if (audioRecord.read(sArr, 0, sArr.length) <= 0) {
                audioRecord.stop();
                audioRecord.release();
                return -2;
            } else {
                audioRecord.stop();
                audioRecord.release();
                return 1;
            }
        } catch (Exception unused) {
            audioRecord.release();
            return -2;
        }
    }

    public static synchronized boolean isCameraUseable(int i) {
        boolean z;
        synchronized (CheckPermission.class) {
            Camera camera = null;
            z = false;
            try {
                try {
                    camera = Camera.open(i);
                    camera.setParameters(camera.getParameters());
                    if (camera != null) {
                        camera.release();
                        z = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (camera != null) {
                    camera.release();
                }
            }
        }
        return z;
    }
}
