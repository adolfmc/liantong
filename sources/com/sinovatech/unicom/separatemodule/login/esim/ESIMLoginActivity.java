package com.sinovatech.unicom.separatemodule.login.esim;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.basic.p314po.LoginAccountEntity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerHomeOMO;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomAutoCompleteTextView;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.dialog.BaseDialog;
import com.sinovatech.unicom.separatemodule.dialog.EsimDialog;
import com.sinovatech.unicom.separatemodule.dialog.EsimVerticalDialog;
import com.sinovatech.unicom.separatemodule.login.esim.KeyboardWatcher;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil;
import com.sinovatech.unicom.separatemodule.login.yinsixieyi.YinSiXieYiUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpPrivateUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayUtils;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ESIMLoginActivity extends BaseActivity implements KeyboardWatcher.SoftKeyboardStateListener {
    public static final String ESIMTYPE = "e29";
    public NBSTraceUnit _nbs_trace;
    private ImageButton accountClearImage;
    private CustomAutoCompleteTextView accountEdittext;
    private ImageButton accountSelectImage;
    private Activity activityContext;
    private int clickSelectButtonFlag;
    private int clickSelectItemFlag;
    private TextView descText;
    private ImageView eSimCloseImage;
    private ImageView editImageImage;
    private LinearLayout editRootLayout;
    private TextView editTeTextView;
    private LinearLayout editTextLayout;
    private EsimLoginManager esimLoginManager;
    private LinearLayout esimRootLayout;
    private String idCardNo;
    private String idCardNoJiaMi;
    private String isFirstLogin;
    private boolean isSelectXieYi;
    private Button loginButton;
    private final int mAnimTime = 300;
    private LinearLayout mBodyLayout;
    private ImageView mImgCheck;
    private String mobile;
    private String mobileJiaMi;
    private PhoneAdapter paAdpter;

    /* renamed from: pd */
    private ProgressDialog f18556pd;
    private List<LoginAccountEntity> selectNumberList;
    private ImageButton shenfenzhengClearImage;
    private EditText shenfenzhengEditText;
    private TextView switchText;
    private UserManager userManager;
    private LinearLayout xieyiLayout;
    private TextView xieyiTextView;
    private YinSiXieYiUtil yinSiXieYiUtil;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 93);
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

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
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

    static /* synthetic */ int access$108(ESIMLoginActivity eSIMLoginActivity) {
        int i = eSIMLoginActivity.clickSelectButtonFlag;
        eSIMLoginActivity.clickSelectButtonFlag = i + 1;
        return i;
    }

    static /* synthetic */ int access$208(ESIMLoginActivity eSIMLoginActivity) {
        int i = eSIMLoginActivity.clickSelectItemFlag;
        eSIMLoginActivity.clickSelectItemFlag = i + 1;
        return i;
    }

    private void initView() {
        this.eSimCloseImage = (ImageView) findViewById(2131296933);
        this.mBodyLayout = (LinearLayout) findViewById(2131296523);
        this.accountEdittext = (CustomAutoCompleteTextView) findViewById(2131297853);
        this.accountClearImage = (ImageButton) findViewById(2131297858);
        this.accountSelectImage = (ImageButton) findViewById(2131297855);
        this.shenfenzhengEditText = (EditText) findViewById(2131297860);
        this.shenfenzhengClearImage = (ImageButton) findViewById(2131297859);
        this.loginButton = (Button) findViewById(2131297857);
        this.switchText = (TextView) findViewById(2131296943);
        this.editRootLayout = (LinearLayout) findViewById(2131296938);
        this.editTextLayout = (LinearLayout) findViewById(2131296941);
        this.editImageImage = (ImageView) findViewById(2131296937);
        this.editTeTextView = (TextView) findViewById(2131296936);
        this.xieyiLayout = (LinearLayout) findViewById(2131297850);
        this.mImgCheck = (ImageView) findViewById(2131297849);
        this.mImgCheck.setImageResource(2131231904);
        this.xieyiTextView = (TextView) findViewById(2131297950);
        this.esimRootLayout = (LinearLayout) findViewById(2131296939);
        this.descText = (TextView) findViewById(2131296934);
        String esimLoginHintText = LoginConfigDataCenter.getInstance().getEntity().getEsimLoginHintText();
        if (!TextUtils.isEmpty(esimLoginHintText)) {
            MsLogUtil.m7979d("esimLoginHintText", esimLoginHintText);
            this.descText.setText(esimLoginHintText);
            findViewById(2131296935).setVisibility(0);
        } else {
            findViewById(2131296935).setVisibility(8);
        }
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$j91RGYcxu3KIao6x0m3FKvcTrUo
            @Override // java.lang.Runnable
            public final void run() {
                KeyboardWatcher.with(r0).setListener(ESIMLoginActivity.this);
            }
        }, 500L);
        try {
            if ("1".equals(this.isFirstLogin)) {
                this.mobile = getIntent().getStringExtra("account");
                if (!TextUtils.isEmpty(this.mobile)) {
                    LoginAccountEntity selectAccountName = this.userManager.getSelectAccountName(this.mobile, "1");
                    this.idCardNo = selectAccountName.getCid();
                    if ("error".equals(selectAccountName.getKeyversion())) {
                        this.mobileJiaMi = hidePhoneNumber(this.mobile);
                        this.idCardNoJiaMi = hideIdcardNumber(this.idCardNo);
                    } else {
                        this.mobileJiaMi = this.mobile;
                        this.idCardNoJiaMi = this.idCardNo;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.editTeTextView.setText(this.mobileJiaMi);
        this.xieyiTextView.setText(getXieyiSpannableString());
        this.xieyiTextView.setMovementMethod(LinkMovementMethod.getInstance());
        setSwitchUi();
    }

    private void initAccount() {
        for (LoginAccountEntity loginAccountEntity : this.selectNumberList) {
            if ("error".equals(loginAccountEntity.getKeyversion())) {
                loginAccountEntity.setAccountnameJiami(hidePhoneNumber(loginAccountEntity.getAccountname()));
            }
            loginAccountEntity.setCidJiami(hideIdcardNumber(loginAccountEntity.getCid()));
        }
        if (TextUtils.isEmpty(this.accountEdittext.getText().toString())) {
            this.accountClearImage.setVisibility(8);
        } else {
            this.accountClearImage.setVisibility(0);
        }
        $$Lambda$ESIMLoginActivity$H0dAV2veiNKTUYfnOtTP8BLjwM __lambda_esimloginactivity_h0dav2veinktuyfnottp8bljwm = new InputFilter() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$H0dAV2veiNKTUYfnOtTP8BLj-wM
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return ESIMLoginActivity.lambda$initAccount$1(charSequence, i, i2, spanned, i3, i4);
            }
        };
        this.accountEdittext.setFilters(new InputFilter[]{__lambda_esimloginactivity_h0dav2veinktuyfnottp8bljwm, new InputFilter.LengthFilter(11)});
        this.shenfenzhengEditText.setFilters(new InputFilter[]{__lambda_esimloginactivity_h0dav2veinktuyfnottp8bljwm, new InputFilter.LengthFilter(18)});
        this.accountEdittext.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ESIMLoginActivity.this.paAdpter.isClickSelectButton = ESIMLoginActivity.access$108(ESIMLoginActivity.this) < 2;
                ESIMLoginActivity.this.paAdpter.isClickSelectItem = ESIMLoginActivity.access$208(ESIMLoginActivity.this) == 0;
                if (TextUtils.isEmpty(ESIMLoginActivity.this.accountEdittext.getText().toString())) {
                    ESIMLoginActivity.this.accountClearImage.setVisibility(8);
                } else {
                    ESIMLoginActivity.this.accountClearImage.setVisibility(0);
                }
                if (ESIMLoginActivity.this.accountEdittext.getText().toString().contains("*") && ESIMLoginActivity.this.accountEdittext.getText().toString().length() != 11) {
                    ESIMLoginActivity.this.accountEdittext.setText("");
                    ESIMLoginActivity.this.accountEdittext.setTag("");
                    ESIMLoginActivity.this.shenfenzhengEditText.setText("");
                    ESIMLoginActivity.this.shenfenzhengEditText.setTag("");
                }
                ESIMLoginActivity.this.accountEdittext.setThreshold(1);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if ("0".equals(ESIMLoginActivity.this.isFirstLogin)) {
                    String trim = ESIMLoginActivity.this.accountEdittext.getText().toString().trim();
                    String trim2 = ESIMLoginActivity.this.shenfenzhengEditText.getText().toString().trim();
                    if (editable.toString().length() <= 0 || trim.length() != 11 || trim2.length() != 18) {
                        ESIMLoginActivity.this.loginButton.setEnabled(false);
                        ESIMLoginActivity.this.loginButton.setBackgroundResource(2131231880);
                        return;
                    }
                    ESIMLoginActivity.this.loginButton.setEnabled(true);
                    ESIMLoginActivity.this.loginButton.setBackgroundResource(2131231879);
                }
            }
        });
        this.shenfenzhengEditText.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(ESIMLoginActivity.this.shenfenzhengEditText.getText().toString())) {
                    ESIMLoginActivity.this.shenfenzhengClearImage.setVisibility(8);
                } else {
                    ESIMLoginActivity.this.shenfenzhengClearImage.setVisibility(0);
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String trim = ESIMLoginActivity.this.accountEdittext.getText().toString().trim();
                String trim2 = ESIMLoginActivity.this.shenfenzhengEditText.getText().toString().trim();
                if (trim2.contains("*") && trim2.length() != 18) {
                    ESIMLoginActivity.this.accountEdittext.setText("");
                    ESIMLoginActivity.this.accountEdittext.setTag("");
                    ESIMLoginActivity.this.shenfenzhengEditText.setText("");
                    ESIMLoginActivity.this.shenfenzhengEditText.setTag("");
                }
                if ("0".equals(ESIMLoginActivity.this.isFirstLogin)) {
                    if (editable.toString().length() <= 0 || trim.length() != 11 || trim2.length() != 18) {
                        ESIMLoginActivity.this.loginButton.setEnabled(false);
                        ESIMLoginActivity.this.loginButton.setBackgroundResource(2131231880);
                        return;
                    }
                    ESIMLoginActivity.this.loginButton.setEnabled(true);
                    ESIMLoginActivity.this.loginButton.setBackgroundResource(2131231879);
                }
            }
        });
        this.paAdpter = new PhoneAdapter(this, 2131493328, this.selectNumberList, new PhoneAdapter.OnPhoneAdapterListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.3
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onItemSelected(LoginAccountEntity loginAccountEntity2) {
                try {
                    if (!loginAccountEntity2.getAccountname().trim().equals(ESIMLoginActivity.this.getJiemiStr(ESIMLoginActivity.this.accountEdittext))) {
                        ESIMLoginActivity.this.accountEdittext.setText("");
                        ESIMLoginActivity.this.accountEdittext.setTag("");
                    }
                    if (TextUtils.isEmpty(loginAccountEntity2.getAccountnameJiami())) {
                        ESIMLoginActivity.this.accountEdittext.setText(loginAccountEntity2.getAccountname().trim());
                        ESIMLoginActivity.this.accountEdittext.setTag(loginAccountEntity2.getAccountname().trim());
                    } else {
                        ESIMLoginActivity.this.accountEdittext.setTag(loginAccountEntity2.getAccountname().trim());
                        ESIMLoginActivity.this.accountEdittext.setText(loginAccountEntity2.getAccountnameJiami().trim());
                    }
                    ESIMLoginActivity.this.accountEdittext.clearFocus();
                    ESIMLoginActivity.this.shenfenzhengEditText.requestFocus();
                    ESIMLoginActivity.this.shenfenzhengEditText.setText(loginAccountEntity2.getCidJiami().trim());
                    ESIMLoginActivity.this.shenfenzhengEditText.setTag(loginAccountEntity2.getCid().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.sinovatech.unicom.basic.p315ui.adapter.PhoneAdapter.OnPhoneAdapterListener
            public void onDeleteItem(LoginAccountEntity loginAccountEntity2) {
                ESIMLoginActivity.this.selectNumberList.remove(loginAccountEntity2);
            }
        });
        this.accountEdittext.setAdapter(this.paAdpter);
        this.accountEdittext.setDropDownHorizontalOffset(0);
        this.accountEdittext.setDropDownVerticalOffset(1);
        this.accountEdittext.setDropDownBackgroundDrawable(new ColorDrawable(-1));
        this.accountSelectImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (ESIMLoginActivity.this.clickSelectButtonFlag != 0) {
                    ESIMLoginActivity.this.clickSelectButtonFlag = 0;
                    ESIMLoginActivity.this.accountEdittext.clearFocus();
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                ESIMLoginActivity.this.accountEdittext.requestFocus();
                ESIMLoginActivity.this.clickSelectButtonFlag = 1;
                ESIMLoginActivity.this.accountEdittext.append(" ");
                ESIMLoginActivity.this.accountEdittext.setText(ESIMLoginActivity.this.accountEdittext.getText().toString().trim());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (this.selectNumberList.size() > 0) {
            LoginAccountEntity loginAccountEntity2 = this.selectNumberList.get(0);
            this.accountEdittext.setText(TextUtils.isEmpty(loginAccountEntity2.getAccountnameJiami()) ? loginAccountEntity2.getAccountname() : loginAccountEntity2.getAccountnameJiami());
            this.accountEdittext.setTag(loginAccountEntity2.getAccountname());
            this.shenfenzhengEditText.setText(loginAccountEntity2.getCidJiami());
            this.shenfenzhengEditText.setTag(loginAccountEntity2.getCid());
            this.accountSelectImage.setVisibility(0);
            this.shenfenzhengClearImage.setVisibility(0);
            return;
        }
        this.accountSelectImage.setVisibility(4);
        this.shenfenzhengClearImage.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CharSequence lambda$initAccount$1(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (charSequence.equals(" ")) {
            return "";
        }
        return null;
    }

    private void initClickListener() {
        this.esimRootLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$N6SrO8YjXGNlhAb1SmsYxGnlwlo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                r0.hideSoftInput(ESIMLoginActivity.this.esimRootLayout);
            }
        });
        this.eSimCloseImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$XZpLSsBj-pTJgdcEHJRtGCeLpXs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESIMLoginActivity.lambda$initClickListener$3(ESIMLoginActivity.this, view);
            }
        });
        this.accountClearImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$XoKL8qpeOgp7o35Yxzr3YzSunJ8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESIMLoginActivity.lambda$initClickListener$4(ESIMLoginActivity.this, view);
            }
        });
        this.shenfenzhengClearImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$GulL6B3qN7A_hfufxE80VGvo4ZA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESIMLoginActivity.this.shenfenzhengEditText.setText("");
            }
        });
        this.editImageImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$C-QN8oxLowynlSbHE3uKwucVebY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESIMLoginActivity.lambda$initClickListener$6(ESIMLoginActivity.this, view);
            }
        });
        this.switchText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$H-YMuz_1BHrM-QSXuH_kWmTq9qE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                r0.yinSiXieYiUtil.checkDialog(r0.isSelectXieYi, r0.mobile, ESIMLoginActivity.ESIMTYPE).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.5
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        if (bool.booleanValue()) {
                            ESIMLoginActivity.this.isSelectXieYi = true;
                            ESIMLoginActivity.this.mImgCheck.setImageResource(2131231907);
                            ESIMLoginActivity.this.isFirstLogin = "0";
                            ESIMLoginActivity.this.setSwitchUi();
                            ESIMLoginActivity.this.log("二次登录切换");
                        }
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.6
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        th.printStackTrace();
                    }
                });
            }
        });
        this.mImgCheck.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$FTNLyRNTp3ga7KmIsWNeN00ElX8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESIMLoginActivity.lambda$initClickListener$8(ESIMLoginActivity.this, view);
            }
        });
        this.loginButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$k3q4ffiO0IoweHobgGfPUu1vhdo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ESIMLoginActivity.lambda$initClickListener$9(ESIMLoginActivity.this, view);
            }
        });
        this.accountEdittext.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (TextUtils.isEmpty(ESIMLoginActivity.this.accountEdittext.getText())) {
                    ESIMLoginActivity.this.clickSelectButtonFlag = 1;
                    ESIMLoginActivity.this.accountEdittext.append(" ");
                    ESIMLoginActivity.this.accountEdittext.setText(ESIMLoginActivity.this.accountEdittext.getText().toString().trim());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.accountEdittext.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.10
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                Tracker.onFocusChange(view, z);
                if (z && TextUtils.isEmpty(ESIMLoginActivity.this.accountEdittext.getText())) {
                    ESIMLoginActivity.this.clickSelectButtonFlag = 1;
                    ESIMLoginActivity.this.accountEdittext.append(" ");
                    ESIMLoginActivity.this.accountEdittext.setText(ESIMLoginActivity.this.accountEdittext.getText().toString().trim());
                }
            }
        });
        this.accountEdittext.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.11
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    ESIMLoginActivity.this.hideSoftInput(textView);
                    return true;
                }
                return false;
            }
        });
    }

    public static /* synthetic */ void lambda$initClickListener$3(ESIMLoginActivity eSIMLoginActivity, View view) {
        eSIMLoginActivity.finish();
        eSIMLoginActivity.log("返回");
    }

    public static /* synthetic */ void lambda$initClickListener$4(ESIMLoginActivity eSIMLoginActivity, View view) {
        eSIMLoginActivity.accountEdittext.setText("");
        eSIMLoginActivity.accountEdittext.setTag("");
    }

    public static /* synthetic */ void lambda$initClickListener$6(ESIMLoginActivity eSIMLoginActivity, View view) {
        eSIMLoginActivity.accountEdittext.setText(eSIMLoginActivity.mobileJiaMi);
        eSIMLoginActivity.accountEdittext.setTag(eSIMLoginActivity.mobile);
        eSIMLoginActivity.shenfenzhengEditText.setText(eSIMLoginActivity.idCardNoJiaMi);
        eSIMLoginActivity.shenfenzhengEditText.setTag(eSIMLoginActivity.idCardNo);
        eSIMLoginActivity.switchText.performClick();
    }

    public static /* synthetic */ void lambda$initClickListener$8(ESIMLoginActivity eSIMLoginActivity, View view) {
        if (eSIMLoginActivity.isSelectXieYi) {
            eSIMLoginActivity.isSelectXieYi = false;
            eSIMLoginActivity.mImgCheck.setImageResource(2131231904);
            eSIMLoginActivity.log("取消隐私协议");
            return;
        }
        eSIMLoginActivity.isSelectXieYi = true;
        eSIMLoginActivity.mImgCheck.setImageResource(2131231907);
        eSIMLoginActivity.log("确定隐私协议");
    }

    public static /* synthetic */ void lambda$initClickListener$9(ESIMLoginActivity eSIMLoginActivity, View view) {
        if ("0".equals(eSIMLoginActivity.isFirstLogin)) {
            eSIMLoginActivity.isSelectXieYi = true;
        }
        eSIMLoginActivity.yinSiXieYiUtil.checkDialog(eSIMLoginActivity.isSelectXieYi, eSIMLoginActivity.mobile, ESIMTYPE).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    ESIMLoginActivity.this.isSelectXieYi = true;
                    ESIMLoginActivity.this.mImgCheck.setImageResource(2131231907);
                    if (!SystemServiceUtils.netIsAvailable()) {
                        CustomDialogManager.show(ESIMLoginActivity.this.activityContext, "", "网络连接失败，请检查网络设置！");
                        return;
                    }
                    if ("0".equals(ESIMLoginActivity.this.isFirstLogin)) {
                        ESIMLoginActivity eSIMLoginActivity2 = ESIMLoginActivity.this;
                        eSIMLoginActivity2.mobile = eSIMLoginActivity2.getJiemiStr(eSIMLoginActivity2.accountEdittext);
                        ESIMLoginActivity eSIMLoginActivity3 = ESIMLoginActivity.this;
                        eSIMLoginActivity3.idCardNo = eSIMLoginActivity3.getJiemiStr(eSIMLoginActivity3.shenfenzhengEditText);
                    }
                    if (Pattern.matches("^1\\d{10}$", ESIMLoginActivity.this.mobile)) {
                        if (Pattern.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}([0-9]|[x|X])$", ESIMLoginActivity.this.idCardNo)) {
                            ESIMLoginActivity.this.pdShow();
                            ESIMLoginActivity.this.log("开始登录");
                            ESIMLoginActivity.this.loginStep0();
                            try {
                                ((InputMethodManager) ESIMLoginActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(ESIMLoginActivity.this.activityContext.getCurrentFocus().getWindowToken(), 2);
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        UIUtils.toastCenterLong("您输入的身份证号码不正确，请输入18位数字号码！");
                        return;
                    }
                    UIUtils.toastCenterLong("请输入正确的手机号码！");
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.8
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity$12 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C894212 implements Consumer<HashMap<String, String>> {
        C894212() {
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(final HashMap<String, String> hashMap) throws Exception {
            ESIMLoginActivity.this.pdDissmiss();
            String str = hashMap.get("errorCode");
            String str2 = hashMap.get("description");
            if ("0".equals(str)) {
                new AvoidOnResult(ESIMLoginActivity.this.activityContext).startForResult(ESIMXeiyiActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.12.1
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i, Intent intent) {
                        if (i == -1) {
                            ESIMLoginActivity.this.loginStep1((String) hashMap.get("resultToken"));
                        }
                    }
                });
            } else if ("ECS99999".equals(str)) {
                LoginFilterUtil.filerLogin(ESIMLoginActivity.this.activityContext, hashMap.get("content"), new LoginFilterUtil.CallBackInterface() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.12.2
                    @Override // com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.CallBackInterface
                    public void complete(final String str3) {
                        HttpPrivateUtil.faceV3Data = "";
                        new AvoidOnResult(ESIMLoginActivity.this.activityContext).startForResult(ESIMXeiyiActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.12.2.1
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public void onActivityResult(int i, Intent intent) {
                                if (i == -1) {
                                    ESIMLoginActivity.this.loginStep1(str3);
                                }
                            }
                        });
                    }
                });
            } else {
                ESIMLoginActivity.this.handleResult(str, str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginStep0() {
        ManagerHomeOMO.initOMO(this, this.mobile, "1", "3");
        this.esimLoginManager.stepZero(this.mobile, getParams("0", "")).subscribe(new C894212(), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.13
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ESIMLoginActivity.this.pdDissmiss();
                UIUtils.toastCenter("操作频繁，请稍后再试。");
                MsLogUtil.m7979d("ESIMLoginActivity", "step0:" + th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "操作频繁，请稍后再试";
        }
        if ("e15".equals(str)) {
            ((EsimDialog.Builder) new EsimDialog.Builder(this.activityContext).setTitle("温馨提示").setMessageView(str2).setConfirm("重新输入").setOnClickListener(2131299122, new BaseDialog.OnClickListener<View>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.14
                @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnClickListener
                public void onClick(BaseDialog baseDialog, View view) {
                    baseDialog.dismiss();
                }
            })).setCancel("").show();
        } else if ("e16".equals(str)) {
            new EsimDialog.Builder(this.activityContext).setTitle("温馨提示").setMessageView(str2).setConfirm("关闭").setCancel("").show();
        } else if ("e17".equals(str)) {
            ((EsimVerticalDialog.Builder) new EsimVerticalDialog.Builder(this.activityContext).setTitle("温馨提示").setMessageView(str2).setOnClickListener(2131299122, new BaseDialog.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.esim.-$$Lambda$ESIMLoginActivity$bzj5Pv9HOroBh5jplj19GJQDaUA
                @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    ESIMLoginActivity.lambda$handleResult$10(ESIMLoginActivity.this, baseDialog, view);
                }
            })).setConfirm("短信验证码登录").show();
        } else if ("ecs0001".equalsIgnoreCase(str)) {
            UIUtils.toastCenter(str2);
        } else {
            new EsimDialog.Builder(this.activityContext).setTitle("温馨提示").setMessageView(str2).setConfirm("关闭").setCancel("").show();
        }
    }

    public static /* synthetic */ void lambda$handleResult$10(ESIMLoginActivity eSIMLoginActivity, BaseDialog baseDialog, View view) {
        baseDialog.dismiss();
        eSIMLoginActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginStep1(String str) {
        LoginManager.logout(this.activityContext);
        pdShow();
        HashMap<String, String> params = getParams("1", HttpPrivateUtil.faceV3Data);
        params.put("resultToken", str);
        this.esimLoginManager.stepOne(this.mobile, params).subscribe(new C894815(str), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.16
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ESIMLoginActivity.this.pdDissmiss();
                UIUtils.toastCenter("操作频繁，请稍后再试.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity$15 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C894815 implements Consumer<HashMap<String, String>> {
        final /* synthetic */ String val$resultToken;

        C894815(String str) {
            this.val$resultToken = str;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(HashMap<String, String> hashMap) throws Exception {
            ESIMLoginActivity.this.pdDissmiss();
            if ("ok".equals(hashMap.get("ok"))) {
                App.setLogined(LoginStateConst.DID_LOGIN);
                WebFragment.isRelogin = true;
                App.reLoadDefaultMenuData = true;
                App.reLoadAdvertise = true;
                try {
                    UnicomPayUtils.getInstance(ESIMLoginActivity.this.activityContext).loginPaySdk();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ESIMLoginActivity.this.userManager.saveSelectAccountName(ESIMLoginActivity.this.mobile, "", ESIMLoginActivity.ESIMTYPE, "esimLogin", ESIMLoginActivity.this.userManager.getKeyVersion(), "1", ESIMLoginActivity.this.userManager.getUserTouxiangURL(), ESIMLoginActivity.this.idCardNo, "");
                Intent intent = new Intent(ESIMLoginActivity.this.activityContext, MainActivity.class);
                intent.putExtra("from", "app");
                ESIMLoginActivity.this.activityContext.startActivity(intent);
                ESIMLoginActivity.this.activityContext.setResult(-1);
                ESIMLoginActivity.this.activityContext.finish();
                return;
            }
            App.setLogined(LoginStateConst.UNLOGIN);
            String str = hashMap.get("errorCode");
            String str2 = hashMap.get("description");
            if (TextUtils.isEmpty(str2)) {
                UIUtils.toastCenter("操作频繁，请稍后再试");
            } else if (!"e14".equals(str)) {
                ESIMLoginActivity.this.handleResult(str, str2);
            } else {
                ((EsimDialog.Builder) new EsimDialog.Builder(ESIMLoginActivity.this.activityContext).setTitle("温馨提示").setMessageView(str2).setOnClickListener(2131299122, new BaseDialog.OnClickListener<View>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.15.1
                    @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.OnClickListener
                    public void onClick(BaseDialog baseDialog, View view) {
                        baseDialog.dismiss();
                        ManagerFaceLogin.starFaceLogin(ESIMLoginActivity.this.activityContext, new ManagerFaceLogin.IFaceResult() { // from class: com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity.15.1.1
                            @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerFaceLogin.IFaceResult
                            public void onResult(String str3) {
                                if (TextUtils.isEmpty(str3)) {
                                    return;
                                }
                                ESIMLoginActivity.this.loginStep1(C894815.this.val$resultToken);
                            }
                        });
                    }
                })).setConfirm("再试一次").show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwitchUi() {
        if ("1".equals(this.isFirstLogin)) {
            this.switchText.setVisibility(0);
            this.editTextLayout.setVisibility(0);
            this.xieyiLayout.setVisibility(0);
            this.editRootLayout.setVisibility(8);
            this.descText.setVisibility(8);
            this.loginButton.setEnabled(true);
            this.loginButton.setBackgroundResource(2131231879);
            this.loginButton.setText("人脸验证登录");
            return;
        }
        this.switchText.setVisibility(8);
        this.editTextLayout.setVisibility(8);
        this.xieyiLayout.setVisibility(8);
        this.editRootLayout.setVisibility(0);
        this.descText.setVisibility(0);
        if (!TextUtils.isEmpty(this.accountEdittext.getText().toString()) && !TextUtils.isEmpty(this.shenfenzhengEditText.getText().toString())) {
            this.loginButton.setEnabled(true);
            this.loginButton.setBackgroundResource(2131231879);
        } else {
            this.loginButton.setEnabled(false);
            this.loginButton.setBackgroundResource(2131231880);
        }
        this.loginButton.setText("下一步");
    }

    private HashMap<String, String> getParams(String str, String str2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("mobile", this.mobile);
        hashMap.put("idCardNo", this.idCardNo);
        hashMap.put("step", str);
        if ("1".equals(str)) {
            hashMap.put("delta", str2);
        }
        hashMap.put("isFirstLogin", this.isFirstLogin);
        MsLogUtil.m7979d("loginStep", " :" + hashMap.toString());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDissmiss() {
        try {
            if (isDestroyed() || isFinishing() || this.f18556pd == null || !this.f18556pd.isShowing()) {
                return;
            }
            this.f18556pd.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdShow() {
        try {
            if (isFinishing() || this.f18556pd == null || this.f18556pd.isShowing()) {
                return;
            }
            this.f18556pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SpannableStringBuilder getXieyiSpannableString() {
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
            ESIMLoginActivity.this.toXieyi(this.url);
            NBSActionInstrumentation.onClickEventExit();
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

    public String hidePhoneNumber(String str) {
        if (str == null || str.length() < 11) {
            return str;
        }
        String substring = str.substring(0, 3);
        String substring2 = str.substring(7);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append('*');
        }
        return substring + ((Object) sb) + substring2;
    }

    public String hideIdcardNumber(String str) {
        if (str == null || str.length() < 18) {
            return str;
        }
        String substring = str.substring(0, 6);
        String substring2 = str.substring(16);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append('*');
        }
        return substring + ((Object) sb) + substring2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getJiemiStr(TextView textView) {
        String charSequence = textView.getText().toString();
        try {
            charSequence = charSequence.trim();
            return (TextUtils.isEmpty(charSequence) || !charSequence.contains("*")) ? charSequence : (String) textView.getTag();
        } catch (Exception e) {
            e.printStackTrace();
            return charSequence;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        PvCurrencyLogUtils.pvCurrencyLog("", 2, "S2ndpage1232", "", "", str, "", "", "", "");
    }

    @Override // com.sinovatech.unicom.separatemodule.login.esim.KeyboardWatcher.SoftKeyboardStateListener
    public void onSoftKeyboardOpened(int i) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mBodyLayout, "translationY", 0.0f, -this.loginButton.getHeight());
        ofFloat.setDuration(300L);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.start();
    }

    @Override // com.sinovatech.unicom.separatemodule.login.esim.KeyboardWatcher.SoftKeyboardStateListener
    public void onSoftKeyboardClosed() {
        LinearLayout linearLayout = this.mBodyLayout;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(linearLayout, "translationY", linearLayout.getTranslationY(), 0.0f);
        ofFloat.setDuration(300L);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.start();
    }
}
