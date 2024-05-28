package com.mob.commons;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.p229a.C5731l;
import com.mob.commons.p231cc.C5791a;
import com.mob.tools.MobLog;
import com.mob.tools.p237a.C6032d;
import com.mob.tools.utils.AbstractRunnableC6217h;
import com.mob.tools.utils.C6152DH;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.z */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5895z {

    /* renamed from: a */
    public static final String[] f14534a = {"SHARESDK", C5731l.m12674a("006,fkidfkfkgljd"), "MOBLINK", "MOBPUSH", C5731l.m12674a("009Ufkhhhkhjhhhifjgmjl"), C5731l.m12674a("008Widhmgjfmglfkgljd"), "MOBGUARD"};

    /* renamed from: b */
    private static AtomicBoolean f14535b = new AtomicBoolean(false);

    /* renamed from: c */
    private static AtomicBoolean f14536c = new AtomicBoolean(false);

    /* renamed from: d */
    private static final HashMap<String, MobProduct> f14537d = new HashMap<>();

    /* renamed from: a */
    public static void m12124a() {
        m12116g();
        C5892y.f14525c.execute(new AbstractRunnableC6217h() { // from class: com.mob.commons.z.1
            @Override // com.mob.tools.utils.AbstractRunnableC6217h
            /* renamed from: a */
            public void mo10991a() {
                MobLog.getInstance().m11342d("init sks start", new Object[0]);
                C5895z.m12121b();
                MobLog.getInstance().m11342d("init sks over", new Object[0]);
            }
        });
    }

    /* renamed from: g */
    private static void m12116g() {
        if (C5879w.m12152h() && f14536c.compareAndSet(false, true)) {
            try {
                MOBLINK moblink = new MOBLINK();
                if (moblink instanceof MobProduct) {
                    moblink.getProductTag();
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m12123a(MobProduct mobProduct) {
        synchronized (f14537d) {
            if (mobProduct != null) {
                if (!f14537d.containsKey(mobProduct.getProductTag())) {
                    f14537d.put(mobProduct.getProductTag(), mobProduct);
                }
            }
        }
    }

    /* renamed from: b */
    public static ArrayList<MobProduct> m12121b() {
        ArrayList<MobProduct> arrayList;
        synchronized (f14537d) {
            if (C5879w.m12152h() && f14535b.compareAndSet(false, true)) {
                f14537d.putAll(m12115h());
            }
            arrayList = new ArrayList<>();
            arrayList.addAll(f14537d.values());
        }
        return arrayList;
    }

    /* renamed from: h */
    private static HashMap<String, MobProduct> m12115h() {
        Class<?> cls;
        HashMap<String, MobProduct> hashMap = new HashMap<>();
        for (Object obj : C5870s.f14482a) {
            try {
                if (obj instanceof String) {
                    cls = Class.forName(String.valueOf(obj).trim());
                } else {
                    cls = (Class) obj;
                }
                if (MobProduct.class.isAssignableFrom(cls) && !MobProduct.class.equals(cls)) {
                    MobProduct mobProduct = (MobProduct) cls.newInstance();
                    String productTag = mobProduct.getProductTag();
                    String[] strArr = f14534a;
                    int length = strArr.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            String str = strArr[i];
                            if (str.equals(productTag)) {
                                hashMap.put(str, mobProduct);
                                break;
                            }
                            i++;
                        }
                    }
                } else {
                    cls.newInstance();
                }
            } catch (Throwable unused) {
            }
        }
        return hashMap;
    }

    /* renamed from: c */
    public static synchronized String m12120c() {
        String m12122a;
        synchronized (C5895z.class) {
            m12122a = m12122a(m12121b(), 0);
        }
        return m12122a;
    }

    /* renamed from: d */
    public static synchronized String m12119d() {
        String m12122a;
        synchronized (C5895z.class) {
            m12122a = m12122a(m12121b(), 1);
        }
        return m12122a;
    }

    /* renamed from: e */
    public static synchronized String m12118e() {
        String m12122a;
        synchronized (C5895z.class) {
            m12122a = m12122a(m12121b(), 2);
        }
        return m12122a;
    }

    /* renamed from: f */
    public static synchronized String m12117f() {
        String m12122a;
        synchronized (C5895z.class) {
            m12122a = m12122a(m12121b(), 3);
        }
        return m12122a;
    }

    /* renamed from: a */
    private static synchronized String m12122a(final ArrayList<MobProduct> arrayList, final int i) {
        String str;
        synchronized (C5895z.class) {
            final String[] strArr = {""};
            C6152DH.RequestBuilder carrier = C6152DH.requester(MobSDK.getContext()).getMIUIVersion().getDetailNetworkTypeForStatic().getCarrier();
            if (C5879w.m12162b() && i != 3) {
                carrier.getDeviceKey();
            } else {
                carrier.getDeviceKeyFromCache(true);
            }
            carrier.request(new C6152DH.DHResponder() { // from class: com.mob.commons.z.2
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                public void onResponse(C6152DH.DHResponse dHResponse) throws Throwable {
                    String deviceKeyFromCache;
                    String encode = TextUtils.isEmpty(C6152DH.SyncMtd.getPackageName()) ? "" : URLEncoder.encode(C6152DH.SyncMtd.getPackageName(), "utf-8");
                    String encode2 = TextUtils.isEmpty(C6152DH.SyncMtd.getAppVersionName()) ? "" : URLEncoder.encode(C6152DH.SyncMtd.getAppVersionName(), "utf-8");
                    String encode3 = TextUtils.isEmpty(C6152DH.SyncMtd.getManufacturer()) ? "" : URLEncoder.encode(C6152DH.SyncMtd.getManufacturer(), "utf-8");
                    String encode4 = TextUtils.isEmpty(C6152DH.SyncMtd.getModel()) ? "" : URLEncoder.encode(C6152DH.SyncMtd.getModel(), "utf-8");
                    String encode5 = TextUtils.isEmpty(dHResponse.getMIUIVersion()) ? "" : URLEncoder.encode(dHResponse.getMIUIVersion(), "utf-8");
                    String encode6 = TextUtils.isEmpty(C6152DH.SyncMtd.getOSVersionName()) ? "" : URLEncoder.encode(C6152DH.SyncMtd.getOSVersionName(), "utf-8");
                    HashMap<String, Object> m12198b = C5869r.m12202a().m12198b();
                    String str2 = C5731l.m12674a("004Jfmhlhl0m") + encode + ";" + encode2;
                    String str3 = C5731l.m12674a("012$fkjlfkZm3fmFf*edekfeejedkh") + C6152DH.SyncMtd.getOSVersionInt() + ";" + encode6;
                    if (C5879w.m12162b() && i != 3) {
                        deviceKeyFromCache = dHResponse.getDeviceKey();
                    } else {
                        deviceKeyFromCache = dHResponse.getDeviceKeyFromCache(new int[0]);
                    }
                    String str4 = C5731l.m12674a("004:fkglfj%m") + deviceKeyFromCache;
                    String str5 = C5731l.m12674a("003.gmid]m") + encode3 + ";" + encode4;
                    if (!TextUtils.isEmpty(encode5)) {
                        str5 = str5 + ";" + encode5;
                    }
                    String str6 = C5731l.m12674a("003Efihh+m") + dHResponse.getDetailNetworkTypeForStatic() + ";" + dHResponse.getCarrier();
                    String str7 = C5731l.m12674a("005GgdDefQffFm") + Locale.getDefault().toString().replace(C5731l.m12674a("002Wilek"), "-");
                    String str8 = C5731l.m12674a("004!hkgdhjGm") + MobSDK.SDK_VERSION_CODE;
                    String m12674a = C5731l.m12674a("004>fkgljdKm");
                    if (!arrayList.isEmpty()) {
                        int size = arrayList.size();
                        String str9 = m12674a;
                        for (int i2 = 0; i2 < size; i2++) {
                            try {
                                MobProduct mobProduct = (MobProduct) arrayList.get(i2);
                                if (i2 != 0) {
                                    str9 = str9 + ",";
                                }
                                str9 = str9 + mobProduct.getProductTag() + ";" + mobProduct.getSdkver() + ";" + m12198b.get(mobProduct.getProductTag());
                            } catch (Throwable unused) {
                            }
                        }
                        m12674a = str9;
                    }
                    String str10 = CSCenter.getInstance().isCusControllerNotNull() ? "DC/7" : "DC/2";
                    int i3 = i;
                    if (i3 == 1) {
                        str10 = "DC/[DC]";
                    } else if (i3 == 2) {
                        str10 = "DC/[DC2]";
                    }
                    String timezone = C6152DH.SyncMtd.getTimezone();
                    String str11 = TextUtils.isEmpty(timezone) ? "" : C5731l.m12674a("0035fljmei") + timezone;
                    String m12593c = C5745ab.m12602a().m12593c();
                    String str12 = TextUtils.isEmpty(m12593c) ? "TID/" : "TID/" + m12593c;
                    int m12462a = C5791a.m12462a();
                    String str13 = "SVM/" + m12462a;
                    if (C6032d.m11682c()) {
                        if (!C5731l.m12674a("004'fkgljd<m").equals(m12674a)) {
                            m12674a = m12674a + ",";
                        }
                        m12674a = m12674a + "CS;" + m12462a;
                    }
                    String m12135f = C5886x.m12146a().m12135f();
                    strArr[0] = str2 + " " + str3 + " " + str4 + " " + str5 + " " + str6 + " " + str7 + " " + str8 + " " + m12674a + " " + str10 + " " + str11 + " " + str12 + " " + str13 + " " + (TextUtils.isEmpty(m12135f) ? "RD/" : "RD/" + m12135f);
                }
            });
            str = strArr[0];
        }
        return str;
    }
}
