package com.sinovatech.unicom.separatemodule.miniprogram.h5auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.JSPermissionEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.JSPermissionManager;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.JSScopeEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthRecordEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthScopeManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/authH5")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AuthH5JSPlugin extends BaseJSPlugin {
    private static Map<String, Dialog> authDialogMap = new HashMap();
    private JSPermissionEntity jsPermissionEntity;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    public static void clearAuthDialogMap() {
        try {
            if (authDialogMap != null) {
                authDialogMap.clear();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(CumpLanucher.TAG, e.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00da -> B:32:0x012c). Please submit an issue!!! */
    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String string = this.parameterJO.getString("scope");
            String optString = this.parameterJO.optString("action");
            final JSScopeEntity jSScopeEntity = UserAuthScopeManager.getInstance(this.activityContext).getJSScopeEntity(string);
            this.jsPermissionEntity = JSPermissionManager.getInstance(this.activityContext).getJSPermissionEntity(optString);
            boolean checkJSPermission = JSPermissionManager.getInstance(this.activityContext).checkJSPermission("", this.webFragment.currentH5Id, optString, getCurrentURL());
            if (jSScopeEntity != null && this.jsPermissionEntity != null && this.jsPermissionEntity.isReqUserGrant_H5()) {
                final H5RegisterEntity h5RegisterEntity = H5AuthManager.getInstance(this.activityContext).getH5RegisterEntity(this.webFragment.currentH5Id);
                if (!TextUtils.isEmpty(this.webFragment.currentH5Id) && h5RegisterEntity != null && checkJSPermission) {
                    UserAuthRecordEntity h5AuthRecord = UserAuthScopeManager.getInstance(this.activityContext).getH5AuthRecord(this.webFragment.currentH5Id, string);
                    if (h5AuthRecord == null) {
                        Activity activity = this.activityContext;
                        activity.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.AuthH5JSPlugin.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AuthH5JSPlugin authH5JSPlugin = AuthH5JSPlugin.this;
                                authH5JSPlugin.showAuthDialog(authH5JSPlugin.activityContext, jSScopeEntity, h5RegisterEntity);
                            }
                        });
                        string = activity;
                    } else if (h5AuthRecord.isGrant()) {
                        Object jSONObject = new JSONObject();
                        callbackSuccess(jSONObject);
                        string = jSONObject;
                    } else {
                        try {
                            if (System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(h5AuthRecord.getTime()).getTime() > 604800000) {
                                this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.AuthH5JSPlugin.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        AuthH5JSPlugin authH5JSPlugin = AuthH5JSPlugin.this;
                                        authH5JSPlugin.showAuthDialog(authH5JSPlugin.activityContext, jSScopeEntity, h5RegisterEntity);
                                    }
                                });
                                string = string;
                            } else {
                                callbackFail("20", "用户未授权使用" + string + "权限");
                                string = string;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            String str = "用户未授权使用" + string + "权限";
                            callbackFail("20", str);
                            string = str;
                        }
                    }
                } else {
                    Object jSONObject2 = new JSONObject();
                    callbackSuccess(jSONObject2);
                    string = jSONObject2;
                }
            } else {
                Object jSONObject3 = new JSONObject();
                callbackSuccess(jSONObject3);
                string = jSONObject3;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            callbackFail("10", "程序错误：" + e2.getMessage());
        }
    }

    public void showAuthDialog(Activity activity, JSScopeEntity jSScopeEntity, H5RegisterEntity h5RegisterEntity) {
        if (authDialogMap.get(jSScopeEntity.getScope()) == null || !authDialogMap.get(jSScopeEntity.getScope()).isShowing()) {
            Dialog dialog = new Dialog(activity, 2131952223);
            RelativeLayout relativeLayout = (RelativeLayout) activity.getLayoutInflater().inflate(2131493066, (ViewGroup) null);
            initAuthDialogView(activity, dialog, relativeLayout, jSScopeEntity, h5RegisterEntity);
            dialog.setContentView(relativeLayout);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setGravity(80);
            window.setAttributes(attributes);
            window.setWindowAnimations(2131952222);
            dialog.show();
            authDialogMap.put(jSScopeEntity.getScope(), dialog);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissAuthDialog(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        dialog.dismiss();
    }

    private void initAuthDialogView(final Activity activity, final Dialog dialog, View view, final JSScopeEntity jSScopeEntity, final H5RegisterEntity h5RegisterEntity) {
        TextView textView = (TextView) view.findViewById(2131296733);
        TextView textView2 = (TextView) view.findViewById(2131296732);
        Button button = (Button) view.findViewById(2131296728);
        Button button2 = (Button) view.findViewById(2131296729);
        ((RelativeLayout) view.findViewById(2131296726)).setVisibility(8);
        ((TextView) view.findViewById(2131296727)).setText(h5RegisterEntity.getAppName());
        ((TextView) view.findViewById(2131296730)).setText(jSScopeEntity.getHint());
        textView2.setVisibility(0);
        JSPermissionEntity jSPermissionEntity = this.jsPermissionEntity;
        if (jSPermissionEntity != null) {
            textView2.setText(h5RegisterEntity.getGrantRemark(jSPermissionEntity.getPlugCode()));
        } else {
            textView2.setText("");
        }
        textView.setVisibility(8);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.AuthH5JSPlugin.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                AuthH5JSPlugin.this.dismissAuthDialog(dialog);
                AuthH5JSPlugin.this.showSecondConfirmDialog(activity, jSScopeEntity, h5RegisterEntity);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.AuthH5JSPlugin.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                UserAuthScopeManager.getInstance(activity).saveH5AuthRecord(AuthH5JSPlugin.this.webFragment.currentH5Id, jSScopeEntity.getScope(), true, h5RegisterEntity.getGrantRemark(AuthH5JSPlugin.this.jsPermissionEntity.getPlugCode()));
                AuthH5JSPlugin.this.callbackSuccess(new JSONObject());
                AuthH5JSPlugin.this.dismissAuthDialog(dialog);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public void showSecondConfirmDialog(Activity activity, JSScopeEntity jSScopeEntity, H5RegisterEntity h5RegisterEntity) {
        Dialog dialog = new Dialog(activity, 2131952223);
        RelativeLayout relativeLayout = (RelativeLayout) activity.getLayoutInflater().inflate(2131493067, (ViewGroup) null);
        initSecondConfirmDialog(activity, dialog, relativeLayout, jSScopeEntity, h5RegisterEntity);
        dialog.setContentView(relativeLayout);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        Window window = dialog.getWindow();
        window.setGravity(80);
        window.setAttributes(attributes);
        window.setWindowAnimations(2131952222);
        dialog.show();
        authDialogMap.put(jSScopeEntity.getScope(), dialog);
    }

    private void initSecondConfirmDialog(final Activity activity, final Dialog dialog, View view, final JSScopeEntity jSScopeEntity, final H5RegisterEntity h5RegisterEntity) {
        ((Button) view.findViewById(2131296728)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.AuthH5JSPlugin.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                AuthH5JSPlugin.this.dismissAuthDialog(dialog);
                UserAuthScopeManager.getInstance(activity).saveH5AuthRecord(AuthH5JSPlugin.this.webFragment.currentH5Id, jSScopeEntity.getScope(), false, h5RegisterEntity.getGrantRemark(AuthH5JSPlugin.this.jsPermissionEntity.getPlugCode()));
                AuthH5JSPlugin.this.callbackFail("11", "用户拒绝授权访问此功能");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((Button) view.findViewById(2131296729)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.h5auth.AuthH5JSPlugin.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                AuthH5JSPlugin.this.dismissAuthDialog(dialog);
                AuthH5JSPlugin.this.showAuthDialog(activity, jSScopeEntity, h5RegisterEntity);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }
}
