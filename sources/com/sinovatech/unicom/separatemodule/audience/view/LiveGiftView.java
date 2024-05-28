package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import com.sinovatech.unicom.separatemodule.audience.view.animview.AttoInfoAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.CommonLiveAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.DzAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.GzAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.MsgAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.PcAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.TopMsgAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.WelcomFloatAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.YtAnimView;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.net.URL;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveGiftView extends FrameLayout {
    private AttoInfoAnimView attoAnimView;
    private CommonLiveAnimView commonLiveAnimView;
    private DzAnimView dzAnimView;
    private WelcomFloatAnimView floatAnimView;
    private GzAnimView gzAnimView;
    private MsgAnimView joinAnimView;
    private SVGAImageView mSVGAImage;
    private SVGAImageView mSVGAImageFudai;
    private SVGAParser parser;
    private PcAnimView pcAnimView;
    private TopMsgAnimView topMsgAnimView;
    View view;
    private YtAnimView ytAnimView;

    public void setJoinBottomMargin(int i) {
    }

    public void setMsgTopMargin(int i) {
    }

    public LiveGiftView(Context context) {
        super(context);
        initView(context);
    }

    public LiveGiftView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        this.view = LayoutInflater.from(context).inflate(2131493278, (ViewGroup) null);
        this.topMsgAnimView = (TopMsgAnimView) this.view.findViewById(2131298814);
        this.commonLiveAnimView = (CommonLiveAnimView) this.view.findViewById(2131296691);
        this.gzAnimView = (GzAnimView) this.view.findViewById(2131297100);
        this.dzAnimView = (DzAnimView) this.view.findViewById(2131296914);
        this.ytAnimView = (YtAnimView) this.view.findViewById(2131299860);
        this.pcAnimView = (PcAnimView) this.view.findViewById(2131298191);
        this.joinAnimView = (MsgAnimView) this.view.findViewById(2131297544);
        this.attoAnimView = (AttoInfoAnimView) this.view.findViewById(2131296379);
        this.floatAnimView = (WelcomFloatAnimView) this.view.findViewById(2131297015);
        this.mSVGAImage = (SVGAImageView) this.view.findViewById(2131298718);
        this.mSVGAImageFudai = (SVGAImageView) this.view.findViewById(2131298719);
        this.parser = new SVGAParser(context);
        this.mSVGAImage.setLoops(1);
        this.mSVGAImage.setClearsAfterStop(true);
        this.mSVGAImage.setCallback(new SVGACallback() { // from class: com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView.1
            @Override // com.opensource.svgaplayer.SVGACallback
            public void onFinished() {
            }

            @Override // com.opensource.svgaplayer.SVGACallback
            public void onPause() {
            }

            @Override // com.opensource.svgaplayer.SVGACallback
            public void onRepeat() {
            }

            @Override // com.opensource.svgaplayer.SVGACallback
            public void onStep(int i, double d) {
            }
        });
        this.mSVGAImageFudai.setLoops(1);
        this.mSVGAImageFudai.setClearsAfterStop(true);
        this.mSVGAImageFudai.setCallback(new SVGACallback() { // from class: com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView.2
            @Override // com.opensource.svgaplayer.SVGACallback
            public void onFinished() {
            }

            @Override // com.opensource.svgaplayer.SVGACallback
            public void onPause() {
            }

            @Override // com.opensource.svgaplayer.SVGACallback
            public void onRepeat() {
            }

            @Override // com.opensource.svgaplayer.SVGACallback
            public void onStep(int i, double d) {
            }
        });
        addView(this.view);
    }

    public void stopAllGiftAnim() {
        stopCommonAnim();
        stopDzAnim();
        stopGzAnim();
        stopPcAnim();
        stopYtAnim();
        stopJoinOrFocusAnim();
        stopBrowseGoodAnim();
        stopOrderAnim();
        stopMsgGiftAnim();
        stopAttoInfoAnim();
        stopSvgaAnim();
        stopFloatAnim();
    }

    public void startGiftAnime(String str) {
        GiftEntity giftinfo = getGiftinfo(str);
        if (giftinfo != null) {
            String giftSvga = giftinfo.getGiftSvga();
            if (!TextUtils.isEmpty(giftSvga)) {
                startSvgaAnim(giftSvga);
                return;
            } else {
                startLocalGiftAnim(str);
                return;
            }
        }
        startLocalGiftAnim(str);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void startLocalGiftAnim(String str) {
        char c;
        UIUtils.logD("lln", "liveGiftView-->startLocalGiftAnim-->本地动画");
        switch (str.hashCode()) {
            case 46730162:
                if (str.equals("10001")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 46730163:
                if (str.equals("10002")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 46730164:
                if (str.equals("10003")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 46730165:
                if (str.equals("10004")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 46730166:
                if (str.equals("10005")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 46730167:
                if (str.equals("10006")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 46730168:
                if (str.equals("10007")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 46730169:
                if (str.equals("10008")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                try {
                    startCommonAnim(Integer.parseInt(str));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 4:
                startGzAnim();
                return;
            case 5:
                startDzAnim();
                return;
            default:
                return;
        }
    }

    public void startLocalZanSvgaAnim() {
        try {
            if (this.mSVGAImage != null && this.mSVGAImage.isAnimating()) {
                MsLogUtil.m7980d("点赞svga动画播放中");
            } else {
                this.parser.decodeFromAssets("svga/30001.svga", new SVGAParser.ParseCompletion() { // from class: com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView.3
                    @Override // com.opensource.svgaplayer.SVGAParser.ParseCompletion
                    @RequiresApi(api = 28)
                    public void onComplete(@NotNull SVGAVideoEntity sVGAVideoEntity) {
                        if (LiveGiftView.this.mSVGAImage != null) {
                            LiveGiftView.this.mSVGAImage.setVideoItem(sVGAVideoEntity);
                            LiveGiftView.this.mSVGAImage.startAnimation();
                        }
                    }

                    @Override // com.opensource.svgaplayer.SVGAParser.ParseCompletion
                    public void onError() {
                        Log.e("LiveGiftView", "svga动画播放报错");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startSvgaAnimLocl(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("10007", "10007.svga");
        hashMap.put("10008", "10008.svga");
        hashMap.put("20001", "20001.svga");
        hashMap.put("20002", "20002.svga");
        hashMap.put("20003", "20003.svga");
        hashMap.put("20004", "20004.svga");
        hashMap.put("20005", "20005.svga");
        hashMap.put("20006", "20006.svga");
        final String str2 = (String) hashMap.get(str);
        try {
            this.parser.decodeFromAssets(str2, new SVGAParser.ParseCompletion() { // from class: com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView.4
                @Override // com.opensource.svgaplayer.SVGAParser.ParseCompletion
                @RequiresApi(api = 28)
                public void onComplete(@NotNull SVGAVideoEntity sVGAVideoEntity) {
                    if (LiveGiftView.this.mSVGAImage != null) {
                        String str3 = str2;
                        char c = 65535;
                        switch (str3.hashCode()) {
                            case 1055665240:
                                if (str3.equals("20001.svga")) {
                                    c = 1;
                                    break;
                                }
                                break;
                            case 1112923542:
                                if (str3.equals("20003.svga")) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case 1141552693:
                                if (str3.equals("20004.svga")) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case 1170181844:
                                if (str3.equals("20005.svga")) {
                                    c = 3;
                                    break;
                                }
                                break;
                            case 1198810995:
                                if (str3.equals("20006.svga")) {
                                    c = 4;
                                    break;
                                }
                                break;
                        }
                        switch (c) {
                            case 0:
                                LiveGiftView.this.mSVGAImageFudai.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                break;
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                LiveGiftView.this.mSVGAImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                break;
                            default:
                                LiveGiftView.this.mSVGAImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                break;
                        }
                        if ("20003.svga".equals(str2)) {
                            LiveGiftView.this.mSVGAImageFudai.setVideoItem(sVGAVideoEntity);
                            LiveGiftView.this.mSVGAImageFudai.startAnimation();
                            return;
                        }
                        LiveGiftView.this.mSVGAImage.setVideoItem(sVGAVideoEntity);
                        LiveGiftView.this.mSVGAImage.startAnimation();
                    }
                }

                @Override // com.opensource.svgaplayer.SVGAParser.ParseCompletion
                public void onError() {
                    Log.e("LiveGiftView", "svga动画播放报错");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startSvgaAnim(String str) {
        try {
            this.parser.decodeFromURL(new URL(str), new SVGAParser.ParseCompletion() { // from class: com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView.5
                @Override // com.opensource.svgaplayer.SVGAParser.ParseCompletion
                @RequiresApi(api = 28)
                public void onComplete(@NotNull SVGAVideoEntity sVGAVideoEntity) {
                    if (LiveGiftView.this.mSVGAImage != null) {
                        LiveGiftView.this.mSVGAImage.setVideoItem(sVGAVideoEntity);
                        LiveGiftView.this.mSVGAImage.startAnimation();
                    }
                }

                @Override // com.opensource.svgaplayer.SVGAParser.ParseCompletion
                public void onError() {
                    Log.e("LiveGiftView", "svga动画播放报错");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopSvgaAnim() {
        SVGAImageView sVGAImageView = this.mSVGAImage;
        if (sVGAImageView == null || !sVGAImageView.isAnimating()) {
            return;
        }
        this.mSVGAImage.stopAnimation();
    }

    private void startCommonAnim(int i) {
        CommonLiveAnimView commonLiveAnimView = this.commonLiveAnimView;
        if (commonLiveAnimView != null) {
            commonLiveAnimView.startAnimCommon(i);
        }
    }

    private void stopCommonAnim() {
        CommonLiveAnimView commonLiveAnimView = this.commonLiveAnimView;
        if (commonLiveAnimView != null) {
            commonLiveAnimView.stopAnimCommon();
        }
    }

    private void startDzAnim() {
        DzAnimView dzAnimView = this.dzAnimView;
        if (dzAnimView != null) {
            dzAnimView.startAnimDz();
        }
    }

    private void stopDzAnim() {
        DzAnimView dzAnimView = this.dzAnimView;
        if (dzAnimView != null) {
            dzAnimView.stopAnimDz();
        }
    }

    private void startGzAnim() {
        GzAnimView gzAnimView = this.gzAnimView;
        if (gzAnimView != null) {
            gzAnimView.startAnimGz();
        }
    }

    private void stopGzAnim() {
        GzAnimView gzAnimView = this.gzAnimView;
        if (gzAnimView != null) {
            gzAnimView.stopAnimGz();
        }
    }

    private void startPcAnim() {
        PcAnimView pcAnimView = this.pcAnimView;
        if (pcAnimView != null) {
            pcAnimView.startAnimPc();
        }
    }

    private void stopPcAnim() {
        PcAnimView pcAnimView = this.pcAnimView;
        if (pcAnimView != null) {
            pcAnimView.stopAnimPc();
        }
    }

    private void startYtAnim() {
        YtAnimView ytAnimView = this.ytAnimView;
        if (ytAnimView != null) {
            ytAnimView.startAnimYt();
        }
    }

    private void stopYtAnim() {
        YtAnimView ytAnimView = this.ytAnimView;
        if (ytAnimView != null) {
            ytAnimView.stopAnimYt();
        }
    }

    public void startJoinOrFocusAnim(String str, String str2, boolean z, boolean z2) {
        if (this.joinAnimView == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.joinAnimView.startAnimJoinOrFocus(str, str2, z, z2);
    }

    public void stopJoinOrFocusAnim() {
        MsgAnimView msgAnimView = this.joinAnimView;
        if (msgAnimView != null) {
            msgAnimView.stopAnimJoinOrFocus();
        }
    }

    public void startBrowseGoodAnim(String str, String str2, boolean z, String str3) {
        if (this.joinAnimView == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.joinAnimView.startAnimBrowseGoods(str, str2, z, str3);
    }

    public void stopBrowseGoodAnim() {
        MsgAnimView msgAnimView = this.joinAnimView;
        if (msgAnimView != null) {
            msgAnimView.stopAnimBrowseGoods();
        }
    }

    public void startOrderAnim(String str, String str2, String str3) {
        TopMsgAnimView topMsgAnimView = this.topMsgAnimView;
        if (topMsgAnimView != null) {
            topMsgAnimView.startAnimOrder(str, str2, str3);
        }
    }

    public void stopOrderAnim() {
        TopMsgAnimView topMsgAnimView = this.topMsgAnimView;
        if (topMsgAnimView != null) {
            topMsgAnimView.stopAnimMsgGift();
        }
    }

    public void startMsgGiftAnim(String str, String str2, int i) {
        if (this.topMsgAnimView != null) {
            if (10005 == i || 10006 == i || 10007 == i || 10008 == i) {
                this.topMsgAnimView.startAnimMsgGift(str, str2, i);
            }
        }
    }

    public void startMsgGiftAnim(String str, String str2, String str3) {
        GiftEntity giftinfo;
        if (this.topMsgAnimView == null || (giftinfo = getGiftinfo(str3)) == null || TextUtils.isEmpty(giftinfo.getImgFileFloat())) {
            return;
        }
        this.topMsgAnimView.startAnimMsgGift(str, str2, giftinfo.getImgFileFloat());
    }

    private GiftEntity getGiftinfo(String str) {
        for (GiftEntity giftEntity : CacheDataCenter.getInstance().getGiftList()) {
            if (giftEntity.getGiftCode().equals(str)) {
                return giftEntity;
            }
        }
        return null;
    }

    public void stopMsgGiftAnim() {
        TopMsgAnimView topMsgAnimView = this.topMsgAnimView;
        if (topMsgAnimView != null) {
            topMsgAnimView.stopAnimMsgGift();
        }
    }

    public void startAttoInfoAnim(String str) {
        if (this.attoAnimView != null) {
            this.attoAnimView.startAnimAttoInfo(str.substring(0, str.indexOf(" ")), str.substring(str.indexOf(" ")));
        }
    }

    public void stopAttoInfoAnim() {
        AttoInfoAnimView attoInfoAnimView = this.attoAnimView;
        if (attoInfoAnimView != null) {
            attoInfoAnimView.stopAnimAttoInfo();
        }
    }

    public void startFloatAnim(String str, String str2, WelcomFloatAnimView.FloatLinkListener floatLinkListener) {
        WelcomFloatAnimView welcomFloatAnimView = this.floatAnimView;
        if (welcomFloatAnimView != null) {
            welcomFloatAnimView.startAnimWelcomFloat(str, str2, floatLinkListener);
        }
    }

    public void stopFloatAnim() {
        WelcomFloatAnimView welcomFloatAnimView = this.floatAnimView;
        if (welcomFloatAnimView != null) {
            welcomFloatAnimView.stopAnimWelcomFloat();
        }
    }
}
