package com.mob.commons.p230b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import com.mob.commons.C5857m;
import com.mob.commons.p230b.AbstractC5764e;

/* renamed from: com.mob.commons.b.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5763d extends AbstractC5764e {
    public C5763d(Context context) {
        super(context);
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    protected Intent mo12492a() {
        Intent intent = new Intent(C5857m.m12226a("036aKcbbddbbecbbabgdfdbcb;hdcKbaQdEbbbg+ad5dbejeieecfcgdichbfcheeefegcgehee"));
        intent.setPackage(C5857m.m12226a("015aFcbbddbWfWbe^b>dd'd bgdb1fKddbgba"));
        return intent;
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    public AbstractC5764e.C5767b mo12491a(IBinder iBinder) {
        String m12226a = C5857m.m12226a("053aHcbbddbbecbbabgdfdbcbGhdcUba8d8bbbg:ad*dbKb,bgba!e!dbej;hdc!di8d,bbbgSad cgba,dcgZbgcdbg2dKbhchXdBbhbbbg3ad");
        AbstractC5764e.C5767b c5767b = new AbstractC5764e.C5767b();
        c5767b.f14234b = m12520a(C5857m.m12226a("004=cb=bVbgba"), iBinder, m12226a, 1, new String[0]);
        m12521a(C5857m.m12226a("024Ybgdfdabgbdbg_gFcjbacibhNbaObjbg8c'cceeQcbFdcYed_ba"), iBinder, m12226a, 2);
        c5767b.f14233a = !TextUtils.isEmpty(c5767b.f14234b);
        return c5767b;
    }
}
