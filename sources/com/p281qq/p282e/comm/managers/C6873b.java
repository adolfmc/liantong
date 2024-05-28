package com.p281qq.p282e.comm.managers;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.p281qq.p282e.ads.dfa.GDTAppDialogClickListener;
import com.p281qq.p282e.comm.managers.devtool.DevTools;
import com.p281qq.p282e.comm.managers.plugin.C6875PM;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.qq.e.comm.managers.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6873b implements IGDTAdManager {

    /* renamed from: g */
    public static final ExecutorService f17916g = Executors.newSingleThreadExecutor();

    /* renamed from: a */
    private volatile Boolean f17917a;

    /* renamed from: b */
    private volatile boolean f17918b;

    /* renamed from: c */
    private volatile Context f17919c;

    /* renamed from: d */
    private volatile C6875PM f17920d;

    /* renamed from: e */
    private volatile DevTools f17921e;

    /* renamed from: f */
    private volatile String f17922f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.comm.managers.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C6874a {

        /* renamed from: a */
        private static C6873b f17923a = new C6873b(null);
    }

    private C6873b() {
        this.f17917a = Boolean.FALSE;
        this.f17918b = false;
    }

    /* synthetic */ C6873b(RunnableC6872a runnableC6872a) {
        this();
    }

    /* renamed from: b */
    public static C6873b m8276b() {
        return C6874a.f17923a;
    }

    /* renamed from: a */
    public String m8280a() {
        return this.f17922f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized boolean m8279a(Context context, String str) {
        if (Build.VERSION.SDK_INT < 14) {
            GDTLogger.m8234e("GDTADManager初始化错误，SDK不支持Android 4.0以下版本");
            return false;
        } else if (this.f17917a.booleanValue()) {
            return true;
        } else {
            if (context == null || TextUtils.isEmpty(str)) {
                GDTLogger.m8234e("GDTADManager初始化错误，context和appId不能为空");
                return false;
            }
            this.f17922f = str;
            this.f17919c = context.getApplicationContext();
            this.f17920d = new C6875PM(this.f17919c, null);
            f17916g.submit(new RunnableC6872a(this));
            this.f17917a = Boolean.TRUE;
            return true;
        }
    }

    /* renamed from: c */
    public C6875PM m8275c() {
        return this.f17920d;
    }

    /* renamed from: d */
    public boolean m8274d() {
        if (this.f17917a == null || !this.f17917a.booleanValue()) {
            GDTLogger.m8234e("SDK 尚未初始化，请在 Application 中调用 GDTAdSdk.init() 初始化");
            return false;
        }
        return true;
    }

    @Override // com.p281qq.p282e.comm.managers.IGDTAdManager
    public String getBuyerId(Map<String, Object> map) {
        if (m8274d()) {
            try {
                return this.f17920d.getPOFactory().getBuyerId(map);
            } catch (Exception e) {
                GDTLogger.m8233e("SDK 初始化异常", e);
                return "";
            }
        }
        return "";
    }

    @Override // com.p281qq.p282e.comm.managers.IGDTAdManager
    public DevTools getDevTools() {
        if (this.f17921e == null) {
            this.f17921e = new DevTools();
        }
        return this.f17921e;
    }

    @Override // com.p281qq.p282e.comm.managers.IGDTAdManager
    public String getSDKInfo(String str) {
        if (m8274d()) {
            try {
                return this.f17920d.getPOFactory().getSDKInfo(str);
            } catch (Exception e) {
                GDTLogger.m8233e("SDK 初始化异常", e);
                return "";
            }
        }
        return "";
    }

    @Override // com.p281qq.p282e.comm.managers.IGDTAdManager
    public int showOpenOrInstallAppDialog(GDTAppDialogClickListener gDTAppDialogClickListener) {
        if (this.f17918b) {
            try {
                return this.f17920d.getPOFactory().showOpenOrInstallAppDialog(gDTAppDialogClickListener);
            } catch (Exception e) {
                GDTLogger.m8233e("SDK 初始化异常", e);
                return 0;
            }
        }
        return 0;
    }
}
