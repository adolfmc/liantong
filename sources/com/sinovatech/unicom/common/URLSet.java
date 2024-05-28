package com.sinovatech.unicom.common;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.HashMap;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class URLSet {
    public static final String myPocketUrl = "https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://epay.10010.com/ci-mps-st-web/";
    public static final String saveMoneyUrl = "https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://epay.10010.com/itf/fixcs/hfb/home?source=app_sjyyt&c=SWDZCQ01";
    private static String ApplicationDomain_URL = URLEnvironmentConfig.getAppDomainNoProtocol() + "/";
    public static String ApplicationServer_URL = URLEnvironmentConfig.getAppServerURLNoProtocol() + "/";
    public static String huanjingType = URLEnvironmentConfig.getDevelopmentEnvironmentSubType();
    public static boolean Debug_mode = URLEnvironmentConfig.isForPublish() ^ true;
    public static boolean PrePublic_mode = URLEnvironmentConfig.isPrepubEnvironment();
    public static String productionLoginIPURL = "https://123.125.99.4:80/mobileService/login.htm";
    static String PreMoxHost = "https://m.client.10010.com/";
    private static String shareNum = "0";
    public static String shareDefaultIcon = "https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20210325/png/20210325145817.png";
    public static String ywLinkUrl = "https://m.10010.com/queen/tencent/king-tab.html?channel=06-0324-1767-9999";
    public static String woDeLiangHaoMoreUrl = "https://u.10010.cn/qA9q2";
    public static String tuijianyouli = "https://m.client.10010.com/newuser_century/static/login/userLogin";

    public static String commonLog() {
        return "https://m.client.10010.com/tyggInterface/commonLog";
    }

    public static String getAnquanzhognxinSMS() {
        return "https://m.client.10010.com/epidemic/sendMsg";
    }

    public static String getAudienceQiandaoUrl() {
        return "https://m.client.10010.com/mobileService/pointsGet/points.htm";
    }

    public static String getGameYuyueLogURL() {
        return "https://m.client.10010.com/tyggInterface/commonLog";
    }

    public static String getHaWenUrl() {
        return "https://img.client.10010.com/hawen/index.html";
    }

    public static String getInterceptLive() {
        return "/zhibo/index.html";
    }

    public static String getLiceneUrl() {
        return "https://m.client.10010.com/mobileService/qualification/toQualificationPage.htm";
    }

    public static String getLogfile_upload_url() {
        return "https://m1.client.10010.com/msupport/nl/upload";
    }

    public static String getLoginFaceUrl() {
        return "https://img.client.10010.com/stprototype/facelogin/faceservice.html";
    }

    public static String getMengWenUrl() {
        return "https://img.client.10010.com/mengwen/index.html";
    }

    public static String getNewFaxian() {
        return "https://img.client.10010.com/caifuhome/index.html";
    }

    public static String getNickName() {
        return "https://m.client.10010.com/mobileService/customerService/getHeadInfo.do";
    }

    public static String getNoticLiuyan() {
        return "https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://bbdigital.10010.com/udbh/jumpWiselist";
    }

    public static String getOneKeyLoginYinSiUrl() {
        return "https://img.client.10010.com/stprototype/clientonekeylogin/onekeyloginyinsi.html";
    }

    public static String getOneKeyQuHaoPrefix() {
        return "https://nisportal.10010.com:9001/api?appid=21474836480";
    }

    public static String getPinglunCommentList() {
        return "https://m.client.10010.com/commentSystem/getCommentList";
    }

    public static String getPinglunDeleteCommentOrDelete() {
        return "https://m.client.10010.com/commentSystem/delDynamic";
    }

    public static String getPinglunReplayList() {
        return "https://m.client.10010.com/commentSystem/getReplyList";
    }

    public static String getPinglunSaveReplay() {
        return "https://m.client.10010.com/commentSystem/saveCommentReply";
    }

    public static String getPinglunUploadImage() {
        return "https://m.client.10010.com/commentSystem/uploadImages";
    }

    public static String getPingluncsPrice() {
        return "https://m.client.10010.com/commentSystem/csPraise";
    }

    public static String getSetting_homedata() {
        return "https://img.client.10010.com/stprototype/xieyiyutiaokuan/index.html";
    }

    public static String getWXAccessToken() {
        return "https://api.weixin.qq.com/sns/oauth2/access_token";
    }

    public static String getWXUserinfo() {
        return "https://api.weixin.qq.com/sns/userinfo";
    }

    public static String getWaterMarkTypeFace() {
        return "https://img.client.10010.com/mobileService/watermark/HYQiHei-55S.otf";
    }

    public static String getYingWenUrl() {
        return "https://img.client.10010.com/duoyuyanenglish/index.html";
    }

    public static String getZangWenUrl() {
        return "https://img.client.10010.com/zangwen/index.html";
    }

    public static String getZhiboLiebiaoUrl() {
        return "https://img.client.10010.com/zhibo/index.html#/livelist";
    }

    public static String getHttpPrefix() {
        return URLEnvironmentConfig.getHttpPrefix();
    }

    public static String getHttpServer() {
        return URLEnvironmentConfig.getHttpPrefix() + ApplicationServer_URL;
    }

    public static String getHttpDomain() {
        return URLEnvironmentConfig.getHttpPrefix() + ApplicationDomain_URL;
    }

    public static String getHttpsServer() {
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return getHttpServer();
        }
        return "https://" + ApplicationServer_URL;
    }

    public static String getNewNavigation() {
        boolean isYiwang = UserManager.getInstance().isYiwang();
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            if (isYiwang) {
                return getHttpDomain() + "diffNav";
            }
            return getHttpServer() + "navigation";
        } else if (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isPreMoxEnvironment() || URLEnvironmentConfig.isPrepubEnvironment2()) {
            return isYiwang ? "https://client.10010.com/diffNav" : "https://client.10010.com/mobileService/navigation";
        } else if (isYiwang) {
            return getHttpDomain() + "diffNav";
        } else {
            return getHttpDomain() + "mobileService/navigation";
        }
    }

    public static String getAdvertiseURL_57() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "uniAdmsInterface/getHomePageAd";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "preUniAdmsInterface/getHomePageAd";
        } else if (URLEnvironmentConfig.isPreMoxEnvironment()) {
            return "https://m.client.10010.com/uniAdmsInterface/getHomePageAd";
        } else {
            return getHttpDomain() + "uniAdmsInterface/getHomePageAd";
        }
    }

    public static String getDataFromService() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "getDataFromService";
        } else if (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/getDataFromService";
        } else {
            return getHttpDomain() + "getDataFromService";
        }
    }

    public static String getWebsocketUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "ws://pushservice.10010.com/pushweb/push/wsServer" : URLEnvironmentConfig.isPrepubEnvironment() ? "ws://113.200.19.8:80/pushweb/push/wsServer" : "ws://113.200.19.8:80/pushweb/push/wsServer";
    }

    public static String getFiveGInformationUrl() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://" + ApplicationDomain_URL + "businessHallJz/getbusinessHallDetail";
        }
        return "https://client.10010.com/businessHallJz/getbusinessHallDetail";
    }

    public static String toClientConfig() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append("mobileserviceNine");
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "_pre" : "");
        sb.append("/v1/config/toClientConfig");
        return sb.toString();
    }

    public static String getYiwangUrl() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://img.client.10010.com/adnew/other_net_activity/";
        }
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://img.client.10010.com/adnew/pre_other_net_activity/";
        }
        return getHttpDomain() + "other_net_activity/";
    }

    public static String getTucaoURL() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://img.client.10010.com/tucao/entrance.html";
        }
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://img.client.10010.com/web_prepub/tucao/entrance.html";
        }
        return getHttpServer() + "clientComment/index.htm";
    }

    public static String getSearchapi() {
        return getHttpPrefix() + "info.10010.com/chinaunicomSearchJB/search/api.do";
    }

    public static String getShopURL() {
        return (URLEnvironmentConfig.isProductionEnvironment() || URLEnvironmentConfig.isPrepubEnvironment()) ? "https://m.10010.com" : "https://demo.mall.10010.com:8108/homepage/11";
    }

    public static String getShopRedirectTo() {
        return getHttpServer() + "storemanager/stroeReIn.htm";
    }

    public static String getBindPhoneNumberURL() {
        return getHttpServer() + "accountmanager/bindList.htm";
    }

    public static String getRefreshBindNumber() {
        return getHttpServer() + "accountmanager/getbindmation.htm";
    }

    public static String getChangeNumber() {
        return getHttpServer() + "accountmanager/switch.htm";
    }

    public static String getLoginURL() {
        return getHttpsServer() + "login.htm";
    }

    public static String getRandomLoginURL() {
        return getHttpsServer() + "radomLogin.htm";
    }

    public static String getRandomPswdURL() {
        return getHttpsServer() + "sendRadomNum.htm";
    }

    public static String getLogoutURL() {
        return getHttpsServer() + "logout.htm";
    }

    public static String getRegisterURL() {
        return getHttpServer() + "view/client/account/registration.jsp";
    }

    public static String getForgetpassword() {
        if (URLEnvironmentConfig.isDevelopmentEnvironment() || URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileService/businessTransact/resetPwd/view.htm";
        }
        return getHttpServer() + "businessTransact/resetPwd/view.htm";
    }

    public static String getBroadbandForgetAccount() {
        return getHttpServer() + "broad/initQueryBroadInfo.htm?menuId=000200060018";
    }

    public static String getCookiedomain() {
        return getHttpDomain();
    }

    public static String getRegistpushservice() {
        return getHttpPrefix() + "pushservice.10010.com/pushweb/push";
    }

    public static String getSendpushservice() {
        return getHttpPrefix() + "pushservice.10010.com/pushweb/push";
    }

    public static String getAbout() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/appjianban/index.html#/aboutus" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/appjianban/index.html#/aboutus" : "";
    }

    public static String getStateion_Search() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpServer() + "customerServiceyfb/queryEhallMapInformactionByKey.htm";
        }
        return getHttpServer() + "customerService/queryEhallMapInformactionByKey.htm";
    }

    public static String getStation_search_recomend() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpServer() + "customerServiceyfb/getSearchSign.htm";
        }
        return getHttpServer() + "customerService/getSearchSign.htm";
    }

    public static String getStateion_Collect() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpServer() + "customerServiceyfb/getEhallCollectionlList.htm";
        }
        return getHttpServer() + "customerService/getEhallCollectionlList.htm";
    }

    public static String getYingyetingHuodongInfo() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpServer() + "customerServiceyfb/getNearbyPushMessage.htm";
        }
        return getHttpServer() + "customerService/getNearbyPushMessage.htm";
    }

    public static String getEhallTitleAndLabel() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpServer() + "customerServiceyfb/getEhallTitleAndLabel.do";
        }
        return getHttpServer() + "customerService/getEhallTitleAndLabel.do";
    }

    public static String getEhallList() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpServer() + "customerServiceyfb/getEhallList.do";
        }
        return getHttpServer() + "customerService/getEhallList.do";
    }

    public static String getStaionDetailUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpServer() + "customerServiceyfb/getDetails.htm";
        }
        return getHttpServer() + "customerService/getDetails.htm";
    }

    public static String getCouponsList() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return URLEnvironmentConfig.getHttpPrefix() + "client.10010.com/HallBusiness/Ticket/toGetTicketList.htm";
        }
        return getHttpDomain() + "HallBusiness/Ticket/toGetTicketList.htm";
    }

    public static String getFavPath() {
        return getHttpServer() + "customerService/addMicroblogComment.htm";
    }

    public static String getHomeUserInfo() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://m.client.10010.com/mobileserviceimportant_pro/home/queryUserInfoSeven";
        }
        return getHttpDomain() + "mobileserviceimportant/home/queryUserInfoSeven";
    }

    public static String getHomeUserInfoType3() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileService/home/querySevenUserInfoAdsl.htm";
        }
        return getHttpServer() + "home/querySevenUserInfoAdsl.htm";
    }

    public static String getPush_statistic() {
        return getHttpServer() + "push/feedBack.htm";
    }

    public static String getConfig_url() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileService/customer/getclientconfig.htm";
        }
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/mobileService/customer/getclientconfig.htm";
        }
        return "https://" + ApplicationServer_URL + "customer/getclientconfig.htm";
    }

    public static String getService_recommend_url() {
        return getHttpServer() + "query/querySmartClient.htm";
    }

    public static String getService_home_toast_url() {
        return getHttpServer() + "query/querySmartBizHome.htm";
    }

    public static String getMenuclick_tongji_url() {
        return getHttpPrefix() + "smartad.10010.com/msupport/count/businessLogRecords.htm";
    }

    public static String getMyunicom_privil_url() {
        return getHttpServer() + "customer/query/getMyUnicomPrivil.htm";
    }

    public static String getMyunicom_info_url2() {
        return getHttpServer() + "thirdRedirect.htm?redirect_uri=https://act.10010.com/SigninApp/signin/getDataCount.htm";
    }

    public static String getKefuzhongxinUrl() {
        String homeKeFuUrl = ConfigManager.getHomeKeFuUrl();
        return TextUtils.isEmpty(homeKeFuUrl) ? "https://u.10010.cn/qAu11" : homeKeFuUrl;
    }

    public static String getThirdshare_url() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileserviceimportant_pro/customer/getShareRedisInfo.htm";
        }
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return getHttpDomain() + "mobileService/customer/getShareRedisInfo.htm";
        }
        return getHttpServer() + "customer/getShareRedisInfo.htm";
    }

    public static String getMyUnicomDateTotle() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpServer() + "customer/query/getMyUnicomDateTotle.htm";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileService/customer/query/getMyUnicomDateTotle.htm";
        } else {
            return getHttpServer() + "customer/query/getMyUnicomDateTotle.htm";
        }
    }

    public static String getnoLoginAgreement() {
        return getHttpServer() + "noLoginAgreement.jsp";
    }

    public static String getZCommandInfo() {
        return getHttpServer() + "customer/getZCommandInfo.htm";
    }

    public static String getUserPrivacy() {
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://client.10010.com/mobileService/userPrivacy.jsp?version=" + App.getInstance().getString(2131886969);
        }
        return getHttpServer() + "userPrivacy.jsp?version=" + App.getInstance().getString(2131886969);
    }

    public static String getUserserver() {
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://client.10010.com/mobileService/serviceAgreement.jsp?version=" + App.getInstance().getString(2131886969);
        }
        return getHttpServer() + "serviceAgreement.jsp?version=" + App.getInstance().getString(2131886969);
    }

    public static final String getunicom_yinsizhengce() {
        return "https://img.client.10010.com/stprototype/stAPPxy/index.html?version=" + App.getInstance().getString(2131886969);
    }

    public static String getBroadBandInfo() {
        return getHttpServer() + "login/getBroadBandInfo.htm";
    }

    public static String getOnline() {
        return getHttpsServer() + "onLine.htm";
    }

    public static String getLoginCertInfo() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/login-web/v1/online/getLoginCertInfo" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/login/v1/online/getLoginCertInfo" : "https://client.10010.com/login/v1/online/getLoginCertInfo";
    }

    public static String getDynamicData() {
        return getHttpServer() + "customer/getDynamicData.htm";
    }

    public static String getAccountListData() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileService/customer/accountListData.htm";
        }
        return getHttpServer() + "customer/accountListData.htm";
    }

    public static String getDelBindInfoUrl() {
        return getHttpServer() + "delBindInfo.htm";
    }

    public static String getCityLatlongUrl() {
        return getHttpServer() + "customerService/getDefaultAddress.htm";
    }

    public static String getYanzhemgma() {
        return getHttpServer() + "auth/ImageVerification";
    }

    public static String getOneKeyLoginQuHao() {
        return getHttpServer() + "dFreeLogin/getDFreeLoginDetail.htm";
    }

    public static String getOneKeyLogin() {
        return getHttpsServer() + "login_net_mobile.htm";
    }

    public static String getWeixinCheckBind() {
        return getHttpsServer() + "weixin_login.htm";
    }

    public static String getWeixinBindLogin() {
        return getHttpsServer() + "bind_login.htm";
    }

    public static String toBurialSiteLog() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://smartad.10010.com/msupport/count/businessLogPoint";
        }
        return getHttpDomain() + "bslog/BurialSiteLog";
    }

    public static String getDishizhuanqu() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://cityzone.10010.com/cszquserapp/?navigation=0" : (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isPreMoxEnvironment()) ? "https://img.client.10010.com/web_prepub/dishizhuanqu/index.html" : "https://ecstest2018.10010.com/dishizhuanqu/index.html";
    }

    public static String getOpenBrowseData() {
        return getHttpServer() + "browseGetIntegra/OpenBrowseData.htm";
    }

    public static String getHeadLineData() {
        return getHttpServer() + "browseGetIntegra/headlinesData.htm";
    }

    public static String liulanshuoming() {
        return getHttpServer() + "browseGetIntegra/getWarmTip.htm";
    }

    public static String getToutiaoTask() {
        return getHttpDomain() + "taskcallback/taskfilter/query";
    }

    public static String sendCompleteTask() {
        return getHttpDomain() + "taskcallback/taskfilter/dotasks";
    }

    public static String getNewUserQuanyiUrl() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "growthfunction/queryHeadUserCard9";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "memberfunction/queryHeadUserCard9";
        } else {
            return getHttpDomain() + "memberfunction/queryHeadUserCard9";
        }
    }

    public static String getCanyuApi() {
        return getHttpServer() + "query/querySmartBizNew.htm";
    }

    public static String getMyNearEhallList() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileService/customerService/getMyNearEhallList.do";
        }
        return getHttpServer() + "customerService/getMyNearEhallList.do";
    }

    public static String getGameApp() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "proMyGameFloor";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "pre_finderInterface/myGameFloor";
        } else {
            return getHttpDomain() + "finderInterface/myGameFloor";
        }
    }

    public static String getFuchuangUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileservicetransact/popWindow/task";
        }
        return getHttpDomain() + "mobileservicetransact/popWindow/task";
    }

    public static String getAudienceList() {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/liveInfo/livingList/CHOICE/1/N?uuid=1");
        return sb.toString();
    }

    public static String getAudienceZhuboInfo(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/videoInfo/checkType/");
        sb.append(str);
        sb.append("/");
        sb.append(getShareUserNumSc());
        sb.append("?uuid=");
        sb.append(new Random().nextInt(10000));
        return sb.toString();
    }

    public static String Audience_SOCKET_URL() {
        return SOCKET_URL();
    }

    public static String Audience_SOCKET_PATH() {
        return SOCKET_PATH();
    }

    public static String getAudienceZhuangXiu(String str) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://m.client.10010.com/small_video/live/decorationData/" + str + "?uuid=" + new Random().nextInt(10000);
        } else if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/small_video/live/decorationData/" + str + "?uuid=" + new Random().nextInt(10000);
        } else {
            return "https://client.10010.com/pre_small_video/live/decorationData/" + str + "?uuid=" + new Random().nextInt(10000);
        }
    }

    public static String getAudienceShop(String str) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://m.client.10010.com/small_video_commodity/commodity/listAnchorsGoodsFirstOne/" + str + "/" + getShareUserNumSc() + "?uuid=" + new Random().nextInt(10000);
        } else if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/small_video_commodity/commodity/listAnchorsGoodsFirstOne/" + str + "/" + getShareUserNumSc() + "?uuid=" + new Random().nextInt(10000);
        } else {
            return "https://client.10010.com/small_video_commodity_pre/commodity/listAnchorsGoodsFirstOne/" + str + "/" + getShareUserNumSc() + "?uuid=" + new Random().nextInt(10000);
        }
    }

    public static String getAudienceShopList(String str) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://m.client.10010.com/small_video_commodity/commodity/listAnchorsGoods/1/" + str + "/" + getShareUserNumSc() + "?uuid=" + new Random().nextInt(10000);
        } else if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/small_video_commodity/commodity/listAnchorsGoods/1/" + str + "/" + getShareUserNumSc() + "?uuid=" + new Random().nextInt(10000);
        } else {
            return "https://client.10010.com/small_video_commodity_pre/commodity/listAnchorsGoods/1/" + str + "/" + getShareUserNumSc() + "?uuid=" + new Random().nextInt(10000);
        }
    }

    public static String explainGoods(String str, String str2) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://m.client.10010.com/small_video_commodity/commodity/explainGoods/" + str + "/" + str2;
        } else if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/small_video_commodity/commodity/explainGoods/" + str + "/" + str2;
        } else {
            return "https://client.10010.com/small_video_commodity_pre/commodity/explainGoods/" + str + "/" + str2;
        }
    }

    public static String getHomeFJYingYeTing() {
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return getHttpDomain() + "nearhall/customerService/getHomePageEhall.do";
        }
        return getHttpServer() + "customerService/getHomePageEhall.do";
    }

    public static String getGiftList() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video_gift/gift/getGiftList?uuid=");
        sb.append(new Random().nextInt(10000));
        String sb2 = sb.toString();
        return URLEnvironmentConfig.isPrepubEnvironment() ? sb2.replace("m.client.10010.com", "client.10010.com") : sb2;
    }

    public static String getGiftListNew() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video_gift/gift/getGiftListNew?uuid=");
        sb.append(new Random().nextInt(10000));
        String sb2 = sb.toString();
        return URLEnvironmentConfig.isPrepubEnvironment() ? sb2.replace("m.client.10010.com", "client.10010.com") : sb2;
    }

    public static String getGifNum() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video_gift/gift/getUserGift?uuid=");
        sb.append(new Random().nextInt(10000));
        String sb2 = sb.toString();
        return URLEnvironmentConfig.isPrepubEnvironment() ? sb2.replace("m.client.10010.com", "client.10010.com") : sb2;
    }

    public static String sendAudienceGift(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video_gift/gift/giveGift/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append("/");
        sb.append(str3);
        sb.append("/");
        sb.append(str4);
        sb.append("/");
        sb.append(getShareUserNumSc());
        sb.append("?taskType=");
        sb.append(AudienceActivity.isTask ? "1" : "0");
        String sb2 = sb.toString();
        return URLEnvironmentConfig.isPrepubEnvironment() ? sb2.replace("m.client.10010.com", "client.10010.com") : sb2;
    }

    public static String getUserScore() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/scoreInfo/getUserScoreByPhone?uuid=");
        sb.append(new Random().nextInt(10000));
        return sb.toString();
    }

    public static String addGaunzhu(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/live/followType/");
        sb.append(str);
        sb.append("/Y?taskType=");
        sb.append(AudienceActivity.isTask ? "1" : "0");
        return sb.toString();
    }

    public static String isGuanzhuUrl(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/videoInfo/closeStatus/");
        sb.append(str);
        sb.append("?uuid=");
        sb.append(new Random().nextInt(10000));
        return sb.toString();
    }

    public static String addRenqi(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/live/addLiveViewNum/");
        sb.append(str);
        sb.append("?uuid=");
        sb.append(new Random().nextInt(10000));
        return sb.toString();
    }

    public static String addpraiseUser(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/live/praiseUser/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append("?uuid=");
        sb.append(new Random().nextInt(10000));
        return sb.toString();
    }

    public static String addpraiseUser(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/videoInfo/praise/");
        sb.append(str);
        return sb.toString();
    }

    public static String goodslog(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/live/clickLiveGoods/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append("/");
        sb.append(str3);
        sb.append("/");
        sb.append(getShareUserNumSc());
        sb.append("?uuid=");
        sb.append(new Random().nextInt(10000));
        return sb.toString();
    }

    public static String getActivityTime() {
        return getHttpDomain() + "LiveBroadcastLottery/getAnchorActivity";
    }

    public static String getLuckyDrawResult() {
        return getHttpDomain() + "LiveBroadcastLottery/audienceLottery";
    }

    public static String ShareInfoLog() {
        if (!URLEnvironmentConfig.isProductionEnvironment()) {
            return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileservicequery_test/customerService/share/shareInfoToActivity.htm" : "https://client.10010.com/mobileservicequery/customerService/share/shareInfoToActivity.htm";
        }
        return getHttpDomain() + "mobileservicequery/customerService/share/shareInfoToActivity.htm";
    }

    public static String createwWoKouLing() {
        if (!URLEnvironmentConfig.isProductionEnvironment()) {
            return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileservicequery_test/customerService/share/creatWoCommmand.htm" : "https://client.10010.com/mobileservicequery/customerService/share/creatWoCommmand.htm";
        }
        return getHttpDomain() + "mobileservicequery/customerService/share/creatWoCommmand.htm";
    }

    public static String getWoKouLing() {
        if (!URLEnvironmentConfig.isProductionEnvironment()) {
            return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileservicequery_test/customerService/share/queryByWoCommmand.htm" : "https://client.10010.com/mobileservicequery/customerService/share/queryByWoCommmand.htm";
        }
        return getHttpDomain() + "mobileservicequery/customerService/share/queryByWoCommmand.htm";
    }

    public static String createShortLinkUrl() {
        if (!URLEnvironmentConfig.isProductionEnvironment()) {
            return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileservicequery_test/longUrlToShort/getShortUrl.htm" : "https://client.10010.com/mobileservicequery/longUrlToShort/getShortUrl.htm";
        }
        return getHttpDomain() + "mobileservicequery/longUrlToShort/getShortUrl.htm";
    }

    public static String getDefaultShare() {
        if (!URLEnvironmentConfig.isProductionEnvironment()) {
            return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileservicequery_test/customerService/share/defaultShare.htm" : "https://client.10010.com/mobileserviceimportant_pro/customer/getShareRedisInfo.htm";
        }
        return getHttpDomain() + "mobileservicequery/customerService/share/defaultShare.htm";
    }

    public static String getZhiboShareUrl(String str) {
        String str2 = URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/zhibo/index.html" : "https://img.client.10010.com/zhibo/index.html";
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            str2 = "https://ecstest2018.10010.com/zhibo/index.html";
        }
        return str2 + "#/liveplayer?userId=" + str + "&from=clientshare&shareUserNumSc=" + App.getSharePreferenceUtil().getString(PreferenceConstUtils.shareUserNumSc());
    }

    public static String getLiveSearch() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/zhibosousuo/index.html#/?webViewNavIsHidden=true" : URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/zhibosousuo/index.html#/?webViewNavIsHidden=true" : "https://img.client.10010.com/web_prepub/zhibosousuo/index.html#/?webViewNavIsHidden=true";
    }

    public static String getZhiboRizhiUrl(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/live/shareRecord/");
        sb.append(str);
        sb.append("?taskType=");
        sb.append(AudienceActivity.isTask ? "1" : "0");
        return sb.toString();
    }

    private static String getShareUserNumSc() {
        return shareNum;
    }

    public static void setShareUserNumSc(String str) {
        shareNum = str;
    }

    public static String getZhiboHuifangUrl(String str) {
        String str2 = URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/zhibo/index.html" : "https://img.client.10010.com/zhibo/index.html";
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            str2 = "https://ecstest2018.10010.com/zhibo/index.html";
        }
        return str2 + "#/huifanglist?userId=" + str + "&from=fromList";
    }

    public static String listAngleMoreLive(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/live/listAngleMoreLive/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String listAngleMorePlayback(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/live/listAngleMorePlayback/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String angleMoreVideoInfo(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/videoInfo/getVideoInfo/");
        sb.append(str);
        return sb.toString();
    }

    public static String getPrimaryListSweet(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/live/primaryListSweet/");
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        return sb.toString();
    }

    public static String getAttentionAnchors(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/liveInfo/livingListClosedNew1/");
        sb.append(str);
        return sb.toString();
    }

    public static String getLiveRoomUIHide(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/live/room/ui/hide/");
        sb.append(str);
        return sb.toString();
    }

    public static String getLivePvInfo(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/liveInfo/noticeByJobNumber/");
        sb.append(str);
        return sb.toString();
    }

    public static String consumeFreeTime(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/live/minusFreeTime/");
        sb.append(str);
        return sb.toString();
    }

    public static String getSharpnessInfo(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/live/sharpness/");
        sb.append(str);
        return sb.toString();
    }

    public static String getCheckPassword(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/checkForcePassword/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String verifyPwd(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/playback/verify/pwd/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String reserveLivePv(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/live/pv/reserve/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String getUsefulChatList(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/chat/listChatDefaultMsg/");
        sb.append(str);
        return sb.toString();
    }

    public static String getInteractionAnchorsInfo(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/interaction/getInteractionAnchorsInfo/");
        sb.append(str);
        return sb.toString();
    }

    public static String getLanjieZhiboUrl() {
        String string = App.getSharePreferenceUtil().getString("zhibo_lanjie_url");
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            string = "https://ecstest2018.10010.com/zhibo/index.html#/liveplayer";
        }
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            string = "https://img.client.10010.com/web_prepub/zhibo/index.html#/liveplayer";
        }
        if (TextUtils.isEmpty(string)) {
            string = "N";
        }
        MsLogUtil.m7979d("拦截直播", string);
        return string;
    }

    public static String getAudienceKadunUrl(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/pre_" : getHttpDomain());
        sb.append("small_video/activityLogLiving/livingPlay/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append("/");
        sb.append(str3);
        sb.append("?streamUrl=");
        sb.append(str4);
        return sb.toString();
    }

    public static String getSearchH5Url() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/search2020/index.html" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/search2020/index.html" : "https://client.10010.com/search2020/index.html";
    }

    public static String getHomeFuwuH5Url() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/homefuwu2020/index.html" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/homefuwu2020/index.html" : "https://img.client.10010.com/web_prepub/homefuwu2020/index.html";
    }

    public static String getKuaibaoAndMessageUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/kuaibaoxiaoxironghe/index.html" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/kuaibaoxiaoxironghe/index.html" : "https://img.client.10010.com/web_prepub/kuaibaoxiaoxironghe/index.html";
    }

    public static String getMemberLoginUrl() {
        return getHttpsServer() + "login_vcode.htm";
    }

    public static String getMemberListUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/login_vcode_member.htm" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileService/login_vcode_member.htm" : "https://ecstest2018.10010.com/mobileServiceClient-test/login_vcode_member.htm";
    }

    public static String getTuiJianData() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceimportant/customer/querySmartServicekhd.htm" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/mobileserviceimportant_pro/customer/querySmartServicekhd.htm" : "https://client.10010.com/mobileserviceimportant_test/customer/querySmartServicekhd.htm";
    }

    public static String getScorePointsQuery() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "questionnaire/gradeRewardCheck";
        }
        return URLEnvironmentConfig.getHttpPrefix() + "client.10010.com/questionnaire/gradeRewardCheck";
    }

    public static String getScorePointsReceive() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "questionnaire/gradeRewardGive";
        }
        return URLEnvironmentConfig.getHttpPrefix() + "client.10010.com/questionnaire/gradeRewardGive";
    }

    public static String getLoginFaceRegist() {
        return getHttpsServer() + "faceAddLogin.htm";
    }

    public static String getLoginFaceLogin() {
        return getHttpsServer() + "faceCompareAndLogin.htm";
    }

    public static String getHomeHuoDongData() {
        return URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileService/customerService/getMynearHallActivity.do" : URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/customerService/getMynearHallActivity.do" : "https://ecstest2018.10010.com/mobileServiceClient-test/customerService/getMynearHallActivity.do";
    }

    public static String getLanguageDataUrl() {
        return URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/uniAdmsInterface_tg/multilingualismCommon" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/uniAdmsInterface/multilingualismCommon" : "https://m.client.10010.com/uniAdmsInterface/multilingualismCommon";
    }

    public static String getLanguageList() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceimportant/customer/getLanguageList" : "https://m.client.10010.com/mobileserviceimportant_pro/customer/getLanguageList";
    }

    public static String getFreezeHtml() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/zhuxiaojihuo/index.html#/activation" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/zhuxiaojihuo/index.html#/activation" : "https://ecstest2018.10010.com/zhuxiaojihuo/index.html#/activation";
    }

    public static String getKefuAndGongchengshi() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceimportant_pro/serviceAdmin/queryServiceAndEngineerPrecisionKey";
        }
        return getHttpDomain() + "mobileserviceimportant/serviceAdmin/queryServiceAndEngineerPrecisionKey";
    }

    public static String getfaceAddLogin() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/faceAddLogin.htm" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileService/faceAddLogin.htm" : "https://client.10010.com/mobileServiceClient-test/faceAddLogin.htm";
    }

    public static String getfaceCompareAndLogin() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/faceCompareAndLogin.htm" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileService/faceCompareAndLogin.htm" : "https://client.10010.com/mobileServiceClient-test/faceCompareAndLogin.htm";
    }

    public static String getface_del() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/face_del.htm" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileService/face_del.htm" : "https://client.10010.com/mobileServiceClient-test/face_del.htm";
    }

    public static String getCareHomeH5Url() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/shoutingguanhuai/index.html" : "https://img.client.10010.com/web_prepub/shoutingguanhuai/index.html";
    }

    public static String getLiveAd() {
        String str = "";
        str = (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isPreMoxEnvironment()) ? "pre_" : "pre_";
        return getHttpDomain() + str + "small_video/live/decorationDataMix";
    }

    public static String gradeRewardGive() {
        return URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileservicequery/marketScore/gradeRewardGive" : URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileservicequery/marketScore/gradeRewardGive" : "https://client.10010.com/mobileservicequery_test/marketScore/gradeRewardGive";
    }

    public static String gradeRewardPicture() {
        return URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileservicequery/marketScore/gradeRewardPicture" : URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileservicequery/marketScore/gradeRewardPicture" : "https://client.10010.com/mobileservicequery_test/marketScore/gradeRewardPicture";
    }

    public static String gradeRewardClose() {
        return URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileservicequery/marketScore/gradeRewardClose" : URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileservicequery/marketScore/gradeRewardClose" : "https://client.10010.com/mobileservicequery_test/marketScore/gradeRewardClose";
    }

    public static String SOCKET_URL() {
        return URLEnvironmentConfig.isPreMoxEnvironment() ? PreMoxHost : getHttpDomain();
    }

    public static String SOCKET_PATH() {
        return (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isPreMoxEnvironment()) ? "/prelive-nodechat/sio" : "/live-nodechat/sio";
    }

    public static String getMianmiSDKNumber() {
        return getHttpDomain() + "unicom_channel_service/login/wosms";
    }

    public static String getGameApps() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "producGameApp";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "pre_finderInterface/gameApp";
        } else {
            return getHttpDomain() + "finderInterface/gameApp";
        }
    }

    public static String gameSignInHistory() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "producGame_signin";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "pre_finderInterface/game_signin";
        } else {
            return getHttpDomain() + "finderInterface/game_signin";
        }
    }

    public static String getTaskCenterUrl(String str, String str2, String str3) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://img.client.10010.com/gametask/index.html?channelID=03&integral=" + str + "&extraIntegral=" + str3 + "&extralFlow=" + str2;
        }
        return "https://img.client.10010.com/web_prepub/gametask/index.html?channelID=03&integral=" + str + "&extraIntegral=" + str3 + "&extralFlow=" + str2;
    }

    public static String getNewGames() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "producGameTaskCenter";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "pre_finderInterface/gameTaskCenter";
        } else {
            return getHttpDomain() + "finderInterface/gameTaskCenter";
        }
    }

    public static String getTuiJianLiangHao() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://client.10010.com/mobileserviceimportant_test/xhIndex/getPubu" : URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceimportant/xhIndex/getPubu" : "https://m.client.10010.com/mobileserviceimportant_pro/xhIndex/getPubu";
    }

    public static String getWeiWenH5Url() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/shoutingweiwenban/index.html" : "https://img.client.10010.com/web_prepub/shoutingweiwenban/index.html";
    }

    public static String statisticalTime(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/activityLogVideo/timeRecord/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String getVideos(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/getVideos/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append("/");
        sb.append(str3);
        return sb.toString();
    }

    public static String getVideoInfo(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/getVideoInfo/");
        sb.append(str);
        return sb.toString();
    }

    public static String searchVideos(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/search/sweet/tab/video/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String shareVideo(String str) {
        String str2 = URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/" : "https://img.client.10010.com/";
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "web_prepub/" : "");
        sb.append("zhibo/index.html#/videoslide?videoId=");
        sb.append(str);
        return sb.toString();
    }

    public static String getVideoZan(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/userStatusAboutVideo/");
        sb.append(str);
        return sb.toString();
    }

    public static String setVideoRing(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_zone/wo/music/judgeRingSettingAbility/");
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        return sb.toString();
    }

    public static String getRingSetting(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_zone/wo/music/ringSetting/");
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        sb.append("/?checkedStatus=");
        sb.append(str3);
        sb.append("&requestSystemResource=android");
        return sb.toString();
    }

    public static String getVideoRingCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_zone/wo/music/ringtoneShortMessageCode");
        return sb.toString();
    }

    public static String getVideoRingRule() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/moreConfig/getMoreConfigInfo");
        return sb.toString();
    }

    public static String getOderRingExplain(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_zone/wo/music/memberOrderingInstructions/");
        sb.append(str);
        return sb.toString();
    }

    public static String zanVideo(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/giveTheThumbsUp/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String videoPraise(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/praise/");
        sb.append(str);
        return sb.toString();
    }

    public static String setLogPoit() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_zone/v1/point/data");
        return sb.toString();
    }

    public static String setVideoNum(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/viewNum/");
        sb.append(str);
        return sb.toString();
    }

    public static String sendMsg(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/score/growth/task/barrage/");
        sb.append(str);
        return sb.toString();
    }

    public static String watchLive(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/score/growth/task/watchLive/");
        sb.append(str);
        return sb.toString();
    }

    public static String watchedLive5m(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/live/buildExpOfMedal/watchedLive/");
        sb.append(str);
        return sb.toString();
    }

    public static String getSameCityLiveList(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/live/getTheSameCityLivingList/");
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        return sb.toString();
    }

    public static String getTheAnchorAvatarInfo(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/liveInfo/getTheAnchorAvatarInformation/");
        sb.append(str);
        return sb.toString();
    }

    public static String getDetailsAuthorWork(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/liveInfo/user/getTheDetailsOfACertainAuthorWork/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String getUserNumberByNet() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_zone/wo/music/getUserNumberByNet");
        return sb.toString();
    }

    public static String getUserNumberByNetForOutSide() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_zone/wo/music/getUserNumberByNetForOutSide");
        return sb.toString();
    }

    public static String getVideoRing(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_zone/wo/music/videoRingtoneBusiness/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append("/");
        sb.append(str3);
        sb.append("/");
        sb.append(str4);
        return sb.toString();
    }

    public static String getVideoNum(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_recommend/ticket/playCount/");
        sb.append(str);
        return sb.toString();
    }

    public static String getComFort() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/getComfortLevel");
        return sb.toString();
    }

    public static String getPopupWindow() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_recommend/activity/content/window/popup");
        return sb.toString();
    }

    public static String isShowPopuWindow() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_recommend/activity/content/window/show/popup");
        return sb.toString();
    }

    public static String getTaskScoreInfo(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/score/growth/task/get/score/sinova/");
        sb.append(str);
        return sb.toString();
    }

    public static String taskVideoThumbsUp(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/score/growth/task/video/thumbs/up/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String taskWatch30s(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/score/growth/task/video/watch/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        return sb.toString();
    }

    public static String getFloatWindow() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_recommend/activity/content/window/floating");
        return sb.toString();
    }

    public static String getHelpBtnInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/videoInfo/showKeyBtnForHelp");
        return sb.toString();
    }

    public static String getWelfareUrl(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("video_recommend/ticket/getWelfareUrl/");
        sb.append(str);
        return sb.toString();
    }

    public static String getVideoList(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/personalCentre/getVideos/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append("/");
        sb.append(str3);
        sb.append("/");
        sb.append(str4);
        return sb.toString();
    }

    public static String getShareCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/activityLog/share/getCode");
        return sb.toString();
    }

    public static String getShareJoin(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(getHttpDomain());
        sb.append(URLEnvironmentConfig.isPrepubEnvironment() ? "pre_" : "");
        sb.append("small_video/activityLog/share/join/");
        sb.append(str);
        return sb.toString();
    }

    public static String focusOnAnchor(String str) {
        String str2 = "small_video/videoInfo/followType/" + str + "/Y";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String liveMyOrder() {
        return URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/wodedingdan2/index.html#/wddd" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/wodedingdan2_dev/#/wddd" : "https://img.client.10010.com/wodedingdan2/index.html#/wddd";
    }

    public static String liveOrderMenu() {
        if (!URLEnvironmentConfig.isPrepubEnvironment()) {
            URLEnvironmentConfig.isDevelopmentEnvironment();
        }
        return liveMyOrder();
    }

    public static String getPinglunSaveComment() {
        String str = getHttpDomain() + "commentSystem/saveComment";
        return "https://m.client.10010.com/commentSystem/saveComment";
    }

    public static String getQianaboUrl() {
        if (!URLEnvironmentConfig.isProductionEnvironment()) {
            return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://client.10010.com/mobileserviceimportant_test/myInfoMoney/queryMoney.do" : "https://m.client.10010.com/mobileserviceimportant_pro/myInfoMoney/queryMoney.do";
        }
        return getHttpDomain() + "mobileserviceimportant/myInfoMoney/queryMoney.do";
    }

    public static String isUnicomWorker() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/recommend2/isunicomuser.htm" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://client.10010.com/mobileService/recommend2/isunicomuser.htm" : "https://client.10010.com/mobileService/recommend2/isunicomuser.htm";
    }

    public static String qianBaoUpLoadEvent() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceimportant/myInfoMoney/moneyFloorClick.do" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://client.10010.com/mobileserviceimportant_test/myInfoMoney/moneyFloorClick.do" : "https://m.client.10010.com/mobileserviceimportant_pro/myInfoMoney/moneyFloorClick.do";
    }

    public static String getSmottoURL() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://api.ad.smaato-net.cn/oapi/v6/ad" : "https://api.ad.smaato.net/oapi/v6/ad";
    }

    public static String getSimboxUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceimportant/simcofferservice/validateSimCofferInfo" : "https://m.client.10010.com/mobileserviceimportant_pro/simcofferservice/validateSimCofferInfo";
    }

    public static String getBannerList(String str) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://m.client.10010.com/small_video/liveInfo/bannerList/" + str;
        } else if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/small_video/liveInfo/bannerList/" + str;
        } else {
            return "https://client.10010.com/pre_small_video/liveInfo/bannerList/" + str;
        }
    }

    public static String getListOldLive(String str, String str2) {
        String str3 = "small_video/anchors/listOldLive/" + str + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str3 = "pre_" + str3;
        }
        return getHttpDomain() + str3;
    }

    public static String getLivingList(String str, String str2, String str3) {
        String str4 = "small_video/liveInfo/livingList/" + str + "/" + str2 + "/" + str3;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str4 = "pre_" + str4;
        }
        return getHttpDomain() + str4;
    }

    public static String getLivingOrFengGuangList(String str, String str2, String str3) {
        String str4 = "small_video/live/listChoiceLiving/" + str + "/" + str2 + "/" + str3;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str4 = "pre_" + str4;
        }
        return getHttpDomain() + str4;
    }

    public static String getYuGaoList(String str, String str2) {
        String str3 = "small_video/liveInfo/livingList/PV/" + str + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str3 = "pre_" + str3;
        }
        return getHttpDomain() + str3;
    }

    public static String getHuiFangList(String str, String str2, String str3) {
        String str4 = "small_video/liveInfo/playBackList/" + str + "/" + str2 + "/" + str3;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str4 = "pre_" + str4;
        }
        return getHttpDomain() + str4;
    }

    public static String getBannerViewData() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "pre_video_recommend/activity/content/list/banner";
        } else if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return getHttpDomain() + "video_recommend/activity/content/list/banner";
        } else if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "video_recommend/activity/content/list/banner";
        } else {
            return "";
        }
    }

    public static String getGameYuyueURL() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/game_server/GetWAdGameInfo/GameList" : URLEnvironmentConfig.isPrepubEnvironment() ? "http://client.10010.com/game_server/GetWAdGameInfo/GameList" : "http://ecstest2018.10010.com/game_server/GetWAdGameInfo/GameList";
    }

    public static String upLoadVideoLog() {
        String str = "small_video/activityLogVideo/record/view";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_small_video/activityLogVideo/record/view";
        }
        return getHttpDomain() + str;
    }

    public static String upLoadVideoRingLog() {
        String str = "video_recommend/HiBoardLog/record/timeView";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/record/timeView";
        }
        return getHttpDomain() + str;
    }

    public static String hiBoardLog(String str, String str2) {
        String str3 = "video_recommend/HiBoardLog/record/" + str + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str3 = "pre_" + str3;
        }
        return getHttpDomain() + str3;
    }

    public static String hiBoardLogFailed() {
        String str = "video_recommend/HiBoardLog/record/failed";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/record/failed";
        }
        return getHttpDomain() + str;
    }

    public static String uploadVideoErrorLog() {
        String str = "small_video/activityLogVideo/record/failed";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_small_video/activityLogVideo/record/failed";
        }
        return getHttpDomain() + str;
    }

    public static String getVideoBannerList(String str) {
        String str2 = "video_recommend/ticket/getBannerList/" + str;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String getContentDate(String str, String str2) {
        String deviceModel = DeviceHelper.getDeviceModel();
        String deviceBranD = DeviceHelper.getDeviceBranD();
        String str3 = "unkown";
        try {
            str3 = DeviceHelper.getDeviceOSVersion().substring(7);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
        if (TextUtils.isEmpty(deviceModel)) {
            deviceModel = "unkown";
        }
        if (TextUtils.isEmpty(deviceBranD)) {
            deviceBranD = "unkown";
        }
        if (TextUtils.isEmpty(deviceModel)) {
            deviceModel = "unknown";
        }
        if (TextUtils.isEmpty(deviceBranD)) {
            deviceBranD = "unknown";
        }
        String str4 = "video_recommend/content/personal/" + str + "/" + deviceModel + "/" + deviceBranD + "/Android/" + str3 + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str4 = "pre_" + str4;
        }
        return getHttpDomain() + str4;
    }

    public static String attentionUser(String str) {
        String str2 = "video_recommend/content/attention/" + str;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String hiBoardLogShow() {
        String str = "video_recommend/HiBoardLog/show";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/show";
        }
        return getHttpDomain() + str;
    }

    public static String hiBoardLogPlay() {
        String str = "video_recommend/HiBoardLog/play";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/play";
        }
        return getHttpDomain() + str;
    }

    public static String hiBoardLogOver() {
        String str = "video_recommend/HiBoardLog/over";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/over";
        }
        return getHttpDomain() + str;
    }

    public static String hiBoardLogClick() {
        String str = "video_recommend/HiBoardLog/click";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/click";
        }
        return getHttpDomain() + str;
    }

    public static String hiBoardLogAutoPlay() {
        String str = "video_recommend/HiBoardLog/autoPlay";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/autoPlay";
        }
        return getHttpDomain() + str;
    }

    public static String hiBoardLogAutoOver() {
        String str = "video_recommend/HiBoardLog/autoOver";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/autoOver";
        }
        return getHttpDomain() + str;
    }

    public static String hiBoardLogStay() {
        String str = "video_recommend/HiBoardLog/stay";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/HiBoardLog/stay";
        }
        return getHttpDomain() + str;
    }

    public static String unFollow(String str) {
        String str2 = "video_recommend/content/unFollow/" + str;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String getAttentionList(String str) {
        String str2 = "video_recommend/content/followList/" + str;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String getOptionInfo() {
        String str = "video_recommend/content/screenSwitch";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/content/screenSwitch";
        }
        return getHttpDomain() + str;
    }

    public static String getBoardList(String str, String str2) {
        String str3 = "video_recommend/ticket/getBoardList/" + str + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str3 = "pre_" + str3;
        }
        return getHttpDomain() + str3;
    }

    public static String getBoardInfo(String str, String str2) {
        String str3 = "video_recommend/ticket/getBoardInfo/" + str + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str3 = "pre_" + str3;
        }
        return getHttpDomain() + str3;
    }

    public static String getVideoInfo() {
        String str = "video_recommend/ticket/getContent";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/ticket/getContent";
        }
        return getHttpDomain() + str;
    }

    public static String likeOperation(String str, String str2) {
        String str3 = "video_recommend/ticket/likeOperation/" + str + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str3 = "pre_" + str3;
        }
        return getHttpDomain() + str3;
    }

    public static String getQuickAccessUrl() {
        String str = URLEnvironmentConfig.isPrepubEnvironment() ? "mobileserviceimportant_pro/startIconQuickEntry/findIconInfo" : "mobileserviceimportant/startIconQuickEntry/findIconInfo";
        return getHttpDomain() + str;
    }

    public static String kingKongDistrict() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/gamediamond" : URLEnvironmentConfig.isPrepubEnvironment() ? "http://m.client.10010.com/pre_finderInterface/gamediamond" : "http://ecstest2018.10010.com/finderInterface/gamediamond";
    }

    public static String getWeiWenDialogUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceimportant/customer/popWindowHappen" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/mobileserviceimportant_pro/customer/popWindowHappen" : "https://client.10010.com/mobileserviceimportant_test/customer/popWindowHappen";
    }

    public static String getEdopHomeConfigUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/edopinterface/service/appInfo/{publishType}/{appVersion}/{appId}" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/edopinterface/service/appInfo/{publishType}/{appVersion}/{appId}" : "https://ecstest2018.10010.com/edopinterface/service/appInfo/{publishType}/{appVersion}/{appId}";
    }

    public static String getEdopPrefetchConfigUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/edopinterface/service/appInfoList/{publishType}/{appVersion}/{appIdList}" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/edopinterface/service/appInfoList/{publishType}/{appVersion}/{appIdList}" : "https://ecstest2018.10010.com/edopinterface/service/appInfoList/{publishType}/{appVersion}/{appIdList}";
    }

    public static String getEdopAppIdInfoUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/edopinterface/service/action" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/edopinterface/service/action" : "https://ecstest2018.10010.com/edopinterface/service/action";
    }

    public static String getEdopLogUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://smartad.10010.com/edop/edopLogFuction/stepLog" : "https://client.10010.com/edop/edopLogFuction/stepLog";
    }

    public static String getEdopYuJiaZaiUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/edop_ng/clientFunction/getBasicPreload" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/edop_ng/clientFunction/getBasicPreload" : "http://client.10010.com/edop_test/clientFunction/getBasicPreload";
    }

    public static String getEdopMoreUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/edopinterface/service/ngAppAction" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/edopinterface/service/ngAppAction" : "http://ecstest2018.10010.com/edopinterface/service/ngAppAction";
    }

    public static String getEdopMoreH5Url() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/edopfront/index.html#/applet/list" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/edopfront/index.html#/applet/list" : "https://ecstest2018.10010.com/edopfront/index.html#/applet/list";
    }

    public static String getEdopFankuiUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/edopfront/index.html#/" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/edopfront/index.html#/" : "https://ecstest2018.10010.com/edopfront/index.html#/";
    }

    public static String getEdopDetailUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/edopfront/index.html#/applet/detail" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/edopfront/index.html#/applet/detail" : "https://ecstest2018.10010.com/edopfront/index.html#/applet/detail";
    }

    public static String getAddShortcutDestopHelpUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/edopfront/index.html#/desktop" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/edopfront/index.html#/desktop" : "https://ecstest2018.10010.com/edopfront/index.html#/desktop";
    }

    public static String getH5AddShortcutDestopConfig() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileservicequery/gphconfig/getGphconfigByAppIds" : URLEnvironmentConfig.isPrepubEnvironment() ? "http://client.10010.com/mobileservicequery_test/gphconfig/getGphconfigByAppIds" : "http://ecstest2018.10010.com/mobileservicequery_test/gphconfig/getGphconfigByAppIds";
    }

    public static String uploadJiFenIconUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/getJiFenFlag" : "https://m.client.10010.com/pre_finderInterface/getJiFenFlag";
    }

    public static String getZhiHuiShengHuoUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/recommend2/intelligentlife.htm" : "http://client.10010.com/mobileService/recommend2/intelligentlife.htm";
    }

    public static String getVideoPlayList(String str) {
        String str2 = "video_recommend/content/contentDetails/" + str;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String dislike() {
        String str = "video_recommend/content/action";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/content/action";
        }
        return getHttpDomain() + str;
    }

    public static String getDianZan(String str, String str2) {
        String str3 = "video_recommend/content/thumbsUp/" + str + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str3 = "pre_" + str3;
        }
        return getHttpDomain() + str3;
    }

    public static String getVideoTuiJianList(String str, String str2) {
        String str3 = "video_recommend/content/related/" + str + "/" + str2;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str3 = "pre_" + str3;
        }
        return getHttpDomain() + str3;
    }

    public static String isFollowing(String str) {
        String str2 = "video_recommend/content/checkAttention/" + str;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String GuanzhuON(String str) {
        String str2 = "video_recommend/content/attention/" + str;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String GuanzhuOFF(String str) {
        String str2 = "video_recommend/content/unFollow/" + str;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str2 = "pre_" + str2;
        }
        return getHttpDomain() + str2;
    }

    public static String getChannel() {
        String str = "video_recommend/content/navigate";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/content/navigate";
        }
        return getHttpDomain() + str;
    }

    public static String SaveChannel() {
        String str = "video_recommend/content/saveNavigate";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/content/saveNavigate";
        }
        return getHttpDomain() + str;
    }

    public static String setJb(String str, String str2, String str3, String str4, String str5) {
        String str6 = "video_recommend/content/informant/" + str + "/" + str2 + "/" + str3 + "/" + str4 + "/" + str5;
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str6 = "pre_" + str6;
        }
        return getHttpDomain() + str6;
    }

    public static String getGDTUploadUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceimportant/provide/setInfo" : "https://m.client.10010.com/mobileserviceimportant_pro/provide/setInfo";
    }

    public static String getYinSiDialogUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/customer/pushReminder.htm" : "https://client.10010.com/mobileService/customer/pushReminder.htm";
    }

    public static String getMessageNotificationList() {
        return getHttpDomain() + "mobileservicequery/generalsettings/get/settinglist";
    }

    public static String getMessageNotificationSwitchStatus() {
        return getHttpDomain() + "mobileservicequery/generalsettings/change/switchStatus";
    }

    public static String getLiBaoTiXing() {
        return getHttpDomain() + "myPrizeForActivity/openServices/findRecommendRecord";
    }

    public static String getFuYiPingYuGaoUrl(String str) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://img.client.10010.com/zhibo/index.html#/huifanglist?userId=" + str + "&from=liveplayer";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://img.client.10010.com/web_prepub/zhibo/index.html#/huifanglist?userId=" + str + "&from=liveplayer";
        } else {
            return "https://ecstest2018.10010.com/zhibo/index.html#/huifanglist?userId=" + str + "&from=liveplayer";
        }
    }

    public static String getFuYiPingHuiFangUrl(String str, String str2) {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://img.client.10010.com/zhibo/index.html#/huifangplayer?userId=" + str + "&videoId=" + str2 + "&from=livelist";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://img.client.10010.com/web_prepub/zhibo/index.html#/huifangplayer?userId=" + str + "&videoId=" + str2 + "&from=livelist";
        } else {
            return "https://ecstest2018.10010.com/zhibo/index.html#/huifangplayer?userId=" + str + "&videoId=" + str2 + "&from=livelist";
        }
    }

    public static String getBoardInfoNew() {
        String str = "video_recommend/ticket/getBoardInfoNew";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/ticket/getBoardInfoNew";
        }
        return getHttpDomain() + str;
    }

    public static String likeOperationNew() {
        String str = "video_recommend/ticket/likeOperationNew";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/ticket/likeOperationNew";
        }
        return getHttpDomain() + str;
    }

    public static String getContentNew() {
        String str = "video_recommend/ticket/getContentNew";
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            str = "pre_video_recommend/ticket/getContentNew";
        }
        return getHttpDomain() + str;
    }

    public static String getHomeDataUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/wisdom/backGroundQuery";
        }
        return getHttpDomain() + "mobileserviceNine/wisdom/backGroundQuery";
    }

    public static String getMenuSkin() {
        return getHttpDomain() + "navHomeImg";
    }

    public static String getHomeMore() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/homefuwu90/index.html?type=fromHome" : "https://client.10010.com/homefuwu90/index.html?type=fromHome";
    }

    public static String getImageCode() {
        String deviceID = DeviceHelper.getDeviceID(false);
        if (TextUtils.isEmpty(deviceID)) {
            deviceID = "";
        }
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://m.client.10010.com/mobileservicequery/faceQuery/code?deviceId=" + deviceID;
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileservicequery/faceQuery/code?deviceId=" + deviceID;
        } else {
            return "";
        }
    }

    public static String getBroadCompare() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceimportant/customer/face.htm" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/mobileserviceimportant_pro/customer/face.htm" : "https://m.client.10010.com/mobileserviceimportant_pro/customer/face.htm";
    }

    public static String getAnquanzhognxingenerate() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "mobileserviceimportant/channel/generate";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceimportant_pro/channel/generate";
        } else {
            return getHttpDomain() + "mobileserviceimportant_test/channel/generate";
        }
    }

    public static String getAnquanzhognxinlistPage() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "mobileserviceimportant/channel/listPage";
        } else if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceimportant_pro/channel/listPage";
        } else {
            return getHttpDomain() + "mobileserviceimportant_test/channel/listPage";
        }
    }

    public static String getXiaoxiUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/kuaibaoxiaoxironghe/index.html#/message?webViewNavIsHidden=true" : "https://img.client.10010.com/web_prepub/kuaibaoxiaoxironghe/index.html#/message?webViewNavIsHidden=true";
    }

    public static String getLiuyanUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/kuaibaoxiaoxironghe/index.html#/leaveMsg?webViewNavIsHidden=true" : "https://img.client.10010.com/web_prepub/kuaibaoxiaoxironghe/index.html#/leaveMsg?webViewNavIsHidden=true";
    }

    public static String getXiaoChengxuUrl() {
        return getHttpDomain() + "edopinterface/service/action";
    }

    public static String getH5RegisterInfoInEdopUrl() {
        return getHttpDomain() + "edopinterface/service/h5Action";
    }

    public static String getXieYi() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/appjianban/index.html#/?webViewNavIsHidden=true" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/appjianban/index.html#/?webViewNavIsHidden=true" : "";
    }

    public static String getSkinUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/customSkin/list";
        }
        return getHttpDomain() + "mobileserviceNine/customSkin/list";
    }

    public static String getHomeMergeUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/homefusion/fuInter";
        }
        return getHttpDomain() + "mobileserviceNine/homefusion/fuInter";
    }

    public static String getHomeFindUrl() {
        if (!URLEnvironmentConfig.isProductionEnvironment()) {
            return URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/caifuhome/index.html" : "https://ecstest2018.10010.com/caifuhome/index.html";
        }
        String string = App.getSharePreferenceUtil().getString(ConfigManager.Config_Caifu_Url_Key);
        return TextUtils.isEmpty(string) ? getNewFaxian() : string;
    }

    public static String getNoticConfigUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://pushservice.10010.com/pushweb/pushType" : "http://ecstest2018.10010.com/pushweb/pushType";
    }

    public static String getNoticKuaixun() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/messagerevision/index.html#/kuaibao" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/messagerevision/index.html#/kuaibao" : "https://img.client.10010.com/messagerevision/index.html#/kuaibao";
    }

    public static String getNoticXiaoxi() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/messagerevision/index.html" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/messagerevision/index.html" : "https://img.client.10010.com/messagerevision/index.html";
    }

    public static String getNoticeSetting() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/messagerevision/index.html#/indexMessagecenter" : "https://img.client.10010.com/messagerevision/index.html#/indexMessagecenter";
    }

    public static String getTicket() {
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return getHttpDomain() + "getTicket";
        } else if (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isPreMoxEnvironment() || URLEnvironmentConfig.isPrepubEnvironment2()) {
            return URLEnvironmentConfig.getHttpPrefix() + "client.10010.com/getTicket";
        } else {
            return "http://ecstest2018.10010.com/getTicket";
        }
    }

    public static String getTongYiPaySettingUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://epay.10010.com/ci-mps-st-web/paysetting" : (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isPreMoxEnvironment() || URLEnvironmentConfig.isPrepubEnvironment2()) ? "https://ecstest2018.10010.com/mobileServiceClient/openPlatform/openPlatLineNew.htm?to_url=https://test1.unicompayment.com/ci-mps-st-web/paysetting" : "https://ecstest2018.10010.com/mobileServiceClient/openPlatform/openPlatLineNew.htm?to_url=https://test1.unicompayment.com/ci-mps-st-web/paysetting";
    }

    public static String getNoticeCollect() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/messagerevision/index.html#/collect" : "https://img.client.10010.com/messagerevision/index.html#/collect";
    }

    public static String getLoginSwitch() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/login/v1/switch/getSwitch";
        }
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://m.client.10010.com/mobileService/login-web/v1/switch/getSwitch";
        }
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/login/v1/switch/getSwitch";
        }
        return getHttpDomain() + "login/v1/switch/getSwitch";
    }

    public static String getMenuUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/v1/nav/home";
        }
        return getHttpDomain() + "mobileserviceNine/v1/nav/home";
    }

    public static String getServiceZuJianUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/homefusion/serviceReminder";
        }
        return getHttpDomain() + "mobileserviceNine/homefusion/serviceReminder";
    }

    public static String getActivityUrl() {
        if (!URLEnvironmentConfig.isProductionEnvironment()) {
            return URLEnvironmentConfig.isPrepubEnvironment() ? "https://m.client.10010.com/mobileserviceNine_pre/wisdom/precisionKeyAndActivity" : "https://ecstest2018.10010.com/mobileserviceNine/wisdom/precisionKeyAndActivity";
        }
        return getHttpDomain() + "mobileserviceNine/wisdom/precisionKeyAndActivity";
    }

    public static String userDetails() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/gerenxinxi/index.html" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/gerenxinxi/index.html" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "http://ecstest2018.10010.com/gerenxinxi/index.html" : "https://img.client.10010.com/web_huidu/gerenxinxi/index.html";
    }

    public static String userLibaoUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/myPage/giftPackageInfo";
        }
        return getHttpDomain() + "mobileserviceNine/myPage/giftPackageInfo";
    }

    public static String tongyicaijiConfigUrl() {
        if (URLEnvironmentConfig.isForTYCJTest()) {
            return "https://ecstest2018.10010.com/collectionService/configurationConsumer/configuration";
        }
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://smartad.10010.com/collectionService/configurationConsumer/configuration";
        }
        return getHttpDomain() + "collectionService/configurationConsumer/configuration";
    }

    public static String tongyicaijiUploadUrl() {
        if (URLEnvironmentConfig.isForTYCJTest()) {
            return "https://ecstest2018.10010.com/collectionService/collectionConsumer/unify";
        }
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://smartad.10010.com/collectionService/collectionConsumer/unify";
        }
        return getHttpDomain() + "collectionService/collectionConsumer/unify";
    }

    public static String liuyanUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileservicequery/messageleave/getcnt" : "https://client.10010.com/mobileservicequery/messageleave/getcnt";
    }

    public static String clearLiuyan() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileservicequery/leavemsg/changeLeaveStatus" : "https://client.10010.com/mobileservicequery/leavemsg/changeLeaveStatus";
    }

    public static String servicePageUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/homefuwu90/index.html?type=fromFW" : "https://client.10010.com/homefuwu90/index.html?type=fromFW";
    }

    public static String getWeWorkUrl() {
        return URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileservicequery/sharebase/getCompanyInfoByProvinceAndMob" : "https://m.client.10010.com/mobileservicequery/sharebase/getCompanyInfoByProvinceAndMob";
    }

    public static String getNewTopUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://m.client.10010.com/mobileserviceNine_pre/myPage/newUserInfo/";
        }
        return getHttpDomain() + "mobileserviceNine/myPage/newUserInfo/";
    }

    public static String getSettingMimaguanli() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/login/v1/switch/getPasswordSwitch";
        }
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            return "https://m.client.10010.com/mobileService/login-web/v1/switch/getPasswordSwitch";
        }
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            return "https://ecstest2018.10010.com/login/v1/switch/getPasswordSwitch";
        }
        return getHttpDomain() + "login/v1/switch/getPasswordSwitch";
    }

    public static String getFuwumimaUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://img.client.10010.com/fuwumimachongzhi/index.html?entrance=setting" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://img.client.10010.com/web_prepub/fuwumimachongzhi/index.html?entrance=setting" : "http://ecstest2018.10010.com/fuwumimachongzhi/index.html?entrance=setting";
    }

    public static String getEdopXunJianUrl() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/edopinterface/service/action" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/edopinterface/service/action" : "https://m.client.10010.com/edopinterface/service/action";
    }

    public static String getSafelyHostConfig() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceNine/v1/config/securityDomain" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileserviceNine/v1/config/securityDomain" : "https://m.client.10010.com/mobileserviceNine_pre/v1/config/securityDomain";
    }

    public static String getHuiDuConfig() {
        return getHttpDomain() + "tag-service/v1/tag/dyeTag";
    }

    public static String getJiaofeiStateUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/login-web/v1/payGreenChannel/getPayMobileState" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/login/v1/payGreenChannel/getPayMobileState" : "https://ecstest2018.10010.com/login/v1/payGreenChannel/getPayMobileState";
    }

    public static String getFaceV3Url() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceNine/v1/config/faceLicense" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileserviceNine/v1/config/faceLicense" : "https://client.10010.com/mobileserviceNine_pre/v1/config/faceLicense";
    }

    public static String getServiceOrderUrl() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/myPage/myOrder";
        }
        return getHttpDomain() + "mobileserviceNine/myPage/myOrder";
    }

    public static String getFootPrint() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/myPage/footMarkInfo";
        }
        return getHttpDomain() + "mobileserviceNine/myPage/footMarkInfo";
    }

    public static String getHomeTabUrl(String str, String str2) {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/api/v1/index/queryIndexCfig/" + str + "/" + str2;
        }
        return getHttpDomain() + "mobileserviceNine/api/v1/index/queryIndexCfig/" + str + "/" + str2;
    }

    public static String getServiceHomeMarketingBits() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://m.client.10010.com/mobileserviceNine/api/v1/homeService/getServiceHomeMarketingBits" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileserviceNine/api/v1/homeService/getServiceHomeMarketingBits" : "https://m.client.10010.com/mobileserviceNine_pre/api/v1/homeService/getServiceHomeMarketingBits";
    }

    public static String getHomeJumpTabSwitchUrl() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "https://card.10010.com/terminal/numberPageNew?channel=06-0324-XXXX-XXXX" : "https://demo.mall.10010.com:8108/terminal/numberPageNew?channel=06-0324-XXXX-XXXX";
    }

    public static String getHomeTuiJianDefUrlAndCumpUrl(String str) {
        HashMap hashMap = new HashMap();
        if (URLEnvironmentConfig.isProductionEnvironment()) {
            hashMap.put("h5Url", "https://img.client.10010.com/homejingxuan11/index.html");
            hashMap.put("cumpUrl", "https://edop_unicom?appid=edop_unicom_547d7616");
            hashMap.put("appId", "edop_unicom_547d7616");
        } else if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            hashMap.put("h5Url", "https://ecstest2018.10010.com/homejingxuan11/index.html");
            hashMap.put("cumpUrl", "https://edop_unicom?appid=edop_unicom_06a93698");
            hashMap.put("appId", "edop_unicom_06a93698");
        } else {
            hashMap.put("h5Url", "https://client.10010.com/homejingxuan11/index.html");
            hashMap.put("cumpUrl", "https://edop_unicom?appid=edop_unicom_1c82054c");
            hashMap.put("appId", "edop_unicom_1c82054c");
        }
        return (String) hashMap.get(str);
    }

    public static String getWodexiaoheitiaoCumpAppId() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "edop_unicom_14985f2e" : URLEnvironmentConfig.isDevelopmentEnvironment() ? "edop_unicom_bf950a44" : "edop_unicom_bf950a44";
    }

    public static String getesimLogin() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return "https://client.10010.com/mobileService/esimLogin.htm";
        }
        return getHttpsServer() + "esimLogin.htm";
    }

    public static String getInitOMO() {
        if (URLEnvironmentConfig.isPrepubEnvironment()) {
            return getHttpDomain() + "mobileserviceNine_pre/query/preload";
        }
        return getHttpDomain() + "mobileserviceNine/query/preload";
    }

    public static String getUserDevice() {
        return URLEnvironmentConfig.isDevelopmentEnvironment() ? "https://ecstest2018.10010.com/mobileserviceNine/myPage/getDeviceInfo" : URLEnvironmentConfig.isPrepubEnvironment() ? "https://client.10010.com/mobileserviceNine_pre/myPage/getDeviceInfo" : "https://m.client.10010.com/mobileserviceNine/myPage/getDeviceInfo";
    }
}
