package com.sinovatech.unicom.separatemodule.idcard.newaction.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.idcard.newaction.utils.NewCamera2Utils;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ShangChuanIdCardActivity extends AppCompatActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private Bitmap bitmap;
    private Button btn_chongxinpai;
    private Button btn_shangchuan;
    private ImageView camera_back;
    private boolean isZhedieping;
    private ImageView iv_img;

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

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        UIUtils.setStatusBarMode(this, true, true);
        if (NewCamera2Utils.getScreenWidth(this) / NewCamera2Utils.getScreenHeight(this) > 0.7d) {
            this.isZhedieping = true;
            setContentView(2131492935);
        } else {
            this.isZhedieping = false;
            setContentView(2131492934);
        }
        this.iv_img = (ImageView) findViewById(2131297403);
        if (NewCamera2Utils.bitmap != null) {
            this.bitmap = NewCamera2Utils.bitmap;
            this.iv_img.setImageBitmap(this.bitmap);
        }
        this.btn_chongxinpai = (Button) findViewById(2131296544);
        this.btn_chongxinpai.setOnClickListener(this);
        this.btn_shangchuan = (Button) findViewById(2131296549);
        this.btn_shangchuan.setOnClickListener(this);
        this.camera_back = (ImageView) findViewById(2131296567);
        this.camera_back.setOnClickListener(this);
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131296544) {
            PvCurrencyLogUtils.pvLogDJCamera2Api("重新拍按钮", "新方法", "上传身份证原件页面");
            NewCamera2Utils.bitmap = null;
            finish();
        } else if (id == 2131296549) {
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.idcard.newaction.activity.ShangChuanIdCardActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    PvCurrencyLogUtils.pvLogDJCamera2Api("上传按钮", "新方法", "上传身份证原件页面");
                    if (ShangChuanIdCardActivity.this.bitmap != null) {
                        ShangChuanIdCardActivity.this.setResult(-1);
                    } else {
                        ShangChuanIdCardActivity.this.setResult(-2);
                    }
                    ShangChuanIdCardActivity.this.finish();
                }
            }, 500L);
        } else if (id == 2131296567) {
            NewCamera2Utils.bitmap = null;
            finish();
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
