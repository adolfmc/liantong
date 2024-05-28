package com.p281qq.p282e.comm.managers.plugin;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.p281qq.p282e.comm.constants.Sig;
import com.p281qq.p282e.comm.managers.C6873b;
import com.p281qq.p282e.comm.managers.status.SDKStatus;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.util.GDTLogger;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.qq.e.comm.managers.plugin.PM */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6875PM {

    /* renamed from: q */
    private static final Map<Class<?>, String> f17925q = new C6877b();

    /* renamed from: b */
    private final Context f17927b;

    /* renamed from: c */
    private String f17928c;

    /* renamed from: d */
    private File f17929d;

    /* renamed from: e */
    private volatile int f17930e;

    /* renamed from: f */
    private DexClassLoader f17931f;

    /* renamed from: g */
    private RandomAccessFile f17932g;

    /* renamed from: h */
    private FileLock f17933h;

    /* renamed from: i */
    private boolean f17934i;

    /* renamed from: j */
    private final InterfaceC6885f f17935j;

    /* renamed from: k */
    private volatile POFactory f17936k;

    /* renamed from: l */
    private int f17937l;

    /* renamed from: m */
    private Future<Boolean> f17938m;

    /* renamed from: o */
    private boolean f17940o;

    /* renamed from: p */
    private String f17941p;

    /* renamed from: a */
    final ExecutorService f17926a = Executors.newSingleThreadExecutor();

    /* renamed from: n */
    private boolean f17939n = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.qq.e.comm.managers.plugin.PM$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class CallableC6876a implements Callable<Boolean> {
        CallableC6876a() {
        }

        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            long currentTimeMillis = System.currentTimeMillis();
            if (!C6875PM.this.f17934i) {
                C6875PM c6875pm = C6875PM.this;
                c6875pm.f17934i = c6875pm.tryLockUpdate();
            }
            if (C6875PM.m8267b(C6875PM.this)) {
                C6875PM.m8265c(C6875PM.this);
            }
            C6875PM.this.f17937l = (int) (System.currentTimeMillis() - currentTimeMillis);
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.comm.managers.plugin.PM$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C6877b extends HashMap<Class<?>, String> {
        C6877b() {
            put(POFactory.class, "com.qq.e.comm.plugin.POFactoryImpl");
        }
    }

    public C6875PM(Context context, InterfaceC6885f interfaceC6885f) {
        this.f17927b = context.getApplicationContext();
        this.f17935j = interfaceC6885f;
        C6879b.m8262a(context);
        m8264d();
    }

    /* renamed from: a */
    private JSONObject m8272a() {
        JSONObject jSONObject = new JSONObject();
        try {
            int pluginVersion = getPluginVersion();
            if (pluginVersion > 10000) {
                jSONObject.put("vas", this.f17941p);
            }
            jSONObject.put("pv", pluginVersion);
            jSONObject.put("sig", this.f17928c);
            jSONObject.put("appId", C6873b.m8276b().m8280a());
            jSONObject.put("pn", C6879b.m8262a(this.f17927b));
            jSONObject.put("ict", this.f17937l);
            jSONObject.put("mup", this.f17934i);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    /* renamed from: b */
    private boolean m8268b() {
        if (this.f17934i) {
            try {
                C6879b.m8261a(this.f17927b, C6887h.m8237e(this.f17927b), C6887h.m8236f(this.f17927b));
                this.f17928c = Sig.ASSET_PLUGIN_SIG;
                this.f17929d = C6887h.m8237e(this.f17927b);
                this.f17930e = SDKStatus.getBuildInPluginVersion();
                return true;
            } catch (Throwable th) {
                GDTLogger.m8234e("插件初始化失败 ");
                C6878a.m8263a(th, th.getMessage());
                return false;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0025, code lost:
        if (r4.m8268b() != false) goto L13;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static boolean m8267b(com.p281qq.p282e.comm.managers.plugin.C6875PM r4) {
        /*
            if (r4 == 0) goto L70
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f
            r1.<init>()     // Catch: java.lang.Throwable -> L2f
            java.lang.String r2 = "TimeStap_BEFORE_PLUGIN_INIT:"
            r1.append(r2)     // Catch: java.lang.Throwable -> L2f
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L2f
            r1.append(r2)     // Catch: java.lang.Throwable -> L2f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L2f
            com.p281qq.p282e.comm.util.GDTLogger.m8235d(r1)     // Catch: java.lang.Throwable -> L2f
            boolean r1 = r4.m8266c()     // Catch: java.lang.Throwable -> L2f
            if (r1 != 0) goto L27
            boolean r4 = r4.m8268b()     // Catch: java.lang.Throwable -> L2f
            if (r4 == 0) goto L29
        L27:
            r4 = 1
            r0 = r4
        L29:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            goto L42
        L2f:
            r4 = move-exception
            java.lang.String r1 = "插件加载出现异常"
            com.p281qq.p282e.comm.util.GDTLogger.m8233e(r1, r4)     // Catch: java.lang.Throwable -> L56
            java.lang.String r1 = r4.getMessage()     // Catch: java.lang.Throwable -> L56
            com.p281qq.p282e.comm.managers.plugin.C6878a.m8263a(r4, r1)     // Catch: java.lang.Throwable -> L56
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
        L42:
            java.lang.String r1 = "TimeStap_AFTER_PLUGIN_INIT:"
            r4.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            com.p281qq.p282e.comm.util.GDTLogger.m8235d(r4)
            return r0
        L56:
            r4 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "TimeStap_AFTER_PLUGIN_INIT:"
            r0.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.p281qq.p282e.comm.util.GDTLogger.m8235d(r0)
            throw r4
        L70:
            r4 = 0
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p281qq.p282e.comm.managers.plugin.C6875PM.m8267b(com.qq.e.comm.managers.plugin.PM):boolean");
    }

    /* renamed from: c */
    static void m8265c(C6875PM c6875pm) {
        File file;
        if (c6875pm == null) {
            throw null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("PluginFile:\t");
        File file2 = c6875pm.f17929d;
        sb.append(file2 == null ? "null" : file2.getAbsolutePath());
        GDTLogger.m8235d(sb.toString());
        if (c6875pm.f17928c == null || (file = c6875pm.f17929d) == null) {
            c6875pm.f17931f = null;
            return;
        }
        try {
            c6875pm.f17931f = new DexClassLoader(file.getAbsolutePath(), C6887h.m8245a(c6875pm.f17927b).getAbsolutePath(), null, c6875pm.getClass().getClassLoader());
            InterfaceC6885f interfaceC6885f = c6875pm.f17935j;
            if (interfaceC6885f != null) {
                interfaceC6885f.m8254a();
            }
        } catch (Throwable th) {
            GDTLogger.m8233e("插件ClassLoader构造发生异常", th);
            InterfaceC6885f interfaceC6885f2 = c6875pm.f17935j;
            if (interfaceC6885f2 != null) {
                interfaceC6885f2.m8253b();
            }
            C6878a.m8263a(th, th.getMessage());
        }
    }

    /* renamed from: c */
    private boolean m8266c() {
        if (this.f17940o) {
            return false;
        }
        if (this.f17934i) {
            C6886g c6886g = new C6886g(C6887h.m8239c(this.f17927b), C6887h.m8238d(this.f17927b));
            if (c6886g.m8252a()) {
                boolean m8250a = c6886g.m8250a(C6887h.m8237e(this.f17927b), C6887h.m8236f(this.f17927b));
                GDTLogger.m8235d("NextExist,Updated=" + m8250a);
            }
        }
        C6886g c6886g2 = new C6886g(C6887h.m8237e(this.f17927b), C6887h.m8236f(this.f17927b));
        if (c6886g2.m8252a()) {
            if (c6886g2.m8248c() >= SDKStatus.getBuildInPluginVersion()) {
                this.f17928c = c6886g2.m8249b();
                this.f17930e = c6886g2.m8248c();
                this.f17929d = C6887h.m8237e(this.f17927b);
                this.f17941p = c6886g2.m8247d();
                this.f17939n = true;
                return true;
            }
            GDTLogger.m8235d("last updated plugin version =" + this.f17930e + ";asset plugin version=" + SDKStatus.getBuildInPluginVersion());
            return false;
        }
        return false;
    }

    /* renamed from: d */
    private void m8264d() {
        this.f17939n = false;
        SharedPreferences sharedPreferences = this.f17927b.getSharedPreferences("start_crash", 0);
        if (sharedPreferences.getInt("crash_count", 0) >= 2) {
            this.f17940o = true;
            sharedPreferences.edit().remove("crash_count").commit();
            GDTLogger.m8234e("加载本地插件");
        }
        this.f17938m = this.f17926a.submit(new CallableC6876a());
    }

    public <T> T getFactory(Class<T> cls) throws C6884e {
        Future<Boolean> future = this.f17938m;
        if (future != null) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        GDTLogger.m8235d("GetFactoryInstaceforInterface:" + cls);
        ClassLoader classLoader = Sig.ASSET_PLUGIN_SIG == null ? C6875PM.class.getClassLoader() : this.f17931f;
        StringBuilder sb = new StringBuilder();
        sb.append("PluginClassLoader is parent");
        sb.append(C6875PM.class.getClassLoader() == classLoader);
        GDTLogger.m8235d(sb.toString());
        if (classLoader == null) {
            throw new C6884e("Fail to init GDTADPLugin,PluginClassLoader == null;while loading factory impl for:" + cls);
        }
        try {
            String str = f17925q.get(cls);
            if (TextUtils.isEmpty(str)) {
                throw new C6884e("factory  implemention name is not specified for interface:" + cls.getName());
            }
            Class<?> loadClass = classLoader.loadClass(str);
            T cast = cls.cast(loadClass.getDeclaredMethod("getInstance", Context.class, JSONObject.class).invoke(loadClass, this.f17927b, m8272a()));
            GDTLogger.m8235d("ServiceDelegateFactory =" + cast);
            return cast;
        } catch (Throwable th) {
            throw new C6884e("Fail to getfactory implement instance for interface:" + cls.getName(), th);
        }
    }

    public POFactory getPOFactory() throws C6884e {
        if (this.f17936k == null) {
            synchronized (this) {
                if (this.f17936k == null) {
                    try {
                        this.f17936k = (POFactory) getFactory(POFactory.class);
                    } catch (C6884e e) {
                        if (!this.f17939n) {
                            throw e;
                        }
                        GDTLogger.m8234e("插件加载错误，回退到内置版本");
                        this.f17940o = true;
                        m8264d();
                        this.f17936k = (POFactory) getFactory(POFactory.class);
                    }
                }
            }
        }
        return this.f17936k;
    }

    public int getPluginVersion() {
        Future<Boolean> future = this.f17938m;
        if (future != null) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        return this.f17930e;
    }

    public boolean tryLockUpdate() {
        try {
            File m8240b = C6887h.m8240b(this.f17927b);
            if (!m8240b.exists()) {
                m8240b.createNewFile();
                C6887h.m8241a("lock", m8240b);
            }
            if (m8240b.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(m8240b, "rw");
                this.f17932g = randomAccessFile;
                FileLock tryLock = randomAccessFile.getChannel().tryLock();
                this.f17933h = tryLock;
                if (tryLock != null) {
                    this.f17932g.writeByte(37);
                    return true;
                }
                return false;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }
}
