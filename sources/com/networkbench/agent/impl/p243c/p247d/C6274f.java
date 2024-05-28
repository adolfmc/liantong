package com.networkbench.agent.impl.p243c.p247d;

import android.os.MessageQueue;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6274f {

    /* renamed from: a */
    public static HashMap<String, C6272d> f15645a = new HashMap<>();

    /* renamed from: a */
    public static MessageQueue.IdleHandler m10696a(String str, InterfaceC6271c interfaceC6271c) {
        C6272d c6272d = new C6272d(str);
        c6272d.m10711a(interfaceC6271c);
        f15645a.put(str, c6272d);
        return c6272d;
    }

    /* renamed from: a */
    public static void m10697a(String str) {
        f15645a.remove(str);
    }

    /* renamed from: a */
    public static void m10698a(Class cls) {
        C6272d c6272d = f15645a.get(cls.getName());
        if (c6272d != null) {
            c6272d.m10709a(cls.getName());
        }
    }
}
