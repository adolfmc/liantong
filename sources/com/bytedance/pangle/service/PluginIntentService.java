package com.bytedance.pangle.service;

import android.app.IntentService;
import android.content.ComponentName;
import android.support.annotation.Keep;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.service.p182a.BinderC3929a;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class PluginIntentService extends IntentService implements InterfaceC3928a {
    private static final String TAG = "PluginService";

    public PluginIntentService(String str) {
        super(str);
    }

    @Override // com.bytedance.pangle.service.InterfaceC3928a
    public void attach(Plugin plugin) {
        attachBaseContext(ZeusTransformUtils.wrapperContext2Application(Zeus.getAppApplication(), plugin.mPkgName));
        try {
            FieldUtils.writeField(this, "mActivityManager", createActivityManagerProxy());
            FieldUtils.writeField(this, "mClassName", getClass().getName());
            FieldUtils.writeField(this, "mApplication", Zeus.getAppApplication());
            FieldUtils.writeField(this, "mStartCompatibility", Boolean.valueOf(getApplicationInfo().targetSdkVersion < 5));
        } catch (Exception e) {
            ZeusLogger.errReport("Zeus/service_pangle", "hook activityManager failed!", e);
        }
    }

    protected Object createActivityManagerProxy() {
        return Proxy.newProxyInstance(getClassLoader(), new Class[]{Class.forName("android.app.IActivityManager")}, new InvocationHandler() { // from class: com.bytedance.pangle.service.PluginIntentService.1
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, Method method, Object[] objArr) {
                char c;
                String name = method.getName();
                int hashCode = name.hashCode();
                if (hashCode == 39551382) {
                    if (name.equals("setServiceForeground")) {
                        c = 1;
                    }
                    c = 65535;
                } else if (hashCode != 690954390) {
                    if (hashCode == 1930712422 && name.equals("stopServiceToken")) {
                        c = 0;
                    }
                    c = 65535;
                } else {
                    if (name.equals("getForegroundServiceType")) {
                        c = 2;
                    }
                    c = 65535;
                }
                switch (c) {
                    case 0:
                        BinderC3929a m16670b = BinderC3929a.m16670b();
                        PluginIntentService pluginIntentService = PluginIntentService.this;
                        return Boolean.valueOf(m16670b.m16678a(new ComponentName(pluginIntentService, pluginIntentService.getClass().getName())));
                    case 1:
                    default:
                        return null;
                    case 2:
                        return 0;
                }
            }
        });
    }
}
