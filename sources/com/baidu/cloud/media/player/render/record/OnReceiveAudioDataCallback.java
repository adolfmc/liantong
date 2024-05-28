package com.baidu.cloud.media.player.render.record;

import com.baidu.cloud.media.player.annotations.CalledByNative;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface OnReceiveAudioDataCallback {
    @CalledByNative
    void onReceivePlayingAudioData(byte[] bArr, int i);
}
