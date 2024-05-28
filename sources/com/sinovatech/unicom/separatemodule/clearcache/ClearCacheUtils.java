package com.sinovatech.unicom.separatemodule.clearcache;

import android.text.TextUtils;
import com.blankj.utilcode.util.CleanUtils;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ClearCacheUtils {
    public static String PHONE_OS_ALL = "1";
    public static String PHONE_OS_ANDROID = "2";
    private static final String TAG = "ClearCacheUtils";

    public static void clearCache() {
        try {
            CleanUtils.cleanAppUserData();
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "清除缓存数据异常:" + e.getMessage());
        }
    }

    public static boolean checkClear() {
        String string;
        try {
            string = App.getSharePreferenceUtil().getString(ConfigManager.config_unicom_clear_cache_phone_system);
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "需要清除缓存的手机系统为=" + string);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "清除缓存异常:" + e.getMessage());
        }
        if (TextUtils.isEmpty(string)) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "需要清除的手机系统为空不弹清除数据弹窗");
            return false;
        } else if (!TextUtils.equals(PHONE_OS_ALL, string) && !TextUtils.equals(PHONE_OS_ANDROID, string)) {
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "需要清除手机的系统不是 all 并且不是 android");
            return false;
        } else {
            long j = App.getSharePreferenceUtil().getLong("unicom_app_crash_time");
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "上次闪退的时间戳为:" + j);
            if (j <= 0) {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "未发生闪退不弹清除数据弹窗");
                return false;
            }
            String string2 = App.getSharePreferenceUtil().getString(ConfigManager.config_unicom_clear_cache_time);
            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "获取配置的需要清除数据的时间段 = " + string2);
            if (!TextUtils.isEmpty(string2) && string2.contains(",")) {
                String[] split = string2.split(",");
                if (split.length >= 2) {
                    String str2 = split[0];
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "开始时间为:" + str2);
                    String str3 = split[1];
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "结束时间为:" + str3);
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                        long parseLong = Long.parseLong(str2);
                        long parseLong2 = Long.parseLong(str3);
                        if (j >= parseLong && j <= parseLong2) {
                            MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "闪退时间在配置的时间段内 弹出清除数据弹窗");
                            return true;
                        }
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "闪退的时间不在配置的时间段内 不弹出清除数据的弹窗");
                    } else {
                        MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "解析的开始时间和结束时间有一个为空不弹出弹窗");
                    }
                } else {
                    MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "截取出的时间段长度小于2不符合规则不弹出清除数据弹窗");
                }
            } else {
                MsLogUtil.m7979d("JIA_CRACSH_UPDATE", "配置的时间段不包含逗号不符合规则不弹出清除数据弹窗");
            }
            return false;
        }
    }
}
