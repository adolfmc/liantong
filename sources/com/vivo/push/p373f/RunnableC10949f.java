package com.vivo.push.p373f;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.vivo.push.util.LogUtil;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NotifyOpenClientClickTask.java */
/* renamed from: com.vivo.push.f.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10949f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f20986a;

    /* renamed from: b */
    final /* synthetic */ Map f20987b;

    /* renamed from: c */
    final /* synthetic */ NotifyOpenClientClickTask f20988c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10949f(NotifyOpenClientClickTask notifyOpenClientClickTask, Context context, Map map) {
        this.f20988c = notifyOpenClientClickTask;
        this.f20986a = context;
        this.f20987b = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String packageName = this.f20986a.getPackageName();
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) this.f20986a.getSystemService("activity")).getRunningTasks(100);
            if (runningTasks != null) {
                for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                    ComponentName componentName = runningTaskInfo.topActivity;
                    if (componentName.getPackageName().equals(packageName)) {
                        LogUtil.m5341d("NotifyOpenClientTask", "topClassName=" + componentName.getClassName());
                        Intent intent = new Intent();
                        intent.setComponent(componentName);
                        intent.setFlags(335544320);
                        NotifyOpenClientClickTask.m5688b(intent, this.f20987b);
                        this.f20986a.startActivity(intent);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.m5353a("NotifyOpenClientTask", "start recentIntent is error", e);
        }
        Intent launchIntentForPackage = this.f20986a.getPackageManager().getLaunchIntentForPackage(this.f20986a.getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.setFlags(268435456);
            NotifyOpenClientClickTask.m5688b(launchIntentForPackage, this.f20987b);
            this.f20986a.startActivity(launchIntentForPackage);
            return;
        }
        LogUtil.m5354a("NotifyOpenClientTask", "LaunchIntent is null");
    }
}
