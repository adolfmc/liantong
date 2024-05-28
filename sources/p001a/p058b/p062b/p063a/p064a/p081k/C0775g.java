package p001a.p058b.p062b.p063a.p064a.p081k;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.Random;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.b.b.a.a.k.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0775g {

    /* renamed from: a */
    public static final Random f2391a = new Random();

    /* renamed from: b */
    public static final InterfaceC3321a f2392b = C0749a.f2299a;

    /* renamed from: a */
    public static void m22231a(String str) {
        int length = str.length();
        int i = 2000;
        int i2 = 0;
        int i3 = 0;
        while (i2 < 100) {
            if (length <= i) {
                f2392b.mo17428D(str.substring(i3, length));
                return;
            }
            f2392b.mo17428D(str.substring(i3, i));
            i2++;
            i3 = i;
            i += 2000;
        }
    }
}
