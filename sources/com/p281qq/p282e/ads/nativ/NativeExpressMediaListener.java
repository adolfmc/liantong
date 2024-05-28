package com.p281qq.p282e.ads.nativ;

import com.p281qq.p282e.comm.util.AdError;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.nativ.NativeExpressMediaListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface NativeExpressMediaListener {
    void onVideoCached(NativeExpressADView nativeExpressADView);

    void onVideoComplete(NativeExpressADView nativeExpressADView);

    void onVideoError(NativeExpressADView nativeExpressADView, AdError adError);

    void onVideoInit(NativeExpressADView nativeExpressADView);

    void onVideoLoading(NativeExpressADView nativeExpressADView);

    void onVideoPageClose(NativeExpressADView nativeExpressADView);

    void onVideoPageOpen(NativeExpressADView nativeExpressADView);

    void onVideoPause(NativeExpressADView nativeExpressADView);

    void onVideoReady(NativeExpressADView nativeExpressADView, long j);

    void onVideoStart(NativeExpressADView nativeExpressADView);
}
