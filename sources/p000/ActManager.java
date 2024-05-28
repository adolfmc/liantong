package p000;

import android.app.Activity;
import java.util.Stack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: s */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ActManager {

    /* renamed from: a */
    public static Stack<Activity> f27649a;

    /* renamed from: a */
    public static void m115a(Activity activity) {
        m114b();
        f27649a.add(activity);
    }

    /* renamed from: b */
    public static void m114b() {
        if (f27649a == null) {
            f27649a = new Stack<>();
        }
    }

    /* renamed from: a */
    public static void m116a() {
        int size = f27649a.size();
        for (int i = 0; i < size; i++) {
            if (f27649a.get(i) != null) {
                Activity activity = f27649a.get(i);
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        f27649a.clear();
    }
}
