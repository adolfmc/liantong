package com.gmrz.android.client.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.SparseArray;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ActivityStarter {
    public static final int DEFAULT_TIMEOUT = 60000;

    /* renamed from: a */
    private static final String f10153a = "ActivityStarter";

    /* renamed from: b */
    private static final Object f10154b = new Object();

    /* renamed from: c */
    private static final Lock f10155c;

    /* renamed from: d */
    private static final Condition f10156d;

    /* renamed from: e */
    private static final SparseArray<ActivityStarter> f10157e;

    /* renamed from: f */
    private static final AtomicInteger f10158f;

    /* renamed from: g */
    private static Handler f10159g;

    /* renamed from: j */
    private int f10162j;

    /* renamed from: m */
    private Object f10165m;

    /* renamed from: h */
    private Activity f10160h = null;

    /* renamed from: i */
    private final Semaphore f10161i = new Semaphore(0, true);

    /* renamed from: k */
    private int f10163k = 60000;

    /* renamed from: l */
    private CountDownTimer f10164l = null;

    /* renamed from: n */
    private Object f10166n = null;

    /* renamed from: o */
    private boolean f10167o = false;

    /* renamed from: f */
    static /* synthetic */ Activity m15897f(ActivityStarter activityStarter) {
        activityStarter.f10160h = null;
        return null;
    }

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        f10155c = reentrantLock;
        f10156d = reentrantLock.newCondition();
        f10157e = new SparseArray<>(4);
        f10158f = new AtomicInteger(0);
        f10159g = null;
    }

    /* renamed from: a */
    private static ActivityStarter m15908a(int i) {
        ActivityStarter activityStarter = null;
        if (i == 0) {
            Logger.m15892e(f10153a, "Invalid lock ID");
        } else {
            synchronized (f10154b) {
                ActivityStarter activityStarter2 = f10157e.get(i);
                if (activityStarter2 == null) {
                    String str = f10153a;
                    Logger.m15892e(str, "no lock found for id " + i);
                } else if (activityStarter2.f10162j != i) {
                    String str2 = f10153a;
                    Logger.m15892e(str2, "id mismatch for the lock: expected=" + i + ", actual=" + activityStarter2.f10162j);
                }
                activityStarter = activityStarter2;
            }
        }
        return activityStarter;
    }

    private ActivityStarter(Object obj) {
        this.f10162j = 0;
        this.f10165m = null;
        this.f10165m = obj;
        synchronized (f10154b) {
            this.f10162j = f10158f.incrementAndGet();
            String str = f10153a;
            Logger.m15895d(str, toString() + ".newId");
            f10157e.put(this.f10162j, this);
        }
    }

    public String toString() {
        return "as[" + this.f10162j + "," + Thread.currentThread().getId() + "]";
    }

    /* renamed from: c */
    private void m15902c() {
        f10159g.post(new Runnable() { // from class: com.gmrz.android.client.utils.ActivityStarter.1
            /* JADX WARN: Type inference failed for: r2v4, types: [com.gmrz.android.client.utils.ActivityStarter$1$1] */
            @Override // java.lang.Runnable
            public final void run() {
                synchronized (ActivityStarter.f10154b) {
                    ActivityStarter.this.m15900d();
                    if (ActivityStarter.this.f10162j != 0) {
                        String str = ActivityStarter.f10153a;
                        Logger.m15895d(str, ActivityStarter.this.toString() + ".startTimeoutTimer(" + ActivityStarter.this.f10163k + ")");
                        ActivityStarter.this.f10164l = new CountDownTimer((long) ActivityStarter.this.f10163k) { // from class: com.gmrz.android.client.utils.ActivityStarter.1.1
                            @Override // android.os.CountDownTimer
                            public final void onTick(long j) {
                            }

                            @Override // android.os.CountDownTimer
                            public final void onFinish() {
                                synchronized (ActivityStarter.f10154b) {
                                    if (ActivityStarter.this.f10164l != null) {
                                        ActivityStarter.this.f10164l = null;
                                        String str2 = ActivityStarter.f10153a;
                                        Logger.m15895d(str2, ActivityStarter.this.toString() + ".onTimeout");
                                        if (ActivityStarter.this.f10160h != null) {
                                            String str3 = ActivityStarter.f10153a;
                                            Logger.m15895d(str3, ActivityStarter.this.toString() + ": finish the activity");
                                            ActivityStarter.this.f10160h.finish();
                                            ActivityStarter.m15897f(ActivityStarter.this);
                                        }
                                    }
                                }
                            }
                        }.start();
                    } else {
                        String str2 = ActivityStarter.f10153a;
                        Logger.m15895d(str2, toString() + ".startTimeoutTimer: lock is released");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m15900d() {
        synchronized (f10154b) {
            if (this.f10164l != null) {
                String str = f10153a;
                Logger.m15895d(str, toString() + ".stopTimer");
                this.f10164l.cancel();
                this.f10164l = null;
            }
        }
    }

    public static boolean setActivity(Activity activity, Intent intent) {
        f10155c.lock();
        try {
            boolean z = false;
            ActivityStarter m15908a = m15908a(intent.getIntExtra("LOCK", 0));
            if (m15908a != null) {
                String str = f10153a;
                Logger.m15895d(str, m15908a.toString() + ".setActivity(" + activity + ")");
                m15908a.f10160h = activity;
                f10156d.signalAll();
                z = true;
            }
            return z;
        } finally {
            f10155c.unlock();
        }
    }

    /* renamed from: a */
    private static boolean m15905a(boolean z, ActivityStarter activityStarter) {
        if (z) {
            f10155c.lock();
            try {
                if (activityStarter.f10160h == null) {
                    if (!f10156d.await(2000L, TimeUnit.MILLISECONDS)) {
                        synchronized (f10154b) {
                            f10157e.remove(activityStarter.f10162j);
                            Logger.m15895d(f10153a, "Activity not set in 2000ms. Returning.");
                        }
                        f10155c.unlock();
                        return false;
                    }
                }
                return true;
            } catch (InterruptedException unused) {
                return m15905a(z, activityStarter);
            } finally {
                f10155c.unlock();
            }
        }
        return true;
    }

    public static <IN, OUT> OUT startActivityForResult(Context context, Intent intent, IN in, int i) {
        ActivityStarter activityStarter = new ActivityStarter(in);
        Logger.m15895d(f10153a, activityStarter.toString() + ".startActivityForResult(in-data:" + in + ", timeout:" + i + ")");
        if (i != 0) {
            if (f10159g == null) {
                synchronized (f10154b) {
                    if (f10159g == null) {
                        Logger.m15895d(f10153a, activityStarter.toString() + ".create the handler");
                        if (context == null) {
                            throw new IllegalArgumentException(activityStarter.toString() + " - null-context");
                        }
                        activityStarter.f10163k = i;
                        f10159g = new Handler(context.getMainLooper());
                    }
                }
            }
            activityStarter.m15902c();
        }
        intent.putExtra("LOCK", activityStarter.f10162j);
        context.startActivity(intent);
        OUT out = null;
        if (!m15905a(true, activityStarter)) {
            Logger.m15895d(f10153a, "startActivity returning null.");
            activityStarter.m15900d();
            return null;
        }
        Logger.m15895d(f10153a, activityStarter.toString() + ".waiting...");
        try {
            activityStarter.f10161i.acquire();
            out = (OUT) activityStarter.f10166n;
        } catch (InterruptedException e) {
            Logger.m15891e(f10153a, "Error while acquire the Semaphore", e);
        }
        Logger.m15895d(f10153a, activityStarter.toString() + ".done");
        if (i != 0) {
            activityStarter.m15900d();
        }
        Logger.m15895d(f10153a, activityStarter.toString() + ".startActivityForResult:" + out);
        synchronized (f10154b) {
            Logger.m15895d(f10153a, activityStarter.toString() + ".releaseId");
            if (activityStarter.f10162j != 0) {
                f10157e.remove(activityStarter.f10162j);
                activityStarter.f10162j = 0;
            }
        }
        return out;
    }

    public static <IN> IN getIncomingData(Intent intent) {
        ActivityStarter m15908a = m15908a(intent.getIntExtra("LOCK", 0));
        if (m15908a != null) {
            IN in = (IN) m15908a.f10165m;
            String str = f10153a;
            Logger.m15895d(str, m15908a.toString() + ".getIncomingData: " + in);
            return in;
        }
        return null;
    }

    public static void resetTimeout(Intent intent) {
        ActivityStarter m15908a = m15908a(intent.getIntExtra("LOCK", 0));
        if (m15908a != null) {
            String str = f10153a;
            Logger.m15895d(str, m15908a.toString() + ".resetTimeout");
            m15908a.m15900d();
            m15908a.m15902c();
        }
    }

    public static <OUT> void setResult(Intent intent, OUT out) {
        ActivityStarter m15908a = m15908a(intent.getIntExtra("LOCK", 0));
        if (m15908a != null) {
            String str = f10153a;
            Logger.m15895d(str, m15908a.toString() + ".setResult(" + out + ")");
            m15908a.m15900d();
            m15908a.f10166n = out;
            m15908a.f10161i.release();
        }
    }

    public static Boolean isInitialized(Intent intent) {
        ActivityStarter m15908a = m15908a(intent.getIntExtra("LOCK", 0));
        if (m15908a != null) {
            Boolean valueOf = Boolean.valueOf(m15908a.f10167o);
            String str = f10153a;
            Logger.m15895d(str, m15908a.toString() + ".mInitialized: " + valueOf);
            return valueOf;
        }
        return null;
    }

    public static boolean setInitialized(Intent intent) {
        ActivityStarter m15908a = m15908a(intent.getIntExtra("LOCK", 0));
        if (m15908a != null) {
            m15908a.f10167o = true;
            return true;
        }
        return false;
    }
}
