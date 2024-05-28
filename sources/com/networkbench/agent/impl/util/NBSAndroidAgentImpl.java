package com.networkbench.agent.impl.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.background.C6246a;
import com.networkbench.agent.impl.background.InterfaceC6247b;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.crash.C6317b;
import com.networkbench.agent.impl.crash.NativeCrashInterface;
import com.networkbench.agent.impl.harvest.ApplicationInformation;
import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.harvest.DeviceData;
import com.networkbench.agent.impl.harvest.DeviceInformation;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.HarvestConnection;
import com.networkbench.agent.impl.harvest.HarvestURLConnection;
import com.networkbench.agent.impl.harvest.SystemInfo;
import com.networkbench.agent.impl.harvest.p260a.C6448l;
import com.networkbench.agent.impl.harvest.p260a.EnumC6455q;
import com.networkbench.agent.impl.instrumentation.NetworkLibInit;
import com.networkbench.agent.impl.p241b.p242a.RunnableC6228a;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p243c.C6305i;
import com.networkbench.agent.impl.p243c.EnumC6263c;
import com.networkbench.agent.impl.p243c.p244a.C6250b;
import com.networkbench.agent.impl.p252e.C6353e;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.C6422g;
import com.networkbench.agent.impl.p264j.RunnableC6488d;
import com.networkbench.agent.impl.plugin.p274e.C6565g;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSAndroidAgentImpl implements InterfaceC6247b {

    /* renamed from: a */
    public static final float f17066a = 500.0f;

    /* renamed from: b */
    private static final float f17067b = 500.0f;

    /* renamed from: c */
    private static final InterfaceC6393e f17068c = C6394f.m10150a();

    /* renamed from: d */
    private final Context f17069d;

    /* renamed from: g */
    private C6646o f17072g;

    /* renamed from: h */
    private LocationListener f17073h;

    /* renamed from: k */
    private DeviceInformation f17076k;

    /* renamed from: l */
    private ApplicationInformation f17077l;

    /* renamed from: e */
    private final ArrayList<C6281e> f17070e = new ArrayList<>();

    /* renamed from: f */
    private C6305i f17071f = C6305i.f15808c;

    /* renamed from: i */
    private final Lock f17074i = new ReentrantLock();

    /* renamed from: j */
    private boolean f17075j = false;

    /* renamed from: k */
    public boolean m9152k() {
        return false;
    }

    public NBSAndroidAgentImpl(Context context) {
        this.f17069d = m9163c(context);
        this.f17072g = new C6646o(this.f17069d);
        this.f17072g.m8837e();
        m9143t();
        C6369q.m10273a().m10272a(new Runnable() { // from class: com.networkbench.agent.impl.util.NBSAndroidAgentImpl.1
            @Override // java.lang.Runnable
            public void run() {
                InterfaceC6393e interfaceC6393e = NBSAndroidAgentImpl.f17068c;
                interfaceC6393e.mo10116d("getVersion : " + NBSAgent.getVersion());
                InterfaceC6393e interfaceC6393e2 = NBSAndroidAgentImpl.f17068c;
                interfaceC6393e2.mo10116d("saveState : " + NBSAndroidAgentImpl.this.f17072g.m8819k());
                if (!NBSAgent.getVersion().equals(NBSAndroidAgentImpl.this.f17072g.m8819k())) {
                    C6638h.m8963w().m9014d(true);
                    NBSAndroidAgentImpl.this.f17072g.m8815l(NBSAgent.getVersion());
                    return;
                }
                C6638h.m8963w().m9014d(false);
                NBSAndroidAgentImpl.this.f17072g.m8815l(NBSAgent.getVersion());
            }
        });
        NBSApplicationStateMonitor.getInstance().addApplicationStateListener(this);
        RunnableC6488d.m9807a(context);
        Application m8714d = C6653u.m8714d();
        if (m8714d != null && C6638h.m8963w().m9038ah()) {
            m8714d.registerActivityLifecycleCallbacks(C6353e.m10315a());
        }
        C6638h.m8963w().m8994i(C6317b.m10449b(context));
    }

    /* renamed from: t */
    private void m9143t() {
        if (C6638h.m8963w().m9085B()) {
            if (this.f17069d.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", m9158e().getPackageId()) == -1) {
                C6396h.m10131k("not handlerAddLocation!");
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.networkbench.agent.impl.util.NBSAndroidAgentImpl.2
                    @Override // java.lang.Runnable
                    public void run() {
                        NBSAndroidAgentImpl.this.addLocationListener();
                    }
                }, 5000L);
            }
        }
    }

    /* renamed from: a */
    public void m9178a() {
        if (!m9152k()) {
            try {
                m9169b();
                C6638h.f17112l = false;
                C6638h.f17108h = System.nanoTime();
                Harvest.start();
                if (C6638h.m8963w().m9061Z()) {
                    C6396h.m10138d("  NBSANRMonitor.getInstance().start();");
                    RunnableC6228a.m10943a().m10942b();
                    return;
                }
                return;
            } catch (Exception e) {
                f17068c.mo10120a("NBSAndroidAgentImpl start() has an error : ", e);
                return;
            }
        }
        m9170a(false);
    }

    /* renamed from: b */
    protected void m9169b() {
        try {
            Harvest.addHarvestListener(this.f17072g);
            Harvest.initialize();
            Harvest.setHarvestConfiguration(this.f17072g.m8834f());
            C6422g.m10014a();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: c */
    public DeviceInformation m9164c() {
        String str;
        DeviceInformation deviceInformation = this.f17076k;
        if (deviceInformation != null) {
            deviceInformation.addMisc("uid", C6638h.m8963w().m8964v());
            return this.f17076k;
        }
        DeviceInformation deviceInformation2 = new DeviceInformation();
        deviceInformation2.setOsName("Android");
        deviceInformation2.setOsVersion(Build.VERSION.RELEASE);
        deviceInformation2.setModel(Build.MODEL);
        deviceInformation2.setAgentName("agent-android");
        deviceInformation2.setAgentVersion(NBSAgent.getVersion());
        deviceInformation2.setManufacturer(Build.MANUFACTURER);
        deviceInformation2.setDeviceId(m9137z());
        deviceInformation2.setScreenSize(m9142u());
        deviceInformation2.addMisc("size", Integer.valueOf(m9168b(this.f17069d).ordinal()));
        if (!TextUtils.isEmpty(m9146q())) {
            deviceInformation2.addMisc("brsAgentMd5", m9146q());
        }
        String m8964v = C6638h.m8963w().m8964v();
        if (!TextUtils.isEmpty(m8964v)) {
            deviceInformation2.addMisc("uid", m8964v);
        } else {
            try {
                deviceInformation2.addMisc("uid", this.f17069d.getSharedPreferences(C6638h.m8981m(this.f17069d.getPackageName()), 0).getString("userId", ""));
            } catch (Exception e) {
                C6638h.f17124y.mo10121a("getDeviceInformation error :", e);
            }
        }
        try {
            str = NativeCrashInterface.getNativeVersion();
        } catch (Throwable unused) {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            deviceInformation2.addMisc("ndkversion", str);
        }
        DeviceData.userId = m8964v;
        this.f17076k = deviceInformation2;
        return this.f17076k;
    }

    /* renamed from: d */
    public DeviceData m9161d() {
        DeviceData deviceData = new DeviceData();
        if (C6642k.m8906d(this.f17069d) == 1) {
            deviceData.setCarrier("");
        } else {
            deviceData.setCarrier(NBSAgent.getActiveNetworkCarrier());
        }
        deviceData.setConnectType(C6642k.m8906d(this.f17069d));
        deviceData.setNetwrokType(C6642k.m8904e(this.f17069d));
        Location m8967s = C6638h.m8963w().m8967s();
        try {
            if (m8967s != null) {
                deviceData.setLatitude(m8967s.getLatitude());
                deviceData.setLongitude(m8967s.getLongitude());
            } else {
                deviceData.setLatitude(0.0d);
                deviceData.setLongitude(0.0d);
            }
        } catch (Exception e) {
            f17068c.mo10121a("location is not null,but getLatitude() or getLongtitude() occur an error ", e);
            deviceData.setLatitude(0.0d);
            deviceData.setLongitude(0.0d);
        }
        return deviceData;
    }

    /* renamed from: e */
    public ApplicationInformation m9158e() {
        ApplicationInformation applicationInformation = this.f17077l;
        if (applicationInformation != null) {
            return applicationInformation;
        }
        this.f17077l = new ApplicationInformation(this.f17069d);
        this.f17077l.generateAppInfo();
        return this.f17077l;
    }

    /* renamed from: b */
    private static EnumC6263c m9168b(Context context) {
        int i = context.getResources().getConfiguration().screenLayout & 15;
        switch (i) {
            case 1:
                return EnumC6263c.SMALL;
            case 2:
                return EnumC6263c.NORMAL;
            case 3:
                return EnumC6263c.LARGE;
            default:
                if (i > 3) {
                    return EnumC6263c.XLARGE;
                }
                return EnumC6263c.UNKNOWN;
        }
    }

    /* renamed from: a */
    public static Boolean m9176a(Context context, String str) {
        if (context.getPackageManager().checkPermission(str, context.getPackageName()) == -1) {
            return false;
        }
        return true;
    }

    /* renamed from: u */
    private double m9142u() {
        try {
            DisplayMetrics displayMetrics = this.f17069d.getResources().getDisplayMetrics();
            return Math.sqrt(Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d) + Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d));
        } catch (Exception unused) {
            return 0.0d;
        }
    }

    /* renamed from: c */
    private static Context m9163c(Context context) {
        return context instanceof Application ? context.getApplicationContext() : context;
    }

    /* renamed from: f */
    public List<C6281e> m9157f() {
        ArrayList arrayList;
        synchronized (this.f17070e) {
            arrayList = new ArrayList(this.f17070e);
            this.f17070e.clear();
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m9171a(List<C6281e> list) {
        this.f17074i.lock();
        try {
            if (this.f17071f.m10504k()) {
                long m10503l = this.f17071f.m10503l();
                this.f17074i.unlock();
                synchronized (this.f17070e) {
                    int size = ((int) m10503l) - this.f17070e.size();
                    if (size > 0) {
                        Iterator<C6281e> it = list.iterator();
                        for (int i = 0; i < size && it.hasNext(); i++) {
                            this.f17070e.add(it.next());
                        }
                    }
                }
            }
        } finally {
            this.f17074i.unlock();
        }
    }

    /* renamed from: g */
    public int m9156g() {
        this.f17074i.lock();
        try {
            return this.f17072g.m8802s();
        } finally {
            this.f17074i.unlock();
        }
    }

    /* renamed from: h */
    public int m9155h() {
        this.f17074i.lock();
        try {
            return this.f17072g.m8834f().getErrRspSize();
        } finally {
            this.f17074i.unlock();
        }
    }

    /* renamed from: i */
    public void m9154i() {
        C6638h.f17109i = System.nanoTime();
        m9170a(true);
    }

    /* renamed from: a */
    private void m9170a(boolean z) {
        RunnableC6228a.m10943a().m10940d();
        RunnableC6488d.m9799e();
        C6250b.m10858a();
        if (z) {
            C6638h.f17112l = true;
            Harvest.harvestNow();
        }
        Harvest.shutdown();
        C6565g.m9379b();
        C6422g.m10010b();
    }

    /* renamed from: j */
    public void m9153j() {
        InterfaceC6393e interfaceC6393e = f17068c;
        interfaceC6393e.mo10115e("PERMANENTLY DISABLING AGENT v" + NBSAgent.getVersion());
        try {
            this.f17072g.m8823i(NBSAgent.getVersion());
            try {
                m9170a(false);
            } finally {
            }
        } catch (Throwable th) {
            try {
                m9170a(false);
                throw th;
            } finally {
            }
        }
    }

    /* renamed from: l */
    public String m9151l() {
        String simOperator = ((TelephonyManager) this.f17069d.getSystemService("phone")).getSimOperator();
        return C6644m.m8887c(simOperator) ? "00000" : simOperator;
    }

    /* renamed from: a */
    public void m9177a(Context context) {
        try {
            if (this.f17072g != null && C6638h.m8963w().m9062Y() && Build.VERSION.SDK_INT < 27) {
                C6638h.m8963w().m9002g(NetworkLibInit.installNetworkMonitor());
                C6638h.f17124y.mo10122a("--->init network in : NBSAndroidAgentImpl start...");
            }
            NBSAgent.start();
            C6638h.f17111k = C6317b.m10452a();
        } catch (Throwable th) {
            f17068c.mo10121a("Failed to initialize the agent: ", th);
        }
    }

    /* renamed from: d */
    private static void m9160d(Context context) {
        if (C6638h.m8963w().f17181x && C6317b.m10440i(context)) {
            m9141v();
        }
    }

    /* renamed from: v */
    private static void m9141v() {
        new Thread(new Runnable() { // from class: com.networkbench.agent.impl.util.NBSAndroidAgentImpl.3
            @Override // java.lang.Runnable
            public void run() {
                NBSAndroidAgentImpl.m9140w();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: w */
    public static void m9140w() {
        try {
            new HarvestURLConnection(C6653u.m8697h(), NBSAgent.getDeviceInformation().initUserHeaderValue(), C6638h.m8963w().m9086A()).sendDataStr(null, C6448l.m9964a(EnumC6455q.UPDATE_HINT, HarvestConnection.redirectHost, true));
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f17068c;
            interfaceC6393e.mo10116d("getUpdateHintRunnable error:" + e.getMessage());
        }
    }

    @Override // com.networkbench.agent.impl.background.InterfaceC6247b
    /* renamed from: a */
    public void mo9174a(C6246a c6246a) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.networkbench.agent.impl.util.NBSAndroidAgentImpl.4
            @Override // java.lang.Runnable
            public void run() {
                NBSAndroidAgentImpl.this.m9139x();
            }
        }, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: x */
    public void m9139x() {
        if (C6638h.m8963w().m8976o()) {
            try {
                m9169b();
                C6638h.f17112l = false;
                C6638h.f17108h = System.nanoTime();
                Harvest.start();
                if (C6638h.m8963w().m9061Z()) {
                    RunnableC6228a.m10943a().m10942b();
                }
            } catch (Throwable th) {
                f17068c.mo10120a("NBSAndroidAgentImpl start() has an error : ", th);
            }
        }
    }

    @Override // com.networkbench.agent.impl.background.InterfaceC6247b
    /* renamed from: b */
    public void mo9166b(C6246a c6246a) {
        m9154i();
    }

    /* renamed from: a */
    public void m9172a(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Country code and administrative region are required.");
        }
    }

    /* renamed from: a */
    public void m9175a(Location location) {
        Address address;
        if (location == null) {
            throw new IllegalArgumentException("Location must not be null.");
        }
        List<Address> list = null;
        try {
            list = new Geocoder(this.f17069d).getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            InterfaceC6393e interfaceC6393e = f17068c;
            interfaceC6393e.mo10116d("Unable to geocode location: " + e.toString());
        }
        if (list == null || list.size() == 0 || (address = list.get(0)) == null) {
            return;
        }
        String countryCode = address.getCountryCode();
        String adminArea = address.getAdminArea();
        if (countryCode == null || adminArea == null) {
            return;
        }
        m9138y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void addLocationListener() {
        try {
            final LocationManager locationManager = (LocationManager) this.f17069d.getSystemService("location");
            if (locationManager == null) {
                f17068c.mo10116d("Unable to retrieve reference to LocationManager. Disabling location listener.");
                return;
            }
            Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
            if (lastKnownLocation == null) {
                lastKnownLocation = locationManager.getLastKnownLocation("network");
            }
            if (lastKnownLocation == null) {
                lastKnownLocation = locationManager.getLastKnownLocation("passive");
            }
            C6638h.m8963w().m9055a(lastKnownLocation);
            this.f17073h = new C6651s(locationManager);
            new Thread(new Runnable() { // from class: com.networkbench.agent.impl.util.NBSAndroidAgentImpl.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Looper.prepare();
                        locationManager.requestLocationUpdates("passive", 1000L, 500.0f, NBSAndroidAgentImpl.this.f17073h, Looper.myLooper());
                        Looper.loop();
                    } catch (Exception e) {
                        InterfaceC6393e interfaceC6393e = NBSAndroidAgentImpl.f17068c;
                        interfaceC6393e.mo10116d("NBSAndroidAgentImpl addLocationListener  Thread looper has error :" + e);
                    }
                }
            }).start();
        } catch (Exception e) {
            f17068c.mo10121a("locationManager.requestLocationUpdates() occur an error ", e);
        }
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: y */
    private void m9138y() {
        if (this.f17073h == null) {
            return;
        }
        LocationManager locationManager = (LocationManager) this.f17069d.getSystemService("location");
        if (locationManager == null) {
            f17068c.mo10116d("Unable to retrieve reference to LocationManager. Can't unregister location listener.");
            return;
        }
        synchronized (locationManager) {
            locationManager.removeUpdates(this.f17073h);
            this.f17073h = null;
        }
    }

    /* renamed from: b */
    private boolean m9167b(Location location) {
        return location != null && 500.0f >= location.getAccuracy();
    }

    /* renamed from: z */
    private String m9137z() {
        String m8814m = this.f17072g.m8814m();
        if (m8814m == null) {
            String uuid = UUID.randomUUID().toString();
            this.f17072g.m8811n(uuid);
            return uuid;
        }
        return m8814m;
    }

    /* renamed from: m */
    public HarvestConfiguration m9150m() {
        C6646o c6646o = this.f17072g;
        if (c6646o != null) {
            return c6646o.m8834f();
        }
        return null;
    }

    /* renamed from: n */
    public SystemInfo m9149n() {
        SystemInfo systemInfo = new SystemInfo();
        systemInfo.setMemory(C6317b.m10439j(this.f17069d));
        systemInfo.setCpuUtilizationRateUser(0);
        systemInfo.setCpuUtilizationRateSystem(0);
        systemInfo.setCpuUtilizationRateTotal(0);
        return systemInfo;
    }

    /* renamed from: o */
    public String m9148o() {
        return C6638h.m8963w().m9066U();
    }

    /* renamed from: p */
    public C6646o m9147p() {
        return this.f17072g;
    }

    /* renamed from: q */
    public String m9146q() {
        String m8842c = this.f17072g.m8842c(ConfigurationName.brsAgentMD5);
        return TextUtils.isEmpty(m8842c) ? "" : m8842c;
    }
}
