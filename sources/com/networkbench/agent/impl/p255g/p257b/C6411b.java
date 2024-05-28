package com.networkbench.agent.impl.p255g.p257b;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.p239a.p240a.C6226b;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.C6409b;
import com.networkbench.agent.impl.p255g.EnumC6421f;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6411b extends C6409b {

    /* renamed from: m */
    private static InterfaceC6393e f16198m = C6394f.m10150a();

    /* renamed from: b */
    public HashMap<String, String> f16199b;

    /* renamed from: c */
    public HashMap<String, String> f16200c;

    /* renamed from: d */
    private String f16201d;

    /* renamed from: e */
    private int f16202e;

    /* renamed from: f */
    private String f16203f;

    /* renamed from: g */
    private String f16204g;

    /* renamed from: h */
    private Map<String, Object> f16205h;

    /* renamed from: i */
    private String f16206i;

    /* renamed from: j */
    private String f16207j;

    /* renamed from: k */
    private String f16208k;

    /* renamed from: l */
    private String f16209l;

    /* renamed from: n */
    private RequestMethodType f16210n;

    /* renamed from: o */
    private String f16211o;

    /* renamed from: p */
    private HttpLibType f16212p;

    /* renamed from: q */
    private int f16213q;

    /* renamed from: r */
    private Map f16214r;

    /* renamed from: s */
    private String f16215s;

    /* renamed from: c */
    public Map m10053c() {
        return this.f16214r;
    }

    /* renamed from: a */
    public void m10056a(Map map) {
        this.f16214r = map;
    }

    /* renamed from: d */
    public String m10051d() {
        return this.f16215s;
    }

    /* renamed from: e */
    public int m10050e() {
        return this.f16213q;
    }

    /* renamed from: f */
    public String m10049f() {
        return this.f16208k;
    }

    /* renamed from: g */
    public RequestMethodType m10048g() {
        return this.f16210n;
    }

    /* renamed from: h */
    public String m10047h() {
        return this.f16207j;
    }

    /* renamed from: i */
    public HttpLibType m10046i() {
        return this.f16212p;
    }

    public C6411b(C6226b c6226b, String str, Map<String, Object> map, String str2) {
        super(EnumC6421f.HttpError);
        this.f16212p = HttpLibType.URLConnection;
        this.f16199b = new HashMap<>();
        this.f16200c = new HashMap<>();
        String m10953r = c6226b.m10953r();
        String m10974d = c6226b.m10974d();
        int m10952s = c6226b.m10952s() == 0 ? -1 : c6226b.m10952s();
        if (m10953r == null) {
            return;
        }
        this.f16201d = m10953r;
        this.f16202e = m10952s;
        m10068a(System.currentTimeMillis());
        this.f16204g = m10037r();
        this.f16203f = str;
        this.f16205h = map;
        this.f16208k = c6226b.m10950u();
        this.f16207j = m10974d;
        this.f16206i = str2;
        this.f16210n = c6226b.m10954q();
        this.f16209l = c6226b.m10959l();
        this.f16211o = c6226b.m10961j();
        this.f16212p = c6226b.m10945z();
        this.f16213q = c6226b.m10955p();
        this.f16215s = c6226b.m10965h();
        this.f16199b = c6226b.f15375b;
        this.f16200c = c6226b.f15376c;
        this.f16214r = c6226b.m10969f();
    }

    /* renamed from: r */
    private String m10037r() {
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = 0;
        for (int i2 = 0; i2 < stackTrace.length; i2++) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            if (!m10058a(stackTraceElement)) {
                sb.append(stackTraceElement.toString());
                if (i2 <= stackTrace.length - 1) {
                    sb.append("\n");
                }
                i++;
                if (i >= NBSAgent.getStackTraceLimit()) {
                    break;
                }
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    private boolean m10058a(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        if (className.startsWith("com.networkbench")) {
            return true;
        }
        if (className.startsWith("dalvik.system.VMStack") && methodName.startsWith("getThreadStackTrace")) {
            return true;
        }
        return className.startsWith("java.lang.Thread") && methodName.startsWith("getStackTrace");
    }

    /* renamed from: a */
    public void m10057a(String str) {
        this.f16201d = str;
    }

    /* renamed from: a */
    public void m10059a(int i) {
        this.f16202e = i;
    }

    /* renamed from: b */
    public void m10055b(String str) {
        this.f16203f = str;
    }

    /* renamed from: c */
    public void m10052c(String str) {
        this.f16204g = str;
    }

    /* renamed from: b */
    public void m10054b(Map<String, Object> map) {
        this.f16205h = map;
    }

    /* renamed from: j */
    public String m10045j() {
        return this.f16201d;
    }

    /* renamed from: k */
    public int m10044k() {
        return this.f16202e;
    }

    /* renamed from: l */
    public String m10043l() {
        return this.f16203f;
    }

    /* renamed from: m */
    public String m10042m() {
        return this.f16204g;
    }

    /* renamed from: n */
    public Map<String, Object> m10041n() {
        return this.f16205h;
    }

    /* renamed from: o */
    public String m10040o() {
        return this.f16206i;
    }

    /* renamed from: p */
    public String m10039p() {
        return this.f16209l;
    }

    /* renamed from: q */
    public String m10038q() {
        return this.f16211o;
    }

    @Override // com.networkbench.agent.impl.p255g.C6409b
    public String toString() {
        return ("HttpErrorMeasurement: url:" + this.f16201d + ", httpStatusCode:" + this.f16202e + ",responseBody:" + this.f16203f + ", stackTrace:" + this.f16204g + ",message:" + this.f16206i + ",urlParams:" + this.f16207j + ", filterParams:" + this.f16208k + ", remoteIp:" + this.f16209l + ",appPhase:" + this.f16213q + ", requestMethodType:" + this.f16210n + ", cdn_vendor_name:" + this.f16211o + ",appPhase : +" + this.f16213q).replaceAll("[\r\n]", ";");
    }
}
