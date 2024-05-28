package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/locationSearchTips")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LocationSearchTipsJSPlugin extends BaseJSPlugin {
    private String locationCity;
    private JSONObject object;
    private RxPermissions rxPermissions;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            if (this.parameterJO != null) {
                this.object = new JSONObject();
                String optString = this.parameterJO.optString("keyWord");
                String optString2 = this.parameterJO.optString("city");
                boolean optBoolean = this.parameterJO.optBoolean("cityLimit", false);
                if (!TextUtils.isEmpty(optString)) {
                    if (!TextUtils.isEmpty(optString2)) {
                        if (TextUtils.isEmpty(optString2)) {
                            optString2 = this.locationCity;
                        }
                        SuggestionSearchManager.suggestionSearch(optString2, optString, optBoolean, new OnGetSuggestionResultListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationSearchTipsJSPlugin.1
                            @Override // com.baidu.mapapi.search.sug.OnGetSuggestionResultListener
                            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                                try {
                                    SuggestionSearchManager.suggestionSearchStop();
                                    if (suggestionResult.getAllSuggestions() == null) {
                                        LocationSearchTipsJSPlugin.this.object.put("code", "11");
                                        LocationSearchTipsJSPlugin.this.object.put("msg", "搜索关键字接口报错");
                                        LocationSearchTipsJSPlugin.this.callbackSuccess(LocationSearchTipsJSPlugin.this.object);
                                        return;
                                    }
                                    JSONArray jSONArray = new JSONArray();
                                    LocationSearchTipsJSPlugin.this.object.put("code", "0000");
                                    LocationSearchTipsJSPlugin.this.object.put("msg", "获取poi列表信息成功");
                                    for (int i = 0; i < suggestionResult.getAllSuggestions().size(); i++) {
                                        JSONObject jSONObject = new JSONObject();
                                        jSONObject.put("name", suggestionResult.getAllSuggestions().get(i).getKey());
                                        jSONObject.put("address", suggestionResult.getAllSuggestions().get(i).getAddress());
                                        if (suggestionResult.getAllSuggestions().get(i).getPt() != null) {
                                            jSONObject.put("latitude", suggestionResult.getAllSuggestions().get(i).getPt().latitude);
                                            jSONObject.put("longitude", suggestionResult.getAllSuggestions().get(i).getPt().longitude);
                                        }
                                        jSONArray.put(jSONObject);
                                    }
                                    LocationSearchTipsJSPlugin.this.object.put("locationList", jSONArray);
                                    LocationSearchTipsJSPlugin.this.callbackSuccess(LocationSearchTipsJSPlugin.this.object);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        return;
                    }
                    this.object.put("code", "13");
                    this.object.put("msg", "keyWord或者city参数为空、入参格式不正确");
                    callbackSuccess(this.object);
                    return;
                }
                this.object.put("code", "13");
                this.object.put("msg", "keyWord或者city参数为空、入参格式不正确");
                callbackSuccess(this.object);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }
}
