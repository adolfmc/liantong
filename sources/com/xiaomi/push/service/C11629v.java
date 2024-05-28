package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11430he;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.v */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11629v {

    /* renamed from: a */
    private static InterfaceC11630a f23772a;

    /* renamed from: a */
    private static InterfaceC11631b f23773a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.v$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11630a {
        /* renamed from: a */
        Map<String, String> m2366a(Context context, C11427hb c11427hb);

        /* renamed from: a */
        void m2365a(Context context, C11427hb c11427hb);

        /* renamed from: a */
        void m2364a(Context context, C11427hb c11427hb, C11430he c11430he);

        /* renamed from: a */
        boolean m2363a(Context context, C11427hb c11427hb, boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.v$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11631b {
        /* renamed from: a */
        void m2362a(C11427hb c11427hb);

        /* renamed from: a */
        void m2360a(String str);

        /* renamed from: a */
        boolean m2361a(C11427hb c11427hb);
    }

    /* renamed from: a */
    public static void m2371a(Context context, C11427hb c11427hb, C11430he c11430he) {
        InterfaceC11630a interfaceC11630a = f23772a;
        if (interfaceC11630a == null) {
            AbstractC11049b.m5268d("The Listener of EventProcessor must be set. Please check extension plugin initialization.");
        } else {
            interfaceC11630a.m2364a(context, c11427hb, c11430he);
        }
    }

    /* renamed from: a */
    public static boolean m2370a(Context context, C11427hb c11427hb, boolean z) {
        InterfaceC11630a interfaceC11630a = f23772a;
        if (interfaceC11630a == null || c11427hb == null) {
            AbstractC11049b.m5282a("pepa judement listener or container is null");
            return false;
        }
        return interfaceC11630a.m2363a(context, c11427hb, z);
    }

    /* renamed from: a */
    public static void m2372a(Context context, C11427hb c11427hb) {
        InterfaceC11630a interfaceC11630a = f23772a;
        if (interfaceC11630a == null || c11427hb == null) {
            AbstractC11049b.m5282a("handle msg wrong");
        } else {
            interfaceC11630a.m2365a(context, c11427hb);
        }
    }

    /* renamed from: a */
    public static Map<String, String> m2373a(Context context, C11427hb c11427hb) {
        InterfaceC11630a interfaceC11630a = f23772a;
        if (interfaceC11630a == null || c11427hb == null) {
            AbstractC11049b.m5282a("pepa listener or container is null");
            return null;
        }
        return interfaceC11630a.m2366a(context, c11427hb);
    }

    /* renamed from: a */
    public static boolean m2368a(C11427hb c11427hb) {
        InterfaceC11631b interfaceC11631b = f23773a;
        if (interfaceC11631b == null || c11427hb == null) {
            AbstractC11049b.m5282a("pepa handleReceiveMessage is null");
            return false;
        }
        return interfaceC11631b.m2361a(c11427hb);
    }

    /* renamed from: a */
    public static void m2369a(C11427hb c11427hb) {
        InterfaceC11631b interfaceC11631b = f23773a;
        if (interfaceC11631b == null || c11427hb == null) {
            AbstractC11049b.m5282a("pepa clearMessage is null");
        } else {
            interfaceC11631b.m2362a(c11427hb);
        }
    }

    /* renamed from: a */
    public static void m2367a(String str) {
        InterfaceC11631b interfaceC11631b = f23773a;
        if (interfaceC11631b == null || str == null) {
            AbstractC11049b.m5282a("pepa clearMessage is null");
        } else {
            interfaceC11631b.m2360a(str);
        }
    }
}
