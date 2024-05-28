package com.p281qq.p282e.mediation.interfaces;

import android.app.Activity;
import android.view.View;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.adevent.ADListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.mediation.interfaces.BaseBannerAd */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class BaseBannerAd implements IBaseAd {
    public BaseBannerAd(Activity activity, String str, String str2, String str3) {
    }

    public abstract void destroy();

    public abstract View getAdView();

    public abstract boolean isValid();

    public abstract void loadAD();

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void sendLossNotification(int i, int i2, String str) {
    }

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void sendWinNotification(int i) {
    }

    public abstract void setAdListener(ADListener aDListener);

    public abstract void setAdSize(int i, int i2);

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void setBidECPM(int i) {
    }

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void setPayload(String str) {
    }

    public abstract void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions);
}
