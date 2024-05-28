package com.networkbench.agent.impl.p268n;

import android.webkit.WebView;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6652t;
import com.networkbench.agent.impl.util.C6653u;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6527l {

    /* renamed from: h */
    private static InterfaceC6393e f16657h = C6394f.m10150a();

    /* renamed from: a */
    public WebView f16658a;

    /* renamed from: b */
    public String f16659b;

    /* renamed from: c */
    public String f16660c;

    /* renamed from: g */
    private InterfaceC6528a f16664g;

    /* renamed from: e */
    public boolean f16662e = false;

    /* renamed from: d */
    public boolean f16661d = true;

    /* renamed from: f */
    public String f16663f = C6255f.m10820a();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.n.l$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC6528a {
        /* renamed from: b */
        Map<String, String> mo9520b();
    }

    /* renamed from: a */
    protected abstract ActionData mo9524a();

    public AbstractC6527l(WebView webView, String str) {
        this.f16658a = webView;
        this.f16659b = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m9522a(InterfaceC6528a interfaceC6528a) {
        this.f16664g = interfaceC6528a;
    }

    /* renamed from: c */
    public void m9521c() {
        HashMap hashMap;
        HashMap hashMap2;
        C6281e c6281e;
        ActionData mo9524a = mo9524a();
        if (mo9524a != null) {
            mo9524a.setDataTag(C6638h.m8963w().m9001h());
            mo9524a.setUrl(C6653u.m8726b(this.f16659b));
            mo9524a.setTotalTime(0);
            mo9524a.setCarrier(NBSAgent.getActiveNetworkCarrier());
            mo9524a.setBytesReceived(0L);
            mo9524a.setBytesSent(0L);
            mo9524a.setAppData("");
            mo9524a.setTimestamp(Long.valueOf(System.currentTimeMillis()));
            mo9524a.setUrlParams(null);
            if (this.f16661d) {
                mo9524a.setHttpLibType(HttpLibType.Webview);
            } else {
                mo9524a.setHttpLibType(HttpLibType.WebViewResource);
            }
            mo9524a.setTime_to_connect(-1);
            mo9524a.setTime_first_package(0);
            mo9524a.setTime_ssl_handshake(-1);
            mo9524a.setTime_to_dns(-1);
            mo9524a.setIP("");
            C6652t.m8762a(mo9524a);
            if (C6652t.m8761a(C6653u.m8744a(mo9524a.getUrl()))) {
                return;
            }
            if (mo9524a.getStatusCode() >= 400 || mo9524a.getStatusCode() == -1) {
                try {
                    if (this.f16664g != null) {
                        hashMap = new HashMap();
                        try {
                            hashMap.putAll(this.f16664g.mo9520b());
                        } catch (Exception unused) {
                            f16657h.mo10115e("get response header error !");
                            hashMap2 = hashMap;
                            c6281e = new C6281e(C6653u.m8744a(mo9524a.getUrl()), "", C6653u.m8685p(mo9524a.getUrl()), "", mo9524a.getStatusCode(), "", "", hashMap2, "", mo9524a.getRequestMethod(), mo9524a.getCdnVendorName(), mo9524a.getHttpLibType(), mo9524a.getAppPhase(), this.f16663f, mo9524a.requestHeaderParam, mo9524a.responseHeaderParam, C6638h.m8963w().m9001h());
                            c6281e.m10658a(C6638h.m8963w().m9001h());
                            if (this.f16662e) {
                            }
                            m9523a(mo9524a, c6281e);
                            f16657h.mo10122a("isForMainFrame  :" + this.f16661d);
                            if (this.f16661d) {
                                return;
                            }
                            return;
                        }
                    } else {
                        hashMap = null;
                    }
                    hashMap2 = hashMap;
                } catch (Exception unused2) {
                    hashMap = null;
                }
                c6281e = new C6281e(C6653u.m8744a(mo9524a.getUrl()), "", C6653u.m8685p(mo9524a.getUrl()), "", mo9524a.getStatusCode(), "", "", hashMap2, "", mo9524a.getRequestMethod(), mo9524a.getCdnVendorName(), mo9524a.getHttpLibType(), mo9524a.getAppPhase(), this.f16663f, mo9524a.requestHeaderParam, mo9524a.responseHeaderParam, C6638h.m8963w().m9001h());
                c6281e.m10658a(C6638h.m8963w().m9001h());
            } else {
                c6281e = null;
            }
            if (this.f16662e || !this.f16661d) {
                m9523a(mo9524a, c6281e);
            }
            f16657h.mo10122a("isForMainFrame  :" + this.f16661d);
            if (this.f16661d || this.f16662e) {
                return;
            }
            if (!C6638h.m8963w().m9065V() || C6516c.f16607a) {
                Harvest.addPagePerfData(C6529m.m9518a(System.currentTimeMillis(), this.f16659b, UUID.randomUUID().toString(), -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, "", "", mo9524a.getStatusCode()));
            }
        }
    }

    /* renamed from: a */
    public void m9523a(ActionData actionData, C6281e c6281e) {
        C6396h.m10138d(" WebErrorBase addActionAndError :" + actionData.getUrl());
        if (c6281e != null) {
            Harvest.addHttpError(actionData, c6281e);
        } else {
            Harvest.addHttpTransaction(actionData);
        }
    }
}
