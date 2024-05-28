package com.baidu.rtc.recorder;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class BRTCMediaRecorderParams {
    public BRTCMediaEncodeParams encodeParams;
    public long infoUpdateInterval;
    public long maxRecordDuration;
    public BRTCMediaRecorderType mediaRecorderType = BRTCMediaRecorderType.AUDIO_VIDEO;
    public String storagePath;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public static class BRTCMediaEncodeParams {
        public int audioBitrate;
        public int audioChannel;
        public int audioSampleRate;
        public int videoBitrate;
        public int videoFps;
        public int videoHeight;
        public int videoWidth;
        public String videoCodec = "video/avc";
        public int videoIFrameInterval = 5;
        public String audioCodec = "audio/mp4a-latm";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public enum BRTCMediaRecorderType {
        AUDIO_VIDEO,
        AUDIO_ONLY,
        VIDEO_ONLY
    }

    public boolean audioOnly() {
        return this.mediaRecorderType == BRTCMediaRecorderType.AUDIO_ONLY;
    }

    public boolean videoOnly() {
        return this.mediaRecorderType == BRTCMediaRecorderType.VIDEO_ONLY;
    }

    public boolean hasAudio() {
        return this.mediaRecorderType == BRTCMediaRecorderType.AUDIO_ONLY || this.mediaRecorderType == BRTCMediaRecorderType.AUDIO_VIDEO;
    }

    public boolean hasVideo() {
        return this.mediaRecorderType == BRTCMediaRecorderType.VIDEO_ONLY || this.mediaRecorderType == BRTCMediaRecorderType.AUDIO_VIDEO;
    }

    public boolean checkParamsValidate() {
        if (TextUtils.isEmpty(this.storagePath) || this.encodeParams == null) {
            return false;
        }
        if (hasVideo() && (this.encodeParams.videoWidth == 0 || this.encodeParams.videoHeight == 0 || this.encodeParams.videoBitrate == 0 || this.encodeParams.videoFps == 0)) {
            return false;
        }
        if (hasAudio()) {
            return (this.encodeParams.audioChannel == 0 || this.encodeParams.audioSampleRate == 0 || this.encodeParams.audioBitrate == 0) ? false : true;
        }
        return true;
    }
}
