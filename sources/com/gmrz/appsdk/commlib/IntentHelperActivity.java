package com.gmrz.appsdk.commlib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.gmrz.appsdk.FidoOut;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.task.FidoTask;
import com.gmrz.appsdk.util.DeviceChecker;
import com.gmrz.appsdk.util.FindUtil;
import com.gmrz.appsdk.util.Logger;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
@SuppressLint({"NewApi"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IntentHelperActivity extends Activity {

    /* renamed from: c */
    protected static final String f10260c = IntentHelperActivity.class.getSimpleName() + "_fido";

    /* renamed from: d */
    protected static int f10261d;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    private Handler f10262a;

    /* renamed from: b */
    private RunnableC4408a f10263b;

    /* renamed from: com.gmrz.appsdk.commlib.IntentHelperActivity$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4408a implements Runnable {

        /* renamed from: a */
        private boolean f10264a = true;

        /* renamed from: b */
        private boolean f10265b = false;

        /* renamed from: c */
        private final List<String> f10266c = new ArrayList();

        /* renamed from: d */
        private final IntentHelperActivity f10267d;

        RunnableC4408a(IntentHelperActivity intentHelperActivity, int i) {
            this.f10267d = intentHelperActivity;
        }

        /* renamed from: a */
        private boolean m15850a(Context context, List<String> list) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return true;
            }
            String packageName = activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
            return list.contains(packageName) || (packageName.equals(context.getPackageName()) && activityManager.getRunningTasks(1).get(0).topActivity.getClassName().equals(context.getClass().getName()));
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!this.f10265b) {
                    Intent intent = new Intent();
                    intent.setAction("org.fidoalliance.intent.FIDO_OPERATION");
                    intent.setType("application/fido.uaf_asm+json");
                    this.f10266c.addAll(FindUtil.getPackNameByIntent(this.f10267d, intent));
                    Intent intent2 = new Intent();
                    intent2.setAction("org.fidoalliance.intent.FIDO_OPERATION");
                    intent2.setType("application/fido.uaf_client+json");
                    this.f10266c.addAll(FindUtil.getPackNameByIntent(this.f10267d, intent2));
                    this.f10265b = true;
                }
                boolean m15850a = m15850a(this.f10267d, this.f10266c);
                if (this.f10264a && !m15850a) {
                    FidoOut fidoOut = new FidoOut();
                    fidoOut.fidoResponse = null;
                    fidoOut.fidoStatus = FidoStatus.EXIT_THIS_TIME;
                }
                this.f10264a = m15850a;
                IntentHelperActivity.this.f10262a.postDelayed(this, 100L);
            } catch (Exception e) {
                e.printStackTrace();
                Logger.m15757e(IntentHelperActivity.f10260c, "check runnable loop cause exception");
            }
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Logger.m15757e(f10260c, "IntentHelperActivity onActivityResult");
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Logger.m15757e(f10260c, "IntentHelperActivity onConfigurationChanged");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        String str = f10260c;
        Logger.m15757e(str, "IntentHelperActivity onCreate");
        getWindow().setFlags(8192, 8192);
        try {
            FragmentManager fragmentManager = getFragmentManager();
            if (((FragmentC4409b) fragmentManager.findFragmentByTag("RetainedIntentHelperFragment")) == null) {
                fragmentManager.beginTransaction().add(new FragmentC4409b(), "RetainedIntentHelperFragment").commit();
                Logger.m15757e(str, "RetainedIntentHelperFragment setting up Fragment");
            }
            this.f10262a = new Handler();
            RunnableC4408a runnableC4408a = new RunnableC4408a(this, getIntent().getIntExtra("requestId", 0));
            this.f10263b = runnableC4408a;
            this.f10262a.postDelayed(runnableC4408a, 100L);
        } catch (Exception e) {
            e.printStackTrace();
            int intExtra = getIntent().getIntExtra("requestId", 0);
            FidoOut fidoOut = new FidoOut();
            fidoOut.fidoResponse = null;
            fidoOut.fidoStatus = FidoStatus.FAILED;
            m15852a(intExtra, f10261d, fidoOut);
            finish();
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Logger.m15757e(f10260c, "IntentHelperActivity onDestroy");
        Handler handler = this.f10262a;
        if (handler != null) {
            handler.removeCallbacks(this.f10263b);
        }
    }

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

    /* renamed from: com.gmrz.appsdk.commlib.IntentHelperActivity$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class FragmentC4409b extends Fragment {

        /* renamed from: a */
        private Boolean f10269a = Boolean.FALSE;

        /* renamed from: b */
        private int f10270b = 0;

        /* renamed from: a */
        public void m15848a(Boolean bool) {
            String str = IntentHelperActivity.f10260c;
            Logger.m15757e(str, "SetInitialized " + this.f10269a.toString());
            this.f10269a = bool;
        }

        @Override // android.app.Fragment
        public void onActivityResult(int i, int i2, Intent intent) {
            FidoOut m15849a = m15849a(i2, intent);
            String str = IntentHelperActivity.f10260c;
            Logger.m15757e(str, "onActivityResult fido out:" + m15849a);
            try {
                try {
                    UafRequestObject m15843a = UafRequestObject.m15843a(this.f10270b);
                    if (m15843a != null) {
                        synchronized (m15843a.getClass()) {
                            Logger.m15757e(str, "return from onActivity Result");
                            m15843a.m15844a().onResponse(m15849a);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                getActivity().finish();
            } finally {
                getActivity().finish();
            }
        }

        @Override // android.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setRetainInstance(true);
            String str = IntentHelperActivity.f10260c;
            Logger.m15757e(str, "RetainedIntentHelperFragment onCreate ");
            m15848a(Boolean.TRUE);
            int intExtra = getActivity().getIntent().getIntExtra("requestId", 0);
            this.f10270b = intExtra;
            if (intExtra == 0) {
                Logger.m15757e(str, "Error: ***");
                getActivity().finish();
            }
            UafRequestObject m15843a = UafRequestObject.m15843a(this.f10270b);
            try {
                Intent intent = new Intent();
                intent.putExtras(getActivity().getIntent());
                UafAppSdkIntent m15820a = UafAppSdkIntent.m15820a(getActivity());
                m15820a.m15822a();
                FidoStatus fidoStatus = m15820a.m15821a(this, intent).f10297a;
                FidoStatus fidoStatus2 = FidoStatus.NOT_INSTALLED;
                if (fidoStatus == fidoStatus2) {
                    synchronized (m15843a) {
                        Logger.m15757e(str, "Return not installed error");
                        FidoOut fidoOut = new FidoOut();
                        fidoOut.fidoResponse = null;
                        fidoOut.fidoStatus = fidoStatus2;
                        m15843a.m15844a().onResponse(fidoOut);
                    }
                    getActivity().finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
                FidoOut fidoOut2 = new FidoOut();
                fidoOut2.fidoResponse = null;
                fidoOut2.fidoStatus = FidoStatus.FAILED;
                m15843a.m15844a().onResponse(fidoOut2);
                ((IntentHelperActivity) getActivity()).m15852a(this.f10270b, IntentHelperActivity.f10261d, fidoOut2);
                getActivity().finish();
            }
        }

        @Override // android.app.Fragment
        public void onHiddenChanged(boolean z) {
            Tracker.onHiddenChanged(this, z);
            super.onHiddenChanged(z);
        }

        @Override // android.app.Fragment
        public void onPause() {
            Tracker.onPause(this);
            super.onPause();
        }

        @Override // android.app.Fragment
        public void onResume() {
            Tracker.onResume(this);
            super.onResume();
        }

        @Override // android.app.Fragment
        public void setUserVisibleHint(boolean z) {
            Tracker.setUserVisibleHint(this, z);
            super.setUserVisibleHint(z);
        }

        /* renamed from: a */
        private FidoOut m15849a(int i, Intent intent) {
            FidoOut fidoOut = new FidoOut();
            fidoOut.fidoStatus = FidoStatus.PROTOCOL_ERROR;
            if (intent == null) {
                Logger.m15757e(IntentHelperActivity.f10260c, "Malformed response: data is missing");
                return fidoOut;
            }
            Bundle extras = intent.getExtras();
            if (!extras.containsKey("componentName")) {
                Logger.m15757e(IntentHelperActivity.f10260c, "Malformed response: mandatory field IEN_COMPONENT_NAME is missing");
                return fidoOut;
            } else if (!extras.containsKey("errorCode")) {
                Logger.m15757e(IntentHelperActivity.f10260c, "Malformed response: mandatory field IEN_ERROR_CODE is missing");
                return fidoOut;
            } else {
                if (i == -1 || i == 0) {
                    if (!extras.containsKey("UAFIntentType")) {
                        Logger.m15757e(IntentHelperActivity.f10260c, "Malformed response: mandatory field IEN_UAF_INTENT_TYPE is missing");
                        return fidoOut;
                    }
                    String string = extras.getString("UAFIntentType");
                    try {
                        UAFIntentType valueOf = UAFIntentType.valueOf(string);
                        switch (valueOf.ordinal()) {
                            case 0:
                            case 2:
                            case 4:
                                break;
                            case 1:
                                if (extras.containsKey("discoveryData")) {
                                    fidoOut.discoveryData = extras.getString("discoveryData");
                                    break;
                                } else {
                                    Logger.m15757e(IntentHelperActivity.f10260c, "IEN_DISCOVERY_DATA is not set");
                                    break;
                                }
                            case 3:
                            case 5:
                                if (extras.containsKey("message")) {
                                    UAFMessage uAFMessage = new UAFMessage(intent.getExtras().getString("message"));
                                    fidoOut.fidoResponse = uAFMessage.f10272a;
                                    fidoOut.responseParams = uAFMessage.f10273b;
                                    break;
                                } else {
                                    Logger.m15757e(IntentHelperActivity.f10260c, "IEN_MESSAGE is not set");
                                    if (DeviceChecker.isDeviceInstalledSamsungpass(getActivity())) {
                                        fidoOut.fidoStatus = FidoStatus.NOT_INITFIDO;
                                        return fidoOut;
                                    }
                                }
                                break;
                            default:
                                String str = IntentHelperActivity.f10260c;
                                Logger.m15757e(str, "Unsupported IEN_UAF_INTENT_TYPE " + valueOf);
                                return fidoOut;
                        }
                    } catch (IllegalArgumentException unused) {
                        String str2 = IntentHelperActivity.f10260c;
                        Logger.m15757e(str2, "Malformed response: unknown IEN_UAF_INTENT_TYPE " + string);
                        return fidoOut;
                    }
                } else if (i != 1) {
                    String str3 = IntentHelperActivity.f10260c;
                    Logger.m15757e(str3, "Malformed response: unknown resultCode " + i);
                    return fidoOut;
                }
                fidoOut.fidoStatus = FidoTask.m15763a(extras.getShort("errorCode"));
                String str4 = IntentHelperActivity.f10260c;
                Logger.m15757e(str4, "fidoStatus:" + fidoOut.fidoStatus);
                return fidoOut;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0022, code lost:
        return;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m15852a(int r3, int r4, java.lang.Object r5) {
        /*
            r2 = this;
            com.gmrz.appsdk.commlib.UafRequestObject r0 = com.gmrz.appsdk.commlib.UafRequestObject.m15843a(r3)     // Catch: java.lang.Exception -> L33
            if (r0 == 0) goto L10
            if (r3 != r4) goto L10
            com.gmrz.appsdk.commlib.UafRequestObject$Station r3 = r0.m15840b()     // Catch: java.lang.Exception -> L33
            com.gmrz.appsdk.commlib.UafRequestObject$Station r1 = com.gmrz.appsdk.commlib.UafRequestObject.Station.INVALID     // Catch: java.lang.Exception -> L33
            if (r3 != r1) goto L20
        L10:
            com.gmrz.appsdk.commlib.UafRequestObject r0 = com.gmrz.appsdk.commlib.UafRequestObject.m15843a(r4)     // Catch: java.lang.Exception -> L33
            if (r0 == 0) goto L1f
            com.gmrz.appsdk.commlib.UafRequestObject$Station r3 = r0.m15840b()     // Catch: java.lang.Exception -> L33
            com.gmrz.appsdk.commlib.UafRequestObject$Station r4 = com.gmrz.appsdk.commlib.UafRequestObject.Station.VALID     // Catch: java.lang.Exception -> L33
            if (r3 != r4) goto L1f
            goto L20
        L1f:
            r0 = 0
        L20:
            if (r0 != 0) goto L23
            return
        L23:
            monitor-enter(r0)     // Catch: java.lang.Exception -> L33
            com.gmrz.appsdk.commlib.api.ICommunicationClientResponse r3 = r0.m15844a()     // Catch: java.lang.Throwable -> L30
            r3.onResponse(r5)     // Catch: java.lang.Throwable -> L30
            r0.m15839c()     // Catch: java.lang.Throwable -> L30
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L30
            goto L37
        L30:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L30
            throw r3     // Catch: java.lang.Exception -> L33
        L33:
            r3 = move-exception
            r3.printStackTrace()
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.appsdk.commlib.IntentHelperActivity.m15852a(int, int, java.lang.Object):void");
    }
}
