package com.sinovatech.unicom.separatemodule.audience;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.AesJieMiUtils;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.entity.ActivityTimeEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionAnchorVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ComfortEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.HelpBtnInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.HuiFangEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LianMZBEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveBanner;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveBannerEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveOrFengGuangInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveRoomUiHideEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LuckyDrawResultEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.RecommendEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.TaskScoreInfo;
import com.sinovatech.unicom.separatemodule.audience.entity.UsefulChatEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoBannerEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoMoreConfigEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuangXiuEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.function.ActivityTimeFunction;
import com.sinovatech.unicom.separatemodule.audience.function.AnchorVideoWorksFunction;
import com.sinovatech.unicom.separatemodule.audience.function.AttentionAnchorsFunction;
import com.sinovatech.unicom.separatemodule.audience.function.InteractionAnchorsInfoFunction;
import com.sinovatech.unicom.separatemodule.audience.function.ListDataFunction;
import com.sinovatech.unicom.separatemodule.audience.function.LivePvInfoFunction;
import com.sinovatech.unicom.separatemodule.audience.function.LuckyDrawResultFunction;
import com.sinovatech.unicom.separatemodule.audience.function.MultiViewPlayBackVideoInfoFunction;
import com.sinovatech.unicom.separatemodule.audience.function.SharpnessFunction;
import com.sinovatech.unicom.separatemodule.audience.function.ShopDataFunction;
import com.sinovatech.unicom.separatemodule.audience.function.ShopListFunction;
import com.sinovatech.unicom.separatemodule.audience.function.SmallVideoItemFunction;
import com.sinovatech.unicom.separatemodule.audience.function.TheSameCityLiveFunction;
import com.sinovatech.unicom.separatemodule.audience.function.UsefulChatFunction;
import com.sinovatech.unicom.separatemodule.audience.function.VideoInfoItemFunction;
import com.sinovatech.unicom.separatemodule.audience.function.ZhuangxiuDataFunction;
import com.sinovatech.unicom.separatemodule.audience.function.ZhuboDataFunction;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import com.sinovatech.unicom.separatemodule.livepinglun.function.LiveCommentFunction;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerAudienceLoadData {
    private AppCompatActivity activityContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$addRenqi$12(String str) throws Exception {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$addShare$13(String str) throws Exception {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$loadGiftNum$7(String str) throws Exception {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$loadScore$8(String str) throws Exception {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$sendLiwu$9(String str) throws Exception {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$videoPraise$20(String str) throws Exception {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$zanVideo$21(String str) throws Exception {
        return str;
    }

    public ManagerAudienceLoadData(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public ObservableSubscribeProxy<String> toClientConfig() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("isLogin", LoginManager.getAccountTypeUser());
            hashMap.put("version", App.getInstance().getString(2131886969));
            hashMap.put("province", UserManager.getInstance().getCurrentProvinceCode());
            hashMap.put("city", UserManager.getInstance().getCurrentCityCode());
            ManagerLocation.LocationEntity locationEntity = ManagerLocation.getInstance().getLocationEntity();
            if (locationEntity != null && locationEntity.isGrant() && locationEntity.getBdLocation() != null) {
                hashMap.put("location", locationEntity.getBdLocation().getLongitude() + "," + locationEntity.getBdLocation().getLatitude());
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("蒙层引导", e.getMessage());
        }
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.toClientConfig(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$zRcsno1oSroQKcEsfyOP7etbcqs
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$toClientConfig$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$toClientConfig$0(String str) throws Exception {
        try {
            String optString = new JSONObject(str).optJSONObject("after").optString("jfyd");
            MsLogUtil.m7979d("蒙层引导", optString);
            return optString;
        } catch (JSONException e) {
            MsLogUtil.m7979d("蒙层引导", e.getMessage());
            return "0";
        }
    }

    public ObservableSubscribeProxy<AudienceDataEntity> loadListData(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", App.getInstance().getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getPrimaryListSweet(UserManager.getInstance().getLocateCityCode(), str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new ListDataFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<BaseVideoEntity<List<AttentionItemEntity>>> getAttentionAnchors(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getAttentionAnchors(str), new HashMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$_sPF6VN4WsuR_csRfj1_Fi4tKNg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getAttentionAnchors$1((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getAttentionAnchors$1(String str) throws Exception {
        JSONArray optJSONArray;
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("1");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                for (int i = 0; i < optJSONArray2.length(); i++) {
                    AttentionItemEntity attentionItemEntity = new AttentionItemEntity();
                    attentionItemEntity.setType(1);
                    ListDataEntity listDataEntity = new ListDataEntity();
                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i);
                    listDataEntity.setCoverImg(optJSONObject2.optString("coverImg"));
                    listDataEntity.setDataType("1");
                    listDataEntity.setJobNumber(optJSONObject2.optString("jobNumber"));
                    listDataEntity.setLivePvUrl(optJSONObject2.optString("livePvUrl"));
                    listDataEntity.setTypeCode(optJSONObject2.optString("typeCode"));
                    attentionItemEntity.setLiveData(listDataEntity);
                    arrayList.add(attentionItemEntity);
                }
            }
            JSONArray optJSONArray3 = optJSONObject.optJSONArray("2");
            if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray3.length(); i2++) {
                    AttentionItemEntity attentionItemEntity2 = new AttentionItemEntity();
                    attentionItemEntity2.setType(2);
                    ListDataEntity listDataEntity2 = new ListDataEntity();
                    JSONObject optJSONObject3 = optJSONArray3.optJSONObject(i2);
                    listDataEntity2.setCoverImg(optJSONObject3.optString("videoImg"));
                    listDataEntity2.setDataType("3");
                    listDataEntity2.setJobNumber(optJSONObject3.optJSONObject("userInfo").optString("userId"));
                    listDataEntity2.setLivePvUrl(optJSONObject3.optString("videoLink"));
                    listDataEntity2.setVideoId(optJSONObject3.optString("videoId"));
                    if ("Y".equals(optJSONObject3.optString("isShowGoods"))) {
                        ShopEntity.DataBean dataBean = new ShopEntity.DataBean();
                        dataBean.setHaveGoods("Y");
                        JSONArray optJSONArray4 = optJSONObject3.optJSONArray("goodsInfo");
                        if (optJSONArray4 != null && optJSONArray4.length() > 0) {
                            JSONObject optJSONObject4 = optJSONArray4.optJSONObject(0);
                            ShopEntity.DataBean.GoodsBean goodsBean = new ShopEntity.DataBean.GoodsBean();
                            goodsBean.setGoodsUrl(optJSONObject4.optString("goodsLink"));
                            goodsBean.setDesc(optJSONObject4.optString("goodsDesc"));
                            goodsBean.setId(optJSONObject4.optString("goodsId"));
                            goodsBean.setName(optJSONObject4.optString("goodsName"));
                            goodsBean.setPrice(optJSONObject4.optString("goodsPrice"));
                            goodsBean.setCoverImgUrl(optJSONObject4.optString("goodsImg"));
                            goodsBean.setOriginalPrice(optJSONObject4.optString("originalPrice"));
                            dataBean.setGoods(goodsBean);
                            attentionItemEntity2.setGoods(dataBean);
                            ArrayList arrayList2 = new ArrayList();
                            for (int i3 = 0; i3 < optJSONArray4.length(); i3++) {
                                JSONObject optJSONObject5 = optJSONArray4.optJSONObject(i3);
                                GoodListEntity goodListEntity = new GoodListEntity();
                                goodListEntity.setGoodsUrl(optJSONObject5.optString("goodsLink"));
                                goodListEntity.setDesc(optJSONObject5.optString("goodsDesc"));
                                goodListEntity.setId(optJSONObject5.optString("goodsId"));
                                goodListEntity.setName(optJSONObject5.optString("goodsName"));
                                goodListEntity.setPrice(optJSONObject5.optString("goodsPrice"));
                                goodListEntity.setCoverImgUrl(optJSONObject5.optString("goodsImg"));
                                goodListEntity.setOriginalPrice(optJSONObject5.optString("originalPrice"));
                                arrayList2.add(goodListEntity);
                            }
                            attentionItemEntity2.setShopList(arrayList2);
                        }
                    }
                    attentionItemEntity2.setLiveData(listDataEntity2);
                    attentionItemEntity2.setMoreViewAngle("Y".equals(optJSONObject3.optString("moreViewAngle")));
                    arrayList.add(attentionItemEntity2);
                }
            }
            JSONArray optJSONArray5 = optJSONObject.optJSONArray("3");
            if (optJSONArray5 != null && optJSONArray5.length() > 0) {
                for (int i4 = 0; i4 < optJSONArray5.length(); i4++) {
                    try {
                        AttentionItemEntity attentionItemEntity3 = new AttentionItemEntity();
                        attentionItemEntity3.setType(3);
                        JSONObject optJSONObject6 = optJSONArray5.optJSONObject(i4);
                        SmallVideoEntity.Data data = new SmallVideoEntity.Data();
                        data.setVideoId(optJSONObject6.optString("videoId"));
                        data.setVideoUrl(optJSONObject6.optString("videoLink"));
                        data.setTranscodeImg(optJSONObject6.optString("transcodeImg"));
                        data.setVideoImg(optJSONObject6.optString("videoImg"));
                        JSONObject optJSONObject7 = optJSONObject6.optJSONObject("userInfo");
                        if (optJSONObject7 != null) {
                            data.setUserImg(optJSONObject7.optString("userImg"));
                            data.setUserName(optJSONObject7.optString("userName"));
                            data.setUserId(optJSONObject7.optString("userId"));
                        }
                        data.setViewNum(optJSONObject6.optString("viewNum"));
                        data.setViewTitle(optJSONObject6.optString("videoTitle"));
                        data.setVideoTag(optJSONObject6.optString("videoTag"));
                        data.setVideoPraiseNum(optJSONObject6.optString("videoPraiseNum"));
                        data.setFocusOn(true);
                        data.setHasZan(false);
                        data.setIsShow(optJSONObject6.optString("isShow"));
                        data.setVideoType(Integer.parseInt(optJSONObject6.optString("videoType")));
                        data.setTjpara(optJSONObject6.optString("tjpara"));
                        data.setContentType("1");
                        data.setIsShowGoods(optJSONObject6.optString("isShowGoods"));
                        if ("Y".equals(data.getIsShowGoods()) && (optJSONArray = optJSONObject6.optJSONArray("goodsInfo")) != null && optJSONArray.length() > 0) {
                            ArrayList arrayList3 = new ArrayList();
                            for (int i5 = 0; i5 < optJSONArray.length(); i5++) {
                                GoodListEntity goodListEntity2 = new GoodListEntity();
                                JSONObject optJSONObject8 = optJSONArray.optJSONObject(i5);
                                goodListEntity2.setDesc(optJSONObject8.optString("goodsDesc"));
                                goodListEntity2.setCoverImgUrl(optJSONObject8.optString("goodsImg"));
                                goodListEntity2.setId(optJSONObject8.optString("goodsId"));
                                goodListEntity2.setName(optJSONObject8.optString("goodsName"));
                                goodListEntity2.setGoodsUrl(optJSONObject8.optString("goodsLink"));
                                goodListEntity2.setPrice(optJSONObject8.optString("goodsPrice"));
                                if (i5 == 0) {
                                    data.setGoodsNum(optJSONArray.length() + "");
                                    data.setGoodsId(optJSONObject8.optString("goodsId"));
                                    data.setGoodsName(optJSONObject8.optString("goodsName"));
                                    data.setGoodsDesc(optJSONObject8.optString("goodsDesc"));
                                    data.setGoodsImg(optJSONObject8.optString("goodsImg"));
                                    data.setGoodsLink(optJSONObject8.optString("goodsLink"));
                                    data.setGoodsPrice(optJSONObject8.optString("goodsPrice"));
                                }
                                arrayList3.add(goodListEntity2);
                            }
                            data.setGoodsData(arrayList3);
                        }
                        attentionItemEntity3.setSmallVideo(data);
                        arrayList.add(attentionItemEntity3);
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }
            }
            baseVideoEntity.setData(arrayList);
        }
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<ZhuboDataEntity> loadZhuboData(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        hashMap.put("liveChannel", str2);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getAudienceZhuboInfo(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new ZhuboDataFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<Map<String, String>> checkPassword(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", str);
        hashMap.put("password", str2);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getCheckPassword(str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$weEhnLO6TXrmHFHFRWVNr049mNs
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$checkPassword$2((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$checkPassword$2(String str) throws Exception {
        UIUtils.logD("checkPassword", str);
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        hashMap.put("statusCode", jSONObject.optString("statusCode"));
        hashMap.put("data", jSONObject.optString("data"));
        return hashMap;
    }

    public ObservableSubscribeProxy<Map<String, String>> verifyPwd(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.verifyPwd(str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$-csR5XpFjoU8FuZ0x26D4OjqAfg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$verifyPwd$3((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$verifyPwd$3(String str) throws Exception {
        UIUtils.logD("verifyPwd", str);
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        hashMap.put("statusCode", jSONObject.optString("code"));
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject != null) {
                hashMap.put("data", optJSONObject.optString("passwordCheckErrorNum"));
            } else {
                hashMap.put("data", "0");
            }
        } catch (Exception unused) {
            hashMap.put("data", "0");
        }
        hashMap.put("msg", jSONObject.optString("msg"));
        return hashMap;
    }

    public ObservableSubscribeProxy<String> consumeFreeTime(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.consumeFreeTime(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$URQvK2RfwqveAM6uJKXGL18uMrU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$consumeFreeTime$4((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$consumeFreeTime$4(String str) throws Exception {
        UIUtils.logD("test", str + "=======");
        return new JSONObject(str).optString("statusCode");
    }

    public ObservableSubscribeProxy<SharpnessEntity> getSharpnessInfo(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getSharpnessInfo(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new SharpnessFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<LivePvInfoEntity> getLivePvInfo(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getLivePvInfo(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new LivePvInfoFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<LivePvInfoEntity> reserveLivePv(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.reserveLivePv(str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new LivePvInfoFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<ZhuangXiuEntity> loadZhuuangXiuData(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getAudienceZhuangXiu(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new ZhuangxiuDataFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<ShopEntity> loadShopData(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getAudienceShop(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new ShopDataFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<ShopEntity> loadShopData(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("recommendPage", str2);
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getAudienceShop(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new ShopDataFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<List<GoodListEntity>> loadShopList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getAudienceShopList(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new ShopListFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<List<GiftEntity>> loadGiftList() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGiftList(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$ff0yXtGi6UpRhQET4HN8usTm77k
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$loadGiftList$5((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$loadGiftList$5(String str) throws Exception {
        CacheDataCenter.getInstance().setGiftListString(str);
        return CacheDataCenter.getInstance().getGiftList();
    }

    public ObservableSubscribeProxy<List<GiftEntity>> getGiftListNew() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGiftListNew(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$oq201jCvBsJDehzPj8389_20cjI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getGiftListNew$6((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$getGiftListNew$6(String str) throws Exception {
        CacheDataCenter.getInstance().setGiftListString(str);
        return CacheDataCenter.getInstance().getGiftList();
    }

    public ObservableSubscribeProxy<String> loadGiftNum() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getGifNum(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$f11JWQGr32jXiI5_sWQPP1jnNVo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$loadGiftNum$7((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> loadScore() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getUserScore(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$0Wilefx0AlhweeK6qGuchXRWtf8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$loadScore$8((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> sendLiwu(ZhuboDataEntity.AnchorInfoBean anchorInfoBean, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.sendAudienceGift(anchorInfoBean.getUserId(), anchorInfoBean.getLiveRoom(), str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$pY35m88DXvKn5wdOicpSLrRs1O0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$sendLiwu$9((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> guanzhu(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.addGaunzhu(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$881Y4duw19EubP8lgapMzb-aFws
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$guanzhu$10((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$guanzhu$10(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        return "0000".equals(optString) ? optString : jSONObject.optString("message");
    }

    public ObservableSubscribeProxy<Boolean> isGuanzhu(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.isGuanzhuUrl(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$FfjjC9Y1d2bw0bq3BrrJ53_8_24
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$isGuanzhu$11((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$isGuanzhu$11(String str) throws Exception {
        String optString = new JSONObject(str).optString("statusCode");
        return Boolean.valueOf("3001".equals(optString) || "0000".equals(optString));
    }

    public ObservableSubscribeProxy<LiveCommentEntity> getCommentList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        hashMap.put("pageSize", "50");
        hashMap.put("pageNum", "1");
        hashMap.put("reqChannel", "sVideo");
        hashMap.put("belongPro", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("belongCity", UserManager.getInstance().getUserAreaid());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getPinglunCommentList(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new LiveCommentFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> addRenqi(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        UIUtils.logD("addRenqi", URLSet.addRenqi(str) + "---");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.addRenqi(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$6YdExYHLVyBN1_76pG9nN3hztRo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$addRenqi$12((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> addShare(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getZhiboRizhiUrl(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$in1ngizTsWQnwgUJzFNsWG19C3o
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$addShare$13((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<Boolean> addPriser(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put("flag", str3);
        }
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.addpraiseUser(str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$0AKY4i1jTwiyGRpNAfWop0PaMqo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$addPriser$14((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$addPriser$14(String str) throws Exception {
        return true;
    }

    public ObservableSubscribeProxy<Boolean> addPriser(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.addpraiseUser(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$GK5rkrv5sHwQ66MprN4fDLfuiRw
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$addPriser$15((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$addPriser$15(String str) throws Exception {
        return true;
    }

    public ObservableSubscribeProxy<Boolean> goodsLog(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        UIUtils.logD("ObservableSubscribeProxy", URLSet.goodslog(str, str2, str3));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.goodslog(str, str2, str3), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$PR7lLmnRcCIfPTTcw7G3apMbFt0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$goodsLog$16((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$goodsLog$16(String str) throws Exception {
        return true;
    }

    public ObservableSubscribeProxy<ActivityTimeEntity> queryActivityTime(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        hashMap.put("anchorName", str2);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getActivityTime(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new ActivityTimeFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<LuckyDrawResultEntity> getLuckyDrawResult(String str, int i, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("acId", str);
        hashMap.put("acType", "" + i);
        hashMap.put("anchorId", str2);
        hashMap.put("anchorName", str3);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getLuckyDrawResult(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new LuckyDrawResultFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public void getKaduanLog(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        if (!TextUtils.isEmpty(str5)) {
            hashMap.put("flag", str5);
        }
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getAudienceKadunUrl(str, str2, str3, str4), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str6) {
                UIUtils.logD("getKaduanLog", "" + str6);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                UIUtils.logD("getKaduanLog", "" + th.getMessage());
            }
        });
    }

    public ObservableSubscribeProxy<UsefulChatEntity> getUsefulChatList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getUsefulChatList(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new UsefulChatFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<LianMZBEntity> getInteractionAnchorsInfo(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getInteractionAnchorsInfo(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new InteractionAnchorsInfoFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public void statisticalTime(String str, String str2) {
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.statisticalTime(str, str2), new HashMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$IOUw3pARluOaPJprLPFH3d8GWqg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("lln", "统计小视频时长-->" + ((String) obj));
            }
        });
    }

    public ObservableSubscribeProxy<SmallVideoEntity> getVideos(final String str, final String str2) {
        MsLogUtil.m7980d("singleVideoId=" + str2);
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        if (!TextUtils.isEmpty(str2) && "1".equals(str)) {
            hashMap.put("videoId", str2);
        }
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getVideos(str, "nogps", UserManager.getInstance().getCurrentCityCode()), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new SmallVideoItemFunction()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$llfLmy5atkd7ahfiz2MF9QG9XQs
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getVideos$18(str, str2, (SmallVideoEntity) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SmallVideoEntity lambda$getVideos$18(String str, String str2, SmallVideoEntity smallVideoEntity) throws Exception {
        try {
            if (!"1".equals(str) && !TextUtils.isEmpty(str2)) {
                Iterator<SmallVideoEntity.Data> it = smallVideoEntity.getData().iterator();
                while (it.hasNext()) {
                    if (str2.equals(it.next().getVideoId())) {
                        it.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return smallVideoEntity;
    }

    public ObservableSubscribeProxy<SmallVideoInfoEntity> getVideoInfo(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getVideoInfo(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new VideoInfoItemFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<SmallVideoEntity> searchVideos(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("searchWorld", str);
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.searchVideos(UserManager.getInstance().getCurrentCityCode(), str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new SmallVideoItemFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<Map<String, String>> getVideoZan(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getVideoZan(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$jB2iyGR3nhUyAlF9VBo5Npj-6aI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getVideoZan$19((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getVideoZan$19(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        HashMap hashMap = new HashMap();
        if ("0000".equals(optString)) {
            hashMap.put("thumbsUp", jSONObject.optJSONObject("data").optString("thumbsUp"));
            hashMap.put("thumbsNum", jSONObject.optJSONObject("data").optString("thumbsNum"));
        }
        return hashMap;
    }

    public ObservableSubscribeProxy<String> videoPraise(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.videoPraise(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$X7pAAAr8Wp7TyD_s9-8oA44jyqU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$videoPraise$20((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> zanVideo(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.zanVideo(str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$JOPSxNlFh5R1ItJlvnvzUgymikA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$zanVideo$21((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<Map<String, String>> setVideoRing(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.setVideoRing(str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$DhyNX59VD37GHwxOKvfk12ytWts
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$setVideoRing$22((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$setVideoRing$22(String str) throws Exception {
        HashMap hashMap = new HashMap();
        JSONObject jSONObject = new JSONObject(str);
        hashMap.put("statusCode", jSONObject.optString("statusCode"));
        hashMap.put("message", jSONObject.optString("message"));
        return hashMap;
    }

    public ObservableSubscribeProxy<Map<String, String>> getRingSetting(String str, String str2, String str3) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(5, 5, 5).rxPost(URLSet.getRingSetting(str2, str, str3), "").subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$GAZvKMJugjESj4DDZKeB10qw9sk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getRingSetting$23((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getRingSetting$23(String str) throws Exception {
        MsLogUtil.m7979d("getRingSetting", str);
        HashMap hashMap = new HashMap();
        JSONObject jSONObject = new JSONObject(str);
        hashMap.put("statusCode", jSONObject.optString("statusCode"));
        hashMap.put("message", jSONObject.optString("message"));
        hashMap.put("data", jSONObject.optString("data"));
        return hashMap;
    }

    public ObservableSubscribeProxy<String> getVideoRingCode() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getVideoRingCode(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$ZEpcKIr83DaY1IuCcKb7Q2ZV9q8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String optString;
                optString = new JSONObject((String) obj).optString("data");
                return optString;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<BaseVideoEntity<List<VideoMoreConfigEntity>>> getVideoRingRule(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("channelCode", str);
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getVideoRingRule(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$bIDcATjHQTREPcbmfE_-R-zwew4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getVideoRingRule$25((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getVideoRingRule$25(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                VideoMoreConfigEntity videoMoreConfigEntity = new VideoMoreConfigEntity();
                videoMoreConfigEntity.setConfIcon(optJSONObject.optString("confIcon"));
                videoMoreConfigEntity.setConfName(optJSONObject.optString("confName"));
                videoMoreConfigEntity.setConfUrl(optJSONObject.optString("confUrl"));
                arrayList.add(videoMoreConfigEntity);
            }
            baseVideoEntity.setData(arrayList);
        }
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<List<VideoRingExplainEntity.DataEntity>>> getOderRingExplain(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoId", str2);
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getOderRingExplain(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$A3GHggOMCFBpyIQidjBJjA9K5qA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getOderRingExplain$26((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getOderRingExplain$26(String str) throws Exception {
        MsLogUtil.m7980d("getOderRingExplain" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                VideoRingExplainEntity.DataEntity dataEntity = new VideoRingExplainEntity.DataEntity();
                dataEntity.setDuration(optJSONObject.optString("duration"));
                dataEntity.setPrice(optJSONObject.optString("price"));
                dataEntity.setSuccessTips(optJSONObject.optString("successTips"));
                dataEntity.setRecommendedStatus(optJSONObject.optString("recommendedStatus"));
                dataEntity.setProductName(optJSONObject.optString("productName"));
                dataEntity.setAgreementName2(optJSONObject.optString("agreementName2"));
                dataEntity.setAgreementContent2(optJSONObject.optString("agreementContent2"));
                dataEntity.setConfirmTips(optJSONObject.optString("confirmTips"));
                dataEntity.setUnsubscribeMethod(optJSONObject.optString("unsubscribeMethod"));
                dataEntity.setAgreementName1(optJSONObject.optString("agreementName1"));
                dataEntity.setAgreementContent1(optJSONObject.optString("agreementContent1"));
                dataEntity.setId(optJSONObject.optString("id"));
                dataEntity.setType(optJSONObject.optString("pkgId"));
                dataEntity.setSelected(false);
                arrayList.add(dataEntity);
            }
            baseVideoEntity.setData(arrayList);
        }
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<String> focusOnAnchor(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoId", str2);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.focusOnAnchor(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<LiveBanner> getBannerList() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getBannerList(UserManager.getInstance().getLocateCityCode()), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$uigJjXiQ9mW_je89dPiXrRXo9ug
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getBannerList$27((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LiveBanner lambda$getBannerList$27(String str) throws Exception {
        LiveBanner liveBanner = new LiveBanner();
        JSONObject jSONObject = new JSONObject(str);
        liveBanner.setMessage(jSONObject.optString("message"));
        liveBanner.setStatusCode(jSONObject.optString("statusCode"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(optString);
            for (int i = 0; i < jSONArray.length(); i++) {
                LiveBannerEntity liveBannerEntity = new LiveBannerEntity();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                liveBannerEntity.setId(optJSONObject.optString("id"));
                liveBannerEntity.setTitle(optJSONObject.optString("title"));
                liveBannerEntity.setImgSrc(optJSONObject.optString("imgSrc"));
                liveBannerEntity.setLinkUrl(optJSONObject.optString("linkUrl"));
                liveBannerEntity.setOpenValue(optJSONObject.optString("openValue"));
                liveBannerEntity.setOpenType(optJSONObject.optString("openType"));
                liveBannerEntity.setSequenceId(optJSONObject.optString("sequenceId"));
                liveBannerEntity.setSearchKeyword(optJSONObject.optString("searchKeyword"));
                liveBannerEntity.setCreateRole(optJSONObject.optString("createRole"));
                liveBannerEntity.setPlaceId(optJSONObject.optString("placeId"));
                arrayList.add(liveBannerEntity);
            }
            liveBanner.setData(arrayList);
        }
        return liveBanner;
    }

    public ObservableSubscribeProxy<HuiFangEntity> getOldLiveList(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getListOldLive(str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$Bx21JM4hXhAq7jfSkGeKMgFgmTg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getOldLiveList$28((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ HuiFangEntity lambda$getOldLiveList$28(String str) throws Exception {
        HuiFangEntity huiFangEntity = new HuiFangEntity();
        JSONObject jSONObject = new JSONObject(str);
        huiFangEntity.setMessage(jSONObject.optString("message"));
        huiFangEntity.setStatusCode(jSONObject.optString("statusCode"));
        huiFangEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            JSONArray jSONArray = new JSONArray(optString);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                HuiFangEntity.HuiFangItem huiFangItem = new HuiFangEntity.HuiFangItem();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                huiFangItem.setVideoImg(optJSONObject.optString("videoImg"));
                huiFangItem.setVideoTitle(optJSONObject.optString("videoTitle"));
                huiFangItem.setVideoId(optJSONObject.optString("videoId"));
                huiFangItem.setVideoLink(optJSONObject.optString("videoLink"));
                huiFangItem.setViewNum(optJSONObject.optString("viewNum"));
                huiFangItem.setVideoPraiseNum(optJSONObject.optString("videoPraiseNum"));
                huiFangItem.setReleaseTime(optJSONObject.optString("releaseTime"));
                huiFangItem.setVideoLiveRound(optJSONObject.optString("videoLiveRound"));
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("userInfo");
                huiFangItem.setUserId(optJSONObject2.optString("userId"));
                huiFangItem.setUserName(optJSONObject2.optString("userName"));
                huiFangItem.setUserImg(optJSONObject2.optString("userImg"));
                arrayList.add(huiFangItem);
            }
            huiFangEntity.setData(arrayList);
        }
        return huiFangEntity;
    }

    public ObservableSubscribeProxy<LiveOrFengGuangInfoEntity> getAllLiveList(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getHuiFangList(str, str2, UserManager.getInstance().getLocateCityCode()), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$GurSLI1DdZm-rL_zoY6iLacTDcM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getAllLiveList$29((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LiveOrFengGuangInfoEntity lambda$getAllLiveList$29(String str) throws Exception {
        LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity = new LiveOrFengGuangInfoEntity();
        JSONObject jSONObject = new JSONObject(str);
        liveOrFengGuangInfoEntity.setMessage(jSONObject.optString("message"));
        liveOrFengGuangInfoEntity.setStatusCode(jSONObject.optString("statusCode"));
        liveOrFengGuangInfoEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            JSONArray jSONArray = new JSONArray(optString);
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem = new LiveOrFengGuangInfoEntity.LiveInfoItem();
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                liveInfoItem.setVideoBgImg(optJSONObject.optString("videoImg"));
                liveInfoItem.setMoreViewAngle(optJSONObject.optString("moreViewAngle"));
                liveInfoItem.setVideoTitle(optJSONObject.optString("videoTitle"));
                liveInfoItem.setVideoId(optJSONObject.optString("videoId"));
                liveInfoItem.setVideoLinkUrl(optJSONObject.optString("videoLink"));
                liveInfoItem.setVideoNum(optJSONObject.optString("videoPraiseNum"));
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("userInfo");
                liveInfoItem.setUserId(optJSONObject2.optString("userId"));
                liveInfoItem.setUserNickName(optJSONObject2.optString("userName"));
                liveInfoItem.setUserHeadImg(optJSONObject2.optString("userImg"));
                liveInfoItem.setUserLinkUrl(optJSONObject2.optString("userIndexUrl"));
                arrayList.add(liveInfoItem);
            }
            liveOrFengGuangInfoEntity.setData(arrayList);
        }
        return liveOrFengGuangInfoEntity;
    }

    public ObservableSubscribeProxy<AudienceDataEntity> getSlowLiveList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getLivingOrFengGuangList("6", str, UserManager.getInstance().getLocateCityCode()), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$UKdRI83D097KcbvF3gzTxhBlkjo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getSlowLiveList$30((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AudienceDataEntity lambda$getSlowLiveList$30(String str) throws Exception {
        JSONArray optJSONArray;
        AudienceDataEntity audienceDataEntity = new AudienceDataEntity();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        audienceDataEntity.setStatusCode(optString);
        audienceDataEntity.setMessage(jSONObject.optString("message"));
        audienceDataEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        if ("0000".equals(optString) && (optJSONArray = jSONObject.optJSONArray("data")) != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                ListDataEntity listDataEntity = new ListDataEntity();
                listDataEntity.setCoverImg(optJSONObject.optString("coverImg"));
                listDataEntity.setCoverImg("1");
                listDataEntity.setJobNumber(optJSONObject.optString("jobNumber"));
                listDataEntity.setLivePullUrl(optJSONObject.optString("livePullUrl"));
                listDataEntity.setLivePvUrl(optJSONObject.optString("livePvUrl"));
                listDataEntity.setTypeCode(optJSONObject.optString("typeCode"));
                arrayList.add(listDataEntity);
            }
        }
        audienceDataEntity.setList(arrayList);
        return audienceDataEntity;
    }

    public ObservableSubscribeProxy<LiveOrFengGuangInfoEntity> getLivingList(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getLivingOrFengGuangList(str, str2, UserManager.getInstance().getLocateCityCode()), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$Nbmj62qxD7yv0pYMKZ0OAQ9gsec
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getLivingList$31((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LiveOrFengGuangInfoEntity lambda$getLivingList$31(String str) throws Exception {
        LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity = new LiveOrFengGuangInfoEntity();
        JSONObject jSONObject = new JSONObject(str);
        liveOrFengGuangInfoEntity.setMessage(jSONObject.optString("message"));
        liveOrFengGuangInfoEntity.setStatusCode(jSONObject.optString("statusCode"));
        liveOrFengGuangInfoEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(optString);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem = new LiveOrFengGuangInfoEntity.LiveInfoItem();
                liveInfoItem.setVideoTitle(optJSONObject.optString("desc"));
                liveInfoItem.setUserNickName(optJSONObject.optString("nickName"));
                liveInfoItem.setMoreViewAngle(optJSONObject.optString("moreViewAngle"));
                liveInfoItem.setUserHeadImg(optJSONObject.optString("headImg"));
                liveInfoItem.setVideoTime(optJSONObject.optString("prevueTime"));
                String optString2 = optJSONObject.optString("liveMoreImg");
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = optJSONObject.optString("coverImg");
                }
                liveInfoItem.setVideoBgImg(optString2);
                liveInfoItem.setUserId(optJSONObject.optString("jobNumber"));
                liveInfoItem.setUserLinkUrl(optJSONObject.optString("userIndexUrl"));
                liveInfoItem.setVideoNum(optJSONObject.optString("liveRoundPraise"));
                liveInfoItem.setVideoLinkUrl(optJSONObject.optString("liveUrl"));
                liveInfoItem.setVideoCloseState(optJSONObject.optString("closeState"));
                arrayList.add(liveInfoItem);
            }
            liveOrFengGuangInfoEntity.setData(arrayList);
        }
        return liveOrFengGuangInfoEntity;
    }

    public ObservableSubscribeProxy<LiveOrFengGuangInfoEntity> getYuGaoList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getYuGaoList(str, UserManager.getInstance().getLocateCityCode()), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$xFIeKU57_f3hNKeijAyplovjmP0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getYuGaoList$32((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ LiveOrFengGuangInfoEntity lambda$getYuGaoList$32(String str) throws Exception {
        LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity = new LiveOrFengGuangInfoEntity();
        JSONObject jSONObject = new JSONObject(str);
        liveOrFengGuangInfoEntity.setMessage(jSONObject.optString("message"));
        liveOrFengGuangInfoEntity.setStatusCode(jSONObject.optString("statusCode"));
        liveOrFengGuangInfoEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(optString);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem = new LiveOrFengGuangInfoEntity.LiveInfoItem();
                liveInfoItem.setVideoTitle(optJSONObject.optString("desc"));
                liveInfoItem.setUserNickName(optJSONObject.optString("nickName"));
                liveInfoItem.setUserHeadImg(optJSONObject.optString("headImg"));
                liveInfoItem.setUserLinkUrl(optJSONObject.optString("userIndexUrl"));
                liveInfoItem.setVideoTime(optJSONObject.optString("prevueTime"));
                liveInfoItem.setVideoBgImg(optJSONObject.optString("prevueImg"));
                liveInfoItem.setUserId(optJSONObject.optString("jobNumber"));
                liveInfoItem.setVideoNum(optJSONObject.optString("liveRoundPraise"));
                liveInfoItem.setVideoCloseState(optJSONObject.optString("reservedState"));
                liveInfoItem.setVideoLinkUrl(optJSONObject.optString("liveUrl"));
                arrayList.add(liveInfoItem);
            }
            liveOrFengGuangInfoEntity.setData(arrayList);
        }
        return liveOrFengGuangInfoEntity;
    }

    public ObservableSubscribeProxy<Map<String, String>> videoTimeLog(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        StringBuffer stringBuffer = new StringBuffer("{\"videoId\":\"");
        stringBuffer.append(str);
        stringBuffer.append("\",\"startTime\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\",\"videoTime\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"slotTime\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",\"playedTime\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\",\"playType\":\"");
        stringBuffer.append(str6);
        stringBuffer.append("\",\"tjpara\":\"");
        stringBuffer.append(str7);
        stringBuffer.append("\",\"visitorId\":\"");
        stringBuffer.append(AesJieMiUtils.encrypt(UserManager.getInstance().getCurrentPhoneNumber()));
        stringBuffer.append("\",\"version\":\"");
        stringBuffer.append(this.activityContext.getString(2131886969));
        stringBuffer.append("\"}");
        String stringBuffer2 = stringBuffer.toString();
        UIUtils.logD("playLog", "参数列表：" + stringBuffer2);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.upLoadVideoLog(), stringBuffer2).subscribeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$LTXXuvJlgXGi-7XB6H8qkpcuK8M
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$videoTimeLog$33((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$videoTimeLog$33(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        hashMap.put("statusCode", jSONObject.optString("statusCode"));
        hashMap.put("message", jSONObject.optString("message"));
        return hashMap;
    }

    public ObservableSubscribeProxy<String> hiBoardLog(String str, String str2) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.hiBoardLog(str, str2), null).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<BaseVideoEntity<List<VideoBannerEntity>>> getVideoBannerList() {
        String videoBannerList = URLSet.getVideoBannerList("8");
        UIUtils.logD("xcyTest", "bannerUrl-->" + videoBannerList);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 10, 10).rxGet(videoBannerList, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$o5SYtLDjwaovGAFNbpnZBAFwkSg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getVideoBannerList$34((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getVideoBannerList$34(String str) throws Exception {
        UIUtils.logD("xcyTest", "banner数据-->" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(optString);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                VideoBannerEntity videoBannerEntity = new VideoBannerEntity();
                videoBannerEntity.setId(optJSONObject.optString("id"));
                videoBannerEntity.setName(optJSONObject.optString("name"));
                videoBannerEntity.setType(optJSONObject.optString("type"));
                videoBannerEntity.setImg(optJSONObject.optString("img"));
                videoBannerEntity.setData(optJSONObject.optString("data"));
                arrayList.add(videoBannerEntity);
            }
            baseVideoEntity.setData(arrayList);
        }
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<String> hiBoardLogFailed(Map<String, String> map) {
        AsyncHttpClient asyncHttpClient = App.getAsyncHttpClient();
        String hiBoardLogFailed = URLSet.hiBoardLogFailed();
        Gson gson = new Gson();
        return (ObservableSubscribeProxy) asyncHttpClient.rxPost(hiBoardLogFailed, !(gson instanceof Gson) ? gson.toJson(map) : NBSGsonInstrumentation.toJson(gson, map)).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<Map<String, String>> videoRingTimeLog(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        StringBuffer stringBuffer = new StringBuffer("{\"videoId\":\"");
        stringBuffer.append(str);
        stringBuffer.append("\",\"startTime\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\",\"videoTime\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"slotTime\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",\"playedTime\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\",\"tjpara\":\"");
        stringBuffer.append(str7);
        stringBuffer.append("\",\"visitorId\":\"");
        stringBuffer.append(AesJieMiUtils.encrypt(UserManager.getInstance().getCurrentPhoneNumber()));
        stringBuffer.append("\",\"version\":\"");
        stringBuffer.append(this.activityContext.getString(2131886969));
        stringBuffer.append("\",\"playType\":\"");
        stringBuffer.append(str6);
        stringBuffer.append("\"}");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.upLoadVideoRingLog(), stringBuffer.toString()).subscribeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$vd8SEot1s88Ddpmupfai2nAtSzA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$videoRingTimeLog$35((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$videoRingTimeLog$35(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        hashMap.put("statusCode", jSONObject.optString("statusCode"));
        hashMap.put("message", jSONObject.optString("message"));
        return hashMap;
    }

    public ObservableSubscribeProxy<String> uploadVideoErrorLog(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("videoId", str);
        hashMap.put("videoUrl", str2);
        hashMap.put("code", str3);
        hashMap.put("msg", str4);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.uploadVideoErrorLog(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> setLogPoit(Map<String, String> map) {
        Gson gson = new Gson();
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.setLogPoit(), !(gson instanceof Gson) ? gson.toJson(map) : NBSGsonInstrumentation.toJson(gson, map)).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> setVideoNum(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.setVideoNum(str), "").subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<Map<String, String>> getVideoNum(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getVideoNum(str), "").subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$yiSJ_wc_cUdvRuGm1XrEPaECSSU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getVideoNum$36((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getVideoNum$36(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        hashMap.put("statusCode", jSONObject.optString("statusCode"));
        hashMap.put("message", jSONObject.optString("message"));
        hashMap.put("data", jSONObject.optString("data"));
        return hashMap;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<List<ComfortEntity>>> getComFort() {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getComFort(), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$lpTjoWVwZip6pwqYXksURBZryIU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getComFort$37((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getComFort$37(String str) throws Exception {
        UIUtils.logD("xcyTest", "景区舒适度接口" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("code"));
        baseVideoEntity.setMessage(jSONObject.optString("msg"));
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        ArrayList arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                ComfortEntity comfortEntity = new ComfortEntity();
                comfortEntity.setZoneName(optJSONObject.optString("zoneName"));
                comfortEntity.setName(optJSONObject.optString("name"));
                comfortEntity.setComfortName(optJSONObject.optString("comfortName"));
                arrayList.add(comfortEntity);
            }
            baseVideoEntity.setData(arrayList);
        }
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<HelpBtnInfoEntity>> getHelpBtnInfo() {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getHelpBtnInfo(), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$ixHp-E4CpRkJMeig6Xn3QzYRdLs
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getHelpBtnInfo$38((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getHelpBtnInfo$38(String str) throws Exception {
        UIUtils.logD("xcyTest", "帮助键位接口==>" + str);
        JSONObject jSONObject = new JSONObject(str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        HelpBtnInfoEntity helpBtnInfoEntity = new HelpBtnInfoEntity();
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        helpBtnInfoEntity.setHelpUrl(optJSONObject.optString("configUrl"));
        helpBtnInfoEntity.setShowHelpBtn(TextUtils.equals("1", optJSONObject.optString("isShow")));
        baseVideoEntity.setData(helpBtnInfoEntity);
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<SmallVideoEntity> getVideoList(String str, String str2) {
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getVideoList(str, str2, UserManager.getInstance().getCurrentCityCode(), "0") + "?userAccount=" + AesJieMiUtils.encrypt(currentPhoneNumber) + ("&version=" + this.activityContext.getString(2131886969)), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new SmallVideoItemFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> getWelfareUrl(String str) {
        String welfareUrl = URLSet.getWelfareUrl(str);
        HashMap hashMap = new HashMap();
        hashMap.put("RSS", "ANDROID");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(welfareUrl, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$qpnrolj5jFobzIH0VyFgAX0B7VI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getWelfareUrl$39((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getWelfareUrl$39(String str) throws Exception {
        UIUtils.logD("xcyTest", "福利中心入口==>" + str);
        JSONObject jSONObject = new JSONObject(str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        new HelpBtnInfoEntity();
        baseVideoEntity.setData(jSONObject.optString("data"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> getShareCode() {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getShareCode(), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$G5G9tCzbzslF-pKs0W9WHJpCnP4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getShareCode$40((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getShareCode$40(String str) throws Exception {
        UIUtils.logD("xcyTest", "获取引流人编码==>" + str);
        JSONObject jSONObject = new JSONObject(str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        new HelpBtnInfoEntity();
        baseVideoEntity.setData(jSONObject.optString("data"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<AudienceDataEntity> getAngleMoreLiveList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        String listAngleMoreLive = URLSet.listAngleMoreLive(UserManager.getInstance().getLocateCityCode(), str);
        UIUtils.logD("多视角测试", "多视角列表：" + listAngleMoreLive);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(listAngleMoreLive, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$eb9MRMUih_5xeYs6nWTPfuQu9a0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getAngleMoreLiveList$41((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AudienceDataEntity lambda$getAngleMoreLiveList$41(String str) throws Exception {
        JSONArray optJSONArray;
        UIUtils.logD("多视角测试", "多视角列表：" + str);
        AudienceDataEntity audienceDataEntity = new AudienceDataEntity();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        audienceDataEntity.setStatusCode(optString);
        audienceDataEntity.setMessage(jSONObject.optString("message"));
        audienceDataEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        if ("0000".equals(optString) && (optJSONArray = jSONObject.optJSONArray("data")) != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                Gson gson = new Gson();
                String jSONObject2 = !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject);
                arrayList.add((ListDataEntity) (!(gson instanceof Gson) ? gson.fromJson(jSONObject2, (Class<Object>) ListDataEntity.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) ListDataEntity.class)));
            }
        }
        audienceDataEntity.setList(arrayList);
        return audienceDataEntity;
    }

    public ObservableSubscribeProxy<AudienceDataEntity> getAngleMorePlayBackList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", App.getInstance().getString(2131886969));
        String listAngleMorePlayback = URLSet.listAngleMorePlayback(UserManager.getInstance().getLocateCityCode(), str);
        UIUtils.logD("多视角测试", "多视角回放请求：" + listAngleMorePlayback);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(listAngleMorePlayback, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$9HRcTGEf_jpW288NLqoC71xH2Ik
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getAngleMorePlayBackList$42((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ AudienceDataEntity lambda$getAngleMorePlayBackList$42(String str) throws Exception {
        JSONArray optJSONArray;
        UIUtils.logD("多视角测试", "多视角回放列表：" + str);
        AudienceDataEntity audienceDataEntity = new AudienceDataEntity();
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        audienceDataEntity.setStatusCode(optString);
        audienceDataEntity.setMessage(jSONObject.optString("message"));
        audienceDataEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        if ("0000".equals(optString) && (optJSONArray = jSONObject.optJSONArray("data")) != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                Gson gson = new Gson();
                String jSONObject2 = !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject);
                ListDataEntity listDataEntity = (ListDataEntity) (!(gson instanceof Gson) ? gson.fromJson(jSONObject2, (Class<Object>) ListDataEntity.class) : NBSGsonInstrumentation.fromJson(gson, jSONObject2, (Class<Object>) ListDataEntity.class));
                listDataEntity.setCoverImg(listDataEntity.getViewAngleCover());
                listDataEntity.setDataType("3");
                listDataEntity.setLivePvUrl(listDataEntity.getViewAngleUrl());
                arrayList.add(listDataEntity);
            }
        }
        audienceDataEntity.setList(arrayList);
        return audienceDataEntity;
    }

    public ObservableSubscribeProxy<SharpnessEntity> getAngleMoreVideoInfo(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.angleMoreVideoInfo(str), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new MultiViewPlayBackVideoInfoFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<SharpnessEntity> getAngleMoreVideoInfo(String str, String str2) {
        String angleMoreVideoInfo = URLSet.angleMoreVideoInfo(str);
        HashMap hashMap = new HashMap();
        hashMap.put("version", App.getInstance().getString(2131886969));
        hashMap.put("flag", str2);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(angleMoreVideoInfo, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new MultiViewPlayBackVideoInfoFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<BaseVideoEntity<LiveRoomUiHideEntity>> getLiveRoomUIHide(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getLiveRoomUIHide(str), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$NFS-zsnhjX2SH8QJMtDNUYfSodk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getLiveRoomUIHide$43((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getLiveRoomUIHide$43(String str) throws Exception {
        UIUtils.logD("liveRoomUI", str);
        JSONObject jSONObject = new JSONObject(str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        LiveRoomUiHideEntity liveRoomUiHideEntity = new LiveRoomUiHideEntity();
        if (optJSONObject != null) {
            liveRoomUiHideEntity.setIsShowChat(optJSONObject.optString("isShowChat"));
            liveRoomUiHideEntity.setIsShowGift(optJSONObject.optString("isShowGift"));
        }
        baseVideoEntity.setData(liveRoomUiHideEntity);
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<RecommendEntity>> getFloatWindow() {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getFloatWindow(), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$uA_9TLjKXUgL7RZxh_yveYKL4ks
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getFloatWindow$44((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getFloatWindow$44(String str) throws Exception {
        UIUtils.logD("xcyTest", "负一屏悬浮窗：" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        RecommendEntity recommendEntity = new RecommendEntity();
        recommendEntity.setActivityImg(optJSONObject.getString("activityImg"));
        recommendEntity.setActivityJumpUrl(optJSONObject.getString("activityJumpUrl"));
        baseVideoEntity.setData(recommendEntity);
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<RecommendEntity>> getPopupWindow() {
        String popupWindow = URLSet.getPopupWindow();
        Log.d("getPopupWindow", "getPopupWindow: " + popupWindow);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(popupWindow, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$ewNlxRr7ln22Q8hnBG-SgFeMShk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getPopupWindow$45((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getPopupWindow$45(String str) throws Exception {
        UIUtils.logD("xcyTest", "负一屏弹窗 接口数据" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        RecommendEntity recommendEntity = new RecommendEntity();
        recommendEntity.setActivityImg(optJSONObject.optString("activityImg"));
        recommendEntity.setActivityJumpUrl(optJSONObject.optString("activityJumpUrl"));
        baseVideoEntity.setData(recommendEntity);
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> isShowPopuWindow() {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.isShowPopuWindow(), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$4F48krJ7qDAgczq6zYvH_kLOI5U
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$isShowPopuWindow$46((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$isShowPopuWindow$46(String str) throws Exception {
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setData(jSONObject.optString("data"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> taskVideoThumbsUp(String str, String str2) {
        String taskVideoThumbsUp = URLSet.taskVideoThumbsUp(str, str2);
        HashMap hashMap = new HashMap();
        hashMap.put("RSS", "ANDROID");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(taskVideoThumbsUp, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$8Jlohhi2jb7A2720ZlAia7fVaxc
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$taskVideoThumbsUp$47((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$taskVideoThumbsUp$47(String str) throws Exception {
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setData(jSONObject.optString("data"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> taskWatch30s(String str, String str2) {
        String taskWatch30s = URLSet.taskWatch30s(str, str2);
        HashMap hashMap = new HashMap();
        hashMap.put("sign", AesJieMiUtils.encrypt((System.currentTimeMillis() / 1000) + "," + UserManager.getInstance().getCurrentPhoneNumber() + "," + str2));
        hashMap.put("RSS", "ANDROID");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(taskWatch30s, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$sx2JzqAcdNXiEOAaf9ZZKw1CRS4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$taskWatch30s$48((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$taskWatch30s$48(String str) throws Exception {
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<TaskScoreInfo>> getTaskScoreInfo(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getTaskScoreInfo(str), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$gr8Naev9EcyFLj73_MXBre6fmmY
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getTaskScoreInfo$49((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getTaskScoreInfo$49(String str) throws Exception {
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null) {
            TaskScoreInfo taskScoreInfo = new TaskScoreInfo();
            taskScoreInfo.setScoreWatchDay(optJSONObject.optString("scoreWatchDay"));
            taskScoreInfo.setScoreWatchMonth(optJSONObject.optString("scoreWatchMonth"));
            taskScoreInfo.setScoreTotalDay(optJSONObject.optString("scoreTotalDay"));
            taskScoreInfo.setScoreTotalMonth(optJSONObject.optString("scoreTotalMonth"));
            taskScoreInfo.setScoreThumbsUpDay(optJSONObject.optString("scoreThumbsUpDay"));
            taskScoreInfo.setScoreThumbsUpMonth(optJSONObject.optString("scoreThumbsUpMonth"));
            taskScoreInfo.setScoreMaxWatchDay(optJSONObject.optString("scoreMaxWatchDay"));
            taskScoreInfo.setScoreMaxWatchMonth(optJSONObject.optString("scoreMaxWatchMonth"));
            taskScoreInfo.setScoreMaxVideoMonth(optJSONObject.optString("scoreMaxVideoMonth"));
            taskScoreInfo.setScoreMaxRecommendMonth(optJSONObject.optString("scoreMaxRecommendMonth"));
            baseVideoEntity.setData(taskScoreInfo);
        }
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> sendMsg(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.sendMsg(str), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$FBB1IPZZf_8kjVWqPHaWBwXSy_Q
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$sendMsg$50((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$sendMsg$50(String str) throws Exception {
        MsLogUtil.m7979d("sendMsg 发送弹幕:", str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> watchlive(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.watchLive(str), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$mMtJnuB_7_KGfdldW5ZZA-yLr-U
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$watchlive$51((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$watchlive$51(String str) throws Exception {
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<AudienceDataEntity> loadSameCityLiveList(String str, String str2) {
        String sameCityLiveList = URLSet.getSameCityLiveList(str, str2);
        HashMap hashMap = new HashMap();
        hashMap.put("version", App.getInstance().getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(sameCityLiveList, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new TheSameCityLiveFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<BaseVideoEntity<AttentionAnchorVideoEntity>> getTheAnchorAvatarInfo(String str) {
        String theAnchorAvatarInfo = URLSet.getTheAnchorAvatarInfo(str);
        HashMap hashMap = new HashMap();
        hashMap.put("version", App.getInstance().getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(theAnchorAvatarInfo, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new AttentionAnchorsFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<AudienceDataEntity> getDetailsAuthorWork(Map<String, String> map) {
        String detailsAuthorWork = URLSet.getDetailsAuthorWork(map.remove("jobNumber"), map.remove("dataType"));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(detailsAuthorWork, "{\"ids\":" + map.get("ids") + "}").subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new AnchorVideoWorksFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> watchedLive5m(String str) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.watchedLive5m(str), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$tPXQiAC1tCxX516P2jt-H3VJ_LY
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$watchedLive5m$52((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$watchedLive5m$52(String str) throws Exception {
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> getUserNumberByNet(String str) {
        String userNumberByNet = URLSet.getUserNumberByNet();
        HashMap hashMap = new HashMap();
        hashMap.put("accessCode", str);
        hashMap.put("appVersion", "android");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(userNumberByNet, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$W02sQGRjodM8wulR2mJ8VUTFZpg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getUserNumberByNet$53((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getUserNumberByNet$53(String str) throws Exception {
        MsLogUtil.m7979d("getUserNumberByNet", str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setData(jSONObject.optString("data"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        return baseVideoEntity;
    }

    public ObservableSubscribeProxy<BaseVideoEntity<String>> getVideoRing(String str, String str2, String str3, String str4) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getVideoRing(str, str2, str3, str4), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$ManagerAudienceLoadData$AwhKU8ONBROOEoJRWgYSUPnEWrA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerAudienceLoadData.lambda$getVideoRing$54((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getVideoRing$54(String str) throws Exception {
        MsLogUtil.m7980d("getVideoRing" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        return baseVideoEntity;
    }
}
