package com.sinovatech.unicom.separatemodule.user.function;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.MyUnicomEntity;
import com.sinovatech.unicom.basic.p314po.UnicomBottomEntity;
import com.sinovatech.unicom.basic.p314po.UnicomTopEntity;
import com.sinovatech.unicom.basic.server.UserManager;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserDataTotleFunction implements Function<String, MyUnicomEntity> {
    private String mobile;

    public UserDataTotleFunction(String str) {
        this.mobile = str;
    }

    @Override // io.reactivex.functions.Function
    public MyUnicomEntity apply(String str) throws Exception {
        MyUnicomEntity myUnicomEntity = new MyUnicomEntity();
        if (TextUtils.isEmpty(str)) {
            return myUnicomEntity;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (this.mobile.equals(jSONObject.optString("phone"))) {
                CacheDataCenter.getInstance().setUserInfo(str, this.mobile);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                myUnicomEntity.setBackImg(jSONObject.optString("backImg"));
                myUnicomEntity.setBackImgURl(jSONObject.optString("backImgURl"));
                myUnicomEntity.setNickName(jSONObject.optString("nickName"));
                myUnicomEntity.setThirdPictureLinkUrl(jSONObject.optString("thirdPictureLinkUrl"));
                myUnicomEntity.setMessageUrl(jSONObject.optString("messageUrl"));
                myUnicomEntity.setInfoDetail(jSONObject.optString("infoDetail"));
                myUnicomEntity.setUploadImage(jSONObject.optString("uploadImage"));
                myUnicomEntity.setJpUrl(jSONObject.optString("jpUrl"));
                UserManager.getInstance().saveIconurl(UserManager.getInstance().getCurrentPhoneNumber(), jSONObject.optString("head_img"));
                myUnicomEntity.setQianming(jSONObject.optString("sign"));
                myUnicomEntity.setHeadImage(jSONObject.optString("head_img"));
                myUnicomEntity.setPhone(jSONObject.optString("phone"));
                myUnicomEntity.setLevelPic(jSONObject.optString("levelPic"));
                myUnicomEntity.setLevelLinkedAddress(jSONObject.optString("levelLinkedAddress"));
                JSONArray optJSONArray = jSONObject.optJSONArray("top");
                JSONArray optJSONArray2 = jSONObject.optJSONArray("buttom");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        UnicomTopEntity unicomTopEntity = new UnicomTopEntity();
                        unicomTopEntity.setName(optJSONObject.optString("name"));
                        unicomTopEntity.setUrl(optJSONObject.optString("url"));
                        unicomTopEntity.setValue(optJSONObject.optString("value"));
                        arrayList.add(unicomTopEntity);
                    }
                }
                if (optJSONArray2 != null) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        try {
                            JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                            String optString = optJSONObject2.optString("value");
                            String optString2 = optJSONObject2.optString("id");
                            if ((TextUtils.isEmpty(optString) || "-".equals(optString) || "--".equals(optString)) && !TextUtils.isEmpty(optString2)) {
                                String cardBottomData = CacheDataCenter.getInstance().getCardBottomData(UserManager.getInstance().getCurrentPhoneNumber(), optString2);
                                if (!TextUtils.isEmpty(cardBottomData)) {
                                    optJSONObject2 = new JSONObject(cardBottomData);
                                }
                            }
                            UnicomBottomEntity unicomBottomEntity = new UnicomBottomEntity();
                            unicomBottomEntity.setLinkurl(optJSONObject2.optString("linkurl"));
                            unicomBottomEntity.setPopUrl(optJSONObject2.optString("popUrl"));
                            unicomBottomEntity.setTimestamp(optJSONObject2.optString("timestamp"));
                            unicomBottomEntity.setTitle(optJSONObject2.optString("title"));
                            unicomBottomEntity.setValue(optJSONObject2.optString("value"));
                            unicomBottomEntity.setId(optJSONObject2.optString("id"));
                            unicomBottomEntity.setWhichKind(optJSONObject2.optString("whichKind"));
                            unicomBottomEntity.setContent(optJSONObject2.optString("content"));
                            unicomBottomEntity.setAddNum(optJSONObject2.optString("addNum"));
                            arrayList2.add(unicomBottomEntity);
                            CacheDataCenter.getInstance().setCardBottomData(!(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2), UserManager.getInstance().getCurrentPhoneNumber(), optString2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                myUnicomEntity.setTopEntityList(arrayList);
                myUnicomEntity.setBottomEntityList(arrayList2);
                MyUnicomEntity.MedalWall medalWall = new MyUnicomEntity.MedalWall();
                JSONObject optJSONObject3 = jSONObject.optJSONObject("medalWall");
                if (optJSONObject3 != null) {
                    try {
                        String optString3 = optJSONObject3.optString("count");
                        Integer.parseInt(optString3);
                        medalWall.setCount(optString3);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    medalWall.setGrade(optJSONObject3.optString("grade"));
                    medalWall.setImgUrl(optJSONObject3.optString(""));
                    medalWall.setTitle(optJSONObject3.optString("title"));
                    medalWall.setLinkurl(optJSONObject3.optString("linkurl"));
                    myUnicomEntity.setMedalWall(medalWall);
                }
                CacheDataCenter.getInstance().setUserInfoNew(str, this.mobile);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return myUnicomEntity;
    }
}
