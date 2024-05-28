package com.baidu.location;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.location.LocationClientOption;
import com.baidu.location.p136a.C2621a;
import com.baidu.location.p137b.C2623a;
import com.baidu.location.p137b.C2634e;
import com.baidu.location.p137b.C2658o;
import com.baidu.location.p140e.C2735k;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class LocationClient implements C2634e.InterfaceC2636b {
    public static final int CONNECT_HOT_SPOT_FALSE = 0;
    public static final int CONNECT_HOT_SPOT_TRUE = 1;
    public static final int CONNECT_HOT_SPOT_UNKNOWN = -1;

    /* renamed from: G */
    private static boolean f5076G = false;
    public static final int LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS = 1;
    public static final int LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI = 2;
    public static final int LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN = 9;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION = 4;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET = 3;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE = 7;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI = 6;
    public static final int LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH = 5;
    public static final int LOC_DIAGNOSTIC_TYPE_SERVER_FAIL = 8;

    /* renamed from: B */
    private boolean f5078B;

    /* renamed from: c */
    private LocationClientOption f5086c;

    /* renamed from: d */
    private LocationClientOption f5087d;

    /* renamed from: f */
    private Context f5089f;

    /* renamed from: h */
    private HandlerC2616a f5091h;

    /* renamed from: i */
    private final Messenger f5092i;

    /* renamed from: v */
    private String f5105v;

    /* renamed from: a */
    private long f5084a = 0;

    /* renamed from: b */
    private String f5085b = null;

    /* renamed from: e */
    private boolean f5088e = false;

    /* renamed from: g */
    private Messenger f5090g = null;

    /* renamed from: j */
    private ArrayList<BDLocationListener> f5093j = null;

    /* renamed from: k */
    private ArrayList<BDAbstractLocationListener> f5094k = null;

    /* renamed from: l */
    private BDLocation f5095l = null;

    /* renamed from: m */
    private boolean f5096m = false;

    /* renamed from: n */
    private boolean f5097n = false;

    /* renamed from: o */
    private boolean f5098o = false;

    /* renamed from: p */
    private RunnableC2617b f5099p = null;

    /* renamed from: q */
    private boolean f5100q = false;

    /* renamed from: r */
    private final Object f5101r = new Object();

    /* renamed from: s */
    private long f5102s = 0;

    /* renamed from: t */
    private long f5103t = 0;

    /* renamed from: u */
    private String f5104u = null;

    /* renamed from: w */
    private boolean f5106w = false;

    /* renamed from: x */
    private boolean f5107x = true;

    /* renamed from: y */
    private Boolean f5108y = false;

    /* renamed from: z */
    private Boolean f5109z = false;

    /* renamed from: A */
    private Boolean f5077A = true;

    /* renamed from: C */
    private C2634e f5079C = null;

    /* renamed from: D */
    private boolean f5080D = false;

    /* renamed from: E */
    private boolean f5081E = false;

    /* renamed from: F */
    private boolean f5082F = false;

    /* renamed from: H */
    private ServiceConnection f5083H = new ServiceConnectionC2622b(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.LocationClient$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class HandlerC2616a extends Handler {

        /* renamed from: a */
        private final WeakReference<LocationClient> f5110a;

        HandlerC2616a(Looper looper, LocationClient locationClient) {
            super(looper);
            this.f5110a = new WeakReference<>(locationClient);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            LocationClient locationClient = this.f5110a.get();
            if (locationClient == null) {
                return;
            }
            int i = message.what;
            if (i == 21) {
                Bundle data = message.getData();
                data.setClassLoader(BDLocation.class.getClassLoader());
                BDLocation bDLocation = (BDLocation) data.getParcelable("locStr");
                if (!locationClient.f5081E && locationClient.f5080D && bDLocation.getLocType() == 66) {
                    return;
                }
                if (!locationClient.f5081E && locationClient.f5080D) {
                    locationClient.f5081E = true;
                    return;
                }
                if (!locationClient.f5081E) {
                    locationClient.f5081E = true;
                }
                locationClient.m19625a(message, 21);
                return;
            }
            try {
                if (i == 303) {
                    Bundle data2 = message.getData();
                    int i2 = data2.getInt("loctype");
                    int i3 = data2.getInt("diagtype");
                    byte[] byteArray = data2.getByteArray("diagmessage");
                    if (i2 <= 0 || i3 <= 0 || byteArray == null || locationClient.f5094k == null) {
                        return;
                    }
                    Iterator it = locationClient.f5094k.iterator();
                    while (it.hasNext()) {
                        ((BDAbstractLocationListener) it.next()).onLocDiagnosticMessage(i2, i3, new String(byteArray, "UTF-8"));
                    }
                } else if (i == 406) {
                    Bundle data3 = message.getData();
                    byte[] byteArray2 = data3.getByteArray("mac");
                    String str = byteArray2 != null ? new String(byteArray2, "UTF-8") : null;
                    int i4 = data3.getInt("hotspot", -1);
                    if (locationClient.f5094k != null) {
                        Iterator it2 = locationClient.f5094k.iterator();
                        while (it2.hasNext()) {
                            ((BDAbstractLocationListener) it2.next()).onConnectHotSpotMessage(str, i4);
                        }
                    }
                } else if (i == 701) {
                    locationClient.m19611b((BDLocation) message.obj);
                } else if (i == 1300) {
                    locationClient.m19606c(message);
                } else if (i == 1400) {
                    locationClient.m19601d(message);
                } else {
                    switch (i) {
                        case 1:
                            locationClient.m19629a();
                            return;
                        case 2:
                            locationClient.m19613b();
                            return;
                        case 3:
                            locationClient.m19626a(message);
                            return;
                        case 4:
                            locationClient.m19597e();
                            return;
                        case 5:
                            locationClient.m19612b(message);
                            return;
                        case 6:
                            locationClient.m19596e(message);
                            return;
                        default:
                            switch (i) {
                                case 54:
                                    if (locationClient.f5086c.location_change_notify) {
                                        locationClient.f5100q = true;
                                        return;
                                    }
                                    return;
                                case 55:
                                    if (locationClient.f5086c.location_change_notify) {
                                        locationClient.f5100q = false;
                                        return;
                                    }
                                    return;
                                default:
                                    switch (i) {
                                        case 703:
                                            Bundle data4 = message.getData();
                                            int i5 = data4.getInt("id", 0);
                                            if (i5 > 0) {
                                                locationClient.m19627a(i5, (Notification) data4.getParcelable("notification"));
                                                return;
                                            }
                                            return;
                                        case 704:
                                            locationClient.m19614a(message.getData().getBoolean("removenotify"));
                                            return;
                                        default:
                                            super.handleMessage(message);
                                            return;
                                    }
                            }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.LocationClient$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class RunnableC2617b implements Runnable {
        private RunnableC2617b() {
        }

        /* synthetic */ RunnableC2617b(LocationClient locationClient, ServiceConnectionC2622b serviceConnectionC2622b) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (LocationClient.this.f5101r) {
                LocationClient.this.f5098o = false;
                if (LocationClient.this.f5090g != null && LocationClient.this.f5092i != null) {
                    if ((LocationClient.this.f5093j != null && LocationClient.this.f5093j.size() >= 1) || (LocationClient.this.f5094k != null && LocationClient.this.f5094k.size() >= 1)) {
                        if (!LocationClient.this.f5097n) {
                            LocationClient.this.f5091h.obtainMessage(4).sendToTarget();
                            return;
                        }
                        if (LocationClient.this.f5099p == null) {
                            LocationClient.this.f5099p = new RunnableC2617b();
                        }
                        LocationClient.this.f5091h.postDelayed(LocationClient.this.f5099p, LocationClient.this.f5086c.scanSpan);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.LocationClient$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2618c extends Thread {
        private C2618c() {
        }

        /* synthetic */ C2618c(LocationClient locationClient, ServiceConnectionC2622b serviceConnectionC2622b) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (LocationClient.this.f5077A.booleanValue()) {
                    if (LocationClient.this.f5079C == null) {
                        LocationClient.this.f5079C = new C2634e(LocationClient.this.f5089f, LocationClient.this.f5087d, LocationClient.this, null);
                    }
                    if (LocationClient.this.f5087d.firstLocType == LocationClientOption.FirstLocType.ACCURACY_IN_FIRST_LOC) {
                        LocationClient.this.f5079C.m19492d();
                        LocationClient.this.f5079C.m19490e();
                    }
                }
                LocationClient.this.f5091h.obtainMessage(1).sendToTarget();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public LocationClient(Context context) throws Exception {
        this.f5086c = new LocationClientOption();
        this.f5087d = new LocationClientOption();
        this.f5089f = null;
        m19607c();
        this.f5089f = context;
        this.f5086c = new LocationClientOption();
        this.f5087d = new LocationClientOption();
        this.f5091h = new HandlerC2616a(Looper.getMainLooper(), this);
        this.f5092i = new Messenger(this.f5091h);
    }

    public LocationClient(Context context, LocationClientOption locationClientOption) throws Exception {
        this.f5086c = new LocationClientOption();
        this.f5087d = new LocationClientOption();
        this.f5089f = null;
        m19607c();
        this.f5089f = context;
        this.f5086c = locationClientOption;
        this.f5087d = new LocationClientOption(locationClientOption);
        this.f5091h = new HandlerC2616a(Looper.getMainLooper(), this);
        this.f5092i = new Messenger(this.f5091h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19629a() {
        if (this.f5088e) {
            return;
        }
        if (this.f5077A.booleanValue()) {
            boolean m19050c = C2735k.m19050c(this.f5089f);
            if (this.f5087d.isOnceLocation()) {
                m19050c = true;
            }
            if (m19050c) {
                try {
                    new C2687c(this).start();
                } catch (Throwable unused) {
                }
            }
        }
        if (this.f5087d.isOnceLocation()) {
            return;
        }
        this.f5077A = false;
        this.f5085b = this.f5089f.getPackageName();
        this.f5104u = this.f5085b + "_bdls_v2.9";
        Intent intent = new Intent(this.f5089f, ServiceC2737f.class);
        try {
            intent.putExtra("debug_dev", this.f5078B);
        } catch (Exception unused2) {
        }
        if (this.f5086c == null) {
            this.f5086c = new LocationClientOption();
        }
        intent.putExtra("cache_exception", this.f5086c.isIgnoreCacheException);
        intent.putExtra("kill_process", this.f5086c.isIgnoreKillProcess);
        try {
            this.f5089f.bindService(intent, this.f5083H, 1);
        } catch (Exception e) {
            e.printStackTrace();
            this.f5088e = false;
        }
    }

    /* renamed from: a */
    private void m19628a(int i) {
        if (this.f5095l.getCoorType() == null) {
            this.f5095l.setCoorType(this.f5086c.coorType);
        }
        if (this.f5096m || ((this.f5086c.location_change_notify && this.f5095l.getLocType() == 61) || this.f5095l.getLocType() == 66 || this.f5095l.getLocType() == 67 || this.f5106w || this.f5095l.getLocType() == 161)) {
            ArrayList<BDLocationListener> arrayList = this.f5093j;
            if (arrayList != null) {
                Iterator<BDLocationListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().onReceiveLocation(this.f5095l);
                }
            }
            ArrayList<BDAbstractLocationListener> arrayList2 = this.f5094k;
            if (arrayList2 != null) {
                Iterator<BDAbstractLocationListener> it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    it2.next().onReceiveLocation(this.f5095l);
                }
            }
            if (this.f5095l.getLocType() == 66 || this.f5095l.getLocType() == 67) {
                return;
            }
            this.f5096m = false;
            this.f5103t = System.currentTimeMillis();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19627a(int i, Notification notification) {
        try {
            Intent intent = new Intent(this.f5089f, ServiceC2737f.class);
            intent.putExtra("notification", notification);
            intent.putExtra("id", i);
            intent.putExtra("command", 1);
            if (Build.VERSION.SDK_INT >= 26) {
                this.f5089f.startForegroundService(intent);
            } else {
                this.f5089f.startService(intent);
            }
            this.f5082F = true;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19626a(Message message) {
        this.f5097n = false;
        if (message == null || message.obj == null) {
            return;
        }
        LocationClientOption locationClientOption = (LocationClientOption) message.obj;
        if (this.f5086c.optionEquals(locationClientOption)) {
            return;
        }
        if (this.f5086c.scanSpan != locationClientOption.scanSpan) {
            try {
                synchronized (this.f5101r) {
                    if (this.f5098o) {
                        this.f5091h.removeCallbacks(this.f5099p);
                        this.f5098o = false;
                    }
                    if (locationClientOption.scanSpan >= 1000 && !this.f5098o) {
                        if (this.f5099p == null) {
                            this.f5099p = new RunnableC2617b(this, null);
                        }
                        this.f5091h.postDelayed(this.f5099p, locationClientOption.scanSpan);
                        this.f5098o = true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        this.f5086c = new LocationClientOption(locationClientOption);
        if (this.f5090g != null && C2735k.m19042f(this.f5089f) >= 1) {
            try {
                Message obtain = Message.obtain((Handler) null, 15);
                obtain.replyTo = this.f5092i;
                obtain.setData(m19602d());
                this.f5090g.send(obtain);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19625a(Message message, int i) {
        if (this.f5088e) {
            try {
                Bundle data = message.getData();
                data.setClassLoader(BDLocation.class.getClassLoader());
                this.f5095l = (BDLocation) data.getParcelable("locStr");
                if (this.f5095l.getLocType() == 61) {
                    this.f5102s = System.currentTimeMillis();
                }
                if (this.f5095l.getLocType() == 61 || this.f5095l.getLocType() == 161) {
                    C2623a.m19570a().m19569a(this.f5095l.getLatitude(), this.f5095l.getLongitude(), this.f5095l.getCoorType());
                }
                m19628a(i);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private void m19624a(BDLocation bDLocation) {
        ArrayList<BDLocationListener> arrayList = this.f5093j;
        if (arrayList != null) {
            Iterator<BDLocationListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onReceiveLocation(bDLocation);
            }
        }
        ArrayList<BDAbstractLocationListener> arrayList2 = this.f5094k;
        if (arrayList2 != null) {
            Iterator<BDAbstractLocationListener> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().onReceiveLocation(bDLocation);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19614a(boolean z) {
        try {
            Intent intent = new Intent(this.f5089f, ServiceC2737f.class);
            intent.putExtra("removenotify", z);
            intent.putExtra("command", 2);
            this.f5089f.startService(intent);
            this.f5082F = true;
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19613b() {
        if (!this.f5088e || this.f5090g == null) {
            return;
        }
        Message obtain = Message.obtain((Handler) null, 12);
        obtain.replyTo = this.f5092i;
        try {
            this.f5090g.send(obtain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.f5089f.unbindService(this.f5083H);
            if (this.f5082F) {
                try {
                    this.f5089f.stopService(new Intent(this.f5089f, ServiceC2737f.class));
                } catch (Exception unused) {
                }
                this.f5082F = false;
            }
        } catch (Exception unused2) {
        }
        synchronized (this.f5101r) {
            try {
                if (this.f5098o) {
                    this.f5091h.removeCallbacks(this.f5099p);
                    this.f5098o = false;
                }
            } catch (Exception unused3) {
            }
        }
        this.f5090g = null;
        this.f5097n = false;
        this.f5106w = false;
        this.f5088e = false;
        this.f5080D = false;
        this.f5081E = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19612b(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
        if (this.f5093j == null) {
            this.f5093j = new ArrayList<>();
        }
        if (this.f5093j.contains(bDLocationListener)) {
            return;
        }
        this.f5093j.add(bDLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19611b(BDLocation bDLocation) {
        if (this.f5107x) {
            return;
        }
        this.f5095l = bDLocation;
        if (!this.f5081E && bDLocation.getLocType() == 161) {
            this.f5080D = true;
            C2623a.m19570a().m19569a(bDLocation.getLatitude(), bDLocation.getLongitude(), bDLocation.getCoorType());
        }
        ArrayList<BDLocationListener> arrayList = this.f5093j;
        if (arrayList != null) {
            Iterator<BDLocationListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onReceiveLocation(bDLocation);
            }
        }
        ArrayList<BDAbstractLocationListener> arrayList2 = this.f5094k;
        if (arrayList2 != null) {
            Iterator<BDAbstractLocationListener> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().onReceiveLocation(bDLocation);
            }
        }
    }

    /* renamed from: c */
    private void m19607c() throws Exception {
        if (f5076G) {
            return;
        }
        Log.e("baidu_location_Client", "The location function has been stopped because you do not agree with the privacy compliance policy. Please recheck the setAgreePrivacy interface");
        throw new Exception("The location function has been stopped because you do not agree with the privacy compliance policy. Please recheck the setAgreePrivacy interface");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m19606c(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDAbstractLocationListener bDAbstractLocationListener = (BDAbstractLocationListener) message.obj;
        if (this.f5094k == null) {
            this.f5094k = new ArrayList<>();
        }
        if (this.f5094k.contains(bDAbstractLocationListener)) {
            return;
        }
        this.f5094k.add(bDAbstractLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public Bundle m19602d() {
        if (this.f5086c == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("packName", this.f5085b);
        bundle.putString("prodName", this.f5086c.prodName);
        bundle.putString("coorType", this.f5086c.coorType);
        bundle.putString("addrType", this.f5086c.addrType);
        bundle.putBoolean("openGPS", this.f5086c.openGps);
        bundle.putBoolean("location_change_notify", this.f5086c.location_change_notify);
        bundle.putInt("scanSpan", this.f5086c.scanSpan);
        bundle.putBoolean("enableSimulateGps", this.f5086c.enableSimulateGps);
        bundle.putInt("timeOut", this.f5086c.timeOut);
        bundle.putInt("priority", this.f5086c.priority);
        bundle.putBoolean("map", this.f5108y.booleanValue());
        bundle.putBoolean("import", this.f5109z.booleanValue());
        bundle.putBoolean("needDirect", this.f5086c.mIsNeedDeviceDirect);
        bundle.putBoolean("isneedaptag", this.f5086c.isNeedAptag);
        bundle.putBoolean("isneedpoiregion", this.f5086c.isNeedPoiRegion);
        bundle.putBoolean("isneedregular", this.f5086c.isNeedRegular);
        bundle.putBoolean("isneedaptagd", this.f5086c.isNeedAptagd);
        bundle.putBoolean("isneedaltitude", this.f5086c.isNeedAltitude);
        bundle.putBoolean("isneednewrgc", this.f5086c.isNeedNewVersionRgc);
        bundle.putInt("autoNotifyMaxInterval", this.f5086c.m19577a());
        bundle.putInt("autoNotifyMinTimeInterval", this.f5086c.getAutoNotifyMinTimeInterval());
        bundle.putInt("autoNotifyMinDistance", this.f5086c.getAutoNotifyMinDistance());
        bundle.putFloat("autoNotifyLocSensitivity", this.f5086c.m19576b());
        bundle.putInt("wifitimeout", this.f5086c.wifiCacheTimeOut);
        bundle.putInt("wfnum", C2623a.m19570a().f5125b);
        bundle.putBoolean("ischeckper", C2623a.m19570a().f5124a);
        bundle.putFloat("wfsm", (float) C2623a.m19570a().f5126c);
        bundle.putDouble("gnmcrm", C2623a.m19570a().f5129f);
        bundle.putInt("gnmcon", C2623a.m19570a().f5130g);
        bundle.putInt("iupl", C2623a.m19570a().f5131h);
        bundle.putInt("lpcs", C2623a.m19570a().f5128e);
        bundle.putInt("hpdts", C2623a.m19570a().f5138o);
        bundle.putInt("oldts", C2623a.m19570a().f5139p);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m19601d(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDAbstractLocationListener bDAbstractLocationListener = (BDAbstractLocationListener) message.obj;
        ArrayList<BDAbstractLocationListener> arrayList = this.f5094k;
        if (arrayList == null || !arrayList.contains(bDAbstractLocationListener)) {
            return;
        }
        this.f5094k.remove(bDAbstractLocationListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m19597e() {
        int i;
        if (this.f5090g == null) {
            return;
        }
        int m19042f = C2735k.m19042f(this.f5089f);
        if ((System.currentTimeMillis() - this.f5102s > 3000 || !this.f5086c.location_change_notify || this.f5097n) && m19042f == 1) {
            if (!this.f5106w || System.currentTimeMillis() - this.f5103t > 20000 || this.f5097n) {
                Message obtain = Message.obtain((Handler) null, 22);
                if (this.f5097n) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("isWaitingLocTag", this.f5097n);
                    this.f5097n = false;
                    obtain.setData(bundle);
                }
                try {
                    obtain.replyTo = this.f5092i;
                    this.f5090g.send(obtain);
                    this.f5084a = System.currentTimeMillis();
                    this.f5096m = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (m19042f < 1) {
            BDLocation bDLocation = new BDLocation();
            if (m19042f == -1) {
                i = 69;
            } else if (m19042f == -2) {
                i = 70;
            } else {
                if (m19042f == 0) {
                    i = 71;
                }
                m19624a(bDLocation);
            }
            bDLocation.setLocType(i);
            m19624a(bDLocation);
        }
        synchronized (this.f5101r) {
            if (this.f5086c != null && this.f5086c.scanSpan >= 1000 && !this.f5098o) {
                if (this.f5099p == null) {
                    this.f5099p = new RunnableC2617b(this, null);
                }
                this.f5091h.postDelayed(this.f5099p, this.f5086c.scanSpan);
                this.f5098o = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m19596e(Message message) {
        if (message == null || message.obj == null) {
            return;
        }
        BDLocationListener bDLocationListener = (BDLocationListener) message.obj;
        ArrayList<BDLocationListener> arrayList = this.f5093j;
        if (arrayList == null || !arrayList.contains(bDLocationListener)) {
            return;
        }
        this.f5093j.remove(bDLocationListener);
    }

    public static BDLocation getBDLocationInCoorType(BDLocation bDLocation, String str) {
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        double[] coorEncrypt = Jni.coorEncrypt(bDLocation.getLongitude(), bDLocation.getLatitude(), str);
        bDLocation2.setLatitude(coorEncrypt[1]);
        bDLocation2.setLongitude(coorEncrypt[0]);
        return bDLocation2;
    }

    public static void setAgreePrivacy(boolean z) {
        f5076G = z;
    }

    public void disableAssistantLocation() {
        C2658o.m19414a().m19404b();
    }

    public void disableLocInForeground(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("removenotify", z);
        Message obtainMessage = this.f5091h.obtainMessage(704);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    public void enableAssistantLocation(WebView webView) {
        C2658o.m19414a().m19412a(this.f5089f, webView, this);
    }

    public void enableLocInForeground(int i, Notification notification) {
        if (i <= 0 || notification == null) {
            Log.e("baidu_location_Client", "can not startLocInForeground if the param is unlegal");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("id", i);
        bundle.putParcelable("notification", notification);
        Message obtainMessage = this.f5091h.obtainMessage(703);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    public String getAccessKey() {
        try {
            this.f5105v = C2621a.m19572b(this.f5089f);
            if (TextUtils.isEmpty(this.f5105v)) {
                throw new IllegalStateException("please setting key from Manifest.xml");
            }
            return String.format("KEY=%s", this.f5105v);
        } catch (Exception unused) {
            return null;
        }
    }

    public BDLocation getLastKnownLocation() {
        return this.f5095l;
    }

    public LocationClientOption getLocOption() {
        return this.f5086c;
    }

    public String getVersion() {
        return "9.3.3.3";
    }

    public boolean isStarted() {
        return this.f5088e;
    }

    public void onReceiveLightLocString(String str) {
    }

    @Override // com.baidu.location.p137b.C2634e.InterfaceC2636b
    public void onReceiveLocation(BDLocation bDLocation) {
        if ((!this.f5081E || this.f5080D) && bDLocation != null) {
            Message obtainMessage = this.f5091h.obtainMessage(701);
            obtainMessage.obj = bDLocation;
            obtainMessage.sendToTarget();
        }
    }

    public void registerLocationListener(BDAbstractLocationListener bDAbstractLocationListener) {
        if (bDAbstractLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message obtainMessage = this.f5091h.obtainMessage(1300);
        obtainMessage.obj = bDAbstractLocationListener;
        obtainMessage.sendToTarget();
    }

    public void registerLocationListener(BDLocationListener bDLocationListener) {
        if (bDLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message obtainMessage = this.f5091h.obtainMessage(5);
        obtainMessage.obj = bDLocationListener;
        obtainMessage.sendToTarget();
    }

    public boolean requestHotSpotState() {
        if (this.f5090g != null && this.f5088e) {
            try {
                this.f5090g.send(Message.obtain((Handler) null, 406));
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public int requestLocation() {
        ArrayList<BDAbstractLocationListener> arrayList;
        if (this.f5090g == null || this.f5092i == null) {
            return 1;
        }
        ArrayList<BDLocationListener> arrayList2 = this.f5093j;
        if ((arrayList2 == null || arrayList2.size() < 1) && ((arrayList = this.f5094k) == null || arrayList.size() < 1)) {
            return 2;
        }
        if (System.currentTimeMillis() - this.f5084a < 1000) {
            return 6;
        }
        this.f5097n = true;
        Message obtainMessage = this.f5091h.obtainMessage(4);
        obtainMessage.arg1 = 0;
        obtainMessage.sendToTarget();
        return 0;
    }

    public void restart() {
        stop();
        this.f5107x = false;
        this.f5091h.sendEmptyMessageDelayed(1, 1000L);
    }

    public void setLocOption(LocationClientOption locationClientOption) {
        if (locationClientOption == null) {
            locationClientOption = new LocationClientOption();
        }
        if (locationClientOption.m19577a() > 0) {
            locationClientOption.setScanSpan(0);
            locationClientOption.setLocationNotify(true);
        }
        this.f5087d = new LocationClientOption(locationClientOption);
        Message obtainMessage = this.f5091h.obtainMessage(3);
        obtainMessage.obj = locationClientOption;
        obtainMessage.sendToTarget();
    }

    public void start() {
        this.f5107x = false;
        LBSAuthManager.getInstance(this.f5089f.getApplicationContext()).setPrivacyMode(f5076G);
        C2623a.m19570a().m19568a(this.f5089f, this.f5087d, (String) null);
        new C2618c(this, null).start();
    }

    public void stop() {
        this.f5107x = true;
        this.f5091h.obtainMessage(2).sendToTarget();
        this.f5079C = null;
    }

    public void unRegisterLocationListener(BDAbstractLocationListener bDAbstractLocationListener) {
        if (bDAbstractLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message obtainMessage = this.f5091h.obtainMessage(1400);
        obtainMessage.obj = bDAbstractLocationListener;
        obtainMessage.sendToTarget();
    }

    public void unRegisterLocationListener(BDLocationListener bDLocationListener) {
        if (bDLocationListener == null) {
            throw new IllegalStateException("please set a non-null listener");
        }
        Message obtainMessage = this.f5091h.obtainMessage(6);
        obtainMessage.obj = bDLocationListener;
        obtainMessage.sendToTarget();
    }

    public boolean updateLocation(Location location) {
        if (this.f5090g == null || this.f5092i == null || location == null) {
            return false;
        }
        try {
            Message obtain = Message.obtain((Handler) null, 57);
            obtain.obj = location;
            this.f5090g.send(obtain);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
