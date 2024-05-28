package com.mob.commons.p229a;

import android.os.Handler;
import android.os.Message;
import com.mob.MobSDK;
import com.mob.commons.C5747b;
import com.mob.commons.C5782c;
import com.mob.commons.C5831e;
import com.mob.commons.C5838f;
import com.mob.commons.C5843h;
import com.mob.commons.C5849j;
import com.mob.commons.C5868q;
import com.mob.commons.C5871t;
import com.mob.commons.C5892y;
import com.mob.commons.C5895z;
import com.mob.commons.MobProduct;
import com.mob.tools.MDP;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.p237a.C6044i;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.mob.tools.utils.UIHandler;
import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.WeakHashMap;

/* renamed from: com.mob.commons.a.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractRunnableC5704c implements Runnable {

    /* renamed from: g */
    private static final WeakHashMap<String, Object> f14065g = new WeakHashMap<>();

    /* renamed from: a */
    protected Object f14066a;

    /* renamed from: b */
    protected int f14067b;

    /* renamed from: c */
    private final String f14068c;

    /* renamed from: d */
    private final String f14069d;

    /* renamed from: e */
    private final long f14070e;

    /* renamed from: f */
    private final long f14071f;

    /* renamed from: h */
    private int f14072h;

    /* renamed from: i */
    private boolean f14073i;

    /* renamed from: a */
    protected abstract void mo12656a() throws Throwable;

    /* renamed from: a */
    public static AbstractRunnableC5704c m12768a(Class<? extends AbstractRunnableC5704c> cls) {
        AbstractRunnableC5704c m12743b = C5709a.m12743b(cls);
        if (m12743b == null) {
            try {
                return cls.newInstance();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return m12743b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractRunnableC5704c(String str, String str2) {
        this(str, 0L, str2, 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractRunnableC5704c(String str, long j, String str2, long j2) {
        this.f14067b = 0;
        this.f14072h = 2;
        this.f14073i = false;
        this.f14068c = str;
        this.f14069d = str2;
        this.f14070e = j;
        this.f14071f = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m12757c() {
        this.f14073i = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m12775a(int i) {
        this.f14072h = i;
    }

    /* renamed from: d */
    public String m12756d() {
        return this.f14068c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public boolean mo12709e() {
        return ((Long) C5747b.m12583a(this.f14068c, Long.valueOf(this.f14070e))).longValue() != 0 && m12749k();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public boolean m12754f() {
        return this.f14067b == 0;
    }

    /* renamed from: g */
    public boolean m12753g() {
        return m12752h();
    }

    /* renamed from: h */
    protected boolean m12752h() {
        if (mo12709e()) {
            C5892y.f14525c.execute(this);
            return true;
        }
        return false;
    }

    /* renamed from: i */
    protected boolean m12751i() {
        boolean m12589a = C5747b.m12589a();
        boolean m12572b = C5747b.m12572b();
        if (!m12589a || !m12572b) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("run clt: " + m12756d() + ", to: " + m12589a + ", conn: " + m12572b, new Object[0]);
            return false;
        }
        boolean mo12709e = mo12709e();
        NLog mobLog2 = MobLog.getInstance();
        mobLog2.m11342d("run clt: " + getClass().getSimpleName() + ", to: " + m12589a + ", conn: " + m12572b + ", " + this.f14068c + ": " + mo12709e + ", key: " + m12764a(this.f14068c, (String) 0) + ", gp: " + m12750j(), new Object[0]);
        return mo12709e;
    }

    /* renamed from: b */
    protected void mo12712b() throws Throwable {
        long m12750j = m12750j();
        if (m12750j <= 0 || m12750j >= 604800) {
            return;
        }
        m12774a(m12750j);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (m12751i()) {
                if (!C5747b.m12557d()) {
                    m12774a(60000L);
                    return;
                }
                mo12656a();
                if (this.f14073i) {
                    C5709a.m12742c(this);
                } else {
                    C5709a.m12741d(this);
                }
                mo12712b();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m12774a(long j) {
        m12773a(j, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    protected void m12773a(long j, Object obj) {
        C5731l.m12681a().m12678a(j, getClass(), new Object[]{Integer.valueOf(this.f14067b + 1), obj}, this.f14072h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: j */
    public long m12750j() {
        try {
            if (this.f14069d != null) {
                return Long.parseLong(String.valueOf(C5747b.m12583a(this.f14069d, Long.valueOf(this.f14071f))));
            }
            return 0L;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return 0L;
        }
    }

    /* renamed from: a */
    public <T> T m12764a(String str, T t) {
        return (T) C5747b.m12583a(str, t);
    }

    /* renamed from: a */
    protected void m12769a(final AbstractC6201c<HashMap<String, Object>> abstractC6201c) {
        if (((Integer) m12764a(C5868q.m12203b("002]dc:f"), (String) 0)).intValue() == 1) {
            C6152DH.requester(MobSDK.getContext()).getPosComm(0, 0, true).request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.c.1
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                public void onResponse(C6152DH.DHResponse dHResponse) {
                    abstractC6201c.mo11088a(AbstractRunnableC5704c.this.m12760b(dHResponse.getPosComm(new int[0])));
                }
            });
        } else {
            abstractC6201c.mo11088a(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m12772a(long j, String str, Object obj) {
        m12770a(j, str, obj, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m12770a(long j, String str, Object obj, boolean z) {
        m12771a(j, str, obj, null, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m12771a(long j, String str, Object obj, HashMap<String, Object> hashMap, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        final long j2 = j > 0 ? (j * 1000) + currentTimeMillis : currentTimeMillis;
        final HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put(C5868q.m12203b("004h7cj4ie"), str);
        hashMap2.put(C5868q.m12203b("004fFchegQh"), obj);
        hashMap2.put(C5868q.m12203b("008.cbUcheh:chce^e"), Long.valueOf(currentTimeMillis));
        if (hashMap != null && !hashMap.isEmpty()) {
            hashMap2.putAll(hashMap);
        }
        if (z) {
            m12769a(new AbstractC6201c<HashMap<String, Object>>() { // from class: com.mob.commons.a.c.2
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(HashMap<String, Object> hashMap3) {
                    hashMap2.put(C5868q.m12203b("002bf"), hashMap3);
                    C5782c.m12489a().m12488a(j2, hashMap2);
                }
            });
        } else {
            C5782c.m12489a().m12488a(j2, hashMap2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m12763a(String str, HashMap<String, Object> hashMap) {
        m12762a(str, hashMap, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m12762a(String str, HashMap<String, Object> hashMap, boolean z) {
        final long currentTimeMillis = System.currentTimeMillis();
        final HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put(C5868q.m12203b("004hKcj.ie"), str);
        if (hashMap != null) {
            hashMap2.put(C5868q.m12203b("004GcbVchc"), hashMap);
        }
        hashMap2.put(C5868q.m12203b("008?cbDcheh!chceOe"), Long.valueOf(currentTimeMillis));
        if (z) {
            m12769a(new AbstractC6201c<HashMap<String, Object>>() { // from class: com.mob.commons.a.c.3
                @Override // com.mob.tools.utils.AbstractC6201c
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo11088a(HashMap<String, Object> hashMap3) {
                    hashMap2.put(C5868q.m12203b("002bf"), hashMap3);
                    C5782c.m12489a().m12488a(currentTimeMillis, hashMap2);
                }
            });
        } else {
            C5782c.m12489a().m12488a(currentTimeMillis, hashMap2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public TreeMap<String, Object> m12767a(Object obj) {
        if (obj != null) {
            try {
                TreeMap<String, Object> treeMap = new TreeMap<>();
                C6044i.C6049a c6049a = new C6044i.C6049a(obj);
                treeMap.put("ltdmt", Double.valueOf(c6049a.m11625b()));
                treeMap.put("lndmt", Double.valueOf(c6049a.m11624c()));
                return treeMap;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public HashMap<String, Object> m12760b(Object obj) {
        Throwable th;
        HashMap<String, Object> hashMap;
        if (obj != null) {
            C6044i.C6049a c6049a = new C6044i.C6049a(obj);
            try {
                hashMap = new HashMap<>();
            } catch (Throwable th2) {
                th = th2;
                hashMap = null;
            }
            try {
                hashMap.put("accmt", Float.valueOf(c6049a.m11627a()));
                if (c6049a.m11618i()) {
                    hashMap.put("vacmt", Float.valueOf(c6049a.m11617j()));
                }
                hashMap.put("ltdmt", Double.valueOf(c6049a.m11625b()));
                hashMap.put("lndmt", Double.valueOf(c6049a.m11624c()));
                hashMap.put(C5838f.f14364a, Long.valueOf(c6049a.m11623d()));
                hashMap.put("prvmt", c6049a.m11622e());
                hashMap.put("atdmt", Double.valueOf(c6049a.m11621f()));
                hashMap.put("brmt", Float.valueOf(c6049a.m11620g()));
                hashMap.put("spmt", Float.valueOf(c6049a.m11619h()));
                return hashMap;
            } catch (Throwable th3) {
                th = th3;
                MobLog.getInstance().m11342d("[cl] glfe " + th, new Object[0]);
                return hashMap;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: k */
    public final boolean m12749k() {
        if ("bs,l,ol,wi,wl,ext,aa,".contains(this.f14068c + ",")) {
            return C5849j.m12264a().m12253b();
        }
        return true;
    }

    /* renamed from: a */
    public static void m12765a(String str, File file, String str2, String str3) throws Throwable {
        Object obj;
        Object invokeInstanceMethod = ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), C5868q.m12203b("014-dd1eh*fi'fc1egegebdcQc9cbOeJci"), new Object[0]);
        ReflectHelper.importClass(C5868q.m12203b("028=cbDcf,ccchckecegcjeg!heMceecej1e=dbfiWfc_egegebdc=c_cbCeOci"), C5868q.m12203b("028=cbDcf,ccchckecegcjeg!heMceecej1e=dbfiWfc_egegebdc=c_cbCeOci"));
        file.setReadOnly();
        File parentFile = file.getParentFile();
        synchronized (f14065g) {
            obj = f14065g.get(str);
            if (obj == null) {
                obj = ReflectHelper.newInstance(C5868q.m12203b("028Ecb$cfPccchckecegcjegUhe0ceecejDe^dbfi*fc)egegebdcYc=cb6e=ci"), file.getAbsolutePath(), parentFile.getAbsolutePath(), parentFile.getAbsolutePath(), invokeInstanceMethod);
                f14065g.put(str, obj);
            }
        }
        ResHelper.deleteFileAndFolder(parentFile);
        String m12319a = C5831e.m12319a((MobProduct) null);
        final Object invokeInstanceMethod2 = ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeInstanceMethod(obj, C5868q.m12203b("009fKdcQc_cbfiKfcIegeg"), str2), C5868q.m12203b("0098dd_eh?gb7ehg+dccb"), str3, String.class);
        HashMap hashMap = new HashMap();
        hashMap.put(C5868q.m12203b("004Hcbcfchcb"), m12319a);
        hashMap.put(C5868q.m12203b("0046cedcchcb"), C6031c.m11708a(MobSDK.getContext()).m11704d().mo11563ah());
        hashMap.put(C5868q.m12203b("010Wegcbckfh$eAciegchdcWd"), Integer.valueOf(MobSDK.SDK_VERSION_CODE));
        hashMap.put(C5868q.m12203b("006cii4hbKeTcj"), C5871t.m12196a());
        hashMap.put(C5868q.m12203b("009ciiNdi,eb*ciAeh"), MobSDK.getAppSecret());
        hashMap.put(C5868q.m12203b("006ScbdcceHc9ch!d"), MobSDK.getDomain().getDomain());
        hashMap.put(C5868q.m12203b("010GdedcciWbe*ei+hhi1eg"), Boolean.valueOf(MobSDK.checkForceHttps()));
        hashMap.put(C5868q.m12203b("009Adedcci7be)dh.iHccgf"), Boolean.valueOf(MobSDK.checkV6()));
        hashMap.put(C5868q.m12203b("004ebe+db"), Long.valueOf(((Long) C5747b.m12583a(C5868q.m12203b("004ebe9db"), 5L)).longValue()));
        hashMap.put(C5868q.m12203b("002b:cb"), (String) C5747b.m12583a(C5868q.m12203b("002bCcb"), C5868q.m12203b("0069gdgdgegegege")));
        hashMap.put("usridt", C5895z.m12119d());
        hashMap.put("mdp", MDP.class.getName());
        final String fromHashMap = HashonHelper.fromHashMap(hashMap);
        ReflectHelper.invokeInstanceMethod(invokeInstanceMethod2, C5868q.m12203b("013+eg)ehUdkHbbeJegegchedWfe"), true);
        C5843h.m12291a().m12290a(15);
        UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.commons.a.c.4
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                try {
                    C5843h.m12291a().m12290a(16);
                    ReflectHelper.invokeInstanceMethod(invokeInstanceMethod2, C5868q.m12203b("0067chId<ccdcck:e"), null, new Object[]{fromHashMap});
                    C5843h.m12291a().m12290a(17);
                } catch (Throwable th) {
                    C5843h.m12291a().m12288a(7, th);
                }
                return false;
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.commons.a.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C5709a {

        /* renamed from: a */
        private static WeakHashMap<Integer, AbstractRunnableC5704c> f14084a = new WeakHashMap<>();

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public static synchronized void m12742c(AbstractRunnableC5704c abstractRunnableC5704c) {
            synchronized (C5709a.class) {
                f14084a.put(Integer.valueOf(abstractRunnableC5704c.getClass().getName().hashCode()), abstractRunnableC5704c);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: d */
        public static synchronized void m12741d(AbstractRunnableC5704c abstractRunnableC5704c) {
            synchronized (C5709a.class) {
                f14084a.remove(Integer.valueOf(abstractRunnableC5704c.getClass().getName().hashCode()));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public static synchronized AbstractRunnableC5704c m12743b(Class<? extends AbstractRunnableC5704c> cls) {
            AbstractRunnableC5704c abstractRunnableC5704c;
            synchronized (C5709a.class) {
                abstractRunnableC5704c = f14084a.get(Integer.valueOf(cls.getName().hashCode()));
            }
            return abstractRunnableC5704c;
        }
    }
}
