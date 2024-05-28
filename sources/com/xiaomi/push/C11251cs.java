package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.cs */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11251cs {
    /* renamed from: a */
    public static int m4467a(InterfaceC11442hq interfaceC11442hq, EnumC11404gf enumC11404gf) {
        switch (enumC11404gf) {
            case Registration:
            case UnRegistration:
            case Subscription:
            case UnSubscription:
            case SendMessage:
            case AckMessage:
            case SetConfig:
            case ReportFeedback:
            case MultiConnectionBroadcast:
            case MultiConnectionResult:
                return C11303ds.m4132a(enumC11404gf.m3684a());
            case Notification:
                int m4132a = C11303ds.m4132a(enumC11404gf.m3684a());
                if (interfaceC11442hq != null) {
                    try {
                        if (interfaceC11442hq instanceof C11421gw) {
                            String str = ((C11421gw) interfaceC11442hq).f22907d;
                            if (!TextUtils.isEmpty(str) && C11303ds.m4120a(C11303ds.m4118a(str)) != -1) {
                                m4132a = C11303ds.m4120a(C11303ds.m4118a(str));
                            }
                            return m4132a;
                        } else if (interfaceC11442hq instanceof C11430he) {
                            String str2 = ((C11430he) interfaceC11442hq).f23015d;
                            if (!TextUtils.isEmpty(str2)) {
                                if (C11303ds.m4120a(C11303ds.m4118a(str2)) != -1) {
                                    m4132a = C11303ds.m4120a(C11303ds.m4118a(str2));
                                }
                                if (EnumC11414gp.UploadTinyData.equals(C11303ds.m4118a(str2))) {
                                    return -1;
                                }
                                return m4132a;
                            }
                        }
                    } catch (Exception unused) {
                        AbstractC11049b.m5268d("PERF_ERROR : parse Notification type error");
                        return m4132a;
                    }
                }
                return m4132a;
            case Command:
                int m4132a2 = C11303ds.m4132a(enumC11404gf.m3684a());
                if (interfaceC11442hq != null) {
                    try {
                        if (interfaceC11442hq instanceof C11426ha) {
                            String m3401b = ((C11426ha) interfaceC11442hq).m3401b();
                            if (!TextUtils.isEmpty(m3401b) && EnumC11317ed.m4048a(m3401b) != -1) {
                                m4132a2 = EnumC11317ed.m4048a(m3401b);
                            }
                            return m4132a2;
                        } else if (interfaceC11442hq instanceof C11424gz) {
                            String m3434a = ((C11424gz) interfaceC11442hq).m3434a();
                            if (!TextUtils.isEmpty(m3434a) && EnumC11317ed.m4048a(m3434a) != -1) {
                                return EnumC11317ed.m4048a(m3434a);
                            }
                        }
                    } catch (Exception unused2) {
                        AbstractC11049b.m5268d("PERF_ERROR : parse Command type error");
                        return m4132a2;
                    }
                }
                return m4132a2;
            default:
                return -1;
        }
    }

    /* renamed from: a */
    public static int m4469a(Context context, int i) {
        int m3738a = C11392fz.m3738a(context);
        if (-1 == m3738a) {
            return -1;
        }
        return (i * (m3738a == 0 ? 13 : 11)) / 10;
    }

    /* renamed from: a */
    public static int m4468a(EnumC11404gf enumC11404gf) {
        return C11303ds.m4132a(enumC11404gf.m3684a());
    }

    /* renamed from: a */
    public static void m4463a(String str, Context context, byte[] bArr) {
        if (context == null || bArr == null || bArr.length <= 0) {
            return;
        }
        C11427hb c11427hb = new C11427hb();
        try {
            C11441hp.m3084a(c11427hb, bArr);
            m4465a(str, context, c11427hb, bArr.length);
        } catch (C11448hu unused) {
            AbstractC11049b.m5282a("fail to convert bytes to container");
        }
    }

    /* renamed from: a */
    public static void m4465a(String str, Context context, C11427hb c11427hb, int i) {
        EnumC11404gf m3389a;
        if (context == null || c11427hb == null || (m3389a = c11427hb.m3389a()) == null) {
            return;
        }
        int m4468a = m4468a(m3389a);
        if (i <= 0) {
            byte[] m3085a = C11441hp.m3085a(c11427hb);
            i = m3085a != null ? m3085a.length : 0;
        }
        m4466a(str, context, m4468a, i);
    }

    /* renamed from: a */
    public static void m4466a(String str, Context context, int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        int m4469a = m4469a(context, i2);
        if (i != C11303ds.m4120a(EnumC11414gp.UploadTinyData)) {
            C11305dt.m4117a(context.getApplicationContext()).m4115a(str, i, 1L, m4469a);
        }
    }

    /* renamed from: a */
    public static void m4464a(String str, Context context, InterfaceC11442hq interfaceC11442hq, EnumC11404gf enumC11404gf, int i) {
        m4466a(str, context, m4467a(interfaceC11442hq, enumC11404gf), i);
    }
}
