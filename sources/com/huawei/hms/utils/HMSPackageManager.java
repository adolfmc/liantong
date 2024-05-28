package com.huawei.hms.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import android.util.Pair;
import com.huawei.hms.device.X509CertUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.PackageManagerHelper;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HMSPackageManager {

    /* renamed from: n */
    private static HMSPackageManager f11816n;

    /* renamed from: o */
    private static final Object f11817o = new Object();

    /* renamed from: p */
    private static final Object f11818p = new Object();

    /* renamed from: q */
    private static final Object f11819q = new Object();

    /* renamed from: a */
    private final Context f11820a;

    /* renamed from: b */
    private final PackageManagerHelper f11821b;

    /* renamed from: c */
    private String f11822c;

    /* renamed from: d */
    private String f11823d;

    /* renamed from: e */
    private int f11824e;

    /* renamed from: f */
    private String f11825f;

    /* renamed from: g */
    private String f11826g;

    /* renamed from: h */
    private String f11827h;

    /* renamed from: i */
    private String f11828i;

    /* renamed from: j */
    private int f11829j;

    /* renamed from: k */
    private int f11830k;

    /* renamed from: l */
    private long f11831l;

    /* renamed from: m */
    private boolean f11832m;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class PackagePriorityInfo implements Comparable<PackagePriorityInfo> {

        /* renamed from: a */
        private String f11833a;

        /* renamed from: b */
        private String f11834b;

        /* renamed from: c */
        private String f11835c;

        /* renamed from: d */
        private String f11836d;

        /* renamed from: e */
        private String f11837e;

        /* renamed from: f */
        private Long f11838f;

        public PackagePriorityInfo(String str, String str2, String str3, String str4, String str5, long j) {
            this.f11833a = str;
            this.f11834b = str2;
            this.f11835c = str3;
            this.f11836d = str4;
            this.f11837e = str5;
            this.f11838f = Long.valueOf(j);
        }

        @Override // java.lang.Comparable
        public int compareTo(PackagePriorityInfo packagePriorityInfo) {
            if (TextUtils.equals(this.f11837e, packagePriorityInfo.f11837e)) {
                return this.f11838f.compareTo(packagePriorityInfo.f11838f);
            }
            return this.f11837e.compareTo(packagePriorityInfo.f11837e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.utils.HMSPackageManager$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5092a implements Runnable {
        RunnableC5092a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.m14110i("HMSPackageManager", "enter asyncOnceCheckMDMState");
            try {
                List<ResolveInfo> queryIntentServices = HMSPackageManager.this.f11820a.getPackageManager().queryIntentServices(new Intent("com.huawei.hms.core.aidlservice"), 128);
                if (queryIntentServices == null || queryIntentServices.size() == 0) {
                    return;
                }
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    if ("com.huawei.hwid".equals(resolveInfo.serviceInfo.applicationInfo.packageName)) {
                        HMSPackageManager.this.m14074c();
                    }
                }
                HMSLog.m14110i("HMSPackageManager", "quit asyncOnceCheckMDMState");
            } catch (Exception e) {
                HMSLog.m14112e("HMSPackageManager", "asyncOnceCheckMDMState query hms action failed. " + e.getMessage());
            }
        }
    }

    private HMSPackageManager(Context context) {
        this.f11820a = context;
        this.f11821b = new PackageManagerHelper(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0024 A[Catch: all -> 0x0077, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0018, B:12:0x0024, B:13:0x0042, B:15:0x0044, B:18:0x004b, B:19:0x0073), top: B:25:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0044 A[Catch: all -> 0x0077, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0018, B:12:0x0024, B:13:0x0042, B:15:0x0044, B:18:0x004b, B:19:0x0073), top: B:25:0x0003 }] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m14074c() {
        /*
            r6 = this;
            java.lang.Object r0 = com.huawei.hms.utils.HMSPackageManager.f11819q
            monitor-enter(r0)
            java.lang.String r1 = "HMSPackageManager"
            java.lang.String r2 = "enter checkHmsIsSpoof"
            com.huawei.hms.support.log.HMSLog.m14110i(r1, r2)     // Catch: java.lang.Throwable -> L77
            com.huawei.hms.utils.PackageManagerHelper r1 = r6.f11821b     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = "com.huawei.hwid"
            long r1 = r1.getPackageFirstInstallTime(r2)     // Catch: java.lang.Throwable -> L77
            int r3 = r6.f11830k     // Catch: java.lang.Throwable -> L77
            r4 = 3
            r5 = 1
            if (r3 == r4) goto L21
            long r3 = r6.f11831l     // Catch: java.lang.Throwable -> L77
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 == 0) goto L1f
            goto L21
        L1f:
            r1 = 0
            goto L22
        L21:
            r1 = r5
        L22:
            if (r1 != 0) goto L44
            java.lang.String r1 = "HMSPackageManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77
            r2.<init>()     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = "quit checkHmsIsSpoof cached state: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            int r3 = r6.f11830k     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = m14082a(r3)     // Catch: java.lang.Throwable -> L77
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L77
            com.huawei.hms.support.log.HMSLog.m14110i(r1, r2)     // Catch: java.lang.Throwable -> L77
            int r1 = r6.f11830k     // Catch: java.lang.Throwable -> L77
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            return r1
        L44:
            boolean r1 = r6.m14077b()     // Catch: java.lang.Throwable -> L77
            if (r1 == 0) goto L4b
            r5 = 2
        L4b:
            r6.f11830k = r5     // Catch: java.lang.Throwable -> L77
            com.huawei.hms.utils.PackageManagerHelper r1 = r6.f11821b     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = "com.huawei.hwid"
            long r1 = r1.getPackageFirstInstallTime(r2)     // Catch: java.lang.Throwable -> L77
            r6.f11831l = r1     // Catch: java.lang.Throwable -> L77
            java.lang.String r1 = "HMSPackageManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77
            r2.<init>()     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = "quit checkHmsIsSpoof state: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            int r3 = r6.f11830k     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = m14082a(r3)     // Catch: java.lang.Throwable -> L77
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L77
            com.huawei.hms.support.log.HMSLog.m14110i(r1, r2)     // Catch: java.lang.Throwable -> L77
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            int r0 = r6.f11830k
            return r0
        L77:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.utils.HMSPackageManager.m14074c():int");
    }

    /* renamed from: d */
    private void m14073d() {
        synchronized (f11818p) {
            this.f11825f = null;
            this.f11826g = null;
            this.f11827h = null;
            this.f11828i = null;
            this.f11829j = 0;
        }
    }

    /* renamed from: e */
    private void m14072e() {
        synchronized (f11818p) {
            this.f11822c = null;
            this.f11823d = null;
            this.f11824e = 0;
        }
    }

    /* renamed from: f */
    private Pair<String, String> m14071f() {
        try {
            List<ResolveInfo> queryIntentServices = this.f11820a.getPackageManager().queryIntentServices(new Intent("com.huawei.hms.core.aidlservice"), 128);
            if (queryIntentServices != null && queryIntentServices.size() != 0) {
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    String str = resolveInfo.serviceInfo.applicationInfo.packageName;
                    String packageSignature = this.f11821b.getPackageSignature(str);
                    if ("com.huawei.hwid".equals(str) && this.f11821b.getPackageVersionCode(str) < 30000000) {
                        return new Pair<>(str, packageSignature);
                    }
                    Bundle bundle = resolveInfo.serviceInfo.metaData;
                    if (bundle == null) {
                        HMSLog.m14112e("HMSPackageManager", "skip package " + str + " for metadata is null");
                    } else if (!bundle.containsKey("hms_app_signer")) {
                        HMSLog.m14112e("HMSPackageManager", "skip package " + str + " for no signer");
                    } else if (bundle.containsKey("hms_app_cert_chain")) {
                        if (!m14078a(str + "&" + packageSignature, bundle.getString("hms_app_signer"), bundle.getString("hms_app_cert_chain"))) {
                            HMSLog.m14112e("HMSPackageManager", "checkSigner failed");
                        } else {
                            return new Pair<>(str, packageSignature);
                        }
                    } else {
                        HMSLog.m14112e("HMSPackageManager", "skip package " + str + " for no cert chain");
                    }
                }
                return null;
            }
            HMSLog.m14112e("HMSPackageManager", "query hms action, resolveInfoList is null or empty.");
            return null;
        } catch (Exception e) {
            HMSLog.m14112e("HMSPackageManager", "getHmsPackageName query hms action failed. " + e.getMessage());
            return null;
        }
    }

    /* renamed from: g */
    private Pair<String, String> m14070g() {
        Pair<String, String> m14071f = m14071f();
        if (m14071f != null) {
            HMSLog.m14110i("HMSPackageManager", "aidlService pkgName: " + ((String) m14071f.first));
            this.f11827h = "com.huawei.hms.core.aidlservice";
            this.f11828i = null;
            return m14071f;
        }
        ArrayList<PackagePriorityInfo> m14069h = m14069h();
        if (m14069h == null) {
            HMSLog.m14112e("HMSPackageManager", "PackagePriorityInfo list is null");
            return null;
        }
        Iterator<PackagePriorityInfo> it = m14069h.iterator();
        while (it.hasNext()) {
            PackagePriorityInfo next = it.next();
            String str = next.f11833a;
            String str2 = next.f11834b;
            String str3 = next.f11835c;
            String str4 = next.f11836d;
            String packageSignature = this.f11821b.getPackageSignature(str);
            if (m14078a(str + "&" + packageSignature + "&" + str2, str3, str4)) {
                HMSLog.m14110i("HMSPackageManager", "result: " + str + ", " + str2 + ", " + next.f11838f);
                this.f11827h = "com.huawei.hms.core";
                m14075b(str2);
                return new Pair<>(str, packageSignature);
            }
        }
        return null;
    }

    public static HMSPackageManager getInstance(Context context) {
        synchronized (f11817o) {
            if (f11816n == null) {
                if (context.getApplicationContext() != null) {
                    f11816n = new HMSPackageManager(context.getApplicationContext());
                } else {
                    f11816n = new HMSPackageManager(context);
                }
                f11816n.m14067j();
                f11816n.m14083a();
            }
        }
        return f11816n;
    }

    /* renamed from: h */
    private ArrayList<PackagePriorityInfo> m14069h() {
        try {
            List<ResolveInfo> queryIntentServices = this.f11820a.getPackageManager().queryIntentServices(new Intent("com.huawei.hms.core"), 128);
            if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
                ArrayList<PackagePriorityInfo> arrayList = new ArrayList<>();
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    String str = resolveInfo.serviceInfo.applicationInfo.packageName;
                    long packageFirstInstallTime = this.f11821b.getPackageFirstInstallTime(str);
                    Bundle bundle = resolveInfo.serviceInfo.metaData;
                    if (bundle == null) {
                        HMSLog.m14112e("HMSPackageManager", "package " + str + " get metaData is null");
                    } else {
                        String m14081a = m14081a(bundle, "hms_app_checker_config");
                        String m14079a = m14079a(m14081a);
                        if (TextUtils.isEmpty(m14079a)) {
                            HMSLog.m14110i("HMSPackageManager", "get priority fail. hmsCheckerCfg: " + m14081a);
                        } else {
                            String m14081a2 = m14081a(bundle, "hms_app_signer_v2");
                            if (TextUtils.isEmpty(m14081a2)) {
                                HMSLog.m14110i("HMSPackageManager", "get signerV2 fail.");
                            } else {
                                String m14081a3 = m14081a(bundle, "hms_app_cert_chain");
                                if (TextUtils.isEmpty(m14081a3)) {
                                    HMSLog.m14110i("HMSPackageManager", "get certChain fail.");
                                } else {
                                    HMSLog.m14110i("HMSPackageManager", "add: " + str + ", " + m14081a + ", " + packageFirstInstallTime);
                                    arrayList.add(new PackagePriorityInfo(str, m14081a, m14081a2, m14081a3, m14079a, packageFirstInstallTime));
                                }
                            }
                        }
                    }
                }
                Collections.sort(arrayList);
                return arrayList;
            }
            HMSLog.m14112e("HMSPackageManager", "query aglite action, resolveInfoList is null or empty");
            return null;
        } catch (Exception e) {
            HMSLog.m14112e("HMSPackageManager", "query aglite action failed. " + e.getMessage());
            return null;
        }
    }

    /* renamed from: i */
    private void m14068i() {
        synchronized (f11818p) {
            Pair<String, String> m14071f = m14071f();
            if (m14071f == null) {
                HMSLog.m14112e("HMSPackageManager", "<initHmsPackageInfo> Failed to find HMS apk");
                m14072e();
                return;
            }
            this.f11822c = (String) m14071f.first;
            this.f11823d = (String) m14071f.second;
            this.f11824e = this.f11821b.getPackageVersionCode(getHMSPackageName());
            HMSLog.m14110i("HMSPackageManager", "<initHmsPackageInfo> Succeed to find HMS apk: " + this.f11822c + " version: " + this.f11824e);
        }
    }

    /* renamed from: j */
    private void m14067j() {
        synchronized (f11818p) {
            Pair<String, String> m14070g = m14070g();
            if (m14070g == null) {
                HMSLog.m14112e("HMSPackageManager", "<initHmsPackageInfoForMultiService> Failed to find HMS apk");
                m14073d();
                return;
            }
            this.f11825f = (String) m14070g.first;
            this.f11826g = (String) m14070g.second;
            this.f11829j = this.f11821b.getPackageVersionCode(getHMSPackageNameForMultiService());
            HMSLog.m14110i("HMSPackageManager", "<initHmsPackageInfoForMultiService> Succeed to find HMS apk: " + this.f11825f + " version: " + this.f11829j);
        }
    }

    /* renamed from: k */
    private boolean m14066k() {
        Bundle bundle;
        PackageManager packageManager = this.f11820a.getPackageManager();
        if (packageManager == null) {
            HMSLog.m14112e("HMSPackageManager", "In isMinApkVersionEffective, Failed to get 'PackageManager' instance.");
            return true;
        }
        try {
        } catch (AndroidException unused) {
            HMSLog.m14112e("HMSPackageManager", "In isMinApkVersionEffective, Failed to read meta data for HMSCore API level.");
        } catch (RuntimeException e) {
            HMSLog.m14111e("HMSPackageManager", "In isMinApkVersionEffective, Failed to read meta data for HMSCore API level.", e);
        }
        if (!TextUtils.isEmpty(this.f11827h) && (this.f11827h.equals("com.huawei.hms.core") || this.f11827h.equals("com.huawei.hms.core.internal"))) {
            HMSLog.m14110i("HMSPackageManager", "action = " + this.f11827h + " exist");
            return false;
        }
        ApplicationInfo applicationInfo = packageManager.getPackageInfo(getHMSPackageName(), 128).applicationInfo;
        if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && bundle.containsKey("com.huawei.hms.kit.api_level:hmscore") && (getHmsVersionCode() >= 50000000 || getHmsVersionCode() <= 19999999)) {
            HMSLog.m14110i("HMSPackageManager", "MinApkVersion is disabled.");
            return false;
        }
        return true;
    }

    public String getHMSFingerprint() {
        String str = this.f11823d;
        return str == null ? "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05" : str;
    }

    public String getHMSPackageName() {
        HMSLog.m14110i("HMSPackageManager", "Enter getHMSPackageName");
        refresh();
        String str = this.f11822c;
        if (str != null) {
            if (PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(this.f11821b.getPackageStates(str))) {
                HMSLog.m14110i("HMSPackageManager", "The package name is not installed and needs to be refreshed again");
                m14068i();
            }
            String str2 = this.f11822c;
            if (str2 != null) {
                return str2;
            }
        }
        if (PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(this.f11821b.getPackageStates("com.huawei.hwid"))) {
            return "com.huawei.hwid";
        }
        "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05".equalsIgnoreCase(this.f11821b.getPackageSignature("com.huawei.hwid"));
        return "com.huawei.hwid";
    }

    public String getHMSPackageNameForMultiService() {
        HMSLog.m14110i("HMSPackageManager", "Enter getHMSPackageNameForMultiService");
        refreshForMultiService();
        String str = this.f11825f;
        if (str != null) {
            if (PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(this.f11821b.getPackageStates(str))) {
                HMSLog.m14110i("HMSPackageManager", "The package name is not installed and needs to be refreshed again");
                m14067j();
            }
            String str2 = this.f11825f;
            return str2 != null ? str2 : "com.huawei.hwid";
        }
        return "com.huawei.hwid";
    }

    public PackageManagerHelper.PackageStates getHMSPackageStates() {
        synchronized (f11817o) {
            refresh();
            PackageManagerHelper.PackageStates packageStates = this.f11821b.getPackageStates(this.f11822c);
            PackageManagerHelper.PackageStates packageStates2 = PackageManagerHelper.PackageStates.NOT_INSTALLED;
            if (packageStates == packageStates2) {
                m14072e();
                return packageStates2;
            }
            boolean z = false;
            if ("com.huawei.hwid".equals(this.f11822c) && m14074c() == 1) {
                return PackageManagerHelper.PackageStates.SPOOF;
            }
            if (packageStates == PackageManagerHelper.PackageStates.ENABLED && !this.f11823d.equals(this.f11821b.getPackageSignature(this.f11822c))) {
                z = true;
            }
            return z ? packageStates2 : packageStates;
        }
    }

    public PackageManagerHelper.PackageStates getHMSPackageStatesForMultiService() {
        synchronized (f11817o) {
            refreshForMultiService();
            PackageManagerHelper.PackageStates packageStates = this.f11821b.getPackageStates(this.f11825f);
            PackageManagerHelper.PackageStates packageStates2 = PackageManagerHelper.PackageStates.NOT_INSTALLED;
            if (packageStates == packageStates2) {
                m14073d();
                return packageStates2;
            }
            boolean z = false;
            if ("com.huawei.hwid".equals(this.f11825f) && m14074c() == 1) {
                return PackageManagerHelper.PackageStates.SPOOF;
            }
            if (packageStates == PackageManagerHelper.PackageStates.ENABLED && !this.f11826g.equals(this.f11821b.getPackageSignature(this.f11825f))) {
                z = true;
            }
            return z ? packageStates2 : packageStates;
        }
    }

    public int getHmsMultiServiceVersion() {
        return this.f11821b.getPackageVersionCode(getHMSPackageNameForMultiService());
    }

    public int getHmsVersionCode() {
        return this.f11821b.getPackageVersionCode(getHMSPackageName());
    }

    public String getInnerServiceAction() {
        return "com.huawei.hms.core.internal";
    }

    public String getServiceAction() {
        return !TextUtils.isEmpty(this.f11827h) ? this.f11827h : "com.huawei.hms.core.aidlservice";
    }

    public boolean hmsVerHigherThan(int i) {
        if (this.f11824e >= i || !m14066k()) {
            return true;
        }
        int packageVersionCode = this.f11821b.getPackageVersionCode(getHMSPackageName());
        this.f11824e = packageVersionCode;
        return packageVersionCode >= i;
    }

    public boolean isApkNeedUpdate(int i) {
        int hmsVersionCode = getHmsVersionCode();
        HMSLog.m14110i("HMSPackageManager", "current versionCode:" + hmsVersionCode + ", target version requirements: " + i);
        return hmsVersionCode < i;
    }

    public boolean isApkUpdateNecessary(int i) {
        int hmsVersionCode = getHmsVersionCode();
        HMSLog.m14110i("HMSPackageManager", "current versionCode:" + hmsVersionCode + ", minimum version requirements: " + i);
        return m14066k() && hmsVersionCode < i;
    }

    public boolean isUseOldCertificate() {
        return this.f11832m;
    }

    public void refresh() {
        if (TextUtils.isEmpty(this.f11822c) || TextUtils.isEmpty(this.f11823d)) {
            m14068i();
        }
    }

    public void refreshForMultiService() {
        if (TextUtils.isEmpty(this.f11825f) || TextUtils.isEmpty(this.f11826g)) {
            m14067j();
        }
    }

    public void resetMultiServiceState() {
        m14073d();
    }

    public void setUseOldCertificate(boolean z) {
        this.f11832m = z;
    }

    /* renamed from: a */
    private String m14081a(Bundle bundle, String str) {
        if (!bundle.containsKey(str)) {
            HMSLog.m14112e("HMSPackageManager", "no " + str + " in metaData");
            return null;
        }
        return bundle.getString(str);
    }

    /* renamed from: b */
    private void m14075b(String str) {
        String m14079a = m14079a(str);
        if (TextUtils.isEmpty(m14079a)) {
            return;
        }
        this.f11828i = m14079a.substring(9);
    }

    /* renamed from: a */
    private String m14079a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("priority=");
        if (indexOf == -1) {
            HMSLog.m14112e("HMSPackageManager", "get indexOfIdentifier -1");
            return null;
        }
        int indexOf2 = str.indexOf(",", indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        return str.substring(indexOf, indexOf2);
    }

    /* renamed from: b */
    private boolean m14077b() {
        String hmsPath = ReadApkFileUtil.getHmsPath(this.f11820a);
        if (hmsPath == null) {
            HMSLog.m14110i("HMSPackageManager", "hmsPath is null!");
            return false;
        } else if (!ReadApkFileUtil.isCertFound(hmsPath)) {
            HMSLog.m14110i("HMSPackageManager", "NO huawer.cer in HMS!");
            return false;
        } else if (!ReadApkFileUtil.checkSignature()) {
            HMSLog.m14110i("HMSPackageManager", "checkSignature fail!");
            return false;
        } else if (ReadApkFileUtil.verifyApkHash(hmsPath)) {
            return true;
        } else {
            HMSLog.m14110i("HMSPackageManager", "verifyApkHash fail!");
            return false;
        }
    }

    /* renamed from: a */
    private boolean m14078a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            List<X509Certificate> m15066b = X509CertUtil.m15066b(str3);
            if (m15066b.size() == 0) {
                HMSLog.m14112e("HMSPackageManager", "certChain is empty");
                return false;
            } else if (!X509CertUtil.m15070a(X509CertUtil.m15077a(this.f11820a), m15066b)) {
                HMSLog.m14112e("HMSPackageManager", "failed to verify cert chain");
                return false;
            } else {
                X509Certificate x509Certificate = m15066b.get(m15066b.size() - 1);
                if (!X509CertUtil.m15072a(x509Certificate, "Huawei CBG HMS")) {
                    HMSLog.m14112e("HMSPackageManager", "CN is invalid");
                    return false;
                } else if (!X509CertUtil.m15065b(x509Certificate, "Huawei CBG Cloud Security Signer")) {
                    HMSLog.m14112e("HMSPackageManager", "OU is invalid");
                    return false;
                } else if (X509CertUtil.m15071a(x509Certificate, str, str2)) {
                    return true;
                } else {
                    HMSLog.m14112e("HMSPackageManager", "signature is invalid: " + str);
                    return false;
                }
            }
        }
        HMSLog.m14112e("HMSPackageManager", "args is invalid");
        return false;
    }

    /* renamed from: a */
    private void m14083a() {
        new Thread(new RunnableC5092a(), "Thread-asyncOnceCheckMDMState").start();
    }

    /* renamed from: a */
    private static String m14082a(int i) {
        if (i == 1) {
            return "SPOOFED";
        }
        if (i == 2) {
            return "SUCCESS";
        }
        if (i == 3) {
            return "UNCHECKED";
        }
        HMSLog.m14112e("HMSPackageManager", "invalid checkMDM state: " + i);
        return "";
    }
}
