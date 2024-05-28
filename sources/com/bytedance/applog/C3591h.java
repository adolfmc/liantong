package com.bytedance.applog;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.util.UriConstants;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* renamed from: com.bytedance.applog.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3591h implements Handler.Callback, Comparator<AbstractC3628m1> {

    /* renamed from: A */
    public static C3591h f8461A;

    /* renamed from: a */
    public C3574e f8462a;

    /* renamed from: b */
    public boolean f8463b;

    /* renamed from: c */
    public Application f8464c;

    /* renamed from: d */
    public C3726x f8465d;

    /* renamed from: e */
    public C3586g f8466e;

    /* renamed from: g */
    public volatile C3659p1 f8468g;

    /* renamed from: h */
    public C3735y f8469h;

    /* renamed from: i */
    public volatile Handler f8470i;

    /* renamed from: j */
    public volatile C3600i f8471j;

    /* renamed from: k */
    public C3612k f8472k;

    /* renamed from: l */
    public C3618l f8473l;

    /* renamed from: m */
    public volatile C3563d f8474m;

    /* renamed from: o */
    public UriConfig f8476o;

    /* renamed from: p */
    public Handler f8477p;

    /* renamed from: q */
    public long f8478q;

    /* renamed from: r */
    public volatile boolean f8479r;

    /* renamed from: s */
    public AbstractC3579f f8480s;

    /* renamed from: t */
    public C3605j f8481t;

    /* renamed from: v */
    public volatile boolean f8483v;

    /* renamed from: w */
    public volatile long f8484w;

    /* renamed from: y */
    public volatile AbstractC3647o f8486y;

    /* renamed from: z */
    public volatile InitConfig.IpcDataChecker f8487z;

    /* renamed from: f */
    public final ArrayList<AbstractC3628m1> f8467f = new ArrayList<>(32);

    /* renamed from: u */
    public ArrayList<AbstractC3579f> f8482u = new ArrayList<>(4);

    /* renamed from: x */
    public final List<AbstractC3592a> f8485x = new ArrayList();

    /* renamed from: n */
    public C3624m f8475n = new C3624m(this);

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.h$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public abstract class AbstractC3592a<T> {

        /* renamed from: a */
        public T f8488a;

        public AbstractC3592a(C3591h c3591h, T t) {
            this.f8488a = t;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.h$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C3593b extends AbstractC3592a<String> {
        public C3593b(String str) {
            super(C3591h.this, str);
        }
    }

    public C3591h(Application application, C3726x c3726x, C3735y c3735y) {
        this.f8464c = application;
        this.f8465d = c3726x;
        this.f8469h = c3735y;
        HandlerThread handlerThread = new HandlerThread("bd_tracker_w");
        handlerThread.start();
        this.f8477p = new Handler(handlerThread.getLooper(), this);
        InterfaceC3662p2 interfaceC3662p2 = this.f8469h.f8939g;
        ((C3603i2) interfaceC3662p2).f8515b.m17211a(this.f8477p);
        if (this.f8465d.f8897b.isClearDidAndIid()) {
            C3735y c3735y2 = this.f8469h;
            String clearKey = this.f8465d.f8897b.getClearKey();
            ((C3603i2) c3735y2.f8939g).m17280a(c3735y2.f8934b, clearKey);
        }
        if (this.f8465d.f8897b.getIpcDataChecker() != null && !this.f8465d.m17045f()) {
            this.f8487z = this.f8465d.f8897b.getIpcDataChecker();
        }
        this.f8477p.sendEmptyMessage(10);
        if (this.f8465d.f8897b.autoStart()) {
            this.f8479r = true;
            this.f8477p.sendEmptyMessage(1);
        }
        f8461A = this;
    }

    /* renamed from: d */
    public static boolean m17286d() {
        C3591h c3591h = f8461A;
        if (c3591h != null) {
            C3726x c3726x = c3591h.f8465d;
            return c3726x.f8907l == 1 && c3726x.m17046e();
        }
        return true;
    }

    /* renamed from: a */
    public C3659p1 m17296a() {
        if (this.f8468g == null) {
            synchronized (this) {
                C3659p1 c3659p1 = this.f8468g;
                if (c3659p1 == null) {
                    c3659p1 = new C3659p1(this, this.f8465d.f8897b.getDbName());
                }
                this.f8468g = c3659p1;
            }
        }
        return this.f8468g;
    }

    /* renamed from: a */
    public final void m17295a(AbstractC3579f abstractC3579f) {
        if (this.f8470i == null || abstractC3579f == null) {
            return;
        }
        abstractC3579f.m17301i();
        if (Looper.myLooper() == this.f8470i.getLooper()) {
            abstractC3579f.m17304a();
            return;
        }
        this.f8470i.removeMessages(6);
        this.f8470i.sendEmptyMessage(6);
    }

    /* renamed from: a */
    public boolean m17291a(boolean z) {
        if ((!this.f8463b || z) && this.f8470i != null) {
            this.f8463b = true;
            this.f8470i.removeMessages(11);
            this.f8470i.sendEmptyMessage(11);
        }
        return this.f8463b;
    }

    /* renamed from: b */
    public int m17289b() {
        if (this.f8471j == null) {
            synchronized (this) {
                C3600i c3600i = this.f8471j;
                if (c3600i == null) {
                    c3600i = new C3600i(this);
                }
                this.f8471j = c3600i;
            }
        }
        return this.f8471j.m17283a();
    }

    /* renamed from: b */
    public void m17288b(boolean z) {
        InterfaceC3674q2 interfaceC3674q2 = C3629m2.f8584a;
        if (interfaceC3674q2 != null) {
            interfaceC3674q2.m17137a(z);
        } else {
            C3704u2.m17108a("can't find ET, should compile with ET", (Throwable) null);
        }
    }

    /* renamed from: c */
    public UriConfig m17287c() {
        if (this.f8476o == null) {
            this.f8476o = this.f8465d.f8897b.getUriConfig();
            if (this.f8476o == null) {
                this.f8476o = UriConstants.createUriConfig(0);
            }
        }
        return this.f8476o;
    }

    @Override // java.util.Comparator
    public int compare(AbstractC3628m1 abstractC3628m1, AbstractC3628m1 abstractC3628m12) {
        int i = ((abstractC3628m1.f8576b - abstractC3628m12.f8576b) > 0L ? 1 : ((abstractC3628m1.f8576b - abstractC3628m12.f8576b) == 0L ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    /*  JADX ERROR: DexException in pass: AttachTryCatchVisitor
        jadx.plugins.input.dex.DexException: Catch handler not found by byte offset: 0
        	at jadx.plugins.input.dex.sections.DexCodeReader.getTries(DexCodeReader.java:151)
        	at jadx.core.dex.visitors.AttachTryCatchVisitor.visit(AttachTryCatchVisitor.java:47)
        */
    @Override // android.os.Handler.Callback
    public boolean handleMessage(android.os.Message r13) {
        /*
            Method dump skipped, instructions count: 1012
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3591h.handleMessage(android.os.Message):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:173:0x00c4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0041 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m17290a(java.lang.String[] r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 646
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3591h.m17290a(java.lang.String[], boolean):void");
    }

    /* renamed from: a */
    public static void m17294a(AbstractC3628m1 abstractC3628m1) {
        int size;
        if (abstractC3628m1.f8576b == 0) {
            C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
        }
        C3591h c3591h = f8461A;
        if (c3591h == null) {
            C3684s.m17127a(abstractC3628m1);
            return;
        }
        synchronized (c3591h.f8467f) {
            size = c3591h.f8467f.size();
            c3591h.f8467f.add(abstractC3628m1);
        }
        if (size % 10 == 0) {
            c3591h.f8477p.removeMessages(4);
            c3591h.f8477p.sendEmptyMessageDelayed(4, size == 0 ? 300L : 0L);
        }
    }

    /* renamed from: a */
    public final boolean m17292a(ArrayList<AbstractC3628m1> arrayList) {
        JSONObject m17076a = C3712v2.m17076a(this.f8469h.m17017b());
        boolean z = true;
        String[] m17252a = C3614k1.m17252a(this, m17076a, true);
        if (m17252a.length > 0) {
            int m17258a = C3607j1.m17258a(m17252a, C3703u1.m17112a(arrayList, m17076a), this.f8465d);
            if (m17258a == 200) {
                this.f8478q = 0L;
                C3704u2.m17108a("sendRealTime, " + z, (Throwable) null);
                return z;
            } else if (C3607j1.m17269a(m17258a)) {
                this.f8478q = System.currentTimeMillis();
            }
        }
        z = false;
        C3704u2.m17108a("sendRealTime, " + z, (Throwable) null);
        return z;
    }

    /* renamed from: a */
    public void m17293a(String str) {
        String m17012d = this.f8469h.m17012d();
        if ((!TextUtils.isEmpty(str) || TextUtils.isEmpty(m17012d)) && (TextUtils.isEmpty(str) || TextUtils.equals(str, m17012d))) {
            return;
        }
        if (this.f8470i == null) {
            synchronized (this.f8485x) {
                this.f8485x.add(new C3593b(str));
            }
            return;
        }
        C3711v1 m17325a = C3557c.m17325a();
        if (m17325a != null) {
            m17325a = (C3711v1) m17325a.m24467clone();
        }
        Message obtainMessage = this.f8470i.obtainMessage(12, new Object[]{str, m17325a});
        this.f8470i.removeMessages(12);
        if (m17325a == null || TextUtils.isEmpty(this.f8475n.f8565l)) {
            this.f8470i.sendMessageDelayed(obtainMessage, 300L);
        } else {
            obtainMessage.sendToTarget();
        }
    }
}
