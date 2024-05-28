package com.p319ss.android.downloadlib.addownload;

import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.model.C9921mb;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.p327mb.C9911mb;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.HandlerC10051je;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import com.p319ss.android.socialbase.downloader.utils.SystemUtils;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.addownload.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9905mb implements HandlerC10051je.InterfaceC10052mb {

    /* renamed from: mb */
    private static final String f19049mb = "mb";

    /* renamed from: ox */
    private static C9905mb f19050ox;

    /* renamed from: b */
    private HandlerC10051je f19051b = new HandlerC10051je(Looper.getMainLooper(), this);

    /* renamed from: hj */
    private long f19052hj;

    /* renamed from: mb */
    public static C9905mb m7533mb() {
        if (f19050ox == null) {
            synchronized (C9905mb.class) {
                if (f19050ox == null) {
                    f19050ox = new C9905mb();
                }
            }
        }
        return f19050ox;
    }

    private C9905mb() {
    }

    /* renamed from: mb */
    public void m7531mb(@NonNull DownloadInfo downloadInfo, long j, long j2, String str, String str2, String str3, String str4) {
        C9921mb c9921mb = new C9921mb(downloadInfo.getId(), j, j2, str, str2, str3, str4);
        DownloadSetting obtain = DownloadSetting.obtain(downloadInfo.getId());
        if (obtain.optInt("back_miui_silent_install", 1) == 0 && ((C10152hj.m6573je() || C10152hj.m6567nk()) && SystemUtils.checkServiceExists(C9940x.getContext(), "com.miui.securitycore", "com.miui.enterprise.service.EntInstallService"))) {
            if (DownloadUtils.getBoolean(downloadInfo.getTempCacheData().get("extra_silent_install_succeed"), false)) {
                Message obtainMessage = this.f19051b.obtainMessage(200, c9921mb);
                obtainMessage.arg1 = 2;
                this.f19051b.sendMessageDelayed(obtainMessage, obtain.optInt("check_silent_install_interval", 60000));
                return;
            }
            C9837ox m7452hj = C9923u.m7451mb().m7452hj(c9921mb.f19119ox);
            JSONObject jSONObject = new JSONObject();
            int i = -1;
            try {
                jSONObject.put("ttdownloader_type", "miui_silent_install");
                jSONObject.put("ttdownloader_message", "miui_silent_install_failed: has not started service");
                i = 5;
            } catch (Exception unused) {
            }
            C9940x.m7346u().mo6869mb(null, new BaseException(i, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)), i);
            AdEventHandler.m7315mb().m7300mb("embeded_ad", "ah_result", jSONObject, m7452hj);
        }
        if (C10049hj.m7082b()) {
            long currentTimeMillis = System.currentTimeMillis() - this.f19052hj;
            long m7077hj = C10049hj.m7077hj();
            if (currentTimeMillis < C10049hj.m7079h()) {
                long m7079h = C10049hj.m7079h() - currentTimeMillis;
                m7077hj += m7079h;
                this.f19052hj = System.currentTimeMillis() + m7079h;
            } else {
                this.f19052hj = System.currentTimeMillis();
            }
            HandlerC10051je handlerC10051je = this.f19051b;
            handlerC10051je.sendMessageDelayed(handlerC10051je.obtainMessage(200, c9921mb), m7077hj);
        }
    }

    /* renamed from: mb */
    private void m7532mb(C9921mb c9921mb, int i) {
        if (C9940x.m7369jb() == null || C9940x.m7369jb().mo7935mb() || c9921mb == null) {
            return;
        }
        if (2 == i) {
            C9837ox m7452hj = C9923u.m7451mb().m7452hj(c9921mb.f19119ox);
            JSONObject jSONObject = new JSONObject();
            int i2 = -1;
            try {
                jSONObject.put("ttdownloader_type", "miui_silent_install");
                if (C10050jb.m7060hj(C9940x.getContext(), c9921mb.f19116hj)) {
                    jSONObject.put("ttdownloader_message", "miui_silent_install_succeed");
                    i2 = 4;
                } else {
                    jSONObject.put("ttdownloader_message", "miui_silent_install_failed: has started service");
                    i2 = 5;
                }
            } catch (Exception unused) {
            }
            C9940x.m7346u().mo6869mb(null, new BaseException(i2, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)), i2);
            AdEventHandler.m7315mb().m7300mb("embeded_ad", "ah_result", jSONObject, m7452hj);
        }
        if (C10050jb.m7060hj(C9940x.getContext(), c9921mb.f19116hj)) {
            AdEventHandler.m7315mb().m7305mb("delayinstall_installed", c9921mb.f19119ox);
        } else if (!C10050jb.m7046mb(c9921mb.f19117ko)) {
            AdEventHandler.m7315mb().m7305mb("delayinstall_file_lost", c9921mb.f19119ox);
        } else if (C9911mb.m7514mb().m7506mb(c9921mb.f19116hj)) {
            AdEventHandler.m7315mb().m7305mb("delayinstall_conflict_with_back_dialog", c9921mb.f19119ox);
        } else {
            AdEventHandler.m7315mb().m7305mb("delayinstall_install_start", c9921mb.f19119ox);
            C10112hj.m6812mb(C9940x.getContext(), (int) c9921mb.f19118mb);
        }
    }

    @Override // com.p319ss.android.downloadlib.utils.HandlerC10051je.InterfaceC10052mb
    /* renamed from: mb */
    public void mo7026mb(Message message) {
        if (message.what != 200) {
            return;
        }
        m7532mb((C9921mb) message.obj, message.arg1);
    }
}
