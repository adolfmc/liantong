package com.p319ss.android.downloadlib.addownload.model;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadlib.addownload.C9940x;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: com.ss.android.downloadlib.addownload.model.hj */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9917hj {

    /* renamed from: b */
    private static final String[] f19104b = {"com", "android", "ss"};

    /* renamed from: hj */
    private static final int[] f19105hj = {3101, 3102, 3103, 3201, 3202, 3203};

    /* renamed from: mb */
    private static volatile C9917hj f19106mb;

    /* renamed from: ox */
    private final LinkedList<C9919mb> f19107ox = new LinkedList<>();

    private C9917hj() {
    }

    /* renamed from: mb */
    public static C9917hj m7468mb() {
        if (f19106mb == null) {
            synchronized (C9917hj.class) {
                if (f19106mb == null) {
                    f19106mb = new C9917hj();
                }
            }
        }
        return f19106mb;
    }

    /* renamed from: mb */
    public void m7466mb(String str) {
        C9919mb m7469b;
        m7464ox();
        if (TextUtils.isEmpty(str) || (m7469b = m7469b(str)) == null) {
            return;
        }
        synchronized (this.f19107ox) {
            this.f19107ox.add(m7469b);
        }
    }

    /* renamed from: ox */
    public void m7462ox(String str) {
        m7464ox();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.f19107ox) {
            Iterator<C9919mb> it = this.f19107ox.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().f19111mb)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    /* renamed from: mb */
    public C9919mb m7467mb(C9837ox c9837ox) {
        if (c9837ox == null) {
            return null;
        }
        m7464ox();
        synchronized (this.f19107ox) {
            Iterator<C9919mb> it = this.f19107ox.iterator();
            while (it.hasNext()) {
                C9919mb next = it.next();
                if (next.f19109h > c9837ox.m7732xa()) {
                    return next;
                }
            }
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0098, code lost:
        r6[1] = r10;
     */
    /* renamed from: ox */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.util.Pair<com.p319ss.android.downloadlib.addownload.model.C9917hj.C9919mb, java.lang.Integer> m7463ox(com.p319ss.android.downloadad.api.p324mb.C9837ox r17) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.downloadlib.addownload.model.C9917hj.m7463ox(com.ss.android.downloadad.api.mb.ox):android.util.Pair");
    }

    /* renamed from: ox */
    private void m7464ox() {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.f19107ox) {
            Iterator<C9919mb> it = this.f19107ox.iterator();
            while (it.hasNext() && currentTimeMillis - it.next().f19109h > 1800000) {
                it.remove();
            }
        }
    }

    /* renamed from: b */
    private C9919mb m7469b(String str) {
        try {
            PackageManager packageManager = C9940x.getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (packageInfo != null) {
                return new C9919mb(str, packageInfo.versionCode, packageInfo.versionName, (String) packageManager.getApplicationLabel(packageInfo.applicationInfo), System.currentTimeMillis());
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: mb */
    private static boolean m7465mb(String str, String str2) {
        String[] split;
        String[] split2;
        int i;
        boolean z;
        try {
            split = str.split("\\.");
            split2 = str2.split("\\.");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (split.length != 0 && split2.length != 0) {
            int i2 = 0;
            int i3 = 0;
            for (String str3 : split) {
                String[] strArr = f19104b;
                int length = strArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 >= length) {
                        i = i2;
                        z = false;
                        break;
                    }
                    String str4 = strArr[i4];
                    if (str4.equals(str3)) {
                        if (i2 < split2.length && str4.equals(split2[i2])) {
                            i2++;
                        }
                        i = i2;
                        z = true;
                    } else {
                        i4++;
                    }
                }
                if (z) {
                    i2 = i;
                } else {
                    i2 = i;
                    while (i < split2.length) {
                        if (str3.equals(split2[i])) {
                            if (i == i2) {
                                i2++;
                            }
                            i3++;
                            if (i3 >= 2) {
                                return true;
                            }
                        }
                        i++;
                    }
                    continue;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.model.hj$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C9919mb {

        /* renamed from: b */
        public final String f19108b;

        /* renamed from: h */
        public final long f19109h;

        /* renamed from: hj */
        public final String f19110hj;

        /* renamed from: mb */
        public final String f19111mb;

        /* renamed from: ox */
        public final int f19112ox;

        private C9919mb(String str, int i, String str2, String str3, long j) {
            this.f19111mb = str;
            this.f19112ox = i;
            this.f19108b = str2 != null ? str2.toLowerCase() : null;
            this.f19110hj = str3 != null ? str3.toLowerCase() : null;
            this.f19109h = j;
        }
    }
}
