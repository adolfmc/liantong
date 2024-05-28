package com.mob.commons.p229a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.mob.commons.C5747b;
import com.mob.commons.C5782c;
import com.mob.commons.C5873u;
import com.mob.commons.C5892y;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;

/* renamed from: com.mob.commons.a.l */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5731l implements Handler.Callback {

    /* renamed from: a */
    private static C5731l f14120a = new C5731l();

    /* renamed from: b */
    private Handler f14121b;

    private C5731l() {
        String str;
        if (TextUtils.isEmpty("M-")) {
            str = null;
        } else {
            str = C5892y.f14523a + m12674a("004Uflidilig");
        }
        this.f14121b = MobHandlerThread.newHandler(str, this);
    }

    /* renamed from: a */
    public static C5731l m12681a() {
        return f14120a;
    }

    /* renamed from: b */
    public Handler m12673b() {
        return this.f14121b;
    }

    /* renamed from: c */
    public Looper m12670c() {
        Handler handler = this.f14121b;
        if (handler != null) {
            return handler.getLooper();
        }
        return null;
    }

    /* renamed from: a */
    public void m12678a(long j, Class<? extends AbstractRunnableC5704c> cls, Object[] objArr, int i) {
        int m12675a = m12675a(cls);
        if (i == 1) {
            this.f14121b.removeMessages(m12675a);
        } else if (i == 2 && this.f14121b.hasMessages(m12675a)) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = m12675a;
        obtain.obj = new Object[]{cls, objArr};
        m12676a(obtain, j * 1000);
    }

    /* renamed from: a */
    public boolean m12677a(long j, Runnable runnable) {
        return m12680a(1003, j * 1000, runnable);
    }

    /* renamed from: b */
    public boolean m12671b(long j, Runnable runnable) {
        return m12680a(1004, j * 1000, runnable);
    }

    /* renamed from: c */
    public boolean m12669c(long j, Runnable runnable) {
        return m12680a(1006, j * 1000, runnable);
    }

    /* renamed from: d */
    public boolean m12667d(long j, Runnable runnable) {
        return m12672b(1007, j * 1000, runnable);
    }

    /* renamed from: e */
    public boolean m12666e(long j, Runnable runnable) {
        return m12672b(1005, j, runnable);
    }

    /* renamed from: a */
    public void m12679a(long j, int i, C5782c.RunnableC5789b runnableC5789b) {
        Message obtain = Message.obtain();
        obtain.what = 1002;
        obtain.arg1 = i;
        obtain.obj = runnableC5789b;
        m12676a(obtain, j * 1000);
    }

    /* renamed from: d */
    public void m12668d() {
        this.f14121b.removeMessages(1002);
    }

    /* renamed from: a */
    private boolean m12680a(int i, long j, Runnable runnable) {
        if (this.f14121b.hasMessages(i)) {
            return false;
        }
        m12672b(i, j, runnable);
        return true;
    }

    /* renamed from: b */
    private boolean m12672b(int i, long j, Runnable runnable) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = runnable;
        m12676a(obtain, j);
        return true;
    }

    /* renamed from: a */
    private int m12675a(Class<? extends AbstractRunnableC5704c> cls) {
        int hashCode = cls.getName().hashCode();
        return hashCode > 0 ? hashCode + 10000 : hashCode - 10000;
    }

    /* renamed from: a */
    private void m12676a(Message message, long j) {
        if (j > 0) {
            this.f14121b.sendMessageDelayed(message, j);
        } else {
            this.f14121b.sendMessage(message);
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        AbstractRunnableC5704c m12768a;
        try {
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        if (!C5747b.m12557d()) {
            Message obtain = Message.obtain();
            obtain.copyFrom(message);
            this.f14121b.sendMessageDelayed(obtain, 60000L);
            return false;
        }
        if (message.what != 1003 && message.what != 1004 && message.what != 1006) {
            if (message.what == 1002) {
                C5782c.RunnableC5789b runnableC5789b = (C5782c.RunnableC5789b) message.obj;
                if (runnableC5789b != null) {
                    if (!runnableC5789b.f14272a) {
                        runnableC5789b.f14272a = true;
                    }
                    C5892y.f14526d.execute(runnableC5789b);
                    int i = message.arg1;
                    Message obtain2 = Message.obtain();
                    obtain2.what = 1002;
                    obtain2.obj = runnableC5789b;
                    obtain2.arg1 = i;
                    m12676a(obtain2, i * 1000);
                }
            } else if (message.what == 1005) {
                Runnable runnable = (Runnable) message.obj;
                if (runnable != null) {
                    C5892y.f14525c.execute(runnable);
                }
            } else if (message.what == 1007) {
                Runnable runnable2 = (Runnable) message.obj;
                if (runnable2 != null) {
                    C5892y.f14525c.execute(runnable2);
                }
            } else if (message.what >= 10000 || message.what < -10000) {
                Object[] objArr = (Object[]) message.obj;
                Class cls = (Class) objArr[0];
                if (cls != null && (m12768a = AbstractRunnableC5704c.m12768a((Class<? extends AbstractRunnableC5704c>) cls)) != null) {
                    Object[] objArr2 = (Object[]) objArr[1];
                    m12768a.f14067b = ((Integer) objArr2[0]).intValue();
                    m12768a.f14066a = objArr2[1];
                    m12768a.m12753g();
                }
            }
            return false;
        }
        Runnable runnable3 = (Runnable) message.obj;
        if (runnable3 != null) {
            C5892y.f14526d.execute(runnable3);
        }
        return false;
    }

    /* renamed from: a */
    public static String m12674a(String str) {
        return C5873u.m12180a(str, 100);
    }
}
