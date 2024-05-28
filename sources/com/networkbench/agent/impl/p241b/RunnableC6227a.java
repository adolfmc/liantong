package com.networkbench.agent.impl.p241b;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.crash.InterfaceC6329h;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConnectionInterface;
import com.networkbench.agent.impl.harvest.HarvestURLConnection;
import com.networkbench.agent.impl.harvest.p260a.C6448l;
import com.networkbench.agent.impl.harvest.p260a.EnumC6455q;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p262i.EnumC6467d;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6227a implements Runnable {

    /* renamed from: b */
    private static final InterfaceC6393e f15400b = C6394f.m10150a();

    /* renamed from: a */
    private String f15401a;

    /* renamed from: c */
    private InterfaceC6329h f15402c;

    /* renamed from: d */
    private String f15403d;

    /* renamed from: e */
    private String f15404e;

    /* renamed from: f */
    private EnumC6467d f15405f;

    /* renamed from: g */
    private String f15406g;

    /* renamed from: h */
    private HarvestConnectionInterface f15407h;

    public RunnableC6227a(String str, InterfaceC6329h interfaceC6329h, String str2, String str3, EnumC6467d enumC6467d, String str4) throws C6632b {
        if (str == null || TextUtils.isEmpty(str3)) {
            throw new C6632b("error param in anrSender");
        }
        this.f15401a = str;
        this.f15402c = interfaceC6329h;
        this.f15403d = str2;
        this.f15404e = str3;
        this.f15405f = enumC6467d;
        this.f15406g = str4;
        this.f15407h = new HarvestURLConnection(C6653u.m8697h(), NBSAgent.getDeviceInformation().initUserHeaderValue(), C6638h.m8963w().m9086A());
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            EnumC6455q enumC6455q = EnumC6455q.CRASH_DATA;
            if (EnumC6467d.ANR_DATA == this.f15405f) {
                enumC6455q = EnumC6455q.ANR_DATA;
            }
            if (this.f15407h.sendDataStr(this.f15401a, C6448l.m9964a(enumC6455q, this.f15404e, C6638h.m8963w().m9069R())).isSuccInStatusCode()) {
                m10944a();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private void m10944a() {
        try {
            this.f15402c.mo10376a(this.f15403d);
            if (this.f15405f != EnumC6467d.CRASH_DATA || TextUtils.isEmpty(this.f15406g)) {
                return;
            }
            Harvest.getInstance().getHarvester().sendForgeUserActionItemHttp(this.f15406g);
        } catch (Throwable unused) {
            this.f15402c.mo10369d();
        }
    }
}
