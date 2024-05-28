package com.p281qq.p282e.ads.splash;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import com.p281qq.p282e.ads.LiteAbstractAD;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.adevent.ADEvent;
import com.p281qq.p282e.comm.adevent.ADListener;
import com.p281qq.p282e.comm.constants.LoadAdParams;
import com.p281qq.p282e.comm.listeners.ADRewardListener;
import com.p281qq.p282e.comm.p283pi.IReward;
import com.p281qq.p282e.comm.p283pi.NSPVI;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.HashMap;

/* renamed from: com.qq.e.ads.splash.SplashAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SplashAD extends LiteAbstractAD<NSPVI> implements IReward {

    /* renamed from: g */
    private volatile ViewGroup f17878g;

    /* renamed from: h */
    private volatile SplashADListener f17879h;

    /* renamed from: i */
    private volatile ADRewardListener f17880i;

    /* renamed from: j */
    private volatile LoadAdParams f17881j;

    /* renamed from: k */
    private volatile boolean f17882k;

    /* renamed from: l */
    private volatile boolean f17883l;

    /* renamed from: m */
    private volatile boolean f17884m;

    /* renamed from: n */
    private volatile int f17885n;

    /* renamed from: o */
    private volatile byte[] f17886o;

    /* renamed from: p */
    private volatile ServerSideVerificationOptions f17887p;

    /* renamed from: q */
    private int f17888q;

    /* renamed from: com.qq.e.ads.splash.SplashAD$ADListenerAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class ADListenerAdapter implements ADListener {
        private ADListenerAdapter() {
        }

        @Override // com.p281qq.p282e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            String str;
            if (SplashAD.this.f17879h == null) {
                GDTLogger.m8235d("SplashADListener == null");
                return;
            }
            int type = aDEvent.getType();
            switch (type) {
                case 100:
                    Long l = (Long) aDEvent.getParam(Long.class);
                    if (l != null) {
                        SplashAD.this.f17879h.onADLoaded(l.longValue());
                        return;
                    }
                    return;
                case 101:
                    Integer num = (Integer) aDEvent.getParam(Integer.class);
                    if (num != null) {
                        SplashAD.this.f17879h.onNoAD(AdErrorConvertor.formatErrorCode(num.intValue()));
                        return;
                    }
                    return;
                case 102:
                    SplashAD.this.f17879h.onADPresent();
                    return;
                case 103:
                    SplashAD.this.f17879h.onADExposure();
                    return;
                case 104:
                    if (SplashAD.this.f17880i == null || (str = (String) aDEvent.getParam(String.class)) == null) {
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("transId", str);
                    SplashAD.this.f17880i.onReward(hashMap);
                    return;
                case 105:
                    SplashAD.this.f17879h.onADClicked();
                    return;
                case 106:
                    SplashAD.this.f17879h.onADDismissed();
                    return;
                default:
                    switch (type) {
                        case 112:
                            Long l2 = (Long) aDEvent.getParam(Long.class);
                            if (l2 != null) {
                                SplashAD.this.f17879h.onADTick(l2.longValue());
                                return;
                            }
                            return;
                        case 113:
                            if (SplashAD.this.f17879h instanceof SplashADZoomOutListener) {
                                ((SplashADZoomOutListener) SplashAD.this.f17879h).onZoomOut();
                                return;
                            }
                            return;
                        case 114:
                            if (SplashAD.this.f17879h instanceof SplashADZoomOutListener) {
                                ((SplashADZoomOutListener) SplashAD.this.f17879h).onZoomOutPlayFinish();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    public SplashAD(Context context, String str, SplashADListener splashADListener) {
        this(context, str, splashADListener, 0);
    }

    public SplashAD(Context context, String str, SplashADListener splashADListener, int i) {
        this.f17882k = false;
        this.f17879h = splashADListener;
        this.f17888q = i;
        m8344a(context, str);
    }

    public SplashAD(Context context, String str, SplashADListener splashADListener, int i, String str2) {
        this.f17882k = false;
        this.f17879h = splashADListener;
        this.f17888q = i;
        m8343a(context, str, str2);
    }

    /* renamed from: a */
    private void m8296a(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            GDTLogger.m8234e("传入参数有误：传入container参数为空");
            m8345a(4001);
            return;
        }
        T t = this.f17738a;
        if (t == 0) {
            this.f17884m = z;
            this.f17878g = viewGroup;
        } else if (z) {
            ((NSPVI) t).fetchFullScreenAndShowIn(viewGroup);
        } else {
            ((NSPVI) t).fetchAndShowIn(viewGroup);
        }
    }

    /* renamed from: a */
    private void m8293a(boolean z) {
        if (m8346a()) {
            if (!m8339b()) {
                this.f17884m = z;
                this.f17883l = true;
                return;
            }
            T t = this.f17738a;
            if (t == 0) {
                m8340a("fetchAdInner");
            } else if (z) {
                ((NSPVI) t).fetchFullScreenAdOnly();
            } else {
                ((NSPVI) t).fetchAdOnly();
            }
        }
    }

    /* renamed from: b */
    private void m8291b(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            GDTLogger.m8234e("传入参数错误，container参数为空");
            m8345a(4001);
            return;
        }
        T t = this.f17738a;
        if (t == 0) {
            this.f17878g = viewGroup;
        } else if (z) {
            ((NSPVI) t).showFullScreenAd(viewGroup);
        } else {
            ((NSPVI) t).showAd(viewGroup);
        }
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public Object mo8297a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getNativeSplashAdView(context, str, str2, str3);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: b */
    public void mo8292b(int i) {
        if (this.f17879h != null) {
            this.f17879h.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public void fetchAdOnly() {
        m8293a(false);
    }

    public void fetchAndShowIn(ViewGroup viewGroup) {
        m8296a(viewGroup, false);
    }

    public void fetchFullScreenAdOnly() {
        m8293a(true);
    }

    public void fetchFullScreenAndShowIn(ViewGroup viewGroup) {
        m8296a(viewGroup, true);
    }

    public String getAdNetWorkName() {
        T t = this.f17738a;
        if (t != 0) {
            return ((NSPVI) t).getAdNetWorkName();
        }
        m8340a("getAdNetWorkName");
        return null;
    }

    public Bitmap getZoomOutBitmap() {
        T t = this.f17738a;
        if (t != 0) {
            return ((NSPVI) t).getZoomOutBitmap();
        }
        m8340a("getZoomOutBitmap");
        return null;
    }

    public void preLoad() {
        if (m8346a()) {
            if (!m8339b()) {
                this.f17882k = true;
                return;
            }
            T t = this.f17738a;
            if (t != 0) {
                ((NSPVI) t).preload();
            } else {
                m8340a("preLoad");
            }
        }
    }

    @Deprecated
    public void setAdLogoMargin(int i, int i2) {
    }

    public void setDeveloperLogo(int i) {
        T t = this.f17738a;
        if (t == 0) {
            this.f17885n = i;
        } else {
            ((NSPVI) t).setDeveloperLogo(i);
        }
    }

    public void setDeveloperLogo(byte[] bArr) {
        T t = this.f17738a;
        if (t == 0) {
            this.f17886o = bArr;
        } else {
            ((NSPVI) t).setDeveloperLogo(bArr);
        }
    }

    public void setLoadAdParams(LoadAdParams loadAdParams) {
        T t = this.f17738a;
        if (t != 0) {
            ((NSPVI) t).setLoadAdParams(loadAdParams);
        } else {
            this.f17881j = loadAdParams;
        }
    }

    @Deprecated
    public void setPreloadView(View view) {
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setRewardListener(ADRewardListener aDRewardListener) {
        this.f17880i = aDRewardListener;
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.f17887p = serverSideVerificationOptions;
        T t = this.f17738a;
        if (t != 0) {
            ((NSPVI) t).setServerSideVerificationOptions(serverSideVerificationOptions);
        }
    }

    public void showAd(ViewGroup viewGroup) {
        m8291b(viewGroup, false);
    }

    public void showFullScreenAd(ViewGroup viewGroup) {
        m8291b(viewGroup, true);
    }

    public void zoomOutAnimationFinish() {
        T t = this.f17738a;
        if (t != 0) {
            ((NSPVI) t).zoomOutAnimationFinish();
        } else {
            m8340a("zoomOutAnimationFinish");
        }
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public void mo8294a(Object obj) {
        NSPVI nspvi = (NSPVI) obj;
        if (this.f17881j != null) {
            nspvi.setLoadAdParams(this.f17881j);
        }
        if (this.f17885n != 0) {
            nspvi.setDeveloperLogo(this.f17885n);
        }
        if (this.f17886o != null) {
            nspvi.setDeveloperLogo(this.f17886o);
        }
        nspvi.setFetchDelay(this.f17888q);
        nspvi.setAdListener(new ADListenerAdapter());
        nspvi.setServerSideVerificationOptions(this.f17887p);
        if ((this.f17879h instanceof SplashADZoomOutListener) && ((SplashADZoomOutListener) this.f17879h).isSupportZoomOut()) {
            nspvi.setSupportZoomOut(true);
        }
        if (this.f17878g != null) {
            if (this.f17884m) {
                fetchFullScreenAndShowIn(this.f17878g);
            } else {
                fetchAndShowIn(this.f17878g);
            }
        }
        if (this.f17882k) {
            nspvi.preload();
            this.f17882k = false;
        }
        if (this.f17883l) {
            if (this.f17884m) {
                nspvi.fetchFullScreenAdOnly();
            } else {
                nspvi.fetchAdOnly();
            }
            this.f17883l = false;
        }
    }
}
