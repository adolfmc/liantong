package com.xiaomi.push.service;

import android.app.Notification;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11395g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.ag */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11534ag {

    /* renamed from: a */
    static Boolean f23494a;

    /* renamed from: a */
    private static final String[] f23496a = {"com.mi.globalbrowser", "com.android.browser"};

    /* renamed from: a */
    private static String f23495a = null;

    /* renamed from: a */
    public static final C11536a<String, String, String> f23493a = new C11536a<>("getNotificationSettings", "getNotificationSettings", "getNotificationSettings");

    /* renamed from: b */
    public static final C11536a<String, String, String> f23497b = new C11536a<>("setSound", "canSound", "canSound");

    /* renamed from: c */
    public static final C11536a<String, String, String> f23498c = new C11536a<>("setVibrate", "canVibrate", "canVibrate");

    /* renamed from: d */
    public static final C11536a<String, String, String> f23499d = new C11536a<>("setLights", "canLights", "canLights");

    /* renamed from: e */
    public static final C11536a<String, String, String> f23500e = new C11536a<>("setShowOnKeyguard", "canShowOnKeyguard", "canShowOnKeyguard");

    /* renamed from: f */
    public static final C11536a<String, String, String> f23501f = new C11536a<>("setFloat", "canFloat", "canShowFloat");

    /* renamed from: g */
    public static final C11536a<String, String, String> f23502g = new C11536a<>("setShowBadge", "canShowBadge", "canShowBadge");

    /* renamed from: h */
    public static final C11536a<String, String, String> f23503h = new C11536a<>("setShowOngoing", "canShowOngoing", "canShowOngoing");

    /* renamed from: a */
    public static boolean m2745a() {
        if (f23494a == null) {
            if (Build.VERSION.SDK_INT >= 30) {
                Bundle m2546a = C11591f.m2546a("com.xiaomi.xmsf", (String) null);
                if (m2546a == null || m2546a.isEmpty()) {
                    f23494a = false;
                } else {
                    f23494a = true;
                }
            } else {
                f23494a = false;
            }
        }
        return f23494a.booleanValue();
    }

    /* renamed from: a */
    public static String m2743a(Notification notification) {
        CharSequence charSequence;
        if (notification.extras != null) {
            charSequence = notification.extras.getCharSequence("android.title");
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = notification.extras.getCharSequence("android.title.big");
            }
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = notification.extras.getCharSequence("mipush.customTitle");
            }
        } else {
            charSequence = null;
        }
        return charSequence != null ? charSequence.toString() : "";
    }

    /* renamed from: b */
    public static String m2724b(Notification notification) {
        CharSequence charSequence;
        if (notification.extras != null) {
            charSequence = notification.extras.getCharSequence("android.text");
            if (TextUtils.isEmpty(charSequence) && Build.VERSION.SDK_INT >= 21) {
                charSequence = notification.extras.getCharSequence("android.bigText");
            }
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = notification.extras.getCharSequence("mipush.customContent");
            }
        } else {
            charSequence = null;
        }
        return charSequence != null ? charSequence.toString() : "";
    }

    /* renamed from: a */
    public static Notification.Action[] m2742a(Notification notification) {
        Parcelable[] parcelableArray;
        if (notification.actions != null) {
            return notification.actions;
        }
        if (notification.extras == null || (parcelableArray = notification.extras.getParcelableArray("mipush.customActions")) == null) {
            return null;
        }
        return (Notification.Action[]) Arrays.copyOf(parcelableArray, parcelableArray.length, Notification.Action[].class);
    }

    /* renamed from: a */
    public static <T> T m2739a(Notification notification, String str) {
        if (notification.extras != null) {
            try {
                return (T) notification.extras.get(str);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static void m2725a(Map<String, String> map, Bundle bundle, String str) {
        if (map == null || bundle == null || TextUtils.isEmpty(str)) {
            AbstractC11049b.m5282a("cp map to b fail:" + str);
        } else if (TextUtils.isEmpty(map.get(str))) {
            bundle.remove(str);
        } else {
            bundle.putString(str, map.get(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2738a(Notification notification, String str) {
        try {
            if (notification.extras != null) {
                notification.extras.putString("target_package", str);
            }
            Object m4814a = C11176aw.m4814a(notification, "extraNotification");
            if (m4814a != null) {
                C11176aw.m4812a(m4814a, "setTargetPkg", str);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2737a(Notification notification, boolean z) {
        try {
            if (notification.extras != null) {
                notification.extras.putBoolean("miui.enableFloat", z);
            }
            Object m4814a = C11176aw.m4814a(notification, "extraNotification");
            if (m4814a != null) {
                C11176aw.m4812a(m4814a, "setEnableFloat", Boolean.valueOf(z));
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public static void m2723b(Notification notification, boolean z) {
        try {
            if (notification.extras != null) {
                notification.extras.putBoolean("miui.enableKeyguard", z);
            }
            Object m4814a = C11176aw.m4814a(notification, "extraNotification");
            if (m4814a != null) {
                C11176aw.m4812a(m4814a, "setEnableKeyguard", Boolean.valueOf(z));
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2741a(Notification notification, int i) {
        try {
            if (notification.extras != null) {
                notification.extras.putInt("miui.messageCount", i);
            }
            Object m4814a = C11176aw.m4814a(notification, "extraNotification");
            if (m4814a != null) {
                C11176aw.m4812a(m4814a, "setMessageCount", Integer.valueOf(i));
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2740a(Notification notification, int i, int i2) {
        if (notification != null) {
            if (notification.extras == null) {
                notification.extras = new Bundle();
            }
            notification.extras.putInt("is_priority", i);
            notification.extras.putInt("mipush_class", i2);
        }
    }

    /* renamed from: c */
    public static String m2722c(Notification notification) {
        Object m4814a;
        try {
            r0 = notification.extras != null ? notification.extras.getString("target_package") : null;
            return (!TextUtils.isEmpty(r0) || (m4814a = C11176aw.m4814a(notification, "extraNotification")) == null) ? r0 : (String) C11176aw.m4812a(m4814a, "getTargetPkg", new Object[0]);
        } catch (Exception unused) {
            return r0;
        }
    }

    /* renamed from: a */
    public static String m2728a(Object obj) {
        return (String) m2727a(obj, "msg_busi_type", "");
    }

    /* renamed from: a */
    public static <T> T m2727a(Object obj, String str, T t) {
        Object obj2 = null;
        try {
            if (obj instanceof Notification) {
                obj2 = m2739a((Notification) obj, str);
            } else if (obj instanceof Map) {
                obj2 = ((Map) obj).get(str);
            } else if (obj instanceof Bundle) {
                obj2 = ((Bundle) obj).get(str);
            } else {
                AbstractC11049b.m5282a("not support get value from classType:" + obj);
            }
        } catch (Exception e) {
            AbstractC11049b.m5282a("get value error " + e);
        }
        return obj2 == null ? t : (T) obj2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m2734a(Context context, String str) {
        return C11395g.m3713b(context, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2733a(Context context, String str, Intent intent) {
        if (intent == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(str);
        }
        arrayList.addAll(Arrays.asList(f23496a));
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            String str2 = (String) arrayList.get(i);
            if (!TextUtils.isEmpty(str2)) {
                Intent intent2 = new Intent(intent);
                intent2.setPackage(str2);
                try {
                    if (context.getPackageManager().resolveActivity(intent2, 65536) != null) {
                        intent.setPackage(str2);
                        break;
                    }
                    continue;
                } catch (Exception e) {
                    AbstractC11049b.m5282a("can't match url intent. " + e);
                }
            }
        }
        intent.setPackage(intent.getPackage());
    }

    /* renamed from: a */
    public static int m2736a(ContentResolver contentResolver) {
        try {
            return Settings.Global.getInt(contentResolver, "user_aggregate", 0);
        } catch (Exception e) {
            AbstractC11049b.m5282a("get user aggregate failed, " + e);
            return 0;
        }
    }

    /* renamed from: a */
    public static boolean m2735a(ContentResolver contentResolver) {
        int m2736a = m2736a(contentResolver);
        return m2736a == 1 || m2736a == 2;
    }

    /* renamed from: a */
    public static boolean m2726a(Map<String, String> map) {
        return Boolean.parseBoolean((String) m2727a(map, "not_suppress", "true"));
    }

    /* renamed from: a */
    public static boolean m2744a(Notification.Builder builder, boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setGroupAlertBehavior(z ? 2 : 1);
            return true;
        }
        AbstractC11049b.m5274b("not support setGroupAlertBehavior");
        return false;
    }

    /* renamed from: a */
    public static int m2731a(Context context, String str, String str2, C11536a<String, String, String> c11536a) {
        if (c11536a != null) {
            try {
                Bundle m2729a = m2729a(context, c11536a.f23505b, str, str2, (Bundle) null);
                if (m2729a == null || !m2729a.containsKey(c11536a.f23506c)) {
                    return -1;
                }
                return m2729a.getBoolean(c11536a.f23506c) ? 1 : 0;
            } catch (Exception unused) {
                return -1;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public static Bundle m2732a(Context context, String str, String str2) {
        try {
            return m2729a(context, f23493a.f23505b, str, str2, (Bundle) null);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m2730a(Context context, String str, String str2, C11536a<String, String, String> c11536a, boolean z) {
        if (c11536a != null) {
            try {
                Bundle bundle = new Bundle();
                bundle.putBoolean(c11536a.f23506c, z);
                m2729a(context, c11536a.f23504a, str, str2, bundle);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* renamed from: a */
    private static Bundle m2729a(Context context, String str, String str2, String str3, Bundle bundle) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("package", str2);
            if (!TextUtils.isEmpty(str3)) {
                bundle2.putString("channel_id", str3);
            }
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            return context.getContentResolver().call(Uri.parse("content://statusbar.notification"), str, (String) null, bundle2);
        }
        throw new IllegalArgumentException("call notification provider failed!");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.ag$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11536a<F, S, T> {

        /* renamed from: a */
        F f23504a;

        /* renamed from: b */
        S f23505b;

        /* renamed from: c */
        T f23506c;

        private C11536a(F f, S s, T t) {
            this.f23504a = f;
            this.f23505b = s;
            this.f23506c = t;
        }
    }
}
