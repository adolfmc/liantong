package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BaiDuLoacationEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/poiList")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PoiListJSPlugin extends BaseJSPlugin {
    private ArrayList<BaiDuLoacationEntity> mLocations;
    private JSONObject object;
    private List<PoiInfo> poiList;
    private RxPermissions rxPermissions;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            this.object = new JSONObject();
            if (this.parameterJO != null) {
                latLongAddress(Double.parseDouble(this.parameterJO.optString("latitude")), Double.parseDouble(this.parameterJO.optString("longitude")));
            } else {
                this.object.put("code", "12");
                this.object.put("msg", "经纬度参数为空或入参格式不对");
                callbackSuccess(this.object);
            }
        } catch (Exception e) {
            callbackFail("10", "程序异常 :" + e.getMessage());
        }
    }

    private void latLongAddress(double d, double d2) {
        GeoCodManager.getInstance().inverseGeocode(new LatLng(d, d2), new OnGetGeoCoderResultListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.PoiListJSPlugin.1
            @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }

            @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                PoiListJSPlugin.this.poiList = reverseGeoCodeResult.getPoiList();
                try {
                    JSONArray jSONArray = new JSONArray();
                    if (PoiListJSPlugin.this.poiList != null) {
                        PoiListJSPlugin.this.object.put("code", "0000");
                        PoiListJSPlugin.this.object.put("msg", "获取poi列表信息成功");
                        for (int i = 0; i < PoiListJSPlugin.this.poiList.size(); i++) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", ((PoiInfo) PoiListJSPlugin.this.poiList.get(i)).name);
                            jSONObject.put("address", ((PoiInfo) PoiListJSPlugin.this.poiList.get(i)).address);
                            jSONObject.put("latitude", ((PoiInfo) PoiListJSPlugin.this.poiList.get(i)).location.latitude + "");
                            jSONObject.put("longitude", ((PoiInfo) PoiListJSPlugin.this.poiList.get(i)).location.longitude + "");
                            jSONArray.put(jSONObject);
                        }
                        PoiListJSPlugin.this.object.put("data", jSONArray);
                        PoiListJSPlugin.this.callbackSuccess(PoiListJSPlugin.this.object);
                        return;
                    }
                    PoiListJSPlugin.this.object.put("code", "11");
                    PoiListJSPlugin.this.object.put("msg", "获取POI列表接口报错");
                    PoiListJSPlugin.this.callbackSuccess(PoiListJSPlugin.this.object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
