package com.p319ss.android.downloadlib.addownload;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p319ss.android.download.api.config.InterfaceC9792b;
import com.p319ss.android.download.api.config.InterfaceC9793e;
import com.p319ss.android.download.api.config.InterfaceC9794g;
import com.p319ss.android.download.api.config.InterfaceC9796h;
import com.p319ss.android.download.api.config.InterfaceC9797hj;
import com.p319ss.android.download.api.config.InterfaceC9798io;
import com.p319ss.android.download.api.config.InterfaceC9799jb;
import com.p319ss.android.download.api.config.InterfaceC9800je;
import com.p319ss.android.download.api.config.InterfaceC9801ko;
import com.p319ss.android.download.api.config.InterfaceC9803lc;
import com.p319ss.android.download.api.config.InterfaceC9804lz;
import com.p319ss.android.download.api.config.InterfaceC9805m;
import com.p319ss.android.download.api.config.InterfaceC9807nk;
import com.p319ss.android.download.api.config.InterfaceC9808o;
import com.p319ss.android.download.api.config.InterfaceC9809ox;
import com.p319ss.android.download.api.config.InterfaceC9811ww;
import com.p319ss.android.download.api.config.InterfaceC9812x;
import com.p319ss.android.download.api.download.DownloadController;
import com.p319ss.android.download.api.download.DownloadEventConfig;
import com.p319ss.android.download.api.download.DownloadModel;
import com.p319ss.android.download.api.model.C9830mb;
import com.p319ss.android.download.api.p322mb.C9820mb;
import com.p319ss.android.download.api.p322mb.C9824ox;
import com.p319ss.android.download.api.p323ox.InterfaceC9834mb;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10100ww;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.addownload.x */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9940x {

    /* renamed from: b */
    private static InterfaceC9801ko f19156b;

    /* renamed from: df */
    private static InterfaceC9805m f19157df;

    /* renamed from: e */
    private static InterfaceC9808o f19158e;

    /* renamed from: g */
    private static InterfaceC9793e f19159g;

    /* renamed from: gm */
    private static InterfaceC9834mb f19160gm;

    /* renamed from: h */
    private static InterfaceC9800je f19161h;

    /* renamed from: hj */
    private static InterfaceC9792b f19162hj;

    /* renamed from: io */
    private static InterfaceC9794g f19163io;

    /* renamed from: jb */
    private static InterfaceC10100ww f19164jb;

    /* renamed from: je */
    private static InterfaceC9797hj f19165je;

    /* renamed from: ko */
    private static InterfaceC9804lz f19166ko;

    /* renamed from: l */
    private static InterfaceC9807nk f19167l;

    /* renamed from: lc */
    private static InterfaceC9799jb f19168lc;

    /* renamed from: lz */
    private static C9830mb f19169lz;

    /* renamed from: m */
    private static InterfaceC9798io f19170m;

    /* renamed from: mb */
    public static final JSONObject f19171mb = new JSONObject();

    /* renamed from: nk */
    private static InterfaceC9796h f19172nk;

    /* renamed from: o */
    private static InterfaceC9803lc f19173o;

    /* renamed from: ox */
    private static Context f19174ox;

    /* renamed from: u */
    private static InterfaceC9811ww f19175u;

    /* renamed from: ww */
    private static InterfaceC9812x f19176ww;

    /* renamed from: x */
    private static InterfaceC9809ox f19177x;

    @NonNull
    /* renamed from: b */
    public static InterfaceC9800je m7377b() {
        if (f19161h == null) {
            f19161h = new C9820mb();
        }
        return f19161h;
    }

    /* renamed from: df */
    public static boolean m7376df() {
        return (f19156b == null || f19175u == null || f19176ww == null || f19177x == null || f19159g == null) ? false : true;
    }

    @NonNull
    /* renamed from: e */
    public static InterfaceC9793e m7375e() {
        return f19159g;
    }

    /* renamed from: g */
    public static String m7374g() {
        try {
            int i = getContext().getApplicationInfo().targetSdkVersion;
            if (Build.VERSION.SDK_INT < 29 || ((i != 29 || Environment.isExternalStorageLegacy()) && i <= 29)) {
                return Environment.getExternalStorageDirectory().getPath() + File.separator + m7364lz().optString("default_save_dir_name", "ByteDownload");
            }
            return getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Context getContext() {
        Context context = f19174ox;
        if (context != null) {
            return context;
        }
        throw new IllegalArgumentException("Context is null");
    }

    @NonNull
    /* renamed from: gm */
    public static InterfaceC9805m m7373gm() {
        if (f19157df == null) {
            f19157df = new InterfaceC9805m() { // from class: com.ss.android.downloadlib.addownload.x.5
                @Override // com.p319ss.android.download.api.config.InterfaceC9805m
                /* renamed from: mb */
                public void mo7340mb(@Nullable Context context, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig, String str, int i) {
                }
            };
        }
        return f19157df;
    }

    @NonNull
    /* renamed from: h */
    public static InterfaceC9804lz m7372h() {
        if (f19166ko == null) {
            f19166ko = new C9824ox();
        }
        return f19166ko;
    }

    /* renamed from: hj */
    public static InterfaceC9811ww m7371hj() {
        return f19175u;
    }

    /* renamed from: io */
    public static InterfaceC9799jb m7370io() {
        return f19168lc;
    }

    @Nullable
    /* renamed from: jb */
    public static InterfaceC9809ox m7369jb() {
        return f19177x;
    }

    @Nullable
    /* renamed from: je */
    public static InterfaceC9808o m7368je() {
        return f19158e;
    }

    /* renamed from: ko */
    public static InterfaceC9803lc m7367ko() {
        return f19173o;
    }

    /* renamed from: l */
    public static InterfaceC9794g m7366l() {
        return f19163io;
    }

    /* renamed from: lc */
    public static InterfaceC9796h m7365lc() {
        return f19172nk;
    }

    @NonNull
    /* renamed from: lz */
    public static JSONObject m7364lz() {
        InterfaceC9812x interfaceC9812x = f19176ww;
        return (interfaceC9812x == null || interfaceC9812x.mo7930mb() == null) ? f19171mb : f19176ww.mo7930mb();
    }

    @NonNull
    /* renamed from: m */
    public static InterfaceC9834mb m7363m() {
        if (f19160gm == null) {
            f19160gm = new InterfaceC9834mb() { // from class: com.ss.android.downloadlib.addownload.x.4
                @Override // com.p319ss.android.download.api.p323ox.InterfaceC9834mb
                /* renamed from: mb */
                public void mo7282mb(Throwable th, String str) {
                }
            };
        }
        return f19160gm;
    }

    /* renamed from: mb */
    public static InterfaceC9801ko m7362mb() {
        return f19156b;
    }

    /* renamed from: mb */
    public static void m7361mb(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            throw new IllegalArgumentException("Context is null");
        }
        f19174ox = context.getApplicationContext();
    }

    /* renamed from: mb */
    public static void m7360mb(InterfaceC9793e interfaceC9793e) {
        f19159g = interfaceC9793e;
    }

    /* renamed from: mb */
    public static void m7359mb(@NonNull InterfaceC9800je interfaceC9800je) {
        f19161h = interfaceC9800je;
    }

    /* renamed from: mb */
    public static void m7358mb(@NonNull InterfaceC9801ko interfaceC9801ko) {
        f19156b = interfaceC9801ko;
    }

    /* renamed from: mb */
    public static void m7357mb(@NonNull InterfaceC9804lz interfaceC9804lz) {
        f19166ko = interfaceC9804lz;
    }

    /* renamed from: mb */
    public static void m7356mb(@NonNull InterfaceC9809ox interfaceC9809ox) {
        f19177x = interfaceC9809ox;
    }

    /* renamed from: mb */
    public static void m7355mb(@NonNull InterfaceC9811ww interfaceC9811ww) {
        f19175u = interfaceC9811ww;
    }

    /* renamed from: mb */
    public static void m7354mb(@NonNull InterfaceC9812x interfaceC9812x) {
        f19176ww = interfaceC9812x;
    }

    /* renamed from: mb */
    public static void m7353mb(@NonNull C9830mb c9830mb) {
        f19169lz = c9830mb;
    }

    /* renamed from: mb */
    public static void m7352mb(InterfaceC9834mb interfaceC9834mb) {
        f19160gm = interfaceC9834mb;
    }

    /* renamed from: mb */
    public static void m7351mb(String str) {
        C10112hj.m6786x().m6797mb(str);
    }

    /* renamed from: nk */
    public static String m7350nk() {
        return "1.7.0";
    }

    /* renamed from: o */
    public static InterfaceC9797hj m7349o() {
        return f19165je;
    }

    @NonNull
    /* renamed from: ox */
    public static InterfaceC9792b m7348ox() {
        if (f19162hj == null) {
            f19162hj = new InterfaceC9792b() { // from class: com.ss.android.downloadlib.addownload.x.1
                @Override // com.p319ss.android.download.api.config.InterfaceC9792b
                /* renamed from: mb */
                public void mo7343mb(@Nullable Context context, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig) {
                }

                @Override // com.p319ss.android.download.api.config.InterfaceC9792b
                /* renamed from: mb */
                public void mo7342mb(@Nullable Context context, @NonNull DownloadModel downloadModel, @Nullable DownloadController downloadController, @Nullable DownloadEventConfig downloadEventConfig, String str, @NonNull String str2) {
                }
            };
        }
        return f19162hj;
    }

    /* renamed from: ox */
    public static void m7347ox(Context context) {
        if (f19174ox != null || context == null || context.getApplicationContext() == null) {
            return;
        }
        f19174ox = context.getApplicationContext();
    }

    /* renamed from: u */
    public static InterfaceC10100ww m7346u() {
        if (f19164jb == null) {
            f19164jb = new InterfaceC10100ww() { // from class: com.ss.android.downloadlib.addownload.x.2
                @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10100ww
                /* renamed from: mb */
                public void mo6869mb(DownloadInfo downloadInfo, BaseException baseException, int i) {
                }
            };
        }
        return f19164jb;
    }

    @NonNull
    /* renamed from: ww */
    public static InterfaceC9798io m7345ww() {
        if (f19170m == null) {
            f19170m = new InterfaceC9798io() { // from class: com.ss.android.downloadlib.addownload.x.3
                @Override // com.p319ss.android.download.api.config.InterfaceC9798io
                /* renamed from: mb */
                public void mo7341mb(String str, int i, JSONObject jSONObject) {
                }
            };
        }
        return f19170m;
    }

    /* renamed from: x */
    public static InterfaceC9807nk m7344x() {
        return f19167l;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:817)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:856)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:160)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:406)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:204)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:138)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:406)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:204)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:138)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:94)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0030 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String x1672829046072dc(java.lang.String r4) {
        /*
        L0:
            r0 = 73
            r1 = 96
        L4:
            r2 = 0
            switch(r0) {
                case 72: goto L4f;
                case 73: goto L9;
                case 74: goto Lc;
                default: goto L8;
            }
        L8:
            goto L54
        L9:
            switch(r1) {
                case 94: goto L0;
                case 95: goto L10;
                case 96: goto L4f;
                default: goto Lc;
            }
        Lc:
            switch(r1) {
                case 55: goto L39;
                case 56: goto L4f;
                case 57: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L0
        L10:
            r0 = 18
            r1 = 1
            switch(r1) {
                case 60: goto L17;
                case 61: goto L23;
                case 62: goto L30;
                default: goto L16;
            }
        L16:
            goto L4f
        L17:
            int r3 = 0 - r1
            int r3 = r3 * r2
            r2 = 0
            int r2 = r2 * 2
            int r2 = r2 - r1
            int r3 = r3 * r2
            int r3 = r3 % 6
            if (r3 == 0) goto L0
        L23:
            int r2 = 18 - r1
            int r2 = r2 * r0
            r3 = 18
            int r3 = r3 * 2
            int r3 = r3 - r1
            int r2 = r2 * r3
            int r2 = r2 % 6
            if (r2 == 0) goto L4f
        L30:
            r1 = 99
            int r1 = r1 * r1
            int r0 = r0 * r0
            int r0 = r0 * 34
            int r1 = r1 - r0
            r0 = -1
            goto L0
        L39:
            char[] r4 = r4.toCharArray()
        L3d:
            int r0 = r4.length
            if (r2 >= r0) goto L49
            char r0 = r4[r2]
            r0 = r0 ^ r2
            char r0 = (char) r0
            r4[r2] = r0
            int r2 = r2 + 1
            goto L3d
        L49:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4)
            return r0
        L4f:
            r0 = 74
            r1 = 55
            goto L4
        L54:
            r0 = 72
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.downloadlib.addownload.C9940x.x1672829046072dc(java.lang.String):java.lang.String");
    }
}
