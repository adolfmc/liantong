package com.p281qq.p282e.ads.rewardvideo;

import android.app.Activity;
import android.content.Context;
import com.p281qq.p282e.ads.LiteAbstractAD;
import com.p281qq.p282e.comm.adevent.ADEvent;
import com.p281qq.p282e.comm.adevent.ADListener;
import com.p281qq.p282e.comm.constants.LoadAdParams;
import com.p281qq.p282e.comm.listeners.NegativeFeedbackListener;
import com.p281qq.p282e.comm.p283pi.NFBI;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.p283pi.RVADI;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import java.util.HashMap;

/* renamed from: com.qq.e.ads.rewardvideo.RewardVideoAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RewardVideoAD extends LiteAbstractAD<RVADI> implements NFBI {
    public static final int REWARD_TYPE_PAGE = 1;
    public static final int REWARD_TYPE_VIDEO = 0;

    /* renamed from: g */
    private final RewardVideoADListener f17866g;

    /* renamed from: h */
    private volatile boolean f17867h;

    /* renamed from: i */
    private LoadAdParams f17868i;

    /* renamed from: j */
    private ServerSideVerificationOptions f17869j;

    /* renamed from: k */
    private final boolean f17870k;

    /* renamed from: l */
    private final ADListenerAdapter f17871l;

    /* renamed from: com.qq.e.ads.rewardvideo.RewardVideoAD$ADListenerAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ADListenerAdapter implements ADListener {

        /* renamed from: a */
        private NegativeFeedbackListener f17872a;
        public RewardVideoADListener adListener;

        public ADListenerAdapter(RewardVideoADListener rewardVideoADListener) {
            this.adListener = rewardVideoADListener;
        }

        /* renamed from: a */
        static void m8300a(ADListenerAdapter aDListenerAdapter, NegativeFeedbackListener negativeFeedbackListener) {
            aDListenerAdapter.f17872a = negativeFeedbackListener;
        }

        @Override // com.p281qq.p282e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            int type = aDEvent.getType();
            if (type == 100) {
                this.adListener.onADLoad();
            } else if (type == 201) {
                this.adListener.onVideoCached();
            } else if (type == 206) {
                this.adListener.onVideoComplete();
            } else if (type == 304) {
                NegativeFeedbackListener negativeFeedbackListener = this.f17872a;
                if (negativeFeedbackListener != null) {
                    negativeFeedbackListener.onComplainSuccess();
                }
            } else {
                switch (type) {
                    case 102:
                        this.adListener.onADShow();
                        return;
                    case 103:
                        this.adListener.onADExpose();
                        return;
                    case 104:
                        String str = (String) aDEvent.getParam(String.class);
                        if (str != null) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("transId", str);
                            this.adListener.onReward(hashMap);
                            return;
                        }
                        return;
                    case 105:
                        this.adListener.onADClick();
                        return;
                    case 106:
                        this.adListener.onADClose();
                        return;
                    case 107:
                        Integer num = (Integer) aDEvent.getParam(Integer.class);
                        if (num != null) {
                            this.adListener.onError(AdErrorConvertor.formatErrorCode(num.intValue()));
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public RewardVideoAD(Context context, String str, RewardVideoADListener rewardVideoADListener) {
        this(context, str, rewardVideoADListener, true);
    }

    public RewardVideoAD(Context context, String str, RewardVideoADListener rewardVideoADListener, boolean z) {
        this(rewardVideoADListener, z);
        m8344a(context, str);
    }

    public RewardVideoAD(Context context, String str, RewardVideoADListener rewardVideoADListener, boolean z, String str2) {
        this(rewardVideoADListener, z);
        m8343a(context, str, str2);
    }

    private RewardVideoAD(RewardVideoADListener rewardVideoADListener, boolean z) {
        this.f17868i = null;
        this.f17870k = z;
        this.f17866g = rewardVideoADListener;
        this.f17871l = new ADListenerAdapter(rewardVideoADListener);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public Object mo8297a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getRewardVideoADDelegate(context, str, str2, str3, this.f17871l);
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: b */
    public void mo8292b(int i) {
        RewardVideoADListener rewardVideoADListener = this.f17866g;
        if (rewardVideoADListener != null) {
            rewardVideoADListener.onError(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public String getAdNetWorkName() {
        T t = this.f17738a;
        if (t != 0) {
            return ((RVADI) t).getAdNetWorkName();
        }
        m8340a("getAdNetWorkName");
        return null;
    }

    public int getRewardAdType() {
        T t = this.f17738a;
        if (t != 0) {
            return ((RVADI) t).getRewardAdType();
        }
        m8340a("getRewardAdType");
        return 0;
    }

    public int getVideoDuration() {
        T t = this.f17738a;
        if (t != 0) {
            return ((RVADI) t).getVideoDuration();
        }
        m8340a("getVideoDuration");
        return 0;
    }

    public boolean hasShown() {
        T t = this.f17738a;
        if (t != 0) {
            return ((RVADI) t).hasShown();
        }
        m8340a("hasShown");
        return false;
    }

    public void loadAD() {
        if (m8346a()) {
            if (!m8339b()) {
                this.f17867h = true;
                return;
            }
            T t = this.f17738a;
            if (t != 0) {
                ((RVADI) t).loadAD();
            } else {
                m8340a("loadAD");
            }
        }
    }

    public void setLoadAdParams(LoadAdParams loadAdParams) {
        this.f17868i = loadAdParams;
        T t = this.f17738a;
        if (t != 0) {
            ((RVADI) t).setLoadAdParams(loadAdParams);
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.NFBI
    public void setNegativeFeedbackListener(NegativeFeedbackListener negativeFeedbackListener) {
        ADListenerAdapter.m8300a(this.f17871l, negativeFeedbackListener);
    }

    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.f17869j = serverSideVerificationOptions;
        T t = this.f17738a;
        if (t != 0) {
            ((RVADI) t).setServerSideVerificationOptions(serverSideVerificationOptions);
        }
    }

    public void showAD() {
        T t = this.f17738a;
        if (t != 0) {
            ((RVADI) t).showAD();
        } else {
            m8340a("showAD");
        }
    }

    public void showAD(Activity activity) {
        T t = this.f17738a;
        if (t != 0) {
            ((RVADI) t).showAD(activity);
        } else {
            m8340a("showAD");
        }
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public void mo8294a(Object obj) {
        RVADI rvadi = (RVADI) obj;
        rvadi.setVolumeOn(this.f17870k);
        rvadi.setLoadAdParams(this.f17868i);
        rvadi.setServerSideVerificationOptions(this.f17869j);
        if (this.f17867h) {
            loadAD();
        }
    }
}
