package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11469j;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.ac */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11524ac {

    /* renamed from: a */
    private static final boolean f23471a = Log.isLoggable("NCHelper", 3);

    @TargetApi(26)
    /* renamed from: a */
    private static void m2808a(C11533af c11533af, NotificationChannel notificationChannel, String str) {
        int i;
        char c;
        int i2;
        char c2;
        Context m2771a = c11533af.m2771a();
        String id = notificationChannel.getId();
        String m2753a = C11533af.m2753a(id, c11533af.m2770a());
        if (f23471a) {
            m2806a("appChannelId:" + id + " oldChannelId:" + m2753a);
        }
        boolean z = true;
        if (C11469j.m2972a(m2771a) && !TextUtils.equals(id, m2753a)) {
            NotificationManager notificationManager = (NotificationManager) m2771a.getSystemService("notification");
            NotificationChannel notificationChannel2 = notificationManager.getNotificationChannel(m2753a);
            NotificationChannel m2757a = c11533af.m2757a(id);
            if (f23471a) {
                m2806a("xmsfChannel:" + notificationChannel2);
                m2806a("appChannel:" + m2757a);
            }
            if (notificationChannel2 != null) {
                NotificationChannel m2805a = m2805a(id, notificationChannel2);
                if (f23471a) {
                    m2806a("copyXmsf copyXmsfChannel:" + m2805a);
                }
                if (m2757a != null) {
                    i2 = m2816a(m2757a);
                    c11533af.m2763a(m2805a, i2 == 0);
                    c2 = 3;
                } else {
                    int m2816a = m2816a(notificationChannel2);
                    m2813a(m2771a, c11533af, m2805a, m2816a, notificationChannel2.getId());
                    i2 = m2816a;
                    c2 = 4;
                }
                m2804b(m2771a, id);
                notificationManager.deleteNotificationChannel(m2753a);
                i = i2;
                c = c2;
            } else if (m2757a != null) {
                if (m2811a(m2771a, id) || !m2815a(notificationChannel, m2757a)) {
                    i = 0;
                    c = 0;
                } else {
                    if (f23471a) {
                        m2806a("appHack updateNotificationChannel:" + notificationChannel);
                    }
                    i = m2816a(m2757a);
                    c11533af.m2763a(notificationChannel, i == 0);
                    c = 2;
                }
            } else {
                if (f23471a) {
                    m2806a("appHack createNotificationChannel:" + notificationChannel);
                }
                c11533af.m2764a(notificationChannel);
                c = 1;
                i = 0;
            }
        } else {
            NotificationChannel m2757a2 = c11533af.m2757a(id);
            if (f23471a) {
                m2806a("elseLogic getNotificationChannel:" + m2757a2);
            }
            if (m2757a2 == null) {
                c11533af.m2764a(notificationChannel);
                i = 0;
                c = 0;
            } else {
                i = 0;
                c = 0;
            }
        }
        if (c != 1 && c != 4 && c != 3) {
            z = false;
        }
        C11591f.m2552a(c11533af.m2771a(), c11533af.m2770a(), id, notificationChannel.getImportance(), str, z, i);
    }

    @TargetApi(26)
    /* renamed from: a */
    private static boolean m2815a(NotificationChannel notificationChannel, NotificationChannel notificationChannel2) {
        boolean z;
        if (notificationChannel == null || notificationChannel2 == null) {
            return false;
        }
        if (TextUtils.equals(notificationChannel.getName(), notificationChannel2.getName())) {
            z = false;
        } else {
            if (f23471a) {
                m2806a("appHack channelConfigLowerCompare:getName");
            }
            z = true;
        }
        if (!TextUtils.equals(notificationChannel.getDescription(), notificationChannel2.getDescription())) {
            if (f23471a) {
                m2806a("appHack channelConfigLowerCompare:getDescription");
            }
            z = true;
        }
        if (notificationChannel.getImportance() != notificationChannel2.getImportance()) {
            notificationChannel.setImportance(Math.min(notificationChannel.getImportance(), notificationChannel2.getImportance()));
            if (f23471a) {
                m2806a("appHack channelConfigLowerCompare:getImportance  " + notificationChannel.getImportance() + " " + notificationChannel2.getImportance());
            }
            z = true;
        }
        if (notificationChannel.shouldVibrate() != notificationChannel2.shouldVibrate()) {
            notificationChannel.enableVibration(false);
            if (f23471a) {
                m2806a("appHack channelConfigLowerCompare:enableVibration");
            }
            z = true;
        }
        if (notificationChannel.shouldShowLights() != notificationChannel2.shouldShowLights()) {
            notificationChannel.enableLights(false);
            if (f23471a) {
                m2806a("appHack channelConfigLowerCompare:enableLights");
            }
            z = true;
        }
        if ((notificationChannel.getSound() != null) != (notificationChannel2.getSound() != null)) {
            notificationChannel.setSound(null, null);
            if (f23471a) {
                m2806a("appHack channelConfigLowerCompare:setSound");
            }
            z = true;
        }
        if (f23471a) {
            m2806a("appHack channelConfigLowerCompare:isDifferent:" + z);
        }
        return z;
    }

    /* renamed from: a */
    private static int m2816a(NotificationChannel notificationChannel) {
        int i = 0;
        try {
            i = ((Integer) C11176aw.m4804b((Object) notificationChannel, "getUserLockedFields", new Object[0])).intValue();
            if (f23471a) {
                m2806a("isUserLockedChannel:" + i + " " + notificationChannel);
            }
        } catch (Exception e) {
            AbstractC11049b.m5280a("NCHelper", "is user locked error" + e);
        }
        return i;
    }

    @TargetApi(26)
    /* renamed from: a */
    private static NotificationChannel m2805a(String str, NotificationChannel notificationChannel) {
        NotificationChannel notificationChannel2 = new NotificationChannel(str, notificationChannel.getName(), notificationChannel.getImportance());
        notificationChannel2.setDescription(notificationChannel.getDescription());
        notificationChannel2.enableVibration(notificationChannel.shouldVibrate());
        notificationChannel2.enableLights(notificationChannel.shouldShowLights());
        notificationChannel2.setSound(notificationChannel.getSound(), notificationChannel.getAudioAttributes());
        notificationChannel2.setLockscreenVisibility(notificationChannel.getLockscreenVisibility());
        return notificationChannel2;
    }

    /* renamed from: b */
    private static void m2804b(Context context, String str) {
        if (f23471a) {
            m2806a("recordCopiedChannel:" + str);
        }
        m2814a(context).edit().putBoolean(str, true).apply();
    }

    /* renamed from: a */
    private static boolean m2811a(Context context, String str) {
        if (f23471a) {
            m2806a("checkCopeidChannel:newFullChannelId:" + str + "  " + m2814a(context).getBoolean(str, false));
        }
        return m2814a(context).getBoolean(str, false);
    }

    /* renamed from: a */
    private static void m2810a(Context context, List<String> list) {
        if (f23471a) {
            m2806a("deleteCopiedChannelRecord:" + list);
        }
        if (list.isEmpty()) {
            return;
        }
        SharedPreferences.Editor edit = m2814a(context).edit();
        for (String str : list) {
            edit.remove(str);
        }
        edit.apply();
    }

    /* renamed from: a */
    private static SharedPreferences m2814a(Context context) {
        return context.getSharedPreferences("mipush_channel_copy_sp", 0);
    }

    @TargetApi(26)
    /* renamed from: a */
    public static String m2807a(C11533af c11533af, String str, CharSequence charSequence, String str2, int i, int i2, String str3, String str4) {
        String m2756a = c11533af.m2756a(str);
        if (f23471a) {
            m2806a("createChannel: appChannelId:" + m2756a + " serverChannelId:" + str + " serverChannelName:" + ((Object) charSequence) + " serverChannelDesc:" + str2 + " serverChannelNotifyType:" + i + " serverChannelName:" + ((Object) charSequence) + " serverChannelImportance:" + i2 + " channelSoundStr:" + str3 + " channelPermissions:" + str4);
        }
        NotificationChannel notificationChannel = new NotificationChannel(m2756a, charSequence, i2);
        notificationChannel.setDescription(str2);
        notificationChannel.enableVibration((i & 2) != 0);
        notificationChannel.enableLights((i & 4) != 0);
        if ((i & 1) != 0) {
            if (!TextUtils.isEmpty(str3)) {
                if (str3.startsWith("android.resource://" + c11533af.m2770a())) {
                    notificationChannel.setSound(Uri.parse(str3), Notification.AUDIO_ATTRIBUTES_DEFAULT);
                }
            }
        } else {
            notificationChannel.setSound(null, null);
        }
        if (f23471a) {
            m2806a("create channel:" + notificationChannel);
        }
        m2808a(c11533af, notificationChannel, str4);
        return m2756a;
    }

    /* renamed from: a */
    private static void m2806a(String str) {
        AbstractC11049b.m5280a("NCHelper", str);
    }

    /* renamed from: a */
    public static void m2812a(Context context, String str) {
        if (!C11469j.m2972a(context) || TextUtils.isEmpty(str)) {
            return;
        }
        m2803c(context, str);
        C11591f.m2555a(context, str);
    }

    /* renamed from: c */
    private static void m2803c(Context context, String str) {
        try {
            C11533af m2760a = C11533af.m2760a(context, str);
            Set<String> keySet = m2814a(context).getAll().keySet();
            ArrayList arrayList = new ArrayList();
            for (String str2 : keySet) {
                if (m2760a.m2754a(str2)) {
                    arrayList.add(str2);
                    if (f23471a) {
                        m2806a("delete channel copy record:" + str2);
                    }
                }
            }
            m2810a(context, arrayList);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m2809a(C11417gs c11417gs) {
        if (c11417gs == null || c11417gs.f22818a == null || !c11417gs.f22818a.containsKey("REMOVE_CHANNEL_MARK")) {
            return;
        }
        c11417gs.f22814a = 0;
        c11417gs.f22818a.remove("channel_id");
        c11417gs.f22818a.remove("channel_importance");
        c11417gs.f22818a.remove("channel_name");
        c11417gs.f22818a.remove("channel_description");
        c11417gs.f22818a.remove("channel_perm");
        AbstractC11049b.m5282a("delete channel info by:" + c11417gs.f22818a.get("REMOVE_CHANNEL_MARK"));
        c11417gs.f22818a.remove("REMOVE_CHANNEL_MARK");
    }

    @SuppressLint({"WrongConstant"})
    @TargetApi(26)
    /* renamed from: a */
    static void m2813a(Context context, C11533af c11533af, NotificationChannel notificationChannel, int i, String str) {
        if (i > 0) {
            int m2547a = C11395g.m3724a(context) >= 2 ? C11591f.m2547a(context.getPackageName(), str) : 0;
            NotificationChannel m2805a = m2805a(notificationChannel.getId(), notificationChannel);
            if ((i & 32) != 0) {
                if (notificationChannel.getSound() != null) {
                    m2805a.setSound(null, null);
                } else {
                    m2805a.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, Notification.AUDIO_ATTRIBUTES_DEFAULT);
                }
            }
            if ((i & 16) != 0) {
                if (notificationChannel.shouldVibrate()) {
                    m2805a.enableVibration(false);
                } else {
                    m2805a.enableVibration(true);
                }
            }
            if ((i & 8) != 0) {
                if (notificationChannel.shouldShowLights()) {
                    m2805a.enableLights(false);
                } else {
                    m2805a.enableLights(true);
                }
            }
            if ((i & 4) != 0) {
                int importance = notificationChannel.getImportance() - 1;
                if (importance <= 0) {
                    importance = 2;
                }
                m2805a.setImportance(importance);
            }
            if ((i & 2) != 0) {
                m2805a.setLockscreenVisibility(notificationChannel.getLockscreenVisibility() - 1);
            }
            c11533af.m2764a(m2805a);
            c11533af.m2763a(notificationChannel, true);
            C11591f.m2543a(c11533af.m2770a(), notificationChannel.getId(), m2547a, 0);
            return;
        }
        c11533af.m2764a(notificationChannel);
    }
}
