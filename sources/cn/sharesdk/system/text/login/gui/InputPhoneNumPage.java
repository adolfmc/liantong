package cn.sharesdk.system.text.login.gui;

import android.app.Instrumentation;
import android.content.Context;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.system.text.login.DialogShell;
import cn.sharesdk.system.text.login.LoginActionListener;
import cn.sharesdk.system.text.login.RequestCore;
import cn.sharesdk.system.text.login.p098a.InputPhoneNumPageLayout;
import cn.sharesdk.system.text.login.utils.CommEditText;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.FakeActivity;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class InputPhoneNumPage extends FakeActivity implements View.OnClickListener {
    private ImageView btnBack;
    private Button btnNext;
    private CommEditText etArrow;
    private CommEditText etCountry;
    private CommEditText etPhone;
    private CommEditText etZone;
    private boolean isShow;
    private LoginActionListener listener;
    private String phoneNum;
    private String zone = "86";
    private String country = "";

    public void setLoginActionListener(LoginActionListener loginActionListener) {
        this.listener = loginActionListener;
    }

    public void IsShowCountryPage(boolean z) {
        this.isShow = z;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        super.onCreate();
        requestPortraitOrientation();
        InputPhoneNumPageLayout inputPhoneNumPageLayout = new InputPhoneNumPageLayout(getContext());
        this.activity.setContentView(inputPhoneNumPageLayout.m21546a());
        initView(inputPhoneNumPageLayout);
    }

    public void initView(InputPhoneNumPageLayout inputPhoneNumPageLayout) {
        this.etCountry = inputPhoneNumPageLayout.m21544b();
        this.etArrow = inputPhoneNumPageLayout.m21543c();
        this.etZone = inputPhoneNumPageLayout.m21542d();
        this.etPhone = inputPhoneNumPageLayout.m21541e();
        this.btnNext = inputPhoneNumPageLayout.m21540f();
        this.btnBack = inputPhoneNumPageLayout.m21539g();
        if (!this.isShow) {
            this.etCountry.setEnabled(false);
            this.etArrow.setText("");
        }
        this.etCountry.setOnClickListener(this);
        this.btnNext.setOnClickListener(this);
        this.btnBack.setOnClickListener(this);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [cn.sharesdk.system.text.login.gui.InputPhoneNumPage$2] */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view == this.btnNext) {
            if (this.etPhone.getText().length() > 0 && isVaildPhoneNum(this.etPhone.getText().toString())) {
                this.phoneNum = this.etPhone.getText().toString().trim().replaceAll("\\s*", "");
                this.zone = this.etZone.getText().toString().trim().replaceAll("\\s*", "").substring(1);
                this.country = this.etCountry.getText().toString();
                final DialogShell dialogShell = new DialogShell(getContext());
                dialogShell.m21567a("+" + this.zone + " " + this.phoneNum).setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.system.text.login.gui.InputPhoneNumPage.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NBSActionInstrumentation.onClickEventEnter(view2, this);
                        Tracker.onClick(view2);
                        RequestCore requestCore = new RequestCore(InputPhoneNumPage.this, dialogShell);
                        HashMap<String, Object> hashMap = new HashMap<>();
                        requestCore.m21528a(InputPhoneNumPage.this.listener);
                        requestCore.m21529a(1);
                        hashMap.put("zone", InputPhoneNumPage.this.zone);
                        hashMap.put("phone", InputPhoneNumPage.this.phoneNum);
                        hashMap.put("country", InputPhoneNumPage.this.country);
                        hashMap.put("type", 1);
                        requestCore.m21523a(hashMap);
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                new DialogShell(getContext()).m21564d();
            }
        } else if (view == this.etCountry) {
            CountryPage countryPage = new CountryPage();
            countryPage.setSMSLoginActionListener(this.listener);
            countryPage.showForResult(getContext(), null, this);
        } else if (view == this.btnBack) {
            new Thread() { // from class: cn.sharesdk.system.text.login.gui.InputPhoneNumPage.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        new Instrumentation().sendKeyDownUpSync(4);
                    } catch (Throwable th) {
                        SSDKLog.m21740b().m21737b(th);
                        if (InputPhoneNumPage.this.listener != null) {
                            InputPhoneNumPage.this.listener.onCancel();
                        }
                        InputPhoneNumPage.this.finish();
                    }
                }
            }.start();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private static boolean isVaildPhoneNum(String str) throws PatternSyntaxException {
        return Pattern.compile("^0{0,1}(13[0-9]|15[3-9]|15[0-2]|18[0-9]|17[0-9]|14[0-9])[0-9]{8}$").matcher(str).matches();
    }

    @Override // com.mob.tools.FakeActivity
    public void onResult(HashMap<String, Object> hashMap) {
        if (hashMap != null) {
            if (((Integer) hashMap.get("page")).intValue() == 1) {
                this.zone = (String) hashMap.get("zone");
                this.country = (String) hashMap.get("country");
                if (!TextUtils.isEmpty(this.zone)) {
                    CommEditText commEditText = this.etZone;
                    commEditText.setText("+" + this.zone);
                    this.etCountry.setText(this.country);
                }
            }
            this.listener = !hashMap.containsKey("listener") ? null : (LoginActionListener) hashMap.get("listener");
            if (this.listener == null) {
                finish();
            }
        }
    }

    public void show(Context context) {
        super.show(context, null);
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0) {
            LoginActionListener loginActionListener = this.listener;
            if (loginActionListener != null) {
                loginActionListener.onCancel();
            }
            finish();
            return true;
        }
        return false;
    }
}
