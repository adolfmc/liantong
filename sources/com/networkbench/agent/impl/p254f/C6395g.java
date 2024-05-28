package com.networkbench.agent.impl.p254f;

import android.util.Log;
import com.networkbench.agent.impl.NBSAppAgent;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.f.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6395g implements InterfaceC6393e {

    /* renamed from: a */
    public static int f16146a = 1;

    /* renamed from: b */
    public static int f16147b = 2;

    /* renamed from: c */
    public static int f16148c = 3;

    /* renamed from: d */
    public static int f16149d = 4;

    /* renamed from: e */
    private final String f16150e;

    public C6395g() {
        this.f16150e = "NBSAgent";
    }

    public C6395g(String str) {
        this.f16150e = str;
    }

    @Override // com.networkbench.agent.impl.p254f.InterfaceC6393e
    /* renamed from: a */
    public void mo10122a(String str) {
        if ((NBSAppAgent.LOG_LEVEL_FLAG & NBSAppAgent.LOG_LEVEL_DEBUG) != 0) {
            m10148a(str, f16147b);
        }
    }

    @Override // com.networkbench.agent.impl.p254f.InterfaceC6393e
    /* renamed from: a */
    public void mo10120a(String str, Object... objArr) {
        if ((NBSAppAgent.LOG_LEVEL_FLAG & NBSAppAgent.LOG_LEVEL_DEBUG) != 0) {
            C6389a m10164a = C6392d.m10164a(str, objArr);
            Log.d(this.f16150e, m10164a.m10178a(), m10164a.m10175c());
        }
    }

    @Override // com.networkbench.agent.impl.p254f.InterfaceC6393e
    /* renamed from: b */
    public void mo10119b(String str) {
        if ((NBSAppAgent.LOG_LEVEL_FLAG & NBSAppAgent.LOG_LEVEL_INFO) != 0) {
            m10148a(str, f16148c);
        }
    }

    @Override // com.networkbench.agent.impl.p254f.InterfaceC6393e
    /* renamed from: b */
    public void mo10118b(String str, Object... objArr) {
        if ((NBSAppAgent.LOG_LEVEL_FLAG & NBSAppAgent.LOG_LEVEL_INFO) != 0) {
            C6389a m10164a = C6392d.m10164a(str, objArr);
            Log.i(this.f16150e, m10164a.m10178a(), m10164a.m10175c());
        }
    }

    @Override // com.networkbench.agent.impl.p254f.InterfaceC6393e
    /* renamed from: c */
    public void mo10117c(String str) {
        if ((NBSAppAgent.LOG_LEVEL_FLAG & NBSAppAgent.LOG_LEVEL_VERBOSE) != 0) {
            m10148a(str, f16146a);
        }
    }

    @Override // com.networkbench.agent.impl.p254f.InterfaceC6393e
    /* renamed from: d */
    public void mo10116d(String str) {
        if ((NBSAppAgent.LOG_LEVEL_FLAG & NBSAppAgent.LOG_LEVEL_ERROR) != 0) {
            m10148a(str, f16149d);
        }
    }

    @Override // com.networkbench.agent.impl.p254f.InterfaceC6393e
    /* renamed from: a */
    public void mo10121a(String str, Throwable th) {
        if ((NBSAppAgent.LOG_LEVEL_FLAG & NBSAppAgent.LOG_LEVEL_ERROR) != 0) {
            Log.e(this.f16150e, str, th);
        }
    }

    @Override // com.networkbench.agent.impl.p254f.InterfaceC6393e
    /* renamed from: e */
    public void mo10115e(String str) {
        if ((NBSAppAgent.LOG_LEVEL_FLAG & NBSAppAgent.LOG_LEVEL_WARNING) != 0) {
            Log.w(this.f16150e, str);
        }
    }

    /* renamed from: a */
    public void m10148a(String str, int i) {
        if (str.length() > 4000) {
            String str2 = this.f16150e;
            Log.v(str2, "sb.length = " + str.length());
            int length = str.length() / 4000;
            int i2 = 0;
            while (i2 <= length) {
                int i3 = i2 + 1;
                int i4 = i3 * 4000;
                switch (i) {
                    case 1:
                        if (i4 >= str.length()) {
                            String str3 = this.f16150e;
                            Log.v(str3, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000));
                            break;
                        } else {
                            String str4 = this.f16150e;
                            Log.v(str4, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000, i4));
                            break;
                        }
                    case 2:
                        if (i4 >= str.length()) {
                            String str5 = this.f16150e;
                            Log.d(str5, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000));
                            break;
                        } else {
                            String str6 = this.f16150e;
                            Log.d(str6, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000, i4));
                            break;
                        }
                    case 3:
                        if (i4 >= str.length()) {
                            String str7 = this.f16150e;
                            Log.i(str7, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000));
                            break;
                        } else {
                            String str8 = this.f16150e;
                            Log.i(str8, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000, i4));
                            break;
                        }
                    case 4:
                        if (i4 >= str.length()) {
                            String str9 = this.f16150e;
                            Log.e(str9, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000));
                            break;
                        } else {
                            String str10 = this.f16150e;
                            Log.e(str10, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000, i4));
                            break;
                        }
                    default:
                        if (i4 >= str.length()) {
                            String str11 = this.f16150e;
                            Log.v(str11, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000));
                            break;
                        } else {
                            String str12 = this.f16150e;
                            Log.v(str12, "chunk " + i2 + " of " + length + ":" + str.substring(i2 * 4000, i4));
                            break;
                        }
                }
                i2 = i3;
            }
            return;
        }
        switch (i) {
            case 1:
                Log.v(this.f16150e, str.toString());
                return;
            case 2:
                Log.d(this.f16150e, str.toString());
                return;
            case 3:
                Log.i(this.f16150e, str.toString());
                return;
            case 4:
                Log.e(this.f16150e, str.toString());
                return;
            default:
                Log.v(this.f16150e, str.toString());
                return;
        }
    }
}
