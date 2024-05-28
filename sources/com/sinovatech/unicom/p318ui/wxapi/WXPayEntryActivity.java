package com.sinovatech.unicom.p318ui.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.modelbase.BaseResp;
import com.tencent.p348mm.sdk.openapi.IWXAPI;
import com.tencent.p348mm.sdk.openapi.IWXAPIEventHandler;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.ui.wxapi.WXPayEntryActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    public NBSTraceUnit _nbs_trace;
    private IWXAPI api;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return JniLib.m15917cZ(this, motionEvent, 364);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 365);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return JniLib.m15917cZ(this, Integer.valueOf(i), keyEvent, 366);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        JniLib.m15918cV(this, intent, 367);
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        JniLib.m15918cV(this, baseResp, 368);
    }

    @Override // android.app.Activity
    public void onRestart() {
        JniLib.m15918cV(this, 369);
    }

    @Override // android.app.Activity
    public void onResume() {
        JniLib.m15918cV(this, 370);
    }

    @Override // android.app.Activity
    public void onStart() {
        JniLib.m15918cV(this, 371);
    }

    @Override // android.app.Activity
    public void onStop() {
        JniLib.m15918cV(this, 372);
    }
}
