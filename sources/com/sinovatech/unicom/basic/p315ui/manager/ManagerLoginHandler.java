package com.sinovatech.unicom.basic.p315ui.manager;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import cn.finalteam.galleryfinal.utils.LoginConstUtil;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.DeviceInfoStatistics;
import java.util.HashMap;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerLoginHandler */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerLoginHandler {
    private AppCompatActivity activityContext;
    private LoginStyle loginStyle = LoginStyle.Account;
    private UserManager userManager = UserManager.getInstance();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerLoginHandler$LoginStyle */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum LoginStyle {
        SMS,
        Account,
        GuHua,
        Broband,
        ShenFen,
        OneKey,
        WeiXin
    }

    public ManagerLoginHandler(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void handlerResult(String str, String str2, boolean z, String str3, String str4, String str5) {
        HashMap<String, String> handleLoginResponse = LoginManager.handleLoginResponse(str, str2, "Login_Type");
        if ("ok".equals(handleLoginResponse.get("ok"))) {
            App.setLogined(LoginStateConst.DID_LOGIN);
            WebFragment.isRelogin = true;
            String currentPhoneNumber = TextUtils.isEmpty(str) ? this.userManager.getCurrentPhoneNumber() : str;
            App.reLoadDefaultMenuData = true;
            App.reLoadAdvertise = true;
            String str6 = handleLoginResponse.get("description");
            if (!TextUtils.isEmpty(str6)) {
                UIUtils.toastLong(str6);
            }
            UserManager userManager = this.userManager;
            userManager.saveSelectAccountName(currentPhoneNumber, userManager.getUserAreaid(), this.userManager.getCurrentPhoneType(), "faceLogin", this.userManager.getKeyVersion(), "1", this.userManager.getUserTouxiangURL());
            if (z) {
                Intent intent = new Intent(this.activityContext, MainActivity.class);
                intent.putExtra("from", "app");
                this.activityContext.startActivity(intent);
                this.activityContext.setResult(-1);
                this.activityContext.finish();
            } else {
                this.activityContext.setResult(-1);
                this.activityContext.finish();
            }
            DeviceInfoStatistics.uploadTianYuan("1", "01", "01");
            return;
        }
        this.userManager.removeUserTouxiangURL();
        App.setLogined(LoginStateConst.UNLOGIN);
        handleLoginResponse.get("exception");
        String str7 = TextUtils.isEmpty(handleLoginResponse.get("errorCode")) ? "SERVER CODE IS NULL" : handleLoginResponse.get("errorCode");
        String str8 = TextUtils.isEmpty(handleLoginResponse.get("description")) ? "很抱歉，暂时无法使用，请您稍候再试" : handleLoginResponse.get("description");
        if ("0003".equals(str7)) {
            CustomDialogManager.show(this.activityContext, "", str8);
        } else if ("ECS10002".equals(str7)) {
            showFreezeDialog(str, str8, str3, str4, str5);
        } else {
            CustomDialogManager.show(this.activityContext, "", str8);
        }
        DeviceInfoStatistics.uploadTianYuan("1", "01", "02");
    }

    private void showFreezeDialog(String str, String str2, String str3, String str4, final String str5) {
        CustomDialogManager.showZhuXiao(SoulPermission.getInstance().getTopActivity(), "号码已注销", LoginConstUtil.ZHUXIAOMSG, true, "取消", "立即激活", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerLoginHandler.1
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                String str6 = "";
                if ("1".equals(str5) || "3".equals(str5)) {
                    str6 = "01";
                } else if ("2".equals(str5)) {
                    str6 = "03";
                } else if ("4".equals(str5)) {
                    str6 = "02";
                }
                HashMap hashMap = new HashMap();
                hashMap.put("type", str6);
                IntentManager.gotoWebViewActivityWithParams(ManagerLoginHandler.this.activityContext, URLSet.getFreezeHtml(), "", hashMap);
            }
        });
    }
}
