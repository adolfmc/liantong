package com.vivo.push.cache;

import android.content.Context;
import com.vivo.push.util.LogUtil;
import java.lang.reflect.Method;

/* renamed from: com.vivo.push.cache.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ConfigManagerFactory {

    /* renamed from: a */
    private static volatile ConfigManagerFactory f20928a;

    /* renamed from: b */
    private IConfigManager f20929b;

    private ConfigManagerFactory() {
    }

    /* renamed from: a */
    public static synchronized ConfigManagerFactory m5749a() {
        ConfigManagerFactory configManagerFactory;
        synchronized (ConfigManagerFactory.class) {
            if (f20928a == null) {
                f20928a = new ConfigManagerFactory();
            }
            configManagerFactory = f20928a;
        }
        return configManagerFactory;
    }

    /* renamed from: a */
    public final IConfigManager m5748a(Context context) {
        IConfigManager iConfigManager = this.f20929b;
        if (iConfigManager != null) {
            return iConfigManager;
        }
        try {
            Method method = Class.forName("com.vivo.push.cache.ClientConfigManagerImpl").getMethod("getInstance", Context.class);
            LogUtil.m5341d("ConfigManagerFactory", "createConfig success is ".concat(String.valueOf("com.vivo.push.cache.ClientConfigManagerImpl")));
            this.f20929b = (IConfigManager) method.invoke(null, context);
            return this.f20929b;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5345b("ConfigManagerFactory", "createConfig error", e);
            return null;
        }
    }
}
