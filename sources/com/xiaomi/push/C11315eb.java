package com.xiaomi.push;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11311dz;
import com.xiaomi.push.service.XMJobService;

@TargetApi(21)
/* renamed from: com.xiaomi.push.eb */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11315eb implements C11311dz.InterfaceC11312a {

    /* renamed from: a */
    JobScheduler f22043a;

    /* renamed from: a */
    Context f22044a;

    /* renamed from: a */
    private boolean f22045a = false;

    C11315eb(Context context) {
        this.f22044a = context;
        this.f22043a = (JobScheduler) context.getSystemService("jobscheduler");
    }

    /* renamed from: a */
    void m4050a(long j) {
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this.f22044a.getPackageName(), XMJobService.class.getName()));
        builder.setMinimumLatency(j);
        builder.setOverrideDeadline(j);
        builder.setRequiredNetworkType(1);
        JobInfo build = builder.build();
        AbstractC11049b.m5270c("schedule Job = " + build.getId() + " in " + j);
        this.f22043a.schedule(builder.build());
    }

    @Override // com.xiaomi.push.C11311dz.InterfaceC11312a
    /* renamed from: a */
    public void mo4049a(boolean z) {
        if (z || this.f22045a) {
            long m3855b = C11363fg.m3855b();
            if (z) {
                mo4052a();
                m3855b -= SystemClock.elapsedRealtime() % m3855b;
            }
            this.f22045a = true;
            m4050a(m3855b);
        }
    }

    @Override // com.xiaomi.push.C11311dz.InterfaceC11312a
    /* renamed from: a */
    public void mo4052a() {
        this.f22045a = false;
        this.f22043a.cancel(1);
    }

    @Override // com.xiaomi.push.C11311dz.InterfaceC11312a
    /* renamed from: a */
    public boolean mo4051a() {
        return this.f22045a;
    }
}
