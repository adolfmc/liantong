package com.mob.commons.p229a;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5855l;
import com.mob.commons.C5857m;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.mob.commons.a.i */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5724i extends AbstractRunnableC5704c {
    public C5724i() {
        super(C5855l.m12238a("003Ohjfmhj"), 0L, C5855l.m12238a("006%hjfmhjggXfl"), 2592000L);
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        C6152DH.requester(MobSDK.getContext()).getSA().request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.i.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                boolean z;
                ArrayList<HashMap<String, String>> sa = dHResponse.getSA();
                if (sa.isEmpty()) {
                    return;
                }
                long m12634b = C5741aa.m12650a().m12634b(C5741aa.f14137d, 0L);
                long currentTimeMillis = System.currentTimeMillis();
                boolean z2 = currentTimeMillis - (C5724i.this.m12750j() * 1000) >= m12634b;
                if (!z2) {
                    ArrayList<HashMap<String, String>> readArrayListFromFile = ResHelper.readArrayListFromFile(C5857m.f14433e, true);
                    Iterator<HashMap<String, String>> it = sa.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String str = it.next().get(C5855l.m12238a("003lRfngg"));
                        if (!TextUtils.isEmpty(str)) {
                            Iterator<HashMap<String, String>> it2 = readArrayListFromFile.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    z = false;
                                    break;
                                } else if (str.equals(it2.next().get(C5855l.m12238a("003lFfngg")))) {
                                    z = true;
                                    break;
                                }
                            }
                            if (!z) {
                                z2 = true;
                                break;
                            }
                        }
                    }
                }
                if (z2) {
                    C5724i.this.m12772a(0L, "SALMT", sa);
                    ResHelper.saveArrayListToFile(sa, C5857m.f14433e, true);
                    C5741aa.m12650a().m12643a(C5741aa.f14137d, currentTimeMillis);
                }
            }
        });
    }
}
