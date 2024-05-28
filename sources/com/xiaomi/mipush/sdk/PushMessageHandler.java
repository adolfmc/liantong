package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.push.C11305dt;
import com.xiaomi.push.C11408gj;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.EnumC11317ed;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PushMessageHandler extends BaseService {

    /* renamed from: a */
    private static List<MiPushClient.ICallbackResult> f21345a = new ArrayList();

    /* renamed from: b */
    private static List<MiPushClient.MiPushClientCallback> f21347b = new ArrayList();

    /* renamed from: a */
    private static ThreadPoolExecutor f21346a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.mipush.sdk.PushMessageHandler$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11085a extends Serializable {
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* renamed from: a */
    public static void m5176a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, PushMessageHandler.class));
        try {
            context.startService(intent);
        } catch (Exception e) {
            AbstractC11049b.m5280a("PushMessageHandler", e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m5175a(Context context, Intent intent) {
        AbstractC11049b.m5272b("PushMessageHandler", "addjob PushMessageHandler " + intent);
        if (intent != null) {
            m5160c(context, intent);
            m5176a(context);
        }
    }

    /* renamed from: c */
    private static void m5160c(final Context context, final Intent intent) {
        if (intent != null && !f21346a.isShutdown()) {
            f21346a.execute(new Runnable() { // from class: com.xiaomi.mipush.sdk.PushMessageHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    PushMessageHandler.m5162b(context, intent);
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("-->scheduleJob() fail, case");
        sb.append(intent == null ? "0" : "1");
        AbstractC11049b.m5267d("PushMessageHandler", sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m5167a(MiPushClient.MiPushClientCallback miPushClientCallback) {
        synchronized (f21347b) {
            if (!f21347b.contains(miPushClientCallback)) {
                f21347b.add(miPushClientCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m5168a(MiPushClient.ICallbackResult iCallbackResult) {
        synchronized (f21345a) {
            if (!f21345a.contains(iCallbackResult)) {
                f21345a.add(iCallbackResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m5179a() {
        synchronized (f21347b) {
            f21347b.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static void m5165b() {
        synchronized (f21345a) {
            f21345a.clear();
        }
    }

    /* renamed from: b */
    protected static void m5162b(Context context, Intent intent) {
        boolean z;
        try {
            z = intent.getBooleanExtra("is_clicked_activity_call", false);
        } catch (Throwable th) {
            AbstractC11049b.m5280a("PushMessageHandler", "intent unparcel error:" + th);
            z = false;
        }
        try {
            AbstractC11049b.m5277a("PushMessageHandler", "-->onHandleIntent(): action=", intent.getAction());
            if ("com.xiaomi.mipush.SEND_TINYDATA".equals(intent.getAction())) {
                C11408gj c11408gj = new C11408gj();
                C11441hp.m3084a(c11408gj, intent.getByteArrayExtra("mipush_payload"));
                AbstractC11049b.m5272b("PushMessageHandler", "PushMessageHandler.onHandleIntent " + c11408gj.m3651d());
                MiTinyDataClient.upload(context, c11408gj);
            } else if (1 == PushMessageHelper.getPushMode(context)) {
                if (m5164b()) {
                    AbstractC11049b.m5269c("PushMessageHandler", "receive a message before application calling initialize");
                    if (z) {
                        m5163b(context);
                        return;
                    }
                    return;
                }
                InterfaceC11085a m5026a = C11116t.m5030a(context).m5026a(intent);
                if (m5026a != null) {
                    m5171a(context, m5026a);
                }
            } else if (!"com.xiaomi.mipush.sdk.SYNC_LOG".equals(intent.getAction())) {
                Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent2.setPackage(context.getPackageName());
                intent2.putExtras(intent);
                try {
                    List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 32);
                    ResolveInfo resolveInfo = null;
                    if (queryBroadcastReceivers != null) {
                        Iterator<ResolveInfo> it = queryBroadcastReceivers.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ResolveInfo next = it.next();
                            if (next.activityInfo != null && next.activityInfo.packageName.equals(context.getPackageName()) && PushMessageReceiver.class.isAssignableFrom(C11479r.m2929a(context, next.activityInfo.name))) {
                                resolveInfo = next;
                                break;
                            }
                        }
                    }
                    if (resolveInfo != null) {
                        m5174a(context, intent2, resolveInfo, z);
                    } else {
                        AbstractC11049b.m5269c("PushMessageHandler", "cannot find the receiver to handler this message, check your manifest");
                        C11305dt.m4117a(context).m4113a(context.getPackageName(), intent, "11");
                    }
                } catch (Exception e) {
                    AbstractC11049b.m5279a("PushMessageHandler", e);
                    C11305dt.m4117a(context).m4113a(context.getPackageName(), intent, "9");
                }
            }
        } finally {
            if (z) {
                m5163b(context);
            }
        }
    }

    /* renamed from: b */
    private static void m5163b(Context context) {
        try {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction("action_clicked_activity_finish");
            context.sendBroadcast(intent, C11089c.m5116a(context));
        } catch (Exception e) {
            AbstractC11049b.m5280a("PushMessageHandler", "callback sync error" + e);
        }
    }

    /* renamed from: a */
    private static void m5174a(Context context, Intent intent, ResolveInfo resolveInfo, boolean z) {
        try {
            MessageHandleService.C11070a c11070a = new MessageHandleService.C11070a(intent, (PushMessageReceiver) C11479r.m2929a(context, resolveInfo.activityInfo.name).newInstance());
            if (z) {
                MessageHandleService.m5203a(context.getApplicationContext(), c11070a);
            } else {
                MessageHandleService.addJob(context.getApplicationContext(), c11070a);
            }
            MessageHandleService.m5204a(context, new Intent(context.getApplicationContext(), MessageHandleService.class));
        } catch (Throwable th) {
            AbstractC11049b.m5276a(th);
        }
    }

    /* renamed from: b */
    public static boolean m5164b() {
        return f21347b.isEmpty();
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
        m5160c(getApplicationContext(), intent);
    }

    @Override // com.xiaomi.mipush.sdk.BaseService
    /* renamed from: a */
    protected boolean mo5178a() {
        ThreadPoolExecutor threadPoolExecutor = f21346a;
        return (threadPoolExecutor == null || threadPoolExecutor.getQueue() == null || f21346a.getQueue().size() <= 0) ? false : true;
    }

    /* renamed from: a */
    public static void m5171a(Context context, InterfaceC11085a interfaceC11085a) {
        if (interfaceC11085a instanceof MiPushMessage) {
            m5172a(context, (MiPushMessage) interfaceC11085a);
        } else if (interfaceC11085a instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) interfaceC11085a;
            String command = miPushCommandMessage.getCommand();
            String str = null;
            if (EnumC11317ed.COMMAND_REGISTER.f22058a.equals(command)) {
                List<String> commandArguments = miPushCommandMessage.getCommandArguments();
                if (commandArguments != null && !commandArguments.isEmpty()) {
                    str = commandArguments.get(0);
                }
                m5177a(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if (EnumC11317ed.COMMAND_SET_ALIAS.f22058a.equals(command) || EnumC11317ed.COMMAND_UNSET_ALIAS.f22058a.equals(command) || EnumC11317ed.COMMAND_SET_ACCEPT_TIME.f22058a.equals(command)) {
                m5169a(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
            } else if (EnumC11317ed.COMMAND_SUBSCRIBE_TOPIC.f22058a.equals(command)) {
                List<String> commandArguments2 = miPushCommandMessage.getCommandArguments();
                if (commandArguments2 != null && !commandArguments2.isEmpty()) {
                    str = commandArguments2.get(0);
                }
                m5170a(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if (EnumC11317ed.COMMAND_UNSUBSCRIBE_TOPIC.f22058a.equals(command)) {
                List<String> commandArguments3 = miPushCommandMessage.getCommandArguments();
                if (commandArguments3 != null && !commandArguments3.isEmpty()) {
                    str = commandArguments3.get(0);
                }
                m5161b(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            }
        }
    }

    /* renamed from: a */
    public static void m5172a(Context context, MiPushMessage miPushMessage) {
        synchronized (f21347b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f21347b) {
                if (m5166a(miPushMessage.getCategory(), miPushClientCallback.getCategory())) {
                    miPushClientCallback.onReceiveMessage(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    miPushClientCallback.onReceiveMessage(miPushMessage);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m5177a(long j, String str, String str2) {
        synchronized (f21347b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f21347b) {
                miPushClientCallback.onInitializeResult(j, str, str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m5170a(Context context, String str, long j, String str2, String str3) {
        synchronized (f21347b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f21347b) {
                if (m5166a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onSubscribeResult(j, str2, str3);
                }
            }
        }
    }

    /* renamed from: b */
    protected static void m5161b(Context context, String str, long j, String str2, String str3) {
        synchronized (f21347b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f21347b) {
                if (m5166a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onUnsubscribeResult(j, str2, str3);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m5169a(Context context, String str, String str2, long j, String str3, List<String> list) {
        synchronized (f21347b) {
            for (MiPushClient.MiPushClientCallback miPushClientCallback : f21347b) {
                if (m5166a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onCommandResult(str2, j, str3, list);
                }
            }
        }
    }

    /* renamed from: a */
    protected static boolean m5166a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m5173a(Context context, MiPushCommandMessage miPushCommandMessage) {
        synchronized (f21345a) {
            for (MiPushClient.ICallbackResult iCallbackResult : f21345a) {
                if (iCallbackResult instanceof MiPushClient.UPSRegisterCallBack) {
                    MiPushClient.TokenResult tokenResult = new MiPushClient.TokenResult();
                    if (miPushCommandMessage != null && miPushCommandMessage.getCommandArguments() != null && miPushCommandMessage.getCommandArguments().size() > 0) {
                        tokenResult.setResultCode(miPushCommandMessage.getResultCode());
                        tokenResult.setToken(miPushCommandMessage.getCommandArguments().get(0));
                    }
                    iCallbackResult.onResult(tokenResult);
                }
            }
        }
    }
}
