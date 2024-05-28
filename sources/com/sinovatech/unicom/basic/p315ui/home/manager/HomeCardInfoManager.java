package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.basic.p315ui.home.function.HomeCardBgFunction;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.HomeCardInfoManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeCardInfoManager {
    public static final String DTGZ = "1000230057";
    private static final String TAG = "HomeCardInfoManager";
    public static final String YWBL = "1000230060";
    public static final String YWSP = "1000230058";
    private AppCompatActivity activityContext;

    public HomeCardInfoManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public ObservableSubscribeProxy<Object> getHomeData(String str) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("serviceCode", str);
            hashMap.put("appSerial", System.currentTimeMillis() + "");
            hashMap.put("city", UserManager.getInstance().getCurrentCityCode());
            hashMap.put("province", UserManager.getInstance().getCurrentProvinceCode());
            hashMap.put("appVersion", this.activityContext.getString(2131886969));
            hashMap.put("appId", DeviceHelper.getDeviceID(false));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getHomeDataUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new HomeCardBgFunction(str)).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }
}
