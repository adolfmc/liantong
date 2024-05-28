package com.baidu.mapsdkplatform.comapi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.baidu.mapsdkplatform.comapi.util.MapSDKAdvancedPermission;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.mapsdkplatform.comapi.util.SysOSAPI;
import com.baidu.mapsdkplatform.comapi.util.SysUpdateObservable;
import com.baidu.mapsdkplatform.comjni.tools.AppTools;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BMapManagerInternal implements PermissionCheck.InterfaceC2954c {

    /* renamed from: a */
    private static final String f7063a = "a";

    /* renamed from: f */
    private static BMapManagerInternal f7064f = null;

    /* renamed from: g */
    private static int f7065g = -100;

    /* renamed from: b */
    private Context f7066b;

    /* renamed from: c */
    private Handler f7067c;

    /* renamed from: d */
    private NetworkListener f7068d;

    /* renamed from: e */
    private int f7069e;

    private BMapManagerInternal() {
    }

    /* renamed from: a */
    public static BMapManagerInternal m18539a() {
        if (f7064f == null) {
            f7064f = new BMapManagerInternal();
        }
        return f7064f;
    }

    /* renamed from: a */
    public void m18538a(Context context) {
        this.f7066b = context;
    }

    /* renamed from: b */
    public void m18535b() {
        if (this.f7069e == 0) {
            if (this.f7066b == null) {
                this.f7066b = JNIInitializer.getCachedContext();
                if (this.f7066b == null) {
                    Log.e("BDMapSDK", "BDMapSDKException: you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
                    return;
                }
            }
            this.f7068d = new NetworkListener();
            m18531f();
            SysUpdateObservable.getInstance().updateNetworkInfo(this.f7066b);
        }
        this.f7069e++;
    }

    /* renamed from: c */
    public boolean m18534c() {
        if (this.f7066b == null) {
            this.f7066b = JNIInitializer.getCachedContext();
            if (this.f7066b == null) {
                Log.e("BDMapSDK", "BDMapSDKException: you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
                return false;
            }
        }
        SysOSAPI.m18138b(this.f7066b);
        if (!Initializer.m18475b()) {
            PermissionCheck.setPrivacyMode(false);
        } else {
            PermissionCheck.setPrivacyMode(true);
        }
        this.f7067c = new HandlerC2883b(this);
        SysOSAPI.m18136c(this.f7066b);
        MapSDKAdvancedPermission.m18161a().m18159a(this.f7066b);
        SysOSAPI.m18129h();
        PermissionCheck.init(this.f7066b);
        PermissionCheck.setPermissionCheckResultListener(this);
        PermissionCheck.permissionCheck();
        if (Initializer.m18475b()) {
            return true;
        }
        throw new BaiduMapSDKException("not agree privacyMode, please invoke SDKInitializer.setAgreePrivacy(Context, boolean) function");
    }

    /* renamed from: d */
    public void m18533d() {
        this.f7069e--;
        if (this.f7069e == 0) {
            m18530g();
            SysOSAPI.m18139b();
        }
    }

    /* renamed from: e */
    public Context m18532e() {
        if (this.f7066b == null) {
            this.f7066b = JNIInitializer.getCachedContext();
        }
        return this.f7066b;
    }

    /* renamed from: f */
    private void m18531f() {
        NetworkListener networkListener;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        Context context = this.f7066b;
        if (context == null || (networkListener = this.f7068d) == null) {
            return;
        }
        context.registerReceiver(networkListener, intentFilter);
    }

    /* renamed from: g */
    private void m18530g() {
        Context context;
        NetworkListener networkListener = this.f7068d;
        if (networkListener == null || (context = this.f7066b) == null) {
            return;
        }
        context.unregisterReceiver(networkListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18537a(Message message) {
        Intent intent;
        if (this.f7066b == null) {
            this.f7066b = JNIInitializer.getCachedContext();
            if (this.f7066b == null) {
                return;
            }
        }
        if (message.what == 2012) {
            if (message.arg1 == 0) {
                intent = new Intent("permission check ok");
            } else {
                Intent intent2 = new Intent("permission check error");
                intent2.putExtra("error_code", message.arg1);
                intent2.putExtra("error_message", (String) message.obj);
                intent = intent2;
            }
            this.f7066b.sendBroadcast(intent);
            return;
        }
        if (message.arg2 == 3) {
            this.f7066b.sendBroadcast(new Intent("network error"));
        }
        if (message.arg2 == 2 || message.arg2 == 404 || message.arg2 == 5 || message.arg2 == 8) {
            this.f7066b.sendBroadcast(new Intent("network error"));
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.util.PermissionCheck.InterfaceC2954c
    /* renamed from: a */
    public void mo18177a(PermissionCheck.C2953b c2953b) {
        if (c2953b == null) {
            return;
        }
        if (c2953b.f7411a == 0) {
            SysOSAPI.f7443c = c2953b.f7415e;
            SysOSAPI.m18140a(c2953b.f7412b, c2953b.f7413c);
        } else {
            Log.e("baidumapsdk", "Authentication Error\n" + c2953b.toString());
        }
        if (c2953b.f7411a != PermissionCheck.f7400b && c2953b.f7411a != PermissionCheck.f7399a && c2953b.f7411a != PermissionCheck.f7401c) {
            MapSDKAdvancedPermission.m18161a().m18160a(c2953b.f7416f);
        }
        if (this.f7067c == null || c2953b.f7411a == f7065g) {
            return;
        }
        f7065g = c2953b.f7411a;
        Message obtainMessage = this.f7067c.obtainMessage();
        obtainMessage.what = 2012;
        obtainMessage.arg1 = c2953b.f7411a;
        obtainMessage.obj = c2953b.f7414d;
        this.f7067c.sendMessage(obtainMessage);
    }

    static {
        NativeLoader.getInstance().loadLibrary("gnustl_shared");
        NativeLoader.getInstance().loadLibrary(VersionInfo.getKitName());
        AppTools.m18113b();
    }
}
