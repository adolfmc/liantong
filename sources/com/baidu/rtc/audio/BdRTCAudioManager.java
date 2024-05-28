package com.baidu.rtc.audio;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaCodec;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.baidu.cloud.framework.Sink;
import com.baidu.cloud.framework.frame.AudioFrameBuffer;
import com.baidu.cloud.mediaprocess.filter.AudioFilter;
import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.PeerConnectionClient;
import com.baidu.rtc.RTCAudioSamples;
import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;
import com.webrtc.Logging;
import com.webrtc.audio.JavaAudioDeviceModule;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BdRTCAudioManager {
    private static final String TAG = "BdRTCAudioManager";
    private BdRTCAudioEndSink bdAudioEndSink;
    private BdRTCAudioChangeSink bdRTCAudioChangeSink;
    private AudioFilter mAudioFilter;
    private BRTCAudioProfileParams mAudioProfileParams;
    private WeakReference<Context> mContext;
    private PeerConnectionClient mPeerConnectionClient;
    private List<Sink<AudioFrameBuffer>> sinkList;
    RTCAudioSamples.RTCSamplesReadyCallback mSamplesReadyCallback = null;
    RTCAudioSamples.RTCSamplesReadyCallback mAecSamplesReadyCallback = null;
    RTCAudioSamples.RTCRemoteSamplesReadyCallback mRemoteSamplesCallback = null;
    RTCAudioSamples.RTCSampleStatusCallback mSampleStatusCallback = null;
    private RTCAudioSamples.RTCMixedSamplesReadyCallback mMixedSamplesCallback = null;
    private AudioManager audioManager = null;
    private int bluetoothOpSeq = 0;
    private Boolean isBluetoothOn = false;
    private boolean sDisableBTSocMode = true;
    private boolean mEnableVoiceChange = false;
    private boolean mEnableAudioMix = false;
    private int mixAudioTrackIndex = -1;
    private boolean mIsEnableExternalAudioRecord = false;
    private int mRemoteFilterTrack = -1;
    private boolean isRelease = false;
    private boolean isStopPublish = false;
    HeadsetReceiver mHeadSetReceiver = null;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    JavaAudioDeviceModule.SamplesReadyCallback mSamplesReadyInternalCallback = new JavaAudioDeviceModule.SamplesReadyCallback() { // from class: com.baidu.rtc.audio.BdRTCAudioManager.1
        @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
        public void onWebRtcAudioRecordSamplesReady(JavaAudioDeviceModule.AudioSamples audioSamples) {
            if (audioSamples == null || audioSamples.getData() == null || audioSamples.getData().length <= 0) {
                return;
            }
            if (BdRTCAudioManager.this.mSamplesReadyCallback != null) {
                BdRTCAudioManager.this.mSamplesReadyCallback.onRtcAudioRecordSamplesReady(new RTCAudioSamples(audioSamples.getAudioFormat(), audioSamples.getChannelCount(), audioSamples.getSampleRate(), audioSamples.getData()));
            }
            if (BdRTCAudioManager.this.mIsEnableExternalAudioRecord) {
                return;
            }
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            bufferInfo.size = audioSamples.getData().length;
            bufferInfo.offset = 0;
            ByteBuffer wrap = ByteBuffer.wrap(audioSamples.getData());
            bufferInfo.presentationTimeUs = System.nanoTime();
            if (BdRTCAudioManager.this.sinkList == null || BdRTCAudioManager.this.sinkList.size() <= 0) {
                return;
            }
            ((Sink) BdRTCAudioManager.this.sinkList.get(0)).getInPort().onFrame(new AudioFrameBuffer(wrap, bufferInfo));
        }

        @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
        public void onStartRecord() {
            if (BdRTCAudioManager.this.mSampleStatusCallback != null) {
                BdRTCAudioManager.this.mSampleStatusCallback.onStartRecord();
            }
        }

        @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
        public void onStopRecord() {
            if (BdRTCAudioManager.this.mSampleStatusCallback != null) {
                BdRTCAudioManager.this.mSampleStatusCallback.onStopRecord();
            }
        }
    };
    JavaAudioDeviceModule.SamplesReadyCallback mAecSamplesReadyInternalCallback = new JavaAudioDeviceModule.SamplesReadyCallback() { // from class: com.baidu.rtc.audio.BdRTCAudioManager.2
        @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
        public void onStartRecord() {
        }

        @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
        public void onStopRecord() {
        }

        @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
        public void onWebRtcAudioRecordSamplesReady(JavaAudioDeviceModule.AudioSamples audioSamples) {
            if (audioSamples == null || audioSamples.getData() == null || audioSamples.getData().length <= 0) {
                return;
            }
            if (BdRTCAudioManager.this.mAecSamplesReadyCallback != null) {
                BdRTCAudioManager.this.mAecSamplesReadyCallback.onRtcAudioRecordSamplesReady(new RTCAudioSamples(audioSamples.getAudioFormat(), audioSamples.getChannelCount(), audioSamples.getSampleRate(), audioSamples.getData()));
            }
            if (BdRTCAudioManager.this.mIsEnableExternalAudioRecord) {
                return;
            }
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            bufferInfo.size = audioSamples.getData().length;
            bufferInfo.offset = 0;
            ByteBuffer wrap = ByteBuffer.wrap(audioSamples.getData());
            bufferInfo.presentationTimeUs = System.nanoTime();
            if (BdRTCAudioManager.this.sinkList == null || BdRTCAudioManager.this.sinkList.size() <= 0) {
                return;
            }
            ((Sink) BdRTCAudioManager.this.sinkList.get(0)).getInPort().onFrame(new AudioFrameBuffer(wrap, bufferInfo));
        }
    };
    JavaAudioDeviceModule.RemoteSamplesReadyCallback mRemoteSamplesInternalCallback = new JavaAudioDeviceModule.RemoteSamplesReadyCallback() { // from class: com.baidu.rtc.audio.BdRTCAudioManager.3
        @Override // com.webrtc.audio.JavaAudioDeviceModule.RemoteSamplesReadyCallback
        public void onWebRtcAudioRemoteSamplesReady(JavaAudioDeviceModule.AudioSamples audioSamples) {
            if (audioSamples == null) {
                return;
            }
            if (BdRTCAudioManager.this.mRemoteSamplesCallback != null) {
                BdRTCAudioManager.this.mRemoteSamplesCallback.onRtcAudioRemoteSamplesReady(new RTCAudioSamples(audioSamples.getAudioFormat(), audioSamples.getChannelCount(), audioSamples.getSampleRate(), audioSamples.getData()));
            }
            if (!BdRTCAudioManager.this.mEnableAudioMix || BdRTCAudioManager.this.mRemoteFilterTrack < 0) {
                return;
            }
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            bufferInfo.size = audioSamples.getData().length;
            bufferInfo.offset = 0;
            BdRTCAudioManager.this.mAudioFilter.getSubInPort(BdRTCAudioManager.this.mRemoteFilterTrack).onFrame(new AudioFrameBuffer(ByteBuffer.wrap(audioSamples.getData()), bufferInfo));
        }
    };
    RTCAudioSamples.RTCExternalSamplesReadyCallback externalSamplesReadyCallback = new RTCAudioSamples.RTCExternalSamplesReadyCallback() { // from class: com.baidu.rtc.audio.BdRTCAudioManager.4
        @Override // com.baidu.rtc.RTCAudioSamples.RTCExternalSamplesReadyCallback
        public void onRtcAudioExternalSamplesReady(RTCAudioSamples rTCAudioSamples) {
            if (BdRTCAudioManager.this.mPeerConnectionClient != null) {
                if (BdRTCAudioManager.this.sinkList == null || BdRTCAudioManager.this.sinkList.size() < 1) {
                    JavaAudioDeviceModule.ExternalSamplesReadyCallback externalSamplesCallback = BdRTCAudioManager.this.mPeerConnectionClient.getExternalSamplesCallback();
                    if (((true ^ BdRTCAudioManager.this.isRelease) & (externalSamplesCallback != null)) && !BdRTCAudioManager.this.isStopPublish) {
                        externalSamplesCallback.onWebRtcAudioExternalSamplesReady(rTCAudioSamples);
                    }
                }
                if (BdRTCAudioManager.this.mIsEnableExternalAudioRecord) {
                    MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
                    bufferInfo.size = rTCAudioSamples.getData().length;
                    bufferInfo.offset = 0;
                    ByteBuffer wrap = ByteBuffer.wrap(rTCAudioSamples.getData());
                    bufferInfo.presentationTimeUs = System.nanoTime();
                    if (BdRTCAudioManager.this.sinkList == null || BdRTCAudioManager.this.sinkList.size() <= 0) {
                        return;
                    }
                    ((Sink) BdRTCAudioManager.this.sinkList.get(0)).getInPort().onFrame(new AudioFrameBuffer(wrap, bufferInfo));
                }
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OutAudioSourceToInnerInterface {
        JavaAudioDeviceModule.ExternalSamplesReadyCallback getInnerSampleSetInterface();
    }

    static /* synthetic */ int access$904(BdRTCAudioManager bdRTCAudioManager) {
        int i = bdRTCAudioManager.bluetoothOpSeq + 1;
        bdRTCAudioManager.bluetoothOpSeq = i;
        return i;
    }

    static /* synthetic */ int access$908(BdRTCAudioManager bdRTCAudioManager) {
        int i = bdRTCAudioManager.bluetoothOpSeq;
        bdRTCAudioManager.bluetoothOpSeq = i + 1;
        return i;
    }

    public JavaAudioDeviceModule.SamplesReadyCallback getSamplesReadyInternalCallback() {
        return this.mSamplesReadyInternalCallback;
    }

    public JavaAudioDeviceModule.SamplesReadyCallback getAecSamplesReadyInternalCallback() {
        return this.mAecSamplesReadyInternalCallback;
    }

    public JavaAudioDeviceModule.RemoteSamplesReadyCallback getRemoteSamplesInternalCallback() {
        return this.mRemoteSamplesInternalCallback;
    }

    public RTCAudioSamples.RTCExternalSamplesReadyCallback getExternalSamplesReadyCallback() {
        return this.externalSamplesReadyCallback;
    }

    public BdRTCAudioManager(Context context) {
        this.mContext = null;
        this.mContext = new WeakReference<>(context);
    }

    public void setSamplesReadyCallback(RTCAudioSamples.RTCSamplesReadyCallback rTCSamplesReadyCallback) {
        this.mSamplesReadyCallback = rTCSamplesReadyCallback;
    }

    public void setAudioAecSamplesReadyCallback(RTCAudioSamples.RTCSamplesReadyCallback rTCSamplesReadyCallback) {
        this.mAecSamplesReadyCallback = rTCSamplesReadyCallback;
    }

    public void setAudioSampleStatusCallback(RTCAudioSamples.RTCSampleStatusCallback rTCSampleStatusCallback) {
        this.mSampleStatusCallback = rTCSampleStatusCallback;
    }

    public void setRemoteSamplesCallback(RTCAudioSamples.RTCRemoteSamplesReadyCallback rTCRemoteSamplesReadyCallback) {
        this.mRemoteSamplesCallback = rTCRemoteSamplesReadyCallback;
    }

    public void setMixedSamplesCallback(RTCAudioSamples.RTCMixedSamplesReadyCallback rTCMixedSamplesReadyCallback) {
        this.mMixedSamplesCallback = rTCMixedSamplesReadyCallback;
        BdRTCAudioEndSink bdRTCAudioEndSink = this.bdAudioEndSink;
        if (bdRTCAudioEndSink != null) {
            bdRTCAudioEndSink.setMixedSamplesCallback(rTCMixedSamplesReadyCallback);
        }
    }

    public void enableExternalAudioRecord(boolean z) {
        this.mIsEnableExternalAudioRecord = z;
    }

    public boolean isEnableExternalAudioRecord() {
        return this.mIsEnableExternalAudioRecord;
    }

    public void enableAudioMix(boolean z) {
        this.mEnableAudioMix = z;
    }

    public boolean isEnableAudioMix() {
        return this.mEnableAudioMix;
    }

    public boolean isEnableAudioProcess() {
        return this.mEnableAudioMix || this.mEnableVoiceChange;
    }

    public void enableVoiceChange(boolean z) {
        this.mEnableVoiceChange = z;
        BdRTCAudioChangeSink bdRTCAudioChangeSink = this.bdRTCAudioChangeSink;
        if (bdRTCAudioChangeSink != null) {
            bdRTCAudioChangeSink.enableAudioEffect(this.mEnableVoiceChange);
        }
    }

    public void setVoiceChangeType(BaiduRtcRoom.BdRTCVoiceChangeType bdRTCVoiceChangeType) {
        BdRTCAudioChangeSink bdRTCAudioChangeSink = this.bdRTCAudioChangeSink;
        if (bdRTCAudioChangeSink != null) {
            bdRTCAudioChangeSink.setAudioEffectType(bdRTCVoiceChangeType);
        }
    }

    public void initAudioMix(PeerConnectionClient peerConnectionClient, BRTCAudioProfileParams bRTCAudioProfileParams) {
        boolean z;
        this.mAudioProfileParams = bRTCAudioProfileParams;
        this.mPeerConnectionClient = peerConnectionClient;
        int i = 0;
        this.isRelease = false;
        this.sinkList = new ArrayList();
        if (this.mEnableVoiceChange) {
            if (this.bdRTCAudioChangeSink == null) {
                this.bdRTCAudioChangeSink = new BdRTCAudioChangeSink();
            }
            this.bdRTCAudioChangeSink.init(bRTCAudioProfileParams, 960);
            this.sinkList.add(this.bdRTCAudioChangeSink);
            z = true;
        } else {
            z = false;
        }
        if (this.mEnableAudioMix) {
            if (this.mAudioFilter == null) {
                this.mAudioFilter = new AudioFilter();
            }
            this.mAudioFilter.setup(false);
            this.mAudioFilter.clearMasterTrackQueue();
            this.mAudioFilter.setNeedRendering(false);
            this.mAudioFilter.setNeedRenderMasterTrack(false);
            this.sinkList.add(this.mAudioFilter);
            z = true;
        }
        if (z) {
            this.bdAudioEndSink = new BdRTCAudioEndSink(this.mMixedSamplesCallback, this.mAudioProfileParams, new OutAudioSourceToInnerInterface() { // from class: com.baidu.rtc.audio.BdRTCAudioManager.5
                @Override // com.baidu.rtc.audio.BdRTCAudioManager.OutAudioSourceToInnerInterface
                public JavaAudioDeviceModule.ExternalSamplesReadyCallback getInnerSampleSetInterface() {
                    return BdRTCAudioManager.this.mPeerConnectionClient.getExternalSamplesCallback();
                }
            });
            this.sinkList.add(this.bdAudioEndSink);
        }
        while (i < this.sinkList.size()) {
            int i2 = i + 1;
            if (i2 < this.sinkList.size()) {
                this.sinkList.get(i).getOutPort().link(this.sinkList.get(i2).getInPort());
            }
            i = i2;
        }
        if (this.mSamplesReadyCallback != null || this.sinkList.size() > 0 || this.mSampleStatusCallback != null) {
            peerConnectionClient.setAudioSamplesReadyCallback(getSamplesReadyInternalCallback());
        }
        if (this.mRemoteSamplesCallback != null || this.mEnableAudioMix) {
            peerConnectionClient.setRemoteAudioSamplesReadyCallback(getRemoteSamplesInternalCallback());
        }
        if (this.mAecSamplesReadyCallback != null) {
            peerConnectionClient.setAudioAecSamplesReadyCallback(getAecSamplesReadyInternalCallback());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class HeadsetReceiver extends BroadcastReceiver {
        HeadsetReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BdRTCAudioManager.this.getSysAudioManager();
            String action = intent.getAction();
            if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                if (intExtra == 0) {
                    Logging.m5305d("BdRTCAudioManager", "Bluetooth headset not disconnected");
                    BdRTCAudioManager.this.setBluetoothScoOn(false);
                    BdRTCAudioManager.access$908(BdRTCAudioManager.this);
                    if (BdRTCAudioManager.this.audioManager.isWiredHeadsetOn()) {
                        return;
                    }
                    BdRTCAudioManager.this.setSoundMod(BaiduRtcRoom.RtcSoundMode.RTC_SOUND_MODE_SPEAKER);
                } else if (intExtra == 2) {
                    final int access$904 = BdRTCAudioManager.access$904(BdRTCAudioManager.this);
                    boolean isBluetoothScoOn = BdRTCAudioManager.this.getSysAudioManager().isBluetoothScoOn();
                    if (BdRTCAudioManager.this.isBluetoothOn.booleanValue() == isBluetoothScoOn) {
                        BdRTCAudioManager.this.mHandler.postDelayed(new Runnable() { // from class: com.baidu.rtc.audio.BdRTCAudioManager.HeadsetReceiver.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (access$904 == BdRTCAudioManager.this.bluetoothOpSeq) {
                                    BdRTCAudioManager.this.setBluetoothScoOn(true);
                                    BdRTCAudioManager.this.setSoundMod(BaiduRtcRoom.RtcSoundMode.RTC_SOUND_MODE_EAR);
                                }
                            }
                        }, 1500L);
                    } else {
                        BdRTCAudioManager.this.isBluetoothOn = Boolean.valueOf(isBluetoothScoOn);
                        if (!BdRTCAudioManager.this.isBluetoothOn.booleanValue()) {
                            BdRTCAudioManager.this.setBluetoothScoOn(true);
                            BdRTCAudioManager.this.setSoundMod(BaiduRtcRoom.RtcSoundMode.RTC_SOUND_MODE_EAR);
                        }
                    }
                    Logging.m5305d("BdRTCAudioManager", "Bluetooth headset connected");
                }
            } else if (intent.getAction().equals("android.intent.action.HEADSET_PLUG") && intent.hasExtra("state")) {
                if (intent.getIntExtra("state", -1) == 0 && !BdRTCAudioManager.this.isHeadsetOn(context)) {
                    Logging.m5305d("BdRTCAudioManager", "headset not connected");
                    BdRTCAudioManager.this.setSoundMod(BaiduRtcRoom.RtcSoundMode.RTC_SOUND_MODE_SPEAKER);
                } else if (intent.getIntExtra("state", -1) == 1) {
                    Logging.m5305d("BdRTCAudioManager", "headset connected");
                    BdRTCAudioManager.this.setSoundMod(BaiduRtcRoom.RtcSoundMode.RTC_SOUND_MODE_EAR);
                }
            } else if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                if (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) == 10) {
                    BdRTCAudioManager.access$908(BdRTCAudioManager.this);
                    BdRTCAudioManager.this.setBluetoothScoOn(false);
                    if (BdRTCAudioManager.this.audioManager.isWiredHeadsetOn()) {
                        return;
                    }
                    BdRTCAudioManager.this.setSoundMod(BaiduRtcRoom.RtcSoundMode.RTC_SOUND_MODE_SPEAKER);
                }
            } else if ("android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(action)) {
                boolean isBlueToothHeadsetConnected = BdRTCAudioManager.this.isBlueToothHeadsetConnected();
                int intExtra2 = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
                Logging.m5305d("BdRTCAudioManager", "BT headset connect state " + isBlueToothHeadsetConnected + " and sco state update to " + intExtra2);
                if (isBlueToothHeadsetConnected) {
                    BdRTCAudioManager.this.setBluetoothScoOn(true);
                } else {
                    BdRTCAudioManager.this.setBluetoothScoOn(false);
                }
            }
        }
    }

    public boolean isBlueToothHeadsetConnected() {
        try {
            return BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1) != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setSoundMod(BaiduRtcRoom.RtcSoundMode rtcSoundMode) {
        getSysAudioManager();
        if (rtcSoundMode == BaiduRtcRoom.RtcSoundMode.RTC_SOUND_MODE_SPEAKER) {
            setSpeakerPhoneState(true);
        } else if (rtcSoundMode == BaiduRtcRoom.RtcSoundMode.RTC_SOUND_MODE_EAR) {
            setSpeakerPhoneState(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHeadsetOn(Context context) {
        AudioDeviceInfo[] devices;
        getSysAudioManager();
        if (this.audioManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return this.audioManager.isWiredHeadsetOn() || this.audioManager.isBluetoothScoOn() || this.audioManager.isBluetoothA2dpOn();
        }
        for (AudioDeviceInfo audioDeviceInfo : this.audioManager.getDevices(2)) {
            if (audioDeviceInfo.getType() == 3 || audioDeviceInfo.getType() == 4 || audioDeviceInfo.getType() == 8 || audioDeviceInfo.getType() == 7 || audioDeviceInfo.getType() == 11) {
                return true;
            }
        }
        return false;
    }

    public void setHeadSetObserver() {
        this.bluetoothOpSeq = 0;
        this.isBluetoothOn = false;
        this.mHeadSetReceiver = new HeadsetReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        getSysAudioManager();
        try {
            if (this.audioManager.isBluetoothScoAvailableOffCall()) {
                intentFilter.addAction("android.media.SCO_AUDIO_STATE_CHANGED");
                intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mContext.get().registerReceiver(this.mHeadSetReceiver, intentFilter);
    }

    public void presetAudioDevice() {
        getSysAudioManager();
        try {
            if (this.audioManager.isBluetoothScoAvailableOffCall() && this.audioManager.isBluetoothScoOn()) {
                setBluetoothScoOn(true);
            }
        } catch (Exception e) {
            Logging.m5304e("BdRTCAudioManager", "Set bt sco on fail " + e.getMessage());
        }
        if (isHeadsetOn(this.mContext.get())) {
            return;
        }
        presetLoudSpeaker(true);
        Logging.m5305d("BdRTCAudioManager", "Headset No");
    }

    public void disableBluetoothSocMode(boolean z) {
        this.sDisableBTSocMode = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int setBluetoothScoOn(Boolean bool) {
        if (this.sDisableBTSocMode || this.isBluetoothOn.equals(bool)) {
            return 0;
        }
        this.audioManager = getSysAudioManager();
        if (this.audioManager == null) {
            return -1;
        }
        try {
            if (bool.booleanValue()) {
                Logging.m5305d("BdRTCAudioManager", "start bluetooth sco ...");
                this.audioManager.startBluetoothSco();
                this.audioManager.setBluetoothScoOn(bool.booleanValue());
            } else {
                Logging.m5305d("BdRTCAudioManager", "stop bluetooth sco ... ");
                this.audioManager.setBluetoothScoOn(bool.booleanValue());
                this.audioManager.stopBluetoothSco();
            }
            this.isBluetoothOn = bool;
            return 0;
        } catch (Exception e) {
            Logging.m5304e("BdRTCAudioManager", "set bluetooth sco fail: " + e.getMessage());
            return -1;
        }
    }

    public void setExternalMixAudio(ByteBuffer byteBuffer, int i) {
        if (this.isRelease) {
            return;
        }
        if (this.mixAudioTrackIndex <= -1) {
            this.mixAudioTrackIndex = this.mAudioFilter.addSubTrack();
        }
        this.mAudioFilter.getSubInPort(this.mixAudioTrackIndex).onConfigure(Integer.valueOf(this.mixAudioTrackIndex));
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        bufferInfo.size = i;
        bufferInfo.offset = 0;
        this.mAudioFilter.getSubInPort(this.mixAudioTrackIndex).onFrame(new AudioFrameBuffer(byteBuffer, bufferInfo));
    }

    public void setSpeakerPhoneState(boolean z) {
        if (this.mContext != null) {
            getSysAudioManager().setMode(z ? 0 : 3);
            getSysAudioManager().setSpeakerphoneOn(z);
        }
    }

    public void switchLoundSpeaker() {
        boolean z = !getSysAudioManager().isSpeakerphoneOn();
        setSpeakerPhoneState(z);
        RtcLogCapturer.reportLog(RtcLogEvent.SWITCH_LOUND_SPEAKER, "switchLoundSpeaker", Boolean.valueOf(z));
    }

    public void presetLoudSpeaker(boolean z) {
        setSpeakerPhoneState(z);
    }

    public boolean isSpeakerOn() {
        boolean isSpeakerphoneOn = getSysAudioManager().isSpeakerphoneOn();
        Logging.m5305d("BdRTCAudioManager", "isSpeakerOn " + isSpeakerphoneOn);
        return isSpeakerphoneOn;
    }

    public AudioManager getSysAudioManager() {
        if (this.audioManager == null) {
            this.audioManager = (AudioManager) this.mContext.get().getSystemService("audio");
        }
        return this.audioManager;
    }

    public void setStopPublish(boolean z) {
        this.isStopPublish = z;
    }

    public void release() {
        this.isRelease = true;
        getSysAudioManager().setMode(0);
        getSysAudioManager().setSpeakerphoneOn(false);
        AudioFilter audioFilter = this.mAudioFilter;
        if (audioFilter != null) {
            audioFilter.getOutPort().unlinkAll();
            this.mAudioFilter.release();
        }
        BdRTCAudioChangeSink bdRTCAudioChangeSink = this.bdRTCAudioChangeSink;
        if (bdRTCAudioChangeSink != null) {
            bdRTCAudioChangeSink.getOutPort().unlinkAll();
            this.bdRTCAudioChangeSink.release();
        }
        BdRTCAudioEndSink bdRTCAudioEndSink = this.bdAudioEndSink;
        if (bdRTCAudioEndSink != null) {
            bdRTCAudioEndSink.getOutPort().unlinkAll();
            this.bdAudioEndSink.release();
        }
        List<Sink<AudioFrameBuffer>> list = this.sinkList;
        if (list != null) {
            list.clear();
            this.sinkList = null;
        }
        setBluetoothScoOn(false);
        if (this.mHeadSetReceiver != null) {
            this.mContext.get().unregisterReceiver(this.mHeadSetReceiver);
            this.mHeadSetReceiver = null;
        }
        this.mPeerConnectionClient = null;
    }
}
