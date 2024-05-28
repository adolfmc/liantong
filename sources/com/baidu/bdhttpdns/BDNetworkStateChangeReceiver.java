package com.baidu.bdhttpdns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import com.baidu.bdhttpdns.BDHttpDns;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BDNetworkStateChangeReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private boolean f4305a = false;

    /* renamed from: b */
    private boolean f4306b = true;

    /* renamed from: c */
    private boolean f4307c = true;

    /* renamed from: a */
    private void m20145a(Context context) {
        C2444h.m20103a("Network change, clearCache(%b) httpDnsPrefetch(%b)", Boolean.valueOf(this.f4306b), Boolean.valueOf(this.f4307c));
        BDHttpDns service = BDHttpDns.getService(context);
        ArrayList<String> m20130b = service.m20151a().m20130b();
        if (this.f4306b) {
            service.m20151a().m20134a();
            service.m20149b().m20134a();
        }
        if (!this.f4307c || m20130b == null || m20130b.isEmpty()) {
            return;
        }
        C2438f m20121a = C2438f.m20121a();
        Iterator<String> it = m20130b.iterator();
        while (it.hasNext()) {
            service.getClass();
            m20121a.m20114a(it.next(), new BDHttpDns.C2429b());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20144a(boolean z) {
        this.f4306b = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m20143b(boolean z) {
        this.f4307c = z;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!this.f4305a) {
            this.f4305a = true;
            return;
        }
        if (Build.VERSION.SDK_INT < 21) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                m20145a(context);
                return;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (!(networkInfo == null && networkInfo2 == null) && ((networkInfo == null || !networkInfo.isConnected()) && (networkInfo2 == null || !networkInfo2.isConnected()))) {
                return;
            }
            m20145a(context);
            return;
        }
        ConnectivityManager connectivityManager2 = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager2 == null) {
            m20145a(context);
            return;
        }
        Network[] allNetworks = connectivityManager2.getAllNetworks();
        if (allNetworks == null) {
            m20145a(context);
            return;
        }
        for (Network network : allNetworks) {
            NetworkInfo networkInfo3 = connectivityManager2.getNetworkInfo(network);
            if (networkInfo3 == null || networkInfo3.isConnected()) {
                m20145a(context);
                return;
            }
        }
    }
}
