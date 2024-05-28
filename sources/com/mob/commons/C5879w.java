package com.mob.commons;

import android.os.Process;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.C6119b;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.p238b.C6120a;
import com.mob.tools.utils.AbstractC6218i;
import com.mob.tools.utils.AbstractRunnableC6217h;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.HashonHelper;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.w */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5879w {

    /* renamed from: a */
    private static volatile boolean f14492a = true;

    /* renamed from: b */
    private static AtomicInteger f14493b = new AtomicInteger(-1);

    /* renamed from: c */
    private static AtomicBoolean f14494c = new AtomicBoolean(false);

    /* renamed from: d */
    private static AtomicBoolean f14495d = new AtomicBoolean(false);

    /* renamed from: e */
    private static C5878v f14496e = new C5878v();

    /* renamed from: f */
    private static volatile String f14497f;

    /* renamed from: f */
    public static String m12154f() {
        return "ecpgnjvr<1fxsowakt{mzqihWPKUVCN0dy2uDJFH|LYZGTXERQO:438l7;/6MI>\"@A?9[\\)_]5=.(S'~盺朼-";
    }

    /* renamed from: a */
    public static void m12165a(final boolean z) {
        C5892y.f14525c.execute(new AbstractRunnableC6217h() { // from class: com.mob.commons.w.1
            @Override // com.mob.tools.utils.AbstractRunnableC6217h
            /* renamed from: a */
            public void mo10991a() {
                C6120a.f14983b.set(true);
                C6119b.m11367a();
                if (!TextUtils.isEmpty("M-")) {
                    Thread currentThread = Thread.currentThread();
                    currentThread.setName("M-" + C5868q.m12203b("004>fjhjgjge"));
                }
                int m12635b = C5741aa.m12637b() ? C5741aa.m12650a().m12635b(C5741aa.f14138e, -1) : -1;
                if (C5879w.f14493b.get() == -1) {
                    C5879w.f14493b.set(m12635b);
                }
                if (C5879w.f14493b.get() == 1) {
                    C5879w.m12159b(true, z);
                } else {
                    C5879w.m12159b(false, z);
                }
                NLog mobLog = MobLog.getInstance();
                StringBuilder sb = new StringBuilder();
                sb.append(z ? C5868q.m12203b("002JciJe") : "");
                sb.append("init cfg over. py ");
                sb.append(C5879w.f14493b.get());
                mobLog.m11342d(sb.toString(), new Object[0]);
                C6120a.f14983b.set(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m12159b(boolean z, boolean z2) {
        if (!z2) {
            f14496e.m12169a();
        }
        if (TextUtils.isEmpty(C5868q.f14468a)) {
            String m12613k = C5741aa.m12650a().m12613k();
            if (TextUtils.isEmpty(m12613k)) {
                m12613k = m12151i();
            }
            if (!TextUtils.isEmpty(m12613k)) {
                C5868q.f14470c = m12613k;
                C5741aa.m12650a().m12621e(m12613k);
            }
        } else {
            C5868q.f14470c = C5868q.f14468a;
            C5741aa.m12650a().m12621e(C5868q.f14468a);
        }
        if (TextUtils.isEmpty(C5868q.f14469b)) {
            String m12612l = C5741aa.m12650a().m12612l();
            if (!TextUtils.isEmpty(m12612l)) {
                C5868q.f14471d = m12612l;
            }
        } else {
            C5868q.f14471d = C5868q.f14469b;
            C5741aa.m12650a().m12618f(C5868q.f14469b);
        }
        if (!z) {
            if (z2) {
                return;
            }
            f14496e.m12168b();
            return;
        }
        CountDownLatch m12153g = m12153g();
        MobLog.getInstance().m11342d(C6152DH.SyncMtd.isInMainProcess() ? "main" : "sub", new Object[0]);
        if (!z2) {
            m12166a(m12153g);
            return;
        }
        C5895z.m12124a();
        C5747b.m12549h();
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.mob.commons.w$2] */
    /* renamed from: a */
    public static void m12166a(final CountDownLatch countDownLatch) {
        if (f14494c.compareAndSet(false, true)) {
            if (C5741aa.m12650a().m12607q() == 0) {
                C5741aa.m12650a().m12648a(System.currentTimeMillis());
            }
            C5868q.m12207a(MobSDK.getContext());
            m12149k();
            m12148l();
            C5895z.m12124a();
            C5847i.m12275a().m12267b();
            C5860p.m12221a().m12214b();
            new AbstractC6218i("PY-C") { // from class: com.mob.commons.w.2
                @Override // com.mob.tools.utils.AbstractC6218i
                /* renamed from: a */
                public void mo10997a() {
                    C6120a.f14983b.set(true);
                    NLog mobLog = MobLog.getInstance();
                    mobLog.m11342d("g lk st: " + Process.myPid(), new Object[0]);
                    boolean m12225a = C5859o.m12225a(C5859o.m12223a(C5859o.f14441g), new InterfaceC5858n() { // from class: com.mob.commons.w.2.1
                        @Override // com.mob.commons.InterfaceC5858n
                        /* renamed from: a */
                        public boolean mo11219a(FileLocker fileLocker) {
                            NLog mobLog2 = MobLog.getInstance();
                            mobLog2.m11342d("g lk pd: " + Process.myPid() + ", proc st", new Object[0]);
                            long currentTimeMillis = System.currentTimeMillis();
                            C5741aa.m12606r();
                            C5747b.m12576a(countDownLatch);
                            NLog mobLog3 = MobLog.getInstance();
                            mobLog3.m11342d("g lk pd: " + Process.myPid() + ", proc ed, dur: " + (System.currentTimeMillis() - currentTimeMillis) + ", release: y", new Object[0]);
                            return false;
                        }
                    });
                    NLog mobLog2 = MobLog.getInstance();
                    mobLog2.m11342d("g lk res: " + m12225a + Process.myPid(), new Object[0]);
                    C6120a.f14983b.set(false);
                }
            }.start();
        }
    }

    /* renamed from: k */
    private static void m12149k() {
        try {
            ServerSocketChannel open = ServerSocketChannel.open();
            open.configureBlocking(false);
            open.socket().bind(new InetSocketAddress(37926));
            C5860p.f14446a = false;
            open.close();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: l */
    private static void m12148l() {
        C5855l.m12246a().m12244a(new InterfaceC5854k() { // from class: com.mob.commons.w.3
            @Override // com.mob.commons.InterfaceC5854k
            /* renamed from: a */
            public void mo12147a(boolean z, boolean z2, long j) {
                if (z) {
                    MobLog.getInstance().m11342d("fg.", new Object[0]);
                    boolean unused = C5879w.f14492a = true;
                    return;
                }
                MobLog.getInstance().m11342d("bg.", new Object[0]);
                boolean unused2 = C5879w.f14492a = false;
            }
        });
    }

    /* renamed from: a */
    public static boolean m12167a() {
        return f14492a;
    }

    /* renamed from: b */
    public static boolean m12162b() {
        return f14493b.get() == 1;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.mob.commons.w$4] */
    /* renamed from: b */
    public static void m12161b(final boolean z) {
        f14493b.set(z ? 1 : 0);
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("submit py: " + z, new Object[0]);
        new AbstractC6218i(C5868q.m12203b("004Lfjhjgjgd")) { // from class: com.mob.commons.w.4
            @Override // com.mob.tools.utils.AbstractC6218i
            /* renamed from: a */
            public void mo10997a() {
                int m12155e = C5879w.m12155e();
                C5741aa.m12650a().m12644a(C5741aa.f14138e, z ? 1 : 0);
                if (!z || m12155e == 1) {
                    return;
                }
                CountDownLatch m12153g = C5879w.m12153g();
                MobLog.getInstance().m11342d(C6152DH.SyncMtd.isInMainProcess() ? "main" : "sub", new Object[0]);
                C5879w.m12166a(m12153g);
                C6152DH.requester(MobSDK.getContext()).getDetailNetworkTypeForStatic().request(new C6152DH.DHResponder() { // from class: com.mob.commons.w.4.1
                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                    public void onResponse(C6152DH.DHResponse dHResponse) {
                        try {
                            C5879w.m12160b(z, dHResponse.getDetailNetworkTypeForStatic());
                        } catch (Throwable th) {
                            MobLog.getInstance().m11341d(th);
                            try {
                                C5879w.m12160b(z, dHResponse.getDetailNetworkTypeForStatic());
                            } catch (Throwable th2) {
                                MobLog.getInstance().m11341d(th2);
                            }
                        }
                    }
                });
            }
        }.start();
    }

    /* renamed from: c */
    public static int m12158c() {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("get py grtd status mem: " + f14493b.get(), new Object[0]);
        return f14493b.get();
    }

    /* renamed from: d */
    public static int m12156d() {
        int m12158c = m12158c();
        return m12158c != -1 ? m12158c : m12155e();
    }

    /* renamed from: e */
    public static int m12155e() {
        int m12635b = C5741aa.m12637b() ? C5741aa.m12650a().m12635b(C5741aa.f14138e, -1) : -1;
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("get py grtd status cac: " + m12635b, new Object[0]);
        return m12635b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m12160b(boolean z, String str) throws Throwable {
        HashMap<String, Object> m12195a = C5871t.m12195a(str);
        m12195a.put(C5868q.m12203b("009Mchegdkddci^eeBfjMi"), String.valueOf(z));
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(C5868q.m12203b("003JckJe9cj"), C5871t.m12196a());
        hashMap.put(C5868q.m12203b("0138dfegTe5cigjdhcbAedh7chZh'cj"), C5895z.m12117f());
        String httpGet = new NetworkHelper().httpGet(C5847i.m12275a().m12272a("gclg") + C5868q.m12203b("036kiZcichcc%cbCcjAkiXdc:f ch[b7cjQkc@cf6hg+dccichfb?chFchdc!dkUeg2hchLcfeg"), m12195a, hashMap);
        MobLog.getInstance().m11342d("RS sp: " + httpGet, new Object[0]);
        HashMap fromJson = HashonHelper.fromJson(httpGet);
        if (fromJson == null) {
            throw new Throwable("RS is illegal: " + httpGet);
        } else if ("200".equals(String.valueOf(fromJson.get(C5868q.m12203b("004b!dccb3e"))))) {
        } else {
            throw new Throwable("RS code is not 200: " + httpGet);
        }
    }

    /* renamed from: g */
    public static CountDownLatch m12153g() {
        if (!f14495d.getAndSet(true)) {
            return C6031c.m11708a(MobSDK.getContext()).m11709a();
        }
        return new CountDownLatch(0);
    }

    /* renamed from: h */
    public static boolean m12152h() {
        String m12196a = C5871t.m12196a();
        return (TextUtils.isEmpty(m12196a) || TextUtils.isEmpty(m12196a.trim()) || TextUtils.equals(m12196a, m12151i())) ? false : true;
    }

    /* renamed from: i */
    public static String m12151i() {
        if (f14497f == null) {
            String str = null;
            try {
                String absolutePath = MobSDK.getContext().getFilesDir().getAbsolutePath();
                if (!TextUtils.isEmpty(absolutePath)) {
                    String substring = absolutePath.substring(0, absolutePath.lastIndexOf(C5868q.m12203b("001k")));
                    if (!TextUtils.isEmpty(substring)) {
                        str = substring.substring(substring.lastIndexOf(C5868q.m12203b("001k")) + 1);
                    }
                }
                if (!TextUtils.isEmpty(str)) {
                    String CRC32 = Data.CRC32(str.getBytes("utf-8"));
                    if (!TextUtils.isEmpty(CRC32)) {
                        String byteToHex = Data.byteToHex(CRC32.getBytes());
                        if (!TextUtils.isEmpty(byteToHex)) {
                            f14497f = "s" + byteToHex;
                        }
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return f14497f;
    }
}
