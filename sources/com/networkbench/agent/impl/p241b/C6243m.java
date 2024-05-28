package com.networkbench.agent.impl.p241b;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6650r;
import com.networkbench.agent.impl.util.C6653u;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.m */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6243m {

    /* renamed from: d */
    private static final InterfaceC6393e f15467d = C6394f.m10150a();

    /* renamed from: e */
    private static final int f15468e = 50;

    /* renamed from: b */
    long f15470b;

    /* renamed from: c */
    public int f15471c;

    /* renamed from: f */
    private String f15472f = "";

    /* renamed from: g */
    private String f15473g = "Dear developer,a slowly operation is blocking the main thread.";

    /* renamed from: a */
    RunnableC6242l f15469a = new RunnableC6242l(null, 0, 5);

    /* renamed from: a */
    public String m10890a() {
        return this.f15472f;
    }

    /* renamed from: b */
    public String m10886b() {
        return this.f15473g;
    }

    /* renamed from: a */
    private ActivityManager.ProcessErrorStateInfo m10888a(Context context, long j) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        long j2 = j / 500;
        int i = 0;
        while (C6229b.m10939a().m10938b().mo10929c().longValue() == this.f15470b) {
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.condition == 2) {
                        f15467d.mo10122a(" 获取到了anr数据信息....");
                        return processErrorStateInfo;
                    }
                }
            }
            try {
                Thread.sleep(500L);
            } catch (Exception e) {
                InterfaceC6393e interfaceC6393e = f15467d;
                interfaceC6393e.mo10116d("geteErrorStateInfo e = " + e);
            }
            int i2 = i + 1;
            if (i >= j2) {
                if (C6229b.m10939a().m10938b().mo10929c().longValue() == this.f15470b) {
                    f15467d.mo10122a(" 说明主线程还在阻塞状态, 而且没有获取到anr信息, 进入新的20秒循环");
                    return m10888a(context, j);
                }
                f15467d.mo10122a(" 说明主线程处于恢复状态, 而且没有获取到anr信息, 退出获取.....");
                return null;
            }
            i = i2;
        }
        f15467d.mo10122a("说明主线程已经恢复  不再获取进程信息 !!!");
        return null;
    }

    /* renamed from: b */
    private ActivityManager.ProcessErrorStateInfo m10885b(Context context, long j) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        long j2 = j / 500;
        boolean z = false;
        int i = 0;
        while (true) {
            if (z) {
                this.f15469a.m10891b();
            }
            z = !z;
            List<ActivityManager.ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ActivityManager.ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.condition == 2) {
                        f15467d.mo10122a(" 获取到了anr数据信息....");
                        return processErrorStateInfo;
                    }
                }
            }
            try {
                Thread.sleep(500L);
            } catch (Exception e) {
                f15467d.mo10116d("geteErrorStateInfo e = " + e);
            }
            int i2 = i + 1;
            if (i >= j2) {
                f15467d.mo10122a(" 说明这个5秒的循环周期内 没有出现弹窗, 而且没有获取到anr信息, 退出获取.....");
                return null;
            }
            i = i2;
        }
    }

    /* renamed from: a */
    public boolean m10889a(Context context) throws C6632b {
        boolean z = false;
        if (context == null) {
            return false;
        }
        try {
            this.f15470b = C6229b.m10939a().m10938b().mo10929c().longValue();
            ActivityManager.ProcessErrorStateInfo m10885b = m10885b(context, 5000L);
            if (m10885b == null) {
                f15467d.mo10116d("can not find processErrorState");
            } else if (m10885b.pid == Process.myPid()) {
                this.f15471c = m10885b.longMsg.hashCode();
                String str = m10885b.shortMsg;
                this.f15472f = m10885b.longMsg;
                this.f15473g = str;
                z = true;
            }
        } catch (Throwable th) {
            f15467d.mo10116d(th.getMessage());
        }
        return z;
    }

    /* renamed from: d */
    private boolean m10883d() {
        Context m9076K = C6638h.m8963w().m9076K();
        return m9076K.getPackageManager().checkPermission("android.permission.READ_EXTERNAL_STORAGE", m9076K.getPackageName()) == 0;
    }

    /* renamed from: a */
    public String m10887a(String str) {
        if (m10883d()) {
            return C6653u.m8718c(str);
        }
        f15467d.mo10122a("not open permission");
        return "";
    }

    /* renamed from: c */
    public String m10884c() {
        return m10882e();
    }

    /* renamed from: e */
    private String m10882e() {
        return new C6650r(50).m8764b();
    }
}
