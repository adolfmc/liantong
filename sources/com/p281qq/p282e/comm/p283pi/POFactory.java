package com.p281qq.p282e.comm.p283pi;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import com.p281qq.p282e.ads.banner2.UnifiedBannerView;
import com.p281qq.p282e.ads.dfa.GDTAppDialogClickListener;
import com.p281qq.p282e.ads.dfa.IGDTApkListener;
import com.p281qq.p282e.ads.hybrid.HybridADListener;
import com.p281qq.p282e.ads.hybrid.HybridADSetting;
import com.p281qq.p282e.ads.nativ.ADSize;
import com.p281qq.p282e.comm.adevent.ADListener;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.POFactory */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface POFactory extends InnerPOFactory {
    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ SVSD getAPKDownloadServiceDelegate(Service service);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ ACTD getActivityDelegate(String str, Activity activity);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ String getBuyerId(Map<String, Object> map);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ DFA getGDTApkDelegate(IGDTApkListener iGDTApkListener);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ HADI getHybridAD(HybridADSetting hybridADSetting, HybridADListener hybridADListener);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ NUADI getNativeAdManagerDelegate(Context context, String str, String str2, String str3, ADListener aDListener);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ NEADI getNativeExpressADDelegate(Context context, ADSize aDSize, String str, String str2, String str3, ADListener aDListener);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ NSPVI getNativeSplashAdView(Context context, String str, String str2, String str3);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ RVADI getRewardVideoADDelegate(Context context, String str, String str2, String str3, ADListener aDListener);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ String getSDKInfo(String str);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ UBVI getUnifiedBannerViewDelegate(UnifiedBannerView unifiedBannerView, Activity activity, String str, String str2, String str3, ADListener aDListener);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ UIADI getUnifiedInterstitialADDelegate(Activity activity, String str, String str2, String str3, ADListener aDListener);

    @Override // com.p281qq.p282e.comm.p283pi.InnerPOFactory
    /* synthetic */ int showOpenOrInstallAppDialog(GDTAppDialogClickListener gDTAppDialogClickListener);
}
