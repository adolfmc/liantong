package com.vivo.push.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.vivo.push.PushClientManager;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.NotifyArriveCallbackByUser;
import com.vivo.push.p368b.OnNotificationClickReceiveCommand;
import com.vivo.push.p373f.OnNotificationArrivedReceiveTask;
import com.vivo.push.restructure.PushClientController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class NotifyAdapterUtil {
    private static final String EXTRA_VPUSH_TYPE = "extra_vpush_type";
    private static final int HIDE_TITLE = 1;
    public static final int NOTIFY_MULTITERM_STYLE = 1;
    public static final int NOTIFY_SINGLE_STYLE = 0;
    public static final String PRIMARY_CHANNEL = "vivo_push_channel";
    private static final String PUSH_EN = "PUSH";
    private static final String PUSH_ID = "pushId";
    private static final String PUSH_ZH = "推送通知";
    private static final String TAG = "NotifyManager";
    private static final String USER_ID = "sysUserId";
    private static NotificationManager sNotificationManager = null;
    private static int sNotifyId = 20000000;

    public static void pushNotification(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, int i, NotifyArriveCallbackByUser notifyArriveCallbackByUser, OnNotificationArrivedReceiveTask.InterfaceC10956a interfaceC10956a) {
        LogUtil.m5341d("NotifyManager", "pushNotification");
        initAdapter(context);
        int notifyMode = NotifyUtil.getNotifyDataAdapter(context).getNotifyMode(insideNotificationItem);
        if (!TextUtils.isEmpty(insideNotificationItem.getPurePicUrl()) && list != null && list.size() > 1 && list.get(1) != null) {
            notifyMode = 1;
        }
        if (notifyMode == 2) {
            pushNotificationBySystem(context, list, insideNotificationItem, j, i, notifyArriveCallbackByUser, interfaceC10956a);
        } else if (notifyMode == 1) {
            pushNotificationByCustom(context, list, insideNotificationItem, j, notifyArriveCallbackByUser, interfaceC10956a);
        }
    }

    private static synchronized void initAdapter(Context context) {
        synchronized (NotifyAdapterUtil.class) {
            if (sNotificationManager == null) {
                sNotificationManager = (NotificationManager) context.getSystemService("notification");
            }
            if (Build.VERSION.SDK_INT >= 26 && sNotificationManager != null) {
                NotificationChannel notificationChannel = sNotificationManager.getNotificationChannel("default");
                if (notificationChannel != null) {
                    CharSequence name = notificationChannel.getName();
                    if ("推送通知".equals(name) || "PUSH".equals(name)) {
                        sNotificationManager.deleteNotificationChannel("default");
                    }
                }
                NotificationChannel notificationChannel2 = new NotificationChannel("vivo_push_channel", isZh(context) ? "推送通知" : "PUSH", 4);
                notificationChannel2.setLightColor(-16711936);
                notificationChannel2.enableVibration(true);
                notificationChannel2.setLockscreenVisibility(1);
                sNotificationManager.createNotificationChannel(notificationChannel2);
            }
        }
    }

    private static void pushNotificationByCustom(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, NotifyArriveCallbackByUser notifyArriveCallbackByUser, OnNotificationArrivedReceiveTask.InterfaceC10956a interfaceC10956a) {
        Notification notification;
        Bitmap bitmap;
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        String title = insideNotificationItem.getTitle();
        int defaultNotifyIcon = NotifyUtil.getNotifyDataAdapter(context).getDefaultNotifyIcon();
        int i = context.getApplicationInfo().icon;
        Bundle bundle = new Bundle();
        bundle.putLong("pushId", j);
        if (PushClientController.m5593a().m5588e().mo5524l().isOpenMultiUser()) {
            bundle.putInt("sysUserId", MultiUserManager.m5336a());
        }
        bundle.putInt("extra_vpush_type", 1);
        if (Build.VERSION.SDK_INT >= 26) {
            Notification.Builder builder = new Notification.Builder(context, "vivo_push_channel");
            if (defaultNotifyIcon > 0) {
                bundle.putInt("vivo.summaryIconRes", defaultNotifyIcon);
            }
            builder.setExtras(bundle);
            notification = builder.build();
        } else if (Build.VERSION.SDK_INT >= 19) {
            Notification.Builder builder2 = new Notification.Builder(context);
            builder2.setExtras(bundle);
            notification = builder2.build();
        } else {
            notification = new Notification();
        }
        notification.priority = 2;
        notification.flags = 16;
        notification.tickerText = title;
        int defaultSmallIconId = NotifyUtil.getNotifyDataAdapter(context).getDefaultSmallIconId();
        if (defaultSmallIconId <= 0) {
            defaultSmallIconId = i;
        }
        notification.icon = defaultSmallIconId;
        RemoteViews remoteViews = new RemoteViews(packageName, NotifyUtil.getNotifyLayoutAdapter(context).getNotificationLayout());
        remoteViews.setTextViewText(resources.getIdentifier("notify_title", "id", packageName), title);
        remoteViews.setTextColor(resources.getIdentifier("notify_title", "id", packageName), NotifyUtil.getNotifyLayoutAdapter(context).getTitleColor());
        remoteViews.setTextViewText(resources.getIdentifier("notify_msg", "id", packageName), insideNotificationItem.getContent());
        if (insideNotificationItem.isShowTime()) {
            remoteViews.setTextViewText(resources.getIdentifier("notify_when", "id", packageName), new SimpleDateFormat(JtDateUtil.dateFormatHM, Locale.CHINA).format(new Date()));
            remoteViews.setViewVisibility(resources.getIdentifier("notify_when", "id", packageName), 0);
        } else {
            remoteViews.setViewVisibility(resources.getIdentifier("notify_when", "id", packageName), 8);
        }
        int suitIconId = NotifyUtil.getNotifyLayoutAdapter(context).getSuitIconId();
        remoteViews.setViewVisibility(suitIconId, 0);
        if (list != null && !list.isEmpty() && (bitmap = list.get(0)) != null) {
            remoteViews.setImageViewBitmap(suitIconId, bitmap);
        } else {
            if (defaultNotifyIcon <= 0) {
                defaultNotifyIcon = i;
            }
            remoteViews.setImageViewResource(suitIconId, defaultNotifyIcon);
        }
        Bitmap bitmap2 = null;
        if (list != null && list.size() > 1) {
            bitmap2 = list.get(1);
        }
        if (bitmap2 != null) {
            if (!TextUtils.isEmpty(insideNotificationItem.getPurePicUrl())) {
                remoteViews.setViewVisibility(resources.getIdentifier("notify_content", "id", packageName), 8);
                remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", "id", packageName), 8);
                remoteViews.setViewVisibility(resources.getIdentifier("notify_pure_cover", "id", packageName), 0);
                remoteViews.setImageViewBitmap(resources.getIdentifier("notify_pure_cover", "id", packageName), bitmap2);
            } else {
                remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", "id", packageName), 0);
                remoteViews.setImageViewBitmap(resources.getIdentifier("notify_cover", "id", packageName), bitmap2);
            }
        } else {
            remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", "id", packageName), 8);
        }
        notification.contentView = remoteViews;
        if (Build.VERSION.SDK_INT >= 16 && TextUtils.isEmpty(insideNotificationItem.getPurePicUrl())) {
            notification.bigContentView = remoteViews;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int ringerMode = audioManager.getRingerMode();
        int vibrateSetting = audioManager.getVibrateSetting(0);
        LogUtil.m5341d("NotifyManager", "ringMode=" + ringerMode + " callVibrateSetting=" + vibrateSetting);
        switch (insideNotificationItem.getNotifyType()) {
            case 2:
                if (ringerMode == 2) {
                    notification.defaults = 1;
                    break;
                }
                break;
            case 3:
                if (vibrateSetting == 1) {
                    notification.defaults = 2;
                    notification.vibrate = new long[]{0, 100, 200, 300};
                    break;
                }
                break;
            case 4:
                if (ringerMode == 2) {
                    notification.defaults = 1;
                }
                if (vibrateSetting == 1) {
                    notification.defaults |= 2;
                    notification.vibrate = new long[]{0, 100, 200, 300};
                    break;
                }
                break;
        }
        ConvertMsgToIntentImpl convertMsgToIntentImpl = new ConvertMsgToIntentImpl();
        Intent m5426a = convertMsgToIntentImpl.m5426a(context, packageName, j, insideNotificationItem, notifyArriveCallbackByUser);
        if (m5426a != null) {
            if (isPullService()) {
                notification.contentIntent = PendingIntent.getService(context, (int) SystemClock.uptimeMillis(), BaseConvertMsgToIntent.m5427a(context, packageName, j, m5426a, insideNotificationItem), 201326592);
            } else {
                new OnNotificationClickReceiveCommand(packageName, j, insideNotificationItem).m5325b(m5426a);
                notification.contentIntent = convertMsgToIntentImpl.mo5402a(context, m5426a);
            }
            if (sNotificationManager != null) {
                int m5612j = PushClientManager.m5648a().m5612j();
                try {
                    if (m5612j == 0) {
                        sNotificationManager.notify(sNotifyId, notification);
                        if (interfaceC10956a != null) {
                            interfaceC10956a.mo5668a();
                            return;
                        }
                        return;
                    } else if (m5612j == 1) {
                        sNotificationManager.notify((int) j, notification);
                        if (interfaceC10956a != null) {
                            interfaceC10956a.mo5668a();
                            return;
                        }
                        return;
                    } else {
                        LogUtil.m5354a("NotifyManager", "unknow notify style ".concat(String.valueOf(m5612j)));
                        return;
                    }
                } catch (Exception e) {
                    LogUtil.m5352a("NotifyManager", e);
                    if (interfaceC10956a != null) {
                        interfaceC10956a.mo5667b();
                        return;
                    }
                    return;
                }
            }
            return;
        }
        LogUtil.m5354a("NotifyManager", "make notify intent error  ");
    }

    private static void pushNotificationBySystem(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, int i, NotifyArriveCallbackByUser notifyArriveCallbackByUser, OnNotificationArrivedReceiveTask.InterfaceC10956a interfaceC10956a) {
        Bitmap bitmap;
        Notification.Builder builder;
        int i2;
        int i3;
        Bitmap bitmap2;
        boolean z;
        Bitmap decodeResource;
        String packageName = context.getPackageName();
        String title = insideNotificationItem.getTitle();
        String content = insideNotificationItem.getContent();
        int i4 = context.getApplicationInfo().icon;
        boolean isShowTime = insideNotificationItem.isShowTime();
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int defaultNotifyIcon = NotifyUtil.getNotifyDataAdapter(context).getDefaultNotifyIcon();
        if (list == null || list.isEmpty()) {
            bitmap = null;
        } else {
            bitmap = list.get(0);
            if (bitmap != null && defaultNotifyIcon > 0 && (decodeResource = BitmapFactory.decodeResource(context.getResources(), defaultNotifyIcon)) != null) {
                int width = decodeResource.getWidth();
                int height = decodeResource.getHeight();
                decodeResource.recycle();
                bitmap = BitmapUtil.m5410a(bitmap, width, height);
            }
        }
        Bundle bundle = new Bundle();
        if (Build.VERSION.SDK_INT >= 26) {
            builder = new Notification.Builder(context, "vivo_push_channel");
            if (defaultNotifyIcon > 0) {
                bundle.putInt("vivo.summaryIconRes", defaultNotifyIcon);
            }
            if (bitmap != null) {
                builder.setLargeIcon(bitmap);
            }
        } else {
            builder = new Notification.Builder(context);
            if (bitmap != null) {
                builder.setLargeIcon(bitmap);
            } else if (Build.VERSION.SDK_INT <= 22) {
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), i4));
            }
        }
        if (PushClientController.m5593a().m5588e().mo5524l().isOpenMultiUser()) {
            bundle.putInt("sysUserId", MultiUserManager.m5336a());
        }
        bundle.putInt("extra_vpush_type", 1);
        if (Build.VERSION.SDK_INT >= 19) {
            bundle.putLong("pushId", j);
            builder.setExtras(bundle);
        }
        int defaultSmallIconId = NotifyUtil.getNotifyDataAdapter(context).getDefaultSmallIconId();
        if (defaultSmallIconId > 0) {
            i4 = defaultSmallIconId;
        }
        builder.setSmallIcon(i4);
        if (insideNotificationItem.getCompatibleType() != 1) {
            builder.setContentTitle(title);
        }
        builder.setPriority(2);
        builder.setContentText(content);
        builder.setWhen(isShowTime ? System.currentTimeMillis() : 0L);
        builder.setShowWhen(isShowTime);
        builder.setTicker(title);
        int ringerMode = audioManager.getRingerMode();
        switch (insideNotificationItem.getNotifyType()) {
            case 2:
                if (ringerMode != 2) {
                    i2 = 1;
                    break;
                } else {
                    i2 = 1;
                    builder.setDefaults(1);
                    break;
                }
            case 3:
                if (ringerMode != 2) {
                    i2 = 1;
                    break;
                } else {
                    builder.setDefaults(2);
                    builder.setVibrate(new long[]{0, 100, 200, 300});
                    i2 = 1;
                    break;
                }
            case 4:
                if (ringerMode != 2) {
                    if (ringerMode != 1) {
                        i2 = 1;
                        break;
                    } else {
                        builder.setDefaults(2);
                        builder.setVibrate(new long[]{0, 100, 200, 300});
                        i2 = 1;
                        break;
                    }
                } else {
                    builder.setDefaults(3);
                    builder.setVibrate(new long[]{0, 100, 200, 300});
                    i2 = 1;
                    break;
                }
            default:
                i2 = 1;
                break;
        }
        if (list == null || list.size() <= i2) {
            i3 = i;
            bitmap2 = null;
        } else {
            bitmap2 = list.get(i2);
            i3 = i;
        }
        if (i3 != i2) {
            Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
            bigTextStyle.setBigContentTitle(title);
            bigTextStyle.bigText(content);
            builder.setStyle(bigTextStyle);
        }
        if (bitmap2 != null) {
            Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle();
            bigPictureStyle.setBigContentTitle(title);
            bigPictureStyle.setSummaryText(content);
            bigPictureStyle.bigPicture(bitmap2);
            builder.setStyle(bigPictureStyle);
            z = true;
        } else {
            z = true;
        }
        builder.setAutoCancel(z);
        ConvertMsgToIntentImpl convertMsgToIntentImpl = new ConvertMsgToIntentImpl();
        Intent m5426a = convertMsgToIntentImpl.m5426a(context, packageName, j, insideNotificationItem, notifyArriveCallbackByUser);
        if (m5426a != null) {
            if (isPullService()) {
                builder.setContentIntent(PendingIntent.getService(context, (int) SystemClock.uptimeMillis(), BaseConvertMsgToIntent.m5427a(context, packageName, j, m5426a, insideNotificationItem), 201326592));
            } else {
                new OnNotificationClickReceiveCommand(packageName, j, insideNotificationItem).m5325b(m5426a);
                builder.setContentIntent(convertMsgToIntentImpl.mo5402a(context, m5426a));
            }
            Notification build = builder.build();
            int m5612j = PushClientManager.m5648a().m5612j();
            NotificationManager notificationManager = sNotificationManager;
            if (notificationManager != null) {
                try {
                    if (m5612j == 0) {
                        notificationManager.notify(sNotifyId, build);
                        if (interfaceC10956a != null) {
                            interfaceC10956a.mo5668a();
                            return;
                        }
                        return;
                    } else if (m5612j == 1) {
                        notificationManager.notify((int) j, build);
                        if (interfaceC10956a != null) {
                            interfaceC10956a.mo5668a();
                            return;
                        }
                        return;
                    } else {
                        LogUtil.m5354a("NotifyManager", "unknow notify style ".concat(String.valueOf(m5612j)));
                        return;
                    }
                } catch (Exception e) {
                    LogUtil.m5352a("NotifyManager", e);
                    if (interfaceC10956a != null) {
                        interfaceC10956a.mo5667b();
                        return;
                    }
                    return;
                }
            }
            return;
        }
        LogUtil.m5354a("NotifyManager", "make notify intent error  ");
    }

    public static boolean repealNotifyById(Context context, long j) {
        int m5612j = PushClientManager.m5648a().m5612j();
        if (m5612j != 0) {
            if (m5612j == 1) {
                return cancelNotify(context, (int) j);
            }
            LogUtil.m5354a("NotifyManager", "unknow cancle notify style ".concat(String.valueOf(m5612j)));
            return false;
        }
        long b = SharePreferenceManager.m5455b().m5412b("com.vivo.push.notify_key", -1L);
        if (b == j) {
            LogUtil.m5341d("NotifyManager", "undo showed message ".concat(String.valueOf(j)));
            LogUtil.m5356a(context, "回收已展示的通知： ".concat(String.valueOf(j)));
            return cancelNotify(context, sNotifyId);
        }
        LogUtil.m5341d("NotifyManager", "current showing message id " + b + " not match " + j);
        LogUtil.m5356a(context, "与已展示的通知" + b + "与待回收的通知" + j + "不匹配");
        return false;
    }

    private static boolean cancelNotify(Context context, int i) {
        initAdapter(context);
        NotificationManager notificationManager = sNotificationManager;
        if (notificationManager != null) {
            notificationManager.cancel(i);
            return true;
        }
        return false;
    }

    public static void cancelNotify(Context context) {
        cancelNotify(context, sNotifyId);
    }

    public static void setNotifyId(int i) {
        sNotifyId = i;
    }

    private static boolean isZh(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }

    private static boolean isPullService() {
        return Device.f21223a ? Build.VERSION.SDK_INT < 31 : Build.VERSION.SDK_INT < 28;
    }
}
