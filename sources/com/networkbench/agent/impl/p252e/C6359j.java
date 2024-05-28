package com.networkbench.agent.impl.p252e;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6359j {

    /* renamed from: b */
    private static C6359j f16007b;

    /* renamed from: d */
    private static final InterfaceC6393e f16008d = C6394f.m10150a();

    /* renamed from: a */
    private WindowManager f16009a;

    /* renamed from: c */
    private Context f16010c;

    /* renamed from: a */
    public static C6359j m10299a(Context context) {
        if (f16007b == null) {
            f16007b = new C6359j(context);
        }
        return f16007b;
    }

    private C6359j(Context context) {
        this.f16010c = context;
        this.f16009a = (WindowManager) this.f16010c.getSystemService("window");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m10297a(View view, WindowManager.LayoutParams layoutParams) {
        try {
            this.f16009a.addView(view, layoutParams);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m10298a(View view) {
        try {
            this.f16009a.removeView(view);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public boolean m10296b(View view, WindowManager.LayoutParams layoutParams) {
        try {
            this.f16009a.updateViewLayout(view, layoutParams);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
