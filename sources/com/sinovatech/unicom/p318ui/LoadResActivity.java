package com.sinovatech.unicom.p318ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.ui.LoadResActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoadResActivity extends Activity {
    public NBSTraceUnit _nbs_trace;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return JniLib.m15917cZ(this, motionEvent, 317);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        JniLib.m15918cV(this, 318);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 319);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return JniLib.m15917cZ(this, Integer.valueOf(i), keyEvent, 320);
    }

    @Override // android.app.Activity
    public void onRestart() {
        JniLib.m15918cV(this, 321);
    }

    @Override // android.app.Activity
    public void onResume() {
        JniLib.m15918cV(this, 322);
    }

    @Override // android.app.Activity
    public void onStart() {
        JniLib.m15918cV(this, 323);
    }

    @Override // android.app.Activity
    public void onStop() {
        JniLib.m15918cV(this, 324);
    }

    /* renamed from: com.sinovatech.unicom.ui.LoadResActivity$LoadDexTask */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class LoadDexTask extends AsyncTask {
        @Override // android.os.AsyncTask
        protected Object doInBackground(Object[] objArr) {
            return JniLib.m15920cL(this, objArr, 315);
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(Object obj) {
            JniLib.m15918cV(this, obj, 316);
        }

        LoadDexTask() {
        }
    }
}
