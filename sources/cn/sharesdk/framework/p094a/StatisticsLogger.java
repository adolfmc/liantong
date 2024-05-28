package cn.sharesdk.framework.p094a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.SdkPlusTags;
import cn.sharesdk.framework.p094a.p095a.SharePrefrenceUtil;
import cn.sharesdk.framework.p094a.p096b.BaseEvent;
import cn.sharesdk.framework.p094a.p096b.ExitEvent;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKHandlerThread;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.commons.SHARESDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import java.io.File;
import java.util.Calendar;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StatisticsLogger extends SSDKHandlerThread {

    /* renamed from: b */
    private static StatisticsLogger f2831b;

    /* renamed from: e */
    private Handler f2834e;

    /* renamed from: f */
    private boolean f2835f;

    /* renamed from: g */
    private long f2836g;

    /* renamed from: c */
    private DeviceHelper f2832c = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: d */
    private EventManager f2833d = EventManager.m21991a();

    /* renamed from: i */
    private FileLocker f2838i = new FileLocker();

    /* renamed from: h */
    private File f2837h = new File(MobSDK.getContext().getFilesDir(), ".statistics");

    /* renamed from: a */
    public static synchronized StatisticsLogger m21893a() {
        StatisticsLogger statisticsLogger;
        synchronized (StatisticsLogger.class) {
            if (f2831b == null) {
                f2831b = new StatisticsLogger();
            }
            statisticsLogger = f2831b;
        }
        return statisticsLogger;
    }

    private StatisticsLogger() {
        if (this.f2837h.exists()) {
            return;
        }
        try {
            this.f2837h.createNewFile();
        } catch (Exception e) {
            SSDKLog.m21740b().m21742a(e);
        }
    }

    /* renamed from: a */
    public void m21892a(Handler handler) {
        this.f2834e = handler;
    }

    @Override // cn.sharesdk.framework.utils.SSDKHandlerThread
    /* renamed from: a */
    public void mo21690a(Message message) {
        if (this.f2835f) {
            return;
        }
        this.f2835f = true;
        try {
            this.f2838i.setLockFile(this.f2837h.getAbsolutePath());
            if (this.f2838i.lock(false)) {
                new Thread(new Runnable() { // from class: cn.sharesdk.framework.a.d.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            StatisticsLogger.this.f2833d.m21985a(DeviceAuthorizer.authorize(new SHARESDK()));
                        } catch (Exception e) {
                            SSDKLog.m21740b().m21742a(e);
                        }
                    }
                }).start();
                this.f2833d.m21979b();
                this.f2833d.m21977c();
                ShareSDK.setEnableAuthTag(true);
                m21889b();
                this.f2969a.sendEmptyMessageDelayed(4, 3600000L);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }

    @Override // cn.sharesdk.framework.utils.SSDKHandlerThread
    /* renamed from: c */
    public void mo21688c(Message message) {
        if (this.f2835f) {
            ExitEvent exitEvent = new ExitEvent();
            exitEvent.f2806a = System.currentTimeMillis() - this.f2836g;
            m21891a(exitEvent);
            this.f2835f = false;
            try {
                this.f2834e.sendEmptyMessage(1);
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
            f2831b = null;
            this.f2969a.getLooper().quit();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [cn.sharesdk.framework.a.d$2] */
    /* renamed from: a */
    public void m21891a(final BaseEvent baseEvent) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread() { // from class: cn.sharesdk.framework.a.d.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    StatisticsLogger.this.m21888b(baseEvent);
                }
            }.start();
        } else {
            m21888b(baseEvent);
        }
    }

    /* renamed from: b */
    public void m21888b(BaseEvent baseEvent) {
        try {
            if (MobSDK.isMob()) {
                if (this.f2835f) {
                    m21887c(baseEvent);
                    if (baseEvent.mo21929g()) {
                        Message message = new Message();
                        message.what = 3;
                        message.obj = baseEvent;
                        this.f2969a.sendMessage(message);
                    } else {
                        SSDKLog m21740b = SSDKLog.m21740b();
                        m21740b.m21744a("Drop event: " + baseEvent.toString(), new Object[0]);
                    }
                }
            }
        } catch (Throwable th) {
            SSDKLog m21740b2 = SSDKLog.m21740b();
            m21740b2.m21744a("logStart " + th, new Object[0]);
        }
    }

    /* renamed from: c */
    private void m21887c(BaseEvent baseEvent) {
        baseEvent.f2792f = DeviceAuthorizer.authorize(new SHARESDK());
        baseEvent.f2793g = this.f2832c.getPackageName();
        baseEvent.f2794h = this.f2832c.getAppVersion();
        baseEvent.f2795i = String.valueOf(ShareSDK.SDK_VERSION_CODE);
        baseEvent.f2796j = this.f2832c.getPlatformCode();
        baseEvent.f2797k = this.f2832c.getDetailNetworkTypeForStatic();
        if (TextUtils.isEmpty(MobSDK.getAppkey())) {
            SSDKLog.m21740b().m21739b("ShareSDKCore", "Your appKey of ShareSDK is null , this will cause its data won't be count!");
        } else if (!"cn.sharesdk.demo".equals(baseEvent.f2793g) && ("api20".equals(MobSDK.getAppkey()) || "androidv1101".equals(MobSDK.getAppkey()))) {
            SSDKLog.m21740b().m21739b("ShareSDKCore", "Your app is using the appkey of ShareSDK Demo, this will cause its data won't be count!");
        }
        baseEvent.f2798l = AppUtils.m21714c();
    }

    @Override // cn.sharesdk.framework.utils.SSDKHandlerThread
    /* renamed from: b */
    public void mo21689b(Message message) {
        switch (message.what) {
            case 1:
            default:
                return;
            case 2:
                try {
                    this.f2833d.m21975d();
                    return;
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21742a(th);
                    return;
                }
            case 3:
                if (message.obj != null) {
                    m21886d((BaseEvent) message.obj);
                    this.f2969a.removeMessages(2);
                    this.f2969a.sendEmptyMessageDelayed(2, 2000L);
                    return;
                }
                return;
            case 4:
                long longValue = SharePrefrenceUtil.m21961a().m21936i().longValue();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(longValue);
                int i = calendar.get(1);
                int i2 = calendar.get(2);
                int i3 = calendar.get(5);
                calendar.setTimeInMillis(System.currentTimeMillis());
                int i4 = calendar.get(1);
                int i5 = calendar.get(2);
                int i6 = calendar.get(5);
                if (i != i4 || i2 != i5 || i3 != i6) {
                    this.f2833d.m21977c();
                }
                this.f2969a.sendEmptyMessageDelayed(4, 3600000L);
                return;
        }
    }

    /* renamed from: d */
    private void m21886d(BaseEvent baseEvent) {
        try {
            this.f2833d.m21987a(baseEvent);
            baseEvent.mo21928h();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            SSDKLog.m21740b().m21744a(baseEvent.toString(), new Object[0]);
        }
    }

    /* renamed from: b */
    private void m21889b() {
        new Thread(new Runnable() { // from class: cn.sharesdk.framework.a.d.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SdkPlusTags.m21865c().m21864d();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }).start();
    }
}
