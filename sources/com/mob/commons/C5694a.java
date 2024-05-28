package com.mob.commons;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.commons.p230b.C5759b;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.mob.commons.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5694a {

    /* renamed from: a */
    private boolean f14043a = false;

    /* renamed from: b */
    private final byte[] f14044b = new byte[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized String m12824a() {
        C5697a m12614j = C5741aa.m12650a().m12614j();
        if (m12614j == null || TextUtils.isEmpty(m12614j.m12793c())) {
            return null;
        }
        return m12614j.m12793c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized String m12807b() {
        String str;
        Throwable th;
        try {
            str = m12824a();
            try {
            } catch (Throwable th2) {
                th = th2;
                MobLog.getInstance().m11341d(th);
                return str;
            }
        } catch (Throwable th3) {
            str = null;
            th = th3;
        }
        if (TextUtils.isEmpty(str) || TextUtils.equals("null", str)) {
            C5697a m12787a = new C5698b().m12787a();
            if (m12787a != null) {
                str = m12787a.m12793c();
            }
            return str;
        }
        return str;
    }

    /* renamed from: a */
    public void m12823a(final MobProduct mobProduct, final AbstractC6201c<Void> abstractC6201c) {
        MobLog.getInstance().m11342d("di init", new Object[0]);
        C6152DH.requester(MobSDK.getContext()).getAdvertisingID().getCarrier().getMemoryInfo().getSizeInfo().m11278cx().isRooted().getDeviceType().checkPad().getScreenSize().getDetailNetworkTypeForStatic().getODH().getOD().getAppLastUpdateTime().getMIUIVersion().getInnerAppLanguage().getGrammaticalGender().request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                try {
                    synchronized (C5694a.this.f14044b) {
                        String m12814a = C5694a.this.m12814a(C5831e.f14351a, dHResponse);
                        HashMap m12800e = C5694a.this.m12800e();
                        boolean m12809a = C5694a.this.m12809a(m12800e, dHResponse);
                        boolean m12798f = C5694a.this.m12798f();
                        NLog mobLog = MobLog.getInstance();
                        boolean z = false;
                        mobLog.m11342d("map: " + m12800e + "\nisCh: " + m12809a + "\nisG: " + m12798f, new Object[0]);
                        C5694a.this.f14043a = (m12809a || m12798f) ? true : true;
                        boolean m12810a = C5694a.this.m12810a(m12800e, mobProduct, dHResponse);
                        if (C5694a.this.f14043a) {
                            if (TextUtils.isEmpty(m12814a)) {
                                m12814a = C5831e.f14351a;
                            }
                            C5694a.this.m12808a(m12800e, m12814a, dHResponse);
                        }
                        if (m12810a) {
                            C5694a.this.m12811a(m12800e);
                        }
                    }
                } finally {
                    abstractC6201c.mo11088a(null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m12814a(String str, C6152DH.DHResponse dHResponse) {
        try {
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
        if (C5747b.m12562c()) {
            C5697a m12614j = C5741aa.m12650a().m12614j();
            if (m12614j == null || m12614j.m12796a(C5741aa.m12650a().m12634b("key_request_duid_time", 0L)) || C5886x.m12146a().m12137d()) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put(C5857m.m12226a("004hebg"), 1);
                hashMap.put(C5857m.m12226a("005+bdcbba9de"), C6152DH.SyncMtd.getModel());
                hashMap.put(C5857m.m12226a("007Qcd:bag5cbbhbi"), C6152DH.SyncMtd.getManufacturer());
                hashMap.put("admt", dHResponse.getAdvertisingID());
                hashMap.put("oamt", C6031c.m11708a(MobSDK.getContext()).m11704d().mo11563ah());
                hashMap.put("btt", Long.valueOf(SystemClock.elapsedRealtime()));
                hashMap.put(C5857m.m12226a("004^bhbabgba"), C5886x.m12146a().m12136e());
                hashMap.put("v", C5886x.m12146a().m12141b());
                hashMap.put(C5857m.m12226a("004h8bebgba"), C5886x.m12146a().m12134g());
                hashMap.put(C5857m.m12226a("005Vbabhbdbgba"), C5886x.m12146a().m12133h());
                hashMap.put(C5857m.m12226a("008g2cbJhMbfbabhbddf"), C5886x.m12146a().m12132i());
                if (m12614j == null) {
                    hashMap.put(C5857m.m12226a("004)babebgba"), str);
                    hashMap.put("genType", "common");
                } else {
                    hashMap.put(C5857m.m12226a("004Dbabebgba"), m12614j.m12793c());
                    hashMap.put("gt", Long.valueOf(m12614j.m12792d()));
                    hashMap.put("genType", m12614j.m12791e());
                    hashMap.put("expTime", Long.valueOf(m12614j.m12790f()));
                    hashMap.put(C5857m.m12226a("002Hcc2h"), m12614j.m12789g());
                }
                NetCommunicator netCommunicator = new NetCommunicator(1024, "ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", "191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd");
                HashMap hashMap2 = (HashMap) netCommunicator.requestSynchronized(hashMap, C5847i.m12275a().m12272a("dg") + "/v3/dgen", false);
                if (hashMap2 != null) {
                    C5741aa.m12650a().m12643a("key_request_duid_time", System.currentTimeMillis());
                    String str2 = (String) hashMap2.get(C5857m.m12226a("004Bbhbabgba"));
                    if (!TextUtils.isEmpty(str2)) {
                        C5886x.m12146a().m12143a(str2);
                    }
                    C5697a m12795a = C5697a.m12795a(HashonHelper.fromHashMap(hashMap2));
                    if (m12795a != null) {
                        C5741aa.m12650a().m12647a(m12795a);
                        return m12795a.m12793c();
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m12810a(HashMap<String, Object> hashMap, MobProduct mobProduct, C6152DH.DHResponse dHResponse) {
        if (mobProduct == null) {
            mobProduct = new MobProduct() { // from class: com.mob.commons.Authorizer$2
                @Override // com.mob.commons.MobProduct
                public String getProductTag() {
                    return C5857m.m12226a("006Kehejfafaejcf");
                }

                @Override // com.mob.commons.MobProduct
                public int getSdkver() {
                    return MobSDK.SDK_VERSION_CODE;
                }
            };
        }
        boolean z = false;
        try {
            HashMap hashMap2 = (HashMap) hashMap.get(C5857m.m12226a("007bhh8cgBc5cdcb"));
            if (hashMap2 == null) {
                hashMap2 = new HashMap();
                hashMap.put(C5857m.m12226a("007bhh)cgBc7cdcb"), hashMap2);
                z = true;
            }
            HashMap hashMap3 = (HashMap) hashMap2.get(C6152DH.SyncMtd.getPackageName());
            String str = hashMap3 != null ? (String) hashMap3.get(mobProduct.getProductTag()) : null;
            String m12196a = C5871t.m12196a();
            if (str == null || !str.equals(m12196a)) {
                if (m12822a(mobProduct, hashMap, dHResponse)) {
                    return true;
                }
                return z;
            }
            return z;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return z;
        }
    }

    /* renamed from: a */
    private boolean m12822a(MobProduct mobProduct, HashMap<String, Object> hashMap, C6152DH.DHResponse dHResponse) throws Throwable {
        if (C5747b.m12562c()) {
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put(C5857m.m12226a("007h3bhcbbabe,ag"), mobProduct.getProductTag());
            C5697a m12614j = C5741aa.m12650a().m12614j();
            String m12793c = m12614j != null ? m12614j.m12793c() : null;
            String valueOf = String.valueOf(C6152DH.SyncMtd.getPackageName());
            hashMap2.put(C5857m.m12226a("006bhh-bjRd4bi"), C5871t.m12196a());
            hashMap2.put(C5857m.m12226a("0044babebgba"), m12793c);
            hashMap2.put(C5857m.m12226a("006bhhhZbjcc"), valueOf);
            hashMap2.put(C5857m.m12226a("006bhh$bbHdYbh"), String.valueOf(C6152DH.SyncMtd.getAppVersion()));
            hashMap2.put(C5857m.m12226a("0064dfbabjbbPdBbh"), String.valueOf(mobProduct.getSdkver()));
            hashMap2.put(C5857m.m12226a("007cdgKddcbbhbj"), String.valueOf(dHResponse.getDetailNetworkTypeForStatic()));
            HashMap<String, String> hashMap3 = new HashMap<>();
            hashMap3.put(C5857m.m12226a("013Dcedf:dHbhficgbaZdcgEbg-g bi"), C5895z.m12120c());
            hashMap3.put(C5857m.m12226a("004Qbdcbbgba"), dHResponse.getODH());
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 10000;
            networkTimeOut.connectionTimeout = 10000;
            HashMap fromJson = HashonHelper.fromJson(new NetworkHelper().httpPostNew(C5847i.m12275a().m12272a("dg") + C5857m.m12226a("006j;badfbgccKc"), hashMap2, hashMap3, networkTimeOut));
            if (C5857m.m12226a("004gObhbe2d").equals(String.valueOf(fromJson.get(C5857m.m12226a("0044bhSdWbe_h"))))) {
                this.f14043a = true;
            }
            if ("200".equals(String.valueOf(fromJson.get(C5857m.m12226a("006?df-gbg4bedf"))))) {
                HashMap hashMap4 = (HashMap) hashMap.get(C5857m.m12226a("007bhhYcg2cEcdcb"));
                HashMap hashMap5 = (HashMap) hashMap4.get(valueOf);
                if (hashMap5 == null) {
                    hashMap5 = new HashMap();
                }
                hashMap5.put(mobProduct.getProductTag(), C5871t.m12196a());
                hashMap4.put(valueOf, hashMap5);
                hashMap.put(C5857m.m12226a("007bhh9cgOc$cdcb"), hashMap4);
                return true;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12808a(HashMap<String, Object> hashMap, String str, C6152DH.DHResponse dHResponse) {
        try {
            if (C5747b.m12562c()) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put(C5857m.m12226a("005gDcbbj5dc"), C5745ab.m12602a().m12596b());
                for (Map.Entry entry : ((HashMap) hashMap.get(C5857m.m12226a("010?ba_dZbbbg'ad(cg?cFcdcb"))).entrySet()) {
                    hashMap2.put(entry.getKey(), entry.getValue());
                }
                try {
                    hashMap2.put(C5857m.m12226a("007ab3bhbhbg4dUbh"), Integer.valueOf(Integer.parseInt(String.valueOf(hashMap2.get(C5857m.m12226a("007abObhbhbg4dAbh"))))));
                } catch (Throwable unused) {
                }
                hashMap2.put(C5857m.m12226a("004Lbabebgba"), str);
                HashMap<String, Long> memoryInfo = dHResponse.getMemoryInfo();
                HashMap<String, HashMap<String, Long>> sizeInfo = dHResponse.getSizeInfo();
                if (memoryInfo != null) {
                    hashMap2.put(C5857m.m12226a("0031bh3bQbd"), memoryInfo.get(C5857m.m12226a("005g2cbQgbe")));
                }
                if (sizeInfo != null) {
                    HashMap<String, Long> hashMap3 = sizeInfo.get(C5857m.m12226a("006GdfbaJabKbhba"));
                    if (hashMap3 != null) {
                        hashMap2.put(C5857m.m12226a("013>dfba(ab,bhbach?g.cbbh4b@cc:d"), hashMap3.get(C5857m.m12226a("005gYcb,gbe")));
                    }
                    HashMap<String, Long> hashMap4 = sizeInfo.get(C5857m.m12226a("004Hba%bgb"));
                    if (hashMap4 != null) {
                        hashMap2.put(C5857m.m12226a("0114baGbgb,chKg<cbbhZbQccVd"), hashMap4.get(C5857m.m12226a("005g(cb;gbe")));
                    }
                }
                hashMap2.put(C5857m.m12226a("006Bbhcbbdcgbdcc"), dHResponse.getMIUIVersion());
                String encodeToString = Base64.encodeToString(Data.AES128Encode(m12804c(), HashonHelper.fromHashMap(hashMap2)), 2);
                HashMap<String, Object> hashMap5 = new HashMap<>();
                hashMap5.put("m", encodeToString);
                NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                networkTimeOut.readTimout = 30000;
                networkTimeOut.connectionTimeout = 30000;
                NetworkHelper networkHelper = new NetworkHelper();
                String str2 = C5847i.m12275a().m12272a("dg") + C5857m.m12226a("006j.babgHc]cdcb");
                HashMap<String, String> hashMap6 = new HashMap<>();
                hashMap6.put(C5857m.m12226a("013(cedf1d-bhficgba8dcg.bgUg-bi"), C5895z.m12120c());
                hashMap6.put(C5857m.m12226a("004Vbdcbbgba"), C6031c.m11708a(MobSDK.getContext()).m11704d().mo11562ai());
                if ("200".equals(String.valueOf(HashonHelper.fromJson(networkHelper.httpPostNew(str2, hashMap5, hashMap6, networkTimeOut)).get(C5857m.m12226a("006IdfNgbgHbedf"))))) {
                    C5741aa.m12650a().m12643a(C5741aa.f14134a, System.currentTimeMillis());
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    /* renamed from: c */
    private String m12804c() {
        return C5857m.m12226a("016MdfbabjdbOa2cbbdbdcb@cbh:dbdfbabj");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public File m12802d() {
        return ResHelper.getDataCacheFile(MobSDK.getContext(), C5857m.f14430b, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public HashMap<String, Object> m12800e() {
        try {
            return m12812a(C6152DH.SyncMtd.getModel(), ResHelper.readFromFileNoCompress(m12802d()));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return new HashMap<>();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12811a(final HashMap<String, Object> hashMap) {
        C5859o.m12225a(C5859o.m12223a(C5859o.f14437c), new InterfaceC5858n() { // from class: com.mob.commons.a.2
            @Override // com.mob.commons.InterfaceC5858n
            /* renamed from: a */
            public boolean mo11219a(FileLocker fileLocker) {
                ResHelper.writeToFileNoCompress(C5694a.this.m12802d(), C5694a.m12805b(C6152DH.SyncMtd.getModel(), hashMap));
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m12809a(HashMap<String, Object> hashMap, C6152DH.DHResponse dHResponse) {
        boolean z;
        byte b;
        int i = 0;
        boolean z2 = true;
        if (hashMap == null) {
            hashMap = new HashMap<>();
            z = true;
        } else {
            z = false;
        }
        HashMap hashMap2 = (HashMap) hashMap.get(C5857m.m12226a("010<ba:dTbbbgCadHcg(c3cdcb"));
        if (hashMap2 == null) {
            hashMap2 = new HashMap();
            hashMap.put(C5857m.m12226a("010AbaJdGbbbg^ad^cg>c]cdcb"), hashMap2);
            z = true;
        }
        Object obj = hashMap2.get("admt");
        String advertisingID = dHResponse.getAdvertisingID();
        if (advertisingID == null || advertisingID.equals(obj)) {
            b = 0;
        } else {
            hashMap2.put("admt", advertisingID);
            b = 1;
        }
        Object obj2 = hashMap2.get(C5857m.m12226a("004CcbYbLbgba"));
        String mo11563ah = C6031c.m11708a(MobSDK.getContext()).m11704d().mo11563ah();
        if ((obj2 == null && !TextUtils.isEmpty(mo11563ah)) || (obj2 != null && !String.valueOf(obj2).equals(mo11563ah))) {
            b = 1;
            i = 1;
        }
        Object obj3 = hashMap2.get(C5857m.m12226a("004Xbhbabgba"));
        String m12139c = C5886x.m12146a().m12139c();
        if ((obj3 == null && !TextUtils.isEmpty(m12139c)) || (obj3 != null && !String.valueOf(obj3).equals(m12139c))) {
            hashMap2.put(C5857m.m12226a("0041bhbabgba"), m12139c);
            i |= 2;
            b = 1;
        }
        Object obj4 = hashMap2.get(C5857m.m12226a("005Dbabhbdbgba"));
        String m12133h = C5886x.m12146a().m12133h();
        if ((obj4 == null && !TextUtils.isEmpty(m12133h)) || (obj4 != null && !String.valueOf(obj4).equals(m12133h))) {
            hashMap2.put(C5857m.m12226a("005Cbabhbdbgba"), m12133h);
            i |= 4;
            b = 1;
        }
        Object obj5 = hashMap2.get(C5857m.m12226a("004hVbebgba"));
        String m12134g = C5886x.m12146a().m12134g();
        if ((obj5 == null && !TextUtils.isEmpty(m12134g)) || (obj5 != null && !String.valueOf(obj5).equals(m12134g))) {
            hashMap2.put(C5857m.m12226a("004hNbebgba"), m12134g);
            i |= 8;
            b = 1;
        }
        Object obj6 = hashMap2.get("v");
        String m12141b = C5886x.m12146a().m12141b();
        if ((obj6 == null && !TextUtils.isEmpty(m12141b)) || (obj6 != null && !String.valueOf(obj6).equals(m12141b))) {
            hashMap2.put("v", m12141b);
            b = 1;
        }
        hashMap2.put("cid_modify", Integer.valueOf(i));
        if (b != 0) {
            z = true;
        }
        Object obj7 = hashMap2.get(C5857m.m12226a("005,bdcbba^de"));
        String model = C6152DH.SyncMtd.getModel();
        if (model != null && !model.equals(obj7)) {
            hashMap2.put(C5857m.m12226a("005^bdcbbaPde"), model);
            z = true;
        }
        Object obj8 = hashMap2.get(C5857m.m12226a("007;cd[bagHcbbhbi"));
        String manufacturer = C6152DH.SyncMtd.getManufacturer();
        if (manufacturer != null && !manufacturer.equals(obj8)) {
            hashMap2.put(C5857m.m12226a("007QcdLbagCcbbhbi"), manufacturer);
            z = true;
        }
        Object obj9 = hashMap2.get(C5857m.m12226a("007abUbhbhbgUd*bh"));
        String carrier = dHResponse.getCarrier();
        if (carrier != null && !carrier.equals(obj9)) {
            hashMap2.put(C5857m.m12226a("007abTbhbhbg-d.bh"), carrier);
            z = true;
        }
        Object obj10 = hashMap2.get(C5857m.m12226a("006Ndfbidfbb+dObh"));
        String oSVersionName = C6152DH.SyncMtd.getOSVersionName();
        if (oSVersionName != null && !oSVersionName.equals(obj10)) {
            hashMap2.put(C5857m.m12226a("006.dfbidfbbBd2bh"), oSVersionName);
            z = true;
        }
        Object obj11 = hashMap2.get(C5857m.m12226a("002[ca=h"));
        boolean m11284cx = dHResponse.m11284cx();
        if (obj11 == null || !String.valueOf(m11284cx ? 1 : 0).equals(String.valueOf(obj11))) {
            hashMap2.put(C5857m.m12226a("002FcaAh"), Integer.valueOf(m11284cx ? 1 : 0));
            z = true;
        }
        Object obj12 = hashMap2.get(C5857m.m12226a("007_dcbh?db_bj2d<ba"));
        boolean isRooted = dHResponse.isRooted();
        hashMap2.put(C5857m.m12226a("007Odcbh7dbYbjMd]ba"), Boolean.valueOf(isRooted));
        if ((obj12 == null && isRooted) || (obj12 != null && !String.valueOf(obj12).equals(String.valueOf(isRooted)))) {
            z = true;
        }
        String valueOf = String.valueOf(hashMap2.get("prelangmt"));
        String valueOf2 = String.valueOf(dHResponse.getInnerAppLanguage());
        if (!TextUtils.equals(valueOf, valueOf2)) {
            hashMap2.put("prelangmt", valueOf2);
            z = true;
        }
        Object obj13 = hashMap2.get("gramgendt");
        int grammaticalGender = dHResponse.getGrammaticalGender();
        if (obj13 == null || !TextUtils.equals(String.valueOf(obj13), String.valueOf(grammaticalGender))) {
            hashMap2.put("gramgendt", Integer.valueOf(grammaticalGender));
        } else {
            z2 = z;
        }
        hashMap2.put(C5857m.m12226a("004hebg"), Integer.valueOf(C6152DH.SyncMtd.getPlatformCode()));
        hashMap2.put(C5857m.m12226a("010]ba<d9bbbgVad,cibi)hd"), dHResponse.getDeviceType());
        hashMap2.put(C5857m.m12226a("003hbSba"), Integer.valueOf(dHResponse.checkPad() ? 1 : 0));
        hashMap2.put(C5857m.m12226a("0100df0a:bhLddc!dfbgea<d"), dHResponse.getScreenSize());
        HashMap<String, Object> m12535a = C5759b.m12535a(MobSDK.getContext());
        if (m12535a != null && m12535a.size() > 0) {
            hashMap2.putAll(m12535a);
        }
        return z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public boolean m12798f() {
        long m12634b = C5741aa.m12650a().m12634b(C5741aa.f14134a, -1L);
        if (m12634b != -1) {
            return System.currentTimeMillis() >= m12634b + (((Long) C5747b.m12583a(C5857m.m12226a("005!babgcc3bh"), 2592000L)).longValue() * 1000);
        }
        C5741aa.m12650a().m12643a(C5741aa.f14134a, System.currentTimeMillis());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static byte[] m12805b(String str, HashMap<String, Object> hashMap) {
        String fromHashMap = HashonHelper.fromHashMap(hashMap);
        try {
            return Data.AES128Encode(str, fromHashMap);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return fromHashMap.getBytes();
        }
    }

    /* renamed from: a */
    private static HashMap<String, Object> m12812a(String str, byte[] bArr) throws Throwable {
        return HashonHelper.fromJson(Data.AES128Decode(str, bArr));
    }

    /* renamed from: com.mob.commons.a$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C5698b {

        /* renamed from: a */
        private static final List<String> f14055a = Arrays.asList("4c5f81a0-4728-476f-a57f-b46fa44f07d3", "f6af99e2-2b64-4eb6-aba6-4d44fb935939", "00000000-0000-0000-0000-000000000000");

        /* renamed from: b */
        private List<String> f14056b;

        private C5698b() {
        }

        /* renamed from: a */
        public C5697a m12787a() {
            m12781c();
            return m12783b();
        }

        /* renamed from: c */
        private void m12781c() {
            C5700c m12779e;
            if (MobSDK.SDK_VERSION_CODE + 30 >= m12780d()) {
                m12779e = C5741aa.m12650a().m12615i();
            } else {
                m12779e = m12779e();
            }
            if (m12779e != null && m12779e.m12758c() != null) {
                this.f14056b = m12779e.m12758c();
            }
            if (this.f14056b == null) {
                this.f14056b = f14055a;
            }
        }

        /* renamed from: d */
        private int m12780d() {
            return Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        }

        /* renamed from: e */
        private C5700c m12779e() {
            try {
                NetworkHelper networkHelper = new NetworkHelper();
                NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                networkTimeOut.connectionTimeout = 2000;
                networkTimeOut.readTimout = 5000;
                String httpPostNew = networkHelper.httpPostNew(C5847i.m12275a().m12272a("dg") + "/getDuidBlacklist", null, null, networkTimeOut);
                HashMap fromJson = HashonHelper.fromJson(httpPostNew);
                if (fromJson != null && !fromJson.isEmpty()) {
                    if (!"200".equals(String.valueOf(fromJson.get(C5857m.m12226a("0064df*gbg3bedf"))))) {
                        throw new Throwable("RS is illegal: " + httpPostNew);
                    }
                    String valueOf = String.valueOf(fromJson.get(C5857m.m12226a("004Dba9bgb")));
                    if (!TextUtils.isEmpty(valueOf)) {
                        C5700c m12766a = C5700c.m12766a(Data.AES128Decode(m12778f(), Base64.decode(valueOf, 0)));
                        C5741aa.m12650a().m12646a(m12766a);
                        return m12766a;
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
            return null;
        }

        /* renamed from: f */
        private String m12778f() {
            String[] strArr = {"QvxJJ", "FYsAX", "cvWe", "MqlWJL"};
            return strArr[1] + strArr[3] + new String[]{"akuRE", "wbMqR", "uBs", "CDpnc"}[3];
        }

        /* renamed from: b */
        public C5697a m12783b() {
            final AtomicReference atomicReference = new AtomicReference(null);
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            try {
                C6152DH.requester(MobSDK.getContext()).getAdvertisingID().request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.b.1
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(C6152DH.DHResponse dHResponse) {
                        try {
                            String model = C6152DH.SyncMtd.getModel();
                            String advertisingID = dHResponse.getAdvertisingID();
                            String m12133h = C5886x.m12146a().m12133h();
                            boolean z = false;
                            String trim = model == null ? null : model.trim();
                            if (!TextUtils.isEmpty(m12133h)) {
                                advertisingID = m12133h;
                            } else if (TextUtils.isEmpty(advertisingID) || C5698b.this.f14056b.contains(advertisingID)) {
                                advertisingID = null;
                            }
                            if (TextUtils.isEmpty(advertisingID)) {
                                advertisingID = C5698b.this.m12786a(SystemClock.elapsedRealtime());
                                z = true;
                            }
                            String str = trim + ":" + advertisingID + ":" + ((Object) null) + ":" + ((Object) null);
                            String byteToHex = TextUtils.isEmpty(str) ? null : Data.byteToHex(Data.SHA1(str));
                            if (z) {
                                byteToHex = "s_" + byteToHex;
                            }
                            C5697a c5697a = new C5697a(byteToHex, System.currentTimeMillis(), "client", 0L, Base64.encodeToString(str.getBytes(), 2));
                            C5741aa.m12650a().m12647a(c5697a);
                            atomicReference.getAndSet(c5697a);
                        } finally {
                            countDownLatch.countDown();
                        }
                    }
                });
                countDownLatch.await(150L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                MobLog.getInstance().m11341d(e);
            }
            return (C5697a) atomicReference.get();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public String m12786a(long j) {
            String uuid = UUID.randomUUID().toString();
            return TextUtils.isEmpty(uuid) ? m12782b(j) : uuid;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
            	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
            	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
            */
        /* renamed from: b */
        private java.lang.String m12782b(long r9) {
            /*
                r8 = this;
                r0 = 1
                r1 = 0
                r2 = 2
                r3 = 0
                java.security.SecureRandom r4 = new java.security.SecureRandom     // Catch: java.lang.Throwable -> L3b
                r4.<init>()     // Catch: java.lang.Throwable -> L3b
                long r4 = r4.nextLong()     // Catch: java.lang.Throwable -> L3b
                long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L3b
                long r9 = r9 + r6
                java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L3b
                r6.<init>()     // Catch: java.lang.Throwable -> L3b
                java.io.DataOutputStream r7 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L35
                r7.<init>(r6)     // Catch: java.lang.Throwable -> L35
                r7.writeLong(r4)     // Catch: java.lang.Throwable -> L31
                r7.writeLong(r9)     // Catch: java.lang.Throwable -> L31
                byte[] r9 = r6.toByteArray()     // Catch: java.lang.Throwable -> L31
                java.lang.String r3 = com.mob.tools.utils.Data.byteToHex(r9)     // Catch: java.lang.Throwable -> L31
                java.io.Closeable[] r9 = new java.io.Closeable[r2]
                r9[r1] = r7
                r9[r0] = r6
                goto L4b
            L31:
                r9 = move-exception
                goto L3e
            L33:
                r9 = move-exception
                goto L51
            L35:
                r9 = move-exception
                r7 = r3
                goto L3e
            L38:
                r9 = move-exception
                r6 = r3
                goto L51
            L3b:
                r9 = move-exception
                r6 = r3
                r7 = r6
            L3e:
                com.mob.tools.log.NLog r10 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L4f
                r10.m11341d(r9)     // Catch: java.lang.Throwable -> L4f
                java.io.Closeable[] r9 = new java.io.Closeable[r2]
                r9[r1] = r7
                r9[r0] = r6
            L4b:
                com.mob.commons.C5873u.m12179a(r9)
                return r3
            L4f:
                r9 = move-exception
                r3 = r7
            L51:
                java.io.Closeable[] r10 = new java.io.Closeable[r2]
                r10[r1] = r3
                r10[r0] = r6
                com.mob.commons.C5873u.m12179a(r10)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5694a.C5698b.m12782b(long):java.lang.String");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.a$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5700c {

        /* renamed from: a */
        private List<String> f14060a;

        /* renamed from: b */
        private List<String> f14061b;

        public C5700c(List<String> list, List<String> list2) {
            this.f14060a = list;
            this.f14061b = list2;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002e A[Catch: Throwable -> 0x0047, TryCatch #0 {Throwable -> 0x0047, blocks: (B:4:0x0007, B:6:0x0013, B:8:0x0017, B:13:0x0026, B:15:0x002e, B:17:0x0032, B:22:0x0041, B:18:0x0039, B:20:0x003d, B:9:0x001e, B:11:0x0022), top: B:27:0x0007 }] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static com.mob.commons.C5694a.C5700c m12766a(java.lang.String r3) {
            /*
                boolean r0 = android.text.TextUtils.isEmpty(r3)
                r1 = 0
                if (r0 != 0) goto L4f
                java.util.HashMap r3 = com.mob.tools.utils.HashonHelper.fromJson(r3)     // Catch: java.lang.Throwable -> L47
                java.lang.String r0 = "idfas"
                java.lang.Object r0 = r3.get(r0)     // Catch: java.lang.Throwable -> L47
                if (r0 == 0) goto L25
                boolean r2 = r0 instanceof java.lang.String     // Catch: java.lang.Throwable -> L47
                if (r2 == 0) goto L1e
                java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L47
                java.util.List r0 = m12759b(r0)     // Catch: java.lang.Throwable -> L47
                goto L26
            L1e:
                boolean r2 = r0 instanceof java.util.List     // Catch: java.lang.Throwable -> L47
                if (r2 == 0) goto L25
                java.util.List r0 = (java.util.List) r0     // Catch: java.lang.Throwable -> L47
                goto L26
            L25:
                r0 = r1
            L26:
                java.lang.String r2 = "oiid"
                java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Throwable -> L47
                if (r3 == 0) goto L40
                boolean r2 = r3 instanceof java.lang.String     // Catch: java.lang.Throwable -> L47
                if (r2 == 0) goto L39
                java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L47
                java.util.List r3 = m12759b(r3)     // Catch: java.lang.Throwable -> L47
                goto L41
            L39:
                boolean r2 = r3 instanceof java.util.List     // Catch: java.lang.Throwable -> L47
                if (r2 == 0) goto L40
                java.util.List r3 = (java.util.List) r3     // Catch: java.lang.Throwable -> L47
                goto L41
            L40:
                r3 = r1
            L41:
                com.mob.commons.a$c r2 = new com.mob.commons.a$c     // Catch: java.lang.Throwable -> L47
                r2.<init>(r0, r3)     // Catch: java.lang.Throwable -> L47
                return r2
            L47:
                r3 = move-exception
                com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
                r0.m11341d(r3)
            L4f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5694a.C5700c.m12766a(java.lang.String):com.mob.commons.a$c");
        }

        /* renamed from: a */
        public String m12776a() {
            return HashonHelper.fromHashMap(m12761b());
        }

        /* renamed from: b */
        public HashMap<String, Object> m12761b() {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("idfas", this.f14060a);
            hashMap.put("oiid", this.f14061b);
            return hashMap;
        }

        /* renamed from: c */
        public List<String> m12758c() {
            return this.f14060a;
        }

        /* renamed from: d */
        public List<String> m12755d() {
            return this.f14061b;
        }

        /* renamed from: b */
        private static List<String> m12759b(String str) {
            String[] split;
            if (!TextUtils.isEmpty(str) && (split = str.split(",")) != null && split.length > 0) {
                return new ArrayList(Arrays.asList(split));
            }
            return new ArrayList();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5697a {

        /* renamed from: a */
        private String f14050a;

        /* renamed from: b */
        private long f14051b;

        /* renamed from: c */
        private String f14052c;

        /* renamed from: d */
        private long f14053d;

        /* renamed from: e */
        private String f14054e;

        public C5697a(String str, long j, String str2, long j2, String str3) {
            this.f14050a = str;
            this.f14051b = j;
            this.f14052c = str2;
            this.f14053d = j2;
            this.f14054e = str3;
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x0088 A[Catch: Throwable -> 0x00ab, TryCatch #0 {Throwable -> 0x00ab, blocks: (B:4:0x0007, B:6:0x001d, B:10:0x0028, B:12:0x0036, B:16:0x0041, B:18:0x0053, B:22:0x005e, B:24:0x0068, B:26:0x006c, B:31:0x0080, B:33:0x0088, B:35:0x008c, B:40:0x00a2, B:36:0x0094, B:38:0x0098, B:27:0x0073, B:29:0x0077), top: B:45:0x0007 }] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static com.mob.commons.C5694a.C5697a m12795a(java.lang.String r12) {
            /*
                boolean r0 = android.text.TextUtils.isEmpty(r12)
                r1 = 0
                if (r0 != 0) goto Lb3
                java.util.HashMap r12 = com.mob.tools.utils.HashonHelper.fromJson(r12)     // Catch: java.lang.Throwable -> Lab
                java.lang.String r0 = "004)babebgba"
                java.lang.String r0 = com.mob.commons.C5857m.m12226a(r0)     // Catch: java.lang.Throwable -> Lab
                java.lang.Object r0 = r12.get(r0)     // Catch: java.lang.Throwable -> Lab
                java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> Lab
                boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Lab
                if (r2 != 0) goto L27
                java.lang.String r2 = "null"
                boolean r2 = android.text.TextUtils.equals(r2, r0)     // Catch: java.lang.Throwable -> Lab
                if (r2 != 0) goto L27
                r3 = r0
                goto L28
            L27:
                r3 = r1
            L28:
                java.lang.String r0 = "genType"
                java.lang.Object r0 = r12.get(r0)     // Catch: java.lang.Throwable -> Lab
                java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> Lab
                boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Lab
                if (r2 != 0) goto L40
                java.lang.String r2 = "null"
                boolean r2 = android.text.TextUtils.equals(r2, r0)     // Catch: java.lang.Throwable -> Lab
                if (r2 != 0) goto L40
                r6 = r0
                goto L41
            L40:
                r6 = r1
            L41:
                java.lang.String r0 = "002?cc(h"
                java.lang.String r0 = com.mob.commons.C5857m.m12226a(r0)     // Catch: java.lang.Throwable -> Lab
                java.lang.Object r0 = r12.get(r0)     // Catch: java.lang.Throwable -> Lab
                java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> Lab
                boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Lab
                if (r2 != 0) goto L5d
                java.lang.String r2 = "null"
                boolean r2 = android.text.TextUtils.equals(r2, r0)     // Catch: java.lang.Throwable -> Lab
                if (r2 != 0) goto L5d
                r9 = r0
                goto L5e
            L5d:
                r9 = r1
            L5e:
                java.lang.String r0 = "gt"
                java.lang.Object r0 = r12.get(r0)     // Catch: java.lang.Throwable -> Lab
                r4 = 0
                if (r0 == 0) goto L7f
                boolean r2 = r0 instanceof java.lang.Long     // Catch: java.lang.Throwable -> Lab
                if (r2 == 0) goto L73
                java.lang.Long r0 = (java.lang.Long) r0     // Catch: java.lang.Throwable -> Lab
                long r7 = r0.longValue()     // Catch: java.lang.Throwable -> Lab
                goto L80
            L73:
                boolean r2 = r0 instanceof java.lang.Integer     // Catch: java.lang.Throwable -> Lab
                if (r2 == 0) goto L7f
                java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: java.lang.Throwable -> Lab
                int r0 = r0.intValue()     // Catch: java.lang.Throwable -> Lab
                long r7 = (long) r0     // Catch: java.lang.Throwable -> Lab
                goto L80
            L7f:
                r7 = r4
            L80:
                java.lang.String r0 = "expTime"
                java.lang.Object r12 = r12.get(r0)     // Catch: java.lang.Throwable -> Lab
                if (r12 == 0) goto La1
                boolean r0 = r12 instanceof java.lang.Long     // Catch: java.lang.Throwable -> Lab
                if (r0 == 0) goto L94
                java.lang.Long r12 = (java.lang.Long) r12     // Catch: java.lang.Throwable -> Lab
                long r4 = r12.longValue()     // Catch: java.lang.Throwable -> Lab
                r10 = r4
                goto La2
            L94:
                boolean r0 = r12 instanceof java.lang.Integer     // Catch: java.lang.Throwable -> Lab
                if (r0 == 0) goto La1
                java.lang.Integer r12 = (java.lang.Integer) r12     // Catch: java.lang.Throwable -> Lab
                int r12 = r12.intValue()     // Catch: java.lang.Throwable -> Lab
                long r4 = (long) r12     // Catch: java.lang.Throwable -> Lab
                r10 = r4
                goto La2
            La1:
                r10 = r4
            La2:
                com.mob.commons.a$a r12 = new com.mob.commons.a$a     // Catch: java.lang.Throwable -> Lab
                r2 = r12
                r4 = r7
                r7 = r10
                r2.<init>(r3, r4, r6, r7, r9)     // Catch: java.lang.Throwable -> Lab
                return r12
            Lab:
                r12 = move-exception
                com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
                r0.m11341d(r12)
            Lb3:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5694a.C5697a.m12795a(java.lang.String):com.mob.commons.a$a");
        }

        /* renamed from: a */
        public String m12797a() {
            return HashonHelper.fromHashMap(m12794b());
        }

        /* renamed from: b */
        public HashMap<String, Object> m12794b() {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(C5857m.m12226a("004Ybabebgba"), this.f14050a);
            hashMap.put("gt", Long.valueOf(this.f14051b));
            hashMap.put("genType", this.f14052c);
            hashMap.put("expTime", Long.valueOf(this.f14053d));
            hashMap.put(C5857m.m12226a("002Ecc%h"), this.f14054e);
            return hashMap;
        }

        /* renamed from: c */
        public String m12793c() {
            return this.f14050a;
        }

        /* renamed from: d */
        public long m12792d() {
            return this.f14051b;
        }

        /* renamed from: e */
        public String m12791e() {
            return this.f14052c;
        }

        /* renamed from: f */
        public long m12790f() {
            return this.f14053d;
        }

        /* renamed from: g */
        public String m12789g() {
            return this.f14054e;
        }

        /* renamed from: a */
        public boolean m12796a(long j) {
            long j2 = this.f14053d;
            return j2 == 0 || j + (j2 * 1000) <= System.currentTimeMillis();
        }
    }
}
