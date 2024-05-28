package com.baidu.mapsdkplatform.comapi.commonutils;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.text.TextUtils;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapsdkplatform.comapi.util.SyncSysInfo;
import com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver;
import com.baidu.platform.comapi.util.C3093e;
import com.baidu.platform.comjni.map.commonmemcache.NACommonMemCache;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SysUpdateUtil implements SysUpdateObserver {

    /* renamed from: a */
    private static NACommonMemCache f7135a = null;

    /* renamed from: b */
    private static boolean f7136b = false;

    /* renamed from: c */
    private static String f7137c = "";

    /* renamed from: d */
    private static int f7138d;

    public SysUpdateUtil() {
        f7135a = C3093e.m17685b();
    }

    @Override // com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver
    public void init(String str) {
        if (f7135a != null) {
            if (TextUtils.isEmpty(str)) {
                str = SyncSysInfo.getPhoneInfoCache();
            }
            f7135a.m17645a(str);
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver
    public void updateNetworkInfo(Context context) {
        NetworkUtil.updateNetworkProxy(context);
    }

    @Override // com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver
    public void updateNetworkProxy(Context context) {
        String str;
        NetworkInfo activeNetworkInfo = NetworkUtil.getActiveNetworkInfo(context);
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return;
        }
        String lowerCase = activeNetworkInfo.getTypeName().toLowerCase();
        if (lowerCase.equals("wifi") && activeNetworkInfo.isConnected()) {
            f7136b = false;
        } else if (lowerCase.equals("mobile") || (lowerCase.equals("wifi") && !NetworkUtil.isWifiConnected(activeNetworkInfo))) {
            String extraInfo = activeNetworkInfo.getExtraInfo();
            f7136b = false;
            if (extraInfo != null) {
                String lowerCase2 = extraInfo.toLowerCase();
                if (lowerCase2.startsWith("cmwap") || lowerCase2.startsWith("uniwap") || lowerCase2.startsWith("3gwap")) {
                    str = "10.0.0.172";
                    f7137c = str;
                    f7138d = 80;
                    f7136b = true;
                } else if (!lowerCase2.startsWith("ctwap")) {
                    if (lowerCase2.startsWith("cmnet") || lowerCase2.startsWith("uninet") || lowerCase2.startsWith("ctnet") || lowerCase2.startsWith("3gnet")) {
                        f7136b = false;
                        return;
                    }
                    return;
                }
            } else {
                String defaultHost = Proxy.getDefaultHost();
                int defaultPort = Proxy.getDefaultPort();
                if (defaultHost == null || defaultHost.length() <= 0) {
                    return;
                }
                if ("10.0.0.172".equals(defaultHost.trim())) {
                    f7137c = "10.0.0.172";
                    f7138d = defaultPort;
                    f7136b = true;
                } else if (!"10.0.0.200".equals(defaultHost.trim())) {
                    return;
                }
            }
            str = "10.0.0.200";
            f7137c = str;
            f7138d = 80;
            f7136b = true;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver
    public void updatePhoneInfo(String str) {
        NACommonMemCache nACommonMemCache = f7135a;
        if (nACommonMemCache != null) {
            nACommonMemCache.m17645a(str);
        }
    }
}
