package com.mob.tools.p237a;

import android.content.Context;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5839g;
import com.mob.commons.C5868q;
import com.mob.commons.C5873u;
import com.mob.commons.C5892y;
import com.mob.commons.p231cc.C5791a;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.mob.tools.a.d */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C6032d {

    /* renamed from: a */
    private static C6032d f14840a;

    /* renamed from: d */
    private static volatile boolean f14841d;

    /* renamed from: b */
    private Context f14842b;

    /* renamed from: c */
    private HashMap<String, Object> f14843c;

    /* renamed from: g */
    private volatile File f14846g;

    /* renamed from: k */
    private long f14850k;

    /* renamed from: l */
    private long f14851l;

    /* renamed from: m */
    private long f14852m;

    /* renamed from: e */
    private final byte[] f14844e = new byte[0];

    /* renamed from: f */
    private AtomicBoolean f14845f = new AtomicBoolean(false);

    /* renamed from: h */
    private ConcurrentLinkedQueue<CountDownLatch> f14847h = new ConcurrentLinkedQueue<>();

    /* renamed from: i */
    private volatile String f14848i = null;

    /* renamed from: j */
    private volatile int f14849j = -1;

    /* renamed from: a */
    static /* synthetic */ long m11698a(C6032d c6032d, long j) {
        c6032d.f14852m = j;
        return j;
    }

    /* renamed from: a */
    static /* synthetic */ File m11696a(C6032d c6032d, File file, String str) {
        return c6032d.m11684b(file, str);
    }

    /* renamed from: a */
    static /* synthetic */ String m11694a(C6032d c6032d, String str, File file, String str2) {
        return c6032d.m11690a(str, file, str2);
    }

    /* renamed from: a */
    static /* synthetic */ void m11697a(C6032d c6032d, File file) {
        c6032d.m11693a(file);
    }

    /* renamed from: a */
    static /* synthetic */ boolean m11695a(C6032d c6032d, String str) {
        return c6032d.m11672e(str);
    }

    /* renamed from: a */
    static /* synthetic */ boolean m11689a(boolean z) {
        f14841d = z;
        return z;
    }

    /* renamed from: a */
    static /* synthetic */ byte[] m11699a(C6032d c6032d) {
        return c6032d.f14844e;
    }

    /* renamed from: b */
    static /* synthetic */ String m11685b(C6032d c6032d, String str) {
        return c6032d.m11683b(str);
    }

    /* renamed from: b */
    static /* synthetic */ HashMap m11686b(C6032d c6032d, File file, String str) {
        return c6032d.m11692a(file, str);
    }

    /* renamed from: b */
    static /* synthetic */ AtomicBoolean m11687b(C6032d c6032d) {
        return c6032d.f14845f;
    }

    /* renamed from: c */
    static /* synthetic */ String m11681c(C6032d c6032d) {
        return c6032d.f14848i;
    }

    /* renamed from: c */
    static /* synthetic */ String m11680c(C6032d c6032d, String str) {
        return c6032d.m11679c(str);
    }

    /* renamed from: d */
    static /* synthetic */ Context m11677d(C6032d c6032d) {
        return c6032d.f14842b;
    }

    /* renamed from: d */
    static /* synthetic */ String m11676d(C6032d c6032d, String str) {
        c6032d.f14848i = str;
        return str;
    }

    /* renamed from: e */
    static /* synthetic */ ConcurrentLinkedQueue m11673e(C6032d c6032d) {
        return c6032d.f14847h;
    }

    /* renamed from: f */
    static /* synthetic */ long m11671f(C6032d c6032d) {
        return c6032d.f14852m;
    }

    /* renamed from: g */
    static /* synthetic */ long m11670g(C6032d c6032d) {
        return c6032d.f14850k;
    }

    /* renamed from: h */
    static /* synthetic */ long m11669h(C6032d c6032d) {
        return c6032d.f14851l;
    }

    /* renamed from: a */
    public static C6032d m11700a(Context context) {
        if (f14840a == null) {
            synchronized (C6032d.class) {
                if (f14840a == null) {
                    f14840a = new C6032d(context);
                }
            }
        }
        return f14840a;
    }

    private C6032d(Context context) {
        this.f14842b = context;
    }

    /* renamed from: a */
    public final CountDownLatch m11702a() {
        return m11691a(m11674e());
    }

    /* renamed from: a */
    public void m11701a(int i) {
        this.f14849j = i;
    }

    /* renamed from: b */
    public int m11688b() {
        return this.f14849j;
    }

    /* renamed from: a */
    public final CountDownLatch m11691a(final String str) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("dhs ofr: " + countDownLatch, new Object[0]);
        this.f14847h.offer(countDownLatch);
        C5892y.f14528f.execute(new Runnable() { // from class: com.mob.tools.a.d.1
            /*  JADX ERROR: Failed to decode insn: 0x05E2: UNKNOWN(0x0042), method: com.mob.tools.a.d.1.run():void
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x05E2: UNKNOWN(0x0042)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
                	at jadx.core.ProcessClass.process(ProcessClass.java:67)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
                */
            /*  JADX ERROR: Failed to decode insn: 0x05F2: UNKNOWN(0xAFF5), method: com.mob.tools.a.d.1.run():void
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x05F2: UNKNOWN(0xAFF5)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
                	at jadx.core.ProcessClass.process(ProcessClass.java:67)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
                */
            @Override // java.lang.Runnable
            public void run() {
                /*
                    Method dump skipped, instructions count: 1679
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6032d.RunnableC60331.run():void");
            }
        });
        return countDownLatch;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m11693a(File file) {
        if (this.f14846g != null && this.f14846g.exists()) {
            if (this.f14846g.delete()) {
                MobLog.getInstance().m11342d("dhs dof succ", new Object[0]);
            } else {
                MobLog.getInstance().m11342d("dhs dof fail", new Object[0]);
            }
        }
        this.f14846g = file;
    }

    /* renamed from: c */
    public static boolean m11682c() {
        return f14841d;
    }

    /* renamed from: d */
    public CountDownLatch m11678d() {
        ConcurrentLinkedQueue<CountDownLatch> concurrentLinkedQueue = this.f14847h;
        if (concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
            return null;
        }
        return this.f14847h.peek();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public HashMap<String, Object> m11692a(File file, String str) {
        String str2;
        Object[] objArr;
        HashMap hashMap = new HashMap();
        String m12629c = C5741aa.m12650a().m12629c();
        if (TextUtils.isEmpty(m12629c)) {
            m12629c = HashonHelper.fromHashMap(hashMap);
        }
        HashMap<String, Object> hashMap2 = new HashMap<>();
        if (this.f14843c == null) {
            this.f14843c = new HashMap<>();
            this.f14843c.put("cacheMap", new ConcurrentHashMap());
            this.f14843c.put("invokeTimesMap", new ConcurrentHashMap());
            this.f14843c.put("expireTimeMap", new ConcurrentHashMap());
        }
        String str3 = null;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            C5791a.m12460a(MobSDK.getContext(), file.getAbsolutePath(), m12629c, hashMap2, this.f14843c);
            this.f14850k = System.currentTimeMillis() - currentTimeMillis;
        } catch (Throwable th) {
            try {
                str3 = "dhs l e: " + th.getMessage();
                hashMap2.clear();
                C5839g.m12306a().m12304a(5, m11688b(), th, "" + str);
                MobLog.getInstance().m11341d(th);
            } catch (Throwable unused) {
            }
            this.f14850k = System.currentTimeMillis() - currentTimeMillis;
            if (TextUtils.isEmpty(str3)) {
                str2 = "dhs l %d";
                objArr = new Object[]{Long.valueOf(this.f14850k)};
            }
        }
        if (TextUtils.isEmpty(null)) {
            str2 = "dhs l %d";
            objArr = new Object[]{Long.valueOf(this.f14850k)};
            str3 = String.format(str2, objArr);
        }
        MobLog.getInstance().m11342d(str3, new Object[0]);
        return hashMap2;
    }

    /* renamed from: e */
    private String m11674e() {
        try {
            String str = (String) C5747b.m12567b(C5868q.m12203b("002Yegeg"), (Object) null);
            return str == null ? (String) C5747b.m12567b(C5868q.m12203b("009Megedcidieech@hbg"), (Object) null) : str;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public String m11683b(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split("#")) == null || split.length != 2) {
            return null;
        }
        return split[0];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public String m11679c(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split("#")) == null || split.length != 2) {
            return null;
        }
        return split[1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public File m11684b(File file, String str) {
        if (!file.exists()) {
            file.mkdirs();
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        m11675d(str);
        return new File(file, str);
    }

    /* renamed from: d */
    private void m11675d(String str) {
        File dataCacheFile = ResHelper.getDataCacheFile(this.f14842b, str);
        if (!dataCacheFile.exists() || dataCacheFile.length() <= 0) {
            return;
        }
        dataCacheFile.delete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public boolean m11672e(String str) {
        return (TextUtils.isEmpty(m11683b(str)) || TextUtils.isEmpty(m11679c(str))) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m11690a(String str, File file, String str2) {
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str) || file == null) {
            return "";
        }
        long currentTimeMillis = System.currentTimeMillis();
        String str3 = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            MobLog.getInstance().m11342d("dhs d...", new Object[0]);
            new NetworkHelper().download(str, fileOutputStream, null);
            String MD5 = Data.MD5(file);
            if (TextUtils.equals(str2, MD5)) {
                C5873u.m12179a(fileOutputStream);
                if (TextUtils.isEmpty(null)) {
                    this.f14851l = System.currentTimeMillis() - currentTimeMillis;
                    str3 = String.format("dhs d %d", Long.valueOf(this.f14851l));
                }
                MobLog.getInstance().m11342d(str3, new Object[0]);
                return MD5;
            }
            C5839g.m12306a().m12305a(-1, 20, "", str2);
            if (file.exists()) {
                file.delete();
            }
            C5873u.m12179a(fileOutputStream);
            if (TextUtils.isEmpty(null)) {
                this.f14851l = System.currentTimeMillis() - currentTimeMillis;
                str3 = String.format("dhs d %d", Long.valueOf(this.f14851l));
            }
            MobLog.getInstance().m11342d(str3, new Object[0]);
            return "";
        } catch (Throwable th2) {
            th = th2;
            try {
                if (file.exists()) {
                    file.delete();
                }
                str3 = "dhs d e: " + th.getMessage();
                MobLog.getInstance().m11341d(th);
                C5839g.m12306a().m12304a(2, m11688b(), th, "" + str2);
                C5873u.m12179a(fileOutputStream);
                if (TextUtils.isEmpty(str3)) {
                    this.f14851l = System.currentTimeMillis() - currentTimeMillis;
                    str3 = String.format("dhs d %d", Long.valueOf(this.f14851l));
                }
                MobLog.getInstance().m11342d(str3, new Object[0]);
                return "";
            } catch (Throwable th3) {
                C5873u.m12179a(fileOutputStream);
                if (TextUtils.isEmpty(str3)) {
                    this.f14851l = System.currentTimeMillis() - currentTimeMillis;
                    str3 = String.format("dhs d %d", Long.valueOf(this.f14851l));
                }
                MobLog.getInstance().m11342d(str3, new Object[0]);
                throw th3;
            }
        }
    }
}
