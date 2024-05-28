package com.sinovatech.unicom.basic.p315ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.telephony.gsm.SmsMessage;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import cn.finalteam.galleryfinal.utils.LoginConstUtil;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.basic.datacenter.AreaDataCenter;
import com.sinovatech.unicom.basic.datacenter.UserUnicomInfoDataCenter;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.basic.eventbus.WeixinEvent;
import com.sinovatech.unicom.basic.eventbus.YWEvent;
import com.sinovatech.unicom.basic.p314po.AreaEntity;
import com.sinovatech.unicom.basic.p314po.BindNumberActivityEntity;
import com.sinovatech.unicom.basic.p314po.BindNumberActivityEntity_;
import com.sinovatech.unicom.basic.p314po.CitySelectEntity;
import com.sinovatech.unicom.basic.p314po.LoginAccountEntity;
import com.sinovatech.unicom.basic.p314po.LoginBindEntity;
import com.sinovatech.unicom.basic.p314po.LoginConfigEntity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.p315ui.adapter.AreaAdapter;
import com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerHomeOMO;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerLoginHandler;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerOneKeyLogin;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerWeixinBind;
import com.sinovatech.unicom.basic.p315ui.view.FaceLoginCustomDialog;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomAutoCompleteTextView;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.DeviceInfoStatistics;
import com.sinovatech.unicom.separatemodule.Log.LogFileUploadUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.login.LoginAreaUtil;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.LoginParamsEntity;
import com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginLogPv;
import com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil;
import com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.JiaoFeiManager;
import com.sinovatech.unicom.separatemodule.login.yinsixieyi.YinSiXieYiUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJAddressUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJAddressEntity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.p348mm.opensdk.modelmsg.SendAuth;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final int MIN_DELAY_TIME = 1000;
    private static LoginActivity context = null;
    public static String idCardBoradLogin = "";
    public static String idCardBoradLoginJiami = "";
    private static long lastClickTime;
    public NBSTraceUnit _nbs_trace;
    private String accountName;
    private CustomAutoCompleteTextView accountNameEditText;
    private LinearLayout accountPassWordLayout;
    private String areaId;
    private List<AreaEntity> areaList;
    private ImageView backButton;
    private Box<BindNumberActivityEntity> bnaBox;
    private String broadband;
    private String broadbandAccount;
    private String broadbandAccountAndAreaidToLogin;
    private ImageButton broadbandAccountnameSelectImagebutton;
    private CustomAutoCompleteTextView broadbandAreacodeEdittext;
    private ImageButton broadbandClearaccountnameImageButton;
    private ImageButton broadbandClearpasswordImageButton;
    private LinearLayout broadbandPassWordLayout;
    private EditText broadbandPasswordEdittext;
    private View broadbandSelectLineView;
    private List<LoginAccountEntity> broadbandSelectNumberList;
    private CustomAutoCompleteTextView broadbandaccountnameEdittext;
    private Map<String, List<LoginBindEntity>> cacheMap;
    private CountDownTimer cdTimer;
    private CountDownTimer cdTimer2;
    private List<CitySelectEntity> citylist;
    private ImageButton clearAccountNameImageButton;
    private ImageButton clearPasswordImageButton;
    private boolean code10Flag;
    private ConfigManager configManager;
    private String currentCode;
    private boolean directAccess;
    private LinearLayout erjiSwitchLayout;
    private TextView erjiTextview1;
    private TextView erjiTextview2;
    private View erjiView1;
    private View erjiView2;
    private ImageView esimImageView;
    private HashMap<String, String> faceAccoutnParams;
    private LinearLayout faceLoginBtn;
    private boolean faceStyleIsCurrent;
    private TextView faceSwitchStyleTextView;
    private Button forgetAccountBtn;
    private Button forgetPasswordButton;
    private String fromJsType;
    private String fromLockPattern;
    private String fromLockPatternbackground;
    private String fromStr;
    private String fromeWelcome;
    private String fromeYiwang;
    private ImageButton guhuaAccountnameSelectImagebutton;
    private String guhuaAreaId;
    private CustomAutoCompleteTextView guhuaAreacodeEdittext;
    private ImageButton guhuaClearaccountnameImageButton;
    private ImageButton guhuaClearpasswordImageButton;
    private String guhuaIntentManager;
    private LinearLayout guhuaPassWordLayout;
    private EditText guhuaPasswordEdittext;
    private View guhuaSelectLineView;
    private List<LoginAccountEntity> guhuaSelectNumberList;
    private Button guhuaWnagjiMimaImageButton;
    private CustomAutoCompleteTextView guhuaaccountnameEdittext;
    private String imageId;
    private String inputPassword;
    private String inputSmsPassword;
    private String intentAccountname;
    private String intentBackMode;
    private String intentEntranceURL;
    private String intentLogintype;
    private String intentMenuId;
    private String intentRequestType;
    private String intentTitle;
    private boolean isAginAreaid;
    private boolean isPassowrd;
    private String keyVersion;
    private LinearLayout kuandaizhanghaoLayout;
    private EditText laxtSixEditText0;
    private EditText laxtSixEditText1;
    private EditText laxtSixEditText2;
    private EditText laxtSixEditText4;
    private LinearLayout laxtSixLayout0;
    private LinearLayout laxtSixLayout1;
    private LinearLayout laxtSixLayout2;
    private LinearLayout laxtSixLayout4;
    private View laxtSixLine0;
    private View laxtSixLine1;
    private View laxtSixLine2;
    private View laxtSixLine4;
    private TextView liantongYinsiZhengce;
    private LinearLayout loginAccountInputlayout;
    private LinearLayout loginBroadbandInputlayout;
    private ToggleButton loginBroadbandPasswordSeeTogglebutton;
    private Button loginButton;
    private LoginConfigEntity loginConfigEntity;
    private LinearLayout loginGuhuaInputlayout;
    private ManagerLoginHandler loginHandler;
    private LoginLogPv loginLogPv;
    private ToggleButton loginPasswordSeeTogglebutton;
    private Button login_sms_request_button;
    private RadioButton login_type_rb_account;
    private RadioButton login_type_rb_broadband;
    private RadioButton login_type_rb_guhua;
    private RadioGroup login_type_rg;
    private ToggleButton loginguhuaPasswordSeeTogglebutton;
    private Disposable lvseJaiofeiDisposable;
    private ImageView mImgCheck;
    private LinearLayout mLinCheckXieYi;
    private TextView mNewYinsiTextView;
    private TextView mTvFuWuXieYi;
    private TextView mTvYinSiXieYi;
    private ManagerFaceLogin managerFaceLogin;
    private String memberLogin;
    private LinearLayout moreLoginlayout;
    private Button onekeyMianliuButton;
    private LinearLayout outLayout;
    private PhoneAdapter paAdpter;
    private PhoneAdapter paBroadbandAdapter;
    private PhoneAdapter paShenfenzhengAdapter;
    private PhoneAdapter paSmsAdpter;
    private View passviewLine0;
    private View passviewLine1;
    private View passviewLine2;
    private View passviewLine4;
    private EditText passwordEditText;
    private String paygreenUrl;

    /* renamed from: pd */
    private ProgressDialog f18402pd;
    private String pip;
    private SharePreferenceUtil preference;
    private Button registerButton;
    private RxPermissions rxPermissions;
    private LinearLayout sehngjiTipLayout;
    private ImageButton selectImageButton;
    private View selectLineView;
    private List<LoginAccountEntity> selectNumberList;
    private CustomAutoCompleteTextView shenfenzhengAreaEdittext;
    private String shenfenzhengAreaId;
    private ImageButton shenfenzhengClearImageButton;
    private LinearLayout shenfenzhengLayout;
    private CustomAutoCompleteTextView shenfenzhengNumberEdittext;
    private List<LoginAccountEntity> shenfenzhengNumberList;
    private ImageButton shenfenzhengSelectImageButton;
    private TextView shenfenzhengTips;
    private boolean showYuyinFlag;
    private String smsAccountName;
    private CustomAutoCompleteTextView smsAccountNameEditText;
    private ImageButton smsClearAccountNameImageButton;
    private ImageButton smsClearPasswordImageButton;
    private LinearLayout smsLayout;
    private LinearLayout smsPassWordLayout;
    private EditText smsPasswordEditText;
    private ImageButton smsSelectImageButton;
    private View smsSelectLineView;
    private List<LoginAccountEntity> smsSelectNumberList;
    private ImageView tipImage1;
    private ImageView tipImage2;
    private ImageView tipImage3;
    private RelativeLayout titleLayout;
    private TextView titleView;
    private UserUnicomInfoDataCenter unicomInfoDataCenter;
    private UserManager userManager;
    private String voice_code;
    private ImageButton weixinLoginButton;
    private ImageView yanzhengmaImage0;
    private ImageView yanzhengmaImage1;
    private ImageView yanzhengmaImage2;
    private ImageView yanzhengmaImage4;
    private LinearLayout yanzhengmaLayout0;
    private LinearLayout yanzhengmaLayout1;
    private LinearLayout yanzhengmaLayout2;
    private LinearLayout yanzhengmaLayout4;
    private EditText yanzhengmaeEditText0;
    private EditText yanzhengmaeEditText1;
    private EditText yanzhengmaeEditText2;
    private EditText yanzhengmaeEditText4;
    private YinSiXieYiUtil yinSiXieYiUtil;
    private LinearLayout yuyinClickLayout;
    private ImageView yuyinIcon;
    private LinearLayout yuyinLayout;
    private TextView yuyinTextView;
    private Map<String, String> ywMap;
    private String zipCode;
    private final String TAG = "LoginActivity";
    private int clickSelectButtonFlag = 0;
    private int clickSelectItemFlag = 1;
    private int clickSmsSelectButtonFlag = 0;
    private int clickSmsSelectItemFlag = 1;
    private Random random = new Random();
    private String randomStr = "000000";
    private int haveRetryNum = 0;
    private int smsHaveRetryNum = 0;
    private StringBuffer errorLogBuffer = new StringBuffer();
    private boolean needBackgroudLogin = false;
    private String loginStyle = "";
    private int clickAreaSelectItemFlag = 2;
    private int clickBroadbandSelectButtonFlag = 10;
    private int clickBroadbandSelectItemFlag = 1;
    private int clickGuhuaSelectButtonFlag = 10;
    private int clickGuhuaSelectItemFlag = 1;
    private int clickShenfenzhengButtonFlag = 10;
    private int clickShenfenzhengItemFlag = 1;
    private String loginType = "3";
    private String channelParam = "";
    private int secondLevel1 = 1;
    private int secondLevel2 = 1;
    private int smsLimitNum = 4;
    private final String FROMEWELCOME = "WelcomeClient";
    private final String FROMEYIWANG = "Yiwangwebview";
    private boolean isSelectXieYi = false;
    private final String tishiwenzi = "请先勾选协议后再进行·登录";
    final int BUFFER_SIZE = 4096;
    final String CITY_JSON_FILENAME = "city_select_json.json";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$CompleteInterfface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CompleteInterfface {
        void add();

        void search();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0401: ARITH  (r39 I:float) = (r112 I:float) + (r16 I:float), expected to be less than 20
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private void getAccountParams(com.sinovatech.unicom.basic.p315ui.activity.LoginActivity.CompleteInterfface r19) {
        /*
            Method dump skipped, instructions count: 1112
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.LoginActivity.getAccountParams(com.sinovatech.unicom.basic.ui.activity.LoginActivity$CompleteInterfface):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: CMP_G  (r59 I:int) = (r186 I:float), (r0 I:float), expected to be less than 10
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private void initGuhuaUI() {
        /*
            Method dump skipped, instructions count: 1036
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.LoginActivity.initGuhuaUI():void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: ARITH  (r13 I:int) = (r13 I:int) * (r3 I:int), expected to be less than 10
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private void prepareLogin(com.loopj.android.http.RequestParams r9) {
        /*
            Method dump skipped, instructions count: 1085
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.LoginActivity.prepareLogin(com.loopj.android.http.RequestParams):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: ARITH  (r63 I:float) = (r186 I:float) / (r0 I:float), expected to be less than 13
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    /* JADX INFO: Access modifiers changed from: private */
    public void switchLayout(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 1126
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.LoginActivity.switchLayout(java.lang.String):void");
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 52);
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

    static /* synthetic */ LoginConfigEntity access$000(LoginActivity loginActivity) {
        return loginActivity.loginConfigEntity;
    }

    static /* synthetic */ int access$10008(LoginActivity loginActivity) {
        int i = loginActivity.clickGuhuaSelectButtonFlag;
        loginActivity.clickGuhuaSelectButtonFlag = i + 1;
        return i;
    }

    static /* synthetic */ boolean access$10802(LoginActivity loginActivity, boolean z) {
        loginActivity.isSelectXieYi = z;
        return z;
    }

    static /* synthetic */ ImageView access$10900(LoginActivity loginActivity) {
        return loginActivity.mImgCheck;
    }

    static /* synthetic */ int access$11700(LoginActivity loginActivity) {
        return loginActivity.haveRetryNum;
    }

    static /* synthetic */ int access$11708(LoginActivity loginActivity) {
        int i = loginActivity.haveRetryNum;
        loginActivity.haveRetryNum = i + 1;
        return i;
    }

    static /* synthetic */ void access$11800(LoginActivity loginActivity) {
        loginActivity.pdDissmiss();
    }

    static /* synthetic */ void access$11900(LoginActivity loginActivity, JSONObject jSONObject) {
        loginActivity.handleTuxingma(jSONObject);
    }

    static /* synthetic */ void access$12000(LoginActivity loginActivity) {
        loginActivity.saveErrorAccount();
    }

    static /* synthetic */ String access$12100(LoginActivity loginActivity) {
        return loginActivity.loginStyle;
    }

    static /* synthetic */ UserManager access$12200(LoginActivity loginActivity) {
        return loginActivity.userManager;
    }

    static /* synthetic */ void access$12300(LoginActivity loginActivity, String str) {
        loginActivity.updateActivityStatus(str);
    }

    static /* synthetic */ StringBuffer access$12400(LoginActivity loginActivity) {
        return loginActivity.errorLogBuffer;
    }

    static /* synthetic */ void access$12500(LoginActivity loginActivity, String str) {
        loginActivity.showFreezeDialog(str);
    }

    static /* synthetic */ void access$13200(LoginActivity loginActivity, String str) {
        loginActivity.resetCdTimer(str);
    }

    static /* synthetic */ void access$13300(LoginActivity loginActivity, String str, RequestParams requestParams) {
        loginActivity.loadSMSRandomCode(str, requestParams);
    }

    static /* synthetic */ void access$13600(LoginActivity loginActivity, View view) {
        loginActivity.showSoftInput(view);
    }

    static /* synthetic */ boolean access$1702(LoginActivity loginActivity, boolean z) {
        loginActivity.code10Flag = z;
        return z;
    }

    static /* synthetic */ String access$1800(LoginActivity loginActivity) {
        return loginActivity.randomStr;
    }

    static /* synthetic */ String access$2000(LoginActivity loginActivity) {
        return loginActivity.smsAccountName;
    }

    static /* synthetic */ String access$2002(LoginActivity loginActivity, String str) {
        loginActivity.smsAccountName = str;
        return str;
    }

    static /* synthetic */ CustomAutoCompleteTextView access$2100(LoginActivity loginActivity) {
        return loginActivity.smsAccountNameEditText;
    }

    static /* synthetic */ String access$2200(LoginActivity loginActivity, EditText editText) {
        return loginActivity.getJiemiStr(editText);
    }

    static /* synthetic */ LoginActivity access$2300() {
        return context;
    }

    static /* synthetic */ Button access$2400(LoginActivity loginActivity) {
        return loginActivity.login_sms_request_button;
    }

    static /* synthetic */ void access$2500(LoginActivity loginActivity, boolean z) {
        loginActivity.changeYuyinYanzheng(z);
    }

    static /* synthetic */ int access$2600(LoginActivity loginActivity) {
        return loginActivity.smsHaveRetryNum;
    }

    static /* synthetic */ int access$2608(LoginActivity loginActivity) {
        int i = loginActivity.smsHaveRetryNum;
        loginActivity.smsHaveRetryNum = i + 1;
        return i;
    }

    static /* synthetic */ void access$2700(LoginActivity loginActivity, String str) {
        loginActivity.loadSMSRandomCode(str);
    }

    static /* synthetic */ EditText access$3200(LoginActivity loginActivity) {
        return loginActivity.smsPasswordEditText;
    }

    static /* synthetic */ String access$3400(LoginActivity loginActivity) {
        return loginActivity.accountName;
    }

    static /* synthetic */ int access$3608(LoginActivity loginActivity) {
        int i = loginActivity.clickSelectButtonFlag;
        loginActivity.clickSelectButtonFlag = i + 1;
        return i;
    }

    static /* synthetic */ int access$3708(LoginActivity loginActivity) {
        int i = loginActivity.clickSelectItemFlag;
        loginActivity.clickSelectItemFlag = i + 1;
        return i;
    }

    static /* synthetic */ String access$4502(LoginActivity loginActivity, String str) {
        loginActivity.currentCode = str;
        return str;
    }

    static /* synthetic */ int access$5408(LoginActivity loginActivity) {
        int i = loginActivity.clickSmsSelectButtonFlag;
        loginActivity.clickSmsSelectButtonFlag = i + 1;
        return i;
    }

    static /* synthetic */ int access$5508(LoginActivity loginActivity) {
        int i = loginActivity.clickSmsSelectItemFlag;
        loginActivity.clickSmsSelectItemFlag = i + 1;
        return i;
    }

    static /* synthetic */ String access$600(LoginActivity loginActivity) {
        return loginActivity.loginType;
    }

    static /* synthetic */ int access$6508(LoginActivity loginActivity) {
        int i = loginActivity.clickAreaSelectItemFlag;
        loginActivity.clickAreaSelectItemFlag = i + 1;
        return i;
    }

    static /* synthetic */ ConfigManager access$700(LoginActivity loginActivity) {
        return loginActivity.configManager;
    }

    static /* synthetic */ int access$7308(LoginActivity loginActivity) {
        int i = loginActivity.clickBroadbandSelectButtonFlag;
        loginActivity.clickBroadbandSelectButtonFlag = i + 1;
        return i;
    }

    static /* synthetic */ int access$7708(LoginActivity loginActivity) {
        int i = loginActivity.clickBroadbandSelectItemFlag;
        loginActivity.clickBroadbandSelectItemFlag = i + 1;
        return i;
    }

    static /* synthetic */ List access$7800(LoginActivity loginActivity) {
        return loginActivity.areaList;
    }

    static /* synthetic */ int access$8408(LoginActivity loginActivity) {
        int i = loginActivity.clickShenfenzhengItemFlag;
        loginActivity.clickShenfenzhengItemFlag = i + 1;
        return i;
    }

    static /* synthetic */ LinearLayout access$900(LoginActivity loginActivity) {
        return loginActivity.yuyinLayout;
    }

    static /* synthetic */ int access$9208(LoginActivity loginActivity) {
        int i = loginActivity.clickShenfenzhengButtonFlag;
        loginActivity.clickShenfenzhengButtonFlag = i + 1;
        return i;
    }

    static /* synthetic */ String access$9600(LoginActivity loginActivity) {
        return loginActivity.guhuaAreaId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C73311 implements Consumer<LoginConfigEntity> {
        C73311() {
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(LoginConfigEntity loginConfigEntity) throws Exception {
            LoginActivity.this.loginConfigEntity = loginConfigEntity;
            LoginActivity.this.setOnekeyMianliuButtonVisable();
            LoginActivity.this.setFaceSwitchVisable();
            LoginActivity.this.setEsimVisable();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        JiaoFeiManager.onResumeJiaofei(this, this.smsAccountName);
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        JiaoFeiManager.onStopJiaofei(this.lvseJaiofeiDisposable);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        JiaoFeiManager.onStopJiaofei(this.lvseJaiofeiDisposable);
    }

    private void handleIntentArgs() {
        this.directAccess = getIntent().getBooleanExtra("directAccess", false);
        this.intentEntranceURL = getIntent().getStringExtra("url");
        this.paygreenUrl = getIntent().getStringExtra("paygreenUrl");
        JiaoFeiManager.isFromJiaofei = !TextUtils.isEmpty(this.paygreenUrl) && this.paygreenUrl.contains("paymanager/businessRecharge/bankCardRecharge.htm");
        this.intentTitle = getIntent().getStringExtra("title");
        this.intentMenuId = getIntent().getStringExtra("menuId");
        this.intentBackMode = getIntent().getStringExtra("backMode");
        this.intentRequestType = getIntent().getStringExtra("requestType");
        this.needBackgroudLogin = getIntent().getBooleanExtra("loginFlag", false);
        this.keyVersion = getIntent().getStringExtra("keyVersion");
        this.intentAccountname = getIntent().getStringExtra("account");
        this.intentLogintype = getIntent().getStringExtra("logintype");
        this.fromStr = getIntent().getStringExtra("from");
        this.broadband = getIntent().getStringExtra("broadband");
        this.fromJsType = getIntent().getStringExtra("logintype");
        this.guhuaIntentManager = getIntent().getStringExtra("guhua");
        this.areaId = getIntent().getStringExtra("broadbandAreaId");
        this.broadbandAccount = getIntent().getStringExtra("broadbandAccount");
        this.broadbandAccountAndAreaidToLogin = getIntent().getStringExtra("broadbandAccountAndAreaidToLogin");
        this.guhuaAreaId = getIntent().getStringExtra("guhuaAreaId");
        this.fromeWelcome = getIntent().getStringExtra("WelcomeClient");
        this.fromeYiwang = getIntent().getStringExtra("Yiwangwebview");
        this.memberLogin = getIntent().getStringExtra("memberLogin");
        if ("01".equals(this.intentLogintype) || this.needBackgroudLogin) {
            this.secondLevel1 = 2;
        }
        if ("YES".equals(this.broadbandAccountAndAreaidToLogin)) {
            if ("05".equals(this.fromJsType)) {
                this.secondLevel2 = 1;
            } else {
                this.secondLevel2 = 2;
            }
            this.clickAreaSelectItemFlag = 0;
            this.isAginAreaid = true;
        }
        if (TextUtils.isEmpty(this.guhuaAreaId)) {
            return;
        }
        this.clickAreaSelectItemFlag = 0;
        this.isAginAreaid = true;
    }

    private void initUiElements() {
        tuxingma();
        this.titleView = (TextView) findViewById(2131297938);
        this.titleView.setOnClickListener(this);
        this.backButton = (ImageView) findViewById(2131297825);
        this.backButton.setOnClickListener(this);
        this.titleLayout = (RelativeLayout) findViewById(2131297937);
        this.registerButton = (Button) findViewById(2131297907);
        this.registerButton.setOnClickListener(this);
        this.kuandaizhanghaoLayout = (LinearLayout) findViewById(2131297837);
        this.areaList = AreaDataCenter.getAreaList(this);
        this.erjiSwitchLayout = (LinearLayout) findViewById(2131297928);
        this.erjiTextview1 = (TextView) findViewById(2131297929);
        this.erjiTextview2 = (TextView) findViewById(2131297930);
        this.erjiView1 = findViewById(2131297931);
        this.erjiView2 = findViewById(2131297932);
        this.mLinCheckXieYi = (LinearLayout) findViewById(2131297850);
        this.mTvYinSiXieYi = (TextView) findViewById(2131297967);
        this.mTvFuWuXieYi = (TextView) findViewById(2131297864);
        this.liantongYinsiZhengce = (TextView) findViewById(2131297895);
        this.mImgCheck = (ImageView) findViewById(2131297849);
        this.sehngjiTipLayout = (LinearLayout) findViewById(2131297905);
        this.mLinCheckXieYi.setOnClickListener(this);
        this.mTvYinSiXieYi.setOnClickListener(this);
        this.mTvFuWuXieYi.setOnClickListener(this);
        this.liantongYinsiZhengce.setOnClickListener(this);
        this.mImgCheck.setImageResource(2131231904);
        this.yuyinLayout = (LinearLayout) findViewById(2131297968);
        this.yuyinClickLayout = (LinearLayout) findViewById(2131297970);
        this.yuyinIcon = (ImageView) findViewById(2131297969);
        this.yuyinTextView = (TextView) findViewById(2131297971);
        this.outLayout = (LinearLayout) findViewById(2131297948);
        this.outLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                LoginActivity.this.hideSoftInput(view);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        initAccountUI();
        initBroadbandUI();
        initSmsAccountUI();
        initGuhuaUI();
        initShenfenzhengUI();
        initFace();
        if ("06".equals(this.intentLogintype) && this.configManager.getAutoSms()) {
            this.login_sms_request_button.performClick();
        }
        if ("YES".equals(this.broadbandAccountAndAreaidToLogin)) {
            if ("05".equals(this.fromJsType)) {
                this.secondLevel2 = 1;
            } else {
                this.secondLevel2 = 2;
            }
            for (int i = 0; i < this.areaList.size(); i++) {
                AreaEntity areaEntity = this.areaList.get(i);
                if (TextUtils.equals(areaEntity.getAreaid(), this.areaId)) {
                    this.broadbandAreacodeEdittext.setText(areaEntity.getAreaname());
                }
            }
            if ("error".equals(this.keyVersion)) {
                this.broadbandaccountnameEdittext.setTag(this.broadbandAccount);
                this.broadbandaccountnameEdittext.setText(getJiamiStr(this.broadbandAccount));
            } else {
                this.broadbandaccountnameEdittext.setText(this.broadbandAccount);
            }
            this.broadbandAreacodeEdittext.clearFocus();
            this.broadbandPasswordEdittext.requestFocus();
        }
        try {
            if ("YES".equals(getIntent().getStringExtra("guhuaTologin"))) {
                this.guhuaaccountnameEdittext.setText(this.intentAccountname);
                for (int i2 = 0; i2 < this.areaList.size(); i2++) {
                    AreaEntity areaEntity2 = this.areaList.get(i2);
                    if (TextUtils.equals(areaEntity2.getAreaid(), this.guhuaAreaId)) {
                        this.guhuaAreacodeEdittext.setText(areaEntity2.getAreaname());
                    }
                }
                this.guhuaAreacodeEdittext.clearFocus();
                this.guhuaPasswordEdittext.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.loginAccountInputlayout = (LinearLayout) findViewById(2131297817);
        this.loginBroadbandInputlayout = (LinearLayout) findViewById(2131297845);
        this.loginGuhuaInputlayout = (LinearLayout) findViewById(2131297875);
        this.forgetAccountBtn = (Button) findViewById(2131297844);
        this.forgetAccountBtn.setOnClickListener(this);
        this.login_type_rg = (RadioGroup) findViewById(2131297943);
        this.login_type_rb_broadband = (RadioButton) findViewById(2131297941);
        this.login_type_rb_account = (RadioButton) findViewById(2131297940);
        this.login_type_rb_guhua = (RadioButton) findViewById(2131297942);
        this.login_type_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.3
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i3) {
                Tracker.onCheckedChanged(radioGroup, i3);
                switch (i3) {
                    case 2131297940:
                        if (LoginActivity.this.secondLevel1 == 1) {
                            LoginActivity.this.loginType = "3";
                            if (!"1".equals(LoginActivity.this.configManager.getYuyinYanzheng()) || !LoginActivity.this.showYuyinFlag) {
                                LoginActivity.this.yuyinLayout.setVisibility(8);
                            } else {
                                LoginActivity.this.yuyinLayout.setVisibility(0);
                            }
                        } else {
                            LoginActivity.this.loginType = "1";
                            LoginActivity.this.yuyinLayout.setVisibility(8);
                        }
                        LoginActivity.this.login_type_rb_account.getPaint().setFakeBoldText(true);
                        LoginActivity.this.login_type_rb_broadband.getPaint().setFakeBoldText(false);
                        LoginActivity.this.login_type_rb_guhua.getPaint().setFakeBoldText(false);
                        LoginActivity.this.setOnekeyMianliuButtonVisable();
                        break;
                    case 2131297941:
                        if ("on".equals(LoginActivity.this.loginConfigEntity.getBroadLoginSwitch())) {
                            if (LoginActivity.this.secondLevel2 == 1) {
                                LoginActivity.this.loginType = "5";
                            } else {
                                LoginActivity.this.loginType = "2";
                            }
                        } else {
                            LoginActivity.this.loginType = "2";
                        }
                        LoginActivity.this.yuyinLayout.setVisibility(8);
                        LoginActivity.this.login_type_rb_account.getPaint().setFakeBoldText(false);
                        LoginActivity.this.login_type_rb_broadband.getPaint().setFakeBoldText(true);
                        LoginActivity.this.login_type_rb_guhua.getPaint().setFakeBoldText(false);
                        LoginActivity.this.onekeyMianliuButton.setVisibility(8);
                        break;
                    case 2131297942:
                        LoginActivity.this.loginType = "4";
                        LoginActivity.this.login_type_rb_account.getPaint().setFakeBoldText(false);
                        LoginActivity.this.login_type_rb_broadband.getPaint().setFakeBoldText(false);
                        LoginActivity.this.login_type_rb_guhua.getPaint().setFakeBoldText(true);
                        LoginActivity.this.yuyinLayout.setVisibility(8);
                        LoginActivity.this.onekeyMianliuButton.setVisibility(8);
                        break;
                }
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.switchLayout(loginActivity.loginType);
            }
        });
        this.loginType = "3";
        if (!TextUtils.isEmpty(this.intentLogintype) && this.intentLogintype.equals("06")) {
            this.loginStyle = "0";
            this.loginType = "3";
        }
        if ("YES".equals(this.broadband)) {
            this.login_type_rg.check(this.login_type_rb_broadband.getId());
        } else if ("YES".equals(this.guhuaIntentManager)) {
            this.login_type_rg.check(this.login_type_rb_guhua.getId());
        } else if ("03".equals(this.intentLogintype)) {
            this.login_type_rg.check(this.login_type_rb_broadband.getId());
        } else if ("02".equals(this.intentLogintype)) {
            this.login_type_rg.check(this.login_type_rb_guhua.getId());
        } else {
            this.login_type_rg.check(this.login_type_rb_account.getId());
        }
        this.erjiTextview1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                switch (LoginActivity.this.login_type_rg.getCheckedRadioButtonId()) {
                    case 2131297940:
                        LoginActivity.this.loginType = "3";
                        LoginActivity.this.secondLevel1 = 1;
                        if (!"1".equals(LoginActivity.this.configManager.getYuyinYanzheng()) || !LoginActivity.this.showYuyinFlag) {
                            LoginActivity.this.yuyinLayout.setVisibility(8);
                            break;
                        } else {
                            LoginActivity.this.yuyinLayout.setVisibility(0);
                            break;
                        }
                        break;
                    case 2131297941:
                        LoginActivity.this.loginType = "5";
                        LoginActivity.this.secondLevel2 = 1;
                        LoginActivity.this.yuyinLayout.setVisibility(8);
                        break;
                }
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.switchLayout(loginActivity.loginType);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.erjiTextview2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                switch (LoginActivity.this.login_type_rg.getCheckedRadioButtonId()) {
                    case 2131297940:
                        LoginActivity.this.loginType = "1";
                        LoginActivity.this.secondLevel1 = 2;
                        LoginActivity.this.yuyinLayout.setVisibility(8);
                        break;
                    case 2131297941:
                        LoginActivity.this.loginType = "2";
                        LoginActivity.this.secondLevel2 = 2;
                        LoginActivity.this.yuyinLayout.setVisibility(8);
                        break;
                }
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.switchLayout(loginActivity.loginType);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.yuyinClickLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (!LoginActivity.this.code10Flag && !LoginActivity.this.showYuyinFlag) {
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.randomStr = LoginActivity.this.random.nextInt(9) + "" + LoginActivity.this.random.nextInt(9) + "" + LoginActivity.this.random.nextInt(9) + "" + LoginActivity.this.random.nextInt(9) + "" + LoginActivity.this.random.nextInt(9) + "" + LoginActivity.this.random.nextInt(9);
                LoginActivity loginActivity2 = LoginActivity.this;
                loginActivity2.smsAccountName = loginActivity2.getJiemiStr(loginActivity2.smsAccountNameEditText);
                if (!TextUtils.isEmpty(LoginActivity.this.smsAccountName)) {
                    if (LoginActivity.this.smsAccountName.length() != 11) {
                        UIUtils.toast("请输入11位手机号码！");
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    } else if (SystemServiceUtils.netIsAvailable()) {
                        StatisticsUploadUtils.upload(LoginActivity.context, "604", "语音验证码", "按钮", "", "", "");
                        LoginActivity.this.login_sms_request_button.setTextColor(-4868683);
                        LoginActivity.this.login_sms_request_button.setBackgroundResource(2131231886);
                        LoginActivity.this.login_sms_request_button.setEnabled(false);
                        LoginActivity.this.changeYuyinYanzheng(false);
                        LoginActivity.this.smsHaveRetryNum = 0;
                        try {
                            LoginActivity.this.loadSMSRandomCode("1");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    } else {
                        CustomDialogManager.show(LoginActivity.this, "", "网络连接失败，请检查网络设置！");
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    }
                }
                UIUtils.toast("请输入手机号码！");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    private void tuxingma() {
        this.yanzhengmaLayout0 = (LinearLayout) findViewById(2131297959);
        this.yanzhengmaImage0 = (ImageView) findViewById(2131297955);
        this.yanzhengmaeEditText0 = (EditText) findViewById(2131297951);
        this.laxtSixLayout0 = (LinearLayout) findViewById(2131297883);
        this.laxtSixLine0 = findViewById(2131297890);
        this.laxtSixEditText0 = (EditText) findViewById(2131297879);
        this.yanzhengmaLayout1 = (LinearLayout) findViewById(2131297960);
        this.yanzhengmaImage1 = (ImageView) findViewById(2131297956);
        this.yanzhengmaeEditText1 = (EditText) findViewById(2131297952);
        this.laxtSixLayout1 = (LinearLayout) findViewById(2131297884);
        this.laxtSixLine1 = findViewById(2131297887);
        this.laxtSixEditText1 = (EditText) findViewById(2131297880);
        this.yanzhengmaLayout2 = (LinearLayout) findViewById(2131297961);
        this.yanzhengmaImage2 = (ImageView) findViewById(2131297957);
        this.yanzhengmaeEditText2 = (EditText) findViewById(2131297953);
        this.laxtSixLayout2 = (LinearLayout) findViewById(2131297885);
        this.laxtSixLine2 = findViewById(2131297888);
        this.laxtSixEditText2 = (EditText) findViewById(2131297881);
        this.yanzhengmaLayout4 = (LinearLayout) findViewById(2131297962);
        this.laxtSixLine4 = findViewById(2131297889);
        this.yanzhengmaImage4 = (ImageView) findViewById(2131297958);
        this.yanzhengmaeEditText4 = (EditText) findViewById(2131297954);
        this.laxtSixLayout4 = (LinearLayout) findViewById(2131297886);
        this.laxtSixEditText4 = (EditText) findViewById(2131297882);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTuxingma(JSONObject jSONObject) {
        StatisticsUploadUtils.upload(context, "605", "展示图形验证码", "展示", "", "", "");
        try {
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("placehoderText");
            String optString3 = jSONObject.optString("dsc");
            String optString4 = jSONObject.optString("imageContent");
            this.imageId = jSONObject.optString("imageID");
            if (!TextUtils.isEmpty(optString3)) {
                UIUtils.toast(optString3);
            }
            if ("7".equals(optString)) {
                this.yanzhengmaLayout0.setVisibility(0);
                this.laxtSixLayout0.setVisibility(8);
                this.laxtSixLine0.setVisibility(8);
                this.yanzhengmaImage0.setImageBitmap(FileTools.stringToBitmap(optString4));
                this.yanzhengmaeEditText0.setHint(optString2);
                this.yanzhengmaLayout1.setVisibility(0);
                this.laxtSixLayout1.setVisibility(8);
                this.laxtSixLine1.setVisibility(8);
                this.yanzhengmaImage1.setImageBitmap(FileTools.stringToBitmap(optString4));
                this.yanzhengmaeEditText1.setHint(optString2);
                this.yanzhengmaLayout2.setVisibility(0);
                this.laxtSixLayout2.setVisibility(8);
                this.laxtSixLine2.setVisibility(8);
                this.yanzhengmaImage2.setImageBitmap(FileTools.stringToBitmap(optString4));
                this.yanzhengmaeEditText2.setHint(optString2);
                this.yanzhengmaLayout4.setVisibility(0);
                this.laxtSixLayout4.setVisibility(8);
                this.laxtSixLine4.setVisibility(8);
                this.yanzhengmaImage4.setImageBitmap(FileTools.stringToBitmap(optString4));
                this.yanzhengmaeEditText4.setHint(optString2);
                TuxingyanzhengmaListener tuxingyanzhengmaListener = new TuxingyanzhengmaListener(this, null);
                this.yanzhengmaImage0.setOnClickListener(tuxingyanzhengmaListener);
                this.yanzhengmaImage1.setOnClickListener(tuxingyanzhengmaListener);
                this.yanzhengmaImage2.setOnClickListener(tuxingyanzhengmaListener);
                this.yanzhengmaImage4.setOnClickListener(tuxingyanzhengmaListener);
                this.loginButton.setEnabled(false);
                this.loginButton.setBackgroundResource(2131231880);
                if ("3".equals(this.loginType)) {
                    this.yanzhengmaeEditText0.setText("");
                }
                if ("1".equals(this.loginType)) {
                    this.yanzhengmaeEditText1.setText("");
                }
                if ("2".equals(this.loginType)) {
                    this.yanzhengmaeEditText2.setText("");
                }
                if ("4".equals(this.loginType)) {
                    this.yanzhengmaeEditText4.setText("");
                }
                this.yanzhengmaeEditText1.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.7
                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                        if (LoginActivity.this.accountNameEditText.getText().toString().length() != 11 || LoginActivity.this.passwordEditText.getText().toString().length() < 3 || editable.length() <= 0) {
                            LoginActivity.this.loginButton.setEnabled(false);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                            return;
                        }
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    }
                });
                this.yanzhengmaeEditText0.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.8
                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                        if (LoginActivity.this.smsAccountNameEditText.getText().toString().length() != 11 || LoginActivity.this.smsPasswordEditText.getText().toString().length() < 3 || editable.length() <= 0) {
                            LoginActivity.this.loginButton.setEnabled(false);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                            return;
                        }
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    }
                });
                this.yanzhengmaeEditText2.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.9
                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                        if (editable.length() > 0) {
                            LoginActivity.this.loginButton.setEnabled(true);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                            return;
                        }
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    }
                });
                this.yanzhengmaeEditText4.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.10
                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                        if (editable.length() > 0) {
                            LoginActivity.this.loginButton.setEnabled(true);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                            return;
                        }
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    }
                });
            }
            if ("8".equals(optString)) {
                this.loginButton.setEnabled(false);
                this.loginButton.setBackgroundResource(2131231880);
                this.yanzhengmaLayout0.setVisibility(8);
                this.laxtSixLayout0.setVisibility(0);
                this.laxtSixLine0.setVisibility(0);
                this.yanzhengmaLayout1.setVisibility(8);
                this.laxtSixLayout1.setVisibility(0);
                this.laxtSixLine1.setVisibility(0);
                this.yanzhengmaLayout2.setVisibility(8);
                this.laxtSixLayout2.setVisibility(0);
                this.laxtSixLine2.setVisibility(0);
                this.yanzhengmaLayout4.setVisibility(8);
                this.laxtSixLayout4.setVisibility(0);
                this.laxtSixLine4.setVisibility(0);
                if ("3".equals(this.loginType)) {
                    this.laxtSixEditText0.setText("");
                    this.laxtSixEditText0.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.11
                        @Override // android.text.TextWatcher
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override // android.text.TextWatcher
                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override // android.text.TextWatcher
                        public void afterTextChanged(Editable editable) {
                            if (LoginActivity.this.smsAccountNameEditText.getText().toString().length() != 11 || LoginActivity.this.smsPasswordEditText.getText().toString().length() < 3 || editable.length() <= 0) {
                                LoginActivity.this.loginButton.setEnabled(false);
                                LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                                return;
                            }
                            LoginActivity.this.loginButton.setEnabled(true);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                        }
                    });
                }
                if ("1".equals(this.loginType)) {
                    this.laxtSixEditText1.setText("");
                    this.laxtSixEditText1.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.12
                        @Override // android.text.TextWatcher
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override // android.text.TextWatcher
                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override // android.text.TextWatcher
                        public void afterTextChanged(Editable editable) {
                            if (LoginActivity.this.accountNameEditText.getText().toString().length() != 11 || LoginActivity.this.passwordEditText.getText().toString().length() < 3 || editable.length() <= 0) {
                                LoginActivity.this.loginButton.setEnabled(false);
                                LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                                return;
                            }
                            LoginActivity.this.loginButton.setEnabled(true);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                        }
                    });
                }
                if ("2".equals(this.loginType)) {
                    this.laxtSixEditText2.setText("");
                    this.laxtSixEditText2.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.13
                        @Override // android.text.TextWatcher
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override // android.text.TextWatcher
                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override // android.text.TextWatcher
                        public void afterTextChanged(Editable editable) {
                            if (editable.length() > 0) {
                                LoginActivity.this.loginButton.setEnabled(true);
                                LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                                return;
                            }
                            LoginActivity.this.loginButton.setEnabled(false);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                        }
                    });
                }
                if ("4".equals(this.loginType)) {
                    this.laxtSixEditText4.setText("");
                    this.laxtSixEditText4.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.14
                        @Override // android.text.TextWatcher
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override // android.text.TextWatcher
                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        @Override // android.text.TextWatcher
                        public void afterTextChanged(Editable editable) {
                            if (editable.length() > 0) {
                                LoginActivity.this.loginButton.setEnabled(true);
                                LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                                return;
                            }
                            LoginActivity.this.loginButton.setEnabled(false);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$TuxingyanzhengmaListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class TuxingyanzhengmaListener implements View.OnClickListener {
        private TuxingyanzhengmaListener() {
        }

        /* synthetic */ TuxingyanzhengmaListener(LoginActivity loginActivity, C73311 c73311) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            LoginActivity.this.refreshTuxing();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    private void initAccountUI() {
        this.loginButton = (Button) findViewById(2131297927);
        this.loginButton.setEnabled(false);
        this.loginButton.setOnClickListener(this);
        this.loginButton.setBackgroundResource(2131231880);
        this.weixinLoginButton = (ImageButton) findViewById(2131297949);
        this.weixinLoginButton.setOnClickListener(this);
        this.moreLoginlayout = (LinearLayout) findViewById(2131297901);
        this.onekeyMianliuButton = (Button) findViewById(2131297902);
        this.onekeyMianliuButton.setOnClickListener(this);
        if (ManagerOneKeyLogin.checkISCanOneKeyLogin(context) && !this.needBackgroudLogin) {
            this.onekeyMianliuButton.setEnabled(true);
            this.onekeyMianliuButton.setBackgroundResource(2131231884);
            this.onekeyMianliuButton.setTextColor(-1703896);
        } else {
            this.onekeyMianliuButton.setEnabled(false);
            this.onekeyMianliuButton.setBackgroundResource(2131231885);
            this.onekeyMianliuButton.setTextColor(-203287);
        }
        setOnekeyMianliuButtonVisable();
        if (!DeviceHelper.isWeixinInstall()) {
            this.weixinLoginButton.setVisibility(8);
        }
        this.accountNameEditText = (CustomAutoCompleteTextView) findViewById(2131297818);
        this.smsAccountNameEditText = (CustomAutoCompleteTextView) findViewById(2131297917);
        this.smsAccountNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.15
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    LoginActivity.this.hideSoftInput(textView);
                    return true;
                }
                return false;
            }
        });
        if (!TextUtils.isEmpty(this.intentLogintype) && !TextUtils.isEmpty(this.intentAccountname)) {
            if ("01".equals(this.intentLogintype) || "05".equals(this.intentLogintype) || "06".equals(this.intentLogintype) || "23".equals(this.intentLogintype)) {
                if ("error".equals(this.keyVersion)) {
                    this.accountNameEditText.setTag(this.intentAccountname);
                    this.smsAccountNameEditText.setTag(this.intentAccountname);
                    this.intentAccountname = getJiamiStr(this.intentAccountname);
                    this.accountNameEditText.setText(this.intentAccountname);
                } else {
                    this.accountNameEditText.setText(this.intentAccountname);
                }
            }
        } else if ("01".equals(this.userManager.getLoginType()) || "05".equals(this.userManager.getLoginType()) || "06".equals(this.userManager.getLoginType()) || "23".equals(this.userManager.getLoginType()) || "999".equals(this.userManager.getLoginType())) {
            this.accountNameEditText.setText(this.userManager.getUserAccountName());
        }
        this.passwordEditText = (EditText) findViewById(2131297903);
        this.forgetPasswordButton = (Button) findViewById(2131297863);
        this.forgetPasswordButton.setOnClickListener(this);
        this.clearAccountNameImageButton = (ImageButton) findViewById(2131297851);
        this.clearAccountNameImageButton.setOnClickListener(this);
        if (TextUtils.isEmpty(this.accountNameEditText.getText().toString())) {
            this.clearAccountNameImageButton.setVisibility(8);
        } else {
            this.clearAccountNameImageButton.setVisibility(0);
        }
        this.clearPasswordImageButton = (ImageButton) findViewById(2131297852);
        this.clearPasswordImageButton.setOnClickListener(this);
        if (this.userManager.getAutoLoginStatus() || "06".equals(this.userManager.getLoginType())) {
            if ("".equals(this.userManager.getUserPassword()) || "".equals(this.userManager.getKeyVersion())) {
                this.userManager.removeUserPassword();
                this.userManager.removeKeyVersion();
            }
        } else {
            this.userManager.removeUserPassword();
            this.userManager.removeKeyVersion();
        }
        String str = this.fromLockPattern;
        if (str != null && str.equals("fromLockPattern")) {
            this.userManager.saveAutoLoginStatus(true);
            this.userManager.removeUserPassword();
            this.userManager.removeKeyVersion();
            this.passwordEditText.setText("");
        }
        InputFilter inputFilter = new InputFilter() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.16
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (charSequence.equals(" ")) {
                    return "";
                }
                return null;
            }
        };
        this.accountNameEditText.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.17
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.accountName = loginActivity.accountNameEditText.getText().toString().trim();
                LoginActivity.this.passwordEditText.setHint("请输入密码");
                LoginActivity.this.paAdpter.isClickSelectButton = LoginActivity.access$3608(LoginActivity.this) < 2;
                LoginActivity.this.paAdpter.isClickSelectItem = LoginActivity.access$3708(LoginActivity.this) == 0;
                if (TextUtils.isEmpty(LoginActivity.this.accountNameEditText.getText().toString())) {
                    LoginActivity.this.clearAccountNameImageButton.setVisibility(8);
                } else {
                    LoginActivity.this.clearAccountNameImageButton.setVisibility(0);
                }
                if (LoginActivity.this.accountName.contains("*") && LoginActivity.this.accountName.length() != 11) {
                    String str2 = LoginActivity.this.accountName;
                    LoginActivity loginActivity2 = LoginActivity.this;
                    if (!str2.equals(loginActivity2.getJiamiStr(loginActivity2.getJiemiStr(loginActivity2.accountNameEditText)))) {
                        LoginActivity.this.accountNameEditText.setText("");
                        LoginActivity.this.accountNameEditText.setTag("");
                    }
                }
                LoginActivity.this.accountNameEditText.setThreshold(1);
                if (LoginActivity.this.faceStyleIsCurrent) {
                    LoginActivity.this.accountPassWordLayout.setVisibility(8);
                    LoginActivity.this.passwordEditText.setText("");
                }
                TextView textView = LoginActivity.this.mNewYinsiTextView;
                LoginActivity loginActivity3 = LoginActivity.this;
                textView.setText(loginActivity3.getXieyiSpannableString(loginActivity3.getJiemiStr(loginActivity3.accountNameEditText)));
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if ("1".equals(LoginActivity.this.loginType)) {
                    LoginActivity loginActivity = LoginActivity.this;
                    loginActivity.accountName = loginActivity.accountNameEditText.getText().toString().trim();
                    LoginActivity loginActivity2 = LoginActivity.this;
                    loginActivity2.inputPassword = loginActivity2.passwordEditText.getText().toString().trim();
                    if (editable.toString().length() <= 0 || TextUtils.isEmpty(LoginActivity.this.accountName) || LoginActivity.this.passwordEditText.getText().toString().length() <= 5 || TextUtils.isEmpty(LoginActivity.this.inputPassword) || LoginActivity.this.accountName.length() != 11) {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    } else if ("7".equals(LoginActivity.this.currentCode)) {
                        if (LoginActivity.this.yanzhengmaeEditText1.getText().toString().length() > 0) {
                            LoginActivity.this.loginButton.setEnabled(true);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                        } else {
                            LoginActivity.this.loginButton.setEnabled(false);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                        }
                    } else if ("8".equals(LoginActivity.this.currentCode)) {
                        if (LoginActivity.this.laxtSixEditText1.getText().toString().length() > 0) {
                            LoginActivity.this.loginButton.setEnabled(true);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                        } else {
                            LoginActivity.this.loginButton.setEnabled(false);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                        }
                    } else {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    }
                    LoginActivity.this.mNewYinsiTextView.setText(LoginActivity.this.getXieyiSpannableString(editable.toString()));
                }
            }
        });
        this.accountNameEditText.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(11)});
        this.passwordEditText.setFilters(new InputFilter[]{inputFilter, new InputFilter.LengthFilter(20)});
        this.accountNameEditText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (TextUtils.isEmpty(LoginActivity.this.accountNameEditText.getText())) {
                    LoginActivity.this.clickSelectButtonFlag = 1;
                    LoginActivity.this.accountNameEditText.append(" ");
                    LoginActivity.this.accountNameEditText.setText(LoginActivity.this.accountNameEditText.getText().toString().trim());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.accountNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.19
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Tracker.onFocusChange(view, z);
                if (z && TextUtils.isEmpty(LoginActivity.this.accountNameEditText.getText())) {
                    LoginActivity.this.clickSelectButtonFlag = 1;
                    LoginActivity.this.accountNameEditText.append(" ");
                    LoginActivity.this.accountNameEditText.setText(LoginActivity.this.accountNameEditText.getText().toString().trim());
                }
            }
        });
        this.passwordEditText.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.20
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(LoginActivity.this.passwordEditText.getText().toString())) {
                    LoginActivity.this.clearPasswordImageButton.setVisibility(8);
                } else {
                    LoginActivity.this.clearPasswordImageButton.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if ("1".equals(LoginActivity.this.loginType)) {
                    LoginActivity loginActivity = LoginActivity.this;
                    loginActivity.accountName = loginActivity.getJiemiStr(loginActivity.accountNameEditText);
                    LoginActivity loginActivity2 = LoginActivity.this;
                    loginActivity2.inputPassword = loginActivity2.passwordEditText.getText().toString().trim();
                    if (TextUtils.isEmpty(LoginActivity.this.accountName) || TextUtils.isEmpty(LoginActivity.this.inputPassword)) {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                        return;
                    }
                    LoginActivity.this.passwordEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
                    if (LoginActivity.this.inputPassword.length() <= 5 || LoginActivity.this.accountName.length() != 11) {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    } else if ("7".equals(LoginActivity.this.currentCode)) {
                        if (LoginActivity.this.yanzhengmaeEditText1.getText().toString().length() > 0) {
                            LoginActivity.this.loginButton.setEnabled(true);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                            return;
                        }
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    } else if ("8".equals(LoginActivity.this.currentCode)) {
                        if (LoginActivity.this.laxtSixEditText1.getText().toString().length() > 0) {
                            LoginActivity.this.loginButton.setEnabled(true);
                            LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                            return;
                        }
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    } else {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    }
                }
            }
        });
        if (TextUtils.isEmpty(this.passwordEditText.getText().toString())) {
            this.clearPasswordImageButton.setVisibility(8);
        } else {
            this.clearPasswordImageButton.setVisibility(0);
        }
        this.passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.21
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 4 || i == 6 || (keyEvent != null && 66 == keyEvent.getKeyCode() && keyEvent.getAction() == 0)) {
                    LoginActivity.this.loginButton.performClick();
                    return true;
                }
                return false;
            }
        });
        this.selectLineView = findViewById(2131297822);
        this.selectNumberList = new ArrayList();
        this.selectNumberList.addAll(this.userManager.getSelectAccountNameList("01"));
        this.selectNumberList.addAll(this.userManager.getSelectAccountNameList("05"));
        this.selectNumberList.addAll(this.userManager.getSelectAccountNameList("06"));
        try {
            for (LoginAccountEntity loginAccountEntity : this.selectNumberList) {
                if ("error".equals(loginAccountEntity.getKeyversion())) {
                    String accountname = loginAccountEntity.getAccountname();
                    loginAccountEntity.setAccountnameJiami(accountname.substring(0, 3) + "****" + accountname.substring(accountname.length() - 4, accountname.length()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.paAdpter = new PhoneAdapter(this, 2131493328, this.selectNumberList, new PhoneAdapter.OnPhoneAdapterListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.22
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onItemSelected(LoginAccountEntity loginAccountEntity2) {
                LoginActivity.this.clickSelectItemFlag = 0;
                try {
                    if (!loginAccountEntity2.getAccountname().trim().equals(LoginActivity.this.accountNameEditText.getText().toString())) {
                        LoginActivity.this.passwordEditText.setText("");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (TextUtils.isEmpty(loginAccountEntity2.getAccountnameJiami())) {
                    LoginActivity.this.accountNameEditText.setText(loginAccountEntity2.getAccountname().trim());
                    LoginActivity.this.mNewYinsiTextView.setText(LoginActivity.this.getXieyiSpannableString(loginAccountEntity2.getAccountname().trim()));
                } else {
                    LoginActivity.this.accountNameEditText.setTag(loginAccountEntity2.getAccountname().trim());
                    LoginActivity.this.accountNameEditText.setText(loginAccountEntity2.getAccountnameJiami().trim());
                    LoginActivity.this.mNewYinsiTextView.setText(LoginActivity.this.getXieyiSpannableString(loginAccountEntity2.getAccountnameJiami().trim()));
                }
                LoginActivity.this.accountNameEditText.clearFocus();
                LoginActivity.this.passwordEditText.requestFocus();
            }

            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onDeleteItem(LoginAccountEntity loginAccountEntity2) {
                LoginActivity.this.selectNumberList.remove(loginAccountEntity2);
                try {
                    for (LoginAccountEntity loginAccountEntity3 : LoginActivity.this.selectNumberList) {
                        if ("error".equals(loginAccountEntity3.getKeyversion())) {
                            String accountname2 = loginAccountEntity3.getAccountname();
                            loginAccountEntity3.setAccountnameJiami(accountname2.substring(0, 3) + "****" + accountname2.substring(accountname2.length() - 4, accountname2.length()));
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                LoginActivity.this.accountNameEditText.showDropDown();
                if (LoginActivity.this.selectNumberList.size() > 0) {
                    LoginActivity.this.selectImageButton.setVisibility(0);
                    LoginActivity.this.selectLineView.setVisibility(0);
                    return;
                }
                LoginActivity.this.selectImageButton.setVisibility(4);
                LoginActivity.this.selectLineView.setVisibility(4);
            }
        });
        this.accountNameEditText.setAdapter(this.paAdpter);
        this.accountNameEditText.setDropDownHorizontalOffset(0);
        this.accountNameEditText.setDropDownVerticalOffset(1);
        this.accountNameEditText.setDropDownBackgroundDrawable(new ColorDrawable(-1));
        this.selectImageButton = (ImageButton) findViewById(2131297821);
        this.selectImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (LoginActivity.this.clickSelectButtonFlag != 0) {
                    LoginActivity.this.clickSelectButtonFlag = 0;
                    LoginActivity.this.accountNameEditText.clearFocus();
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                LoginActivity.this.accountNameEditText.requestFocus();
                LoginActivity.this.clickSelectButtonFlag = 1;
                LoginActivity.this.accountNameEditText.append(" ");
                LoginActivity.this.accountNameEditText.setText(LoginActivity.this.accountNameEditText.getText().toString().trim());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (this.selectNumberList.size() > 0) {
            this.selectImageButton.setVisibility(0);
            this.selectLineView.setVisibility(0);
        } else {
            this.selectImageButton.setVisibility(4);
            this.selectLineView.setVisibility(4);
        }
        findViewById(2131297865).setOnClickListener(this);
        this.loginPasswordSeeTogglebutton = (ToggleButton) findViewById(2131297904);
        this.loginPasswordSeeTogglebutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.24
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Tracker.onCheckedChanged(compoundButton, z);
                LoginActivity.this.isPassowrd = false;
                if (z) {
                    LoginActivity.this.passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    LoginActivity.this.passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void initSmsAccountUI() {
        this.smsLayout = (LinearLayout) findViewById(2131297923);
        this.login_sms_request_button = (Button) findViewById(2131297926);
        this.login_sms_request_button.setOnClickListener(this);
        String sMSHint = this.configManager.getSMSHint();
        if (!TextUtils.isEmpty(sMSHint)) {
            this.smsAccountNameEditText.setHint(sMSHint);
        } else {
            this.smsAccountNameEditText.setHint("请输入联通/移动/电信手机号码");
        }
        if (!TextUtils.isEmpty(this.intentLogintype) && !TextUtils.isEmpty(this.intentAccountname)) {
            if ("01".equals(this.intentLogintype) || "06".equals(this.intentLogintype) || "23".equals(this.intentLogintype)) {
                this.smsAccountNameEditText.setText(this.intentAccountname);
                this.mNewYinsiTextView.setText(getXieyiSpannableString(this.intentAccountname));
            }
        } else if ("01".equals(this.userManager.getLoginType()) || "06".equals(this.userManager.getLoginType()) || "23".equals(this.userManager.getLoginType()) || "999".equals(this.userManager.getLoginType())) {
            this.smsAccountNameEditText.setText(this.userManager.getUserAccountName());
            this.mNewYinsiTextView.setText(getXieyiSpannableString(this.userManager.getUserAccountName()));
        }
        this.smsPasswordEditText = (EditText) findViewById(2131297924);
        this.smsPasswordEditText.setHint("请输入您收到的验证码");
        this.smsPasswordEditText.setTextIsSelectable(true);
        this.smsPasswordEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        this.smsClearAccountNameImageButton = (ImageButton) findViewById(2131297921);
        this.smsClearAccountNameImageButton.setOnClickListener(this);
        if (TextUtils.isEmpty(this.smsAccountNameEditText.getText().toString())) {
            this.smsClearAccountNameImageButton.setVisibility(8);
        } else {
            this.smsClearAccountNameImageButton.setVisibility(0);
        }
        this.smsClearPasswordImageButton = (ImageButton) findViewById(2131297922);
        this.smsClearPasswordImageButton.setOnClickListener(this);
        if (TextUtils.isEmpty(this.smsPasswordEditText.getText().toString())) {
            this.smsClearPasswordImageButton.setVisibility(8);
        } else {
            this.smsClearPasswordImageButton.setVisibility(0);
        }
        this.smsAccountNameEditText.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.25
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = LoginActivity.this.smsAccountNameEditText.getText().toString();
                LoginActivity.this.paSmsAdpter.isClickSelectButton = LoginActivity.access$5408(LoginActivity.this) < 2;
                LoginActivity.this.paSmsAdpter.isClickSelectItem = LoginActivity.access$5508(LoginActivity.this) == 0;
                if (TextUtils.isEmpty(LoginActivity.this.smsAccountNameEditText.getText().toString())) {
                    LoginActivity.this.smsClearAccountNameImageButton.setVisibility(8);
                } else {
                    LoginActivity.this.smsClearAccountNameImageButton.setVisibility(0);
                }
                if (obj.contains("*") && obj.length() != 11) {
                    LoginActivity loginActivity = LoginActivity.this;
                    if (!obj.equals(loginActivity.getJiamiStr(loginActivity.getJiemiStr(loginActivity.smsAccountNameEditText)))) {
                        LoginActivity.this.smsAccountNameEditText.setText("");
                        LoginActivity.this.smsAccountNameEditText.setTag("");
                    }
                }
                LoginActivity.this.smsAccountNameEditText.setThreshold(1);
                if (LoginActivity.this.faceStyleIsCurrent) {
                    LoginActivity.this.smsPassWordLayout.setVisibility(8);
                    LoginActivity.this.smsPasswordEditText.setText("");
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() != 11 || LoginActivity.this.smsPasswordEditText.getText().toString().length() < 3) {
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else if ("7".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.yanzhengmaeEditText0.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    } else {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    }
                } else if ("8".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.laxtSixEditText0.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    } else {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    }
                } else {
                    LoginActivity.this.loginButton.setEnabled(true);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                }
                TextView textView = LoginActivity.this.mNewYinsiTextView;
                LoginActivity loginActivity = LoginActivity.this;
                textView.setText(loginActivity.getXieyiSpannableString(loginActivity.getJiemiStr(loginActivity.smsAccountNameEditText)));
            }
        });
        this.smsAccountNameEditText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (TextUtils.isEmpty(LoginActivity.this.smsAccountNameEditText.getText())) {
                    LoginActivity.this.clickSelectButtonFlag = 1;
                    LoginActivity.this.smsAccountNameEditText.append(" ");
                    LoginActivity.this.smsAccountNameEditText.setText(LoginActivity.this.smsAccountNameEditText.getText().toString().trim());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.smsAccountNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.27
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Tracker.onFocusChange(view, z);
                if (z && TextUtils.isEmpty(LoginActivity.this.smsAccountNameEditText.getText())) {
                    LoginActivity.this.clickSelectButtonFlag = 1;
                    LoginActivity.this.smsAccountNameEditText.append(" ");
                    LoginActivity.this.smsAccountNameEditText.setText(LoginActivity.this.smsAccountNameEditText.getText().toString().trim());
                }
            }
        });
        this.smsAccountNameEditText.setFilters(new InputFilter[]{new InputFilter() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.28
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (charSequence.equals(" ")) {
                    return "";
                }
                return null;
            }
        }, new InputFilter.LengthFilter(11)});
        this.smsPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.29
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 4 || i == 6 || (keyEvent != null && 66 == keyEvent.getKeyCode() && keyEvent.getAction() == 0)) {
                    LoginActivity.this.hideSoftInput(textView);
                    return true;
                }
                return false;
            }
        });
        this.smsPasswordEditText.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.30
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(LoginActivity.this.smsPasswordEditText.getText().toString())) {
                    LoginActivity.this.smsClearPasswordImageButton.setVisibility(8);
                } else {
                    LoginActivity.this.smsClearPasswordImageButton.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (LoginActivity.this.smsAccountNameEditText.getText().toString().length() != 11 || editable.toString().length() < 3) {
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else if ("7".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.yanzhengmaeEditText0.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    } else {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    }
                } else if ("8".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.laxtSixEditText0.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    } else {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    }
                } else {
                    LoginActivity.this.loginButton.setEnabled(true);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                }
                if (!"3".equals(LoginActivity.this.loginType) || LoginActivity.this.faceStyleIsCurrent) {
                    return;
                }
                if (!"4".equals(LoginActivity.this.loginConfigEntity.getLoginCodeLen())) {
                    if ("6".equals(LoginActivity.this.loginConfigEntity.getLoginCodeLen()) && editable.toString().length() == 6) {
                        LoginActivity.this.loginSubmit();
                    }
                } else if (editable.toString().length() == 4) {
                    LoginActivity.this.loginSubmit();
                }
            }
        });
        this.smsSelectNumberList = new ArrayList();
        this.smsSelectNumberList.addAll(this.userManager.getSelectAccountNameList("01"));
        this.smsSelectNumberList.addAll(this.userManager.getSelectAccountNameList("06"));
        Iterator<LoginAccountEntity> it = this.smsSelectNumberList.iterator();
        while (it.hasNext()) {
            if (it.next().getAccountname().contains("@")) {
                it.remove();
            }
        }
        try {
            for (LoginAccountEntity loginAccountEntity : this.smsSelectNumberList) {
                if ("error".equals(loginAccountEntity.getKeyversion())) {
                    String accountname = loginAccountEntity.getAccountname();
                    loginAccountEntity.setAccountnameJiami(accountname.substring(0, 3) + "****" + accountname.substring(accountname.length() - 4, accountname.length()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.smsSelectLineView = findViewById(2131297920);
        this.paSmsAdpter = new PhoneAdapter(this, 2131493338, this.smsSelectNumberList, new PhoneAdapter.OnPhoneAdapterListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.31
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onItemSelected(LoginAccountEntity loginAccountEntity2) {
                LoginActivity.this.clickSmsSelectItemFlag = 0;
                if (TextUtils.isEmpty(loginAccountEntity2.getAccountnameJiami())) {
                    LoginActivity.this.smsAccountNameEditText.setText(loginAccountEntity2.getAccountname().trim());
                    LoginActivity.this.mNewYinsiTextView.setText(LoginActivity.this.getXieyiSpannableString(loginAccountEntity2.getAccountname().trim()));
                } else {
                    LoginActivity.this.smsAccountNameEditText.setTag(loginAccountEntity2.getAccountname().trim());
                    LoginActivity.this.smsAccountNameEditText.setText(loginAccountEntity2.getAccountnameJiami().trim());
                    LoginActivity.this.mNewYinsiTextView.setText(LoginActivity.this.getXieyiSpannableString(loginAccountEntity2.getAccountnameJiami().trim()));
                }
                LoginActivity.this.smsAccountNameEditText.clearFocus();
                LoginActivity.this.smsPasswordEditText.requestFocus();
            }

            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onDeleteItem(LoginAccountEntity loginAccountEntity2) {
                LoginActivity.this.smsSelectNumberList.remove(loginAccountEntity2);
                try {
                    for (LoginAccountEntity loginAccountEntity3 : LoginActivity.this.smsSelectNumberList) {
                        if ("error".equals(loginAccountEntity3.getKeyversion())) {
                            String accountname2 = loginAccountEntity3.getAccountname();
                            loginAccountEntity3.setAccountnameJiami(accountname2.substring(0, 3) + "****" + accountname2.substring(accountname2.length() - 4, accountname2.length()));
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                LoginActivity.this.smsAccountNameEditText.showDropDown();
                if (LoginActivity.this.smsSelectNumberList.size() > 0) {
                    LoginActivity.this.smsSelectImageButton.setVisibility(0);
                    LoginActivity.this.smsSelectLineView.setVisibility(0);
                    return;
                }
                LoginActivity.this.smsSelectImageButton.setVisibility(4);
                LoginActivity.this.smsSelectLineView.setVisibility(4);
            }
        });
        this.smsAccountNameEditText.setAdapter(this.paSmsAdpter);
        this.smsAccountNameEditText.setDropDownHorizontalOffset(0);
        this.smsAccountNameEditText.setDropDownVerticalOffset(1);
        this.smsAccountNameEditText.setDropDownBackgroundDrawable(new ColorDrawable(-1));
        this.smsSelectImageButton = (ImageButton) findViewById(2131297919);
        this.smsSelectImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (LoginActivity.this.clickSmsSelectButtonFlag != 0) {
                    LoginActivity.this.clickSmsSelectButtonFlag = 0;
                    LoginActivity.this.smsAccountNameEditText.clearFocus();
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                LoginActivity.this.smsAccountNameEditText.requestFocus();
                LoginActivity.this.clickSmsSelectButtonFlag = 1;
                LoginActivity.this.smsAccountNameEditText.append(" ");
                LoginActivity.this.smsAccountNameEditText.setText(LoginActivity.this.smsAccountNameEditText.getText().toString().trim());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (this.smsSelectNumberList.size() > 0) {
            this.smsSelectImageButton.setVisibility(0);
            this.smsSelectLineView.setVisibility(0);
            return;
        }
        this.smsSelectImageButton.setVisibility(4);
        this.smsSelectLineView.setVisibility(4);
    }

    private void initBroadbandUI() {
        LoginAccountEntity selectAccountName;
        this.broadbandAreacodeEdittext = (CustomAutoCompleteTextView) findViewById(2131297840);
        this.broadbandaccountnameEdittext = (CustomAutoCompleteTextView) findViewById(2131297836);
        this.broadbandSelectLineView = findViewById(2131297839);
        this.broadbandClearaccountnameImageButton = (ImageButton) findViewById(2131297842);
        this.broadbandAccountnameSelectImagebutton = (ImageButton) findViewById(2131297838);
        this.broadbandClearpasswordImageButton = (ImageButton) findViewById(2131297843);
        this.broadbandPasswordEdittext = (EditText) findViewById(2131297846);
        this.loginBroadbandPasswordSeeTogglebutton = (ToggleButton) findViewById(2131297848);
        AreaAdapter areaAdapter = new AreaAdapter(this, 2131493329, this.areaList, new AreaAdapter.OnItemAdapterListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.33
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.AreaAdapter.OnItemAdapterListener
            public void onItemSelected(String str, String str2) {
                LoginActivity.this.clickAreaSelectItemFlag = 0;
                LoginActivity.this.broadbandAreacodeEdittext.setText(str.trim());
                LoginActivity.this.broadbandAreacodeEdittext.clearFocus();
                LoginActivity.this.broadbandaccountnameEdittext.requestFocus();
                LoginActivity.this.areaId = str2;
                if (LoginActivity.this.broadbandaccountnameEdittext.getText().toString().length() <= 0 || LoginActivity.this.broadbandPasswordEdittext.getText().toString().length() <= 0) {
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else if ("7".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.yanzhengmaeEditText2.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                        return;
                    }
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else if ("8".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.laxtSixEditText2.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                        return;
                    }
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else {
                    LoginActivity.this.loginButton.setEnabled(true);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                }
            }
        });
        InputFilter inputFilter = new InputFilter() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.34
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (charSequence.equals(" ")) {
                    return "";
                }
                return null;
            }
        };
        this.broadbandAreacodeEdittext.setAdapter(areaAdapter);
        this.broadbandAreacodeEdittext.setDropDownHorizontalOffset(0);
        this.broadbandAreacodeEdittext.setDropDownVerticalOffset(1);
        this.broadbandAreacodeEdittext.setDropDownBackgroundDrawable(new ColorDrawable(-1));
        this.broadbandAreacodeEdittext.setFilters(new InputFilter[]{inputFilter});
        this.broadbandPasswordEdittext.setFilters(new InputFilter[]{inputFilter});
        this.broadbandAreacodeEdittext.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.35
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (LoginActivity.access$6508(LoginActivity.this) == 0) {
                    return;
                }
                if (!LoginActivity.this.isAginAreaid) {
                    LoginActivity.this.areaId = "";
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                }
                if (LoginActivity.this.clickAreaSelectItemFlag >= 2) {
                    LoginActivity.this.isAginAreaid = false;
                }
            }
        });
        this.broadbandAreacodeEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.36
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Tracker.onFocusChange(view, z);
                if (!z && LoginActivity.this.clickAreaSelectItemFlag > 1) {
                    LoginActivity.this.broadbandAreacodeEdittext.setText("");
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                }
                if (z && TextUtils.isEmpty(LoginActivity.this.broadbandAreacodeEdittext.getText())) {
                    LoginActivity.this.clickBroadbandSelectButtonFlag = 1;
                    LoginActivity.this.broadbandAreacodeEdittext.append(" ");
                    LoginActivity.this.broadbandAreacodeEdittext.setText(LoginActivity.this.broadbandAreacodeEdittext.getText().toString().trim());
                }
            }
        });
        this.broadbandAreacodeEdittext.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (TextUtils.isEmpty(LoginActivity.this.broadbandAreacodeEdittext.getText())) {
                    LoginActivity.this.clickBroadbandSelectButtonFlag = 1;
                    LoginActivity.this.broadbandAreacodeEdittext.append(" ");
                    LoginActivity.this.broadbandAreacodeEdittext.setText(LoginActivity.this.broadbandAreacodeEdittext.getText().toString().trim());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.broadbandaccountnameEdittext.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.38
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LoginActivity.this.paBroadbandAdapter.isClickSelectButton = LoginActivity.access$7308(LoginActivity.this) < 2;
                if (TextUtils.isEmpty(LoginActivity.this.broadbandaccountnameEdittext.getText().toString())) {
                    LoginActivity.this.broadbandClearaccountnameImageButton.setVisibility(4);
                } else {
                    LoginActivity.this.broadbandClearaccountnameImageButton.setVisibility(0);
                }
                LoginActivity.this.broadbandaccountnameEdittext.setThreshold(1);
                if (LoginActivity.this.faceStyleIsCurrent) {
                    LoginActivity.this.broadbandPassWordLayout.setVisibility(8);
                    LoginActivity.this.broadbandPasswordEdittext.setText("");
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(LoginActivity.this.areaId) || editable.toString().length() <= 0 || LoginActivity.this.broadbandPasswordEdittext.getText().toString().length() <= 0) {
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else if ("7".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.yanzhengmaeEditText2.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    } else {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    }
                } else if ("8".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.laxtSixEditText2.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    } else {
                        LoginActivity.this.loginButton.setEnabled(false);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    }
                } else {
                    LoginActivity.this.loginButton.setEnabled(true);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                }
                TextView textView = LoginActivity.this.mNewYinsiTextView;
                LoginActivity loginActivity = LoginActivity.this;
                textView.setText(loginActivity.getXieyiSpannableString(loginActivity.getJiemiStr(loginActivity.broadbandaccountnameEdittext)));
            }
        });
        this.broadbandaccountnameEdittext.setFilters(new InputFilter[]{inputFilter});
        this.broadbandSelectNumberList = this.userManager.getSelectAccountNameList("03");
        this.broadbandSelectNumberList.addAll(this.userManager.getSelectAccountNameList("04"));
        try {
            for (LoginAccountEntity loginAccountEntity : this.broadbandSelectNumberList) {
                if ("error".equals(loginAccountEntity.getKeyversion()) || TextUtils.isEmpty(loginAccountEntity.getPassword())) {
                    loginAccountEntity.setAccountnameJiami(getJiamiStr(loginAccountEntity.getAccountname()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.paBroadbandAdapter = new PhoneAdapter(this, 2131493332, this.broadbandSelectNumberList, new PhoneAdapter.OnPhoneAdapterListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.39
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onItemSelected(LoginAccountEntity loginAccountEntity2) {
                LoginActivity.this.clickBroadbandSelectItemFlag = 0;
                if (TextUtils.isEmpty(loginAccountEntity2.getAccountnameJiami())) {
                    LoginActivity.this.broadbandaccountnameEdittext.setText(loginAccountEntity2.getAccountname().trim());
                    LoginActivity.this.mNewYinsiTextView.setText(LoginActivity.this.getXieyiSpannableString(loginAccountEntity2.getAccountname().trim()));
                } else {
                    LoginActivity.this.broadbandaccountnameEdittext.setTag(loginAccountEntity2.getAccountname().trim());
                    LoginActivity.this.broadbandaccountnameEdittext.setText(loginAccountEntity2.getAccountnameJiami().trim());
                    LoginActivity.this.mNewYinsiTextView.setText(LoginActivity.this.getXieyiSpannableString(loginAccountEntity2.getAccountnameJiami().trim()));
                }
                LoginActivity.this.broadbandaccountnameEdittext.clearFocus();
                int i = 0;
                while (true) {
                    if (i >= LoginActivity.this.areaList.size()) {
                        break;
                    } else if (TextUtils.equals(loginAccountEntity2.getAreaid(), ((AreaEntity) LoginActivity.this.areaList.get(i)).getAreaid())) {
                        LoginActivity.this.clickAreaSelectItemFlag = 0;
                        LoginActivity.this.broadbandAreacodeEdittext.setText(((AreaEntity) LoginActivity.this.areaList.get(i)).getAreaname());
                        LoginActivity.this.areaId = loginAccountEntity2.getAreaid();
                        break;
                    } else {
                        i++;
                    }
                }
                LoginActivity.this.broadbandPasswordEdittext.requestFocus();
            }

            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onDeleteItem(LoginAccountEntity loginAccountEntity2) {
                LoginActivity.this.broadbandSelectNumberList.remove(loginAccountEntity2);
                LoginActivity.this.broadbandaccountnameEdittext.showDropDown();
                if (LoginActivity.this.broadbandSelectNumberList.size() > 0) {
                    LoginActivity.this.broadbandAccountnameSelectImagebutton.setVisibility(0);
                    LoginActivity.this.broadbandSelectLineView.setVisibility(0);
                    return;
                }
                LoginActivity.this.broadbandAccountnameSelectImagebutton.setVisibility(4);
                LoginActivity.this.broadbandSelectLineView.setVisibility(4);
            }
        });
        this.broadbandaccountnameEdittext.setAdapter(this.paBroadbandAdapter);
        this.broadbandaccountnameEdittext.setDropDownHorizontalOffset(0);
        this.broadbandaccountnameEdittext.setDropDownVerticalOffset(1);
        this.broadbandaccountnameEdittext.setDropDownBackgroundDrawable(new ColorDrawable(-1));
        this.broadbandaccountnameEdittext.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (TextUtils.isEmpty(LoginActivity.this.broadbandaccountnameEdittext.getText())) {
                    LoginActivity.this.clickBroadbandSelectButtonFlag = 1;
                    LoginActivity.this.broadbandaccountnameEdittext.append(" ");
                    LoginActivity.this.broadbandaccountnameEdittext.setText(LoginActivity.this.broadbandaccountnameEdittext.getText().toString().trim());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.broadbandaccountnameEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.41
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Tracker.onFocusChange(view, z);
                if (z && TextUtils.isEmpty(LoginActivity.this.broadbandaccountnameEdittext.getText())) {
                    LoginActivity.this.clickBroadbandSelectButtonFlag = 1;
                    LoginActivity.this.broadbandaccountnameEdittext.append(" ");
                    LoginActivity.this.broadbandaccountnameEdittext.setText(LoginActivity.this.broadbandaccountnameEdittext.getText().toString().trim());
                }
            }
        });
        this.broadbandAccountnameSelectImagebutton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.42
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                LoginActivity.this.broadbandaccountnameEdittext.requestFocus();
                LoginActivity.this.clickBroadbandSelectButtonFlag = 1;
                LoginActivity.this.broadbandaccountnameEdittext.append(" ");
                LoginActivity.this.broadbandaccountnameEdittext.setText(LoginActivity.this.broadbandaccountnameEdittext.getText().toString().trim());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (this.broadbandSelectNumberList.size() > 0) {
            this.broadbandAccountnameSelectImagebutton.setVisibility(0);
            this.broadbandSelectLineView.setVisibility(0);
        } else {
            this.broadbandAccountnameSelectImagebutton.setVisibility(4);
            this.broadbandSelectLineView.setVisibility(4);
        }
        this.broadbandClearaccountnameImageButton.setOnClickListener(this);
        if (TextUtils.isEmpty(this.broadbandaccountnameEdittext.getText().toString())) {
            this.broadbandClearaccountnameImageButton.setVisibility(4);
        } else {
            this.broadbandClearaccountnameImageButton.setVisibility(0);
        }
        this.broadbandClearpasswordImageButton.setOnClickListener(this);
        this.broadbandPasswordEdittext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        this.broadbandPasswordEdittext.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.43
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LoginActivity.this.paBroadbandAdapter.isClickSelectButton = LoginActivity.access$7308(LoginActivity.this) < 2;
                LoginActivity.this.paBroadbandAdapter.isClickSelectItem = LoginActivity.access$7708(LoginActivity.this) == 0;
                if (TextUtils.isEmpty(LoginActivity.this.broadbandaccountnameEdittext.getText().toString())) {
                    LoginActivity.this.broadbandClearaccountnameImageButton.setVisibility(4);
                } else {
                    LoginActivity.this.broadbandClearaccountnameImageButton.setVisibility(0);
                }
                LoginActivity.this.broadbandaccountnameEdittext.setThreshold(1);
                if (TextUtils.isEmpty(LoginActivity.this.broadbandPasswordEdittext.getText().toString())) {
                    LoginActivity.this.broadbandClearpasswordImageButton.setVisibility(8);
                } else {
                    LoginActivity.this.broadbandClearpasswordImageButton.setVisibility(0);
                }
                String obj = LoginActivity.this.broadbandaccountnameEdittext.getText().toString();
                if (obj.contains("*")) {
                    LoginActivity loginActivity = LoginActivity.this;
                    if (obj.equals(loginActivity.getJiamiStr(loginActivity.getJiemiStr(loginActivity.broadbandaccountnameEdittext)))) {
                        return;
                    }
                    LoginActivity.this.broadbandPasswordEdittext.setText("");
                    LoginActivity.this.broadbandPasswordEdittext.setTag("");
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(LoginActivity.this.areaId) || LoginActivity.this.broadbandaccountnameEdittext.getText().toString().length() <= 0 || editable.toString().length() <= 5) {
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else if ("7".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.yanzhengmaeEditText2.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                        return;
                    }
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else if ("8".equals(LoginActivity.this.currentCode)) {
                    if (LoginActivity.this.laxtSixEditText2.getText().toString().length() > 0) {
                        LoginActivity.this.loginButton.setEnabled(true);
                        LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                        return;
                    }
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else {
                    LoginActivity.this.loginButton.setEnabled(true);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                }
            }
        });
        if (TextUtils.isEmpty(this.broadbandPasswordEdittext.getText().toString())) {
            this.broadbandClearpasswordImageButton.setVisibility(8);
        } else {
            this.broadbandClearpasswordImageButton.setVisibility(0);
        }
        this.broadbandPasswordEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.44
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 4 || i == 6 || (keyEvent != null && 66 == keyEvent.getKeyCode() && keyEvent.getAction() == 0)) {
                    LoginActivity.this.loginButton.performClick();
                    return true;
                }
                return false;
            }
        });
        if (!TextUtils.isEmpty(this.intentLogintype) && !TextUtils.isEmpty(this.intentAccountname)) {
            if (("03".equals(this.intentLogintype) || "04".equals(this.intentLogintype)) && (selectAccountName = this.userManager.getSelectAccountName(this.intentAccountname, "1")) != null) {
                int i = 0;
                while (true) {
                    if (i >= this.areaList.size()) {
                        break;
                    } else if (TextUtils.equals(selectAccountName.getAreaid(), this.areaList.get(i).getAreaid())) {
                        this.clickBroadbandSelectItemFlag = 0;
                        this.broadbandAreacodeEdittext.setText(this.areaList.get(i).getAreaname());
                        this.areaId = this.userManager.getUserAreaid();
                        break;
                    } else {
                        i++;
                    }
                }
                if ("error".equals(this.keyVersion)) {
                    this.broadbandaccountnameEdittext.setTag(this.intentAccountname);
                    this.broadbandaccountnameEdittext.setText(getJiamiStr(this.intentAccountname));
                } else {
                    this.broadbandaccountnameEdittext.setText(this.intentAccountname);
                }
            }
        } else if ("03".equals(this.userManager.getLoginType())) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.areaList.size()) {
                    break;
                }
                if (TextUtils.isEmpty(this.areaId) && TextUtils.equals(this.userManager.getUserAreaid(), this.areaList.get(i2).getAreaid())) {
                    this.clickBroadbandSelectItemFlag = 0;
                    this.broadbandAreacodeEdittext.setText(this.areaList.get(i2).getAreaname());
                    this.areaId = this.userManager.getUserAreaid();
                    break;
                }
                i2++;
            }
            this.broadbandaccountnameEdittext.setText(this.userManager.getUserAccountName());
        }
        if (this.userManager.getAutoLoginStatus() || "06".equals(this.userManager.getLoginType())) {
            if ("".equals(this.userManager.getUserPassword()) || "".equals(this.userManager.getKeyVersion())) {
                this.userManager.removeUserPassword();
                this.userManager.removeKeyVersion();
            }
        } else {
            this.userManager.removeUserPassword();
            this.userManager.removeKeyVersion();
        }
        this.loginBroadbandPasswordSeeTogglebutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.45
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Tracker.onCheckedChanged(compoundButton, z);
                if (z) {
                    LoginActivity.this.broadbandPasswordEdittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    LoginActivity.this.broadbandPasswordEdittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    private void initShenfenzhengUI() {
        this.shenfenzhengTips = (TextView) findViewById(2131297913);
        this.shenfenzhengLayout = (LinearLayout) findViewById(2131297910);
        this.shenfenzhengAreaEdittext = (CustomAutoCompleteTextView) findViewById(2131297914);
        this.shenfenzhengSelectImageButton = (ImageButton) findViewById(2131297915);
        this.shenfenzhengNumberEdittext = (CustomAutoCompleteTextView) findViewById(2131297909);
        this.shenfenzhengClearImageButton = (ImageButton) findViewById(2131297908);
        this.shenfenzhengClearImageButton.setOnClickListener(this);
        this.shenfenzhengSelectImageButton = (ImageButton) findViewById(2131297912);
        this.shenfenzhengNumberList = this.userManager.getSelectAccountNameList("idcard");
        if (this.shenfenzhengNumberList.size() > 0) {
            this.shenfenzhengSelectImageButton.setVisibility(0);
        } else {
            this.shenfenzhengSelectImageButton.setVisibility(8);
        }
        if (TextUtils.isEmpty(getIdcardNumber())) {
            this.shenfenzhengClearImageButton.setVisibility(4);
        } else {
            this.shenfenzhengClearImageButton.setVisibility(0);
        }
        this.shenfenzhengSelectImageButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.46
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                LoginActivity.this.shenfenzhengNumberEdittext.requestFocus();
                LoginActivity.this.clickShenfenzhengItemFlag = 1;
                LoginActivity.this.shenfenzhengNumberEdittext.append(" ");
                LoginActivity.this.shenfenzhengNumberEdittext.setText("");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.shenfenzhengNumberEdittext.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.47
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (TextUtils.isEmpty(LoginActivity.this.getIdcardNumber())) {
                    LoginActivity.this.clickSelectButtonFlag = 1;
                    LoginActivity.this.shenfenzhengNumberEdittext.append(" ");
                    LoginActivity.this.shenfenzhengNumberEdittext.setText(LoginActivity.this.getIdcardNumber());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.shenfenzhengNumberEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.48
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Tracker.onFocusChange(view, z);
                if (z && TextUtils.isEmpty(LoginActivity.this.getIdcardNumber())) {
                    LoginActivity.this.clickSelectButtonFlag = 1;
                    LoginActivity.this.shenfenzhengNumberEdittext.append(" ");
                    LoginActivity.this.shenfenzhengNumberEdittext.setText(LoginActivity.this.getIdcardNumber());
                }
            }
        });
        this.paShenfenzhengAdapter = new PhoneAdapter(this, 2131493328, this.shenfenzhengNumberList, new PhoneAdapter.OnPhoneAdapterListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.49
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onItemSelected(LoginAccountEntity loginAccountEntity) {
                String str;
                String str2;
                LoginActivity.this.clickShenfenzhengItemFlag = 0;
                String trim = loginAccountEntity.getAccountnameJiami().trim();
                LoginActivity.this.shenfenzhengNumberEdittext.setTag(loginAccountEntity.getAccountname().trim());
                LoginActivity.this.mNewYinsiTextView.setText(LoginActivity.this.getXieyiSpannableString(loginAccountEntity.getAccountname().trim()));
                LoginActivity.this.shenfenzhengTips.setVisibility(0);
                LoginActivity.this.shenfenzhengNumberEdittext.setText(trim);
                LoginActivity.this.shenfenzhengNumberEdittext.clearFocus();
                if (LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().length() >= 6) {
                    String substring = LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().substring(0, 6);
                    Iterator it = LoginActivity.this.areaList.iterator();
                    while (true) {
                        str = null;
                        if (!it.hasNext()) {
                            str2 = null;
                            break;
                        }
                        AreaEntity areaEntity = (AreaEntity) it.next();
                        if (substring.startsWith(areaEntity.getIdNumber())) {
                            str = areaEntity.getAreaname();
                            str2 = areaEntity.getAreaid();
                            break;
                        }
                    }
                    Iterator it2 = LoginActivity.this.areaList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        AreaEntity areaEntity2 = (AreaEntity) it2.next();
                        String idNumber = areaEntity2.getIdNumber();
                        if (idNumber.length() == 6 && substring.equals(idNumber)) {
                            str = areaEntity2.getAreaname();
                            str2 = areaEntity2.getAreaid();
                            break;
                        }
                    }
                    LoginActivity.this.shenfenzhengAreaEdittext.setText(str);
                    LoginActivity.this.shenfenzhengAreaId = str2;
                } else {
                    LoginActivity.this.shenfenzhengAreaEdittext.requestFocus();
                }
                if (TextUtils.isEmpty(LoginActivity.this.shenfenzhengAreaId) || LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().length() != 18 || trim.toString().length() <= 0) {
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                    return;
                }
                LoginActivity.this.loginButton.setEnabled(true);
                LoginActivity.this.loginButton.setBackgroundResource(2131231879);
            }

            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onDeleteItem(LoginAccountEntity loginAccountEntity) {
                LoginActivity.this.shenfenzhengNumberList.remove(loginAccountEntity);
                LoginActivity.this.shenfenzhengNumberEdittext.showDropDown();
                if (LoginActivity.this.shenfenzhengNumberList.size() > 0) {
                    LoginActivity.this.shenfenzhengSelectImageButton.setVisibility(0);
                } else {
                    LoginActivity.this.shenfenzhengSelectImageButton.setVisibility(4);
                }
            }
        });
        this.shenfenzhengNumberEdittext.setAdapter(this.paShenfenzhengAdapter);
        AreaAdapter areaAdapter = new AreaAdapter(this, 2131493329, this.areaList, new AreaAdapter.OnItemAdapterListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.50
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.AreaAdapter.OnItemAdapterListener
            public void onItemSelected(String str, String str2) {
                LoginActivity.this.clickAreaSelectItemFlag = 0;
                LoginActivity.this.shenfenzhengAreaEdittext.setText(str.trim());
                LoginActivity.this.shenfenzhengAreaEdittext.clearFocus();
                LoginActivity.this.shenfenzhengAreaId = str2;
                if (LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().length() == 18) {
                    LoginActivity.this.loginButton.setEnabled(true);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                    return;
                }
                LoginActivity.this.loginButton.setEnabled(false);
                LoginActivity.this.loginButton.setBackgroundResource(2131231880);
            }
        });
        InputFilter inputFilter = new InputFilter() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.51
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return charSequence.equals(" ") ? "" : charSequence;
            }
        };
        this.shenfenzhengAreaEdittext.setAdapter(areaAdapter);
        this.shenfenzhengAreaEdittext.setDropDownHorizontalOffset(0);
        this.shenfenzhengAreaEdittext.setDropDownVerticalOffset(1);
        this.shenfenzhengAreaEdittext.setDropDownBackgroundDrawable(new ColorDrawable(-1));
        this.shenfenzhengAreaEdittext.setFilters(new InputFilter[]{inputFilter});
        this.shenfenzhengAreaEdittext.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.52
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (LoginActivity.access$6508(LoginActivity.this) == 0) {
                    return;
                }
                if (!LoginActivity.this.isAginAreaid) {
                    LoginActivity.this.shenfenzhengAreaId = "";
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                }
                if (LoginActivity.this.clickAreaSelectItemFlag >= 2) {
                    LoginActivity.this.isAginAreaid = false;
                }
            }
        });
        this.shenfenzhengAreaEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.53
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Tracker.onFocusChange(view, z);
                if (!z && LoginActivity.this.clickAreaSelectItemFlag > 1 && TextUtils.isEmpty(LoginActivity.this.shenfenzhengAreaEdittext.getText())) {
                    LoginActivity.this.shenfenzhengAreaEdittext.setText("");
                    LoginActivity.this.shenfenzhengAreaEdittext.setTag("");
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                }
                if (z && TextUtils.isEmpty(LoginActivity.this.shenfenzhengAreaEdittext.getText().toString())) {
                    LoginActivity.this.shenfenzhengAreaEdittext.append(" ");
                    LoginActivity.this.shenfenzhengAreaEdittext.setText(LoginActivity.this.shenfenzhengAreaEdittext.getText().toString().trim());
                }
            }
        });
        this.shenfenzhengAreaEdittext.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.54
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (TextUtils.isEmpty(LoginActivity.this.shenfenzhengAreaEdittext.getText())) {
                    LoginActivity.this.shenfenzhengAreaEdittext.append(" ");
                    LoginActivity.this.shenfenzhengAreaEdittext.setText(LoginActivity.this.shenfenzhengAreaEdittext.getText().toString().trim());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.shenfenzhengNumberEdittext.setFilters(new InputFilter[]{new InputFilter() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.55
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return charSequence.equals(" ") ? "" : (Pattern.matches("^[0-9]|[X|x]|[*]$", charSequence.toString()) || charSequence.length() == 18) ? charSequence : "";
            }
        }, new InputFilter.LengthFilter(18)});
        this.shenfenzhengNumberEdittext.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.56
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LoginActivity.this.paShenfenzhengAdapter.isClickSelectButton = LoginActivity.access$9208(LoginActivity.this) < 2;
                LoginActivity.this.paShenfenzhengAdapter.isClickSelectItem = LoginActivity.access$8408(LoginActivity.this) == 0;
                if (TextUtils.isEmpty(LoginActivity.this.shenfenzhengNumberEdittext.getText().toString())) {
                    LoginActivity.this.shenfenzhengClearImageButton.setVisibility(4);
                } else {
                    LoginActivity.this.shenfenzhengClearImageButton.setVisibility(0);
                }
                LoginActivity.this.shenfenzhengNumberEdittext.setThreshold(1);
                if (TextUtils.isEmpty(LoginActivity.this.shenfenzhengNumberEdittext.getText().toString())) {
                    LoginActivity.this.shenfenzhengClearImageButton.setVisibility(8);
                } else {
                    LoginActivity.this.shenfenzhengClearImageButton.setVisibility(0);
                }
                String str = "";
                String str2 = "";
                if (LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().length() == 6) {
                    String obj = LoginActivity.this.shenfenzhengNumberEdittext.getText().toString();
                    Iterator it = LoginActivity.this.areaList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        AreaEntity areaEntity = (AreaEntity) it.next();
                        if (obj.startsWith(areaEntity.getIdNumber())) {
                            str = areaEntity.getAreaname();
                            str2 = areaEntity.getAreaid();
                            break;
                        }
                    }
                    Iterator it2 = LoginActivity.this.areaList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        AreaEntity areaEntity2 = (AreaEntity) it2.next();
                        String idNumber = areaEntity2.getIdNumber();
                        if (idNumber.length() == 6 && TextUtils.equals(obj, idNumber)) {
                            str = areaEntity2.getAreaname();
                            str2 = areaEntity2.getAreaid();
                            break;
                        }
                    }
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        LoginActivity.this.shenfenzhengAreaEdittext.setText(str);
                        LoginActivity.this.shenfenzhengAreaId = str2;
                    }
                }
                if (LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().length() < 6 && TextUtils.isEmpty(LoginActivity.this.shenfenzhengAreaEdittext.getText())) {
                    LoginActivity.this.shenfenzhengAreaEdittext.setText("");
                    LoginActivity.this.shenfenzhengAreaEdittext.setTag("");
                    LoginActivity.this.shenfenzhengAreaId = "";
                }
                if (LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().length() >= 18 || !LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().contains("*")) {
                    return;
                }
                LoginActivity.this.shenfenzhengNumberEdittext.setText("");
                LoginActivity.this.shenfenzhengNumberEdittext.setTag("");
                LoginActivity.this.shenfenzhengAreaEdittext.setText("");
                LoginActivity.this.shenfenzhengAreaEdittext.setTag("");
                LoginActivity.this.shenfenzhengAreaId = "";
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(LoginActivity.this.shenfenzhengAreaId) || LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().length() != 18 || editable.toString().length() <= 0) {
                    LoginActivity.this.loginButton.setEnabled(false);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231880);
                } else {
                    LoginActivity.this.loginButton.setEnabled(true);
                    LoginActivity.this.loginButton.setBackgroundResource(2131231879);
                }
                if (LoginActivity.this.shenfenzhengNumberEdittext.getText().toString().length() != 18 || editable.toString().length() <= 0) {
                    LoginActivity.this.shenfenzhengTips.setVisibility(8);
                } else {
                    LoginActivity.this.shenfenzhengTips.setVisibility(0);
                }
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$69 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C739669 implements CompoundButton.OnCheckedChangeListener {
        C739669() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Tracker.onCheckedChanged(compoundButton, z);
            if (z) {
                LoginActivity.this.guhuaPasswordEdittext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                LoginActivity.this.guhuaPasswordEdittext.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toXieyi(String str) {
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        switch (view.getId()) {
            case 2131297825:
                back();
                break;
            case 2131297842:
                this.broadbandaccountnameEdittext.setText((CharSequence) null);
                break;
            case 2131297843:
                this.broadbandPasswordEdittext.setText((CharSequence) null);
                break;
            case 2131297844:
                StatisticsUploadUtils.upload(this, "6", "登录", "按钮", "0", "忘记宽带账号", "");
                IntentManager.generateIntentAndGo(this, URLSet.getBroadbandForgetAccount() + "&cityCode=" + this.areaId + "&deviceId=" + DeviceHelper.getDeviceID(true) + "&deviceCode=" + DeviceHelper.getDeviceID(true) + "&version=" + context.getString(2131886969), "忘记宽带账号", false, "get");
                break;
            case 2131297850:
                if (this.isSelectXieYi) {
                    this.isSelectXieYi = false;
                    this.mImgCheck.setImageResource(2131231904);
                    JiaoFeiManager.uploadLogin(this.accountName, "0005取消勾选隐私政策协议", "");
                    break;
                } else {
                    this.isSelectXieYi = true;
                    this.mImgCheck.setImageResource(2131231907);
                    JiaoFeiManager.uploadLogin(this.accountName, "0001主动勾选隐私政策协议", "");
                    break;
                }
            case 2131297851:
                this.accountNameEditText.setText((CharSequence) null);
                break;
            case 2131297852:
                this.passwordEditText.setText((CharSequence) null);
                break;
            case 2131297863:
                StatisticsUploadUtils.upload(this, "6", "登录", "按钮", "0", "忘记密码", "");
                JianDanMiMaUtil.gotoWebiview(context, getWebParams(), null);
                break;
            case 2131297864:
                toXieyi(URLSet.getUserserver());
                break;
            case 2131297865:
                StatisticsUploadUtils.upload(this, "6", "登录", "按钮", "0", "忘记宽带密码", "");
                JianDanMiMaUtil.gotoWebiview(context, getWebParams(), null);
                break;
            case 2131297866:
                StatisticsUploadUtils.upload(this, "6", "登录", "按钮", "0", "忘记固话密码", "");
                JianDanMiMaUtil.gotoWebiview(context, getWebParams(), null);
                break;
            case 2131297873:
                this.guhuaaccountnameEdittext.setText((CharSequence) null);
                break;
            case 2131297874:
                this.guhuaPasswordEdittext.setText((CharSequence) null);
                break;
            case 2131297895:
                toXieyi(URLSet.getunicom_yinsizhengce());
                break;
            case 2131297902:
                this.yinSiXieYiUtil.checkDialog(this.isSelectXieYi, "一键登录", "").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.70
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        if (bool.booleanValue()) {
                            LoginActivity.this.isSelectXieYi = true;
                            LoginActivity.this.mImgCheck.setImageResource(2131231907);
                            LoginActivity loginActivity = LoginActivity.this;
                            ManagerOneKeyLogin.mianmiSDKAccessCode(loginActivity, loginActivity.f18402pd);
                            StatisticsUploadUtils.upload(LoginActivity.this, "6", "登录", "按钮", "0", "免密登录", "");
                        }
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                break;
            case 2131297907:
                StatisticsUploadUtils.upload(this, "6", "登录", "按钮", "0", "注册", "");
                IntentManager.gotoWebViewActivity(context, URLSet.getRegisterURL(), "注册");
                break;
            case 2131297908:
                this.shenfenzhengNumberEdittext.setText((CharSequence) null);
                break;
            case 2131297921:
                this.smsAccountNameEditText.setText((CharSequence) null);
                break;
            case 2131297922:
                this.smsPasswordEditText.setText((CharSequence) null);
                break;
            case 2131297926:
                this.randomStr = this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9);
                this.smsAccountName = getJiemiStr(this.smsAccountNameEditText);
                if (TextUtils.isEmpty(this.smsAccountName)) {
                    UIUtils.toast("请输入手机号码！");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else if (this.smsAccountName.length() != 11) {
                    UIUtils.toast("请输入11位手机号码！");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else if (!"获取验证码".equals(this.login_sms_request_button.getText().toString())) {
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else if (!SystemServiceUtils.netIsAvailable()) {
                    CustomDialogManager.show(this, "", "网络连接失败，请检查网络设置！");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else {
                    this.login_sms_request_button.setTextColor(-4868683);
                    this.login_sms_request_button.setBackgroundResource(2131231886);
                    this.login_sms_request_button.setEnabled(false);
                    changeYuyinYanzheng(false);
                    this.smsHaveRetryNum = 0;
                    try {
                        loadSMSRandomCode("");
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            case 2131297927:
                loginSubmit();
                break;
            case 2131297949:
                this.yinSiXieYiUtil.checkDialog(this.isSelectXieYi, "微信登录", "W").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.71
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        if (bool.booleanValue()) {
                            LoginActivity.this.isSelectXieYi = true;
                            LoginActivity.this.mImgCheck.setImageResource(2131231907);
                            if (App.getWXAPI().isWXAppInstalled()) {
                                SendAuth.Req req = new SendAuth.Req();
                                req.scope = "snsapi_userinfo";
                                req.state = "Login_session";
                                App.getWXAPI().sendReq(req);
                                if (!TextUtils.isEmpty(LoginActivity.this.smsAccountName)) {
                                    LoginActivity.this.loginLogPv.setMobile(LoginActivity.this.smsAccountName);
                                    if (LoginActivity.this.loginLogPv.getSmsCount() > 1) {
                                        LoginActivity.this.loginLogPv.log(LoginLogPv.RE_OTHER_LOGIN_11);
                                    } else if (LoginActivity.this.loginLogPv.getSmsCount() > 0) {
                                        LoginActivity.this.loginLogPv.log(LoginLogPv.FIRST_OTHER_LOGIN_10);
                                    }
                                }
                            } else {
                                UIUtils.toast("您尚未安装微信 无法使用微信登录");
                            }
                            StatisticsUploadUtils.upload(LoginActivity.this, "603", "登录", "按钮", "0", "微信登录", "");
                        }
                    }
                });
                break;
            case 2131297967:
                toXieyi(URLSet.getUserPrivacy());
                break;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCdTimer(String str) {
        if (TextUtils.isEmpty(str)) {
            startCdTimer1();
        } else {
            startCdTimer2();
        }
    }

    private void startCdTimer1() {
        JiaoFeiManager.jiaofeiYichangDialog = false;
        JiaoFeiManager.stopJiaofeiMobile = this.smsAccountName;
        CountDownTimer countDownTimer = this.cdTimer2;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = this.cdTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
            this.cdTimer.start();
            return;
        }
        this.cdTimer = new CountDownTimer(60200L, 1000L) { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.72
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (LoginActivity.this.isFinishing()) {
                    return;
                }
                Button button = LoginActivity.this.login_sms_request_button;
                StringBuilder sb = new StringBuilder();
                long j2 = (20 + j) / 1000;
                sb.append(j2);
                sb.append("s");
                button.setText(sb.toString());
                if ("1".equals(LoginActivity.this.configManager.getYuyinYanzheng()) && j < LoginActivity.this.loginConfigEntity.getVoiceCountDownLenLong()) {
                    LoginActivity.this.changeYuyinYanzheng(true);
                }
                if ("on".equals(LoginActivity.this.loginConfigEntity.getPayGreenChannelSwitch()) && LoginActivity.this.loginConfigEntity.getDelayTimeInt() <= 60 && j2 == 60) {
                    JiaoFeiManager.stopoDisposable(LoginActivity.this.lvseJaiofeiDisposable);
                    LoginActivity loginActivity = LoginActivity.this;
                    loginActivity.lvseJaiofeiDisposable = Observable.timer(loginActivity.loginConfigEntity.getDelayTimeInt(), TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.72.1
                        @Override // io.reactivex.functions.Consumer
                        public void accept(Long l) throws Exception {
                            JiaoFeiManager.getPayMobileState(LoginActivity.context, LoginActivity.this.smsAccountName, LoginActivity.this.paygreenUrl);
                        }
                    });
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                LoginActivity.this.login_sms_request_button.setText("获取验证码");
                LoginActivity.this.login_sms_request_button.setEnabled(true);
                LoginActivity.this.login_sms_request_button.setTextColor(-1703896);
                LoginActivity.this.login_sms_request_button.setBackgroundResource(2131231884);
                if ("1".equals(LoginActivity.this.configManager.getYuyinYanzheng()) && 0 == LoginActivity.this.loginConfigEntity.getVoiceCountDownLenLong()) {
                    LoginActivity.this.changeYuyinYanzheng(true);
                }
            }
        };
        this.cdTimer.start();
    }

    private void startCdTimer2() {
        changeYuyinYanzheng(false);
        CountDownTimer countDownTimer = this.cdTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = this.cdTimer2;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
            this.cdTimer2.start();
            return;
        }
        this.cdTimer2 = new CountDownTimer(60200L, 1000L) { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.73
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (LoginActivity.this.isFinishing()) {
                    return;
                }
                Button button = LoginActivity.this.login_sms_request_button;
                button.setText(((20 + j) / 1000) + "s");
                if (!"1".equals(LoginActivity.this.configManager.getYuyinYanzheng()) || j >= LoginActivity.this.loginConfigEntity.getVoiceCountDownLenLong()) {
                    return;
                }
                LoginActivity.this.changeYuyinYanzheng(true);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                LoginActivity.this.login_sms_request_button.setText("获取验证码");
                LoginActivity.this.login_sms_request_button.setEnabled(true);
                LoginActivity.this.login_sms_request_button.setTextColor(-1703896);
                LoginActivity.this.login_sms_request_button.setBackgroundResource(2131231884);
                LoginActivity.this.changeYuyinYanzheng(true);
            }
        };
        this.cdTimer2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCdTimer(String str) {
        if (TextUtils.isEmpty(str)) {
            resetCdTimer1();
        } else {
            resetCdTimer2();
        }
    }

    private void resetCdTimer1() {
        CountDownTimer countDownTimer = this.cdTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = this.cdTimer2;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        this.login_sms_request_button.setText("获取验证码");
        this.login_sms_request_button.setEnabled(true);
        this.login_sms_request_button.setTextColor(-1703896);
        this.login_sms_request_button.setBackgroundResource(2131231884);
    }

    private void resetCdTimer2() {
        resetCdTimer1();
        if ("1".equals(this.configManager.getYuyinYanzheng())) {
            changeYuyinYanzheng(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginSubmit() {
        if (isFastClick()) {
            return;
        }
        this.yinSiXieYiUtil.checkDialog(this.isSelectXieYi, this.accountName, this.loginType).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$Gh2hj1PCLbk0Hi82jb6nh68eLUM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LoginActivity.lambda$loginSubmit$0(LoginActivity.this, (Boolean) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    public static /* synthetic */ void lambda$loginSubmit$0(LoginActivity loginActivity, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            loginActivity.isSelectXieYi = true;
            loginActivity.mImgCheck.setImageResource(2131231907);
            try {
                ((InputMethodManager) loginActivity.getSystemService("input_method")).hideSoftInputFromWindow(loginActivity.getCurrentFocus().getWindowToken(), 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            loginActivity.userManager.saveAutoLoginStatus(true);
            RequestParams requestParams = new RequestParams();
            switch (loginActivity.login_type_rg.getCheckedRadioButtonId()) {
                case 2131297940:
                    if ("1".equals(loginActivity.loginType)) {
                        loginActivity.accountName = loginActivity.getJiemiStr(loginActivity.accountNameEditText);
                        loginActivity.inputPassword = loginActivity.passwordEditText.getText().toString().trim();
                        if (TextUtils.isEmpty(loginActivity.accountName)) {
                            UIUtils.toast("请输入手机号码！");
                            return;
                        } else if (loginActivity.accountName.length() != 11) {
                            UIUtils.toast("请输入11位手机号码！");
                            return;
                        } else if (!Pattern.matches("^1[3456789]\\d{9}$", loginActivity.accountName)) {
                            UIUtils.toastCenterLong("请输入正确的手机号码！");
                            return;
                        } else {
                            StatisticsUploadUtils.upload(loginActivity, "6", "服务密码登录", "按钮", "0", "登录", "");
                            requestParams.put("simCount", DeviceInfoStatistics.isHasSim(context) ? "1" : "0");
                            loginActivity.prepareLogin(requestParams);
                            return;
                        }
                    }
                    StatisticsUploadUtils.upload(loginActivity, "6", "短信验证码登录", "按钮", "0", "短信验证码登录", "");
                    loginActivity.userManager.saveAutoLoginStatus(true);
                    loginActivity.accountName = loginActivity.getJiemiStr(loginActivity.smsAccountNameEditText);
                    loginActivity.inputPassword = loginActivity.smsPasswordEditText.getText().toString().trim();
                    if (TextUtils.isEmpty(loginActivity.accountName)) {
                        UIUtils.toastCenterLong("请输入手机号码！");
                        return;
                    } else if (loginActivity.accountName.length() != 11) {
                        UIUtils.toastCenterLong("请输入11位手机号码！");
                        return;
                    } else if (!Pattern.matches("^1[3456789]\\d{9}$", loginActivity.accountName)) {
                        UIUtils.toastCenterLong("请输入正确的手机号码！");
                        return;
                    } else if (TextUtils.isEmpty(loginActivity.inputPassword)) {
                        UIUtils.toastCenterLong("请输入您收到的验证码！");
                        return;
                    } else {
                        RequestParams requestParams2 = new RequestParams();
                        requestParams2.put("loginStyle", loginActivity.loginStyle);
                        requestParams2.put("voice_code", loginActivity.voice_code);
                        requestParams2.put("voiceoff_flag", loginActivity.configManager.getYuyinYanzheng());
                        loginActivity.prepareLogin(requestParams2);
                        return;
                    }
                case 2131297941:
                    if ("5".equals(loginActivity.loginType)) {
                        if (!Pattern.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}([0-9]|[x|X])$", loginActivity.getIdcardNumber())) {
                            CustomDialogManager.show((Activity) context, "温馨提示", "您输入的身份证号码不正确，请输入18位数字号码", false, "", "确定", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.74
                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                                public void onClickOk() {
                                }
                            });
                            return;
                        }
                        String str = "";
                        try {
                            str = URLEncoder.encode(Base64.encodeToString(RSACryptos.encryptByPublicKey((loginActivity.getIdcardNumber() + (loginActivity.random.nextInt(9) + "" + loginActivity.random.nextInt(9) + "" + loginActivity.random.nextInt(9) + "" + loginActivity.random.nextInt(9) + "" + loginActivity.random.nextInt(9) + "" + loginActivity.random.nextInt(9))).getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2), "UTF-8");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        MsLogUtil.m7979d("LoginActivityBraod", "首次登录标识firstLogin：" + ConfigManager.getIdcardKeyIsFirst(loginActivity.getIdcardNumber()) + "   \n 签名sig=" + ConfigManager.getIdCardKey(loginActivity.getIdcardNumber()));
                        idCardBoradLoginJiami = str;
                        idCardBoradLogin = loginActivity.getIdcardNumber();
                        UserManager.getInstance().setIdCardKey(idCardBoradLogin);
                        UserManager.getInstance().setIdCardKeyJiaMi(idCardBoradLoginJiami);
                        IntentManager.gotoWebViewActivity(context, URLSet.getBroadBandInfo() + "?version=" + context.getResources().getString(2131886969) + "&idCard=" + str + "&cityCode=" + loginActivity.shenfenzhengAreaId + "&deviceId=" + DeviceHelper.getDeviceID(true) + "&deviceCode=" + DeviceHelper.getDeviceID(true) + "&pip=" + SystemServiceUtils.getLocalIpAddress() + "&firstLogin=" + ConfigManager.getIdcardKeyIsFirst(loginActivity.getIdcardNumber()) + "&sig=" + ConfigManager.getIdCardKey(loginActivity.getIdcardNumber()), "");
                        loginActivity.userManager.saveSelectAccountName(loginActivity.getIdcardNumber(), "", "idcard", "", "", "0", "");
                        ConfigManager.setLoginYinsiVersion(loginActivity.getIdcardNumber());
                        return;
                    }
                    StatisticsUploadUtils.upload(loginActivity, "6", "宽带登录", "按钮", "0", "登录", "");
                    String trim = loginActivity.broadbandAreacodeEdittext.getText().toString().trim();
                    loginActivity.accountName = loginActivity.getJiemiStr(loginActivity.broadbandaccountnameEdittext);
                    loginActivity.inputPassword = loginActivity.broadbandPasswordEdittext.getText().toString().trim();
                    if ("".equals(trim)) {
                        UIUtils.toastCenterLong("请选择区号");
                        return;
                    }
                    requestParams.put("areaCode", loginActivity.areaId);
                    requestParams.put("userType", "03");
                    loginActivity.prepareLogin(requestParams);
                    return;
                case 2131297942:
                    StatisticsUploadUtils.upload(loginActivity, "6", "固话登录", "按钮", "0", "登录", "");
                    String trim2 = loginActivity.guhuaAreacodeEdittext.getText().toString().trim();
                    loginActivity.accountName = loginActivity.getJiemiStr(loginActivity.guhuaaccountnameEdittext);
                    loginActivity.inputPassword = loginActivity.guhuaPasswordEdittext.getText().toString().trim();
                    if ("".equals(trim2)) {
                        UIUtils.toastCenterLong("请选择区号");
                        return;
                    }
                    requestParams.put("areaCode", loginActivity.guhuaAreaId);
                    requestParams.put("zipCode", LoginAreaUtil.getAreaCode(loginActivity.guhuaAreaId, loginActivity.areaList));
                    loginActivity.userManager.saveUserAreaCode(loginActivity.guhuaAreaId);
                    requestParams.put("userType", "02");
                    loginActivity.prepareLogin(requestParams);
                    return;
                default:
                    return;
            }
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            back();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$75 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C740475 extends AsyncHttpResponseHandler {
        final /* synthetic */ RequestParams val$params;

        C740475(RequestParams requestParams) {
            this.val$params = requestParams;
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onStart() {
            super.onStart();
            LoginActivity.this.f18402pd.setMessage("正在登录 请稍候");
            LoginActivity.this.pdShow();
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFinish() {
            super.onFinish();
            App.clearPersistentCookiesList();
            UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
            LoginActivity.this.loginHttp(this.val$params);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginHttp(final RequestParams requestParams) {
        JiaoFeiManager.jiaofeiYichangDialog = true;
        JiaoFeiManager.stopoDisposable(this.lvseJaiofeiDisposable);
        int loginTimeout = this.haveRetryNum > 0 ? this.configManager.getLoginTimeout() + 10 : this.configManager.getLoginTimeout();
        int loginTimeout2 = this.configManager.getLoginTimeout();
        int loginTimeout3 = this.configManager.getLoginTimeout();
        final String switchLoginURL = switchLoginURL();
        if (!TextUtils.isEmpty(LoginFilterUtil.resultToken)) {
            requestParams.put("resultToken", LoginFilterUtil.resultToken);
        }
        requestParams.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
        LoginParamsEntity headerAndBody = DongtaiMiyaoUtils.getHeaderAndBody(this.accountName, requestParams.getRealParams());
        App.getAsyncHttpClient(loginTimeout, loginTimeout2, loginTimeout3).post(switchLoginURL, headerAndBody.getBodyMap(), headerAndBody.getHeaderMap(), new AsyncHttpResponseHandler(1) { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.76
            /*  JADX ERROR: Failed to decode insn: 0x05E4: UNKNOWN(0xA873), method: com.sinovatech.unicom.basic.ui.activity.LoginActivity.76.onSuccess(int, java.lang.String):void
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x05E4: UNKNOWN(0xA873)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
                	at jadx.core.ProcessClass.process(ProcessClass.java:67)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
                */
            /*  JADX ERROR: Failed to decode insn: 0x05F8: UNKNOWN(0x007A), method: com.sinovatech.unicom.basic.ui.activity.LoginActivity.76.onSuccess(int, java.lang.String):void
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x05F8: UNKNOWN(0x007A)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
                	at jadx.core.ProcessClass.process(ProcessClass.java:67)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
                */
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int r23, java.lang.String r24) {
                /*
                    Method dump skipped, instructions count: 1648
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.LoginActivity.C740576.onSuccess(int, java.lang.String):void");
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                if (!"0".equals(LoginActivity.this.ywMap.get(LoginActivity.this.accountName))) {
                    App.setLogined(LoginStateConst.DOING_NORMAL_LOGIN);
                }
                try {
                    if (LoginActivity.this.haveRetryNum > 0) {
                        LoginActivity.this.f18402pd.setMessage("重新连接服务 请稍候");
                    } else {
                        LoginActivity.this.f18402pd.setMessage("正在登录 请稍候");
                    }
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                }
                LoginActivity.this.pdShow();
            }

            /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$76$4 */
            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            class C74094 implements Consumer<Boolean> {
                C74094() {
                }

                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    if (TextUtils.equals("1", LoginActivity.this.loginType)) {
                        LoginActivity.this.smsAccountNameEditText.setText(LoginActivity.this.getJiamiStr(LoginActivity.this.getJiemiStr(LoginActivity.this.accountNameEditText)));
                        LoginActivity.this.smsAccountNameEditText.setTag(LoginActivity.this.getJiemiStr(LoginActivity.this.accountNameEditText));
                        LoginActivity.this.loginType = "3";
                        LoginActivity.this.smsAccountNameEditText.setTag(LoginActivity.this.getJiemiStr(LoginActivity.this.accountNameEditText));
                        LoginActivity.this.switchLayout(LoginActivity.this.loginType);
                    } else if (TextUtils.equals("2", LoginActivity.this.loginType)) {
                        LoginActivity.this.loginType = "5";
                        LoginActivity.this.switchLayout(LoginActivity.this.loginType);
                    }
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str) {
                super.onFailure(th, str);
                th.printStackTrace();
                LoginActivity.this.pdDissmiss();
                App.setLogined(LoginStateConst.UNLOGIN);
                StringBuffer stringBuffer = LoginActivity.this.errorLogBuffer;
                stringBuffer.append("第" + (LoginActivity.this.haveRetryNum + 1) + "次请求失败(" + th.toString() + " " + str + ")");
                if (IOException.class.isInstance(th) && LoginActivity.this.haveRetryNum < LoginActivity.this.configManager.getLoginRetryNum()) {
                    LoginActivity.access$11708(LoginActivity.this);
                    LoginActivity.this.loginHttp(requestParams);
                    return;
                }
                LoginActivity loginActivity = LoginActivity.this;
                LogFileUploadUtils.writeLoginErrorLog(loginActivity, loginActivity.accountName, TextUtils.isEmpty(LoginActivity.this.loginStyle) ? "手动登录" : "短信验证码登录", LoginActivity.this.errorLogBuffer.toString(), str, th);
                LoginManager.showLoginNetWorkErrorMessage(LoginActivity.this, th, true);
                LogFileUploadUtils.upload(LoginActivity.this);
                DeviceInfoStatistics.uploadTianYuan("1", "01", "02", LoginActivity.this.accountName);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_START_WEBSOCKET));
                PushManager.getInstance().pushSwitch(LoginActivity.context, LoginActivity.this.preference.getBoolean("isAllowNotification"));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDissmiss() {
        try {
            if (isDestroyed() || isFinishing() || this.f18402pd == null || !this.f18402pd.isShowing()) {
                return;
            }
            this.f18402pd.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdShow() {
        try {
            if (isFinishing() || this.f18402pd == null || this.f18402pd.isShowing()) {
                return;
            }
            this.f18402pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveErrorAccount() {
        String str;
        String str2;
        if ("4".equals(this.loginType)) {
            str = this.guhuaAreaId;
        } else {
            str = "2".equals(this.loginType) ? this.areaId : "";
        }
        if ("1".equals(this.loginType)) {
            str2 = "01";
        } else if ("2".equals(this.loginType)) {
            str2 = "03";
        } else if ("3".equals(this.loginType)) {
            str2 = "06";
        } else {
            str2 = "4".equals(this.loginType) ? "02" : "01";
        }
        LoginAccountEntity selectAccountName = this.userManager.getSelectAccountName(this.accountName, "1");
        if (selectAccountName == null || "error".equals(selectAccountName.getKeyversion())) {
            this.userManager.saveSelectAccountName(this.accountName, str, str2, "", "error", "1", "", "", selectAccountName != null ? selectAccountName.getIntact() : "");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$77 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C741077 implements Consumer<Boolean> {
        final /* synthetic */ String val$type;

        C741077(String str) {
            this.val$type = str;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Boolean bool) throws Exception {
            if (bool.booleanValue()) {
                LoginActivity.this.isSelectXieYi = true;
                LoginActivity.this.mImgCheck.setImageResource(2131231907);
                RequestParams requestParams = new RequestParams();
                String str = LoginActivity.this.smsAccountName + LoginActivity.this.randomStr;
                try {
                    requestParams.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(str.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
                } catch (Exception e) {
                    requestParams.put("mobile", EncodeHelper.encodeByAES(str));
                    e.printStackTrace();
                }
                requestParams.put("keyVersion", LoginActivity.this.userManager.getKeyVersion());
                requestParams.put("version", LoginActivity.this.getString(2131886969));
                requestParams.put("send_flag", this.val$type);
                requestParams.put("loginCodeLen", LoginActivity.this.loginConfigEntity.getLoginCodeLen());
                requestParams.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
                requestParams.put(LoginActivity.context.getString(2131886495), DeviceHelper.getAndroidId());
                requestParams.put("resultToken", LoginFilterUtil.resultToken);
                requestParams.put("deviceId", DeviceHelper.getDeviceID(true));
                requestParams.put("deviceCode", DeviceHelper.getDeviceID(true));
                requestParams.put("netWay", DeviceHelper.getNETType(LoginActivity.this.getApplicationContext()));
                requestParams.put("deviceBrand", DeviceHelper.getDeviceBrand());
                requestParams.put("deviceModel", DeviceHelper.getDeviceModel());
                requestParams.put("deviceOS", DeviceHelper.getDeviceOSVersion());
                requestParams.put("appId", LoginActivity.this.userManager.getLoginAppId());
                requestParams.put("pip", SystemServiceUtils.getLocalIpAddress());
                TYCJAddressEntity addressEntity = TYCJAddressUtil.getAddressEntity();
                requestParams.put("provinceCode", addressEntity.getLocateProvinceCode());
                requestParams.put("cityCode", addressEntity.getLocateCityCode());
                LoginActivity.this.loadSMSRandomCode(this.val$type, requestParams);
            } else if ("获取验证码".equals(LoginActivity.this.login_sms_request_button.getText().toString())) {
                LoginActivity.this.resetCdTimer(this.val$type);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void loadSMSRandomCode(String str) {
        this.yinSiXieYiUtil.checkDialog(this.isSelectXieYi, this.smsAccountName, "SMS").subscribe(new C741077(str), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.78
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSMSRandomCode(String str, RequestParams requestParams) {
        LoginParamsEntity headerAndBody = DongtaiMiyaoUtils.getHeaderAndBody(this.smsAccountName, requestParams.getRealParams());
        App.getAsyncHttpClient(this.configManager.getLoginTimeout(), this.configManager.getLoginTimeout(), this.configManager.getLoginTimeout()).post(URLSet.getRandomPswdURL(), headerAndBody.getBodyMap(), headerAndBody.getHeaderMap(), new C741279(2, str, requestParams));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$79 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C741279 extends AsyncHttpResponseHandler {
        final /* synthetic */ RequestParams val$sms_params;
        final /* synthetic */ String val$type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C741279(int i, String str, RequestParams requestParams) {
            super(i);
            this.val$type = str;
            this.val$sms_params = requestParams;
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onSuccess(int i, String str) {
            super.onSuccess(i, str);
            LoginActivity.this.resetCdTimer(this.val$type);
            if (LoginFilterUtil.filerLogin(LoginActivity.context, str, new LoginFilterUtil.CallBackInterface() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.79.1
                @Override // com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.CallBackInterface
                public void complete(String str2) {
                    if (TextUtils.isEmpty(str2)) {
                        return;
                    }
                    C741279.this.val$sms_params.put("resultToken", str2);
                    LoginActivity.this.login_sms_request_button.setTextColor(-4868683);
                    LoginActivity.this.login_sms_request_button.setBackgroundResource(2131231886);
                    LoginActivity.this.login_sms_request_button.setEnabled(false);
                    LoginActivity.this.loadSMSRandomCode(C741279.this.val$type, C741279.this.val$sms_params);
                }
            })) {
                return;
            }
            if (TextUtils.isEmpty(this.val$type)) {
                try {
                    if (!App.isSuccessful(i)) {
                        throw new RuntimeException("获取短信验证码接口返回错误的状态码[" + i + "]");
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    String optString = jSONObject.optString("rsp_code");
                    String optString2 = jSONObject.optString("rsp_desc");
                    String optString3 = jSONObject.optString("yw_code");
                    LoginActivity.this.voice_code = "";
                    LoginActivity.this.ywMap.put(LoginActivity.this.getJiemiStr(LoginActivity.this.smsAccountNameEditText), optString3);
                    DeviceInfoStatistics.uploadTianYuan("1", "00", "01", LoginActivity.this.smsAccountName);
                    if ("0000".equals(optString)) {
                        LoginActivity.this.loginLogPv.setMobile(LoginActivity.this.smsAccountName);
                        if (LoginActivity.this.loginLogPv.getSmsCount() > 0) {
                            LoginActivity.this.loginLogPv.log(LoginLogPv.SMS_RESEND_03);
                        }
                        LoginActivity.this.loginLogPv.addSmsCount();
                        LoginFilterUtil.resultToken = "";
                        if (!TextUtils.isEmpty(optString2)) {
                            UIUtils.toastCenterLong(optString2);
                        }
                        LoginActivity.this.startCdTimer(this.val$type);
                        LoginActivity.this.smsPasswordEditText.requestFocus();
                        LoginActivity.this.showSoftInput(LoginActivity.this.smsPasswordEditText);
                        return;
                    } else if (TextUtils.isEmpty(optString2)) {
                        return;
                    } else {
                        CustomDialogManager.show(LoginActivity.context, "温馨提示", optString2);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!App.isSuccessful(i)) {
                        LoginActivity loginActivity = LoginActivity.this;
                        CustomDialogManager.show(loginActivity, "", "获取短信验证码异常，请重试【ECS" + i + "】");
                    } else {
                        CustomDialogManager.show(LoginActivity.this, "", "获取短信验证码异常，请重试【ECS0003】");
                    }
                    LoginActivity loginActivity2 = LoginActivity.this;
                    LogFileUploadUtils.writeLoginErrorLog(loginActivity2, loginActivity2.smsAccountName, "获取短信验证码", "--", str, e);
                    DeviceInfoStatistics.uploadTianYuan("1", "00", "02", LoginActivity.this.accountName);
                    return;
                }
            }
            try {
                if (!App.isSuccessful(i)) {
                    throw new RuntimeException("获取语音验证码异常，请重试【ECS" + i + "】");
                }
                JSONObject jSONObject2 = new JSONObject(str);
                String optString4 = jSONObject2.optString("code");
                String optString5 = jSONObject2.optString("dsc");
                LoginActivity.this.voice_code = jSONObject2.optString("send_flag");
                if (TextUtils.isEmpty(optString4)) {
                    optString4 = jSONObject2.optString("rsp_code");
                    optString5 = jSONObject2.optString("rsp_desc");
                }
                if ("0".equals(optString4)) {
                    LoginActivity.this.loginLogPv.setMobile(LoginActivity.this.smsAccountName);
                    if (LoginActivity.this.loginLogPv.getVoiceCount().intValue() == 0) {
                        LoginActivity.this.loginLogPv.log(LoginLogPv.VOICE_SEND_06);
                    } else {
                        LoginActivity.this.loginLogPv.log(LoginLogPv.RE_VOICE_SEND_07);
                    }
                    LoginActivity.this.loginLogPv.addVoiceCount();
                    CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "温馨提示", TextUtils.isEmpty(optString5) ? "我们将以电话的方式告诉您验证码，请注意接听" : optString5, false, "", "知道了", false, (CustomDialogManager.CustomeDialogListener) new C74142());
                    LoginFilterUtil.resultToken = "";
                    LoginActivity.this.startCdTimer(this.val$type);
                } else if (!TextUtils.isEmpty(optString5)) {
                    CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "温馨提示", optString5);
                }
                DeviceInfoStatistics.uploadTianYuan("1", "00", "01", LoginActivity.this.smsAccountName);
            } catch (Exception e2) {
                CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "温馨提示", "系统或网络异常，请稍后重试");
                DeviceInfoStatistics.uploadTianYuan("1", "00", "02", LoginActivity.this.accountName);
                e2.printStackTrace();
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$79$2 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C74142 implements CustomDialogManager.CustomeDialogListener {
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

            C74142() {
            }

            /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$79$2$1 */
            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            class RunnableC74151 implements Runnable {
                RunnableC74151() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    LoginActivity.this.smsPasswordEditText.requestFocus();
                    LoginActivity.this.showSoftInput(LoginActivity.this.smsPasswordEditText);
                }
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                new Handler().postDelayed(new RunnableC74151(), 500L);
            }
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFailure(Throwable th, String str) {
            super.onFailure(th, str);
            if (TextUtils.isEmpty(this.val$type)) {
                if (IOException.class.isInstance(th) && LoginActivity.this.smsHaveRetryNum < LoginActivity.this.configManager.getLoginRetryNum()) {
                    LoginActivity.access$2608(LoginActivity.this);
                    try {
                        LoginActivity.this.loadSMSRandomCode("");
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                CustomDialogManager.show(LoginActivity.this, "", "网络连接异常，请您重新获取验证码");
                LoginActivity loginActivity = LoginActivity.this;
                LogFileUploadUtils.writeLoginErrorLog(loginActivity, loginActivity.smsAccountName, "获取短信验证码", "--", str, th);
            } else {
                CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "温馨提示", "系统或网络异常，请稍后重试");
            }
            LoginActivity.this.resetCdTimer(this.val$type);
            DeviceInfoStatistics.uploadTianYuan("1", "00", "02", LoginActivity.this.smsAccountName);
        }
    }

    private String switchLoginURL() {
        String randomLoginURL;
        if (TextUtils.isEmpty(this.loginStyle)) {
            boolean z = false;
            if ((this.haveRetryNum == this.configManager.getLoginRetryNum() && this.haveRetryNum > 1) && this.configManager.getConfig_IPLink_Key().booleanValue()) {
                z = true;
            }
            randomLoginURL = z ? URLSet.productionLoginIPURL : URLSet.getLoginURL();
            if (!TextUtils.isEmpty(this.smsAccountName)) {
                this.loginLogPv.setMobile(this.smsAccountName);
                if (this.loginLogPv.getSmsCount() > 1) {
                    this.loginLogPv.log(LoginLogPv.RE_OTHER_LOGIN_11);
                } else if (this.loginLogPv.getSmsCount() > 0) {
                    this.loginLogPv.log(LoginLogPv.FIRST_OTHER_LOGIN_10);
                }
            }
        } else {
            randomLoginURL = URLSet.getRandomLoginURL();
            this.loginLogPv.setMobile(this.smsAccountName);
            if (this.loginLogPv.getSmsCount() > 1 && this.loginLogPv.getSmsCount() > this.loginLogPv.getVoiceCount().intValue()) {
                this.loginLogPv.log(LoginLogPv.RE_SMS_LOGIN_04);
            } else if (this.loginLogPv.getVoiceCount().intValue() > 1 && this.loginLogPv.getVoiceCount().intValue() > this.loginLogPv.getSmsCount()) {
                this.loginLogPv.log(LoginLogPv.VOICE_LOGIN_08);
            } else {
                this.loginLogPv.log(LoginLogPv.SMS_LOGIN_01);
            }
        }
        return randomLoginURL;
    }

    private void back() {
        this.loginLogPv.setMobile(this.smsAccountName);
        this.loginLogPv.setLoginType(this.loginType);
        if (this.loginLogPv.getVoiceCount().intValue() > 0 && this.loginLogPv.getVoiceCount().intValue() > this.loginLogPv.getSmsCount()) {
            this.loginLogPv.log(LoginLogPv.VOICE_LEAVE_09);
        } else if (this.loginLogPv.getSmsCount() > 1 && this.loginLogPv.getSmsCount() > this.loginLogPv.getVoiceCount().intValue()) {
            this.loginLogPv.log(LoginLogPv.RE_LOGIN_LEAVE_05);
        } else if (this.loginLogPv.getSmsCount() > 0) {
            this.loginLogPv.log(LoginLogPv.LOGIN_LEAVE_02);
        }
        if ("fromLockPatternbackground".equals(this.fromLockPatternbackground)) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("clear", "clear");
            startActivity(intent);
            finish();
            return;
        }
        setResult(0);
        finish();
    }

    public void directAccess() {
        UIUtils.logD("登录", "登录完成》》》directAccess");
        if (this.userManager.isYiwang()) {
            EventBusUtils.post(new YWEvent(EventBusUtils.EVENT_YW_lOGIN));
        } else {
            EventBusUtils.post(new YWEvent(EventBusUtils.EVENT_YW_lOGOUT));
        }
        String str = this.fromLockPattern;
        if (str != null && str.equals("fromLockPattern")) {
            startActivity(new Intent(this, MainActivity.class));
            setResult(-1);
            finish();
            return;
        }
        if (this.directAccess && !TextUtils.isEmpty(this.intentEntranceURL) && !IntentManager.handleLocal(this, this.intentTitle, this.intentEntranceURL) && ((!"YES".equals(this.broadband) || "03".equals(UserManager.getInstance().getLoginType())) && (!"YES".equals(this.guhuaIntentManager) || "02".equals(UserManager.getInstance().getLoginType())))) {
            IntentManager.gotoWebViewActivity(context, this.intentEntranceURL, this.intentTitle, this.intentBackMode, this.intentRequestType);
        }
        setResult(-1);
        finish();
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$Receiver */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class Receiver extends BroadcastReceiver {
        public Receiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                for (Object obj : (Object[]) intent.getExtras().get("pdus")) {
                    SmsMessage createFromPdu = SmsMessage.createFromPdu((byte[]) obj);
                    if (createFromPdu.getOriginatingAddress().indexOf("10010") != -1) {
                        Matcher matcher = Pattern.compile("\\d{6}").matcher(createFromPdu.getMessageBody());
                        if (matcher.find()) {
                            LoginActivity.this.smsPasswordEditText.setText(createFromPdu.getMessageBody().subSequence(matcher.start(), matcher.end()));
                        } else {
                            LoginActivity.this.smsPasswordEditText.setText("");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCustemLoginErrorDialog(String str, final String str2, String str3, final String str4, boolean z) {
        CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "", str, true, str3, str2, true, z, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.80
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                if ("短信验证码登录".equals(str2)) {
                    LoginActivity loginActivity = LoginActivity.this;
                    LoginActivity.this.smsAccountNameEditText.setText(loginActivity.getJiamiStr(loginActivity.getJiemiStr(loginActivity.accountNameEditText)));
                    LoginActivity loginActivity2 = LoginActivity.this;
                    LoginActivity.this.smsAccountNameEditText.setTag(loginActivity2.getJiemiStr(loginActivity2.accountNameEditText));
                    LoginActivity.this.loginType = "3";
                    CustomAutoCompleteTextView customAutoCompleteTextView = LoginActivity.this.smsAccountNameEditText;
                    LoginActivity loginActivity3 = LoginActivity.this;
                    customAutoCompleteTextView.setTag(loginActivity3.getJiemiStr(loginActivity3.accountNameEditText));
                    LoginActivity loginActivity4 = LoginActivity.this;
                    loginActivity4.switchLayout(loginActivity4.loginType);
                    if (LoginActivity.this.configManager.getAutoSms()) {
                        LoginActivity.this.login_sms_request_button.performClick();
                    }
                }
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
                if ("4".equals(str4)) {
                    JianDanMiMaUtil.gotoWebiview(LoginActivity.context, LoginActivity.this.getWebParams(), null);
                } else if ("2".equals(str4) && "7".equals(LoginActivity.this.currentCode)) {
                    LoginActivity.this.refreshTuxing();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKuandaiLoginErrorDialog(String str, String str2, String str3) {
        CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "", str, true, str3, str2, true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.81
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
                if ("4".equals(LoginActivity.this.loginType)) {
                    JianDanMiMaUtil.gotoWebiview(LoginActivity.context, LoginActivity.this.getWebParams(), null);
                    return;
                }
                LoginActivity.this.loginType = "5";
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.switchLayout(loginActivity.loginType);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showKuandaiLoginErrorDialog2(String str, String str2, String str3) {
        CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "", str, false, str3, str2, true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.82
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
            public void onClickOk() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shenfenzhengLoginErrorDialog(String str, String str2, String str3) {
        CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "", str, true, str3, str2, true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.83
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onBackKeyDown() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onCancel() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onShow() {
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickOk() {
                LoginActivity.this.loginType = "5";
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.switchLayout(loginActivity.loginType);
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
            public void onClickCancel() {
                JianDanMiMaUtil.gotoWebiview(LoginActivity.context, LoginActivity.this.getWebParams(), null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shenfenzhengLoginErrorDialog2(String str, String str2, String str3) {
        CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "", str, false, "", str2, true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.84
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
                JianDanMiMaUtil.gotoWebiview(LoginActivity.context, LoginActivity.this.getWebParams(), null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFreezeDialog(String str) {
        CustomDialogManager.showZhuXiao(SoulPermission.getInstance().getTopActivity(), "号码已注销", LoginConstUtil.ZHUXIAOMSG, true, "取消", "立即激活", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.85
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
                String str2 = "";
                if ("1".equals(LoginActivity.this.loginType) || "3".equals(LoginActivity.this.loginType)) {
                    str2 = "01";
                } else if ("2".equals(LoginActivity.this.loginType)) {
                    str2 = "03";
                    String unused = LoginActivity.this.areaId;
                } else if ("4".equals(LoginActivity.this.loginType)) {
                    str2 = "02";
                    String unused2 = LoginActivity.this.guhuaAreaId;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("type", str2);
                IntentManager.gotoWebViewActivityWithParams(LoginActivity.context, URLSet.getFreezeHtml(), "", hashMap);
            }
        });
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < 1000;
        lastClickTime = currentTimeMillis;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getIdcardNumber() {
        String obj = this.shenfenzhengNumberEdittext.getText().toString();
        try {
            return obj.contains("*") ? this.shenfenzhengNumberEdittext.getTag().toString().trim() : obj;
        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        }
    }

    private String getProvinceData(Context context2) {
        try {
            InputStream open = context2.getAssets().open("city_select_json.json");
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

    private List<CitySelectEntity> getList(String str) {
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
    public void updateActivityStatus(String str) {
        List<BindNumberActivityEntity> find = this.bnaBox.query().equal(BindNumberActivityEntity_.mobile, str).build().find();
        if (find == null || find.size() <= 0) {
            return;
        }
        BindNumberActivityEntity bindNumberActivityEntity = find.get(0);
        if (TextUtils.isEmpty(bindNumberActivityEntity.getMobile()) || TextUtils.isEmpty(bindNumberActivityEntity.getUpdateTime())) {
            return;
        }
        bindNumberActivityEntity.setClickTimeForCheckUpdated(bindNumberActivityEntity.getUpdateTime());
        this.bnaBox.put((Box<BindNumberActivityEntity>) bindNumberActivityEntity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getJiamiStr(String str) {
        try {
            return str.substring(0, 3) + "****" + str.substring(str.length() - 4, str.length());
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getJiemiStr(EditText editText) {
        String obj = editText.getText().toString();
        try {
            return (TextUtils.isEmpty(obj) || !obj.contains("*")) ? obj : (String) editText.getTag();
        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTuxing() {
        RequestParams requestParams = new RequestParams();
        this.randomStr = this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9);
        StringBuilder sb = new StringBuilder();
        sb.append(this.accountName);
        sb.append(this.randomStr);
        String sb2 = sb.toString();
        try {
            requestParams.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(sb2.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
        } catch (Exception e) {
            requestParams.put("mobile", EncodeHelper.encodeByAES(sb2));
            e.printStackTrace();
        }
        requestParams.put("version", getString(2131886969));
        App.getAsyncHttpClient().post(URLSet.getYanzhemgma(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.86
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                LoginActivity.this.f18402pd.setMessage("正在刷新请稍后");
                LoginActivity.this.pdShow();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str) {
                super.onSuccess(i, str);
                try {
                    LoginActivity.this.handleTuxingma(new JSONObject(str));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    UIUtils.toast("图形验证码获取失败，请重试");
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str) {
                super.onFailure(th, str);
                UIUtils.toast("图形验证码获取失败，请重试");
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                LoginActivity.this.pdDissmiss();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void recevieWeixinCode(WeixinEvent weixinEvent) {
        UIUtils.logD("LoginActivity", "接收到微信事件：" + weixinEvent.getData().toString());
        ManagerWeixinBind.checkIsBind(this, weixinEvent);
    }

    private void initFace() {
        this.faceSwitchStyleTextView = (TextView) findViewById(2131297933);
        this.faceLoginBtn = (LinearLayout) findViewById(2131297862);
        this.accountPassWordLayout = (LinearLayout) findViewById(2131297824);
        this.smsPassWordLayout = (LinearLayout) findViewById(2131297925);
        this.broadbandPassWordLayout = (LinearLayout) findViewById(2131297847);
        this.guhuaPassWordLayout = (LinearLayout) findViewById(2131297877);
        this.passviewLine0 = findViewById(2131297944);
        this.passviewLine1 = findViewById(2131297945);
        this.passviewLine2 = findViewById(2131297946);
        this.passviewLine4 = findViewById(2131297947);
        this.faceAccoutnParams = new HashMap<>();
        this.managerFaceLogin = new ManagerFaceLogin(context);
        this.loginHandler = new ManagerLoginHandler(context);
        this.faceSwitchStyleTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$Ed7tK16NV3RWjglf318j7LoeSOA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.lambda$initFace$1(LoginActivity.this, view);
            }
        });
        setFaceSwitchVisable();
        this.faceLoginBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$3FMIR0Pf4x1kMal6klXi4oVorj8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                r0.yinSiXieYiUtil.checkDialog(r0.isSelectXieYi, r0.accountName, "L").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.87
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        if (bool.booleanValue()) {
                            LoginActivity.this.isSelectXieYi = true;
                            LoginActivity.this.mImgCheck.setImageResource(2131231907);
                            LoginActivity.this.startQueryFace();
                            if (TextUtils.isEmpty(LoginActivity.this.smsAccountName)) {
                                return;
                            }
                            LoginActivity.this.loginLogPv.setMobile(LoginActivity.this.smsAccountName);
                            if (LoginActivity.this.loginLogPv.getSmsCount() > 1) {
                                LoginActivity.this.loginLogPv.log(LoginLogPv.RE_OTHER_LOGIN_11);
                            } else if (LoginActivity.this.loginLogPv.getSmsCount() > 0) {
                                LoginActivity.this.loginLogPv.log(LoginLogPv.FIRST_OTHER_LOGIN_10);
                            }
                        }
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            }
        });
    }

    public static /* synthetic */ void lambda$initFace$1(LoginActivity loginActivity, View view) {
        loginActivity.switchFaceUI();
        loginActivity.switchLayout(loginActivity.loginType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startQueryFace() {
        this.faceAccoutnParams.clear();
        this.faceAccoutnParams.put("yw_code", this.ywMap.get(this.accountName));
        this.faceAccoutnParams.put("search_code", "0");
        getAccountParams(new C742588());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$88 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C742588 implements CompleteInterfface {
        C742588() {
        }

        @Override // com.sinovatech.unicom.basic.p315ui.activity.LoginActivity.CompleteInterfface
        public void search() {
            if (LoginActivity.this.isSelectXieYi) {
                LoginActivity.this.pdShow();
                StatisticsUploadUtils.upload("shualiandenglu", "点击登录按钮", "查询", "", LoginActivity.this.accountName, "");
                if (!TextUtils.isEmpty(LoginActivity.this.accountName) && !TextUtils.isEmpty(LoginActivity.this.loginType)) {
                    MsLogUtil.m7979d("登录---->", "刷脸登录,accountName=" + LoginActivity.this.accountName + ",loginType=" + LoginActivity.this.loginType + ",guhuaAreaId=" + LoginActivity.this.guhuaAreaId);
                    if ("4".equals(LoginActivity.this.loginType)) {
                        ManagerHomeOMO.initOMO(LoginActivity.context, ManagerHomeOMO.getAreaCode(LoginActivity.context, LoginActivity.this.guhuaAreaId, LoginActivity.this.accountName), "3", "2");
                    } else {
                        ManagerHomeOMO.initOMO(LoginActivity.context, LoginActivity.this.accountName, "2".equals(LoginActivity.this.loginType) ? "2" : "1", "2");
                    }
                }
                LoginActivity.this.managerFaceLogin.loadFaceQuery(LoginActivity.this.faceAccoutnParams).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$88$ySByf3Ls8mb905x9l9a8Ba1gDf0
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        LoginActivity.C742588.lambda$search$0(LoginActivity.C742588.this, (String) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$88$mWF7hYNaySxclNwyOSl_fT6_BQU
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        LoginActivity.C742588.lambda$search$1(LoginActivity.C742588.this, (Throwable) obj);
                    }
                });
                return;
            }
            UIUtils.toastCenter("请先勾选协议后再进行登录");
        }

        public static /* synthetic */ void lambda$search$0(C742588 c742588, String str) throws Exception {
            LoginActivity.this.pdDissmiss();
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("code");
            if ("0000".equals(optString)) {
                StatisticsUploadUtils.upload("shualiandenglu", "点击登录按钮", "登录", "", LoginActivity.this.accountName, "");
                SharePreferenceUtil sharePreferenceUtil = LoginActivity.this.preference;
                LoginActivity loginActivity = LoginActivity.this;
                if (!sharePreferenceUtil.getBoolean(loginActivity.getFaceXieyiKey(loginActivity.accountName))) {
                    FaceLoginCustomDialog.show(LoginActivity.context, new FaceLoginCustomDialog.UpdateCustomDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.88.1
                        @Override // com.sinovatech.unicom.basic.p315ui.view.FaceLoginCustomDialog.UpdateCustomDialogListener
                        public void onCancel() {
                        }

                        @Override // com.sinovatech.unicom.basic.p315ui.view.FaceLoginCustomDialog.UpdateCustomDialogListener
                        public void onClickOk() {
                            LoginActivity.this.preference.putBoolean(LoginActivity.this.getFaceXieyiKey(LoginActivity.this.accountName), true);
                            LoginActivity.this.faceLogin();
                        }
                    });
                } else {
                    LoginActivity.this.faceLogin();
                }
            } else if ("0001".equals(optString)) {
                SharePreferenceUtil sharePreferenceUtil2 = LoginActivity.this.preference;
                LoginActivity loginActivity2 = LoginActivity.this;
                sharePreferenceUtil2.putBoolean(loginActivity2.getFaceXieyiKey(loginActivity2.accountName), false);
                FaceLoginCustomDialog.show(LoginActivity.context, new FaceLoginCustomDialog.UpdateCustomDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.88.2
                    @Override // com.sinovatech.unicom.basic.p315ui.view.FaceLoginCustomDialog.UpdateCustomDialogListener
                    public void onCancel() {
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.view.FaceLoginCustomDialog.UpdateCustomDialogListener
                    public void onClickOk() {
                        LoginActivity.this.preference.putBoolean(LoginActivity.this.getFaceXieyiKey(LoginActivity.this.accountName), true);
                        LoginActivity.this.showFacePassWord();
                        LoginActivity.this.showTipToast();
                    }
                });
            } else {
                String optString2 = jSONObject.optString("dsc");
                if (TextUtils.isEmpty(optString2)) {
                    optString2 = "系统或网络异常，请稍后重试";
                }
                CustomDialogManager.show(LoginActivity.context, "温馨提示", optString2);
            }
        }

        public static /* synthetic */ void lambda$search$1(C742588 c742588, Throwable th) throws Exception {
            CustomDialogManager.show(LoginActivity.context, "温馨提示", "查询刷脸状态错误");
            th.printStackTrace();
            LoginActivity.this.pdDissmiss();
        }

        @Override // com.sinovatech.unicom.basic.p315ui.activity.LoginActivity.CompleteInterfface
        public void add() {
            StatisticsUploadUtils.upload("shualiandenglu", "点击登录按钮", "注册", "", LoginActivity.this.accountName, "");
            LoginActivity.this.logout();
            LoginActivity.this.zhuceLogin();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getFaceXieyiKey(String str) {
        return "shualianxieyi_new" + EncodeHelper.encoderByMd5(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$89 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C742889 implements ManagerFaceLogin.IFaceResult {
        C742889() {
        }

        @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin.IFaceResult
        public void onResult(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            LoginActivity.this.logout();
            LoginActivity.this.pdShow();
            LoginActivity.this.managerFaceLogin.loadFaceLogin(LoginActivity.this.faceAccoutnParams, str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$89$HYfZgqDpYYJr49NQ0s0lp7Gwxog
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    LoginActivity.C742889.lambda$onResult$0(LoginActivity.C742889.this, (String) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$89$yLqc_nbcpYXhxSDtRlCWfBfEDco
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    LoginActivity.C742889.lambda$onResult$1(LoginActivity.C742889.this, (Throwable) obj);
                }
            });
        }

        public static /* synthetic */ void lambda$onResult$0(C742889 c742889, String str) throws Exception {
            LoginActivity.this.pdDissmiss();
            LoginActivity.this.loginHandler.handlerResult(LoginActivity.this.accountName, str, false, LoginActivity.this.areaId, LoginActivity.this.guhuaAreaId, LoginActivity.this.loginType);
        }

        public static /* synthetic */ void lambda$onResult$1(C742889 c742889, Throwable th) throws Exception {
            LoginActivity.this.pdDissmiss();
            CustomDialogManager.show(LoginActivity.context, "温馨提示", "刷脸方式登录失败，请稍后再试或使用其他方式登录");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void faceLogin() {
        starActivieLive(new C742889());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$90 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C743090 implements ManagerFaceLogin.IFaceResult {
        C743090() {
        }

        @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin.IFaceResult
        public void onResult(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            LoginActivity.this.pdShow();
            LoginActivity.this.faceAccoutnParams.put("add_type", "1");
            LoginActivity.this.managerFaceLogin.loadFaceRegist(LoginActivity.this.faceAccoutnParams, str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$90$yQSr_XM8SttU1m-DPavpnq19dlY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    LoginActivity.C743090.lambda$onResult$0(LoginActivity.C743090.this, (String) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$90$z45cFPXcFvhFYJQe9BcSaMSj1r0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    LoginActivity.C743090.lambda$onResult$1(LoginActivity.C743090.this, (Throwable) obj);
                }
            });
        }

        public static /* synthetic */ void lambda$onResult$0(C743090 c743090, String str) throws Exception {
            LoginActivity.this.pdDissmiss();
            if (!"0".equals(new JSONObject(str).optString("code"))) {
                LoginActivity.this.accountPassWordLayout.setVisibility(8);
                LoginActivity.this.smsPassWordLayout.setVisibility(8);
                LoginActivity.this.broadbandPassWordLayout.setVisibility(8);
                LoginActivity.this.guhuaPassWordLayout.setVisibility(8);
                LoginActivity.this.passwordEditText.setText("");
                LoginActivity.this.smsPasswordEditText.setText("");
                LoginActivity.this.broadbandPasswordEdittext.setText("");
                LoginActivity.this.guhuaPasswordEdittext.setText("");
            }
            LoginActivity.this.loginHandler.handlerResult(LoginActivity.this.accountName, str, false, LoginActivity.this.areaId, LoginActivity.this.guhuaAreaId, LoginActivity.this.loginType);
        }

        public static /* synthetic */ void lambda$onResult$1(C743090 c743090, Throwable th) throws Exception {
            LoginActivity.this.pdDissmiss();
            CustomDialogManager.show(LoginActivity.context, "温馨提示", "刷脸方式登录失败，请稍后再试或使用其他方式登录");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zhuceLogin() {
        starActivieLive(new C743090());
    }

    private void switchFaceUI() {
        if (this.faceStyleIsCurrent) {
            setOnekeyMianliuButtonVisable();
            this.accountPassWordLayout.setVisibility(0);
            this.smsPassWordLayout.setVisibility(0);
            this.broadbandPassWordLayout.setVisibility(0);
            this.guhuaPassWordLayout.setVisibility(0);
            this.faceLoginBtn.setVisibility(8);
            this.loginButton.setVisibility(0);
            this.passviewLine0.setVisibility(0);
            this.passviewLine1.setVisibility(0);
            this.passviewLine2.setVisibility(0);
            this.passviewLine4.setVisibility(0);
            this.faceSwitchStyleTextView.setText("刷脸方式登录");
            this.faceStyleIsCurrent = false;
            if ("1".equals(this.configManager.getYuyinYanzheng()) && this.showYuyinFlag) {
                this.yuyinLayout.setVisibility(0);
            }
        } else {
            this.onekeyMianliuButton.setVisibility(8);
            this.accountPassWordLayout.setVisibility(8);
            this.smsPassWordLayout.setVisibility(8);
            this.broadbandPassWordLayout.setVisibility(8);
            this.guhuaPassWordLayout.setVisibility(8);
            this.faceLoginBtn.setVisibility(0);
            this.loginButton.setVisibility(8);
            this.passviewLine0.setVisibility(8);
            this.passviewLine1.setVisibility(8);
            this.passviewLine2.setVisibility(8);
            this.passviewLine4.setVisibility(8);
            this.faceSwitchStyleTextView.setText("账号密码登录");
            this.faceStyleIsCurrent = true;
            this.yuyinLayout.setVisibility(8);
        }
        StatisticsUploadUtils.upload("shualiandenglu", "切换登录方式", "按钮", "", this.faceSwitchStyleTextView.getText().toString(), "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void showFacePassWord() {
        char c;
        String str = this.loginType;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 50:
                if (str.equals("2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 51:
                if (str.equals("3")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 52:
                if (str.equals("4")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 53:
                if (str.equals("5")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.accountPassWordLayout.setVisibility(0);
                this.passviewLine1.setVisibility(0);
                return;
            case 1:
                this.broadbandPassWordLayout.setVisibility(0);
                this.passviewLine2.setVisibility(0);
                return;
            case 2:
                this.smsPassWordLayout.setVisibility(0);
                this.passviewLine0.setVisibility(0);
                return;
            case 3:
                this.guhuaPassWordLayout.setVisibility(0);
                this.passviewLine4.setVisibility(0);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFaceSwitchVisable() {
        if ("on".equals(this.loginConfigEntity.getFaceLoginSwitch()) || "5".equals(this.loginType)) {
            this.faceSwitchStyleTextView.setVisibility(8);
        } else {
            this.faceSwitchStyleTextView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEsimVisable() {
        if (!"on".equals(this.loginConfigEntity.getEsimLoginSwitch())) {
            this.esimImageView.setVisibility(8);
        } else {
            this.esimImageView.setVisibility(0);
        }
        if (this.esimImageView.getVisibility() == 8 && this.weixinLoginButton.getVisibility() == 8) {
            this.moreLoginlayout.setVisibility(4);
        } else {
            this.moreLoginlayout.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void showTipToast() {
        char c;
        String str = this.loginType;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 50:
                if (str.equals("2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 51:
                if (str.equals("3")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 52:
                if (str.equals("4")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 53:
                if (str.equals("5")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
                UIUtils.toastCenterLong("开通刷脸登录，需要输入密码或短信验证");
                return;
            case 2:
            case 3:
                UIUtils.toastCenterLong("开通刷脸登录，需要输入密码验证身份");
                return;
            default:
                return;
        }
    }

    @SuppressLint({"CheckResult"})
    private void starActivieLive(final ManagerFaceLogin.IFaceResult iFaceResult) {
        if (isFastClick()) {
            return;
        }
        PermissionDialog.show("人脸登录为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        this.rxPermissions.request("android.permission.CAMERA").subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$9138l3hzwCkGs-yVygAiobmBjA8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LoginActivity.lambda$starActivieLive$3(ManagerFaceLogin.IFaceResult.this, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$starActivieLive$3(ManagerFaceLogin.IFaceResult iFaceResult, Boolean bool) throws Exception {
        PermissionDialog.dimissDialog();
        if (bool.booleanValue()) {
            TYCJBoxManager.getInstance().collectClickSdk(context, "S2ndpage1214", "登录", "人脸识别", "", "com.megvii.kas.livenessdetection", "1");
            ManagerFaceLogin.starFaceLogin(context, iFaceResult);
            return;
        }
        UIUtils.toast("请开启摄像头权限");
    }

    private void initEsim() {
        this.esimImageView = (ImageView) findViewById(2131297857);
        this.esimImageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$LoginActivity$YJHJ31VZltSmSQE0fbqDtOIkILM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                r0.yinSiXieYiUtil.checkDialog(r0.isSelectXieYi, "eSIM", ESIMLoginActivity.ESIMTYPE).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.93
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        if (bool.booleanValue()) {
                            LoginActivity.this.isSelectXieYi = true;
                            LoginActivity.this.mImgCheck.setImageResource(2131231907);
                            LoginActivity loginActivity = LoginActivity.this;
                            loginActivity.startActivity(new Intent(loginActivity, ESIMLoginActivity.class));
                        }
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.activity.LoginActivity.94
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        th.printStackTrace();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logout() {
        if (!SystemServiceUtils.netIsAvailable()) {
            CustomDialogManager.show(context, "", "网络连接失败，请检查网络设置！");
            return;
        }
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        App.clearPersistentCookiesList();
        UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
    }

    public static void startMobileIntent(Activity activity, String str) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("logintype", "01");
        intent.putExtra("account", str);
        activity.startActivity(intent);
    }

    public static void startKuanDaiIntent(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("broadband", "YES");
        intent.putExtra("broadbandAccountAndAreaidToLogin", "YES");
        intent.putExtra("logintype", "03");
        intent.putExtra("broadbandAccount", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("broadbandAreaId", str2);
        }
        activity.startActivity(intent);
    }

    public static void startGuHuaIntent(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("guhua", "YES");
        intent.putExtra("memberLogin", "YES");
        intent.putExtra("account", str);
        intent.putExtra("logintype", "02");
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("guhuaAreaId", str2);
        }
        activity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SpannableStringBuilder getXieyiSpannableString(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) "已阅读并同意");
        SpannableString spannableString = new SpannableString("《中国联通APP用户隐私政策》");
        spannableString.setSpan(new XieyiClickSpan(URLSet.getUserPrivacy()), 0, spannableString.length(), 33);
        spannableStringBuilder.append((CharSequence) spannableString);
        spannableStringBuilder.append((CharSequence) "、");
        SpannableString spannableString2 = new SpannableString("《中国联通APP用户服务协议》");
        spannableString2.setSpan(new XieyiClickSpan(URLSet.getUserserver()), 0, spannableString2.length(), 33);
        spannableStringBuilder.append((CharSequence) spannableString2);
        spannableStringBuilder.append((CharSequence) "以及");
        SpannableString spannableString3 = new SpannableString("《中国联通用户隐私政策》");
        spannableString3.setSpan(new XieyiClickSpan(URLSet.getunicom_yinsizhengce()), 0, spannableString3.length(), 33);
        spannableStringBuilder.append((CharSequence) spannableString3);
        return spannableStringBuilder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LoginActivity$XieyiClickSpan */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class XieyiClickSpan extends ClickableSpan {
        private String url;

        public XieyiClickSpan(String str) {
            this.url = str;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            super.updateDrawState(textPaint);
            textPaint.setColor(-10786159);
            textPaint.setUnderlineText(false);
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(@NonNull View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            LoginActivity.this.toXieyi(this.url);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnekeyMianliuButtonVisable() {
        this.loginConfigEntity = LoginConfigDataCenter.getInstance().getEntity();
        if ("on".equals(this.loginConfigEntity.getF_switch())) {
            this.onekeyMianliuButton.setVisibility(0);
        } else {
            this.onekeyMianliuButton.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeYuyinYanzheng(boolean z) {
        if ("3".equals(this.loginType)) {
            if (z) {
                this.showYuyinFlag = true;
                this.yuyinLayout.setVisibility(0);
                this.yuyinTextView.setTextColor(getResources().getColor(2131099937));
                this.yuyinIcon.setImageResource(2131231911);
                this.yuyinClickLayout.setEnabled(true);
                this.yuyinClickLayout.setBackgroundResource(2131231909);
            } else {
                this.yuyinTextView.setTextColor(-10066330);
                this.yuyinIcon.setImageResource(2131231908);
                this.yuyinClickLayout.setEnabled(false);
                this.yuyinClickLayout.setBackgroundResource(2131231910);
            }
            if (this.faceStyleIsCurrent && z) {
                if (this.smsPassWordLayout.getVisibility() == 0) {
                    this.yuyinLayout.setVisibility(0);
                } else {
                    this.yuyinLayout.setVisibility(8);
                }
            }
        }
    }

    public Map<String, Object> getWebParams() {
        HashMap hashMap = new HashMap();
        String str = "";
        String str2 = "";
        String jiemiStr = getJiemiStr(this.accountNameEditText);
        if ("2".equals(this.loginType)) {
            str = "broad";
            str2 = this.areaId;
            jiemiStr = getJiemiStr(this.broadbandaccountnameEdittext);
        }
        if ("4".equals(this.loginType)) {
            str = "fixedTel";
            str2 = this.guhuaAreaId;
            jiemiStr = getJiemiStr(this.guhuaaccountnameEdittext);
        }
        hashMap.put("accountId", jiemiStr);
        hashMap.put("logintype", str);
        hashMap.put("cityCode", str2);
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
        hashMap.put("netWay", DeviceHelper.getNETType(App.getInstance()));
        hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
        hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
        hashMap.put("deviceOS", DeviceHelper.getDeviceOSVersion());
        hashMap.put("pip", SystemServiceUtils.getLocalIpAddress());
        hashMap.put("version", context.getString(2131886969));
        MsLogUtil.m7979d("getWebParams", "---" + hashMap.toString());
        this.loginLogPv.setMobile(this.smsAccountName);
        this.loginLogPv.setLoginType(this.loginType);
        if (this.loginLogPv.getVoiceCount().intValue() > 0 && this.loginLogPv.getVoiceCount().intValue() > this.loginLogPv.getSmsCount()) {
            this.loginLogPv.log(LoginLogPv.VOICE_LEAVE_09);
        } else if (this.loginLogPv.getSmsCount() > 1 && this.loginLogPv.getSmsCount() > this.loginLogPv.getVoiceCount().intValue()) {
            this.loginLogPv.log(LoginLogPv.RE_LOGIN_LEAVE_05);
        } else if (this.loginLogPv.getSmsCount() > 0) {
            this.loginLogPv.log(LoginLogPv.LOGIN_LEAVE_02);
        }
        return hashMap;
    }
}
