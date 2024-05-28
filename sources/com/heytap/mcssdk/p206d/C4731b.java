package com.heytap.mcssdk.p206d;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import com.heytap.mcssdk.PushService;
import com.heytap.mcssdk.p209g.C4742a;
import com.heytap.mcssdk.utils.C4746d;
import com.heytap.msp.push.HeytapPushManager;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.mode.NotificationSortMessage;
import com.heytap.msp.push.notification.ISortListener;
import com.heytap.msp.push.notification.PushNotification;
import com.heytap.msp.push.statis.StatisticUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.heytap.mcssdk.d.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4731b {

    /* renamed from: c */
    private int f10686c;

    /* renamed from: d */
    private int f10687d;

    /* renamed from: f */
    private int f10689f;

    /* renamed from: g */
    private int f10690g;

    /* renamed from: h */
    private StatusBarNotification f10691h;

    /* renamed from: a */
    private int f10684a = 3;

    /* renamed from: b */
    private List<NotificationSortMessage> f10685b = new ArrayList();

    /* renamed from: e */
    private List<String> f10688e = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.heytap.mcssdk.d.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C4732a {

        /* renamed from: a */
        private static final C4731b f10692a = new C4731b();

        private C4732a() {
        }
    }

    /* renamed from: a */
    private int m15529a(List<NotificationSortMessage> list, int i) {
        int size = list == null ? 0 : list.size();
        if (i <= 0 || size == 0) {
            return i;
        }
        if (size < i) {
            int i2 = i - size;
            list.clear();
            return i2;
        }
        for (int i3 = 0; i3 < i; i3++) {
            list.remove((size - 1) - i3);
        }
        return 0;
    }

    /* renamed from: a */
    public static C4731b m15545a() {
        return C4732a.f10692a;
    }

    /* renamed from: a */
    private DataMessage m15536a(Context context, NotificationSortMessage notificationSortMessage) {
        DataMessage dataMessage = new DataMessage(context.getPackageName(), notificationSortMessage.getMessageId());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("isMcs", "false");
            String statisticData = notificationSortMessage.getStatisticData();
            if (!TextUtils.isEmpty(statisticData)) {
                jSONObject.put("clientStatisticData", statisticData);
            }
            dataMessage.setStatisticsExtra(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (JSONException unused) {
        }
        return dataMessage;
    }

    /* renamed from: a */
    private void m15544a(int i) {
        if (i == 7) {
            this.f10686c++;
        } else if (i == 5) {
            this.f10687d++;
        }
    }

    /* renamed from: a */
    private void m15543a(NotificationManager notificationManager, Context context, int i) {
        m15528a(C4730a.m15549a(notificationManager, context.getPackageName()), i);
    }

    /* renamed from: a */
    private void m15541a(Context context, NotificationManager notificationManager, int i) {
        m15529a(this.f10685b, i);
        m15538a(context, notificationManager, this.f10685b);
    }

    /* renamed from: a */
    private void m15538a(Context context, NotificationManager notificationManager, List<NotificationSortMessage> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        ArrayList arrayList = new ArrayList();
        m15537a(context, notificationManager, jSONArray, list, arrayList);
        if (jSONArray.length() != 0) {
            try {
                jSONObject.put("SORT_ARRAY", jSONArray);
                HeytapPushManager.cancelNotification(jSONObject);
            } catch (JSONException unused) {
            }
        }
        if (arrayList.size() != 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("push_delete_by_fold", arrayList);
            StatisticUtils.statisticEvent(context, hashMap);
        }
    }

    /* renamed from: a */
    private void m15537a(Context context, NotificationManager notificationManager, JSONArray jSONArray, List<NotificationSortMessage> list, List<DataMessage> list2) {
        for (NotificationSortMessage notificationSortMessage : list) {
            if (notificationSortMessage.isMcs()) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("EXTRA_MESSAGE_ID", notificationSortMessage.getMessageId());
                    jSONObject.put("EXTRA_NOTIFY_ID", notificationSortMessage.getNotifyId());
                    jSONArray.put(jSONObject);
                } catch (JSONException unused) {
                }
            } else {
                list2.add(m15536a(context, notificationSortMessage));
                this.f10688e.add(notificationSortMessage.getMessageId());
            }
            notificationManager.cancel(notificationSortMessage.getNotifyId());
        }
    }

    /* renamed from: a */
    private void m15534a(NotificationSortMessage notificationSortMessage) {
        if (notificationSortMessage.getAutoDelete() != 1) {
            return;
        }
        if (this.f10685b.size() != 0) {
            for (int size = this.f10685b.size() - 1; size >= 0; size--) {
                NotificationSortMessage notificationSortMessage2 = this.f10685b.get(size);
                if (notificationSortMessage.getImportantLevel() >= notificationSortMessage2.getImportantLevel() && notificationSortMessage.getPostTime() >= notificationSortMessage2.getPostTime()) {
                    this.f10685b.add(size + 1, notificationSortMessage2);
                    return;
                }
            }
        }
        this.f10685b.add(0, notificationSortMessage);
    }

    /* renamed from: a */
    private void m15533a(ISortListener iSortListener, boolean z, PushNotification.Builder builder) {
        if (iSortListener != null) {
            iSortListener.buildCompleted(z, builder, this.f10688e);
        }
    }

    /* renamed from: a */
    private void m15531a(PushNotification.Builder builder, NotificationSortMessage notificationSortMessage) {
        Bundle bundle = new Bundle();
        bundle.putInt("EXTRA_AUTO_DELETE", notificationSortMessage.getAutoDelete());
        bundle.putInt("EXTRA_IMPORTANT_LEVEL", notificationSortMessage.getImportantLevel());
        bundle.putString("EXTRA_MESSAGE_ID", notificationSortMessage.getMessageId());
        bundle.putLong("EXTRA_POST_TIME", System.currentTimeMillis());
        bundle.putBoolean("EXTRA_IS_MCS", false);
        bundle.putString("EXTRA_STATISTIC_DATA", notificationSortMessage.getStatisticData());
        if (Build.VERSION.SDK_INT >= 20) {
            builder.addExtras(bundle);
            builder.setGroup(notificationSortMessage.getGroup());
        }
    }

    /* renamed from: a */
    private void m15528a(StatusBarNotification[] statusBarNotificationArr, int i) {
        m15527b();
        if (statusBarNotificationArr != null && statusBarNotificationArr.length != 0) {
            for (StatusBarNotification statusBarNotification : statusBarNotificationArr) {
                Bundle bundle = statusBarNotification.getNotification().extras;
                boolean z = bundle.getBoolean("EXTRA_IS_MCS", true);
                long j = bundle.getLong("EXTRA_POST_TIME", statusBarNotification.getPostTime());
                String string = bundle.getString("EXTRA_MESSAGE_ID", "");
                int i2 = bundle.getInt("EXTRA_AUTO_DELETE", 1);
                int i3 = bundle.getInt("EXTRA_IMPORTANT_LEVEL", 5);
                String string2 = bundle.getString("EXTRA_STATISTIC_DATA");
                int id = statusBarNotification.getId();
                if (i == id) {
                    this.f10691h = statusBarNotification;
                    return;
                }
                NotificationSortMessage notificationSortMessage = new NotificationSortMessage(string, i3, i2, z, j, id, string2);
                m15526b(i2);
                m15544a(i3);
                m15534a(notificationSortMessage);
            }
        }
        if (C4746d.m15476g()) {
            C4746d.m15494b("initParams : notDelete:" + this.f10690g + " canDelete : " + this.f10689f + "\n highSize : " + this.f10686c + " normalSize :" + this.f10687d + '\n');
            StringBuilder sb = new StringBuilder();
            sb.append("canDeleteList size : ");
            sb.append(this.f10685b.size());
            C4746d.m15494b(sb.toString());
            for (int i4 = 0; i4 < this.f10685b.size(); i4++) {
                NotificationSortMessage notificationSortMessage2 = this.f10685b.get(i4);
                C4746d.m15494b("第" + i4 + "条消息 messageId : " + notificationSortMessage2.getMessageId() + " importanceLevel : " + notificationSortMessage2.getImportantLevel() + " autoDelete : " + notificationSortMessage2.getAutoDelete() + " notifyId: " + notificationSortMessage2.getNotifyId() + " postTime:" + notificationSortMessage2.getPostTime());
            }
        }
    }

    /* renamed from: a */
    private boolean m15542a(NotificationManager notificationManager, Context context, PushNotification.Builder builder, NotificationSortMessage notificationSortMessage) {
        StringBuilder sb;
        String str;
        if (C4746d.m15476g()) {
            C4746d.m15494b("dealCurrentMessage : deleteNumber" + (this.f10689f + this.f10690g) + " keepNumber : " + this.f10684a);
        }
        boolean z = true;
        if (this.f10689f + this.f10690g < this.f10684a) {
            if (notificationSortMessage.getAutoDelete() == -1) {
                sb = new StringBuilder();
                str = "mcs.";
            } else {
                sb = new StringBuilder();
                str = "mcs.enable.";
            }
            sb.append(str);
            sb.append(context.getPackageName());
            notificationSortMessage.setGroup(sb.toString());
        } else if (notificationSortMessage.getAutoDelete() == -1) {
            notificationSortMessage.setGroup("mcs." + context.getPackageName());
            int i = this.f10684a - this.f10690g;
            if (C4746d.m15476g()) {
                C4746d.m15494b("dealCurrentMessage : allowDelete :" + i);
            }
            if (i > 0) {
                m15541a(context, notificationManager, i - 1);
            } else {
                Notification m15546a = C4730a.m15546a(context, notificationSortMessage.getGroup(), builder);
                if (m15546a != null) {
                    notificationManager.notify(4096, m15546a);
                }
            }
        } else {
            z = m15540a(context, notificationManager, notificationSortMessage);
        }
        if (C4746d.m15476g()) {
            C4746d.m15494b("dealCurrentMessage : needPost :" + z);
        }
        if (z) {
            m15531a(builder, notificationSortMessage);
        } else {
            C4742a.m15513a(context, "push_no_show_by_fold", m15536a(context, notificationSortMessage));
        }
        return z;
    }

    /* renamed from: a */
    private boolean m15540a(Context context, NotificationManager notificationManager, NotificationSortMessage notificationSortMessage) {
        int i = this.f10690g;
        int i2 = this.f10684a;
        boolean z = false;
        if (i >= i2) {
            return false;
        }
        int i3 = i2 - i;
        if (C4746d.m15476g()) {
            C4746d.m15494b("judgeShowCurrentMessage : allowDelete" + i3);
        }
        if (notificationSortMessage.getImportantLevel() == 7 || (notificationSortMessage.getImportantLevel() != 5 ? this.f10686c + this.f10687d < i3 : this.f10686c < i3)) {
            z = true;
        }
        if (z) {
            m15541a(context, notificationManager, i3 - 1);
        }
        return z;
    }

    /* renamed from: a */
    private boolean m15535a(Context context, PushNotification.Builder builder, NotificationSortMessage notificationSortMessage) {
        Notification notification;
        StringBuilder sb;
        String str;
        int verifyNotifyId = builder.getVerifyNotifyId();
        StatusBarNotification statusBarNotification = this.f10691h;
        if (statusBarNotification == null || verifyNotifyId == -1 || (notification = statusBarNotification.getNotification()) == null) {
            return false;
        }
        if (notificationSortMessage.getAutoDelete() == 1) {
            sb = new StringBuilder();
            str = "mcs.enable.";
        } else {
            sb = new StringBuilder();
            str = "mcs.";
        }
        sb.append(str);
        sb.append(context.getPackageName());
        notificationSortMessage.setGroup(sb.toString());
        Bundle bundle = notification.extras;
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("EXTRA_MESSAGE_ID", "");
        m15531a(builder, notificationSortMessage);
        this.f10688e.add(string);
        return true;
    }

    /* renamed from: a */
    private boolean m15532a(PushNotification.Builder builder, int i, int i2, String str, String str2) {
        Context context = PushService.getInstance().getContext();
        if (builder == null || context == null) {
            return false;
        }
        NotificationManager m15547a = C4730a.m15547a(context);
        NotificationSortMessage notificationSortMessage = new NotificationSortMessage(str, i2, i, false, System.currentTimeMillis(), str2);
        if (m15539a(context, m15547a, notificationSortMessage, builder)) {
            m15543a(m15547a, context, builder.getVerifyNotifyId());
            if (m15535a(context, builder, notificationSortMessage)) {
                return true;
            }
            return m15542a(m15547a, context, builder, notificationSortMessage);
        }
        return true;
    }

    /* renamed from: b */
    private void m15527b() {
        this.f10689f = 0;
        this.f10690g = 0;
        this.f10686c = 0;
        this.f10687d = 0;
        this.f10685b.clear();
        this.f10688e.clear();
        this.f10691h = null;
    }

    /* renamed from: b */
    private void m15526b(int i) {
        if (i == -1) {
            this.f10690g++;
        } else if (i == 1) {
            this.f10689f++;
        }
    }

    /* renamed from: a */
    public void m15530a(PushNotification.Builder builder, ISortListener iSortListener) {
        if (builder == null) {
            return;
        }
        m15533a(iSortListener, m15532a(builder, builder.getAutoDelete(), builder.getImportantLevel(), builder.getMessageId(), builder.getStatisticData()), builder);
    }

    /* renamed from: a */
    public boolean m15539a(Context context, NotificationManager notificationManager, NotificationSortMessage notificationSortMessage, PushNotification.Builder builder) {
        if (notificationSortMessage.getAutoDelete() != 0 && Build.VERSION.SDK_INT >= 24 && Build.VERSION.SDK_INT < 30) {
            if (C4730a.m15548a(notificationManager, context.getPackageName(), 4096)) {
                notificationSortMessage.setGroup("mcs." + context.getPackageName());
                m15531a(builder, notificationSortMessage);
                return false;
            }
            return true;
        }
        return false;
    }
}
