package p001a.p058b.p062b.p063a.p064a.p081k;

import android.content.Context;
import android.os.Environment;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.File;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: a.b.b.a.a.k.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0769b {

    /* renamed from: a */
    public static final InterfaceC3321a f2376a = C0749a.f2299a;

    /* renamed from: b */
    public static String f2377b = null;

    /* renamed from: c */
    public static File f2378c = new File(Environment.getDataDirectory(), "uaq_installation");

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a.b.b.a.a.k.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC0770a {
        UNKNOWN,
        SMALL,
        NORMAL,
        LARGE,
        XLARGE
    }

    /* renamed from: a */
    public static EnumC0770a m22242a(Context context) {
        int i = context.getResources().getConfiguration().screenLayout & 15;
        return i != 1 ? i != 2 ? i != 3 ? i > 3 ? EnumC0770a.XLARGE : EnumC0770a.UNKNOWN : EnumC0770a.LARGE : EnumC0770a.NORMAL : EnumC0770a.SMALL;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0064 A[Catch: IOException -> 0x0039, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x0039, blocks: (B:8:0x0035, B:28:0x0064), top: B:80:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x012d A[Catch: IOException -> 0x0131, TRY_ENTER, TRY_LEAVE, TryCatch #3 {IOException -> 0x0131, blocks: (B:53:0x010e, B:68:0x012d), top: B:83:0x00e2 }] */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v34, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v35 */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.IOException, java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.baidu.uaq.agent.android.logging.a] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.io.BufferedWriter] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m22241b(android.content.Context r5) {
        /*
            Method dump skipped, instructions count: 339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p058b.p062b.p063a.p064a.p081k.C0769b.m22241b(android.content.Context):java.lang.String");
    }
}
