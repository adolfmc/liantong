package com.mob.commons.p230b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.mob.commons.C5869r;
import com.mob.commons.p230b.AbstractC5764e;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C6152DH;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mob.commons.b.m */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5780m extends AbstractC5764e {
    public C5780m(Context context) {
        super(context);
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    protected Intent mo12492a() {
        m12490f();
        Intent intent = new Intent();
        intent.setClassName(C5869r.m12200a("012cUeddffddfdcdidcfddffh%d"), C5869r.m12200a("033cEeddffddfdcdidcfddffh.d+fdfh0f2djdddiMcf$fdhcfhDdUeidcejEfDdjdddi)cf"));
        intent.setAction(C5869r.m12200a("033c'eddffdfedg8eQfddffhNdTfd9dci)died,eNfdfedi)e+dcWiQedfdfh9f:djdddiDcf"));
        intent.putExtra(C5869r.m12200a("025cBeddffdfedg-e)fddffh2d,fdOjd5djQd0dffd?jDdleeIedPdfGf"), this.f14225b);
        return intent;
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: a */
    protected AbstractC5764e.C5767b mo12491a(IBinder iBinder) {
        AbstractC5764e.C5767b c5767b = new AbstractC5764e.C5767b();
        c5767b.f14233a = true;
        c5767b.f14234b = m12520a(C5869r.m12200a("0047edTd+didc"), iBinder, C5869r.m12200a("026cMeddffdfedgSeQfdPgCdifefdhcfhQd@eidcei-eifAdjef(dcf"), 3, new String[0]);
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        C6152DH.requester(this.f14224a).getMpfo(C5869r.m12200a("012c6eddffddfdcdidcfddffh9d"), 0).request(new C6152DH.DHResponder() { // from class: com.mob.commons.b.m.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                if (dHResponse.getMpfo(new int[0]) == null) {
                    linkedBlockingQueue.offer(false);
                } else {
                    linkedBlockingQueue.offer(true);
                }
            }
        });
        try {
            Boolean bool = (Boolean) linkedBlockingQueue.poll(120L, TimeUnit.MILLISECONDS);
            if (bool != null) {
                c5767b.f14233a = bool.booleanValue();
            }
        } catch (Throwable unused) {
        }
        return c5767b;
    }

    /* renamed from: f */
    private void m12490f() {
        try {
            Intent intent = new Intent();
            intent.setClassName(C5869r.m12200a("012c9eddffddfdcdidcfddffhTd"), C5869r.m12200a("033c+eddffddfdcdidcfddffh[dWfdfh@f1djdddi:cfNfdhcfh%dIicHg%ej8f?djdddi1cf"));
            intent.setAction(C5869r.m12200a("032cOeddffdfedgAeUfddffh'dHfd;dciRdied<e6fdfh*idMdjJiWfdfhBf%djdddi*cf"));
            intent.putExtra(C5869r.m12200a("025cReddffdfedg+e[fddffhPdAfd<jd]djOdVdffd1j!dlee(edYdf'f"), this.f14225b);
            intent.putExtra(C5869r.m12200a("026cHeddffdfedgXe4fddffh0dUfdYjdBdjQdSdffddjdg*eQdi7eZfhIfi"), true);
            if (this.f14224a.startService(intent) != null) {
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }
}
