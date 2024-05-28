package com.mob.commons;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.tools.C6023a;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.AbstractRunnableC6217h;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.ReflectHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.mob.commons.p */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5860p {

    /* renamed from: a */
    public static volatile boolean f14446a;

    /* renamed from: b */
    private static C5860p f14447b;

    /* renamed from: c */
    private File f14448c;

    /* renamed from: d */
    private BigInteger f14449d = new BigInteger("f53c224aefb38daa0825c1b8ea691b16d2e16db10880548afddd780c6670a091a11dafa954ea4a9483797fda1045d2693a08daa48cf9cedce1e8733b857304cb", 16);

    /* renamed from: e */
    private BigInteger f14450e = new BigInteger("27749621e6ca022469645faed16e8261acf6af822467382d55c24bb9bc02356ab16e76ddc799dc8ba6b4f110411996eeb63505c9dcf969d3fc085d712f0f1a9713b67aa1128d7cc41bda363afb0ec7ade60e542a4e22869395331cc0096de412034551e98bb2629ae1b7168b8bc82006d064ab335d8567283e70beb6a49e9423", 16);

    private C5860p() {
    }

    /* renamed from: a */
    public static synchronized C5860p m12221a() {
        C5860p c5860p;
        synchronized (C5860p.class) {
            if (f14447b == null) {
                f14447b = new C5860p();
            }
            c5860p = f14447b;
        }
        return c5860p;
    }

    /* renamed from: b */
    public void m12214b() {
        MobLog.getInstance().m11342d("[LGSM] Sd last", new Object[0]);
        C5892y.f14525c.execute(new RunnableC5865c());
    }

    /* renamed from: a */
    public void m12218a(int i, String str, int i2, String str2) {
        MobLog.getInstance().m11342d("[LGSM] Sd curr", new Object[0]);
        if (i == 1) {
            new RunnableC5862a().m12212a(i2, i, str, str2).run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m12216a(final Runnable runnable) {
        if (this.f14448c == null) {
            this.f14448c = new File(MobSDK.getContext().getFilesDir(), C5869r.m12200a("005=fd]g2edYc^dl"));
            if (!this.f14448c.exists()) {
                try {
                    this.f14448c.createNewFile();
                } catch (Throwable unused) {
                }
            }
        }
        return C5859o.m12225a(this.f14448c, new InterfaceC5858n() { // from class: com.mob.commons.p.1
            @Override // com.mob.commons.InterfaceC5858n
            /* renamed from: a */
            public boolean mo11219a(FileLocker fileLocker) {
                runnable.run();
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static C6023a m12213b(int i) {
        String m12200a = C5869r.m12200a("005Xfddf?cg(ee");
        return new C6023a(m12200a, C5869r.m12200a("005Xfddf?cg(ee") + "-" + i, 50);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.p$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RunnableC5865c implements Runnable {

        /* renamed from: a */
        private Runnable f14465a;

        private RunnableC5865c() {
            this.f14465a = new AbstractRunnableC6217h() { // from class: com.mob.commons.p.c.1
                @Override // com.mob.tools.utils.AbstractRunnableC6217h
                /* renamed from: a */
                public void mo10991a() {
                    MobLog.getInstance().m11342d("[LGSM] UCLR", new Object[0]);
                    C5860p.m12213b(1).m11821a(new C5864b());
                }
            };
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!C5747b.m12562c()) {
                    MobLog.getInstance().m11342d("[LGSM] ULR Ck nt: FBDN", new Object[0]);
                } else {
                    C6152DH.requester(MobSDK.getContext()).getDetailNetworkTypeForStatic().request(new C6152DH.DHResponder() { // from class: com.mob.commons.p.c.2
                        @Override // com.mob.tools.utils.C6152DH.DHResponder
                        public void onResponse(C6152DH.DHResponse dHResponse) {
                            if (C5869r.m12200a("004e@ed+ef").equals(dHResponse.getDetailNetworkTypeForStatic())) {
                                return;
                            }
                            int intValue = ((Integer) C5747b.m12583a(C5869r.m12200a("004cfGdjdj"), 1)).intValue();
                            NLog mobLog = MobLog.getInstance();
                            mobLog.m11342d("[LGSM] ULR Ck cerr: " + intValue, new Object[0]);
                            if (intValue == 1) {
                                C5860p.m12221a().m12216a(RunnableC5865c.this.f14465a);
                                return;
                            }
                            C5860p.m12213b(1).m11822a(((Long) C5747b.m12583a("cerr_max", 104857600L)).longValue());
                        }
                    });
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    /* renamed from: a */
    public int m12219a(int i, String str) {
        if (MobSDK.getContextSafely() != null && f14446a) {
            Intent intent = new Intent();
            intent.setPackage(C5869r.m12200a("015ce;fdfh!hd=dj'fLfhdcdlfdYgOedee"));
            intent.putExtra(C5869r.m12200a("007jdc:dl)dPeeDf"), MobSDK.getContext().getPackageName());
            intent.putExtra(C5869r.m12200a("008jSdjdieddjdiNi3dk"), i);
            intent.putExtra("ver", MobSDK.SDK_VERSION_CODE);
            intent.putExtra(C5869r.m12200a("003;dffhee"), m12215a(str));
            ReflectHelper.invokeInstanceMethod(MobSDK.getContextSafely(), C5869r.m12200a("013)fh6feLdcfidjed,dWdc cdHfh]i"), new Object[]{intent}, new Class[]{Intent.class}, 0);
        }
        return 0;
    }

    /* renamed from: a */
    private String m12215a(String str) {
        DataOutputStream dataOutputStream;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] m12174c = C5873u.m12174c();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
                dataOutputStream = null;
            }
            try {
                byte[] encode = new MobRSA(1024).encode(m12174c, this.f14449d, this.f14450e);
                dataOutputStream.writeInt(encode.length);
                dataOutputStream.write(encode);
                byte[] AES128Encode = Data.AES128Encode(m12174c, str.getBytes("utf-8"));
                dataOutputStream.writeInt(AES128Encode.length);
                dataOutputStream.write(AES128Encode);
                dataOutputStream.flush();
                C5873u.m12179a(dataOutputStream, byteArrayOutputStream);
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            } catch (Throwable th2) {
                th = th2;
                C5873u.m12179a(dataOutputStream, byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            MobLog.getInstance().m11341d(th3);
            return null;
        }
    }

    /* renamed from: com.mob.commons.p$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C5864b implements C6023a.InterfaceC6027a {

        /* renamed from: a */
        ArrayList<HashMap<String, Object>> f14462a;

        /* renamed from: b */
        int f14463b;

        /* renamed from: c */
        String f14464c;

        private C5864b() {
            this.f14462a = new ArrayList<>();
            this.f14463b = -1;
        }

        @Override // com.mob.tools.C6023a.InterfaceC6027a
        /* renamed from: a */
        public void mo11810a(String str) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("[LGSM] ULL onRd " + str, new Object[0]);
            HashMap<String, Object> fromJson = HashonHelper.fromJson(str);
            try {
                this.f14463b = Integer.parseInt(String.valueOf(fromJson.get(C5869r.m12200a("010+fhdcdlgiCfVdjfhdied)e"))));
            } catch (Throwable unused) {
            }
            this.f14464c = (String) fromJson.get(C5869r.m12200a("006Dfhdcdlek4dRee"));
            this.f14462a.add(fromJson);
        }

        @Override // com.mob.tools.C6023a.InterfaceC6027a
        /* renamed from: a */
        public boolean mo11811a(C6152DH.DHResponse dHResponse) {
            String m12209b;
            String str;
            MobLog.getInstance().m11342d("[LGSM] ULL onUd", new Object[0]);
            HashMap<String, Object> m12210a = m12210a(dHResponse, this.f14463b, this.f14464c);
            m12210a.put(C5869r.m12200a("006f1djdjdffhee"), this.f14462a);
            try {
                String fromHashMap = HashonHelper.fromHashMap(m12210a);
                this.f14462a.clear();
                m12209b = m12209b(fromHashMap);
            } catch (Throwable th) {
                MobLog.getInstance().m11342d("[LGSM] ULL onUd: E", new Object[0]);
                MobLog.getInstance().m11341d(th);
            }
            if (C5869r.m12200a("004e:edQef").equals(dHResponse.getDetailNetworkTypeForStatic())) {
                throw new IllegalStateException("network is disconnected!");
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("m", m12209b);
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 10000;
            networkTimeOut.connectionTimeout = 10000;
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put(C5869r.m12200a("013[egfh_f(djhkeidc)feiPdi3iQdk"), C5895z.m12120c());
            hashMap2.put(C5869r.m12200a("004*dfeddidc"), dHResponse.getODH());
            MobLog.getInstance().m11342d("[LGSM] ULL onUd: Req", new Object[0]);
            String httpPostNew = new NetworkHelper().httpPostNew(C5847i.m12275a().m12272a("el") + "/errlog", hashMap, hashMap2, networkTimeOut);
            MobLog.getInstance().m11342d("[LGSM] ULL onUd: " + String.format("Resp(%s): %s", str, httpPostNew), new Object[0]);
            Object obj = HashonHelper.fromJson(httpPostNew).get(C5869r.m12200a("006Gfh idiKdgfh"));
            return (obj != null ? ((Integer) obj).intValue() : 0) == 200;
        }

        /* renamed from: a */
        private HashMap<String, Object> m12210a(C6152DH.DHResponse dHResponse, int i, String str) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(C5869r.m12200a("003Zdl+f dk"), C5871t.m12196a());
            hashMap.put(C5869r.m12200a("004 dcdgdidc"), C5831e.m12319a((MobProduct) null));
            hashMap.put(C5869r.m12200a("004jgdi"), Integer.valueOf(C6152DH.SyncMtd.getPlatformCode()));
            hashMap.put(C5869r.m12200a("003>fhdcdl"), str);
            hashMap.put(C5869r.m12200a("006Ffhdcdldd:fKdj"), Integer.valueOf(i));
            hashMap.put(C5869r.m12200a("007djjed4df_f"), dHResponse.getAppName());
            hashMap.put(C5869r.m12200a("006djjj.dlee"), C6152DH.SyncMtd.getPackageName());
            hashMap.put(C5869r.m12200a("006djj,ddGfEdj"), String.valueOf(C6152DH.SyncMtd.getAppVersion()));
            hashMap.put(C5869r.m12200a("005KdfeddcOfg"), C6152DH.SyncMtd.getModel());
            if (C5747b.m12572b()) {
                hashMap.put(C5869r.m12200a("008TdcRfKdddi<cf didc"), dHResponse.getDeviceKey());
            }
            hashMap.put(C5869r.m12200a("0069fhdkfhddHfPdj"), String.valueOf(C6152DH.SyncMtd.getOSVersionInt()));
            hashMap.put(C5869r.m12200a("011efi:ffeddjdl iPdk7jf"), dHResponse.getDetailNetworkTypeForStatic());
            return hashMap;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: b */
        private String m12209b(String str) throws Throwable {
            ByteArrayInputStream byteArrayInputStream;
            Throwable th;
            byte[] bArr;
            GZIPOutputStream gZIPOutputStream;
            Throwable th2;
            try {
                bArr = str.getBytes();
                byteArrayInputStream = new ByteArrayInputStream(bArr);
            } catch (Throwable th3) {
                byteArrayInputStream = null;
                th = th3;
                bArr = null;
            }
            try {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    } catch (Throwable th4) {
                        gZIPOutputStream = null;
                        th2 = th4;
                    }
                    try {
                        byte[] bArr2 = new byte[1024];
                        while (true) {
                            int read = byteArrayInputStream.read(bArr2, 0, 1024);
                            if (read != -1) {
                                gZIPOutputStream.write(bArr2, 0, read);
                            } else {
                                gZIPOutputStream.flush();
                                C5873u.m12179a(gZIPOutputStream);
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                byteArrayOutputStream.flush();
                                String encodeToString = Base64.encodeToString(byteArray, 2);
                                C5873u.m12179a(byteArrayOutputStream, byteArrayInputStream);
                                return encodeToString;
                            }
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        C5873u.m12179a(gZIPOutputStream);
                        throw th2;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    bArr = null;
                    C5873u.m12179a(bArr, byteArrayInputStream);
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                C5873u.m12179a(bArr, byteArrayInputStream);
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.p$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RunnableC5862a implements Runnable {

        /* renamed from: a */
        private int f14453a;

        /* renamed from: b */
        private int f14454b;

        /* renamed from: c */
        private String f14455c;

        /* renamed from: d */
        private String f14456d;

        private RunnableC5862a() {
        }

        /* renamed from: a */
        public RunnableC5862a m12212a(int i, int i2, String str, String str2) {
            this.f14453a = i;
            this.f14454b = i2;
            this.f14455c = str;
            this.f14456d = str2;
            return this;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                m12211b(this.f14453a, this.f14454b, this.f14455c, this.f14456d);
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
            }
        }

        /* renamed from: b */
        private void m12211b(final int i, final int i2, final String str, final String str2) {
            MobLog.getInstance().m11342d("[LGSM] SLR: onL", new Object[0]);
            if (C5860p.m12221a().m12216a(new AbstractRunnableC6217h() { // from class: com.mob.commons.p.a.1
                @Override // com.mob.tools.utils.AbstractRunnableC6217h
                /* renamed from: a */
                public void mo10991a() throws Throwable {
                    MobLog.getInstance().m11342d("[LGSM] SLR: Ins", new Object[0]);
                    HashMap hashMap = new HashMap();
                    hashMap.put(C5869r.m12200a("010MfhdcdlgiCfPdjfhdiedMe"), Integer.valueOf(i));
                    hashMap.put(C5869r.m12200a("006Pfhdcdlek[dFee"), str);
                    hashMap.put(C5869r.m12200a("004i!dk9jf"), Integer.valueOf(i2));
                    hashMap.put(C5869r.m12200a("005fCdjdj?di"), Long.valueOf(System.currentTimeMillis()));
                    String encode = URLEncoder.encode(str2);
                    if (TextUtils.isEmpty(encode)) {
                        encode = str2;
                    }
                    hashMap.put(C5869r.m12200a("0035dffhee"), Base64.encodeToString(encode.getBytes("utf-8"), 2));
                    hashMap.put(C5869r.m12200a("005i(didf!fAfh"), 1);
                    NLog mobLog = MobLog.getInstance();
                    mobLog.m11342d("[LGSM] W l " + hashMap, new Object[0]);
                    C5860p.m12213b(i2).m11818a(HashonHelper.fromHashMap(hashMap));
                }
            }) && C5879w.m12162b()) {
                MobLog.getInstance().m11342d("[LGSM] SLR: U", new Object[0]);
                C5892y.f14525c.execute(new RunnableC5865c());
            }
        }
    }
}
