package com.sinovatech.unicom.basic.boxcenter;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.p314po.CacheBox;
import com.sinovatech.unicom.basic.p314po.CacheBox_;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.PreferenceConstUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import io.objectbox.Box;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CacheDataCenter {
    private static CacheDataCenter dataCenter;
    private Box<CacheBox> box = App.getBoxStore().boxFor(CacheBox.class);

    private CacheDataCenter() {
    }

    public static synchronized CacheDataCenter getInstance() {
        CacheDataCenter cacheDataCenter;
        synchronized (CacheDataCenter.class) {
            if (dataCenter == null) {
                dataCenter = new CacheDataCenter();
            }
            cacheDataCenter = dataCenter;
        }
        return cacheDataCenter;
    }

    public void setHomeInfo(String str, String str2) {
        setContent("homeInfo_", str, str2);
    }

    public String getHomeInfo(String str) {
        return getContent("homeInfo_", str);
    }

    public void setHomeInfoCard(String str, String str2) {
        setContent("homeInfoCard_", str, str2);
    }

    public String getHomeInfoCard(String str) {
        return getContent("homeInfoCard_", str);
    }

    public void setHomeInfoDataTime(String str, String str2) {
        setContent("flush_date_time", str, str2);
    }

    public String getHomeInfoDataTime(String str) {
        return getContent("flush_date_time", str);
    }

    public void setHomeTab(String str) {
        setContent("unicom_home_tab", str, "0");
    }

    public String getHomeTab() {
        return getContent("unicom_home_tab", "0");
    }

    public void setNewUserGiftBagData(String str) {
        setContent("home_search_new_user", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getNewUserGiftBagData() {
        return getContent("home_search_new_user", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setNewUserEndTime(String str) {
        setContent("home_search_new_user_endtime", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getNewUserEndTime() {
        return getContent("home_search_new_user_endtime", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setHomeCardShunxu(String str, String str2) {
        setContent("home_kapian", str, str2);
    }

    public String getHomeCardShunxu(String str) {
        return getContent("home_kapian", str);
    }

    public void setUserInfo(String str, String str2) {
        setContent("user_info", str, str2);
    }

    public String getUserInfo(String str) {
        return getContent("user_info", str);
    }

    public void setCardBottomData(String str, String str2, String str3) {
        setContent("user_info_bottom", str, str2 + str3);
    }

    public String getCardBottomData(String str, String str2) {
        return getContent("user_info_bottom", str + str2);
    }

    public String getUserNumerInfo(String str) {
        return getContent("user_number", str);
    }

    public void setServiceRecommend(String str, String str2) {
        setContent("service_recommend", str, str2);
    }

    public String getServiceRecommeen(String str) {
        return getContent("service_recommend", str);
    }

    public String getCustomContent(String str) {
        return getContent("custom_ceche_recommend1", str);
    }

    public void setCustomContentNew(String str, String str2) {
        setContent("custom_ceche_jingangqu", str, str2);
    }

    public String getCustomContentNew(String str) {
        return getContent("custom_ceche_jingangqu", str);
    }

    public void setFuWuContent(String str, String str2) {
        setContent("custom_ceche_fuwu", str, str2);
    }

    public String getFuWuContent(String str) {
        return getContent("custom_ceche_fuwu", str);
    }

    public void setFindRedpoint(String str) {
        setContent("find_redpoint", str, "");
    }

    public String getFindRedpoing() {
        return getContent("find_redpoint", "");
    }

    public void setLiulanYouli(String str) {
        setContent("liulanyouli", str, "0");
    }

    public String getLiulanYouli() {
        return getContent("liulanyouli", "0");
    }

    public void setQuanyiNew(String str, String str2) {
        setContent("quanyi_info_new9", str, str2);
    }

    public String getQuanyiNew(String str) {
        return getContent("quanyi_info_new9", str);
    }

    public void setLibao(String str, String str2) {
        setContent("libao_info_new9", str, str2);
    }

    public String getLibao(String str) {
        return getContent("libao_info_new9", str);
    }

    public void setGiftListString(String str) {
        try {
            if ("0000".equals(new JSONObject(str).optString("statusCode"))) {
                setContent("audience_liwu_list", str, "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GiftEntity> getGiftList() {
        ArrayList arrayList = new ArrayList();
        String content = getContent("audience_liwu_list", "0");
        if (!TextUtils.isEmpty(content)) {
            try {
                JSONArray optJSONArray = new JSONObject(content).optJSONArray("giftArr");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    GiftEntity giftEntity = new GiftEntity();
                    giftEntity.setGiftCode(optJSONObject.optString("giftCode"));
                    giftEntity.setGiftName(optJSONObject.optString("giftName"));
                    giftEntity.setGiftPrice(optJSONObject.optString("giftPrice"));
                    giftEntity.setPayUrl(optJSONObject.optString("payUrl"));
                    giftEntity.setObtainUrl(optJSONObject.optString("obtainUrl"));
                    giftEntity.setGiftSvga(optJSONObject.optString("giftSvga"));
                    giftEntity.setGiftImgSrc(optJSONObject.optString("giftImgSrc"));
                    giftEntity.setGiftIdx(optJSONObject.optString("giftIdx"));
                    giftEntity.setImgFileChat(optJSONObject.optString("imgFileChat"));
                    giftEntity.setImgFileNum(optJSONObject.optString("imgFileNum"));
                    giftEntity.setImgFileFloat(optJSONObject.optString("imgFileFloat"));
                    giftEntity.setXianhua("10001".equals(giftEntity.getGiftCode()));
                    arrayList.add(giftEntity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public void setCityData(String str) {
        setContent("city_select_Data", str, "0");
    }

    public String getCityData() {
        return getContent("city_select_Data", "0");
    }

    private void setContent(String str, String str2, String str3) {
        CacheBox findFirst = this.box.query().equal(CacheBox_.key, str).and().equal(CacheBox_.userMobile, str3).build().findFirst();
        if (findFirst != null) {
            findFirst.setContent(str2);
        } else {
            findFirst = new CacheBox();
            findFirst.setContent(str2);
            findFirst.setKey(str);
            findFirst.setUserMobile(str3);
        }
        this.box.put((Box<CacheBox>) findFirst);
    }

    private String getContent(String str, String str2) {
        CacheBox findFirst = this.box.query().equal(CacheBox_.key, str).and().equal(CacheBox_.userMobile, str2).build().findFirst();
        return findFirst != null ? findFirst.getContent() : "";
    }

    public void setIsUnicomWorker(String str) {
        setContent("unicom_worker_state", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public boolean isUnicomWorker() {
        return "1".equals(getContent("unicom_worker_state", UserManager.getInstance().getCurrentPhoneNumber()));
    }

    public void setBackAdvData(String str) {
        setContent("background_ceche_adv", str, "0");
    }

    public void setUserRecommend(String str) {
        setContent("user_recommend_blacklist", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getUserRecommend() {
        return getContent("user_recommend_blacklist", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setGameCenterHot(String str) {
        setContent("game_center_hot", str, "0");
    }

    public String getGameCenterHot() {
        return getContent("game_center_hot", "0");
    }

    public void setGameCenterFloor(String str) {
        setContent("game_center_floor", str, "0");
    }

    public String getGameCenterFloor() {
        return getContent("game_center_floor", "0");
    }

    public void setGameBanner(String str) {
        setContent("game_banner", str, "0");
    }

    public String getGameBanner() {
        return getContent("game_banner", "0");
    }

    public void setGameKingKongDistrict(String str) {
        setContent("game_center_KingKong", str, "0");
    }

    public String getGameKingKongDistrict() {
        return getContent("game_center_KingKong", "0");
    }

    public void setGameCenterFloor2(String str) {
        setContent("game_center_floor2", str, "0");
    }

    public String getGameCenterFloor2() {
        return getContent("game_center_floor2", "0");
    }

    public void setGameCenterPlay(String str) {
        setContent("game_center_play", str, "0");
    }

    public String getGameCenterPlay() {
        return getContent("game_center_play", "0");
    }

    public void setGameCenterPoster(String str) {
        setContent("game_center_poster", str, "0");
    }

    public void setGameCenterRecommend(String str) {
        setContent("game_center_recommend", str, "0");
    }

    public String getGameCenterRecommend() {
        return getContent("game_center_recommend", "0");
    }

    public void setMapUrl(String str) {
        setContent("game_center_url", str, "0");
    }

    public String getMapUrl() {
        return getContent("game_center_url", "0");
    }

    public void setGameCenterSingInHistory(String str) {
        setContent("game_center_sign_in_history", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getGameCenterSingInHistory() {
        return getContent("game_center_sign_in_history", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setUserQianBaoData(String str) {
        setContent("uesr_qianbao_data", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getUserQianBaoData() {
        return getContent("uesr_qianbao_data", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setQuickAccessData(String str) {
        UIUtils.logD("setQuickAccessData", "setQuickAccessData: " + str);
        setContent("quick_access_data", str, "0");
    }

    public String getQuickAccessData() {
        return getContent("quick_access_data", "0");
    }

    public void setUseQuickData(String str) {
        setContent("use_quick_data", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getUseQuickData() {
        return getContent("use_quick_data", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setFirstOpen(String str) {
        setContent("user_first_open_1", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public boolean getFirstOpen() {
        return TextUtils.isEmpty(getContent("user_first_open_1", UserManager.getInstance().getCurrentPhoneNumber()));
    }

    public void saveCustomHaiBao(String str) {
        setContent("zidingyi_haibao", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getCustomHaiBao() {
        return getContent("zidingyi_haibao", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setFlowGet(String str) {
        setContent("game_flowget", str, "0");
    }

    public String getFlowGet() {
        return getContent("game_flowget", "0");
    }

    public void setHotGet(String str) {
        setContent("game_hotget", str, "0");
    }

    public String getHotGet() {
        return getContent("game_hotget", "0");
    }

    public void setNickName(String str) {
        setContent("game_nickname", str, "0");
    }

    public String getNickName() {
        return getContent("game_nickname", "0");
    }

    public void setKefuPushData(String str) {
        setContent("kefu_push_data", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getKefuPushData() {
        return getContent("kefu_push_data", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setUserInfoNew(String str, String str2) {
        setContent("user_info_new", str, str2);
    }

    public String getUserInfoNew(String str) {
        return getContent("user_info_new", str);
    }

    public void setFuWuTopList(String str, String str2) {
        setContent("fuwu_top_list", str, str2);
    }

    public String getFuWuTopList(String str) {
        return getContent("fuwu_top_list", str);
    }

    public void setFuWuText(String str, String str2) {
        setContent("fuwu_text", str, str2);
    }

    public String getFuWuText(String str) {
        return getContent("fuwu_text", str);
    }

    public void setPageNewOldUser(String str) {
        try {
            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            int i = sharePreferenceUtil.getInt("page_new_old_user_" + str, 0);
            if (i == 2) {
                return;
            }
            SharePreferenceUtil sharePreferenceUtil2 = App.getSharePreferenceUtil();
            sharePreferenceUtil2.putInt("page_new_old_user_" + str, i == 0 ? 1 : 2);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public String getPageNewOldUser() {
        try {
            if (App.hasLogined()) {
                SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                StringBuilder sb = new StringBuilder();
                sb.append("page_new_old_user_");
                sb.append(UserManager.getInstance().getCurrentPhoneNumber());
                return sharePreferenceUtil.getInt(sb.toString(), 0) == 1 ? "Y" : "N";
            }
            return "N";
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
            return "N";
        }
    }

    public void setJGQSkinData(String str) {
        setContent("home_jgq_skin_info", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setUnicomCaijiData(String str) {
        setContent("unicom_caiji_data", str, "0");
    }

    public void setXialaTeseData(String str) {
        setContent("xiala_teseyewu", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getXialaTeseData() {
        return getContent("xiala_teseyewu", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setXialaCollectData(String str, String str2) {
        MsLogUtil.m7979d(CollectionMoreActivity.TAG, "缓存收藏数据: type = " + str2 + " content = " + str);
        StringBuilder sb = new StringBuilder();
        sb.append("xiala_collect");
        sb.append(str2);
        setContent(sb.toString(), str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getXialaCollectData(String str) {
        String content = getContent("xiala_collect" + str, UserManager.getInstance().getCurrentPhoneNumber());
        MsLogUtil.m7979d(CollectionMoreActivity.TAG, "获取缓存收藏数据: type = " + str + " content = " + content);
        return content;
    }

    public void setCustomSkin(BackgroundSkinBean backgroundSkinBean) {
        try {
            if (backgroundSkinBean != null) {
                Gson gson = new Gson();
                setContent("app_custom_skin", !(gson instanceof Gson) ? gson.toJson(backgroundSkinBean) : NBSGsonInstrumentation.toJson(gson, backgroundSkinBean), UserManager.getInstance().getCurrentPhoneNumber());
                return;
            }
            setContent("app_custom_skin", "", UserManager.getInstance().getCurrentPhoneNumber());
        } catch (Exception e) {
            MsLogUtil.m7978e("设置自定义皮肤数据异常:" + e.getMessage());
        }
    }

    public BackgroundSkinBean getCustomSkinBean() {
        try {
            String content = getContent("app_custom_skin", UserManager.getInstance().getCurrentPhoneNumber());
            if (!TextUtils.isEmpty(content)) {
                Gson gson = new Gson();
                BackgroundSkinBean backgroundSkinBean = (BackgroundSkinBean) (!(gson instanceof Gson) ? gson.fromJson(content, (Class<Object>) BackgroundSkinBean.class) : NBSGsonInstrumentation.fromJson(gson, content, (Class<Object>) BackgroundSkinBean.class));
                if (backgroundSkinBean != null) {
                    String productImgUrl = backgroundSkinBean.getProductImgUrl();
                    if (!TextUtils.isEmpty(productImgUrl) && (!productImgUrl.endsWith("_output_crop_image.jpg") || new File(productImgUrl).exists())) {
                        return backgroundSkinBean;
                    }
                    setCustomSkin(null);
                    return null;
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7978e("读取自定义皮肤数据异常:" + e.getMessage());
        }
        return null;
    }

    public void setSkinCache(String str) {
        try {
            setContent(PreferenceConstUtils.app_home_background_data, str, UserManager.getInstance().getCurrentPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSkinCache() {
        try {
            return getContent(PreferenceConstUtils.app_home_background_data, UserManager.getInstance().getCurrentPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setFirstInstallData() {
        setContent("schemeFirstInstall", "0000", "0");
    }

    public boolean isFirstInstall() {
        return !"0000".equals(getContent("schemeFirstInstall", "0"));
    }

    public void setNoticeConfigData(String str) {
        setContent("notice_content_config", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setNoticeConfigDefault(String str) {
        setContent("notice_content_config", str, "0");
    }

    public String getNoticeConfigData() {
        String content = getContent("notice_content_config", UserManager.getInstance().getCurrentPhoneNumber());
        return TextUtils.isEmpty(content) ? getContent("notice_content_config", "0") : content;
    }

    public void setNoticeTopData(String str) {
        setContent("notice_content_top_data", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getNoticeTopData() {
        return getContent("notice_content_top_data", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setUserActData(String str) {
        setContent("user_activity_data", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getUserActData() {
        return getContent("user_activity_data", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setTongyicaijiJs(String str) {
        setContent("tongyicaiji_js_data", str, "0");
    }

    public String getTongyicaijiJs() {
        return getContent("tongyicaiji_js_data", "0");
    }

    public void setLoginConfigData(String str) {
        setContent("login_config_data_10", str, "0");
    }

    public String getLoginConfigData() {
        return getContent("login_config_data_10", "0");
    }

    public void setTongyicaijiConfigData(String str) {
        setContent("tongyicaiji_config_data_10", str, "0");
    }

    public String getTongyicaijiConfigData() {
        return getContent("tongyicaiji_config_data_10", "0");
    }

    public void setNewTopHeader(String str, String str2) {
        setContent("user_new_topheader", str, str2);
    }

    public String getNewTopHeader(String str) {
        return getContent("user_new_topheader", str);
    }

    public void setNewTopHeaderUserInfoData(String str, String str2) {
        setContent("user_new_topheader_userinfodata", str, str2);
    }

    public String getNewTopHeaderUserInfoData(String str) {
        return getContent("user_new_topheader_userinfodata", str);
    }

    public void setNewTopHeaderCardData(String str, String str2) {
        setContent("user_new_topheader_carddata", str, str2);
    }

    public String getNewTopHeaderCardData(String str) {
        return getContent("user_new_topheader_carddata", str);
    }

    public void setLoginMimaguanliData(String str, String str2) {
        setContent("login_setting_mimaguanli", str, str2);
    }

    public String getLoginMimaguanliData(String str) {
        return getContent("login_setting_mimaguanli", str);
    }

    public void saveServiceOrderData(String str) {
        setContent("service_order_data", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getServiceOrderData() {
        return getContent("service_order_data", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void saveFootPrintData(String str) {
        setContent("foot_print_data", str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getFootPrintData() {
        return getContent("foot_print_data", UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void setBaseRequestCache(String str, String str2) {
        setContent("base_request_" + str, str2, "0");
    }

    public String getBaseRequestCache(String str) {
        return getContent("base_request_" + str, "0");
    }
}
