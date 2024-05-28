package com.p281qq.p282e.ads.nativ;

import android.content.Context;
import com.p281qq.p282e.ads.NativeAbstractAD;
import com.p281qq.p282e.ads.cfg.VideoOption;
import com.p281qq.p282e.ads.rewardvideo.ServerSideVerificationOptions;
import com.p281qq.p282e.comm.adevent.ADEvent;
import com.p281qq.p282e.comm.adevent.ADListener;
import com.p281qq.p282e.comm.constants.LoadAdParams;
import com.p281qq.p282e.comm.listeners.ADRewardListener;
import com.p281qq.p282e.comm.listeners.NegativeFeedbackListener;
import com.p281qq.p282e.comm.p283pi.IReward;
import com.p281qq.p282e.comm.p283pi.NEADI;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.qq.e.ads.nativ.NativeExpressAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NativeExpressAD extends NativeAbstractAD<NEADI> implements IReward {

    /* renamed from: g */
    private volatile int f17828g;

    /* renamed from: h */
    private volatile int f17829h;

    /* renamed from: i */
    private List<Integer> f17830i = Collections.synchronizedList(new ArrayList());

    /* renamed from: j */
    private VideoOption f17831j;

    /* renamed from: k */
    private ADSize f17832k;

    /* renamed from: l */
    private NativeExpressADListener f17833l;

    /* renamed from: m */
    private final ADListenerAdapter f17834m;

    /* renamed from: n */
    private LoadAdParams f17835n;

    /* renamed from: o */
    private volatile ServerSideVerificationOptions f17836o;

    /* renamed from: com.qq.e.ads.nativ.NativeExpressAD$ADListenerAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ADListenerAdapter implements ADListener {

        /* renamed from: a */
        private NativeExpressADListener f17837a;

        /* renamed from: b */
        private NativeExpressMediaListener f17838b;

        /* renamed from: c */
        private NegativeFeedbackListener f17839c;

        /* renamed from: d */
        private ADRewardListener f17840d;

        public ADListenerAdapter(NativeExpressADListener nativeExpressADListener) {
            this.f17837a = nativeExpressADListener;
        }

        public ADListenerAdapter(NativeExpressMediaListener nativeExpressMediaListener) {
            this.f17838b = nativeExpressMediaListener;
        }

        @Override // com.p281qq.p282e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            if (!NativeExpressAD.m8311a(this.f17837a, aDEvent) && !NativeExpressAD.m8310a(this.f17838b, aDEvent) && !NativeExpressAD.m8308a(this.f17839c, aDEvent) && NativeExpressAD.m8309a(this.f17840d, aDEvent)) {
            }
        }

        public void setAdRewardListener(ADRewardListener aDRewardListener) {
            this.f17840d = aDRewardListener;
        }

        public void setMediaListener(NativeExpressMediaListener nativeExpressMediaListener) {
            this.f17838b = nativeExpressMediaListener;
        }

        public void setNegativeFeedbackListener(NegativeFeedbackListener negativeFeedbackListener) {
            this.f17839c = negativeFeedbackListener;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.ads.nativ.NativeExpressAD$NativeExpressADListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface NativeExpressADListener extends NativeAbstractAD.BasicADListener {
        void onADClicked(NativeExpressADView nativeExpressADView);

        void onADClosed(NativeExpressADView nativeExpressADView);

        void onADExposure(NativeExpressADView nativeExpressADView);

        void onADLeftApplication(NativeExpressADView nativeExpressADView);

        void onADLoaded(List<NativeExpressADView> list);

        void onRenderFail(NativeExpressADView nativeExpressADView);

        void onRenderSuccess(NativeExpressADView nativeExpressADView);
    }

    public NativeExpressAD(Context context, ADSize aDSize, String str, NativeExpressADListener nativeExpressADListener) {
        this.f17833l = nativeExpressADListener;
        this.f17834m = new ADListenerAdapter(nativeExpressADListener);
        if (m8312a(aDSize)) {
            return;
        }
        m8344a(context, str);
    }

    public NativeExpressAD(Context context, ADSize aDSize, String str, NativeExpressADListener nativeExpressADListener, String str2) {
        this.f17833l = nativeExpressADListener;
        this.f17834m = new ADListenerAdapter(nativeExpressADListener);
        if (m8312a(aDSize)) {
            return;
        }
        m8343a(context, str, str2);
    }

    /* renamed from: a */
    private boolean m8312a(ADSize aDSize) {
        if (aDSize != null) {
            this.f17832k = aDSize;
            return false;
        }
        GDTLogger.m8234e("初始化错误：参数adSize不能为空");
        m8345a(2001);
        return true;
    }

    /* renamed from: a */
    static boolean m8311a(NativeExpressADListener nativeExpressADListener, ADEvent aDEvent) {
        if (nativeExpressADListener != null) {
            switch (aDEvent.getType()) {
                case 100:
                    List<NativeExpressADView> list = (List) aDEvent.getParam(List.class);
                    if (list != null) {
                        nativeExpressADListener.onADLoaded(list);
                    }
                    return true;
                case 101:
                    Integer num = (Integer) aDEvent.getParam(Integer.class);
                    if (num != null) {
                        nativeExpressADListener.onNoAD(AdErrorConvertor.formatErrorCode(num.intValue()));
                    }
                    return true;
                case 103:
                    NativeExpressADView nativeExpressADView = (NativeExpressADView) aDEvent.getParam(NativeExpressADView.class);
                    if (nativeExpressADView != null) {
                        nativeExpressADListener.onADExposure(nativeExpressADView);
                    }
                    return true;
                case 105:
                    NativeExpressADView nativeExpressADView2 = (NativeExpressADView) aDEvent.getParam(NativeExpressADView.class);
                    if (nativeExpressADView2 != null) {
                        nativeExpressADListener.onADClicked(nativeExpressADView2);
                    }
                    return true;
                case 106:
                    NativeExpressADView nativeExpressADView3 = (NativeExpressADView) aDEvent.getParam(NativeExpressADView.class);
                    if (nativeExpressADView3 != null) {
                        nativeExpressADListener.onADClosed(nativeExpressADView3);
                        nativeExpressADView3.negativeFeedback();
                    }
                    return true;
                case 109:
                    NativeExpressADView nativeExpressADView4 = (NativeExpressADView) aDEvent.getParam(NativeExpressADView.class);
                    if (nativeExpressADView4 != null) {
                        nativeExpressADListener.onRenderSuccess(nativeExpressADView4);
                    }
                    return true;
                case 110:
                    NativeExpressADView nativeExpressADView5 = (NativeExpressADView) aDEvent.getParam(NativeExpressADView.class);
                    if (nativeExpressADView5 != null) {
                        nativeExpressADListener.onRenderFail(nativeExpressADView5);
                    }
                    return true;
                case 303:
                    NativeExpressADView nativeExpressADView6 = (NativeExpressADView) aDEvent.getParam(NativeExpressADView.class);
                    if (nativeExpressADView6 != null) {
                        nativeExpressADListener.onADLeftApplication(nativeExpressADView6);
                    }
                    return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.p281qq.p282e.ads.NativeAbstractAD, com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo8294a(NEADI neadi) {
        super.mo8305a((NativeExpressAD) neadi);
        neadi.setMinVideoDuration(this.f17828g);
        neadi.setMaxVideoDuration(this.f17829h);
        ((NEADI) this.f17738a).setServerSideVerificationOptions(this.f17836o);
        VideoOption videoOption = this.f17831j;
        if (videoOption != null) {
            setVideoOption(videoOption);
        }
        synchronized (this.f17830i) {
            Iterator<Integer> it = this.f17830i.iterator();
            while (it.hasNext()) {
                if (this.f17738a != 0) {
                    if (this.f17835n != null) {
                        ((NEADI) this.f17738a).loadAd(it.next().intValue(), this.f17835n);
                    } else {
                        ((NEADI) this.f17738a).loadAd(it.next().intValue());
                    }
                }
            }
        }
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: b */
    public void mo8292b(int i) {
        NativeExpressADListener nativeExpressADListener = this.f17833l;
        if (nativeExpressADListener != null) {
            nativeExpressADListener.onNoAD(AdErrorConvertor.formatErrorCode(i));
        }
    }

    public String getAdNetWorkName() {
        T t = this.f17738a;
        if (t != 0) {
            return ((NEADI) t).getAdNetWorkName();
        }
        m8340a("getAdNetWorkName");
        return null;
    }

    public void loadAD(int i) {
        loadAD(i, null);
    }

    public void loadAD(int i, LoadAdParams loadAdParams) {
        if (m8346a()) {
            if (loadAdParams != null) {
                setAdParams(loadAdParams);
            }
            if (!m8339b()) {
                synchronized (this.f17830i) {
                    this.f17830i.add(Integer.valueOf(i));
                }
                return;
            }
            T t = this.f17738a;
            if (t == 0) {
                m8340a("loadAD");
                return;
            }
            LoadAdParams loadAdParams2 = this.f17835n;
            if (loadAdParams2 != null) {
                ((NEADI) t).loadAd(i, loadAdParams2);
            } else {
                ((NEADI) t).loadAd(i);
            }
        }
    }

    public void setAdParams(LoadAdParams loadAdParams) {
        this.f17835n = loadAdParams;
    }

    public void setMaxVideoDuration(int i) {
        this.f17829h = i;
        if (this.f17829h > 0 && this.f17828g > this.f17829h) {
            GDTLogger.m8234e("maxVideoDuration 设置值非法，不得小于minVideoDuration");
        }
        T t = this.f17738a;
        if (t != 0) {
            ((NEADI) t).setMaxVideoDuration(this.f17829h);
        }
    }

    public void setMinVideoDuration(int i) {
        this.f17828g = i;
        if (this.f17829h > 0 && this.f17828g > this.f17829h) {
            GDTLogger.m8234e("minVideoDuration 设置值非法，不得大于maxVideoDuration");
        }
        T t = this.f17738a;
        if (t != 0) {
            ((NEADI) t).setMinVideoDuration(this.f17828g);
        }
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setRewardListener(ADRewardListener aDRewardListener) {
        this.f17834m.setAdRewardListener(aDRewardListener);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IReward
    public void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.f17836o = serverSideVerificationOptions;
        T t = this.f17738a;
        if (t != 0) {
            ((NEADI) t).setServerSideVerificationOptions(serverSideVerificationOptions);
        }
    }

    public void setVideoOption(VideoOption videoOption) {
        this.f17831j = videoOption;
        T t = this.f17738a;
        if (t == 0 || videoOption == null) {
            return;
        }
        ((NEADI) t).setVideoOption(videoOption);
    }

    /* renamed from: a */
    static boolean m8310a(NativeExpressMediaListener nativeExpressMediaListener, ADEvent aDEvent) {
        NativeExpressADView nativeExpressADView;
        if (nativeExpressMediaListener != null && (nativeExpressADView = (NativeExpressADView) aDEvent.getParam(NativeExpressADView.class)) != null) {
            switch (aDEvent.getType()) {
                case 201:
                    nativeExpressMediaListener.onVideoCached(nativeExpressADView);
                    return true;
                case 202:
                    nativeExpressMediaListener.onVideoStart(nativeExpressADView);
                    return true;
                case 204:
                    nativeExpressMediaListener.onVideoPause(nativeExpressADView);
                    return true;
                case 206:
                    nativeExpressMediaListener.onVideoComplete(nativeExpressADView);
                    return true;
                case 207:
                    Integer num = (Integer) aDEvent.getParam(1, Integer.class);
                    if (num != null) {
                        nativeExpressMediaListener.onVideoError(nativeExpressADView, AdErrorConvertor.formatErrorCode(num.intValue()));
                        return true;
                    }
                    return true;
                case 209:
                    nativeExpressMediaListener.onVideoInit(nativeExpressADView);
                    return true;
                case 210:
                    Integer num2 = (Integer) aDEvent.getParam(1, Integer.class);
                    if (num2 != null) {
                        nativeExpressMediaListener.onVideoReady(nativeExpressADView, num2.intValue());
                        return true;
                    }
                    return true;
                case 211:
                    nativeExpressMediaListener.onVideoLoading(nativeExpressADView);
                    return true;
                case 301:
                    nativeExpressMediaListener.onVideoPageOpen(nativeExpressADView);
                    return true;
                case 302:
                    nativeExpressMediaListener.onVideoPageClose(nativeExpressADView);
                    return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    static boolean m8308a(NegativeFeedbackListener negativeFeedbackListener, ADEvent aDEvent) {
        if (negativeFeedbackListener != null && aDEvent.getType() == 304) {
            negativeFeedbackListener.onComplainSuccess();
            return true;
        }
        return false;
    }

    /* renamed from: a */
    static boolean m8309a(ADRewardListener aDRewardListener, ADEvent aDEvent) {
        if (aDRewardListener != null && aDEvent.getType() == 104) {
            String str = (String) aDEvent.getParam(String.class);
            if (str != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("transId", str);
                aDRewardListener.onReward(hashMap);
            }
            return true;
        }
        return false;
    }

    @Override // com.p281qq.p282e.ads.AbstractAD
    /* renamed from: a */
    public Object mo8297a(Context context, POFactory pOFactory, String str, String str2, String str3) {
        return pOFactory.getNativeExpressADDelegate(context, this.f17832k, str, str2, str3, this.f17834m);
    }
}
