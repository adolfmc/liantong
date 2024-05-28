package com.p281qq.p282e.ads.banner2;

import com.p281qq.p282e.comm.util.AdError;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.banner2.UnifiedBannerADListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface UnifiedBannerADListener {
    void onADClicked();

    void onADClosed();

    void onADExposure();

    void onADLeftApplication();

    void onADReceive();

    void onNoAD(AdError adError);
}
