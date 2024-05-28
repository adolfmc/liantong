package com.p281qq.p282e.ads.nativ;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.p281qq.p282e.ads.cfg.VideoOption;
import com.p281qq.p282e.ads.nativ.widget.NativeAdContainer;
import com.p281qq.p282e.comm.adevent.ADEvent;
import com.p281qq.p282e.comm.adevent.ADEventListener;
import com.p281qq.p282e.comm.adevent.ADListener;
import com.p281qq.p282e.comm.compliance.DownloadConfirmCallBack;
import com.p281qq.p282e.comm.compliance.DownloadConfirmListener;
import com.p281qq.p282e.comm.listeners.NegativeFeedbackListener;
import com.p281qq.p282e.comm.util.AdErrorConvertor;
import java.util.List;
import java.util.Map;

/* renamed from: com.qq.e.ads.nativ.NativeUnifiedADDataAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NativeUnifiedADDataAdapter implements NativeUnifiedADData, DownloadConfirmListener {

    /* renamed from: a */
    private NativeUnifiedADData f17856a;

    /* renamed from: b */
    private NativeADEventListener f17857b;

    /* renamed from: c */
    private NativeADMediaListener f17858c;

    /* renamed from: d */
    private DownloadConfirmListener f17859d;

    /* renamed from: e */
    private NegativeFeedbackListener f17860e;

    /* renamed from: com.qq.e.ads.nativ.NativeUnifiedADDataAdapter$UnifiedAdListener */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class UnifiedAdListener implements ADListener {
        private UnifiedAdListener() {
        }

        @Override // com.p281qq.p282e.comm.adevent.ADListener
        public void onADEvent(ADEvent aDEvent) {
            if (NativeUnifiedADDataAdapter.m8303a(NativeUnifiedADDataAdapter.this, aDEvent) || NativeUnifiedADDataAdapter.m8302b(NativeUnifiedADDataAdapter.this, aDEvent)) {
                return;
            }
            NativeUnifiedADDataAdapter nativeUnifiedADDataAdapter = NativeUnifiedADDataAdapter.this;
            if (nativeUnifiedADDataAdapter == null) {
                throw null;
            }
            if (NativeUnifiedADDataAdapter.m8301c(nativeUnifiedADDataAdapter, aDEvent)) {
            }
        }
    }

    public NativeUnifiedADDataAdapter(NativeUnifiedADData nativeUnifiedADData) {
        this.f17856a = nativeUnifiedADData;
        if (nativeUnifiedADData instanceof ADEventListener) {
            ((ADEventListener) nativeUnifiedADData).setAdListener(new UnifiedAdListener());
        }
    }

    /* renamed from: a */
    static boolean m8303a(NativeUnifiedADDataAdapter nativeUnifiedADDataAdapter, ADEvent aDEvent) {
        if (nativeUnifiedADDataAdapter.f17857b != null) {
            int type = aDEvent.getType();
            if (type == 103) {
                nativeUnifiedADDataAdapter.f17857b.onADExposed();
            } else if (type == 105) {
                NativeADEventListener nativeADEventListener = nativeUnifiedADDataAdapter.f17857b;
                if (nativeADEventListener instanceof NativeADEventListenerWithClickInfo) {
                    ((NativeADEventListenerWithClickInfo) nativeUnifiedADDataAdapter.f17857b).onADClicked((View) aDEvent.getParam(View.class));
                } else {
                    nativeADEventListener.onADClicked();
                }
            } else if (type == 107) {
                Integer num = (Integer) aDEvent.getParam(Integer.class);
                if (num != null) {
                    nativeUnifiedADDataAdapter.f17857b.onADError(AdErrorConvertor.formatErrorCode(num.intValue()));
                }
            } else if (type == 111) {
                nativeUnifiedADDataAdapter.f17857b.onADStatusChanged();
            }
            return true;
        }
        return false;
    }

    /* renamed from: b */
    static boolean m8302b(NativeUnifiedADDataAdapter nativeUnifiedADDataAdapter, ADEvent aDEvent) {
        if (nativeUnifiedADDataAdapter.f17858c != null) {
            switch (aDEvent.getType()) {
                case 201:
                    Integer num = (Integer) aDEvent.getParam(Integer.class);
                    if (num != null) {
                        nativeUnifiedADDataAdapter.f17858c.onVideoLoaded(num.intValue());
                    }
                    return true;
                case 202:
                    nativeUnifiedADDataAdapter.f17858c.onVideoStart();
                    return true;
                case 203:
                    nativeUnifiedADDataAdapter.f17858c.onVideoResume();
                    return true;
                case 204:
                    nativeUnifiedADDataAdapter.f17858c.onVideoPause();
                    return true;
                case 205:
                    nativeUnifiedADDataAdapter.f17858c.onVideoStop();
                    return true;
                case 206:
                    nativeUnifiedADDataAdapter.f17858c.onVideoCompleted();
                    return true;
                case 207:
                    Integer num2 = (Integer) aDEvent.getParam(Integer.class);
                    if (num2 != null) {
                        nativeUnifiedADDataAdapter.f17858c.onVideoError(AdErrorConvertor.formatErrorCode(num2.intValue()));
                    }
                    return true;
                case 208:
                    nativeUnifiedADDataAdapter.f17858c.onVideoClicked();
                    return true;
                case 209:
                    nativeUnifiedADDataAdapter.f17858c.onVideoInit();
                    return true;
                case 210:
                    nativeUnifiedADDataAdapter.f17858c.onVideoReady();
                    return true;
                case 211:
                    nativeUnifiedADDataAdapter.f17858c.onVideoLoading();
                    return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    static boolean m8301c(NativeUnifiedADDataAdapter nativeUnifiedADDataAdapter, ADEvent aDEvent) {
        if (nativeUnifiedADDataAdapter.f17860e != null && aDEvent.getType() == 304) {
            nativeUnifiedADDataAdapter.f17860e.onComplainSuccess();
            return true;
        }
        return false;
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void bindAdToCustomVideo(ViewGroup viewGroup, Context context, List<View> list, List<View> list2) {
        this.f17856a.bindAdToCustomVideo(viewGroup, context, list, list2);
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void bindAdToView(Context context, NativeAdContainer nativeAdContainer, FrameLayout.LayoutParams layoutParams, List<View> list) {
        this.f17856a.bindAdToView(context, nativeAdContainer, layoutParams, list);
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void bindAdToView(Context context, NativeAdContainer nativeAdContainer, FrameLayout.LayoutParams layoutParams, List<View> list, List<View> list2) {
        this.f17856a.bindAdToView(context, nativeAdContainer, layoutParams, list, list2);
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void bindCTAViews(List<View> list) {
        this.f17856a.bindCTAViews(list);
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void bindImageViews(List<ImageView> list, int i) {
        this.f17856a.bindImageViews(list, i);
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void bindImageViews(List<ImageView> list, byte[] bArr) {
        this.f17856a.bindImageViews(list, bArr);
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void bindMediaView(MediaView mediaView, VideoOption videoOption, NativeADMediaListener nativeADMediaListener) {
        this.f17858c = nativeADMediaListener;
        this.f17856a.bindMediaView(mediaView, videoOption, nativeADMediaListener);
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void destroy() {
        this.f17856a.destroy();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public boolean equalsAdData(NativeUnifiedADData nativeUnifiedADData) {
        return this.f17856a.equalsAdData(nativeUnifiedADData);
    }

    public NativeUnifiedADData getAdData() {
        return this.f17856a;
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public int getAdPatternType() {
        return this.f17856a.getAdPatternType();
    }

    @Override // com.p281qq.p282e.comm.compliance.ApkDownloadComplianceInterface
    public String getApkInfoUrl() {
        return this.f17856a.getApkInfoUrl();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public NativeUnifiedADAppMiitInfo getAppMiitInfo() {
        return this.f17856a.getAppMiitInfo();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public double getAppPrice() {
        return this.f17856a.getAppPrice();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public int getAppScore() {
        return this.f17856a.getAppScore();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public int getAppStatus() {
        return this.f17856a.getAppStatus();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public String getButtonText() {
        return this.f17856a.getButtonText();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public String getCTAText() {
        return this.f17856a.getCTAText();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public CustomizeVideo getCustomizeVideo() {
        return this.f17856a.getCustomizeVideo();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public String getDesc() {
        return this.f17856a.getDesc();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public long getDownloadCount() {
        return this.f17856a.getDownloadCount();
    }

    @Override // com.p281qq.p282e.comm.p283pi.LADI
    public int getECPM() {
        return this.f17856a.getECPM();
    }

    @Override // com.p281qq.p282e.comm.p283pi.LADI
    public String getECPMLevel() {
        return this.f17856a.getECPMLevel();
    }

    @Override // com.p281qq.p282e.comm.p283pi.LADI
    public Map<String, Object> getExtraInfo() {
        return this.f17856a.getExtraInfo();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public String getIconUrl() {
        return this.f17856a.getIconUrl();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public List<String> getImgList() {
        return this.f17856a.getImgList();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public String getImgUrl() {
        return this.f17856a.getImgUrl();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public int getPictureHeight() {
        return this.f17856a.getPictureHeight();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public int getPictureWidth() {
        return this.f17856a.getPictureWidth();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public int getProgress() {
        return this.f17856a.getProgress();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public String getTitle() {
        return this.f17856a.getTitle();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public int getVideoCurrentPosition() {
        return this.f17856a.getVideoCurrentPosition();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public int getVideoDuration() {
        return this.f17856a.getVideoDuration();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public boolean isAppAd() {
        return this.f17856a.isAppAd();
    }

    @Override // com.p281qq.p282e.comm.p283pi.LADI
    public boolean isValid() {
        return this.f17856a.isValid();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public boolean isWeChatCanvasAd() {
        return this.f17856a.isWeChatCanvasAd();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void negativeFeedback() {
        this.f17856a.negativeFeedback();
    }

    @Override // com.p281qq.p282e.comm.compliance.DownloadConfirmListener
    public void onDownloadConfirm(Activity activity, int i, String str, DownloadConfirmCallBack downloadConfirmCallBack) {
        DownloadConfirmListener downloadConfirmListener = this.f17859d;
        if (downloadConfirmListener != null) {
            downloadConfirmListener.onDownloadConfirm(activity, i, str, downloadConfirmCallBack);
        }
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void pauseAppDownload() {
        this.f17856a.pauseAppDownload();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void pauseVideo() {
        this.f17856a.pauseVideo();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void resume() {
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void resumeAppDownload() {
        this.f17856a.resumeAppDownload();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void resumeVideo() {
        this.f17856a.resumeVideo();
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendLossNotification(int i, int i2, String str) {
        this.f17856a.sendLossNotification(i, i2, str);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendLossNotification(Map<String, Object> map) {
        this.f17856a.sendLossNotification(map);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendWinNotification(int i) {
        this.f17856a.sendWinNotification(i);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void sendWinNotification(Map<String, Object> map) {
        this.f17856a.sendWinNotification(map);
    }

    @Override // com.p281qq.p282e.comm.p283pi.IBidding
    public void setBidECPM(int i) {
        this.f17856a.setBidECPM(i);
    }

    @Override // com.p281qq.p282e.comm.compliance.ApkDownloadComplianceInterface
    public void setDownloadConfirmListener(DownloadConfirmListener downloadConfirmListener) {
        this.f17859d = downloadConfirmListener;
        NativeUnifiedADData nativeUnifiedADData = this.f17856a;
        if (nativeUnifiedADData != null) {
            nativeUnifiedADData.setDownloadConfirmListener(this);
        }
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void setNativeAdEventListener(NativeADEventListener nativeADEventListener) {
        this.f17857b = nativeADEventListener;
    }

    @Override // com.p281qq.p282e.comm.p283pi.NFBI
    public void setNegativeFeedbackListener(NegativeFeedbackListener negativeFeedbackListener) {
        this.f17860e = negativeFeedbackListener;
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void setVideoMute(boolean z) {
        this.f17856a.setVideoMute(z);
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void startVideo() {
        this.f17856a.startVideo();
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADData
    public void stopVideo() {
        this.f17856a.stopVideo();
    }
}
