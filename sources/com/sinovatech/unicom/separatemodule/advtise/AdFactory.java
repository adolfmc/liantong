package com.sinovatech.unicom.separatemodule.advtise;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.advtise.admanager.PangleAdmanger;
import com.sinovatech.unicom.separatemodule.advtise.admanager.UnicomAdmanger;
import com.sinovatech.unicom.separatemodule.advtise.admanager.YLHAdmanger;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.jsplugin.AbstractJsHandler;
import com.sinovatech.unicom.separatemodule.advtise.jsplugin.BannerHandler;
import com.sinovatech.unicom.separatemodule.advtise.jsplugin.DrawHandler;
import com.sinovatech.unicom.separatemodule.advtise.jsplugin.VideoHandler;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AdFactory {
    public static final String HIDEBANNER = "hideBannerAd";
    public static final String HIDEDRAW = "hideXXLAd";
    public static final String JINGDONG = "JINGDONG";
    public static final String PANGLE = "PANGLE";
    public static final String SHOWBANNER = "showBannerAd";
    public static final String SHOWDRAW = "showXXLAd";
    public static final String SHOWREWORDVIDEO = "showVideoAd";
    public static final String SMATOO = "SMATOO";
    public static final String UNIOCMPIC = "UNIOCMPIC";
    public static final String UNIOCMVIDEO = "UNIOCMVIDEO";
    public static final String XINTAI = "XINTAI";
    public static final String YLH = "YLH";

    public static IAdInterface getAd(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity) {
        if (TextUtils.isEmpty(adConfigEntity.getAdType())) {
            adConfigEntity.setAdType("UNIOCMPIC");
        }
        UIUtils.logD("YLH", adConfigEntity.getAdType() + adConfigEntity.getAction());
        String adType = adConfigEntity.getAdType();
        char c = 65535;
        int hashCode = adType.hashCode();
        if (hashCode != -1942211997) {
            if (hashCode == 87957 && adType.equals("YLH")) {
                c = 1;
            }
        } else if (adType.equals("PANGLE")) {
            c = 0;
        }
        switch (c) {
            case 0:
                return PangleAdmanger.getInstance(appCompatActivity, adConfigEntity);
            case 1:
                return YLHAdmanger.getInstance(appCompatActivity, adConfigEntity);
            default:
                return UnicomAdmanger.getInstance(appCompatActivity, adConfigEntity);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static AbstractJsHandler getAdHandler(AppCompatActivity appCompatActivity, AdConfigEntity adConfigEntity, IAdInterface iAdInterface, BaseJSPlugin baseJSPlugin) {
        char c;
        String adAction = adConfigEntity.getAdAction();
        switch (adAction.hashCode()) {
            case -1910655566:
                if (adAction.equals("showXXLAd")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1293595695:
                if (adAction.equals("hideBannerAd")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 840941633:
                if (adAction.equals("showVideoAd")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 846354061:
                if (adAction.equals("hideXXLAd")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1587175052:
                if (adAction.equals("showBannerAd")) {
                    c = 0;
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
                return new BannerHandler(appCompatActivity, adConfigEntity, iAdInterface, baseJSPlugin);
            case 2:
            case 3:
                return new DrawHandler(appCompatActivity, adConfigEntity, iAdInterface, baseJSPlugin);
            case 4:
                return new VideoHandler(appCompatActivity, adConfigEntity, iAdInterface, baseJSPlugin);
            default:
                return null;
        }
    }
}
