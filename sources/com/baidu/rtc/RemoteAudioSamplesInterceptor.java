package com.baidu.rtc;

import android.annotation.SuppressLint;
import com.baidu.rtc.RTCAudioSamples;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RemoteAudioSamplesInterceptor implements RTCAudioSamples.RTCRemoteSamplesReadyCallback {
    @SuppressLint({"UseSparseArrays"})
    protected final List<RTCAudioSamples.RTCRemoteSamplesReadyCallback> callbacks = new ArrayList();

    @Override // com.baidu.rtc.RTCAudioSamples.RTCRemoteSamplesReadyCallback
    public void onRtcAudioRemoteSamplesReady(RTCAudioSamples rTCAudioSamples) {
        for (RTCAudioSamples.RTCRemoteSamplesReadyCallback rTCRemoteSamplesReadyCallback : this.callbacks) {
            rTCRemoteSamplesReadyCallback.onRtcAudioRemoteSamplesReady(rTCAudioSamples);
        }
    }

    public void attachCallback(RTCAudioSamples.RTCRemoteSamplesReadyCallback rTCRemoteSamplesReadyCallback) {
        this.callbacks.add(rTCRemoteSamplesReadyCallback);
    }

    public void detachCallback() {
        this.callbacks.clear();
    }
}
