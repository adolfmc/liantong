package com.sinovatech.unicom.basic.p315ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebView;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.JiePinEvent;
import com.sinovatech.unicom.basic.eventbus.NoticEvent;
import com.sinovatech.unicom.basic.eventbus.ServiceMenuEvent;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerWebViewFullScreen;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.KeyBoardListener;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.chuanshanjia.ToutiaoEvent;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.PushMsgDao;
import com.sinovatech.unicom.separatemodule.webrtc.RtcHelper;
import com.sinovatech.unicom.separatemodule.webrtc.RtcVoiceHelper;
import java.net.URLDecoder;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.WebDetailActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WebDetailActivity extends BaseActivity {
    public static boolean gaodedache = true;
    public static boolean isgaodedache;
    public NBSTraceUnit _nbs_trace;
    private WebDetailActivity activityContext = this;
    private boolean iskControlPage;
    private WebFragment webFragment;

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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        String[] split;
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            WebView.enableSlowWholeDocumentDraw();
        }
        setContentView(2131493548);
        ManagerWebViewFullScreen.isPortrait = false;
        if (getIntent() != null && getIntent().getData() != null) {
            String uri = getIntent().getData().toString();
            if (!TextUtils.isEmpty(uri)) {
                try {
                    String decode = URLDecoder.decode(uri, "utf-8");
                    JSONObject jSONObject = new JSONObject(decode.substring(decode.indexOf("{")).split("\\?id=")[0]);
                    String str = jSONObject.optString("openUrl") + "&id=" + split[1];
                    String optString = jSONObject.optString("title", "");
                    String optString2 = jSONObject.optString("actCode", "");
                    WebParamsEntity webParamsEntity = new WebParamsEntity();
                    webParamsEntity.setTitle(optString);
                    webParamsEntity.setUrl(str);
                    webParamsEntity.setNeedTitle(true);
                    this.webFragment = WebFragment.newIntence(webParamsEntity);
                    getSupportFragmentManager().beginTransaction().replace(2131299557, this.webFragment).commitAllowingStateLoss();
                    StatisticsUploadUtils.upload(this.activityContext, "S2ndpage1006", optString, "", optString2, "", str, "", "", UserManager.getInstance().getCurrentPhoneNumber().equals("0") ? "" : UserManager.getInstance().getCurrentPhoneNumber(), "");
                    NBSAppInstrumentation.activityCreateEndIns();
                    return;
                } catch (Exception e) {
                    UIUtils.toastCenterLong(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        try {
            UIUtils.setStatusBarColor(this.activityContext);
            WebParamsEntity webParamsEntity2 = (WebParamsEntity) getIntent().getParcelableExtra(WebFragment.webParams);
            if (webParamsEntity2 == null) {
                webParamsEntity2 = new WebParamsEntity();
                String stringExtra = getIntent().getStringExtra("url");
                webParamsEntity2.setTitle(getIntent().getStringExtra("title"));
                webParamsEntity2.setUrl(stringExtra);
                webParamsEntity2.setNeedTitle(true);
            } else {
                String url = webParamsEntity2.getUrl();
                if (!TextUtils.isEmpty(url) && url.contains("webViewNavIsHidden")) {
                    UIUtils.setStatusBlack(this);
                    KeyBoardListener.getInstance(this).init();
                }
                if (!TextUtils.isEmpty(url) && url.contains("setLandscape=true")) {
                    setRequestedOrientation(0);
                }
                if ("city".equals(webParamsEntity2.getType())) {
                    UIUtils.setStatusBlack(this);
                    KeyBoardListener.getInstance(this).init();
                }
                if ("notice".equals(webParamsEntity2.getType())) {
                    new PushMsgDao(this.activityContext).updatePushMessageRecordStatus(webParamsEntity2.getAcId(), "1");
                    sendBroadcast(new Intent("com.sinovatech.unicom.basic.ui.pushmessagereciever"));
                    EventBusUtils.post(new NoticEvent(EventBusUtils.EVENT_NOTIC));
                }
            }
            this.webFragment = WebFragment.newIntence(webParamsEntity2);
            getSupportFragmentManager().beginTransaction().replace(2131299557, this.webFragment).commitAllowingStateLoss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    public void showKeyBoard(String str) {
        this.webFragment.showKeyBoard(str);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        this.webFragment.onBackPressed();
    }

    public void showToutiaoBanner(ToutiaoEvent toutiaoEvent) {
        this.webFragment.getToutiaoEvent(toutiaoEvent);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            if (isgaodedache) {
                return gaodedache;
            }
            if (RtcHelper.getInstance() == null || !RtcHelper.getInstance().getDisableBack()) {
                if (RtcVoiceHelper.getInstance() == null || !RtcVoiceHelper.getInstance().getDisableBack()) {
                    return super.onKeyDown(i, keyEvent);
                }
                return true;
            }
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        isgaodedache = false;
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            setWindowSecure(false);
            if (FuWuConstant.isRefshMenu) {
                FuWuConstant.isRefshMenu = false;
                EventBusUtils.post(new ServiceMenuEvent(EventBusUtils.EVENT_FUWU_REFRESHINIT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goMain() {
        IntentManager.intentFilter(this, true, true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setJiePinVisible(JiePinEvent jiePinEvent) {
        setWindowSecure(jiePinEvent.isSecure());
    }

    public void setWindowSecure(boolean z) {
        MsLogUtil.m7979d("是否允许截图", z + "");
        if (z) {
            if ((getWindow().getAttributes().flags & 8192) != 0) {
                return;
            }
            getWindow().addFlags(8192);
        } else if ((getWindow().getAttributes().flags & 8192) == 0) {
        } else {
            getWindow().clearFlags(8192);
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        WebFragment webFragment = this.webFragment;
        if (webFragment != null) {
            webFragment.onActivityResult(i, i2, intent);
        }
    }

    public void setStrongLoginResult(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("code");
            String optString2 = jSONObject.optString("resultToken");
            Intent intent = new Intent();
            intent.putExtra("code", optString);
            intent.putExtra("resultToken", optString2);
            if ("0000".equals(optString)) {
                setResult(-1, intent);
                MsLogUtil.m7979d("setStrongLoginResult", "客户端成功接收--" + optString2);
            } else {
                setResult(0);
                MsLogUtil.m7979d("setStrongLoginResult", "客户端失败接收--");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isIskControlPage() {
        return this.iskControlPage;
    }

    public void setIskControlPage(boolean z) {
        this.iskControlPage = z;
    }

    public boolean shouLoadJS() {
        return getIntent().getBooleanExtra("shouldLoadJs", false);
    }

    public String getWebUrl() {
        return this.webFragment.getWebUrl();
    }

    public String getWebTitle() {
        return this.webFragment.getWebTitle();
    }
}
