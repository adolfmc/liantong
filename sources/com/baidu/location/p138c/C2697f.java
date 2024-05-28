package com.baidu.location.p138c;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.GnssMeasurementsEvent;
import android.location.GnssNavigationMessage;
import android.location.GnssStatus;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p137b.C2628b;
import com.baidu.location.p137b.C2631c;
import com.baidu.location.p137b.C2666p;
import com.baidu.location.p137b.C2676u;
import com.baidu.location.p137b.C2684y;
import com.baidu.location.p137b.C2686z;
import com.baidu.location.p140e.C2724c;
import com.baidu.location.p140e.C2726e;
import com.baidu.location.p140e.C2735k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.c.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2697f {

    /* renamed from: L */
    private static String f5526L = null;

    /* renamed from: N */
    private static double f5527N = 100.0d;

    /* renamed from: Q */
    private static float f5528Q = -1.0f;

    /* renamed from: a */
    public static int f5529a = 0;

    /* renamed from: b */
    public static String f5530b = "";

    /* renamed from: c */
    public static String f5531c = "";

    /* renamed from: e */
    private static C2697f f5532e = null;

    /* renamed from: i */
    private static Location f5533i = null;

    /* renamed from: j */
    private static int f5534j = -1;

    /* renamed from: t */
    private static int f5535t;

    /* renamed from: u */
    private static int f5536u;

    /* renamed from: v */
    private static int f5537v;

    /* renamed from: w */
    private static int f5538w;

    /* renamed from: x */
    private static int f5539x;

    /* renamed from: y */
    private static long f5540y;

    /* renamed from: ai */
    private BDLocation f5572ai;

    /* renamed from: am */
    private String f5576am;

    /* renamed from: f */
    private Context f5580f;

    /* renamed from: h */
    private Location f5582h;

    /* renamed from: m */
    private GpsStatus f5585m;

    /* renamed from: n */
    private C2700c f5586n;

    /* renamed from: o */
    private boolean f5587o;

    /* renamed from: q */
    private boolean f5589q;

    /* renamed from: g */
    private LocationManager f5581g = null;

    /* renamed from: k */
    private C2703f f5583k = null;

    /* renamed from: l */
    private C2705h f5584l = null;

    /* renamed from: p */
    private C2701d f5588p = null;

    /* renamed from: r */
    private GpsStatus.NmeaListener f5590r = null;

    /* renamed from: s */
    private OnNmeaMessageListener f5591s = null;

    /* renamed from: z */
    private long f5592z = 0;

    /* renamed from: A */
    private boolean f5541A = false;

    /* renamed from: B */
    private boolean f5542B = false;

    /* renamed from: C */
    private String f5543C = null;

    /* renamed from: D */
    private boolean f5544D = false;

    /* renamed from: E */
    private long f5545E = 0;

    /* renamed from: F */
    private double f5546F = -1.0d;

    /* renamed from: G */
    private double f5547G = 0.0d;

    /* renamed from: H */
    private double f5548H = 0.0d;

    /* renamed from: I */
    private long f5549I = 0;

    /* renamed from: J */
    private long f5550J = 0;

    /* renamed from: K */
    private long f5551K = 0;

    /* renamed from: M */
    private HandlerC2702e f5552M = null;

    /* renamed from: O */
    private long f5553O = 0;

    /* renamed from: P */
    private long f5554P = 0;

    /* renamed from: R */
    private C2698a f5555R = null;

    /* renamed from: S */
    private C2699b f5556S = null;

    /* renamed from: T */
    private ArrayList<ArrayList<Float>> f5557T = new ArrayList<>();

    /* renamed from: U */
    private ArrayList<ArrayList<Float>> f5558U = new ArrayList<>();

    /* renamed from: V */
    private ArrayList<ArrayList<Float>> f5559V = new ArrayList<>();

    /* renamed from: W */
    private ArrayList<ArrayList<Float>> f5560W = new ArrayList<>();

    /* renamed from: X */
    private ArrayList<ArrayList<Float>> f5561X = new ArrayList<>();

    /* renamed from: Y */
    private ArrayList<ArrayList<Float>> f5562Y = new ArrayList<>();

    /* renamed from: Z */
    private ArrayList<ArrayList<Float>> f5563Z = new ArrayList<>();

    /* renamed from: aa */
    private String f5564aa = null;

    /* renamed from: ab */
    private long f5565ab = 0;

    /* renamed from: ac */
    private ArrayList<Integer> f5566ac = new ArrayList<>();

    /* renamed from: ad */
    private String f5567ad = null;

    /* renamed from: ae */
    private String f5568ae = null;

    /* renamed from: af */
    private long f5569af = 0;

    /* renamed from: ag */
    private long f5570ag = -1;

    /* renamed from: ah */
    private long f5571ah = -1;

    /* renamed from: aj */
    private boolean f5573aj = false;

    /* renamed from: ak */
    private boolean f5574ak = false;

    /* renamed from: al */
    private long f5575al = 0;

    /* renamed from: an */
    private long f5577an = 0;

    /* renamed from: ao */
    private StringBuilder f5578ao = new StringBuilder();

    /* renamed from: d */
    public long f5579d = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @TargetApi(24)
    /* renamed from: com.baidu.location.c.f$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2698a extends GnssMeasurementsEvent.Callback {

        /* renamed from: a */
        public int f5593a;

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssMeasurementsEvent) {
        }

        @Override // android.location.GnssMeasurementsEvent.Callback
        public void onStatusChanged(int i) {
            this.f5593a = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @TargetApi(24)
    /* renamed from: com.baidu.location.c.f$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2699b extends GnssNavigationMessage.Callback {

        /* renamed from: a */
        public int f5594a;

        private C2699b() {
            this.f5594a = 0;
        }

        /* synthetic */ C2699b(C2697f c2697f, C2706g c2706g) {
            this();
        }

        @Override // android.location.GnssNavigationMessage.Callback
        public void onGnssNavigationMessageReceived(GnssNavigationMessage gnssNavigationMessage) {
            C2686z m19278a;
            long currentTimeMillis;
            if (C2697f.this.f5551K != 0) {
                m19278a = C2686z.m19278a();
                currentTimeMillis = C2697f.this.f5551K;
            } else {
                m19278a = C2686z.m19278a();
                currentTimeMillis = System.currentTimeMillis() / 1000;
            }
            m19278a.m19277a(gnssNavigationMessage, currentTimeMillis);
        }

        @Override // android.location.GnssNavigationMessage.Callback
        public void onStatusChanged(int i) {
            this.f5594a = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @TargetApi(24)
    /* renamed from: com.baidu.location.c.f$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2700c extends GnssStatus.Callback {
        private C2700c() {
        }

        /* synthetic */ C2700c(C2697f c2697f, C2706g c2706g) {
            this();
        }

        @Override // android.location.GnssStatus.Callback
        public void onFirstFix(int i) {
        }

        @Override // android.location.GnssStatus.Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
            ArrayList arrayList;
            if (C2697f.this.f5581g == null) {
                return;
            }
            C2697f.this.f5554P = System.currentTimeMillis();
            int satelliteCount = gnssStatus.getSatelliteCount();
            C2697f.this.f5560W.clear();
            C2697f.this.f5561X.clear();
            C2697f.this.f5562Y.clear();
            C2697f.this.f5563Z.clear();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < satelliteCount; i4++) {
                i3++;
                ArrayList arrayList2 = new ArrayList();
                int constellationType = gnssStatus.getConstellationType(i4);
                arrayList2.add(Float.valueOf(gnssStatus.getAzimuthDegrees(i4)));
                arrayList2.add(Float.valueOf(gnssStatus.getElevationDegrees(i4)));
                arrayList2.add(Float.valueOf(gnssStatus.getCn0DbHz(i4)));
                if (gnssStatus.usedInFix(i4)) {
                    i++;
                    arrayList2.add(Float.valueOf(1.0f));
                    if (constellationType == 1) {
                        i2++;
                    }
                } else {
                    arrayList2.add(Float.valueOf(0.0f));
                }
                arrayList2.add(Float.valueOf(gnssStatus.getSvid(i4)));
                if (constellationType == 1) {
                    arrayList2.add(Float.valueOf(1.0f));
                    arrayList = C2697f.this.f5560W;
                } else if (constellationType == 5) {
                    arrayList2.add(Float.valueOf(2.0f));
                    arrayList = C2697f.this.f5561X;
                } else if (constellationType == 3) {
                    arrayList2.add(Float.valueOf(3.0f));
                    arrayList = C2697f.this.f5562Y;
                } else if (constellationType == 6) {
                    arrayList2.add(Float.valueOf(4.0f));
                    arrayList = C2697f.this.f5563Z;
                }
                arrayList.add(arrayList2);
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(C2697f.this.f5560W);
            arrayList3.addAll(C2697f.this.f5561X);
            arrayList3.addAll(C2697f.this.f5562Y);
            arrayList3.addAll(C2697f.this.f5563Z);
            C2697f.this.m19196b(arrayList3);
            C2697f c2697f = C2697f.this;
            c2697f.f5557T = c2697f.m19206a(true, false, false, false, true, -1.0f);
            C2697f c2697f2 = C2697f.this;
            C2697f.f5530b = c2697f2.m19209a(c2697f2.f5557T);
            C2697f c2697f3 = C2697f.this;
            c2697f3.f5558U = c2697f3.m19206a(true, true, true, true, true, -1.0f);
            C2697f c2697f4 = C2697f.this;
            c2697f4.f5559V = c2697f4.m19206a(true, true, true, true, false, -1.0f);
            C2697f c2697f5 = C2697f.this;
            C2697f.f5531c = c2697f5.m19209a(c2697f5.f5559V);
            C2697f.f5529a = i;
            int unused = C2697f.f5535t = i2;
            int unused2 = C2697f.f5539x = i3;
            long unused3 = C2697f.f5540y = System.currentTimeMillis();
            C2697f c2697f6 = C2697f.this;
            int unused4 = C2697f.f5536u = c2697f6.m19208a((ArrayList<ArrayList<Float>>) c2697f6.f5562Y, true, -1.0f).size();
            C2697f c2697f7 = C2697f.this;
            int unused5 = C2697f.f5537v = c2697f7.m19208a((ArrayList<ArrayList<Float>>) c2697f7.f5563Z, true, -1.0f).size();
            C2697f c2697f8 = C2697f.this;
            int unused6 = C2697f.f5538w = c2697f8.m19208a((ArrayList<ArrayList<Float>>) c2697f8.f5561X, true, -1.0f).size();
        }

        @Override // android.location.GnssStatus.Callback
        public void onStarted() {
        }

        @Override // android.location.GnssStatus.Callback
        public void onStopped() {
            C2697f.this.m19180e((Location) null);
            C2697f.this.m19195b(false);
            C2697f.f5529a = 0;
            int unused = C2697f.f5535t = 0;
            int unused2 = C2697f.f5536u = 0;
            int unused3 = C2697f.f5537v = 0;
            int unused4 = C2697f.f5538w = 0;
            int unused5 = C2697f.f5534j = -1;
            Location unused6 = C2697f.f5533i = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.f$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2701d implements GpsStatus.Listener {

        /* renamed from: b */
        private long f5598b;

        private C2701d() {
            this.f5598b = 0L;
        }

        /* synthetic */ C2701d(C2697f c2697f, C2706g c2706g) {
            this();
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            long currentTimeMillis;
            ArrayList arrayList;
            if (C2697f.this.f5581g == null) {
                return;
            }
            int i2 = 0;
            if (i == 2) {
                C2697f.this.m19180e((Location) null);
                C2697f.this.m19195b(false);
                C2697f.f5529a = 0;
                int unused = C2697f.f5535t = 0;
                int unused2 = C2697f.f5536u = 0;
                int unused3 = C2697f.f5537v = 0;
                int unused4 = C2697f.f5538w = 0;
            } else if (i == 4 && C2697f.this.f5542B) {
                try {
                    if (C2697f.this.f5585m == null) {
                        C2697f.this.f5585m = C2697f.this.f5581g.getGpsStatus(null);
                    } else {
                        C2697f.this.f5581g.getGpsStatus(C2697f.this.f5585m);
                    }
                    C2697f.this.f5554P = System.currentTimeMillis();
                    C2697f.this.f5560W.clear();
                    C2697f.this.f5561X.clear();
                    C2697f.this.f5562Y.clear();
                    C2697f.this.f5563Z.clear();
                    int i3 = 0;
                    for (GpsSatellite gpsSatellite : C2697f.this.f5585m.getSatellites()) {
                        ArrayList arrayList2 = new ArrayList();
                        int prn = gpsSatellite.getPrn();
                        arrayList2.add(Float.valueOf(gpsSatellite.getAzimuth()));
                        arrayList2.add(Float.valueOf(gpsSatellite.getElevation()));
                        arrayList2.add(Float.valueOf(gpsSatellite.getSnr()));
                        if (gpsSatellite.usedInFix()) {
                            i2++;
                            arrayList2.add(Float.valueOf(1.0f));
                            if (prn >= 1 && prn <= 32) {
                                i3++;
                            }
                        } else {
                            arrayList2.add(Float.valueOf(0.0f));
                        }
                        arrayList2.add(Float.valueOf(prn));
                        if (prn >= 1 && prn <= 32) {
                            arrayList2.add(Float.valueOf(1.0f));
                            arrayList = C2697f.this.f5560W;
                        } else if (prn >= 201 && prn <= 235) {
                            arrayList2.add(Float.valueOf(2.0f));
                            arrayList = C2697f.this.f5561X;
                        } else if (prn >= 65 && prn <= 96) {
                            arrayList2.add(Float.valueOf(3.0f));
                            arrayList = C2697f.this.f5562Y;
                        } else if (prn >= 301 && prn <= 336) {
                            arrayList2.add(Float.valueOf(4.0f));
                            arrayList = C2697f.this.f5563Z;
                        }
                        arrayList.add(arrayList2);
                    }
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.addAll(C2697f.this.f5560W);
                    arrayList3.addAll(C2697f.this.f5561X);
                    arrayList3.addAll(C2697f.this.f5562Y);
                    arrayList3.addAll(C2697f.this.f5563Z);
                    C2697f.this.m19196b(arrayList3);
                    C2697f.this.f5557T = C2697f.this.m19206a(true, false, false, false, true, -1.0f);
                    C2697f.f5530b = C2697f.this.m19209a(C2697f.this.f5557T);
                    C2697f.this.f5558U = C2697f.this.m19206a(true, true, true, true, true, -1.0f);
                    C2697f.this.f5559V = C2697f.this.m19206a(true, true, true, true, false, -1.0f);
                    C2697f.f5531c = C2697f.this.m19209a(C2697f.this.f5559V);
                    if (i3 > 0) {
                        int unused5 = C2697f.f5535t = i3;
                    }
                    if (i2 <= 0) {
                        if (System.currentTimeMillis() - this.f5598b > 100) {
                            currentTimeMillis = System.currentTimeMillis();
                        }
                        long unused6 = C2697f.f5540y = System.currentTimeMillis();
                    }
                    currentTimeMillis = System.currentTimeMillis();
                    this.f5598b = currentTimeMillis;
                    C2697f.f5529a = i2;
                    long unused62 = C2697f.f5540y = System.currentTimeMillis();
                } catch (Exception unused7) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.f$e */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class HandlerC2702e extends Handler {

        /* renamed from: a */
        WeakReference<C2697f> f5599a;

        /* renamed from: b */
        C2697f f5600b;

        HandlerC2702e(C2697f c2697f) {
            this.f5599a = new WeakReference<>(c2697f);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            C2697f c2697f;
            String str;
            if (ServiceC2737f.isServing) {
                this.f5600b = this.f5599a.get();
                if (this.f5600b == null) {
                    return;
                }
                int i = message.what;
                if (i == 1) {
                    this.f5600b.m19170g((Location) message.obj);
                    return;
                }
                switch (i) {
                    case 3:
                        c2697f = this.f5600b;
                        str = "&og=1";
                        break;
                    case 4:
                        c2697f = this.f5600b;
                        str = "&og=2";
                        break;
                    case 5:
                        this.f5600b.m19212a((String) message.obj);
                        return;
                    default:
                        return;
                }
                c2697f.m19210a(str, (Location) message.obj);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.f$f */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2703f implements LocationListener {
        private C2703f() {
        }

        /* synthetic */ C2703f(C2697f c2697f, C2706g c2706g) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (!(location == null && C2735k.f5814f == 4) && !C2735k.m19066a(location) && Math.abs(location.getLatitude()) <= 360.0d && Math.abs(location.getLongitude()) <= 360.0d) {
                C2697f.this.f5551K = location.getTime() / 1000;
                C2697f.this.f5570ag = System.currentTimeMillis();
                if (C2697f.this.f5550J != 0) {
                    C2697f.this.f5549I = System.currentTimeMillis() - C2697f.this.f5550J;
                }
                C2697f.this.f5550J = System.currentTimeMillis();
                int i = C2697f.f5529a;
                if (i == 0) {
                    try {
                        i = location.getExtras().getInt("satellites");
                    } catch (Exception unused) {
                    }
                }
                if (i == 0 || C2666p.m19375c().m19356j()) {
                    System.currentTimeMillis();
                    long unused2 = C2697f.this.f5554P;
                }
                C2697f.this.m19195b(true);
                C2697f.this.m19180e(location);
                C2697f.this.f5541A = false;
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            C2697f.this.m19180e((Location) null);
            C2697f.this.m19195b(false);
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            switch (i) {
                case 0:
                    C2697f.this.m19180e((Location) null);
                    break;
                case 1:
                    C2697f.this.f5592z = System.currentTimeMillis();
                    C2697f.this.f5541A = true;
                    break;
                case 2:
                    C2697f.this.f5541A = false;
                    return;
                default:
                    return;
            }
            C2697f.this.m19195b(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.f$g */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2704g implements GpsStatus.NmeaListener {
        private C2704g() {
        }

        /* synthetic */ C2704g(C2697f c2697f, C2706g c2706g) {
            this();
        }

        @Override // android.location.GpsStatus.NmeaListener
        public void onNmeaReceived(long j, String str) {
            if (C2697f.this.f5552M != null) {
                C2697f.this.f5552M.sendMessage(C2697f.this.f5552M.obtainMessage(5, str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.f$h */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2705h implements LocationListener {

        /* renamed from: b */
        private long f5604b;

        private C2705h() {
            this.f5604b = 0L;
        }

        /* synthetic */ C2705h(C2697f c2697f, C2706g c2706g) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (!(C2697f.this.f5542B && C2735k.f5814f == 4) && location != null && TextUtils.equals(location.getProvider(), "gps") && System.currentTimeMillis() - this.f5604b >= 10000 && Math.abs(location.getLatitude()) <= 360.0d && Math.abs(location.getLongitude()) <= 360.0d && C2684y.m19293a(location, false)) {
                this.f5604b = System.currentTimeMillis();
                if (C2697f.this.f5552M != null) {
                    C2697f.this.f5579d = System.currentTimeMillis();
                    C2697f.this.f5552M.sendMessage(C2697f.this.f5552M.obtainMessage(4, location));
                }
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    private C2697f() {
        this.f5587o = false;
        this.f5589q = false;
        this.f5576am = null;
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Class.forName("android.location.GnssStatus");
                this.f5587o = true;
            } catch (ClassNotFoundException unused) {
                this.f5587o = false;
            }
        }
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                this.f5576am = Build.MANUFACTURER;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.f5589q = false;
    }

    /* renamed from: a */
    public static synchronized C2697f m19228a() {
        C2697f c2697f;
        synchronized (C2697f.class) {
            if (f5532e == null) {
                f5532e = new C2697f();
            }
            c2697f = f5532e;
        }
        return c2697f;
    }

    /* renamed from: a */
    public static String m19225a(Location location) {
        Locale locale;
        String str;
        Object[] objArr;
        StringBuilder sb;
        if (location == null) {
            return null;
        }
        float speed = (float) (location.getSpeed() * 3.6d);
        if (!location.hasSpeed()) {
            speed = -1.0f;
        }
        int accuracy = (int) (location.hasAccuracy() ? location.getAccuracy() : -1.0f);
        double altitude = location.hasAltitude() ? location.getAltitude() : 555.0d;
        float bearing = location.hasBearing() ? location.getBearing() : -1.0f;
        if (f5528Q < -0.01f) {
            locale = Locale.CHINA;
            str = "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d&ll_sn=%d|%d|%d|%d|%d&ll_snr=%.1f";
            objArr = new Object[]{Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(bearing), Integer.valueOf(accuracy), Integer.valueOf(f5529a), Double.valueOf(altitude), Long.valueOf(location.getTime() / 1000), Integer.valueOf(f5529a), Integer.valueOf(f5535t), Integer.valueOf(f5536u), Integer.valueOf(f5537v), Integer.valueOf(f5538w), Double.valueOf(f5527N)};
        } else {
            locale = Locale.CHINA;
            str = "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d&ll_sn=%d|%d|%d|%d|%d&ll_snr=%.1f&ll_bp=%.2f";
            objArr = new Object[]{Double.valueOf(location.getLongitude()), Double.valueOf(location.getLatitude()), Float.valueOf(speed), Float.valueOf(bearing), Integer.valueOf(accuracy), Integer.valueOf(f5529a), Double.valueOf(altitude), Long.valueOf(location.getTime() / 1000), Integer.valueOf(f5529a), Integer.valueOf(f5535t), Integer.valueOf(f5536u), Integer.valueOf(f5537v), Integer.valueOf(f5538w), Double.valueOf(f5527N), Float.valueOf(f5528Q)};
        }
        String format = String.format(locale, str, objArr);
        try {
            if (f5534j != 2 || f5533i == null) {
                sb = new StringBuilder();
                sb.append(format);
                sb.append("&ll_fake=");
                sb.append(f5534j);
            } else {
                sb = new StringBuilder();
                sb.append(format);
                sb.append(String.format(Locale.CHINA, "&ll_fake=%d|%.5f|%.5f|%d", Integer.valueOf(f5534j), Double.valueOf(f5533i.getLongitude()), Double.valueOf(f5533i.getLatitude()), Long.valueOf(f5533i.getTime() / 1000)));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return format;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m19209a(ArrayList<ArrayList<Float>> arrayList) {
        StringBuilder sb = new StringBuilder();
        if (arrayList.size() == 0) {
            return sb.toString();
        }
        Iterator<ArrayList<Float>> it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            ArrayList<Float> next = it.next();
            if (next.size() == 6) {
                if (z) {
                    z = false;
                } else {
                    sb.append("|");
                }
                sb.append(String.format("%.1f;", next.get(0)));
                sb.append(String.format("%.1f;", next.get(1)));
                sb.append(String.format("%.1f;", next.get(2)));
                sb.append(String.format("%.0f;", next.get(3)));
                sb.append(String.format("%.0f;", next.get(4)));
                sb.append(String.format("%.0f", next.get(5)));
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public ArrayList<ArrayList<Float>> m19208a(ArrayList<ArrayList<Float>> arrayList, boolean z, float f) {
        ArrayList<ArrayList<Float>> arrayList2 = new ArrayList<>();
        if (arrayList.size() <= 40 && arrayList.size() != 0) {
            Iterator<ArrayList<Float>> it = arrayList.iterator();
            while (it.hasNext()) {
                ArrayList<Float> next = it.next();
                if (next.size() == 6) {
                    float floatValue = next.get(3).floatValue();
                    float floatValue2 = next.get(2).floatValue();
                    if (!z || floatValue >= 1.0f) {
                        if (f <= 0.0f || floatValue2 >= f) {
                            arrayList2.add(next);
                        }
                    }
                }
            }
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public ArrayList<ArrayList<Float>> m19206a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, float f) {
        ArrayList<ArrayList<Float>> arrayList = new ArrayList<>();
        if (z) {
            arrayList.addAll(m19208a(this.f5560W, z5, f));
        }
        if (z2) {
            arrayList.addAll(m19208a(this.f5561X, z5, f));
        }
        if (z3) {
            arrayList.addAll(m19208a(this.f5562Y, z5, f));
        }
        if (z4) {
            arrayList.addAll(m19208a(this.f5563Z, z5, f));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19212a(String str) {
        if (TextUtils.isEmpty(str) || !m19197b(str)) {
            return;
        }
        if (str.startsWith("$GPGGA,")) {
            m19211a(str, 2, 4, 6);
        } else if (str.startsWith("$GPRMC,")) {
            m19211a(str, 3, 5, 2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00e9, code lost:
        if (android.text.TextUtils.equals(r0[r14], "A") != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00f6, code lost:
        if (android.text.TextUtils.equals(r0[r14], "0") != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f9, code lost:
        r10.f5573aj = true;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m19211a(java.lang.String r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p138c.C2697f.m19211a(java.lang.String, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19210a(String str, Location location) {
        if (location == null) {
            return;
        }
        String str2 = str + C2628b.m19560a().m19548c();
        boolean m19121e = C2711l.m19133a().m19121e();
        C2676u.m19318a(new C2688a(C2689b.m19259a().m19237f()));
        C2676u.m19320a(System.currentTimeMillis());
        C2676u.m19319a(new Location(location));
        C2676u.m19317a(str2);
        C2676u.m19315b(C2631c.m19525a().m19515c());
        if (m19121e) {
            return;
        }
        C2684y.m19292a(C2676u.m19314c(), (C2710k) null, C2676u.m19313d(), str2, C2676u.m19312e());
    }

    /* renamed from: a */
    public static boolean m19224a(Location location, Location location2, boolean z) {
        if (location == location2) {
            return false;
        }
        if (location == null || location2 == null) {
            return true;
        }
        float speed = location2.getSpeed();
        if (!z || ((C2735k.f5829u != 3 && C2726e.m19082a().m19080a(location2.getLongitude(), location2.getLatitude())) || speed >= 5.0f)) {
            float distanceTo = location2.distanceTo(location);
            return speed > C2735k.f5752K ? distanceTo > C2735k.f5754M : speed > C2735k.f5751J ? distanceTo > C2735k.f5753L : distanceTo > 5.0f;
        }
        return true;
    }

    /* renamed from: b */
    public static String m19203b(Location location) {
        String m19225a = m19225a(location);
        if (m19225a != null) {
            return m19225a + "&g_tp=0";
        }
        return m19225a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19196b(ArrayList<ArrayList<Float>> arrayList) {
        String str;
        if (arrayList == null || arrayList.size() <= 0) {
            str = null;
        } else {
            StringBuilder sb = new StringBuilder(100);
            sb.append(C2724c.m19084g(this.f5560W));
            sb.append("|");
            sb.append(C2724c.m19085f(this.f5560W));
            sb.append("|");
            sb.append(C2724c.m19090a(this.f5560W));
            sb.append("|");
            sb.append(C2724c.m19083h(this.f5560W));
            sb.append("|");
            sb.append(C2724c.m19089b(this.f5560W));
            sb.append("|");
            sb.append(C2724c.m19088c(this.f5560W));
            sb.append("|");
            sb.append(C2724c.m19086e(this.f5560W));
            sb.append("|");
            sb.append(C2724c.m19087d(this.f5560W));
            str = sb.toString();
        }
        this.f5564aa = str;
        this.f5565ab = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19195b(boolean z) {
        this.f5544D = z;
        f5528Q = -1.0f;
    }

    /* renamed from: b */
    private boolean m19197b(String str) {
        int i;
        if (str.indexOf("*") != -1 && str.indexOf("$") != -1 && str.indexOf("$") <= str.indexOf("*") && str.length() >= str.indexOf("*")) {
            byte[] bytes = str.substring(0, str.indexOf("*")).getBytes();
            int i2 = bytes[1];
            for (int i3 = 2; i3 < bytes.length; i3++) {
                i2 ^= bytes[i3];
            }
            String format = String.format("%02x", Integer.valueOf(i2));
            int indexOf = str.indexOf("*");
            if (indexOf != -1 && str.length() >= (i = indexOf + 3) && format.equalsIgnoreCase(str.substring(indexOf + 1, i))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public static String m19192c(Location location) {
        String m19225a = m19225a(location);
        if (m19225a != null) {
            return m19225a + f5526L;
        }
        return m19225a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m19180e(Location location) {
        if (this.f5552M == null || System.currentTimeMillis() - this.f5577an <= 3000) {
            return;
        }
        this.f5552M.sendMessage(this.f5552M.obtainMessage(1, location));
    }

    /* renamed from: f */
    private int m19174f(Location location) {
        if (location == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT <= 17 || !location.isFromMockProvider()) {
            if (Math.abs(this.f5570ag - this.f5571ah) >= 3000) {
                this.f5571ah = -1L;
                this.f5574ak = false;
                this.f5573aj = false;
                this.f5572ai = null;
            } else if (this.f5572ai == null) {
                if (!this.f5573aj) {
                    return 200;
                }
                if (this.f5574ak) {
                    return 300;
                }
            } else if (!this.f5574ak && this.f5573aj) {
                return 400;
            }
            if (this.f5570ag > 0) {
                if (this.f5571ah == -1) {
                    return 500;
                }
            }
            return 0;
        }
        return 100;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m19170g(Location location) {
        String str = null;
        if (location == null) {
            this.f5582h = null;
            return;
        }
        if (f5529a == 0) {
            try {
                location.getExtras().getInt("satellites");
            } catch (Exception unused) {
            }
        }
        if (this.f5589q && C2735k.m19069a(location.getSpeed(), 0.0f) && !C2735k.m19071a(this.f5547G, 0.0d) && System.currentTimeMillis() - this.f5548H < 2000.0d) {
            location.setSpeed((float) this.f5547G);
        }
        Location location2 = new Location(location);
        this.f5545E = System.currentTimeMillis();
        this.f5582h = location;
        int i = f5529a;
        if (this.f5582h != null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.f5582h.setTime(currentTimeMillis);
            float speed = (float) (this.f5582h.getSpeed() * 3.6d);
            if (!this.f5582h.hasSpeed()) {
                speed = -1.0f;
            }
            if (i == 0) {
                try {
                    i = this.f5582h.getExtras().getInt("satellites");
                } catch (Exception unused2) {
                }
            }
            str = String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", Double.valueOf(this.f5582h.getLongitude()), Double.valueOf(this.f5582h.getLatitude()), Float.valueOf(speed), Float.valueOf(this.f5582h.getBearing()), Integer.valueOf(i), Long.valueOf(currentTimeMillis));
        }
        this.f5543C = str;
        if (this.f5582h != null) {
            BDLocation bDLocation = new BDLocation(m19176f());
            C2631c.m19525a().m19522a(bDLocation, "gcj02", this.f5582h);
            bDLocation.setExtrainfo(location.getExtras());
            Location location3 = this.f5582h;
            if (location3 != null && "bd_beidou".equals(location3.getProvider())) {
                bDLocation.setGnssProvider("bd_beidou");
            }
            m19223a(bDLocation);
            if (f5529a > 2 && C2684y.m19293a(this.f5582h, true) && "gps".equals(this.f5582h.getProvider())) {
                boolean m19121e = C2711l.m19133a().m19121e();
                C2676u.m19318a(new C2688a(C2689b.m19259a().m19237f()));
                C2676u.m19320a(System.currentTimeMillis());
                C2676u.m19319a(new Location(this.f5582h));
                C2676u.m19317a(C2628b.m19560a().m19548c());
                C2676u.m19315b(C2631c.m19525a().m19515c());
                if (!m19121e) {
                    C2686z.m19278a().m19274b();
                }
            }
        }
        if ("gps".equals(location2.getProvider())) {
            C2686z.m19278a().m19276a(location2, f5529a);
        }
    }

    /* renamed from: l */
    public static String m19160l() {
        long currentTimeMillis = System.currentTimeMillis() - f5540y;
        if (currentTimeMillis < 0 || currentTimeMillis >= 3000) {
            return null;
        }
        return String.format(Locale.US, "&gsvn=%d&gsfn=%d", Integer.valueOf(f5539x), Integer.valueOf(f5529a));
    }

    /* renamed from: a */
    public void m19223a(BDLocation bDLocation) {
        if (C2735k.f5820l || m19174f(this.f5582h) <= 0) {
            C2628b.m19560a().m19543d(bDLocation);
        } else {
            C2628b.m19560a().m19546c(bDLocation);
        }
    }

    /* renamed from: a */
    public void m19207a(boolean z) {
        if (z) {
            m19194c();
        } else {
            m19188d();
        }
    }

    /* renamed from: b */
    public synchronized void m19205b() {
        if (ServiceC2737f.isServing) {
            this.f5580f = ServiceC2737f.getServiceContext();
            try {
                this.f5581g = (LocationManager) this.f5580f.getSystemService("location");
            } catch (Exception unused) {
            }
            this.f5552M = new HandlerC2702e(this);
        }
    }

    /* renamed from: c */
    public void m19194c() {
        Log.d("baidu_location_service", "GpsMan start gps...");
        if (this.f5542B) {
            return;
        }
        try {
            if (!this.f5587o) {
                this.f5588p = new C2701d(this, null);
                this.f5581g.addGpsStatusListener(this.f5588p);
            } else if (C2735k.m19067a(this.f5580f, "android.permission.ACCESS_FINE_LOCATION") == 1) {
                this.f5586n = new C2700c(this, null);
                this.f5581g.registerGnssStatusCallback(this.f5586n);
            }
            this.f5584l = new C2705h(this, null);
            this.f5581g.requestLocationUpdates("passive", 9000L, 0.0f, this.f5584l);
        } catch (Exception unused) {
        }
        try {
            this.f5583k = new C2703f(this, null);
            try {
                this.f5581g.sendExtraCommand("gps", "force_xtra_injection", new Bundle());
            } catch (Exception unused2) {
            }
            this.f5581g.requestLocationUpdates("gps", 1000L, 0.0f, this.f5583k);
            if (this.f5587o && this.f5556S == null && C2735k.f5770aB == 1 && new Random().nextDouble() < C2735k.f5769aA) {
                this.f5556S = new C2699b(this, null);
            }
            if (this.f5556S != null) {
                this.f5581g.registerGnssNavigationMessageCallback(this.f5556S);
            }
            this.f5553O = System.currentTimeMillis();
            if (!C2735k.f5820l && C2735k.f5781aM == 1) {
                if (Build.VERSION.SDK_INT >= 24) {
                    this.f5591s = new C2706g(this);
                    this.f5581g.addNmeaListener(this.f5591s);
                } else {
                    this.f5590r = new C2704g(this, null);
                    Class.forName("android.location.LocationManager").getMethod("addNmeaListener", GpsStatus.NmeaListener.class).invoke(this.f5581g, this.f5590r);
                }
            }
            this.f5542B = true;
        } catch (Exception unused3) {
        }
    }

    /* renamed from: d */
    public void m19188d() {
        if (this.f5542B) {
            LocationManager locationManager = this.f5581g;
            if (locationManager != null) {
                try {
                    if (this.f5588p != null) {
                        locationManager.removeGpsStatusListener(this.f5588p);
                        this.f5588p = null;
                    }
                    if (this.f5587o && this.f5586n != null) {
                        this.f5581g.unregisterGnssStatusCallback(this.f5586n);
                        this.f5586n = null;
                    }
                    if (this.f5584l != null) {
                        this.f5581g.removeUpdates(this.f5584l);
                        this.f5584l = null;
                    }
                } catch (Exception unused) {
                }
                try {
                    if (this.f5583k != null) {
                        this.f5581g.removeUpdates(this.f5583k);
                    }
                    if (this.f5591s != null) {
                        this.f5581g.removeNmeaListener(this.f5591s);
                    }
                    if (this.f5590r != null) {
                        Class.forName("android.location.LocationManager").getMethod("removeNmeaListener", GpsStatus.NmeaListener.class).invoke(this.f5581g, this.f5590r);
                    }
                    if (this.f5556S != null) {
                        this.f5581g.unregisterGnssNavigationMessageCallback(this.f5556S);
                    }
                    m19162k();
                } catch (Exception unused2) {
                }
            }
            C2735k.f5812d = 0;
            C2735k.f5829u = 0;
            this.f5583k = null;
            this.f5542B = false;
            m19195b(false);
        }
    }

    /* renamed from: e */
    public synchronized void m19182e() {
        m19188d();
        if (this.f5581g == null) {
            return;
        }
        try {
            if (this.f5552M != null) {
                this.f5552M.removeCallbacksAndMessages(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f5581g = null;
    }

    /* renamed from: f */
    public String m19176f() {
        boolean z;
        StringBuilder sb;
        String str;
        if (this.f5582h != null) {
            String str2 = "{\"result\":{\"time\":\"" + C2735k.m19072a() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\",\"s\":\"%f\",\"n\":\"%d\"";
            int accuracy = (int) (this.f5582h.hasAccuracy() ? this.f5582h.getAccuracy() : 10.0f);
            float speed = (float) (this.f5582h.getSpeed() * 3.6d);
            if (!this.f5582h.hasSpeed()) {
                speed = -1.0f;
            }
            double[] dArr = new double[2];
            if (C2726e.m19082a().m19080a(this.f5582h.getLongitude(), this.f5582h.getLatitude())) {
                dArr = Jni.coorEncrypt(this.f5582h.getLongitude(), this.f5582h.getLatitude(), "gps2gcj");
                if (dArr[0] <= 0.0d && dArr[1] <= 0.0d) {
                    dArr[0] = this.f5582h.getLongitude();
                    dArr[1] = this.f5582h.getLatitude();
                }
                z = true;
            } else {
                dArr[0] = this.f5582h.getLongitude();
                dArr[1] = this.f5582h.getLatitude();
                if (dArr[0] <= 0.0d && dArr[1] <= 0.0d) {
                    dArr[0] = this.f5582h.getLongitude();
                    dArr[1] = this.f5582h.getLatitude();
                }
                z = false;
            }
            String format = String.format(Locale.CHINA, str2, Double.valueOf(dArr[0]), Double.valueOf(dArr[1]), Integer.valueOf(accuracy), Float.valueOf(this.f5582h.getBearing()), Float.valueOf(speed), Integer.valueOf(f5529a));
            if (!z) {
                format = format + ",\"in_cn\":\"0\"";
            }
            if (!C2735k.f5820l) {
                format = format + String.format(Locale.CHINA, ",\"is_mock\":%d", Integer.valueOf(m19174f(this.f5582h)));
            }
            if (this.f5582h.hasAltitude()) {
                sb = new StringBuilder();
                sb.append(format);
                str = String.format(Locale.CHINA, ",\"h\":%.2f}}", Double.valueOf(this.f5582h.getAltitude()));
            } else {
                sb = new StringBuilder();
                sb.append(format);
                str = "}}";
            }
            sb.append(str);
            return sb.toString();
        }
        return null;
    }

    /* renamed from: g */
    public Location m19171g() {
        if (this.f5582h != null && Math.abs(System.currentTimeMillis() - this.f5582h.getTime()) <= 60000) {
            return this.f5582h;
        }
        return null;
    }

    /* renamed from: h */
    public BDLocation m19168h() {
        if (this.f5572ai != null && Math.abs(System.currentTimeMillis() - this.f5571ah) <= 3000) {
            return this.f5572ai;
        }
        return null;
    }

    /* renamed from: i */
    public boolean m19166i() {
        try {
            System.currentTimeMillis();
            long j = this.f5554P;
            if (f5529a == 0) {
                try {
                    this.f5582h.getExtras().getInt("satellites");
                } catch (Exception unused) {
                }
            }
            if (this.f5582h != null && this.f5582h.getLatitude() != 0.0d) {
                if (this.f5582h.getLongitude() != 0.0d) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused2) {
            Location location = this.f5582h;
            return (location == null || location.getLatitude() == 0.0d || this.f5582h.getLongitude() == 0.0d) ? false : true;
        }
    }

    /* renamed from: j */
    public boolean m19164j() {
        if (m19166i() && System.currentTimeMillis() - this.f5545E <= 10000) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!this.f5541A || currentTimeMillis - this.f5592z >= 3000) {
                return this.f5544D;
            }
            return true;
        }
        return false;
    }

    /* renamed from: k */
    public void m19162k() {
        C2698a c2698a;
        LocationManager locationManager;
        if (!this.f5587o || (c2698a = this.f5555R) == null || (locationManager = this.f5581g) == null) {
            return;
        }
        try {
            locationManager.unregisterGnssMeasurementsCallback(c2698a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f5555R = null;
    }

    /* renamed from: m */
    public synchronized String m19158m() {
        String str;
        str = Math.abs(System.currentTimeMillis() - this.f5565ab) < 3000 ? this.f5564aa == null ? "0" : this.f5564aa : "-1";
        return "&gnsf=" + str;
    }
}
