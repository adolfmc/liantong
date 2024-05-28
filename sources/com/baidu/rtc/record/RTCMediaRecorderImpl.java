package com.baidu.rtc.record;

import android.support.annotation.Nullable;
import com.baidu.rtc.RemoteAudioSamplesInterceptor;
import com.webrtc.VideoTrack;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class RTCMediaRecorderImpl implements IMediaRecord {
    private static final String TAG = "RTCMediaRecorderImpl";
    private final RemoteAudioSamplesInterceptor audioInterceptor;
    private boolean isRunning = false;
    private RTCVideoFileRenderer videoFileRenderer;
    private final VideoTrack videoTrack;

    public RTCMediaRecorderImpl(@Nullable VideoTrack videoTrack, @Nullable RemoteAudioSamplesInterceptor remoteAudioSamplesInterceptor) {
        this.videoTrack = videoTrack;
        this.audioInterceptor = remoteAudioSamplesInterceptor;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x009a, code lost:
        r11.onRecordCompleted(false, "Is current recording with file path " + r9);
     */
    @Override // com.baidu.rtc.record.IMediaRecord
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void startRecording(java.lang.String r9, com.baidu.rtc.record.MediaEncodeParams r10, com.baidu.rtc.record.RecorderCallback r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.isRunning     // Catch: java.lang.Throwable -> Lb0
            r1 = 0
            if (r0 != 0) goto L98
            boolean r0 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Throwable -> Lb0
            if (r0 != 0) goto L98
            if (r10 != 0) goto L10
            goto L98
        L10:
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> Lb0
            r0.<init>(r9)     // Catch: java.lang.Throwable -> Lb0
            java.io.File r9 = r0.getParentFile()     // Catch: java.lang.Throwable -> Lb0
            boolean r9 = r9.exists()     // Catch: java.lang.Throwable -> Lb0
            if (r9 != 0) goto L33
            java.io.File r9 = r0.getParentFile()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> Lb0
            r9.mkdirs()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> Lb0
            goto L33
        L27:
            r9 = move-exception
            if (r11 == 0) goto L31
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> Lb0
            r11.onRecordCompleted(r1, r9)     // Catch: java.lang.Throwable -> Lb0
        L31:
            monitor-exit(r8)
            return
        L33:
            com.webrtc.VideoTrack r9 = r8.videoTrack     // Catch: java.lang.Throwable -> Lb0
            if (r9 == 0) goto L84
            if (r10 != 0) goto L40
            com.baidu.rtc.record.MediaEncodeParams r10 = new com.baidu.rtc.record.MediaEncodeParams     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            r10.<init>()     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            r4 = r10
            goto L41
        L40:
            r4 = r10
        L41:
            com.baidu.rtc.record.RTCVideoFileRenderer r9 = new com.baidu.rtc.record.RTCVideoFileRenderer     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            java.lang.String r3 = r0.getAbsolutePath()     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            com.webrtc.EglBase r10 = com.webrtc.EglBase.CC.create()     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            com.webrtc.EglBase$Context r5 = r10.getEglBaseContext()     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            com.baidu.rtc.RemoteAudioSamplesInterceptor r10 = r8.audioInterceptor     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            r0 = 1
            if (r10 == 0) goto L56
            r6 = r0
            goto L57
        L56:
            r6 = r1
        L57:
            r2 = r9
            r7 = r11
            r2.<init>(r3, r4, r5, r6, r7)     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            r8.videoFileRenderer = r9     // Catch: java.io.IOException -> L78 java.lang.Throwable -> Lb0
            com.webrtc.VideoTrack r9 = r8.videoTrack     // Catch: java.lang.Throwable -> Lb0
            com.baidu.rtc.record.RTCVideoFileRenderer r10 = r8.videoFileRenderer     // Catch: java.lang.Throwable -> Lb0
            r9.addSink(r10)     // Catch: java.lang.Throwable -> Lb0
            com.baidu.rtc.RemoteAudioSamplesInterceptor r9 = r8.audioInterceptor     // Catch: java.lang.Throwable -> Lb0
            if (r9 == 0) goto L70
            com.baidu.rtc.RemoteAudioSamplesInterceptor r9 = r8.audioInterceptor     // Catch: java.lang.Throwable -> Lb0
            com.baidu.rtc.record.RTCVideoFileRenderer r10 = r8.videoFileRenderer     // Catch: java.lang.Throwable -> Lb0
            r9.attachCallback(r10)     // Catch: java.lang.Throwable -> Lb0
        L70:
            if (r11 == 0) goto L75
            r11.onRecordStart()     // Catch: java.lang.Throwable -> Lb0
        L75:
            r8.isRunning = r0     // Catch: java.lang.Throwable -> Lb0
            goto L96
        L78:
            r9 = move-exception
            r8.isRunning = r1     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> Lb0
            r11.onRecordCompleted(r1, r9)     // Catch: java.lang.Throwable -> Lb0
            monitor-exit(r8)
            return
        L84:
            java.lang.String r9 = "RTCMediaRecorderImpl"
            java.lang.String r10 = "Video track is null"
            com.webrtc.Logging.m5304e(r9, r10)     // Catch: java.lang.Throwable -> Lb0
            com.baidu.rtc.RemoteAudioSamplesInterceptor r9 = r8.audioInterceptor     // Catch: java.lang.Throwable -> Lb0
            if (r9 == 0) goto L94
            java.lang.String r9 = "Audio only is not support, add video track"
            r11.onRecordCompleted(r1, r9)     // Catch: java.lang.Throwable -> Lb0
        L94:
            r8.isRunning = r1     // Catch: java.lang.Throwable -> Lb0
        L96:
            monitor-exit(r8)
            return
        L98:
            if (r11 == 0) goto Lae
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb0
            r10.<init>()     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r0 = "Is current recording with file path "
            r10.append(r0)     // Catch: java.lang.Throwable -> Lb0
            r10.append(r9)     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r9 = r10.toString()     // Catch: java.lang.Throwable -> Lb0
            r11.onRecordCompleted(r1, r9)     // Catch: java.lang.Throwable -> Lb0
        Lae:
            monitor-exit(r8)
            return
        Lb0:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.rtc.record.RTCMediaRecorderImpl.startRecording(java.lang.String, com.baidu.rtc.record.MediaEncodeParams, com.baidu.rtc.record.RecorderCallback):void");
    }

    public boolean isRecording() {
        return this.isRunning;
    }

    @Override // com.baidu.rtc.record.IMediaRecord
    public synchronized void stopRecording() {
        if (this.isRunning) {
            if (this.audioInterceptor != null) {
                this.audioInterceptor.detachCallback();
            }
            if (this.videoTrack != null && this.videoFileRenderer != null) {
                this.videoTrack.removeSink(this.videoFileRenderer);
                this.videoFileRenderer.release();
                this.videoFileRenderer = null;
            }
            this.isRunning = false;
        }
    }
}
