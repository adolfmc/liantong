package com.bytedance.pangle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.p083v4.app.Fragment;
import android.text.TextUtils;
import com.bytedance.pangle.activity.IntentUtils;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.receiver.C3895a;
import com.bytedance.pangle.receiver.C3903c;
import com.bytedance.pangle.receiver.PluginBroadcastReceiver;
import com.bytedance.pangle.util.MethodUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ComponentManager {
    public static HashMap<String, List<String>> stubActivity2TargetActivities = new HashMap<>();
    public static HashMap<String, String> targetString2StubActivity = new HashMap<>();
    public static HashMap<String, String> targetString2PluginPkgName = new HashMap<>();

    private static boolean isSupportLibIso(String str) {
        try {
            return PluginManager.getInstance().getPlugin(str).mIsSupportLibIso;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void registerActivity(String str, String str2, String... strArr) {
        if (strArr != null) {
            for (String str3 : strArr) {
                String str4 = targetString2PluginPkgName.get(str3);
                if (str4 != null && TextUtils.equals(str4, str)) {
                    throw new RuntimeException(String.format("%s is repeated in %s and %s", str3, str, str4));
                }
                List<String> list = stubActivity2TargetActivities.get(str2);
                if (list == null) {
                    list = new ArrayList<>();
                    stubActivity2TargetActivities.put(str2, list);
                }
                list.add(str3);
                targetString2StubActivity.put(str3, str2);
                targetString2PluginPkgName.put(str3, str);
            }
        }
    }

    public static void startActivity(Object obj, Intent intent, Bundle bundle, String str) {
        if (obj instanceof Context) {
            realStartActivity(null, (Context) obj, intent, bundle, str);
        } else if (!isSupportLibIso(str) && (obj instanceof Fragment)) {
            realStartActivity(obj, ((Fragment) obj).getActivity(), intent, bundle, str);
        } else if (obj instanceof android.app.Fragment) {
            realStartActivity(obj, ((android.app.Fragment) obj).getActivity(), intent, bundle, str);
        } else {
            try {
                MethodUtils.invokeMethod(obj, "startActivity", new Object[]{intent, bundle}, new Class[]{Intent.class, Bundle.class});
            } catch (Throwable th) {
                throw new RuntimeException(th);
            }
        }
    }

    public static void startActivity(Context context, Intent intent, String str) {
        realStartActivity(null, context, intent, null, str);
    }

    public static void startActivity(Context context, Intent intent, Bundle bundle, String str) {
        realStartActivity(null, context, intent, bundle, str);
    }

    public static void realStartActivity(Object obj, Context context, Intent intent, Bundle bundle, String str) {
        if (context instanceof PluginContext) {
            context = ((PluginContext) context).mOriginContext;
        }
        if (intent.getComponent() != null) {
            String className = intent.getComponent().getClassName();
            Zeus.loadPlugin(str);
            String targetStubActivityString = getTargetStubActivityString(className);
            if (!TextUtils.isEmpty(targetStubActivityString)) {
                IntentUtils.m16980a(intent, str);
                intent.setComponent(new ComponentName(context, targetStubActivityString));
                intent.putExtra("targetPlugin", className);
            }
        }
        try {
            if (!isSupportLibIso(str) && (obj instanceof Fragment)) {
                ((Fragment) obj).startActivity(intent, bundle);
            } else if (obj instanceof android.app.Fragment) {
                ((android.app.Fragment) obj).startActivity(intent, bundle);
            } else {
                context.startActivity(intent, bundle);
            }
        } catch (Throwable th) {
            throw new RuntimeException("component = " + intent.getComponent(), th);
        }
    }

    public static void startActivityForResult(Object obj, Intent intent, int i, Bundle bundle, String str) {
        if (obj instanceof Activity) {
            realStartActivityForResult(null, (Activity) obj, intent, i, null, str);
        } else if (!isSupportLibIso(str) && (obj instanceof Fragment)) {
            realStartActivityForResult(obj, ((Fragment) obj).getActivity(), intent, i, null, str);
        } else if (obj instanceof android.app.Fragment) {
            realStartActivityForResult(obj, ((android.app.Fragment) obj).getActivity(), intent, i, null, str);
        } else {
            try {
                MethodUtils.invokeMethod(obj, "startActivityForResult", new Object[]{intent, Integer.valueOf(i), bundle}, new Class[]{Intent.class, Integer.TYPE, Bundle.class});
            } catch (Throwable th) {
                throw new RuntimeException(th);
            }
        }
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle, String str) {
        realStartActivityForResult(null, activity, intent, i, bundle, str);
    }

    private static void realStartActivityForResult(Object obj, Activity activity, Intent intent, int i, Bundle bundle, String str) {
        if (intent.getComponent() != null) {
            String className = intent.getComponent().getClassName();
            Zeus.loadPlugin(str);
            String targetStubActivityString = getTargetStubActivityString(className);
            if (!TextUtils.isEmpty(targetStubActivityString)) {
                IntentUtils.m16980a(intent, str);
                intent.setComponent(new ComponentName(activity, targetStubActivityString));
                intent.putExtra("targetPlugin", className);
            }
        }
        try {
            if (!isSupportLibIso(str) && (obj instanceof Fragment)) {
                ((Fragment) obj).startActivityForResult(intent, i, bundle);
            } else if (obj instanceof android.app.Fragment) {
                ((android.app.Fragment) obj).startActivityForResult(intent, i, bundle);
            } else {
                activity.startActivityForResult(intent, i, bundle);
            }
        } catch (Throwable th) {
            throw new RuntimeException("component =  " + intent.getComponent(), th);
        }
    }

    public static String getTargetStubActivityString(String str) {
        return targetString2StubActivity.get(str);
    }

    public static Intent registerReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter, String str) {
        Zeus.loadPlugin(str);
        C3903c m16735a = C3903c.m16735a();
        if (intentFilter == null || intentFilter.actionsIterator() == null) {
            return null;
        }
        if (pluginBroadcastReceiver == null) {
            return context.registerReceiver(null, intentFilter);
        }
        C3895a c3895a = new C3895a();
        Intent registerReceiver = context.registerReceiver(c3895a, intentFilter);
        m16735a.f9286b.put(pluginBroadcastReceiver, c3895a);
        m16735a.m16733a(intentFilter, pluginBroadcastReceiver);
        return registerReceiver;
    }

    public static Intent registerReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter, String str, Handler handler, String str2) {
        Zeus.loadPlugin(str2);
        C3903c m16735a = C3903c.m16735a();
        if (intentFilter == null || intentFilter.actionsIterator() == null) {
            return null;
        }
        if (pluginBroadcastReceiver == null) {
            return context.registerReceiver(null, intentFilter);
        }
        C3895a c3895a = new C3895a();
        Intent registerReceiver = context.registerReceiver(c3895a, intentFilter, str, handler);
        m16735a.f9286b.put(pluginBroadcastReceiver, c3895a);
        if (handler != null) {
            m16735a.f9287c.add(Integer.valueOf(c3895a.hashCode()));
        }
        m16735a.m16733a(intentFilter, pluginBroadcastReceiver);
        return registerReceiver;
    }

    public static Intent registerReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter, int i, String str) {
        Zeus.loadPlugin(str);
        C3903c m16735a = C3903c.m16735a();
        if (intentFilter == null || intentFilter.actionsIterator() == null || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        if (pluginBroadcastReceiver == null) {
            return context.registerReceiver(null, intentFilter);
        }
        C3895a c3895a = new C3895a();
        Intent registerReceiver = context.registerReceiver(c3895a, intentFilter, i);
        m16735a.f9286b.put(pluginBroadcastReceiver, c3895a);
        m16735a.m16733a(intentFilter, pluginBroadcastReceiver);
        return registerReceiver;
    }

    public static Intent registerReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i, String str2) {
        Zeus.loadPlugin(str2);
        C3903c m16735a = C3903c.m16735a();
        if (intentFilter == null || intentFilter.actionsIterator() == null || Build.VERSION.SDK_INT < 26) {
            return null;
        }
        if (pluginBroadcastReceiver == null) {
            return context.registerReceiver(null, intentFilter);
        }
        C3895a c3895a = new C3895a();
        Intent registerReceiver = context.registerReceiver(c3895a, intentFilter, str, handler, i);
        m16735a.f9286b.put(pluginBroadcastReceiver, c3895a);
        if (handler != null) {
            m16735a.f9287c.add(Integer.valueOf(c3895a.hashCode()));
        }
        m16735a.m16733a(intentFilter, pluginBroadcastReceiver);
        return registerReceiver;
    }

    public static void unregisterReceiver(Context context, PluginBroadcastReceiver pluginBroadcastReceiver) {
        boolean remove;
        if (pluginBroadcastReceiver != null) {
            C3903c m16735a = C3903c.m16735a();
            for (Map.Entry<String, C3903c.C3904a> entry : m16735a.f9285a.entrySet()) {
                C3903c.C3904a value = entry.getValue();
                if (value != null && pluginBroadcastReceiver != null) {
                    try {
                        if (value.f9289b.size() > 0 && (remove = value.f9289b.remove(pluginBroadcastReceiver))) {
                            ZeusLogger.m16794d("Zeus/receiver_pangle", "unregisterReceiver->[移除Action]:" + value.f9288a + ",receiver.hashCode()=" + pluginBroadcastReceiver.hashCode() + ",是否成功，success:" + remove);
                        }
                    } catch (Throwable th) {
                        ZeusLogger.m16787w("Zeus/receiver_pangle", "unregisterReceiver-plugin-receiver->action:" + value.f9288a + "[exception]:", th);
                    }
                }
                BroadcastReceiver broadcastReceiver = m16735a.f9286b.get(pluginBroadcastReceiver);
                if (broadcastReceiver != null) {
                    try {
                        m16735a.f9287c.remove(Integer.valueOf(broadcastReceiver.hashCode()));
                        m16735a.f9286b.remove(pluginBroadcastReceiver);
                        context.unregisterReceiver(broadcastReceiver);
                    } catch (Throwable th2) {
                        ZeusLogger.m16787w("Zeus/receiver_pangle", "unregisterReceiver-移除系统注册的广播发生异常:", th2);
                    }
                }
            }
        }
    }
}
