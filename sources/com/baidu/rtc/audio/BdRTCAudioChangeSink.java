package com.baidu.rtc.audio;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.baidu.cloud.framework.InPort;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.Sink;
import com.baidu.cloud.framework.frame.AudioFrameBuffer;
import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.audio.effect.AudioChangeOperator;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BdRTCAudioChangeSink implements Sink<AudioFrameBuffer> {
    private AudioChangeOperator audioChangeOperator;
    private BRTCAudioProfileParams mAudioProfileParams;
    private int mByteWidth;
    private Handler mHandler;
    private InPort<AudioFrameBuffer> mMainInPort = new AudioEffectInPortFactory().createInPort();
    private OutPort<AudioFrameBuffer> mOutPort = new OutPortFactory().createOutPort();
    private boolean enableAudioEffect = true;
    private BaiduRtcRoom.BdRTCVoiceChangeType audioEffectType = BaiduRtcRoom.BdRTCVoiceChangeType.AUDIO_EFFECT_ORIGIN;
    private Thread mEffectThread = null;
    private volatile BlockingQueue<AudioFrameBuffer> audioBufferQueue = null;
    private volatile boolean isRunning = false;

    public void init(BRTCAudioProfileParams bRTCAudioProfileParams, int i) {
        this.audioBufferQueue = new ArrayBlockingQueue(256);
        this.mAudioProfileParams = bRTCAudioProfileParams;
        this.mByteWidth = i;
        this.isRunning = true;
        initChangeOperator();
        this.mEffectThread = new Thread(new Runnable() { // from class: com.baidu.rtc.audio.BdRTCAudioChangeSink.1
            @Override // java.lang.Runnable
            public void run() {
                BdRTCAudioChangeSink.this.initHandler();
            }
        });
        this.mEffectThread.start();
    }

    public void enableAudioEffect(boolean z) {
        this.enableAudioEffect = z;
    }

    public synchronized void setAudioEffectType(BaiduRtcRoom.BdRTCVoiceChangeType bdRTCVoiceChangeType) {
        if (bdRTCVoiceChangeType == null) {
            bdRTCVoiceChangeType = BaiduRtcRoom.BdRTCVoiceChangeType.AUDIO_EFFECT_ORIGIN;
        }
        this.audioEffectType = bdRTCVoiceChangeType;
        initChangeOperator();
        if (this.audioChangeOperator != null) {
            this.audioChangeOperator.setVoiceChangeType(bdRTCVoiceChangeType.ordinal());
        }
    }

    private void initChangeOperator() {
        if (this.audioChangeOperator == null) {
            this.audioChangeOperator = new AudioChangeOperator();
            this.audioChangeOperator.initVoiceChanger(this.mAudioProfileParams.getInputAudioChannel(), this.mAudioProfileParams.getAudioSampleRate(), this.mByteWidth);
        }
    }

    @Override // com.baidu.cloud.framework.Sink
    public InPort<AudioFrameBuffer> getInPort() {
        return this.mMainInPort;
    }

    @Override // com.baidu.cloud.framework.Sink
    public OutPort<AudioFrameBuffer> getOutPort() {
        return this.mOutPort;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initHandler() {
        Looper.prepare();
        this.mHandler = new Handler(Looper.myLooper()) { // from class: com.baidu.rtc.audio.BdRTCAudioChangeSink.2
            @Override // android.os.Handler
            public void handleMessage(@NonNull Message message) {
                super.handleMessage(message);
                if (message.what != 100 || BdRTCAudioChangeSink.this.audioBufferQueue == null || BdRTCAudioChangeSink.this.audioBufferQueue.size() < 1 || BdRTCAudioChangeSink.this.audioChangeOperator == null || !BdRTCAudioChangeSink.this.isRunning) {
                    return;
                }
                try {
                    AudioFrameBuffer audioFrameBuffer = (AudioFrameBuffer) BdRTCAudioChangeSink.this.audioBufferQueue.take();
                    BdRTCAudioChangeSink.this.audioChangeOperator.setBytesWidth(audioFrameBuffer.size);
                    BdRTCAudioChangeSink.this.audioChangeOperator.putBytes(audioFrameBuffer.buffer.array());
                    byte[] bArr = new byte[audioFrameBuffer.buffer.capacity()];
                    BdRTCAudioChangeSink.this.audioChangeOperator.getBytes(bArr);
                    int length = bArr.length;
                    ByteBuffer allocate = ByteBuffer.allocate(length);
                    allocate.put(bArr, 0, length);
                    allocate.flip();
                    BdRTCAudioChangeSink.this.getOutPort().onFrame((OutPort<AudioFrameBuffer>) new AudioFrameBuffer(allocate, audioFrameBuffer.ptsUs, length));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Looper.loop();
    }

    public void release() {
        this.isRunning = false;
        try {
            if (this.mEffectThread != null) {
                this.mEffectThread.interrupt();
                this.mEffectThread.join(1000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        AudioChangeOperator audioChangeOperator = this.audioChangeOperator;
        if (audioChangeOperator != null) {
            audioChangeOperator.finish();
            this.audioChangeOperator.release();
        }
        if (this.audioBufferQueue != null) {
            this.audioBufferQueue.clear();
            this.audioBufferQueue = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioEffectInPortFactory implements InPort.Factory<AudioFrameBuffer> {
        private AudioEffectInPortFactory() {
        }

        @Override // com.baidu.cloud.framework.InPort.Factory
        public InPort<AudioFrameBuffer> createInPort() {
            return new InPort<AudioFrameBuffer>() { // from class: com.baidu.rtc.audio.BdRTCAudioChangeSink.AudioEffectInPortFactory.1
                @Override // com.baidu.cloud.framework.IPort
                public void onConfigure(Object obj) {
                }

                @Override // com.baidu.cloud.framework.IPort
                public void onFrame(AudioFrameBuffer audioFrameBuffer) {
                    try {
                        if (BdRTCAudioChangeSink.this.enableAudioEffect && BdRTCAudioChangeSink.this.audioEffectType != BaiduRtcRoom.BdRTCVoiceChangeType.AUDIO_EFFECT_ORIGIN) {
                            if (BdRTCAudioChangeSink.this.audioBufferQueue != null) {
                                BdRTCAudioChangeSink.this.audioBufferQueue.put(audioFrameBuffer);
                                BdRTCAudioChangeSink.this.mHandler.sendEmptyMessage(100);
                            }
                        } else {
                            BdRTCAudioChangeSink.this.getOutPort().onFrame((OutPort<AudioFrameBuffer>) audioFrameBuffer);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    }
}
