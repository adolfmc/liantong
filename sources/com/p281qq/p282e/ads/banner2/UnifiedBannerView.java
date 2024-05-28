package com.p281qq.p282e.ads.banner2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.p281qq.p282e.ads.cfg.DownAPPConfirmPolicy;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.compliance.ApkDownloadComplianceInterface;
import com.p281qq.p282e.comm.compliance.DownloadConfirmListener;
import com.p281qq.p282e.comm.constants.LoadAdParams;
import com.p281qq.p282e.comm.listeners.ADRewardListener;
import com.p281qq.p282e.comm.listeners.NegativeFeedbackListener;
import com.p281qq.p282e.comm.p283pi.IBidding;
import com.p281qq.p282e.comm.p283pi.IReward;
import com.p281qq.p282e.comm.p283pi.NFBI;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.Map;

@SuppressLint({"ViewConstructor"})
/* renamed from: com.qq.e.ads.banner2.UnifiedBannerView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnifiedBannerView extends FrameLayout implements ApkDownloadComplianceInterface, IBidding, IReward, NFBI {

    /* renamed from: a */
    final UnifiedBannerAD f17765a;

    public UnifiedBannerView(Activity activity, String str, UnifiedBannerADListener unifiedBannerADListener) {
        this(activity, str, unifiedBannerADListener, null);
    }

    public UnifiedBannerView(Activity activity, String str, UnifiedBannerADListener unifiedBannerADListener, Map map) {
        super(activity);
        this.f17765a = new UnifiedBannerAD(activity, this, str, unifiedBannerADListener);
        m8335a();
    }

    public UnifiedBannerView(Activity activity, String str, UnifiedBannerADListener unifiedBannerADListener, Map map, String str2) {
        super(activity);
        if (TextUtils.isEmpty(str2)) {
            GDTLogger.m8234e(UnifiedBannerView.class.getSimpleName() + "构造函数中 token 参数不可为空");
        }
        this.f17765a = new UnifiedBannerAD(activity, this, str, str2, unifiedBannerADListener);
        m8335a();
    }

    /* renamed from: a */
    private void m8335a() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
    }

    public void destroy() {
        this.f17765a.destroy();
    }

    public String getAdNetWorkName() {
        return this.f17765a.getAdNetWorkName();
    }

    @Override // com.p281qq.p282e.comm.compliance.ApkDownloadComplianceInterface
    public String getApkInfoUrl() {
        return this.f17765a.getApkInfoUrl();
    }

    public int getECPM() {
        return this.f17765a.getECPM();
    }

    public String getECPMLevel() {
        return this.f17765a.getECPMLevel();
    }

    public Map<String, Object> getExtraInfo() {
        return this.f17765a.getExtraInfo();
    }

    public boolean isValid() {
        return this.f17765a.isValid();
    }

    public void loadAD() {
        this.f17765a.loadAD();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.f17765a.onWindowFocusChanged(z);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendLossNotification(int i, int i2, String str) {
        this.f17765a.sendLossNotification(i, i2, str);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendLossNotification(Map<String, Object> map) {
        this.f17765a.sendLossNotification(map);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendWinNotification(int i) {
        this.f17765a.sendWinNotification(i);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendWinNotification(Map<String, Object> map) {
        this.f17765a.sendWinNotification(map);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void setBidECPM(int i) {
        this.f17765a.setBidECPM(i);
    }

    public void setDownConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        this.f17765a.m8337a(downAPPConfirmPolicy);
    }

    @Override // com.p281qq.p282e.comm.compliance.ApkDownloadComplianceInterface
    public void setDownloadConfirmListener(DownloadConfirmListener downloadConfirmListener) {
        this.f17765a.setDownloadConfirmListener(downloadConfirmListener);
    }

    public void setLoadAdParams(LoadAdParams loadAdParams) {
        this.f17765a.setLoadAdParams(loadAdParams);
    }

    @Override // com.p281qq.p282e.comm.p283pi.NFBI
    public void setNegativeFeedbackListener(NegativeFeedbackListener negativeFeedbackListener) {
        this.f17765a.setNegativeFeedbackListener(negativeFeedbackListener);
    }

    public void setRefresh(int i) {
        this.f17765a.m8336c(i);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setRewardListener(ADRewardListener aDRewardListener) {
        this.f17765a.setRewardListener(aDRewardListener);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.f17765a.setServerSideVerificationOptions(serverSideVerificationOptions);
    }
}
