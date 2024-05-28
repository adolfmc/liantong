package com.baidu.location.p137b;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.p136a.C2621a;
import com.baidu.location.p138c.C2688a;
import com.baidu.location.p140e.AbstractC2729f;
import com.baidu.location.p140e.C2725d;
import com.baidu.location.p140e.C2735k;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import org.json.JSONObject;
import szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2634e {

    /* renamed from: q */
    private static char[] f5189q = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.".toCharArray();

    /* renamed from: a */
    String f5190a;

    /* renamed from: b */
    String f5191b;

    /* renamed from: d */
    private Context f5193d;

    /* renamed from: e */
    private TelephonyManager f5194e;

    /* renamed from: g */
    private WifiManager f5196g;

    /* renamed from: i */
    private String f5198i;

    /* renamed from: j */
    private String f5199j;

    /* renamed from: k */
    private LocationClientOption f5200k;

    /* renamed from: l */
    private InterfaceC2636b f5201l;

    /* renamed from: n */
    private String f5203n;

    /* renamed from: o */
    private String f5204o;

    /* renamed from: p */
    private boolean f5205p;

    /* renamed from: t */
    private C2641f f5208t;

    /* renamed from: v */
    private boolean f5210v;

    /* renamed from: f */
    private C2688a f5195f = new C2688a();

    /* renamed from: h */
    private C2640e f5197h = null;

    /* renamed from: m */
    private String f5202m = null;

    /* renamed from: c */
    C2637c f5192c = new C2637c();

    /* renamed from: r */
    private boolean f5206r = false;

    /* renamed from: s */
    private final Object f5207s = new Object();

    /* renamed from: u */
    private boolean f5209u = true;

    /* renamed from: w */
    private long f5211w = 0;

    /* renamed from: x */
    private boolean f5212x = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @TargetApi(29)
    /* renamed from: com.baidu.location.b.e$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2635a extends TelephonyManager.CellInfoCallback {
        private C2635a() {
        }

        /* synthetic */ C2635a(C2634e c2634e, C2642f c2642f) {
            this();
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public void onCellInfo(List<CellInfo> list) {
            if (list == null) {
                return;
            }
            C2634e c2634e = C2634e.this;
            c2634e.f5209u = !c2634e.f5209u;
            if (C2634e.this.f5209u || C2634e.this.f5200k.priority == 4) {
                synchronized (C2634e.this.f5207s) {
                    C2634e.this.f5207s.notifyAll();
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.e$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2636b {
        void onReceiveLocation(BDLocation bDLocation);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.e$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2637c extends AbstractC2729f {

        /* renamed from: b */
        LocationManager f5215b;

        /* renamed from: c */
        C2638a f5216c;

        /* renamed from: a */
        String f5214a = null;

        /* renamed from: d */
        boolean f5217d = false;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.baidu.location.b.e$c$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public class C2638a implements LocationListener {
            private C2638a() {
            }

            /* synthetic */ C2638a(C2637c c2637c, C2642f c2642f) {
                this();
            }

            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                C2637c.this.m19478c();
                C2637c.this.f5217d = true;
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

        C2637c() {
            this.f5727j = new HashMap();
        }

        /* renamed from: b */
        private void m19479b() {
            try {
                this.f5215b = (LocationManager) C2634e.this.f5193d.getSystemService("location");
                this.f5216c = new C2638a(this, null);
                if (this.f5215b == null || this.f5216c == null) {
                    return;
                }
                try {
                    this.f5215b.requestLocationUpdates("network", 1000L, 0.0f, this.f5216c, Looper.getMainLooper());
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            } catch (Throwable unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public void m19478c() {
            LocationManager locationManager;
            C2638a c2638a = this.f5216c;
            if (c2638a == null || (locationManager = this.f5215b) == null) {
                return;
            }
            try {
                locationManager.removeUpdates(c2638a);
            } catch (Exception unused) {
            }
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19077a() {
            if (C2634e.this.f5203n != null && C2634e.this.f5204o != null) {
                this.f5214a += String.format(Locale.CHINA, "&ki=%s&sn=%s", C2634e.this.f5203n, C2634e.this.f5204o);
            }
            this.f5214a += "&enc=2";
            String encodeTp4 = Jni.encodeTp4(this.f5214a);
            this.f5214a = null;
            this.f5727j.put(BaseLocationBox.TYPE, encodeTp4);
            this.f5727j.put("trtm", String.format(Locale.CHINA, "%d", Long.valueOf(System.currentTimeMillis())));
        }

        /* renamed from: a */
        public void m19480a(String str) {
            this.f5214a = str;
            m19073b(C2725d.f5705c);
            if (C2634e.this.f5206r) {
                m19479b();
                Timer timer = new Timer();
                timer.schedule(new C2643g(this, timer), 10000L);
                SharedPreferences.Editor edit = C2634e.this.f5193d.getSharedPreferences("cuidRelate", 0).edit();
                edit.putLong("reqtime", System.currentTimeMillis());
                edit.apply();
            }
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19074a(boolean z) {
            BDLocation bDLocation;
            if (!z || this.f5726i == null) {
                C2634e.this.m19498b(63);
            } else {
                try {
                    String str = this.f5726i;
                    if (str.contains("\"enc\"")) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.has("enc")) {
                                str = C2655n.m19419a().m19416b(jSONObject.getString("enc"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        bDLocation = new BDLocation(str);
                        C2634e.this.m19501a(str);
                    } catch (Exception unused) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(63);
                    }
                    if (bDLocation.getLocType() == 161) {
                        bDLocation.setCoorType(C2634e.this.f5200k.coorType);
                        bDLocation.setLocationID(Jni.en1(C2634e.this.f5190a + ";" + C2634e.this.f5191b + ";" + bDLocation.getTime()));
                        bDLocation.setRoadLocString(0.0f, 0.0f, null);
                        C2634e.this.f5212x = true;
                        C2634e.this.f5201l.onReceiveLocation(bDLocation);
                    } else {
                        C2634e.this.m19498b(bDLocation.getLocType());
                    }
                } catch (Exception e2) {
                    C2634e.this.m19498b(63);
                    e2.printStackTrace();
                }
            }
            if (this.f5727j != null) {
                this.f5727j.clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.e$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2639d {

        /* renamed from: a */
        public String f5220a;

        /* renamed from: b */
        public int f5221b;

        C2639d(String str, int i) {
            this.f5220a = str;
            this.f5221b = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.e$e */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2640e {

        /* renamed from: a */
        public List<ScanResult> f5223a;

        /* renamed from: c */
        private long f5225c;

        /* renamed from: b */
        public String f5224b = null;

        /* renamed from: d */
        private String f5226d = null;

        /* renamed from: e */
        private int f5227e = 16;

        public C2640e(List<ScanResult> list) {
            this.f5223a = null;
            this.f5225c = 0L;
            this.f5223a = list;
            this.f5225c = System.currentTimeMillis();
            try {
                m19475b();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
            jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
            */
        /* renamed from: b */
        private void m19475b() {
            /*
                r7 = this;
                int r0 = r7.m19477a()
                r1 = 1
                if (r0 >= r1) goto L8
                return
            L8:
                java.util.List<android.net.wifi.ScanResult> r0 = r7.f5223a
                int r0 = r0.size()
                int r0 = r0 - r1
                r2 = r1
            L10:
                if (r0 < r1) goto L5e
                if (r2 == 0) goto L5e
                r2 = 0
                r3 = r2
            L16:
                if (r2 >= r0) goto L5a
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f5223a
                java.lang.Object r4 = r4.get(r2)
                if (r4 == 0) goto L57
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f5223a
                int r5 = r2 + 1
                java.lang.Object r4 = r4.get(r5)
                if (r4 == 0) goto L57
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f5223a
                java.lang.Object r4 = r4.get(r2)
                android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
                int r4 = r4.level
                java.util.List<android.net.wifi.ScanResult> r6 = r7.f5223a
                java.lang.Object r6 = r6.get(r5)
                android.net.wifi.ScanResult r6 = (android.net.wifi.ScanResult) r6
                int r6 = r6.level
                if (r4 >= r6) goto L57
                java.util.List<android.net.wifi.ScanResult> r3 = r7.f5223a
                java.lang.Object r3 = r3.get(r5)
                android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f5223a
                java.lang.Object r6 = r4.get(r2)
                r4.set(r5, r6)
                java.util.List<android.net.wifi.ScanResult> r4 = r7.f5223a
                r4.set(r2, r3)
                r3 = r1
            L57:
                int r2 = r2 + 1
                goto L16
            L5a:
                int r0 = r0 + (-1)
                r2 = r3
                goto L10
            L5e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2634e.C2640e.m19475b():void");
        }

        /* renamed from: a */
        public int m19477a() {
            List<ScanResult> list = this.f5223a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00ef  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:95:0x00e9 A[SYNTHETIC] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String m19476a(int r24, java.lang.String r25, boolean r26, int r27) {
            /*
                Method dump skipped, instructions count: 469
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2634e.C2640e.m19476a(int, java.lang.String, boolean, int):java.lang.String");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.e$f */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2641f extends BroadcastReceiver {
        private C2641f() {
        }

        /* synthetic */ C2641f(C2634e c2634e, C2642f c2642f) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                return;
            }
            String action = intent.getAction();
            C2634e c2634e = C2634e.this;
            c2634e.f5209u = !c2634e.f5209u;
            if (!(action.equals("android.net.wifi.SCAN_RESULTS") && C2634e.this.f5209u) && Build.VERSION.SDK_INT >= 29 && C2634e.this.f5210v) {
                return;
            }
            synchronized (C2634e.this.f5207s) {
                C2634e.this.f5207s.notifyAll();
            }
        }
    }

    public C2634e(Context context, LocationClientOption locationClientOption, InterfaceC2636b interfaceC2636b, String str) {
        StringBuilder sb;
        this.f5193d = null;
        this.f5194e = null;
        this.f5196g = null;
        this.f5198i = null;
        this.f5199j = null;
        this.f5203n = null;
        this.f5204o = null;
        this.f5190a = null;
        this.f5191b = null;
        this.f5205p = false;
        this.f5208t = null;
        this.f5210v = false;
        this.f5193d = context.getApplicationContext();
        try {
            C2735k.f5806aw = this.f5193d.getPackageName();
        } catch (Exception unused) {
        }
        this.f5205p = true;
        if (this.f5205p) {
            this.f5200k = new LocationClientOption(locationClientOption);
            this.f5201l = interfaceC2636b;
            this.f5190a = this.f5193d.getPackageName();
            this.f5191b = null;
            try {
                this.f5194e = (TelephonyManager) this.f5193d.getSystemService("phone");
                this.f5196g = (WifiManager) this.f5193d.getApplicationContext().getSystemService("wifi");
            } catch (Exception unused2) {
            }
            if (this.f5200k.firstLocType == LocationClientOption.FirstLocType.ACCURACY_IN_FIRST_LOC) {
                if (this.f5200k.priority != 4) {
                    this.f5208t = new C2641f(this, null);
                    try {
                        this.f5193d.registerReceiver(this.f5208t, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                    } catch (Exception unused3) {
                    }
                }
                if (Build.VERSION.SDK_INT >= 30) {
                    this.f5210v = C2735k.m19053b("android.telephony.TelephonyManager$CellInfoCallback");
                }
            }
            this.f5199j = "&" + this.f5190a + "&" + ((String) null);
            try {
                this.f5191b = LBSAuthManager.getInstance(this.f5193d).getCUID();
            } catch (Throwable unused4) {
                this.f5191b = null;
                this.f5194e = null;
                this.f5196g = null;
            }
            if (this.f5191b != null) {
                C2735k.f5822n = "" + this.f5191b;
                sb = new StringBuilder();
                sb.append("&prod=");
                sb.append(this.f5200k.prodName);
                sb.append(":");
                sb.append(this.f5190a);
                sb.append("|&cu=");
                sb.append(this.f5191b);
            } else {
                sb = new StringBuilder();
                sb.append("&prod=");
                sb.append(this.f5200k.prodName);
                sb.append(":");
                sb.append(this.f5190a);
                sb.append("|&im=");
                sb.append((String) null);
            }
            sb.append("&coor=");
            sb.append(locationClientOption.getCoorType());
            this.f5198i = sb.toString();
            StringBuffer stringBuffer = new StringBuffer(256);
            stringBuffer.append("&fw=");
            stringBuffer.append("9.333");
            stringBuffer.append("&sdk=");
            stringBuffer.append("9.333");
            stringBuffer.append("&lt=1");
            stringBuffer.append("&mb=");
            stringBuffer.append(Build.MODEL);
            stringBuffer.append("&resid=");
            stringBuffer.append("12");
            locationClientOption.getAddrType();
            if (locationClientOption.getAddrType() != null && locationClientOption.getAddrType().equals("all")) {
                this.f5198i += "&addr=allj2";
                if (locationClientOption.isNeedNewVersionRgc) {
                    stringBuffer.append("&adtp=n2");
                }
            }
            if (locationClientOption.isNeedAptag || locationClientOption.isNeedAptagd) {
                this.f5198i += "&sema=";
                if (locationClientOption.isNeedAptag) {
                    this.f5198i += "aptag|";
                }
                if (locationClientOption.isNeedAptagd) {
                    this.f5198i += "aptagd2|";
                }
                this.f5203n = C2621a.m19572b(this.f5193d);
                this.f5204o = C2621a.m19571c(this.f5193d);
            }
            stringBuffer.append("&first=1");
            stringBuffer.append("&os=A");
            stringBuffer.append(Build.VERSION.SDK);
            this.f5198i += stringBuffer.toString();
        }
    }

    /* renamed from: a */
    public static C2688a m19507a(CellLocation cellLocation, TelephonyManager telephonyManager, C2688a c2688a) {
        GsmCellLocation gsmCellLocation;
        if (cellLocation == null || telephonyManager == null) {
            return null;
        }
        C2688a c2688a2 = new C2688a();
        c2688a2.f5500l = 1;
        String networkOperator = telephonyManager.getNetworkOperator();
        if (networkOperator != null && networkOperator.length() > 0) {
            try {
                if (networkOperator.length() >= 3) {
                    int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                    if (intValue < 0) {
                        intValue = c2688a.f5491c;
                    }
                    c2688a2.f5491c = intValue;
                }
                String substring = networkOperator.substring(3);
                if (substring != null) {
                    char[] charArray = substring.toCharArray();
                    int i = 0;
                    while (i < charArray.length && Character.isDigit(charArray[i])) {
                        i++;
                    }
                    int intValue2 = Integer.valueOf(substring.substring(0, i)).intValue();
                    if (intValue2 < 0) {
                        intValue2 = c2688a.f5492d;
                    }
                    c2688a2.f5492d = intValue2;
                }
            } catch (Exception unused) {
            }
        }
        if (cellLocation instanceof GsmCellLocation) {
            c2688a2.f5489a = ((GsmCellLocation) cellLocation).getLac();
            c2688a2.f5490b = gsmCellLocation.getCid();
            c2688a2.f5497i = 'g';
        } else if (cellLocation instanceof CdmaCellLocation) {
            c2688a2.f5497i = 'c';
            try {
                Class<?> cls = Class.forName("android.telephony.cdma.CdmaCellLocation");
                if (cls != null && cls.isInstance(cellLocation)) {
                    try {
                        int systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                        if (systemId < 0) {
                            systemId = -1;
                        }
                        c2688a2.f5492d = systemId;
                        c2688a2.f5490b = ((CdmaCellLocation) cellLocation).getBaseStationId();
                        c2688a2.f5489a = ((CdmaCellLocation) cellLocation).getNetworkId();
                        int baseStationLatitude = ((CdmaCellLocation) cellLocation).getBaseStationLatitude();
                        if (baseStationLatitude < Integer.MAX_VALUE) {
                            c2688a2.f5493e = baseStationLatitude;
                        }
                        int baseStationLongitude = ((CdmaCellLocation) cellLocation).getBaseStationLongitude();
                        if (baseStationLongitude < Integer.MAX_VALUE) {
                            c2688a2.f5494f = baseStationLongitude;
                        }
                    } catch (Exception unused2) {
                    }
                }
            } catch (Exception unused3) {
                return null;
            }
        }
        if (c2688a2.m19267b()) {
            return c2688a2;
        }
        return null;
    }

    /* renamed from: a */
    private Object m19502a(Object obj, String str) throws Exception {
        return obj.getClass().getField(str).get(obj);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:1|(2:2|3)|(15:5|(1:7)|8|9|(1:58)(1:13)|15|16|(1:22)|24|25|(5:29|30|31|(1:35)|(2:(1:(1:44)(1:45))|(1:47)(4:48|(1:50)|51|52))(2:39|40))|55|(0)|(0)|(0)(0))|59|(2:61|8)|9|(1:11)|58|15|16|(3:18|20|22)|24|25|(8:27|29|30|31|(2:33|35)|(0)|(0)|(0)(0))|55|(0)|(0)|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(19:1|2|3|(15:5|(1:7)|8|9|(1:58)(1:13)|15|16|(1:22)|24|25|(5:29|30|31|(1:35)|(2:(1:(1:44)(1:45))|(1:47)(4:48|(1:50)|51|52))(2:39|40))|55|(0)|(0)|(0)(0))|59|(2:61|8)|9|(1:11)|58|15|16|(3:18|20|22)|24|25|(8:27|29|30|31|(2:33|35)|(0)|(0)|(0)(0))|55|(0)|(0)|(0)(0)) */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ab A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c8  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m19509a(int r7) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2634e.m19509a(int):java.lang.String");
    }

    /* renamed from: a */
    private String m19500a(List<WifiConfiguration> list) {
        ArrayList<C2639d> arrayList;
        int i;
        int i2 = 0;
        if (list == null || list.size() <= 0) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (WifiConfiguration wifiConfiguration : list) {
                String str = wifiConfiguration.SSID;
                try {
                    i = ((Integer) m19502a(wifiConfiguration, "numAssociation")).intValue();
                } catch (Throwable unused) {
                    i = 0;
                }
                if (i > 0 && !TextUtils.isEmpty(str)) {
                    arrayList.add(new C2639d(str, i));
                }
            }
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList, new C2642f(this));
        }
        StringBuffer stringBuffer = new StringBuffer(200);
        for (C2639d c2639d : arrayList) {
            stringBuffer.append(c2639d.f5220a);
            stringBuffer.append(",");
            stringBuffer.append(c2639d.f5221b);
            stringBuffer.append("|");
            i2++;
            if (i2 == 4) {
                break;
            }
        }
        if (arrayList.size() >= 5) {
            stringBuffer.append(((C2639d) arrayList.get(4)).f5220a);
            stringBuffer.append(",");
            stringBuffer.append(((C2639d) arrayList.get(4)).f5221b);
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19501a(String str) {
        String[] split;
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("content");
            String string = jSONObject.has("ideocfre") ? jSONObject.getString("ideocfre") : null;
            if (TextUtils.isEmpty(string) || !string.contains("|") || (split = string.split("\\|")) == null || split.length < 2) {
                return;
            }
            int parseInt = Integer.parseInt(split[0]);
            long parseLong = Long.parseLong(split[1]);
            SharedPreferences.Editor edit = this.f5193d.getSharedPreferences("cuidRelate", 0).edit();
            edit.putInt("cuidoc", parseInt);
            edit.putLong("cuidfreq", parseLong);
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m19508a(WifiManager wifiManager) {
        try {
            if (!wifiManager.isWifiEnabled()) {
                if (Build.VERSION.SDK_INT <= 17) {
                    return false;
                }
                if (!wifiManager.isScanAlwaysAvailable()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m19497b(WifiManager wifiManager) {
        if (wifiManager == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo == null) {
                return null;
            }
            String bssid = connectionInfo.getBSSID();
            String replace = bssid != null ? bssid.replace(":", "") : null;
            if (replace == null || replace.length() == 12) {
                return new String(replace);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19498b(int i) {
        if (this.f5200k.isOnceLocation()) {
            BDLocation bDLocation = new BDLocation();
            bDLocation.setLocType(i);
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis()));
            bDLocation.setLocationID(Jni.en1(this.f5190a + ";" + this.f5191b + ";" + format));
            InterfaceC2636b interfaceC2636b = this.f5201l;
            if (interfaceC2636b != null) {
                interfaceC2636b.onReceiveLocation(bDLocation);
            }
        }
    }

    /* renamed from: f */
    private boolean m19488f() {
        if (C2623a.m19570a().f5127d == 0) {
            return false;
        }
        SharedPreferences sharedPreferences = this.f5193d.getApplicationContext().getSharedPreferences("cuidRelate", 0);
        if (!sharedPreferences.contains("isInstalled")) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (!C2735k.m19054b(this.f5193d, "com.baidu.map.location")) {
                edit.putInt("isInstalled", 0);
                return false;
            }
            edit.putInt("isInstalled", 1);
            edit.apply();
        } else if (sharedPreferences.getInt("isInstalled", -1) == 0) {
            return false;
        }
        return sharedPreferences.getInt("cuidoc", 1) != 0 && (System.currentTimeMillis() - sharedPreferences.getLong("reqtime", 0L)) / 1000 >= sharedPreferences.getLong("cuidfreq", 60L) && C2735k.m19055b(this.f5193d) >= 2 && m19508a(this.f5196g) && this.f5197h.m19477a() > 3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x004e, code lost:
        if (android.text.TextUtils.isEmpty(r2) == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0050, code lost:
        r2 = com.baidu.location.p140e.C2735k.m19057a(r2.getBytes(), false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0059, code lost:
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x002f, code lost:
        if (android.text.TextUtils.isEmpty(r2) == false) goto L21;
     */
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m19486g() {
        /*
            r8 = this;
            boolean r0 = r8.m19488f()
            r1 = 0
            if (r0 == 0) goto Lb4
            r0 = 1
            r8.f5206r = r0
            com.baidu.location.b.e$e r2 = r8.f5197h
            int r2 = r2.m19477a()
            r3 = 10
            r4 = 0
            if (r2 < r3) goto L32
            com.baidu.location.b.e$e r2 = r8.f5197h
            r3 = 9
            android.net.wifi.WifiManager r5 = r8.f5196g
            java.lang.String r5 = m19497b(r5)
            boolean r6 = r8.f5206r
            com.baidu.location.b.a r7 = com.baidu.location.p137b.C2623a.m19570a()
            int r7 = r7.f5125b
            java.lang.String r2 = r2.m19476a(r3, r5, r6, r7)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L59
            goto L50
        L32:
            com.baidu.location.b.e$e r2 = r8.f5197h
            int r3 = r2.m19477a()
            android.net.wifi.WifiManager r5 = r8.f5196g
            java.lang.String r5 = m19497b(r5)
            boolean r6 = r8.f5206r
            com.baidu.location.b.a r7 = com.baidu.location.p137b.C2623a.m19570a()
            int r7 = r7.f5125b
            java.lang.String r2 = r2.m19476a(r3, r5, r6, r7)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L59
        L50:
            byte[] r2 = r2.getBytes()
            java.lang.String r2 = com.baidu.location.p140e.C2735k.m19057a(r2, r1)
            goto L5a
        L59:
            r2 = r4
        L5a:
            java.util.List r3 = r8.m19484h()
            java.lang.String r3 = r8.m19500a(r3)
            boolean r5 = android.text.TextUtils.isEmpty(r3)
            if (r5 != 0) goto L70
            byte[] r3 = r3.getBytes()
            java.lang.String r4 = com.baidu.location.p140e.C2735k.m19057a(r3, r1)
        L70:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L91
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = r8.f5202m
            r1.append(r3)
            java.lang.String r3 = "&swf5="
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r8.f5202m = r1
            r8.f5206r = r0
            goto L93
        L91:
            r8.f5206r = r1
        L93:
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 != 0) goto Lb6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r8.f5202m
            r1.append(r2)
            java.lang.String r2 = "&hwf5="
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r8.f5202m = r1
            r8.f5206r = r0
            goto Lb6
        Lb4:
            r8.f5206r = r1
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2634e.m19486g():void");
    }

    /* renamed from: h */
    private List<WifiConfiguration> m19484h() {
        try {
            if (this.f5196g != null) {
                return this.f5196g.getConfiguredNetworks();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public void m19510a() {
        m19499b();
    }

    /* renamed from: b */
    public String m19499b() {
        try {
            return m19509a(15);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: c */
    public void m19494c() {
        if (this.f5202m == null) {
            int i = 62;
            int m19042f = C2735k.m19042f(this.f5193d);
            if (m19042f == -1) {
                i = 69;
            } else if (m19042f == -2) {
                i = 70;
            } else if (m19042f == 0) {
                i = 71;
            }
            m19498b(i);
        } else if (this.f5205p) {
            if (this.f5193d != null) {
                C2631c.m19525a().m19523a(this.f5193d);
                this.f5202m += C2631c.m19525a().m19518b();
            }
            this.f5192c.m19480a(this.f5202m);
        }
    }

    /* renamed from: d */
    public void m19492d() {
        if ((this.f5196g.isWifiEnabled() || this.f5196g.isScanAlwaysAvailable()) && this.f5200k.priority != 4) {
            this.f5196g.startScan();
        }
        if (C2735k.m19067a(this.f5193d, "android.permission.ACCESS_FINE_LOCATION") == 1 && Build.VERSION.SDK_INT >= 29 && this.f5210v) {
            this.f5194e.requestCellInfoUpdate(this.f5193d.getMainExecutor(), new C2635a(this, null));
        }
        synchronized (this.f5207s) {
            try {
                this.f5207s.wait(3000L);
            } catch (InterruptedException unused) {
            }
        }
    }

    /* renamed from: e */
    public void m19490e() {
        try {
            if (this.f5208t != null) {
                this.f5193d.unregisterReceiver(this.f5208t);
            }
        } catch (Exception unused) {
        }
    }
}
