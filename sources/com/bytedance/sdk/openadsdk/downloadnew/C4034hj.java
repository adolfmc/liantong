package com.bytedance.sdk.openadsdk.downloadnew;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTDownloadEventLogger;
import com.bytedance.sdk.openadsdk.api.C3972mb;
import com.bytedance.sdk.openadsdk.downloadnew.C4031b;
import com.bytedance.sdk.openadsdk.downloadnew.core.DialogBuilder;
import com.bytedance.sdk.openadsdk.downloadnew.core.ExitInstallListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadAdapter;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTDownloadVisitor;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback;
import com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadEventModel;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p319ss.android.download.api.InterfaceC9819mb;
import com.p319ss.android.download.api.config.IDownloadButtonClickListener;
import com.p319ss.android.download.api.config.InterfaceC9793e;
import com.p319ss.android.download.api.config.InterfaceC9795gm;
import com.p319ss.android.download.api.config.InterfaceC9800je;
import com.p319ss.android.download.api.config.InterfaceC9801ko;
import com.p319ss.android.download.api.config.InterfaceC9802l;
import com.p319ss.android.download.api.config.InterfaceC9804lz;
import com.p319ss.android.download.api.config.InterfaceC9809ox;
import com.p319ss.android.download.api.config.InterfaceC9811ww;
import com.p319ss.android.download.api.config.InterfaceC9812x;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.download.p321mb.InterfaceC9817mb;
import com.p319ss.android.download.api.model.C9830mb;
import com.p319ss.android.download.api.model.C9832ox;
import com.p319ss.android.download.api.model.DownloadAlertDialogInfo;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.C10071ww;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.p327mb.C9911mb;
import com.p319ss.android.downloadlib.p334ox.C10044x;
import com.p319ss.android.downloadlib.utils.C10061mb;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.downloader.depend.IDownloadSettings;
import com.p319ss.android.socialbase.downloader.depend.IInstallAppHandler;
import com.p319ss.android.socialbase.downloader.downloader.DownloaderBuilder;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpService;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.sdk.openadsdk.downloadnew.hj */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4034hj {

    /* renamed from: b */
    public static ITTDownloadVisitor f9636b;

    /* renamed from: h */
    private static Context f9637h;

    /* renamed from: ko */
    private static final InterfaceC9817mb f9639ko;

    /* renamed from: mb */
    public static volatile String f9640mb;

    /* renamed from: u */
    private static Map<Integer, ITTDownloadAdapter.OnEventLogHandler> f9642u;

    /* renamed from: hj */
    private static final AtomicBoolean f9638hj = new AtomicBoolean(false);

    /* renamed from: ox */
    public static boolean f9641ox = true;

    /* renamed from: u */
    private static boolean m16387u() {
        return false;
    }

    /* renamed from: hj */
    static /* synthetic */ ITTDownloadVisitor m16405hj() {
        return m16406h();
    }

    static {
        try {
            f9640mb = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        } catch (Throwable unused) {
        }
        f9639ko = new InterfaceC9817mb() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.6
            @Override // com.p319ss.android.download.api.download.p321mb.InterfaceC9817mb
            /* renamed from: mb */
            public void mo7910mb(DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig) {
                C3972mb.m16544ox("TTDownloadVisitor", "completeListener: onDownloadStart");
            }

            @Override // com.p319ss.android.download.api.download.p321mb.InterfaceC9817mb
            /* renamed from: mb */
            public void mo7907mb(DownloadInfo downloadInfo, String str) {
                C3972mb.m16544ox("TTDownloadVisitor", "completeListener: onDownloadFinished");
            }

            @Override // com.p319ss.android.download.api.download.p321mb.InterfaceC9817mb
            /* renamed from: ox */
            public void mo7906ox(DownloadInfo downloadInfo, String str) {
                C3972mb.m16544ox("TTDownloadVisitor", "completeListener: onInstalled");
                C4034hj.m16407b(str);
            }

            @Override // com.p319ss.android.download.api.download.p321mb.InterfaceC9817mb
            /* renamed from: mb */
            public void mo7908mb(DownloadInfo downloadInfo, BaseException baseException, String str) {
                C3972mb.m16544ox("TTDownloadVisitor", "completeListener: onDownloadFailed");
            }

            @Override // com.p319ss.android.download.api.download.p321mb.InterfaceC9817mb
            /* renamed from: mb */
            public void mo7909mb(DownloadInfo downloadInfo) {
                C3972mb.m16544ox("TTDownloadVisitor", "completeListener: onCanceled");
            }
        };
    }

    /* renamed from: h */
    private static ITTDownloadVisitor m16406h() {
        ITTDownloadVisitor iTTDownloadVisitor = f9636b;
        if (iTTDownloadVisitor == null) {
            TTAdManager adManager = TTAdSdk.getAdManager();
            if (adManager == null) {
                return null;
            }
            return (ITTDownloadVisitor) adManager.getExtra(ITTDownloadVisitor.class, C4053ox.m16382mb(1));
        }
        return iTTDownloadVisitor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m16407b(String str) {
        C9837ox m7441mb;
        JSONObject mo7483ko;
        if (TextUtils.isEmpty(str) || (m7441mb = C9923u.m7451mb().m7441mb(str)) == null || (mo7483ko = m7441mb.mo7483ko()) == null || m16406h() == null) {
            return;
        }
        m16406h().checkAutoControl(mo7483ko, str);
    }

    /* renamed from: mb */
    public static void m16400mb(Context context) {
        if (context == null) {
            context = TTAppContextHolder.getContext();
        }
        if (context == null || f9638hj.get()) {
            return;
        }
        synchronized (C4034hj.class) {
            if (!f9638hj.get()) {
                f9637h = context.getApplicationContext();
                if (m16406h() != null) {
                    String initPath = m16406h().initPath(f9641ox);
                    if (!TextUtils.isEmpty(initPath)) {
                        f9640mb = initPath;
                    }
                }
                f9638hj.set(m16390ox(f9637h));
            }
        }
    }

    /* renamed from: mb */
    public static void m16393mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f9640mb = str;
    }

    /* renamed from: mb */
    public static C10071ww m16404mb() {
        m16400mb(getContext());
        return C10071ww.m6960mb(getContext());
    }

    /* renamed from: mb */
    public static boolean m16398mb(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController, IDownloadButtonClickListener iDownloadButtonClickListener) {
        return m16404mb().m6964h().mo7154mb(context, uri, downloadModel, downloadEventConfig, downloadController, iDownloadButtonClickListener);
    }

    /* renamed from: mb */
    public static boolean m16399mb(Context context, Uri uri, DownloadModel downloadModel, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        return m16404mb().m6964h().mo7155mb(context, uri, downloadModel, downloadEventConfig, downloadController);
    }

    /* renamed from: mb */
    public static boolean m16395mb(Uri uri) {
        return C10044x.m7099mb(uri);
    }

    /* renamed from: ox */
    public static void m16391ox() {
        m16404mb().m6962ko();
        if (m16406h() != null) {
            m16406h().clearAllData(f9640mb);
        }
    }

    /* renamed from: mb */
    public static void m16403mb(int i) {
        Map<Integer, ITTDownloadAdapter.OnEventLogHandler> map = f9642u;
        if (map != null) {
            map.remove(Integer.valueOf(i));
        }
    }

    /* renamed from: mb */
    public static void m16402mb(int i, ITTDownloadAdapter.OnEventLogHandler onEventLogHandler) {
        if (onEventLogHandler != null) {
            if (f9642u == null) {
                f9642u = Collections.synchronizedMap(new WeakHashMap());
            }
            f9642u.put(Integer.valueOf(i), onEventLogHandler);
        }
    }

    /* renamed from: b */
    public static Map<Integer, ITTDownloadAdapter.OnEventLogHandler> m16408b() {
        return f9642u;
    }

    /* renamed from: mb */
    public static boolean m16392mb(String str, String str2, JSONObject jSONObject, Object obj) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || jSONObject == null) {
            return false;
        }
        Map<Integer, ITTDownloadAdapter.OnEventLogHandler> m16408b = m16408b();
        if (m16408b != null) {
            for (Map.Entry<Integer, ITTDownloadAdapter.OnEventLogHandler> entry : m16408b.entrySet()) {
                int intValue = entry.getKey().intValue();
                ITTDownloadAdapter.OnEventLogHandler value = entry.getValue();
                if (value != null) {
                    boolean onEventLog = value.onEventLog(intValue, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), str, str2, obj);
                    if (!z && !onEventLog) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: ox */
    private static boolean m16390ox(Context context) {
        InterfaceC9819mb m6961mb;
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            packageName = "";
        }
        if (m16387u()) {
            try {
                m6961mb = C10071ww.m6960mb(applicationContext).m6956mb("pangolin");
            } catch (Throwable unused) {
                m6961mb = C10071ww.m6960mb(applicationContext).m6961mb();
            }
        } else {
            m6961mb = C10071ww.m6960mb(applicationContext).m6961mb();
        }
        if (m6961mb == null) {
            return false;
        }
        InterfaceC9819mb mo7268mb = m6961mb.mo7265mb(new C4042b()).mo7266mb(new C4048mb()).mo7267mb(new C4046hj(applicationContext)).mo7263mb(new C4049ox()).mo7262mb(new InterfaceC9812x() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.3
            @Override // com.p319ss.android.download.api.config.InterfaceC9812x
            /* renamed from: mb */
            public JSONObject mo7930mb() {
                if (C4034hj.m16405hj() != null) {
                    return C4034hj.m16405hj().getDownloadSettings();
                }
                return new JSONObject();
            }
        }).mo7264mb(new InterfaceC9809ox() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.2
            @Override // com.p319ss.android.download.api.config.InterfaceC9809ox
            /* renamed from: mb */
            public boolean mo7935mb() {
                if (C4034hj.m16405hj() != null) {
                    return C4034hj.m16405hj().getAppIsBackground();
                }
                return false;
            }
        }).mo7261mb(new C9830mb.C9831mb().m7868ox("143").m7870mb("open_news").m7876b("5.1.0.2").m7873hj(String.valueOf(5102)).m7872mb()).mo7268mb(new InterfaceC9793e() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.1
            @Override // com.p319ss.android.download.api.config.InterfaceC9793e
            /* renamed from: mb */
            public byte[] mo7951mb(byte[] bArr, int i) {
                return new byte[0];
            }
        });
        mo7268mb.mo7259mb(packageName + ".TTFileProvider").mo7260mb(m16396mb(applicationContext, m16406h() != null ? m16406h().getDownloadSettings() : new JSONObject())).mo7269mb();
        C10061mb.m6988mb();
        if (!"main".equals("internal")) {
            C10071ww.m6960mb(applicationContext).m6963hj().mo7214mb(1);
            C10071ww.m6960mb(applicationContext).m6958mb(f9639ko);
            C10112hj.m6786x().m6801mb(new IInstallAppHandler() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.4
                @Override // com.p319ss.android.socialbase.downloader.depend.IInstallAppHandler
                public boolean installApp(Intent intent) {
                    return false;
                }
            });
        }
        TTDownloadEventLogger tTDownloadEventLogger = m16406h().getTTDownloadEventLogger();
        if (tTDownloadEventLogger != null) {
            tTDownloadEventLogger.onDownloadConfigReady();
        }
        return true;
    }

    /* renamed from: mb */
    private static DownloaderBuilder m16396mb(Context context, JSONObject jSONObject) {
        return new DownloaderBuilder(context).downloadSetting(new IDownloadSettings() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.5
            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadSettings
            public JSONObject get() {
                if (C4034hj.m16405hj() != null) {
                    return C4034hj.m16405hj().getDownloadSettings();
                }
                return new JSONObject();
            }
        }).downloadExpSwitch(jSONObject.optInt("download_exp_switch_temp", 1040187391)).httpService(new C4044h());
    }

    /* renamed from: mb */
    public static boolean m16397mb(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        List<DownloadInfo> m6791ox = C10112hj.m6786x().m6791ox(context);
        if (!m6791ox.isEmpty()) {
            for (DownloadInfo downloadInfo : m6791ox) {
                if (downloadInfo != null && str.equals(downloadInfo.getUrl())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Context getContext() {
        Context context = f9637h;
        return context == null ? TTAppContextHolder.getContext() : context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.sdk.openadsdk.downloadnew.hj$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C4042b implements InterfaceC9804lz {
        @Override // com.p319ss.android.download.api.config.InterfaceC9804lz
        /* renamed from: mb */
        public void mo7902mb(Activity activity, int i, String[] strArr, int[] iArr) {
        }

        @Override // com.p319ss.android.download.api.config.InterfaceC9804lz
        /* renamed from: mb */
        public void mo7901mb(Activity activity, String[] strArr, final InterfaceC9795gm interfaceC9795gm) {
            if (C4034hj.m16405hj() != null) {
                C4034hj.m16405hj().requestPermission(activity, strArr, new ITTPermissionCallback() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.b.1
                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback
                    public void onGranted() {
                        InterfaceC9795gm interfaceC9795gm2 = interfaceC9795gm;
                        if (interfaceC9795gm2 != null) {
                            interfaceC9795gm2.mo7541mb();
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTPermissionCallback
                    public void onDenied(String str) {
                        InterfaceC9795gm interfaceC9795gm2 = interfaceC9795gm;
                        if (interfaceC9795gm2 != null) {
                            interfaceC9795gm2.mo7540mb(str);
                        }
                    }
                });
            }
        }

        @Override // com.p319ss.android.download.api.config.InterfaceC9804lz
        /* renamed from: mb */
        public boolean mo7900mb(Context context, String str) {
            if (C4034hj.m16405hj() != null) {
                return C4034hj.m16405hj().hasPermission(context, str);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.sdk.openadsdk.downloadnew.hj$ox */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C4049ox implements InterfaceC9811ww {
        private C4049ox() {
        }

        @Override // com.p319ss.android.download.api.config.InterfaceC9811ww
        /* renamed from: mb */
        public void mo7932mb(String str, String str2, Map<String, Object> map, final InterfaceC9802l interfaceC9802l) {
            char c;
            int hashCode = str.hashCode();
            int i = 0;
            if (hashCode != 70454) {
                if (hashCode == 2461856 && str.equals("POST")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (str.equals("GET")) {
                    c = 0;
                }
                c = 65535;
            }
            switch (c) {
                case 1:
                    i = 1;
                    break;
            }
            if (C4034hj.m16405hj() != null) {
                C4034hj.m16405hj().execute(i, str2, map, new ITTHttpCallback() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.ox.1
                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback
                    public void onResponse(String str3) {
                        InterfaceC9802l interfaceC9802l2 = interfaceC9802l;
                        if (interfaceC9802l2 != null) {
                            interfaceC9802l2.mo6999mb(str3);
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback
                    public void onError(Throwable th) {
                        InterfaceC9802l interfaceC9802l2 = interfaceC9802l;
                        if (interfaceC9802l2 != null) {
                            interfaceC9802l2.mo6998mb(th);
                        }
                    }
                });
            }
        }

        @Override // com.p319ss.android.download.api.config.InterfaceC9811ww
        /* renamed from: mb */
        public void mo7931mb(String str, byte[] bArr, String str2, int i, final InterfaceC9802l interfaceC9802l) {
            if (C4034hj.m16405hj() != null) {
                C4034hj.m16405hj().postBody(str, bArr, str2, new ITTHttpCallback() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.ox.2
                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback
                    public void onResponse(String str3) {
                        InterfaceC9802l interfaceC9802l2 = interfaceC9802l;
                        if (interfaceC9802l2 != null) {
                            interfaceC9802l2.mo6999mb(str3);
                        }
                    }

                    @Override // com.bytedance.sdk.openadsdk.downloadnew.core.ITTHttpCallback
                    public void onError(Throwable th) {
                        InterfaceC9802l interfaceC9802l2 = interfaceC9802l;
                        if (interfaceC9802l2 != null) {
                            interfaceC9802l2.mo6998mb(th);
                        }
                    }
                });
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.downloadnew.hj$h */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4044h implements IDownloadHttpService {
        @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHttpService
        public IDownloadHttpConnection downloadWithConnection(int i, String str, List<HttpHeader> list) throws IOException {
            final C4031b.C4032mb m16420mb = C4031b.m16420mb(str, list);
            if (m16420mb != null) {
                return new IDownloadHttpConnection() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.h.1
                    @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                    public void cancel() {
                    }

                    @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection
                    public InputStream getInputStream() {
                        return m16420mb.f9628mb;
                    }

                    @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                    public String getResponseHeaderField(String str2) {
                        if (m16420mb.f9629ox != null) {
                            return m16420mb.f9629ox.get(str2);
                        }
                        return null;
                    }

                    @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                    public int getResponseCode() {
                        return m16420mb.f9626b;
                    }

                    @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection
                    public void end() {
                        try {
                            m16420mb.f9627hj.disconnect();
                        } catch (Exception unused) {
                        }
                    }
                };
            }
            return null;
        }
    }

    /* renamed from: com.bytedance.sdk.openadsdk.downloadnew.hj$hj */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C4046hj implements InterfaceC9800je {

        /* renamed from: mb */
        private final WeakReference<Context> f9648mb;

        public C4046hj(Context context) {
            this.f9648mb = new WeakReference<>(context);
        }

        @Override // com.p319ss.android.download.api.config.InterfaceC9800je
        /* renamed from: mb */
        public void mo7905mb(int i, Context context, DownloadModel downloadModel, String str, Drawable drawable, int i2) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Toast.makeText(context, str, 0).show();
            } catch (Exception e) {
                Logger.m6472e("LibUIFactory", "showToastWithDuration e " + e.getMessage());
            }
        }

        @Override // com.p319ss.android.download.api.config.InterfaceC9800je
        /* renamed from: mb */
        public AlertDialog mo7903ox(DownloadAlertDialogInfo downloadAlertDialogInfo) {
            if (downloadAlertDialogInfo != null && C4034hj.m16405hj() != null) {
                if (downloadAlertDialogInfo.f18776mb != null && (downloadAlertDialogInfo.f18776mb instanceof Activity)) {
                    return C4034hj.m16405hj().showDialogBySelf((Activity) downloadAlertDialogInfo.f18776mb, downloadAlertDialogInfo.f18780x == 1, m16386b(downloadAlertDialogInfo));
                }
                C4034hj.m16405hj().showDialogByDelegate(this.f9648mb, downloadAlertDialogInfo.f18780x == 1, m16386b(downloadAlertDialogInfo));
            }
            return null;
        }

        /* renamed from: b */
        private DialogBuilder m16386b(final DownloadAlertDialogInfo downloadAlertDialogInfo) {
            return DialogBuilder.builder().setTitle(downloadAlertDialogInfo.f18777ox).setMessage(downloadAlertDialogInfo.f18771b).setNegativeBtnText(downloadAlertDialogInfo.f18772h).setPositiveBtnText(downloadAlertDialogInfo.f18773hj).setIcon(downloadAlertDialogInfo.f18774ko).setDialogStatusChangedListener(new IDialogStatusChangedListener() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.hj.1
                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onPositiveBtnClick(DialogInterface dialogInterface) {
                    if (downloadAlertDialogInfo.f18779ww != null) {
                        downloadAlertDialogInfo.f18779ww.mo7140mb(dialogInterface);
                    }
                }

                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onNegativeBtnClick(DialogInterface dialogInterface) {
                    if (downloadAlertDialogInfo.f18779ww != null) {
                        try {
                            downloadAlertDialogInfo.f18779ww.mo7139ox(dialogInterface);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override // com.bytedance.sdk.openadsdk.downloadnew.core.IDialogStatusChangedListener
                public void onCancel(DialogInterface dialogInterface) {
                    if (downloadAlertDialogInfo.f18779ww != null) {
                        downloadAlertDialogInfo.f18779ww.mo7141b(dialogInterface);
                    }
                }
            });
        }
    }

    /* renamed from: com.bytedance.sdk.openadsdk.downloadnew.hj$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C4048mb implements InterfaceC9801ko {
        @Override // com.p319ss.android.download.api.config.InterfaceC9801ko
        /* renamed from: mb */
        public void mo7946mb(C9832ox c9832ox) {
            C3972mb.m16544ox("LibEventLogger", "onV3Event");
            m16383mb(c9832ox, true);
        }

        @Override // com.p319ss.android.download.api.config.InterfaceC9801ko
        /* renamed from: ox */
        public void mo7945ox(C9832ox c9832ox) {
            C3972mb.m16544ox("LibEventLogger", "onEvent called");
            m16383mb(c9832ox, false);
            m16384b(c9832ox);
        }

        /* renamed from: b */
        private void m16384b(C9832ox c9832ox) {
            if (c9832ox == null) {
                return;
            }
            Object m7863je = c9832ox.m7863je();
            TTDownloadEventModel label = TTDownloadEventModel.builder().setTag(c9832ox.m7856ox()).setExtJson(c9832ox.m7854ww()).setMaterialMeta(m7863je instanceof JSONObject ? (JSONObject) m7863je : null).setLabel(c9832ox.m7867b());
            boolean z = "download_notification".equals(c9832ox.m7856ox()) || "landing_h5_download_ad_button".equals(c9832ox.m7856ox());
            if (C4034hj.m16405hj() != null) {
                C4034hj.m16405hj().executeLogUpload(label, z);
            }
        }

        /* renamed from: mb */
        private void m16383mb(C9832ox c9832ox, boolean z) {
            TTDownloadEventLogger tTDownloadEventLogger;
            if (C4034hj.m16405hj() == null || (tTDownloadEventLogger = C4034hj.m16405hj().getTTDownloadEventLogger()) == null || c9832ox == null) {
                return;
            }
            if (tTDownloadEventLogger.shouldFilterOpenSdkLog() && C4034hj.m16405hj().isOpenSdkEvent(c9832ox.toString())) {
                return;
            }
            if (z) {
                tTDownloadEventLogger.onV3Event(C4034hj.m16389ox(c9832ox));
            } else {
                tTDownloadEventLogger.onEvent(C4034hj.m16389ox(c9832ox));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static JSONObject m16389ox(C9832ox c9832ox) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("category", c9832ox.m7859mb());
            jSONObject.put("tag", c9832ox.m7856ox());
            jSONObject.put("label", c9832ox.m7867b());
            jSONObject.put("isAd", c9832ox.m7865hj());
            jSONObject.put("adId", c9832ox.m7866h());
            jSONObject.put("logExtra", c9832ox.m7855u());
            jSONObject.put("extValue", c9832ox.m7862ko());
            jSONObject.put("extJson", c9832ox.m7854ww());
            jSONObject.put("paramsJson", c9832ox.m7860lz());
            jSONObject.put("eventSource", c9832ox.m7864jb());
            jSONObject.put("extraObject", c9832ox.m7863je());
            jSONObject.put("clickTrackUrl", c9832ox.m7853x());
            jSONObject.put("isV3", c9832ox.m7858nk());
            jSONObject.put("V3EventName", c9832ox.m7857o());
            jSONObject.put("V3EventParams", c9832ox.m7861lc());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: mb */
    public static boolean m16401mb(Activity activity, final ExitInstallListener exitInstallListener) {
        return C9911mb.m7514mb().m7511mb(activity, false, new C9911mb.InterfaceC9913mb() { // from class: com.bytedance.sdk.openadsdk.downloadnew.hj.7
            @Override // com.p319ss.android.downloadlib.addownload.p327mb.C9911mb.InterfaceC9913mb
            /* renamed from: mb */
            public void mo7504mb() {
                ExitInstallListener exitInstallListener2 = ExitInstallListener.this;
                if (exitInstallListener2 != null) {
                    exitInstallListener2.onExitInstall();
                }
            }
        });
    }
}
