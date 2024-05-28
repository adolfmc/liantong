package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/geocoding")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GeocodingJSPlugin extends BaseJSPlugin {

    /* renamed from: ob */
    private JSONObject f18574ob;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            this.f18574ob = new JSONObject();
            String optString = this.parameterJO.optString("address");
            if (!TextUtils.isEmpty(optString)) {
                GeoCodManager.getInstance().getGeCoder(optString, new OnGetGeoCoderResultListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.GeocodingJSPlugin.1
                    @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                    }

                    @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                        try {
                            GeoCodManager.getInstance().GeoCodeStop();
                            if (geoCodeResult == null) {
                                GeocodingJSPlugin.this.f18574ob.put("code", "11");
                                GeocodingJSPlugin.this.f18574ob.put("msg", "搜索关键字接口报错");
                                GeocodingJSPlugin.this.callbackSuccess(GeocodingJSPlugin.this.f18574ob);
                            } else {
                                JSONObject jSONObject = new JSONObject();
                                GeocodingJSPlugin.this.f18574ob.put("code", "0000");
                                GeocodingJSPlugin.this.f18574ob.put("msg", "获取poi列表信息成功");
                                jSONObject.put("precise", geoCodeResult.getPrecise() + "");
                                jSONObject.put("confidence", geoCodeResult.getConfidence());
                                jSONObject.put("latitude", String.valueOf(geoCodeResult.getLocation().latitude));
                                jSONObject.put("longitude", String.valueOf(geoCodeResult.getLocation().longitude));
                                GeocodingJSPlugin.this.f18574ob.put("geoInfo", jSONObject);
                                GeocodingJSPlugin.this.callbackSuccess(GeocodingJSPlugin.this.f18574ob);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                this.f18574ob.put("code", "13");
                this.f18574ob.put("msg", "address为空或入参格式不正确");
                callbackSuccess(this.f18574ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }
}
