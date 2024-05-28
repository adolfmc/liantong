package com.p281qq.p282e.ads.banner2;

import android.app.Activity;
import android.content.Context;
import com.p281qq.p282e.ads.LiteAbstractAD;
import com.p281qq.p282e.ads.cfg.DownAPPConfirmPolicy;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.constants.LoadAdParams;
import com.p281qq.p282e.comm.listeners.ADRewardListener;
import com.p281qq.p282e.comm.listeners.NegativeFeedbackListener;
import com.p281qq.p282e.comm.p283pi.IReward;
import com.p281qq.p282e.comm.p283pi.NFBI;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.p283pi.UBVI;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.qq.e.ads.banner2.UnifiedBannerAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnifiedBannerAD extends LiteAbstractAD<UBVI> implements IReward, NFBI {

    /* renamed from: g */
    private UnifiedBannerADListener f17757g;

    /* renamed from: h */
    private DownAPPConfirmPolicy f17758h;

    /* renamed from: i */
    private AtomicInteger f17759i;

    /* renamed from: j */
    private int f17760j;

    /* renamed from: k */
    private LoadAdParams f17761k;

    /* renamed from: l */
    private UnifiedBannerView f17762l;

    /* renamed from: m */
    private final ADListenerAdapter f17763m;

    /* renamed from: n */
    private volatile ServerSideVerificationOptions f17764n;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnifiedBannerAD(Activity activity, UnifiedBannerView unifiedBannerView, String str, UnifiedBannerADListener unifiedBannerADListener) {
        this(unifiedBannerView, unifiedBannerADListener);
        m8344a(activity, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnifiedBannerAD(Activity activity, UnifiedBannerView unifiedBannerView, String str, String str2, UnifiedBannerADListener unifiedBannerADListener) {
        this(unifiedBannerView, unifiedBannerADListener);
        m8343a(activity, str, str2);
    }

    private UnifiedBannerAD(UnifiedBannerView unifiedBannerView, UnifiedBannerADListener unifiedBannerADListener) {
        this.f17759i = new AtomicInteger(0);
        this.f17760j = 30;
        this.f17761k = null;
        this.f17757g = unifiedBannerADListener;
        this.f17762l = unifiedBannerView;
        this.f17763m = new ADListenerAdapter(unifiedBannerADListener);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public Object mo8297a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getUnifiedBannerViewDelegate(this.f17762l, (Activity) context, str, str2, str3, this.f17763m);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m8337a(DownAPPConfirmPolicy downAPPConfirmPolicy) {
        T t;
        this.f17758h = downAPPConfirmPolicy;
        if (downAPPConfirmPolicy == null || (t = this.f17738a) == 0) {
            return;
        }
        ((UBVI) t).setDownAPPConfirmPolicy(downAPPConfirmPolicy);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: b */
    public void mo8292b(int i) {
        UnifiedBannerADListener unifiedBannerADListener = this.f17757g;
        if (unifiedBannerADListener != null) {
            unifiedBannerADListener.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m8336c(int i) {
        this.f17760j = i;
        T t = this.f17738a;
        if (t != 0) {
            ((UBVI) t).setRefresh(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroy() {
        T t = this.f17738a;
        if (t != 0) {
            ((UBVI) t).destroy();
        } else {
            m8340a("destroy");
        }
    }

    public String getAdNetWorkName() {
        T t = this.f17738a;
        if (t != 0) {
            return ((UBVI) t).getAdNetWorkName();
        }
        m8340a("getAdNetWorkName");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void loadAD() {
        if (m8346a()) {
            if (!m8339b()) {
                this.f17759i.incrementAndGet();
                return;
            }
            T t = this.f17738a;
            if (t != 0) {
                ((UBVI) t).fetchAd();
            } else {
                m8340a("loadAD");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onWindowFocusChanged(boolean z) {
        T t = this.f17738a;
        if (t != 0) {
            ((UBVI) t).onWindowFocusChanged(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLoadAdParams(LoadAdParams loadAdParams) {
        this.f17761k = loadAdParams;
        T t = this.f17738a;
        if (t != 0) {
            ((UBVI) t).setLoadAdParams(loadAdParams);
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.NFBI
    public void setNegativeFeedbackListener(NegativeFeedbackListener negativeFeedbackListener) {
        this.f17763m.setNegativeFeedbackListener(negativeFeedbackListener);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setRewardListener(ADRewardListener aDRewardListener) {
        this.f17763m.setAdRewardListener(aDRewardListener);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.f17764n = serverSideVerificationOptions;
        T t = this.f17738a;
        if (t != 0) {
            ((UBVI) t).setServerSideVerificationOptions(serverSideVerificationOptions);
        }
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public void mo8294a(Object obj) {
        UBVI ubvi = (UBVI) obj;
        DownAPPConfirmPolicy downAPPConfirmPolicy = this.f17758h;
        if (downAPPConfirmPolicy != null) {
            this.f17758h = downAPPConfirmPolicy;
            T t = this.f17738a;
            if (t != 0) {
                ((UBVI) t).setDownAPPConfirmPolicy(downAPPConfirmPolicy);
            }
        }
        int i = this.f17760j;
        this.f17760j = i;
        T t2 = this.f17738a;
        if (t2 != 0) {
            ((UBVI) t2).setRefresh(i);
        }
        LoadAdParams loadAdParams = this.f17761k;
        this.f17761k = loadAdParams;
        T t3 = this.f17738a;
        if (t3 != 0) {
            ((UBVI) t3).setLoadAdParams(loadAdParams);
        }
        ubvi.setServerSideVerificationOptions(this.f17764n);
        while (this.f17759i.getAndDecrement() > 0) {
            loadAD();
        }
    }
}
