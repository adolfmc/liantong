package com.mob.tools.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import com.mob.commons.C5857m;
import com.mob.commons.C5873u;
import com.mob.commons.CSCenter;
import com.mob.tools.MobLog;
import com.mob.tools.p237a.C6034e;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.C6152DH;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NtFetcher implements PublicMemberKeeper {

    /* renamed from: a */
    private static NtFetcher f15251a;

    /* renamed from: b */
    private Context f15252b;

    /* renamed from: c */
    private BroadcastReceiver f15253c;

    /* renamed from: d */
    private String f15254d;

    /* renamed from: e */
    private Integer f15255e;

    /* renamed from: b */
    private boolean m11148b(int i) {
        return i == 20;
    }

    /* renamed from: c */
    private boolean m11145c(int i) {
        return i == 13;
    }

    /* renamed from: d */
    private boolean m11143d(int i) {
        switch (i) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                return true;
            case 6:
                return true;
            case 7:
                return false;
            case 8:
                return true;
            case 9:
                return true;
            case 10:
                return true;
            case 11:
                return false;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 15:
                return true;
            default:
                return false;
        }
    }

    private NtFetcher(Context context) {
        this.f15252b = context;
        m11153a();
    }

    public static NtFetcher getInstance(Context context) {
        if (f15251a == null) {
            synchronized (NtFetcher.class) {
                if (f15251a == null) {
                    f15251a = new NtFetcher(context);
                }
            }
        }
        return f15251a;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: a */
    private void m11153a() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) C6152DH.SyncMtd.getSystemServiceSafe("connectivity");
            if (Build.VERSION.SDK_INT >= 26 && C6152DH.SyncMtd.checkPermission(C5857m.m12226a("039bcUbabhcbbgbadb hd[bhbdbgdfdfbgcbHc;dbcjeheheechchbfcfeeciecejefgabfchcicjciee"))) {
                connectivityManager.registerDefaultNetworkCallback(m11149b());
            } else if (Build.VERSION.SDK_INT >= 21 && C6152DH.SyncMtd.checkPermission(C5857m.m12226a("039bc:babhcbbgbadbXhdJbhbdbgdfdfbgcbJcUdbcjeheheechchbfcfeeciecejefgabfchcicjciee"))) {
                connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), m11149b());
            } else {
                m11142e();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    public void recycle() {
        m11141f();
    }

    public synchronized String getNtType() {
        if (TextUtils.isEmpty(this.f15254d)) {
            this.f15254d = m11140g();
        }
        return this.f15254d;
    }

    public synchronized int getDtNtType() {
        if (this.f15255e == null) {
            this.f15255e = Integer.valueOf(m11144d());
        }
        return this.f15255e.intValue();
    }

    @TargetApi(21)
    /* renamed from: b */
    private ConnectivityManager.NetworkCallback m11149b() {
        return new ConnectivityManager.NetworkCallback() { // from class: com.mob.tools.utils.NtFetcher.1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                super.onAvailable(network);
                NtFetcher.this.m11146c();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                super.onLost(network);
                NtFetcher.this.m11146c();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLosing(Network network, int i) {
                super.onLosing(network, i);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                super.onLinkPropertiesChanged(network, linkProperties);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onUnavailable() {
                super.onUnavailable();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m11146c() {
        this.f15254d = m11140g();
        this.f15255e = Integer.valueOf(m11144d());
    }

    /* renamed from: d */
    private int m11144d() {
        if (C6152DH.SyncMtd.getSystemServiceSafe("phone") != null && C6152DH.SyncMtd.checkPermission(C5857m.m12226a("035bc0babhcbbgbadb3hd,bhbdbgdfdfbgcb'cQdbefeecjdibfeidhejcfeebfchcicjciee"))) {
            if (Build.VERSION.SDK_INT >= 24) {
                return C6034e.m11667a(this.f15252b).m11653c();
            }
            return C6034e.m11667a(this.f15252b).m11657b();
        }
        return -1;
    }

    /* renamed from: e */
    private void m11142e() {
        this.f15253c = new BroadcastReceiver() { // from class: com.mob.tools.utils.NtFetcher.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    if (intent.getAction().equalsIgnoreCase(C5857m.m12226a("036bcKbabhcbbgbadb[cdgJdb=aIcb;ccNdbehejcfcfeeehcicgegcgcigibfehdhcjcfgbee"))) {
                        NtFetcher.this.m11146c();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(C5857m.m12226a("036bcGbabhcbbgbadbHcdg(db8a@cbCccAdbehejcfcfeeehcicgegcgcigibfehdhcjcfgbee"));
        C5873u.m12185a(this.f15253c, intentFilter);
    }

    /* renamed from: f */
    private void m11141f() {
        BroadcastReceiver broadcastReceiver = this.f15253c;
        if (broadcastReceiver != null) {
            C5873u.m12186a(broadcastReceiver);
            this.f15253c = null;
        }
    }

    /* renamed from: g */
    private String m11140g() {
        Object systemServiceSafe;
        NetworkInfo activeNetworkInfo;
        try {
            if (C6152DH.SyncMtd.checkPermission(C5857m.m12226a("039bc;babhcbbgbadbMhd3bhbdbgdfdfbgcbAc0dbcjeheheechchbfcfeeciecejefgabfchcicjciee")) && (systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe("connectivity")) != null && (activeNetworkInfo = ((ConnectivityManager) systemServiceSafe).getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                switch (type) {
                    case 0:
                        int m11657b = C6034e.m11667a(this.f15252b).m11657b();
                        if (m11152a(m11657b)) {
                            return C5857m.m12226a("002Qfggb");
                        }
                        if (m11145c(m11657b)) {
                            return C5857m.m12226a("0028fjgb");
                        }
                        return C5857m.m12226a(m11143d(m11657b) ? "002Dhdgb" : "0027fcgb");
                    case 1:
                        return C5857m.m12226a("0042ddbgcdbg");
                    default:
                        switch (type) {
                            case 6:
                                return C5857m.m12226a("005)ddbgbd=bFca");
                            case 7:
                                return C5857m.m12226a("009Rdc eXbeBdg@cbcb4gf");
                            case 8:
                                return C5857m.m12226a("0058babebdbdbi");
                            case 9:
                                return C5857m.m12226a("008dgfdYbh7cdg");
                            default:
                                return String.valueOf(type);
                        }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
        return C5857m.m12226a("004c:cb6cd");
    }

    /* renamed from: a */
    private boolean m11152a(int i) {
        Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return false;
        }
        if (m11150a(systemServiceSafe) || m11147b(systemServiceSafe)) {
            return true;
        }
        return m11148b(i);
    }

    /* renamed from: a */
    private boolean m11150a(Object obj) {
        Object serviceState;
        if (obj != null && C6152DH.SyncMtd.checkPermission(C5857m.m12226a("035bc'babhcbbgbadbXhdZbhbdbgdfdfbgcb5c!dbefeecjdibfeidhejcfeebfchcicjciee"))) {
            if (CSCenter.getInstance().isPhoneStateDataEnable()) {
                String manufacturer = C6152DH.SyncMtd.getManufacturer();
                serviceState = null;
                if (!TextUtils.isEmpty(manufacturer) && ((manufacturer.contains(C5857m.m12226a("006fLbeWbLdd5d?bg")) || manufacturer.contains(C5857m.m12226a("006=dhbeRbEdd+d2bg")) || manufacturer.contains(C5857m.m12226a("0069dhcecjeceecg"))) && Build.VERSION.SDK_INT >= 29)) {
                    serviceState = ReflectHelper.invokeInstanceMethodNoThrow(obj, C5857m.m12226a("015DccJdgBchCd!bhbbbgOad^chZgbgd"), null, new Object[0]);
                }
            } else {
                serviceState = CSCenter.getInstance().getServiceState();
            }
            if (serviceState != null && ((Integer) ReflectHelper.invokeInstanceMethodNoThrow(serviceState, C5857m.m12226a("016)ccKdg'dhddcfVdg2ddcbbhbjcibi'hd"), 0, new Object[0])).intValue() == 20) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private boolean m11147b(Object obj) {
        Object serviceState;
        if (obj != null && C6152DH.SyncMtd.checkPermission(C5857m.m12226a("035bc=babhcbbgbadbVhd^bhbdbgdfdfbgcb9c>dbefeecjdibfeidhejcfeebfchcicjciee")) && Build.VERSION.SDK_INT >= 26) {
            if (CSCenter.getInstance().isPhoneStateDataEnable()) {
                serviceState = ReflectHelper.invokeInstanceMethodNoThrow(obj, C5857m.m12226a("015Kcc;dg)ch$d+bhbbbgTadHch5gbgd"), null, new Object[0]);
            } else {
                serviceState = CSCenter.getInstance().getServiceState();
            }
            if (serviceState != null && ((Integer) ReflectHelper.invokeInstanceMethodNoThrow(serviceState, C5857m.m12226a("010-cc>dg9cfbhch>gbgd"), 0, new Object[0])).intValue() == 3) {
                return true;
            }
        }
        return false;
    }
}
