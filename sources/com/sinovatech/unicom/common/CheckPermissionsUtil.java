package com.sinovatech.unicom.common;

import android.text.TextUtils;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CheckPermissionsUtil {
    private static final String TAG = "CheckPermissionsUtil";
    private static long oneHourTime = 3600000;

    public static boolean checkPermission() {
        String string = App.getSharePreferenceUtil().getString("home_permission_dialog_flag");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        try {
            return System.currentTimeMillis() - Long.parseLong(string) >= (oneHourTime * 24) * 7;
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
            return true;
        }
    }

    public static String getPermissionContent() {
        Permission checkSinglePermission = SoulPermission.getInstance().checkSinglePermission("android.permission.READ_PHONE_STATE");
        return (checkSinglePermission.isGranted() || (SoulPermission.getInstance().checkSinglePermission("android.permission.WRITE_EXTERNAL_STORAGE").isGranted() && SoulPermission.getInstance().checkSinglePermission("android.permission.READ_EXTERNAL_STORAGE").isGranted())) ? !checkSinglePermission.isGranted() ? "尊敬的用户您好，为保证您更好的使用APP基础能力以及向您推荐活动，我们将获取您的设备信息。对于您授权的信息，我们竭尽提供安全保护。" : "尊敬的用户您好，感谢您使用中国联通APP。为了给您带来更好的服务体验，需要获取您在APP内存储的文件信息，用于向您提供APP分享功能。对于您授权的信息，我们竭尽提供安全保护。" : "尊敬的用户您好，为保证您拥有更好的体验，我们将获取您的设备信息、通话权限、APP存储媒体照片及文件内容，分别用于提供APP基础能力、优惠活动推送及客户服务等功能。";
    }
}
