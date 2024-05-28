package com.baidu.location.p138c;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p137b.C2628b;
import com.baidu.location.p140e.C2735k;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.c.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2711l {

    /* renamed from: a */
    public static long f5639a;

    /* renamed from: b */
    private static C2711l f5640b;

    /* renamed from: c */
    private WifiManager f5641c = null;

    /* renamed from: d */
    private C2713a f5642d = null;

    /* renamed from: e */
    private C2710k f5643e = null;

    /* renamed from: f */
    private long f5644f = 0;

    /* renamed from: g */
    private long f5645g = 0;

    /* renamed from: h */
    private boolean f5646h = false;

    /* renamed from: i */
    private ConnectivityManager f5647i = null;

    /* renamed from: j */
    private Handler f5648j = new Handler();

    /* renamed from: k */
    private boolean f5649k = false;

    /* renamed from: l */
    private long f5650l = 0;

    /* renamed from: m */
    private long f5651m = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.c.l$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2713a extends BroadcastReceiver {

        /* renamed from: b */
        private long f5653b;

        /* renamed from: c */
        private boolean f5654c;

        private C2713a() {
            this.f5653b = 0L;
            this.f5654c = false;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null) {
                return;
            }
            String action = intent.getAction();
            if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                C2711l.f5639a = System.currentTimeMillis() / 1000;
                C2711l.this.f5648j.post(new RunnableC2714m(this, intent.getBooleanExtra("resultsUpdated", true)));
            } else if (action.equals("android.net.wifi.STATE_CHANGE") && ((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().equals(NetworkInfo.State.CONNECTED) && System.currentTimeMillis() - this.f5653b >= 5000) {
                this.f5653b = System.currentTimeMillis();
                if (this.f5654c) {
                    return;
                }
                this.f5654c = true;
            }
        }
    }

    private C2711l() {
    }

    /* renamed from: a */
    public static synchronized C2711l m19133a() {
        C2711l c2711l;
        synchronized (C2711l.class) {
            if (f5640b == null) {
                f5640b = new C2711l();
            }
            c2711l = f5640b;
        }
        return c2711l;
    }

    /* renamed from: a */
    private String m19132a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 24) & 255)));
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static boolean m19130a(C2710k c2710k, C2710k c2710k2) {
        boolean m19129a = m19129a(c2710k, c2710k2, C2735k.f5809az);
        long currentTimeMillis = System.currentTimeMillis() - C2628b.f5153c;
        if (currentTimeMillis <= 0 || currentTimeMillis >= 30000 || !m19129a || c2710k2.m19141g() - c2710k.m19141g() <= 30) {
            return m19129a;
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m19129a(C2710k c2710k, C2710k c2710k2, float f) {
        if (c2710k != null && c2710k2 != null) {
            List<ScanResult> list = c2710k.f5634a;
            List<ScanResult> list2 = c2710k2.f5634a;
            if (list == list2) {
                return true;
            }
            if (list != null && list2 != null) {
                int size = list.size();
                int size2 = list2.size();
                if (size == 0 && size2 == 0) {
                    return true;
                }
                if (size != 0 && size2 != 0) {
                    int i = 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        String str = list.get(i2) != null ? list.get(i2).BSSID : null;
                        if (str != null) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= size2) {
                                    break;
                                }
                                String str2 = list2.get(i3) != null ? list2.get(i3).BSSID : null;
                                if (str2 != null && str.equals(str2)) {
                                    i++;
                                    break;
                                }
                                i3++;
                            }
                        }
                    }
                    if (i >= size * f) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public void m19109q() {
        WifiManager wifiManager = this.f5641c;
        if (wifiManager == null) {
            return;
        }
        try {
            C2710k m19131a = m19131a(wifiManager, System.currentTimeMillis());
            if (m19131a.f5634a != null) {
                C2710k c2710k = this.f5643e;
                if (c2710k == null || !m19131a.m19152a(c2710k)) {
                    this.f5643e = m19131a;
                }
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public C2710k m19131a(WifiManager wifiManager, long j) {
        C2710k c2710k = new C2710k(null, 0L);
        return (wifiManager == null || C2735k.f5814f == 4) ? c2710k : new C2710k(wifiManager.getScanResults(), j);
    }

    /* renamed from: b */
    public void m19126b() {
        this.f5650l = 0L;
    }

    /* renamed from: c */
    public synchronized void m19124c() {
        if (this.f5646h) {
            return;
        }
        if (ServiceC2737f.isServing) {
            this.f5641c = (WifiManager) ServiceC2737f.getServiceContext().getApplicationContext().getSystemService("wifi");
            this.f5642d = new C2713a();
            try {
                ServiceC2737f.getServiceContext().registerReceiver(this.f5642d, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
            } catch (Exception unused) {
            }
            this.f5646h = true;
        }
    }

    /* renamed from: d */
    public synchronized void m19122d() {
        if (this.f5646h) {
            try {
                ServiceC2737f.getServiceContext().unregisterReceiver(this.f5642d);
                f5639a = 0L;
            } catch (Exception unused) {
            }
            this.f5642d = null;
            this.f5641c = null;
            this.f5646h = false;
        }
    }

    /* renamed from: e */
    public boolean m19121e() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.f5645g;
        if (currentTimeMillis - j <= 0 || currentTimeMillis - j > 5000) {
            this.f5645g = currentTimeMillis;
            m19126b();
            return m19120f();
        }
        return false;
    }

    /* renamed from: f */
    public boolean m19120f() {
        if (this.f5641c == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.f5644f;
        if (currentTimeMillis - j > 0) {
            long j2 = this.f5650l;
            if (currentTimeMillis - j <= j2 + 5000 || currentTimeMillis - (f5639a * 1000) <= j2 + 5000) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 28 && currentTimeMillis - this.f5644f < 25000) {
                return false;
            }
            if (m19117i() && currentTimeMillis - this.f5644f <= this.f5650l + 10000) {
                return false;
            }
        }
        return m19118h();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: g */
    public String m19119g() {
        WifiManager wifiManager = this.f5641c;
        if (wifiManager != null) {
            try {
                if (!wifiManager.isWifiEnabled()) {
                    if (Build.VERSION.SDK_INT <= 17) {
                        return "";
                    }
                    if (!this.f5641c.isScanAlwaysAvailable()) {
                        return "";
                    }
                }
                return "&wifio=1";
            } catch (Exception | NoSuchMethodError unused) {
                return "";
            }
        }
        return "";
    }

    @SuppressLint({"NewApi"})
    /* renamed from: h */
    public boolean m19118h() {
        long currentTimeMillis = System.currentTimeMillis() - this.f5651m;
        if (currentTimeMillis < 0 || currentTimeMillis > 2000) {
            this.f5651m = System.currentTimeMillis();
            try {
                if ((this.f5641c.isWifiEnabled() || (Build.VERSION.SDK_INT > 17 && this.f5641c.isScanAlwaysAvailable())) && C2735k.f5814f != 4) {
                    this.f5641c.startScan();
                    this.f5644f = System.currentTimeMillis();
                    return true;
                }
            } catch (Exception | NoSuchMethodError unused) {
            }
            return false;
        }
        return false;
    }

    /* renamed from: i */
    public boolean m19117i() {
        try {
            if (this.f5647i == null) {
                this.f5647i = (ConnectivityManager) ServiceC2737f.getServiceContext().getSystemService("connectivity");
            }
            if (this.f5647i != null) {
                return this.f5647i.getNetworkInfo(1).isConnected();
            }
            return false;
        } catch (Error | Exception unused) {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: j */
    public boolean m19116j() {
        C2710k m19112n;
        try {
            if ((!this.f5641c.isWifiEnabled() && (Build.VERSION.SDK_INT <= 17 || !this.f5641c.isScanAlwaysAvailable())) || m19117i() || (m19112n = m19112n()) == null) {
                return false;
            }
            return m19112n.m19143e();
        } catch (Exception | NoSuchMethodError unused) {
            return false;
        }
    }

    /* renamed from: k */
    public WifiInfo m19115k() {
        if (this.f5641c != null && C2735k.f5814f != 4) {
            try {
                WifiInfo connectionInfo = this.f5641c.getConnectionInfo();
                if (connectionInfo != null && connectionInfo.getBSSID() != null && connectionInfo.getRssi() > -100) {
                    String bssid = connectionInfo.getBSSID();
                    if (bssid != null) {
                        String replace = bssid.replace(":", "");
                        if (!"000000000000".equals(replace) && !"".equals(replace)) {
                            if (replace.equals("020000000000")) {
                            }
                        }
                        return null;
                    }
                    return connectionInfo;
                }
            } catch (Error | Exception unused) {
            }
        }
        return null;
    }

    /* renamed from: l */
    public String m19114l() {
        StringBuffer stringBuffer = new StringBuffer();
        WifiInfo m19115k = m19133a().m19115k();
        if (m19115k != null && m19115k.getBSSID() != null) {
            String replace = m19115k.getBSSID().replace(":", "");
            int rssi = m19115k.getRssi();
            String m19113m = m19133a().m19113m();
            if (rssi < 0) {
                rssi = -rssi;
            }
            if (replace != null && rssi < 100 && !replace.equals("020000000000")) {
                stringBuffer.append("&wf=");
                stringBuffer.append(replace);
                stringBuffer.append(";");
                stringBuffer.append("" + rssi + ";");
                String ssid = m19115k.getSSID();
                if (ssid != null && (ssid.contains("&") || ssid.contains(";"))) {
                    ssid = ssid.replace("&", "_");
                }
                stringBuffer.append(ssid);
                stringBuffer.append("&wf_n=1");
                if (m19113m != null) {
                    stringBuffer.append("&wf_gw=");
                    stringBuffer.append(m19113m);
                }
                return stringBuffer.toString();
            }
        }
        return null;
    }

    /* renamed from: m */
    public String m19113m() {
        DhcpInfo dhcpInfo;
        WifiManager wifiManager = this.f5641c;
        if (wifiManager == null || (dhcpInfo = wifiManager.getDhcpInfo()) == null) {
            return null;
        }
        return m19132a(dhcpInfo.gateway);
    }

    /* renamed from: n */
    public C2710k m19112n() {
        C2710k c2710k = this.f5643e;
        return (c2710k == null || !c2710k.m19138j()) ? m19110p() : this.f5643e;
    }

    /* renamed from: o */
    public C2710k m19111o() {
        C2710k c2710k = this.f5643e;
        return (c2710k == null || !c2710k.m19137k()) ? m19110p() : this.f5643e;
    }

    /* renamed from: p */
    public C2710k m19110p() {
        WifiManager wifiManager = this.f5641c;
        if (wifiManager != null) {
            try {
                return m19131a(wifiManager, this.f5644f);
            } catch (Exception unused) {
            }
        }
        return m19131a((WifiManager) null, 0L);
    }
}
