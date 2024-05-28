package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11305dt;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11480s;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.Notification$BuilderC11306du;
import com.xiaomi.push.Notification$BuilderC11307dv;
import com.xiaomi.push.Notification$BuilderC11308dw;
import com.xiaomi.push.service.C11529ae;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.x */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11635x {

    /* renamed from: a */
    public static long f23777a;

    /* renamed from: a */
    private static volatile AbstractC11523ab f23778a;

    /* renamed from: a */
    private static final LinkedList<Pair<Integer, C11427hb>> f23779a = new LinkedList<>();

    /* renamed from: a */
    private static ExecutorService f23780a = Executors.newCachedThreadPool();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.x$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11638b {

        /* renamed from: a */
        long f23787a = 0;

        /* renamed from: a */
        Notification f23788a;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.x$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11639c {

        /* renamed from: a */
        public String f23790a;

        /* renamed from: a */
        public long f23789a = 0;

        /* renamed from: a */
        public boolean f23791a = false;
    }

    /* renamed from: a */
    public static boolean m2330a(Context context, String str) {
        return C11395g.m3711b(context, str);
    }

    /* renamed from: a */
    public static boolean m2318a(Context context, String str, boolean z) {
        return C11469j.m2974a() && !z && m2330a(context, str);
    }

    /* renamed from: a */
    public static C11639c m2335a(Context context, C11427hb c11427hb, byte[] bArr) {
        int i;
        Map<String, String> map;
        C11639c c11639c = new C11639c();
        C11395g.EnumC11397b m3714a = C11395g.m3714a(context, m2312a(c11427hb), true);
        C11417gs m3388a = c11427hb.m3388a();
        if (m3388a != null) {
            i = m3388a.m3540c();
            map = m3388a.m3558a();
        } else {
            i = 0;
            map = null;
        }
        final int m2920b = C11480s.m2920b(m2312a(c11427hb), i);
        if (C11469j.m2972a(context) && m3714a == C11395g.EnumC11397b.NOT_ALLOWED) {
            if (m3388a != null) {
                C11305dt.m4117a(context.getApplicationContext()).m4110a(c11427hb.m3374b(), m2298b(c11427hb), m3388a.m3559a(), "10:" + m2312a(c11427hb));
            }
            AbstractC11049b.m5282a("Do not notify because user block " + m2312a(c11427hb) + "‘s notification");
            return c11639c;
        } else if (C11469j.m2972a(context) && f23778a != null && f23778a.m2820a(context, m2920b, m2312a(c11427hb), map)) {
            if (m3388a != null) {
                C11305dt.m4117a(context.getApplicationContext()).m4110a(c11427hb.m3374b(), m2298b(c11427hb), m3388a.m3559a(), "14:" + m2312a(c11427hb));
            }
            AbstractC11049b.m5282a("Do not notify because card notification is canceled or sequence incorrect");
            return c11639c;
        } else {
            RemoteViews m2336a = m2336a(context, c11427hb, bArr);
            PendingIntent m2338a = m2338a(context, c11427hb, c11427hb.m3374b(), bArr, m2920b);
            if (m2338a == null) {
                if (m3388a != null) {
                    C11305dt.m4117a(context.getApplicationContext()).m4110a(c11427hb.m3374b(), m2298b(c11427hb), m3388a.m3559a(), "11");
                }
                AbstractC11049b.m5282a("The click PendingIntent is null. ");
                return c11639c;
            }
            C11638b m2334a = m2334a(context, c11427hb, bArr, m2336a, m2338a, m2920b);
            c11639c.f23789a = m2334a.f23787a;
            c11639c.f23790a = m2312a(c11427hb);
            Notification notification = m2334a.f23788a;
            if (C11469j.m2974a()) {
                if (!TextUtils.isEmpty(m3388a.m3559a())) {
                    notification.extras.putString("message_id", m3388a.m3559a());
                }
                notification.extras.putString("local_paid", c11427hb.m3387a());
                C11534ag.m2725a(map, notification.extras, "msg_busi_type");
                C11534ag.m2725a(map, notification.extras, "disable_notification_flags");
                String str = m3388a.m3546b() == null ? null : m3388a.m3546b().get("score_info");
                if (!TextUtils.isEmpty(str)) {
                    notification.extras.putString("score_info", str);
                }
                notification.extras.putString("pushUid", m2305a(m3388a.f22818a, "n_stats_expose"));
                int i2 = -1;
                if (m2293c(c11427hb)) {
                    i2 = 1000;
                } else if (m2311a(c11427hb)) {
                    i2 = 3000;
                }
                notification.extras.putString("eventMessageType", String.valueOf(i2));
                notification.extras.putString("target_package", m2312a(c11427hb));
            }
            String str2 = m3388a.m3558a() == null ? null : m3388a.m3558a().get("message_count");
            if (C11469j.m2974a() && str2 != null) {
                try {
                    C11534ag.m2741a(notification, Integer.parseInt(str2));
                } catch (NumberFormatException e) {
                    if (m3388a != null) {
                        C11305dt.m4117a(context.getApplicationContext()).m4109b(c11427hb.m3374b(), m2298b(c11427hb), m3388a.m3559a(), "8");
                    }
                    AbstractC11049b.m5268d("fail to set message count. " + e);
                }
            }
            String m2312a = m2312a(c11427hb);
            C11534ag.m2738a(notification, m2312a);
            final C11533af m2760a = C11533af.m2760a(context, m2312a);
            if (C11469j.m2972a(context) && f23778a != null) {
                f23778a.m2819a(c11427hb, m3388a.m3558a(), m2920b, notification);
            }
            if (C11469j.m2972a(context) && f23778a != null && f23778a.m2817a(m3388a.m3558a(), m2920b, notification)) {
                AbstractC11049b.m5274b("consume this notificaiton by agent");
            } else {
                m2760a.m2765a(m2920b, notification);
                c11639c.f23791a = true;
                AbstractC11049b.m5282a("notification: " + m3388a.m3559a() + " is notifyied");
            }
            if (C11469j.m2974a() && C11469j.m2972a(context)) {
                C11525ad.m2802a().m2797a(context, m2920b, notification);
                C11581bb.m2577a(context, m2312a, m2920b, m3388a.m3559a(), notification);
            }
            if (m2311a(c11427hb)) {
                C11305dt.m4117a(context.getApplicationContext()).m4111a(c11427hb.m3374b(), m2298b(c11427hb), m3388a.m3559a(), 3002, null);
            }
            if (m2293c(c11427hb)) {
                C11305dt.m4117a(context.getApplicationContext()).m4111a(c11427hb.m3374b(), m2298b(c11427hb), m3388a.m3559a(), 1002, null);
            }
            if (Build.VERSION.SDK_INT < 26) {
                String m3559a = m3388a != null ? m3388a.m3559a() : null;
                C11134ae m4937a = C11134ae.m4937a(context);
                int m2308a = m2308a(m3388a.m3558a());
                if (m2308a > 0 && !TextUtils.isEmpty(m3559a)) {
                    final String str3 = "n_timeout_" + m3559a;
                    m4937a.m4925a(str3);
                    m4937a.m4924b(new C11134ae.AbstractRunnableC11137a() { // from class: com.xiaomi.push.service.x.1
                        @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
                        /* renamed from: a */
                        public String mo2289a() {
                            return str3;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            m2760a.m2766a(m2920b);
                        }
                    }, m2308a);
                }
            }
            Pair<Integer, C11427hb> pair = new Pair<>(Integer.valueOf(m2920b), c11427hb);
            synchronized (f23779a) {
                f23779a.add(pair);
                if (f23779a.size() > 100) {
                    f23779a.remove();
                }
            }
            return c11639c;
        }
    }

    /* renamed from: a */
    private static PendingIntent m2338a(Context context, C11427hb c11427hb, String str, byte[] bArr, int i) {
        return m2337a(context, c11427hb, str, bArr, i, 0, m2339a(context, c11427hb, str));
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a2  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.app.PendingIntent m2337a(android.content.Context r16, com.xiaomi.push.C11427hb r17, java.lang.String r18, byte[] r19, int r20, int r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 369
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11635x.m2337a(android.content.Context, com.xiaomi.push.hb, java.lang.String, byte[], int, int, boolean):android.app.PendingIntent");
    }

    /* renamed from: a */
    private static void m2341a(Context context, Intent intent, C11427hb c11427hb, C11417gs c11417gs, String str, int i) {
        if (c11427hb == null || c11417gs == null || TextUtils.isEmpty(str)) {
            return;
        }
        String m2306a = m2306a(c11417gs.m3558a(), i);
        if (TextUtils.isEmpty(m2306a)) {
            return;
        }
        if (AbstractC11555an.f23575a.equals(m2306a) || AbstractC11555an.f23576b.equals(m2306a) || AbstractC11555an.f23577c.equals(m2306a)) {
            intent.putExtra("messageId", str);
            intent.putExtra("local_paid", c11427hb.f22977a);
            if (!TextUtils.isEmpty(c11427hb.f22981b)) {
                intent.putExtra("target_package", c11427hb.f22981b);
            }
            intent.putExtra("job_key", m2305a(c11417gs.m3558a(), "jobkey"));
            intent.putExtra(i + "_target_component", m2322a(context, c11427hb.f22981b, c11417gs.m3558a(), i));
        }
    }

    /* renamed from: a */
    private static boolean m2339a(Context context, C11427hb c11427hb, String str) {
        if (c11427hb != null && c11427hb.m3388a() != null && c11427hb.m3388a().m3558a() != null && !TextUtils.isEmpty(str)) {
            return Boolean.parseBoolean(c11427hb.m3388a().m3558a().get("use_clicked_activity")) && C11600j.m2525a(context, m2310a(str));
        }
        AbstractC11049b.m5282a("should clicked activity params are null.");
        return false;
    }

    /* renamed from: a */
    public static ComponentName m2310a(String str) {
        return new ComponentName(str, "com.xiaomi.mipush.sdk.NotificationClickedActivity");
    }

    /* renamed from: a */
    private static String[] m2340a(Context context, C11417gs c11417gs) {
        String m3539c = c11417gs.m3539c();
        String m3534d = c11417gs.m3534d();
        Map<String, String> m3558a = c11417gs.m3558a();
        if (m3558a != null) {
            int intValue = Float.valueOf((context.getResources().getDisplayMetrics().widthPixels / context.getResources().getDisplayMetrics().density) + 0.5f).intValue();
            if (intValue <= 320) {
                String str = m3558a.get("title_short");
                if (!TextUtils.isEmpty(str)) {
                    m3539c = str;
                }
                String str2 = m3558a.get("description_short");
                if (!TextUtils.isEmpty(str2)) {
                    m3534d = str2;
                }
            } else if (intValue > 360) {
                String str3 = m3558a.get("title_long");
                if (!TextUtils.isEmpty(str3)) {
                    m3539c = str3;
                }
                String str4 = m3558a.get("description_long");
                if (!TextUtils.isEmpty(str4)) {
                    m3534d = str4;
                }
            }
        }
        return new String[]{m3539c, m3534d};
    }

    /* renamed from: a */
    private static String m2305a(Map<String, String> map, String str) {
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    /* renamed from: a */
    private static int m2322a(Context context, String str, Map<String, String> map, int i) {
        ComponentName m2524a;
        Intent m2299b = m2299b(context, str, map, i);
        if (m2299b == null || (m2524a = C11600j.m2524a(context, m2299b)) == null) {
            return 0;
        }
        return m2524a.hashCode();
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0284  */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.xiaomi.push.service.C11635x.C11638b m2334a(android.content.Context r26, com.xiaomi.push.C11427hb r27, byte[] r28, android.widget.RemoteViews r29, android.app.PendingIntent r30, int r31) {
        /*
            Method dump skipped, instructions count: 1153
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11635x.m2334a(android.content.Context, com.xiaomi.push.hb, byte[], android.widget.RemoteViews, android.app.PendingIntent, int):com.xiaomi.push.service.x$b");
    }

    /* renamed from: b */
    private static boolean m2295b(Map<String, String> map) {
        if (map == null) {
            AbstractC11049b.m5282a("meta extra is null");
            return false;
        }
        return "6".equals(map.get("notification_style_type"));
    }

    @TargetApi(16)
    /* renamed from: a */
    private static void m2314a(Notification$BuilderC11307dv notification$BuilderC11307dv, Context context, String str, C11427hb c11427hb, byte[] bArr, int i) {
        PendingIntent m2326a;
        PendingIntent m2326a2;
        PendingIntent m2326a3;
        PendingIntent m2326a4;
        Map<String, String> m3558a = c11427hb.m3388a().m3558a();
        if (TextUtils.equals("3", m3558a.get("notification_style_type")) || TextUtils.equals("4", m3558a.get("notification_style_type"))) {
            return;
        }
        if (m2295b(m3558a)) {
            for (int i2 = 1; i2 <= 3; i2++) {
                String str2 = m3558a.get(String.format("cust_btn_%s_n", Integer.valueOf(i2)));
                if (!TextUtils.isEmpty(str2) && (m2326a4 = m2326a(context, str, c11427hb, bArr, i, i2)) != null) {
                    notification$BuilderC11307dv.addAction(0, str2, m2326a4);
                }
            }
            return;
        }
        if (!TextUtils.isEmpty(m3558a.get("notification_style_button_left_name")) && (m2326a3 = m2326a(context, str, c11427hb, bArr, i, 1)) != null) {
            notification$BuilderC11307dv.addAction(0, m3558a.get("notification_style_button_left_name"), m2326a3);
        }
        if (!TextUtils.isEmpty(m3558a.get("notification_style_button_mid_name")) && (m2326a2 = m2326a(context, str, c11427hb, bArr, i, 2)) != null) {
            notification$BuilderC11307dv.addAction(0, m3558a.get("notification_style_button_mid_name"), m2326a2);
        }
        if (TextUtils.isEmpty(m3558a.get("notification_style_button_right_name")) || (m2326a = m2326a(context, str, c11427hb, bArr, i, 3)) == null) {
            return;
        }
        notification$BuilderC11307dv.addAction(0, m3558a.get("notification_style_button_right_name"), m2326a);
    }

    /* renamed from: a */
    private static PendingIntent m2326a(Context context, String str, C11427hb c11427hb, byte[] bArr, int i, int i2) {
        Map<String, String> m3558a = c11427hb.m3388a().m3558a();
        if (m3558a == null) {
            return null;
        }
        boolean m2339a = m2339a(context, c11427hb, str);
        if (m2339a) {
            return m2337a(context, c11427hb, str, bArr, i, i2, m2339a);
        }
        Intent m2321a = m2321a(context, str, m3558a, i2);
        if (m2321a != null) {
            if (Build.VERSION.SDK_INT >= 31) {
                return PendingIntent.getActivity(context, 0, m2321a, 167772160);
            }
            return PendingIntent.getActivity(context, 0, m2321a, 134217728);
        }
        return null;
    }

    /* renamed from: a */
    public static String m2306a(Map<String, String> map, int i) {
        String format;
        if (i == 0) {
            format = "notify_effect";
        } else {
            format = m2295b(map) ? String.format("cust_btn_%s_ne", Integer.valueOf(i)) : i == 1 ? "notification_style_button_left_notify_effect" : i == 2 ? "notification_style_button_mid_notify_effect" : i == 3 ? "notification_style_button_right_notify_effect" : i == 4 ? "notification_colorful_button_notify_effect" : null;
        }
        if (map == null || format == null) {
            return null;
        }
        return map.get(format);
    }

    /* renamed from: a */
    public static Intent m2321a(Context context, String str, Map<String, String> map, int i) {
        if (m2295b(map)) {
            return m2320a(context, str, map, String.format("cust_btn_%s_ne", Integer.valueOf(i)), String.format("cust_btn_%s_iu", Integer.valueOf(i)), String.format("cust_btn_%s_ic", Integer.valueOf(i)), String.format("cust_btn_%s_wu", Integer.valueOf(i)));
        }
        switch (i) {
            case 1:
                return m2320a(context, str, map, "notification_style_button_left_notify_effect", "notification_style_button_left_intent_uri", "notification_style_button_left_intent_class", "notification_style_button_left_web_uri");
            case 2:
                return m2320a(context, str, map, "notification_style_button_mid_notify_effect", "notification_style_button_mid_intent_uri", "notification_style_button_mid_intent_class", "notification_style_button_mid_web_uri");
            case 3:
                return m2320a(context, str, map, "notification_style_button_right_notify_effect", "notification_style_button_right_intent_uri", "notification_style_button_right_intent_class", "notification_style_button_right_web_uri");
            case 4:
                return m2320a(context, str, map, "notification_colorful_button_notify_effect", "notification_colorful_button_intent_uri", "notification_colorful_button_intent_class", "notification_colorful_button_web_uri");
            default:
                return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x011e  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.content.Intent m2320a(android.content.Context r2, java.lang.String r3, java.util.Map<java.lang.String, java.lang.String> r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 377
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11635x.m2320a(android.content.Context, java.lang.String, java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String):android.content.Intent");
    }

    @TargetApi(16)
    /* renamed from: a */
    private static Notification$BuilderC11307dv m2333a(Context context, C11427hb c11427hb, byte[] bArr, String str, int i) {
        PendingIntent m2326a;
        String m2312a = m2312a(c11427hb);
        Map<String, String> m3558a = c11427hb.m3388a().m3558a();
        String str2 = m3558a.get("notification_style_type");
        Notification$BuilderC11307dv m2821a = (!C11469j.m2972a(context) || f23778a == null) ? null : f23778a.m2821a(context, i, m2312a, m3558a);
        if (m2821a != null) {
            m2821a.mo4081a(m3558a);
            return m2821a;
        } else if ("2".equals(str2)) {
            Notification$BuilderC11307dv notification$BuilderC11307dv = new Notification$BuilderC11307dv(context);
            Bitmap m2319a = TextUtils.isEmpty(m3558a.get("notification_bigPic_uri")) ? null : m2319a(context, m3558a.get("notification_bigPic_uri"), false);
            if (m2319a == null) {
                AbstractC11049b.m5282a("can not get big picture.");
                return notification$BuilderC11307dv;
            }
            Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle(notification$BuilderC11307dv);
            bigPictureStyle.bigPicture(m2319a);
            bigPictureStyle.setSummaryText(str);
            bigPictureStyle.bigLargeIcon((Bitmap) null);
            notification$BuilderC11307dv.setStyle(bigPictureStyle);
            return notification$BuilderC11307dv;
        } else if ("1".equals(str2)) {
            Notification$BuilderC11307dv notification$BuilderC11307dv2 = new Notification$BuilderC11307dv(context);
            notification$BuilderC11307dv2.setStyle(new Notification.BigTextStyle().bigText(str));
            return notification$BuilderC11307dv2;
        } else if ("4".equals(str2) && C11469j.m2974a()) {
            Notification$BuilderC11306du notification$BuilderC11306du = new Notification$BuilderC11306du(context, m2312a);
            if (!TextUtils.isEmpty(m3558a.get("notification_banner_image_uri"))) {
                notification$BuilderC11306du.mo4084a(m2319a(context, m3558a.get("notification_banner_image_uri"), false));
            }
            if (!TextUtils.isEmpty(m3558a.get("notification_banner_icon_uri"))) {
                notification$BuilderC11306du.m4107b(m2319a(context, m3558a.get("notification_banner_icon_uri"), false));
            }
            notification$BuilderC11306du.mo4081a(m3558a);
            return notification$BuilderC11306du;
        } else if ("3".equals(str2) && C11469j.m2974a()) {
            Notification$BuilderC11308dw notification$BuilderC11308dw = new Notification$BuilderC11308dw(context, i, m2312a);
            if (!TextUtils.isEmpty(m3558a.get("notification_colorful_button_text")) && (m2326a = m2326a(context, m2312a, c11427hb, bArr, i, 4)) != null) {
                notification$BuilderC11308dw.m4099a(m3558a.get("notification_colorful_button_text"), m2326a).mo4098a(m3558a.get("notification_colorful_button_bg_color"));
            }
            if (!TextUtils.isEmpty(m3558a.get("notification_colorful_bg_color"))) {
                notification$BuilderC11308dw.m4097b(m3558a.get("notification_colorful_bg_color"));
            } else if (!TextUtils.isEmpty(m3558a.get("notification_colorful_bg_image_uri"))) {
                notification$BuilderC11308dw.mo4084a(m2319a(context, m3558a.get("notification_colorful_bg_image_uri"), false));
            }
            notification$BuilderC11308dw.mo4081a(m3558a);
            return notification$BuilderC11308dw;
        } else {
            return new Notification$BuilderC11307dv(context);
        }
    }

    /* renamed from: a */
    private static int m2308a(Map<String, String> map) {
        String str = map == null ? null : map.get("timeout");
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: a */
    private static RemoteViews m2336a(Context context, C11427hb c11427hb, byte[] bArr) {
        C11417gs m3388a = c11427hb.m3388a();
        String m2312a = m2312a(c11427hb);
        if (m3388a == null || m3388a.m3558a() == null) {
            return null;
        }
        Map<String, String> m3558a = m3388a.m3558a();
        String str = m3558a.get("layout_name");
        String str2 = m3558a.get("layout_value");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(m2312a);
            int identifier = resourcesForApplication.getIdentifier(str, "layout", m2312a);
            if (identifier == 0) {
                return null;
            }
            RemoteViews remoteViews = new RemoteViews(m2312a, identifier);
            try {
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.has("text")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("text");
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        String string = jSONObject2.getString(next);
                        int identifier2 = resourcesForApplication.getIdentifier(next, "id", m2312a);
                        if (identifier2 > 0) {
                            remoteViews.setTextViewText(identifier2, string);
                        }
                    }
                }
                if (jSONObject.has("image")) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject("image");
                    Iterator<String> keys2 = jSONObject3.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        String string2 = jSONObject3.getString(next2);
                        int identifier3 = resourcesForApplication.getIdentifier(next2, "id", m2312a);
                        int identifier4 = resourcesForApplication.getIdentifier(string2, "drawable", m2312a);
                        if (identifier3 > 0) {
                            remoteViews.setImageViewResource(identifier3, identifier4);
                        }
                    }
                }
                if (jSONObject.has("time")) {
                    JSONObject jSONObject4 = jSONObject.getJSONObject("time");
                    Iterator<String> keys3 = jSONObject4.keys();
                    while (keys3.hasNext()) {
                        String next3 = keys3.next();
                        String string3 = jSONObject4.getString(next3);
                        if (string3.length() == 0) {
                            string3 = "yy-MM-dd hh:mm";
                        }
                        int identifier5 = resourcesForApplication.getIdentifier(next3, "id", m2312a);
                        if (identifier5 > 0) {
                            remoteViews.setTextViewText(identifier5, new SimpleDateFormat(string3).format(new Date(System.currentTimeMillis())));
                        }
                    }
                }
                return remoteViews;
            } catch (JSONException e) {
                AbstractC11049b.m5276a(e);
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            AbstractC11049b.m5276a(e2);
            return null;
        }
    }

    /* renamed from: a */
    private static Bitmap m2342a(Context context, int i) {
        return m2315a(context.getResources().getDrawable(i));
    }

    /* renamed from: b */
    private static int m2304b(Context context, String str) {
        int m2325a = m2325a(context, str, "mipush_notification");
        int m2325a2 = m2325a(context, str, "mipush_small_notification");
        if (m2325a <= 0) {
            m2325a = m2325a2 > 0 ? m2325a2 : context.getApplicationInfo().icon;
        }
        return m2325a == 0 ? context.getApplicationInfo().logo : m2325a;
    }

    /* renamed from: a */
    private static int m2325a(Context context, String str, String str2) {
        if (str.equals(context.getPackageName())) {
            return context.getResources().getIdentifier(str2, "drawable", str);
        }
        return 0;
    }

    /* renamed from: a */
    public static Bitmap m2315a(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight > 0 ? intrinsicHeight : 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    private static Notification m2343a(Notification notification) {
        Object m4814a = C11176aw.m4814a(notification, "extraNotification");
        if (m4814a != null) {
            C11176aw.m4812a(m4814a, "setCustomizedIcon", true);
        }
        return notification;
    }

    /* renamed from: a */
    public static String m2312a(C11427hb c11427hb) {
        C11417gs m3388a;
        if ("com.xiaomi.xmsf".equals(c11427hb.f22981b) && (m3388a = c11427hb.m3388a()) != null && m3388a.m3558a() != null) {
            String str = m3388a.m3558a().get("miui_package_name");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return c11427hb.f22981b;
    }

    /* renamed from: a */
    public static void m2331a(Context context, String str) {
        m2329a(context, str, -1);
    }

    /* renamed from: a */
    public static void m2329a(Context context, String str, int i) {
        m2328a(context, str, i, -1);
    }

    /* renamed from: a */
    public static void m2328a(Context context, String str, int i, int i2) {
        boolean z;
        if (context == null || TextUtils.isEmpty(str) || i < -1) {
            return;
        }
        C11533af m2760a = C11533af.m2760a(context, str);
        List<StatusBarNotification> m2749b = m2760a.m2749b();
        if (C11480s.m2921a(m2749b)) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        int i3 = 0;
        if (i == -1) {
            z = true;
        } else {
            i3 = ((str.hashCode() / 10) * 10) + i;
            z = false;
        }
        Iterator<StatusBarNotification> it = m2749b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            StatusBarNotification next = it.next();
            if (!TextUtils.isEmpty(String.valueOf(next.getId()))) {
                int id = next.getId();
                if (z) {
                    linkedList.add(next);
                    m2760a.m2766a(id);
                } else if (i3 == id) {
                    C11588d.m2561a(context, next, i2);
                    linkedList.add(next);
                    m2760a.m2766a(id);
                    break;
                }
            }
        }
        m2317a(context, linkedList);
    }

    /* renamed from: b */
    public static void m2303b(Context context, String str) {
        if (!C11469j.m2972a(context) || f23778a == null || TextUtils.isEmpty(str)) {
            return;
        }
        f23778a.m2818a(str);
    }

    /* renamed from: a */
    public static void m2324a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        C11533af m2760a = C11533af.m2760a(context, str);
        List<StatusBarNotification> m2749b = m2760a.m2749b();
        if (C11480s.m2921a(m2749b)) {
            return;
        }
        LinkedList linkedList = new LinkedList();
        for (StatusBarNotification statusBarNotification : m2749b) {
            Notification notification = statusBarNotification.getNotification();
            if (notification != null && !TextUtils.isEmpty(String.valueOf(statusBarNotification.getId()))) {
                int id = statusBarNotification.getId();
                String m2743a = C11534ag.m2743a(notification);
                String m2724b = C11534ag.m2724b(notification);
                if (!TextUtils.isEmpty(m2743a) && !TextUtils.isEmpty(m2724b) && m2309a(m2743a, str2) && m2309a(m2724b, str3)) {
                    linkedList.add(statusBarNotification);
                    m2760a.m2766a(id);
                }
            }
        }
        m2317a(context, linkedList);
    }

    /* renamed from: a */
    private static boolean m2309a(String str, String str2) {
        return TextUtils.isEmpty(str) || str2.contains(str);
    }

    /* renamed from: a */
    public static void m2317a(Context context, LinkedList<? extends Object> linkedList) {
        if (linkedList == null || linkedList.size() <= 0) {
            return;
        }
        C11577az.m2597a(context, "category_clear_notification", "clear_notification", linkedList.size(), "");
    }

    /* renamed from: a */
    static int m2332a(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(str, Integer.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static void m2294c(Context context, String str) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(str).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m2302b(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(str);
    }

    /* renamed from: b */
    static void m2301b(Context context, String str, int i) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(str, i).commit();
    }

    /* renamed from: a */
    public static boolean m2307a(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return "1".equals(map.get("notify_foreground"));
    }

    /* renamed from: a */
    private static boolean m2313a(C11417gs c11417gs) {
        if (c11417gs != null) {
            String m3559a = c11417gs.m3559a();
            return !TextUtils.isEmpty(m3559a) && m3559a.length() == 22 && "satuigmo".indexOf(m3559a.charAt(0)) >= 0;
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m2311a(C11427hb c11427hb) {
        C11417gs m3388a = c11427hb.m3388a();
        return m2313a(m3388a) && m3388a.m3522l();
    }

    /* renamed from: b */
    public static boolean m2297b(C11427hb c11427hb) {
        C11417gs m3388a = c11427hb.m3388a();
        return m2313a(m3388a) && m3388a.f22820b == 1 && !m2311a(c11427hb);
    }

    /* renamed from: c */
    public static boolean m2293c(C11427hb c11427hb) {
        C11417gs m3388a = c11427hb.m3388a();
        return m2313a(m3388a) && m3388a.f22820b == 0 && !m2311a(c11427hb);
    }

    /* renamed from: d */
    public static boolean m2291d(C11427hb c11427hb) {
        return c11427hb.m3389a() == EnumC11404gf.Registration;
    }

    /* renamed from: e */
    public static boolean m2290e(C11427hb c11427hb) {
        return m2311a(c11427hb) || m2293c(c11427hb) || m2297b(c11427hb);
    }

    /* renamed from: b */
    public static String m2298b(C11427hb c11427hb) {
        return m2311a(c11427hb) ? "E100002" : m2293c(c11427hb) ? "E100000" : m2297b(c11427hb) ? "E100001" : m2291d(c11427hb) ? "E100003" : "";
    }

    /* renamed from: a */
    private static Bitmap m2319a(Context context, String str, boolean z) {
        Future submit = f23780a.submit(new CallableC11637a(str, context, z));
        try {
            try {
                try {
                    Bitmap bitmap = (Bitmap) submit.get(180L, TimeUnit.SECONDS);
                    return bitmap == null ? bitmap : bitmap;
                } catch (InterruptedException e) {
                    AbstractC11049b.m5276a(e);
                    submit.cancel(true);
                    return null;
                } catch (TimeoutException e2) {
                    AbstractC11049b.m5276a(e2);
                    submit.cancel(true);
                    return null;
                }
            } catch (ExecutionException e3) {
                AbstractC11049b.m5276a(e3);
                submit.cancel(true);
                return null;
            }
        } finally {
            submit.cancel(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.x$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class CallableC11637a implements Callable<Bitmap> {

        /* renamed from: a */
        private Context f23784a;

        /* renamed from: a */
        private String f23785a;

        /* renamed from: a */
        private boolean f23786a;

        public CallableC11637a(String str, Context context, boolean z) {
            this.f23784a = context;
            this.f23785a = str;
            this.f23786a = z;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Bitmap call() {
            if (!TextUtils.isEmpty(this.f23785a)) {
                if (this.f23785a.startsWith("http")) {
                    C11529ae.C11532b m2779a = C11529ae.m2779a(this.f23784a, this.f23785a, this.f23786a);
                    if (m2779a != null) {
                        return m2779a.f23486a;
                    }
                    AbstractC11049b.m5282a("Failed get online picture/icon resource");
                    return null;
                }
                Bitmap m2780a = C11529ae.m2780a(this.f23784a, this.f23785a);
                if (m2780a == null) {
                    AbstractC11049b.m5282a("Failed get online picture/icon resource");
                    return m2780a;
                }
                return m2780a;
            }
            AbstractC11049b.m5282a("Failed get online picture/icon resource cause picUrl is empty");
            return null;
        }
    }

    /* renamed from: a */
    private static String m2323a(Context context, String str, Map<String, String> map) {
        if (map != null && !TextUtils.isEmpty(map.get("channel_name"))) {
            return map.get("channel_name");
        }
        return C11395g.m3712b(context, str);
    }

    /* renamed from: b */
    private static int m2296b(Map<String, String> map) {
        if (map != null) {
            String str = map.get("channel_importance");
            if (TextUtils.isEmpty(str)) {
                return 3;
            }
            try {
                AbstractC11049b.m5270c("importance=" + str);
                return Integer.parseInt(str);
            } catch (Exception e) {
                AbstractC11049b.m5268d("parsing channel importance error: " + e);
                return 3;
            }
        }
        return 3;
    }

    /* renamed from: c */
    private static int m2292c(Map<String, String> map) {
        if (map != null) {
            String str = map.get("notification_priority");
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            try {
                AbstractC11049b.m5270c("priority=" + str);
                return Integer.parseInt(str);
            } catch (Exception e) {
                AbstractC11049b.m5268d("parsing notification priority error: " + e);
                return 0;
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0160  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.Intent m2299b(android.content.Context r5, java.lang.String r6, java.util.Map<java.lang.String, java.lang.String> r7, int r8) {
        /*
            Method dump skipped, instructions count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11635x.m2299b(android.content.Context, java.lang.String, java.util.Map, int):android.content.Intent");
    }

    /* renamed from: a */
    private static void m2316a(Intent intent) {
        if (intent == null) {
            return;
        }
        int flags = intent.getFlags() & (-2) & (-3) & (-65);
        if (Build.VERSION.SDK_INT >= 21) {
            flags &= -129;
        }
        intent.setFlags(flags);
    }

    /* renamed from: a */
    private static void m2327a(Context context, String str, Notification$BuilderC11307dv notification$BuilderC11307dv, Map<String, String> map) {
        int m2325a = m2325a(context, str, "mipush_small_notification");
        int m2325a2 = m2325a(context, str, "mipush_notification");
        if (C11469j.m2972a(context)) {
            if (m2325a > 0 && m2325a2 > 0) {
                notification$BuilderC11307dv.setSmallIcon(m2325a);
                notification$BuilderC11307dv.setLargeIcon(m2342a(context, m2325a2));
                return;
            }
            m2300b(context, str, notification$BuilderC11307dv, map);
            return;
        }
        if (m2325a > 0) {
            notification$BuilderC11307dv.setSmallIcon(m2325a);
        } else {
            m2300b(context, str, notification$BuilderC11307dv, map);
        }
        if (m2325a2 > 0) {
            notification$BuilderC11307dv.setLargeIcon(m2342a(context, m2325a2));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m2300b(android.content.Context r2, java.lang.String r3, com.xiaomi.push.Notification$BuilderC11307dv r4, java.util.Map<java.lang.String, java.lang.String> r5) {
        /*
            boolean r0 = com.xiaomi.push.C11469j.m2972a(r2)
            if (r0 != 0) goto L2c
            java.lang.String r0 = "fcm_icon_uri"
            java.lang.String r0 = m2305a(r5, r0)
            java.lang.String r1 = "fcm_icon_color"
            java.lang.String r5 = m2305a(r5, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L2c
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto L2c
            int r0 = m2325a(r2, r3, r0)
            if (r0 <= 0) goto L2c
            r1 = 1
            r4.setSmallIcon(r0)
            r4.mo4098a(r5)
            goto L2d
        L2c:
            r1 = 0
        L2d:
            if (r1 != 0) goto L48
            int r5 = android.os.Build.VERSION.SDK_INT
            r0 = 23
            if (r5 < r0) goto L41
            int r2 = com.xiaomi.push.service.C11534ag.m2734a(r2, r3)
            android.graphics.drawable.Icon r2 = android.graphics.drawable.Icon.createWithResource(r3, r2)
            r4.setSmallIcon(r2)
            goto L48
        L41:
            int r2 = m2304b(r2, r3)
            r4.setSmallIcon(r2)
        L48:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11635x.m2300b(android.content.Context, java.lang.String, com.xiaomi.push.dv, java.util.Map):void");
    }
}
