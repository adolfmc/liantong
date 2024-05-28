package com.sinovatech.unicom.separatemodule.dianziqianming;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.dianziqianming.entity.SignatureEntity;
import com.sinovatech.unicom.separatemodule.dianziqianming.utils.SignatureUtils;
import com.sinovatech.unicom.separatemodule.dianziqianming.view.PathView;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SignatureActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "SignatureActivity";
    public static boolean isNeedYasuo;
    public NBSTraceUnit _nbs_trace;
    private boolean isHaveQm;
    private Button mBtQmCancel;
    private Button mBtQmOk;
    private RelativeLayout mRlQmLayout;
    private TextView mTvQmClear;
    private TextView qmDescribe;
    private PathView signaturePads;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 81);
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

    /* renamed from: com.sinovatech.unicom.separatemodule.dianziqianming.SignatureActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class RunnableC86571 implements Runnable {
        RunnableC86571() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SignatureActivity.isNeedYasuo) {
                SignatureUtils.yaSuoXY = 0;
                SignatureUtils.isYaSuo = false;
                SignatureUtils.oldSignaturePadsHeight = SignatureActivity.this.signaturePads.getMeasuredHeight();
                SignatureUtils.oldSignaturePadsWidth = SignatureActivity.this.signaturePads.getMeasuredWidth();
                SignatureUtils.signaturePadsWidth = SignatureUtils.oldSignaturePadsWidth;
                SignatureUtils.signaturePadsHeight = SignatureUtils.oldSignaturePadsHeight;
                if (SignatureUtils.oldSignaturePadsWidth <= SignatureUtils.qWidth) {
                    if (SignatureUtils.oldSignaturePadsHeight > SignatureUtils.qHeight) {
                        SignatureUtils.isYaSuo = true;
                        SignatureUtils.yaSuoXY = 2;
                        SignatureUtils.signaturePadsWidth = (int) ((SignatureUtils.oldSignaturePadsWidth / SignatureUtils.oldSignaturePadsHeight) * SignatureUtils.qHeight);
                        SignatureUtils.signaturePadsHeight = SignatureUtils.qHeight;
                        return;
                    }
                    return;
                }
                SignatureUtils.isYaSuo = true;
                SignatureUtils.yaSuoXY = 1;
                SignatureUtils.signaturePadsWidth = SignatureUtils.qWidth;
                SignatureUtils.signaturePadsHeight = (int) ((SignatureUtils.oldSignaturePadsHeight / SignatureUtils.oldSignaturePadsWidth) * SignatureUtils.qWidth);
                if (SignatureUtils.signaturePadsHeight > SignatureUtils.qHeight) {
                    SignatureUtils.yaSuoXY = 3;
                    SignatureUtils.signaturePadsWidth = (int) ((SignatureUtils.signaturePadsWidth / SignatureUtils.signaturePadsHeight) * SignatureUtils.qHeight);
                    SignatureUtils.signaturePadsHeight = SignatureUtils.qHeight;
                }
            }
        }
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setRequestedOrientation(0);
    }

    private void initView() {
        this.mRlQmLayout = (RelativeLayout) findViewById(2131298244);
        this.signaturePads = (PathView) findViewById(2131298243);
        this.mTvQmClear = (TextView) findViewById(2131298246);
        this.mBtQmOk = (Button) findViewById(2131298248);
        this.mBtQmCancel = (Button) findViewById(2131298245);
        this.qmDescribe = (TextView) findViewById(2131298247);
    }

    private void initListener() {
        this.mTvQmClear.setOnClickListener(this);
        this.mBtQmCancel.setOnClickListener(this);
        this.mBtQmOk.setOnClickListener(this);
        this.signaturePads.setOnSignedListener(new PathView.OnSignedListener() { // from class: com.sinovatech.unicom.separatemodule.dianziqianming.SignatureActivity.2
            @Override // com.sinovatech.unicom.separatemodule.dianziqianming.view.PathView.OnSignedListener
            public void onStartSigning() {
            }

            @Override // com.sinovatech.unicom.separatemodule.dianziqianming.view.PathView.OnSignedListener
            public void onSigned() {
                SignatureActivity.this.isHaveQm = true;
            }

            @Override // com.sinovatech.unicom.separatemodule.dianziqianming.view.PathView.OnSignedListener
            public void onClear() {
                SignatureActivity.this.isHaveQm = false;
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        switch (view.getId()) {
            case 2131298245:
                Intent intent = new Intent();
                intent.putExtra("msg", "用户主动取消");
                setResult(1, intent);
                finish();
                break;
            case 2131298246:
                this.signaturePads.clear();
                this.signaturePads.clearData();
                break;
            case 2131298247:
            default:
                MsLogUtil.m7977e(TAG, "无效的点击事件");
                break;
            case 2131298248:
                if (!this.isHaveQm) {
                    UIUtils.toast("您还没有签名");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                JSONArray jSONArray = null;
                if (isNeedYasuo) {
                    try {
                        List<SignatureEntity> list = SignatureUtils.signatureEntities;
                        if (list.size() > 0) {
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i = 0; i < list.size(); i++) {
                                try {
                                    JSONArray jSONArray3 = new JSONArray();
                                    JSONArray jSONArray4 = new JSONArray();
                                    JSONArray jSONArray5 = new JSONArray();
                                    SignatureEntity signatureEntity = list.get(i);
                                    for (int i2 = 0; i2 < signatureEntity.getxList().size(); i2++) {
                                        jSONArray3.put(SignatureUtils.zHDouble(signatureEntity.getxList().get(i2)));
                                        jSONArray4.put(SignatureUtils.zHDouble(signatureEntity.getyList().get(i2)));
                                        jSONArray5.put(SignatureUtils.zHDouble(signatureEntity.getzList().get(i2)));
                                    }
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("x", jSONArray3);
                                    jSONObject.put("y", jSONArray4);
                                    jSONObject.put("z", jSONArray5);
                                    jSONArray2.put(jSONObject);
                                } catch (Exception unused) {
                                    jSONArray = jSONArray2;
                                }
                            }
                            jSONArray = jSONArray2;
                        }
                    } catch (Exception unused2) {
                    }
                }
                Bitmap viewToBitmap = viewToBitmap(this.signaturePads);
                if (SignatureUtils.isYaSuo && viewToBitmap != null && isNeedYasuo) {
                    viewToBitmap = SignatureUtils.imageScale(viewToBitmap, SignatureUtils.signaturePadsWidth, SignatureUtils.signaturePadsHeight);
                }
                Intent intent2 = new Intent();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                viewToBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                intent2.putExtra("signPic", Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
                if (isNeedYasuo) {
                    intent2.putExtra("x", String.valueOf(SignatureUtils.oldSignaturePadsWidth));
                    intent2.putExtra("y", String.valueOf(SignatureUtils.oldSignaturePadsHeight));
                    if (jSONArray != null) {
                        intent2.putExtra("dataArray", !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
                    } else {
                        intent2.putExtra("dataArray", "");
                    }
                }
                setResult(-1, intent2);
                finish();
                break;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private Bitmap viewToBitmap(View view) {
        view.buildDrawingCache();
        return view.getDrawingCache();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.putExtra("msg", "用户主动取消");
            setResult(1, intent);
            finish();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
