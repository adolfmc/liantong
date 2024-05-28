package com.baidu.uaq.agent.android.customtransmission;

import android.support.annotation.Keep;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.net.MalformedURLException;
import java.net.URL;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.C0762h;
import p001a.p058b.p062b.p063a.p064a.p066b.C0721b;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0730g;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0731h;
import p001a.p058b.p062b.p063a.p064a.p067c.p070c.C0738a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\567196_dexfile_execute.dex */
public class APMAgent {
    public static final InterfaceC3321a LOG = C0749a.f2299a;
    public APMAgent apmAgent;

    public void addDebugLog(String str) {
    }

    public void addLogWithHandler(APMUploadHandler aPMUploadHandler, String str) {
        if (aPMUploadHandler == null || str == null || str.isEmpty()) {
            LOG.info("APMAgent addLogWithHandler failed, cause APMUploadHandler is null or log is empty!");
        } else if (aPMUploadHandler.getUploadName().equals("APMPerformanceConfigurationName")) {
            C0730g c0730g = new C0730g(str);
            if (C0731h.f2234b.getConfig().isEnableTrace()) {
                C0762h.m22248a(c0730g);
            }
        } else {
            C0721b.m22318a(aPMUploadHandler.getUploadName(), str);
        }
    }

    public APMUploadHandler addUploadConfigure(APMUploadConfigure aPMUploadConfigure) {
        InterfaceC3321a interfaceC3321a;
        String str;
        if (aPMUploadConfigure == null || aPMUploadConfigure.getUploadName() == null || aPMUploadConfigure.getUploadName().isEmpty()) {
            LOG.error("添加上报策略失败：APMUploadConfigure, uploadName 有空值");
            return null;
        }
        if (!aPMUploadConfigure.getUploadName().equals("APMPerformanceConfigurationName")) {
            String url = aPMUploadConfigure.getUrl();
            if (aPMUploadConfigure.getMergeBlockCallBack() == null) {
                interfaceC3321a = LOG;
                str = "添加上报策略失败：mergeBlockCallBack为空";
            } else {
                try {
                    new URL(url);
                } catch (MalformedURLException unused) {
                    interfaceC3321a = LOG;
                    str = "添加上报策略失败：url for newUploadConfigure is not legal! url: " + url;
                }
            }
            interfaceC3321a.error(str);
            return null;
        }
        APMUploadHandler apmUploadHandler = aPMUploadConfigure.getApmUploadHandler();
        C0738a c0738a = C0738a.f2254b;
        if (c0738a.f2258f > 0) {
            c0738a.m22297a(aPMUploadConfigure);
        }
        InterfaceC3321a interfaceC3321a2 = LOG;
        StringBuilder m24437a = outline.m24437a("addUploadConfigure getInstanceNumber:");
        m24437a.append(C0738a.f2254b.f2258f);
        interfaceC3321a2.mo17428D(m24437a.toString());
        return apmUploadHandler;
    }

    public APMUploadConfigure newUploadConfigure(String str, String str2, MergeBlockCallBack mergeBlockCallBack) {
        return new APMUploadConfigure(str, str2, mergeBlockCallBack);
    }

    @Deprecated
    public void removeUploadConfigure(APMUploadHandler aPMUploadHandler) {
        if (aPMUploadHandler == null) {
            return;
        }
        C0721b.m22320a(aPMUploadHandler.getUploadName());
    }

    public void setAgent(APMAgent aPMAgent) {
        this.apmAgent = aPMAgent;
    }

    public synchronized void stopAPM() {
        if (this.apmAgent != null) {
            C0738a.f2254b.m22299a();
            this.apmAgent = null;
        } else {
            LOG.mo17428D("This instance already stop one time");
        }
    }
}
