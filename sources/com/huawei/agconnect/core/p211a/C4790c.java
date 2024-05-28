package com.huawei.agconnect.core.p211a;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import com.huawei.agconnect.core.Service;
import com.huawei.agconnect.core.ServiceDiscovery;
import com.huawei.agconnect.core.ServiceRegistrar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.huawei.agconnect.core.a.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4790c {

    /* renamed from: a */
    private final Context f10811a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.agconnect.core.a.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4792a implements Serializable, Comparator<Map.Entry<String, Integer>> {
        private C4792a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Map.Entry<String, Integer> entry, Map.Entry<String, Integer> entry2) {
            return entry.getValue().intValue() - entry2.getValue().intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4790c(Context context) {
        this.f10811a = context;
    }

    /* renamed from: a */
    private <T extends ServiceRegistrar> T m15383a(String str) {
        String str2;
        StringBuilder sb;
        String localizedMessage;
        try {
            Class<?> cls = Class.forName(str);
            if (ServiceRegistrar.class.isAssignableFrom(cls)) {
                return (T) Class.forName(str).newInstance();
            }
            Log.e("ServiceRegistrarParser", cls + " must extends from ServiceRegistrar.");
            return null;
        } catch (ClassNotFoundException e) {
            str2 = "ServiceRegistrarParser";
            sb = new StringBuilder();
            sb.append("Can not found service class, ");
            localizedMessage = e.getMessage();
            sb.append(localizedMessage);
            Log.e(str2, sb.toString());
            return null;
        } catch (IllegalAccessException e2) {
            str2 = "ServiceRegistrarParser";
            sb = new StringBuilder();
            sb.append("instantiate service class exception ");
            localizedMessage = e2.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e(str2, sb.toString());
            return null;
        } catch (InstantiationException e3) {
            str2 = "ServiceRegistrarParser";
            sb = new StringBuilder();
            sb.append("instantiate service class exception ");
            localizedMessage = e3.getLocalizedMessage();
            sb.append(localizedMessage);
            Log.e(str2, sb.toString());
            return null;
        }
    }

    /* renamed from: b */
    private List<String> m15382b() {
        String str;
        StringBuilder sb;
        ArrayList arrayList = new ArrayList();
        Bundle m15381c = m15381c();
        if (m15381c == null) {
            return arrayList;
        }
        HashMap hashMap = new HashMap(10);
        for (String str2 : m15381c.keySet()) {
            if ("com.huawei.agconnect.core.ServiceRegistrar".equals(m15381c.getString(str2))) {
                String[] split = str2.split(":");
                if (split.length == 2) {
                    try {
                        hashMap.put(split[0], Integer.valueOf(split[1]));
                    } catch (NumberFormatException e) {
                        str = "ServiceRegistrarParser";
                        sb = new StringBuilder();
                        sb.append("registrar configuration format error:");
                        str2 = e.getMessage();
                    }
                } else if (split.length == 1) {
                    hashMap.put(split[0], 1000);
                } else {
                    str = "ServiceRegistrarParser";
                    sb = new StringBuilder();
                    sb.append("registrar configuration error, ");
                    sb.append(str2);
                    Log.e(str, sb.toString());
                }
            }
        }
        ArrayList<Map.Entry> arrayList2 = new ArrayList(hashMap.entrySet());
        Collections.sort(arrayList2, new C4792a());
        for (Map.Entry entry : arrayList2) {
            arrayList.add(entry.getKey());
        }
        return arrayList;
    }

    /* renamed from: c */
    private Bundle m15381c() {
        ServiceInfo serviceInfo;
        PackageManager packageManager = this.f10811a.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        try {
            serviceInfo = packageManager.getServiceInfo(new ComponentName(this.f10811a, ServiceDiscovery.class), 128);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ServiceRegistrarParser", "get ServiceDiscovery exception." + e.getLocalizedMessage());
        }
        if (serviceInfo == null) {
            Log.e("ServiceRegistrarParser", "Can not found ServiceDiscovery service.");
            return null;
        }
        return serviceInfo.metaData;
    }

    /* renamed from: a */
    public List<Service> m15384a() {
        Log.i("ServiceRegistrarParser", "getServices");
        List<String> m15382b = m15382b();
        ArrayList arrayList = new ArrayList();
        for (String str : m15382b) {
            ServiceRegistrar m15383a = m15383a(str);
            if (m15383a != null) {
                m15383a.initialize(this.f10811a);
                List<Service> services = m15383a.getServices(this.f10811a);
                if (services != null) {
                    arrayList.addAll(services);
                }
            }
        }
        Log.i("ServiceRegistrarParser", "services:" + Integer.valueOf(arrayList.size()));
        return arrayList;
    }
}
