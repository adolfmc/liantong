package com.sinovatech.unicom.separatemodule.audience.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoEntity;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecommendListFunction implements Function<String, BaseVideoEntity<List<VideoEntity>>> {
    @Override // io.reactivex.functions.Function
    public BaseVideoEntity<List<VideoEntity>> apply(@NonNull String str) throws Exception {
        BaseVideoEntity<List<VideoEntity>> baseVideoEntity = new BaseVideoEntity<>();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setCount(jSONObject.optJSONObject("data").optString("count"));
        baseVideoEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
        String optString = jSONObject.optJSONObject("data").optString("videolist");
        if (optString.startsWith("[")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(optString);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                VideoEntity videoEntity = new VideoEntity();
                String optString2 = optJSONObject.optString("contentId");
                String optString3 = optJSONObject.optString("contentid");
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = optString3;
                }
                videoEntity.setId(optString2);
                if (!TextUtils.isEmpty(videoEntity.getId())) {
                    videoEntity.setName(optJSONObject.optString("name"));
                    videoEntity.setPicpath(optJSONObject.optString("picpath"));
                    videoEntity.setPrice(optJSONObject.optString("price"));
                    videoEntity.setLikeNum(optJSONObject.optString("likeNum"));
                    videoEntity.setLikeFlag(optJSONObject.optString("likeFlag"));
                    videoEntity.setViewNum(optJSONObject.optString("viewNum"));
                    videoEntity.setFavNum(optJSONObject.optString("favNum"));
                    videoEntity.setFavFlag(optJSONObject.optString("favFlag"));
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("userinfo");
                    JSONObject optJSONObject3 = optJSONObject.optJSONObject("userbaseinfo");
                    if (optJSONObject2 != null) {
                        videoEntity.setHeadimg(optJSONObject2.optString("headimg"));
                        videoEntity.setNickname(optJSONObject2.optString("nickname"));
                        videoEntity.setIdentification(optJSONObject2.optString("identification"));
                    } else if (optJSONObject3 != null) {
                        videoEntity.setHeadimg(optJSONObject3.optString("headimg"));
                        videoEntity.setNickname(optJSONObject3.optString("nickname"));
                        videoEntity.setIdentification(optJSONObject3.optString("identification"));
                    } else {
                        videoEntity.setIdentification(optJSONObject.optString("identification"));
                    }
                    JSONArray optJSONArray = optJSONObject.optJSONArray("tags");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        JSONObject optJSONObject4 = optJSONArray.optJSONObject(0);
                        if (optJSONObject4 != null) {
                            videoEntity.setSingleTag(optJSONObject4.optString("tagName"));
                        }
                    } else {
                        videoEntity.setSingleTag("1".equals(optJSONObject.optString("isOriginal")) ? "原创" : "");
                    }
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("label");
                    if (optJSONArray2 != null) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            stringBuffer.append("#");
                            stringBuffer.append(optJSONArray2.optString(i2));
                        }
                        videoEntity.setLabel(stringBuffer.toString());
                    }
                    videoEntity.setTjpara(optJSONObject.optString("tjpara"));
                    videoEntity.setContentType(optJSONObject.optString("contentType"));
                    arrayList.add(videoEntity);
                }
            }
            baseVideoEntity.setData(arrayList);
        }
        return baseVideoEntity;
    }
}
