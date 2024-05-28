package com.mob.commons;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.mob.MobSDK;
import com.mob.commons.p229a.C5731l;
import com.mob.commons.p231cc.C5791a;
import com.mob.tools.MDP;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.p238b.C6120a;
import com.mob.tools.p238b.InterfaceC6121b;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.FileUtils;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.SQLiteHelper;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.mob.commons.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5782c {

    /* renamed from: a */
    private static C5782c f14254a;

    /* renamed from: b */
    private static volatile SQLiteHelper.SingleTableDB f14255b;

    /* renamed from: a */
    public static synchronized C5782c m12489a() {
        C5782c c5782c;
        synchronized (C5782c.class) {
            if (f14254a == null) {
                f14254a = new C5782c();
            }
            c5782c = f14254a;
        }
        return c5782c;
    }

    private C5782c() {
        try {
            File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), C5857m.f14429a, true);
            if (dataCacheFile.exists() && dataCacheFile.length() > 209715200) {
                dataCacheFile.delete();
                dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), C5857m.f14429a, true);
            }
            String absolutePath = dataCacheFile.getAbsolutePath();
            f14255b = SQLiteHelper.getDatabase(absolutePath, C5868q.m12203b("008XejIchc1ei7eci") + "_1");
            f14255b.addField(C5868q.m12203b("004hIchce)e"), C5868q.m12203b("004he<dbCh"), true);
            f14255b.addField(C5868q.m12203b("004VcbSchc"), C5868q.m12203b("004he;dbBh"), true);
            RunnableC5789b m12473a = RunnableC5789b.m12473a();
            if (m12473a != null) {
                C5731l.m12681a().m12679a(0L, 180, m12473a);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
    }

    /* renamed from: a */
    public void m12488a(long j, HashMap<String, Object> hashMap) {
        boolean m12589a = C5747b.m12589a();
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("DH PD: " + hashMap.get(C5868q.m12203b("004h_cj+ie")) + ", to: " + m12589a, new Object[0]);
        if (m12589a) {
            C5892y.f14526d.execute(RunnableC5786a.m12475b(j, hashMap));
        }
    }

    /* renamed from: com.mob.commons.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class RunnableC5786a implements Runnable {

        /* renamed from: a */
        private static final RunnableC5786a[] f14266a = new RunnableC5786a[3];

        /* renamed from: b */
        private long f14267b;

        /* renamed from: c */
        private HashMap<String, Object> f14268c;

        private RunnableC5786a(long j, HashMap<String, Object> hashMap) {
            this.f14267b = j;
            this.f14268c = hashMap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public static RunnableC5786a m12475b(long j, HashMap<String, Object> hashMap) {
            RunnableC5786a[] runnableC5786aArr = f14266a;
            synchronized (runnableC5786aArr) {
                for (int i = 0; i < 3; i++) {
                    RunnableC5786a runnableC5786a = runnableC5786aArr[i];
                    if (runnableC5786a != null) {
                        runnableC5786a.f14267b = j;
                        if (runnableC5786a.f14268c != null) {
                            runnableC5786a.f14268c.clear();
                        }
                        runnableC5786a.f14268c = hashMap;
                        runnableC5786aArr[i] = null;
                        return runnableC5786a;
                    }
                }
                return new RunnableC5786a(j, hashMap);
            }
        }

        /* renamed from: a */
        private void m12478a() {
            try {
                RunnableC5786a[] runnableC5786aArr = f14266a;
                synchronized (runnableC5786aArr) {
                    for (int i = 0; i < 3; i++) {
                        if (runnableC5786aArr[i] == null) {
                            this.f14267b = 0L;
                            if (this.f14268c != null) {
                                this.f14268c.clear();
                            }
                            this.f14268c = null;
                            runnableC5786aArr[i] = this;
                            return;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C5859o.m12225a(C5859o.m12223a(C5859o.f14436b), new InterfaceC5858n() { // from class: com.mob.commons.c.a.1
                    @Override // com.mob.commons.InterfaceC5858n
                    /* renamed from: a */
                    public boolean mo11219a(FileLocker fileLocker) {
                        C6152DH.requester(MobSDK.getContext()).getDetailNetworkTypeForStatic().request(new C6152DH.DHResponder() { // from class: com.mob.commons.c.a.1.1
                            @Override // com.mob.tools.utils.C6152DH.DHResponder
                            public void onResponse(C6152DH.DHResponse dHResponse) throws Throwable {
                                RunnableC5789b m12473a;
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(C5868q.m12203b("004h=chceMe"), String.valueOf(RunnableC5786a.this.f14267b));
                                if (RunnableC5786a.this.f14268c != null) {
                                    RunnableC5786a.this.f14268c.put(C5868q.m12203b("006cii(ck6eKcj"), C5871t.m12196a());
                                    RunnableC5786a.this.f14268c.put(C5868q.m12203b("006ciii8ckdd"), C6152DH.SyncMtd.getPackageName());
                                    RunnableC5786a.this.f14268c.put(C5868q.m12203b("006ciiVccYeTci"), C6152DH.SyncMtd.getAppVersionName());
                                    long longValue = ((Long) C5747b.m12583a(C5868q.m12203b("010Oeg4h]ci5che4ddcjdhcb"), 0L)).longValue();
                                    if (longValue != 0) {
                                        RunnableC5786a.this.f14268c.put(C5868q.m12203b("010+egVh1ciAche4ddcjdhcb"), Long.valueOf(longValue));
                                    }
                                }
                                contentValues.put(C5868q.m12203b("004*cbTchc"), Base64.encodeToString(Data.AES128Encode(Data.rawMD5(C6152DH.SyncMtd.getManufacturer()), HashonHelper.fromHashMap(RunnableC5786a.this.f14268c).getBytes("utf-8")), 2));
                                SQLiteHelper.insert(C5782c.f14255b, contentValues);
                                long longValue2 = ((Long) C5747b.m12583a(C5868q.m12203b("004>cb>e3cf7i"), 2L)).longValue();
                                if (C5868q.m12203b("004dSdc0de").equals(dHResponse.getDetailNetworkTypeForStatic())) {
                                    longValue2 = 120;
                                }
                                if (!C5747b.m12562c() || (m12473a = RunnableC5789b.m12473a()) == null) {
                                    return;
                                }
                                if (longValue2 <= 0) {
                                    m12473a.run();
                                } else if (C5731l.m12681a().m12677a(longValue2, m12473a)) {
                                } else {
                                    m12473a.m12463c();
                                }
                            }
                        });
                        return false;
                    }
                });
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    /* renamed from: com.mob.commons.c$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RunnableC5789b implements Runnable {

        /* renamed from: b */
        private static final RunnableC5789b[] f14271b = new RunnableC5789b[1];

        /* renamed from: a */
        public boolean f14272a = false;

        /* renamed from: a */
        static /* synthetic */ RunnableC5789b m12473a() {
            return m12464b();
        }

        static {
            f14271b[0] = new RunnableC5789b();
        }

        /* renamed from: b */
        private static RunnableC5789b m12464b() {
            synchronized (f14271b) {
                RunnableC5789b runnableC5789b = f14271b[0];
                if (runnableC5789b != null) {
                    f14271b[0] = null;
                    if (runnableC5789b.f14272a) {
                        runnableC5789b.f14272a = false;
                    }
                    return runnableC5789b;
                }
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public void m12463c() {
            synchronized (f14271b) {
                if (f14271b[0] == null) {
                    f14271b[0] = this;
                }
            }
            this.f14272a = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            C6152DH.requester(MobSDK.getContext()).getDeviceKey().getDetailNetworkTypeForStatic().getDataNtType().request(new C6152DH.DHResponder() { // from class: com.mob.commons.c.b.1
                /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
                    com.mob.commons.p229a.C5731l.m12681a().m12668d();
                 */
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onResponse(com.mob.tools.utils.C6152DH.DHResponse r5) {
                    /*
                        r4 = this;
                        r0 = 50
                        java.lang.String[][] r0 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L42
                        com.mob.commons.c$b r1 = com.mob.commons.C5782c.RunnableC5789b.this     // Catch: java.lang.Throwable -> L42
                        int r1 = com.mob.commons.C5782c.RunnableC5789b.m12469a(r1, r0)     // Catch: java.lang.Throwable -> L42
                    La:
                        if (r1 <= 0) goto L3c
                        com.mob.commons.c$b r2 = com.mob.commons.C5782c.RunnableC5789b.this     // Catch: java.lang.Throwable -> L42
                        android.util.SparseArray r2 = com.mob.commons.C5782c.RunnableC5789b.m12468a(r2, r0, r1, r5)     // Catch: java.lang.Throwable -> L42
                        int r3 = r2.size()     // Catch: java.lang.Throwable -> L42
                        if (r3 != 0) goto L26
                        com.mob.commons.c$b r3 = com.mob.commons.C5782c.RunnableC5789b.this     // Catch: java.lang.Throwable -> L42
                        boolean r3 = r3.f14272a     // Catch: java.lang.Throwable -> L42
                        if (r3 == 0) goto L26
                        com.mob.commons.a.l r5 = com.mob.commons.p229a.C5731l.m12681a()     // Catch: java.lang.Throwable -> L42
                        r5.m12668d()     // Catch: java.lang.Throwable -> L42
                        goto L3c
                    L26:
                        int r3 = r2.size()     // Catch: java.lang.Throwable -> L42
                        if (r3 <= 0) goto L31
                        com.mob.commons.c$b r3 = com.mob.commons.C5782c.RunnableC5789b.this     // Catch: java.lang.Throwable -> L42
                        com.mob.commons.C5782c.RunnableC5789b.m12470a(r3, r2)     // Catch: java.lang.Throwable -> L42
                    L31:
                        int r2 = r0.length     // Catch: java.lang.Throwable -> L42
                        if (r1 >= r2) goto L35
                        goto L3c
                    L35:
                        com.mob.commons.c$b r1 = com.mob.commons.C5782c.RunnableC5789b.this     // Catch: java.lang.Throwable -> L42
                        int r1 = com.mob.commons.C5782c.RunnableC5789b.m12469a(r1, r0)     // Catch: java.lang.Throwable -> L42
                        goto La
                    L3c:
                        com.mob.commons.c$b r5 = com.mob.commons.C5782c.RunnableC5789b.this
                        com.mob.commons.C5782c.RunnableC5789b.m12471a(r5)
                        return
                    L42:
                        r5 = move-exception
                        com.mob.commons.c$b r0 = com.mob.commons.C5782c.RunnableC5789b.this
                        com.mob.commons.C5782c.RunnableC5789b.m12471a(r0)
                        throw r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5782c.RunnableC5789b.C57901.onResponse(com.mob.tools.utils.DH$DHResponse):void");
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
            if (r1 != null) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0063, code lost:
            r1.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0074, code lost:
            if (r1 == null) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0077, code lost:
            return r6;
         */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int m12466a(java.lang.String[][] r12) {
            /*
                r11 = this;
                r0 = 2
                r1 = 0
                r2 = 0
                java.lang.String[] r3 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L6b
                java.lang.String r4 = "004h;chce:e"
                java.lang.String r4 = com.mob.commons.C5868q.m12203b(r4)     // Catch: java.lang.Throwable -> L6b
                r3[r2] = r4     // Catch: java.lang.Throwable -> L6b
                java.lang.String r4 = "004Icb7chc"
                java.lang.String r4 = com.mob.commons.C5868q.m12203b(r4)     // Catch: java.lang.Throwable -> L6b
                r5 = 1
                r3[r5] = r4     // Catch: java.lang.Throwable -> L6b
                java.lang.String r4 = "time desc"
                com.mob.tools.utils.SQLiteHelper$SingleTableDB r6 = com.mob.commons.C5782c.m12482b()     // Catch: java.lang.Throwable -> L6b
                android.database.Cursor r1 = com.mob.tools.utils.SQLiteHelper.query(r6, r3, r1, r1, r4)     // Catch: java.lang.Throwable -> L6b
                if (r1 != 0) goto L29
                if (r1 == 0) goto L28
                r1.close()     // Catch: java.lang.Throwable -> L28
            L28:
                return r2
            L29:
                boolean r3 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L6b
                if (r3 != 0) goto L35
                if (r1 == 0) goto L34
                r1.close()     // Catch: java.lang.Throwable -> L34
            L34:
                return r2
            L35:
                long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L6b
                r6 = r2
            L3a:
                java.lang.String[] r7 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L67
                java.lang.String r8 = r1.getString(r2)     // Catch: java.lang.Throwable -> L67
                r7[r2] = r8     // Catch: java.lang.Throwable -> L67
                java.lang.String r8 = r1.getString(r5)     // Catch: java.lang.Throwable -> L67
                r7[r5] = r8     // Catch: java.lang.Throwable -> L67
                r8 = -1
                r10 = r7[r2]     // Catch: java.lang.Throwable -> L50
                long r8 = java.lang.Long.parseLong(r10)     // Catch: java.lang.Throwable -> L50
            L50:
                int r8 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
                if (r8 > 0) goto L58
                r12[r6] = r7     // Catch: java.lang.Throwable -> L67
                int r6 = r6 + 1
            L58:
                int r7 = r12.length     // Catch: java.lang.Throwable -> L67
                if (r6 >= r7) goto L61
                boolean r7 = r1.moveToNext()     // Catch: java.lang.Throwable -> L67
                if (r7 != 0) goto L3a
            L61:
                if (r1 == 0) goto L77
            L63:
                r1.close()     // Catch: java.lang.Throwable -> L77
                goto L77
            L67:
                r12 = move-exception
                goto L6d
            L69:
                r12 = move-exception
                goto L78
            L6b:
                r12 = move-exception
                r6 = r2
            L6d:
                com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L69
                r0.m11325w(r12)     // Catch: java.lang.Throwable -> L69
                if (r1 == 0) goto L77
                goto L63
            L77:
                return r6
            L78:
                if (r1 == 0) goto L7d
                r1.close()     // Catch: java.lang.Throwable -> L7d
            L7d:
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5782c.RunnableC5789b.m12466a(java.lang.String[][]):int");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public int m12472a(SparseArray<String> sparseArray) {
            try {
                StringBuilder sb = new StringBuilder();
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append('\'');
                    sb.append(sparseArray.valueAt(i));
                    sb.append('\'');
                }
                return SQLiteHelper.delete(C5782c.f14255b, "time in (" + sb.toString() + ")", null);
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
                return 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public SparseArray<String> m12465a(String[][] strArr, int i, C6152DH.DHResponse dHResponse) {
            HashMap hashMap;
            ArrayList arrayList;
            SparseArray<String> sparseArray = new SparseArray<>();
            try {
                hashMap = new HashMap();
                hashMap.put(C5868q.m12203b("004ifch"), Integer.valueOf(C6152DH.SyncMtd.getPlatformCode()));
                hashMap.put(C5868q.m12203b("006IcbTeIccchJbe"), dHResponse.getDeviceKey());
                hashMap.put(C5868q.m12203b("0057cedccb)ef"), C6152DH.SyncMtd.getModel());
                hashMap.put(C5868q.m12203b("0045cbcfchcb"), C5831e.m12319a((MobProduct) null));
                hashMap.put(C5868q.m12203b("011deh[eedccick(hMcjFie"), dHResponse.getDetailNetworkTypeForStatic());
                hashMap.put(C5868q.m12203b("0157cbYchcIdg'eh)eedccickdjcj@ie"), Integer.valueOf(dHResponse.getDataNtType()));
                arrayList = new ArrayList();
                byte[] rawMD5 = Data.rawMD5(C6152DH.SyncMtd.getManufacturer());
                for (int i2 = 0; i2 < i; i2++) {
                    String[] strArr2 = strArr[i2];
                    HashMap fromJson = HashonHelper.fromJson(new String(Data.AES128Decode(rawMD5, Base64.decode(strArr2[1], 2)), "utf-8").trim());
                    sparseArray.put(i2, strArr2[0]);
                    arrayList.add(fromJson);
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
            }
            if (arrayList.isEmpty()) {
                return new SparseArray<>();
            }
            hashMap.put(C5868q.m12203b("0056cb2chcNeg"), arrayList);
            hashMap.put(C5868q.m12203b("005h7dcckBed"), C5745ab.m12602a().m12596b());
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put(C5868q.m12203b("013,dfeg_e$cigjdhcb1edh=chHh7cj"), C5895z.m12120c());
            hashMap2.put(C5868q.m12203b("004?cedcchcb"), C6031c.m11708a(MobSDK.getContext()).m11704d().mo11562ai());
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            if (!"200".equals(String.valueOf(HashonHelper.fromJson(new NetworkHelper().httpPostWithBytes(C5847i.m12275a().m12272a("gclg") + "/v6/gcl", m12467a(HashonHelper.fromHashMap(hashMap)), hashMap2, networkTimeOut)).get(C5868q.m12203b("006BegQhchMcfeg"))))) {
                sparseArray.clear();
            }
            return sparseArray;
        }

        /* renamed from: a */
        private static byte[] m12467a(String str) throws Throwable {
            ByteArrayOutputStream byteArrayOutputStream;
            GZIPOutputStream gZIPOutputStream;
            ByteArrayOutputStream byteArrayOutputStream2;
            DataOutputStream dataOutputStream;
            byte[] m12174c = C5873u.m12174c();
            BufferedOutputStream bufferedOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(gZIPOutputStream);
                        try {
                            bufferedOutputStream2.write(str.getBytes("utf-8"));
                            bufferedOutputStream2.flush();
                            C5873u.m12179a(bufferedOutputStream2, gZIPOutputStream, byteArrayOutputStream);
                            byte[] AES128Encode = Data.AES128Encode(m12174c, byteArrayOutputStream.toByteArray());
                            byte[] encode = new MobRSA(1024).encode(m12174c, new BigInteger("ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", 16), new BigInteger("191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd", 16));
                            try {
                                byteArrayOutputStream2 = new ByteArrayOutputStream();
                                try {
                                    dataOutputStream = new DataOutputStream(byteArrayOutputStream2);
                                } catch (Throwable th) {
                                    th = th;
                                    dataOutputStream = null;
                                }
                                try {
                                    dataOutputStream.writeInt(encode.length);
                                    dataOutputStream.write(encode);
                                    dataOutputStream.writeInt(AES128Encode.length);
                                    dataOutputStream.write(AES128Encode);
                                    dataOutputStream.flush();
                                    C5873u.m12179a(dataOutputStream, byteArrayOutputStream2);
                                    return byteArrayOutputStream2.toByteArray();
                                } catch (Throwable th2) {
                                    th = th2;
                                    C5873u.m12179a(dataOutputStream, byteArrayOutputStream2);
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                byteArrayOutputStream2 = null;
                                dataOutputStream = null;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            bufferedOutputStream = bufferedOutputStream2;
                            C5873u.m12179a(bufferedOutputStream, gZIPOutputStream, byteArrayOutputStream);
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    gZIPOutputStream = null;
                }
            } catch (Throwable th7) {
                th = th7;
                byteArrayOutputStream = null;
                gZIPOutputStream = null;
            }
        }
    }

    /* renamed from: a */
    public static void m12483a(Object... objArr) {
        try {
            C5843h.m12291a().m12290a(13);
            ResHelper.deleteFileAndFolder(m12479b(objArr));
        } catch (Throwable th) {
            C5843h.m12291a().m12288a(4, th);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:41:0x00fc
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: b */
    private static java.io.File m12479b(java.lang.Object... r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5782c.m12479b(java.lang.Object[]):java.io.File");
    }

    /* renamed from: a */
    public static void m12485a(final ArrayList<HashMap<String, Object>> arrayList, final AbstractC6201c<Void> abstractC6201c) throws Throwable {
        if (arrayList != null && !arrayList.isEmpty()) {
            C6152DH.requester(MobSDK.getContext()).getDeviceKey().getMIUIVersion().getAdvertisingID().request(new C6152DH.DHResponder() { // from class: com.mob.commons.c.1
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                public void onResponse(C6152DH.DHResponse dHResponse) {
                    try {
                        File file = new File(MobSDK.getContext().getFilesDir(), C5868q.m12203b("003$eged^f"));
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        final ArrayList arrayList2 = new ArrayList();
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            HashMap hashMap = (HashMap) it.next();
                            Boolean bool = (Boolean) hashMap.get(C5868q.m12203b("002c.eg"));
                            boolean booleanValue = bool != null ? bool.booleanValue() : false;
                            String str = (String) hashMap.get(C5868q.m12203b("002$deFf"));
                            String str2 = (String) hashMap.get("m");
                            String str3 = (String) hashMap.get("args");
                            Object obj = hashMap.get(C5868q.m12203b("0025chcb"));
                            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                                String m12319a = C5831e.m12319a((MobProduct) null);
                                HashMap hashMap2 = new HashMap();
                                hashMap2.put(C5868q.m12203b("004_cbcfchcb"), m12319a);
                                hashMap2.put(C5868q.m12203b("005h(dcck7ed"), C5745ab.m12602a().m12596b());
                                hashMap2.put(C5868q.m12203b("004Ccedcchcb"), C6031c.m11708a(MobSDK.getContext()).m11704d().mo11563ah());
                                hashMap2.put(C5868q.m12203b("0103egcbckfhEe ciegchdc9d"), Integer.valueOf(MobSDK.SDK_VERSION_CODE));
                                hashMap2.put(C5868q.m12203b("006cii[ckAeEcj"), C5871t.m12196a());
                                hashMap2.put(C5868q.m12203b("009cii+diEebCci*eh"), MobSDK.getAppSecret());
                                hashMap2.put(C5868q.m12203b("006Ncbdcce2cWch?d"), MobSDK.getDomain().getDomain());
                                hashMap2.put(C5868q.m12203b("010PdedcciObeJei hhiQeg"), Boolean.valueOf(MobSDK.checkForceHttps()));
                                hashMap2.put(C5868q.m12203b("009Ededcci<beDdh2i?ccgf"), Boolean.valueOf(MobSDK.checkV6()));
                                hashMap2.put(C5868q.m12203b("004ebe:db"), Long.valueOf(((Long) C5747b.m12583a(C5868q.m12203b("004ebeIdb"), 5L)).longValue()));
                                hashMap2.put(C5868q.m12203b("002bMcb"), (String) C5747b.m12583a(C5868q.m12203b("002bYcb"), C5868q.m12203b("006Xgdgdgegegege")));
                                hashMap2.put("usridt", C5895z.m12118e());
                                hashMap2.put(C5868q.m12203b("0022chcb"), obj);
                                if (!TextUtils.isEmpty(str3)) {
                                    hashMap2.put("args", HashonHelper.fromJson(str3));
                                }
                                hashMap2.put(C5868q.m12203b("0082cb%e2ccchDbeOdhcb"), dHResponse.getDeviceKey());
                                hashMap2.put("imei", null);
                                hashMap2.put("imsi", null);
                                hashMap2.put("sno", null);
                                hashMap2.put("ssno", null);
                                hashMap2.put("miui", dHResponse.getMIUIVersion());
                                hashMap2.put(C5868q.m12203b("005]cedccbTef"), C6152DH.SyncMtd.getModel());
                                hashMap2.put(C5868q.m12203b("0079de0cbh>dccicj"), C6152DH.SyncMtd.getManufacturer());
                                hashMap2.put(C5868q.m12203b("005>edci!cd:cb"), C6152DH.SyncMtd.getBrand());
                                hashMap2.put(C5868q.m12203b("005c8cbegchcb"), dHResponse.getAdvertisingID());
                                hashMap2.put(C5868q.m12203b("006cii.ccYeYci"), C6152DH.SyncMtd.getAppVersionName());
                                hashMap2.put("appVerCode", Integer.valueOf(C6152DH.SyncMtd.getAppVersion()));
                                hashMap2.put(C5868q.m12203b("011icbKck+cJddDe?dg7cAceUe"), C6152DH.SyncMtd.getPackageName());
                                hashMap2.put(C5868q.m12203b("005 edegegchcb"), null);
                                hashMap2.put("osint", Integer.valueOf(C6152DH.SyncMtd.getOSVersionInt()));
                                hashMap2.put("osname", C6152DH.SyncMtd.getOSVersionName());
                                hashMap2.put("mdpName", MDP.class.getName());
                                String fromHashMap = HashonHelper.fromHashMap(hashMap2);
                                String checkHttpRequestUrl = NetCommunicator.checkHttpRequestUrl(str);
                                if (!TextUtils.isEmpty(str2)) {
                                    File file2 = new File(file, str2);
                                    if (booleanValue) {
                                        arrayList2.add(file2.getAbsolutePath());
                                    }
                                    C5782c.m12480b(String.valueOf(obj), file2, booleanValue, checkHttpRequestUrl, str2, fromHashMap);
                                }
                            }
                        }
                        FileUtils.deleteFilesInDirWithFilter(file, new FileFilter() { // from class: com.mob.commons.c.1.1
                            @Override // java.io.FileFilter
                            public boolean accept(File file3) {
                                return !arrayList2.contains(file3.getAbsolutePath());
                            }
                        });
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            });
        } else {
            abstractC6201c.mo11088a(null);
        }
    }

    /* renamed from: a */
    public static String m12484a(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iArr.length; i++) {
            String m12154f = C5879w.m12154f();
            if (iArr[i] < m12154f.length()) {
                sb.append((char) (m12154f.charAt(iArr[i]) - 2));
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m12480b(final String str, final File file, final boolean z, final String str2, final String str3, final String str4) {
        new Thread(new Runnable() { // from class: com.mob.commons.c.2
            @Override // java.lang.Runnable
            public void run() {
                FileOutputStream fileOutputStream;
                ByteArrayOutputStream byteArrayOutputStream;
                int i = 13;
                try {
                    try {
                        if (z) {
                            try {
                                if (file.exists() && str3.equals(Data.MD5(file))) {
                                    if (C5782c.m12481b(str, 5, file.getAbsolutePath(), null, str4)) {
                                        return;
                                    }
                                    file.delete();
                                    return;
                                }
                                if (file.exists()) {
                                    file.delete();
                                }
                                try {
                                    fileOutputStream = new FileOutputStream(file);
                                } catch (Throwable th) {
                                    th = th;
                                    fileOutputStream = null;
                                }
                                try {
                                    NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
                                    networkTimeOut.readTimout = 60000;
                                    networkTimeOut.connectionTimeout = 15000;
                                    new NetworkHelper().download(str2, fileOutputStream, networkTimeOut);
                                    C5873u.m12179a(fileOutputStream);
                                    if (file.length() > 0 && TextUtils.equals(str3, Data.MD5(file))) {
                                        if (C5782c.m12481b(str, 7, file.getAbsolutePath(), null, str4)) {
                                            return;
                                        }
                                        file.delete();
                                        return;
                                    } else if (file.exists()) {
                                        file.delete();
                                        return;
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    C5873u.m12179a(fileOutputStream);
                                    if (file.length() > 0 && TextUtils.equals(str3, Data.MD5(file))) {
                                        if (!C5782c.m12481b(str, 7, file.getAbsolutePath(), null, str4)) {
                                            file.delete();
                                        }
                                    } else if (file.exists()) {
                                        file.delete();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                i = 5;
                                C5839g.m12306a().m12304a(5, i, th, str);
                                MobLog.getInstance().m11341d(th);
                                return;
                            }
                        }
                        if (file.exists()) {
                            file.delete();
                        }
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } catch (Throwable th4) {
                            th = th4;
                            byteArrayOutputStream = null;
                        }
                        try {
                            NetworkHelper.NetworkTimeOut networkTimeOut2 = new NetworkHelper.NetworkTimeOut();
                            networkTimeOut2.readTimout = 60000;
                            networkTimeOut2.connectionTimeout = 15000;
                            new NetworkHelper().download(str2, byteArrayOutputStream, networkTimeOut2);
                            C5873u.m12179a(byteArrayOutputStream);
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            if (byteArray.length <= 0 || !TextUtils.equals(str3, Data.MD5(byteArray))) {
                                return;
                            }
                            C5782c.m12481b(str, 9, null, byteArray, str4);
                        } catch (Throwable th5) {
                            th = th5;
                            C5873u.m12179a(byteArrayOutputStream);
                            throw th;
                        }
                    } catch (Throwable th6) {
                        i = 7;
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m12481b(String str, int i, String str2, byte[] bArr, String str3) {
        Method[] methods;
        try {
            boolean z = false;
            Method method = null;
            for (Method method2 : C6120a.class.getMethods()) {
                Annotation[] annotations = method2.getAnnotations();
                if (annotations != null) {
                    int length = annotations.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        Annotation annotation = annotations[i2];
                        if (annotation != null && annotation.annotationType() == InterfaceC6121b.class) {
                            z = true;
                            method = method2;
                            break;
                        }
                        i2++;
                    }
                    if (z) {
                        break;
                    }
                }
            }
            if (bArr != null) {
                C5791a.m12459a(MobSDK.getContext(), bArr, str3, method);
            } else {
                C5791a.m12461a(MobSDK.getContext(), str2, str3, method);
            }
            return true;
        } catch (Throwable th) {
            try {
                C5839g.m12306a().m12304a(6, i, th, str);
                MobLog.getInstance().m11341d(th);
            } catch (Throwable unused) {
            }
            return false;
        }
    }
}
