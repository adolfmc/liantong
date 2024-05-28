package com.p319ss.android.socialbase.appdownloader;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10088b;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10089h;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10090hj;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10093ko;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10096nk;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10097o;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10099u;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10100ww;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10101x;
import com.p319ss.android.socialbase.appdownloader.p337hj.C10119ox;
import com.p319ss.android.socialbase.appdownloader.p339ox.C10148mb;
import com.p319ss.android.socialbase.downloader.constants.DownloadConstants;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.p319ss.android.socialbase.downloader.depend.IInstallAppHandler;
import com.p319ss.android.socialbase.downloader.depend.IOpenInstallerListener;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.impls.RetryScheduler;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.DownloadTask;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* renamed from: com.ss.android.socialbase.appdownloader.hj */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10112hj {

    /* renamed from: ko */
    private static boolean f19490ko = false;

    /* renamed from: mb */
    private static final String f19491mb = "hj";

    /* renamed from: ox */
    private static volatile C10112hj f19492ox;

    /* renamed from: u */
    private static boolean f19493u;

    /* renamed from: b */
    private String f19494b;

    /* renamed from: e */
    private IOpenInstallerListener f19495e;

    /* renamed from: h */
    private DownloadReceiver f19496h;

    /* renamed from: hj */
    private String f19497hj;

    /* renamed from: io */
    private IInstallAppHandler f19498io;

    /* renamed from: jb */
    private InterfaceC10100ww f19499jb;

    /* renamed from: je */
    private InterfaceC10093ko f19500je;

    /* renamed from: lc */
    private InterfaceC10101x f19501lc;

    /* renamed from: lz */
    private InterfaceC10088b f19502lz;

    /* renamed from: nk */
    private InterfaceC10096nk f19503nk;

    /* renamed from: o */
    private InterfaceC10099u f19504o;

    /* renamed from: ww */
    private boolean f19505ww = false;

    /* renamed from: x */
    private InterfaceC10090hj f19506x;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public static /* synthetic */ InterfaceC10100ww m6804mb(C10112hj c10112hj) {
        return c10112hj.f19499jb;
    }

    /* renamed from: mb */
    public InterfaceC10088b m6814mb() {
        return this.f19502lz;
    }

    /* renamed from: ox */
    public InterfaceC10090hj m6792ox() {
        return this.f19506x;
    }

    /* renamed from: b */
    public InterfaceC10100ww m6826b() {
        return this.f19499jb;
    }

    /* renamed from: mb */
    public void m6797mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f19497hj = str;
    }

    /* renamed from: hj */
    public String m6822hj() {
        return this.f19497hj;
    }

    /* renamed from: h */
    public InterfaceC10099u m6823h() {
        return this.f19504o;
    }

    /* renamed from: u */
    public boolean m6788u() {
        return DownloadSetting.getGlobalSettings().optInt("package_flag_config", 1) == 1;
    }

    /* renamed from: ko */
    public InterfaceC10101x m6818ko() {
        return this.f19501lc;
    }

    /* renamed from: mb */
    public void m6805mb(InterfaceC10101x interfaceC10101x) {
        this.f19501lc = interfaceC10101x;
    }

    /* renamed from: ww */
    public File m6787ww() {
        return Downloader.getInstance(DownloadComponentManager.getAppContext()).getGlobalSaveDir();
    }

    /* renamed from: lz */
    public String m6815lz() {
        return this.f19494b;
    }

    private C10112hj() {
    }

    /* renamed from: x */
    public static C10112hj m6786x() {
        if (f19492ox == null) {
            synchronized (C10112hj.class) {
                if (f19492ox == null) {
                    f19492ox = new C10112hj();
                }
            }
        }
        return f19492ox;
    }

    @Deprecated
    /* renamed from: mb */
    public void m6809mb(Context context, String str, InterfaceC10088b interfaceC10088b, InterfaceC10090hj interfaceC10090hj, InterfaceC10100ww interfaceC10100ww) {
        if (interfaceC10088b != null) {
            this.f19502lz = interfaceC10088b;
        }
        if (interfaceC10090hj != null) {
            this.f19506x = interfaceC10090hj;
        }
        if (interfaceC10100ww != null) {
            this.f19499jb = interfaceC10100ww;
        }
        m6825b(context);
    }

    /* renamed from: b */
    private void m6825b(Context context) {
        if (context == null || f19493u) {
            return;
        }
        DownloadConstants.setMimeApk("application/vnd.android.package-archive");
        DownloadComponentManager.setAppContext(context);
        DownloadComponentManager.setDownloadLaunchHandler(new C10119ox());
        m6824e();
        m6817l();
        f19493u = true;
    }

    /* renamed from: ox */
    public void m6789ox(String str) {
        Downloader.getInstance(DownloadComponentManager.getAppContext()).setDefaultSavePath(str);
    }

    /* renamed from: e */
    private void m6824e() {
        if (f19490ko) {
            return;
        }
        if (this.f19496h == null) {
            this.f19496h = new DownloadReceiver();
        }
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
            intentFilter.addAction("android.ss.intent.action.DOWNLOAD_COMPLETE");
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter2.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter2.addDataScheme("package");
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter3.addDataScheme("file");
            DownloadComponentManager.getAppContext().registerReceiver(this.f19496h, intentFilter);
            DownloadComponentManager.getAppContext().registerReceiver(this.f19496h, intentFilter2);
            DownloadComponentManager.getAppContext().registerReceiver(this.f19496h, intentFilter3);
            f19490ko = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.appdownloader.hj$1 */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public class C101131 implements RetryScheduler.RetryScheduleHandler {
        C101131() {
        }

        @Override // com.p319ss.android.socialbase.downloader.impls.RetryScheduler.RetryScheduleHandler
        public void scheduleRetry(DownloadInfo downloadInfo, long j, boolean z, int i) {
            RetryJobSchedulerService.m6934mb(downloadInfo, j, z, i);
        }

        @Override // com.p319ss.android.socialbase.downloader.impls.RetryScheduler.RetryScheduleHandler
        public void cancelRetry(int i) {
            RetryJobSchedulerService.m6935mb(i);
        }
    }

    /* renamed from: l */
    private void m6817l() {
        if (Build.VERSION.SDK_INT >= 21) {
            RetryScheduler.setRetryScheduleHandler(new C101131());
        }
    }

    /* renamed from: mb */
    public static boolean m6812mb(Context context, int i) {
        return C10085b.m6916mb(context, i, true) == 1;
    }

    /* renamed from: mb */
    public void m6811mb(Context context, int i, int i2) {
        try {
            switch (i2) {
                case -4:
                case -1:
                    Downloader.getInstance(context).restart(i);
                    break;
                case -3:
                    C10085b.m6916mb(context, i, true);
                    break;
                case -2:
                    Downloader.getInstance(context).resume(i);
                    break;
                case 0:
                case 6:
                default:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                    Downloader.getInstance(context).pause(i);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0354  */
    /* renamed from: mb */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m6802mb(com.p319ss.android.socialbase.appdownloader.C10149u r23) {
        /*
            Method dump skipped, instructions count: 971
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.C10112hj.m6802mb(com.ss.android.socialbase.appdownloader.u):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.socialbase.appdownloader.hj$2 */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public class C101142 implements IDownloadDepend {
        C101142() {
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadDepend
        public void monitorLogSend(DownloadInfo downloadInfo, BaseException baseException, int i) {
            if (C10112hj.this.f19499jb != null) {
                C10112hj.this.f19499jb.mo6869mb(downloadInfo, baseException, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.socialbase.appdownloader.hj$3 */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public class C101153 implements InterfaceC10097o {

        /* renamed from: b */
        final /* synthetic */ int f19509b;

        /* renamed from: hj */
        final /* synthetic */ boolean f19511hj;

        /* renamed from: mb */
        final /* synthetic */ String f19512mb;

        /* renamed from: ox */
        final /* synthetic */ DownloadTask f19513ox;

        C101153(String str, DownloadTask downloadTask, int i, boolean z) {
            this.f19512mb = str;
            this.f19513ox = downloadTask;
            this.f19509b = i;
            this.f19511hj = z;
        }

        @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10097o
        /* renamed from: mb */
        public void mo6785mb() {
            String str = C10112hj.f19491mb;
            Logger.m6475d(str, "notification permission granted, start download :" + this.f19512mb);
            C10112hj.this.m6798mb(this.f19513ox, this.f19509b, this.f19511hj);
        }

        @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10097o
        /* renamed from: ox */
        public void mo6784ox() {
            String str = C10112hj.f19491mb;
            Logger.m6475d(str, "notification permission denied, start download :" + this.f19512mb);
            C10112hj.this.m6798mb(this.f19513ox, this.f19509b, this.f19511hj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mb */
    public void m6798mb(DownloadTask downloadTask, int i, boolean z) {
        if (downloadTask == null) {
            return;
        }
        downloadTask.download();
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo != null) {
            downloadInfo.setAntiHijackErrorCode(i);
        }
        if (downloadInfo == null || !z) {
            return;
        }
        downloadInfo.setSavePathRedirected(z);
    }

    /* renamed from: mb */
    private List<HttpHeader> m6795mb(List<HttpHeader> list) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        if (list != null && list.size() > 0) {
            for (HttpHeader httpHeader : list) {
                if (httpHeader != null && !TextUtils.isEmpty(httpHeader.getName()) && !TextUtils.isEmpty(httpHeader.getValue())) {
                    if (httpHeader.getName().equals("User-Agent")) {
                        z = true;
                    }
                    arrayList.add(new HttpHeader(httpHeader.getName(), httpHeader.getValue()));
                }
            }
        }
        if (!z) {
            arrayList.add(new HttpHeader("User-Agent", C10148mb.f19563mb));
        }
        return arrayList;
    }

    /* renamed from: mb */
    public String m6796mb(String str, String str2) {
        return (TextUtils.isEmpty(str) || !str.endsWith(".apk") || C10085b.m6930b(str2)) ? str2 : "application/vnd.android.package-archive";
    }

    /* renamed from: mb */
    private IDownloadNotificationEventListener m6807mb(final InterfaceC10089h interfaceC10089h) {
        if (interfaceC10089h == null) {
            return null;
        }
        return new IDownloadNotificationEventListener() { // from class: com.ss.android.socialbase.appdownloader.hj.4
            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public void onNotificationEvent(int i, DownloadInfo downloadInfo, String str, String str2) {
                if (i != 1 && i != 3) {
                    switch (i) {
                        case 5:
                        case 6:
                        case 7:
                            break;
                        case 8:
                            interfaceC10089h.m6888mb(i, downloadInfo.getPackageName(), str, str2);
                            return;
                        case 9:
                            interfaceC10089h.m6887mb(DownloadComponentManager.getAppContext(), str);
                            return;
                        case 10:
                            interfaceC10089h.m6886mb(downloadInfo);
                            return;
                        default:
                            return;
                    }
                }
                interfaceC10089h.m6889mb(i, str, downloadInfo.getStatus(), downloadInfo.getDownloadTime());
            }

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public boolean interceptAfterNotificationSuccess(boolean z) {
                return interfaceC10089h.m6885mb(z);
            }

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public String getNotifyProcessName() {
                return interfaceC10089h.m6890mb();
            }
        };
    }

    /* renamed from: mb */
    public DownloadInfo m6810mb(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        try {
            DownloadInfo m6808mb = m6808mb(context, str, m6787ww());
            if (m6808mb == null) {
                m6808mb = m6808mb(context, str, context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
            }
            if (m6808mb == null) {
                m6808mb = m6808mb(context, str, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
            }
            if (m6808mb == null) {
                m6808mb = m6808mb(context, str, context.getFilesDir());
            }
            return (m6808mb == null && DownloadSetting.obtainGlobal().optBugFix("get_download_info_by_list")) ? m6790ox(context, str) : m6808mb;
        } catch (Throwable th) {
            Logger.m6475d(f19491mb, String.format("getAppDownloadInfo error:%s", th.getMessage()));
            return null;
        }
    }

    /* renamed from: mb */
    private DownloadInfo m6808mb(Context context, String str, File file) {
        if (context == null || TextUtils.isEmpty(str) || file == null) {
            return null;
        }
        return Downloader.getInstance(context).getDownloadInfo(str, file.getAbsolutePath());
    }

    /* renamed from: ox */
    private DownloadInfo m6790ox(Context context, String str) {
        List<DownloadInfo> downloadInfoList = Downloader.getInstance(context).getDownloadInfoList(str);
        if (downloadInfoList != null) {
            for (DownloadInfo downloadInfo : downloadInfoList) {
                if (downloadInfo != null && downloadInfo.isSavePathRedirected()) {
                    return downloadInfo;
                }
            }
            return null;
        }
        return null;
    }

    /* renamed from: mb */
    public List<DownloadInfo> m6813mb(Context context) {
        return Downloader.getInstance(context).getUnCompletedDownloadInfosWithMimeType("application/vnd.android.package-archive");
    }

    /* renamed from: ox */
    public List<DownloadInfo> m6791ox(Context context) {
        return Downloader.getInstance(context).getDownloadingDownloadInfosWithMimeType("application/vnd.android.package-archive");
    }

    /* renamed from: jb */
    public InterfaceC10096nk m6820jb() {
        return this.f19503nk;
    }

    /* renamed from: je */
    public InterfaceC10093ko m6819je() {
        return this.f19500je;
    }

    /* renamed from: mb */
    public void m6806mb(InterfaceC10093ko interfaceC10093ko) {
        this.f19500je = interfaceC10093ko;
    }

    /* renamed from: nk */
    public IReserveWifiStatusListener m6794nk() {
        return Downloader.getInstance(DownloadComponentManager.getAppContext()).getReserveWifiStatusListener();
    }

    /* renamed from: mb */
    public void m6799mb(IReserveWifiStatusListener iReserveWifiStatusListener) {
        Downloader.getInstance(DownloadComponentManager.getAppContext()).setReserveWifiStatusListener(iReserveWifiStatusListener);
    }

    /* renamed from: mb */
    public void m6801mb(IInstallAppHandler iInstallAppHandler) {
        this.f19498io = iInstallAppHandler;
    }

    /* renamed from: o */
    public IInstallAppHandler m6793o() {
        return this.f19498io;
    }

    /* renamed from: mb */
    public void m6800mb(IOpenInstallerListener iOpenInstallerListener) {
        this.f19495e = iOpenInstallerListener;
    }

    /* renamed from: lc */
    public IOpenInstallerListener m6816lc() {
        return this.f19495e;
    }
}
