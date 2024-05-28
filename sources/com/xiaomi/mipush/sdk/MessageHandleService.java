package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11305dt;
import com.xiaomi.push.EnumC11317ed;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class MessageHandleService extends BaseService {

    /* renamed from: a */
    private static ConcurrentLinkedQueue<C11070a> f21320a = new ConcurrentLinkedQueue<>();

    /* renamed from: a */
    private static ExecutorService f21321a = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startService(final Context context) {
        final Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, MessageHandleService.class));
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.mipush.sdk.MessageHandleService.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    context.startService(intent);
                } catch (Exception e) {
                    AbstractC11049b.m5282a(e.getMessage());
                }
            }
        });
    }

    public static void addJob(Context context, C11070a c11070a) {
        if (c11070a != null) {
            f21320a.add(c11070a);
            m5202b(context);
            startService(context);
        }
    }

    /* renamed from: b */
    private static void m5202b(final Context context) {
        if (f21321a.isShutdown()) {
            return;
        }
        f21321a.execute(new Runnable() { // from class: com.xiaomi.mipush.sdk.MessageHandleService.2
            @Override // java.lang.Runnable
            public void run() {
                MessageHandleService.m5201c(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m5204a(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        m5202b(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m5201c(Context context) {
        try {
            m5203a(context, f21320a.poll());
        } catch (RuntimeException e) {
            AbstractC11049b.m5276a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m5203a(Context context, C11070a c11070a) {
        String[] stringArrayExtra;
        if (c11070a == null) {
            return;
        }
        try {
            PushMessageReceiver m5199a = c11070a.m5199a();
            Intent m5200a = c11070a.m5200a();
            int intExtra = m5200a.getIntExtra("message_type", 1);
            if (intExtra != 1) {
                switch (intExtra) {
                    case 3:
                        MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) m5200a.getSerializableExtra("key_command");
                        AbstractC11049b.m5266e("(Local) begin execute onCommandResult, command=" + miPushCommandMessage.getCommand() + ", resultCode=" + miPushCommandMessage.getResultCode() + ", reason=" + miPushCommandMessage.getReason());
                        m5199a.onCommandResult(context, miPushCommandMessage);
                        if (TextUtils.equals(miPushCommandMessage.getCommand(), EnumC11317ed.COMMAND_REGISTER.f22058a)) {
                            m5199a.onReceiveRegisterResult(context, miPushCommandMessage);
                            PushMessageHandler.m5173a(context, miPushCommandMessage);
                            if (miPushCommandMessage.getResultCode() == 0) {
                                C11094f.m5085b(context);
                                return;
                            }
                            return;
                        }
                        return;
                    case 4:
                        return;
                    case 5:
                        if (!"error_lack_of_permission".equals(m5200a.getStringExtra("error_type")) || (stringArrayExtra = m5200a.getStringArrayExtra("error_message")) == null) {
                            return;
                        }
                        AbstractC11049b.m5266e("begin execute onRequirePermissions, lack of necessary permissions");
                        m5199a.onRequirePermissions(context, stringArrayExtra);
                        return;
                    default:
                        return;
                }
            }
            PushMessageHandler.InterfaceC11085a m5026a = C11116t.m5030a(context).m5026a(m5200a);
            int intExtra2 = m5200a.getIntExtra("eventMessageType", -1);
            if (m5026a != null) {
                if (m5026a instanceof MiPushMessage) {
                    MiPushMessage miPushMessage = (MiPushMessage) m5026a;
                    if (!miPushMessage.isArrivedMessage()) {
                        m5199a.onReceiveMessage(context, miPushMessage);
                    }
                    if (miPushMessage.getPassThrough() == 1) {
                        C11305dt.m4117a(context.getApplicationContext()).m4114a(context.getPackageName(), m5200a, 2004, (String) null);
                        AbstractC11049b.m5267d("MessageHandleService", "begin execute onReceivePassThroughMessage from " + miPushMessage.getMessageId());
                        m5199a.onReceivePassThroughMessage(context, miPushMessage);
                        return;
                    } else if (miPushMessage.isNotified()) {
                        if (intExtra2 == 1000) {
                            C11305dt.m4117a(context.getApplicationContext()).m4114a(context.getPackageName(), m5200a, 1007, (String) null);
                        } else {
                            C11305dt.m4117a(context.getApplicationContext()).m4114a(context.getPackageName(), m5200a, 3007, (String) null);
                        }
                        AbstractC11049b.m5267d("MessageHandleService", "begin execute onNotificationMessageClicked from\u3000" + miPushMessage.getMessageId());
                        m5199a.onNotificationMessageClicked(context, miPushMessage);
                        return;
                    } else {
                        AbstractC11049b.m5267d("MessageHandleService", "begin execute onNotificationMessageArrived from " + miPushMessage.getMessageId());
                        m5199a.onNotificationMessageArrived(context, miPushMessage);
                        return;
                    }
                } else if (m5026a instanceof MiPushCommandMessage) {
                    MiPushCommandMessage miPushCommandMessage2 = (MiPushCommandMessage) m5026a;
                    AbstractC11049b.m5267d("MessageHandleService", "begin execute onCommandResult, command=" + miPushCommandMessage2.getCommand() + ", resultCode=" + miPushCommandMessage2.getResultCode() + ", reason=" + miPushCommandMessage2.getReason());
                    m5199a.onCommandResult(context, miPushCommandMessage2);
                    if (TextUtils.equals(miPushCommandMessage2.getCommand(), EnumC11317ed.COMMAND_REGISTER.f22058a)) {
                        m5199a.onReceiveRegisterResult(context, miPushCommandMessage2);
                        PushMessageHandler.m5173a(context, miPushCommandMessage2);
                        if (miPushCommandMessage2.getResultCode() == 0) {
                            C11094f.m5085b(context);
                            return;
                        }
                        return;
                    }
                    return;
                } else {
                    AbstractC11049b.m5267d("MessageHandleService", "unknown raw message: " + m5026a);
                    return;
                }
            }
            AbstractC11049b.m5267d("MessageHandleService", "no message from raw for receiver");
        } catch (RuntimeException e) {
            AbstractC11049b.m5279a("MessageHandleService", e);
        }
    }

    @Override // com.xiaomi.mipush.sdk.BaseService, android.app.Service
    public void onStart(Intent intent, int i) {
        super.onStart(intent, i);
    }

    @Override // com.xiaomi.mipush.sdk.BaseService
    /* renamed from: a */
    protected boolean mo5178a() {
        ConcurrentLinkedQueue<C11070a> concurrentLinkedQueue = f21320a;
        return concurrentLinkedQueue != null && concurrentLinkedQueue.size() > 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.mipush.sdk.MessageHandleService$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11070a {

        /* renamed from: a */
        private Intent f21325a;

        /* renamed from: a */
        private PushMessageReceiver f21326a;

        public C11070a(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.f21326a = pushMessageReceiver;
            this.f21325a = intent;
        }

        /* renamed from: a */
        public PushMessageReceiver m5199a() {
            return this.f21326a;
        }

        /* renamed from: a */
        public Intent m5200a() {
            return this.f21325a;
        }
    }
}
