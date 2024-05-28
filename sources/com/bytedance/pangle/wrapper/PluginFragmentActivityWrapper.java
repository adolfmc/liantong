package com.bytedance.pangle.wrapper;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.ContextWrapper;
import android.support.annotation.Keep;
import android.support.p083v4.app.FragmentActivity;
import com.bytedance.pangle.C3768a;
import com.bytedance.pangle.PluginContext;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.util.C3938a;
import com.bytedance.pangle.util.C3950i;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.Field;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PluginFragmentActivityWrapper extends GenerateFragmentActivityWrapper {
    boolean hasInit = true;

    public PluginFragmentActivityWrapper(Activity activity, PluginContext pluginContext) {
        this.mOriginActivity = (FragmentActivity) activity;
        this.pluginContext = pluginContext;
        if (!this.mOriginActivity.isDestroyed()) {
            Zeus.getAppApplication().registerActivityLifecycleCallbacks(new C3768a() { // from class: com.bytedance.pangle.wrapper.PluginFragmentActivityWrapper.1
                @Override // com.bytedance.pangle.C3768a, android.app.Application.ActivityLifecycleCallbacks
                public final void onActivityDestroyed(Activity activity2) {
                    super.onActivityDestroyed(activity2);
                    if (activity2 == PluginFragmentActivityWrapper.this.mOriginActivity) {
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

    @Override // com.bytedance.pangle.wrapper.GenerateFragmentActivityWrapper, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.arch.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        if (!this.hasInit) {
            try {
                LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
                try {
                    Field declaredField = lifecycleRegistry.getClass().getDeclaredField("mEnforceMainThread");
                    declaredField.setAccessible(true);
                    declaredField.set(lifecycleRegistry, Boolean.FALSE);
                } catch (Throwable unused) {
                }
                return lifecycleRegistry;
            } catch (Throwable unused2) {
            }
        }
        return super.getLifecycle();
    }

    public Activity getOriginActivity() {
        return this.mOriginActivity;
    }
}
