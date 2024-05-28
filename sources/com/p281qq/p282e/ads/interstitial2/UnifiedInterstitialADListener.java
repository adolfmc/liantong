package com.p281qq.p282e.ads.interstitial2;

import com.p281qq.p282e.comm.util.AdError;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.interstitial2.UnifiedInterstitialADListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface UnifiedInterstitialADListener {
    void onADClicked();

    void onADClosed();

    void onADExposure();

    void onADLeftApplication();

    void onADOpened();

    void onADReceive();

    void onNoAD(AdError adError);

    void onRenderFail();

    void onRenderSuccess();

    void onVideoCached();
}
