package com.baidu.platform.comapi.util;

import com.baidu.p166vi.VIContext;
import com.baidu.platform.comapi.util.p156a.DpiInfo;
import com.baidu.platform.comapi.util.p156a.PathInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SysOSUtil {

    /* renamed from: g */
    private static SysOSUtil f8036g = new SysOSUtil();

    /* renamed from: a */
    private PathInfo f8037a = null;

    /* renamed from: b */
    private DpiInfo f8038b = null;

    /* renamed from: c */
    private boolean f8039c = false;

    /* renamed from: d */
    private String f8040d = "";

    /* renamed from: e */
    private String f8041e = "";

    /* renamed from: f */
    private String f8042f = "";

    private SysOSUtil() {
    }

    public void init(PathInfo pathInfo, DpiInfo dpiInfo) {
        if (this.f8039c) {
            return;
        }
        this.f8037a = pathInfo;
        this.f8038b = dpiInfo;
        if (this.f8037a == null) {
            this.f8037a = new PathInfo();
        }
        if (this.f8038b == null) {
            this.f8038b = new DpiInfo();
        }
        this.f8037a.m17693a(VIContext.getContext());
        this.f8038b.m17698a(VIContext.getContext());
        this.f8040d = NetworkUtil.getCurrentNetMode(VIContext.getContext());
        this.f8039c = true;
    }

    public static SysOSUtil getInstance() {
        return f8036g;
    }

    public int getDensityDPI() {
        DpiInfo dpiInfo = this.f8038b;
        if (dpiInfo != null) {
            return dpiInfo.m17695d();
        }
        return 1;
    }

    public float getDensity() {
        DpiInfo dpiInfo = this.f8038b;
        if (dpiInfo != null) {
            return dpiInfo.m17696c();
        }
        return 1.0f;
    }

    public int getScreenWidth() {
        DpiInfo dpiInfo = this.f8038b;
        if (dpiInfo != null) {
            return dpiInfo.m17699a();
        }
        return 0;
    }

    public int getScreenHeight() {
        DpiInfo dpiInfo = this.f8038b;
        if (dpiInfo != null) {
            return dpiInfo.m17697b();
        }
        return 0;
    }

    public String getOutputDirPath() {
        PathInfo pathInfo = this.f8037a;
        return pathInfo != null ? pathInfo.m17694a() : "";
    }

    public String getSdcardPath() {
        PathInfo pathInfo = this.f8037a;
        return pathInfo != null ? pathInfo.m17691b() : "";
    }

    public String getOutputCache() {
        PathInfo pathInfo = this.f8037a;
        return pathInfo != null ? pathInfo.m17689d() : "";
    }

    public String getCompatibleSdcardPath() {
        PathInfo pathInfo = this.f8037a;
        return pathInfo != null ? pathInfo.m17690c() : "";
    }

    public String getExternalFilesDir() {
        PathInfo pathInfo = this.f8037a;
        return pathInfo != null ? pathInfo.m17688e() : "";
    }

    public String getNetType() {
        return this.f8040d;
    }

    public void updateNetType(String str) {
        this.f8040d = str;
    }

    public void setGLInfo(String str, String str2) {
        if (this.f8042f.equals(str2) && this.f8041e.equals(str)) {
            return;
        }
        this.f8041e = str;
        this.f8042f = str2;
    }

    public String getGLVersion() {
        return this.f8041e;
    }

    public String getGLRenderer() {
        return this.f8042f;
    }
}
