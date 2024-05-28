package com.p281qq.p282e.comm.p283pi;

import android.app.Activity;
import com.p281qq.p282e.ads.cfg.DownAPPConfirmPolicy;
import com.p281qq.p282e.ads.cfg.VideoOption;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.constants.LoadAdParams;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.UIADI */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface UIADI extends LADI {
    void close();

    void destroy();

    String getAdNetWorkName();

    int getAdPatternType();

    int getVideoDuration();

    void loadAd();

    void loadFullScreenAD();

    void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy);

    void setLoadAdParams(LoadAdParams loadAdParams);

    void setMaxVideoDuration(int i);

    void setMinVideoDuration(int i);

    void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions);

    void setVideoOption(VideoOption videoOption);

    void show();

    void show(Activity activity);

    void showAsPopupWindow();

    void showAsPopupWindow(Activity activity);

    void showFullScreenAD(Activity activity);
}
