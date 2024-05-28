package com.p319ss.android.socialbase.appdownloader.p340u;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.C10123ko;
import com.p319ss.android.socialbase.downloader.constants.DownloadConstants;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.u.hj */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10152hj {

    /* renamed from: b */
    public static String f19623b = null;

    /* renamed from: h */
    private static String f19624h = null;

    /* renamed from: hj */
    private static String f19625hj = "";

    /* renamed from: ko */
    private static String f19626ko = null;

    /* renamed from: mb */
    public static String f19627mb = null;

    /* renamed from: ox */
    public static String f19628ox = "";

    /* renamed from: u */
    private static String f19629u;

    /* renamed from: mb */
    public static boolean m6569mb() {
        return m6568mb("EMUI");
    }

    /* renamed from: ox */
    public static boolean m6565ox() {
        return m6568mb("MIUI");
    }

    /* renamed from: b */
    public static boolean m6580b() {
        return m6568mb("VIVO");
    }

    /* renamed from: hj */
    public static boolean m6577hj() {
        m6571lc();
        return m6568mb(f19627mb);
    }

    /* renamed from: h */
    public static boolean m6578h() {
        return m6568mb("FLYME");
    }

    /* renamed from: u */
    public static boolean m6563u() {
        return m6568mb("SAMSUNG");
    }

    /* renamed from: ko */
    public static String m6572ko() {
        if (f19624h == null) {
            m6568mb("");
        }
        return f19624h;
    }

    /* renamed from: ww */
    public static String m6562ww() {
        if (f19629u == null) {
            m6568mb("");
        }
        return f19629u;
    }

    /* renamed from: lz */
    public static String m6570lz() {
        if (f19623b == null) {
            m6568mb("");
        }
        return f19623b;
    }

    /* renamed from: lc */
    private static void m6571lc() {
        if (TextUtils.isEmpty(f19627mb)) {
            DownloadComponentManager.ensureOPPO();
            f19627mb = DownloadConstants.UPPER_OPPO;
            f19625hj = "ro.build.version." + DownloadConstants.LOWER_OPPO + "rom";
            f19628ox = "com." + DownloadConstants.LOWER_OPPO + ".market";
        }
    }

    /* renamed from: mb */
    public static boolean m6568mb(String str) {
        m6571lc();
        String str2 = f19624h;
        if (str2 != null) {
            return str2.equals(str);
        }
        String m6576hj = m6576hj("ro.miui.ui.version.name");
        f19629u = m6576hj;
        if (!TextUtils.isEmpty(m6576hj)) {
            f19624h = "MIUI";
            f19623b = "com.xiaomi.market";
            f19626ko = f19629u;
        } else {
            String m6576hj2 = m6576hj("ro.build.version.emui");
            f19629u = m6576hj2;
            if (!TextUtils.isEmpty(m6576hj2)) {
                f19624h = "EMUI";
                f19623b = "com.huawei.appmarket";
            } else {
                String m6576hj3 = m6576hj(f19625hj);
                f19629u = m6576hj3;
                if (!TextUtils.isEmpty(m6576hj3)) {
                    f19624h = f19627mb;
                    if (C10123ko.m6764mb(f19628ox) > -1) {
                        f19623b = f19628ox;
                    } else {
                        f19623b = "com.heytap.market";
                    }
                } else {
                    String m6576hj4 = m6576hj("ro.vivo.os.version");
                    f19629u = m6576hj4;
                    if (!TextUtils.isEmpty(m6576hj4)) {
                        f19624h = "VIVO";
                        f19623b = "com.bbk.appstore";
                    } else {
                        String m6576hj5 = m6576hj("ro.smartisan.version");
                        f19629u = m6576hj5;
                        if (!TextUtils.isEmpty(m6576hj5)) {
                            f19624h = "SMARTISAN";
                            f19623b = "com.smartisanos.appstore";
                        } else {
                            String m6576hj6 = m6576hj("ro.gn.sv.version");
                            f19629u = m6576hj6;
                            if (!TextUtils.isEmpty(m6576hj6)) {
                                f19624h = "QIONEE";
                                f19623b = "com.gionee.aora.market";
                            } else {
                                String m6576hj7 = m6576hj("ro.lenovo.lvp.version");
                                f19629u = m6576hj7;
                                if (!TextUtils.isEmpty(m6576hj7)) {
                                    f19624h = "LENOVO";
                                    f19623b = "com.lenovo.leos.appstore";
                                } else if (m6561x().toUpperCase().contains("SAMSUNG")) {
                                    f19624h = "SAMSUNG";
                                    f19623b = "com.sec.android.app.samsungapps";
                                } else if (m6561x().toUpperCase().contains("ZTE")) {
                                    f19624h = "ZTE";
                                    f19623b = "zte.com.market";
                                } else if (m6561x().toUpperCase().contains("NUBIA")) {
                                    f19624h = "NUBIA";
                                    f19623b = "cn.nubia.neostore";
                                } else if (m6574jb().toUpperCase().contains("FLYME")) {
                                    f19624h = "FLYME";
                                    f19623b = "com.meizu.mstore";
                                    f19629u = m6574jb();
                                } else if (m6561x().toUpperCase().contains("ONEPLUS")) {
                                    f19624h = "ONEPLUS";
                                    f19629u = m6576hj("ro.rom.version");
                                    if (C10123ko.m6764mb(f19628ox) > -1) {
                                        f19623b = f19628ox;
                                    } else {
                                        f19623b = "com.heytap.market";
                                    }
                                } else {
                                    f19624h = m6561x().toUpperCase();
                                    f19623b = "";
                                    f19629u = "";
                                }
                            }
                        }
                    }
                }
            }
        }
        return f19624h.equals(str);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: ox */
    public static java.lang.String m6564ox(java.lang.String r6) {
        /*
            r0 = 0
            r1 = 0
            r2 = 1
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L47
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L47
            r4.<init>()     // Catch: java.lang.Throwable -> L47
            java.lang.String r5 = "getprop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L47
            r4.append(r6)     // Catch: java.lang.Throwable -> L47
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L47
            java.lang.Process r6 = r3.exec(r6)     // Catch: java.lang.Throwable -> L47
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L47
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L47
            java.io.InputStream r6 = r6.getInputStream()     // Catch: java.lang.Throwable -> L47
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L47
            r6 = 1024(0x400, float:1.435E-42)
            r3.<init>(r4, r6)     // Catch: java.lang.Throwable -> L47
            java.lang.String r6 = r3.readLine()     // Catch: java.lang.Throwable -> L48
            r3.close()     // Catch: java.lang.Throwable -> L48
            java.io.Closeable[] r0 = new java.io.Closeable[r2]
            r0[r1] = r3
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r0)
            return r6
        L3b:
            r6 = move-exception
            r0 = r3
            goto L3f
        L3e:
            r6 = move-exception
        L3f:
            java.io.Closeable[] r2 = new java.io.Closeable[r2]
            r2[r1] = r0
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r2)
            throw r6
        L47:
            r3 = r0
        L48:
            java.io.Closeable[] r6 = new java.io.Closeable[r2]
            r6[r1] = r3
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.p340u.C10152hj.m6564ox(java.lang.String):java.lang.String");
    }

    /* renamed from: b */
    public static String m6579b(String str) throws Throwable {
        return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
    }

    /* renamed from: hj */
    public static String m6576hj(String str) {
        if (DownloadSetting.getGlobalSettings().optBoolean("enable_reflect_system_properties", true)) {
            try {
                return m6579b(str);
            } catch (Throwable th) {
                th.printStackTrace();
                return m6564ox(str);
            }
        }
        return m6564ox(str);
    }

    @NonNull
    /* renamed from: x */
    public static String m6561x() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER.trim();
    }

    @NonNull
    /* renamed from: jb */
    public static String m6574jb() {
        return Build.DISPLAY == null ? "" : Build.DISPLAY.trim();
    }

    /* renamed from: je */
    public static boolean m6573je() {
        m6575io();
        return "V10".equals(f19626ko);
    }

    /* renamed from: nk */
    public static boolean m6567nk() {
        m6575io();
        return "V11".equals(f19626ko);
    }

    /* renamed from: o */
    public static boolean m6566o() {
        m6575io();
        return "V12".equals(f19626ko);
    }

    /* renamed from: io */
    private static void m6575io() {
        if (f19626ko == null) {
            try {
                f19626ko = m6576hj("ro.miui.ui.version.name");
            } catch (Exception e) {
                e.printStackTrace();
            }
            String str = f19626ko;
            if (str == null) {
                str = "";
            }
            f19626ko = str;
        }
    }
}
