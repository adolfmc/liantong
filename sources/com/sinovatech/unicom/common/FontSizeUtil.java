package com.sinovatech.unicom.common;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.p318ui.App;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FontSizeUtil {
    public static boolean isBigFont() {
        if (TextUtils.equals("1", App.getSharePreferenceUtil().getString("config_big_font"))) {
            return TextUtils.equals("config_font_big", App.getSharePreferenceUtil().getString(ConfigManager.config_font_Size));
        }
        return false;
    }
}
