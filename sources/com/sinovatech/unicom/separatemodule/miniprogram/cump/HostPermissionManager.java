package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HostPermissionManager {
    private static HostPermissionManager instance;
    private Context context;

    public static synchronized HostPermissionManager getInstance(Context context) {
        HostPermissionManager hostPermissionManager;
        synchronized (HostPermissionManager.class) {
            if (instance == null) {
                synchronized (HostPermissionManager.class) {
                    if (instance == null) {
                        instance = new HostPermissionManager(context);
                    }
                }
            }
            hostPermissionManager = instance;
        }
        return hostPermissionManager;
    }

    public HostPermissionManager(Context context) {
        this.context = context;
    }

    public boolean checkHostPermission(String str, String str2) {
        boolean z = true;
        try {
            if (!TextUtils.isEmpty(str) && str.contains("edop_unicom") && !str.equals("edop_unicom_debug") && !str2.startsWith("https://native/") && !str2.startsWith("http://native/") && !str2.startsWith("https://edop_unicom") && !str2.startsWith("http://edop_unicom") && !str2.startsWith("LOCAL") && !str2.startsWith("Native") && !str2.startsWith("file:///")) {
                if (URLUtil.isValidUrl(str2)) {
                    Uri parse = Uri.parse(str2);
                    if (parse.getHost().endsWith(".10010.com") || parse.getHost().endsWith(".10010.cn") || parse.getPath().contains("edop/lowcode/page/content")) {
                        return true;
                    }
                }
                CumpEntity appInfoFromBox = CumpLanucher.getInstance(this.context).getAppInfoFromBox(str);
                if (appInfoFromBox != null) {
                    if (appInfoFromBox.isInnerMiniP()) {
                        return true;
                    }
                    try {
                        String[] split = appInfoFromBox.getRealmUrlList().split(",");
                        if (split.length > 0) {
                            for (String str3 : split) {
                                if (str2.startsWith(str3)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    } catch (Exception e) {
                        e = e;
                        z = false;
                        e.printStackTrace();
                        return z;
                    }
                }
                return true;
            }
            return true;
        } catch (Exception e2) {
            e = e2;
        }
    }
}
