package com.mob.commons.p229a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5829d;
import com.mob.commons.C5857m;
import com.mob.commons.C5873u;
import com.mob.commons.CSCenter;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6212e;
import com.mob.tools.utils.ResHelper;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.mob.commons.a.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5720h extends AbstractRunnableC5704c {
    public C5720h() {
        super(null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: e */
    public boolean mo12709e() {
        return m12708l() && m12749k();
    }

    /* renamed from: l */
    private boolean m12708l() {
        return C5747b.m12585a(C5731l.m12674a("003ehh"));
    }

    /* renamed from: m */
    private boolean m12707m() {
        return C5747b.m12585a(C5731l.m12674a("002Dej;f"));
    }

    /* renamed from: n */
    private boolean m12706n() {
        return C5747b.m12585a(C5731l.m12674a("002Teh-f"));
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        if (m12708l()) {
            final boolean z = true;
            final boolean z2 = m12707m() || m12706n();
            m12713a(z2);
            long currentTimeMillis = System.currentTimeMillis();
            long longValue = ((Long) m12764a(C5731l.m12674a("004e!gi5ke"), (String) 2592000L)).longValue() * 1000;
            long m12634b = C5741aa.m12650a().m12634b(C5741aa.f14136c, 0L);
            boolean m12187a = C5873u.m12187a(currentTimeMillis, m12634b);
            boolean z3 = this.f14066a != null && (this.f14066a instanceof Boolean) && ((Boolean) this.f14066a).booleanValue();
            if (currentTimeMillis - longValue < m12634b && m12187a) {
                z = false;
            }
            if (z || z3) {
                C6152DH.requester(MobSDK.getContext()).getIAForce(false, z3).request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.h.1
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(C6152DH.DHResponse dHResponse) {
                        ArrayList<HashMap<String, String>> iAForce = dHResponse.getIAForce(new int[0]);
                        if (iAForce == null || iAForce.isEmpty()) {
                            return;
                        }
                        if (z) {
                            C5720h.this.m12716a(iAForce);
                        }
                        C5720h.this.m12714a(iAForce, z2);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12716a(ArrayList<HashMap<String, String>> arrayList) {
        m12772a(((Long) m12764a(C5731l.m12674a("004eOed>hg"), (String) 0L)).longValue(), "ALSAMT", arrayList);
        C5741aa.m12650a().m12643a(C5741aa.f14136c, System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12714a(ArrayList<HashMap<String, String>> arrayList, boolean z) {
        ArrayList<HashMap<String, String>> readArrayListFromFile = ResHelper.readArrayListFromFile(C5857m.f14432d, true);
        if (readArrayListFromFile.isEmpty()) {
            m12711b(arrayList);
        } else if (z) {
            m12715a(readArrayListFromFile, arrayList);
        }
    }

    /* renamed from: a */
    private void m12713a(boolean z) {
        if (CSCenter.getInstance().isAppListDataEnable()) {
            if (z) {
                C5722a.m12705a().m12702b();
            } else {
                C5722a.m12705a().m12701c();
            }
        }
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: b */
    protected void mo12712b() throws Throwable {
        long nextInt;
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
        calendar.add(5, 1);
        long timeInMillis2 = calendar.getTimeInMillis();
        String m12674a = C5731l.m12674a("005PghghCl+ghgh");
        int m11047b = C6212e.m11053a().m11047b(m12674a, ((Integer) C5747b.m12583a("slr", 0)).intValue());
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("LPT dbr " + m11047b, new Object[0]);
        SecureRandom secureRandom = new SecureRandom();
        if (m11047b >= 0) {
            calendar.setTime(C6212e.m11053a().m11051a(new Date(calendar.getTimeInMillis()), m12674a));
            int i = ((m11047b + 1) * 60000) / 2;
            nextInt = (calendar.getTimeInMillis() - timeInMillis) + i + secureRandom.nextInt(i);
        } else {
            nextInt = (timeInMillis2 - timeInMillis) + secureRandom.nextInt(120000);
        }
        m12774a((nextInt / 1000) + (nextInt % 1000 == 0 ? 0 : 1));
    }

    /* renamed from: b */
    private void m12711b(ArrayList<HashMap<String, String>> arrayList) {
        try {
            ResHelper.saveArrayListToFile(arrayList, C5857m.f14432d, true);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            try {
                ResHelper.saveArrayListToFile(arrayList, C5857m.f14432d, true);
            } catch (Throwable unused) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    /* renamed from: a */
    private boolean m12715a(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2) {
        boolean z;
        ArrayList<HashMap<String, String>> m12710b = m12710b(arrayList2, arrayList);
        if (m12710b.isEmpty() || !m12707m()) {
            z = false;
        } else {
            m12772a(0L, "ALSIMT", m12710b);
            z = true;
        }
        ArrayList<HashMap<String, String>> m12710b2 = m12710b(arrayList, arrayList2);
        if (!m12710b2.isEmpty() && m12706n()) {
            m12772a(0L, "ALSUMT", m12710b2);
            z = true;
        }
        if (z) {
            m12711b(arrayList2);
        }
        return true;
    }

    /* renamed from: b */
    private ArrayList<HashMap<String, String>> m12710b(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2) {
        ArrayList<HashMap<String, String>> arrayList3 = new ArrayList<>();
        Iterator<HashMap<String, String>> it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap<String, String> next = it.next();
            String str = next.get(C5731l.m12674a("003k5emff"));
            if (!TextUtils.isEmpty(str)) {
                boolean z = false;
                Iterator<HashMap<String, String>> it2 = arrayList2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (str.equals(it2.next().get(C5731l.m12674a("003k[emff")))) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    arrayList3.add(next);
                }
            }
        }
        return arrayList3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.a.h$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5722a {

        /* renamed from: a */
        private static final C5722a f14106a = new C5722a();

        /* renamed from: b */
        private static final String[] f14107b = {C5731l.m12674a("035ef'edekfeejedgeej=fjgfjSge6edj]ejfeIf gehlfmhkjdfmjehheifmglglhhgl"), C5731l.m12674a("037ef-edekfeejedgeej4fjgfj2ge)edj+ejfeXf-gehlfmhkjdfmjehheihkgkfmfijehhgl"), C5731l.m12674a("037efWedekfeejedgeej7fjgfj3ge edj[ejfe4f%gehlfmhkjdfmjehheihihhidhmhjhhgl"), C5731l.m12674a("038efVedekfeejedgeejHfjgfj;ge$edjLejfeEf3gehlfmhkjdfmjehheihihhhlgdfmhkhhgl")};

        /* renamed from: c */
        private volatile boolean f14108c = false;

        /* renamed from: d */
        private final BroadcastReceiver f14109d = new BroadcastReceiver() { // from class: com.mob.commons.a.h.a.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                C5722a.f14106a.m12704a(context, intent);
            }
        };

        /* renamed from: a */
        public static C5722a m12705a() {
            return f14106a;
        }

        private C5722a() {
        }

        /* renamed from: b */
        public synchronized void m12702b() {
            if (C5829d.m12330b()) {
                if (this.f14108c) {
                    return;
                }
                this.f14108c = true;
                IntentFilter intentFilter = new IntentFilter();
                for (int i = 0; i < f14107b.length; i++) {
                    intentFilter.addAction(f14107b[i]);
                }
                intentFilter.addDataScheme(C5731l.m12674a("007ked!em,eQff0g"));
                C5873u.m12185a(this.f14109d, intentFilter);
            }
        }

        /* renamed from: c */
        public synchronized void m12701c() {
            if (C5829d.m12330b()) {
                if (!this.f14108c) {
                    return;
                }
                this.f14108c = false;
                C5873u.m12186a(this.f14109d);
            }
        }

        /* renamed from: a */
        public void m12704a(Context context, Intent intent) {
            String str = null;
            if (intent != null) {
                try {
                    str = intent.getAction();
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                    return;
                }
            }
            if (m12703a(str)) {
                C5747b.f14167b = true;
                C5731l.m12681a().m12678a(2L, C5720h.class, new Object[]{-1, true}, 1);
            }
        }

        /* renamed from: a */
        private boolean m12703a(String str) {
            for (String str2 : f14107b) {
                if (str2.equals(str)) {
                    return true;
                }
            }
            return false;
        }
    }
}
