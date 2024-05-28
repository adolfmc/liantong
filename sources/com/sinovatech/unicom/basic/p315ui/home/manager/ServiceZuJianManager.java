package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.basic.p315ui.home.entity.ServiceViewEntity;
import com.sinovatech.unicom.basic.p315ui.home.function.ServiceZuJianFuction;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.ServiceZuJianManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ServiceZuJianManager {
    private AppCompatActivity activityContext;

    public ServiceZuJianManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public ObservableSubscribeProxy<ServiceViewEntity> getService() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("version", this.activityContext.getString(2131886969));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getServiceZuJianUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new ServiceZuJianFuction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }
}
