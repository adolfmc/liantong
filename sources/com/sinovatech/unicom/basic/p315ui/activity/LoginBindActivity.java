package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.utils.LoginConstUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.ErrorStatusCodeException;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.basic.eventbus.YWEvent;
import com.sinovatech.unicom.basic.p314po.BindNumberActivityEntity;
import com.sinovatech.unicom.basic.p314po.BindNumberActivityEntity_;
import com.sinovatech.unicom.basic.p314po.CitySelectEntity;
import com.sinovatech.unicom.basic.p314po.LoginAccountEntity;
import com.sinovatech.unicom.basic.p315ui.FingerDialogManager;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerHomeOMO;
import com.sinovatech.unicom.basic.p315ui.view.SwipeLayout;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.MeasureListView;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.FingerPrintUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.DeviceInfoStatistics;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhognxinCheckStart;
import com.sinovatech.unicom.separatemodule.anquanzhongxin.LockScreenManager;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.LoginParamsEntity;
import com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.security.LockPatternUtil;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginBindActivity extends BaseActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private BindAccountListAdapter adapter;
    private AnquanzhognxinCheckStart anquanzhognxin;
    private ImageView backButton;
    private List<LoginAccountEntity> bindAccountList;
    private Box<BindNumberActivityEntity> bnaBox;
    private List<CitySelectEntity> citylist;
    private ConfigManager configManager;
    private Activity context;
    private String diffType;
    private boolean directAccess;
    private TextView editText;
    private int fingerCount;
    private FingerDialogManager fingerDialogManager;
    private String fromeWelcome;
    private String fromeYiwang;
    private String intentBackMode;
    private String intentEntranceURL;
    private String intentMenuId;
    private String intentRequestType;
    private String intentTitle;
    private RelativeLayout loginBindLayout;
    private ImageView loginIngImageview;
    private LinearLayout loginIngLaoyut;
    private TextView loginIngTextview;
    private MeasureListView login_bind_listview;
    private LinearLayout login_go_layout;
    private FingerPrintUtils mFingerUtils;
    private boolean needLogout;
    private RequestOptions optionsPhoto;
    private String paygreenUrl;

    /* renamed from: pd */
    private ProgressDialog f18403pd;
    private SharePreferenceUtil preference;
    private Button registerButton;
    private LoginAccountEntity selectedAccount;
    private ImageView tipImage;
    private LinearLayout tipImageLayout;
    private TextView tipText;
    private UserManager userManager;
    private final String FROMEWELCOME = "WelcomeClient";
    private final String FROMEYIWANG = "Yiwangwebview";
    private int haveRetryNum = 0;
    private boolean needBackgroudLogin = false;
    private StringBuffer errorLogBuffer = new StringBuffer();
    private Random random = new Random();
    private List<SwipeLayout> openList = new ArrayList();
    final int BUFFER_SIZE = 4096;
    final String CITY_JSON_FILENAME = "city_select_json.json";

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 53);
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

    static /* synthetic */ String access$1800(LoginBindActivity loginBindActivity) {
        return loginBindActivity.fromeWelcome;
    }

    static /* synthetic */ boolean access$1900(LoginBindActivity loginBindActivity) {
        return loginBindActivity.directAccess;
    }

    static /* synthetic */ String access$2000(LoginBindActivity loginBindActivity) {
        return loginBindActivity.intentEntranceURL;
    }

    static /* synthetic */ String access$2100(LoginBindActivity loginBindActivity) {
        return loginBindActivity.paygreenUrl;
    }

    static /* synthetic */ String access$2200(LoginBindActivity loginBindActivity) {
        return loginBindActivity.intentTitle;
    }

    static /* synthetic */ String access$2300(LoginBindActivity loginBindActivity) {
        return loginBindActivity.intentMenuId;
    }

    static /* synthetic */ String access$2400(LoginBindActivity loginBindActivity) {
        return loginBindActivity.intentBackMode;
    }

    static /* synthetic */ String access$2500(LoginBindActivity loginBindActivity) {
        return loginBindActivity.intentRequestType;
    }

    static /* synthetic */ boolean access$2600(LoginBindActivity loginBindActivity) {
        return loginBindActivity.needBackgroudLogin;
    }

    static /* synthetic */ String access$2700(LoginBindActivity loginBindActivity) {
        return loginBindActivity.diffType;
    }

    static /* synthetic */ int access$3008(LoginBindActivity loginBindActivity) {
        int i = loginBindActivity.fingerCount;
        loginBindActivity.fingerCount = i + 1;
        return i;
    }

    static /* synthetic */ void access$3500(LoginBindActivity loginBindActivity) {
        loginBindActivity.pdDissmiss();
    }

    static /* synthetic */ int access$3600(LoginBindActivity loginBindActivity) {
        return loginBindActivity.haveRetryNum;
    }

    static /* synthetic */ StringBuffer access$3700(LoginBindActivity loginBindActivity) {
        return loginBindActivity.errorLogBuffer;
    }

    static /* synthetic */ void access$3800(LoginBindActivity loginBindActivity) {
        loginBindActivity.directAccess();
    }

    static /* synthetic */ Activity access$600(LoginBindActivity loginBindActivity) {
        return loginBindActivity.context;
    }

    static /* synthetic */ UserManager access$900(LoginBindActivity loginBindActivity) {
        return loginBindActivity.userManager;
    }

    private void handleIntentArgs() {
        this.needLogout = getIntent().getBooleanExtra("needLogout", false);
        this.directAccess = getIntent().getBooleanExtra("directAccess", false);
        this.intentEntranceURL = getIntent().getStringExtra("url");
        this.paygreenUrl = getIntent().getStringExtra("paygreenUrl");
        this.intentTitle = getIntent().getStringExtra("title");
        this.intentMenuId = getIntent().getStringExtra("menuId");
        this.intentBackMode = getIntent().getStringExtra("backMode");
        this.intentRequestType = getIntent().getStringExtra("requestType");
        this.needBackgroudLogin = getIntent().getBooleanExtra("loginFlag", false);
        this.diffType = getIntent().getStringExtra("diffType");
        if ("2".equals(this.diffType)) {
            UIUtils.toast("该业务仅限移动、电信用户使用，请使用移动、电信号码登录");
        }
        this.fromeWelcome = getIntent().getStringExtra("WelcomeClient");
        this.fromeYiwang = getIntent().getStringExtra("Yiwangwebview");
        String stringExtra = getIntent().getStringExtra("needDialog");
        String stringExtra2 = getIntent().getStringExtra("desc");
        if (!"1".equals(stringExtra) || TextUtils.isEmpty(stringExtra2)) {
            return;
        }
        CustomDialogManager.show(this.context, "温馨提示", stringExtra2);
    }

    private void initUiElements() {
        this.backButton = (ImageView) findViewById(2131297825);
        this.backButton.setOnClickListener(this);
        this.registerButton = (Button) findViewById(2131297907);
        this.registerButton.setOnClickListener(this);
        this.login_go_layout = (LinearLayout) findViewById(2131297867);
        this.login_go_layout.setOnClickListener(this);
        this.login_bind_listview = (MeasureListView) findViewById(2131297826);
        this.tipText = (TextView) findViewById(2131297827);
        this.tipImage = (ImageView) findViewById(2131297972);
        this.tipImageLayout = (LinearLayout) findViewById(2131297973);
        this.editText = (TextView) findViewById(2131297975);
        this.editText.setOnClickListener(this);
        this.bindAccountList = new ArrayList();
        this.adapter = new BindAccountListAdapter();
        this.login_bind_listview.setAdapter((ListAdapter) this.adapter);
        this.loginBindLayout = (RelativeLayout) findViewById(2131297974);
        this.loginIngLaoyut = (LinearLayout) findViewById(2131297897);
        this.loginIngTextview = (TextView) findViewById(2131297898);
        this.loginIngImageview = (ImageView) findViewById(2131297896);
        String loginImage = this.configManager.getLoginImage();
        if (!TextUtils.isEmpty(loginImage)) {
            this.tipImage.setVisibility(0);
            this.tipImageLayout.setVisibility(0);
            this.tipText.setVisibility(8);
            if (loginImage.endsWith("gif")) {
                Glide.with(this.context).asGif().load(this.configManager.getLoginImage()).into(this.tipImage);
                return;
            } else {
                Glide.with(this.context).asDrawable().load(this.configManager.getLoginImage()).into(this.tipImage);
                return;
            }
        }
        this.tipImage.setVisibility(8);
        this.tipImageLayout.setVisibility(8);
        this.tipText.setVisibility(0);
        if (TextUtils.isEmpty(this.configManager.getLoginText())) {
            if ("on".equals(LoginConfigDataCenter.getInstance().getEntity().getBroadLoginSwitch())) {
                this.tipText.setText("支持手机、宽带、固话或身份证登录");
            } else {
                this.tipText.setText("支持手机、宽带或固话登录");
            }
            this.tipText.setTextColor(-7895161);
            return;
        }
        this.tipText.setText(this.configManager.getLoginText());
        this.tipText.setTextColor(this.configManager.getLoginTextColor());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131297825) {
            finish();
        } else if (id == 2131297867) {
            Intent intent = new Intent(this.context, LoginActivity.class);
            intent.putExtra("from", "LoginBindActivity");
            intent.putExtra("directAccess", this.directAccess);
            intent.putExtra("url", this.intentEntranceURL);
            intent.putExtra("paygreenUrl", this.paygreenUrl);
            intent.putExtra("title", this.intentTitle);
            intent.putExtra("menuId", this.intentMenuId);
            intent.putExtra("backMode", this.intentBackMode);
            intent.putExtra("requestType", this.intentRequestType);
            intent.putExtra("loginFlag", this.needBackgroudLogin);
            intent.putExtra("diffType", this.diffType);
            intent.putExtra("WelcomeClient", this.fromeWelcome);
            intent.putExtra("Yiwangwebview", this.fromeYiwang);
            startActivityForResult(intent, 1010);
        } else if (id == 2131297907) {
            StatisticsUploadUtils.upload(this.context, "6", "登录", "按钮", "", "注册", "");
            IntentManager.gotoWebViewActivity(this.context, URLSet.getRegisterURL(), "注册");
            this.context.finish();
        } else if (id == 2131297975) {
            if ("编辑".equals(this.editText.getText().toString())) {
                this.editText.setText("确定");
            } else {
                this.editText.setText("编辑");
            }
            this.adapter.notifyDataSetChanged();
            if (this.bindAccountList.size() == 0) {
                this.loginBindLayout.setVisibility(8);
            } else {
                this.loginBindLayout.setVisibility(0);
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        List<LoginAccountEntity> bindAccountNameList = this.userManager.getBindAccountNameList("");
        if (App.hasLogined() && bindAccountNameList.size() > 0) {
            final boolean z = true;
            if (bindAccountNameList.size() == 1) {
                this.loginBindLayout.setVisibility(8);
            }
            String currentPhoneNumber = this.userManager.getCurrentPhoneNumber();
            final LoginAccountEntity loginAccountEntity = bindAccountNameList.get(0);
            if (!currentPhoneNumber.equals(loginAccountEntity.getAccountname())) {
                Iterator<LoginAccountEntity> it = bindAccountNameList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    LoginAccountEntity next = it.next();
                    if (currentPhoneNumber.equals(next.getAccountname())) {
                        loginAccountEntity = next;
                        break;
                    }
                }
            }
            this.loginIngLaoyut.setVisibility(0);
            this.loginIngTextview.setText("当前使用账号：" + currentPhoneNumber);
            final boolean z2 = this.anquanzhognxin.getOpenState(loginAccountEntity.getAccountname()) == 3;
            if (this.anquanzhognxin.getOpenState(loginAccountEntity.getAccountname()) != 1 || !LockPatternUtil.hasLockPattern(this.context, loginAccountEntity.getAccountname())) {
                z = false;
            }
            if (z2) {
                this.loginIngImageview.setVisibility(0);
                this.loginIngImageview.setImageResource(2131230834);
                this.loginIngImageview.setPadding(0, 0, 0, 0);
            } else if (z) {
                this.loginIngImageview.setVisibility(0);
                this.loginIngImageview.setImageResource(2131230846);
                this.loginIngImageview.setPadding(0, 0, 0, 0);
            } else {
                this.loginIngImageview.setVisibility(0);
                this.loginIngImageview.setImageResource(2131232531);
                int dip2px = UIUtils.dip2px(2.0f);
                this.loginIngImageview.setPadding(dip2px, dip2px, dip2px, dip2px);
            }
            this.loginIngLaoyut.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    LoginBindActivity.this.startLogin(loginAccountEntity, z2, z);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } else {
            this.loginBindLayout.setVisibility(0);
            this.loginIngLaoyut.setVisibility(8);
        }
        updateBindNumListUI();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void updateBindNumListUI() {
        Observable.create(new ObservableOnSubscribe<List<LoginAccountEntity>>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.4
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<List<LoginAccountEntity>> observableEmitter) throws Exception {
                String accountname;
                LoginBindActivity loginBindActivity = LoginBindActivity.this;
                loginBindActivity.citylist = loginBindActivity.getCityList(loginBindActivity.readCityFile(loginBindActivity.context));
                List<LoginAccountEntity> bindAccountNameList = LoginBindActivity.this.userManager.getBindAccountNameList("");
                for (LoginAccountEntity loginAccountEntity : bindAccountNameList) {
                    if ("02".equals(loginAccountEntity.getAccouttype())) {
                        accountname = loginAccountEntity.getIntact();
                        if (TextUtils.isEmpty(accountname)) {
                            accountname = loginAccountEntity.getAccountname();
                        }
                    } else {
                        accountname = loginAccountEntity.getAccountname();
                    }
                    List find = LoginBindActivity.this.bnaBox.query().equal(BindNumberActivityEntity_.mobile, accountname).build().find();
                    if (find != null && find.size() > 0) {
                        loginAccountEntity.setActivityEntity((BindNumberActivityEntity) find.get(0));
                    }
                }
                observableEmitter.onNext(LoginBindActivity.this.sortBindList(bindAccountNameList));
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<LoginAccountEntity>>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.2
            @Override // io.reactivex.functions.Consumer
            public void accept(List<LoginAccountEntity> list) throws Exception {
                LoginBindActivity.this.bindAccountList = list;
                LoginBindActivity.this.adapter.notifyDataSetChanged();
                if (LoginBindActivity.this.bindAccountList.size() == 0) {
                    LoginBindActivity.this.loginBindLayout.setVisibility(8);
                } else {
                    LoginBindActivity.this.loginBindLayout.setVisibility(0);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<LoginAccountEntity> sortBindList(List<LoginAccountEntity> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            try {
                LoginAccountEntity loginAccountEntity = list.get(i);
                if (!"error".equals(loginAccountEntity.getKeyversion()) && !TextUtils.isEmpty(loginAccountEntity.getPassword()) && (!App.hasLogined() || !loginAccountEntity.getAccountname().equals(this.userManager.getCurrentPhoneNumber()))) {
                    arrayList.add(loginAccountEntity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            LoginAccountEntity loginAccountEntity2 = list.get(i2);
            if (!"error".equals(loginAccountEntity2.getKeyversion()) && TextUtils.isEmpty(loginAccountEntity2.getPassword())) {
                String accountname = loginAccountEntity2.getAccountname();
                loginAccountEntity2.setAccountnameJiami(accountname.substring(0, 3) + "****" + accountname.substring(accountname.length() - 4, accountname.length()));
                arrayList.add(loginAccountEntity2);
            }
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            LoginAccountEntity loginAccountEntity3 = list.get(i3);
            if ("error".equals(loginAccountEntity3.getKeyversion())) {
                String accountname2 = loginAccountEntity3.getAccountname();
                loginAccountEntity3.setAccountnameJiami(accountname2.substring(0, 3) + "****" + accountname2.substring(accountname2.length() - 4, accountname2.length()));
                arrayList.add(loginAccountEntity3);
            }
        }
        return arrayList;
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            finish();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void goMain() {
        IntentManager.intentFilter(this);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1010 && i2 == -1) {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void directAccess() {
        if (this.userManager.isYiwang()) {
            EventBusUtils.post(new YWEvent(EventBusUtils.EVENT_YW_lOGIN));
        } else {
            EventBusUtils.post(new YWEvent(EventBusUtils.EVENT_YW_lOGOUT));
        }
        if (this.directAccess && !TextUtils.isEmpty(this.intentEntranceURL) && !IntentManager.handleLocal(this, this.intentTitle, this.intentEntranceURL)) {
            IntentManager.gotoWebViewActivity(this.context, this.intentEntranceURL, this.intentTitle, this.intentBackMode, this.intentRequestType);
        }
        setResult(-1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity$BindAccountListAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BindAccountListAdapter extends BaseAdapter {
        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        private BindAccountListAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return LoginBindActivity.this.bindAccountList.size();
        }

        @Override // android.widget.Adapter
        public LoginAccountEntity getItem(int i) {
            return (LoginAccountEntity) LoginBindActivity.this.bindAccountList.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            int i2;
            final SwipeLayout swipeLayout = (SwipeLayout) LayoutInflater.from(LoginBindActivity.this.context).inflate(2131493331, viewGroup, false);
            TextView textView = (TextView) swipeLayout.findViewById(2131297833);
            CircularImage circularImage = (CircularImage) swipeLayout.findViewById(2131297830);
            TextView textView2 = (TextView) swipeLayout.findViewById(2131297832);
            final ImageButton imageButton = (ImageButton) swipeLayout.findViewById(2131297829);
            ImageView imageView = (ImageView) swipeLayout.findViewById(2131297828);
            ImageView imageView2 = (ImageView) swipeLayout.findViewById(2131297835);
            TextView textView3 = (TextView) swipeLayout.findViewById(2131297834);
            ImageView imageView3 = (ImageView) swipeLayout.findViewById(2131297977);
            TextView textView4 = (TextView) swipeLayout.findViewById(2131297979);
            LinearLayout linearLayout = (LinearLayout) swipeLayout.findViewById(2131297831);
            ImageView imageView4 = (ImageView) swipeLayout.findViewById(2131297978);
            final ImageView imageView5 = (ImageView) swipeLayout.findViewById(2131297976);
            ((TextView) swipeLayout.findViewById(2131297939)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginBindActivity$BindAccountListAdapter$qhXBhKeawadHTzHUARqVF32cJnU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    LoginBindActivity.BindAccountListAdapter.lambda$getView$0(imageButton, swipeLayout, view2);
                }
            });
            swipeLayout.setSwipeChangeListener(new SwipeLayout.OnSwipeChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.BindAccountListAdapter.1
                @Override // com.sinovatech.unicom.basic.p315ui.view.SwipeLayout.OnSwipeChangeListener
                public void onDraging(SwipeLayout swipeLayout2) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.view.SwipeLayout.OnSwipeChangeListener
                public void onStartClose(SwipeLayout swipeLayout2) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.view.SwipeLayout.OnSwipeChangeListener
                public void onStartOpen(SwipeLayout swipeLayout2) {
                    for (SwipeLayout swipeLayout3 : LoginBindActivity.this.openList) {
                        swipeLayout3.close();
                    }
                    LoginBindActivity.this.openList.clear();
                }

                @Override // com.sinovatech.unicom.basic.p315ui.view.SwipeLayout.OnSwipeChangeListener
                public void onOpen(SwipeLayout swipeLayout2) {
                    LoginBindActivity.this.openList.add(swipeLayout2);
                }

                @Override // com.sinovatech.unicom.basic.p315ui.view.SwipeLayout.OnSwipeChangeListener
                public void onClose(SwipeLayout swipeLayout2) {
                    LoginBindActivity.this.openList.remove(swipeLayout2);
                }
            });
            final LoginAccountEntity loginAccountEntity = (LoginAccountEntity) LoginBindActivity.this.bindAccountList.get(i);
            if (!TextUtils.isEmpty(loginAccountEntity.getActivityEntity().getUserNumPriture())) {
                imageView4.setVisibility(0);
                Glide.with(LoginBindActivity.this.context).load(loginAccountEntity.getActivityEntity().getUserNumPriture()).into(imageView4);
            } else {
                imageView4.setVisibility(8);
            }
            if (!TextUtils.isEmpty(loginAccountEntity.getActivityEntity().getProductUrl())) {
                if (!TextUtils.isEmpty(loginAccountEntity.getActivityEntity().getUpdateTime()) && !loginAccountEntity.getActivityEntity().getUpdateTime().equals(loginAccountEntity.getActivityEntity().getClickTimeForCheckUpdated())) {
                    imageView5.setVisibility(0);
                    if (loginAccountEntity.getActivityEntity().getProductUrl().endsWith("gif")) {
                        Glide.with(LoginBindActivity.this.context).asGif().load(loginAccountEntity.getActivityEntity().getProductUrl()).into(imageView5);
                    } else {
                        Glide.with(LoginBindActivity.this.context).asDrawable().load(loginAccountEntity.getActivityEntity().getProductUrl()).into(imageView5);
                    }
                } else {
                    imageView5.setVisibility(8);
                }
            } else {
                imageView5.setVisibility(8);
            }
            String newUserColorForBindList = TextUtils.isEmpty(loginAccountEntity.getActivityEntity().getUserNumColor()) ? LoginBindActivity.this.configManager.getNewUserColorForBindList() : loginAccountEntity.getActivityEntity().getUserNumColor();
            String oldUserColorForBindList = LoginBindActivity.this.configManager.getOldUserColorForBindList();
            try {
                Color.parseColor("#" + newUserColorForBindList);
                i2 = Color.parseColor("#" + oldUserColorForBindList);
            } catch (Exception e) {
                e.printStackTrace();
                i2 = -16777216;
            }
            try {
                textView.setTextColor(i2);
            } catch (Exception e2) {
                e2.printStackTrace();
                textView.setTextColor(i2);
            }
            if ("02".equals(loginAccountEntity.getAccouttype()) || "03".equals(loginAccountEntity.getAccouttype()) || "04".equals(loginAccountEntity.getAccouttype())) {
                if (!TextUtils.isEmpty(loginAccountEntity.getAreaid()) && !TextUtils.isEmpty(loginAccountEntity.getCid())) {
                    Iterator it = LoginBindActivity.this.citylist.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        CitySelectEntity citySelectEntity = (CitySelectEntity) it.next();
                        if (citySelectEntity.getCityCode().equals(loginAccountEntity.getAreaid())) {
                            textView4.setVisibility(0);
                            String provienceName = citySelectEntity.getProvienceName();
                            String cityName = citySelectEntity.getCityName();
                            if (cityName.equals(provienceName)) {
                                textView4.setText(provienceName);
                            } else {
                                textView4.setText(provienceName + "    " + cityName);
                            }
                        } else {
                            textView4.setVisibility(8);
                        }
                    }
                } else {
                    textView4.setVisibility(8);
                }
            } else {
                textView4.setVisibility(8);
            }
            boolean z = LoginBindActivity.this.anquanzhognxin.getOpenState(loginAccountEntity.getAccountname()) == 3;
            if (z) {
                imageView.setVisibility(0);
            } else {
                imageView.setVisibility(4);
            }
            boolean z2 = LoginBindActivity.this.anquanzhognxin.getOpenState(loginAccountEntity.getAccountname()) == 1 && LockPatternUtil.hasLockPattern(LoginBindActivity.this.context, loginAccountEntity.getAccountname());
            if (z2) {
                imageView2.setVisibility(0);
            } else {
                imageView2.setVisibility(8);
            }
            if (TextUtils.isEmpty(loginAccountEntity.getAccountnameJiami())) {
                textView.setText(loginAccountEntity.getAccountname());
            } else {
                textView.setText(loginAccountEntity.getAccountnameJiami());
            }
            if (!TextUtils.isEmpty(LoginBindActivity.this.userManager.getUserPhotoURL(loginAccountEntity.getAccountname()))) {
                Glide.with(LoginBindActivity.this.context).asDrawable().load(LoginBindActivity.this.userManager.getUserPhotoURL(loginAccountEntity.getAccountname())).apply((BaseRequestOptions<?>) LoginBindActivity.this.optionsPhoto).into(circularImage);
            } else {
                Glide.with(LoginBindActivity.this.context).asDrawable().load(loginAccountEntity.getIconurl()).apply((BaseRequestOptions<?>) LoginBindActivity.this.optionsPhoto).into(circularImage);
            }
            if ("编辑".equals(LoginBindActivity.this.editText.getText().toString())) {
                imageButton.setVisibility(8);
                imageView3.setVisibility(0);
            } else {
                imageButton.setVisibility(0);
                imageView3.setVisibility(8);
            }
            imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.BindAccountListAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    CustomDialogManager.show(LoginBindActivity.this.context, "提示", "是否确定删除该账号的绑定记录", true, "取消", "确定", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.BindAccountListAdapter.2.1
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

                        /* JADX WARN: Code restructure failed: missing block: B:11:0x004e, code lost:
                            r0 = r1.get(r3).getAreacode();
                         */
                        /* JADX WARN: Code restructure failed: missing block: B:21:0x00b1, code lost:
                            r6.this$2.this$1.this$0.bindAccountList.remove(r1);
                         */
                        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public void onClickOk() {
                            /*
                                Method dump skipped, instructions count: 269
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity.BindAccountListAdapter.View$OnClickListenerC74522.C74531.onClickOk():void");
                        }
                    });
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            final boolean z3 = z;
            final boolean z4 = z2;
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.BindAccountListAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    if ("编辑".equals(LoginBindActivity.this.editText.getText().toString())) {
                        if (!swipeLayout.isOpen()) {
                            LoginBindActivity.this.startLogin(loginAccountEntity, z3, z4);
                            if (imageView5.getVisibility() == 0) {
                                StatisticsUploadUtils.upload(LoginBindActivity.this.context, "131", "绑定列表-气泡", "按钮", loginAccountEntity.getActivityEntity().getProductName(), loginAccountEntity.getAccountname(), "");
                            }
                        } else {
                            swipeLayout.close(true);
                            NBSActionInstrumentation.onClickEventExit();
                            return;
                        }
                    } else {
                        imageButton.performClick();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            final boolean z5 = z;
            final boolean z6 = z2;
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.BindAccountListAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    LoginBindActivity.this.startLogin(loginAccountEntity, z5, z6);
                    if (imageView5.getVisibility() == 0) {
                        StatisticsUploadUtils.upload(LoginBindActivity.this.context, "131", "绑定列表-气泡", "按钮", loginAccountEntity.getActivityEntity().getProductName(), loginAccountEntity.getAccountname(), "");
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            return swipeLayout;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$getView$0(ImageButton imageButton, SwipeLayout swipeLayout, View view) {
            imageButton.performClick();
            swipeLayout.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLogin(final LoginAccountEntity loginAccountEntity, boolean z, boolean z2) {
        if (!z) {
            if (z2) {
                LockScreenManager.welcomeStart(this.context, loginAccountEntity.getAccountname(), new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.7
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        LoginBindActivity.this.login(loginAccountEntity);
                    }
                });
            } else {
                login(loginAccountEntity);
            }
        } else if (DeviceHelper.getSupportFinggerNum() != 0) {
            CustomDialogManager.show2(this.context, "与该账号绑定的指纹解锁已失效，请您重新登录。", new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.5
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    LoginBindActivity.this.anquanzhognxin.reset(loginAccountEntity.getAccountname());
                    LoginBindActivity.this.userManager.removeSelectAccountName(loginAccountEntity.getAccountname());
                    Intent intent = new Intent(LoginBindActivity.this.context, LoginActivity.class);
                    intent.putExtra("WelcomeClient", LoginBindActivity.this.fromeWelcome);
                    intent.putExtra("from", "LoginBindActivity");
                    intent.putExtra("account", loginAccountEntity.getAccountname());
                    intent.putExtra("logintype", loginAccountEntity.getAccouttype());
                    intent.putExtra("directAccess", LoginBindActivity.this.directAccess);
                    intent.putExtra("url", LoginBindActivity.this.intentEntranceURL);
                    intent.putExtra("paygreenUrl", LoginBindActivity.this.paygreenUrl);
                    intent.putExtra("title", LoginBindActivity.this.intentTitle);
                    intent.putExtra("menuId", LoginBindActivity.this.intentMenuId);
                    intent.putExtra("backMode", LoginBindActivity.this.intentBackMode);
                    intent.putExtra("requestType", LoginBindActivity.this.intentRequestType);
                    intent.putExtra("loginFlag", LoginBindActivity.this.needBackgroudLogin);
                    intent.putExtra("diffType", LoginBindActivity.this.diffType);
                    LoginBindActivity.this.startActivityForResult(intent, 1010);
                }
            });
        } else {
            startFinner(loginAccountEntity);
            this.fingerDialogManager = new FingerDialogManager(this.context);
            this.fingerDialogManager.show("温馨提示", "请验证已有的指纹，用于登录", true, new FingerDialogManager.FingerDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.6
                @Override // com.sinovatech.unicom.basic.p315ui.FingerDialogManager.FingerDialogListener
                public void onCancel() {
                    LoginBindActivity.this.mFingerUtils.stopsFingerPrintListener();
                    LoginBindActivity.this.fingerDialogManager.cancel();
                    LoginBindActivity.this.fingerCount = 0;
                }

                @Override // com.sinovatech.unicom.basic.p315ui.FingerDialogManager.FingerDialogListener
                public void onReset() {
                    Intent intent = new Intent(LoginBindActivity.this.context, LoginActivity.class);
                    intent.putExtra("from", "LoginBindActivity");
                    intent.putExtra("logintype", loginAccountEntity.getAccouttype());
                    intent.putExtra("WelcomeClient", LoginBindActivity.this.fromeWelcome);
                    intent.putExtra("directAccess", LoginBindActivity.this.directAccess);
                    intent.putExtra("url", LoginBindActivity.this.intentEntranceURL);
                    intent.putExtra("paygreenUrl", LoginBindActivity.this.paygreenUrl);
                    intent.putExtra("title", LoginBindActivity.this.intentTitle);
                    intent.putExtra("menuId", LoginBindActivity.this.intentMenuId);
                    intent.putExtra("backMode", LoginBindActivity.this.intentBackMode);
                    intent.putExtra("requestType", LoginBindActivity.this.intentRequestType);
                    intent.putExtra("loginFlag", LoginBindActivity.this.needBackgroudLogin);
                    intent.putExtra("diffType", LoginBindActivity.this.diffType);
                    LoginBindActivity.this.startActivityForResult(intent, 1010);
                    if (LoginBindActivity.this.mFingerUtils != null) {
                        LoginBindActivity.this.mFingerUtils.stopsFingerPrintListener();
                    }
                    LoginBindActivity.this.anquanzhognxin.reset(loginAccountEntity.getAccountname());
                    LoginBindActivity.this.userManager.removeSelectAccountName(loginAccountEntity.getAccountname());
                }
            });
        }
    }

    private void startFinner(LoginAccountEntity loginAccountEntity) {
        this.mFingerUtils = new FingerPrintUtils();
        this.mFingerUtils.setFingerPrintListener(new FingerCallBack(loginAccountEntity));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity$FingerCallBack */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class FingerCallBack extends FingerprintManagerCompat.AuthenticationCallback {
        private LoginAccountEntity phone;

        public FingerCallBack(LoginAccountEntity loginAccountEntity) {
            this.phone = loginAccountEntity;
        }

        @Override // android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
        public void onAuthenticationError(int i, CharSequence charSequence) {
            super.onAuthenticationError(i, charSequence);
            LoginBindActivity.this.fingerDialogManager.startShake();
            LoginBindActivity.this.fingerDialogManager.setTip("失败次数过多，短时间内不能调用指纹验证");
            LoginBindActivity.this.mFingerUtils.stopsFingerPrintListener();
        }

        @Override // android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
        public void onAuthenticationHelp(int i, CharSequence charSequence) {
            super.onAuthenticationHelp(i, charSequence);
        }

        @Override // android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult authenticationResult) {
            super.onAuthenticationSucceeded(authenticationResult);
            LoginBindActivity.this.login(this.phone);
            LoginBindActivity.this.mFingerUtils.stopsFingerPrintListener();
            LoginBindActivity.this.fingerDialogManager.cancel();
        }

        @Override // android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat.AuthenticationCallback
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            LoginBindActivity.this.fingerDialogManager.startShake();
            LoginBindActivity.this.fingerDialogManager.setTip("再试一次!");
            LoginBindActivity.access$3008(LoginBindActivity.this);
            if (LoginBindActivity.this.fingerCount > 2) {
                LoginBindActivity.this.fingerDialogManager.setTip("失败次数过多，短时间内不能调用指纹验证");
                LoginBindActivity.this.mFingerUtils.stopsFingerPrintListener();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void login(LoginAccountEntity loginAccountEntity) {
        if (loginAccountEntity != null && !TextUtils.isEmpty(loginAccountEntity.getAccountname()) && !TextUtils.isEmpty(loginAccountEntity.getAccouttype())) {
            String accountname = loginAccountEntity.getAccountname();
            String accouttype = loginAccountEntity.getAccouttype();
            String areaid = loginAccountEntity.getAreaid();
            MsLogUtil.m7979d("登录---->", "列表登录,accountName=" + accountname + ",loginType=" + accouttype + ",areaId=" + areaid);
            if (accouttype.equals("02")) {
                ManagerHomeOMO.initOMO(this, ManagerHomeOMO.getAreaCode(this.context, areaid, accountname), "3", "3");
            } else {
                ManagerHomeOMO.initOMO(this, accountname, accouttype.equals("03") ? "2" : "1", "3");
            }
        }
        if ("error".equals(loginAccountEntity.getKeyversion())) {
            if (ESIMLoginActivity.ESIMTYPE.equals(loginAccountEntity.getAccouttype()) && "on".equals(LoginConfigDataCenter.getInstance().getEntity().getEsimLoginSwitch())) {
                Intent intent = new Intent(this.context, ESIMLoginActivity.class);
                intent.putExtra("isFirstLogin", "1");
                intent.putExtra("account", loginAccountEntity.getAccountname());
                startActivity(intent);
                return;
            }
            Intent intent2 = new Intent(this.context, LoginActivity.class);
            intent2.putExtra("WelcomeClient", this.fromeWelcome);
            intent2.putExtra("from", "LoginBindActivity");
            intent2.putExtra("account", loginAccountEntity.getAccountname());
            intent2.putExtra("keyVersion", "error");
            intent2.putExtra("directAccess", this.directAccess);
            intent2.putExtra("url", this.intentEntranceURL);
            intent2.putExtra("paygreenUrl", this.paygreenUrl);
            intent2.putExtra("title", this.intentTitle);
            intent2.putExtra("menuId", this.intentMenuId);
            intent2.putExtra("backMode", this.intentBackMode);
            intent2.putExtra("requestType", this.intentRequestType);
            intent2.putExtra("loginFlag", this.needBackgroudLogin);
            intent2.putExtra("diffType", this.diffType);
            if ("1".equals(loginAccountEntity.getAccouttype()) || "01".equals(loginAccountEntity.getAccouttype())) {
                intent2.putExtra("logintype", "01");
            } else if ("3".equals(loginAccountEntity.getAccouttype()) || "06".equals(loginAccountEntity.getAccouttype())) {
                intent2.putExtra("logintype", "06");
            } else if ("4".equals(loginAccountEntity.getAccouttype()) || "02".equals(loginAccountEntity.getAccouttype())) {
                intent2.putExtra("logintype", "02");
                intent2.putExtra("guhuaAreaId", loginAccountEntity.getAreaid());
            } else if ("2".equals(loginAccountEntity.getAccouttype()) || "03".equals(loginAccountEntity.getAccouttype())) {
                intent2.putExtra("logintype", "03");
                intent2.putExtra("broadbandAccountAndAreaidToLogin", "YES");
                intent2.putExtra("broadbandAreaId", loginAccountEntity.getAreaid());
                intent2.putExtra("broadbandAccount", loginAccountEntity.getAccountname());
            }
            startActivityForResult(intent2, 1010);
        } else if (("02".equals(loginAccountEntity.getAccouttype()) || "03".equals(loginAccountEntity.getAccouttype()) || "04".equals(loginAccountEntity.getAccouttype())) && !TextUtils.isEmpty(loginAccountEntity.getCid())) {
            String string = this.context.getResources().getString(2131886969);
            String accountname2 = loginAccountEntity.getAccountname();
            IntentManager.gotoWebViewActivity(this.context, URLSet.getBroadBandInfo() + "?version=" + string + "&idCard=" + loginAccountEntity.getCid() + "&cityCode=" + loginAccountEntity.getAreaid() + "&fixedNumber=" + accountname2 + "&fixedNumberType=" + loginAccountEntity.getAccouttype() + "&deviceId=" + DeviceHelper.getDeviceID(true) + "&deviceCode=" + DeviceHelper.getDeviceID(false) + "&pip=" + SystemServiceUtils.getLocalIpAddress(), "");
            updateActivityStatus(loginAccountEntity.getActivityEntity());
        } else {
            this.selectedAccount = loginAccountEntity;
            if (!SystemServiceUtils.netIsAvailable()) {
                CustomDialogManager.show(this.context, "", "网络连接失败，请检查网络设置！");
                return;
            }
            long time = new Date().getTime();
            try {
                if (time - Long.parseLong(this.preference.getString("last_login_date")) < this.configManager.getLoginInterval() * 1000) {
                    CustomDialogManager.show(this.context, "", "登录频繁，请您稍候再试！");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.preference.putString("last_login_date", String.valueOf(time));
            logoutBeforeLogin(this.selectedAccount);
        }
    }

    private void logoutBeforeLogin(final LoginAccountEntity loginAccountEntity) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", getString(2131886969));
        requestParams.put("desmobile", this.userManager.getPassBackDesmobile());
        UserManager userManager = this.userManager;
        requestParams.put("token_online", userManager.getOnlineToken(userManager.getCurrentPhoneNumber()));
        requestParams.put("outFlag", "1");
        App.setLogined(LoginStateConst.UNLOGIN);
        App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.8
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                if (LoginBindActivity.this.isFinishing()) {
                    return;
                }
                if (LoginBindActivity.this.f18403pd != null) {
                    LoginBindActivity.this.f18403pd.setMessage("正在登录 请稍候");
                }
                LoginBindActivity.this.pdShow();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                LoginManager.logout(LoginBindActivity.this.context);
                LoginBindActivity.this.prepareLogin(loginAccountEntity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareLogin(LoginAccountEntity loginAccountEntity) {
        this.userManager.saveAutoLoginStatus(true);
        App.clearPersistentCookiesList();
        UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
        RequestParams requestParams = new RequestParams();
        Random random = new Random();
        String str = loginAccountEntity.getAccountname() + (random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9));
        try {
            requestParams.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(str.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
        } catch (Exception e) {
            requestParams.put("mobile", EncodeHelper.encodeByAES(str));
            e.printStackTrace();
        }
        requestParams.put("password", loginAccountEntity.getPassword());
        requestParams.put("appId", this.userManager.getLoginAppId());
        requestParams.put("areaCode", loginAccountEntity.getAreaid());
        if ("03".equals(loginAccountEntity.getAccouttype()) || "04".equals(loginAccountEntity.getAccouttype()) || "02".equals(loginAccountEntity.getAccouttype())) {
            requestParams.put("userType", loginAccountEntity.getAccouttype());
        }
        requestParams.put("isRemberPwd", String.valueOf(true));
        requestParams.put("keyVersion", loginAccountEntity.getKeyversion());
        requestParams.put("version", getString(2131886969));
        requestParams.put("deviceId", DeviceHelper.getDeviceID(true));
        requestParams.put("deviceCode", DeviceHelper.getDeviceID(true));
        requestParams.put("netWay", DeviceHelper.getNETType(getApplicationContext()));
        requestParams.put("deviceBrand", DeviceHelper.getDeviceBrand());
        requestParams.put("deviceModel", DeviceHelper.getDeviceModel());
        requestParams.put("deviceOS", DeviceHelper.getDeviceOSVersion());
        requestParams.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        if (DeviceHelper.getNeedHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
            try {
                requestParams.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
                requestParams.put("platformToken", this.preference.getString("platformToken"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            requestParams.put("provinceChanel", getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("CHANNEL_ID"));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.haveRetryNum = 0;
        this.errorLogBuffer = new StringBuffer();
        refreshOnlindata(loginAccountEntity, loginAccountEntity.getAccountname(), loginAccountEntity.getAccouttype());
    }

    private void showFreezeDialog(String str, String str2, final String str3, String str4) {
        CustomDialogManager.showZhuXiao(SoulPermission.getInstance().getTopActivity(), "号码已注销", LoginConstUtil.ZHUXIAOMSG, true, "取消", "立即激活", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.9
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
                HashMap hashMap = new HashMap();
                hashMap.put("type", str3);
                IntentManager.gotoWebViewActivityWithParams(LoginBindActivity.this.context, URLSet.getFreezeHtml(), "", hashMap);
            }
        });
    }

    private void refreshOnlindata(LoginAccountEntity loginAccountEntity, String str, String str2) {
        String onlineToken = UserManager.getInstance().getOnlineToken(str);
        HashMap hashMap = new HashMap();
        hashMap.put("token_online", onlineToken);
        hashMap.put("version", getString(2131886969));
        hashMap.put("reqtime", System.currentTimeMillis() + "");
        hashMap.put("appId", this.userManager.getLoginAppId());
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
        hashMap.put(this.context.getString(2131886495), DeviceHelper.getAndroidId());
        hashMap.put("netWay", DeviceHelper.getNETType(getApplicationContext()));
        hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
        hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
        hashMap.put("step", "bindlist");
        if (DeviceHelper.getNeedHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
            try {
                hashMap.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
                hashMap.put("platformToken", this.preference.getString("platformToken"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            hashMap.put("provinceChanel", getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("CHANNEL_ID"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        hashMap.put("flushkey", "1");
        ProgressDialog progressDialog = this.f18403pd;
        if (progressDialog != null) {
            progressDialog.setMessage("正在登录 请稍候");
        }
        hashMap.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
        loginHttp(hashMap, loginAccountEntity, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginHttp(final Map<String, String> map, final LoginAccountEntity loginAccountEntity, final String str, final String str2) {
        pdShow();
        if (!TextUtils.isEmpty(LoginFilterUtil.resultToken)) {
            map.put("resultToken", LoginFilterUtil.resultToken);
        }
        LoginParamsEntity onlineHeaderAndBody = DongtaiMiyaoUtils.getOnlineHeaderAndBody(str, map, ESIMLoginActivity.ESIMTYPE.equals(loginAccountEntity.getAccouttype()));
        App.getAsyncHttpClient().rxPost(URLSet.getOnline(), onlineHeaderAndBody.getBodyMap(), onlineHeaderAndBody.getHeaderMap(), 2, 0).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, HashMap<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.12
            @Override // io.reactivex.functions.Function
            public HashMap<String, String> apply(String str3) throws Exception {
                return LoginManager.handleLoginResponse(str, str3, "Login_Type");
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.10
            /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
                jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: SGET  (r170 I:int) =  android.util.DisplayMetrics.heightPixels int, expected to be less than 12
                	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
                	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
                */
            @Override // io.reactivex.functions.Consumer
            public void accept(java.util.HashMap<java.lang.String, java.lang.String> r11) {
                /*
                    Method dump skipped, instructions count: 1050
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity.C743610.accept(java.util.HashMap):void");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.11
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ErrorStatusCodeException errorStatusCodeException;
                LoginBindActivity.this.pdDissmiss();
                App.setLogined(LoginStateConst.UNLOGIN);
                if (!(th instanceof ErrorStatusCodeException)) {
                    LoginManager.showLoginErrorMessage(LoginBindActivity.this.context, "未登录成功，请重试【ECS0002】", true, "未登录成功，请重试【ECS0002】！", false);
                } else {
                    if (!App.isSuccessful(((ErrorStatusCodeException) th).statusCode)) {
                        LoginManager.showLoginErrorMessage(LoginBindActivity.this.context, "未登录成功，请重试【ECS" + errorStatusCodeException.statusCode + "】", true, "未登录成功，请重试【ECS" + errorStatusCodeException.statusCode + "】\n接口状态码=" + errorStatusCodeException.statusCode, false);
                    } else {
                        LoginManager.showLoginErrorMessage(LoginBindActivity.this.context, "未登录成功，请重试【ECS0002】", true, "未登录成功，请重试【ECS0002】\n接口返状态码=200，数据处理有问题，需查询服务器日志定位问题！", false);
                    }
                }
                EventBusUtils.post(new YWEvent(EventBusUtils.EVENT_YW_lOGOUT));
                DeviceInfoStatistics.uploadTianYuan("1", "01", "02");
            }
        });
    }

    private void showCustemLoginErrorDialog(String str, String str2, String str3) {
        final Intent intent = new Intent(this.context, LoginActivity.class);
        intent.putExtra("directAccess", this.directAccess);
        intent.putExtra("url", this.intentEntranceURL);
        intent.putExtra("paygreenUrl", this.paygreenUrl);
        intent.putExtra("title", this.intentTitle);
        intent.putExtra("menuId", this.intentMenuId);
        intent.putExtra("backMode", this.intentBackMode);
        intent.putExtra("requestType", this.intentRequestType);
        intent.putExtra("loginFlag", this.needBackgroudLogin);
        intent.putExtra("account", this.selectedAccount.getAccountname());
        intent.putExtra("WelcomeClient", this.fromeWelcome);
        intent.putExtra("logintype", "06");
        CustomDialogManager.show(this.context, "", str, true, str3, str2, false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.13
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
                LoginBindActivity.this.startActivity(intent);
                LoginBindActivity.this.finish();
            }
        });
    }

    private void updateActivityStatus(BindNumberActivityEntity bindNumberActivityEntity) {
        if (bindNumberActivityEntity == null || TextUtils.isEmpty(bindNumberActivityEntity.getMobile()) || TextUtils.isEmpty(bindNumberActivityEntity.getUpdateTime())) {
            return;
        }
        bindNumberActivityEntity.setClickTimeForCheckUpdated(bindNumberActivityEntity.getUpdateTime());
        this.bnaBox.put((Box<BindNumberActivityEntity>) bindNumberActivityEntity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String readCityFile(Context context) {
        try {
            InputStream open = context.getAssets().open("city_select_json.json");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = open.read(bArr, 0, 4096);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return new String(byteArrayOutputStream.toByteArray(), "utf-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<CitySelectEntity> getCityList(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                CitySelectEntity citySelectEntity = new CitySelectEntity();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                citySelectEntity.setCityCode(jSONObject.optString("cityCode"));
                citySelectEntity.setCityName(jSONObject.optString("cityName"));
                citySelectEntity.setCityRank(jSONObject.optString("cityRank"));
                citySelectEntity.setPingyin(jSONObject.optString("pingyin"));
                citySelectEntity.setPrivienceCode(jSONObject.optString("privienceCode"));
                citySelectEntity.setProvienceName(jSONObject.optString("provienceName"));
                citySelectEntity.setSortLetters(jSONObject.optString("sortLetters"));
                citySelectEntity.setMapCode(jSONObject.optString("mapCode"));
                arrayList.add(citySelectEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void delBindInfo(String str, String str2, String str3, String str4) {
        try {
            RequestParams requestParams = new RequestParams();
            requestParams.put("mobile", str);
            requestParams.put("type", str2);
            requestParams.put("city", str3);
            requestParams.put("card_id", str4);
            requestParams.put("version", getString(2131886969));
            requestParams.put("deviceId", DeviceHelper.getDeviceID(true));
            requestParams.put("deviceCode", DeviceHelper.getDeviceID(true));
            requestParams.put(this.context.getString(2131886495), DeviceHelper.getAndroidId());
            requestParams.put("netWay", DeviceHelper.getNETType(getApplicationContext()));
            requestParams.put("deviceBrand", DeviceHelper.getDeviceBrand());
            requestParams.put("deviceModel", DeviceHelper.getDeviceModel());
            requestParams.put("deviceOS", DeviceHelper.getDeviceOSVersion());
            App.getAsyncHttpClient().post(URLSet.getDelBindInfoUrl(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginBindActivity.14
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str5) {
                    super.onSuccess(i, str5);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str5) {
                    super.onFailure(th, str5);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFinish() {
                    super.onFinish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDissmiss() {
        try {
            if (isDestroyed() || isFinishing() || this.f18403pd == null || !this.f18403pd.isShowing()) {
                return;
            }
            this.f18403pd.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdShow() {
        try {
            if (isFinishing() || this.f18403pd == null || this.f18403pd.isShowing()) {
                return;
            }
            this.f18403pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
