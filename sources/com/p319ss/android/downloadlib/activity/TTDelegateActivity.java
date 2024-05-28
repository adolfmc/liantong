package com.p319ss.android.downloadlib.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p319ss.android.download.api.config.InterfaceC9795gm;
import com.p319ss.android.download.api.config.InterfaceC9800je;
import com.p319ss.android.download.api.model.DownloadAlertDialogInfo;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import com.p319ss.android.downloadlib.C10071ww;
import com.p319ss.android.downloadlib.addownload.C9896je;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.compliance.DialogC9866mb;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.p325b.C9847mb;
import com.p319ss.android.downloadlib.addownload.p325b.C9849ox;
import com.p319ss.android.downloadlib.addownload.p327mb.DialogC9907hj;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.guide.install.InterfaceC9977mb;
import com.p319ss.android.downloadlib.p334ox.C10032mb;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.C10053ko;
import com.p319ss.android.downloadlib.utils.C10059lz;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.activity.TTDelegateActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TTDelegateActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {

    /* renamed from: hj */
    private static InterfaceC9977mb f18899hj;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private C9837ox f18900b;

    /* renamed from: mb */
    protected Intent f18901mb = null;

    /* renamed from: ox */
    private boolean f18902ox;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.ss.android.downloadlib.activity.TTDelegateActivity$IntentType */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface IntentType {
        public static final int AD_LP_APPINFO_DIALOG = 10;
        public static final int APK_INSTALL = 9;
        public static final int APK_SIZE_RETAIN = 8;
        public static final int DOWNLOAD_PERCENT_RETAIN = 7;
        public static final int INSTALL_GUIDE = 6;
        public static final int INTENT_CLEAN_DISK_SPACE = 3;
        public static final int OPEN_APP_DIALOG = 4;
        public static final int OPEN_URL = 2;
        public static final int OPEN_V1_MARKET = 12;
        public static final int PACKAGE_NAME = 11;
        public static final int REQUEST_PERMISSION = 1;
        public static final int REVERSE_WIFI_DIALOG = 5;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 395);
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

    /* renamed from: mb */
    public static void m7715mb(String str, String[] strArr) {
        Intent intent = new Intent(C9940x.getContext(), TTDelegateActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("type", 1);
        intent.putExtra("permission_id_key", str);
        intent.putExtra("permission_content_key", strArr);
        if (C9940x.getContext() != null) {
            C9940x.getContext().startActivity(intent);
        }
    }

    /* renamed from: mb */
    public static void m7716mb(String str, InterfaceC9836mb interfaceC9836mb) {
        Intent m7726b = m7726b(interfaceC9836mb);
        m7726b.addFlags(268435456);
        m7726b.putExtra("type", 2);
        m7726b.putExtra("open_url", str);
        if (C9940x.getContext() != null) {
            C9940x.getContext().startActivity(m7726b);
        }
    }

    /* renamed from: ox */
    public static void m7709ox(String str, InterfaceC9836mb interfaceC9836mb) {
        Intent m7726b = m7726b(interfaceC9836mb);
        m7726b.addFlags(268435456);
        m7726b.putExtra("type", 11);
        m7726b.putExtra("package_name", str);
        if (C9940x.getContext() != null) {
            C9940x.getContext().startActivity(m7726b);
        }
    }

    /* renamed from: mb */
    public static void m7722mb(InterfaceC9836mb interfaceC9836mb) {
        Intent m7726b = m7726b(interfaceC9836mb);
        m7726b.addFlags(268435456);
        m7726b.putExtra("type", 4);
        m7726b.putExtra("model_id", interfaceC9836mb.mo7474ox());
        if (C9940x.getContext() != null) {
            C9940x.getContext().startActivity(m7726b);
        }
    }

    /* renamed from: mb */
    public static void m7720mb(InterfaceC9836mb interfaceC9836mb, InterfaceC9977mb interfaceC9977mb) {
        Intent m7726b = m7726b(interfaceC9836mb);
        m7726b.addFlags(268435456);
        m7726b.putExtra("type", 9);
        f18899hj = interfaceC9977mb;
        if (C9940x.getContext() != null) {
            C9940x.getContext().startActivity(m7726b);
        }
    }

    /* renamed from: mb */
    public static void m7723mb(long j) {
        Intent intent = new Intent(C9940x.getContext(), TTDelegateActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("type", 10);
        intent.putExtra("app_info_id", j);
        if (C9940x.getContext() != null) {
            C9940x.getContext().startActivity(intent);
        }
    }

    /* renamed from: mb */
    public static void m7717mb(String str, long j, String str2, @NonNull JSONObject jSONObject) {
        Intent intent = new Intent(C9940x.getContext(), TTDelegateActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("type", 12);
        intent.putExtra("package_name", str);
        intent.putExtra("model_id", j);
        intent.putExtra("param", str2);
        intent.putExtra("ext_json", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        if (C9940x.getContext() != null) {
            C9940x.getContext().startActivity(intent);
        }
    }

    /* renamed from: b */
    private static Intent m7726b(@NonNull InterfaceC9836mb interfaceC9836mb) {
        return new Intent(C9940x.getContext(), TTDelegateActivity.class);
    }

    /* renamed from: ox */
    public static void m7712ox(@NonNull InterfaceC9836mb interfaceC9836mb) {
        m7721mb(interfaceC9836mb, 5, "", "", "");
    }

    /* renamed from: mb */
    public static void m7719mb(@NonNull InterfaceC9836mb interfaceC9836mb, String str, String str2, String str3) {
        m7721mb(interfaceC9836mb, 8, str, str2, str3);
    }

    /* renamed from: ox */
    public static void m7711ox(@NonNull InterfaceC9836mb interfaceC9836mb, String str, String str2, String str3) {
        m7721mb(interfaceC9836mb, 7, str, str2, str3);
    }

    /* renamed from: mb */
    private static void m7721mb(@NonNull InterfaceC9836mb interfaceC9836mb, int i, String str, String str2, String str3) {
        Intent m7726b = m7726b(interfaceC9836mb);
        m7726b.addFlags(268435456);
        m7726b.putExtra("type", i);
        if (!TextUtils.isEmpty(str2)) {
            m7726b.putExtra("positive_button_text", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            m7726b.putExtra("negative_button_text", str3);
        }
        if (!TextUtils.isEmpty(str)) {
            m7726b.putExtra("message_text", str);
        }
        m7726b.putExtra("model_id", interfaceC9836mb.mo7474ox());
        if (C9940x.getContext() != null) {
            C9940x.getContext().startActivity(m7726b);
        }
    }

    /* renamed from: ox */
    private void m7714ox() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.f18901mb = intent;
        C9940x.m7347ox(this);
        mo7724mb();
    }

    @Override // android.app.Activity, android.support.p083v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        C9940x.m7372h().mo7902mb(this, i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        DownloadInfo m6947ox;
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        if (!this.f18902ox || this.f18900b == null || (m6947ox = C10071ww.m6960mb((Context) null).m6947ox(this.f18900b.mo7478mb())) == null || m6947ox.getCurBytes() < m6947ox.getTotalBytes() || isFinishing()) {
            return;
        }
        finish();
    }

    /* renamed from: mb */
    protected void mo7724mb() {
        Intent intent = this.f18901mb;
        if (intent == null) {
            return;
        }
        switch (intent.getIntExtra("type", 0)) {
            case 1:
                m7708ox(this.f18901mb.getStringExtra("permission_id_key"), this.f18901mb.getStringArrayExtra("permission_content_key"));
                break;
            case 2:
                m7718mb(this.f18901mb.getStringExtra("open_url"));
                break;
            case 3:
            case 6:
            default:
                C10085b.m6920mb((Activity) this);
                break;
            case 4:
                m7727b(this.f18901mb.getLongExtra("model_id", 0L));
                break;
            case 5:
                m7713ox(this.f18901mb.getLongExtra("model_id", 0L));
                break;
            case 7:
            case 8:
                m7728b();
                break;
            case 9:
                InterfaceC9977mb interfaceC9977mb = f18899hj;
                if (interfaceC9977mb != null) {
                    interfaceC9977mb.mo7240mb();
                }
                C10085b.m6920mb((Activity) this);
                break;
            case 10:
                m7725hj(this.f18901mb.getLongExtra("app_info_id", 0L));
                break;
            case 11:
                m7710ox(this.f18901mb.getStringExtra("package_name"));
                break;
            case 12:
                C10053ko.m7019mb(this, this.f18901mb.getStringExtra("package_name"), this.f18901mb.getLongExtra("model_id", 0L), this.f18901mb.getStringExtra("param"), this.f18901mb.getStringExtra("ext_json"));
                C10085b.m6920mb((Activity) this);
                break;
        }
        this.f18901mb = null;
    }

    /* renamed from: b */
    private void m7728b() {
        long longExtra = this.f18901mb.getLongExtra("model_id", 0L);
        String stringExtra = this.f18901mb.getStringExtra("message_text");
        String stringExtra2 = this.f18901mb.getStringExtra("positive_button_text");
        String stringExtra3 = this.f18901mb.getStringExtra("negative_button_text");
        int intExtra = this.f18901mb.getIntExtra("type", 0);
        C9837ox m7452hj = C9923u.m7451mb().m7452hj(longExtra);
        String str = "";
        DialogC9907hj.C9910mb m7520b = new DialogC9907hj.C9910mb(this).m7516mb(false).m7517mb(stringExtra).m7515ox(stringExtra2).m7520b(stringExtra3);
        if (intExtra == 7) {
            if (C9849ox.m7694mb() == null) {
                return;
            }
            m7520b.m7518mb(C9849ox.m7694mb());
            m7520b.m7519mb().show();
            str = "download_percent";
        } else if (intExtra == 8) {
            if (C9847mb.m7699mb() == null) {
                return;
            }
            m7520b.m7518mb(C9847mb.m7699mb());
            m7520b.m7519mb().show();
            str = "apk_size";
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f18902ox = true;
        this.f18900b = m7452hj;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("pause_optimise_type", str);
            jSONObject.putOpt("pause_optimise_action", "show_dialog");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AdEventHandler.m7315mb().m7298mb("pause_optimise", jSONObject, m7452hj);
    }

    /* renamed from: ox */
    private void m7713ox(long j) {
        if (C9896je.m7573mb() == null) {
            return;
        }
        C9837ox m7452hj = C9923u.m7451mb().m7452hj(j);
        if (m7452hj != null) {
            DownloadInfo downloadInfo = Downloader.getInstance(C9940x.getContext()).getDownloadInfo(m7452hj.mo7479m());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("time_after_click", Long.valueOf(System.currentTimeMillis() - m7452hj.m7751sa()));
                jSONObject.putOpt("click_download_size", Long.valueOf(m7452hj.m7750sr()));
                if (downloadInfo != null) {
                    jSONObject.putOpt("download_length", Long.valueOf(downloadInfo.getCurBytes()));
                    jSONObject.putOpt("download_percent", Long.valueOf(downloadInfo.getCurBytes() / downloadInfo.getTotalBytes()));
                    jSONObject.putOpt("download_apk_size", Long.valueOf(downloadInfo.getTotalBytes()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            AdEventHandler.m7315mb().m7293ox("pause_reserve_wifi_dialog_show", jSONObject, m7452hj);
        }
        new DialogC9907hj.C9910mb(this).m7516mb(false).m7518mb(C9896je.m7573mb()).m7519mb().show();
        this.f18902ox = true;
        this.f18900b = m7452hj;
    }

    /* renamed from: ox */
    private void m7708ox(final String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length <= 0) {
            C10085b.m6920mb((Activity) this);
            return;
        }
        InterfaceC9795gm interfaceC9795gm = new InterfaceC9795gm() { // from class: com.ss.android.downloadlib.activity.TTDelegateActivity.1

            /* renamed from: b */
            private WeakReference<Activity> f18903b;

            {
                this.f18903b = new WeakReference<>(TTDelegateActivity.this);
            }

            @Override // com.p319ss.android.download.api.config.InterfaceC9795gm
            /* renamed from: mb */
            public void mo7541mb() {
                C10059lz.m6996mb(str);
                C10085b.m6920mb(this.f18903b.get());
            }

            @Override // com.p319ss.android.download.api.config.InterfaceC9795gm
            /* renamed from: mb */
            public void mo7540mb(String str2) {
                C10059lz.m6994mb(str, str2);
                C10085b.m6920mb(this.f18903b.get());
            }
        };
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                C9940x.m7372h().mo7901mb(this, strArr, interfaceC9795gm);
                return;
            } catch (Exception e) {
                C9940x.m7363m().mo7282mb(e, "requestPermission");
                interfaceC9795gm.mo7541mb();
                return;
            }
        }
        interfaceC9795gm.mo7541mb();
    }

    /* renamed from: mb */
    private void m7718mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            try {
                Uri parse = Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(parse);
                intent.putExtra("open_url", str);
                intent.addFlags(268435456);
                if (DownloadSetting.obtainGlobal().optBugFix("fix_app_link_flag")) {
                    intent.addFlags(67108864);
                }
                intent.putExtra("start_only_for_android", true);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            C10085b.m6920mb((Activity) this);
        }
    }

    /* renamed from: ox */
    private void m7710ox(String str) {
        Intent m7028u = C10050jb.m7028u(this, str);
        if (m7028u == null) {
            return;
        }
        try {
            try {
                m7028u.addFlags(268435456);
                m7028u.putExtra("start_only_for_android", true);
                startActivity(m7028u);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            C10085b.m6920mb((Activity) this);
        }
    }

    /* renamed from: b */
    private void m7727b(long j) {
        final C9837ox m7452hj = C9923u.m7451mb().m7452hj(j);
        if (m7452hj == null) {
            C9971b.m7285mb().m7284mb("showOpenAppDialogInner nativeModel null");
            C10085b.m6920mb((Activity) this);
            return;
        }
        InterfaceC9800je m7377b = C9940x.m7377b();
        DownloadAlertDialogInfo.C9826mb m7888mb = new DownloadAlertDialogInfo.C9826mb(this).m7888mb("已安装完成");
        Object[] objArr = new Object[1];
        objArr[0] = TextUtils.isEmpty(m7452hj.m7755pa()) ? "刚刚下载的应用" : m7452hj.m7755pa();
        m7377b.mo7903ox(m7888mb.m7885ox(String.format("%1$s已安装完成，是否立即打开？", objArr)).m7898b("打开").m7895hj("取消").m7887mb(false).m7891mb(C10050jb.m7063b(this, m7452hj.mo7489h())).m7889mb(new DownloadAlertDialogInfo.InterfaceC9827ox() { // from class: com.ss.android.downloadlib.activity.TTDelegateActivity.2
            @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
            /* renamed from: mb */
            public void mo7140mb(DialogInterface dialogInterface) {
                C10032mb.m7118ox(m7452hj);
                dialogInterface.dismiss();
                C10085b.m6920mb((Activity) TTDelegateActivity.this);
            }

            @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
            /* renamed from: ox */
            public void mo7139ox(DialogInterface dialogInterface) {
                AdEventHandler.m7315mb().m7294ox("market_openapp_cancel", m7452hj);
                dialogInterface.dismiss();
                C10085b.m6920mb((Activity) TTDelegateActivity.this);
            }

            @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
            /* renamed from: b */
            public void mo7141b(DialogInterface dialogInterface) {
                C10085b.m6920mb((Activity) TTDelegateActivity.this);
            }
        }).m7892mb(2).m7893mb());
        AdEventHandler.m7315mb().m7294ox("market_openapp_window_show", m7452hj);
    }

    /* renamed from: hj */
    private void m7725hj(long j) {
        new DialogC9866mb(this, j).show();
    }
}
