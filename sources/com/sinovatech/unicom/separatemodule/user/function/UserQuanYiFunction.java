package com.sinovatech.unicom.separatemodule.user.function;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.separatemodule.user.entity.UserQuanyiEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserQuanYiFunction implements Function<String, UserQuanyiEntity> {
    private String mobile;

    public UserQuanYiFunction(String str) {
        this.mobile = str;
    }

    @Override // io.reactivex.functions.Function
    public UserQuanyiEntity apply(String str) throws Exception {
        UserQuanyiEntity userQuanyiEntity = new UserQuanyiEntity();
        if (TextUtils.isEmpty(str)) {
            return userQuanyiEntity;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("0000".equals(jSONObject.optString("code"))) {
                CacheDataCenter.getInstance().setQuanyiNew(str, this.mobile);
                userQuanyiEntity.setCode(jSONObject.optString("code"));
                userQuanyiEntity.setDesc(jSONObject.optString("desc"));
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                Gson gson = new Gson();
                String optString = optJSONObject.optString("member");
                userQuanyiEntity.setMember((UserQuanyiEntity.MemberBean) (!(gson instanceof Gson) ? gson.fromJson(optString, (Class<Object>) UserQuanyiEntity.MemberBean.class) : NBSGsonInstrumentation.fromJson(gson, optString, (Class<Object>) UserQuanyiEntity.MemberBean.class)));
                ArrayList arrayList = new ArrayList();
                try {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("identifyArray");
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        UserQuanyiEntity.IdentifyArrayBean identifyArrayBean = new UserQuanyiEntity.IdentifyArrayBean();
                        identifyArrayBean.setIcon(optJSONObject2.optString("icon"));
                        identifyArrayBean.setUrl(optJSONObject2.optString("url"));
                        arrayList.add(identifyArrayBean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                userQuanyiEntity.setIdentifyArray(arrayList);
                ArrayList arrayList2 = new ArrayList();
                try {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("privilegeArray");
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i2);
                        UserQuanyiEntity.PrivilegeArrayBean privilegeArrayBean = new UserQuanyiEntity.PrivilegeArrayBean();
                        privilegeArrayBean.setArrowImg(optJSONObject3.optString("arrowImg"));
                        privilegeArrayBean.setContent(optJSONObject3.optString("content"));
                        privilegeArrayBean.setIcon(optJSONObject3.optString("icon"));
                        privilegeArrayBean.setTitle(optJSONObject3.optString("title"));
                        privilegeArrayBean.setUrl(optJSONObject3.optString("url"));
                        arrayList2.add(privilegeArrayBean);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                userQuanyiEntity.setPrivilegeArray(arrayList2);
                ArrayList arrayList3 = new ArrayList();
                try {
                    JSONArray optJSONArray3 = optJSONObject.optJSONArray("equityArray");
                    for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                        JSONObject optJSONObject4 = optJSONArray3.optJSONObject(i3);
                        UserQuanyiEntity.EquityArrayBean equityArrayBean = new UserQuanyiEntity.EquityArrayBean();
                        equityArrayBean.setIcon(optJSONObject4.optString("icon"));
                        equityArrayBean.setTitle(optJSONObject4.optString("title"));
                        equityArrayBean.setUrl(optJSONObject4.optString("url"));
                        equityArrayBean.setImgc(optJSONObject4.optString("imgc"));
                        arrayList3.add(equityArrayBean);
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                userQuanyiEntity.setEquityArray(arrayList3);
            }
            return userQuanyiEntity;
        } catch (Exception e4) {
            e4.printStackTrace();
            throw e4;
        }
    }
}
