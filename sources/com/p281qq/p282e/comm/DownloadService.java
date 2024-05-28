package com.p281qq.p282e.comm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.text.TextUtils;
import com.p281qq.p282e.comm.managers.C6873b;
import com.p281qq.p282e.comm.p283pi.SVSD;
import com.p281qq.p282e.comm.util.GDTLogger;

/* renamed from: com.qq.e.comm.DownloadService */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DownloadService extends Service {

    /* renamed from: a */
    private SVSD f17890a;

    /* renamed from: a */
    private boolean m8289a() {
        if (this.f17890a == null) {
            try {
                if (C6873b.m8276b().m8274d()) {
                    SVSD aPKDownloadServiceDelegate = C6873b.m8276b().m8275c().getPOFactory().getAPKDownloadServiceDelegate(this);
                    this.f17890a = aPKDownloadServiceDelegate;
                    aPKDownloadServiceDelegate.onCreate();
                }
            } catch (Throwable th) {
                GDTLogger.m8233e("初始化Service发生异常", th);
            }
        }
        return this.f17890a != null;
    }

    public static void enterAPPDownloadListPage(Context context) {
        if (context == null) {
            GDTLogger.m8234e("enterAPPDownloadListPage 调用异常，context为空");
            return;
        }
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra("GDT_APPID", C6873b.m8276b().m8280a());
        intent.setAction("com.qq.e.comm.ACTION_DOWNLOAD_LIST");
        context.startService(intent);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        GDTLogger.m8235d("DownloadService.onBind");
        SVSD svsd = this.f17890a;
        if (svsd != null) {
            return svsd.onBind(intent);
        }
        String stringExtra = intent.getStringExtra("GDT_APPID");
        GDTLogger.m8235d("DownloadService.onBind,appID=" + stringExtra);
        if (TextUtils.isEmpty(stringExtra) || !m8289a()) {
            return null;
        }
        return this.f17890a.onBind(intent);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        SVSD svsd = this.f17890a;
        if (svsd != null) {
            svsd.onConfigurationChanged(configuration);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        SVSD svsd = this.f17890a;
        if (svsd != null) {
            svsd.onDestroy();
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        SVSD svsd = this.f17890a;
        if (svsd != null) {
            svsd.onLowMemory();
        }
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        SVSD svsd = this.f17890a;
        if (svsd != null) {
            svsd.onRebind(intent);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            stopSelf(i2);
            return 2;
        } else if (TextUtils.isEmpty(intent.getStringExtra("GDT_APPID")) || !m8289a()) {
            GDTLogger.m8231w("Service onStartCommand 出现异常");
            return 2;
        } else {
            return this.f17890a.onStartCommand(intent, i, i2);
        }
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        SVSD svsd = this.f17890a;
        if (svsd != null) {
            svsd.onTaskRemoved(intent);
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        SVSD svsd = this.f17890a;
        if (svsd != null) {
            svsd.onTrimMemory(i);
        }
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        SVSD svsd = this.f17890a;
        return svsd != null ? svsd.onUnbind(intent) : super.onUnbind(intent);
    }
}
