package com.sinovatech.unicom.separatemodule.skin.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundEntity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import com.sinovatech.unicom.separatemodule.skin.function.BackgroundFunction;
import com.sinovatech.unicom.separatemodule.skin.function.BackgroundMoreFunction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeBackgroundInfoManager {
    private static final String TAG = "HomeBackgroundInfoManager";
    private AppCompatActivity activityContext;

    public HomeBackgroundInfoManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public ObservableSubscribeProxy<BackgroundEntity> getBackGroundData(BackgroundSkinBean backgroundSkinBean) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("appVersion", this.activityContext.getString(2131886969));
            hashMap.put("province", UserManager.getInstance().getCurrentProvinceCode());
            hashMap.put("city", UserManager.getInstance().getCurrentCityCode());
            hashMap.put("showType", "all");
            hashMap.put("currentPage", "1");
            hashMap.put("appId", DeviceHelper.getDeviceID(false));
            hashMap.put("appSerial", System.currentTimeMillis() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getSkinUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new BackgroundFunction(backgroundSkinBean)).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<List<BackgroundSkinBean>> getBackGroundMoreData(String str, String str2, int i, BackgroundSkinBean backgroundSkinBean) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("appVersion", this.activityContext.getString(2131886969));
            hashMap.put("province", UserManager.getInstance().getCurrentProvinceCode());
            hashMap.put("city", UserManager.getInstance().getCurrentCityCode());
            hashMap.put("showType", str);
            hashMap.put("currentPage", String.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getSkinUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new BackgroundMoreFunction(str, backgroundSkinBean, str2)).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }
}
