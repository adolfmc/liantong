package com.sinovatech.unicom.separatemodule.user.manager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.hjq.gson.factory.GsonFactory;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.sinovatech.unicom.basic.p315ui.home.manager.BaseFunction;
import com.sinovatech.unicom.basic.p315ui.home.manager.BaseRequestManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.entity.UserDeviceEntity;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.util.HashMap;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserDeviceRequestManager extends BaseRequestManager<UserDeviceEntity> {
    private final String TAG;

    public UserDeviceRequestManager(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        this.TAG = "UserDeviceRequestManager";
    }

    public ObservableSubscribeProxy<UserDeviceEntity> getUserDevice(String str) {
        return setRequestType(str).setNetObserver(App.getAsyncHttpClient().rxGet(URLSet.getUserDevice(), new HashMap(), getHeader())).setFunction(new BaseFunction<UserDeviceEntity>() { // from class: com.sinovatech.unicom.separatemodule.user.manager.UserDeviceRequestManager.1
            @Override // io.reactivex.functions.Function
            public UserDeviceEntity apply(String str2) throws Exception {
                MsLogUtil.m7979d("UserDeviceRequestManager", str2);
                UserDeviceEntity userDeviceEntity = new UserDeviceEntity();
                Gson singletonGson = GsonFactory.getSingletonGson();
                if (TextUtils.isEmpty(str2)) {
                    return userDeviceEntity;
                }
                return (UserDeviceEntity) (!(singletonGson instanceof Gson) ? singletonGson.fromJson(str2, (Class<Object>) UserDeviceEntity.class) : NBSGsonInstrumentation.fromJson(singletonGson, str2, (Class<Object>) UserDeviceEntity.class));
            }
        }).getObservable();
    }
}
