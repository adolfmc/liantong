package com.mob.commons.p230b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.mob.commons.C5869r;
import com.mob.commons.p230b.AbstractC5764e;

/* renamed from: com.mob.commons.b.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5771g extends AbstractC5764e {
    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: c */
    protected long mo12506c() {
        return 3000L;
    }

    public C5771g(Context context) {
        super(context);
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    protected Intent mo12492a() {
        Intent intent = new Intent();
        intent.setClassName(C5869r.m12200a("023c.eddffdgcdgdifddc-fVdddiDcf7didcfhKf6djdddi1cf"), C5869r.m12200a("039c>eddffdgcdgdifddcBfIdddiJcf7didcfhMf:djdddiEcfTfdfkQfUdddi_cf*didcej0f^djdddi<cf"));
        return intent;
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    public AbstractC5764e.C5767b mo12491a(IBinder iBinder) {
        String m12200a = C5869r.m12200a("042cWeddffdgcdgdifddcYf5dddi=cf$didcfhYf<djdddi-cf]fdeifkWf:dddi<cf4didcei^eif:djef-dcf");
        AbstractC5764e.C5767b c5767b = new AbstractC5764e.C5767b();
        c5767b.f14234b = m12520a(C5869r.m12200a("004Aed^dPdidc"), iBinder, m12200a, 1, new String[0]);
        c5767b.f14233a = m12521a(C5869r.m12200a("009MfhdgOjjZeddjKifJdc"), iBinder, m12200a, 3) != 0;
        return c5767b;
    }
}
