package com.sdk.p300p;

import android.util.Log;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.p301q.C7035b;
import com.sdk.p301q.C7036c;
import com.sdk.p301q.InterfaceC7034a;
import com.sdk.p304t.C7039a;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.p.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7032f {

    /* renamed from: c */
    public static C7032f f18192c;

    /* renamed from: a */
    public InterfaceC7034a f18193a;

    /* renamed from: b */
    public final String f18194b;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public C7032f() {
        char c;
        InterfaceC7034a c7036c;
        String m8125b = C7039a.m8125b();
        this.f18194b = C7039a.m8127a();
        m8125b.hashCode();
        switch (m8125b.hashCode()) {
            case 67:
                if (m8125b.equals("C")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 68:
                if (m8125b.equals("D")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 69:
                if (m8125b.equals("E")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                if (SDKManager.isSupport_GM()) {
                    c7036c = new C7036c();
                    break;
                } else {
                    return;
                }
            default:
                c7036c = new C7035b();
                break;
        }
        this.f18193a = c7036c;
    }

    /* renamed from: a */
    public static C7032f m8136a() {
        if (f18192c == null) {
            Log.d("TAG", "SecurityPlan getInstance() 开始 " + System.currentTimeMillis());
            f18192c = new C7032f();
            if (SDKManager.isSupport_GM()) {
                if (Security.getProvider("BC") != null) {
                    double version = Security.getProvider("BC").getVersion();
                    System.out.println(version);
                    if (version < 1.69d) {
                        Security.removeProvider("BC");
                    }
                }
                if (Security.getProvider("BC") == null) {
                    Security.addProvider(new BouncyCastleProvider());
                }
                if (Security.getProvider("BC") != null) {
                    System.out.println(Security.getProvider("BC").getVersion());
                }
            }
        }
        return f18192c;
    }
}
