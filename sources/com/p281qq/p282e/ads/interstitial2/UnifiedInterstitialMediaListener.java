package com.p281qq.p282e.ads.interstitial2;

import com.p281qq.p282e.comm.util.AdError;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.interstitial2.UnifiedInterstitialMediaListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface UnifiedInterstitialMediaListener {
    void onVideoComplete();

    void onVideoError(AdError adError);

    void onVideoInit();

    void onVideoLoading();

    void onVideoPageClose();

    void onVideoPageOpen();

    void onVideoPause();

    void onVideoReady(long j);

    void onVideoStart();
}
