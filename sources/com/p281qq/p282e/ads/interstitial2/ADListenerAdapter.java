package com.p281qq.p282e.ads.interstitial2;

import com.p281qq.p282e.comm.adevent.ADEvent;
import com.p281qq.p282e.comm.adevent.ADListener;
import com.p281qq.p282e.comm.listeners.ADRewardListener;
import com.p281qq.p282e.comm.listeners.NegativeFeedbackListener;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import com.p281qq.p282e.comm.util.C6888a;
import java.util.HashMap;

/* renamed from: com.qq.e.ads.interstitial2.ADListenerAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
class ADListenerAdapter implements ADListener {

    /* renamed from: a */
    private final UnifiedInterstitialADListener f17813a;

    /* renamed from: b */
    private UnifiedInterstitialMediaListener f17814b;

    /* renamed from: c */
    private ADRewardListener f17815c;

    /* renamed from: d */
    private NegativeFeedbackListener f17816d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ADListenerAdapter(UnifiedInterstitialADListener unifiedInterstitialADListener) {
        this.f17813a = unifiedInterstitialADListener;
    }

    @Override // com.p281qq.p282e.comm.adevent.ADListener
    public void onADEvent(ADEvent aDEvent) {
        UnifiedInterstitialADListener unifiedInterstitialADListener;
        String str;
        if (aDEvent == null) {
            return;
        }
        int type = aDEvent.getType();
        if (type == 109) {
            UnifiedInterstitialADListener unifiedInterstitialADListener2 = this.f17813a;
            if (unifiedInterstitialADListener2 == null || !C6888a.m8227b(unifiedInterstitialADListener2)) {
                return;
            }
            this.f17813a.onRenderSuccess();
        } else if (type == 110) {
            UnifiedInterstitialADListener unifiedInterstitialADListener3 = this.f17813a;
            if (unifiedInterstitialADListener3 == null || !C6888a.m8228a(unifiedInterstitialADListener3)) {
                return;
            }
            this.f17813a.onRenderFail();
        } else if (type == 201) {
            UnifiedInterstitialADListener unifiedInterstitialADListener4 = this.f17813a;
            if (unifiedInterstitialADListener4 != null) {
                unifiedInterstitialADListener4.onVideoCached();
            }
        } else if (type == 202) {
            UnifiedInterstitialMediaListener unifiedInterstitialMediaListener = this.f17814b;
            if (unifiedInterstitialMediaListener != null) {
                unifiedInterstitialMediaListener.onVideoStart();
            }
        } else if (type == 204) {
            UnifiedInterstitialMediaListener unifiedInterstitialMediaListener2 = this.f17814b;
            if (unifiedInterstitialMediaListener2 != null) {
                unifiedInterstitialMediaListener2.onVideoPause();
            }
        } else if (type == 206) {
            UnifiedInterstitialMediaListener unifiedInterstitialMediaListener3 = this.f17814b;
            if (unifiedInterstitialMediaListener3 != null) {
                unifiedInterstitialMediaListener3.onVideoComplete();
            }
        } else if (type == 207) {
            Integer num = (Integer) aDEvent.getParam(Integer.class);
            UnifiedInterstitialMediaListener unifiedInterstitialMediaListener4 = this.f17814b;
            if (unifiedInterstitialMediaListener4 == null || num == null) {
                return;
            }
            unifiedInterstitialMediaListener4.onVideoError(AdErrorConvertor.formatErrorCode(num.intValue()));
        } else {
            switch (type) {
                case 100:
                    UnifiedInterstitialADListener unifiedInterstitialADListener5 = this.f17813a;
                    if (unifiedInterstitialADListener5 != null) {
                        unifiedInterstitialADListener5.onADReceive();
                        return;
                    }
                    return;
                case 101:
                    Integer num2 = (Integer) aDEvent.getParam(Integer.class);
                    if (num2 == null || (unifiedInterstitialADListener = this.f17813a) == null) {
                        return;
                    }
                    unifiedInterstitialADListener.onNoAD(AdErrorConvertor.formatErrorCode(num2.intValue()));
                    return;
                case 102:
                    UnifiedInterstitialADListener unifiedInterstitialADListener6 = this.f17813a;
                    if (unifiedInterstitialADListener6 != null) {
                        unifiedInterstitialADListener6.onADOpened();
                        return;
                    }
                    return;
                case 103:
                    UnifiedInterstitialADListener unifiedInterstitialADListener7 = this.f17813a;
                    if (unifiedInterstitialADListener7 != null) {
                        unifiedInterstitialADListener7.onADExposure();
                        return;
                    }
                    return;
                case 104:
                    if (this.f17815c == null || (str = (String) aDEvent.getParam(String.class)) == null) {
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("transId", str);
                    this.f17815c.onReward(hashMap);
                    return;
                case 105:
                    UnifiedInterstitialADListener unifiedInterstitialADListener8 = this.f17813a;
                    if (unifiedInterstitialADListener8 != null) {
                        unifiedInterstitialADListener8.onADClicked();
                        return;
                    }
                    return;
                case 106:
                    UnifiedInterstitialADListener unifiedInterstitialADListener9 = this.f17813a;
                    if (unifiedInterstitialADListener9 != null) {
                        unifiedInterstitialADListener9.onADClosed();
                        return;
                    }
                    return;
                default:
                    switch (type) {
                        case 209:
                            UnifiedInterstitialMediaListener unifiedInterstitialMediaListener5 = this.f17814b;
                            if (unifiedInterstitialMediaListener5 != null) {
                                unifiedInterstitialMediaListener5.onVideoInit();
                                return;
                            }
                            return;
                        case 210:
                            Integer num3 = (Integer) aDEvent.getParam(Integer.class);
                            UnifiedInterstitialMediaListener unifiedInterstitialMediaListener6 = this.f17814b;
                            if (unifiedInterstitialMediaListener6 == null || num3 == null) {
                                return;
                            }
                            unifiedInterstitialMediaListener6.onVideoReady(num3.intValue());
                            return;
                        case 211:
                            UnifiedInterstitialMediaListener unifiedInterstitialMediaListener7 = this.f17814b;
                            if (unifiedInterstitialMediaListener7 != null) {
                                unifiedInterstitialMediaListener7.onVideoLoading();
                                return;
                            }
                            return;
                        default:
                            switch (type) {
                                case 301:
                                    UnifiedInterstitialMediaListener unifiedInterstitialMediaListener8 = this.f17814b;
                                    if (unifiedInterstitialMediaListener8 != null) {
                                        unifiedInterstitialMediaListener8.onVideoPageOpen();
                                        return;
                                    }
                                    return;
                                case 302:
                                    UnifiedInterstitialMediaListener unifiedInterstitialMediaListener9 = this.f17814b;
                                    if (unifiedInterstitialMediaListener9 != null) {
                                        unifiedInterstitialMediaListener9.onVideoPageClose();
                                        return;
                                    }
                                    return;
                                case 303:
                                    UnifiedInterstitialADListener unifiedInterstitialADListener10 = this.f17813a;
                                    if (unifiedInterstitialADListener10 != null) {
                                        unifiedInterstitialADListener10.onADLeftApplication();
                                        return;
                                    }
                                    return;
                                case 304:
                                    NegativeFeedbackListener negativeFeedbackListener = this.f17816d;
                                    if (negativeFeedbackListener != null) {
                                        negativeFeedbackListener.onComplainSuccess();
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                    }
            }
        }
    }

    public void setAdRewardListener(ADRewardListener aDRewardListener) {
        this.f17815c = aDRewardListener;
    }

    public void setMediaListener(UnifiedInterstitialMediaListener unifiedInterstitialMediaListener) {
        this.f17814b = unifiedInterstitialMediaListener;
    }

    public void setNegativeFeedbackListener(NegativeFeedbackListener negativeFeedbackListener) {
        this.f17816d = negativeFeedbackListener;
    }
}
