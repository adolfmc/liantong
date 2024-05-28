package com.p281qq.p282e.ads.interstitial2;

import android.app.Activity;
import android.content.Context;
import com.p281qq.p282e.ads.LiteAbstractAD;
import com.p281qq.p282e.ads.cfg.VideoOption;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.constants.LoadAdParams;
import com.p281qq.p282e.comm.listeners.ADRewardListener;
import com.p281qq.p282e.comm.listeners.NegativeFeedbackListener;
import com.p281qq.p282e.comm.p283pi.IReward;
import com.p281qq.p282e.comm.p283pi.NFBI;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.p283pi.UIADI;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.qq.e.ads.interstitial2.UnifiedInterstitialAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnifiedInterstitialAD extends LiteAbstractAD<UIADI> implements IReward, NFBI {

    /* renamed from: g */
    private AtomicInteger f17817g;

    /* renamed from: h */
    private AtomicInteger f17818h;

    /* renamed from: i */
    private volatile VideoOption f17819i;

    /* renamed from: j */
    private volatile int f17820j;

    /* renamed from: k */
    private volatile int f17821k;

    /* renamed from: l */
    private volatile LoadAdParams f17822l;

    /* renamed from: m */
    private UnifiedInterstitialADListener f17823m;

    /* renamed from: n */
    private ServerSideVerificationOptions f17824n;

    /* renamed from: o */
    private final ADListenerAdapter f17825o;

    public UnifiedInterstitialAD(Activity activity, String str, UnifiedInterstitialADListener unifiedInterstitialADListener) {
        this(activity, str, unifiedInterstitialADListener, null);
    }

    public UnifiedInterstitialAD(Activity activity, String str, UnifiedInterstitialADListener unifiedInterstitialADListener, Map map) {
        this.f17817g = new AtomicInteger(0);
        this.f17818h = new AtomicInteger(0);
        this.f17823m = unifiedInterstitialADListener;
        this.f17825o = new ADListenerAdapter(unifiedInterstitialADListener);
        m8344a(activity, str);
    }

    public UnifiedInterstitialAD(Activity activity, String str, UnifiedInterstitialADListener unifiedInterstitialADListener, Map map, String str2) {
        this.f17817g = new AtomicInteger(0);
        this.f17818h = new AtomicInteger(0);
        this.f17823m = unifiedInterstitialADListener;
        this.f17825o = new ADListenerAdapter(unifiedInterstitialADListener);
        m8343a(activity, str, str2);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public Object mo8297a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getUnifiedInterstitialADDelegate((Activity) context, str, str2, str3, this.f17825o);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public /* synthetic */ void mo8294a(Object obj) {
        UIADI uiadi = (UIADI) obj;
        m8313c();
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: b */
    public void mo8292b(int i) {
        UnifiedInterstitialADListener unifiedInterstitialADListener = this.f17823m;
        if (unifiedInterstitialADListener != null) {
            unifiedInterstitialADListener.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    /* renamed from: c */
    protected void m8313c() {
        setVideoOption(this.f17819i);
        setMinVideoDuration(this.f17820j);
        setMaxVideoDuration(this.f17821k);
        setLoadAdParams(this.f17822l);
        setServerSideVerificationOptions(this.f17824n);
        while (this.f17817g.getAndDecrement() > 0) {
            loadAD();
        }
        while (this.f17818h.getAndDecrement() > 0) {
            loadFullScreenAD();
        }
    }

    public void close() {
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).close();
        }
    }

    public void destroy() {
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).destroy();
        } else {
            m8340a("destroy");
        }
    }

    public String getAdNetWorkName() {
        T t = this.f17738a;
        if (t != 0) {
            return ((UIADI) t).getAdNetWorkName();
        }
        m8340a("getAdNetWorkName");
        return null;
    }

    public int getAdPatternType() {
        T t = this.f17738a;
        if (t != 0) {
            return ((UIADI) t).getAdPatternType();
        }
        m8340a("getAdPatternType");
        return 0;
    }

    public int getVideoDuration() {
        T t = this.f17738a;
        if (t != 0) {
            return ((UIADI) t).getVideoDuration();
        }
        m8340a("getVideoDuration");
        return 0;
    }

    public void loadAD() {
        if (m8346a()) {
            if (!m8339b()) {
                this.f17817g.incrementAndGet();
                return;
            }
            T t = this.f17738a;
            if (t != 0) {
                ((UIADI) t).loadAd();
            } else {
                m8340a("loadAD");
            }
        }
    }

    public void loadFullScreenAD() {
        if (m8346a()) {
            if (!m8339b()) {
                this.f17818h.incrementAndGet();
                return;
            }
            T t = this.f17738a;
            if (t != 0) {
                ((UIADI) t).loadFullScreenAD();
            } else {
                m8340a("loadFullScreenAD");
            }
        }
    }

    public void setLoadAdParams(LoadAdParams loadAdParams) {
        this.f17822l = loadAdParams;
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).setLoadAdParams(this.f17822l);
        }
    }

    public void setMaxVideoDuration(int i) {
        this.f17821k = i;
        if (this.f17821k > 0 && this.f17820j > this.f17821k) {
            GDTLogger.m8234e("maxVideoDuration 设置值非法，不得小于minVideoDuration");
        }
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).setMaxVideoDuration(i);
        }
    }

    public void setMediaListener(UnifiedInterstitialMediaListener unifiedInterstitialMediaListener) {
        this.f17825o.setMediaListener(unifiedInterstitialMediaListener);
    }

    public void setMinVideoDuration(int i) {
        this.f17820j = i;
        if (this.f17821k > 0 && this.f17820j > this.f17821k) {
            GDTLogger.m8234e("minVideoDuration 设置值非法，不得大于maxVideoDuration");
        }
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).setMinVideoDuration(i);
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.NFBI
    public void setNegativeFeedbackListener(NegativeFeedbackListener negativeFeedbackListener) {
        this.f17825o.setNegativeFeedbackListener(negativeFeedbackListener);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setRewardListener(ADRewardListener aDRewardListener) {
        this.f17825o.setAdRewardListener(aDRewardListener);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.f17824n = serverSideVerificationOptions;
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).setServerSideVerificationOptions(serverSideVerificationOptions);
        }
    }

    public void setVideoOption(VideoOption videoOption) {
        this.f17819i = videoOption;
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).setVideoOption(videoOption);
        }
    }

    public void show() {
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).show();
        } else {
            m8340a("show");
        }
    }

    public void show(Activity activity) {
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).show(activity);
        } else {
            m8340a("show");
        }
    }

    public void showAsPopupWindow() {
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).showAsPopupWindow();
        } else {
            m8340a("showAsPopupWindow");
        }
    }

    public void showAsPopupWindow(Activity activity) {
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).showAsPopupWindow(activity);
        } else {
            m8340a("showAsPopupWindow");
        }
    }

    public void showFullScreenAD(Activity activity) {
        T t = this.f17738a;
        if (t != 0) {
            ((UIADI) t).showFullScreenAD(activity);
        } else {
            m8340a("showFullScreenAD");
        }
    }
}
