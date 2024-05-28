package com.sinovatech.unicom.separatemodule.audience.function;

import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SmallVideoItemFunction implements Function<String, SmallVideoEntity> {
    @Override // io.reactivex.functions.Function
    public SmallVideoEntity apply(@NonNull String str) throws Exception {
        JSONArray optJSONArray;
        SmallVideoEntity smallVideoEntity = new SmallVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        smallVideoEntity.setMessage(jSONObject.optString("message"));
        smallVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        smallVideoEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(optString);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                SmallVideoEntity.Data data = new SmallVideoEntity.Data();
                data.setVideoId(optJSONObject.optString("videoId"));
                data.setVideoUrl(optJSONObject.optString("videoLink"));
                data.setVideoImg(optJSONObject.optString("videoImg"));
                data.setVideoAdvert(optJSONObject.optString("videoAdvert"));
                data.setViewNum(optJSONObject.optString("viewNum"));
                data.setVideoPraiseNum(optJSONObject.optString("videoPraiseNum"));
                data.setTranscodeImg(optJSONObject.optString("transcodeImg"));
                data.setVideoType(optJSONObject.optInt("videoType"));
                data.setIsShow(optJSONObject.optString("isShow"));
                if (UserManager.getInstance().isYiwang() && data.getIsShow().equals("Y")) {
                    data.setIsShow("N");
                }
                data.setVideoTag(optJSONObject.optString("videoTag"));
                data.setViewTitle(optJSONObject.optString("videoTitle"));
                data.setTjpara(optJSONObject.optString("tjpara"));
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("userInfo");
                if (optJSONObject2 != null) {
                    data.setUserImg(optJSONObject2.optString("userImg"));
                    data.setUserName(optJSONObject2.optString("userName"));
                    data.setUserId(optJSONObject2.optString("userId"));
                    data.setUserIndexUrl(optJSONObject2.optString("userIndexUrl"));
                }
                data.setCommentNum("0");
                data.setShareNum("");
                data.setIsShowGoods(optJSONObject.optString("isShowGoods"));
                if ("Y".equals(data.getIsShowGoods()) && (optJSONArray = optJSONObject.optJSONArray("goodsInfo")) != null && optJSONArray.length() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        GoodListEntity goodListEntity = new GoodListEntity();
                        JSONObject optJSONObject3 = optJSONArray.optJSONObject(i2);
                        goodListEntity.setDesc(optJSONObject3.optString("goodsDesc"));
                        goodListEntity.setCoverImgUrl(optJSONObject3.optString("goodsImg"));
                        goodListEntity.setId(optJSONObject3.optString("goodsId"));
                        goodListEntity.setName(optJSONObject3.optString("goodsName"));
                        goodListEntity.setGoodsUrl(optJSONObject3.optString("goodsLink"));
                        goodListEntity.setPrice(optJSONObject3.optString("goodsPrice"));
                        if (i2 == 0) {
                            data.setGoodsNum(optJSONArray.length() + "");
                            data.setGoodsId(optJSONObject3.optString("goodsId"));
                            data.setGoodsName(optJSONObject3.optString("goodsName"));
                            data.setGoodsDesc(optJSONObject3.optString("goodsDesc"));
                            data.setGoodsImg(optJSONObject3.optString("goodsImg"));
                            data.setGoodsLink(optJSONObject3.optString("goodsLink"));
                            data.setGoodsPrice(optJSONObject3.optString("goodsPrice"));
                        }
                        arrayList2.add(goodListEntity);
                    }
                    data.setGoodsData(arrayList2);
                }
                arrayList.add(data);
            }
            smallVideoEntity.setData(arrayList);
        }
        return smallVideoEntity;
    }
}
