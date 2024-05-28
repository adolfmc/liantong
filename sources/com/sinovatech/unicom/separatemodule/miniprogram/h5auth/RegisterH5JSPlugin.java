package com.sinovatech.unicom.separatemodule.miniprogram.h5auth;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import io.objectbox.Box;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/registerH5")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RegisterH5JSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            JSONObject jSONObject = this.parameterJO;
            MsLogUtil.m7979d("RegisterH5JSPlugin", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            String string = this.parameterJO.getString("appId");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            this.webFragment.currentH5Id = string;
            queryRegisterInfo(string);
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }

    public void queryRegisterInfo(final String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", str);
        App.getAsyncHttpClient().rxPost(URLSet.getH5RegisterInfoInEdopUrl(), getParams("getH5Grant", hashMap)).subscribeOn(Schedulers.m1934io()).map(new Function<String, RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.RegisterH5JSPlugin.3
            @Override // io.reactivex.functions.Function
            public RecentStateEntity apply(String str2) throws Exception {
                MsLogUtil.m7979d("RegisterH5JSPlugin", "查询H5在Edop后台注册的信息-map：" + str2);
                return RecentStateEntity.getInstance(str2);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.RegisterH5JSPlugin.1
            @Override // io.reactivex.functions.Consumer
            public void accept(RecentStateEntity recentStateEntity) throws Exception {
                if (recentStateEntity.getRespCode().equals("0102")) {
                    MsLogUtil.m7979d("RegisterH5JSPlugin", "查询H5在Edop后台注册的信息-accept：code=" + recentStateEntity.getRespCode());
                    Box boxFor = App.getBoxStore().boxFor(H5RegisterEntity.class);
                    H5RegisterEntity h5RegisterEntity = (H5RegisterEntity) boxFor.query().equal(H5RegisterEntity_.appId, str).build().findFirst();
                    if (h5RegisterEntity != null) {
                        boxFor.remove((Box) h5RegisterEntity);
                    }
                    H5AuthManager.getInstance(RegisterH5JSPlugin.this.activityContext).deleteH5AuthRecord(str);
                    return;
                }
                JSONObject jSONObject = new JSONObject(recentStateEntity.getBody());
                StringBuilder sb = new StringBuilder();
                sb.append("查询H5在Edop后台注册的信息-accept：");
                sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                MsLogUtil.m7979d("RegisterH5JSPlugin", sb.toString());
                JSONObject jSONObject2 = jSONObject.getJSONObject("appH5Info");
                String string = jSONObject2.getString("appName");
                String string2 = jSONObject2.getString("h5Url");
                JSONArray jSONArray = jSONObject2.getJSONArray("grantInfoList");
                Box boxFor2 = App.getBoxStore().boxFor(H5RegisterEntity.class);
                H5RegisterEntity h5RegisterEntity2 = (H5RegisterEntity) boxFor2.query().equal(H5RegisterEntity_.appId, str).build().findFirst();
                if (h5RegisterEntity2 == null) {
                    h5RegisterEntity2 = new H5RegisterEntity(str, string, string2, !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
                } else {
                    h5RegisterEntity2.setAppName(string);
                    h5RegisterEntity2.setH5Urls(string2);
                    h5RegisterEntity2.setPlugCodes(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
                }
                boxFor2.put((Box) h5RegisterEntity2);
                H5AuthManager.getInstance(RegisterH5JSPlugin.this.activityContext).deleteH5AuthRecord(str);
                MsLogUtil.m7979d("RegisterH5JSPlugin", "入库完成");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.RegisterH5JSPlugin.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                MsLogUtil.m7979d("RegisterH5JSPlugin", th.getMessage());
            }
        });
    }

    private String getParams(String str, Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObject4 = new JSONObject();
            jSONObject3.put("interNo", str);
            jSONObject3.put("seq", uuid());
            jSONObject3.put("reqTime", String.valueOf(System.currentTimeMillis()));
            if (map != null) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    jSONObject4.put(entry.getKey(), entry.getValue());
                }
            }
            jSONObject4.put("appVersion", this.activityContext.getString(2131886969));
            jSONObject2.put("head", jSONObject3);
            jSONObject2.put("body", jSONObject4);
            jSONObject.put("request", jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    private String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }
}
