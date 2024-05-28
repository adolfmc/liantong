package com.sinovatech.unicom.basic.webview;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerCdnCacheTime {
    private static String getCdnCacheTime() {
        String str = "";
        try {
            int h5CdnCacheTime = ConfigManager.getH5CdnCacheTime();
            if (h5CdnCacheTime <= 0) {
                h5CdnCacheTime = 10;
            }
            str = "cdncachetime=" + ((int) ((System.currentTimeMillis() / 1000) / (h5CdnCacheTime * 60)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TextUtils.isEmpty(str) ? "cdncachetime=0" : str;
    }

    public static String replaceCdnCacheTime(String str) {
        return (TextUtils.isEmpty(str) || !str.contains("cdncachetime=cdncachetime")) ? str : str.replace("cdncachetime=cdncachetime", getCdnCacheTime());
    }

    public static boolean checkCdnCacheTime(String str) {
        return !TextUtils.isEmpty(str) && str.contains("cdncachetime=cdncachetime");
    }
}
