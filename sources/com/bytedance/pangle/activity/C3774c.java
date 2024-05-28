package com.bytedance.pangle.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatViewInflater;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.pangle.ComponentManager;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.util.FieldUtils;
import com.bytedance.pangle.util.MethodUtils;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.activity.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3774c {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m16975a(InterfaceC3773b interfaceC3773b, Bundle bundle) {
        List<String> list;
        if (!Zeus.hasInit()) {
            Log.e("Zeus/init_pangle", "ProxyActivityUtils.onCreate finish. AppApplication == null.");
            interfaceC3773b.zeusSuperOnCreate(null);
            interfaceC3773b.finish();
            return;
        }
        Intent intent = interfaceC3773b.getIntent();
        String pluginPkgName = interfaceC3773b.getPluginPkgName();
        Plugin plugin = interfaceC3773b.getPlugin();
        intent.setExtrasClassLoader(plugin.mClassLoader);
        IntentUtils.m16981a(intent);
        String stringExtra = intent.getStringExtra("targetPlugin");
        if (TextUtils.isEmpty(stringExtra) && (list = ComponentManager.stubActivity2TargetActivities.get(interfaceC3773b.getClass().getName())) != null && list.size() == 1) {
            stringExtra = list.get(0);
            intent.putExtra("targetPlugin", stringExtra);
        }
        if (!plugin.isLoaded() || TextUtils.isEmpty(stringExtra)) {
            try {
                interfaceC3773b.zeusSuperOnCreate(null);
                ZeusLogger.m16788w("Zeus/activity_pangle", "Cant start pluginActivity, plugin load failed! pluginPkgName: " + pluginPkgName + " targetActivity: " + stringExtra);
                interfaceC3773b.finish();
                return;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ActivityInfo activityInfo = plugin.pluginActivities.get(stringExtra);
        if (activityInfo == null) {
            ZeusLogger.m16788w("Zeus/activity_pangle", "Have you declared " + stringExtra + " in plugin's AndroidManifest.xml!");
            interfaceC3773b.zeusSuperOnCreate(null);
            interfaceC3773b.finish();
        }
        int i = -1;
        try {
            IPluginActivity iPluginActivity = (IPluginActivity) plugin.mClassLoader.loadClass(stringExtra).newInstance();
            FieldUtils.writeField(iPluginActivity, "mApplication", interfaceC3773b.getApplication());
            interfaceC3773b.setTargetActivity(iPluginActivity);
            iPluginActivity.setPluginProxyActivity(interfaceC3773b, plugin);
            i = activityInfo.theme;
            interfaceC3773b.zeusSuperSetTheme(i);
            TypedArray obtainStyledAttributes = ((Activity) interfaceC3773b).getTheme().obtainStyledAttributes(new int[]{16842840});
            if (obtainStyledAttributes.getBoolean(obtainStyledAttributes.getIndex(0), false)) {
                m16978a((Activity) interfaceC3773b);
            }
            obtainStyledAttributes.recycle();
            iPluginActivity.attachBaseContext(interfaceC3773b.getBaseContext());
            try {
                if (((Activity) interfaceC3773b).getRequestedOrientation() != activityInfo.screenOrientation) {
                    ((Activity) interfaceC3773b).setRequestedOrientation(activityInfo.screenOrientation);
                }
            } catch (IllegalStateException unused) {
            }
            iPluginActivity.onCreate(bundle);
        } catch (Exception e2) {
            throw new RuntimeException(e2 instanceof IllegalStateException ? "activityTheme:".concat(String.valueOf(i)) : "", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m16976a(InterfaceC3773b interfaceC3773b, Context context) {
        if (!Zeus.hasInit()) {
            Log.e("Zeus/init_pangle", "ProxyActivityUtils.attachBaseContext. AppApplication == null.");
            interfaceC3773b.zeusSuperAttachBaseContext(context);
            return;
        }
        String pluginPkgName = interfaceC3773b.getPluginPkgName();
        boolean loadPlugin = PluginManager.getInstance().loadPlugin(pluginPkgName);
        try {
            interfaceC3773b.setPlugin(PluginManager.getInstance().getPlugin(pluginPkgName));
            if (loadPlugin) {
                interfaceC3773b.zeusSuperAttachBaseContext(ZeusTransformUtils.wrapperContext(context, pluginPkgName));
                FieldUtils.writeField(interfaceC3773b, "mResources", (Object) null);
                return;
            }
            interfaceC3773b.zeusSuperAttachBaseContext(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m16977a(Activity activity, View view) {
        Object readField;
        if (view == null) {
            return;
        }
        try {
            Object readField2 = FieldUtils.readField(view, "mListenerInfo");
            if (readField2 != null && (readField = FieldUtils.readField(readField2, "mOnClickListener")) != null) {
                String name = readField.getClass().getName();
                if (name.startsWith(AppCompatViewInflater.class.getName()) || name.startsWith(View.class.getName())) {
                    view.setOnClickListener(new View$OnClickListenerC3772a(activity, view.getId(), (String) FieldUtils.readField(readField, "mMethodName")));
                }
            }
        } catch (Exception e) {
            ZeusLogger.errReport("Zeus/activity_pangle", "checkOnClickListener failed!".concat(String.valueOf(view)));
            e.printStackTrace();
        }
        if (!(view instanceof ViewGroup)) {
            return;
        }
        int i = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i >= viewGroup.getChildCount()) {
                return;
            }
            m16977a(activity, viewGroup.getChildAt(i));
            i++;
        }
    }

    /* renamed from: a */
    private static Class m16979a() {
        Class<?>[] declaredClasses;
        Class<?> cls = null;
        for (Class<?> cls2 : Activity.class.getDeclaredClasses()) {
            if (cls2.getSimpleName().contains("TranslucentConversionListener")) {
                cls = cls2;
            }
        }
        return cls;
    }

    /* renamed from: a */
    private static void m16978a(Activity activity) {
        try {
            MethodUtils.getAccessibleMethod(Activity.class, "convertToTranslucent", m16979a(), ActivityOptions.class).invoke(activity, null, MethodUtils.getAccessibleMethod(Activity.class, "getActivityOptions", new Class[0]).invoke(activity, new Object[0]));
        } catch (Throwable unused) {
        }
    }
}
