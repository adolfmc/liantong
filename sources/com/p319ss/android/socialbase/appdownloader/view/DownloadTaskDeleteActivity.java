package com.p319ss.android.socialbase.appdownloader.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.C10125lz;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10088b;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10091jb;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je;
import com.p319ss.android.socialbase.appdownloader.p337hj.C10117mb;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;

@NBSInstrumented
/* renamed from: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadTaskDeleteActivity extends Activity {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: mb */
    private InterfaceC10091jb f19660mb;

    /* renamed from: ox */
    private Intent f19661ox;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 397);
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

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* renamed from: mb */
    private void m6507mb() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.f19661ox = getIntent();
        m6504ox();
        InterfaceC10091jb interfaceC10091jb = this.f19660mb;
        if (interfaceC10091jb != null && !interfaceC10091jb.mo6776ox()) {
            this.f19660mb.mo6777mb();
        } else if (this.f19660mb == null) {
            finish();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    /* renamed from: ox */
    private void m6504ox() {
        Intent intent;
        if (this.f19660mb != null || (intent = this.f19661ox) == null) {
            return;
        }
        try {
            final boolean z = false;
            final int intExtra = intent.getIntExtra("extra_click_download_ids", 0);
            final DownloadInfo downloadInfo = Downloader.getInstance(getApplicationContext()).getDownloadInfo(intExtra);
            if (downloadInfo == null) {
                return;
            }
            String title = downloadInfo.getTitle();
            if (TextUtils.isEmpty(title)) {
                Log.w("DeleteActivity", "Missing appName; skipping handle");
                return;
            }
            String format = String.format(getString(C10125lz.m6745mb(this, "tt_appdownloader_notification_download_delete")), title);
            InterfaceC10088b m6814mb = C10112hj.m6786x().m6814mb();
            InterfaceC10092je mo6873mb = m6814mb != null ? m6814mb.mo6873mb(this) : null;
            if (mo6873mb == null) {
                mo6873mb = new C10117mb(this);
            }
            if (mo6873mb != null) {
                int m6745mb = C10125lz.m6745mb(this, "tt_appdownloader_tip");
                int m6745mb2 = C10125lz.m6745mb(this, "tt_appdownloader_label_ok");
                int m6745mb3 = C10125lz.m6745mb(this, "tt_appdownloader_label_cancel");
                if (DownloadSetting.obtain(downloadInfo.getId()).optInt("cancel_with_net_opt", 0) == 1 && DownloadUtils.isNoWifiAndInNet() && downloadInfo.getCurBytes() != downloadInfo.getTotalBytes()) {
                    z = true;
                }
                if (z) {
                    m6745mb2 = C10125lz.m6745mb(this, "tt_appdownloader_label_reserve_wifi");
                    m6745mb3 = C10125lz.m6745mb(this, "tt_appdownloader_label_cancel_directly");
                    format = getResources().getString(C10125lz.m6745mb(this, "tt_appdownloader_resume_in_wifi"));
                }
                mo6873mb.mo6782mb(m6745mb).mo6779mb(format).mo6781mb(m6745mb2, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Tracker.onClick(dialogInterface, i);
                        if (!z) {
                            DownloadTaskDeleteActivity.this.m6505mb(downloadInfo, intExtra);
                        } else {
                            downloadInfo.setOnlyWifi(true);
                            Downloader.getInstance(DownloadTaskDeleteActivity.this).pause(downloadInfo.getId());
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.3.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Downloader.getInstance(DownloadTaskDeleteActivity.this).resume(downloadInfo.getId());
                                }
                            }, 100L);
                        }
                        DownloadTaskDeleteActivity.this.finish();
                    }
                }).mo6778ox(m6745mb3, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Tracker.onClick(dialogInterface, i);
                        if (z) {
                            DownloadTaskDeleteActivity.this.m6505mb(downloadInfo, intExtra);
                        }
                        DownloadTaskDeleteActivity.this.finish();
                    }
                }).mo6780mb(new DialogInterface.OnCancelListener() { // from class: com.ss.android.socialbase.appdownloader.view.DownloadTaskDeleteActivity.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        DownloadTaskDeleteActivity.this.finish();
                    }
                });
                this.f19660mb = mo6873mb.mo6783mb();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public void m6505mb(DownloadInfo downloadInfo, int i) {
        InterfaceC10090hj m6792ox = C10112hj.m6786x().m6792ox();
        if (m6792ox != null) {
            m6792ox.mo6879mb(downloadInfo);
        }
        IDownloadNotificationEventListener downloadNotificationEventListener = Downloader.getInstance(DownloadComponentManager.getAppContext()).getDownloadNotificationEventListener(i);
        if (downloadNotificationEventListener != null) {
            downloadNotificationEventListener.onNotificationEvent(10, downloadInfo, "", "");
        }
        if (DownloadComponentManager.getAppContext() != null) {
            Downloader.getInstance(DownloadComponentManager.getAppContext()).cancel(i);
        }
    }
}
