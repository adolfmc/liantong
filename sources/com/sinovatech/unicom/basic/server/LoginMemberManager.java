package com.sinovatech.unicom.basic.server;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.utils.LoginConstUtil;
import com.bytedance.applog.tracker.Tracker;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ErrorStatusCodeException;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.eventbus.FragmentOnResumeEvent;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.basic.p314po.LoginMemberEntity;
import com.sinovatech.unicom.basic.p314po.LoginTuiJianEntity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.LogFileUploadUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.LoginParamsEntity;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayUtils;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LoginMemberManager {
    private static final String TAG = "LoginMemberManager";
    private static Activity activity = null;
    private static final int colorBlue = -12483108;
    private static final int colorRed = -2872014;
    public static boolean isRuanDengLu = false;
    public static boolean isShow = false;
    static String mErrorCode = "";
    private static Dialog memberDialog;

    /* JADX INFO: Access modifiers changed from: private */
    public static String getTouchCode(int i) {
        return i == -2872014 ? "1000230055" : "1000230056";
    }

    public static void clearMemberData() {
        try {
            App.getBoxStore().boxFor(LoginMemberEntity.class).removeAll();
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
        }
    }

    public static void saveMemberData(List<LoginMemberEntity> list) {
        try {
            Box boxFor = App.getBoxStore().boxFor(LoginMemberEntity.class);
            boxFor.removeAll();
            boxFor.put((Collection) list);
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
        }
    }

    public static void clearTuiJianData() {
        try {
            App.getBoxStore().boxFor(LoginTuiJianEntity.class).removeAll();
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
        }
    }

    public static List<LoginMemberEntity> getMemberBoxAllData() {
        ArrayList arrayList = new ArrayList();
        try {
            List<LoginMemberEntity> all = App.getBoxStore().boxFor(LoginMemberEntity.class).getAll();
            return all != null ? all.size() > 0 ? all : arrayList : arrayList;
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
            return arrayList;
        }
    }

    public static void saveMemberEntity(LoginMemberEntity loginMemberEntity) {
        try {
            App.getBoxStore().boxFor(LoginMemberEntity.class).put((Box) loginMemberEntity);
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
        }
    }

    public static void saveTuiJianData(List<LoginTuiJianEntity> list) {
        try {
            Box boxFor = App.getBoxStore().boxFor(LoginTuiJianEntity.class);
            boxFor.removeAll();
            boxFor.put((Collection) list);
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
        }
    }

    public static List<LoginTuiJianEntity> getLoginTuiJianBoxAllData() {
        ArrayList arrayList = new ArrayList();
        try {
            List<LoginTuiJianEntity> all = App.getBoxStore().boxFor(LoginTuiJianEntity.class).getAll();
            return all != null ? all.size() > 0 ? all : arrayList : arrayList;
        } catch (Exception e) {
            UIUtils.logE(TAG, e.getMessage());
            return arrayList;
        }
    }

    public static List<LoginMemberEntity> getMemberData(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            if ("0".equals(new ConfigManager(context).getYiWangState())) {
                List<LoginMemberEntity> memberBoxAllData = getMemberBoxAllData();
                if (memberBoxAllData == null || memberBoxAllData.size() <= 0) {
                    return arrayList;
                }
                for (int i = 0; i < memberBoxAllData.size(); i++) {
                    LoginMemberEntity loginMemberEntity = memberBoxAllData.get(i);
                    if (loginMemberEntity != null && (!"0050".equals(loginMemberEntity.getType()) || "0".equals(loginMemberEntity.getCurrentNum()) || "1".equals(loginMemberEntity.getOrigin()))) {
                        arrayList.add(loginMemberEntity);
                    }
                }
                return arrayList;
            }
            return getMemberBoxAllData();
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
            return arrayList;
        }
    }

    public static LoginMemberEntity getCurrentMemberData() {
        try {
            List<LoginMemberEntity> memberBoxAllData = getMemberBoxAllData();
            if (memberBoxAllData == null || memberBoxAllData.size() <= 0) {
                return null;
            }
            for (int i = 0; i < memberBoxAllData.size(); i++) {
                LoginMemberEntity loginMemberEntity = memberBoxAllData.get(i);
                if (loginMemberEntity != null && "0".equals(loginMemberEntity.getCurrentNum())) {
                    return loginMemberEntity;
                }
            }
            return null;
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
            return null;
        }
    }

    public static void showMemberDialog(Activity activity2) {
        isRuanDengLu = false;
        activity = activity2;
        memberDialog = new Dialog(activity2, 2131952223);
        LinearLayout linearLayout = (LinearLayout) activity2.getLayoutInflater().inflate(2131493335, (ViewGroup) null);
        initMemberDialogView(activity2, linearLayout);
        memberDialog.setContentView(linearLayout);
        memberDialog.setCancelable(true);
        memberDialog.setCanceledOnTouchOutside(true);
        memberDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                FragmentOnResumeEvent fragmentOnResumeEvent = new FragmentOnResumeEvent(EventBusUtils.EVENT_MEMBER_INFO);
                fragmentOnResumeEvent.setType(FragmentOnResumeEvent.HOME);
                EventBusUtils.post(fragmentOnResumeEvent);
                FragmentOnResumeEvent fragmentOnResumeEvent2 = new FragmentOnResumeEvent(EventBusUtils.EVENT_MEMBER_INFO);
                fragmentOnResumeEvent2.setType(FragmentOnResumeEvent.HOME_TJ);
                EventBusUtils.post(fragmentOnResumeEvent2);
            }
        });
        memberDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                LoginMemberManager.dismissDialog();
            }
        });
        WindowManager.LayoutParams attributes = memberDialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = UIUtils.dip2px(340.0f);
        Window window = memberDialog.getWindow();
        window.setGravity(80);
        window.setAttributes(attributes);
        window.setWindowAnimations(2131952222);
        isShow = true;
        memberDialog.show();
    }

    public static void dismissDialog() {
        try {
            if (isShow && activity != null && !activity.isFinishing() && !activity.isDestroyed() && memberDialog != null && memberDialog.isShowing()) {
                memberDialog.dismiss();
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "软登录取消弹窗异常:" + e.getMessage());
        }
        isShow = false;
    }

    private static void initMemberDialogView(final Activity activity2, View view) {
        isRuanDengLu = false;
        ((LinearLayout) view.findViewById(2131298050)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                LoginMemberManager.dismissDialog();
                Activity activity3 = activity2;
                activity3.startActivity(new Intent(activity3, LoginBindActivity.class));
                StatisticsUploadUtils.upload("1107", "首页-卡片-dialog", "按钮", "", "其它账号登录", "");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(2131298049);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity2, 1, false));
        MemberItemAdapter memberItemAdapter = new MemberItemAdapter(activity2);
        recyclerView.setAdapter(memberItemAdapter);
        memberItemAdapter.notifyDataSetChanged();
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(2131298854);
        recyclerView2.setLayoutManager(new LinearLayoutManager(activity2, 1, false));
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(activity2);
        recyclerView2.setAdapter(tuiJianAdapter);
        tuiJianAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class MemberItemAdapter extends RecyclerView.Adapter<MemberItemViewHolder> {
        private Activity activityContext;
        private List<LoginMemberEntity> list;

        public MemberItemAdapter(Activity activity) {
            this.activityContext = activity;
            this.list = LoginMemberManager.getMemberData(activity);
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public MemberItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MemberItemViewHolder(this.activityContext.getLayoutInflater().inflate(2131493337, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull MemberItemViewHolder memberItemViewHolder, final int i) {
            final LoginMemberEntity loginMemberEntity;
            List<LoginMemberEntity> list = this.list;
            if (list == null || list.size() <= i || (loginMemberEntity = this.list.get(i)) == null) {
                return;
            }
            memberItemViewHolder.numText.setText(loginMemberEntity.getNum());
            if (!TextUtils.isEmpty(loginMemberEntity.getTypeName())) {
                memberItemViewHolder.typeText.setVisibility(0);
                memberItemViewHolder.typeText.setText(loginMemberEntity.getTypeName());
            } else {
                memberItemViewHolder.typeText.setVisibility(4);
            }
            if (TextUtils.equals("0", loginMemberEntity.getCurrentNum())) {
                memberItemViewHolder.imageView.setImageResource(2131231958);
            } else {
                memberItemViewHolder.imageView.setImageResource(2131231959);
            }
            if (TextUtils.equals("custom_number", loginMemberEntity.getType())) {
                memberItemViewHolder.imageView.setVisibility(4);
            } else {
                memberItemViewHolder.imageView.setVisibility(0);
            }
            memberItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.MemberItemAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (!TextUtils.equals("custom_number", loginMemberEntity.getType())) {
                        LoginMemberManager.isRuanDengLu = true;
                        LoginMemberManager.memberLogout(MemberItemAdapter.this.activityContext, loginMemberEntity);
                        StatisticsUploadUtils.upload("1107", "首页-卡片-dialog", "按钮", "", "" + i, loginMemberEntity.getTypeName());
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<LoginMemberEntity> list = this.list;
            if (list == null) {
                return 0;
            }
            return list.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class MemberItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView numText;
        public TextView typeText;

        public MemberItemViewHolder(@NonNull View view) {
            super(view);
            this.numText = (TextView) view.findViewById(2131298047);
            this.typeText = (TextView) view.findViewById(2131298048);
            this.imageView = (ImageView) view.findViewById(2131298046);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class TuiJianAdapter extends RecyclerView.Adapter<TuiJianItemViewHolder> {
        private List<LoginTuiJianEntity> list = LoginMemberManager.getLoginTuiJianBoxAllData();
        private Activity mContext;

        public TuiJianAdapter(Activity activity) {
            this.mContext = activity;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public TuiJianItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new TuiJianItemViewHolder(this.mContext.getLayoutInflater().inflate(2131493191, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull TuiJianItemViewHolder tuiJianItemViewHolder, int i) {
            final LoginTuiJianEntity loginTuiJianEntity;
            try {
                if (this.list == null || this.list.size() <= i || (loginTuiJianEntity = this.list.get(i)) == null) {
                    return;
                }
                if (!TextUtils.isEmpty(loginTuiJianEntity.getShowTab())) {
                    tuiJianItemViewHolder.tuijian.setText(loginTuiJianEntity.getShowTab());
                    tuiJianItemViewHolder.tuijian.setTextColor(loginTuiJianEntity.getTextColor());
                    if (loginTuiJianEntity.getTextColor() == -2872014) {
                        tuiJianItemViewHolder.tuijian.setBackgroundResource(2131231897);
                    }
                    if (loginTuiJianEntity.getTextColor() == -12483108) {
                        tuiJianItemViewHolder.tuijian.setBackgroundResource(2131231898);
                    }
                }
                if (!TextUtils.isEmpty(loginTuiJianEntity.getTag()) && TextUtils.equals("1", loginTuiJianEntity.getTag()) && !TextUtils.isEmpty(loginTuiJianEntity.getProductName()) && loginTuiJianEntity.getProductName().length() > 4) {
                    try {
                        String productName = loginTuiJianEntity.getProductName();
                        String substring = productName.substring(productName.length() - 4, productName.length());
                        String substring2 = productName.substring(0, productName.length() - 4);
                        TextView textView = tuiJianItemViewHolder.textView;
                        textView.setText(Html.fromHtml("<font color='#333333'>" + substring2 + "</font><strong><font color='#E60027'>" + substring + "</font></strong>"));
                    } catch (Exception e) {
                        tuiJianItemViewHolder.textView.setText(loginTuiJianEntity.getProductName());
                        UIUtils.logE(e.getMessage());
                    }
                } else {
                    tuiJianItemViewHolder.textView.setText(loginTuiJianEntity.getProductName());
                }
                tuiJianItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.TuiJianAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        try {
                            IntentManager.gotoWebViewActivity(TuiJianAdapter.this.mContext, loginTuiJianEntity.getProductRedirecturl(), loginTuiJianEntity.getProductName());
                            StatisticsUploadUtils.upload("1107", "首页软登陆-推荐点击", "按钮", LoginMemberManager.getTouchCode(loginTuiJianEntity.getTextColor()), loginTuiJianEntity.getProductName(), loginTuiJianEntity.getProductRedirecturl());
                            LoginMemberManager.dismissDialog();
                        } catch (Exception e2) {
                            UIUtils.logE(e2.getMessage());
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } catch (Exception e2) {
                UIUtils.logE(e2.getMessage());
            }
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            try {
                int size = this.list.size();
                if (size <= 2) {
                    return size;
                }
                return 2;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class TuiJianItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView tuijian;

        public TuiJianItemViewHolder(@NonNull View view) {
            super(view);
            this.textView = (TextView) view.findViewById(2131298852);
            this.tuijian = (TextView) view.findViewById(2131298853);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void memberLogout(final Activity activity2, final LoginMemberEntity loginMemberEntity) {
        final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(activity2);
        customePorgressDialog.setMessage("正在登录 请稍候");
        customePorgressDialog.setCanceledOnTouchOutside(false);
        customePorgressDialog.setCancelable(false);
        final String userAccountName = UserManager.getInstance().getUserAccountName();
        final String onlineToken = UserManager.getInstance().getOnlineToken(userAccountName);
        MsLogUtil.m7980d("成员号码切换登录 currentPhoneNumber=" + userAccountName + "  tokenOnline=" + onlineToken);
        mErrorCode = "";
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", activity2.getString(2131886969));
        requestParams.put("desmobile", UserManager.getInstance().getPassBackDesmobile());
        requestParams.put("token_online", onlineToken);
        App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.4
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                UIUtils.showDialog(activity2, customePorgressDialog);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                LoginMemberManager.memberLogin(activity2, loginMemberEntity, userAccountName, onlineToken, customePorgressDialog);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void memberLogin(Activity activity2, LoginMemberEntity loginMemberEntity, String str, String str2, CustomePorgressDialog customePorgressDialog) {
        App.setLogined(LoginStateConst.UNLOGIN);
        LoginManager.logout(activity2);
        UserManager.getInstance().saveAutoLoginStatus(true);
        App.clearPersistentCookiesList();
        UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
        HashMap hashMap = new HashMap();
        hashMap.put("encryption", loginMemberEntity.getEncryption());
        hashMap.put("token_online", str2);
        hashMap.put("userType", loginMemberEntity.getType());
        hashMap.put("appId", UserManager.getInstance().getLoginAppId());
        hashMap.put("isRemberPwd", String.valueOf(true));
        hashMap.put("keyVersion", UserManager.getInstance().getKeyVersion());
        hashMap.put("version", activity2.getString(2131886969));
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
        hashMap.put(activity2.getString(2131886495), DeviceHelper.getAndroidId());
        hashMap.put("netWay", DeviceHelper.getNETType(activity2.getApplicationContext()));
        hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
        hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
        hashMap.put("deviceOS", DeviceHelper.getDeviceOSVersion());
        hashMap.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        if (DeviceHelper.getNeedHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
            try {
                hashMap.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
                hashMap.put("platformToken", App.getSharePreferenceUtil().getString("platformToken"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            hashMap.put("provinceChanel", activity2.getPackageManager().getApplicationInfo(activity2.getPackageName(), 128).metaData.getString("CHANNEL_ID"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            hashMap.put("jumpOver", "true");
        }
        hashMap.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
        loginHttp(activity2, hashMap, loginMemberEntity, str, str2, customePorgressDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loginHttp(final Activity activity2, final Map<String, String> map, final LoginMemberEntity loginMemberEntity, final String str, final String str2, final CustomePorgressDialog customePorgressDialog) {
        if (!TextUtils.isEmpty(LoginFilterUtil.resultToken)) {
            map.put("resultToken", LoginFilterUtil.resultToken);
        }
        LoginParamsEntity headerAndBody = DongtaiMiyaoUtils.getHeaderAndBody("0", map);
        App.getAsyncHttpClient().rxPost(URLSet.getMemberLoginUrl(), headerAndBody.getBodyMap(), headerAndBody.getHeaderMap(), 1, 0).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.5
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                App.setLogined(LoginStateConst.DOING_NORMAL_LOGIN);
                UIUtils.showDialog(activity2, customePorgressDialog);
            }

            @Override // io.reactivex.Observer
            public void onNext(String str3) {
                String str4;
                UIUtils.dismissDialog(activity2, customePorgressDialog);
                if (LoginFilterUtil.filerLogin(activity2, str3, new LoginFilterUtil.CallBackInterface() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.5.1
                    @Override // com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.CallBackInterface
                    public void complete(String str5) {
                        map.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                        map.put("resultToken", str5);
                        LoginMemberManager.loginHttp(activity2, map, loginMemberEntity, str, str2, customePorgressDialog);
                    }
                })) {
                    LoginMemberManager.dismissDialog();
                    return;
                }
                MsLogUtil.m7980d("成员号码切换登录：" + str3);
                try {
                    HashMap<String, String> handleLoginResponse = LoginManager.handleLoginResponse("", str3, "Login_Type");
                    UserManager.getInstance().getUserAccountName();
                    String str5 = handleLoginResponse.get("ok");
                    if (str5 != null && "ok".equals(str5)) {
                        if (TextUtils.equals("1", handleLoginResponse.get("loginPull"))) {
                            String str6 = handleLoginResponse.get("loginType");
                            String str7 = handleLoginResponse.get("desmobile");
                            LoginManager.logout(activity2);
                            if (TextUtils.equals("01", str6)) {
                                LoginActivity.startMobileIntent(activity2, str7);
                            } else if (TextUtils.equals("02", str6)) {
                                LoginActivity.startGuHuaIntent(activity2, str7, UserManager.getInstance().getUserAreaid());
                            } else if (TextUtils.equals("03", str6)) {
                                LoginActivity.startKuanDaiIntent(activity2, str7, UserManager.getInstance().getUserAreaid());
                            }
                        } else {
                            App.setLogined(LoginStateConst.DID_LOGIN);
                            UserManager.getInstance().saveAutoLoginStatus(true);
                            App.reLoadDefaultMenuData = true;
                            App.reLoadAdvertise = true;
                            WebFragment.isRelogin = true;
                            String str8 = handleLoginResponse.get("description");
                            if (!TextUtils.isEmpty(str8)) {
                                UIUtils.toastLong(str8);
                            }
                            EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_START_WEBSOCKET));
                            try {
                                UnicomPayUtils.getInstance(activity2).loginPaySdk();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            boolean z = App.getSharePreferenceUtil().getBoolean("isAllowNotification");
                            if (DeviceHelper.getNeedHuawei()) {
                                PushManager.getInstance().pushSwitch(activity2, z);
                            }
                        }
                    } else {
                        UserManager.getInstance().removeUserTouxiangURL();
                        App.setLogined(LoginStateConst.UNLOGIN);
                        String str9 = handleLoginResponse.get("exception");
                        String str10 = TextUtils.isEmpty(handleLoginResponse.get("errorCode")) ? "SERVER CODE IS NULL" : handleLoginResponse.get("errorCode");
                        if (TextUtils.isEmpty(handleLoginResponse.get("description"))) {
                            str4 = "很抱歉，暂时无法使用，请您稍候再试(code=" + str10 + ")";
                        } else {
                            str4 = handleLoginResponse.get("description");
                        }
                        if (TextUtils.isEmpty(str9)) {
                            if (str10.trim().equals("2")) {
                                UserManager.getInstance().removeUserPassword();
                                UserManager.getInstance().removeKeyVersion();
                            }
                            if ("ECS10002".equals(str10)) {
                                LoginMemberManager.mErrorCode = "ECS10002";
                                CustomDialogManager.showZhuXiao(SoulPermission.getInstance().getTopActivity(), "号码已注销", LoginConstUtil.ZHUXIAOMSG, true, "取消", "立即激活", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.5.2
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
                                        char c;
                                        HashMap hashMap = new HashMap();
                                        String str11 = "01";
                                        String type = loginMemberEntity.getType();
                                        int hashCode = type.hashCode();
                                        if (hashCode != 1477725) {
                                            if (hashCode == 1477756 && type.equals("0040")) {
                                                c = 0;
                                            }
                                            c = 65535;
                                        } else {
                                            if (type.equals("0030")) {
                                                c = 1;
                                            }
                                            c = 65535;
                                        }
                                        switch (c) {
                                            case 0:
                                                str11 = "03";
                                                break;
                                            case 1:
                                                str11 = "02";
                                                break;
                                        }
                                        hashMap.put("type", str11);
                                        IntentManager.gotoWebViewActivityWithParams(activity2, URLSet.getFreezeHtml(), "", hashMap);
                                        LoginMemberManager.dismissDialog();
                                    }
                                });
                            } else {
                                CustomDialogManager.show(activity2, "", str4);
                            }
                        } else {
                            throw new RuntimeException("接口数据解析异常[" + str9 + "]");
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    App.setLogined(LoginStateConst.UNLOGIN);
                    LoginManager.showLoginErrorMessage(activity2, "未登录成功，请重试【ECS0002】", true, "未登录成功，请重试【ECS0002】\n接口返状态码=200，数据处理有问题，需查询服务器日志定位问题！", false);
                    LogFileUploadUtils.writeLoginErrorLog(activity2, loginMemberEntity.getEncryption(), "微信登录", "", str3, e2);
                }
                if (!"ECS10002".equals(LoginMemberManager.mErrorCode)) {
                    LoginMemberManager.dismissDialog();
                }
                LogFileUploadUtils.upload(activity2);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                UIUtils.dismissDialog(activity2, customePorgressDialog);
                MsLogUtil.m7978e("成员号码切换登录：" + th.getMessage());
                App.setLogined(LoginStateConst.UNLOGIN);
                LogFileUploadUtils.writeLoginErrorLog(activity2, loginMemberEntity.getEncryption(), "成员号码切换登录", "", th.getMessage(), th);
                if (th instanceof ErrorStatusCodeException) {
                    ErrorStatusCodeException errorStatusCodeException = (ErrorStatusCodeException) th;
                    LoginManager.showLoginErrorMessage(activity2, "未登录成功，请重试【ECS" + errorStatusCodeException.statusCode + "】", true, "未登录成功，请重试【ECS" + errorStatusCodeException.statusCode + "】\n接口状态码=" + errorStatusCodeException.statusCode, false);
                } else {
                    LoginManager.showLoginNetWorkErrorMessage(activity2, th, true);
                }
                LogFileUploadUtils.upload(activity2);
                LoginMemberManager.dismissDialog();
            }
        });
    }

    public static Observable loadLoginTuiJainData() {
        clearTuiJianData();
        return Observable.create(new ObservableOnSubscribe<Boolean>() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.6
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                boolean z = false;
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("height", "540");
                    hashMap.put("width", "160");
                    String syncPost = App.getAsyncHttpClient().syncPost(URLSet.getTuiJianData(), hashMap);
                    UIUtils.logD("tuijian 智慧推荐 = ", syncPost);
                    if (!TextUtils.isEmpty(syncPost)) {
                        JSONObject jSONObject = new JSONObject(syncPost);
                        if (TextUtils.equals("0000", jSONObject.optString("code"))) {
                            ArrayList arrayList = new ArrayList();
                            JSONObject optJSONObject = jSONObject.optJSONObject("data");
                            arrayList.addAll(LoginMemberManager.getTuijianList(optJSONObject.optJSONArray("1000230055"), -2872014));
                            arrayList.addAll(LoginMemberManager.getTuijianList(optJSONObject.optJSONArray("1000230056"), -12483108));
                            LoginMemberManager.saveTuiJianData(arrayList);
                            z = true;
                            observableEmitter.onNext(true);
                        }
                    }
                } catch (Exception e) {
                    UIUtils.logD(LoginMemberManager.TAG, e.getMessage());
                }
                if (!z) {
                    observableEmitter.onNext(Boolean.valueOf(z));
                }
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m1934io());
    }

    public static List<LoginTuiJianEntity> getTuijianList(JSONArray jSONArray, int i) {
        String str;
        String str2;
        JSONObject optJSONObject;
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject optJSONObject2 = jSONArray.optJSONObject(i2);
                String optString = optJSONObject2.optString("productName");
                String optString2 = optJSONObject2.optString("productUrl");
                String optString3 = optJSONObject2.optString("productRedirecturl");
                JSONArray optJSONArray = optJSONObject2.optJSONArray("goodsVo");
                if (optJSONArray == null || optJSONArray.length() <= 0 || (optJSONObject = optJSONArray.optJSONObject(0)) == null) {
                    str = "";
                    str2 = "";
                } else {
                    String optString4 = optJSONObject.optString("showTab");
                    str2 = optJSONObject.optString("tag");
                    str = optString4;
                }
                arrayList.add(new LoginTuiJianEntity(optString3, optString, optString2, i, str, str2));
            }
        }
        return arrayList;
    }

    public static Observable loadLoginMemberData(final Activity activity2) {
        clearMemberData();
        return Observable.create(new ObservableOnSubscribe<Boolean>() { // from class: com.sinovatech.unicom.basic.server.LoginMemberManager.7
            /* JADX WARN: Removed duplicated region for block: B:44:0x0258  */
            @Override // io.reactivex.ObservableOnSubscribe
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void subscribe(io.reactivex.ObservableEmitter<java.lang.Boolean> r18) throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 611
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.server.LoginMemberManager.C71547.subscribe(io.reactivex.ObservableEmitter):void");
            }
        }).subscribeOn(Schedulers.m1934io());
    }

    public static String Phone(String str) {
        try {
            return str.substring(0, 3) + "****" + str.substring(str.length() - 4, str.length());
        } catch (Exception unused) {
            if (str.length() >= 11 || str.length() <= 1) {
                return "";
            }
            return str.substring(0, 1) + "****";
        }
    }
}
