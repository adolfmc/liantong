package com.bytedance.applog;

import android.view.View;
import java.util.Comparator;

/* renamed from: com.bytedance.applog.t0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3693t0 implements Comparator<View> {
    public C3693t0(C3701u0 c3701u0) {
    }

    @Override // java.util.Comparator
    public int compare(View view, View view2) {
        C3672q0 c3672q0 = (C3672q0) view.getTag();
        C3672q0 c3672q02 = (C3672q0) view2.getTag();
        int i = c3672q0.f8766A;
        int i2 = c3672q02.f8766A;
        return (i <= i2 && (i != i2 || c3672q0.f8773y * c3672q0.f8774z >= c3672q02.f8773y * c3672q02.f8774z)) ? 0 : -1;
    }
}
