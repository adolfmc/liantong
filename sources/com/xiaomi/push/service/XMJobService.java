package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11311dz;
import com.xiaomi.push.C11469j;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class XMJobService extends Service {

    /* renamed from: a */
    static Service f23389a;

    /* renamed from: a */
    private IBinder f23390a = null;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 21) {
            this.f23390a = new jobJobServiceC11484a(this).f23391a;
        }
        f23389a = this;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        f23389a = null;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        IBinder iBinder = this.f23390a;
        return iBinder != null ? iBinder : new Binder();
    }

    @TargetApi(21)
    /* renamed from: com.xiaomi.push.service.XMJobService$a  reason: invalid class name */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class jobJobServiceC11484a extends JobService {

        /* renamed from: a */
        Binder f23391a;

        /* renamed from: a */
        private Handler f23392a;

        jobJobServiceC11484a(Service service) {
            this.f23391a = null;
            this.f23391a = (Binder) C11176aw.m4812a((Object) this, "onBind", new Intent());
            C11176aw.m4812a((Object) this, "attachBaseContext", service);
        }

        /* renamed from: com.xiaomi.push.service.XMJobService$a$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        static class HandlerC11485a extends Handler {

            /* renamed from: a */
            JobService f23393a;

            HandlerC11485a(JobService jobService) {
                super(jobService.getMainLooper());
                this.f23393a = jobService;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                JobParameters jobParameters = (JobParameters) message.obj;
                AbstractC11049b.m5282a("Job finished " + jobParameters.getJobId());
                this.f23393a.jobFinished(jobParameters, false);
                if (jobParameters.getJobId() == 1) {
                    C11311dz.m4064a(false);
                }
            }
        }

        @Override // android.app.job.JobService
        public boolean onStartJob(JobParameters jobParameters) {
            AbstractC11049b.m5282a("Job started " + jobParameters.getJobId());
            Intent intent = new Intent(this, XMPushService.class);
            intent.setAction("com.xiaomi.push.timer");
            intent.setPackage(getPackageName());
            startService(intent);
            if (this.f23392a == null) {
                this.f23392a = new HandlerC11485a(this);
            }
            Handler handler = this.f23392a;
            handler.sendMessage(Message.obtain(handler, 1, jobParameters));
            return true;
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters jobParameters) {
            AbstractC11049b.m5282a("Job stop " + jobParameters.getJobId());
            return false;
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int onStartCommand = super.onStartCommand(intent, i, i2);
        if (C11469j.m2972a((Context) this)) {
            return onStartCommand;
        }
        return 2;
    }
}
