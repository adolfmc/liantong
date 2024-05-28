package com.mob.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.ReflectHelper;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MobUIShell extends Activity {

    /* renamed from: a */
    private static HashMap<String, FakeActivity> f14801a = new HashMap<>();
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private FakeActivity f14802b;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 23);
    }

    static {
        MobLog.getInstance().m11342d("===============================", new Object[0]);
        String replace = "2024-03-06".replace("-0", "-").replace("-", ".");
        MobLog.getInstance().m11342d("MobTools " + replace, new Object[0]);
        MobLog.getInstance().m11342d("===============================", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static String m11828a(Object obj) {
        return m11826a(String.valueOf(System.currentTimeMillis()), obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static String m11826a(String str, Object obj) {
        f14801a.put(str, (FakeActivity) obj);
        return str;
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i) {
        if (m11825b()) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            int i2 = 0;
            while (i2 < stackTrace.length) {
                if (stackTrace[i2].toString().startsWith(C5731l.m12674a("030PiiYe?eeReQgeBhef7ffgefl0i)ekYge9edgeff=gjHfkGjed1emflek7edg")) && (i2 = i2 + 2) < stackTrace.length) {
                    int onSetTheme = this.f14802b.onSetTheme(i, stackTrace[i2].toString().startsWith(C5731l.m12674a("048ef_edekfeejedge,ekk;gefm1dj7ejeeej@jYelfl i,ekAgeHedge'kgWekfgfeekeggdMeQeh:fdiHfmZdj_ejeeejEjFel")));
                    if (onSetTheme > 0) {
                        super.setTheme(onSetTheme);
                        return;
                    }
                    return;
                }
                i2++;
            }
        }
        super.setTheme(i);
    }

    /* renamed from: b */
    private boolean m11825b() {
        if (this.f14802b == null) {
            Intent intent = getIntent();
            Uri data = intent.getData();
            if (data != null && C5731l.m12674a("0050egfegfehej").equals(data.getScheme())) {
                this.f14802b = m11827a(data.getHost());
                if (this.f14802b != null) {
                    NLog mobLog = MobLog.getInstance();
                    mobLog.m11333i("MobUIShell found executor: " + this.f14802b.getClass());
                    this.f14802b.setActivity(this);
                    return true;
                }
            }
            try {
                String stringExtra = intent.getStringExtra(C5731l.m12674a("011heOehEfdi,ei,j_ejeg@g"));
                String stringExtra2 = intent.getStringExtra(C5731l.m12674a("013gYfdXgd(ehUj]feekei9fe;eg_g"));
                this.f14802b = f14801a.remove(stringExtra);
                if (this.f14802b == null) {
                    this.f14802b = f14801a.remove(intent.getScheme());
                    if (this.f14802b == null) {
                        this.f14802b = m11829a();
                        if (this.f14802b == null) {
                            NLog mobLog2 = MobLog.getInstance();
                            mobLog2.m11325w(new RuntimeException("Executor lost! launchTime = " + stringExtra + ", executorName: " + stringExtra2));
                            return false;
                        }
                    }
                }
                NLog mobLog3 = MobLog.getInstance();
                mobLog3.m11333i("MobUIShell found executor: " + this.f14802b.getClass());
                this.f14802b.setActivity(this);
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private FakeActivity m11827a(String str) {
        Object newInstance;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (str.startsWith(".")) {
                str = getPackageName() + str;
            }
            String importClass = ReflectHelper.importClass(str);
            if (TextUtils.isEmpty(importClass) || (newInstance = ReflectHelper.newInstance(importClass, new Object[0])) == null || !(newInstance instanceof FakeActivity)) {
                return null;
            }
            return (FakeActivity) newInstance;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    /* renamed from: a */
    public FakeActivity m11829a() {
        String str;
        try {
            str = getPackageManager().getActivityInfo(getComponentName(), 128).metaData.getString(C5731l.m12674a("015-edVg)fg%e]eh(hjZfm@dj]ejeeej*jXel"));
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            str = null;
        }
        return m11827a(str);
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        setContentView(LayoutInflater.from(this).inflate(i, (ViewGroup) null));
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        if (view == null) {
            return;
        }
        super.setContentView(view);
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            fakeActivity.setContentView(view);
        }
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (view == null) {
            return;
        }
        if (layoutParams == null) {
            super.setContentView(view);
        } else {
            super.setContentView(view, layoutParams);
        }
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            fakeActivity.setContentView(view);
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity == null) {
            super.onNewIntent(intent);
        } else {
            fakeActivity.onNewIntent(intent);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        if (this.f14802b != null) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d(this.f14802b.getClass().getSimpleName() + " onStart", new Object[0]);
            this.f14802b.onStart();
        }
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        if (this.f14802b != null) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d(this.f14802b.getClass().getSimpleName() + " onResume", new Object[0]);
            this.f14802b.onResume();
        }
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    protected void onPause() {
        if (this.f14802b != null) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d(this.f14802b.getClass().getSimpleName() + " onPause", new Object[0]);
            this.f14802b.onPause();
        }
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        if (this.f14802b != null) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d(this.f14802b.getClass().getSimpleName() + " onStop", new Object[0]);
            this.f14802b.onStop();
        }
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        if (this.f14802b != null) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d(this.f14802b.getClass().getSimpleName() + " onRestart", new Object[0]);
            this.f14802b.onRestart();
        }
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            fakeActivity.sendResult();
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d(this.f14802b.getClass().getSimpleName() + " onDestroy", new Object[0]);
            this.f14802b.onDestroy();
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            fakeActivity.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        try {
            if (this.f14802b != null ? this.f14802b.onKeyEvent(i, keyEvent) : false) {
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return false;
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        try {
            if (this.f14802b != null ? this.f14802b.onKeyEvent(i, keyEvent) : false) {
                return true;
            }
            return super.onKeyUp(i, keyEvent);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return false;
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            fakeActivity.onConfigurationChanged(configuration);
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            fakeActivity.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity == null || !fakeActivity.onFinish()) {
            super.finish();
        }
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        if (Build.VERSION.SDK_INT == 26 && m11823d()) {
            return;
        }
        super.setRequestedOrientation(i);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean onCreateOptionsMenu = super.onCreateOptionsMenu(menu);
        FakeActivity fakeActivity = this.f14802b;
        return fakeActivity != null ? fakeActivity.onCreateOptionsMenu(menu) : onCreateOptionsMenu;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        NBSActionInstrumentation.onOptionsItemSelectedEnter(menuItem, this);
        boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            boolean onOptionsItemSelected2 = fakeActivity.onOptionsItemSelected(menuItem);
            NBSActionInstrumentation.onOptionsItemSelectedExit();
            return onOptionsItemSelected2;
        }
        NBSActionInstrumentation.onOptionsItemSelectedExit();
        return onOptionsItemSelected;
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            fakeActivity.beforeStartActivityForResult(intent, i, null);
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        FakeActivity fakeActivity = this.f14802b;
        if (fakeActivity != null) {
            fakeActivity.beforeStartActivityForResult(intent, i, bundle);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            super.startActivityForResult(intent, i, bundle);
        } else {
            super.startActivityForResult(intent, i);
        }
    }

    /* renamed from: c */
    private boolean m11824c() {
        if (Build.VERSION.SDK_INT > 27) {
            return false;
        }
        try {
            Field declaredField = Activity.class.getDeclaredField(C5731l.m12674a("013;egfm.djIejeeejNjXelfjCf.fgfe"));
            declaredField.setAccessible(true);
            ((ActivityInfo) declaredField.get(this)).screenOrientation = -1;
            declaredField.setAccessible(false);
            return true;
        } catch (Exception e) {
            MobLog.getInstance().m11324w(e, "Fix orientation for 8.0 encountered exception", new Object[0]);
            return false;
        }
    }

    /* renamed from: d */
    private boolean m11823d() {
        boolean z = false;
        if (Build.VERSION.SDK_INT > 27) {
            return false;
        }
        try {
            TypedArray obtainStyledAttributes = this.f14802b.activity.obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null));
            Method method = ActivityInfo.class.getMethod(C5731l.m12674a("023^ejgiflek0ef1gi@hTeh;dgfj,hmekgm,h(feVejNej'f ff"), TypedArray.class);
            method.setAccessible(true);
            boolean booleanValue = ((Boolean) method.invoke(null, obtainStyledAttributes)).booleanValue();
            try {
                method.setAccessible(false);
                return booleanValue;
            } catch (Exception e) {
                z = booleanValue;
                e = e;
                MobLog.getInstance().m11325w(e);
                return z;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
