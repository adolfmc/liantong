package com.sinovatech.unicom.basic.p315ui.share;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.ScreenShotUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import java.io.File;
import java.util.HashMap;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.share.SaveShareImgActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SaveShareImgActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private ImageButton backButton;
    private String businessCode;
    private String businessName;
    private ImageView mImgHuaBao;
    private ImageView mImgSave;
    private String mobileA;
    private String oldShareUrl;
    private TextView titleView;

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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(2131492943);
        this.titleView = (TextView) findViewById(2131298800);
        this.backButton = (ImageButton) findViewById(2131296473);
        this.mImgHuaBao = (ImageView) findViewById(2131298604);
        this.mImgSave = (ImageView) findViewById(2131298605);
        Intent intent = getIntent();
        if (intent != null) {
            this.mobileA = intent.getStringExtra("mobileA");
            this.businessName = intent.getStringExtra("businessName");
            this.businessCode = intent.getStringExtra("businessCode");
            this.oldShareUrl = intent.getStringExtra("oldShareUrl");
        }
        this.titleView.setText(this.businessName);
        MsLogUtil.m7979d("画报url", ScreenShotUtils.LongWebviewCapturePicture);
        ShowImageUtils.showRoundImageView(this, ScreenShotUtils.LongWebviewCapturePicture, this.mImgHuaBao, UIUtils.dip2px(7.0f));
        this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.share.SaveShareImgActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                SaveShareImgActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImgSave.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.share.SaveShareImgActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                    SaveShareImgActivity.this.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(ScreenShotUtils.LongWebviewCapturePicture))));
                    SaveShareImgActivity.this.upLoadLog();
                    UIUtils.toast("图片保存完成,快去分享给好友吧~");
                    SaveShareImgActivity.this.finish();
                } catch (Exception e) {
                    UIUtils.logE(e.getMessage());
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        NBSAppInstrumentation.activityCreateEndIns();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upLoadLog() {
        HashMap hashMap = new HashMap();
        hashMap.put("step", "1");
        hashMap.put("mobileA", this.mobileA);
        hashMap.put("shareUrl", this.oldShareUrl);
        hashMap.put("businessName", this.businessName);
        hashMap.put("businessCode", this.businessCode);
        hashMap.put("shareType", "2");
        hashMap.put("shareCompleteUrls", App.originalUrl);
        ShareLogUtil.upLoadSharedByLog(hashMap);
    }
}
