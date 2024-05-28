package com.baidu.location.p138c;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityNr;
import android.telephony.CellIdentityTdscdma;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoTdscdma;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthNr;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p140e.C2735k;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"NewApi"})
/* renamed from: com.baidu.location.c.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2689b {

    /* renamed from: a */
    public static int f5505a;

    /* renamed from: b */
    public static int f5506b;

    /* renamed from: c */
    private static C2689b f5507c;

    /* renamed from: k */
    private static String f5508k;

    /* renamed from: l */
    private static Class<?> f5509l;

    /* renamed from: m */
    private C2691a f5517m;

    /* renamed from: p */
    private boolean f5520p;

    /* renamed from: d */
    private TelephonyManager f5510d = null;

    /* renamed from: e */
    private C2688a f5511e = new C2688a();

    /* renamed from: f */
    private C2688a f5512f = null;

    /* renamed from: g */
    private List<C2688a> f5513g = null;

    /* renamed from: h */
    private C2692b f5514h = null;

    /* renamed from: i */
    private boolean f5515i = false;

    /* renamed from: j */
    private boolean f5516j = false;

    /* renamed from: n */
    private long f5518n = 0;

    /* renamed from: o */
    private Handler f5519o = new Handler();

    /* renamed from: q */
    private int f5521q = 30;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2691a extends TelephonyManager.CellInfoCallback {
        private C2691a() {
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public void onCellInfo(List<CellInfo> list) {
            if (list == null) {
                return;
            }
            C2689b.this.f5519o.post(new RunnableC2694c(this));
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public void onError(int i, Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.b$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2692b extends PhoneStateListener {
        public C2692b() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellInfoChanged(List<CellInfo> list) {
            if (list == null) {
                return;
            }
            C2689b.this.f5519o.post(new RunnableC2695d(this));
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            C2688a c2688a;
            int cdmaDbm;
            if (C2689b.this.f5511e != null) {
                if (C2689b.this.f5511e.f5497i == 'g') {
                    c2688a = C2689b.this.f5511e;
                    cdmaDbm = signalStrength.getGsmSignalStrength();
                } else if (C2689b.this.f5511e.f5497i != 'c') {
                    return;
                } else {
                    c2688a = C2689b.this.f5511e;
                    cdmaDbm = signalStrength.getCdmaDbm();
                }
                c2688a.f5496h = cdmaDbm;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.b$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2693c implements Comparator<C2707h> {
        private C2693c() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(C2707h c2707h, C2707h c2707h2) {
            return c2707h.f5612g - c2707h2.f5612g;
        }
    }

    private C2689b() {
        this.f5520p = false;
        if (Build.VERSION.SDK_INT >= this.f5521q) {
            this.f5520p = C2735k.m19053b("android.telephony.TelephonyManager$CellInfoCallback");
        }
    }

    /* renamed from: a */
    private static int m19258a(int i) {
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i;
    }

    /* renamed from: a */
    public static int m19257a(CellIdentityNr cellIdentityNr) {
        try {
            return C2735k.m19062a(cellIdentityNr, "getHwTac");
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public static int m19249a(String str) {
        if (str == null || !str.contains("mNrTac")) {
            return -1;
        }
        Matcher matcher = Pattern.compile("mNrTac=(.+?)\\}").matcher(str.replace(" ", ""));
        while (true) {
            int i = -1;
            while (matcher.find()) {
                if (matcher.groupCount() >= 1) {
                    try {
                        i = Integer.parseInt(matcher.group(1));
                    } catch (Throwable unused) {
                    }
                }
            }
            return i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ea, code lost:
        if (r0 < 0) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ed, code lost:
        if (r0 > 0) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0134, code lost:
        if (android.os.Build.VERSION.SDK_INT >= 28) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0187, code lost:
        if (android.os.Build.VERSION.SDK_INT >= 28) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0066, code lost:
        if (android.os.Build.VERSION.SDK_INT >= 28) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0068, code lost:
        r4.f5498j = r18.getCellConnectionStatus();
     */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.baidu.location.p138c.C2688a m19255a(android.telephony.CellInfo r18, com.baidu.location.p138c.C2688a r19, android.telephony.TelephonyManager r20) {
        /*
            Method dump skipped, instructions count: 811
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p138c.C2689b.m19255a(android.telephony.CellInfo, com.baidu.location.c.a, android.telephony.TelephonyManager):com.baidu.location.c.a");
    }

    /* renamed from: a */
    private C2688a m19254a(CellLocation cellLocation) {
        return m19253a(cellLocation, false);
    }

    /* renamed from: a */
    private C2688a m19253a(CellLocation cellLocation, boolean z) {
        GsmCellLocation gsmCellLocation;
        if (cellLocation == null || this.f5510d == null) {
            return null;
        }
        C2688a c2688a = new C2688a();
        c2688a.f5500l = 1;
        if (z) {
            c2688a.m19263f();
        }
        c2688a.f5495g = System.currentTimeMillis();
        try {
            String networkOperator = this.f5510d.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                int i = -1;
                if (networkOperator.length() >= 3) {
                    i = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
                    c2688a.f5491c = i < 0 ? this.f5511e.f5491c : i;
                }
                String substring = networkOperator.substring(3);
                if (substring != null) {
                    char[] charArray = substring.toCharArray();
                    int i2 = 0;
                    while (i2 < charArray.length && Character.isDigit(charArray[i2])) {
                        i2++;
                    }
                    i = Integer.valueOf(substring.substring(0, i2)).intValue();
                }
                if (i < 0) {
                    i = this.f5511e.f5492d;
                }
                c2688a.f5492d = i;
            }
            f5505a = this.f5510d.getSimState();
        } catch (Exception unused) {
            f5506b = 1;
        }
        if (cellLocation instanceof GsmCellLocation) {
            c2688a.f5489a = ((GsmCellLocation) cellLocation).getLac();
            c2688a.f5490b = gsmCellLocation.getCid();
            c2688a.f5497i = 'g';
        } else if (cellLocation instanceof CdmaCellLocation) {
            c2688a.f5497i = 'c';
            if (f5509l == null) {
                try {
                    f5509l = Class.forName("android.telephony.cdma.CdmaCellLocation");
                } catch (Exception unused2) {
                    f5509l = null;
                    return c2688a;
                }
            }
            Class<?> cls = f5509l;
            if (cls != null && cls.isInstance(cellLocation)) {
                try {
                    int systemId = ((CdmaCellLocation) cellLocation).getSystemId();
                    if (systemId < 0) {
                        systemId = this.f5511e.f5492d;
                    }
                    c2688a.f5492d = systemId;
                    c2688a.f5490b = ((CdmaCellLocation) cellLocation).getBaseStationId();
                    c2688a.f5489a = ((CdmaCellLocation) cellLocation).getNetworkId();
                    int baseStationLatitude = ((CdmaCellLocation) cellLocation).getBaseStationLatitude();
                    if (baseStationLatitude < Integer.MAX_VALUE) {
                        c2688a.f5493e = baseStationLatitude;
                    }
                    int baseStationLongitude = ((CdmaCellLocation) cellLocation).getBaseStationLongitude();
                    if (baseStationLongitude < Integer.MAX_VALUE) {
                        c2688a.f5494f = baseStationLongitude;
                    }
                } catch (Exception unused3) {
                    f5506b = 3;
                    return c2688a;
                }
            }
        }
        m19242c(c2688a);
        return c2688a;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static C2688a m19251a(C2688a c2688a, TelephonyManager telephonyManager, boolean z) {
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() < 17) {
            return null;
        }
        try {
            f5505a = telephonyManager.getSimState();
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo == null || allCellInfo.size() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            C2688a c2688a2 = null;
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo.isRegistered()) {
                    boolean z2 = c2688a2 != null;
                    C2688a m19255a = m19255a(cellInfo, c2688a, telephonyManager);
                    if (m19255a != null) {
                        if (!m19255a.m19267b()) {
                            m19255a = null;
                        } else if (z2 && c2688a2 != null) {
                            c2688a2.f5501m = m19255a.m19260i();
                            c2688a2.f5502n = m19255a.m19262g();
                        }
                        if (c2688a2 == null) {
                            c2688a2 = m19255a;
                        }
                    }
                }
            }
            f5508k = m19248a(m19244b(arrayList));
            return c2688a2;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static synchronized C2689b m19259a() {
        C2689b c2689b;
        synchronized (C2689b.class) {
            if (f5507c == null) {
                f5507c = new C2689b();
            }
            c2689b = f5507c;
        }
        return c2689b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [android.telephony.TelephonyManager] */
    /* JADX WARN: Type inference failed for: r13v1, types: [com.baidu.location.c.h] */
    /* JADX WARN: Type inference failed for: r13v21, types: [com.baidu.location.c.h] */
    /* JADX WARN: Type inference failed for: r13v23 */
    /* JADX WARN: Type inference failed for: r13v24 */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v26 */
    /* renamed from: a */
    private static C2707h m19256a(CellInfo cellInfo, TelephonyManager telephonyManager) {
        long elapsedRealtimeNanos;
        C2709j c2709j;
        long elapsedRealtimeNanos2;
        C2708i c2708i;
        long elapsedRealtimeNanos3;
        long currentTimeMillis;
        long elapsedRealtimeNanos4;
        long currentTimeMillis2;
        long elapsedRealtimeNanos5;
        long currentTimeMillis3;
        int i = Build.VERSION.SDK_INT;
        C2707h c2707h = null;
        c2707h = null;
        CellIdentityNr cellIdentityNr = null;
        c2707h = null;
        if (i < 17) {
            return null;
        }
        try {
            if (cellInfo instanceof CellInfoGsm) {
                C2707h c2707h2 = new C2707h();
                CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
                c2707h2.f5606a = 1;
                if (cellInfo.isRegistered()) {
                    c2707h2.f5609d = 1;
                }
                if (i >= 28) {
                    c2707h2.f5607b = cellIdentity.getMccString();
                    c2707h2.f5608c = cellIdentity.getMncString();
                    c2707h2.f5611f = cellInfo.getCellConnectionStatus();
                } else {
                    c2707h2.f5607b = cellIdentity.getMcc() == Integer.MAX_VALUE ? null : String.valueOf(cellIdentity.getMcc());
                    c2707h2.f5608c = cellIdentity.getMnc() != Integer.MAX_VALUE ? String.valueOf(cellIdentity.getMnc()) : null;
                }
                if (i >= 30) {
                    elapsedRealtimeNanos4 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                    currentTimeMillis2 = System.currentTimeMillis();
                    telephonyManager = c2707h2;
                } else {
                    elapsedRealtimeNanos4 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                    currentTimeMillis2 = System.currentTimeMillis();
                    telephonyManager = c2707h2;
                }
            } else if (cellInfo instanceof CellInfoCdma) {
                C2707h c2707h3 = new C2707h();
                CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
                c2707h3.f5606a = 2;
                c2707h3.f5608c = cellIdentity2.getSystemId() != Integer.MAX_VALUE ? String.valueOf(cellIdentity2.getSystemId()) : null;
                if (cellInfo.isRegistered()) {
                    c2707h3.f5609d = 1;
                }
                if (i >= 28) {
                    c2707h3.f5611f = cellInfo.getCellConnectionStatus();
                }
                try {
                    String networkOperator = telephonyManager.getNetworkOperator();
                    if (!TextUtils.isEmpty(networkOperator) && networkOperator.length() >= 3) {
                        c2707h3.f5607b = networkOperator.substring(0, 3);
                    }
                } catch (Exception unused) {
                }
                try {
                    if (i >= 30) {
                        elapsedRealtimeNanos5 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                        currentTimeMillis3 = System.currentTimeMillis();
                    } else {
                        elapsedRealtimeNanos5 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                        currentTimeMillis3 = System.currentTimeMillis();
                    }
                    c2707h3.f5610e = currentTimeMillis3 - elapsedRealtimeNanos5;
                } catch (Error unused2) {
                    c2707h3.f5610e = System.currentTimeMillis();
                }
                return c2707h3;
            } else if (!(cellInfo instanceof CellInfoWcdma)) {
                if (cellInfo instanceof CellInfoTdscdma) {
                    if (i >= 28) {
                        c2707h = new C2707h();
                        CellIdentityTdscdma cellIdentity3 = ((CellInfoTdscdma) cellInfo).getCellIdentity();
                        c2707h.f5606a = 5;
                        if (cellInfo.isRegistered()) {
                            c2707h.f5609d = 1;
                        }
                        c2707h.f5607b = cellIdentity3.getMccString();
                        c2707h.f5608c = cellIdentity3.getMncString();
                        c2707h.f5611f = cellInfo.getCellConnectionStatus();
                        try {
                            if (i >= 30) {
                                elapsedRealtimeNanos3 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                                currentTimeMillis = System.currentTimeMillis();
                            } else {
                                elapsedRealtimeNanos3 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                                currentTimeMillis = System.currentTimeMillis();
                            }
                            c2707h.f5610e = currentTimeMillis - elapsedRealtimeNanos3;
                        } catch (Error unused3) {
                            c2707h.f5610e = System.currentTimeMillis();
                        }
                    }
                } else if (cellInfo instanceof CellInfoLte) {
                    c2707h = new C2708i();
                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                    CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
                    CellSignalStrengthLte cellSignalStrength = cellInfoLte.getCellSignalStrength();
                    C2708i c2708i2 = (C2708i) c2707h;
                    c2708i2.f5606a = 3;
                    if (cellInfo.isRegistered()) {
                        c2708i2.f5609d = 1;
                    }
                    c2708i2.f5613h = cellIdentity4.getCi();
                    c2708i2.f5614i = cellIdentity4.getPci();
                    c2708i2.f5615j = cellIdentity4.getTac();
                    c2708i2.f5623r = cellSignalStrength.getTimingAdvance();
                    if (i >= 28) {
                        c2708i2.f5607b = cellIdentity4.getMccString();
                        c2708i2.f5608c = cellIdentity4.getMncString();
                        c2708i2.f5611f = cellInfo.getCellConnectionStatus();
                        c2708i2.f5617l = cellIdentity4.getBandwidth();
                    } else {
                        if (cellIdentity4.getMcc() != Integer.MAX_VALUE) {
                            c2708i2.f5607b = String.valueOf(cellIdentity4.getMcc());
                        }
                        if (cellIdentity4.getMnc() != Integer.MAX_VALUE) {
                            c2708i2.f5608c = String.valueOf(cellIdentity4.getMnc());
                        }
                    }
                    if (i >= 24) {
                        c2708i2.f5616k = cellIdentity4.getEarfcn();
                    }
                    if (i >= 29) {
                        c2708i2.f5618m = Math.abs(cellSignalStrength.getRssi());
                    }
                    if (i >= 26) {
                        c2708i2.f5619n = Math.abs(cellSignalStrength.getRsrp());
                        c2708i2.f5612g = Math.abs(cellSignalStrength.getRsrp());
                        c2708i2.f5620o = cellSignalStrength.getRsrq();
                        c2708i2.f5621p = cellSignalStrength.getRssnr();
                        c2708i2.f5622q = cellSignalStrength.getCqi();
                    }
                    try {
                        if (i >= 30) {
                            elapsedRealtimeNanos2 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                            c2708i = (C2708i) c2707h;
                        } else {
                            elapsedRealtimeNanos2 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                            c2708i = (C2708i) c2707h;
                        }
                        c2708i.f5610e = System.currentTimeMillis() - elapsedRealtimeNanos2;
                    } catch (Error unused4) {
                        c2708i2.f5610e = System.currentTimeMillis();
                    }
                } else if ((cellInfo instanceof CellInfoNr) && i >= 29) {
                    C2709j c2709j2 = new C2709j();
                    try {
                        cellIdentityNr = (CellIdentityNr) ((CellInfoNr) cellInfo).getCellIdentity();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    CellSignalStrengthNr cellSignalStrengthNr = (CellSignalStrengthNr) ((CellInfoNr) cellInfo).getCellSignalStrength();
                    if (cellIdentityNr != null) {
                        C2709j c2709j3 = c2709j2;
                        c2709j3.f5606a = 6;
                        c2709j3.f5607b = cellIdentityNr.getMccString();
                        c2709j3.f5608c = cellIdentityNr.getMncString();
                        c2709j3.f5624h = cellIdentityNr.getNci();
                        c2709j3.f5625i = cellIdentityNr.getPci();
                        c2709j3.f5626j = cellIdentityNr.getTac();
                        if (c2709j3.f5626j == Integer.MAX_VALUE) {
                            try {
                                c2709j2.f5626j = m19257a(cellIdentityNr);
                            } catch (Throwable unused5) {
                            }
                        }
                        if (c2709j3.f5626j == Integer.MAX_VALUE) {
                            try {
                                c2709j2.f5626j = m19249a(cellIdentityNr.toString());
                            } catch (Throwable unused6) {
                            }
                        }
                        c2709j3.f5627k = cellIdentityNr.getNrarfcn();
                    }
                    if (cellInfo.isRegistered()) {
                        c2709j2.f5609d = 1;
                    }
                    C2709j c2709j4 = c2709j2;
                    c2709j4.f5611f = cellInfo.getCellConnectionStatus();
                    c2709j4.f5628l = Math.abs(cellSignalStrengthNr.getSsRsrp());
                    c2709j4.f5612g = Math.abs(cellSignalStrengthNr.getSsRsrp());
                    c2709j4.f5629m = cellSignalStrengthNr.getSsRsrq();
                    c2709j4.f5630n = cellSignalStrengthNr.getSsSinr();
                    c2709j4.f5631o = Math.abs(cellSignalStrengthNr.getCsiRsrp());
                    c2709j4.f5632p = cellSignalStrengthNr.getCsiRsrq();
                    c2709j4.f5633q = cellSignalStrengthNr.getCsiSinr();
                    try {
                        if (i >= 30) {
                            elapsedRealtimeNanos = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                            c2709j = c2709j2;
                        } else {
                            elapsedRealtimeNanos = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                            c2709j = c2709j2;
                        }
                        c2709j.f5610e = System.currentTimeMillis() - elapsedRealtimeNanos;
                        return c2709j2;
                    } catch (Error unused7) {
                        c2709j4.f5610e = System.currentTimeMillis();
                        return c2709j2;
                    }
                }
                return c2707h;
            } else {
                C2707h c2707h4 = new C2707h();
                CellIdentityWcdma cellIdentity5 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                c2707h4.f5606a = 4;
                if (cellInfo.isRegistered()) {
                    c2707h4.f5609d = 1;
                }
                if (i >= 28) {
                    c2707h4.f5607b = cellIdentity5.getMccString();
                    c2707h4.f5608c = cellIdentity5.getMncString();
                    c2707h4.f5611f = cellInfo.getCellConnectionStatus();
                } else {
                    c2707h4.f5607b = cellIdentity5.getMcc() == Integer.MAX_VALUE ? null : String.valueOf(cellIdentity5.getMcc());
                    c2707h4.f5608c = cellIdentity5.getMnc() != Integer.MAX_VALUE ? String.valueOf(cellIdentity5.getMnc()) : null;
                }
                if (i >= 30) {
                    elapsedRealtimeNanos4 = SystemClock.elapsedRealtime() - cellInfo.getTimestampMillis();
                    currentTimeMillis2 = System.currentTimeMillis();
                    telephonyManager = c2707h4;
                } else {
                    elapsedRealtimeNanos4 = (SystemClock.elapsedRealtimeNanos() - cellInfo.getTimeStamp()) / 1000000;
                    currentTimeMillis2 = System.currentTimeMillis();
                    telephonyManager = c2707h4;
                }
            }
            telephonyManager.f5610e = currentTimeMillis2 - elapsedRealtimeNanos4;
            return telephonyManager;
        } catch (Error unused8) {
            telephonyManager.f5610e = System.currentTimeMillis();
            return telephonyManager;
        }
    }

    /* renamed from: a */
    private static String m19248a(List<C2707h> list) {
        if (list == null || list.size() == 0) {
        }
        return null;
    }

    /* renamed from: b */
    private static List<C2707h> m19244b(List<C2707h> list) {
        if (list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return list;
        }
        Collections.sort(list.subList(1, list.size()), new C2693c());
        return list.subList(0, list.size());
    }

    /* renamed from: c */
    private void m19242c(C2688a c2688a) {
        if (c2688a.m19267b()) {
            C2688a c2688a2 = this.f5511e;
            if (c2688a2 == null || !c2688a2.m19268a(c2688a)) {
                this.f5511e = c2688a;
                if (!c2688a.m19267b()) {
                    List<C2688a> list = this.f5513g;
                    if (list != null) {
                        list.clear();
                        return;
                    }
                    return;
                }
                int size = this.f5513g.size();
                C2688a c2688a3 = size == 0 ? null : this.f5513g.get(size - 1);
                if (c2688a3 != null && c2688a3.f5490b == this.f5511e.f5490b && c2688a3.f5489a == this.f5511e.f5489a) {
                    return;
                }
                this.f5513g.add(this.f5511e);
                if (this.f5513g.size() > 3) {
                    this.f5513g.remove(0);
                }
                m19232k();
                this.f5516j = false;
            }
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: d */
    private String m19239d(C2688a c2688a) {
        C2688a m19255a;
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = null;
        if (Integer.valueOf(Build.VERSION.SDK_INT).intValue() >= 17) {
            try {
                List<CellInfo> allCellInfo = this.f5510d.getAllCellInfo();
                if (allCellInfo != null && allCellInfo.size() > 0) {
                    sb2.append("&nc=");
                    for (CellInfo cellInfo : allCellInfo) {
                        if (!cellInfo.isRegistered() && (m19255a = m19255a(cellInfo, this.f5511e, this.f5510d)) != null) {
                            if (m19255a.f5489a != -1 && m19255a.f5490b != -1) {
                                if (c2688a != null && c2688a.f5489a == m19255a.f5489a) {
                                    sb = new StringBuilder();
                                    sb.append("|");
                                    sb.append(m19255a.f5490b);
                                    sb.append("|");
                                    sb.append(m19255a.f5496h);
                                    sb.append(";");
                                    sb2.append(sb.toString());
                                }
                                sb = new StringBuilder();
                                sb.append(m19255a.f5489a);
                                sb.append("|");
                                sb.append(m19255a.f5490b);
                                sb.append("|");
                                sb.append(m19255a.f5496h);
                                sb.append(";");
                                sb2.append(sb.toString());
                            }
                            if (Build.VERSION.SDK_INT > 28 && m19255a.f5499k == 6 && m19255a.f5503o != null && m19255a.m19267b()) {
                                if (sb3 == null) {
                                    StringBuilder sb4 = new StringBuilder();
                                    try {
                                        sb4.append("&ncnr=");
                                        sb3 = sb4;
                                    } catch (Throwable unused) {
                                        sb3 = sb4;
                                    }
                                }
                                sb3.append(m19255a.m19262g());
                                sb3.append("_");
                                sb3.append(m19255a.f5503o);
                                sb3.append(";");
                            }
                        }
                    }
                }
            } catch (Throwable unused2) {
            }
        }
        if (sb3 != null) {
            return sb2.toString() + sb3.toString();
        }
        return sb2.toString();
    }

    /* renamed from: i */
    public static String m19234i() {
        String str = f5508k;
        if (str == null || str.length() == 0) {
            return null;
        }
        return f5508k.replace("\n", "");
    }

    /* renamed from: j */
    private void m19233j() {
        String m19043f = C2735k.m19043f();
        if (m19043f == null) {
            return;
        }
        File file = new File(m19043f + File.separator + "lcvif2.dat");
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                long j = 0;
                randomAccessFile.seek(0L);
                if (System.currentTimeMillis() - randomAccessFile.readLong() > 60000) {
                    randomAccessFile.close();
                    file.delete();
                    return;
                }
                randomAccessFile.readInt();
                int i = 0;
                while (i < 3) {
                    long readLong = randomAccessFile.readLong();
                    int readInt = randomAccessFile.readInt();
                    int readInt2 = randomAccessFile.readInt();
                    int readInt3 = randomAccessFile.readInt();
                    long readLong2 = randomAccessFile.readLong();
                    int readInt4 = randomAccessFile.readInt();
                    char c = readInt4 == 1 ? 'g' : (char) 0;
                    if (readInt4 == 2) {
                        c = 'c';
                    }
                    if (readLong != j) {
                        C2688a c2688a = new C2688a(readInt3, readLong2, readInt, readInt2, 0, c, -1);
                        c2688a.f5495g = readLong;
                        if (c2688a.m19267b()) {
                            this.f5516j = true;
                            this.f5513g.add(c2688a);
                        }
                    }
                    i++;
                    j = 0;
                }
                randomAccessFile.close();
            } catch (Exception unused) {
                file.delete();
            }
        }
    }

    /* renamed from: k */
    private void m19232k() {
        if (this.f5513g == null && this.f5512f == null) {
            return;
        }
        if (this.f5513g == null && this.f5512f != null) {
            this.f5513g = new LinkedList();
            this.f5513g.add(this.f5512f);
        }
        String m19043f = C2735k.m19043f();
        if (m19043f == null || this.f5513g == null) {
            return;
        }
        File file = new File(m19043f + File.separator + "lcvif2.dat");
        int size = this.f5513g.size();
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeLong(this.f5513g.get(size - 1).f5495g);
            randomAccessFile.writeInt(size);
            for (int i = 0; i < 3 - size; i++) {
                randomAccessFile.writeLong(0L);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeInt(-1);
                randomAccessFile.writeLong(-1L);
                randomAccessFile.writeInt(2);
            }
            for (int i2 = 0; i2 < size; i2++) {
                randomAccessFile.writeLong(this.f5513g.get(i2).f5495g);
                randomAccessFile.writeInt(this.f5513g.get(i2).f5491c);
                randomAccessFile.writeInt(this.f5513g.get(i2).f5492d);
                randomAccessFile.writeInt(this.f5513g.get(i2).f5489a);
                randomAccessFile.writeLong(this.f5513g.get(i2).f5490b);
                if (this.f5513g.get(i2).f5497i == 'g') {
                    randomAccessFile.writeInt(1);
                } else if (this.f5513g.get(i2).f5497i == 'c') {
                    randomAccessFile.writeInt(2);
                } else {
                    randomAccessFile.writeInt(3);
                }
            }
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m19231l() {
        CellLocation cellLocation;
        C2688a m19251a = m19251a(this.f5511e, this.f5510d, false);
        if (m19251a != null) {
            m19242c(m19251a);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            if (m19251a == null || !m19251a.m19267b()) {
                try {
                    cellLocation = this.f5510d.getCellLocation();
                } catch (Throwable unused) {
                    cellLocation = null;
                }
                if (cellLocation != null) {
                    m19254a(cellLocation);
                }
            }
        }
    }

    /* renamed from: a */
    public String m19252a(C2688a c2688a) {
        String str;
        try {
            str = m19239d(c2688a);
            int intValue = Integer.valueOf(Build.VERSION.SDK_INT).intValue();
            if (str != null && !str.equals("")) {
                if (!str.equals("&nc=")) {
                    return str;
                }
            }
            if (intValue >= 17) {
                return str;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            str = "";
        }
        if (str == null || !str.equals("&nc=")) {
            return str;
        }
        return null;
    }

    /* renamed from: b */
    public String m19246b(C2688a c2688a) {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(c2688a.f5497i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", Integer.valueOf(c2688a.f5491c), Integer.valueOf(c2688a.f5492d), Integer.valueOf(c2688a.f5489a), Long.valueOf(c2688a.f5490b), Integer.valueOf(c2688a.f5496h)));
        if (c2688a.f5493e < Integer.MAX_VALUE && c2688a.f5494f < Integer.MAX_VALUE) {
            stringBuffer.append(String.format(Locale.CHINA, "&cdmall=%.6f|%.6f", Double.valueOf(c2688a.f5494f / 14400.0d), Double.valueOf(c2688a.f5493e / 14400.0d)));
        }
        stringBuffer.append("&cl_t=");
        stringBuffer.append(c2688a.f5495g);
        stringBuffer.append("&cl_api=");
        stringBuffer.append(c2688a.f5500l);
        stringBuffer.append("&clp=");
        stringBuffer.append(c2688a.f5499k);
        if (c2688a.f5503o != null) {
            stringBuffer.append("&clnrs=");
            stringBuffer.append(c2688a.f5503o);
        }
        if (Build.VERSION.SDK_INT >= 28 && c2688a.f5498j != Integer.MAX_VALUE) {
            stringBuffer.append("&cl_cs=");
            stringBuffer.append(c2688a.f5498j);
        }
        try {
            if (this.f5513g != null && this.f5513g.size() > 0) {
                int size = this.f5513g.size();
                stringBuffer.append("&clt=");
                for (int i = 0; i < size; i++) {
                    C2688a c2688a2 = this.f5513g.get(i);
                    if (c2688a2 != null) {
                        if (c2688a2.f5491c != c2688a.f5491c) {
                            stringBuffer.append(c2688a2.f5491c);
                        }
                        stringBuffer.append("|");
                        if (c2688a2.f5492d != c2688a.f5492d) {
                            stringBuffer.append(c2688a2.f5492d);
                        }
                        stringBuffer.append("|");
                        if (c2688a2.f5489a != c2688a.f5489a) {
                            stringBuffer.append(c2688a2.f5489a);
                        }
                        stringBuffer.append("|");
                        if (c2688a2.f5490b != c2688a.f5490b) {
                            stringBuffer.append(c2688a2.f5490b);
                        }
                        stringBuffer.append("|");
                        stringBuffer.append((System.currentTimeMillis() - c2688a2.f5495g) / 1000);
                        stringBuffer.append(";");
                    }
                }
            }
        } catch (Exception unused) {
        }
        if (f5505a > 100) {
            f5505a = 0;
        }
        stringBuffer.append("&cs=" + (f5505a + (f5506b << 8)));
        if (c2688a.f5501m != null) {
            stringBuffer.append(c2688a.f5501m);
        }
        stringBuffer.append("&cl_list=");
        stringBuffer.append(m19234i());
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public synchronized void m19247b() {
        if (this.f5515i) {
            return;
        }
        if (ServiceC2737f.isServing) {
            this.f5510d = (TelephonyManager) ServiceC2737f.getServiceContext().getSystemService("phone");
            this.f5513g = new LinkedList();
            this.f5514h = new C2692b();
            m19233j();
            if (this.f5510d != null && this.f5514h != null) {
                if (Build.VERSION.SDK_INT < this.f5521q || !this.f5520p) {
                    try {
                        this.f5510d.listen(this.f5514h, 1280);
                    } catch (Exception unused) {
                    }
                }
                this.f5515i = true;
            }
        }
    }

    /* renamed from: c */
    public synchronized void m19243c() {
        if (this.f5515i) {
            if (this.f5514h != null && this.f5510d != null) {
                this.f5510d.listen(this.f5514h, 0);
            }
            this.f5514h = null;
            this.f5510d = null;
            this.f5513g.clear();
            this.f5513g = null;
            m19232k();
            this.f5515i = false;
        }
    }

    /* renamed from: d */
    public boolean m19240d() {
        return this.f5516j;
    }

    /* renamed from: e */
    public int m19238e() {
        TelephonyManager telephonyManager = this.f5510d;
        if (telephonyManager == null) {
            return 0;
        }
        try {
            return telephonyManager.getNetworkType();
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: f */
    public C2688a m19237f() {
        C2688a c2688a = this.f5511e;
        if ((c2688a == null || !c2688a.m19269a() || !this.f5511e.m19267b()) && this.f5510d != null) {
            try {
                m19231l();
                if (Build.VERSION.SDK_INT >= 29 && this.f5520p && System.currentTimeMillis() - this.f5518n > 30000) {
                    this.f5518n = System.currentTimeMillis();
                    if (this.f5517m == null) {
                        this.f5517m = new C2691a();
                    }
                    this.f5510d.requestCellInfoUpdate(ServiceC2737f.getServiceContext().getMainExecutor(), this.f5517m);
                }
            } catch (Exception unused) {
            }
        }
        C2688a c2688a2 = this.f5511e;
        if (c2688a2 != null && c2688a2.m19264e()) {
            this.f5512f = null;
            this.f5512f = new C2688a(this.f5511e);
        }
        C2688a c2688a3 = this.f5511e;
        if (c2688a3 != null && c2688a3.m19265d() && this.f5512f != null && this.f5511e.f5497i == 'g') {
            this.f5511e.f5492d = this.f5512f.f5492d;
            this.f5511e.f5491c = this.f5512f.f5491c;
        }
        return this.f5511e;
    }

    /* renamed from: g */
    public String m19236g() {
        int i = -1;
        try {
            if (this.f5510d != null) {
                i = this.f5510d.getSimState();
            }
        } catch (Exception unused) {
        }
        return "&sim=" + i;
    }

    /* renamed from: h */
    public int m19235h() {
        return 0;
    }
}
