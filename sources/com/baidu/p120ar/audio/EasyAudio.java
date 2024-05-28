package com.baidu.p120ar.audio;

import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.EasyAudio */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EasyAudio implements AudioCallback, IEasyAudio, VolumeListener {
    private static final String TAG = "EasyAudio";
    private static volatile EasyAudio sInstance;
    private AudioController mAudioController;
    private ArrayList<EasyAudioCallback> mCallbackList;
    private ArrayList<VolumeListener> mVolumeListenerList;
    private final Lock mAudioCallbackLock = new ReentrantLock(true);
    private final Lock mVolumeListenerLock = new ReentrantLock(true);

    public static EasyAudio getInstance() {
        if (sInstance == null) {
            synchronized (EasyAudio.class) {
                if (sInstance == null) {
                    sInstance = new EasyAudio();
                }
            }
        }
        return sInstance;
    }

    private static void releaseInstance() {
        sInstance = null;
    }

    private EasyAudio() {
    }

    @Override // com.baidu.p120ar.audio.IEasyAudio
    public void startAudio(AudioParams audioParams, EasyAudioCallback easyAudioCallback) {
        if (audioParams == null || easyAudioCallback == null) {
            ARLog.m20419e(TAG, "AudioParams && EasyAudioCallback can not be null!!!");
            return;
        }
        if (this.mAudioController == null) {
            this.mAudioController = AudioController.getInstance();
        }
        if (this.mCallbackList == null) {
            this.mCallbackList = new ArrayList<>();
        }
        if (this.mCallbackList.contains(easyAudioCallback)) {
            ARLog.m20419e(TAG, "EasyAudio has been started!!!");
            return;
        }
        if (this.mAudioController.isRunning()) {
            easyAudioCallback.onAudioStart(true, this.mAudioController.getAudioParams());
        } else {
            this.mCallbackList.clear();
            this.mAudioController.setupAudio(audioParams, this);
        }
        this.mAudioCallbackLock.lock();
        try {
            if (this.mCallbackList != null) {
                this.mCallbackList.add(easyAudioCallback);
            }
        } finally {
            this.mAudioCallbackLock.unlock();
        }
    }

    @Override // com.baidu.p120ar.audio.IEasyAudio
    public void setVolumeListener(VolumeListener volumeListener) {
        if (volumeListener == null) {
            ARLog.m20419e(TAG, "VolumeListener can not be null!!!");
            return;
        }
        if (this.mVolumeListenerList == null) {
            this.mVolumeListenerList = new ArrayList<>();
        }
        if (this.mVolumeListenerList.contains(volumeListener)) {
            ARLog.m20419e(TAG, "setVolumeListener volumeListener has been added!!!");
            return;
        }
        if (this.mAudioController == null) {
            this.mAudioController = AudioController.getInstance();
        }
        this.mVolumeListenerLock.lock();
        try {
            if (this.mVolumeListenerList.size() == 0) {
                this.mAudioController.setVolumeListener(this);
            }
            this.mVolumeListenerList.add(volumeListener);
        } finally {
            this.mVolumeListenerLock.unlock();
        }
    }

    @Override // com.baidu.p120ar.audio.IEasyAudio
    public void removeVolumeListener(VolumeListener volumeListener) {
        if (volumeListener == null) {
            ARLog.m20419e(TAG, "VolumeListener can not be null!!!");
            return;
        }
        this.mVolumeListenerLock.lock();
        try {
            if (this.mVolumeListenerList != null && this.mVolumeListenerList.size() > 0 && this.mVolumeListenerList.contains(volumeListener)) {
                this.mVolumeListenerList.remove(volumeListener);
            }
        } finally {
            this.mVolumeListenerLock.unlock();
        }
    }

    @Override // com.baidu.p120ar.audio.IEasyAudio
    public void stopAudio(EasyAudioCallback easyAudioCallback) {
        if (easyAudioCallback == null) {
            ARLog.m20419e(TAG, "EasyAudioCallback can not be null!!!");
            return;
        }
        ArrayList<EasyAudioCallback> arrayList = this.mCallbackList;
        if (arrayList != null && arrayList.contains(easyAudioCallback)) {
            if (this.mCallbackList.size() > 1) {
                this.mAudioCallbackLock.lock();
                try {
                    boolean remove = this.mCallbackList.remove(easyAudioCallback);
                    this.mAudioCallbackLock.unlock();
                    easyAudioCallback.onAudioStop(remove);
                    return;
                } catch (Throwable th) {
                    this.mAudioCallbackLock.unlock();
                    throw th;
                }
            }
            stopAndReleaseAudioController();
            return;
        }
        ARLog.m20419e(TAG, "Please confirm EasyAudio has been started!!!");
    }

    @Override // com.baidu.p120ar.audio.IEasyAudio
    public void release() {
        stopAndReleaseAudioController();
        releaseEasyAudio();
    }

    private synchronized void stopAndReleaseAudioController() {
        if (this.mAudioController != null) {
            this.mAudioController.stopAudio();
            this.mAudioController.releaseAudio();
            this.mAudioController = null;
        }
    }

    @Override // com.baidu.p120ar.audio.AudioCallback
    public void onAudioSetup(boolean z) {
        if (z) {
            AudioController audioController = this.mAudioController;
            if (audioController != null) {
                audioController.startAudio();
                return;
            }
            return;
        }
        ArrayList<EasyAudioCallback> arrayList = this.mCallbackList;
        if (arrayList == null || arrayList.get(0) == null) {
            return;
        }
        this.mCallbackList.get(0).onAudioStart(false, null);
        release();
    }

    @Override // com.baidu.p120ar.audio.AudioCallback
    public void onAudioStart(boolean z) {
        ArrayList<EasyAudioCallback> arrayList = this.mCallbackList;
        if (arrayList != null && arrayList.get(0) != null && this.mAudioController != null) {
            this.mCallbackList.get(0).onAudioStart(z, this.mAudioController.getAudioParams());
        }
        if (z) {
            return;
        }
        release();
    }

    @Override // com.baidu.p120ar.audio.AudioCallback
    public void onAudioFrameAvailable(ByteBuffer byteBuffer, int i, long j) {
        this.mAudioCallbackLock.lock();
        try {
            if (this.mCallbackList != null) {
                Iterator<EasyAudioCallback> it = this.mCallbackList.iterator();
                while (it.hasNext()) {
                    it.next().onAudioFrameAvailable(byteBuffer, i, j);
                }
            }
        } finally {
            this.mAudioCallbackLock.unlock();
        }
    }

    @Override // com.baidu.p120ar.audio.AudioCallback
    public void onAudioStop(boolean z) {
        ArrayList<EasyAudioCallback> arrayList = this.mCallbackList;
        if (arrayList == null || arrayList.get(0) == null) {
            return;
        }
        this.mCallbackList.get(0).onAudioStop(z);
    }

    @Override // com.baidu.p120ar.audio.AudioCallback
    public void onAudioRelease() {
        releaseEasyAudio();
    }

    private synchronized void releaseEasyAudio() {
        this.mAudioCallbackLock.lock();
        if (this.mCallbackList != null) {
            this.mCallbackList.clear();
            this.mCallbackList = null;
        }
        this.mAudioCallbackLock.unlock();
        if (this.mVolumeListenerList != null) {
            this.mVolumeListenerList.clear();
            this.mVolumeListenerList = null;
        }
        releaseInstance();
    }

    @Override // com.baidu.p120ar.audio.VolumeListener
    public void onRealtimeVolume(int i) {
        this.mVolumeListenerLock.lock();
        try {
            if (this.mVolumeListenerList != null) {
                Iterator<VolumeListener> it = this.mVolumeListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onRealtimeVolume(i);
                }
            }
        } finally {
            this.mVolumeListenerLock.unlock();
        }
    }
}
