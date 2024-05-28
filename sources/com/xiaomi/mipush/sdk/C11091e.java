package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.EnumC11409gk;
import com.xiaomi.push.service.C11537ah;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.mipush.sdk.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11091e implements AbstractPushManager {

    /* renamed from: a */
    private static volatile C11091e f21374a;

    /* renamed from: a */
    private Context f21375a;

    /* renamed from: a */
    private PushConfiguration f21376a;

    /* renamed from: a */
    private boolean f21378a = false;

    /* renamed from: a */
    private Map<EnumC11090d, AbstractPushManager> f21377a = new HashMap();

    private C11091e(Context context) {
        this.f21375a = context.getApplicationContext();
    }

    /* renamed from: a */
    public static C11091e m5114a(Context context) {
        if (f21374a == null) {
            synchronized (C11091e.class) {
                if (f21374a == null) {
                    f21374a = new C11091e(context);
                }
            }
        }
        return f21374a;
    }

    /* renamed from: a */
    public void m5113a(PushConfiguration pushConfiguration) {
        this.f21376a = pushConfiguration;
        this.f21378a = C11537ah.m2715a(this.f21375a).m2716a(EnumC11409gk.AggregatePushSwitch.m3637a(), true);
        if (this.f21376a.getOpenHmsPush() || this.f21376a.getOpenFCMPush() || this.f21376a.getOpenCOSPush() || this.f21376a.getOpenFTOSPush()) {
            C11537ah.m2715a(this.f21375a).m2711a(new C11537ah.AbstractRunnableC11538a(101, "assemblePush") { // from class: com.xiaomi.mipush.sdk.e.1
                @Override // com.xiaomi.push.service.C11537ah.AbstractRunnableC11538a
                public void onCallback() {
                    boolean m2716a = C11537ah.m2715a(C11091e.this.f21375a).m2716a(EnumC11409gk.AggregatePushSwitch.m3637a(), true);
                    if (C11091e.this.f21378a != m2716a) {
                        C11091e.this.f21378a = m2716a;
                        C11094f.m5085b(C11091e.this.f21375a);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void m5109a(EnumC11090d enumC11090d, AbstractPushManager abstractPushManager) {
        if (abstractPushManager != null) {
            if (this.f21377a.containsKey(enumC11090d)) {
                this.f21377a.remove(enumC11090d);
            }
            this.f21377a.put(enumC11090d, abstractPushManager);
        }
    }

    /* renamed from: a */
    public void m5111a(EnumC11090d enumC11090d) {
        this.f21377a.remove(enumC11090d);
    }

    /* renamed from: a */
    public boolean m5110a(EnumC11090d enumC11090d) {
        return this.f21377a.containsKey(enumC11090d);
    }

    /* renamed from: a */
    public AbstractPushManager m5112a(EnumC11090d enumC11090d) {
        return this.f21377a.get(enumC11090d);
    }

    @Override // com.xiaomi.mipush.sdk.AbstractPushManager
    public void register() {
        AbstractC11049b.m5282a("ASSEMBLE_PUSH : assemble push register");
        if (this.f21377a.size() <= 0) {
            m5115a();
        }
        if (this.f21377a.size() > 0) {
            for (AbstractPushManager abstractPushManager : this.f21377a.values()) {
                if (abstractPushManager != null) {
                    abstractPushManager.register();
                }
            }
            C11094f.m5102a(this.f21375a);
        }
    }

    @Override // com.xiaomi.mipush.sdk.AbstractPushManager
    public void unregister() {
        AbstractC11049b.m5282a("ASSEMBLE_PUSH : assemble push unregister");
        for (AbstractPushManager abstractPushManager : this.f21377a.values()) {
            if (abstractPushManager != null) {
                abstractPushManager.unregister();
            }
        }
        this.f21377a.clear();
    }

    /* renamed from: a */
    private void m5115a() {
        AbstractPushManager m5112a;
        AbstractPushManager m5112a2;
        AbstractPushManager m5112a3;
        AbstractPushManager m5112a4;
        PushConfiguration pushConfiguration = this.f21376a;
        if (pushConfiguration != null) {
            if (pushConfiguration.getOpenHmsPush()) {
                StringBuilder sb = new StringBuilder();
                sb.append("ASSEMBLE_PUSH : ");
                sb.append(" HW user switch : " + this.f21376a.getOpenHmsPush() + " HW online switch : " + C11094f.m5097a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_HUAWEI) + " HW isSupport : " + C11100h.m5072a(this.f21375a));
                AbstractC11049b.m5282a(sb.toString());
            }
            if (this.f21376a.getOpenHmsPush() && C11094f.m5097a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_HUAWEI) && C11100h.m5072a(this.f21375a)) {
                if (!m5110a(EnumC11090d.ASSEMBLE_PUSH_HUAWEI)) {
                    m5109a(EnumC11090d.ASSEMBLE_PUSH_HUAWEI, C11115s.m5033a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_HUAWEI));
                }
                AbstractC11049b.m5270c("hw manager add to list");
            } else if (m5110a(EnumC11090d.ASSEMBLE_PUSH_HUAWEI) && (m5112a = m5112a(EnumC11090d.ASSEMBLE_PUSH_HUAWEI)) != null) {
                m5111a(EnumC11090d.ASSEMBLE_PUSH_HUAWEI);
                m5112a.unregister();
            }
            if (this.f21376a.getOpenFCMPush()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("ASSEMBLE_PUSH : ");
                sb2.append(" FCM user switch : " + this.f21376a.getOpenFCMPush() + " FCM online switch : " + C11094f.m5097a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_FCM) + " FCM isSupport : " + C11100h.m5071b(this.f21375a));
                AbstractC11049b.m5282a(sb2.toString());
            }
            if (this.f21376a.getOpenFCMPush() && C11094f.m5097a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_FCM) && C11100h.m5071b(this.f21375a)) {
                if (!m5110a(EnumC11090d.ASSEMBLE_PUSH_FCM)) {
                    m5109a(EnumC11090d.ASSEMBLE_PUSH_FCM, C11115s.m5033a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_FCM));
                }
                AbstractC11049b.m5270c("fcm manager add to list");
            } else if (m5110a(EnumC11090d.ASSEMBLE_PUSH_FCM) && (m5112a2 = m5112a(EnumC11090d.ASSEMBLE_PUSH_FCM)) != null) {
                m5111a(EnumC11090d.ASSEMBLE_PUSH_FCM);
                m5112a2.unregister();
            }
            if (this.f21376a.getOpenCOSPush()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("ASSEMBLE_PUSH : ");
                sb3.append(" COS user switch : " + this.f21376a.getOpenCOSPush() + " COS online switch : " + C11094f.m5097a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_COS) + " COS isSupport : " + C11100h.m5070c(this.f21375a));
                AbstractC11049b.m5282a(sb3.toString());
            }
            if (this.f21376a.getOpenCOSPush() && C11094f.m5097a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_COS) && C11100h.m5070c(this.f21375a)) {
                m5109a(EnumC11090d.ASSEMBLE_PUSH_COS, C11115s.m5033a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_COS));
            } else if (m5110a(EnumC11090d.ASSEMBLE_PUSH_COS) && (m5112a3 = m5112a(EnumC11090d.ASSEMBLE_PUSH_COS)) != null) {
                m5111a(EnumC11090d.ASSEMBLE_PUSH_COS);
                m5112a3.unregister();
            }
            if (this.f21376a.getOpenFTOSPush() && C11094f.m5097a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_FTOS) && C11100h.m5069d(this.f21375a)) {
                m5109a(EnumC11090d.ASSEMBLE_PUSH_FTOS, C11115s.m5033a(this.f21375a, EnumC11090d.ASSEMBLE_PUSH_FTOS));
            } else if (!m5110a(EnumC11090d.ASSEMBLE_PUSH_FTOS) || (m5112a4 = m5112a(EnumC11090d.ASSEMBLE_PUSH_FTOS)) == null) {
            } else {
                m5111a(EnumC11090d.ASSEMBLE_PUSH_FTOS);
                m5112a4.unregister();
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: b */
    public boolean m5105b(EnumC11090d enumC11090d) {
        boolean z = false;
        switch (enumC11090d) {
            case ASSEMBLE_PUSH_HUAWEI:
                PushConfiguration pushConfiguration = this.f21376a;
                if (pushConfiguration != null) {
                    return pushConfiguration.getOpenHmsPush();
                }
                return false;
            case ASSEMBLE_PUSH_FCM:
                PushConfiguration pushConfiguration2 = this.f21376a;
                if (pushConfiguration2 != null) {
                    return pushConfiguration2.getOpenFCMPush();
                }
                return false;
            case ASSEMBLE_PUSH_COS:
                PushConfiguration pushConfiguration3 = this.f21376a;
                if (pushConfiguration3 != null) {
                    z = pushConfiguration3.getOpenCOSPush();
                    break;
                }
                break;
            case ASSEMBLE_PUSH_FTOS:
                break;
            default:
                return false;
        }
        PushConfiguration pushConfiguration4 = this.f21376a;
        return pushConfiguration4 != null ? pushConfiguration4.getOpenFTOSPush() : z;
    }
}
