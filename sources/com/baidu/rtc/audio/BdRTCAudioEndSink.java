package com.baidu.rtc.audio;

import com.baidu.cloud.framework.InPort;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.Sink;
import com.baidu.cloud.framework.frame.AudioFrameBuffer;
import com.baidu.rtc.RTCAudioSamples;
import com.baidu.rtc.audio.BdRTCAudioManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BdRTCAudioEndSink implements Sink<AudioFrameBuffer> {
    private BRTCAudioProfileParams mAudioProfileParams;
    private RTCAudioSamples.RTCMixedSamplesReadyCallback mMixedSamplesCallback;
    private BdRTCAudioManager.OutAudioSourceToInnerInterface outAudioSampleToInner;
    private boolean isRelease = false;
    private OutPort<AudioFrameBuffer> mOutPort = new OutPortFactory().createOutPort();
    private InPort<AudioFrameBuffer> mMainInPort = new AudioEndInPortFactory().createInPort();

    public BdRTCAudioEndSink(RTCAudioSamples.RTCMixedSamplesReadyCallback rTCMixedSamplesReadyCallback, BRTCAudioProfileParams bRTCAudioProfileParams, BdRTCAudioManager.OutAudioSourceToInnerInterface outAudioSourceToInnerInterface) {
        this.mMixedSamplesCallback = null;
        this.outAudioSampleToInner = null;
        this.mMixedSamplesCallback = rTCMixedSamplesReadyCallback;
        this.mAudioProfileParams = bRTCAudioProfileParams;
        this.outAudioSampleToInner = outAudioSourceToInnerInterface;
    }

    @Override // com.baidu.cloud.framework.Sink
    public InPort<AudioFrameBuffer> getInPort() {
        return this.mMainInPort;
    }

    @Override // com.baidu.cloud.framework.Sink
    public OutPort<AudioFrameBuffer> getOutPort() {
        return this.mOutPort;
    }

    public void setMixedSamplesCallback(RTCAudioSamples.RTCMixedSamplesReadyCallback rTCMixedSamplesReadyCallback) {
        this.mMixedSamplesCallback = rTCMixedSamplesReadyCallback;
    }

    public void release() {
        this.isRelease = true;
        this.outAudioSampleToInner = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioEndInPortFactory implements InPort.Factory<AudioFrameBuffer> {
        private AudioEndInPortFactory() {
        }

        @Override // com.baidu.cloud.framework.InPort.Factory
        public InPort<AudioFrameBuffer> createInPort() {
            return new InPort<AudioFrameBuffer>() { // from class: com.baidu.rtc.audio.BdRTCAudioEndSink.AudioEndInPortFactory.1
                @Override // com.baidu.cloud.framework.IPort
                public void onConfigure(Object obj) {
                }

                @Override // com.baidu.cloud.framework.IPort
                public void onFrame(AudioFrameBuffer audioFrameBuffer) {
                    if (BdRTCAudioEndSink.this.isRelease) {
                        return;
                    }
                    if (BdRTCAudioEndSink.this.outAudioSampleToInner != null) {
                        BdRTCAudioEndSink.this.outAudioSampleToInner.getInnerSampleSetInterface().onWebRtcAudioExternalSamplesReady(new RTCAudioSamples(2, BdRTCAudioEndSink.this.mAudioProfileParams.getOutputAudioChannel(), BdRTCAudioEndSink.this.mAudioProfileParams.getAudioSampleRate(), audioFrameBuffer.buffer.array(), audioFrameBuffer.ptsUs));
                    }
                    if (BdRTCAudioEndSink.this.mMixedSamplesCallback != null) {
                        BdRTCAudioEndSink.this.mMixedSamplesCallback.onRtcAudioMixedSamplesReady(new RTCAudioSamples(2, BdRTCAudioEndSink.this.mAudioProfileParams.getOutputAudioChannel(), BdRTCAudioEndSink.this.mAudioProfileParams.getAudioSampleRate(), audioFrameBuffer.buffer.array(), audioFrameBuffer.ptsUs));
                    }
                }
            };
        }
    }
}
