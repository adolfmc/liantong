package com.baidu.rtc.audio;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum BRTCAudioProfileType {
    BRTC_AUDIO_PROFILE_DEFAULT(0),
    BRTC_AUDIO_PROFILE_LOW_QUALITY(1),
    BRTC_AUDIO_PROFILE_STANDARD(2),
    BRTC_AUDIO_PROFILE_HIGH_QUALITY(3),
    BRTC_AUDIO_PROFILE_STEREO_HIGH_QUALITY(4),
    BRTC_AUDIO_PROFILE_SUPER_QUALITY(5),
    BRTC_AUDIO_PROFILE_STEREO_SUPER_QUALITY(6);
    
    private final int value;

    BRTCAudioProfileType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
