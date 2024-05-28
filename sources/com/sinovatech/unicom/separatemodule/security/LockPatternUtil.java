package com.sinovatech.unicom.separatemodule.security;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sinovatech.unicom.basic.eventbus.CustomActivityEvent;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WelcomeClient;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinEntity;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinEntity_;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import group.pals.android.lib.p392ui.lockpattern.LockPatternActivity;
import group.pals.android.lib.p392ui.lockpattern.prefs.DisplayPrefs;
import group.pals.android.lib.p392ui.lockpattern.prefs.SecurityPrefs;
import io.objectbox.Box;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LockPatternUtil {
    public static final int REQ_CREATE_PATTERN = 2400;
    public static final int REQ_ENTER_PATTERN = 2401;
    private static Context mContext;

    public static void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 2400:
                if (i2 == -1) {
                    UIUtils.toast("您已成功设置手势密码！");
                    return;
                }
                return;
            case 2401:
                switch (i2) {
                    case -1:
                    case 0:
                    default:
                        return;
                }
            default:
                return;
        }
    }

    public static void createLockPattern(Activity activity, String str, AvoidOnResult.Callback callback) {
        registCallBack(activity, str);
        Intent intent = new Intent(LockPatternActivity.ACTION_CREATE_PATTERN, null, activity, LockPatternActivity.class);
        intent.putExtra(LockPatternActivity.EXTRA_INTENT_ACTIVITY_ACCOUNT_INFO, str);
        new AvoidOnResult(activity).startForResult(intent, callback);
    }

    public static void compareLockPattern(Activity activity, String str, AvoidOnResult.Callback callback) {
        compareLockPattern(activity, str, false, "", callback);
    }

    public static void compareLockPattern(Activity activity, String str, boolean z, String str2, AvoidOnResult.Callback callback) {
        compareLockPattern(activity, str, z, false, str2, callback);
    }

    public static void compareLockPattern(Activity activity, String str, boolean z, boolean z2, String str2, AvoidOnResult.Callback callback) {
        registCallBack(activity, str, z, str2);
        Intent intent = new Intent(LockPatternActivity.ACTION_COMPARE_PATTERN, null, activity, LockPatternActivity.class);
        intent.putExtra(LockPatternActivity.EXTRA_INTENT_ACTIVITY_ACCOUNT_INFO, str);
        intent.setFlags(536870912);
        intent.putExtra("isH5", z);
        intent.putExtra("isReset", z2);
        new AvoidOnResult(activity).startForResult(intent, callback);
    }

    public static void registCallBack(Activity activity, String str) {
        registCallBack(activity, str, false, "");
    }

    public static void registCallBack(final Activity activity, final String str, final boolean z, final String str2) {
        LockPatternActivity.callBackInterface = new LockPatternActivity.CallBackInterface() { // from class: com.sinovatech.unicom.separatemodule.security.LockPatternUtil.1
            @Override // group.pals.android.lib.p392ui.lockpattern.LockPatternActivity.CallBackInterface
            public void onKeyDownForBack() {
                MsLogUtil.m7979d("LockScreenManager", "====点击返回按钮---");
            }

            @Override // group.pals.android.lib.p392ui.lockpattern.LockPatternActivity.CallBackInterface
            public void gotoLogin() {
                if (z) {
                    DeviceHelper.resetAnquanzhongxinDeviceID(str2);
                }
                LockPatternUtil.clearLockPattern(activity, str);
                UserManager.getInstance().removeSelectAccountName(str);
                Box boxFor = App.getBoxStore().boxFor(AnquanzhongxinEntity.class);
                AnquanzhongxinEntity anquanzhongxinEntity = (AnquanzhongxinEntity) boxFor.query().equal(AnquanzhongxinEntity_.mobile, str).build().findFirst();
                if (anquanzhongxinEntity != null) {
                    anquanzhongxinEntity.setSelected2(false);
                    anquanzhongxinEntity.setSelected4(false);
                    boxFor.remove(anquanzhongxinEntity.getId());
                    boxFor.put((Box) anquanzhongxinEntity);
                }
                if (!"0".equals(App.getSharePreferenceUtil().getString("unicom_app_main_type"))) {
                    App.getSharePreferenceUtil().putString("unicom_app_main_type", "0");
                    App.getSharePreferenceUtil().putString("unicom_app_main_url", "");
                    EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
                    Activity activity2 = activity;
                    activity2.startActivity(new Intent(activity2, MainActivity.class));
                    App.getSharePreferenceUtil().putBoolean("CareHome", false);
                    LoginManager.logout(activity);
                    activity.finish();
                    return;
                }
                Intent intent = new Intent(activity, LoginActivity.class);
                intent.putExtra("fromLockPattern", "fromLockPattern");
                intent.putExtra("fromLockPatternbackground", "fromLockPatternbackground");
                activity.startActivity(intent);
                LoginManager.logout(activity);
                Activity activity3 = activity;
                if (activity3 instanceof WelcomeClient) {
                    activity3.finish();
                }
            }
        };
    }

    public static void init(Context context, int i, int i2) {
        mContext = context;
        SecurityPrefs.setAutoSavePattern(context, true);
        DisplayPrefs.setMaxRetry(context, i);
        DisplayPrefs.setMinWiredDots(context, i2);
    }

    public static boolean hasLockPattern(Context context, String str) {
        return SecurityPrefs.getPattern(context, str) != null;
    }

    public static void clearLockPattern(Context context, String str) {
        SecurityPrefs.setPattern(context, str, null);
    }
}
