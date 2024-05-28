package com.baidu.location.p137b;

import android.location.Location;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.location.PoiRegion;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p137b.AbstractC2652m;
import com.baidu.location.p138c.C2688a;
import com.baidu.location.p138c.C2689b;
import com.baidu.location.p138c.C2697f;
import com.baidu.location.p138c.C2710k;
import com.baidu.location.p138c.C2711l;
import com.baidu.location.p140e.C2735k;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2666p extends AbstractC2652m {

    /* renamed from: f */
    public static String f5350f = "0";

    /* renamed from: h */
    public static boolean f5351h;

    /* renamed from: i */
    private static C2666p f5352i;

    /* renamed from: B */
    private double f5354B;

    /* renamed from: C */
    private double f5355C;

    /* renamed from: e */
    public AbstractC2652m.C2654b f5375e;

    /* renamed from: x */
    private long f5391x;

    /* renamed from: j */
    private boolean f5377j = true;

    /* renamed from: k */
    private String f5378k = null;

    /* renamed from: l */
    private BDLocation f5379l = null;

    /* renamed from: m */
    private BDLocation f5380m = null;

    /* renamed from: n */
    private C2710k f5381n = null;

    /* renamed from: o */
    private C2688a f5382o = null;

    /* renamed from: p */
    private C2710k f5383p = null;

    /* renamed from: q */
    private C2688a f5384q = null;

    /* renamed from: r */
    private boolean f5385r = true;

    /* renamed from: s */
    private volatile boolean f5386s = false;

    /* renamed from: t */
    private boolean f5387t = false;

    /* renamed from: u */
    private long f5388u = 0;

    /* renamed from: v */
    private long f5389v = 0;

    /* renamed from: w */
    private Address f5390w = null;

    /* renamed from: y */
    private String f5392y = null;

    /* renamed from: z */
    private List<Poi> f5393z = null;

    /* renamed from: A */
    private PoiRegion f5353A = null;

    /* renamed from: D */
    private boolean f5356D = false;

    /* renamed from: E */
    private long f5357E = 0;

    /* renamed from: F */
    private long f5358F = 0;

    /* renamed from: G */
    private RunnableC2667a f5359G = null;

    /* renamed from: H */
    private boolean f5360H = false;

    /* renamed from: I */
    private boolean f5361I = false;

    /* renamed from: J */
    private boolean f5362J = true;

    /* renamed from: g */
    public final Handler f5376g = new AbstractC2652m.HandlerC2653a();

    /* renamed from: K */
    private boolean f5363K = false;

    /* renamed from: L */
    private boolean f5364L = false;

    /* renamed from: M */
    private RunnableC2668b f5365M = null;

    /* renamed from: N */
    private boolean f5366N = false;

    /* renamed from: O */
    private int f5367O = 0;

    /* renamed from: P */
    private long f5368P = 0;

    /* renamed from: Q */
    private boolean f5369Q = false;

    /* renamed from: R */
    private String f5370R = null;

    /* renamed from: S */
    private boolean f5371S = false;

    /* renamed from: T */
    private boolean f5372T = false;

    /* renamed from: U */
    private boolean f5373U = false;

    /* renamed from: V */
    private boolean f5374V = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.p$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class RunnableC2667a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ C2666p f5394a;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f5394a.f5360H) {
                this.f5394a.f5360H = false;
                boolean unused = this.f5394a.f5361I;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.p$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class RunnableC2668b implements Runnable {
        private RunnableC2668b() {
        }

        /* synthetic */ RunnableC2668b(C2666p c2666p, RunnableC2669q runnableC2669q) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            if (C2666p.this.f5366N) {
                C2666p.this.f5366N = false;
            }
            if (C2666p.this.f5387t) {
                C2666p.this.f5387t = false;
                C2666p.this.m19358h(null);
            }
        }
    }

    private C2666p() {
        this.f5375e = null;
        this.f5375e = new AbstractC2652m.C2654b();
    }

    /* renamed from: a */
    private boolean m19383a(C2688a c2688a) {
        this.f5309b = C2689b.m19259a().m19237f();
        if (this.f5309b == c2688a) {
            return false;
        }
        if (this.f5309b == null || c2688a == null) {
            return true;
        }
        return !c2688a.m19268a(this.f5309b);
    }

    /* renamed from: a */
    private boolean m19382a(C2710k c2710k) {
        this.f5308a = C2711l.m19133a().m19111o();
        if (c2710k == this.f5308a) {
            return false;
        }
        if (this.f5308a == null || c2710k == null) {
            return true;
        }
        return !c2710k.m19145c(this.f5308a);
    }

    /* renamed from: b */
    private void m19376b(String str) {
        this.f5372T = str != null && "subway".equals(str.toLowerCase());
    }

    /* renamed from: c */
    public static synchronized C2666p m19375c() {
        C2666p c2666p;
        synchronized (C2666p.class) {
            if (f5352i == null) {
                f5352i = new C2666p();
            }
            c2666p = f5352i;
        }
        return c2666p;
    }

    /* renamed from: c */
    private void m19374c(Message message) {
        if (!C2735k.m19050c(ServiceC2737f.getServiceContext())) {
            BDLocation bDLocation = new BDLocation();
            bDLocation.setLocType(62);
            C2628b.m19560a().m19556a(bDLocation);
            return;
        }
        if (C2735k.m19056b()) {
            Log.d("baidu_location_service", "isInforbiddenTime on request location ...");
        }
        if (message.getData().getBoolean("isWaitingLocTag", false)) {
            f5351h = true;
        }
        int m19544d = C2628b.m19560a().m19544d(message);
        switch (m19544d) {
            case 1:
                m19369d(message);
                return;
            case 2:
                if (C2697f.m19228a().m19164j()) {
                    m19365e(message);
                    return;
                }
                return;
            case 3:
            case 4:
                m19360g(message);
                return;
            default:
                throw new IllegalArgumentException(String.format("this type %d is illegal", Integer.valueOf(m19544d)));
        }
    }

    /* renamed from: d */
    private void m19369d(Message message) {
        if (C2697f.m19228a().m19164j()) {
            m19365e(message);
            C2675t.m19327a().m19324c();
            return;
        }
        m19360g(message);
        C2675t.m19327a().m19325b();
    }

    /* renamed from: d */
    private void m19368d(BDLocation bDLocation) {
        if (C2735k.f5820l || bDLocation.getMockGpsStrategy() <= 0) {
            C2628b.m19560a().m19556a(bDLocation);
        } else {
            C2628b.m19560a().m19546c(bDLocation);
        }
    }

    /* renamed from: e */
    private void m19365e(Message message) {
        BDLocation bDLocation = new BDLocation(C2697f.m19228a().m19176f());
        Location m19171g = C2697f.m19228a().m19171g();
        if (m19171g != null && "bd_beidou".equals(m19171g.getProvider())) {
            bDLocation.setGnssProvider("bd_beidou");
        }
        if (m19171g != null) {
            bDLocation.setExtrainfo(m19171g.getExtras());
        }
        if (C2735k.f5813e.equals("all") || C2735k.f5815g || C2735k.f5817i) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.f5355C, this.f5354B, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] < 100.0f) {
                Address address = this.f5390w;
                if (address != null) {
                    bDLocation.setAddr(address);
                }
                String str = this.f5392y;
                if (str != null) {
                    bDLocation.setLocationDescribe(str);
                }
                List<Poi> list = this.f5393z;
                if (list != null) {
                    bDLocation.setPoiList(list);
                }
                PoiRegion poiRegion = this.f5353A;
                if (poiRegion != null) {
                    bDLocation.setPoiRegion(poiRegion);
                }
            } else {
                this.f5356D = true;
                m19360g(null);
            }
        }
        this.f5379l = bDLocation;
        this.f5380m = null;
        m19368d(bDLocation);
    }

    /* renamed from: e */
    private void m19364e(BDLocation bDLocation) {
        this.f5373U = bDLocation != null && bDLocation.isInIndoorPark();
    }

    /* renamed from: f */
    private void m19362f(Message message) {
        RunnableC2668b runnableC2668b;
        if (!C2711l.m19133a().m19120f()) {
            m19358h(message);
            return;
        }
        this.f5387t = true;
        if (this.f5365M == null) {
            this.f5365M = new RunnableC2668b(this, null);
        }
        if (this.f5366N && (runnableC2668b = this.f5365M) != null) {
            this.f5376g.removeCallbacks(runnableC2668b);
        }
        this.f5376g.postDelayed(this.f5365M, 3500L);
        this.f5366N = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m19360g(Message message) {
        this.f5367O = 0;
        if (!this.f5385r) {
            m19362f(message);
            this.f5358F = SystemClock.uptimeMillis();
            return;
        }
        this.f5367O = 1;
        this.f5358F = SystemClock.uptimeMillis();
        if (C2711l.m19133a().m19116j()) {
            m19362f(message);
        } else {
            m19358h(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009e, code lost:
        if (r6 <= 0) goto L46;
     */
    /* renamed from: h */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m19358h(android.os.Message r14) {
        /*
            Method dump skipped, instructions count: 513
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2666p.m19358h(android.os.Message):void");
    }

    /* renamed from: l */
    private String[] m19354l() {
        boolean z;
        C2633d m19512a;
        int i;
        String str;
        String[] strArr = {"", "Location failed beacuse we can not get any loc information!"};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&apl=");
        int m19068a = C2735k.m19068a(ServiceC2737f.getServiceContext());
        if (m19068a == 1) {
            strArr[1] = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        }
        stringBuffer.append(m19068a);
        String m19047d = C2735k.m19047d(ServiceC2737f.getServiceContext());
        if (m19047d.contains("0|0|")) {
            strArr[1] = "Location failed beacuse we can not get any loc information without any location permission!";
        }
        stringBuffer.append(m19047d);
        if (Build.VERSION.SDK_INT >= 23) {
            stringBuffer.append("&loc=");
            int m19055b = C2735k.m19055b(ServiceC2737f.getServiceContext());
            if (m19055b == 0) {
                strArr[1] = "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!";
                z = true;
            } else {
                z = false;
            }
            stringBuffer.append(m19055b);
        } else {
            z = false;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            stringBuffer.append("&lmd=");
            int m19055b2 = C2735k.m19055b(ServiceC2737f.getServiceContext());
            if (m19055b2 >= 0) {
                stringBuffer.append(m19055b2);
            }
        }
        String m19236g = C2689b.m19259a().m19236g();
        String m19119g = C2711l.m19133a().m19119g();
        stringBuffer.append(m19119g);
        stringBuffer.append(m19236g);
        stringBuffer.append(C2735k.m19044e(ServiceC2737f.getServiceContext()));
        if (m19068a == 1) {
            m19512a = C2633d.m19512a();
            i = 7;
            str = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        } else if (m19047d.contains("0|0|")) {
            m19512a = C2633d.m19512a();
            i = 4;
            str = "Location failed beacuse we can not get any loc information without any location permission!";
        } else if (z) {
            m19512a = C2633d.m19512a();
            i = 5;
            str = "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!";
        } else if (m19236g == null || m19119g == null || !m19236g.equals("&sim=1") || m19119g.equals("&wifio=1")) {
            m19512a = C2633d.m19512a();
            i = 9;
            str = "Location failed beacuse we can not get any loc information!";
        } else {
            m19512a = C2633d.m19512a();
            i = 6;
            str = "Location failed beacuse we can not get any loc information , you can insert a sim card or open wifi and try again!";
        }
        m19512a.m19511a(62, i, str);
        strArr[0] = stringBuffer.toString();
        return strArr;
    }

    /* renamed from: m */
    private void m19353m() {
        this.f5386s = false;
        this.f5361I = false;
        this.f5362J = false;
        this.f5356D = false;
        m19352n();
        if (this.f5374V) {
            this.f5374V = false;
        }
    }

    /* renamed from: n */
    private void m19352n() {
        if (this.f5379l == null || !C2711l.m19133a().m19117i()) {
            return;
        }
        C2686z.m19278a().m19272d();
    }

    /* renamed from: a */
    public Address m19387a(BDLocation bDLocation) {
        if (C2735k.f5813e.equals("all") || C2735k.f5815g || C2735k.f5817i) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.f5355C, this.f5354B, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] < 100.0d) {
                Address address = this.f5390w;
                if (address != null) {
                    return address;
                }
            } else {
                this.f5392y = null;
                this.f5393z = null;
                this.f5353A = null;
                this.f5356D = true;
                this.f5376g.post(new RunnableC2669q(this));
            }
        }
        return null;
    }

    @Override // com.baidu.location.p137b.AbstractC2652m
    /* renamed from: a */
    public void mo19389a() {
        RunnableC2667a runnableC2667a = this.f5359G;
        if (runnableC2667a != null && this.f5360H) {
            this.f5360H = false;
            this.f5376g.removeCallbacks(runnableC2667a);
        }
        if (C2697f.m19228a().m19164j()) {
            BDLocation bDLocation = new BDLocation(C2697f.m19228a().m19176f());
            Location m19171g = C2697f.m19228a().m19171g();
            if (m19171g != null && "bd_beidou".equals(m19171g.getProvider())) {
                bDLocation.setGnssProvider("bd_beidou");
            }
            if (m19171g != null) {
                bDLocation.setExtrainfo(m19171g.getExtras());
            }
            if (C2735k.f5813e.equals("all") || C2735k.f5815g || C2735k.f5817i) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.f5355C, this.f5354B, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    Address address = this.f5390w;
                    if (address != null) {
                        bDLocation.setAddr(address);
                    }
                    String str = this.f5392y;
                    if (str != null) {
                        bDLocation.setLocationDescribe(str);
                    }
                    List<Poi> list = this.f5393z;
                    if (list != null) {
                        bDLocation.setPoiList(list);
                    }
                    PoiRegion poiRegion = this.f5353A;
                    if (poiRegion != null) {
                        bDLocation.setPoiRegion(poiRegion);
                    }
                }
            }
            C2628b.m19560a().m19556a(bDLocation);
        } else if (this.f5361I) {
            m19353m();
            return;
        } else {
            if (this.f5377j || this.f5379l == null) {
                BDLocation bDLocation2 = new BDLocation();
                bDLocation2.setLocType(63);
                this.f5379l = null;
                C2628b.m19560a().m19556a(bDLocation2);
            } else {
                C2628b.m19560a().m19556a(this.f5379l);
            }
            this.f5380m = null;
        }
        m19353m();
    }

    @Override // com.baidu.location.p137b.AbstractC2652m
    /* renamed from: a */
    public void mo19388a(Message message) {
        RunnableC2667a runnableC2667a = this.f5359G;
        if (runnableC2667a != null && this.f5360H) {
            this.f5360H = false;
            this.f5376g.removeCallbacks(runnableC2667a);
        }
        BDLocation bDLocation = (BDLocation) message.obj;
        int i = message.arg1;
        if (bDLocation != null && bDLocation.getLocType() == 161) {
            m19376b(bDLocation.getTraffic());
            m19364e(bDLocation);
            if (i == 1) {
                C2631c.m19525a().m19522a(bDLocation, "gcj02", null);
            }
        }
        if (bDLocation != null && bDLocation.getLocType() == 167 && this.f5364L) {
            bDLocation.setLocType(62);
        }
        m19380b(bDLocation);
    }

    /* renamed from: b */
    public void m19381b(Message message) {
        if (this.f5363K) {
            m19374c(message);
        }
    }

    /* renamed from: b */
    public void m19380b(BDLocation bDLocation) {
        String m19119g;
        int m19055b;
        C2710k c2710k;
        BDLocation bDLocation2;
        new BDLocation(bDLocation);
        if (bDLocation.hasAddr()) {
            this.f5390w = bDLocation.getAddress();
            Address address = this.f5390w;
            if (address != null && address.cityCode != null) {
                f5350f = this.f5390w.cityCode;
                this.f5391x = System.currentTimeMillis();
            }
            this.f5354B = bDLocation.getLongitude();
            this.f5355C = bDLocation.getLatitude();
        }
        if (bDLocation.getLocationDescribe() != null) {
            this.f5392y = bDLocation.getLocationDescribe();
            this.f5354B = bDLocation.getLongitude();
            this.f5355C = bDLocation.getLatitude();
        }
        if (bDLocation.getPoiList() != null) {
            this.f5393z = bDLocation.getPoiList();
            this.f5354B = bDLocation.getLongitude();
            this.f5355C = bDLocation.getLatitude();
        }
        if (bDLocation.getPoiRegion() != null) {
            this.f5353A = bDLocation.getPoiRegion();
            this.f5354B = bDLocation.getLongitude();
            this.f5355C = bDLocation.getLatitude();
        }
        boolean z = false;
        if (C2697f.m19228a().m19164j()) {
            BDLocation bDLocation3 = new BDLocation(C2697f.m19228a().m19176f());
            Location m19171g = C2697f.m19228a().m19171g();
            if (m19171g != null && "bd_beidou".equals(m19171g.getProvider())) {
                bDLocation3.setGnssProvider("bd_beidou");
            }
            if (m19171g != null) {
                bDLocation3.setExtrainfo(m19171g.getExtras());
            }
            if (C2735k.f5813e.equals("all") || C2735k.f5815g || C2735k.f5817i) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.f5355C, this.f5354B, bDLocation3.getLatitude(), bDLocation3.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    Address address2 = this.f5390w;
                    if (address2 != null) {
                        bDLocation3.setAddr(address2);
                    }
                    String str = this.f5392y;
                    if (str != null) {
                        bDLocation3.setLocationDescribe(str);
                    }
                    List<Poi> list = this.f5393z;
                    if (list != null) {
                        bDLocation3.setPoiList(list);
                    }
                    PoiRegion poiRegion = this.f5353A;
                    if (poiRegion != null) {
                        bDLocation3.setPoiRegion(poiRegion);
                    }
                }
            }
            m19368d(bDLocation3);
            m19353m();
        } else if (this.f5361I) {
            float[] fArr2 = new float[2];
            BDLocation bDLocation4 = this.f5379l;
            if (bDLocation4 != null) {
                Location.distanceBetween(bDLocation4.getLatitude(), this.f5379l.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr2);
            }
            if (fArr2[0] <= 10.0f) {
                if (bDLocation.getUserIndoorState() > -1) {
                    this.f5379l = bDLocation;
                    C2628b.m19560a().m19556a(bDLocation);
                }
                m19353m();
            }
            this.f5379l = bDLocation;
            if (!this.f5362J) {
                this.f5362J = false;
                C2628b.m19560a().m19556a(bDLocation);
            }
            m19353m();
        } else {
            if (bDLocation.getLocType() == 167) {
                C2633d.m19512a().m19511a(167, 8, "NetWork location failed because baidu location service can not caculate the location!");
            } else if (bDLocation.getLocType() == 161) {
                if (Build.VERSION.SDK_INT >= 19 && ((m19055b = C2735k.m19055b(ServiceC2737f.getServiceContext())) == 0 || m19055b == 2)) {
                    C2633d.m19512a().m19511a(161, 1, "NetWork location successful, open gps will be better!");
                } else if (bDLocation.getRadius() >= 100.0f && bDLocation.getNetworkLocationType() != null && bDLocation.getNetworkLocationType().equals("cl") && (m19119g = C2711l.m19133a().m19119g()) != null && !m19119g.equals("&wifio=1")) {
                    C2633d.m19512a().m19511a(161, 2, "NetWork location successful, open wifi will be better!");
                }
            }
            this.f5380m = null;
            if (bDLocation.getLocType() == 161 && "cl".equals(bDLocation.getNetworkLocationType()) && (bDLocation2 = this.f5379l) != null && bDLocation2.getLocType() == 161 && "wf".equals(this.f5379l.getNetworkLocationType()) && System.currentTimeMillis() - this.f5389v < 30000) {
                this.f5380m = bDLocation;
                z = true;
            }
            if (z) {
                C2628b.m19560a().m19556a(this.f5379l);
            } else {
                C2628b.m19560a().m19556a(bDLocation);
                this.f5389v = System.currentTimeMillis();
            }
            if (!C2735k.m19065a(bDLocation)) {
                this.f5379l = null;
            } else if (!z) {
                this.f5379l = bDLocation;
            }
            int m19060a = C2735k.m19060a(f5307c, "ssid\":\"", "\"");
            if (m19060a == Integer.MIN_VALUE || (c2710k = this.f5381n) == null) {
                this.f5378k = null;
            } else {
                this.f5378k = c2710k.m19149b(m19060a);
            }
            C2711l.m19133a().m19117i();
            m19353m();
        }
    }

    /* renamed from: c */
    public void m19373c(BDLocation bDLocation) {
        this.f5379l = new BDLocation(bDLocation);
    }

    /* renamed from: d */
    public void m19370d() {
        this.f5385r = true;
        this.f5386s = false;
        this.f5363K = true;
    }

    /* renamed from: e */
    public void m19366e() {
        this.f5386s = false;
        this.f5387t = false;
        this.f5361I = false;
        this.f5362J = true;
        m19355k();
        this.f5363K = false;
    }

    /* renamed from: f */
    public String m19363f() {
        return this.f5392y;
    }

    /* renamed from: g */
    public List<Poi> m19361g() {
        return this.f5393z;
    }

    /* renamed from: h */
    public PoiRegion m19359h() {
        return this.f5353A;
    }

    /* renamed from: i */
    public void m19357i() {
        if (this.f5387t) {
            m19358h(null);
            this.f5387t = false;
        }
    }

    /* renamed from: j */
    public boolean m19356j() {
        return this.f5373U;
    }

    /* renamed from: k */
    public void m19355k() {
        this.f5379l = null;
    }
}
