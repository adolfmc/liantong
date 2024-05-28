package com.sinovatech.unicom.common;

import android.app.Activity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.eventbus.CustomActivityEvent;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LaunchModeUtils {
    private static final String TAG = "LaunchModeUtils";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnChangeLaunchModeLanguageListener {
        void onFail(String str);

        void onSuccess(LanguageEntity languageEntity);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnChangeLaunchModeListener {
        void onFail(String str);

        void onSuccess();
    }

    public static void startCare(Activity activity) {
    }

    public static void startCustom(Activity activity) {
    }

    public static void startLanguage(Activity activity, LanguageEntity languageEntity) {
    }

    public static void startNormal(Activity activity) {
    }

    public static void changeAppNorml(Activity activity, OnChangeLaunchModeListener onChangeLaunchModeListener) {
        try {
            StatisticsUploadUtils.upload(activity, "7", "H5-设置", "", "", "原生首页-打开", "");
            EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
            App.getSharePreferenceUtil().putBoolean("CareHome", false);
            LanguageUtil.setLanguage(LanguageUtil.CHN_CN, "");
            if (onChangeLaunchModeListener != null) {
                onChangeLaunchModeListener.onSuccess();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, String.format("切换首页异常:%s", e.getMessage()));
            if (onChangeLaunchModeListener != null) {
                onChangeLaunchModeListener.onFail(String.format("切换首页异常:%s", e.getMessage()));
            }
        }
    }

    public static void changeCare(Activity activity, OnChangeLaunchModeListener onChangeLaunchModeListener) {
        try {
            if (!isShowGuanHuai()) {
                if (onChangeLaunchModeListener != null) {
                    onChangeLaunchModeListener.onFail("当前不支持切换至关怀模式!");
                    return;
                }
                return;
            }
            StatisticsUploadUtils.upload(activity, "7", "H5-设置", "", "", "关怀版切换按钮-打开", "");
            LanguageUtil.setLanguage(LanguageUtil.CHN_CN, "");
            App.getSharePreferenceUtil().putString("unicom_app_main_type", "1");
            App.getSharePreferenceUtil().putString("unicom_app_main_url", "ms_unicom_guanhuai");
            App.getSharePreferenceUtil().putBoolean("CareHome", true);
            if (onChangeLaunchModeListener != null) {
                onChangeLaunchModeListener.onSuccess();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, String.format("设置关怀版异常:%s", e.getMessage()));
            if (onChangeLaunchModeListener != null) {
                onChangeLaunchModeListener.onFail(String.format("设置关怀版异常:%s", e.getMessage()));
            }
        }
    }

    public static void changeLanguage(String str, OnChangeLaunchModeLanguageListener onChangeLaunchModeLanguageListener) {
        try {
            if (LanguageUtil.getInstance().isShowLanguage()) {
                LanguageEntity isExist = LanguageUtil.getInstance().isExist(str);
                if (isExist != null) {
                    StatisticsUploadUtils.upload("7", "H5-设置-多语言", "", "", isExist.getLanguageCode(), "");
                    App.getSharePreferenceUtil().putBoolean("CareHome", false);
                    if (onChangeLaunchModeLanguageListener != null) {
                        onChangeLaunchModeLanguageListener.onSuccess(isExist);
                    }
                } else if (onChangeLaunchModeLanguageListener != null) {
                    onChangeLaunchModeLanguageListener.onFail("当前多语言系统开关未开启，无法设置多语言! ");
                }
            } else if (onChangeLaunchModeLanguageListener != null) {
                onChangeLaunchModeLanguageListener.onFail("未查询到" + str + "多对应的多语言配置项数据，切换失败!");
            }
        } catch (Exception e) {
            MsLogUtil.m7978e(String.format("切换多语言异常:%s", e.getMessage()));
            if (onChangeLaunchModeLanguageListener != null) {
                onChangeLaunchModeLanguageListener.onFail(String.format("切换多语言异常:%s", e.getMessage()));
            }
        }
    }

    public static boolean isShowGuanHuai() {
        try {
            if (!App.hasLogined() || UserManager.getInstance().isYiwang()) {
                return false;
            }
            String string = App.getSharePreferenceUtil().getString("careProvinceSwitch");
            String currentProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            if (string.contains("098") || string.contains(currentProvinceCode)) {
                String loginType = UserManager.getInstance().getLoginType();
                if (!"01".equals(loginType) && !"06".equals(loginType) && !"999".equals(loginType) && !"27".equals(loginType)) {
                    if (!"23".equals(loginType)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
