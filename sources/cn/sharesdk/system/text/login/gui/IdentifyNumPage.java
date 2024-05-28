package cn.sharesdk.system.text.login.gui;

import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.system.text.login.DialogShell;
import cn.sharesdk.system.text.login.LoginActionListener;
import cn.sharesdk.system.text.login.RequestCore;
import cn.sharesdk.system.text.login.p098a.IdentifyNumPageLayout;
import cn.sharesdk.system.text.login.utils.CommEditText;
import cn.sharesdk.system.text.login.utils.VerifyCodeReceiver;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IdentifyNumPage extends FakeActivity implements View.OnClickListener {
    private static final int RETRY_INTERVAL = 60;
    private ImageView btnBack;
    private Button btnSendVoice;
    private Button btnSubmit;
    private String codeNum;
    private String country;
    private CommEditText etCode;
    private CommEditText etPhone;
    private CommEditText etSendAgain;
    private LoginActionListener listener;
    private String phoneNum;
    private VerifyCodeReceiver receiver;
    private RequestCore request;
    private String sendRecordId;
    private int time = 60;
    private int type;
    private TextView voiceText;
    private String zoneNum;

    static /* synthetic */ int access$210(IdentifyNumPage identifyNumPage) {
        int i = identifyNumPage.time;
        identifyNumPage.time = i - 1;
        return i;
    }

    public void setLoginActionListener(LoginActionListener loginActionListener) {
        this.listener = loginActionListener;
    }

    public void setRequestData(String str, String str2, int i, String str3, String str4) {
        this.zoneNum = str;
        this.phoneNum = str2;
        this.country = str3;
        this.sendRecordId = str4;
        this.type = i;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        super.onCreate();
        requestPortraitOrientation();
        IdentifyNumPageLayout identifyNumPageLayout = new IdentifyNumPageLayout(getContext());
        this.activity.setContentView(identifyNumPageLayout.m21557a());
        initView(identifyNumPageLayout);
        int stringRes = ResHelper.getStringRes(getContext(), "ssdk_sms_dialog_send_success");
        if (stringRes > 0) {
            new DialogShell(getContext()).m21569a(stringRes, 1).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.sharesdk.system.text.login.gui.IdentifyNumPage.1
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    IdentifyNumPage identifyNumPage = IdentifyNumPage.this;
                    identifyNumPage.request = new RequestCore(identifyNumPage, null);
                    IdentifyNumPage.this.request.m21528a(IdentifyNumPage.this.listener);
                }
            });
        }
    }

    private void initView(IdentifyNumPageLayout identifyNumPageLayout) {
        this.etPhone = identifyNumPageLayout.m21555b();
        this.etCode = identifyNumPageLayout.m21554c();
        this.etSendAgain = identifyNumPageLayout.m21553d();
        this.voiceText = identifyNumPageLayout.m21552e();
        this.btnSubmit = identifyNumPageLayout.m21551f();
        this.btnSendVoice = identifyNumPageLayout.m21550g();
        this.btnBack = identifyNumPageLayout.m21549h();
        CommEditText commEditText = this.etPhone;
        commEditText.setText("+" + this.zoneNum + " " + this.phoneNum);
        this.etSendAgain.setOnClickListener(this);
        this.voiceText.setOnClickListener(this);
        this.btnSubmit.setOnClickListener(this);
        this.btnSendVoice.setOnClickListener(this);
        this.btnBack.setOnClickListener(this);
        countDown();
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [cn.sharesdk.system.text.login.gui.IdentifyNumPage$2] */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view == this.voiceText) {
            this.btnSendVoice.setVisibility(0);
        } else if (view == this.btnSendVoice) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("zone", this.zoneNum);
            hashMap.put("phone", this.phoneNum);
            hashMap.put("type", 2);
            this.type = 2;
            this.request.m21529a(0);
            this.request.m21523a(hashMap);
        } else if (view == this.btnSubmit) {
            this.codeNum = this.etCode.getText().toString().trim().replaceAll("\\s*", "");
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("zone", this.zoneNum);
            hashMap2.put("phone", this.phoneNum);
            hashMap2.put("country", this.country);
            hashMap2.put("code", this.codeNum);
            hashMap2.put("type", Integer.valueOf(this.type));
            hashMap2.put("sendRecordId", this.sendRecordId);
            this.request.m21529a(2);
            this.request.m21523a(hashMap2);
        } else if (view == this.etSendAgain) {
            countDown();
            HashMap<String, Object> hashMap3 = new HashMap<>();
            hashMap3.put("zone", this.zoneNum);
            hashMap3.put("phone", this.phoneNum);
            hashMap3.put("type", 1);
            this.type = 1;
            this.request.m21529a(0);
            this.request.m21523a(hashMap3);
        } else if (view == this.btnBack) {
            new Thread() { // from class: cn.sharesdk.system.text.login.gui.IdentifyNumPage.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        new Instrumentation().sendKeyDownUpSync(4);
                    } catch (Throwable th) {
                        SSDKLog.m21740b().m21737b(th);
                        IdentifyNumPage.this.finish();
                    }
                }
            }.start();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private void countDown() {
        runOnUIThread(new Runnable() { // from class: cn.sharesdk.system.text.login.gui.IdentifyNumPage.3
            @Override // java.lang.Runnable
            public void run() {
                int stringRes = ResHelper.getStringRes(IdentifyNumPage.this.getContext(), "ssdk_sms_send_again");
                IdentifyNumPage.access$210(IdentifyNumPage.this);
                if (IdentifyNumPage.this.time == 0) {
                    IdentifyNumPage.this.etSendAgain.setEnabled(true);
                    IdentifyNumPage.this.etSendAgain.setTextColor(-14060034);
                    IdentifyNumPage.this.time = 60;
                    if (stringRes > 0) {
                        IdentifyNumPage.this.etSendAgain.setText(stringRes);
                        return;
                    }
                    return;
                }
                if (stringRes > 0) {
                    IdentifyNumPage.this.etSendAgain.setText(stringRes);
                    IdentifyNumPage.this.etSendAgain.setTextColor(-7829368);
                    IdentifyNumPage.this.etSendAgain.setEnabled(false);
                    CommEditText commEditText = IdentifyNumPage.this.etSendAgain;
                    commEditText.setText(IdentifyNumPage.this.etSendAgain.getText().toString() + "(" + IdentifyNumPage.this.time + ")");
                }
                if (IdentifyNumPage.this.time == 30) {
                    IdentifyNumPage.this.voiceText.setVisibility(0);
                }
                IdentifyNumPage.this.runOnUIThread(this, 1000L);
            }
        }, 1000L);
    }

    public void show(Context context) {
        super.show(context, null);
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onFinish() {
        if (this.receiver != null) {
            this.activity.unregisterReceiver(this.receiver);
            return false;
        }
        return false;
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0) {
            final DialogShell dialogShell = new DialogShell(getContext());
            dialogShell.m21566b().setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.system.text.login.gui.IdentifyNumPage.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("page", 2);
                    hashMap.put("listener", IdentifyNumPage.this.listener);
                    IdentifyNumPage.this.setResult(hashMap);
                    dialogShell.m21570a();
                    IdentifyNumPage.this.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            return true;
        }
        return false;
    }
}
