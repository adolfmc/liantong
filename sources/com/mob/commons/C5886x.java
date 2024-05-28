package com.mob.commons;

import android.content.pm.ApplicationInfo;
import android.media.MediaDrm;
import android.os.Build;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5694a;
import com.mob.tools.C6122c;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.p237a.C6040h;
import com.mob.tools.utils.AbstractRunnableC6217h;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mob.commons.x */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5886x {

    /* renamed from: e */
    private static volatile C5886x f14503e;

    /* renamed from: f */
    private HashMap<String, Integer> f14508f;

    /* renamed from: a */
    private volatile String f14504a = null;

    /* renamed from: b */
    private volatile String f14505b = null;

    /* renamed from: c */
    private volatile String f14506c = null;

    /* renamed from: d */
    private volatile String f14507d = null;

    /* renamed from: g */
    private final byte[] f14509g = new byte[0];

    /* renamed from: h */
    private final byte[] f14510h = new byte[0];

    /* renamed from: b */
    public String m12141b() {
        return "2";
    }

    private C5886x() {
    }

    /* renamed from: a */
    public static C5886x m12146a() {
        if (f14503e == null) {
            synchronized (C5886x.class) {
                if (f14503e == null) {
                    f14503e = new C5886x();
                }
            }
        }
        return f14503e;
    }

    /* renamed from: c */
    public String m12139c() {
        if (TextUtils.isEmpty(this.f14505b)) {
            String m12632b = C5741aa.m12650a().m12632b("key_rdt2", (String) null);
            if (!TextUtils.isEmpty(m12632b)) {
                this.f14505b = m12632b;
            }
        }
        return this.f14505b;
    }

    /* renamed from: d */
    public boolean m12137d() {
        if (TextUtils.isEmpty(this.f14505b)) {
            synchronized (this) {
                if (TextUtils.isEmpty(this.f14505b)) {
                    return TextUtils.isEmpty(C5741aa.m12650a().m12632b("key_rdt2", (String) null));
                }
                return false;
            }
        }
        return false;
    }

    /* renamed from: e */
    public synchronized String m12136e() {
        String m12139c;
        m12139c = m12139c();
        if (TextUtils.isEmpty(m12139c)) {
            m12139c = m12131j();
            this.f14505b = m12139c;
            if (!TextUtils.isEmpty(m12139c)) {
                C5741aa.m12650a().m12641a("key_rdt2", m12139c);
            }
        }
        return m12139c;
    }

    /* renamed from: f */
    public String m12135f() {
        String m12139c = m12139c();
        if (TextUtils.isEmpty(m12139c)) {
            m12139c = m12130k();
            this.f14505b = m12139c;
            if (!TextUtils.isEmpty(m12139c)) {
                C5741aa.m12650a().m12641a("key_rdt2", m12139c);
            }
        }
        return m12139c;
    }

    /* renamed from: a */
    public void m12143a(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, this.f14505b)) {
            return;
        }
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("rddd saveRD pre is " + this.f14505b + " cur is " + str, new Object[0]);
        C5741aa.m12650a().m12641a("key_rdt2", str);
    }

    /* renamed from: j */
    private String m12131j() {
        if (!TextUtils.isEmpty(m12133h())) {
            return "12" + m12138c(m12133h());
        } else if (!TextUtils.isEmpty(m12134g())) {
            return "22" + m12138c(m12134g());
        } else if (!TextUtils.isEmpty(m12129l())) {
            return "32" + m12138c(this.f14507d);
        } else {
            return "42" + m12138c(UUID.randomUUID().toString());
        }
    }

    /* renamed from: k */
    private String m12130k() {
        if (!TextUtils.isEmpty(m12133h())) {
            return "12" + m12138c(m12133h());
        }
        return "42" + m12138c(UUID.randomUUID().toString());
    }

    /* renamed from: g */
    public String m12134g() {
        if (TextUtils.isEmpty(this.f14506c)) {
            synchronized (this.f14510h) {
                if (TextUtils.isEmpty(this.f14506c)) {
                    this.f14506c = m12127n();
                }
            }
        }
        return this.f14506c;
    }

    /* renamed from: l */
    private String m12129l() {
        C6152DH.requester(MobSDK.getContext()).getOD().request(new C6152DH.DHResponder() { // from class: com.mob.commons.x.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                String od = dHResponse.getOD();
                List<String> asList = Arrays.asList("00000000-0000-0000-0000-000000000000", "00000000000000000000000000000000");
                C5694a.C5700c m12615i = C5741aa.m12650a().m12615i();
                if (m12615i != null && m12615i.m12755d() != null) {
                    asList = m12615i.m12755d();
                }
                if (TextUtils.isEmpty(od) || asList.contains(od)) {
                    return;
                }
                C5886x.this.f14507d = od;
            }
        });
        return this.f14507d;
    }

    /* renamed from: h */
    public String m12133h() {
        if (TextUtils.isEmpty(this.f14504a)) {
            synchronized (this.f14509g) {
                if (TextUtils.isEmpty(this.f14504a)) {
                    this.f14504a = m12128m();
                    m12140b(this.f14504a);
                }
            }
        }
        return this.f14504a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    private void m12140b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = (HashMap) C5741aa.m12650a().m12645a("key_drds");
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            if (hashMap.containsKey(str)) {
                int intValue = ((Integer) hashMap.get(str)).intValue();
                if (intValue < 100000) {
                    hashMap.put(str, Integer.valueOf(intValue + 1));
                }
            } else {
                hashMap.put(str, 1);
            }
            ArrayList<Map.Entry> arrayList = new ArrayList(hashMap.entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: com.mob.commons.x.2
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
                    return entry2.getValue().compareTo(entry.getValue());
                }
            });
            for (int size = arrayList.size(); size > 7; size--) {
                arrayList.remove(size - 1);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : arrayList) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
            C5741aa.m12650a().m12642a("key_drds", linkedHashMap);
            this.f14508f = new LinkedHashMap();
            int min = Math.min(3, arrayList.size());
            for (int i = 0; i < min; i++) {
                Map.Entry entry2 = (Map.Entry) arrayList.get(i);
                this.f14508f.put(entry2.getKey(), entry2.getValue());
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    /* renamed from: i */
    public HashMap<String, Integer> m12132i() {
        return this.f14508f;
    }

    /* renamed from: c */
    private String m12138c(String str) {
        StringBuilder sb = new StringBuilder(str);
        String manufacturer = C6152DH.SyncMtd.getManufacturer();
        String model = C6152DH.SyncMtd.getModel();
        if (!TextUtils.isEmpty(manufacturer)) {
            sb.append(manufacturer.trim().toUpperCase());
        }
        if (!TextUtils.isEmpty(model)) {
            sb.append(model.trim().toUpperCase());
        }
        return Data.MD5(sb.toString());
    }

    /* renamed from: m */
    private String m12128m() throws Throwable {
        if (Build.VERSION.SDK_INT < 18) {
            return null;
        }
        final String[] strArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        C5892y.f14525c.execute(new AbstractRunnableC6217h() { // from class: com.mob.commons.x.3
            @Override // com.mob.tools.utils.AbstractRunnableC6217h
            /* renamed from: a */
            public void mo10991a() {
                MediaDrm mediaDrm;
                long currentTimeMillis = System.currentTimeMillis();
                String m12203b = C5868q.m12203b("061f2dbieccgfefgfAe3dehigi9eb*ecccDfFgfegWbeLdecbgkecchddchedhigfiehigcecZh9dddb?bIdbghedVbAedecdcegddghcbgdgegd4b2gfgegehihi=bAgeef");
                UUID uuid = new UUID(-1301668207276963122L, -6645017420763422227L);
                MediaDrm mediaDrm2 = null;
                try {
                    try {
                        mediaDrm = new MediaDrm(uuid);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        C6040h.m11645a(MobSDK.getContext()).m11644a(mediaDrm.getClass(), mediaDrm, "native_setup", new Class[]{Object.class, byte[].class, String.class}, new Object[]{new WeakReference(mediaDrm), C5886x.this.m12142a(uuid), m12203b});
                        byte[] propertyByteArray = mediaDrm.getPropertyByteArray("deviceUniqueId");
                        strArr[0] = Data.byteToHex(propertyByteArray, 0, propertyByteArray.length);
                        NLog mobLog = MobLog.getInstance();
                        mobLog.m11342d("rddd wv c " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
                        countDownLatch.countDown();
                        if (Build.VERSION.SDK_INT >= 28) {
                            mediaDrm.close();
                        } else {
                            mediaDrm.release();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        mediaDrm2 = mediaDrm;
                        try {
                            MobLog.getInstance().m11341d(th);
                            countDownLatch.countDown();
                            if (Build.VERSION.SDK_INT >= 28) {
                                if (mediaDrm2 != null) {
                                    mediaDrm2.close();
                                }
                            } else if (mediaDrm2 != null) {
                                mediaDrm2.release();
                            }
                        } catch (Throwable th3) {
                            MediaDrm mediaDrm3 = mediaDrm2;
                            try {
                                countDownLatch.countDown();
                                if (Build.VERSION.SDK_INT >= 28) {
                                    if (mediaDrm3 != null) {
                                        mediaDrm3.close();
                                    }
                                } else if (mediaDrm3 != null) {
                                    mediaDrm3.release();
                                }
                            } catch (Throwable th4) {
                                MobLog.getInstance().m11341d(th4);
                            }
                            throw th3;
                        }
                    }
                } catch (Throwable th5) {
                    MobLog.getInstance().m11341d(th5);
                }
            }
        });
        countDownLatch.await(1L, TimeUnit.SECONDS);
        return strArr[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public byte[] m12142a(UUID uuid) {
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        byte[] bArr = new byte[16];
        for (int i = 0; i < 8; i++) {
            int i2 = (7 - i) * 8;
            bArr[i] = (byte) (mostSignificantBits >>> i2);
            bArr[i + 8] = (byte) (leastSignificantBits >>> i2);
        }
        return bArr;
    }

    /* renamed from: n */
    private String m12127n() {
        final String[] strArr = new String[1];
        if (C5747b.m12585a(C5868q.m12203b("003cff"))) {
            try {
                strArr[0] = C5741aa.m12650a().m12632b("key_pddt", (String) null);
                if (!TextUtils.isEmpty(strArr[0])) {
                    long m12634b = C5741aa.m12650a().m12634b("key_lgpdt", 0L);
                    long j = 604800000;
                    try {
                        j = Long.parseLong(String.valueOf(C5747b.m12583a(C5868q.m12203b("006=egcjegdd=ci"), 604800))) * 1000;
                    } catch (Throwable unused) {
                    }
                    if (System.currentTimeMillis() - m12634b < j) {
                        MobLog.getInstance().m11342d("rddd che p useable", new Object[0]);
                        return strArr[0];
                    }
                }
                if ((C5868q.m12203b("004+ccchccdc").equalsIgnoreCase(C6152DH.SyncMtd.getManufacturer()) && Build.VERSION.SDK_INT <= 25) || (C5868q.m12203b("006g*cfJcLee@eKch").equalsIgnoreCase(C6152DH.SyncMtd.getManufacturer()) && Build.VERSION.SDK_INT <= 22)) {
                    return null;
                }
                final List<String> m12126o = m12126o();
                if (!m12126o.isEmpty()) {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    final StringBuilder sb = new StringBuilder();
                    C6152DH.RequestBuilder requester = C6152DH.requester(MobSDK.getContext());
                    for (String str : m12126o) {
                        requester.getAInfoForPkg(str, 1);
                    }
                    requester.request(new C6152DH.DHResponder() { // from class: com.mob.commons.x.4
                        @Override // com.mob.tools.utils.C6152DH.DHResponder
                        public void onResponse(C6152DH.DHResponse dHResponse) {
                            int i = 0;
                            for (int i2 = 0; i2 < m12126o.size(); i2++) {
                                try {
                                    ApplicationInfo aInfoForPkg = dHResponse.getAInfoForPkg(i2);
                                    if (aInfoForPkg != null) {
                                        sb.append((String) m12126o.get(i2));
                                        sb.append(C6122c.m11361a(aInfoForPkg, (String) m12126o.get(i2)));
                                        i++;
                                    }
                                } finally {
                                    countDownLatch.countDown();
                                }
                            }
                            if (i > 0) {
                                StringBuilder sb2 = sb;
                                sb2.append(Build.BRAND.toUpperCase(Locale.ROOT));
                                sb2.append(Build.MODEL.toUpperCase(Locale.ROOT));
                                sb2.append(Build.MANUFACTURER.toUpperCase(Locale.ROOT));
                                sb.append(i);
                                strArr[0] = Data.MD5(sb.toString());
                                C5741aa.m12650a().m12641a("key_pddt", strArr[0]);
                                C5741aa.m12650a().m12643a("key_lgpdt", System.currentTimeMillis());
                            }
                        }
                    });
                    try {
                        countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
                    } catch (Throwable unused2) {
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return strArr[0];
    }

    /* renamed from: o */
    private List<String> m12126o() {
        final ArrayList arrayList = new ArrayList();
        C6152DH.requester(MobSDK.getContext()).getSA().request(new C6152DH.DHResponder() { // from class: com.mob.commons.x.5
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                if (dHResponse.getSA().isEmpty()) {
                    return;
                }
                Iterator<HashMap<String, String>> it = dHResponse.getSA().iterator();
                while (it.hasNext()) {
                    String str = it.next().get(C5868q.m12203b("003i!ckdd"));
                    if (str != null && !str.contains("com.google.android") && !str.contains("com.miui.packageinstaller")) {
                        arrayList.add(str);
                    }
                }
                Collections.sort(arrayList);
            }
        });
        return arrayList;
    }
}
