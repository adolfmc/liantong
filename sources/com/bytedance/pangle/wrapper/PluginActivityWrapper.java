package com.bytedance.pangle.wrapper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContextWrapper;
import android.support.annotation.Keep;
import com.bytedance.pangle.C3768a;
import com.bytedance.pangle.PluginContext;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.util.C3938a;
import com.bytedance.pangle.util.C3950i;
import com.bytedance.pangle.util.FieldUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
@SuppressLint({"NewApi"})
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PluginActivityWrapper extends GenerateActivityWrapper {
    public PluginActivityWrapper(Activity activity, PluginContext pluginContext) {
        this.mOriginActivity = activity;
        this.pluginContext = pluginContext;
        if (!this.mOriginActivity.isDestroyed()) {
            Zeus.getAppApplication().registerActivityLifecycleCallbacks(new C3768a() { // from class: com.bytedance.pangle.wrapper.PluginActivityWrapper.1
                @Override // com.bytedance.pangle.C3768a, android.app.Application.ActivityLifecycleCallbacks
                public final void onActivityDestroyed(Activity activity2) {
                    super.onActivityDestroyed(activity2);
                    if (activity2 == PluginActivityWrapper.this.mOriginActivity) {
                        Zeus.getAppApplication().unregisterActivityLifecycleCallbacks(this);
                    }
                }
            });
        }
        try {
            FieldUtils.writeField(this, "mBase", pluginContext);
            if (!C3950i.m16623a()) {
                FieldUtils.writeField(FieldUtils.getField(ContextWrapper.class, "mBase"), this, pluginContext);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            FieldUtils.writeField(this, "mApplication", activity.getApplication());
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        C3938a.m16657a(this, activity);
    }
}
