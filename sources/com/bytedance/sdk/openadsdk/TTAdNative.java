package com.bytedance.sdk.openadsdk;

import com.bytedance.sdk.openadsdk.common.CommonListener;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface TTAdNative {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface BannerAdListener extends CommonListener {
        void onBannerAdLoad(TTBannerAd tTBannerAd);

        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface CSJSplashAdListener {
        void onSplashLoadFail(CSJAdError cSJAdError);

        void onSplashLoadSuccess();

        void onSplashRenderFail(CSJSplashAd cSJSplashAd, CSJAdError cSJAdError);

        void onSplashRenderSuccess(CSJSplashAd cSJSplashAd);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface DrawFeedAdListener extends CommonListener {
        void onDrawFeedAdLoad(List<TTDrawFeedAd> list);

        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface FeedAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onFeedAdLoad(List<TTFeedAd> list);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface FullScreenVideoAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onFullScreenVideoAdLoad(TTFullScreenVideoAd tTFullScreenVideoAd);

        @Deprecated
        void onFullScreenVideoCached();

        void onFullScreenVideoCached(TTFullScreenVideoAd tTFullScreenVideoAd);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InteractionAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onInteractionAdLoad(TTInteractionAd tTInteractionAd);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface NativeAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onNativeAdLoad(List<TTNativeAd> list);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface NativeExpressAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onNativeExpressAdLoad(List<TTNativeExpressAd> list);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface RewardVideoAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onRewardVideoAdLoad(TTRewardVideoAd tTRewardVideoAd);

        @Deprecated
        void onRewardVideoCached();

        void onRewardVideoCached(TTRewardVideoAd tTRewardVideoAd);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface SplashAdListener extends CommonListener {
        @Override // com.bytedance.sdk.openadsdk.common.CommonListener
        void onError(int i, String str);

        void onSplashAdLoad(TTSplashAd tTSplashAd);

        void onTimeout();
    }

    void loadBannerExpressAd(AdSlot adSlot, NativeExpressAdListener nativeExpressAdListener);

    void loadDrawFeedAd(AdSlot adSlot, DrawFeedAdListener drawFeedAdListener);

    void loadExpressDrawFeedAd(AdSlot adSlot, NativeExpressAdListener nativeExpressAdListener);

    void loadFeedAd(AdSlot adSlot, FeedAdListener feedAdListener);

    void loadFullScreenVideoAd(AdSlot adSlot, FullScreenVideoAdListener fullScreenVideoAdListener);

    @Deprecated
    void loadInteractionExpressAd(AdSlot adSlot, NativeExpressAdListener nativeExpressAdListener);

    void loadNativeAd(AdSlot adSlot, NativeAdListener nativeAdListener);

    void loadNativeExpressAd(AdSlot adSlot, NativeExpressAdListener nativeExpressAdListener);

    void loadRewardVideoAd(AdSlot adSlot, RewardVideoAdListener rewardVideoAdListener);

    void loadSplashAd(AdSlot adSlot, CSJSplashAdListener cSJSplashAdListener, int i);

    @Deprecated
    void loadSplashAd(AdSlot adSlot, SplashAdListener splashAdListener);

    @Deprecated
    void loadSplashAd(AdSlot adSlot, SplashAdListener splashAdListener, int i);

    void loadStream(AdSlot adSlot, FeedAdListener feedAdListener);
}
