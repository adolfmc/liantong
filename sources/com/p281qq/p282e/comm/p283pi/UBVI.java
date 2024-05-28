package com.p281qq.p282e.comm.p283pi;

import com.p281qq.p282e.ads.cfg.DownAPPConfirmPolicy;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.constants.LoadAdParams;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.UBVI */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface UBVI extends LADI {
    void destroy();

    void fetchAd();

    String getAdNetWorkName();

    void onWindowFocusChanged(boolean z);

    void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy);

    void setLoadAdParams(LoadAdParams loadAdParams);

    void setRefresh(int i);

    void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions);
}
