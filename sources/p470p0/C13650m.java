package p470p0;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import java.util.TimerTask;

/* renamed from: p0.m */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13650m extends TimerTask {

    /* renamed from: a */
    public final /* synthetic */ Context f27493a;

    public C13650m(Context context) {
        this.f27493a = context;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        ((InputMethodManager) this.f27493a.getSystemService("input_method")).toggleSoftInput(0, 2);
    }
}
