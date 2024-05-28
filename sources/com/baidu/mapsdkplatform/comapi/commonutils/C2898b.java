package com.baidu.mapsdkplatform.comapi.commonutils;

import android.text.TextUtils;
import com.baidu.mapapi.OpenLogUtil;
import com.baidu.platform.comapi.util.C3097i;
import com.baidu.platform.comapi.util.SysOSUtil;
import com.baidu.platform.comjni.engine.NAEngine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2898b {

    /* renamed from: a */
    private static boolean f7154a = true;

    /* renamed from: b */
    private static boolean f7155b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    enum EnumC2899a {
        eMonitorConsole(1),
        eMonitorNative(2),
        eMonitorNet(4);
        

        /* renamed from: d */
        private int f7160d;

        EnumC2899a(int i) {
            this.f7160d = 0;
            this.f7160d = i;
        }

        /* renamed from: a */
        public int m18453a() {
            return this.f7160d;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.b$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC2900b {
        eNone,
        eMonitorVerbose,
        eMonitorDebug,
        eMonitorInfo,
        eMonitorWarn,
        eMonitorError,
        eMonitorRealTime
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.b$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2901c {

        /* renamed from: a */
        private static final C2898b f7169a = new C2898b(null);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.b$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC2902d {
        SDK_MAP,
        Net,
        Engine
    }

    private C2898b() {
    }

    /* synthetic */ C2898b(RunnableC2903c runnableC2903c) {
        this();
    }

    /* renamed from: a */
    public static C2898b m18459a() {
        return C2901c.f7169a;
    }

    /* renamed from: a */
    private void m18458a(EnumC2900b enumC2900b, String str, String str2) {
        if (f7154a) {
            C3097i.m17680a().submit(new RunnableC2903c(this, enumC2900b, str, str2));
        }
    }

    /* renamed from: d */
    private void m18454d() {
        NAEngine.m17670a(new String[]{EnumC2902d.SDK_MAP.name(), EnumC2902d.Engine.name()});
    }

    /* renamed from: a */
    public void m18457a(String str) {
        m18458a(EnumC2900b.eMonitorRealTime, EnumC2902d.SDK_MAP.name(), str);
    }

    /* renamed from: b */
    public void m18456b() {
        f7154a = OpenLogUtil.isMapLogEnable();
        if (!f7154a || f7155b) {
            return;
        }
        String mapLogFilePath = OpenLogUtil.getMapLogFilePath();
        if (TextUtils.isEmpty(mapLogFilePath)) {
            mapLogFilePath = SysOSUtil.getInstance().getExternalFilesDir();
        }
        NAEngine.m17671a(false);
        NAEngine.m17672a(mapLogFilePath);
        NAEngine.m17675a(EnumC2899a.eMonitorNative.m18453a());
        NAEngine.m17668b(EnumC2900b.eMonitorError.ordinal());
        m18454d();
        NAEngine.m17671a(true);
        f7155b = true;
    }

    /* renamed from: c */
    public void m18455c() {
        if (f7154a && f7155b) {
            f7155b = false;
            f7154a = false;
            NAEngine.m17671a(false);
        }
    }
}
