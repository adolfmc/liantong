package com.baidu.location.p137b;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.location.PoiRegion;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p136a.C2621a;
import com.baidu.location.p138c.C2697f;
import com.baidu.location.p138c.C2711l;
import com.baidu.location.p140e.C2721b;
import com.baidu.location.p140e.C2735k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2628b {

    /* renamed from: c */
    public static long f5153c = 0;

    /* renamed from: d */
    public static int f5154d = -1;

    /* renamed from: f */
    private static C2628b f5155f;

    /* renamed from: g */
    private ArrayList<C2629a> f5159g;

    /* renamed from: h */
    private boolean f5160h = false;

    /* renamed from: a */
    public boolean f5156a = false;

    /* renamed from: b */
    boolean f5157b = false;

    /* renamed from: i */
    private BDLocation f5161i = null;

    /* renamed from: j */
    private BDLocation f5162j = null;

    /* renamed from: k */
    private Object f5163k = new Object();

    /* renamed from: e */
    int f5158e = 0;

    /* renamed from: l */
    private BDLocation f5164l = null;

    /* renamed from: m */
    private boolean f5165m = false;

    /* renamed from: n */
    private boolean f5166n = false;

    /* renamed from: o */
    private RunnableC2630b f5167o = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2629a {

        /* renamed from: a */
        public String f5168a;

        /* renamed from: b */
        public Messenger f5169b;

        /* renamed from: c */
        public LocationClientOption f5170c = new LocationClientOption();

        /* renamed from: d */
        public int f5171d = 0;

        public C2629a(Message message) {
            this.f5168a = null;
            this.f5169b = null;
            this.f5169b = message.replyTo;
            this.f5168a = message.getData().getString("packName");
            this.f5170c.prodName = message.getData().getString("prodName");
            C2721b.m19096a().m19094a(this.f5170c.prodName, this.f5168a);
            this.f5170c.coorType = message.getData().getString("coorType");
            this.f5170c.addrType = message.getData().getString("addrType");
            this.f5170c.enableSimulateGps = message.getData().getBoolean("enableSimulateGps", false);
            C2735k.f5820l = C2735k.f5820l || this.f5170c.enableSimulateGps;
            if (!C2735k.f5813e.equals("all")) {
                C2735k.f5813e = this.f5170c.addrType;
            }
            this.f5170c.openGps = message.getData().getBoolean("openGPS");
            this.f5170c.scanSpan = message.getData().getInt("scanSpan");
            this.f5170c.timeOut = message.getData().getInt("timeOut");
            this.f5170c.priority = message.getData().getInt("priority");
            this.f5170c.location_change_notify = message.getData().getBoolean("location_change_notify");
            this.f5170c.mIsNeedDeviceDirect = message.getData().getBoolean("needDirect", false);
            this.f5170c.isNeedAltitude = message.getData().getBoolean("isneedaltitude", false);
            this.f5170c.isNeedNewVersionRgc = message.getData().getBoolean("isneednewrgc", false);
            C2735k.f5816h = C2735k.f5816h || this.f5170c.isNeedNewVersionRgc;
            C2735k.f5815g = C2735k.f5815g || message.getData().getBoolean("isneedaptag", false);
            C2735k.f5817i = C2735k.f5817i || message.getData().getBoolean("isneedaptagd", false);
            C2735k.f5759R = message.getData().getFloat("autoNotifyLocSensitivity", 0.5f);
            int i = message.getData().getInt("wfnum", C2735k.f5808ay);
            float f = message.getData().getFloat("wfsm", C2735k.f5809az);
            int i2 = message.getData().getInt("gnmcon", C2735k.f5770aB);
            double d = message.getData().getDouble("gnmcrm", C2735k.f5769aA);
            int i3 = message.getData().getInt("iupl", 1);
            C2735k.f5775aG = message.getData().getInt("ct", 10);
            C2735k.f5776aH = message.getData().getInt("suci", 3);
            C2735k.f5778aJ = message.getData().getDoubleArray("cgs");
            C2735k.f5779aK = message.getData().getInt("ums", 1);
            C2735k.f5777aI = message.getData().getInt("smn", 40);
            if (i3 <= 0) {
                C2735k.f5774aF = 0;
            } else if (C2735k.f5774aF == -1) {
                C2735k.f5774aF = 1;
            }
            if (message.getData().getInt("opetco", 1) == 0) {
                C2735k.f5780aL = 0;
            }
            if (message.getData().getInt("lpcs", C2735k.f5781aM) == 0) {
                C2735k.f5781aM = 0;
            }
            if (i2 == 1) {
                C2735k.f5770aB = 1;
            }
            if (d > C2735k.f5769aA) {
                C2735k.f5769aA = d;
            }
            C2735k.f5807ax = C2735k.f5807ax || message.getData().getBoolean("ischeckper", false);
            boolean z = message.getData().getBoolean("isEnableBeidouMode", false);
            if (Build.VERSION.SDK_INT >= 28) {
                C2735k.f5782aN = C2735k.f5782aN || z;
            }
            if (i > C2735k.f5808ay) {
                C2735k.f5808ay = i;
            }
            if (f > C2735k.f5809az) {
                C2735k.f5809az = f;
            }
            int i4 = message.getData().getInt("wifitimeout", Integer.MAX_VALUE);
            if (i4 < C2735k.f5789af) {
                C2735k.f5789af = i4;
            }
            int i5 = message.getData().getInt("autoNotifyMaxInterval", 0);
            if (i5 >= C2735k.f5764W) {
                C2735k.f5764W = i5;
            }
            int i6 = message.getData().getInt("autoNotifyMinDistance", 0);
            if (i6 >= C2735k.f5766Y) {
                C2735k.f5766Y = i6;
            }
            int i7 = message.getData().getInt("autoNotifyMinTimeInterval", 0);
            if (i7 >= C2735k.f5765X) {
                C2735k.f5765X = i7;
            }
            if (this.f5170c.mIsNeedDeviceDirect || this.f5170c.isNeedAltitude) {
                C2675t.m19327a().m19326a(this.f5170c.mIsNeedDeviceDirect);
                C2675t.m19327a().m19325b();
            }
            C2628b.this.f5157b = C2628b.this.f5157b || this.f5170c.isNeedAltitude;
            if (message.getData().getInt("hpdts", C2735k.f5771aC) == 1) {
                C2735k.f5771aC = 1;
            } else {
                C2735k.f5771aC = 0;
            }
            if (message.getData().getInt("oldts", C2735k.f5772aD) == 1) {
                C2735k.f5772aD = 1;
            } else {
                C2735k.f5772aD = 0;
            }
        }

        /* renamed from: a */
        private double m19528a(boolean z, BDLocation bDLocation, BDLocation bDLocation2) {
            double d;
            double m19070a;
            double[] dArr;
            double[] dArr2;
            double longitude;
            double latitude;
            String str;
            if (!z) {
                if (!TextUtils.equals(bDLocation2.getCoorType(), bDLocation.getCoorType())) {
                    double[] coorEncrypt = Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), "gcj2wgs");
                    bDLocation.setLatitude(coorEncrypt[1]);
                    d = coorEncrypt[0];
                    bDLocation.setLongitude(d);
                    bDLocation.setTime(C2735k.m19072a());
                    bDLocation.setCoorType("wgs84");
                    m19070a = C2735k.m19070a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude());
                }
                m19070a = C2735k.m19070a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude());
            } else if (TextUtils.equals(bDLocation2.getCoorType(), bDLocation.getCoorType())) {
                if (TextUtils.equals("bd09", bDLocation2.getCoorType())) {
                    double[] coorEncrypt2 = Jni.coorEncrypt(bDLocation2.getLongitude(), bDLocation2.getLatitude(), "bd092gcj");
                    double[] coorEncrypt3 = Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), "bd092gcj");
                    m19070a = C2735k.m19070a(coorEncrypt2[1], coorEncrypt2[0], coorEncrypt3[1], coorEncrypt3[0]);
                }
                m19070a = C2735k.m19070a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude());
            } else {
                if (TextUtils.equals("wgs84", bDLocation.getCoorType())) {
                    dArr = new double[]{bDLocation.getLongitude(), bDLocation.getLatitude()};
                } else {
                    if (TextUtils.equals("bd09", bDLocation.getCoorType())) {
                        longitude = bDLocation.getLongitude();
                        latitude = bDLocation.getLatitude();
                        str = "bd092gcj";
                    } else if (TextUtils.equals("bd09ll", bDLocation.getCoorType())) {
                        longitude = bDLocation.getLongitude();
                        latitude = bDLocation.getLatitude();
                        str = "bd09ll2gcj";
                    } else {
                        dArr2 = new double[]{bDLocation.getLongitude(), bDLocation.getLatitude()};
                        dArr = Jni.coorEncrypt(dArr2[0], dArr2[1], "gcj2wgs");
                    }
                    dArr2 = Jni.coorEncrypt(longitude, latitude, str);
                    dArr = Jni.coorEncrypt(dArr2[0], dArr2[1], "gcj2wgs");
                }
                bDLocation.setLatitude(dArr[1]);
                d = dArr[0];
                bDLocation.setLongitude(d);
                bDLocation.setTime(C2735k.m19072a());
                bDLocation.setCoorType("wgs84");
                m19070a = C2735k.m19070a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude());
            }
            bDLocation2.setDisToRealLocation(m19070a);
            if (bDLocation != null) {
                bDLocation2.setReallLocation(bDLocation);
            }
            return m19070a;
        }

        /* renamed from: a */
        private int m19537a(double d) {
            if (d < 0.0d || d > 10.0d) {
                if (d <= 10.0d || d > 100.0d) {
                    return (d <= 100.0d || d > 200.0d) ? 3 : 2;
                }
                return 1;
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m19536a(int i) {
            Message obtain = Message.obtain((Handler) null, i);
            try {
                if (this.f5169b != null) {
                    this.f5169b.send(obtain);
                }
                this.f5171d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.f5171d++;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m19535a(int i, Bundle bundle) {
            Message obtain = Message.obtain((Handler) null, i);
            obtain.setData(bundle);
            try {
                if (this.f5169b != null) {
                    this.f5169b.send(obtain);
                }
                this.f5171d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.f5171d++;
                }
                e.printStackTrace();
            }
        }

        /* renamed from: a */
        private void m19534a(int i, String str, BDLocation bDLocation) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(str, bDLocation);
            bundle.setClassLoader(BDLocation.class.getClassLoader());
            Message obtain = Message.obtain((Handler) null, i);
            obtain.setData(bundle);
            try {
                if (this.f5169b != null) {
                    this.f5169b.send(obtain);
                }
                this.f5171d = 0;
            } catch (Exception e) {
                if (e instanceof DeadObjectException) {
                    this.f5171d++;
                }
            }
        }

        /* renamed from: b */
        private BDLocation m19527b() {
            BDLocation m19168h = C2697f.m19228a().m19168h();
            if (m19168h != null) {
                double[] coorEncrypt = Jni.coorEncrypt(m19168h.getLongitude(), m19168h.getLatitude(), "gps2gcj");
                double[] coorEncrypt2 = Jni.coorEncrypt(coorEncrypt[0], coorEncrypt[1], this.f5170c.coorType);
                BDLocation bDLocation = new BDLocation();
                bDLocation.setLongitude(coorEncrypt2[0]);
                bDLocation.setLatitude(coorEncrypt2[1]);
                bDLocation.setTime(C2735k.m19072a());
                bDLocation.setLocType(61);
                bDLocation.setCoorType(this.f5170c.coorType);
                return bDLocation;
            }
            return null;
        }

        /* renamed from: c */
        private BDLocation m19526c() {
            BDLocation m19168h = C2697f.m19228a().m19168h();
            if (m19168h != null) {
                double[] coorEncrypt = Jni.coorEncrypt(m19168h.getLongitude(), m19168h.getLatitude(), "gps2gcj");
                BDLocation bDLocation = new BDLocation();
                bDLocation.setLongitude(coorEncrypt[0]);
                bDLocation.setLatitude(coorEncrypt[1]);
                bDLocation.setTime(C2735k.m19072a());
                bDLocation.setLocType(61);
                bDLocation.setCoorType("gcj02");
                return bDLocation;
            }
            return null;
        }

        /* renamed from: a */
        public int m19533a(int i, boolean z, BDLocation bDLocation) {
            double m19528a;
            if (i == 100) {
                if (z) {
                    BDLocation m19527b = m19527b();
                    if (m19527b != null) {
                        m19528a(true, m19527b, bDLocation);
                        return 3;
                    }
                    return 3;
                }
                BDLocation m19526c = m19526c();
                if (m19526c != null) {
                    m19528a(false, m19526c, bDLocation);
                    return 3;
                }
                return 3;
            } else if (i == 200 || i == 300) {
                return 1;
            } else {
                if (i != 400) {
                    return i == 500 ? 1 : 0;
                }
                if (z) {
                    BDLocation m19527b2 = m19527b();
                    if (m19527b2 == null) {
                        return -1;
                    }
                    m19528a = m19528a(true, m19527b2, bDLocation);
                } else {
                    BDLocation m19526c2 = m19526c();
                    if (m19526c2 == null) {
                        return -1;
                    }
                    m19528a = m19528a(false, m19526c2, bDLocation);
                }
                return m19537a(m19528a);
            }
        }

        /* renamed from: a */
        public void m19538a() {
            if (this.f5170c.location_change_notify) {
                m19536a(C2735k.f5810b ? 54 : 55);
            }
        }

        /* renamed from: a */
        public void m19532a(BDLocation bDLocation) {
            m19531a(bDLocation, 21);
        }

        /* renamed from: a */
        public void m19531a(BDLocation bDLocation, int i) {
            int m19533a;
            String str;
            BDLocation bDLocation2 = new BDLocation(bDLocation);
            if (i == 21) {
                m19534a(27, "locStr", bDLocation2);
            }
            if (this.f5170c.coorType != null && !this.f5170c.coorType.equals("gcj02")) {
                double longitude = bDLocation2.getLongitude();
                double latitude = bDLocation2.getLatitude();
                if (longitude != Double.MIN_VALUE && latitude != Double.MIN_VALUE) {
                    if ((bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals("gcj02")) || bDLocation2.getCoorType() == null) {
                        double[] coorEncrypt = Jni.coorEncrypt(longitude, latitude, this.f5170c.coorType);
                        bDLocation2.setLongitude(coorEncrypt[0]);
                        bDLocation2.setLatitude(coorEncrypt[1]);
                        str = this.f5170c.coorType;
                    } else if (bDLocation2.getCoorType() != null && bDLocation2.getCoorType().equals("wgs84") && !this.f5170c.coorType.equals("bd09ll")) {
                        double[] coorEncrypt2 = Jni.coorEncrypt(longitude, latitude, "wgs842mc");
                        bDLocation2.setLongitude(coorEncrypt2[0]);
                        bDLocation2.setLatitude(coorEncrypt2[1]);
                        str = "wgs84mc";
                    }
                    bDLocation2.setCoorType(str);
                }
                if (!C2735k.f5820l && bDLocation2.getMockGpsStrategy() > 0) {
                    m19533a = m19533a(bDLocation2.getMockGpsStrategy(), true, bDLocation2);
                    bDLocation2.setMockGpsProbability(m19533a);
                }
            } else if (!C2735k.f5820l && bDLocation2.getMockGpsStrategy() > 0) {
                m19533a = m19533a(bDLocation2.getMockGpsStrategy(), false, bDLocation2);
                bDLocation2.setMockGpsProbability(m19533a);
            }
            m19534a(i, "locStr", bDLocation2);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.b$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class RunnableC2630b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ C2628b f5173a;

        /* renamed from: b */
        private int f5174b;

        /* renamed from: c */
        private boolean f5175c;

        @Override // java.lang.Runnable
        public void run() {
            if (this.f5175c) {
                return;
            }
            this.f5174b++;
            this.f5173a.f5166n = false;
        }
    }

    private C2628b() {
        this.f5159g = null;
        this.f5159g = new ArrayList<>();
    }

    /* renamed from: a */
    private C2629a m19557a(Messenger messenger) {
        ArrayList<C2629a> arrayList = this.f5159g;
        if (arrayList == null) {
            return null;
        }
        try {
            Iterator<C2629a> it = arrayList.iterator();
            while (it.hasNext()) {
                C2629a next = it.next();
                if (next.f5169b.equals(messenger)) {
                    return next;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: a */
    public static C2628b m19560a() {
        if (f5155f == null) {
            f5155f = new C2628b();
        }
        return f5155f;
    }

    /* renamed from: a */
    private void m19555a(C2629a c2629a) {
        if (c2629a == null) {
            return;
        }
        synchronized (this.f5163k) {
            if (m19557a(c2629a.f5169b) != null) {
                c2629a.m19536a(14);
            } else {
                this.f5159g.add(c2629a);
                c2629a.m19536a(13);
            }
        }
    }

    /* renamed from: a */
    private void m19553a(String str) {
        Intent intent = new Intent("com.baidu.location.flp.log");
        intent.setPackage("com.baidu.baidulocationdemo");
        intent.putExtra("data", str);
        intent.putExtra("pack", C2721b.f5690e);
        intent.putExtra("tag", "state");
        ServiceC2737f.getServiceContext().sendBroadcast(intent);
    }

    /* renamed from: e */
    private void m19542e() {
        m19540f();
        m19545d();
        m19539g();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m19540f() {
        /*
            r6 = this;
            r0 = 0
            java.util.ArrayList<com.baidu.location.b.b$a> r1 = r6.f5159g     // Catch: java.lang.Exception -> L26
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Exception -> L26
            r2 = r0
        L8:
            boolean r3 = r1.hasNext()     // Catch: java.lang.Exception -> L24
            if (r3 == 0) goto L2b
            java.lang.Object r3 = r1.next()     // Catch: java.lang.Exception -> L24
            com.baidu.location.b.b$a r3 = (com.baidu.location.p137b.C2628b.C2629a) r3     // Catch: java.lang.Exception -> L24
            com.baidu.location.LocationClientOption r4 = r3.f5170c     // Catch: java.lang.Exception -> L24
            boolean r4 = r4.openGps     // Catch: java.lang.Exception -> L24
            r5 = 1
            if (r4 == 0) goto L1c
            r0 = r5
        L1c:
            com.baidu.location.LocationClientOption r3 = r3.f5170c     // Catch: java.lang.Exception -> L24
            boolean r3 = r3.location_change_notify     // Catch: java.lang.Exception -> L24
            if (r3 == 0) goto L8
            r2 = r5
            goto L8
        L24:
            r1 = move-exception
            goto L28
        L26:
            r1 = move-exception
            r2 = r0
        L28:
            r1.printStackTrace()
        L2b:
            com.baidu.location.p140e.C2735k.f5768a = r2
            boolean r1 = r6.f5160h
            if (r1 == r0) goto L3c
            r6.f5160h = r0
            com.baidu.location.c.f r0 = com.baidu.location.p138c.C2697f.m19228a()
            boolean r1 = r6.f5160h
            r0.m19207a(r1)
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2628b.m19540f():void");
    }

    /* renamed from: g */
    private void m19539g() {
        try {
            Iterator<C2629a> it = this.f5159g.iterator();
            while (it.hasNext()) {
                C2735k.f5814f = Math.min(C2735k.f5814f, it.next().f5170c.priority);
            }
            if (ServiceC2737f.isServing) {
                return;
            }
            C2735k.f5814f = 4;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m19559a(Bundle bundle, int i) {
        synchronized (this.f5163k) {
            Iterator<C2629a> it = this.f5159g.iterator();
            while (it.hasNext()) {
                try {
                    C2629a next = it.next();
                    next.m19535a(i, bundle);
                    if (next.f5171d > 4) {
                        it.remove();
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    /* renamed from: a */
    public void m19558a(Message message) {
        if (message == null || message.replyTo == null) {
            return;
        }
        f5153c = System.currentTimeMillis();
        this.f5156a = true;
        C2711l.m19133a().m19126b();
        m19555a(new C2629a(message));
        m19542e();
        if (this.f5165m) {
            m19553a("start");
            this.f5158e = 0;
        }
    }

    /* renamed from: a */
    public void m19556a(BDLocation bDLocation) {
        m19549b(bDLocation);
    }

    /* renamed from: a */
    public void m19552a(boolean z) {
        this.f5156a = z;
        f5154d = z ? 1 : 0;
    }

    /* renamed from: b */
    public void m19551b() {
        synchronized (this.f5163k) {
            try {
                if (this.f5159g != null) {
                    this.f5159g.clear();
                }
            } catch (Throwable unused) {
            }
        }
        this.f5161i = null;
        m19542e();
    }

    /* renamed from: b */
    public void m19550b(Message message) {
        synchronized (this.f5163k) {
            C2629a m19557a = m19557a(message.replyTo);
            if (m19557a != null) {
                this.f5159g.remove(m19557a);
            }
        }
        C2675t.m19327a().m19324c();
        m19542e();
        if (this.f5165m) {
            m19553a("stop");
            this.f5158e = 0;
        }
    }

    /* renamed from: b */
    public void m19549b(BDLocation bDLocation) {
        BDLocation bDLocation2;
        if (bDLocation == null || bDLocation.getLocType() != 161 || C2621a.m19575a().m19573b()) {
            synchronized (this.f5163k) {
                Iterator<C2629a> it = this.f5159g.iterator();
                while (it.hasNext()) {
                    try {
                        C2629a next = it.next();
                        next.m19532a(bDLocation);
                        if (next.f5171d > 4) {
                            it.remove();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        } else {
            if (this.f5162j == null) {
                this.f5162j = new BDLocation();
                this.f5162j.setLocType(505);
            }
            synchronized (this.f5163k) {
                Iterator<C2629a> it2 = this.f5159g.iterator();
                while (it2.hasNext()) {
                    try {
                        C2629a next2 = it2.next();
                        next2.m19532a(this.f5162j);
                        if (next2.f5171d > 4) {
                            it2.remove();
                        }
                    } catch (Exception unused2) {
                    }
                }
            }
        }
        boolean z = C2666p.f5351h;
        if (z) {
            C2666p.f5351h = false;
        }
        if (C2735k.f5764W >= 10000) {
            if (bDLocation.getLocType() == 61 || bDLocation.getLocType() == 161 || bDLocation.getLocType() == 66) {
                BDLocation bDLocation3 = this.f5161i;
                if (bDLocation3 != null) {
                    float[] fArr = new float[1];
                    Location.distanceBetween(bDLocation3.getLatitude(), this.f5161i.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                    if (fArr[0] <= C2735k.f5766Y && !z) {
                        return;
                    }
                    this.f5161i = null;
                    bDLocation2 = new BDLocation(bDLocation);
                } else {
                    bDLocation2 = new BDLocation(bDLocation);
                }
                this.f5161i = bDLocation2;
            }
        }
    }

    /* renamed from: c */
    public String m19548c() {
        StringBuilder sb;
        StringBuffer stringBuffer = new StringBuffer(256);
        if (this.f5159g.isEmpty()) {
            return "&prod=" + C2721b.f5691f + ":" + C2721b.f5690e;
        }
        String stringBuffer2 = stringBuffer.toString();
        try {
            C2629a c2629a = this.f5159g.get(0);
            if (c2629a.f5170c.prodName != null) {
                stringBuffer.append(c2629a.f5170c.prodName);
            }
            if (c2629a.f5168a != null) {
                stringBuffer.append(":");
                stringBuffer.append(c2629a.f5168a);
                stringBuffer.append("|");
            }
            if (stringBuffer2 == null || stringBuffer2.equals("")) {
                sb = new StringBuilder();
                sb.append("&prod=");
                sb.append(C2721b.f5691f);
                sb.append(":");
                stringBuffer2 = C2721b.f5690e;
            } else {
                sb = new StringBuilder();
                sb.append("&prod=");
            }
            sb.append(stringBuffer2);
            return sb.toString();
        } catch (Exception unused) {
            return "&prod=" + C2721b.f5691f + ":" + C2721b.f5690e;
        }
    }

    /* renamed from: c */
    public void m19546c(BDLocation bDLocation) {
        Address m19387a = C2666p.m19375c().m19387a(bDLocation);
        String m19363f = C2666p.m19375c().m19363f();
        List<Poi> m19361g = C2666p.m19375c().m19361g();
        PoiRegion m19359h = C2666p.m19375c().m19359h();
        if (m19387a != null) {
            bDLocation.setAddr(m19387a);
        }
        if (m19363f != null) {
            bDLocation.setLocationDescribe(m19363f);
        }
        if (m19361g != null) {
            bDLocation.setPoiList(m19361g);
        }
        if (m19359h != null) {
            bDLocation.setPoiRegion(m19359h);
        }
        m19556a(bDLocation);
        C2666p.m19375c().m19373c(bDLocation);
    }

    /* renamed from: c */
    public boolean m19547c(Message message) {
        C2629a m19557a = m19557a(message.replyTo);
        boolean z = false;
        if (m19557a == null) {
            return false;
        }
        int i = m19557a.f5170c.scanSpan;
        m19557a.f5170c.scanSpan = message.getData().getInt("scanSpan", m19557a.f5170c.scanSpan);
        if (m19557a.f5170c.scanSpan < 1000) {
            C2675t.m19327a().m19324c();
            this.f5156a = false;
        } else {
            this.f5156a = true;
        }
        if (m19557a.f5170c.scanSpan > 999 && i < 1000) {
            if (m19557a.f5170c.mIsNeedDeviceDirect || m19557a.f5170c.isNeedAltitude) {
                C2675t.m19327a().m19326a(m19557a.f5170c.mIsNeedDeviceDirect);
                C2675t.m19327a().m19325b();
            }
            if (this.f5157b || m19557a.f5170c.isNeedAltitude) {
                z = true;
            }
            this.f5157b = z;
            z = true;
        }
        m19557a.f5170c.openGps = message.getData().getBoolean("openGPS", m19557a.f5170c.openGps);
        String string = message.getData().getString("coorType");
        LocationClientOption locationClientOption = m19557a.f5170c;
        if (string == null || string.equals("")) {
            string = m19557a.f5170c.coorType;
        }
        locationClientOption.coorType = string;
        String string2 = message.getData().getString("addrType");
        LocationClientOption locationClientOption2 = m19557a.f5170c;
        if (string2 == null || string2.equals("")) {
            string2 = m19557a.f5170c.addrType;
        }
        locationClientOption2.addrType = string2;
        if (!C2735k.f5813e.equals(m19557a.f5170c.addrType)) {
            C2666p.m19375c().m19355k();
        }
        m19557a.f5170c.timeOut = message.getData().getInt("timeOut", m19557a.f5170c.timeOut);
        m19557a.f5170c.location_change_notify = message.getData().getBoolean("location_change_notify", m19557a.f5170c.location_change_notify);
        m19557a.f5170c.priority = message.getData().getInt("priority", m19557a.f5170c.priority);
        C2735k.f5814f = m19557a.f5170c.priority;
        int i2 = message.getData().getInt("wifitimeout", Integer.MAX_VALUE);
        if (i2 < C2735k.f5789af) {
            C2735k.f5789af = i2;
        }
        m19542e();
        return z;
    }

    /* renamed from: d */
    public int m19544d(Message message) {
        C2629a m19557a;
        if (message == null || message.replyTo == null || (m19557a = m19557a(message.replyTo)) == null || m19557a.f5170c == null) {
            return 1;
        }
        return C2735k.f5814f;
    }

    /* renamed from: d */
    public void m19545d() {
        try {
            Iterator<C2629a> it = this.f5159g.iterator();
            while (it.hasNext()) {
                it.next().m19538a();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    public void m19543d(BDLocation bDLocation) {
        m19546c(bDLocation);
    }

    /* renamed from: e */
    public int m19541e(Message message) {
        C2629a m19557a;
        if (message == null || message.replyTo == null || (m19557a = m19557a(message.replyTo)) == null || m19557a.f5170c == null) {
            return 1000;
        }
        return m19557a.f5170c.scanSpan;
    }
}
