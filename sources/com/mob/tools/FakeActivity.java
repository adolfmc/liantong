package com.mob.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.mob.commons.C5857m;
import com.mob.tools.proguard.EverythingKeeper;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.lang.reflect.Method;
import java.util.HashMap;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FakeActivity implements EverythingKeeper {
    private static Class<? extends Activity> shellClass;
    public Activity activity;
    private View contentView;
    private HashMap<String, Object> result;
    private FakeActivity resultReceiver;

    public void beforeStartActivityForResult(Intent intent, int i, Bundle bundle) {
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onCreate() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void onDestroy() {
    }

    public boolean onFinish() {
        return false;
    }

    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        return false;
    }

    public void onNewIntent(Intent intent) {
    }

    public void onPause() {
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onRestart() {
    }

    public void onResult(HashMap<String, Object> hashMap) {
    }

    public void onResume() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int onSetTheme(int i, boolean z) {
        return i;
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public static void setShell(Class<? extends Activity> cls) {
        shellClass = cls;
    }

    public static void registerExecutor(String str, Object obj) {
        Class<? extends Activity> cls = shellClass;
        if (cls != null) {
            try {
                Method method = cls.getMethod(C5857m.m12226a("016.bhSd*ccbgdf2gd(bheeca<da>beNg?cbbh"), String.class, Object.class);
                method.setAccessible(true);
                method.invoke(null, str, obj);
                return;
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
                return;
            }
        }
        MobUIShell.m11826a(str, obj);
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean disableScreenCapture() {
        if (this.activity == null || Build.VERSION.SDK_INT < 11) {
            return false;
        }
        this.activity.getWindow().setFlags(8192, 8192);
        return true;
    }

    public void setContentViewLayoutResName(String str) {
        int layoutRes;
        Activity activity = this.activity;
        if (activity != null && (layoutRes = ResHelper.getLayoutRes(activity, str)) > 0) {
            this.activity.setContentView(layoutRes);
        }
    }

    public void setContentView(View view) {
        this.contentView = view;
    }

    public View getContentView() {
        return this.contentView;
    }

    public <T extends View> T findViewById(int i) {
        Activity activity = this.activity;
        if (activity == null) {
            return null;
        }
        return (T) activity.findViewById(i);
    }

    public <T extends View> T findViewByResName(View view, String str) {
        int idRes;
        Activity activity = this.activity;
        if (activity != null && (idRes = ResHelper.getIdRes(activity, str)) > 0) {
            return (T) view.findViewById(idRes);
        }
        return null;
    }

    public <T extends View> T findViewByResName(String str) {
        int idRes;
        Activity activity = this.activity;
        if (activity != null && (idRes = ResHelper.getIdRes(activity, str)) > 0) {
            return (T) findViewById(idRes);
        }
        return null;
    }

    public final void finish() {
        Activity activity = this.activity;
        if (activity != null) {
            activity.finish();
        }
    }

    public void startActivity(Intent intent) {
        startActivityForResult(intent, -1);
    }

    public void startActivityForResult(Intent intent, int i) {
        Activity activity = this.activity;
        if (activity == null) {
            return;
        }
        activity.startActivityForResult(intent, i);
    }

    public Context getContext() {
        return this.activity;
    }

    public void show(Context context, Intent intent) {
        showForResult(context, intent, null);
    }

    public void showForResult(final Context context, Intent intent, FakeActivity fakeActivity) {
        final Intent intent2;
        this.resultReceiver = fakeActivity;
        Class<? extends Activity> cls = shellClass;
        String str = null;
        if (cls != null) {
            intent2 = new Intent(context, cls);
            try {
                Method method = shellClass.getMethod(C5857m.m12226a("016Qbh=d*ccbgdf-gd^bheeca1daObeUg+cbbh"), Object.class);
                method.setAccessible(true);
                str = (String) method.invoke(null, this);
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
            }
        } else {
            intent2 = new Intent(context, MobUIShell.class);
            str = MobUIShell.m11828a(this);
        }
        intent2.putExtra(C5857m.m12226a("011eb-be caf'bf)g1bgbd]d"), str);
        intent2.putExtra(C5857m.m12226a("013d5ca@daTbeHg?cbbhbfNcbLbdRd"), getClass().getName());
        if (intent != null) {
            intent2.putExtras(intent);
        }
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            showActivity(context, intent2);
        } else {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.tools.FakeActivity.1
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    FakeActivity.this.showActivity(context, intent2);
                    return false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showActivity(final Context context, final Intent intent) {
        if (!(context instanceof Activity)) {
            C6152DH.requester(context).getTopActivity().request(new C6152DH.DHResponder() { // from class: com.mob.tools.FakeActivity.2
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                public void onResponse(C6152DH.DHResponse dHResponse) {
                    Activity topActivity = dHResponse.getTopActivity();
                    if (topActivity == null) {
                        intent.addFlags(268435456);
                        context.startActivity(intent);
                        return;
                    }
                    topActivity.startActivity(intent);
                }
            });
        } else {
            context.startActivity(intent);
        }
    }

    public FakeActivity getParent() {
        return this.resultReceiver;
    }

    public final void setResult(HashMap<String, Object> hashMap) {
        this.result = hashMap;
    }

    public void sendResult() {
        FakeActivity fakeActivity = this.resultReceiver;
        if (fakeActivity != null) {
            fakeActivity.onResult(this.result);
        }
    }

    public void runOnUIThread(final Runnable runnable) {
        UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.tools.FakeActivity.3
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                runnable.run();
                return false;
            }
        });
    }

    public void runOnUIThread(final Runnable runnable, long j) {
        UIHandler.sendEmptyMessageDelayed(0, j, new Handler.Callback() { // from class: com.mob.tools.FakeActivity.4
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                runnable.run();
                return false;
            }
        });
    }

    public void setRequestedOrientation(int i) {
        if (this.activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 26 || getContext().getApplicationInfo().targetSdkVersion < 27) {
            this.activity.setRequestedOrientation(i);
        }
    }

    public void requestPortraitOrientation() {
        setRequestedOrientation(1);
    }

    public void requestLandscapeOrientation() {
        setRequestedOrientation(0);
    }

    public void requestSensorPortraitOrientation() {
        setRequestedOrientation(7);
    }

    public void requestSensorLandscapeOrientation() {
        setRequestedOrientation(6);
    }

    public int getOrientation() {
        return this.activity.getResources().getConfiguration().orientation;
    }

    public void requestFullScreen(boolean z) {
        Activity activity = this.activity;
        if (activity == null) {
            return;
        }
        if (z) {
            activity.getWindow().addFlags(1024);
            this.activity.getWindow().clearFlags(2048);
        } else {
            activity.getWindow().addFlags(2048);
            this.activity.getWindow().clearFlags(1024);
        }
        this.activity.getWindow().getDecorView().requestLayout();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        NBSActionInstrumentation.onOptionsItemSelectedEnter(menuItem, this);
        NBSActionInstrumentation.onOptionsItemSelectedExit();
        return false;
    }

    public void requestPermissions(String[] strArr, int i) {
        if (this.activity != null && Build.VERSION.SDK_INT >= 23) {
            ReflectHelper.invokeInstanceMethod(this.activity, C5857m.m12226a("018ZbhQd1bcbe=d1dfYgTeiQdAbhbdbgdfdfbgcb.c+df"), new Object[]{strArr, Integer.valueOf(i)}, new Class[]{String.class, Integer.TYPE}, null);
        }
    }
}
