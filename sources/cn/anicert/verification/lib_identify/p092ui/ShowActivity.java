package cn.anicert.verification.lib_identify.p092ui;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import cn.ctid.verification.AbstractC1607E;
import cn.ctid.verification.ActivityC1632r;
import cn.ctid.verification.C1618d;
import cn.ctid.verification.FragmentC1631q;
import cn.ctid.verification.InterfaceC1602A;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.tfd.sdk.LF8bOvWP4;

@NBSInstrumented
/* renamed from: cn.anicert.verification.lib_identify.ui.ShowActivity */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class ShowActivity extends ActivityC1632r implements InterfaceC1602A {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    private AbstractC1607E f2603a;

    /* renamed from: b */
    private FrameLayout f2604b;

    /* renamed from: c */
    private C1618d f2605c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.anicert.verification.lib_identify.ui.ShowActivity$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C1582a extends AbstractC1607E {
        C1582a(int i, FragmentManager fragmentManager) {
            super(i, fragmentManager);
        }
    }

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(179);
    }

    /* renamed from: a */
    private static native Intent m22150a(Context context, String str, String str2, int i);

    /* renamed from: a */
    public static native void m22153a(Context context, Class<? extends FragmentC1631q> cls);

    /* renamed from: a */
    public static native void m22152a(Context context, Class<? extends FragmentC1631q> cls, int i);

    /* renamed from: a */
    public static native void m22151a(Context context, Class<? extends FragmentC1631q> cls, Bundle bundle);

    @Override // cn.ctid.verification.InterfaceC1602A
    /* renamed from: a */
    public native void mo22073a();

    @Override // cn.ctid.verification.InterfaceC1602A
    /* renamed from: b */
    public native void mo22071b();

    @Override // android.app.Activity
    public native void onBackPressed();

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    @Override // android.app.Activity
    protected native void onDestroy();

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }
}
