package com.mob.commons.p229a;

import android.content.pm.ApplicationInfo;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5831e;
import com.mob.commons.C5847i;
import com.mob.commons.C5855l;
import com.mob.commons.C5871t;
import com.mob.commons.CSCenter;
import com.mob.commons.MobProduct;
import com.mob.tools.C6122c;
import com.mob.tools.MobLog;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.utils.C6152DH;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.mob.commons.a.m */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5732m extends AbstractRunnableC5704c {

    /* renamed from: c */
    private static final String f14122c = C5855l.m12238a("0165hegnglgmfjgiimheingnhmfjgmgkjeii");

    /* renamed from: d */
    private static final String f14123d = C5855l.m12238a("016Qhegnglgmfjhngngkheiihmfjhmgngmgn");

    public C5732m() {
        super(C5855l.m12238a("002li"), 0L, C5855l.m12238a("005li_gg,fl"), 86400L);
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        if (CSCenter.getInstance().isAppListDataEnable()) {
            try {
                Thread.sleep(((Long) m12764a(m12756d(), (String) 0L)).longValue() * 1000);
                HashMap<String, Object> hashMap = (HashMap) C5741aa.m12650a().m12627c(f14123d, null);
                if (hashMap != null && !hashMap.isEmpty() && m12662b(hashMap) != null) {
                    m12664a((HashMap<String, Object>) null);
                }
            } catch (Throwable unused) {
            }
            m12659m();
        }
    }

    /* renamed from: m */
    private void m12659m() {
        C6152DH.requester(MobSDK.getContext()).getDetailNetworkTypeForStatic().request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.m.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) throws Throwable {
                final List<String> list;
                HashMap<String, Object> hashMap = new HashMap<>();
                String m12196a = C5871t.m12196a();
                String m12319a = C5831e.m12319a((MobProduct) null);
                hashMap.put(C5855l.m12238a("006fll;fn*h1fm"), m12196a);
                hashMap.put(C5855l.m12238a("006flll$fngg"), C6152DH.SyncMtd.getPackageName());
                hashMap.put(C5855l.m12238a("006fll1ffDh0fl"), Integer.valueOf(C6152DH.SyncMtd.getAppVersion()));
                hashMap.put(C5855l.m12238a("004Nfefifkfe"), m12319a);
                hashMap.put(C5855l.m12238a("004lifk"), Integer.valueOf(C6152DH.SyncMtd.getPlatformCode()));
                hashMap.put(C5855l.m12238a("011ghk'hhgfflfnFk5fm4lh"), dHResponse.getDetailNetworkTypeForStatic());
                hashMap.put(C5855l.m12238a("009ifBhjHk(ikSli-gnRk"), Long.valueOf(C5741aa.m12650a().m12634b(C5732m.f14122c, 0L)));
                String encodeToString = Base64.encodeToString((m12196a + ":" + m12319a).getBytes("utf-8"), 2);
                hashMap.put(C5855l.m12238a("009if0hjMkXik[liWgkfe"), encodeToString);
                HashMap hashMap2 = (HashMap) C5732m.m12661b(hashMap, C5847i.m12275a().m12272a("gclg") + C5855l.m12238a("004nXff]li"));
                if (hashMap2 == null || hashMap2.size() == 0 || (list = (List) hashMap2.get(C5855l.m12238a("004lKfngghj"))) == null || list.size() <= 0) {
                    return;
                }
                C5741aa.m12650a().m12643a(C5732m.f14122c, System.currentTimeMillis());
                final ArrayList arrayList = new ArrayList();
                MobLog.getInstance().m11342d("[dhss] vpl", new Object[0]);
                C6152DH.RequestBuilder requester = C6152DH.requester(MobSDK.getContext());
                for (String str : list) {
                    requester.getMpfos(180000, str, 0);
                }
                requester.request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.m.1.1
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(C6152DH.DHResponse dHResponse2) {
                        int size = list.size();
                        for (int i = 0; i < size; i++) {
                            boolean z = true;
                            try {
                                Object mpfos = dHResponse2.getMpfos(i);
                                if (mpfos != null) {
                                    String str2 = (String) list.get(i);
                                    ApplicationInfo m11360a = C6122c.m11360a(mpfos, str2);
                                    HashMap hashMap3 = new HashMap();
                                    hashMap3.put(C5855l.m12238a("006flll!fngg"), str2);
                                    hashMap3.put(C5855l.m12238a("006fll+ff4h]fl"), C6122c.m11355c(mpfos, str2));
                                    if (m11360a != null) {
                                        boolean z2 = (m11360a.flags & 1) == 1;
                                        boolean z3 = (m11360a.flags & 128) != 0;
                                        String m12238a = C5855l.m12238a("005:fkhjhjfmhj");
                                        if (!z2 && !z3) {
                                            z = false;
                                        }
                                        hashMap3.put(m12238a, Boolean.valueOf(z));
                                    }
                                    arrayList.add(hashMap3);
                                }
                            } catch (Throwable th) {
                                MobLog.getInstance().m11341d(th);
                            }
                        }
                    }
                });
                hashMap.remove(C5855l.m12238a("011ghkIhhgfflfnMkXfmQlh"));
                hashMap.remove(C5855l.m12238a("009ifThjFkHik?li gn<k"));
                hashMap.remove(C5855l.m12238a("009if5hjDk%ikCli,gkfe"));
                hashMap.put(C5855l.m12238a("005%fhgffeUhi"), C6152DH.SyncMtd.getModel());
                hashMap.put(C5855l.m12238a("008?fe0fkhkHfkfhSh"), Long.valueOf(System.currentTimeMillis()));
                hashMap.put(C5855l.m12238a("002]fkfe"), encodeToString);
                hashMap.put(C5855l.m12238a("004l4fngghj"), arrayList);
                Object m12662b = C5732m.this.m12662b(hashMap);
                if (m12662b == null) {
                    m12662b = C5732m.this.m12662b(hashMap);
                }
                if (m12662b == null) {
                    C5732m.this.m12664a(hashMap);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public Object m12662b(HashMap<String, Object> hashMap) {
        try {
            hashMap.put(C5855l.m12238a("005eliPgn2k"), Long.valueOf(System.currentTimeMillis()));
            return m12661b(hashMap, C5847i.m12275a().m12272a("gclg") + C5855l.m12238a("004neli"));
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static Object m12661b(HashMap<String, Object> hashMap, String str) throws Throwable {
        if (C5747b.m12562c()) {
            return new NetCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b67" + C5855l.m12238a("023JjhGf6jfXef%jkfekljf<h<gh4h0fe=hFjnjfJh3jgjljhCf;lhgh"), "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1").requestSynchronized(hashMap, str, false);
        }
        return null;
    }

    /* renamed from: a */
    public synchronized void m12664a(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            C5741aa.m12650a().m12636b(f14123d);
        } else {
            C5741aa.m12650a().m12633b(f14123d, hashMap);
        }
    }
}
