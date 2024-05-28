package com.xiaomi.mipush.sdk;

import com.xiaomi.push.EnumC11409gk;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.mipush.sdk.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11097g {

    /* renamed from: a */
    private static HashMap<EnumC11090d, C11099a> f21385a = new HashMap<>();

    static {
        m5075a(EnumC11090d.ASSEMBLE_PUSH_HUAWEI, new C11099a("com.xiaomi.assemble.control.HmsPushManager", "newInstance"));
        m5075a(EnumC11090d.ASSEMBLE_PUSH_FCM, new C11099a("com.xiaomi.assemble.control.FCMPushManager", "newInstance"));
        m5075a(EnumC11090d.ASSEMBLE_PUSH_COS, new C11099a("com.xiaomi.assemble.control.COSPushManager", "newInstance"));
        m5075a(EnumC11090d.ASSEMBLE_PUSH_FTOS, new C11099a("com.xiaomi.assemble.control.FTOSPushManager", "newInstance"));
    }

    /* renamed from: a */
    private static void m5075a(EnumC11090d enumC11090d, C11099a c11099a) {
        if (c11099a != null) {
            f21385a.put(enumC11090d, c11099a);
        }
    }

    /* renamed from: a */
    public static C11099a m5078a(EnumC11090d enumC11090d) {
        return f21385a.get(enumC11090d);
    }

    /* renamed from: a */
    public static EnumC11409gk m5076a(EnumC11090d enumC11090d) {
        return EnumC11409gk.AggregatePushSwitch;
    }

    /* renamed from: a */
    public static EnumC11125v m5077a(EnumC11090d enumC11090d) {
        switch (enumC11090d) {
            case ASSEMBLE_PUSH_HUAWEI:
                return EnumC11125v.UPLOAD_HUAWEI_TOKEN;
            case ASSEMBLE_PUSH_FCM:
                return EnumC11125v.UPLOAD_FCM_TOKEN;
            case ASSEMBLE_PUSH_COS:
                return EnumC11125v.UPLOAD_COS_TOKEN;
            case ASSEMBLE_PUSH_FTOS:
                return EnumC11125v.UPLOAD_FTOS_TOKEN;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.mipush.sdk.g$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11099a {

        /* renamed from: a */
        public String f21387a;

        /* renamed from: b */
        public String f21388b;

        public C11099a(String str, String str2) {
            this.f21387a = str;
            this.f21388b = str2;
        }
    }
}
