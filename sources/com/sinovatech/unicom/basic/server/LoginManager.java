package com.sinovatech.unicom.basic.server;

import android.app.Activity;
import android.content.Intent;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.DiagnoseActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayJSPlugin;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayUtils;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LoginManager {
    public static final String Change_Type = "Change_Type";
    public static final String Login_Type = "Login_Type";
    public static final String Mianmi_Login_Type = "Mianmi_Login_Type";
    public static final String OnlineToken_Type = "OnlineToken_Type";
    private static final String TAG = "LoginManager";
    private static UserManager userManager = UserManager.getInstance();

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:56:0x02b8
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.util.HashMap<java.lang.String, java.lang.String> handleLoginResponse(java.lang.String r35, java.lang.String r36, java.lang.String r37) {
        /*
            Method dump skipped, instructions count: 1587
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.server.LoginManager.handleLoginResponse(java.lang.String, java.lang.String, java.lang.String):java.util.HashMap");
    }

    public static void logout(Activity activity) {
        App.clearPersistentCookiesList();
        UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
        userManager.removePassBackDesmobile();
        userManager.removeCurrentPhoneNumber();
        userManager.removeCurrentPhoneType();
        userManager.removeUserBindPhoneNumber();
        userManager.removeCollectionOwner();
        userManager.setFaceType("");
        userManager.removeToken();
        userManager.removeUserPassword();
        userManager.saveAutoLoginStatus(false);
        userManager.removeKeyVersion();
        userManager.removeUserTouxiangURL();
        userManager.removeLimitInterface();
        userManager.removeMainMemberFlag();
        LanguageUtil.setLanguage(LanguageUtil.CHN_CN, "");
        try {
            UnicomPayUtils.getInstance(activity).loginOutPaySdk();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d(UnicomPayJSPlugin.TAG, e.getMessage());
        }
        try {
            MsLogUtil.m7979d("APPSESSIONID", "退出登录");
            App.setPvLogSessionId();
            TYCJBoxManager.getInstance().collectUnicomV("0", "退出登录");
            MsLogUtil.m7980d("实时PV日志>>>调用注销接口，重新生成会话ID " + App.getPvLogSessionId());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        App.setLogined(LoginStateConst.UNLOGIN);
    }

    public static void showLoginNetWorkErrorMessage(Activity activity, Throwable th, boolean z) {
        try {
            String str = "网络连接异常，请您稍后再试";
            String str2 = "登录遇到网络问题:" + th.getMessage() + "\n当您确认您的手机网络处于可用状态而中国联通APP无法连接网络时，您可以辅助我们诊断当前网络状态。";
            if (th != null && SocketTimeoutException.class.isInstance(th)) {
                str = "登录服务超时，请重试";
            } else if (th != null && ConnectTimeoutException.class.isInstance(th)) {
                str = "服务器连接超时，请重试";
            }
            showLoginErrorMessage(activity, str, z, str2, true);
        } catch (Throwable th2) {
            th2.printStackTrace();
            UIUtils.toastLong("登录异常 请重新登录");
        }
    }

    public static void showLoginErrorMessage(final Activity activity, String str, boolean z, String str2, boolean z2) {
        try {
            if (activity.getPackageName().endsWith("beta")) {
                if (z2) {
                    CustomDialogManager.show(activity, "", str2, true, "取消", "辅助诊断", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.server.LoginManager.1
                        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                        public void onClickOk() {
                            activity.startActivity(new Intent(activity, DiagnoseActivity.class));
                        }
                    });
                } else {
                    CustomDialogManager.show(activity, "", str2);
                }
            } else if (z) {
                CustomDialogManager.show(activity, "", str);
            } else {
                UIUtils.toastLong(str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            UIUtils.toastLong("登录异常 请重新登录");
        }
    }

    public static boolean isKuandaiOrGuhua() {
        String currentPhoneType = userManager.getCurrentPhoneType();
        return "03".equals(currentPhoneType) || "02".equals(currentPhoneType) || "04".equals(currentPhoneType);
    }

    public static String getAccountType() {
        String currentPhoneType = userManager.getCurrentPhoneType();
        return UserManager.getInstance().isYiwang() ? "4" : "01".equals(currentPhoneType) ? "1" : "02".equals(currentPhoneType) ? "3" : "03".equals(currentPhoneType) ? "2" : "5";
    }

    public static String getAccountTypeUser() {
        String currentPhoneType = userManager.getCurrentPhoneType();
        return !App.hasLogined() ? "0" : UserManager.getInstance().isYiwang() ? "2" : "01".equals(currentPhoneType) ? "1" : "02".equals(currentPhoneType) ? "4" : "03".equals(currentPhoneType) ? "3" : "5";
    }

    public static void goHome(Activity activity) {
        try {
            App.getSharePreferenceUtil().putBoolean("CareHome", false);
            App.mainTagFromOtherActivity = MainActivity.Fragment_Home;
            LanguageUtil.setLanguage(LanguageUtil.CHN_CN, "");
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
