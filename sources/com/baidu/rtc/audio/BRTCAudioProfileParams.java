package com.baidu.rtc.audio;

import com.baidu.rtc.RtcParameterSettings;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BRTCAudioProfileParams {
    public static final int AUDIO_CHANNEL_MONO = 1;
    public static final int AUDIO_CHANNEL_STEREO = 2;
    public static final int AUDIO_MAX_BITRATE_128KBPS = 128;
    public static final int AUDIO_MAX_BITRATE_12KBPS = 12;
    public static final int AUDIO_MAX_BITRATE_32KBPS = 32;
    public static final int AUDIO_MAX_BITRATE_48KBPS = 48;
    public static final int AUDIO_MAX_BITRATE_80KBPS = 80;
    public static final int AUDIO_MAX_BITRATE_96KBPS = 96;
    public static final int AUDIO_MAX_BITRATE_AUTO = -1;
    public static final int AUDIO_SAMPLE_RATE_16000 = 16000;
    public static final int AUDIO_SAMPLE_RATE_48000 = 48000;
    public static final int AUDIO_SAMPLE_RATE_8000 = 8000;
    private String audioCodec = "opus";
    private int audioSampleRate = 48000;
    private int outputAudioChannel = 1;
    private int inputAudioChannel = 1;
    private int audioMaxBitrateInKbps = -1;
    private RtcParameterSettings.RtcAudioBitrateMode audioBitrateMode = RtcParameterSettings.RtcAudioBitrateMode.RTC_AUDIO_BITRATE_CBR;
    private RtcParameterSettings.RtcAudioChannel audioChannelForEncode = RtcParameterSettings.RtcAudioChannel.RTC_AUDIO_MONO;
    private int audioSource = 7;
    private int audioContentType = 1;
    private boolean disableBluetoothScoMode = true;
    private BRTCAudioProfileType audioProfileType = BRTCAudioProfileType.BRTC_AUDIO_PROFILE_DEFAULT;
    private BRTCAudioScenario scenarioType = BRTCAudioScenario.BRTC_AUDIO_SCENARIO_DEFAULT;

    public void setAudioSampleRate(int i) {
        this.audioSampleRate = i;
    }

    public void setAudioCodec(String str) {
        this.audioCodec = str;
    }

    public void setAudioMaxBitrateInKbps(int i) {
        this.audioMaxBitrateInKbps = i;
    }

    public void setInputAudioChannel(int i) {
        this.inputAudioChannel = i;
    }

    public void setOutputAudioChannel(int i) {
        this.outputAudioChannel = i;
    }

    public void setAudioSource(int i) {
        this.audioSource = i;
    }

    public void setAudioContentType(int i) {
        this.audioContentType = i;
    }

    public void setAudioChannelForEncode(RtcParameterSettings.RtcAudioChannel rtcAudioChannel) {
        this.audioChannelForEncode = rtcAudioChannel;
    }

    public void setDisableBluetoothScoMode(boolean z) {
        this.disableBluetoothScoMode = z;
    }

    public int getAudioSampleRate() {
        return this.audioSampleRate;
    }

    public String getAudioCodec() {
        return this.audioCodec;
    }

    public int getInputAudioChannel() {
        return this.inputAudioChannel;
    }

    public int getOutputAudioChannel() {
        return this.outputAudioChannel;
    }

    public int getAudioContentType() {
        return this.audioContentType;
    }

    public RtcParameterSettings.RtcAudioChannel getAudioChannelForEncode() {
        return this.audioChannelForEncode;
    }

    public int getAudioSource() {
        return this.audioSource;
    }

    public int getAudioMaxBitrateInKbps() {
        return this.audioMaxBitrateInKbps;
    }

    public boolean isDisableBluetoothScoMode() {
        return this.disableBluetoothScoMode;
    }

    public void setAudioProfileType(BRTCAudioProfileType bRTCAudioProfileType) {
        this.audioProfileType = bRTCAudioProfileType;
    }

    public void setScenarioType(BRTCAudioScenario bRTCAudioScenario) {
        this.scenarioType = bRTCAudioScenario;
    }

    public String toString() {
        return "BRTCAudioProfileParams{audioCodec='" + this.audioCodec + "', audioSampleRate=" + this.audioSampleRate + ", outputAudioChannel=" + this.outputAudioChannel + ", inputAudioChannel=" + this.inputAudioChannel + ", audioMaxBitrateInKbps=" + this.audioMaxBitrateInKbps + ", audioBitrateMode=" + this.audioBitrateMode + ", transportAudioChannel=" + this.audioChannelForEncode + ", audioSource=" + this.audioSource + ", audioContentType=" + this.audioContentType + ", disableBluetoothScoMode=" + this.disableBluetoothScoMode + ", audioProfileType=" + this.audioProfileType + ", scenarioType=" + this.scenarioType + '}';
    }
}
