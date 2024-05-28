package com.p281qq.p282e.mediation.interfaces;

import android.app.Activity;
import android.content.Context;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.adevent.ADListener;
import com.p281qq.p282e.comm.constants.LoadAdParams;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.mediation.interfaces.BaseRewardAd */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class BaseRewardAd implements IBaseAd {
    public static final int DEFAULT_PRIORITY = -1;

    /* renamed from: a */
    private LoadAdParams f17977a = null;

    public BaseRewardAd(Context context, String str, String str2, String str3) {
    }

    public int getAdapterPriority() {
        return -1;
    }

    public abstract String getECPMLevel();

    @Deprecated
    public abstract long getExpireTimestamp();

    public LoadAdParams getLoadAdParams() {
        return this.f17977a;
    }

    public int getRewardAdType() {
        return 0;
    }

    public abstract int getVideoDuration();

    public abstract boolean hasShown();

    public abstract boolean isValid();

    public abstract void loadAD();

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void sendLossNotification(int i, int i2, String str) {
    }

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void sendWinNotification(int i) {
    }

    public abstract void setAdListener(ADListener aDListener);

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void setBidECPM(int i) {
    }

    public void setLoadAdParams(LoadAdParams loadAdParams) {
        this.f17977a = loadAdParams;
    }

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void setPayload(String str) {
    }

    public abstract void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions);

    public abstract void setVolumeOn(boolean z);

    public abstract void showAD();

    public void showAD(Activity activity) {
        showAD();
    }
}
