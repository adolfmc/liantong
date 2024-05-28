package com.networkbench.agent.impl.p252e;

import android.view.View;
import android.view.WindowManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.u */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6374u {

    /* renamed from: a */
    private final View f16057a;

    /* renamed from: b */
    private final WindowManager.LayoutParams f16058b;

    /* renamed from: c */
    private final int f16059c;

    /* renamed from: d */
    private final int f16060d;

    public C6374u(View view, WindowManager.LayoutParams layoutParams) {
        this.f16057a = view;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.f16060d = iArr[0];
        this.f16059c = iArr[1];
        this.f16058b = layoutParams;
    }

    /* renamed from: a */
    public View m10250a() {
        return this.f16057a;
    }

    /* renamed from: b */
    public int m10249b() {
        return this.f16059c;
    }

    /* renamed from: c */
    public int m10248c() {
        return this.f16060d;
    }

    /* renamed from: d */
    public WindowManager.LayoutParams m10247d() {
        return this.f16058b;
    }
}
