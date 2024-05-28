package com.megvii.lv5.sdk.detect.guide;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.megvii.lv5.C5399c3;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5415d3;
import com.megvii.lv5.C5435f1;
import com.megvii.lv5.C5538q0;
import com.megvii.lv5.EnumC5548r2;
import com.megvii.lv5.RunnableC5481j0;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* compiled from: Proguard */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class GrantActivity extends Activity implements View.OnClickListener {

    /* renamed from: F */
    public static final /* synthetic */ int f13477F = 0;

    /* renamed from: A */
    public boolean f13478A;

    /* renamed from: B */
    public int f13479B;

    /* renamed from: C */
    public String f13480C;

    /* renamed from: D */
    public String f13481D;

    /* renamed from: E */
    public int f13482E = 3;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    public Button f13483a;

    /* renamed from: b */
    public CheckBox f13484b;

    /* renamed from: c */
    public CheckBox f13485c;

    /* renamed from: d */
    public LinearLayout f13486d;

    /* renamed from: e */
    public LinearLayout f13487e;

    /* renamed from: f */
    public TextView f13488f;

    /* renamed from: g */
    public TextView f13489g;

    /* renamed from: h */
    public LinearLayout f13490h;

    /* renamed from: i */
    public TextView f13491i;

    /* renamed from: j */
    public LinearLayout f13492j;

    /* renamed from: k */
    public LinearLayout f13493k;

    /* renamed from: l */
    public TextView f13494l;

    /* renamed from: m */
    public TextView f13495m;

    /* renamed from: n */
    public RelativeLayout f13496n;

    /* renamed from: o */
    public RelativeLayout f13497o;

    /* renamed from: p */
    public ImageView f13498p;

    /* renamed from: q */
    public ImageView f13499q;

    /* renamed from: r */
    public ImageView f13500r;

    /* renamed from: s */
    public C5415d3 f13501s;

    /* renamed from: t */
    public int f13502t;

    /* renamed from: u */
    public String f13503u;

    /* renamed from: v */
    public String f13504v;

    /* renamed from: w */
    public String f13505w;

    /* renamed from: x */
    public int f13506x;

    /* renamed from: y */
    public boolean f13507y;

    /* renamed from: z */
    public String f13508z;

    /* renamed from: a */
    public final void m13041a() {
        C5402d.f12429a = "FaceIDZFAC";
        C5402d c5402d = C5402d.C5403a.f12436a;
        C5399c3.m13606a(c5402d.m13599a("exit_guide_page", this.f13505w, this.f13502t));
        C5402d.f12429a = "FaceIDZFAC";
        C5399c3.m13606a(c5402d.m13599a("fail_detect:user_cancel", this.f13505w, this.f13502t));
        C5435f1.C5437b.f12597a.mo13538a(EnumC5548r2.USER_CANCEL, "", null, "".getBytes());
        finish();
        finish();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        if (context.getResources().getConfiguration().fontScale != 1.0f) {
            Configuration configuration = new Configuration(context.getResources().getConfiguration());
            configuration.fontScale = 1.0f;
            applyOverrideConfiguration(configuration);
        }
        super.attachBaseContext(context);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    /*  JADX ERROR: IllegalArgumentException in pass: AttachTryCatchVisitor
        java.lang.IllegalArgumentException: newPosition > limit: (549343873 > 13931064)
        	at java.nio.Buffer.createPositionException(Buffer.java:269)
        	at java.nio.Buffer.position(Buffer.java:244)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:177)
        	at jadx.plugins.input.dex.sections.SectionReader.getType(SectionReader.java:165)
        	at jadx.plugins.input.dex.sections.DexCodeReader.getCatchHandlers(DexCodeReader.java:170)
        	at jadx.plugins.input.dex.sections.DexCodeReader.getTries(DexCodeReader.java:142)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:47)
        */
    @Override // android.view.View.OnClickListener
    public void onClick(android.view.View r11) {
        /*
            Method dump skipped, instructions count: 929
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.guide.GrantActivity.onClick(android.view.View):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: ARITH  (r142 I:double) = (r171 I:double) % (r0 I:double), expected to be less than 13
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    @Override // android.app.Activity
    public void onCreate(android.os.Bundle r12) {
        /*
            Method dump skipped, instructions count: 1320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.guide.GrantActivity.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (C5415d3.f12534a != null) {
            C5415d3.f12534a = null;
        }
        this.f13501s = null;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            m13041a();
            return true;
        }
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
        this.f13483a.post(new RunnableC5481j0(this));
        C5538q0.m13194a((Activity) this);
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
