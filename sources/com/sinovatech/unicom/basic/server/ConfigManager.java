package com.sinovatech.unicom.basic.server;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.Random;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ConfigManager {
    public static final String Config_Caifu_Url_Key = "Config_Caifu_Url_key";
    private static final String Config_HomePopUpUrl_Key = "Home_PopUp_Url_Key";
    private static final String Config_HttpsCNS_Key = "Config_HttpsCNS_Key";
    private static final String Config_Login_Image = "Config_Login_Image";
    private static final String Config_Login_Text = "Config_Login_Text";
    private static final String Config_Login_Textcolor = "Config_Login_Textcolor";
    private static final String Config_SharePoliteUrl_Key = "Config_SharePoliteUrl_Key";
    private static final String Config_mianmidenlgu_Key = "Config_mianmidenlgu_Key";
    private static final String Config_search_Key = "Config_search_Key";
    private static final String Config_shoushimima_Key = "Config_shoushimima_Key";
    private static final String Login_image1 = "Login_image1";
    private static final String Login_image2 = "Login_image2";
    private static final String Login_image3 = "Login_image3";
    public static final String NEWCOMERSWITCH = "newcomerSwitch";
    public static final String WECHAT_GET_DATA = "wechat_get_data";
    private static final String autoBindActivity = "autoBindActivity";
    private static final String autoLognBind1 = "autoLognBind1";
    private static final String autoLognBind2 = "autoLognBind2";
    private static final String autoLognBind3 = "autoLognBind3";
    private static final String autoLognBind4 = "autoLognBind4";
    private static final String autoSms = "autoSMS";
    private static final String clickUserColorForLoginRelationList = "clickUserColorForLoginRelationList";
    public static final String config_activity_jiaobiao = "config_activity_jiaobiao";
    public static final String config_caifu_jiaobiao = "config_caifu_jiaobiao";
    public static final String config_font_Size = "config_font_Size";
    public static final String config_gongjiri_end_time = "config_gongjiri_end_time";
    public static final String config_gongjiri_start_time = "config_gongjiri_start_time";
    public static final String config_home_jiaobiao = "config_home_jiaobiao";
    public static final String config_jump_def_act_city_code = "config_jump_def_act_city_code";
    public static final String config_jump_def_act_province_code = "config_jump_def_act_province_code";
    public static final String config_jump_def_act_switch = "config_jump_def_act_switch";
    public static final String config_jump_def_act_url = "config_jump_def_act_url";
    public static final String config_jump_def_act_version = "config_jump_def_act_version";
    public static final String config_market_jiaobiao = "config_market_jiaobiao";
    public static final String config_my_jiaobiao = "config_my_jiaobiao";
    public static final String config_on_or_off_splash = "config_on_or_off_splash";
    public static final String config_service_jiaobiao = "config_service_jiaobiao";
    public static final String config_unicom_clear_cache_phone_system = "config_unicom_clear_cache_phone_system";
    public static final String config_unicom_clear_cache_time = "config_unicom_clear_cache_time";
    public static final String config_unicom_home_kefu_url = "config_unicom_home_kefu_url";
    public static final String config_unicom_welcome_show_update_dialog = "config_unicom_welcome_show_update_dialog";
    private static final String customHint = "customHint";
    private static final String diffNetFlag = "diffNetFlag";
    private static final String ipv6Desc = "ipv6Desc";
    private static final String isEnabledNoCache = "isEnabledNoCache";
    public static final String languageSettingSwitch = "languageSettingSwitch";
    private static final String limitNumer = "limitNumer";
    private static final String menuName1st = "menuName1st";
    private static final String menuName2nd = "menuName2nd";
    private static final String menuName3rd = "menuName3rd";
    private static final String menuName4th = "menuName4th";
    private static final String menuUrl1st = "menuUrl1st";
    private static final String menuUrl2nd = "menuUrl2nd";
    private static final String menuUrl3rd = "menuUrl3rd";
    private static final String menuUrl4th = "menuUrl4th";
    private static final String newUserColorForBindList = "newUserColorForBindList";
    private static final String newUserColorForLoginRelationList = "newUserColorForLoginRelationList";
    private static final String oldUserColorForBindList = "oldUserColorForBindList";
    private static final String oldUserColorForLoginRelationList = "oldUserColorForLoginRelationList";
    private static final String openEmptyCardCheck = "openEmptyCardCheck";
    private static final String popupNotPopped = "popupNotPopped";
    private static final String privacyAgreement = "privacyAgreement";
    private static final String qiangzhiTip = "qiangzhiTip";
    private static final String reloadHint = "reloadHint";
    private static final String rightSlipHint = "rightSlipHint";
    public static final String shiLaoHuaDaZiKaiGuan = "shilaohua_dazi_kaiguan";
    public static final String shiLaoHuaKaiGuan = "shilaohua_kaiguan";
    private static final String smsHint = "smsHint";
    private static final String smsNumber = "smsnumber";
    public static final String unicom_colleciont_switch = "unicom_colleciont_switch";
    public static final String unicom_yinsi_dialog_code = "unicom_yinsi_dialog_code";
    public static final String unicom_yinsi_login_agree = "unicom_yinsi_login_agree";
    private static final String userAgreement = "userAgreement";
    private static final String viewflipertime = "viewflipertime";
    private static final String welomeUrl = "welomeUrl";
    public static final String zheDiePingUrl = "zhe_die_ping_url";
    private Context activityContext;
    public final String Config_Login_IntervalTime_Key = "Config_Login_IntervalTime_Key";
    public final String Config_Login_Timeout_Key = "Config_Login_Timeout_Key";
    public final String Config_Login_RetryNum_Key = "Config_Login_RetryNum_Key";
    public final String Config_ADPush_IntervalTime_Key = "Config_ADPush_IntervalTime_Key";
    public final String Config_MsgPush_IntervalTime_Key = "Config_MsgPush_IntervalTime_Key";
    public final String Config_IPLink_Key = "Config_IPLink_Key";
    public final String Config_homeNewsRemindShowTime = "Config_homeNewsRemindShowTime";
    public final String Config_RegisterURL_Key = "Config_RegisterURL_Key";
    public final String Config_GetMessageURL_Key = "Config_GetMessageURL_Key";
    public final String Config_Logfile_Upload_Key = "Config_Logfile_Upload_Key";
    public final String Config_PopupDelayTime_Key = "Config_PopupDelayTime_Key";
    public int Default_Login_IntervalTime = 4;
    public int Default_Login_Timeout = 10;
    public int Default_Login_RetryNum = 2;
    public int Default_ADPush_IntervalTime = 600;
    public int Default_MsgPush_IntervalTime = 300;
    public final String Config_HttpsSwitch_Key = "Config_HttpsSwitch_Key";
    public final String Config_StasticsUpload_Key = "Config_StasticsUpload_Key";
    public final String Config_StasticsUploadUrl_Key = "Config_StasticsUploadUrl_Key";
    public final String Config_StartMaxTime_Key = "Config_StartMaxTime_Key";
    public final String Config_AdMaxTime_Key = "Config_AdMaxTime_Key";
    public final String Config_LoginLog_Key = "Config_LoginLog_Key";
    public final String Config_YuyinSearch_Key = "Config_YuyinSearch_Key";
    public final String Config_MyUnicome_MyCaifu_Key = "Config_MyUnicome_MyCaifu_Key";
    public final String Config_MyUnicome_Privil_Key = "Config_MyUnicome_Privil_Key";
    public final String Config_Find_Url_Key = "Config_Find_Url_Key_new";
    public final String Config_Huaheng_Start_Key = "Config_Huaheng_Start_Key";
    public final String Config_Birthdaytime_Key = "Config_Birthdaytime_Key";

    /* renamed from: sp */
    private SharePreferenceUtil f18400sp = App.getSharePreferenceUtil();

    public int getCacheVideoMax() {
        return 5;
    }

    public int getCacheVideoTime() {
        return 10;
    }

    public void setCacheVideoMax(int i) {
    }

    public void setCacheVideoTime(int i) {
    }

    public ConfigManager(Context context) {
        this.activityContext = context;
    }

    public ConfigManager() {
    }

    public void setYuleUnloginTip(String str) {
        App.getSharePreferenceUtil().putString(rightSlipHint, str);
    }

    public String getYuleUnloginTip() {
        String string = App.getSharePreferenceUtil().getString(rightSlipHint);
        return TextUtils.isEmpty(string) ? "魔性、潮酷、激萌短视频；热门影视、院线大片；品质音乐；畅销好书！免费免流量" : string;
    }

    public void setCustomHint(String str) {
        App.getSharePreferenceUtil().putString(customHint, str);
    }

    public String getCustomHint() {
        String string = App.getSharePreferenceUtil().getString(customHint);
        return TextUtils.isEmpty(string) ? "请点击按钮前往自定义！" : string;
    }

    public void setReloadTip(String str) {
        App.getSharePreferenceUtil().putString(reloadHint, str);
    }

    public String getReloadTip() {
        String string = App.getSharePreferenceUtil().getString(reloadHint);
        return TextUtils.isEmpty(string) ? "加载失败，请刷新重试！" : string;
    }

    public void setPopupNotPopped(String str) {
        App.getSharePreferenceUtil().putString(popupNotPopped, str);
    }

    public String getPopupNotPopped() {
        return App.getSharePreferenceUtil().getString(popupNotPopped);
    }

    public void setAutoLognBind1(boolean z) {
        App.getSharePreferenceUtil().putBoolean(autoLognBind1, Boolean.valueOf(z));
    }

    public boolean getAutoLognBind1() {
        return App.getSharePreferenceUtil().getBoolean(autoLognBind1);
    }

    public void setAutoLognBind2(boolean z) {
        App.getSharePreferenceUtil().putBoolean(autoLognBind2, Boolean.valueOf(z));
    }

    public boolean getAutoLognBind2() {
        return App.getSharePreferenceUtil().getBoolean(autoLognBind2);
    }

    public void setAutoLognBind3(boolean z) {
        App.getSharePreferenceUtil().putBoolean(autoLognBind3, Boolean.valueOf(z));
    }

    public boolean getAutoLognBind3() {
        return App.getSharePreferenceUtil().getBoolean(autoLognBind3);
    }

    public void setAutoLognBind4(boolean z) {
        App.getSharePreferenceUtil().putBoolean(autoLognBind4, Boolean.valueOf(z));
    }

    public boolean getAutoLognBind4() {
        return App.getSharePreferenceUtil().getBoolean(autoLognBind4);
    }

    public void setLoginText(String str) {
        App.getSharePreferenceUtil().putString(Config_Login_Text, str);
    }

    public String getLoginText() {
        return App.getSharePreferenceUtil().getString(Config_Login_Text);
    }

    public void setLoginTextColor(int i) {
        App.getSharePreferenceUtil().putInt(Config_Login_Textcolor, i);
    }

    public int getLoginTextColor() {
        return App.getSharePreferenceUtil().getInt(Config_Login_Textcolor);
    }

    public void setLoginImage(String str) {
        App.getSharePreferenceUtil().putString(Config_Login_Image, str);
    }

    public String getLoginImage() {
        return App.getSharePreferenceUtil().getString(Config_Login_Image);
    }

    public void setLogin_image1(String str) {
        App.getSharePreferenceUtil().putString(Login_image1, str);
    }

    public String getLogin_image1() {
        return App.getSharePreferenceUtil().getString(Login_image1);
    }

    public void setLogin_image2(String str) {
        App.getSharePreferenceUtil().putString(Login_image2, str);
    }

    public String getLogin_image2() {
        return App.getSharePreferenceUtil().getString(Login_image2);
    }

    public void setLogin_image3(String str) {
        App.getSharePreferenceUtil().putString(Login_image3, str);
    }

    public String getLogin_image3() {
        return App.getSharePreferenceUtil().getString(Login_image3);
    }

    public void setLimitNumer(String str) {
        App.getSharePreferenceUtil().putString(limitNumer, str);
    }

    public String getLimitNumer() {
        return App.getSharePreferenceUtil().getString(limitNumer);
    }

    public void setMSSNumer(int i) {
        App.getSharePreferenceUtil().putInt(smsNumber, i);
    }

    public int getSMSNumer() {
        return App.getSharePreferenceUtil().getInt(smsNumber);
    }

    public void setIpv6Desc(String str) {
        App.getSharePreferenceUtil().putString(ipv6Desc, str);
    }

    public String getIpv6Desc() {
        return App.getSharePreferenceUtil().getString(ipv6Desc);
    }

    public void setAutoSms(boolean z) {
        App.getSharePreferenceUtil().putBoolean(autoSms, Boolean.valueOf(z));
    }

    public boolean getAutoSms() {
        return App.getSharePreferenceUtil().getBooleanTrue(autoSms);
    }

    public void setAutoBindActivity(boolean z) {
        App.getSharePreferenceUtil().putBoolean(autoBindActivity, Boolean.valueOf(z));
    }

    public boolean getAutoBindActivity() {
        return App.getSharePreferenceUtil().getBoolean(autoBindActivity);
    }

    public void setSMSHint(String str) {
        App.getSharePreferenceUtil().putString(smsHint, str);
    }

    public String getSMSHint() {
        return App.getSharePreferenceUtil().getString(smsHint);
    }

    public int getLoginInterval() {
        String string = App.getSharePreferenceUtil().getString("Config_Login_IntervalTime_Key");
        int i = this.Default_Login_IntervalTime;
        try {
            int parseInt = Integer.parseInt(string);
            return parseInt >= 0 ? parseInt : i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public void setLoginInterval(String str) {
        App.getSharePreferenceUtil().putString("Config_Login_IntervalTime_Key", str);
    }

    public void setSearchSwitch(String str) {
        App.getSharePreferenceUtil().putString(Config_search_Key, str);
    }

    public String getSearchSwitch() {
        return App.getSharePreferenceUtil().getString(Config_search_Key);
    }

    public void setMianmidenglu(String str) {
        App.getSharePreferenceUtil().putString(Config_mianmidenlgu_Key, str);
    }

    public String getMianmidenglu() {
        return App.getSharePreferenceUtil().getString(Config_mianmidenlgu_Key);
    }

    public void setShoushimima(String str) {
        App.getSharePreferenceUtil().putString(Config_shoushimima_Key, str);
    }

    public String getSoushimiam() {
        return App.getSharePreferenceUtil().getString(Config_shoushimima_Key);
    }

    public int getLoginTimeout() {
        String string = App.getSharePreferenceUtil().getString("Config_Login_Timeout_Key");
        int i = this.Default_Login_Timeout;
        try {
            int parseInt = Integer.parseInt(string);
            return parseInt > 0 ? parseInt : i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public void setLoginTimeout(String str) {
        App.getSharePreferenceUtil().putString("Config_Login_Timeout_Key", str);
    }

    public int getLoginRetryNum() {
        String string = App.getSharePreferenceUtil().getString("Config_Login_RetryNum_Key");
        int i = this.Default_Login_RetryNum;
        try {
            if (App.getInstance().getApplicationContext().getPackageName().endsWith("beta")) {
                return i;
            }
            int parseInt = Integer.parseInt(string);
            return parseInt >= 0 ? parseInt : i;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public void setLoginRetryNum(String str) {
        App.getSharePreferenceUtil().putString("Config_Login_RetryNum_Key", str);
    }

    public int getMsgPushInterval() {
        String string = App.getSharePreferenceUtil().getString("Config_MsgPush_IntervalTime_Key");
        int i = this.Default_MsgPush_IntervalTime;
        try {
            int parseInt = Integer.parseInt(string);
            if (parseInt > 0) {
                i = parseInt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i * 1000;
    }

    public void setMsgPushInterval(String str) {
        App.getSharePreferenceUtil().putString("Config_MsgPush_IntervalTime_Key", str);
    }

    public String getRegisterURL() {
        String string = App.getSharePreferenceUtil().getString("Config_RegisterURL_Key");
        return (URLEnvironmentConfig.isDevelopmentEnvironment() || URLEnvironmentConfig.isPrepubEnvironment()) ? "https://ecstest2018.10010.com/pushweb/push" : TextUtils.isEmpty(string) ? URLSet.getRegistpushservice() : string;
    }

    public void setRegisterURL(String str) {
        App.getSharePreferenceUtil().putString("Config_RegisterURL_Key", str);
    }

    public String getGetMessageURL() {
        String string = App.getSharePreferenceUtil().getString("Config_GetMessageURL_Key");
        return (URLEnvironmentConfig.isDevelopmentEnvironment() || URLEnvironmentConfig.isPrepubEnvironment()) ? "https://ecstest2018.10010.com/pushweb/push" : TextUtils.isEmpty(string) ? URLSet.getSendpushservice() : string;
    }

    public void setGetMessageURL(String str) {
        App.getSharePreferenceUtil().putString("Config_GetMessageURL_Key", str);
    }

    public String getLogfileUploadKey() {
        String string = App.getSharePreferenceUtil().getString("Config_Logfile_Upload_Key");
        return TextUtils.isEmpty(string) ? "0" : string;
    }

    public void setLogfileUploadKey(String str) {
        App.getSharePreferenceUtil().putString("Config_Logfile_Upload_Key", str);
    }

    public int getPopupDelayTimeKey() {
        int i = App.getSharePreferenceUtil().getInt("Config_PopupDelayTime_Key");
        if (i == 0) {
            return 2;
        }
        return i;
    }

    public void setPopupDelayTimeKey(int i) {
        App.getSharePreferenceUtil().putInt("Config_PopupDelayTime_Key", i);
    }

    public String getHttpsSwitchKey() {
        String string = App.getSharePreferenceUtil().getString("Config_HttpsSwitch_Key");
        return TextUtils.isEmpty(string) ? "0" : string;
    }

    public void setHttpsSwitchKey(String str) {
        App.getSharePreferenceUtil().putString("Config_HttpsSwitch_Key", str);
        if (TextUtils.isEmpty(str) || str.equals("0")) {
            URLEnvironmentConfig.switchHttps(false);
        } else {
            URLEnvironmentConfig.switchHttps(true);
        }
    }

    public String getStasticsUploadKey() {
        String string = App.getSharePreferenceUtil().getString("Config_StasticsUpload_Key");
        return TextUtils.isEmpty(string) ? "0" : string;
    }

    public void setStasticsUploadKey(String str) {
        App.getSharePreferenceUtil().putString("Config_StasticsUpload_Key", str);
    }

    public String getStasticsUploadUrlKey() {
        String string = App.getSharePreferenceUtil().getString("Config_StasticsUploadUrl_Key");
        return TextUtils.isEmpty(string) ? URLSet.getMenuclick_tongji_url() : string;
    }

    public void setStasticsUploadUrlKey(String str) {
        App.getSharePreferenceUtil().putString("Config_StasticsUploadUrl_Key", str);
    }

    public String getStartMaxTimeKey() {
        String string = App.getSharePreferenceUtil().getString("Config_StartMaxTime_Key");
        return TextUtils.isEmpty(string) ? "3" : string;
    }

    public void setStartMaxTimeKey(String str) {
        App.getSharePreferenceUtil().putString("Config_StartMaxTime_Key", str);
    }

    public String getAdMaxTimeKey() {
        String string = App.getSharePreferenceUtil().getString("Config_AdMaxTime_Key");
        return TextUtils.isEmpty(string) ? "3" : string;
    }

    public void setAdMaxTimeKey(String str) {
        App.getSharePreferenceUtil().putString("Config_AdMaxTime_Key", str);
    }

    public String getLoginLogKey() {
        String string = App.getSharePreferenceUtil().getString("Config_LoginLog_Key");
        return TextUtils.isEmpty(string) ? URLSet.getLogfile_upload_url() : string;
    }

    public void setLoginLogKey(String str) {
        App.getSharePreferenceUtil().putString("Config_LoginLog_Key", str);
    }

    public String getYuyinSearchKey() {
        String string = App.getSharePreferenceUtil().getString("Config_YuyinSearch_Key");
        return TextUtils.isEmpty(string) ? URLSet.getSearchapi() : string;
    }

    public void setYuyinSearchKey(String str) {
        App.getSharePreferenceUtil().putString("Config_YuyinSearch_Key", str);
    }

    public String getMyUnicomePrivilKey() {
        String string = App.getSharePreferenceUtil().getString("Config_MyUnicome_Privil_Key");
        return TextUtils.isEmpty(string) ? URLSet.getMyunicom_privil_url() : string;
    }

    public void setMyUnicomePrivilKey(String str) {
        App.getSharePreferenceUtil().putString("Config_MyUnicome_Privil_Key", str);
    }

    public String getMyUnicomeMyCaifuKey() {
        String string = App.getSharePreferenceUtil().getString("Config_MyUnicome_MyCaifu_Key");
        return TextUtils.isEmpty(string) ? URLSet.getMyunicom_info_url2() : string;
    }

    public void setMyUnicomeMyCaifuKey(String str) {
        App.getSharePreferenceUtil().putString("Config_MyUnicome_MyCaifu_Key", str);
    }

    public String getFindUrlKey() {
        String string = App.getSharePreferenceUtil().getString(Config_Caifu_Url_Key);
        return TextUtils.isEmpty(string) ? URLSet.getNewFaxian() : string;
    }

    public void setFindUrlKey(String str) {
        App.getSharePreferenceUtil().putString(Config_Caifu_Url_Key, str);
    }

    public void setSharePoliteUrl(String str) {
        App.getSharePreferenceUtil().putString(Config_SharePoliteUrl_Key, str);
    }

    public String getHuahengStartKey() {
        String string = App.getSharePreferenceUtil().getString("Config_Huaheng_Start_Key");
        return TextUtils.isEmpty(string) ? "1" : string;
    }

    public void setHuahengStartKey(String str) {
        App.getSharePreferenceUtil().putString("Config_Huaheng_Start_Key", str);
    }

    public int getBirthday() {
        int i = App.getSharePreferenceUtil().getInt("Config_Birthdaytime_Key");
        if (i == 0) {
            return 10;
        }
        return i;
    }

    public void setBirthday(int i) {
        App.getSharePreferenceUtil().putInt("Config_Birthdaytime_Key", i);
    }

    public static String getConfigCNS() {
        return App.getSharePreferenceUtil().getString(Config_HttpsCNS_Key);
    }

    public static void setConfigCNS(String str) {
        App.getSharePreferenceUtil().putString(Config_HttpsCNS_Key, str);
    }

    public static String getConfig_HomePopUpUrl_Key() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileServiceClient-test/activity/homePopUpActivity.htm" : App.getSharePreferenceUtil().getString(Config_HomePopUpUrl_Key);
    }

    public static void setConfig_HomePopUpUrl_Key(String str) {
        App.getSharePreferenceUtil().putString(Config_HomePopUpUrl_Key, str);
    }

    public Boolean getConfig_IPLink_Key() {
        boolean z;
        try {
            z = "1".equals(App.getSharePreferenceUtil().getString("Config_IPLink_Key"));
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return Boolean.valueOf(z);
    }

    public void setConfig_IPLink_Key(String str) {
        App.getSharePreferenceUtil().putString("Config_IPLink_Key", str);
    }

    public void setHomeNewsRemindShowTime(String str) {
        App.getSharePreferenceUtil().putString("Config_homeNewsRemindShowTime", str);
    }

    public int getHomeNewsRemindShowTime() {
        int i = 2;
        try {
            int parseInt = Integer.parseInt(App.getSharePreferenceUtil().getString("Config_homeNewsRemindShowTime"));
            if (parseInt > 0) {
                i = parseInt;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i * 1000;
    }

    public String getNewUserColorForBindList() {
        return App.getSharePreferenceUtil().getString(newUserColorForBindList);
    }

    public void setNewUserColorForBindList(String str) {
        App.getSharePreferenceUtil().putString(newUserColorForBindList, str);
    }

    public String getOldUserColorForBindList() {
        return App.getSharePreferenceUtil().getString(oldUserColorForBindList);
    }

    public void setOldUserColorForBindList(String str) {
        App.getSharePreferenceUtil().putString(oldUserColorForBindList, str);
    }

    public String getNewUserColorForLoginRelationList() {
        return App.getSharePreferenceUtil().getString(newUserColorForLoginRelationList);
    }

    public void setNewUserColorForLoginRelationList(String str) {
        App.getSharePreferenceUtil().putString(newUserColorForLoginRelationList, str);
    }

    public String getOldUserColorForLoginRelationList() {
        return App.getSharePreferenceUtil().getString(oldUserColorForLoginRelationList);
    }

    public void setOldUserColorForLoginRelationList(String str) {
        App.getSharePreferenceUtil().putString(oldUserColorForLoginRelationList, str);
    }

    public String getClickUserColorForLoginRelationList() {
        return App.getSharePreferenceUtil().getString(clickUserColorForLoginRelationList);
    }

    public void setClickUserColorForLoginRelationList(String str) {
        App.getSharePreferenceUtil().putString(clickUserColorForLoginRelationList, str);
    }

    public void setMenuName1st(String str) {
        App.getSharePreferenceUtil().putString(menuName1st, str);
    }

    public String getMenuName1st() {
        return App.getSharePreferenceUtil().getString(menuName1st);
    }

    public void setMenuName2nd(String str) {
        App.getSharePreferenceUtil().putString(menuName2nd, str);
    }

    public String getMenuName2nd() {
        return App.getSharePreferenceUtil().getString(menuName2nd);
    }

    public void setMenuName3rd(String str) {
        App.getSharePreferenceUtil().putString(menuName3rd, str);
    }

    public String getMenuName3rd() {
        return App.getSharePreferenceUtil().getString(menuName3rd);
    }

    public void setMenuName4th(String str) {
        App.getSharePreferenceUtil().putString(menuName4th, str);
    }

    public String getMenuName4th() {
        return App.getSharePreferenceUtil().getString(menuName4th);
    }

    public void setMenuUrl1s(String str) {
        App.getSharePreferenceUtil().putString(menuUrl1st, str);
    }

    public String getMenuUrl1st() {
        return App.getSharePreferenceUtil().getString(menuUrl1st);
    }

    public void setMenuUrl2nd(String str) {
        App.getSharePreferenceUtil().putString(menuUrl2nd, str);
    }

    public String getMenuUrl2nd() {
        return App.getSharePreferenceUtil().getString(menuUrl2nd);
    }

    public void setMenuUrl3rd(String str) {
        App.getSharePreferenceUtil().putString(menuUrl3rd, str);
    }

    public String getMenuUrl3rd() {
        return App.getSharePreferenceUtil().getString(menuUrl3rd);
    }

    public void setMenuUrl4th(String str) {
        App.getSharePreferenceUtil().putString(menuUrl4th, str);
    }

    public String getMenuUrl4th() {
        return App.getSharePreferenceUtil().getString(menuUrl4th);
    }

    public void setViewflipertimeh(int i) {
        App.getSharePreferenceUtil().putInt(viewflipertime, i);
    }

    public int getViewflipertime() {
        int i = App.getSharePreferenceUtil().getInt(viewflipertime);
        if (i == 0) {
            return 10000;
        }
        return i * 1000;
    }

    public void setDiffNetFlag(String str) {
        App.getSharePreferenceUtil().putString(diffNetFlag, str);
    }

    public String getDiffNetFlag() {
        return App.getSharePreferenceUtil().getString(diffNetFlag);
    }

    public void setQiangzhiTip(String str) {
        this.f18400sp.putString(qiangzhiTip, str);
    }

    public String getQiangzhiTip() {
        return this.f18400sp.getString(qiangzhiTip);
    }

    public void setPrivacyAgreement(String str) {
        this.f18400sp.putString(privacyAgreement, str);
    }

    public String getPrivacyAgreement() {
        return this.f18400sp.getString(privacyAgreement);
    }

    public void setUserAgreement(String str) {
        this.f18400sp.putString(userAgreement, str);
    }

    public String getUserAgreement() {
        return this.f18400sp.getString(userAgreement);
    }

    public void setOpenEmptyCardCheck(String str) {
        this.f18400sp.putString(openEmptyCardCheck, str);
    }

    public String getOpenEmptyCardCheck() {
        return this.f18400sp.getString(openEmptyCardCheck);
    }

    public void setOpenOneTouchLoading(String str) {
        this.f18400sp.putString("OpenOneTouchLoading", str);
    }

    public String getOpenOneTouchLoading() {
        return this.f18400sp.getString("OpenOneTouchLoading");
    }

    public void setServiceLinkUrl(String str) {
        this.f18400sp.putString("serviceLinkUrl", str);
    }

    public String getServiceLinkUrl() {
        String string = this.f18400sp.getString("serviceLinkUrl");
        if (TextUtils.isEmpty(string)) {
            string = URLSet.getOneKeyLoginQuHao();
        }
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            string = "https://client.10010.com/mobileService/dFreeLogin/getDFreeLoginDetail.htm";
        }
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileServiceClient-test/dFreeLogin/getDFreeLoginDetail.htm" : string;
    }

    public void setExtranetActivityPageUrl(String str) {
        this.f18400sp.putString("ExtranetActivityPageUrl", str);
    }

    public String getExtranetActivityPageUrl() {
        String string = this.f18400sp.getString("ExtranetActivityPageUrl");
        return TextUtils.isEmpty(string) ? URLSet.getYiwangUrl() : string;
    }

    public void setCardSwitchTime(String str) {
        this.f18400sp.putString("CardSwitchTime", str);
    }

    public String getCardSwitchTime() {
        return this.f18400sp.getString("CardSwitchTime");
    }

    public int getCardSwitchTime1() {
        try {
            return Integer.parseInt(this.f18400sp.getString("CardSwitchTime").split(",")[0]) * 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return 5000;
        }
    }

    public int getCardSwitchTime2() {
        try {
            return Integer.parseInt(this.f18400sp.getString("CardSwitchTime").split(",")[1]) * 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return 5000;
        }
    }

    public void setOneKeyQuHaoPrefix(String str) {
        this.f18400sp.putString("onekeyloginUrl", str);
    }

    public String getOneKeyQuHaoPrefix() {
        String string = this.f18400sp.getString("onekeyloginUrl");
        return TextUtils.isEmpty(string) ? URLSet.getOneKeyQuHaoPrefix() : string;
    }

    public void setTecentUtilTime(String str) {
        this.f18400sp.putString("setTecentUtilTime", str);
    }

    public void setLastClickTime(String str) {
        this.f18400sp.putString("tecentTime", str);
    }

    public boolean getIsTecentVisiable() {
        String string = this.f18400sp.getString("tecentTime");
        String string2 = this.f18400sp.getString("setTecentUtilTime");
        if (TextUtils.isEmpty(string2)) {
            string2 = "1";
        }
        if (!TextUtils.isEmpty(string)) {
            try {
                return System.currentTimeMillis() > Long.parseLong(string) + ((Long.parseLong(string2) * 60) * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public void setMainLiuSPID(String str) {
        this.f18400sp.putString("mianLiuSPID", str);
    }

    public String getMianLiuSPID() {
        return this.f18400sp.getString("mianLiuSPID");
    }

    public void setMianLiuPID(String str) {
        this.f18400sp.putString("mianLiuPID", str);
    }

    public String getMainLiuPID() {
        return this.f18400sp.getString("mianLiuPID");
    }

    public void setMianLiuPORTALID(String str) {
        this.f18400sp.putString("mianLiuPORTALID", str);
    }

    public String getMianLiuPORTALID() {
        return this.f18400sp.getString("mianLiuPORTALID");
    }

    public void setMianLiuSPKEY(String str) {
        this.f18400sp.putString("mianLiuSPKEY", str);
    }

    public String getMianLiuSPKEY() {
        return this.f18400sp.getString("mianLiuSPKEY");
    }

    public void setTencentAdvertFlag(String str) {
        this.f18400sp.putString("tencentAdvertFlag", str);
    }

    public String getTencentAdvertFlag() {
        return this.f18400sp.getString("tencentAdvertFlag");
    }

    public void setYuiyinYanzheng(String str) {
        this.f18400sp.putString("yuyiinyanzheng", str);
    }

    public String getYuyinYanzheng() {
        return this.f18400sp.getString("yuyiinyanzheng");
    }

    public void setAddressGuanliUrl(String str) {
        this.f18400sp.putString("AddressGuanliUrl", str);
    }

    public String getAddressGuanliUrl() {
        return this.f18400sp.getString("AddressGuanliUrl");
    }

    public void setActivityDomain(String str) {
        this.f18400sp.putString("ActivityDomain", str);
    }

    public String getActivityDomainString() {
        String string = this.f18400sp.getString("ActivityDomain");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        String[] split = string.split(",");
        return split[new Random().nextInt(split.length)];
    }

    public void setCityUrl(String str) {
        this.f18400sp.putString("city_home", str);
    }

    public String getCityUrl() {
        String string = this.f18400sp.getString("city_home");
        return TextUtils.isEmpty(string) ? URLSet.getDishizhuanqu() : string;
    }

    public void setHomeIcon(String str) {
        this.f18400sp.putString("homeIcon", str);
    }

    public String getHomeIcon() {
        return this.f18400sp.getString("homeIcon");
    }

    public void setServiceIcon(String str) {
        this.f18400sp.putString("serviceIcon", str);
    }

    public String getServiceIcon() {
        return this.f18400sp.getString("serviceIcon");
    }

    public void setMarketIcon(String str) {
        this.f18400sp.putString("marketIconIcon", str);
    }

    public String getMarketIcon() {
        return this.f18400sp.getString("marketIconIcon");
    }

    public void setFindIcon(String str) {
        this.f18400sp.putString("findIcon", str);
    }

    public String getFindIcon() {
        return this.f18400sp.getString("findIcon");
    }

    public void setMyIcon(String str) {
        this.f18400sp.putString("myIcon", str);
    }

    public String getMyIcon() {
        return this.f18400sp.getString("myIcon");
    }

    public void setActivityIcon(String str) {
        this.f18400sp.putString("activityIcon", str);
    }

    public String getActivityIcon() {
        return this.f18400sp.getString("activityIcon");
    }

    public void setHomeHide(String str) {
        this.f18400sp.putString("homeHide", str);
    }

    public String getHomeHide() {
        return this.f18400sp.getString("homeHide");
    }

    public void setServiceHide(String str) {
        this.f18400sp.putString("serviceHide", str);
    }

    public String getServiceHide() {
        return this.f18400sp.getString("serviceHide");
    }

    public void setActivityHide(String str) {
        this.f18400sp.putString("activityHide", str);
    }

    public String getActivityHide() {
        return this.f18400sp.getString("activityHide");
    }

    public void setMarketHide(String str) {
        this.f18400sp.putString("marketHide", str);
    }

    public String getMarketHide() {
        return this.f18400sp.getString("marketHide");
    }

    public void setCaiFuHide(String str) {
        this.f18400sp.putString("caifuHide", str);
    }

    public String getCaiFuHide() {
        return this.f18400sp.getString("caifuHide");
    }

    public void setMyHide(String str) {
        this.f18400sp.putString("myHide", str);
    }

    public String getMyHide() {
        return this.f18400sp.getString("myHide");
    }

    public void setLiulanyouli(String str) {
        this.f18400sp.putString("liulanyouli111", str);
    }

    public String getLiulanyouli() {
        return this.f18400sp.getString("liulanyouli111");
    }

    public void setWhiteList(String str) {
        this.f18400sp.putString("white_list", str);
    }

    public String getWhiteList() {
        return this.f18400sp.getString("white_list");
    }

    public void setZhiboLanjieUrl(String str) {
        this.f18400sp.putString("zhibo_lanjie_url", str);
    }

    public String getZhiboLanjieUrl() {
        return this.f18400sp.getString("zhibo_lanjie_url");
    }

    public void setJGbaohuo(String str) {
        this.f18400sp.putString("jg_baohuo", str);
    }

    public String getJGbaohuo() {
        return this.f18400sp.getString("jg_baohuo");
    }

    public void setYiWangState(String str) {
        this.f18400sp.putString("showMemberTel", str);
    }

    public String getYiWangState() {
        return this.f18400sp.getString("showMemberTel");
    }

    public void setServiceSearchTime(int i) {
        this.f18400sp.putInt("service_search_time", i);
    }

    public int getServiceSearchTime() {
        return this.f18400sp.getInt("service_search_time");
    }

    public void setServiceSearchArea(String str) {
        this.f18400sp.putString("service_search_area", str);
    }

    public String getServiceSearchArea() {
        return this.f18400sp.getString("service_search_area");
    }

    public void setXieyiTimetemp(String str) {
        this.f18400sp.putString("xieyitimetemp", str);
    }

    public String getXieyiTimeTemp() {
        return this.f18400sp.getString("xieyitimetemp");
    }

    public void setXieyiClickTime(String str) {
        this.f18400sp.putString("setXieyiClickTime", str);
    }

    public String getXieyiClickTime() {
        return this.f18400sp.getString("setXieyiClickTime");
    }

    public void setLanguageSettingSwitch(String str) {
        if (TextUtils.equals("1", str)) {
            App.getSharePreferenceUtil().putString(LanguageUtil.TIMESTAMP_KEY, "");
            App.getSharePreferenceUtil().putString(LanguageUtil.LOCALE_JSON_VALUE, "");
            App.getSharePreferenceUtil().putString(LanguageUtil.LOCALE_KEY, "");
            LanguageUtil.getInstance().setLanguage_now("");
            App.getSharePreferenceUtil().putString(LanguageUtil.LOCALE_KEY_NOW, "");
            App.getSharePreferenceUtil().putString(LanguageUtil.LOCALE_LEFT_TO_RIGHT, "");
            App.getSharePreferenceUtil().putString(LanguageUtil.LOCALE_LINK_URL, "");
        }
        this.f18400sp.putString(languageSettingSwitch, str);
    }

    public String getLanguageSettingSwitch() {
        return this.f18400sp.getString(languageSettingSwitch);
    }

    public void setCareProvinceSwitch(String str) {
        this.f18400sp.putString("careProvinceSwitch", str);
    }

    public String getCareProvinceSwitch() {
        return this.f18400sp.getString("careProvinceSwitch");
    }

    public void setSweepFaceSafetyDesc(String str) {
        this.f18400sp.putString("sweepFaceSafetyDesc", str);
    }

    public String getSweepFaceSafetyDesc() {
        String string = this.f18400sp.getString("sweepFaceSafetyDesc");
        return TextUtils.isEmpty(string) ? "    在您开通和使用刷脸登录功能前，请您充分阅读、理解并接受刷脸登录的全部内容。\n    为了保障您的账户信息安全，需采集您的人脸信息进行身份核实。刷脸验证服务是通过人脸信息比对方式，来核对您的身份信息服务。服务过程中，在您同意我们收集您的人脸信息并做必要的处理后，来提升刷脸验证的准确性与信息的安全性。\n    具体信息说明请查看" : string;
    }

    public void setSweepFaceLogin(String str) {
        this.f18400sp.putString("SweepFaceLogin", str);
    }

    public String getSweepFaceLogin() {
        return this.f18400sp.getString("SweepFaceLogin");
    }

    public void setBigFont(String str) {
        this.f18400sp.putString("config_big_font", str);
        if (TextUtils.equals("1", str)) {
            return;
        }
        App.getSharePreferenceUtil().putString(config_font_Size, "config_font_standard");
    }

    public String getBigFont() {
        return this.f18400sp.getString("config_big_font");
    }

    public void setMiddleButtonTitle(String str) {
        this.f18400sp.putString("middleButtonTitle", str);
    }

    public String getMiddleButtonTitle() {
        String string = this.f18400sp.getString("middleButtonTitle");
        return TextUtils.isEmpty(string) ? "登录" : string;
    }

    public void setMiddleButtonDesc(String str) {
        this.f18400sp.putString("middleButtonDesc", str);
    }

    public String getMiddleButtonDesc() {
        String string = this.f18400sp.getString("middleButtonDesc");
        return TextUtils.isEmpty(string) ? "请登录" : string;
    }

    public void setFWZUState(String str) {
        this.f18400sp.putString("user_fuwuzhuanyuan", str);
    }

    public String setFWZUState() {
        return this.f18400sp.getString("user_fuwuzhuanyuan");
    }

    public void setMianliuOpenH5(String str) {
        this.f18400sp.putString("setMianliuOpenH5", str);
    }

    public String getMianliuOpenH5() {
        String string = this.f18400sp.getString("setMianliuOpenH5");
        return TextUtils.isEmpty(string) ? "0" : string;
    }

    public void setMianliuOpen(String str) {
        this.f18400sp.putString("setMianliuOpen", str);
    }

    public String getMianliuOpen() {
        String string = this.f18400sp.getString("setMianliuOpen");
        return TextUtils.isEmpty(string) ? "0" : string;
    }

    public void setCityChange(String str, String str2, String str3) {
        this.f18400sp.putString("cityChangeAll", str);
        this.f18400sp.putString("cityChangeList", str2);
        this.f18400sp.putString("cityChangeUriHead", str3);
    }

    public String getCityChangeAll() {
        return this.f18400sp.getString("cityChangeAll");
    }

    public String getCityChangeList() {
        return this.f18400sp.getString("cityChangeList");
    }

    public String getCityChangeUriHead() {
        return this.f18400sp.getString("cityChangeUriHead");
    }

    public String getWebresourcelog() {
        return this.f18400sp.getString("webresourcelog");
    }

    public void setWebresourcelog(String str) {
        this.f18400sp.putString("webresourcelog", str);
    }

    public void setUighur(String str) {
        this.f18400sp.putString("language_uighur", str);
    }

    public static String getUighur() {
        return App.getSharePreferenceUtil().getString("language_uighur");
    }

    public void setJumpActSwitch(String str) {
        this.f18400sp.putString(config_jump_def_act_switch, str);
    }

    public static String getJumpActSwitch() {
        return App.getSharePreferenceUtil().getString(config_jump_def_act_switch);
    }

    public void setJumpActCityCode(String str) {
        this.f18400sp.putString(config_jump_def_act_city_code, str);
    }

    public static String getJumpActCityCode() {
        String string = App.getSharePreferenceUtil().getString(config_jump_def_act_city_code);
        return TextUtils.isEmpty(string) ? "" : string;
    }

    public void setJumpActUrl(String str) {
        this.f18400sp.putString(config_jump_def_act_url, str);
    }

    public static String getJumpActUrl() {
        return !URLEnvironmentConfig.isForPublish() ? "" : App.getSharePreferenceUtil().getString(config_jump_def_act_url);
    }

    public void setJumpActVersion(String str) {
        this.f18400sp.putString(config_jump_def_act_version, str);
    }

    public static String getJumpActVersion() {
        return App.getSharePreferenceUtil().getString(config_jump_def_act_version);
    }

    public void setDongTaiQiDongYe(String str) {
        if (!TextUtils.equals("0", str)) {
            this.f18400sp.putBoolean("dongtaikaipinxiaoguo", false);
        }
        this.f18400sp.putString(config_on_or_off_splash, str);
    }

    public static String getDongTaiQiDongYe() {
        return App.getSharePreferenceUtil().getString(config_on_or_off_splash);
    }

    public void openShiLaoHua(String str) {
        App.getSharePreferenceUtil().putString(shiLaoHuaKaiGuan, str);
    }

    public static boolean isOpenShiLaoHua() {
        return "01".equals(App.getSharePreferenceUtil().getString(shiLaoHuaKaiGuan));
    }

    public void setBigKaiGuan(String str) {
        App.getSharePreferenceUtil().putString(shiLaoHuaDaZiKaiGuan, str);
    }

    public static boolean isOpenBigKaiGuan() {
        return "01".equals(App.getSharePreferenceUtil().getString(shiLaoHuaDaZiKaiGuan));
    }

    public void setZheDiePingUrl(String str) {
        App.getSharePreferenceUtil().putString(zheDiePingUrl, str);
    }

    public static String getZheDiePingUrl() {
        return App.getSharePreferenceUtil().getString(zheDiePingUrl);
    }

    public void setNewComerSwitch(String str) {
        App.getSharePreferenceUtil().putString(NEWCOMERSWITCH, str);
    }

    public static boolean isNewComerOpen() {
        return "01".equals(App.getSharePreferenceUtil().getString(NEWCOMERSWITCH));
    }

    public static void setYinSiDialogCode(String str, String str2) {
        String string = App.getSharePreferenceUtil().getString(unicom_yinsi_dialog_code);
        App.getSharePreferenceUtil().putString(unicom_yinsi_login_agree, str2);
        if (!TextUtils.equals(string, str)) {
            App.getSharePreferenceUtil().putString(unicom_yinsi_dialog_code, str);
        } else if (App.getSharePreferenceUtil().getBoolean("IsBasicCustom")) {
            App.getSharePreferenceUtil().putBoolean("IsBasicCustom", false);
        }
    }

    public static void setLoginYinsiVersion(String str) {
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        sharePreferenceUtil.putBoolean(App.getSharePreferenceUtil().getString(unicom_yinsi_dialog_code) + "_yinsi_" + str, true);
    }

    public boolean getLoginYinssiVersion(String str) {
        SharePreferenceUtil sharePreferenceUtil = this.f18400sp;
        return sharePreferenceUtil.getBoolean(this.f18400sp.getString(unicom_yinsi_dialog_code) + "_yinsi_" + str);
    }

    public String getHomeFanKui() {
        return this.f18400sp.getString("client_sfeedbackurl");
    }

    public void setHomeFanKui(String str) {
        this.f18400sp.putString("client_sfeedbackurl", str);
    }

    public long getCityChangeTime() {
        String string = this.f18400sp.getString("cityChangeTime");
        if (TextUtils.isEmpty(string)) {
            return Long.MAX_VALUE;
        }
        try {
            return Long.parseLong(string) * 86400 * 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return Long.MAX_VALUE;
        }
    }

    public void setCityChangeTime(String str) {
        this.f18400sp.putString("cityChangeTime", str);
    }

    public void setGongJiRiStartTime(String str) {
        this.f18400sp.putString(config_gongjiri_start_time, str);
    }

    public void setGongJiRiEndTime(String str) {
        App.getSharePreferenceUtil().putString(config_gongjiri_end_time, str);
    }

    public static void setIdcardKey(String str, String str2) {
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        sharePreferenceUtil.putString("firstlogin" + str, str2);
    }

    public static String getIdCardKey(String str) {
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        return sharePreferenceUtil.getString("firstlogin" + str);
    }

    public static String getIdcardKeyIsFirst(String str) {
        return TextUtils.isEmpty(getIdCardKey(str)) ? "0" : "1";
    }

    public void setHomeKeFuUrl(String str) {
        App.getSharePreferenceUtil().putString(config_unicom_home_kefu_url, str);
    }

    public static String getHomeKeFuUrl() {
        return App.getSharePreferenceUtil().getString(config_unicom_home_kefu_url);
    }

    public void setXiaoxikuaibaoXiaoxiUrl(String str) {
        App.getSharePreferenceUtil().putString("kuaibaoxiaoxironghe_xiaoxi", str);
        MsLogUtil.m7979d("XiaoxikuaibaoXiaoxiUrl", "设置消息---" + str);
    }

    public static String getXiaoxikuaibaoXiaoxiUrl() {
        String string = App.getSharePreferenceUtil().getString("kuaibaoxiaoxironghe_xiaoxi");
        if (TextUtils.isEmpty(string)) {
            string = URLSet.getXiaoxiUrl();
        }
        MsLogUtil.m7979d("XiaoxikuaibaoXiaoxiUrl", "返回留言---" + string);
        return string;
    }

    public void setXiaoxikuaibaoLiuyanUrl(String str) {
        App.getSharePreferenceUtil().putString("kuaibaoxiaoxironghe_liuyan", str);
        MsLogUtil.m7979d("XiaoxikuaibaoXiaoxiUrl", "设置留言---" + str);
    }

    public static String getXiaoxikuaibaoLiuyanUrl() {
        String string = App.getSharePreferenceUtil().getString("kuaibaoxiaoxironghe_liuyan");
        if (TextUtils.isEmpty(string)) {
            string = URLSet.getLiuyanUrl();
        }
        MsLogUtil.m7979d("XiaoxikuaibaoXiaoxiUrl", "返回消息---" + string);
        return string;
    }

    public void setConfigClearCacheSystem(String str) {
        App.getSharePreferenceUtil().putString(config_unicom_clear_cache_phone_system, str);
    }

    public void setConfigClearCacheTime(String str) {
        App.getSharePreferenceUtil().putString(config_unicom_clear_cache_time, str);
    }

    public void setWelcomeShowUpdateDialog(String str) {
        App.getSharePreferenceUtil().putString(config_unicom_welcome_show_update_dialog, str);
    }

    public String getWelcomeShowUpdateDialogFlag() {
        return App.getSharePreferenceUtil().getString(config_unicom_welcome_show_update_dialog);
    }

    public boolean getWechatData() {
        return App.getSharePreferenceUtil().getBoolean(WECHAT_GET_DATA);
    }

    public void setWechatData(boolean z) {
        App.getSharePreferenceUtil().putBoolean(WECHAT_GET_DATA, Boolean.valueOf(z));
    }

    public void setCollectionSwitch(String str) {
        App.getSharePreferenceUtil().putString(unicom_colleciont_switch, str);
    }

    public boolean getCollectionSwitch() {
        return App.hasLogined() && App.getSharePreferenceUtil().getString(unicom_colleciont_switch).contains(UserManager.getInstance().getCurrentProvinceCode());
    }

    public static void setH5CdnCacheTime(int i) {
        App.getSharePreferenceUtil().putInt("unicom_h5_cdn_cache_time", i);
    }

    public static int getH5CdnCacheTime() {
        return App.getSharePreferenceUtil().getInt("unicom_h5_cdn_cache_time", 10);
    }
}
