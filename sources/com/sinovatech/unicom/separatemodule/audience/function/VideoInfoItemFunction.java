package com.sinovatech.unicom.separatemodule.audience.function;

import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoInfoEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoInfoItemFunction implements Function<String, SmallVideoInfoEntity> {
    @Override // io.reactivex.functions.Function
    public SmallVideoInfoEntity apply(@NonNull String str) throws Exception {
        SmallVideoInfoEntity smallVideoInfoEntity = new SmallVideoInfoEntity();
        JSONObject jSONObject = new JSONObject(str);
        smallVideoInfoEntity.setMessage(jSONObject.optString("message"));
        String string = jSONObject.getString("statusCode");
        smallVideoInfoEntity.setStatusCode(string);
        if ("0000".equals(string)) {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
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
            JSONObject jSONObject2 = optJSONObject.getJSONObject("userInfo");
            if (jSONObject2 != null) {
                data.setUserImg(jSONObject2.optString("userImg"));
                data.setUserName(jSONObject2.optString("userName"));
                data.setUserId(jSONObject2.optString("userId"));
                data.setUserIndexUrl(jSONObject2.optString("userIndexUrl"));
            }
            data.setCommentNum("0");
            data.setShareNum("");
            data.setIsShowGoods(optJSONObject.optString("isShowGoods"));
            if ("Y".equals(data.getIsShowGoods())) {
                JSONArray jSONArray = optJSONObject.getJSONArray("goodsInfo");
                if (jSONArray.length() > 0) {
                    JSONObject optJSONObject2 = jSONArray.optJSONObject(0);
                    data.setGoodsNum(jSONArray.length() + "");
                    data.setGoodsId(optJSONObject2.optString("goodsId"));
                    data.setGoodsName(optJSONObject2.optString("goodsName"));
                    data.setGoodsDesc(optJSONObject2.optString("goodsDesc"));
                    data.setGoodsImg(optJSONObject2.optString("goodsImg"));
                    data.setGoodsLink(optJSONObject2.optString("goodsLink"));
                    data.setGoodsPrice(optJSONObject2.optString("goodsPrice"));
                }
            }
            smallVideoInfoEntity.setData(data);
        }
        return smallVideoInfoEntity;
    }
}
