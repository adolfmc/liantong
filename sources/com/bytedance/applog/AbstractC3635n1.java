package com.bytedance.applog;

import android.os.Handler;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.bytedance.applog.n1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC3635n1 {

    /* renamed from: a */
    public AbstractC3635n1 f8607a;

    /* renamed from: b */
    public Handler f8608b;

    /* renamed from: com.bytedance.applog.n1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3636a implements InterfaceC3643h<String> {
        public C3636a() {
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17200a() {
            return AbstractC3635n1.this.mo17065b("openudid");
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: b */
        public boolean mo17196b(String str) {
            return C3712v2.m17078a(str);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public boolean mo17198a(String str, String str2) {
            return C3712v2.m17073b(str, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17197a(String str, String str2, AbstractC3635n1 abstractC3635n1) {
            String str3 = str;
            return abstractC3635n1 == null ? str3 : abstractC3635n1.m17206d(str3, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public void mo17199a(String str) {
            AbstractC3635n1.this.mo17067a("openudid", str);
        }
    }

    /* renamed from: com.bytedance.applog.n1$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3637b implements InterfaceC3643h<String> {
        public C3637b() {
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17200a() {
            return AbstractC3635n1.this.mo17065b("clientudid");
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: b */
        public boolean mo17196b(String str) {
            return C3712v2.m17078a(str);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public boolean mo17198a(String str, String str2) {
            return C3712v2.m17073b(str, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17197a(String str, String str2, AbstractC3635n1 abstractC3635n1) {
            String str3 = str;
            return abstractC3635n1 == null ? str3 : abstractC3635n1.m17208b(str3, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public void mo17199a(String str) {
            AbstractC3635n1.this.mo17067a("clientudid", str);
        }
    }

    /* renamed from: com.bytedance.applog.n1$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3638c implements InterfaceC3643h<String> {
        public C3638c() {
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17200a() {
            return AbstractC3635n1.this.mo17065b("serial_number");
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: b */
        public boolean mo17196b(String str) {
            String str2 = str;
            return (TextUtils.isEmpty(str2) || TextUtils.equals(str2, "unknown")) ? false : true;
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public boolean mo17198a(String str, String str2) {
            return C3712v2.m17073b(str, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17197a(String str, String str2, AbstractC3635n1 abstractC3635n1) {
            String str3 = str;
            return abstractC3635n1 == null ? str3 : abstractC3635n1.m17205e(str3, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public void mo17199a(String str) {
            AbstractC3635n1.this.mo17067a("serial_number", str);
        }
    }

    /* renamed from: com.bytedance.applog.n1$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3639d implements InterfaceC3643h<String[]> {
        public C3639d() {
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String[] mo17200a() {
            return AbstractC3635n1.this.mo17064c("sim_serial_number");
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: b */
        public boolean mo17196b(String[] strArr) {
            String[] strArr2 = strArr;
            return strArr2 != null && strArr2.length > 0;
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public boolean mo17198a(String[] strArr, String[] strArr2) {
            String[] strArr3 = strArr;
            String[] strArr4 = strArr2;
            if (strArr3 == strArr4) {
                return true;
            }
            if (strArr3 != null && strArr4 != null && strArr3.length == strArr4.length) {
                for (String str : strArr3) {
                    boolean z = false;
                    for (String str2 : strArr4) {
                        z = C3712v2.m17073b(str2, str) || z;
                    }
                    if (z) {
                    }
                }
                return true;
            }
            return false;
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String[] mo17197a(String[] strArr, String[] strArr2, AbstractC3635n1 abstractC3635n1) {
            String[] strArr3 = strArr;
            return abstractC3635n1 == null ? strArr3 : abstractC3635n1.m17209a(strArr3, strArr2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public void mo17199a(String[] strArr) {
            AbstractC3635n1.this.mo17066a("sim_serial_number", strArr);
        }
    }

    /* renamed from: com.bytedance.applog.n1$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3640e implements InterfaceC3643h<String> {
        public C3640e() {
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17200a() {
            return AbstractC3635n1.this.mo17065b("udid");
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: b */
        public boolean mo17196b(String str) {
            return C3712v2.m17078a(str);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public boolean mo17198a(String str, String str2) {
            return C3712v2.m17073b(str, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17197a(String str, String str2, AbstractC3635n1 abstractC3635n1) {
            String str3 = str;
            return abstractC3635n1 == null ? str3 : abstractC3635n1.m17204f(str3, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public void mo17199a(String str) {
            AbstractC3635n1.this.mo17067a("udid", str);
        }
    }

    /* renamed from: com.bytedance.applog.n1$f */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3641f implements InterfaceC3643h<String> {
        public C3641f() {
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public boolean mo17196b(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    return C3710v0.m17094a(new JSONArray(str));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17200a() {
            return AbstractC3635n1.this.mo17065b("udid_list");
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public boolean mo17198a(String str, String str2) {
            return (C3710v0.m17089b(str) && C3710v0.m17089b(str2)) || (str != null && str.equals(str2));
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17197a(String str, String str2, AbstractC3635n1 abstractC3635n1) {
            String str3 = str;
            return abstractC3635n1 == null ? str3 : abstractC3635n1.m17203g(str3, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public void mo17199a(String str) {
            AbstractC3635n1.this.mo17067a("udid_list", str);
        }
    }

    /* renamed from: com.bytedance.applog.n1$g */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3642g implements InterfaceC3643h<String> {
        public C3642g() {
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17200a() {
            return AbstractC3635n1.this.mo17065b("device_id");
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: b */
        public boolean mo17196b(String str) {
            return !TextUtils.isEmpty(str);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public boolean mo17198a(String str, String str2) {
            return C3712v2.m17073b(str, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public String mo17197a(String str, String str2, AbstractC3635n1 abstractC3635n1) {
            String str3 = str;
            return abstractC3635n1 == null ? str3 : abstractC3635n1.m17207c(str3, str2);
        }

        @Override // com.bytedance.applog.AbstractC3635n1.InterfaceC3643h
        /* renamed from: a */
        public void mo17199a(String str) {
            AbstractC3635n1.this.mo17067a("device_id", str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.n1$h */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3643h<L> {
        /* renamed from: a */
        L mo17200a();

        /* renamed from: a */
        L mo17197a(L l, L l2, AbstractC3635n1 abstractC3635n1);

        /* renamed from: a */
        void mo17199a(L l);

        /* renamed from: a */
        boolean mo17198a(L l, L l2);

        /* renamed from: b */
        boolean mo17196b(L l);
    }

    /* renamed from: a */
    public void mo17068a(String str) {
        AbstractC3635n1 abstractC3635n1 = this.f8607a;
        if (abstractC3635n1 != null) {
            abstractC3635n1.mo17068a(str);
        }
    }

    /* renamed from: a */
    public abstract void mo17067a(String str, String str2);

    /* renamed from: a */
    public abstract void mo17066a(String str, String[] strArr);

    /* renamed from: a */
    public String[] m17209a(String[] strArr, String[] strArr2) {
        return (String[]) m17210a(strArr, strArr2, new C3639d());
    }

    /* renamed from: b */
    public abstract String mo17065b(String str);

    /* renamed from: b */
    public String m17208b(String str, String str2) {
        return (String) m17210a(str, str2, new C3637b());
    }

    /* renamed from: c */
    public String m17207c(String str, String str2) {
        return (String) m17210a(str, str2, new C3642g());
    }

    /* renamed from: c */
    public abstract String[] mo17064c(String str);

    /* renamed from: d */
    public String m17206d(String str, String str2) {
        return (String) m17210a(str, str2, new C3636a());
    }

    /* renamed from: e */
    public String m17205e(String str, String str2) {
        return (String) m17210a(str, str2, new C3638c());
    }

    /* renamed from: f */
    public String m17204f(String str, String str2) {
        return (String) m17210a(str, str2, new C3640e());
    }

    /* renamed from: g */
    public String m17203g(String str, String str2) {
        return (String) m17210a(str, str2, new C3641f());
    }

    /* renamed from: a */
    public final <T> T m17210a(T t, T t2, InterfaceC3643h<T> interfaceC3643h) {
        if (interfaceC3643h != null) {
            AbstractC3635n1 abstractC3635n1 = this.f8607a;
            T mo17200a = interfaceC3643h.mo17200a();
            boolean mo17196b = interfaceC3643h.mo17196b(t);
            boolean mo17196b2 = interfaceC3643h.mo17196b(mo17200a);
            if (!mo17196b && mo17196b2) {
                t = mo17200a;
            }
            if (abstractC3635n1 != null) {
                T mo17197a = interfaceC3643h.mo17197a(t, t2, abstractC3635n1);
                if (!interfaceC3643h.mo17198a(mo17197a, mo17200a)) {
                    interfaceC3643h.mo17199a(mo17197a);
                }
                return mo17197a;
            }
            boolean z = false;
            if (!mo17196b && !mo17196b2) {
                z = true;
                t = t2;
            }
            if ((z && interfaceC3643h.mo17196b(t)) || (mo17196b && !interfaceC3643h.mo17198a(t, mo17200a))) {
                interfaceC3643h.mo17199a(t);
            }
            return t;
        }
        throw new IllegalArgumentException("agent == null");
    }

    /* renamed from: a */
    public void m17211a(Handler handler) {
        AbstractC3635n1 abstractC3635n1 = this.f8607a;
        if (abstractC3635n1 != null) {
            abstractC3635n1.m17211a(handler);
        }
        this.f8608b = handler;
    }
}
