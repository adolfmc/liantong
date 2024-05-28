package com.bytedance.sdk.openadsdk;

import android.view.View;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TTSplashAd extends TTClientBidding {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface AdInteractionListener {
        void onAdClicked(View view, int i);

        void onAdShow(View view, int i);

        void onAdSkip();

        void onAdTimeOver();
    }

    int getInteractionType();

    Map<String, Object> getMediaExtraInfo();

    int[] getSplashClickEyeSizeToDp();

    View getSplashView();

    void renderExpressAd(TTNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener);

    void setDownloadListener(TTAppDownloadListener tTAppDownloadListener);

    void setNotAllowSdkCountdown();

    void setSplashCardListener(ISplashCardListener iSplashCardListener);

    void setSplashClickEyeListener(ISplashClickEyeListener iSplashClickEyeListener);

    void setSplashInteractionListener(AdInteractionListener adInteractionListener);

    void splashClickEyeAnimationFinish();

    void startClickEye();

    void startClickEye(boolean z);
}
