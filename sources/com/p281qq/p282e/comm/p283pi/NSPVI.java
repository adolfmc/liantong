package com.p281qq.p282e.comm.p283pi;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.adevent.ADListener;
import com.p281qq.p282e.comm.constants.LoadAdParams;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.NSPVI */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface NSPVI extends LADI {
    void fetchAdOnly();

    void fetchAndShowIn(ViewGroup viewGroup);

    void fetchFullScreenAdOnly();

    void fetchFullScreenAndShowIn(ViewGroup viewGroup);

    String getAdNetWorkName();

    Bitmap getZoomOutBitmap();

    void preload();

    void setAdListener(ADListener aDListener);

    void setDeveloperLogo(int i);

    void setDeveloperLogo(byte[] bArr);

    void setFetchDelay(int i);

    void setLoadAdParams(LoadAdParams loadAdParams);

    void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions);

    @Deprecated
    void setSkipView(View view);

    void setSupportZoomOut(boolean z);

    void showAd(ViewGroup viewGroup);

    void showFullScreenAd(ViewGroup viewGroup);

    void zoomOutAnimationFinish();
}
