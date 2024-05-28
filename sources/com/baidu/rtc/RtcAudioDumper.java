package com.baidu.rtc;

import android.os.Environment;
import android.support.annotation.Nullable;
import com.baidu.rtc.RTCAudioSamples;
import com.webrtc.Logging;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RtcAudioDumper implements RTCAudioSamples.RTCRemoteSamplesReadyCallback, RTCAudioSamples.RTCSamplesReadyCallback {
    private static final long MAX_FILE_SIZE_IN_BYTES = 58348800;
    private static final String TAG = "RtcRemoteAudioDumper";
    private final ExecutorService executor;
    private long fileSizeInBytes;
    private boolean isRunning;
    private final Object lock = new Object();
    @Nullable
    private OutputStream rawAudioFileOutputStream;

    public RtcAudioDumper(ExecutorService executorService) {
        Logging.m5305d("RtcRemoteAudioDumper", "remote audio dumper created");
        this.executor = executorService;
    }

    public boolean start() {
        Logging.m5305d("RtcRemoteAudioDumper", "remote audio dumper start");
        if (!isExternalStorageWritable()) {
            Logging.m5304e("RtcRemoteAudioDumper", "Writing to external media is not possible");
            return false;
        }
        synchronized (this.lock) {
            this.isRunning = true;
        }
        return true;
    }

    public void stop() {
        Logging.m5305d("RtcRemoteAudioDumper", "remote audio dumper stop");
        synchronized (this.lock) {
            this.isRunning = false;
            if (this.rawAudioFileOutputStream != null) {
                try {
                    this.rawAudioFileOutputStream.close();
                } catch (IOException e) {
                    Logging.m5304e("RtcRemoteAudioDumper", "Failed to close file with saved input audio: " + e);
                }
                this.rawAudioFileOutputStream = null;
            }
            this.fileSizeInBytes = 0L;
        }
    }

    private boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private void openRawAudioOutputFile(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getPath());
        sb.append(File.separator);
        sb.append("rtc-audio");
        sb.append(File.separator);
        sb.append("audio_16bits_");
        sb.append(String.valueOf(i));
        sb.append("Hz");
        sb.append(i2 == 1 ? "_mono_" : "_stereo_");
        sb.append(System.currentTimeMillis());
        sb.append(".pcm");
        String sb2 = sb.toString();
        File file = new File(sb2);
        File file2 = new File(file.getParent());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        try {
            this.rawAudioFileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Logging.m5304e("RtcRemoteAudioDumper", "Failed to open audio output file: " + e.getMessage());
        }
        Logging.m5305d("RtcRemoteAudioDumper", "Opened file for recording: " + sb2);
    }

    @Override // com.baidu.rtc.RTCAudioSamples.RTCSamplesReadyCallback
    public void onRtcAudioRecordSamplesReady(RTCAudioSamples rTCAudioSamples) {
        dumpAudioSamples(rTCAudioSamples);
    }

    @Override // com.baidu.rtc.RTCAudioSamples.RTCRemoteSamplesReadyCallback
    public void onRtcAudioRemoteSamplesReady(RTCAudioSamples rTCAudioSamples) {
        dumpAudioSamples(rTCAudioSamples);
    }

    private void dumpAudioSamples(final RTCAudioSamples rTCAudioSamples) {
        if (rTCAudioSamples.getAudioFormat() != 2) {
            Logging.m5304e("RtcRemoteAudioDumper", "Invalid audio format");
            return;
        }
        synchronized (this.lock) {
            if (this.isRunning) {
                if (this.rawAudioFileOutputStream == null) {
                    openRawAudioOutputFile(rTCAudioSamples.getSampleRate(), rTCAudioSamples.getChannelCount());
                    this.fileSizeInBytes = 0L;
                }
                this.executor.execute(new Runnable() { // from class: com.baidu.rtc.-$$Lambda$RtcAudioDumper$1ZnweygluS0pXY78M8aBAPDyZ-M
                    @Override // java.lang.Runnable
                    public final void run() {
                        RtcAudioDumper.lambda$dumpAudioSamples$0(RtcAudioDumper.this, rTCAudioSamples);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void lambda$dumpAudioSamples$0(RtcAudioDumper rtcAudioDumper, RTCAudioSamples rTCAudioSamples) {
        OutputStream outputStream = rtcAudioDumper.rawAudioFileOutputStream;
        if (outputStream != null) {
            try {
                if (rtcAudioDumper.fileSizeInBytes < 58348800) {
                    outputStream.write(rTCAudioSamples.getData());
                    rtcAudioDumper.fileSizeInBytes += rTCAudioSamples.getData().length;
                }
            } catch (IOException e) {
                Logging.m5304e("RtcRemoteAudioDumper", "Failed to write audio to file: " + e.getMessage());
            }
        }
    }
}
