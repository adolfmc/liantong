package com.mob.commons.p229a;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5869r;
import com.mob.commons.C5873u;
import com.mob.tools.MobLog;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6202d;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.mob.commons.a.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5710d extends AbstractRunnableC5704c {
    public C5710d() {
        super("l", 0L, C5869r.m12200a("004gNeeFdj"), 86400L);
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() throws Throwable {
        long m12634b = C5741aa.m12650a().m12634b("key_lgwst", 0L);
        if (C6152DH.SyncMtd.checkPermission(C5869r.m12200a("036deAdcdjeddidcfd2jfWdjdfdifhfhdiedZeNfdgjfjelehidggdhgeeifleidhejekelekgg")) && C6152DH.SyncMtd.checkPermission(C5869r.m12200a("036de(dcdjeddidcfdYjf[djdfdifhfhdied1eSfdelgjgjggejejdhgeeifleidhejekelekgg")) && System.currentTimeMillis() - m12634b >= 1800000) {
            C5873u.m12183a(new AbstractC6201c<ArrayList<HashMap<String, Object>>>() { // from class: com.mob.commons.a.d.1
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(ArrayList<HashMap<String, Object>> arrayList) {
                    C5741aa.m12650a().m12643a("key_lgwst", System.currentTimeMillis());
                    C5710d.this.m12739a(arrayList);
                }
            });
        } else {
            m12739a((ArrayList<HashMap<String, Object>>) null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12739a(ArrayList<HashMap<String, Object>> arrayList) {
        try {
            if (mo12709e()) {
                m12738a(arrayList, 2);
                m12738a(arrayList, 1);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
    }

    /* renamed from: a */
    private void m12738a(final ArrayList<HashMap<String, Object>> arrayList, final int i) {
        C6152DH.RequestBuilder mbcdi = C6152DH.requester(MobSDK.getContext()).getMcdi().getMbcdi();
        if (i == 1) {
            mbcdi.getPosComm(30, 0, true);
        } else {
            mbcdi.getPosComm(0, 15, true);
        }
        mbcdi.request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.d.2
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                HashMap<String, Object> b;
                if (dHResponse.getPosComm(new int[0]) == null || (b = C5710d.this.m12760b(dHResponse.getPosComm(new int[0]))) == null || b.isEmpty()) {
                    return;
                }
                String mcdi = dHResponse.getMcdi();
                String mbcdi2 = dHResponse.getMbcdi();
                if (!TextUtils.isEmpty(mbcdi2)) {
                    b.put("cbsmt", mbcdi2);
                }
                if (!TextUtils.isEmpty(mcdi)) {
                    b.put("cssmt", mcdi);
                }
                if (C5710d.this.m12754f()) {
                    b.put("pt", 1);
                } else {
                    b.put("pt", 2);
                }
                if (i == 1) {
                    C6202d.m11087a().m11080a(dHResponse.getPosComm(new int[0]));
                }
                b.put("lctpmt", Integer.valueOf(i));
                ArrayList arrayList2 = arrayList;
                if (arrayList2 != null && !arrayList2.isEmpty()) {
                    b.put("wilmt", arrayList);
                }
                C5710d.this.m12763a("LCMT", b);
            }
        });
    }
}
