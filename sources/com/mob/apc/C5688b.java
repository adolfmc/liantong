package com.mob.apc;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.mob.apc.p228a.C5682c;
import com.mob.apc.p228a.C5687f;
import com.mob.tools.utils.AbstractC6201c;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.apc.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5688b {

    /* renamed from: a */
    public static volatile boolean f14033a;

    /* renamed from: b */
    private static Context f14034b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.apc.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5689a {
        /* renamed from: a */
        HashMap<String, Object> mo11921a(int i, String str);

        /* renamed from: a */
        boolean mo11906a(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.apc.b$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5690b {
        /* renamed from: a */
        C5677a mo11904a(String str, C5677a c5677a, long j);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.apc.b$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5691c {
        /* renamed from: a */
        void mo11917a(Bundle bundle);
    }

    static {
        C5687f.m12837a().m12834b("MOBAPC : 2021.11.07", new Object[0]);
    }

    /* renamed from: a */
    public static void m12831a(Context context) {
        f14034b = context.getApplicationContext();
    }

    /* renamed from: a */
    public static Context m12833a() {
        return f14034b;
    }

    /* renamed from: a */
    public static void m12827a(String str, InterfaceC5690b interfaceC5690b) {
        f14033a = true;
        C5682c.m12850a().m12843a(str, interfaceC5690b);
    }

    /* renamed from: a */
    public static C5677a m12832a(int i, String str, String str2, C5677a c5677a, long j) throws Throwable {
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            C5687f.m12837a().m12834b("[sendMessage] not allow main thread to invoke", new Object[0]);
            throw new APCException("not allow main thread to invoke");
        }
        return C5682c.m12850a().m12849a(i, str, str2, c5677a, j);
    }

    /* renamed from: a */
    public static void m12828a(AbstractC6201c<List<String>> abstractC6201c) {
        C5682c.m12850a().m12844a(abstractC6201c);
    }

    /* renamed from: a */
    public static void m12829a(InterfaceC5691c interfaceC5691c) {
        C5682c.m12850a().m12845a(interfaceC5691c);
    }

    /* renamed from: a */
    public static void m12830a(InterfaceC5689a interfaceC5689a) {
        C5682c.m12850a().m12846a(interfaceC5689a);
    }
}
