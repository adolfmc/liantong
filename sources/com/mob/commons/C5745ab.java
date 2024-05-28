package com.mob.commons;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.mob.commons.ab */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5745ab {

    /* renamed from: a */
    private static final String f14155a = C5869r.m12200a("002,ekfl");

    /* renamed from: b */
    private static final String f14156b = C5869r.m12200a("005'fded*jie");

    /* renamed from: c */
    private static final String f14157c = C5869r.m12200a("005Vfded7jOdc$e");

    /* renamed from: d */
    private static final String f14158d = C5869r.m12200a("016FhfhejfhlhihgijhdhjfgLdEfe<c7dcefdi");

    /* renamed from: e */
    private static C5745ab f14159e;

    /* renamed from: f */
    private String f14160f;

    /* renamed from: g */
    private Context f14161g = MobSDK.getContext();

    /* renamed from: h */
    private TreeMap<String, Object> f14162h;

    private C5745ab() {
    }

    /* renamed from: a */
    public static C5745ab m12602a() {
        if (f14159e == null) {
            synchronized (C5745ab.class) {
                if (f14159e == null) {
                    f14159e = new C5745ab();
                }
            }
        }
        return f14159e;
    }

    /* renamed from: b */
    public String m12596b() {
        if (TextUtils.isEmpty(this.f14160f)) {
            synchronized (C5745ab.class) {
                if (TextUtils.isEmpty(this.f14160f)) {
                    return m12592d();
                }
            }
        }
        return this.f14160f;
    }

    /* renamed from: c */
    public String m12593c() {
        String str = this.f14160f;
        return TextUtils.isEmpty(str) ? m12591e() : str;
    }

    /* renamed from: d */
    private String m12592d() {
        this.f14162h = new TreeMap<>();
        String str = null;
        try {
            String m12591e = m12591e();
            boolean m12598a = m12598a(m12590f());
            if (TextUtils.isEmpty(m12591e)) {
                str = m12597a(this.f14162h);
            } else {
                NLog mobLog = MobLog.getInstance();
                mobLog.m11342d("[%s] %s", f14155a, "tk status: " + m12598a);
                str = !m12598a ? m12591e : m12597a(this.f14162h);
            }
            f14159e.f14160f = str;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return str;
    }

    /* renamed from: a */
    private boolean m12598a(HashMap<String, Object> hashMap) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String[] strArr = new String[1];
        C6152DH.requester(MobSDK.getContext()).getDeviceKey().request(new C6152DH.DHResponder() { // from class: com.mob.commons.ab.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                strArr[0] = dHResponse.getDeviceKey();
                countDownLatch.countDown();
            }
        });
        try {
            this.f14162h.put(C5869r.m12200a("007_ef@dci7eddjdk"), C6152DH.SyncMtd.getManufacturer());
            this.f14162h.put(C5869r.m12200a("005AdfeddcOfg"), C6152DH.SyncMtd.getModel());
            this.f14162h.put(C5869r.m12200a("006Ifhdkfhdd$fDdj"), Integer.valueOf(C6152DH.SyncMtd.getOSVersionInt()));
            countDownLatch.await(100L, TimeUnit.MILLISECONDS);
            String str = strArr[0];
            if (!TextUtils.isEmpty(str)) {
                this.f14162h.put(C5869r.m12200a("008%dc%f@dddiPcfKeidc"), str);
            }
            this.f14162h.put(C5869r.m12200a("004.dcdgdidc"), C5831e.m12319a((MobProduct) null));
            JSONObject jSONObject = new JSONObject(this.f14162h);
            String MD5 = Data.MD5(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            TreeMap<String, Object> treeMap = new TreeMap<>();
            treeMap.put(C5869r.m12200a("0106ee]fefIdjOdgEhcdchi"), MD5);
            m12594b(treeMap);
            if (hashMap == null || hashMap.isEmpty() || !MD5.equals((String) hashMap.get(C5869r.m12200a("0109ee'fef8dj*dg6hcdchi")))) {
                return true;
            }
            MobLog.getInstance().m11342d("[%s] %s", f14155a, "No changes");
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().m11336e(th);
            return false;
        }
    }

    /* renamed from: a */
    private String m12597a(TreeMap<String, Object> treeMap) {
        HashMap hashMap;
        String str = null;
        if (!C5747b.m12562c() || treeMap == null || treeMap.isEmpty()) {
            return null;
        }
        try {
            HashMap hashMap2 = new HashMap();
            hashMap2.put(C5869r.m12200a("0073efGdci0eddjdk"), treeMap.get(C5869r.m12200a("0073efGdci0eddjdk")));
            hashMap2.put(C5869r.m12200a("0054dfeddcDfg"), treeMap.get(C5869r.m12200a("0054dfeddcDfg")));
            hashMap2.put(C5869r.m12200a("006.fhdkfhddUf'dj"), treeMap.get(C5869r.m12200a("006.fhdkfhddUf'dj")));
            hashMap2.put(C5869r.m12200a("008<dcTf+dddi>cf(eidc"), treeMap.get(C5869r.m12200a("008<dcTf+dddi>cf(eidc")));
            hashMap2.put(C5869r.m12200a("004Gdcdgdidc"), treeMap.get(C5869r.m12200a("004Gdcdgdidc")));
            HashMap<String, Object> hashMap3 = new HashMap<>();
            hashMap3.put(C5869r.m12200a("006djjAdl.f?dk"), C5871t.m12196a());
            hashMap3.put("m", m12601a(HashonHelper.fromHashMap(hashMap2)));
            HashMap<String, String> hashMap4 = new HashMap<>();
            hashMap4.put(C5869r.m12200a("013RegfhHf0djhkeidcQfei:diGi@dk"), C5895z.m12120c());
            hashMap4.put(C5869r.m12200a("004*dfeddidc"), C6031c.m11708a(MobSDK.getContext()).m11704d().mo11562ai());
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            NetworkHelper networkHelper = new NetworkHelper();
            HashMap fromJson = HashonHelper.fromJson(networkHelper.httpPostNew(C5847i.m12275a().m12272a("gclg") + C5869r.m12200a("007l)edWjfe;didc"), hashMap3, hashMap4, networkTimeOut));
            if (!"200".equals(String.valueOf(fromJson.get(C5869r.m12200a("004c[eddcKf")))) || (hashMap = (HashMap) fromJson.get(C5869r.m12200a("0041dcPdid"))) == null) {
                return null;
            }
            String str2 = (String) hashMap.get(C5869r.m12200a("005i9eddl.fe"));
            try {
                f14159e.f14160f = str2;
                m12595b(str2);
                return str2;
            } catch (Throwable th) {
                th = th;
                str = str2;
                MobLog.getInstance().m11336e(th);
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: a */
    private String m12601a(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        BufferedOutputStream bufferedOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        DataOutputStream dataOutputStream;
        byte[] m12174c = C5873u.m12174c();
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    bufferedOutputStream = new BufferedOutputStream(gZIPOutputStream);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
        }
        try {
            bufferedOutputStream.write(str.getBytes("utf-8"));
            bufferedOutputStream.flush();
            C5873u.m12179a(bufferedOutputStream, gZIPOutputStream, byteArrayOutputStream);
            byte[] AES128Encode = Data.AES128Encode(m12174c, byteArrayOutputStream.toByteArray());
            byte[] encode = new MobRSA(1024).encode(m12174c, new BigInteger("ceeef5035212dfe7c6a0acdc0ef35ce5b118aab916477037d7381f85c6b6176fcf57b1d1c3296af0bb1c483fe5e1eb0ce9eb2953b44e494ca60777a1b033cc07", 16), new BigInteger("191737288d17e660c4b61440d5d14228a0bf9854499f9d68d8274db55d6d954489371ecf314f26bec236e58fac7fffa9b27bcf923e1229c4080d49f7758739e5bd6014383ed2a75ce1be9b0ab22f283c5c5e11216c5658ba444212b6270d629f2d615b8dfdec8545fb7d4f935b0cc10b6948ab4fc1cb1dd496a8f94b51e888dd", 16));
            try {
                byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    dataOutputStream = new DataOutputStream(byteArrayOutputStream2);
                } catch (Throwable th4) {
                    th = th4;
                    dataOutputStream = null;
                }
            } catch (Throwable th5) {
                th = th5;
                byteArrayOutputStream2 = null;
                dataOutputStream = null;
            }
            try {
                dataOutputStream.writeInt(encode.length);
                dataOutputStream.write(encode);
                dataOutputStream.writeInt(AES128Encode.length);
                dataOutputStream.write(AES128Encode);
                dataOutputStream.flush();
                C5873u.m12179a(dataOutputStream, byteArrayOutputStream2);
                return Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2);
            } catch (Throwable th6) {
                th = th6;
                C5873u.m12179a(dataOutputStream, byteArrayOutputStream2);
                throw th;
            }
        } catch (Throwable th7) {
            th = th7;
            bufferedOutputStream2 = bufferedOutputStream;
            C5873u.m12179a(bufferedOutputStream2, gZIPOutputStream, byteArrayOutputStream);
            throw th;
        }
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
    private void m12595b(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = 1
            r2 = 0
            r3 = 2
            android.content.Context r4 = r6.f14161g     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = com.mob.commons.C5745ab.f14156b     // Catch: java.lang.Throwable -> L33
            java.io.File r4 = com.mob.tools.utils.ResHelper.getDataCacheFile(r4, r5)     // Catch: java.lang.Throwable -> L33
            if (r4 == 0) goto L28
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L33
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L33
            java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L26
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L26
            r4.writeUTF(r7)     // Catch: java.lang.Throwable -> L23
            r4.flush()     // Catch: java.lang.Throwable -> L23
            r0 = r4
            goto L29
        L20:
            r7 = move-exception
            r0 = r4
            goto L47
        L23:
            r7 = move-exception
            r0 = r4
            goto L35
        L26:
            r7 = move-exception
            goto L35
        L28:
            r5 = r0
        L29:
            java.io.Closeable[] r7 = new java.io.Closeable[r3]
            r7[r2] = r0
            r7[r1] = r5
            goto L42
        L30:
            r7 = move-exception
            r5 = r0
            goto L47
        L33:
            r7 = move-exception
            r5 = r0
        L35:
            com.mob.tools.log.NLog r4 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L46
            r4.m11341d(r7)     // Catch: java.lang.Throwable -> L46
            java.io.Closeable[] r7 = new java.io.Closeable[r3]
            r7[r2] = r0
            r7[r1] = r5
        L42:
            com.mob.commons.C5873u.m12179a(r7)
            return
        L46:
            r7 = move-exception
        L47:
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r2] = r0
            r3[r1] = r5
            com.mob.commons.C5873u.m12179a(r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5745ab.m12595b(java.lang.String):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: e */
    private java.lang.String m12591e() {
        /*
            r10 = this;
            r0 = 1
            r1 = 0
            r2 = 2
            r3 = 0
            android.content.Context r4 = r10.f14161g     // Catch: java.lang.Throwable -> L45
            java.lang.String r5 = com.mob.commons.C5745ab.f14156b     // Catch: java.lang.Throwable -> L45
            java.io.File r4 = com.mob.tools.utils.ResHelper.getDataCacheFile(r4, r5)     // Catch: java.lang.Throwable -> L45
            boolean r5 = r4.exists()     // Catch: java.lang.Throwable -> L45
            if (r5 == 0) goto L35
            long r5 = r4.length()     // Catch: java.lang.Throwable -> L45
            r7 = 0
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L35
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L45
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L45
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch: java.lang.Throwable -> L32
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L32
            java.lang.String r3 = r4.readUTF()     // Catch: java.lang.Throwable -> L2e
            r9 = r4
            r4 = r3
            r3 = r9
            goto L37
        L2e:
            r6 = move-exception
            goto L48
        L30:
            r4 = move-exception
            goto L5d
        L32:
            r6 = move-exception
            r4 = r3
            goto L48
        L35:
            r4 = r3
            r5 = r4
        L37:
            java.io.Closeable[] r2 = new java.io.Closeable[r2]
            r2[r1] = r3
            r2[r0] = r5
            com.mob.commons.C5873u.m12179a(r2)
            r3 = r4
            goto L58
        L42:
            r4 = move-exception
            r5 = r3
            goto L5d
        L45:
            r6 = move-exception
            r4 = r3
            r5 = r4
        L48:
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L59
            r7.m11341d(r6)     // Catch: java.lang.Throwable -> L59
            java.io.Closeable[] r2 = new java.io.Closeable[r2]
            r2[r1] = r4
            r2[r0] = r5
            com.mob.commons.C5873u.m12179a(r2)
        L58:
            return r3
        L59:
            r3 = move-exception
            r9 = r4
            r4 = r3
            r3 = r9
        L5d:
            java.io.Closeable[] r2 = new java.io.Closeable[r2]
            r2[r1] = r3
            r2[r0] = r5
            com.mob.commons.C5873u.m12179a(r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5745ab.m12591e():java.lang.String");
    }

    /* renamed from: b */
    private void m12594b(TreeMap<String, Object> treeMap) {
        ResHelper.writeToFileNoCompress(ResHelper.getDataCacheFile(this.f14161g, f14157c), m12600a(f14158d, treeMap));
    }

    /* renamed from: f */
    private HashMap<String, Object> m12590f() {
        return m12599a(f14158d, ResHelper.readFromFileNoCompress(ResHelper.getDataCacheFile(this.f14161g, f14157c)));
    }

    /* renamed from: a */
    private byte[] m12600a(String str, TreeMap<String, Object> treeMap) {
        try {
            JSONObject jSONObject = new JSONObject(treeMap);
            return Data.AES128Encode(str, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: a */
    private HashMap<String, Object> m12599a(String str, byte[] bArr) {
        try {
            return HashonHelper.fromJson(Data.AES128Decode(str, bArr));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return new HashMap<>();
        }
    }
}
