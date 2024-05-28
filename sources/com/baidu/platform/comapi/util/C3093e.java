package com.baidu.platform.comapi.util;

import android.os.Build;
import com.baidu.mapsdkplatform.comapi.util.SyncSysInfo;
import com.baidu.p166vi.VIContext;
import com.baidu.platform.comjni.map.commonmemcache.NACommonMemCache;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3093e {

    /* renamed from: a */
    private static NACommonMemCache f8066a = new NACommonMemCache();

    /* renamed from: a */
    public static void m17686a() {
        m17684c();
    }

    /* renamed from: b */
    public static NACommonMemCache m17685b() {
        return f8066a;
    }

    /* renamed from: c */
    private static void m17684c() {
        JsonBuilder jsonBuilder = new JsonBuilder();
        String str = Build.MODEL;
        f8066a.m17645a(SyncSysInfo.initPhoneInfo());
        jsonBuilder.object();
        jsonBuilder.putStringValue("pd", "map");
        jsonBuilder.putStringValue("ov", "Android" + Build.VERSION.SDK_INT);
        jsonBuilder.putStringValue("ver", "2");
        jsonBuilder.key("sw").value(SysOSUtil.getInstance().getScreenWidth());
        jsonBuilder.key("sh").value(SysOSUtil.getInstance().getScreenHeight());
        jsonBuilder.putStringValue("channel", "oem");
        jsonBuilder.putStringValue("mb", str);
        jsonBuilder.putStringValue("sv", SyncSysInfo.getSoftWareVer());
        jsonBuilder.putStringValue("os", "android");
        jsonBuilder.putStringValue("cuid", SyncSysInfo.getCid());
        jsonBuilder.putStringValue("path", SysOSUtil.getInstance().getOutputDirPath() + "/udc/");
        jsonBuilder.endObject();
        f8066a.m17644a("logstatistics", jsonBuilder.getJson());
        jsonBuilder.reset();
        jsonBuilder.object();
        jsonBuilder.putStringValue("cuid", SyncSysInfo.getCid());
        jsonBuilder.putStringValue("app", "1");
        jsonBuilder.putStringValue("path", VIContext.getContext().getCacheDir().getAbsolutePath() + "/");
        jsonBuilder.putStringValue("domain", "");
        jsonBuilder.endObject();
        f8066a.m17644a("longlink", jsonBuilder.getJson());
    }
}
