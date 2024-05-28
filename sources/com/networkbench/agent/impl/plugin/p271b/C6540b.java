package com.networkbench.agent.impl.plugin.p271b;

import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.p275f.p276a.C6578b;
import com.networkbench.agent.impl.plugin.p275f.p276a.C6587f;
import com.networkbench.agent.impl.plugin.p275f.p276a.p277a.C6576c;
import com.networkbench.agent.impl.util.C6648q;
import com.networkbench.agent.impl.util.C6653u;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6540b {

    /* renamed from: k */
    protected static InterfaceC6393e f16702k = C6394f.m10150a();

    /* renamed from: a */
    String f16703a;

    /* renamed from: b */
    long f16704b;

    /* renamed from: c */
    String f16705c;

    /* renamed from: d */
    String f16706d;

    /* renamed from: e */
    String f16707e;

    /* renamed from: f */
    long f16708f;

    /* renamed from: g */
    int f16709g;

    /* renamed from: h */
    int f16710h;

    /* renamed from: i */
    int f16711i;

    /* renamed from: j */
    Map<String, List<String>> f16712j = new HashMap();

    /* renamed from: l */
    private String f16713l;

    public C6540b(String str, int i) {
        this.f16713l = str;
        this.f16710h = i * 1024 * 1024;
        C6396h.m10137e("downloadSizeLimit:" + this.f16710h);
        this.f16703a = "";
        this.f16705c = "";
        this.f16706d = "";
        this.f16707e = "";
        this.f16711i = 0;
    }

    /* renamed from: a */
    public void m9479a() throws Exception {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.f16707e = m9475c();
            m9476b();
            HttpURLConnection m9474d = m9474d();
            m9474d.setConnectTimeout(30000);
            m9474d.setReadTimeout(30000);
            m9474d.connect();
            this.f16712j = m9474d.getHeaderFields();
            if (m9474d.getResponseCode() != 200) {
                if (m9474d.getResponseCode() >= 400) {
                    this.f16703a = "HTTP statusCode: " + m9474d.getResponseCode();
                }
            } else {
                this.f16709g = m9478a(new BufferedInputStream(m9474d.getInputStream()));
            }
            this.f16711i = C6648q.m8778b(this.f16707e);
            m9474d.disconnect();
            this.f16704b = System.currentTimeMillis() - currentTimeMillis;
        } catch (IOException e) {
            this.f16703a = e.getMessage();
        }
    }

    /* renamed from: b */
    private void m9476b() throws Exception {
        if (TextUtils.isEmpty(this.f16707e)) {
            return;
        }
        if (!C6653u.m8688m(this.f16707e)) {
            C6578b.m9327a(this.f16707e, new C6587f(), new C6578b.InterfaceC6580a() { // from class: com.networkbench.agent.impl.plugin.b.b.1
                @Override // com.networkbench.agent.impl.plugin.p275f.p276a.C6578b.InterfaceC6580a
                /* renamed from: a */
                public void mo9325a(C6578b.C6581b c6581b) {
                    C6576c[] c6576cArr;
                    if (c6581b == null) {
                        return;
                    }
                    C6540b.this.f16703a = c6581b.f16863d;
                    if (c6581b.f16862c == null) {
                        return;
                    }
                    for (C6576c c6576c : c6581b.f16862c) {
                        C6540b.f16702k.mo10122a("record type:" + c6576c.f16851d + ", cname:" + c6576c.f16850c);
                        if (c6576c.f16851d == 1 && TextUtils.isEmpty(C6540b.this.f16706d)) {
                            C6540b.this.f16706d = c6576c.f16850c;
                            C6540b.this.f16708f = c6581b.f16861b;
                        }
                        if (c6576c.f16851d == 5) {
                            C6540b.this.f16705c = c6576c.f16850c;
                        }
                    }
                }
            });
        } else {
            this.f16706d = this.f16707e;
        }
    }

    /* renamed from: c */
    private String m9475c() {
        try {
            return new URL(this.f16713l).getHost();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f16702k;
            interfaceC6393e.mo10115e("DownloadPlugin get hostName error: " + e.getMessage());
            return "";
        }
    }

    /* renamed from: a */
    private void m9477a(HttpURLConnection httpURLConnection) {
        httpURLConnection.getHeaderFields();
    }

    /* renamed from: a */
    private int m9478a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        do {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        } while (byteArrayOutputStream.size() < this.f16710h);
        inputStream.close();
        return byteArrayOutputStream.size();
    }

    /* renamed from: d */
    private HttpURLConnection m9474d() throws IOException {
        return (HttpURLConnection) new URL(this.f16713l).openConnection();
    }

    public String toString() {
        return "VisitDownUrl{urlStr='" + this.f16713l + "', exception='" + this.f16703a + "', networktime=" + this.f16704b + ", cName='" + this.f16705c + "', ip='" + this.f16706d + "', host='" + this.f16707e + "', dnsTime=" + this.f16708f + ", downloadSize=" + this.f16709g + ", limitSize=" + this.f16710h + '}';
    }
}
