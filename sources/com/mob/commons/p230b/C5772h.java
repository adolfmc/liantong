package com.mob.commons.p230b;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.mob.commons.C5869r;
import com.mob.commons.p230b.AbstractC5764e;
import com.mob.tools.MobLog;

/* renamed from: com.mob.commons.b.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5772h extends AbstractC5764e {
    public C5772h(Context context) {
        super(context);
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: b */
    protected AbstractC5764e.C5767b mo12493b() {
        AbstractC5764e.C5767b c5767b = new AbstractC5764e.C5767b();
        c5767b.f14233a = m12502f();
        c5767b.f14234b = m12504a(C5869r.m12200a("007IeeOfi+gleleifk"), (String) null);
        return c5767b;
    }

    /* renamed from: a */
    private String m12504a(String str, String str2) {
        Bundle m12503b = m12503b(str, str2);
        if (m12505a(m12503b)) {
            return m12503b.getString(C5869r.m12200a("0026didc"));
        }
        if (m12503b != null) {
            return m12503b.getString(C5869r.m12200a("007Bdf;f3fhfh_dXeeLf"));
        }
        return null;
    }

    /* renamed from: f */
    private boolean m12502f() {
        Bundle m12503b = m12503b(C5869r.m12200a("009=difhejdgXjj:eddj9i"), null);
        if (m12505a(m12503b)) {
            return m12503b.getBoolean(C5869r.m12200a("009Vdifhfhdg$jj,eddj%i"), true);
        }
        return false;
    }

    /* renamed from: b */
    private Bundle m12503b(String str, String str2) {
        Bundle bundle = null;
        try {
            Uri parse = Uri.parse(C5869r.m12200a("036c@ed+eifeikllce!fd!eOdgfedi^dGfddidcQfeiPdi,i)dk;lUdidc;feiIdi[i'dk"));
            if (Build.VERSION.SDK_INT >= 17) {
                ContentProviderClient acquireUnstableContentProviderClient = this.f14224a.getContentResolver().acquireUnstableContentProviderClient(parse);
                bundle = acquireUnstableContentProviderClient.call(str, str2, null);
                if (acquireUnstableContentProviderClient != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        acquireUnstableContentProviderClient.close();
                    } else {
                        acquireUnstableContentProviderClient.release();
                    }
                }
            } else if (Build.VERSION.SDK_INT >= 11) {
                bundle = this.f14224a.getContentResolver().call(parse, str, str2, (Bundle) null);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return bundle;
    }

    /* renamed from: a */
    private boolean m12505a(Bundle bundle) {
        return bundle != null && bundle.getInt(C5869r.m12200a("004c1eddc]f"), -1) == 0;
    }
}
