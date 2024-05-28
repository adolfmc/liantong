package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.security.LockPatternUtil;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LockScreenManager {
    public static boolean hasLockPattern(String str) {
        return LockPatternUtil.hasLockPattern(App.getInstance(), str);
    }

    public static void welcomeStart(Activity activity, String str, final Consumer<Boolean> consumer) {
        if (((TextUtils.isEmpty(str) || TextUtils.isEmpty(UserManager.getInstance().getOnlineToken(str))) ? false : true) && hasLockPattern(str)) {
            LockPatternUtil.compareLockPattern(activity, str, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.LockScreenManager.1
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    MsLogUtil.m7979d("LockScreenManager", "====resultCode---" + i);
                    if (i == -1) {
                        MsLogUtil.m7979d("LockScreenManager", "====继续执行---");
                        try {
                            Consumer.this.accept(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            return;
        }
        MsLogUtil.m7979d("LockScreenManager", "====继续执行---");
        try {
            consumer.accept(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
