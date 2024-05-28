package com.sinovatech.unicom.basic.server;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ConfigParser {
    public static String parseConfigResponse(Context context, String str) {
        String str2;
        Log.d("peizhixiang", "parseConfigResponse: 加载配置项");
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("DTO"));
            String optString = jSONObject2.optString("loginClickTimeInterval");
            String optString2 = jSONObject2.optString("loginRetryCount");
            String optString3 = jSONObject2.optString("loginRetryTimeInterval");
            String optString4 = jSONObject2.optString("messagePushTimeInterval");
            String optString5 = jSONObject2.optString("registerURL");
            String optString6 = jSONObject2.optString("getMessageURL");
            String optString7 = jSONObject2.optString("popupDelayTime");
            String optString8 = jSONObject2.optString("logupload");
            String optString9 = jSONObject2.optString("isOpenHttps");
            String optString10 = jSONObject2.optString("isOpenClickCount");
            String optString11 = jSONObject2.optString("clientBusinessClickCountUrl");
            String optString12 = jSONObject2.optString("startMaxTime");
            str2 = "";
            try {
                String optString13 = jSONObject2.optString("adMaxTime");
                String optString14 = jSONObject2.optString("errorLogUploadAddress");
                String optString15 = jSONObject2.optString("configuringVoiceSearch");
                String optString16 = jSONObject2.optString("coinAccessAdress");
                String optString17 = jSONObject2.optString("wealthPageUrl");
                String optString18 = jSONObject2.optString("hmuOpenFlag");
                String optString19 = jSONObject2.optString("birthdayPlayTime");
                String optString20 = jSONObject2.optString("trustedCNS");
                String optString21 = jSONObject2.optString("myUnicomUrl");
                String optString22 = jSONObject2.optString("homePopUpUrl");
                String optString23 = jSONObject2.optString("sharePoliteUrl");
                String optString24 = jSONObject2.optString("isIfStartLink");
                String optString25 = jSONObject2.optString("homeNewsRemindShowTime");
                String optString26 = jSONObject2.optString("searchConfiguration");
                String optString27 = jSONObject2.optString("uploadImageA");
                String optString28 = jSONObject2.optString("uploadImageB");
                String optString29 = jSONObject2.optString("smsValidateCode");
                String optString30 = jSONObject2.optString("ipvCopyWriting");
                String optString31 = jSONObject2.optString("bindNumber");
                String optString32 = jSONObject2.optString("smsSwitch");
                String optString33 = jSONObject2.optString("whetherSpinner");
                String optString34 = jSONObject2.optString("bindAccountNewUserColor");
                String optString35 = jSONObject2.optString("bindAccountNewOldColor");
                String optString36 = jSONObject2.optString("relationAccountNewUserColor");
                String optString37 = jSONObject2.optString("relationAccountOldUserColor");
                String optString38 = jSONObject2.optString("relationClickColor");
                String optString39 = jSONObject2.optString("rightSlipHint");
                String optString40 = jSONObject2.optString("customHint");
                String optString41 = jSONObject2.optString("reloadHint");
                String optString42 = jSONObject2.optString("popupNotPopped");
                int optInt = jSONObject2.optInt("viewFlpertime");
                String optString43 = jSONObject2.optString("diffNetFlag");
                String optString44 = jSONObject2.optString("cardCarouselTime");
                String optString45 = jSONObject2.optString("menuName1st");
                String optString46 = jSONObject2.optString("menuName2nd");
                String optString47 = jSONObject2.optString("menuName3rd");
                String optString48 = jSONObject2.optString("menuName4th");
                String optString49 = jSONObject2.optString("menuUrl1st");
                String optString50 = jSONObject2.optString("menuUrl2nd");
                String optString51 = jSONObject2.optString("menuUrl3rd");
                String optString52 = jSONObject2.optString("menuUrl4th");
                String optString53 = jSONObject2.optString("userAgreement");
                String optString54 = jSONObject2.optString("privacyAgreement");
                String optString55 = jSONObject2.optString("qianminganquan");
                String optString56 = jSONObject2.optString("openEmptyCardCheck");
                String optString57 = jSONObject2.optString("extranetActivityPageUrl");
                String optString58 = jSONObject2.optString(ConfigManager.languageSettingSwitch);
                ConfigManager configManager = new ConfigManager(context);
                configManager.setLanguageSettingSwitch(optString58);
                configManager.setOpenOneTouchLoading(jSONObject2.optString("openOneTouchLoading"));
                configManager.setServiceLinkUrl(jSONObject2.optString("serviceLinkUrl"));
                configManager.setOneKeyQuHaoPrefix(jSONObject2.optString("interfaceLinkUrl"));
                configManager.setTecentUtilTime(jSONObject2.optString("adCloseTime"));
                configManager.setMainLiuSPID(jSONObject2.optString("mianLiuSPID"));
                configManager.setMianLiuPID(jSONObject2.optString("mianLiuPID"));
                configManager.setMianLiuPORTALID(jSONObject2.optString("mianLiuPORTALID"));
                configManager.setMianLiuSPKEY(jSONObject2.optString("mianLiuSPKEY"));
                configManager.setTencentAdvertFlag(jSONObject2.optString("tencentAdvertFlag"));
                configManager.openShiLaoHua(jSONObject2.optString("newChanger"));
                configManager.setBigKaiGuan(jSONObject2.optString("largeOff"));
                configManager.setYuiyinYanzheng(jSONObject2.optString("useVoiceValidateCode"));
                configManager.setAddressGuanliUrl(jSONObject2.optString("addressUrl"));
                configManager.setActivityDomain(jSONObject2.optString("activityDomain"));
                configManager.setCityUrl(jSONObject2.optString("nettypeUrl"));
                configManager.setCareProvinceSwitch(jSONObject2.optString("careProvinceSwitch"));
                configManager.setSweepFaceSafetyDesc(jSONObject2.optString("sweepFaceSafetyDesc"));
                configManager.setSweepFaceLogin(jSONObject2.optString("sweepFaceLogin"));
                configManager.setMianliuOpen(jSONObject2.optString("mianliuSwitch87"));
                configManager.setMianliuOpenH5(jSONObject2.optString("mianliuSwitch87H5"));
                configManager.setCityChange(jSONObject2.optString("cityChangeAll"), jSONObject2.optString("cityChangeList"), jSONObject2.optString("cityChangeUriHead"));
                configManager.setWebresourcelog(jSONObject2.optString("setWebresourcelog"));
                configManager.setMiddleButtonDesc(jSONObject2.optString("middleButtonDesc"));
                configManager.setMiddleButtonTitle(jSONObject2.optString("middleButtonTitle"));
                configManager.setFWZUState(jSONObject2.optString("serverOnlineState"));
                configManager.setUighur(jSONObject2.optString(LanguageUtil.CHN_WEIWUER));
                configManager.setBigFont(jSONObject2.optString("bigFont"));
                configManager.setZheDiePingUrl(jSONObject2.optString("linkSplit"));
                configManager.setNewComerSwitch(jSONObject2.optString(ConfigManager.NEWCOMERSWITCH));
                try {
                    String optString59 = jSONObject2.optString("checkLink");
                    if (TextUtils.isEmpty(optString59)) {
                        StatisticsUploadUtils.uploadRealTimeBeiDong(context, "zhiboLink", "", "", "", "直播拦截url", "配置为空");
                    } else {
                        StatisticsUploadUtils.uploadRealTimeBeiDong(context, "zhiboLink", "", "", "", "直播拦截url", optString59);
                    }
                    configManager.setZhiboLanjieUrl(jSONObject2.getString("checkLink"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                configManager.setHomeFanKui(jSONObject2.optString("client_sfeedbackurl"));
                configManager.setServiceSearchTime(jSONObject2.optInt("searchBoxHighlightTime"));
                configManager.setServiceSearchArea(jSONObject2.optString("turnOnHighlightPro"));
                int i = 8;
                try {
                    i = Integer.parseInt(optString29);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                configManager.setMSSNumer(i);
                configManager.setIpv6Desc(optString30);
                configManager.setLimitNumer(optString31);
                configManager.setAutoSms("1".equals(optString32));
                configManager.setAutoBindActivity("1".equals(optString33));
                configManager.setLoginInterval(optString);
                configManager.setLoginRetryNum(optString2);
                configManager.setLoginTimeout(optString3);
                configManager.setHomeKeFuUrl(jSONObject2.optString("unicom_home_kefu_url"));
                configManager.setMsgPushInterval(optString4);
                configManager.setSharePoliteUrl(optString23);
                configManager.setRegisterURL(optString5);
                configManager.setGetMessageURL(optString6);
                configManager.setLogfileUploadKey(optString8);
                configManager.setPopupDelayTimeKey(Integer.parseInt(optString7));
                configManager.setHttpsSwitchKey(optString9);
                configManager.setStasticsUploadKey(optString10);
                configManager.setStasticsUploadUrlKey(optString11);
                configManager.setAdMaxTimeKey(optString13);
                configManager.setStartMaxTimeKey(optString12);
                configManager.setLoginLogKey(optString14);
                configManager.setYuyinSearchKey(optString15);
                configManager.setMyUnicomeMyCaifuKey(optString16);
                configManager.setFindUrlKey(optString17);
                configManager.setHuahengStartKey(optString18);
                configManager.setConfig_IPLink_Key(optString24);
                configManager.setHomeNewsRemindShowTime(optString25);
                try {
                    configManager.setBirthday(Integer.parseInt(optString19));
                } catch (Exception e3) {
                    configManager.setBirthday(10);
                    e3.printStackTrace();
                }
                ConfigManager.setConfigCNS(optString20);
                configManager.setMyUnicomePrivilKey(optString21);
                ConfigManager.setConfig_HomePopUpUrl_Key(optString22);
                configManager.setSearchSwitch(optString26);
                configManager.setMianmidenglu(optString28);
                configManager.setShoushimima(optString27);
                configManager.setAutoLognBind1("1".equals(jSONObject2.optString("numberLand")));
                configManager.setAutoLognBind2("1".equals(jSONObject2.optString("broadbandLand")));
                configManager.setAutoLognBind3("1".equals(jSONObject2.optString("fixedLand")));
                configManager.setAutoLognBind4("1".equals(jSONObject2.optString("idLand")));
                if (TextUtils.isEmpty(optString34)) {
                    optString34 = "FF000000";
                }
                configManager.setNewUserColorForBindList(optString34);
                if (TextUtils.isEmpty(optString35)) {
                    optString35 = "FF000000";
                }
                configManager.setOldUserColorForBindList(optString35);
                if (TextUtils.isEmpty(optString36)) {
                    optString36 = "FF000000";
                }
                configManager.setNewUserColorForLoginRelationList(optString36);
                if (TextUtils.isEmpty(optString37)) {
                    optString37 = "FF000000";
                }
                configManager.setOldUserColorForLoginRelationList(optString37);
                if (TextUtils.isEmpty(optString38)) {
                    optString38 = "FF000000";
                }
                configManager.setClickUserColorForLoginRelationList(optString38);
                configManager.setYuleUnloginTip(optString39);
                configManager.setCustomHint(optString40);
                configManager.setReloadTip(optString41);
                configManager.setPopupNotPopped(optString42);
                configManager.setViewflipertimeh(optInt);
                configManager.setMenuName1st(optString45);
                configManager.setMenuName2nd(optString46);
                configManager.setMenuName3rd(optString47);
                configManager.setMenuName4th(optString48);
                configManager.setMenuUrl1s(optString49);
                configManager.setMenuUrl2nd(optString50);
                configManager.setMenuUrl3rd(optString51);
                configManager.setMenuUrl4th(optString52);
                configManager.setDiffNetFlag(optString43);
                configManager.setQiangzhiTip(optString55);
                if (TextUtils.isEmpty(optString54)) {
                    optString54 = "privacyAgreement";
                }
                configManager.setPrivacyAgreement(optString54);
                if (TextUtils.isEmpty(optString53)) {
                    optString53 = "userAgreement";
                }
                configManager.setUserAgreement(optString53);
                configManager.setOpenEmptyCardCheck(optString56);
                configManager.setExtranetActivityPageUrl(optString57);
                configManager.setHomeIcon(jSONObject2.optString("homeNewIcon"));
                configManager.setServiceIcon(jSONObject2.optString("serviceNewIcon"));
                configManager.setMarketIcon(jSONObject2.optString("marketNewIcon"));
                configManager.setFindIcon(jSONObject2.optString("wealthIcon"));
                configManager.setMyIcon(jSONObject2.optString("myNewIcon"));
                configManager.setActivityIcon(jSONObject2.optString("activityIcon"));
                configManager.setHomeHide(jSONObject2.optString("homeHide"));
                configManager.setServiceHide(jSONObject2.optString("serviceHide"));
                configManager.setActivityHide(jSONObject2.optString("activityHide"));
                configManager.setMarketHide(jSONObject2.optString("marketHide"));
                configManager.setCaiFuHide(jSONObject2.optString("caiFuHide"));
                configManager.setMyHide(jSONObject2.optString("myHide"));
                configManager.setLiulanyouli(jSONObject2.optString("advertisingSpaceShow"));
                configManager.setWhiteList(jSONObject2.optString("appWhitelistConfig"));
                configManager.setYiWangState(jSONObject2.optString("showMemberTel"));
                configManager.setXieyiTimetemp(jSONObject2.optString("privacyAgreementVersion"));
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.putOpt("turnOnAurora", jSONObject2.optString("turnOnAurora"));
                jSONObject3.putOpt("backstageWakeDesc", jSONObject2.optString("backstageWakeDesc"));
                jSONObject3.putOpt("backstageWakeTitle", jSONObject2.optString("backstageWakeTitle"));
                configManager.setJGbaohuo(!(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3));
                configManager.setJumpActSwitch(jSONObject2.optString("cityOpenorClose"));
                configManager.setJumpActCityCode(jSONObject2.optString("clientCityList"));
                configManager.setJumpActUrl(jSONObject2.optString("cityjumpUrl"));
                configManager.setJumpActVersion(jSONObject2.optString("checkType"));
                configManager.setCacheVideoMax(jSONObject2.optInt("BUVideoPreMaxCount"));
                configManager.setCacheVideoTime(jSONObject2.optInt("BUVideoPreTimeOut"));
                configManager.setGongJiRiStartTime(jSONObject2.optString("startTimeofMd"));
                configManager.setGongJiRiEndTime(jSONObject2.optString("endTimeofMd"));
                configManager.setConfigClearCacheSystem(jSONObject2.optString("unicom_phone_system"));
                configManager.setConfigClearCacheTime(jSONObject2.optString("unicom_clear_cache_time"));
                configManager.setWelcomeShowUpdateDialog(jSONObject2.optString("adr_show_up_dialog"));
                try {
                    configManager.setCardSwitchTime(optString44);
                    configManager.setCityChangeTime(jSONObject2.optString("clientCityCron"));
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                configManager.setXiaoxikuaibaoXiaoxiUrl(jSONObject2.optString("kuaibaoxiaoxironghe_xiaoxi"));
                configManager.setXiaoxikuaibaoLiuyanUrl(jSONObject2.optString("kuaibaoxiaoxironghe_liuyan"));
                configManager.setCollectionSwitch(jSONObject.optString("my_Collection_Switch"));
                return jSONObject.optString("versionSTR");
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                return str2;
            }
        } catch (Exception e6) {
            e = e6;
            str2 = "";
        }
    }
}
