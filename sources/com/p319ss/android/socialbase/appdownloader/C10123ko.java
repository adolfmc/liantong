package com.p319ss.android.socialbase.appdownloader;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.ko */
/* loaded from: E:\567196_dexfile_execute.dex */
public final class C10123ko {
    /* renamed from: mb */
    public static int m6764mb(String str) {
        if (m6766b(str)) {
            return -1;
        }
        try {
            PackageInfo packageInfo = DownloadComponentManager.getAppContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return -1;
            }
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: ox */
    public static C10124mb m6763ox(String str) {
        try {
            PackageManager packageManager = DownloadComponentManager.getAppContext().getPackageManager();
            if (packageManager == null) {
                return null;
            }
            return m6765mb(packageManager, packageManager.getPackageInfo(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: mb */
    private static C10124mb m6765mb(PackageManager packageManager, PackageInfo packageInfo) {
        Drawable drawable;
        if (packageInfo == null) {
            return null;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        String str = packageInfo.packageName;
        String charSequence = applicationInfo.loadLabel(packageManager).toString();
        try {
            drawable = applicationInfo.loadIcon(packageManager);
        } catch (Exception unused) {
            drawable = null;
        }
        return new C10124mb(str, charSequence, drawable, applicationInfo.sourceDir, packageInfo.versionName, packageInfo.versionCode, (applicationInfo.flags & 1) != 0);
    }

    /* renamed from: com.ss.android.socialbase.appdownloader.ko$mb */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public static class C10124mb {

        /* renamed from: b */
        private Drawable f19526b;

        /* renamed from: h */
        private String f19527h;

        /* renamed from: hj */
        private String f19528hj;

        /* renamed from: ko */
        private boolean f19529ko;

        /* renamed from: mb */
        private String f19530mb;

        /* renamed from: ox */
        private String f19531ox;

        /* renamed from: u */
        private int f19532u;

        /* renamed from: mb */
        public Drawable m6756mb() {
            return this.f19526b;
        }

        /* renamed from: mb */
        public void m6754mb(Drawable drawable) {
            this.f19526b = drawable;
        }

        /* renamed from: ox */
        public boolean m6751ox() {
            return this.f19529ko;
        }

        /* renamed from: mb */
        public void m6752mb(boolean z) {
            this.f19529ko = z;
        }

        /* renamed from: b */
        public String m6762b() {
            return this.f19530mb;
        }

        /* renamed from: mb */
        public void m6753mb(String str) {
            this.f19530mb = str;
        }

        /* renamed from: hj */
        public String m6759hj() {
            return this.f19531ox;
        }

        /* renamed from: ox */
        public void m6750ox(String str) {
            this.f19531ox = str;
        }

        /* renamed from: h */
        public String m6760h() {
            return this.f19528hj;
        }

        /* renamed from: b */
        public void m6761b(String str) {
            this.f19528hj = str;
        }

        /* renamed from: u */
        public int m6749u() {
            return this.f19532u;
        }

        /* renamed from: mb */
        public void m6755mb(int i) {
            this.f19532u = i;
        }

        /* renamed from: ko */
        public String m6757ko() {
            return this.f19527h;
        }

        /* renamed from: hj */
        public void m6758hj(String str) {
            this.f19527h = str;
        }

        public C10124mb(String str, String str2, Drawable drawable, String str3, String str4, int i, boolean z) {
            m6750ox(str2);
            m6754mb(drawable);
            m6753mb(str);
            m6761b(str3);
            m6758hj(str4);
            m6755mb(i);
            m6752mb(z);
        }

        public String toString() {
            return "{\n  pkg name: " + m6762b() + "\n  app icon: " + m6756mb() + "\n  app name: " + m6759hj() + "\n  app path: " + m6760h() + "\n  app v name: " + m6757ko() + "\n  app v code: " + m6749u() + "\n  is system: " + m6751ox() + "}";
        }
    }

    /* renamed from: b */
    private static boolean m6766b(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
