package com.p281qq.p282e.ads.splash;

import com.p281qq.p282e.comm.util.AdError;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.splash.SplashADListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface SplashADListener {
    void onADClicked();

    void onADDismissed();

    void onADExposure();

    void onADLoaded(long j);

    void onADPresent();

    void onADTick(long j);

    void onNoAD(AdError adError);
}
