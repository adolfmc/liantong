package com.p281qq.p282e.comm.p283pi;

import android.app.Activity;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.constants.LoadAdParams;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.RVADI */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface RVADI extends LADI {
    String getAdNetWorkName();

    @Deprecated
    long getExpireTimestamp();

    int getRewardAdType();

    int getVideoDuration();

    boolean hasShown();

    void loadAD();

    void setLoadAdParams(LoadAdParams loadAdParams);

    void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions);

    void setVolumeOn(boolean z);

    void showAD();

    void showAD(Activity activity);
}
