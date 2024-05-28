package com.sinovatech.unicom.basic.p315ui.fuwu.manager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.p315ui.fuwu.entity.MarketingBitsEntity;
import com.sinovatech.unicom.basic.p315ui.fuwu.entity.MarketingBitsListEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.BaseFunction;
import com.sinovatech.unicom.basic.p315ui.home.manager.BaseRequestManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.manager.MarketingBitsManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MarketingBitsManager extends BaseRequestManager<MarketingBitsListEntity> {
    private static final String FUWU_GUANGGAO_CACHE = "fuwu_guanggao_cache";
    private static final String TAG = "MarketingBitsManager";

    public MarketingBitsManager(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }

    public ObservableSubscribeProxy<MarketingBitsListEntity> getServiceHomeMarketingBits(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getResources().getString(2131886969));
        return setRequestType(str).setNetObserver(App.getAsyncHttpClient().rxGet(URLSet.getServiceHomeMarketingBits(), hashMap, getHeader())).setCacheKey(FUWU_GUANGGAO_CACHE, UserManager.getInstance().getCurrentPhoneNumber()).setFunction(new BaseFunction<MarketingBitsListEntity>() { // from class: com.sinovatech.unicom.basic.ui.fuwu.manager.MarketingBitsManager.1
            @Override // io.reactivex.functions.Function
            public MarketingBitsListEntity apply(String str2) throws Exception {
                JSONObject jSONObject;
                MsLogUtil.m7979d(MarketingBitsManager.TAG, "服务页面广告位营销位解析类= " + str2);
                try {
                    JSONObject jSONObject2 = new JSONObject(str2);
                    MarketingBitsListEntity marketingBitsListEntity = new MarketingBitsListEntity();
                    String optString = jSONObject2.optString("respCode");
                    String optString2 = jSONObject2.optString("transId");
                    marketingBitsListEntity.setRespTime(jSONObject2.optString("respTime"));
                    marketingBitsListEntity.setRespCode(optString);
                    marketingBitsListEntity.setTransId(optString2);
                    if (TextUtils.equals("0000", optString)) {
                        JSONObject optJSONObject = jSONObject2.optJSONObject("data");
                        boolean optBoolean = optJSONObject.optBoolean("hiddenTop", true);
                        boolean optBoolean2 = optJSONObject.optBoolean("hiddenBottom", true);
                        marketingBitsListEntity.setHiddenTop(optBoolean);
                        marketingBitsListEntity.setHiddenBottom(optBoolean2);
                        ArrayList arrayList = new ArrayList();
                        int i = 0;
                        if (optBoolean) {
                            jSONObject = optJSONObject;
                        } else {
                            JSONArray optJSONArray = optJSONObject.optJSONArray("topList");
                            int i2 = 0;
                            while (i2 < optJSONArray.length()) {
                                MarketingBitsEntity marketingBitsEntity = new MarketingBitsEntity();
                                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                                String optString3 = optJSONObject2.optString("channelCode");
                                String optString4 = optJSONObject2.optString("currentTouch");
                                JSONObject optJSONObject3 = optJSONObject2.optJSONArray("list").optJSONObject(i);
                                String optString5 = optJSONObject3.optString("title");
                                String optString6 = optJSONObject3.optString("viceTitle");
                                String optString7 = optJSONObject3.optString("showTab");
                                String optString8 = optJSONObject3.optString("goodsUrl");
                                String optString9 = optJSONObject3.optString("imgSrc");
                                String optString10 = optJSONObject3.optString("actId");
                                JSONArray jSONArray = optJSONArray;
                                String optString11 = optJSONObject3.optString("goodsId");
                                JSONObject jSONObject3 = optJSONObject;
                                String optString12 = optJSONObject3.optString("commodity_id");
                                marketingBitsEntity.setCurrentTouch(optString4);
                                marketingBitsEntity.setChannelCode(optString3);
                                marketingBitsEntity.setGoodsUrl(optString8);
                                marketingBitsEntity.setImgSrc(optString9);
                                marketingBitsEntity.setTitle(optString5);
                                marketingBitsEntity.setShowTab(optString7);
                                marketingBitsEntity.setViceTitle(optString6);
                                marketingBitsEntity.setActId(optString10);
                                marketingBitsEntity.setGoodsId(optString11);
                                marketingBitsEntity.setId(optString12);
                                arrayList.add(marketingBitsEntity);
                                i2++;
                                optJSONArray = jSONArray;
                                optJSONObject = jSONObject3;
                                i = 0;
                            }
                            jSONObject = optJSONObject;
                        }
                        marketingBitsListEntity.setTopList(arrayList);
                        ArrayList arrayList2 = new ArrayList();
                        if (!optBoolean2) {
                            JSONArray optJSONArray2 = jSONObject.optJSONArray("bottomList");
                            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                                MarketingBitsEntity marketingBitsEntity2 = new MarketingBitsEntity();
                                JSONObject optJSONObject4 = optJSONArray2.optJSONObject(i3);
                                String optString13 = optJSONObject4.optString("channelCode");
                                String optString14 = optJSONObject4.optString("currentTouch");
                                JSONObject optJSONObject5 = optJSONObject4.optJSONArray("list").optJSONObject(0);
                                String optString15 = optJSONObject5.optString("title");
                                String optString16 = optJSONObject5.optString("viceTitle");
                                String optString17 = optJSONObject5.optString("showTab");
                                String optString18 = optJSONObject5.optString("goodsUrl");
                                String optString19 = optJSONObject5.optString("imgSrc");
                                String optString20 = optJSONObject5.optString("actId");
                                String optString21 = optJSONObject5.optString("goodsId");
                                String optString22 = optJSONObject5.optString("commodity_id");
                                marketingBitsEntity2.setCurrentTouch(optString14);
                                marketingBitsEntity2.setChannelCode(optString13);
                                marketingBitsEntity2.setGoodsUrl(optString18);
                                marketingBitsEntity2.setImgSrc(optString19);
                                marketingBitsEntity2.setTitle(optString15);
                                marketingBitsEntity2.setShowTab(optString17);
                                marketingBitsEntity2.setViceTitle(optString16);
                                marketingBitsEntity2.setActId(optString20);
                                marketingBitsEntity2.setGoodsId(optString21);
                                marketingBitsEntity2.setId(optString22);
                                arrayList2.add(marketingBitsEntity2);
                            }
                        }
                        marketingBitsListEntity.setBottomList(arrayList2);
                    }
                    return marketingBitsListEntity;
                } catch (Exception e) {
                    MsLogUtil.m7977e(MarketingBitsManager.TAG, "服务页面广告位营销位解析异常：" + e.getMessage());
                    return null;
                }
            }
        }).getObservable();
    }
}
