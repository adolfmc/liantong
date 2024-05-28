package com.baidu.rtc.audio;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum BRTCAudioScenario {
    BRTC_AUDIO_SCENARIO_DEFAULT(0),
    BRTC_AUDIO_SCENARIO_SPEECH(1),
    BRTC_AUDIO_SCENARIO_MUSIC(2),
    BRTC_AUDIO_SCENARIO_METAVERSE(3);
    
    private final int value;

    BRTCAudioScenario(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
