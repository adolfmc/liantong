package com.bytedance.android.openliveplugin.stub.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import com.bytedance.android.live.base.api.IOuterLiveRoomService;
import com.bytedance.android.live.base.api.JavaCallsUtils;
import com.bytedance.android.openliveplugin.LivePluginHelper;
import com.bytedance.pangle.activity.GenerateProxyActivity;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StubActivity {

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class SuperActivity extends GenerateProxyActivity {
        Handler handler;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public String getPluginPkgName() {
            return "com.byted.live.lite";
        }

        SuperActivity() {
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z) {
                return;
            }
            if (this.handler == null) {
                this.handler = new Handler(Looper.getMainLooper());
            }
            this.handler.post(new Runnable() { // from class: com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    SuperActivity.this.execAsyncCheckReport();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void execAsyncCheckReport() {
            LivePluginHelper.sExecutor.execute(new Runnable() { // from class: com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    IOuterLiveRoomService liveRoomService = LivePluginHelper.getLiveRoomService();
                    if (liveRoomService == null || SuperActivity.this.mTargetActivity == null) {
                        return;
                    }
                    try {
                        liveRoomService.callExpandMethod("report_window_focus_change", JavaCallsUtils.getField(SuperActivity.this, "mToken"), SuperActivity.this.mTargetActivity.getClass().getCanonicalName());
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Activity_Portrait extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            NBSTraceEngine.startTracing(getClass().getName());
            super.onCreate(bundle);
            NBSAppInstrumentation.activityCreateEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class AppCompat extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            JniLib.m15918cV(this, bundle, 7);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class AppCompat_T extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            NBSTraceEngine.startTracing(getClass().getName());
            super.onCreate(bundle);
            NBSAppInstrumentation.activityCreateEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class AppCompat_T_SingleTop1 extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            NBSTraceEngine.startTracing(getClass().getName());
            super.onCreate(bundle);
            NBSAppInstrumentation.activityCreateEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Activity_T extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            NBSTraceEngine.startTracing(getClass().getName());
            super.onCreate(bundle);
            NBSAppInstrumentation.activityCreateEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Activity extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            JniLib.m15918cV(this, bundle, 6);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class AppCompat_Portrait extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            NBSTraceEngine.startTracing(getClass().getName());
            super.onCreate(bundle);
            NBSAppInstrumentation.activityCreateEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Activity_T_SingleTask2 extends SuperActivity {
        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Activity_Behind extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            NBSTraceEngine.startTracing(getClass().getName());
            super.onCreate(bundle);
            NBSAppInstrumentation.activityCreateEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }

    @NBSInstrumented
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class AppCompat_SingleTask2 extends SuperActivity {
        public NBSTraceUnit _nbs_trace;

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onCreate(Bundle bundle) {
            NBSTraceEngine.startTracing(getClass().getName());
            super.onCreate(bundle);
            NBSAppInstrumentation.activityCreateEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            return super.onKeyDown(i, keyEvent);
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onRestart() {
            NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
            super.onRestart();
            NBSAppInstrumentation.activityRestartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onResume() {
            NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
            super.onResume();
            NBSAppInstrumentation.activityResumeEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStart() {
            NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
            super.onStart();
            NBSAppInstrumentation.activityStartEndIns();
        }

        @Override // com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity
        public void onStop() {
            NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
            super.onStop();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, com.bytedance.pangle.activity.InterfaceC3773b
        public /* bridge */ /* synthetic */ String getPluginPkgName() {
            return super.getPluginPkgName();
        }

        @Override // com.bytedance.android.openliveplugin.stub.activity.StubActivity.SuperActivity, com.bytedance.pangle.activity.GenerateProxyActivity, android.app.Activity, android.view.Window.Callback
        public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
        }
    }
}
