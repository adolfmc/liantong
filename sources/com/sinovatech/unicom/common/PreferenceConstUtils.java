package com.sinovatech.unicom.common;

import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PreferenceConstUtils {
    public static final String BASICCUSTOM = "BasicCustom";
    public static final String CAREHOME = "CareHome";
    public static final String DONGTAIKAIPIN = "dongtaikaipinxiaoguo";
    public static final String GDT_GUIYININFO = "guangdiantongguiyinxinxi";
    public static final String HOME_CITY_SWITCH = "home_city_switch_yuanshen";
    public static final String HomeCreditTime = "HomecreditTime";
    public static final String HomeCumpAppIdConfig = "HomeCumpAppIdConfig";
    public static final String HomeCumpPublishType = "HomeCumpPublishType";
    public static final String ISALLOWNADD = "isAlloAdd";
    public static final String ISALLOWNOTIFICATION = "isAllowNotification";
    public static final String ISBASICCUSTOM = "IsBasicCustom";
    public static final String ISMIANDENGLU = "ismiandengluon";
    public static final String ISNOTIFYAGAIN = "isnotifyagain";
    public static final String IS_FUGAIANZHUANG = "user_is_fugai";
    public static final String MainTabPrefetchAppId = "MainTabPrefetchAppId";
    public static final String PUBLICPUSHMESSAGECOUNT = "publicPushMessageCount";
    public static final String PointUpdateTimeStamp = "PointUpdateTimeStamp";
    public static final String PointUsefulRedpoint = "PointUsefulRedpoint";
    public static final String SHARE_DEFAULT_SHARE = "share_default_share";
    public static final String SHARE_FiRST_OPEN = "share_first_open";
    public static final String SHARE_WOKOULINGCONTENT = "share_wokoulingcontent";
    public static final String UNICOM_APP_GUID_FLAG = "unicom_app_guid_flag";
    public static final String VIDEO_WATER_IS_OPEN = "video_water_is_open";
    public static final String WOKOULINGCONTENT = "wokoulingcontent";
    public static final String WOKOULING_URL = "wokouling_url";
    public static final String WebviewResourceIntercepterCache = "WebviewResourceIntercepterCache";
    public static final String WoDeXiaoHeiTiaoCumpAppIdConfig = "WoDeXiaoHeiTiaoCumpAppIdConfig";
    public static final String XIAOHEITIAOCHENGXU_SWITCH = "xiaoheitiao_xiaochngxu";
    public static final String XIAOHEITIAOCHENGXU_URL = "xiaoheitiao_xiaochngxu_url";
    public static final String app_crash_time = "unicom_app_crash_time";
    public static final String app_home_background_data = "app_home_background_data_" + UserManager.getInstance().getCurrentPhoneNumber();
    public static final String app_home_usa_dialog = "app_home_usa_dialog";
    public static final String app_main_AppCare = "1";
    public static final String app_main_AppCustom = "4";
    public static final String app_main_AppCustomPage = "3";
    public static final String app_main_AppNormal = "0";
    public static final String app_main_AppOtherLanguage = "2";
    public static final String app_main_type = "unicom_app_main_type";
    public static final String app_main_url = "unicom_app_main_url";
    public static final String dropoutAdvertise_reshow_afte_rrestart = "dropoutAdvertise_reshow_afte_rrestart";
    public static final String guideIsNeddYinsi = "guideIsNeddYinsi";
    public static final String gzdqingxidu = "gzdqingxidu";
    public static final String hasShowYinsi = "hasShowYinsi";
    public static final String home_mengceng_flag = "my_home_mengceng_flag";
    public static final String home_permission_dialog_flag = "home_permission_dialog_flag";
    public static final String home_video_center_masking_flag = "home_video_center_masking_flag";
    public static final String kaipingUrl = "kaiping_url";
    public static final String mulitview_details_masking_flag = "mulitview_details_masking_flag";
    public static final String mulitview_masking_flag = "mulitview_masking_flag";
    public static final String shualianxieyi = "shualianxieyi_new";
    public static final String versionCodeForOverlaySteup = "versionCodeForOverlaySteup";
    public static final String video_center_count_flag = "video_center_count_flag";
    public static final String video_center_masking_flag = "video_center_masking_flag";
    public static final String video_ring_list_masking_flag = "video_ring_list_masking_flag";
    public static final String video_ring_masking_flag = "video_ring_masking_flag";
    public static final String xieyiTimetempClick = "xieyiTimetempClick_0314";

    public static String shareUserNumSc() {
        return "unicomn_shareUserNumSc" + EncodeHelper.encoderByMd5(UserManager.getInstance().getCurrentPhoneNumber());
    }

    public static String hasCustomflg() {
        return "has_custom_flag_9" + EncodeHelper.encoderByMd5(UserManager.getInstance().getCurrentPhoneNumber());
    }

    public static String hasCustomflgNew() {
        return "has_custom_flag_10" + EncodeHelper.encoderByMd5(UserManager.getInstance().getCurrentPhoneNumber());
    }

    public static final String first_Switch_Refuse(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("---:    ");
        sb.append(EncodeHelper.encoderByMd5(UserManager.getInstance().getCurrentPhoneNumber() + "firstSwitchRefuse" + str));
        MsLogUtil.m7979d("first_Switch_Refuse", sb.toString());
        return EncodeHelper.encoderByMd5(UserManager.getInstance().getCurrentPhoneNumber() + "firstSwitchRefuse" + str);
    }

    public static final String getCardFloorMaterialIdKey() {
        return EncodeHelper.encoderByMd5("cardFloorMaterialId" + UserManager.getInstance().getCurrentPhoneNumber());
    }

    public static final String getCardGoodsIdKey() {
        return EncodeHelper.encoderByMd5("cardgooidslId" + UserManager.getInstance().getCurrentPhoneNumber());
    }

    public static String getXiaoheitiaoHeightKey() {
        return EncodeHelper.encoderByMd5("xiaoheitiao_height" + UserManager.getInstance().getCurrentPhoneNumber() + App.hasLogined());
    }
}
