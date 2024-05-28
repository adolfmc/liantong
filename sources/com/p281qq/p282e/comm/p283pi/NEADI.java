package com.p281qq.p282e.comm.p283pi;

import com.p281qq.p282e.ads.cfg.VideoOption;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.constants.LoadAdParams;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.NEADI */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface NEADI extends ADI {
    String getAdNetWorkName();

    void loadAd(int i);

    void loadAd(int i, LoadAdParams loadAdParams);

    void setMaxVideoDuration(int i);

    void setMinVideoDuration(int i);

    void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions);

    void setVideoOption(VideoOption videoOption);
}
