package com.networkbench.agent.impl.p252e;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.n */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6365n {

    /* renamed from: b */
    private static final int f16035b = -591618;

    /* renamed from: a */
    private Context f16036a;

    public C6365n(Context context) {
        this.f16036a = context;
    }

    /* renamed from: a */
    public RelativeLayout m10279a() {
        RelativeLayout relativeLayout = new RelativeLayout(this.f16036a);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setPadding(0, 0, 0, 0);
        relativeLayout.setBackgroundColor(-591618);
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.networkbench.agent.impl.e.n.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        return relativeLayout;
    }
}
