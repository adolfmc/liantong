package com.bytedance.pangle;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Keep;
import com.bytedance.pangle.plugin.Plugin;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ZeusApplication extends PluginContext {
    Application mHostApplication;

    public void onCreate() {
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public void attach(Plugin plugin, Application application) {
        this.mPlugin = plugin;
        this.mHostApplication = application;
        attachBaseContext(application);
        onCreate();
    }
}
