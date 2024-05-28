package com.baidu.location;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import com.baidu.location.p139d.ServiceC2716a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ServiceC2737f extends Service {
    public static boolean isServing = false;
    public static boolean isStartedServing = false;

    /* renamed from: mC */
    public static Context f5835mC = null;
    public static String replaceFileName = "repll.jar";

    /* renamed from: a */
    LLSInterface f5836a = null;

    /* renamed from: b */
    LLSInterface f5837b = null;

    /* renamed from: c */
    LLSInterface f5838c = null;

    public static float getFrameVersion() {
        return 9.333f;
    }

    public static String getJarFileName() {
        return "app.jar";
    }

    public static Context getServiceContext() {
        return f5835mC;
    }

    public static void setServiceContext(Context context) {
        f5835mC = context;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LLSInterface lLSInterface = this.f5838c;
        if (lLSInterface != null) {
            return lLSInterface.onBind(intent);
        }
        return null;
    }

    @Override // android.app.Service
    @SuppressLint({"NewApi"})
    public void onCreate() {
        if (isServing) {
            Log.d("baidu_location_service", "baidu location service can not start again ...20190306..." + Process.myPid());
            return;
        }
        f5835mC = getApplicationContext();
        System.currentTimeMillis();
        this.f5837b = new ServiceC2716a();
        LLSInterface lLSInterface = this.f5836a;
        if (lLSInterface == null || lLSInterface.getVersion() < this.f5837b.getVersion()) {
            this.f5838c = this.f5837b;
            this.f5836a = null;
        } else {
            this.f5838c = this.f5836a;
            this.f5837b = null;
        }
        isServing = true;
        this.f5838c.onCreate(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        isServing = false;
        LLSInterface lLSInterface = this.f5838c;
        if (lLSInterface != null) {
            lLSInterface.onDestroy();
        }
        if (isStartedServing) {
            stopForeground(true);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                int intExtra = intent.getIntExtra("command", 0);
                if (intExtra == 1) {
                    startForeground(intent.getIntExtra("id", 0), (Notification) intent.getParcelableExtra("notification"));
                    isStartedServing = true;
                } else if (intExtra == 2) {
                    stopForeground(intent.getBooleanExtra("removenotify", true));
                    isStartedServing = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LLSInterface lLSInterface = this.f5838c;
        if (lLSInterface == null) {
            return 2;
        }
        return lLSInterface.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        LLSInterface lLSInterface = this.f5838c;
        if (lLSInterface != null) {
            lLSInterface.onTaskRemoved(intent);
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return false;
    }
}
