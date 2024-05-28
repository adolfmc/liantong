package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/reverseGeocoding")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ReverseGeocodingJSPlugin extends BaseJSPlugin {

    /* renamed from: ob */
    private JSONObject f18575ob;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("latitude");
            String optString2 = this.parameterJO.optString("longitude");
            this.f18575ob = new JSONObject();
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                GeoCodManager.getInstance().inverseGeocode(new LatLng(Double.parseDouble(optString), Double.parseDouble(optString2)), new OnGetGeoCoderResultListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.ReverseGeocodingJSPlugin.1
                    @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                    }

                    @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                        try {
                            GeoCodManager.getInstance().GeoCodeStop();
                            if (reverseGeoCodeResult == null) {
                                ReverseGeocodingJSPlugin.this.f18575ob.put("code", "11");
                                ReverseGeocodingJSPlugin.this.f18575ob.put("msg", "搜索关键字接口报错");
                                ReverseGeocodingJSPlugin.this.callbackSuccess(ReverseGeocodingJSPlugin.this.f18575ob);
                            } else {
                                JSONObject jSONObject = new JSONObject();
                                ReverseGeocodingJSPlugin.this.f18575ob.put("code", "0000");
                                jSONObject.put("msg", "获取poi列表信息成功");
                                jSONObject.put("address", reverseGeoCodeResult.getAddress());
                                jSONObject.put("adCode", reverseGeoCodeResult.getAdcode());
                                jSONObject.put("name", reverseGeoCodeResult.getPoiList().get(0).getName());
                                jSONObject.put("province", reverseGeoCodeResult.getAddressDetail().province);
                                jSONObject.put("city", reverseGeoCodeResult.getAddressDetail().city);
                                jSONObject.put("district", reverseGeoCodeResult.getAddressDetail().district);
                                jSONObject.put("town", reverseGeoCodeResult.getAddressDetail().town);
                                jSONObject.put("streetName", reverseGeoCodeResult.getAddressDetail().street);
                                jSONObject.put("streetNumber", reverseGeoCodeResult.getAddressDetail().streetNumber);
                                jSONObject.put("latitude", reverseGeoCodeResult.getLocation().latitude);
                                jSONObject.put("longitude", reverseGeoCodeResult.getLocation().longitude);
                                ReverseGeocodingJSPlugin.this.f18575ob.put("reverseGeoInfo", jSONObject);
                                ReverseGeocodingJSPlugin.this.callbackSuccess(ReverseGeocodingJSPlugin.this.f18575ob);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                this.f18575ob.put("code", "13");
                this.f18575ob.put("msg", "入参为空或格式不正确");
                callbackSuccess(this.f18575ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }
}
