package com.baidu.mapsdkplatform.comapi.map;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.baidu.mapsdkplatform.comapi.Initializer;
import com.baidu.mapsdkplatform.comapi.NativeLoader;
import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.mapsdkplatform.comapi.commonutils.SysUpdateUtil;
import com.baidu.mapsdkplatform.comapi.util.SysUpdateObservable;
import com.baidu.platform.comapi.C2981b;
import com.baidu.platform.comapi.longlink.BNLongLink;
import com.baidu.platform.comapi.util.C3093e;
import com.baidu.platform.comjni.engine.NAEngine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2934j {

    /* renamed from: a */
    private static int f7324a;

    /* renamed from: b */
    private static Context f7325b = BMapManager.getContext();

    static {
        if (!Initializer.m18479a()) {
            NativeLoader.getInstance().loadLibrary(com.baidu.mapapi.VersionInfo.getKitName());
        }
        if (!com.baidu.mapapi.VersionInfo.getApiVersion().equals(VersionInfo.getApiVersion())) {
            throw new BaiduMapSDKException("the version of map is not match with base");
        }
        NativeLoader.getInstance().loadLibrary(VersionInfo.getKitName());
    }

    /* renamed from: a */
    public static void m18235a() {
        if (f7324a == 0) {
            if (f7325b == null) {
                Log.e("BDMapSDK", "you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
                return;
            }
            C2898b.m18459a().m18456b();
            C2981b.m18072a((Application) f7325b, true, false, false, true);
            C2981b.m18070b();
            C2981b.m18071a(new C2911a());
            C3093e.m17686a();
            BNLongLink.initLongLink();
            NAEngine.m17667c();
            NAEngine.startRunningRequest();
            SysUpdateObservable.getInstance().addObserver(new SysUpdateUtil());
            SysUpdateObservable.getInstance().init("");
        }
        f7324a++;
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b.m18459a().m18457a("BasicMap init mRef = " + f7324a);
        }
    }

    /* renamed from: b */
    public static void m18234b() {
        f7324a--;
        if (OpenLogUtil.isMapLogEnable()) {
            C2898b.m18459a().m18457a("BasicMap destroy mRef = " + f7324a);
        }
        if (f7324a == 0) {
            C2898b.m18459a().m18455c();
            C2981b.m18069c();
        }
    }
}
