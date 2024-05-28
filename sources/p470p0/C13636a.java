package p470p0;

import android.support.p083v4.app.FragmentActivity;
import java.util.Stack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: p0.a */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13636a {

    /* renamed from: a */
    public static Stack<FragmentActivity> f27477a = new Stack<>();

    /* renamed from: b */
    public static int f27478b = 0;

    /* renamed from: a */
    public static void m190a() {
        for (int size = f27477a.size() - 1; size >= 0; size--) {
            FragmentActivity fragmentActivity = f27477a.get(size);
            if (fragmentActivity != null) {
                fragmentActivity.finish();
            }
        }
        f27477a.clear();
    }
}
