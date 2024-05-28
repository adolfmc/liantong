package com.mob.commons.p230b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.mob.commons.C5855l;
import com.mob.commons.p230b.AbstractC5764e;
import com.mob.tools.MobLog;

/* renamed from: com.mob.commons.b.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5776j extends AbstractC5764e {
    public C5776j(Context context) {
        super(context);
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    protected Intent mo12492a() {
        Intent intent = new Intent();
        intent.setClassName(C5855l.m12238a("035e?gffhhfhj?f*fhhjfiUg1gghf*fg0feflgffkfehffe>h2fffkOehTfkfehj=hQflfffkJeh"), C5855l.m12238a("051eMgffhhfhj9fHfhhjfi<g,gghfMfg?feflgffkfehffeCh6fffk2eh!fkfehjGh-flfffk2ehDhfhmVhCfffk^eh:gkfeglWh7flfffkEeh"));
        return intent;
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    protected AbstractC5764e.C5767b mo12491a(IBinder iBinder) {
        boolean z;
        if (iBinder != null) {
            try {
                z = iBinder.queryLocalInterface(C5855l.m12238a("052e'gffhhfhj7f^fhhjfiJg!gghf8fgUfeflgffkfehffe9hZfffkVeh-fkfehj>h$flfffk?ehEhfgkhmLhRfffk3eh2gkfegl hVflfffkGeh")) != null;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                z = false;
            }
        } else {
            z = false;
        }
        AbstractC5764e.C5767b c5767b = new AbstractC5764e.C5767b();
        c5767b.f14233a = z;
        c5767b.f14234b = m12520a(C5855l.m12238a("004]gf2fUfkfe"), iBinder, C5855l.m12238a("052e^gffhhfhj@f0fhhjfi g6gghf,fg]feflgffkfehffeUh+fffkUehAfkfehjIh+flfffk[eh hfgkhmBhZfffk$eh!gkfegl@hXflfffkBeh"), 1, new String[0]);
        return c5767b;
    }
}
