package p470p0;

import android.content.SharedPreferences;

/* renamed from: p0.s */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13660s {

    /* renamed from: a */
    public SharedPreferences f27497a;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: p0.s$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class C13661a {

        /* renamed from: a */
        public static C13660s f27498a = new C13660s();
    }

    /* renamed from: a */
    public final void m167a(String str, String str2) {
        SharedPreferences.Editor edit = this.f27497a.edit();
        edit.putString(str, str2);
        edit.apply();
    }
}
