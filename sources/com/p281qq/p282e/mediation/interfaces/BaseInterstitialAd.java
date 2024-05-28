package com.p281qq.p282e.mediation.interfaces;

import android.app.Activity;
import com.p281qq.p282e.ads.cfg.DownAPPConfirmPolicy;
import com.p281qq.p282e.ads.cfg.VideoOption;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.adevent.ADListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.mediation.interfaces.BaseInterstitialAd */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class BaseInterstitialAd implements IBaseAd {
    public static final int DEFAULT_PRIORITY = -1;

    public BaseInterstitialAd(Activity activity, String str, String str2, String str3) {
    }

    public abstract void close();

    public abstract void destory();

    public abstract int getAdPatternType();

    public int getAdapterPriority() {
        return -1;
    }

    public abstract String getECPMLevel();

    public abstract int getVideoDuration();

    public abstract boolean isValid();

    public abstract void loadAd();

    public abstract void loadFullScreenAD();

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

    public abstract void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy);

    public abstract void setMaxVideoDuration(int i);

    public abstract void setMinVideoDuration(int i);

    @Override // com.p281qq.p282e.mediation.interfaces.IBaseAd
    public void setPayload(String str) {
    }

    public abstract void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions);

    public abstract void setVideoOption(VideoOption videoOption);

    public abstract void show();

    public abstract void show(Activity activity);

    public abstract void showAsPopupWindow();

    public abstract void showAsPopupWindow(Activity activity);

    public abstract void showFullScreenAD(Activity activity);
}
