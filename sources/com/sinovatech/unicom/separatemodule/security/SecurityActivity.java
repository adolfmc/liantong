package com.sinovatech.unicom.separatemodule.security;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerWeixinBind;
import com.sinovatech.unicom.basic.p315ui.view.FaceLoginCustomDialog;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinActivity;
import com.sinovatech.unicom.separatemodule.security.SecurityActivity;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SecurityActivity extends BaseActivity {
    private static final String TAG = "SecurityActivity";
    public NBSTraceUnit _nbs_trace;
    private AnQuanAdapter adapter;
    private ImageButton backButton;
    private ConfigManager configManager;
    private AppCompatActivity context;
    private MenuDataCenter dataCenter;
    private HashMap<String, String> faceAccoutnParams;
    private LinearLayout faceLayout;
    private ToggleButton faceToggleButton;
    private List<MenuEntity> list;
    private ListView listView;
    private View listview_bottomline;
    private ManagerFaceLogin managerFaceLogin;
    private ImageView mianmidenlguImage;

    /* renamed from: pd */
    private CustomePorgressDialog f18610pd;
    private SharePreferenceUtil preferenceUtil;
    private LinearLayout resetSecurityPasswordLayout;
    private LinearLayout rootLayout;
    private RxPermissions rxPermissions;
    private ToggleButton securityMianmiToggleButton;
    private ToggleButton securityToggleButton;
    private ImageView shoushimimaImage;
    private LinearLayout titleLayout;
    private TextView titleView;
    private UserManager userManager;
    private LinearLayout weixinToggleLayout;
    private TextView weixinUnbindButton;
    private LinearLayout zhifushezhiLayout;
    private ToggleButton zhiwenButton;
    private LinearLayout zhiwenLayout;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 111);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.security.SecurityActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC94511 implements View.OnClickListener {
        View$OnClickListenerC94511() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            SecurityActivity.this.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.security.SecurityActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C94522 implements CompoundButton.OnCheckedChangeListener {
        C94522() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Tracker.onCheckedChanged(compoundButton, z);
            StatisticsUploadUtils.upload(SecurityActivity.this.context, "8", "安全中心", "按钮", "", "设置手势密码", "");
            if (App.hasLogined() || !z) {
                return;
            }
            CustomDialogManager.show((Activity) SecurityActivity.this.context, "温馨提示", "请您登录时选择自动登录后，再来设置手势密码吧！", false, "", "确定", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.security.SecurityActivity.2.1
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onBackKeyDown() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickOk() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onShow() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onCancel() {
                    SecurityActivity.this.securityToggleButton.setChecked(false);
                }
            });
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.security.SecurityActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C94543 implements CompoundButton.OnCheckedChangeListener {
        C94543() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Tracker.onCheckedChanged(compoundButton, z);
            if (z) {
                if (SecurityActivity.this.preferenceUtil.getBoolean("isnotifyagain")) {
                    return;
                }
                IntentManager.gotoWebViewActivity(SecurityActivity.this.context, URLSet.getnoLoginAgreement(), "免密登录协议");
                return;
            }
            SecurityActivity.this.preferenceUtil.putBoolean("ismiandengluon", Boolean.valueOf(z));
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.security.SecurityActivity$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC94554 implements View.OnClickListener {
        View$OnClickListenerC94554() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            String string = App.getInstance().getString(2131886213);
            SecurityActivity securityActivity = SecurityActivity.this;
            CustomDialogManager.show((Activity) securityActivity, "", "解除绑定后将无法使用微信登录" + string + "，您确定要这么做吗？", true, "再想想", "解除", false, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.security.SecurityActivity.4.1
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    ManagerWeixinBind.unBind(SecurityActivity.this);
                }
            });
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.security.SecurityActivity$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC94575 implements View.OnClickListener {
        View$OnClickListenerC94575() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            SecurityActivity securityActivity = SecurityActivity.this;
            securityActivity.startActivity(new Intent(securityActivity, AnquanzhongxinActivity.class));
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.security.SecurityActivity$6 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC94586 implements View.OnClickListener {
        View$OnClickListenerC94586() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            IntentManager.gotoWebViewActivity(SecurityActivity.this.context, URLSet.getTongYiPaySettingUrl(), "支付设置");
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.security.SecurityActivity$7 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C94597 implements AdapterView.OnItemClickListener {
        C94597() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            NBSActionInstrumentation.onItemClickEnter(view, i, this);
            Tracker.onItemClick(adapterView, view, i, j);
            StatisticsUploadUtils.upload(SecurityActivity.this.context, "8", "安全中心", "导航", ((MenuEntity) SecurityActivity.this.list.get(i)).getMenuId(), ((MenuEntity) SecurityActivity.this.list.get(i)).getMenuTitle(), ((MenuEntity) SecurityActivity.this.list.get(i)).getMenuURL());
            IntentManager.generateIntentAndGo(SecurityActivity.this.context, (MenuEntity) SecurityActivity.this.list.get(i), "get");
            NBSActionInstrumentation.onItemClickExit();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.list = this.dataCenter.getAnquanzhongxin();
        this.adapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(this.listView);
        if (this.list.size() > 0) {
            this.listView.setVisibility(0);
            this.listview_bottomline.setVisibility(8);
        } else {
            this.listView.setVisibility(8);
            this.listview_bottomline.setVisibility(8);
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(2131493420, (ViewGroup) null);
        int i = 0;
        for (int i2 = 0; i2 < this.adapter.getCount(); i2++) {
            linearLayout.measure(0, 0);
            i += linearLayout.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = i + (listView.getDividerHeight() * (this.adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class AnQuanAdapter extends BaseAdapter {
        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        AnQuanAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return SecurityActivity.this.list.size();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            MenuEntity menuEntity = (MenuEntity) SecurityActivity.this.list.get(i);
            LinearLayout linearLayout = (LinearLayout) SecurityActivity.this.getLayoutInflater().inflate(2131493420, (ViewGroup) null);
            View findViewById = linearLayout.findViewById(2131298500);
            ((TextView) linearLayout.findViewById(2131298501)).setText(menuEntity.getMenuTitle());
            Glide.with((FragmentActivity) SecurityActivity.this.context).load(menuEntity.getMenuIconURL()).into((ImageView) linearLayout.findViewById(2131298499));
            if (i == getCount() - 1) {
                findViewById.setVisibility(8);
            }
            return linearLayout;
        }
    }

    private void showFasongToast(String str) {
        View inflate = getLayoutInflater().inflate(2131493077, (ViewGroup) null);
        ((TextView) inflate.findViewById(2131297026)).setText(str);
        Toast toast = new Toast(this);
        toast.setGravity(80, 0, 250);
        toast.setDuration(0);
        toast.setView(inflate);
        toast.show();
    }

    private void initSaoLian() {
        this.faceLayout = (LinearLayout) findViewById(2131298445);
        this.faceToggleButton = (ToggleButton) findViewById(2131298504);
        this.managerFaceLogin = new ManagerFaceLogin(this.context);
        this.faceAccoutnParams = new HashMap<>();
        this.faceToggleButton.setChecked(false);
        if (App.hasLogined()) {
            this.faceLayout.setVisibility(0);
            this.faceAccoutnParams.clear();
            this.faceAccoutnParams.put("accountName", UserManager.getInstance().getCurrentPhoneNumber());
            this.faceAccoutnParams.put("search_code", "1");
            this.faceAccoutnParams.put("userType", UserManager.getInstance().getLoginType());
            this.faceToggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.security.-$$Lambda$SecurityActivity$Jprc_zKMnW45z7i-ekuVxmbleqM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SecurityActivity.lambda$initSaoLian$0(SecurityActivity.this, view);
                }
            });
            if ("29".equals(this.userManager.getFaceType()) || "30".equals(this.userManager.getFaceType()) || "31".equals(this.userManager.getFaceType())) {
                this.faceLayout.setVisibility(0);
                chaxun();
                return;
            }
            this.faceLayout.setVisibility(8);
            return;
        }
        this.faceLayout.setVisibility(8);
    }

    public static /* synthetic */ void lambda$initSaoLian$0(SecurityActivity securityActivity, View view) {
        if (securityActivity.faceToggleButton.isChecked()) {
            securityActivity.showFuwuDialog();
        } else {
            securityActivity.deleteFace();
        }
    }

    private void showFuwuDialog() {
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        if (!sharePreferenceUtil.getBoolean("shualianxieyi_new" + this.userManager.getCurrentPhoneNumber())) {
            FaceLoginCustomDialog.show(this.context, new FaceLoginCustomDialog.UpdateCustomDialogListener() { // from class: com.sinovatech.unicom.separatemodule.security.SecurityActivity.8
                @Override // com.sinovatech.unicom.basic.p315ui.view.FaceLoginCustomDialog.UpdateCustomDialogListener
                public void onClickOk() {
                    SecurityActivity.this.zhuce();
                }

                @Override // com.sinovatech.unicom.basic.p315ui.view.FaceLoginCustomDialog.UpdateCustomDialogListener
                public void onCancel() {
                    SecurityActivity.this.faceToggleButton.setChecked(false);
                }
            });
        } else {
            zhuce();
        }
    }

    private void chaxun() {
        StatisticsUploadUtils.upload("shualiandenglu", "安全中心", "查询", "", UserManager.getInstance().getCurrentPhoneNumber(), "");
        this.faceAccoutnParams.put("areaCode", UserManager.getInstance().getUserAreaid());
        this.managerFaceLogin.loadFaceQuery(this.faceAccoutnParams).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.security.-$$Lambda$SecurityActivity$KJ4AWiEcZtDWGngJOoysj0u3esE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SecurityActivity.lambda$chaxun$1(SecurityActivity.this, (String) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.security.-$$Lambda$SecurityActivity$74MWwCyszQkNcATMGNW7RcKRXuQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SecurityActivity.lambda$chaxun$2(SecurityActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$chaxun$1(SecurityActivity securityActivity, String str) throws Exception {
        if ("0000".equals(new JSONObject(str).optString("code"))) {
            securityActivity.faceToggleButton.setChecked(true);
            return;
        }
        securityActivity.faceToggleButton.setChecked(false);
        securityActivity.faceLayout.setVisibility(8);
    }

    public static /* synthetic */ void lambda$chaxun$2(SecurityActivity securityActivity, Throwable th) throws Exception {
        securityActivity.faceToggleButton.setChecked(false);
        securityActivity.faceLayout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.security.SecurityActivity$9 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C94619 implements ManagerFaceLogin.IFaceResult {
        C94619() {
        }

        @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin.IFaceResult
        public void onResult(String str) {
            if (TextUtils.isEmpty(str)) {
                SecurityActivity.this.faceToggleButton.setChecked(false);
                return;
            }
            SecurityActivity.this.faceAccoutnParams.clear();
            SecurityActivity.this.faceAccoutnParams.put("accountName", UserManager.getInstance().getCurrentPhoneNumber());
            SecurityActivity.this.faceAccoutnParams.put("add_type", "0");
            SecurityActivity.this.f18610pd.show();
            StatisticsUploadUtils.upload("shualiandenglu", "安全中心", "注册", "", UserManager.getInstance().getCurrentPhoneNumber(), "");
            SecurityActivity.this.managerFaceLogin.loadFaceRegist(SecurityActivity.this.faceAccoutnParams, str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.security.-$$Lambda$SecurityActivity$9$2Pi_lMqr0qrY69ZmrmdcW93zVU0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SecurityActivity.C94619.lambda$onResult$0(SecurityActivity.C94619.this, (String) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.security.-$$Lambda$SecurityActivity$9$c_nUxYxNyWYfKyHIe0cHXL_Ygd8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SecurityActivity.C94619.lambda$onResult$1(SecurityActivity.C94619.this, (Throwable) obj);
                }
            });
        }

        public static /* synthetic */ void lambda$onResult$0(C94619 c94619, String str) throws Exception {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("dsc");
            if ("0000".equals(optString)) {
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = "开启刷脸成功";
                }
                CustomDialogManager.show(SecurityActivity.this.context, "", optString2);
                SecurityActivity.this.faceToggleButton.setChecked(true);
                SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                sharePreferenceUtil.putBoolean("shualianxieyi_new" + UserManager.getInstance().getCurrentPhoneNumber(), true);
            } else {
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = "开启刷脸失败";
                }
                SecurityActivity.this.faceToggleButton.setChecked(false);
                CustomDialogManager.show(SecurityActivity.this.context, "", optString2);
                SharePreferenceUtil sharePreferenceUtil2 = App.getSharePreferenceUtil();
                sharePreferenceUtil2.putBoolean("shualianxieyi_new" + UserManager.getInstance().getCurrentPhoneNumber(), false);
            }
            SecurityActivity.this.pdDismiss();
        }

        public static /* synthetic */ void lambda$onResult$1(C94619 c94619, Throwable th) throws Exception {
            UIUtils.toast("s：" + th.getMessage());
            SecurityActivity.this.pdDismiss();
            SecurityActivity.this.faceToggleButton.setChecked(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zhuce() {
        starActivieLive(new C94619());
    }

    private void deleteFace() {
        this.faceAccoutnParams.clear();
        this.faceAccoutnParams.put("accountName", UserManager.getInstance().getCurrentPhoneNumber());
        HashMap<String, String> hashMap = this.faceAccoutnParams;
        UserManager userManager = this.userManager;
        hashMap.put("token_online", userManager.getOnlineToken(userManager.getCurrentPhoneNumber()));
        this.f18610pd.show();
        StatisticsUploadUtils.upload("shualiandenglu", "安全中心", "删除", "", UserManager.getInstance().getCurrentPhoneNumber(), "");
        this.managerFaceLogin.loadDelete(this.faceAccoutnParams).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.security.-$$Lambda$SecurityActivity$Nq3Yqlw6rkZzgM4rgXttKLTB828
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SecurityActivity.lambda$deleteFace$3(SecurityActivity.this, (String) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.security.-$$Lambda$SecurityActivity$HYISx3LuMy66Yn0On9KEOZa61fk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                SecurityActivity.this.pdDismiss();
            }
        });
    }

    public static /* synthetic */ void lambda$deleteFace$3(SecurityActivity securityActivity, String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("code");
        String optString2 = jSONObject.optString("dsc");
        if ("0000".equals(optString)) {
            if (TextUtils.isEmpty(optString2)) {
                optString2 = "注销刷脸成功";
            }
            CustomDialogManager.show(securityActivity.context, "", optString2);
            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            sharePreferenceUtil.putBoolean("shualianxieyi_new" + UserManager.getInstance().getCurrentPhoneNumber(), false);
        } else {
            if (TextUtils.isEmpty(optString2)) {
                optString2 = "注销刷脸失败";
            }
            AppCompatActivity appCompatActivity = securityActivity.context;
            CustomDialogManager.show(appCompatActivity, "", optString2 + "【" + optString + "】");
        }
        securityActivity.pdDismiss();
    }

    private void starActivieLive(final ManagerFaceLogin.IFaceResult iFaceResult) {
        PermissionDialog.show("扫脸登录为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        this.rxPermissions.request("android.permission.CAMERA").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.security.-$$Lambda$SecurityActivity$bIDfb9551QCX841O7-dPj62njaA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SecurityActivity.lambda$starActivieLive$5(SecurityActivity.this, iFaceResult, (Boolean) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$starActivieLive$5(SecurityActivity securityActivity, ManagerFaceLogin.IFaceResult iFaceResult, Boolean bool) throws Exception {
        PermissionDialog.dimissDialog();
        if (bool.booleanValue()) {
            TYCJBoxManager.getInstance().collectClickSdk(securityActivity.context, "S2ndpage1214", "登录", "人脸识别", "", "com.megvii.kas.livenessdetection", "1");
            ManagerFaceLogin.starFaceLogin(securityActivity.context, iFaceResult);
            return;
        }
        UIUtils.toastCenter("相机权限未开启，请前往[设置>中国联通]打开相机权限[ECSO12F]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDismiss() {
        CustomePorgressDialog customePorgressDialog = this.f18610pd;
        if (customePorgressDialog == null || !customePorgressDialog.isShowing()) {
            return;
        }
        this.f18610pd.dismiss();
    }
}
