package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.webkit.WebView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.sinovatech.unicom.basic.server.LocationManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class StartContinuousLocationPlugin extends YHXXJSPlugin {
    private int callbackInterval;
    private JSONObject originConfigJO;

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.sinovatech.unicom.separatemodule.gaodedache.YHXXJSPlugin
    public void call(final AppCompatActivity appCompatActivity, final WebView webView, int i, String str) {
        try {
            this.originConfigJO = new JSONObject(str);
            this.callbackInterval = this.originConfigJO.getJSONObject("parameter").getInt("callbackInterval");
            PermissionDialog.show("需要您授予中国联通APP位置权限，以开启与位置相关的推荐/搜索/安全保障服务。");
            new RxPermissions(appCompatActivity).request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.gaodedache.StartContinuousLocationPlugin.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    PermissionDialog.dimissDialog();
                    if (bool.booleanValue()) {
                        LocationManager.startLocation(appCompatActivity, StartContinuousLocationPlugin.this.callbackInterval, new BDAbstractLocationListener() { // from class: com.sinovatech.unicom.separatemodule.gaodedache.StartContinuousLocationPlugin.1.1
                            @Override // com.baidu.location.BDAbstractLocationListener
                            public void onReceiveLocation(BDLocation bDLocation) {
                                int locType = bDLocation.getLocType();
                                try {
                                    if (locType == 61 || locType == 161) {
                                        double latitude = bDLocation.getLatitude();
                                        double longitude = bDLocation.getLongitude();
                                        String city = bDLocation.getCity();
                                        String adCode = bDLocation.getAdCode();
                                        JSONObject jSONObject = new JSONObject();
                                        try {
                                            jSONObject.put("latitude", latitude);
                                            jSONObject.put("longitude", longitude);
                                            jSONObject.put("city", city);
                                            jSONObject.put("cityAdcode", adCode);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        StartContinuousLocationPlugin.this.callBack(webView, StartContinuousLocationPlugin.this.originConfigJO, YHXXJSPlugin.STATUS_SUCCESS, jSONObject);
                                        return;
                                    }
                                    throw new RuntimeException();
                                } catch (RuntimeException e2) {
                                    StartContinuousLocationPlugin.this.callBack(webView, StartContinuousLocationPlugin.this.originConfigJO, YHXXJSPlugin.STATUS_SUCCESS, new JSONObject());
                                    e2.printStackTrace();
                                }
                            }
                        });
                        return;
                    }
                    StartContinuousLocationPlugin startContinuousLocationPlugin = StartContinuousLocationPlugin.this;
                    startContinuousLocationPlugin.callBack(webView, startContinuousLocationPlugin.originConfigJO, YHXXJSPlugin.STATUS_FAIL, new JSONObject());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
