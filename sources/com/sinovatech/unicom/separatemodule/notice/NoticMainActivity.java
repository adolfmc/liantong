package com.sinovatech.unicom.separatemodule.notice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p083v4.app.FragmentTransaction;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.eventbus.NoticEvent;
import com.sinovatech.unicom.basic.eventbus.ReloadWebviewEvent;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.MsJSEvent;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoticMainActivity extends BaseActivity {
    private static final int MIN_DELAY_TIME = 200;
    private static final String TAG = "NoticMainActivity";
    private static long lastClickTime;
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private ImageView backImage;
    private ImageView clearImage;
    private ImageView kuaixunCollectImage;
    private WebFragment kuaixunFragment;
    private TextView kuanxunText;
    private View kuanxunView;
    private WebFragment liuyanFragment;
    private RelativeLayout liuyanLayout;
    private LiuyanManager liuyanManager;
    private TextView liuyanNumText;
    private TextView liuyanText;
    private View liuyanView;
    private WebFragment mFragmentContent;
    private NoticeConfigEntity noticeEntity;
    private PushMsgDao pushMsgDao;
    private ImageView settingImage;
    private WebFragment xinxiFragment;
    private TextView xinxiNumText;
    private TextView xinxiText;
    private View xinxiView;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 100);
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
        try {
            EventBusUtils.post(new NoticEvent(EventBusUtils.EVENT_NOTIC));
            App.refreshXiaohongdianClear = true;
            App.refreshXiaohongdianClear2 = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initFragment() {
        try {
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(this.noticeEntity.getConfigUrl().getKuaixun());
            if (URLEnvironmentConfig.isPrepubEnvironment()) {
                webParamsEntity.setUrl(URLSet.getNoticKuaixun());
            }
            webParamsEntity.setType("get");
            webParamsEntity.setBackMode("1");
            webParamsEntity.setRequestType("get");
            this.kuaixunFragment = WebFragment.newIntence(webParamsEntity);
            WebParamsEntity webParamsEntity2 = new WebParamsEntity();
            webParamsEntity2.setUrl(this.noticeEntity.getConfigUrl().getXiaoxi());
            if (URLEnvironmentConfig.isPrepubEnvironment()) {
                webParamsEntity2.setUrl(URLSet.getNoticXiaoxi());
            }
            webParamsEntity2.setBackMode("1");
            webParamsEntity2.setRequestType("get");
            this.xinxiFragment = WebFragment.newIntence(webParamsEntity2);
            WebParamsEntity webParamsEntity3 = new WebParamsEntity();
            webParamsEntity3.setUrl(this.noticeEntity.getConfigUrl().getLiuyan());
            try {
                List list = (List) PushServer.parseBackData(CacheDataCenter.getInstance().getKefuPushData())[1];
                if (list.size() >= 1) {
                    webParamsEntity3.setUrl(((PushMessageEntity) list.get(0)).getSaleMessListUrl());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isPrepubEnvironment2()) {
                webParamsEntity3.setUrl("https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://bbdigital.10010.com/udbh/jumpWiselist");
            }
            webParamsEntity3.setType("get");
            webParamsEntity3.setBackMode("1");
            webParamsEntity3.setRequestType("get");
            this.liuyanFragment = WebFragment.newIntence(webParamsEntity3);
            int intExtra = getIntent().getIntExtra("index", 0);
            if (intExtra == 0) {
                this.mFragmentContent = this.kuaixunFragment;
            } else if (intExtra == 1) {
                this.mFragmentContent = this.xinxiFragment;
            } else if (intExtra == 2) {
                this.mFragmentContent = this.liuyanFragment;
            }
            getSupportFragmentManager().beginTransaction().add(2131298139, this.mFragmentContent).commit();
            if (intExtra == 2 && "02".equals(UserManager.getInstance().getCurrentPhoneType())) {
                UIUtils.toastCenter("该留言暂不支持固话用户，敬请谅解!");
                changeSelectTab(0);
                return;
            }
            changeSelectTab(intExtra);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        this.mFragmentContent.onBackPressed();
    }

    private void initView() {
        this.backImage = (ImageView) findViewById(2131298133);
        this.kuanxunText = (TextView) findViewById(2131298141);
        this.kuanxunView = findViewById(2131298142);
        this.xinxiText = (TextView) findViewById(2131298146);
        this.xinxiView = findViewById(2131298148);
        this.liuyanText = (TextView) findViewById(2131298143);
        this.liuyanView = findViewById(2131298145);
        this.clearImage = (ImageView) findViewById(2131298134);
        this.settingImage = (ImageView) findViewById(2131298137);
        this.kuaixunCollectImage = (ImageView) findViewById(2131298135);
        this.xinxiNumText = (TextView) findViewById(2131298147);
        this.liuyanNumText = (TextView) findViewById(2131298140);
        this.liuyanLayout = (RelativeLayout) findViewById(2131298144);
    }

    private void initListener() {
        this.kuanxunText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$NoticMainActivity$YPMsCThCwgNqyjOKlBkduyIy2BU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoticMainActivity.this.changeSelectTab(0);
            }
        });
        this.xinxiText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$NoticMainActivity$eBVnFKGhcpdWaUz8nEcyVQCCfe8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoticMainActivity.this.changeSelectTab(1);
            }
        });
        this.liuyanText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$NoticMainActivity$lEc8LKwC5S6ilMjyjlfO4zc_wt0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoticMainActivity.lambda$initListener$2(NoticMainActivity.this, view);
            }
        });
        this.clearImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$NoticMainActivity$ZQVPRjZ7XmPtVYHJ5kRRdq4k4sQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoticMainActivity.lambda$initListener$3(NoticMainActivity.this, view);
            }
        });
        this.settingImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$NoticMainActivity$-UellAT4VEPUwc7TDgg6430WuEg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoticMainActivity.lambda$initListener$4(NoticMainActivity.this, view);
            }
        });
        this.kuaixunCollectImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$NoticMainActivity$cDQdN-OiT3St-oYdBlxHBy54ZbE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoticMainActivity.lambda$initListener$5(NoticMainActivity.this, view);
            }
        });
        this.backImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$NoticMainActivity$3XYuGywQ2uJsg2GQbAlL9aqpYSA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoticMainActivity.this.mFragmentContent.onBackPressed();
            }
        });
    }

    public static /* synthetic */ void lambda$initListener$2(NoticMainActivity noticMainActivity, View view) {
        if (App.hasLogined()) {
            noticMainActivity.changeSelectTab(2);
        } else {
            new AvoidOnResult(noticMainActivity.activityContext).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.notice.NoticMainActivity.1
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    if (App.hasLogined()) {
                        if (!"02".equals(UserManager.getInstance().getLoginType())) {
                            NoticMainActivity.this.changeSelectTab(2);
                            return;
                        }
                        UIUtils.toastCenter("该留言暂不支持固话用户，敬请谅解!");
                        if (NoticMainActivity.this.kuaixunCollectImage.getVisibility() == 0) {
                            NoticMainActivity.this.changeSelectTab(0);
                        } else {
                            NoticMainActivity.this.changeSelectTab(1);
                        }
                    }
                }
            });
        }
    }

    public static /* synthetic */ void lambda$initListener$3(NoticMainActivity noticMainActivity, View view) {
        try {
            if (isFastClick()) {
                return;
            }
            if (App.hasLogined()) {
                if (1 == noticMainActivity.getCurrentTabIndex()) {
                    JSONObject jSONObject = new JSONObject();
                    if (noticMainActivity.pushMsgDao.getPushMessageRecordSize(UserManager.getInstance().getCurrentPhoneNumber(), "0") > 0) {
                        jSONObject.put("code", "0000");
                    } else {
                        jSONObject.put("code", "0001");
                    }
                    noticMainActivity.pushMsgDao.updateAllPushMessageRecordStatus("1");
                    noticMainActivity.xinxiFragment.postEventToJS(MsJSEvent.clearRedPoint, jSONObject);
                    noticMainActivity.setNum(0);
                }
                if (2 == noticMainActivity.getCurrentTabIndex()) {
                    if (!TextUtils.isEmpty(noticMainActivity.liuyanNumText.getText().toString()) && !"0".equals(noticMainActivity.liuyanNumText.getText().toString())) {
                        noticMainActivity.liuyanManager.clearLiuyanHongian(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.notice.NoticMainActivity.2
                            @Override // io.reactivex.functions.Consumer
                            public void accept(String str) throws Exception {
                                try {
                                    if ("0000".equals(new JSONObject(str).optString("status"))) {
                                        UIUtils.toastCenter("已将所有留言标记为已读");
                                        NoticMainActivity.this.mFragmentContent.reloadCurrentWebivew(new ReloadWebviewEvent(0));
                                        NoticMainActivity.this.liuyanNumText.setVisibility(4);
                                        NoticMainActivity.this.liuyanNumText.setText("0");
                                    } else {
                                        UIUtils.toastCenter("网络走丢了，重试一下");
                                    }
                                } catch (Exception e) {
                                    UIUtils.toastCenter("网络走丢了，重试一下");
                                    String str2 = NoticMainActivity.TAG;
                                    MsLogUtil.m7977e(str2, "解析一键已读接口异常:" + e.getMessage());
                                }
                            }
                        });
                        return;
                    }
                    UIUtils.toastCenter("暂无未读留言");
                    return;
                }
                return;
            }
            new AvoidOnResult(noticMainActivity.activityContext).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.notice.NoticMainActivity.3
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    if (App.hasLogined()) {
                        NoticMainActivity.this.changeSelectTab(1);
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            if (NoticMainActivity.this.pushMsgDao.getPushMessageRecordSize(UserManager.getInstance().getCurrentPhoneNumber(), "0") > 0) {
                                jSONObject2.put("code", "0000");
                            } else {
                                jSONObject2.put("code", "0001");
                            }
                            NoticMainActivity.this.pushMsgDao.updateAllPushMessageRecordStatus("1");
                            NoticMainActivity.this.xinxiFragment.postEventToJS(MsJSEvent.clearRedPoint, jSONObject2);
                            NoticMainActivity.this.xinxiFragment.postEventToJS(MsJSEvent.onPageShow, new JSONObject());
                            NoticMainActivity.this.setNum(0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initListener$4(NoticMainActivity noticMainActivity, View view) {
        if (isFastClick()) {
            return;
        }
        if (App.hasLogined()) {
            IntentManager.generateIntentAndGo(noticMainActivity.activityContext, noticMainActivity.noticeEntity.getConfigUrl().getSetting());
        } else {
            new AvoidOnResult(noticMainActivity.activityContext).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.notice.NoticMainActivity.4
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    if (App.hasLogined()) {
                        NoticMainActivity.this.changeSelectTab(1);
                        IntentManager.generateIntentAndGo(NoticMainActivity.this.activityContext, NoticMainActivity.this.noticeEntity.getConfigUrl().getSetting());
                    }
                }
            });
        }
    }

    public static /* synthetic */ void lambda$initListener$5(NoticMainActivity noticMainActivity, View view) {
        if (isFastClick()) {
            return;
        }
        IntentManager.generateIntentAndGo(noticMainActivity.activityContext, noticMainActivity.noticeEntity.getConfigUrl().getCollect());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeSelectTab(int i) {
        switch (i) {
            case 0:
                switchContent(this.mFragmentContent, this.kuaixunFragment);
                this.kuanxunView.setVisibility(0);
                this.xinxiView.setVisibility(4);
                this.liuyanView.setVisibility(4);
                this.kuanxunText.setTextColor(-13421773);
                this.xinxiText.setTextColor(-10066330);
                this.liuyanText.setTextColor(-10066330);
                this.kuanxunText.getPaint().setFakeBoldText(true);
                this.xinxiText.getPaint().setFakeBoldText(false);
                this.liuyanText.getPaint().setFakeBoldText(false);
                this.kuaixunCollectImage.setVisibility(0);
                this.clearImage.setVisibility(8);
                this.settingImage.setVisibility(8);
                break;
            case 1:
                switchContent(this.mFragmentContent, this.xinxiFragment);
                this.kuanxunView.setVisibility(4);
                this.xinxiView.setVisibility(0);
                this.liuyanView.setVisibility(4);
                this.kuanxunText.setTextColor(-10066330);
                this.xinxiText.setTextColor(-13421773);
                this.liuyanText.setTextColor(-10066330);
                this.kuanxunText.getPaint().setFakeBoldText(false);
                this.xinxiText.getPaint().setFakeBoldText(true);
                this.liuyanText.getPaint().setFakeBoldText(false);
                this.kuaixunCollectImage.setVisibility(8);
                this.clearImage.setVisibility(0);
                this.settingImage.setVisibility(0);
                break;
            case 2:
                switchContent(this.mFragmentContent, this.liuyanFragment);
                this.kuanxunView.setVisibility(4);
                this.xinxiView.setVisibility(4);
                this.liuyanView.setVisibility(0);
                this.kuanxunText.setTextColor(-10066330);
                this.xinxiText.setTextColor(-10066330);
                this.liuyanText.setTextColor(-13421773);
                this.kuanxunText.getPaint().setFakeBoldText(false);
                this.xinxiText.getPaint().setFakeBoldText(false);
                this.liuyanText.getPaint().setFakeBoldText(true);
                this.kuaixunCollectImage.setVisibility(8);
                this.clearImage.setVisibility(0);
                this.settingImage.setVisibility(8);
                break;
        }
        hideGuhua();
    }

    private void switchContent(WebFragment webFragment, WebFragment webFragment2) {
        try {
            if (this.mFragmentContent != webFragment2) {
                this.mFragmentContent = webFragment2;
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                if (!webFragment2.isAdded()) {
                    beginTransaction.hide(webFragment).add(2131298139, webFragment2).commitAllowingStateLoss();
                } else {
                    beginTransaction.hide(webFragment).show(webFragment2).commitAllowingStateLoss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNum(int i) {
        if (i > 0) {
            if (i > 3) {
                i = 3;
            }
            this.xinxiNumText.setVisibility(0);
            this.xinxiNumText.setText(i + "");
            return;
        }
        this.xinxiNumText.setVisibility(4);
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        if (this.mFragmentContent == this.liuyanFragment) {
            queryLiuyan();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void queryLiuyan() {
        this.liuyanManager.queryLiuyan(new Consumer<Integer>() { // from class: com.sinovatech.unicom.separatemodule.notice.NoticMainActivity.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Integer num) {
                try {
                    if (num.intValue() > 0) {
                        NoticMainActivity.this.liuyanNumText.setVisibility(0);
                        if (num.intValue() > 3) {
                            NoticMainActivity.this.liuyanNumText.setText("3+");
                        } else {
                            NoticMainActivity.this.liuyanNumText.setText(String.valueOf(num));
                        }
                    } else {
                        NoticMainActivity.this.liuyanNumText.setVisibility(4);
                        NoticMainActivity.this.liuyanNumText.setText("0");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < 200;
        lastClickTime = currentTimeMillis;
        return z;
    }

    public static void gotoNotice(Activity activity, int i) {
        Intent intent = new Intent(activity, NoticMainActivity.class);
        intent.putExtra("index", i);
        activity.startActivity(intent);
    }

    private void hideGuhua() {
        if ("02".equals(UserManager.getInstance().getCurrentPhoneType())) {
            this.liuyanView.setVisibility(8);
            this.liuyanLayout.setVisibility(8);
            return;
        }
        this.liuyanLayout.setVisibility(0);
    }

    private int getCurrentTabIndex() {
        WebFragment webFragment = this.mFragmentContent;
        if (webFragment == this.xinxiFragment) {
            return 1;
        }
        return webFragment == this.liuyanFragment ? 2 : 0;
    }
}
