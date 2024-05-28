package com.p281qq.p282e.ads.rewardvideo;

import com.p281qq.p282e.comm.util.AdError;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.rewardvideo.RewardVideoADListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface RewardVideoADListener {
    void onADClick();

    void onADClose();

    void onADExpose();

    void onADLoad();

    void onADShow();

    void onError(AdError adError);

    void onReward(Map<String, Object> map);

    void onVideoCached();

    void onVideoComplete();
}
