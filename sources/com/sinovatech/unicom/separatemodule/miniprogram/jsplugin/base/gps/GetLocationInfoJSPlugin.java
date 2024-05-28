package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Context;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.baidu.location.BDLocation;
import com.sinovatech.unicom.basic.p314po.CitySelectEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.List;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getLocationInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetLocationInfoJSPlugin extends BaseJSPlugin {
    private static final String TAG = "GetLocationInfoJSPlugin";
    private static List<CitySelectEntity> cityDatas;
    private boolean isLocation = false;
    private long timeInterval = 0;
    private long defTimeInterval = 300000;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            startLocation();
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误:" + e.getMessage());
        }
    }

    private void startLocation() {
        try {
            this.timeInterval = this.defTimeInterval;
            this.isLocation = false;
            LocationManager.getInstance().startLocation(this.activityContext, this.timeInterval, new LocationManager.LocationListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.GetLocationInfoJSPlugin.1
                @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager.LocationListener
                public void onSuccess(BDLocation bDLocation) {
                    C91451 c91451;
                    try {
                        if (GetLocationInfoJSPlugin.this.isLocation) {
                            return;
                        }
                        GetLocationInfoJSPlugin.this.isLocation = true;
                        LocationManager.getInstance().stopLocaiton();
                        double latitude = bDLocation.getLatitude();
                        double longitude = bDLocation.getLongitude();
                        String province = bDLocation.getProvince();
                        String provinceCode = GetLocationInfoJSPlugin.this.getProvinceCode(province);
                        String city = bDLocation.getCity();
                        String cityCode = GetLocationInfoJSPlugin.this.getCityCode(city);
                        String cityCode2 = bDLocation.getCityCode();
                        String district = bDLocation.getDistrict();
                        String town = bDLocation.getTown();
                        String street = bDLocation.getStreet();
                        String streetNumber = bDLocation.getStreetNumber();
                        String adCode = bDLocation.getAdCode();
                        String country = bDLocation.getCountry();
                        try {
                            String addrStr = bDLocation.getAddrStr();
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("latitude", latitude);
                                jSONObject.put("longitude", longitude);
                                jSONObject.put("provinceName", province);
                                jSONObject.put("provinceCode", provinceCode);
                                jSONObject.put("cityName", city);
                                jSONObject.put("cityCode", cityCode);
                                jSONObject.put("bdCityCode", cityCode2);
                                jSONObject.put("district", district);
                                jSONObject.put("town", town);
                                jSONObject.put("street", street);
                                jSONObject.put("streetNumber", streetNumber);
                                jSONObject.put("adCode", adCode);
                                jSONObject.put("country", country);
                                jSONObject.put("address", addrStr);
                                c91451 = this;
                            } catch (Exception e) {
                                e.printStackTrace();
                                c91451 = this;
                            }
                            try {
                                GetLocationInfoJSPlugin.this.callbackSuccess(jSONObject);
                            } catch (Exception e2) {
                                e = e2;
                                String str = GetLocationInfoJSPlugin.TAG;
                                MsLogUtil.m7977e(str, "定位回调信息异常:" + e.getMessage());
                                GetLocationInfoJSPlugin getLocationInfoJSPlugin = GetLocationInfoJSPlugin.this;
                                getLocationInfoJSPlugin.callbackFail("10", "程序错误:" + e.getMessage());
                            }
                        } catch (Exception e3) {
                            e = e3;
                            c91451 = this;
                            String str2 = GetLocationInfoJSPlugin.TAG;
                            MsLogUtil.m7977e(str2, "定位回调信息异常:" + e.getMessage());
                            GetLocationInfoJSPlugin getLocationInfoJSPlugin2 = GetLocationInfoJSPlugin.this;
                            getLocationInfoJSPlugin2.callbackFail("10", "程序错误:" + e.getMessage());
                        }
                    } catch (Exception e4) {
                        e = e4;
                        c91451 = this;
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager.LocationListener
                public void onFail(String str, String str2) {
                    GetLocationInfoJSPlugin.this.callbackFail(str, str2);
                }
            });
        } catch (Exception e) {
            PermissionDialog.dimissDialog();
            callbackFail("10", "程序错误:" + e.getMessage());
            String str = TAG;
            MsLogUtil.m7977e(str, "定位信息异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getProvinceCode(String str) {
        try {
            if (cityDatas == null) {
                cityDatas = getData();
            }
            for (CitySelectEntity citySelectEntity : cityDatas) {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(citySelectEntity.getProvienceName()) && (str.equals(citySelectEntity.getProvienceName()) || str.startsWith(citySelectEntity.getProvienceName()) || citySelectEntity.getProvienceName().startsWith(str))) {
                    return citySelectEntity.getPrivienceCode();
                }
            }
            return "";
        } catch (Exception e) {
            MsLogUtil.m7978e("getProvinceCode()" + e.getMessage());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCityCode(String str) {
        try {
            if (cityDatas == null) {
                cityDatas = getData();
            }
            for (CitySelectEntity citySelectEntity : cityDatas) {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(citySelectEntity.getCityName()) && (str.equals(citySelectEntity.getCityName()) || str.startsWith(citySelectEntity.getCityName()) || citySelectEntity.getCityName().startsWith(str))) {
                    return citySelectEntity.getCityCode();
                }
            }
            return "";
        } catch (Exception e) {
            MsLogUtil.m7978e("getCityCode()" + e.getMessage());
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x002a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.sinovatech.unicom.basic.p314po.CitySelectEntity> getData() {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.sinovatech.unicom.basic.boxcenter.CacheDataCenter r2 = com.sinovatech.unicom.basic.boxcenter.CacheDataCenter.getInstance()     // Catch: java.lang.Exception -> L24
            java.lang.String r2 = r2.getCityData()     // Catch: java.lang.Exception -> L24
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L24
            if (r3 != 0) goto L28
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch: java.lang.Exception -> L24
            r3.<init>(r2)     // Catch: java.lang.Exception -> L24
            java.lang.String r1 = "GetLocationInfoJSPlugin 城市编码使用远程缓存"
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7980d(r1)     // Catch: java.lang.Exception -> L20
            r1 = r3
            goto L28
        L20:
            r1 = move-exception
            r2 = r1
            r1 = r3
            goto L25
        L24:
            r2 = move-exception
        L25:
            r2.printStackTrace()
        L28:
            if (r1 != 0) goto L59
            android.app.Activity r2 = r6.activityContext     // Catch: java.lang.Exception -> L55
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch: java.lang.Exception -> L55
            java.lang.String r3 = "city_select_json.json"
            java.io.InputStream r2 = r2.open(r3)     // Catch: java.lang.Exception -> L55
            java.util.Scanner r3 = new java.util.Scanner     // Catch: java.lang.Exception -> L55
            r3.<init>(r2)     // Catch: java.lang.Exception -> L55
            java.lang.String r2 = "\\A"
            java.util.Scanner r2 = r3.useDelimiter(r2)     // Catch: java.lang.Exception -> L55
            java.lang.String r2 = r2.next()     // Catch: java.lang.Exception -> L55
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch: java.lang.Exception -> L55
            r3.<init>(r2)     // Catch: java.lang.Exception -> L55
            java.lang.String r1 = "GetLocationInfoJSPlugin 城市编码使用本地文件"
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7980d(r1)     // Catch: java.lang.Exception -> L51
            r1 = r3
            goto L59
        L51:
            r1 = move-exception
            r2 = r1
            r1 = r3
            goto L56
        L55:
            r2 = move-exception
        L56:
            r2.printStackTrace()
        L59:
            if (r1 == 0) goto L99
            r2 = 0
        L5c:
            int r3 = r1.length()     // Catch: java.lang.Exception -> L95
            if (r2 >= r3) goto L99
            com.sinovatech.unicom.basic.po.CitySelectEntity r3 = new com.sinovatech.unicom.basic.po.CitySelectEntity     // Catch: java.lang.Exception -> L95
            r3.<init>()     // Catch: java.lang.Exception -> L95
            org.json.JSONObject r4 = r1.getJSONObject(r2)     // Catch: java.lang.Exception -> L95
            java.lang.String r5 = "cityCode"
            java.lang.String r5 = r4.optString(r5)     // Catch: java.lang.Exception -> L95
            r3.setCityCode(r5)     // Catch: java.lang.Exception -> L95
            java.lang.String r5 = "cityName"
            java.lang.String r5 = r4.optString(r5)     // Catch: java.lang.Exception -> L95
            r3.setCityName(r5)     // Catch: java.lang.Exception -> L95
            java.lang.String r5 = "privienceCode"
            java.lang.String r5 = r4.optString(r5)     // Catch: java.lang.Exception -> L95
            r3.setPrivienceCode(r5)     // Catch: java.lang.Exception -> L95
            java.lang.String r5 = "provienceName"
            java.lang.String r4 = r4.optString(r5)     // Catch: java.lang.Exception -> L95
            r3.setProvienceName(r4)     // Catch: java.lang.Exception -> L95
            r0.add(r3)     // Catch: java.lang.Exception -> L95
            int r2 = r2 + 1
            goto L5c
        L95:
            r1 = move-exception
            r1.printStackTrace()
        L99:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.GetLocationInfoJSPlugin.getData():java.util.List");
    }
}
