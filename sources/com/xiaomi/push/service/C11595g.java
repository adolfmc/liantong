package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11479r;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11595g {

    /* renamed from: a */
    private static InterfaceC11596a f23682a;

    /* renamed from: a */
    private static InterfaceC11597b f23683a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.g$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11596a {
        /* renamed from: a */
        boolean m2539a(C11430he c11430he);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.g$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11597b {
    }

    /* renamed from: a */
    public static void m2540a(InterfaceC11597b interfaceC11597b) {
        f23683a = interfaceC11597b;
    }

    /* renamed from: a */
    public static boolean m2541a(C11430he c11430he) {
        if (f23682a == null || c11430he == null) {
            AbstractC11049b.m5282a("rc params is null, not cpra");
            return false;
        } else if (!C11469j.m2972a(C11479r.m2934a())) {
            AbstractC11049b.m5282a("rc app not permission to cpra");
            return false;
        } else {
            return f23682a.m2539a(c11430he);
        }
    }
}
