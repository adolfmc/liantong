package com.baidu.location.p139d;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.location.LLSInterface;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p136a.C2621a;
import com.baidu.location.p137b.C2628b;
import com.baidu.location.p137b.C2631c;
import com.baidu.location.p137b.C2650l;
import com.baidu.location.p137b.C2666p;
import com.baidu.location.p137b.C2677v;
import com.baidu.location.p137b.C2678w;
import com.baidu.location.p137b.C2684y;
import com.baidu.location.p137b.C2686z;
import com.baidu.location.p138c.C2689b;
import com.baidu.location.p138c.C2697f;
import com.baidu.location.p138c.C2711l;
import com.baidu.location.p140e.C2721b;
import com.baidu.location.p140e.C2735k;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.d.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ServiceC2716a extends Service implements LLSInterface {

    /* renamed from: a */
    static HandlerC2717a f5657a;

    /* renamed from: c */
    public static long f5658c;

    /* renamed from: g */
    private static long f5659g;

    /* renamed from: b */
    Messenger f5660b = null;

    /* renamed from: d */
    private Looper f5661d = null;

    /* renamed from: e */
    private HandlerThread f5662e = null;

    /* renamed from: f */
    private boolean f5663f = true;

    /* renamed from: h */
    private int f5664h = 0;

    /* renamed from: i */
    private boolean f5665i = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.d.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class HandlerC2717a extends Handler {

        /* renamed from: a */
        private final WeakReference<ServiceC2716a> f5666a;

        public HandlerC2717a(Looper looper, ServiceC2716a serviceC2716a) {
            super(looper);
            this.f5666a = new WeakReference<>(serviceC2716a);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ServiceC2716a serviceC2716a = this.f5666a.get();
            if (serviceC2716a == null) {
                return;
            }
            if (ServiceC2737f.isServing) {
                switch (message.what) {
                    case 11:
                        serviceC2716a.m19107a(message);
                        break;
                    case 12:
                        serviceC2716a.m19102b(message);
                        break;
                    case 15:
                        serviceC2716a.m19099c(message);
                        break;
                    case 22:
                        C2666p.m19375c().m19381b(message);
                        break;
                    case 41:
                        C2666p.m19375c().m19357i();
                        break;
                    case 401:
                        try {
                            message.getData();
                            break;
                        } catch (Exception unused) {
                            break;
                        }
                    case 406:
                        C2650l.m19439a().m19427e();
                        break;
                    case 705:
                        C2628b.m19560a().m19552a(message.getData().getBoolean("foreground"));
                        break;
                }
            }
            if (message.what == 1) {
                serviceC2716a.m19103b();
            }
            if (message.what == 0) {
                serviceC2716a.m19108a();
            }
            super.handleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19108a() {
        C2621a.m19575a().m19574a(ServiceC2737f.getServiceContext());
        C2721b.m19096a();
        C2631c.m19525a().m19523a(ServiceC2737f.getServiceContext());
        try {
            C2686z.m19278a().m19271e();
        } catch (Exception unused) {
        }
        C2650l.m19439a().m19432b();
        C2697f.m19228a().m19205b();
        C2689b.m19259a().m19247b();
        C2666p.m19375c().m19370d();
        C2711l.m19133a().m19124c();
        this.f5664h = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19107a(Message message) {
        C2628b.m19560a().m19558a(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19103b() {
        C2697f.m19228a().m19182e();
        C2711l.m19133a().m19122d();
        C2686z.m19278a().m19270f();
        C2689b.m19259a().m19243c();
        C2666p.m19375c().m19366e();
        C2650l.m19439a().m19430c();
        if (this.f5665i) {
            C2684y.m19284d();
        }
        C2628b.m19560a().m19551b();
        try {
            C2678w.m19310a().m19307d();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f5664h = 4;
        Log.d("baidu_location_service", "baidu location service has stoped ...");
        if (this.f5663f) {
            return;
        }
        Process.killProcess(Process.myPid());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19102b(Message message) {
        C2628b.m19560a().m19550b(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m19099c(Message message) {
        C2628b.m19560a().m19547c(message);
    }

    @Override // com.baidu.location.LLSInterface
    public double getVersion() {
        return 9.333000183105469d;
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            C2721b.f5693h = extras.getString("key");
            C2721b.f5692g = extras.getString("sign");
            this.f5663f = extras.getBoolean("kill_process");
            extras.getBoolean("cache_exception");
        }
        return this.f5660b.getBinder();
    }

    @Override // com.baidu.location.LLSInterface
    public void onCreate(Context context) {
        LBSAuthManager.getInstance(ServiceC2737f.getServiceContext()).setPrivacyMode(true);
        try {
            C2735k.f5806aw = context.getPackageName();
        } catch (Exception unused) {
        }
        f5659g = System.currentTimeMillis();
        this.f5662e = C2677v.m19311a();
        HandlerThread handlerThread = this.f5662e;
        if (handlerThread != null) {
            this.f5661d = handlerThread.getLooper();
        }
        Looper looper = this.f5661d;
        if (looper == null) {
            f5657a = new HandlerC2717a(Looper.getMainLooper(), this);
        } else {
            f5657a = new HandlerC2717a(looper, this);
        }
        f5658c = System.currentTimeMillis();
        this.f5660b = new Messenger(f5657a);
        f5657a.sendEmptyMessage(0);
        this.f5664h = 1;
        Log.d("baidu_location_service", "baidu location service start 0509 version ...20220509_1728..." + Process.myPid());
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public void onDestroy() {
        try {
            f5657a.sendEmptyMessage(1);
        } catch (Exception unused) {
            Log.d("baidu_location_service", "baidu location service stop exception...");
            this.f5665i = false;
            m19103b();
            Process.killProcess(Process.myPid());
        }
        this.f5664h = 3;
        new Handler(Looper.getMainLooper()).postDelayed(new RunnableC2718b(this, new WeakReference(this)), 1000L);
        Log.d("baidu_location_service", "baidu location service stop ...");
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public void onTaskRemoved(Intent intent) {
        Log.d("baidu_location_service", "baidu location service remove task...");
    }

    @Override // com.baidu.location.LLSInterface
    public boolean onUnBind(Intent intent) {
        return false;
    }
}
