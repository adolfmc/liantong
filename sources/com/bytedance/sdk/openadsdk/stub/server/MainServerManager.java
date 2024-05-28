package com.bytedance.sdk.openadsdk.stub.server;

import android.app.Application;
import android.content.Context;
import com.bytedance.pangle.servermanager.AbsServerManager;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.api.plugin.C4021u;
import com.bytedance.sdk.openadsdk.p186mb.C4071ox;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MainServerManager extends AbsServerManager {
    @Override // com.bytedance.pangle.servermanager.AbsServerManager, android.content.ContentProvider
    public boolean onCreate() {
        Context applicationContext = getContext().getApplicationContext();
        TTAppContextHolder.setContext(applicationContext);
        C4021u.m16446mb(applicationContext);
        if (applicationContext instanceof Application) {
            ((Application) applicationContext).registerActivityLifecycleCallbacks(C4071ox.m16352mb().m16350ox());
        }
        return super.onCreate();
    }
}
