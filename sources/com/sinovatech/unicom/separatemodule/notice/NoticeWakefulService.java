package com.sinovatech.unicom.separatemodule.notice;

import android.app.IntentService;
import android.content.Intent;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.common.URLEnvironmentConfig;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoticeWakefulService extends IntentService {
    private ConfigManager configM;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fb: MOVE  (r0 I:??[long, double]) = (r63389 I:??[long, double]), expected to be less than 19
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    public static void handleResult(android.content.Context r17, java.lang.Object[] r18) {
        /*
            Method dump skipped, instructions count: 1145
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.notice.NoticeWakefulService.handleResult(android.content.Context, java.lang.Object[]):void");
    }

    public NoticeWakefulService() {
        super("NoticeWakefulService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.content.Intent] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r6v2, types: [android.content.Intent] */
    @Override // android.app.IntentService
    protected void onHandleIntent(Intent e) {
        try {
            try {
                try {
                    PushServer pushServer = new PushServer(this);
                    this.configM = new ConfigManager(getApplicationContext());
                    pushServer.registPushServer("PUSH0000");
                    handleResult(getApplicationContext(), pushServer.sendPushServer("PUSH0002"));
                    PushAlarmManager.startAlarmManagerPush(this.configM.getMsgPushInterval());
                    new NoticManager().getNoticConfigData();
                    if (!URLEnvironmentConfig.isForPublish()) {
                        PushAlarmManager.startAlarmManagerPush(30000L);
                    }
                    NoticeWakefulReceiver.completeWakefulIntent(e);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    PushAlarmManager.startAlarmManagerPush(this.configM.getMsgPushInterval());
                    new NoticManager().getNoticConfigData();
                    if (!URLEnvironmentConfig.isForPublish()) {
                        PushAlarmManager.startAlarmManagerPush(30000L);
                    }
                    NoticeWakefulReceiver.completeWakefulIntent(e);
                }
            } catch (Throwable th) {
                try {
                    PushAlarmManager.startAlarmManagerPush(this.configM.getMsgPushInterval());
                    new NoticManager().getNoticConfigData();
                    if (!URLEnvironmentConfig.isForPublish()) {
                        PushAlarmManager.startAlarmManagerPush(30000L);
                    }
                    NoticeWakefulReceiver.completeWakefulIntent(e);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            e.printStackTrace();
        }
    }
}
