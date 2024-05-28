package com.sinovatech.unicom.separatemodule.comonjsplugin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/logout")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LogoutJSPlugin extends BaseJSPlugin {

    /* renamed from: pd */
    private ProgressDialog f18515pd;
    private UserManager userManager;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
        this.userManager = UserManager.getInstance();
        this.f18515pd = new CustomePorgressDialog(context);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        logout(this.parameterJO.optInt("type"));
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        logout(this.parameterJO.optInt("type"));
        return "";
    }

    private void logout(int i) {
        if (i == 0) {
            UserManager.getInstance().removeSelectAccountName(UserManager.getInstance().getUserAccountName());
        }
        RequestParams requestParams = new RequestParams();
        if (i == 0) {
            requestParams.put("logout_flag", "2");
        } else {
            requestParams.put("logout_flag", "1");
        }
        requestParams.put("version", this.activityContext.getString(2131886969));
        requestParams.put("desmobile", this.userManager.getPassBackDesmobile());
        UserManager userManager = this.userManager;
        requestParams.put("token_online", userManager.getOnlineToken(userManager.getCurrentPhoneNumber()));
        App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.comonjsplugin.LogoutJSPlugin.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                try {
                    LogoutJSPlugin.this.f18515pd.setMessage("正在注销 请稍候");
                    LogoutJSPlugin.this.f18515pd.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                LogoutJSPlugin.this.f18515pd.cancel();
                LoginManager.logout(LogoutJSPlugin.this.activityContext);
                LogoutJSPlugin.this.activityContext.startActivity(new Intent(LogoutJSPlugin.this.activityContext, MainActivity.class));
            }
        });
    }
}
