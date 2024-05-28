package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.JSScopeEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthRecordEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthScopeManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/auth")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AuthJSPlugin extends BaseJSPlugin {
    private static Map<String, Dialog> authDialogMap = new HashMap();

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

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String string = this.parameterJO.getString("scope");
            final JSScopeEntity jSScopeEntity = UserAuthScopeManager.getInstance(this.activityContext).getJSScopeEntity(string);
            if (jSScopeEntity != null) {
                final CumpEntity appInfoFromBox = CumpLanucher.getInstance(this.activityContext).getAppInfoFromBox(getCurrentCumpAppId());
                if (!TextUtils.isEmpty(getCurrentCumpAppId()) && getCurrentCumpAppId().startsWith("edop_unicom") && appInfoFromBox != null && !appInfoFromBox.isInnerMiniP()) {
                    UserAuthRecordEntity userAuthRecord = UserAuthScopeManager.getInstance(this.activityContext).getUserAuthRecord(UserManager.getInstance().getCurrentPhoneNumber(), getCurrentCumpAppId(), string);
                    if (userAuthRecord == null) {
                        this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.AuthJSPlugin.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AuthJSPlugin authJSPlugin = AuthJSPlugin.this;
                                authJSPlugin.showAuthDialog(authJSPlugin.activityContext, jSScopeEntity, appInfoFromBox);
                            }
                        });
                    } else if (userAuthRecord.isGrant()) {
                        callbackSuccess(new JSONObject());
                    } else {
                        callbackFail("20", "用户未授权小程序使用" + string + "权限");
                    }
                } else {
                    callbackSuccess(new JSONObject());
                }
            } else {
                callbackSuccess(new JSONObject());
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }

    public void showAuthDialog(Activity activity, JSScopeEntity jSScopeEntity, CumpEntity cumpEntity) {
        LinearLayout linearLayout;
        if (authDialogMap.get(jSScopeEntity.getScope()) == null || !authDialogMap.get(jSScopeEntity.getScope()).isShowing()) {
            Dialog dialog = new Dialog(activity, 2131952223);
            if (jSScopeEntity.getScope().equals("scope.unicomaccount")) {
                linearLayout = (LinearLayout) activity.getLayoutInflater().inflate(2131493070, (ViewGroup) null);
                initUnicomAccountAuthDialogView(activity, dialog, linearLayout, jSScopeEntity, cumpEntity);
            } else {
                linearLayout = (LinearLayout) activity.getLayoutInflater().inflate(2131493065, (ViewGroup) null);
                initAuthDialogView(activity, dialog, linearLayout, jSScopeEntity, cumpEntity);
            }
            dialog.setContentView(linearLayout);
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

    private void initAuthDialogView(final Activity activity, final Dialog dialog, View view, final JSScopeEntity jSScopeEntity, final CumpEntity cumpEntity) {
        TextView textView = (TextView) view.findViewById(2131296733);
        Button button = (Button) view.findViewById(2131296728);
        Button button2 = (Button) view.findViewById(2131296729);
        Glide.with(activity).load(cumpEntity.getAppHighImg()).into((ImageView) view.findViewById(2131296725));
        ((TextView) view.findViewById(2131296727)).setText(cumpEntity.getAppName());
        ((TextView) view.findViewById(2131296730)).setText(jSScopeEntity.getHint());
        if (TextUtils.isEmpty(cumpEntity.getPrivacyUrl())) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.AuthJSPlugin.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    IntentManager.gotoWebViewActivity(activity, cumpEntity.getPrivacyUrl(), "用户隐私协议");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.AuthJSPlugin.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                UserAuthScopeManager.getInstance(activity).saveAuthRecord(UserManager.getInstance().getCurrentPhoneNumber(), AuthJSPlugin.this.getCurrentCumpAppId(), jSScopeEntity.getScope(), false);
                AuthJSPlugin.this.dismissAuthDialog(dialog);
                AuthJSPlugin.this.callbackFail("20", "用户拒绝授权小程序访问此功能");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.AuthJSPlugin.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                UserAuthScopeManager.getInstance(activity).saveAuthRecord(UserManager.getInstance().getCurrentPhoneNumber(), AuthJSPlugin.this.getCurrentCumpAppId(), jSScopeEntity.getScope(), true);
                AuthJSPlugin.this.dismissAuthDialog(dialog);
                AuthJSPlugin.this.callbackSuccess(new JSONObject());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    private void initUnicomAccountAuthDialogView(final Activity activity, final Dialog dialog, View view, final JSScopeEntity jSScopeEntity, final CumpEntity cumpEntity) {
        TextView textView = (TextView) view.findViewById(2131296731);
        TextView textView2 = (TextView) view.findViewById(2131296733);
        Button button = (Button) view.findViewById(2131296728);
        Button button2 = (Button) view.findViewById(2131296729);
        Glide.with(activity).load(cumpEntity.getAppHighImg()).into((ImageView) view.findViewById(2131296725));
        ((TextView) view.findViewById(2131296727)).setText(cumpEntity.getAppName());
        ((TextView) view.findViewById(2131296730)).setText(jSScopeEntity.getHint());
        String userAccountName = UserManager.getInstance().getUserAccountName();
        if (!TextUtils.isEmpty(userAccountName)) {
            StringBuilder sb = new StringBuilder(userAccountName);
            if (userAccountName.length() >= 11) {
                int length = (userAccountName.length() - 4) / 2;
                sb.replace(length, length + 4, "****");
            } else if (userAccountName.length() >= 6) {
                int length2 = (userAccountName.length() - 3) / 2;
                sb.replace(length2, length2 + 3, "***");
            }
            textView.setText(sb.toString());
        } else {
            textView.setText("");
        }
        if (TextUtils.isEmpty(cumpEntity.getPrivacyUrl())) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.AuthJSPlugin.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    IntentManager.gotoWebViewActivity(activity, cumpEntity.getPrivacyUrl(), "用户隐私协议");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.AuthJSPlugin.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                UserAuthScopeManager.getInstance(activity).saveAuthRecord(UserManager.getInstance().getCurrentPhoneNumber(), AuthJSPlugin.this.getCurrentCumpAppId(), jSScopeEntity.getScope(), false);
                AuthJSPlugin.this.dismissAuthDialog(dialog);
                AuthJSPlugin.this.callbackFail("20", "用户拒绝授权小程序访问此功能");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.auth.AuthJSPlugin.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                UserAuthScopeManager.getInstance(activity).saveAuthRecord(UserManager.getInstance().getCurrentPhoneNumber(), AuthJSPlugin.this.getCurrentCumpAppId(), jSScopeEntity.getScope(), true);
                AuthJSPlugin.this.dismissAuthDialog(dialog);
                AuthJSPlugin.this.callbackSuccess(new JSONObject());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }
}
