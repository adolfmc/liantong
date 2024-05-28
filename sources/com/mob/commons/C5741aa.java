package com.mob.commons;

import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.commons.C5694a;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.AbstractC6218i;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.SharePrefrenceHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.mob.commons.aa */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5741aa {

    /* renamed from: s */
    private static C5741aa f14152s;

    /* renamed from: t */
    private SharePrefrenceHelper f14153t;

    /* renamed from: n */
    private static final String f14147n = C5731l.m12674a("011Yegfegfei-dEfeegegfeGfYgi");

    /* renamed from: a */
    public static final String f14134a = C5731l.m12674a("009:emJg>eleiedejffNek");

    /* renamed from: b */
    public static final String f14135b = C5731l.m12674a("010'emTgBeleiPf<eh;kJek!d;ed");

    /* renamed from: c */
    public static final String f14136c = C5731l.m12674a("009)em-g^eleiBf*eh?heh");

    /* renamed from: d */
    public static final String f14137d = C5731l.m12674a("010HemPg3eleigiXf!eh1heh");

    /* renamed from: e */
    public static final String f14138e = C5731l.m12674a("011^em-g<elei%kkBeiffek)j1ed");

    /* renamed from: f */
    public static final String f14139f = C5731l.m12674a("031KemCg+elei2fgXfdFj7eiehUkhRfeJePedei@ekkHei)edj'ejee!g4ei>j)ejeg<g");

    /* renamed from: g */
    public static final String f14140g = C5731l.m12674a("025>emOgMeleigfehfgfg.g3ek]g5edei:h8feAdej)ejfe^fNeiegedij");

    /* renamed from: h */
    public static final String f14141h = C5731l.m12674a("038Rem-g,eleiGfgUfd<jWeiehWkh@fe,e]edeigfehfgfg:g7ek4gNedei%h-feVdej*ejfeQf>eiYjXejegZg");

    /* renamed from: i */
    public static final String f14142i = C5731l.m12674a("014FggejfgejeiVhe2giBj%eiejRfLfgfe");

    /* renamed from: j */
    public static final String f14143j = C5731l.m12674a("018LemZg eleiggejfgejeiIhTejgiWj4ei6ie1giWi");

    /* renamed from: k */
    public static final String f14144k = C5731l.m12674a("030'em*gDeleiGfgOfd[jXeieh%khHfe1eQedeiggejfgejei8hMejgi6jSei4j%ejegEg");

    /* renamed from: l */
    public static final String f14145l = C5731l.m12674a("012*emEgIeleigiggej0jdig(gi");

    /* renamed from: m */
    public static final String f14146m = C5731l.m12674a("022?emTgEeleigiggejWjdigBgiei@jMejeg;g@giMjeAeg>k");

    /* renamed from: o */
    private static final String f14148o = C5731l.m12674a("019Zem gJelei(ekk:ei=edjHejee=g1ei,j0ejegWg");

    /* renamed from: p */
    private static final String f14149p = C5731l.m12674a("012Lem@g_eleiQdieffgh^gi");

    /* renamed from: q */
    private static AtomicBoolean f14150q = new AtomicBoolean(false);

    /* renamed from: r */
    private static AtomicBoolean f14151r = new AtomicBoolean(false);

    private C5741aa() {
        if (this.f14153t == null) {
            this.f14153t = new SharePrefrenceHelper(MobSDK.getContext());
            this.f14153t.open(f14147n, 1);
        }
    }

    /* renamed from: a */
    public static synchronized C5741aa m12650a() {
        C5741aa c5741aa;
        synchronized (C5741aa.class) {
            if (f14152s == null) {
                f14152s = new C5741aa();
            }
            c5741aa = f14152s;
        }
        return c5741aa;
    }

    /* renamed from: b */
    public static boolean m12637b() {
        if (MobSDK.getContext() != null) {
            if (SharePrefrenceHelper.isMpfFileExist(MobSDK.getContext(), f14147n, 1)) {
                return true;
            }
            return SharePrefrenceHelper.isMobSpFileExist(MobSDK.getContext(), f14147n, 1);
        }
        return false;
    }

    /* renamed from: a */
    public void m12643a(String str, long j) {
        this.f14153t.putLong(str, Long.valueOf(j));
    }

    /* renamed from: b */
    public long m12634b(String str, long j) {
        return this.f14153t.getLong(str, j);
    }

    /* renamed from: a */
    public void m12644a(String str, int i) {
        this.f14153t.putInt(str, Integer.valueOf(i));
    }

    /* renamed from: b */
    public int m12635b(String str, int i) {
        return this.f14153t.getInt(str, i);
    }

    /* renamed from: a */
    public void m12640a(String str, boolean z) {
        this.f14153t.putBoolean(str, Boolean.valueOf(z));
    }

    /* renamed from: b */
    public boolean m12631b(String str, boolean z) {
        return this.f14153t.getBoolean(str, z);
    }

    /* renamed from: a */
    public void m12641a(String str, String str2) {
        if (str2 == null) {
            this.f14153t.remove(str);
        } else {
            this.f14153t.putString(str, str2);
        }
    }

    /* renamed from: b */
    public String m12632b(String str, String str2) {
        return this.f14153t.getString(str, str2);
    }

    /* renamed from: a */
    public void m12642a(String str, Object obj) {
        this.f14153t.put(str, obj);
    }

    /* renamed from: a */
    public Object m12645a(String str) {
        return this.f14153t.get(str);
    }

    /* renamed from: b */
    public void m12633b(String str, Object obj) {
        this.f14153t.put(str, obj);
    }

    /* renamed from: c */
    public Object m12627c(String str, Object obj) {
        return this.f14153t.get(str, obj);
    }

    /* renamed from: b */
    public void m12636b(String str) {
        this.f14153t.remove(str);
    }

    /* renamed from: c */
    public void m12628c(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                str = Base64.encodeToString(Data.AES128Encode(m12605s(), str), 0);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        m12641a(f14145l, str);
    }

    /* renamed from: c */
    public String m12629c() {
        String m12632b = m12632b(f14145l, (String) null);
        if (TextUtils.isEmpty(m12632b)) {
            return m12632b;
        }
        try {
            String m12605s = m12605s();
            return Data.AES128PaddingDecode(m12605s.getBytes("UTF-8"), Base64.decode(m12632b, 0));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return m12632b;
        }
    }

    /* renamed from: d */
    public void m12624d(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                str = Base64.encodeToString(Data.AES128Encode(m12605s(), str), 0);
                m12643a(f14146m, System.currentTimeMillis());
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        m12641a("key_gfrt", str);
    }

    /* renamed from: d */
    public String m12625d() {
        String m12632b = m12632b("key_gfrt", (String) null);
        if (TextUtils.isEmpty(m12632b)) {
            return m12632b;
        }
        try {
            String m12605s = m12605s();
            return Data.AES128PaddingDecode(m12605s.getBytes("UTF-8"), Base64.decode(m12632b, 0));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return m12632b;
        }
    }

    /* renamed from: e */
    public void m12622e() {
        m12628c((String) null);
        m12624d((String) null);
    }

    /* renamed from: s */
    private static String m12605s() {
        return Data.MD5(C6152DH.SyncMtd.getModel());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: f */
    public HashMap<Long, Long> m12619f() {
        HashMap fromJson;
        String string = this.f14153t.getString(f14148o);
        HashMap<Long, Long> hashMap = new HashMap<>();
        if (!TextUtils.isEmpty(string) && (fromJson = HashonHelper.fromJson(string)) != null && !fromJson.isEmpty()) {
            for (Map.Entry entry : fromJson.entrySet()) {
                if (entry != null) {
                    hashMap.put(Long.valueOf(Long.parseLong((String) entry.getKey())), entry.getValue());
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public void m12638a(HashMap<Long, Long> hashMap) {
        if (hashMap != null && !hashMap.isEmpty()) {
            HashMap hashMap2 = new HashMap();
            for (Map.Entry<Long, Long> entry : hashMap.entrySet()) {
                if (entry != null) {
                    hashMap2.put(String.valueOf(entry.getKey()), entry.getValue());
                }
            }
            this.f14153t.putString(f14148o, HashonHelper.fromHashMap(hashMap2));
            return;
        }
        this.f14153t.remove(f14148o);
    }

    /* renamed from: g */
    public HashMap<String, Object> m12617g() {
        String m12632b = m12632b(f14149p, (String) null);
        if (TextUtils.isEmpty(m12632b)) {
            return null;
        }
        return HashonHelper.fromJson(m12632b);
    }

    /* renamed from: b */
    public void m12630b(HashMap<String, Object> hashMap) {
        m12641a(f14149p, HashonHelper.fromHashMap(hashMap));
    }

    /* renamed from: h */
    public int m12616h() {
        return m12635b("key_mstrgy", 0);
    }

    /* renamed from: a */
    public void m12649a(int i) {
        if (i >= 0) {
            m12644a("key_mstrgy", i);
        }
    }

    /* renamed from: i */
    public C5694a.C5700c m12615i() {
        return C5694a.C5700c.m12766a(m12632b("key_duid_param_blacklist", (String) null));
    }

    /* renamed from: a */
    public void m12646a(C5694a.C5700c c5700c) {
        m12641a("key_duid_param_blacklist", c5700c != null ? c5700c.m12776a() : null);
    }

    /* renamed from: j */
    public C5694a.C5697a m12614j() {
        try {
            String m12632b = m12632b("key_duid_entity", (String) null);
            if (!TextUtils.isEmpty(m12632b)) {
                return C5694a.C5697a.m12795a(Data.AES128Decode(C6152DH.SyncMtd.getModel(), Base64.decode(m12632b, 0)));
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return null;
    }

    /* renamed from: a */
    public void m12647a(C5694a.C5697a c5697a) {
        String str = null;
        if (c5697a != null) {
            try {
                str = c5697a.m12797a();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return;
            }
        }
        m12641a("key_duid_entity", Base64.encodeToString(Data.AES128Encode(C6152DH.SyncMtd.getModel(), str), 0));
    }

    /* renamed from: k */
    public String m12613k() {
        return m12632b("key_chd_ak", (String) null);
    }

    /* renamed from: e */
    public void m12621e(String str) {
        m12641a("key_chd_ak", str);
    }

    /* renamed from: l */
    public String m12612l() {
        return m12632b("key_chd_as", (String) null);
    }

    /* renamed from: f */
    public void m12618f(String str) {
        m12641a("key_chd_as", str);
    }

    /* renamed from: m */
    public HashMap<String, HashMap<String, ArrayList<String>>> m12611m() {
        return HashonHelper.fromJson(m12632b("key_chd_busi_dm", (String) null));
    }

    /* renamed from: c */
    public void m12626c(HashMap<String, HashMap<String, ArrayList<String>>> hashMap) {
        m12641a("key_chd_busi_dm", HashonHelper.fromHashMap(hashMap));
    }

    /* renamed from: n */
    public HashMap<String, String> m12610n() {
        return HashonHelper.fromJson(m12632b("key_ckd_busi_dm", (String) null));
    }

    /* renamed from: d */
    public void m12623d(HashMap<String, String> hashMap) {
        m12641a("key_ckd_busi_dm", HashonHelper.fromHashMap(hashMap));
    }

    /* renamed from: o */
    public ArrayList<String> m12609o() {
        HashMap fromJson = HashonHelper.fromJson(m12632b("key_chd_prx_dm", (String) null));
        if (fromJson != null && !fromJson.isEmpty()) {
            return (ArrayList) fromJson.get(C5731l.m12674a("008WfgIe(em8ghMejgi*j"));
        }
        return new ArrayList<>();
    }

    /* renamed from: a */
    public void m12639a(ArrayList<String> arrayList) {
        m12641a("key_chd_prx_dm", HashonHelper.fromObject(arrayList));
    }

    /* renamed from: p */
    public HashMap<String, Long> m12608p() {
        return HashonHelper.fromJson(m12632b("key_dm_ck_tm", (String) null));
    }

    /* renamed from: e */
    public void m12620e(HashMap<String, Long> hashMap) {
        m12641a("key_dm_ck_tm", HashonHelper.fromHashMap(hashMap));
    }

    /* renamed from: q */
    public long m12607q() {
        return m12634b("key_fst_lnch_tm", 0L);
    }

    /* renamed from: a */
    public void m12648a(long j) {
        m12643a("key_fst_lnch_tm", j);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.mob.commons.aa$1] */
    /* renamed from: r */
    public static void m12606r() {
        if (f14150q.compareAndSet(false, true)) {
            new AbstractC6218i(C5731l.m12674a("004_gljlilhf")) { // from class: com.mob.commons.aa.1
                @Override // com.mob.tools.utils.AbstractC6218i
                /* renamed from: a */
                public void mo10997a() {
                    synchronized (C5859o.f14443i) {
                        try {
                            C5859o.f14443i.wait(600000L);
                            C5843h.m12291a().m12290a(11);
                            ConcurrentHashMap<String, Object> m12554e = C5747b.m12554e();
                            if (m12554e != null && m12554e.size() > 0) {
                                C5843h.m12291a().m12290a(12);
                                Object obj = m12554e.get("h");
                                Object obj2 = m12554e.get("k");
                                Object obj3 = m12554e.get(C5731l.m12674a("001Wgf"));
                                Object obj4 = m12554e.get("s");
                                Object obj5 = m12554e.get(C5731l.m12674a("002df"));
                                Object obj6 = m12554e.get(C5731l.m12674a("002:fgVf"));
                                m12554e.clear();
                                C5782c.m12483a(obj, obj2, obj3, obj4, obj5, obj6);
                            }
                        }
                    }
                }
            }.start();
        }
        m12604t();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.mob.commons.aa$2] */
    /* renamed from: t */
    private static void m12604t() {
        if (f14151r.compareAndSet(false, true)) {
            new AbstractC6218i("DS-W") { // from class: com.mob.commons.aa.2
                @Override // com.mob.tools.utils.AbstractC6218i
                /* renamed from: a */
                public void mo10997a() {
                    synchronized (C5859o.f14444j) {
                        try {
                            C5859o.f14444j.wait();
                            ConcurrentHashMap<String, Object> m12552f = C5747b.m12552f();
                            m12552f.clear();
                            C5782c.m12485a((ArrayList) m12552f.get(C5731l.m12674a("002hj")), new AbstractC6201c<Void>() { // from class: com.mob.commons.aa.2.1
                                @Override // com.mob.tools.utils.AbstractC6201c
                                /* renamed from: a  reason: avoid collision after fix types in other method */
                                public void mo11088a(Void r1) {
                                }
                            });
                        }
                    }
                }
            }.start();
        }
    }
}
