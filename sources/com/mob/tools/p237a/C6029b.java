package com.mob.tools.p237a;

import android.app.ActivityManager;
import android.app.Application;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.net.Proxy;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Environment;
import android.os.LocaleList;
import android.os.Looper;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.NeighboringCellInfo;
import android.text.TextUtils;
import com.mob.commons.C5747b;
import com.mob.commons.C5829d;
import com.mob.commons.C5868q;
import com.mob.commons.C5873u;
import com.mob.commons.CSCenter;
import com.mob.commons.MobMeta;
import com.mob.commons.p230b.C5759b;
import com.mob.tools.C6122c;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6202d;
import com.mob.tools.utils.C6213f;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.NtFetcher;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.mob.tools.a.b */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C6029b {

    /* renamed from: b */
    private static C6029b f14830b;

    /* renamed from: a */
    private Context f14831a;

    /* renamed from: M */
    public boolean m11797M() {
        return false;
    }

    /* renamed from: u */
    public String m11715u() throws Throwable {
        return null;
    }

    /* renamed from: a */
    public static synchronized C6029b m11780a(Context context) {
        C6029b c6029b;
        synchronized (C6029b.class) {
            if (f14830b == null && context != null) {
                f14830b = new C6029b(context);
            }
            c6029b = f14830b;
        }
        return c6029b;
    }

    private C6029b(Context context) {
        this.f14831a = context.getApplicationContext();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r0.charAt(4) == '1') goto L12;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean m11783a() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = com.mob.commons.C5873u.m12172d()     // Catch: java.lang.Throwable -> L23
            if (r0 == 0) goto L20
            int r1 = r0.length()     // Catch: java.lang.Throwable -> L23
            r2 = 5
            if (r1 != r2) goto L20
            r1 = 3
            char r1 = r0.charAt(r1)     // Catch: java.lang.Throwable -> L23
            r2 = 49
            if (r1 == r2) goto L1e
            r1 = 4
            char r0 = r0.charAt(r1)     // Catch: java.lang.Throwable -> L23
            if (r0 != r2) goto L20
        L1e:
            r0 = 1
            goto L21
        L20:
            r0 = 0
        L21:
            monitor-exit(r3)
            return r0
        L23:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11783a():boolean");
    }

    /* renamed from: b */
    public String m11743b() {
        String str = Build.MODEL;
        return !TextUtils.isEmpty(str) ? str.trim() : str;
    }

    /* renamed from: c */
    public String m11738c() {
        return Build.MANUFACTURER;
    }

    /* renamed from: a */
    public String m11777a(String str) {
        return C6034e.m11667a(this.f14831a).m11664a(str);
    }

    /* renamed from: d */
    public String m11736d() {
        try {
            String str = C6031c.m11708a(this.f14831a).m11704d().mo11517l() + "|" + m11732f() + "|" + m11738c() + "|" + m11724l() + "|" + m11725k();
            String m11771a = m11771a(false);
            if (m11771a == null) {
                m11771a = "";
            } else if (m11771a.length() > 16) {
                m11771a = m11771a.substring(0, 16);
            }
            return Data.Base64AES(str, m11771a);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return "";
        }
    }

    /* renamed from: e */
    public String m11734e() {
        return C6031c.m11708a(this.f14831a).m11704d().mo11517l() + "|" + m11732f() + "|" + m11738c() + "|" + m11724l() + "|" + m11725k();
    }

    /* renamed from: f */
    public int m11732f() {
        return Build.VERSION.SDK_INT;
    }

    /* renamed from: g */
    public String m11730g() {
        return Build.VERSION.RELEASE;
    }

    /* renamed from: h */
    public String m11728h() {
        return Locale.getDefault().getLanguage();
    }

    /* renamed from: i */
    public String m11727i() {
        return this.f14831a.getResources().getConfiguration().locale.getLanguage();
    }

    /* renamed from: j */
    public String m11726j() {
        return Locale.getDefault().getCountry();
    }

    /* renamed from: k */
    public String m11725k() {
        int[] screenSize = ResHelper.getScreenSize(this.f14831a);
        if (this.f14831a.getResources().getConfiguration().orientation == 1) {
            return screenSize[0] + "x" + screenSize[1];
        }
        return screenSize[1] + "x" + screenSize[0];
    }

    /* renamed from: l */
    public String m11724l() {
        String simOperator;
        Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("005ig@dc5de"));
        if (systemServiceSafe == null || !C5829d.m12323i()) {
            return "-1";
        }
        if (CSCenter.getInstance().isPhoneStateDataEnable()) {
            simOperator = (String) ReflectHelper.invokeInstanceMethodNoThrow(systemServiceSafe, C5868q.m12203b("014@ddJehOdichcefkKie?ci4ch4dcci"), null, new Object[0]);
        } else {
            simOperator = CSCenter.getInstance().getSimOperator();
        }
        return TextUtils.isEmpty(simOperator) ? "-1" : simOperator;
    }

    /* renamed from: m */
    public String m11723m() {
        String simOperatorName;
        Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("005ig(dcOde"));
        if (systemServiceSafe == null || !C5829d.m12323i()) {
            return null;
        }
        if (CSCenter.getInstance().isPhoneStateDataEnable()) {
            simOperatorName = (String) ReflectHelper.invokeInstanceMethodNoThrow(systemServiceSafe, C5868q.m12203b("018SddNeh%dichcefkFieCci chNdccidgXc'cePe"), null, new Object[0]);
        } else {
            simOperatorName = CSCenter.getInstance().getSimOperatorName();
        }
        if (TextUtils.isEmpty(simOperatorName)) {
            return null;
        }
        return simOperatorName;
    }

    /* renamed from: b */
    public String m11740b(String str) {
        Signature[] m11357b;
        try {
            Object mo11539b = C6031c.m11708a(this.f14831a).m11704d().mo11539b(false, 0, str, 64);
            if (mo11539b == null || (m11357b = C6122c.m11357b(mo11539b, str)) == null || m11357b.length <= 0) {
                return null;
            }
            return Data.MD5(m11357b[0].toByteArray());
        } catch (Exception e) {
            MobLog.getInstance().m11325w(e);
            return null;
        }
    }

    /* renamed from: n */
    public String m11722n() {
        return NtFetcher.getInstance(this.f14831a).getNtType();
    }

    /* renamed from: a */
    public String m11771a(boolean z) {
        String m11750at = m11750at();
        if (!z && (TextUtils.isEmpty(m11750at) || m11750at.length() < 40)) {
            m11750at = m11751as();
        }
        if (!TextUtils.isEmpty(m11750at) && m11750at.length() >= 40) {
            return m11750at.trim();
        }
        String m11748av = m11748av();
        if (!TextUtils.isEmpty(m11748av) && m11748av.length() >= 40) {
            return m11748av.trim();
        }
        if (TextUtils.isEmpty(m11748av) || m11748av.length() < 40) {
            m11748av = m11782a(40);
        }
        if (m11748av != null) {
            String trim = m11748av.trim();
            m11731f(trim);
            return trim;
        }
        return m11748av;
    }

    /* renamed from: as */
    private String m11751as() {
        try {
            String mo11517l = C6031c.m11708a(this.f14831a).m11704d().mo11517l();
            return Data.byteToHex(Data.SHA1(((Object) null) + ":" + ((Object) null) + ":" + mo11517l));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: a */
    public String m11782a(int i) {
        long currentTimeMillis = System.currentTimeMillis() ^ SystemClock.elapsedRealtime();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(currentTimeMillis);
        SecureRandom secureRandom = new SecureRandom();
        for (int i2 = 0; i2 < i; i2++) {
            if (C5868q.m12203b("004bgcTci").equalsIgnoreCase(C5868q.m12203b(secureRandom.nextInt(2) % 2 == 0 ? "004bgc4ci" : "003d5cfce"))) {
                stringBuffer.insert(i2 + 1, (char) (secureRandom.nextInt(26) + 97));
            } else {
                stringBuffer.insert(stringBuffer.length(), secureRandom.nextInt(10));
            }
        }
        return stringBuffer.toString().substring(0, 40);
    }

    /* renamed from: at */
    private String m11750at() {
        HashMap hashMap;
        HashMap<String, Object> m11749au = m11749au();
        if (m11749au == null || (hashMap = (HashMap) m11749au.get(C5868q.m12203b("0109cbLe]ccchBbeXdhIdMdedc"))) == null) {
            return null;
        }
        try {
            return Data.byteToHex(Data.SHA1(((Object) null) + ":" + ((Object) null) + ":" + ((String) hashMap.get(C5868q.m12203b("005GcedccbBef")))));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: au */
    private java.util.HashMap<java.lang.String, java.lang.Object> m11749au() {
        /*
            r8 = this;
            android.content.Context r0 = r8.f14831a
            java.lang.String r1 = "014bXdcceceGk0cbedeg0k:eccbcfchcb"
            java.lang.String r1 = com.mob.commons.C5868q.m12203b(r1)
            r2 = 1
            java.io.File r0 = com.mob.tools.utils.ResHelper.getDataCacheFile(r0, r1, r2)
            boolean r1 = r0.exists()
            r3 = 0
            if (r1 == 0) goto L78
            long r4 = r0.length()
            r6 = 0
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 <= 0) goto L78
            r1 = 0
            r4 = 2
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4d
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L4d
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L3f
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L3f
            java.lang.Object r7 = r6.readObject()     // Catch: java.lang.Throwable -> L4f
            java.util.HashMap r7 = (java.util.HashMap) r7     // Catch: java.lang.Throwable -> L4f
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r1] = r6
            r4[r2] = r5
            com.mob.commons.C5873u.m12179a(r4)
            goto L59
        L3a:
            r0 = move-exception
            r3 = r6
            goto L43
        L3d:
            r0 = move-exception
            goto L43
        L3f:
            r6 = r3
            goto L4f
        L41:
            r0 = move-exception
            r5 = r3
        L43:
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r1] = r3
            r4[r2] = r5
            com.mob.commons.C5873u.m12179a(r4)
            throw r0
        L4d:
            r5 = r3
            r6 = r5
        L4f:
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r1] = r6
            r4[r2] = r5
            com.mob.commons.C5873u.m12179a(r4)
            r7 = r3
        L59:
            if (r7 == 0) goto L61
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L65
        L61:
            java.util.HashMap r7 = r8.m11778a(r0)
        L65:
            boolean r0 = r7.isEmpty()
            if (r0 != 0) goto L78
            java.lang.String r0 = "010@cbUe5ccchUbe'dhRdRdedc"
            java.lang.String r0 = com.mob.commons.C5868q.m12203b(r0)
            java.lang.Object r0 = r7.get(r0)
            java.util.HashMap r0 = (java.util.HashMap) r0
            return r0
        L78:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11749au():java.util.HashMap");
    }

    /* renamed from: a */
    private HashMap<String, Object> m11778a(File file) {
        return m11776a(C6031c.m11708a(this.f14831a).m11704d().mo11517l(), ResHelper.readFromFileNoCompress(file));
    }

    /* renamed from: a */
    private HashMap<String, Object> m11776a(String str, byte[] bArr) {
        try {
            return HashonHelper.fromJson(Data.AES128Decode(str, bArr));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return new HashMap<>();
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: av */
    private java.lang.String m11748av() {
        /*
            r9 = this;
            java.lang.String r0 = r9.m11716t()
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "008YdiFgc!ci)eHdiejhb"
            java.lang.String r2 = com.mob.commons.C5868q.m12203b(r2)
            r1.<init>(r0, r2)
            boolean r0 = r1.exists()
            if (r0 == 0) goto L3d
            java.io.File r0 = new java.io.File
            java.lang.String r2 = "003Keccbck"
            java.lang.String r2 = com.mob.commons.C5868q.m12203b(r2)
            r0.<init>(r1, r2)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L3d
            android.content.Context r1 = r9.f14831a
            java.lang.String r2 = "003Beccbck"
            java.lang.String r2 = com.mob.commons.C5868q.m12203b(r2)
            java.io.File r1 = com.mob.tools.utils.ResHelper.getCacheRootFile(r1, r2)
            if (r1 == 0) goto L3d
            boolean r1 = r0.renameTo(r1)
            if (r1 == 0) goto L3d
            r0.delete()
        L3d:
            android.content.Context r0 = r9.f14831a
            java.lang.String r1 = "003.eccbck"
            java.lang.String r1 = com.mob.commons.C5868q.m12203b(r1)
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r0, r1)
            r1 = 0
            if (r0 == 0) goto L53
            boolean r2 = r0.exists()
            if (r2 != 0) goto L53
            return r1
        L53:
            r2 = 1
            r3 = 0
            r4 = 2
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L86
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L86
            java.io.ObjectInputStream r0 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L7e
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L7e
            java.lang.Object r6 = r0.readObject()     // Catch: java.lang.Throwable -> L77
            if (r6 == 0) goto L70
            boolean r7 = r6 instanceof char[]     // Catch: java.lang.Throwable -> L77
            if (r7 == 0) goto L70
            char[] r6 = (char[]) r6     // Catch: java.lang.Throwable -> L77
            java.lang.String r1 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L77
        L70:
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r3] = r0
            r4[r2] = r5
            goto L96
        L77:
            r6 = move-exception
            goto L89
        L79:
            r0 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L9b
        L7e:
            r6 = move-exception
            r0 = r1
            goto L89
        L81:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L9b
        L86:
            r6 = move-exception
            r0 = r1
            r5 = r0
        L89:
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L9a
            r7.m11341d(r6)     // Catch: java.lang.Throwable -> L9a
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r3] = r0
            r4[r2] = r5
        L96:
            com.mob.commons.C5873u.m12179a(r4)
            return r1
        L9a:
            r1 = move-exception
        L9b:
            java.io.Closeable[] r4 = new java.io.Closeable[r4]
            r4[r3] = r0
            r4[r2] = r5
            com.mob.commons.C5873u.m12179a(r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11748av():java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: f */
    private void m11731f(java.lang.String r7) {
        /*
            r6 = this;
            android.content.Context r0 = r6.f14831a
            java.lang.String r1 = "003Leccbck"
            java.lang.String r1 = com.mob.commons.C5868q.m12203b(r1)
            java.io.File r0 = com.mob.tools.utils.ResHelper.getCacheRootFile(r0, r1)
            if (r0 == 0) goto L17
            boolean r1 = r0.exists()
            if (r1 == 0) goto L17
            r0.delete()
        L17:
            r1 = 0
            r2 = 1
            r3 = 0
            r4 = 2
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L41
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L41
            java.io.ObjectOutputStream r0 = new java.io.ObjectOutputStream     // Catch: java.lang.Throwable -> L3c
            r0.<init>(r5)     // Catch: java.lang.Throwable -> L3c
            char[] r7 = r7.toCharArray()     // Catch: java.lang.Throwable -> L39
            r0.writeObject(r7)     // Catch: java.lang.Throwable -> L39
            r0.flush()     // Catch: java.lang.Throwable -> L39
            java.io.Closeable[] r7 = new java.io.Closeable[r4]
            r7[r3] = r0
            r7[r2] = r5
            goto L50
        L36:
            r7 = move-exception
            r1 = r0
            goto L55
        L39:
            r7 = move-exception
            r1 = r0
            goto L43
        L3c:
            r7 = move-exception
            goto L43
        L3e:
            r7 = move-exception
            r5 = r1
            goto L55
        L41:
            r7 = move-exception
            r5 = r1
        L43:
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L54
            r0.m11341d(r7)     // Catch: java.lang.Throwable -> L54
            java.io.Closeable[] r7 = new java.io.Closeable[r4]
            r7[r3] = r1
            r7[r2] = r5
        L50:
            com.mob.commons.C5873u.m12179a(r7)
            return
        L54:
            r7 = move-exception
        L55:
            java.io.Closeable[] r0 = new java.io.Closeable[r4]
            r0[r3] = r1
            r0[r2] = r5
            com.mob.commons.C5873u.m12179a(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11731f(java.lang.String):void");
    }

    /* renamed from: o */
    public String m11721o() {
        return this.f14831a.getPackageName();
    }

    /* renamed from: p */
    public String m11720p() {
        try {
            ApplicationInfo mo11560ak = C6031c.m11708a(this.f14831a).m11704d().mo11560ak();
            String packageName = C6152DH.SyncMtd.getPackageName();
            String m11358b = C6122c.m11358b(mo11560ak, packageName);
            if (m11358b != null) {
                if (Build.VERSION.SDK_INT < 25 || m11358b.endsWith(".*")) {
                    return m11358b;
                }
                ReflectHelper.importClassNoThrow(m11358b, null);
            }
            int m11356c = C6122c.m11356c(mo11560ak, packageName);
            if (m11356c > 0) {
                return this.f14831a.getString(m11356c);
            }
            return String.valueOf(C6122c.m11354d(mo11560ak, packageName));
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return "";
        }
    }

    /* renamed from: c */
    public String m11737c(String str) {
        ApplicationInfo mo11581a;
        CharSequence m11348g;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (TextUtils.isEmpty(str) || (mo11581a = C6031c.m11708a(this.f14831a).m11704d().mo11581a(str, 1)) == null || (m11348g = C6122c.m11348g(mo11581a, str)) == null) {
                return null;
            }
            return m11348g.toString();
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: q */
    public int m11719q() {
        try {
            int intValue = ((Integer) MobMeta.get(null, C5868q.m12203b("011Xcc>e(ciegchdc@d4fidccb7e"), Integer.class, 0)).intValue();
            if (intValue <= 0) {
                Object mo11539b = C6031c.m11708a(this.f14831a).m11704d().mo11539b(false, 0, m11721o(), 0);
                if (Build.VERSION.SDK_INT >= 28) {
                    return (int) C6122c.m11347g(mo11539b, C6152DH.SyncMtd.getPackageName());
                }
                return C6122c.m11349f(mo11539b, C6152DH.SyncMtd.getPackageName());
            }
            return intValue;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return 0;
        }
    }

    /* renamed from: r */
    public String m11718r() {
        try {
            String str = (String) MobMeta.get(null, C5868q.m12203b("011Ucc%eCciegchdcBdNdgUc@ce+e"), String.class, null);
            return !TextUtils.isEmpty(str) ? str : C6122c.m11355c(C6031c.m11708a(this.f14831a).m11704d().mo11539b(false, 0, m11721o(), 0), C6152DH.SyncMtd.getPackageName());
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return "1.0";
        }
    }

    /* renamed from: a */
    public ArrayList<HashMap<String, String>> m11775a(ArrayList<HashMap<String, String>> arrayList, int i) {
        try {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("DH PD: fabt " + i, new Object[0]);
            ArrayList<HashMap<String, String>> arrayList2 = new ArrayList<>();
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator<HashMap<String, String>> it = arrayList.iterator();
                while (it.hasNext()) {
                    HashMap<String, String> next = it.next();
                    boolean equals = TextUtils.equals("1", next.get(C5868q.m12203b("005Uchegegcjeg")));
                    if (i != 1 || !equals) {
                        if (i != 2 || equals) {
                            HashMap<String, String> hashMap = new HashMap<>(next);
                            hashMap.remove(C5868q.m12203b("0053chegegcjeg"));
                            arrayList2.add(hashMap);
                        }
                    }
                }
            }
            return arrayList2;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return new ArrayList<>();
        }
    }

    /* renamed from: aw */
    private HashMap<String, String> m11747aw() {
        try {
            return (HashMap) ResHelper.readObjectFromFile(ResHelper.getDataCacheFile(this.f14831a, C5868q.m12203b("004.ec=cd@eg")).getAbsolutePath());
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            ResHelper.getDataCacheFile(this.f14831a, C5868q.m12203b("004WecIcd0eg")).delete();
            return null;
        }
    }

    /* renamed from: b */
    private void m11739b(HashMap<String, String> hashMap) {
        if (hashMap != null) {
            ResHelper.saveObjectToFile(ResHelper.getDataCacheFile(this.f14831a, C5868q.m12203b("004Zec;cd;eg")).getAbsolutePath(), hashMap);
        }
    }

    /* renamed from: s */
    public ArrayList<HashMap<String, String>> m11717s() {
        if (CSCenter.getInstance().isAppListDataEnable()) {
            ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
            try {
                if (Build.VERSION.SDK_INT <= 25) {
                    return m11773a(m11746ax());
                }
                ArrayList arrayList2 = (ArrayList) C5747b.m12583a(C5868q.m12203b("004cfDdc3f"), (Object) null);
                if (arrayList2 == null || arrayList2.size() == 0) {
                    arrayList2 = new ArrayList(Arrays.asList("1", "2"));
                }
                for (int i = 0; i < arrayList2.size(); i++) {
                    arrayList = m11742b(Integer.parseInt(String.valueOf(arrayList2.get(i))));
                    if (!arrayList.isEmpty() && arrayList.size() > 1) {
                        return arrayList;
                    }
                }
                return arrayList;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return new ArrayList<>();
            }
        }
        List<PackageInfo> packageInfos = CSCenter.getInstance().getPackageInfos();
        if (packageInfos != null && !packageInfos.isEmpty()) {
            HashMap<String, Object> hashMap = new HashMap<>();
            for (PackageInfo packageInfo : packageInfos) {
                hashMap.put(packageInfo.packageName, packageInfo);
            }
            return m11774a(hashMap);
        }
        return new ArrayList<>();
    }

    /* renamed from: b */
    private ArrayList<HashMap<String, String>> m11742b(int i) {
        Set<String> m11375a;
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        try {
            switch (i) {
                case 1:
                    m11375a = C6117k.m11375a(this.f14831a, 1);
                    break;
                case 2:
                    m11375a = m11745ay();
                    break;
                case 3:
                    m11375a = m11746ax();
                    break;
                case 4:
                    m11375a = C6117k.m11375a(this.f14831a, 4);
                    break;
                case 5:
                    m11375a = m11744az();
                    break;
                default:
                    m11375a = null;
                    break;
            }
            if (m11375a != null && !m11375a.isEmpty()) {
                arrayList = m11773a(m11375a);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("DH PD: ap " + arrayList.size() + " tpe " + i, new Object[0]);
        return arrayList;
    }

    /* renamed from: a */
    private ArrayList<HashMap<String, String>> m11773a(Set<String> set) {
        if (C5829d.m12330b() && set != null && !set.isEmpty()) {
            HashMap<String, Object> hashMap = new HashMap<>();
            for (String str : set) {
                hashMap.put(str, C6031c.m11708a(this.f14831a).m11704d().mo11539b(true, 0, str, 0));
            }
            if (!hashMap.isEmpty()) {
                return m11774a(hashMap);
            }
        }
        return new ArrayList<>();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: ax */
    private java.util.Set<java.lang.String> m11746ax() {
        /*
            Method dump skipped, instructions count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11746ax():java.util.Set");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: ay */
    private java.util.Set<java.lang.String> m11745ay() {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11745ay():java.util.Set");
    }

    /* renamed from: az */
    private Set<String> m11744az() {
        HashSet hashSet = new HashSet();
        if (C5829d.m12330b()) {
            for (int i = 10000; i <= 13000; i++) {
                String[] strArr = (String[]) ReflectHelper.invokeInstanceMethod(this.f14831a.getPackageManager(), "getPackagesForUid", new Object[]{Integer.valueOf(i)}, new Class[]{Integer.TYPE}, null);
                if (strArr != null && !TextUtils.isEmpty(strArr[0]) && !strArr[0].startsWith(C5868q.m12203b("035b7dcceecdddcdcddTfe ecYcdUcbcidcchcbec^h cich7bg'cidcce-ef2chedci,c@cicj"))) {
                    hashSet.add(strArr[0]);
                }
            }
        }
        return hashSet;
    }

    /* renamed from: a */
    public ArrayList<HashMap<String, String>> m11774a(HashMap<String, Object> hashMap) {
        String str;
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        if (C5829d.m12330b()) {
            try {
                PackageManager packageManager = this.f14831a.getPackageManager();
                boolean z = false;
                HashMap<String, String> m11747aw = m11747aw();
                if (hashMap != null && !hashMap.isEmpty()) {
                    for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if (value != null) {
                            HashMap<String, String> hashMap2 = new HashMap<>();
                            ApplicationInfo m11360a = C6122c.m11360a(value, key);
                            if (m11360a != null) {
                                if (m11779a(m11360a)) {
                                    hashMap2.put(C5868q.m12203b("005?chegegcjeg"), "1");
                                } else {
                                    hashMap2.put(C5868q.m12203b("005Xchegegcjeg"), "0");
                                }
                                hashMap2.put(C5868q.m12203b("003i-ckdd"), key);
                                CharSequence charSequence = null;
                                if (m11747aw != null) {
                                    str = m11747aw.get(Data.MD5(key));
                                } else {
                                    m11747aw = new HashMap<>();
                                    str = null;
                                }
                                if (TextUtils.isEmpty(str)) {
                                    try {
                                        try {
                                            charSequence = C6122c.m11348g(m11360a, key);
                                        } catch (Throwable unused) {
                                        }
                                    } catch (Throwable unused2) {
                                        int m11356c = C6122c.m11356c(m11360a, key);
                                        if (m11356c > 0) {
                                            charSequence = packageManager.getText(key, m11356c, m11360a);
                                        }
                                    }
                                    str = charSequence == null ? key : charSequence.toString();
                                    m11747aw.put(Data.MD5(key), str);
                                    z = true;
                                }
                                hashMap2.put(C5868q.m12203b("004dc@ceDe"), str);
                                hashMap2.put(C5868q.m12203b("007Wcc'eTciegchdcYd"), C6122c.m11355c(value, key));
                                hashMap2.put(C5868q.m12203b("006edcWedLfe"), C6122c.m11352e(m11360a, key) ? "1" : "0");
                                hashMap2.put(C5868q.m12203b("016%dechciegLh7dhSdLegChcffWdjchceRe"), String.valueOf(C6122c.m11353d(value, key)));
                                hashMap2.put(C5868q.m12203b("014fcNeg)h?df@iEcb2cheUdjchceEe"), String.valueOf(C6122c.m11351e(value, key)));
                                arrayList.add(hashMap2);
                            }
                        }
                    }
                    if (z) {
                        m11739b(m11747aw);
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private boolean m11779a(ApplicationInfo applicationInfo) {
        return ((applicationInfo.flags & 1) == 1) || ((applicationInfo.flags & 128) != 0);
    }

    /* renamed from: d */
    public boolean m11735d(String str) throws Throwable {
        int checkPermission;
        if (Build.VERSION.SDK_INT >= 23) {
            ReflectHelper.importClassNoThrow(C5868q.m12203b("023cdAcbcidcchcbec2bJdc8dhedh]ecfidc5dhe2dbFh"), null);
            checkPermission = -1;
            Integer num = (Integer) ReflectHelper.invokeInstanceMethodNoThrow(this.f14831a, C5868q.m12203b("019bgeb%ckdi9ef^defj9eZcicechegegchdc^d"), -1, str);
            if (num != null) {
                checkPermission = num.intValue();
            }
        } else {
            checkPermission = this.f14831a.getPackageManager().checkPermission(str, m11721o());
        }
        return checkPermission == 0;
    }

    /* renamed from: t */
    public String m11716t() {
        if (Build.VERSION.SDK_INT >= 29 && C6031c.m11708a(this.f14831a).m11704d().mo11560ak().targetSdkVersion >= 29 && "mounted".equals(Environment.getExternalStorageState())) {
            return this.f14831a.getExternalFilesDir(null).getAbsolutePath();
        }
        return this.f14831a.getFilesDir().getAbsolutePath();
    }

    /* renamed from: a */
    public Object m11781a(int i, int i2, boolean z) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return C6202d.m11087a().m11086a(this.f14831a, i, i2, z);
        }
        MobLog.getInstance().m11342d("glctn can not be called from Main Thread", new Object[0]);
        return null;
    }

    /* renamed from: v */
    public ArrayList<HashMap<String, Object>> m11714v() {
        if (C5829d.m12324h()) {
            try {
                if (!m11735d(C5868q.m12203b("041cd_cbcidcchcbecQie_cicechegegchdcJd;ecdkfififfdidicgfifkdkfgdiffcgebfkfidkdjdhfkdg")) || m11794P()) {
                    return null;
                }
                List arrayList = new ArrayList();
                if (CSCenter.getInstance().isCellLocationDataEnable()) {
                    Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("005ig9dc4de"));
                    if (systemServiceSafe != null) {
                        arrayList = (List) ReflectHelper.invokeInstanceMethod(systemServiceSafe, C5868q.m12203b("022[ddReh'dgJe_chddPgNeddccichIdUddfi'effCdh3dPdedc"), new Object[0]);
                    }
                } else {
                    List<NeighboringCellInfo> neighboringCellInfo = CSCenter.getInstance().getNeighboringCellInfo();
                    if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                        arrayList.addAll(neighboringCellInfo);
                    }
                }
                if (arrayList == null || arrayList.size() <= 0) {
                    return null;
                }
                ArrayList<HashMap<String, Object>> arrayList2 = new ArrayList<>();
                for (Object obj : arrayList) {
                    int intValue = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(obj, C5868q.m12203b("006;ddYeh$fichcb"), new Object[0]), -1)).intValue();
                    int intValue2 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(obj, C5868q.m12203b("006Qdd3eh$eb3cb"), new Object[0]), -1)).intValue();
                    int intValue3 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(obj, C5868q.m12203b("007Rdd:eh*fgegegch"), new Object[0]), -1)).intValue();
                    int intValue4 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(obj, C5868q.m12203b("006BddKehJfjegZb"), new Object[0]), -1)).intValue();
                    int intValue5 = ((Integer) ResHelper.forceCast(ReflectHelper.invokeInstanceMethod(obj, C5868q.m12203b("014]dd=eh@dgPehGeedccickdjcj5ie"), new Object[0]), -1)).intValue();
                    if (intValue != -1 && intValue2 != -1) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put(C5868q.m12203b("004beff"), Integer.valueOf(intValue));
                        hashMap.put(C5868q.m12203b("003fcb"), Integer.valueOf(intValue2));
                        hashMap.put(C5868q.m12203b("004Tciegegch"), Integer.valueOf(intValue3));
                        hashMap.put(C5868q.m12203b("003iOegSb"), Integer.valueOf(intValue4));
                        hashMap.put(C5868q.m12203b("011dehWeedccickdjcj0ie"), Integer.valueOf(intValue5));
                        arrayList2.add(hashMap);
                    }
                }
                if (arrayList2.size() > 0) {
                    return arrayList2;
                }
                return null;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    /* renamed from: w */
    public String m11713w() {
        String m12203b = C5868q.m12203b("0097dfdgejffekdhdgffej");
        UiModeManager uiModeManager = (UiModeManager) C6152DH.SyncMtd.getSystemServiceSafe("uimode");
        if (uiModeManager != null) {
            switch (uiModeManager.getCurrentModeType()) {
                case 1:
                    return C5868q.m12203b("005Sdgfkcgdfdh");
                case 2:
                    return C5868q.m12203b("004Kejffdihb");
                case 3:
                    return C5868q.m12203b("003Rfidkfg");
                case 4:
                    return C5868q.m12203b("010Cdjffebfffhdhdidhfkdg");
                case 5:
                    return C5868q.m12203b("009Adkfjfjebdhdkdgfiff");
                case 6:
                    return C5868q.m12203b("005%fddkdjfiei");
                case 7:
                    return C5868q.m12203b("009Bfhfgeiffdkejdiffdj");
                default:
                    return C5868q.m12203b("009;dfdgejffekdhdgffej");
            }
        }
        return m12203b;
    }

    /* renamed from: x */
    public static Context m11712x() {
        return C5873u.m12188a();
    }

    /* renamed from: y */
    public HashMap<String, Object> m11711y() {
        Object connectionInfo;
        if (C5829d.m12329c()) {
            try {
                if (m11735d(C5868q.m12203b("036cd*cbcidcchcbecIie$cicechegegchdcJd@ecdkfififfdidicgfddhekdhcgdidjdkdjff"))) {
                    if (CSCenter.getInstance().isWifiDataEnable()) {
                        Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("004Beechdech"));
                        connectionInfo = systemServiceSafe != null ? ReflectHelper.invokeInstanceMethod(systemServiceSafe, C5868q.m12203b("017*dd;ehVfidcAddebh>chdcId2dh!d$dedc"), new Object[0]) : null;
                    } else {
                        connectionInfo = CSCenter.getInstance().getConnectionInfo();
                    }
                    if (connectionInfo != null) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("bsmt", (String) ReflectHelper.invokeInstanceMethodNoThrow(connectionInfo, C5868q.m12203b("008'ddJehLehdididhej"), null, new Object[0]));
                        String str = (String) ReflectHelper.invokeInstanceMethodNoThrow(connectionInfo, C5868q.m12203b("007Tdd,ehRdididhej"), null, new Object[0]);
                        hashMap.put("ssmt", str == null ? null : str.replace("\"", ""));
                        try {
                            hashMap.put(C5868q.m12203b("006gQchcbcb^ed"), Boolean.valueOf(((Boolean) ReflectHelper.invokeInstanceMethod(connectionInfo, C5868q.m12203b("013Cdd*ehVeichcbcbBed4dididhej"), new Object[0])).booleanValue()));
                        } catch (Throwable unused) {
                        }
                        try {
                            hashMap.put("spmt", Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(connectionInfo, C5868q.m12203b("012'dd)ehUebchKdPckdiUieeBcb"), new Object[0])).intValue()));
                        } catch (Throwable unused2) {
                        }
                        try {
                            hashMap.put(C5868q.m12203b("009deh?eedccickdhcb"), Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(connectionInfo, C5868q.m12203b("0127dd7ehOdg>eh7eedccickdhcb"), new Object[0])).intValue()));
                        } catch (Throwable unused3) {
                        }
                        try {
                            hashMap.put(C5868q.m12203b("005feRcc5ef"), Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(connectionInfo, C5868q.m12203b("007<dd<eh[fgegegch"), new Object[0])).intValue()));
                        } catch (Throwable unused4) {
                        }
                        try {
                            hashMap.put(C5868q.m12203b("009[deci_eLcdcf5edb9cj"), Integer.valueOf(((Integer) ReflectHelper.invokeInstanceMethod(connectionInfo, C5868q.m12203b("012Fdd>eh3ekciGeHcdcf9edbWcj"), new Object[0])).intValue()));
                        } catch (Throwable unused5) {
                        }
                        return hashMap;
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return null;
    }

    /* renamed from: z */
    public ArrayList<HashMap<String, Object>> m11710z() {
        List list;
        String[] split;
        String[] split2;
        if (C5829d.m12328d()) {
            try {
                if (m11735d(C5868q.m12203b("036cd^cbcidcchcbecSie_cicechegegchdc3d0ecdkfififfdidicgfddhekdhcgdidjdkdjff"))) {
                    if (CSCenter.getInstance().isWifiDataEnable()) {
                        Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("004Deechdech"));
                        if (systemServiceSafe == null) {
                            return null;
                        }
                        list = (List) ReflectHelper.invokeInstanceMethod(systemServiceSafe, C5868q.m12203b("014(ddNeh(diLbcdPfg;e$egcfTfhZeg"), new Object[0]);
                    } else {
                        List<ScanResult> wifiScanResults = CSCenter.getInstance().getWifiScanResults();
                        if (wifiScanResults != null) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.addAll(wifiScanResults);
                            list = arrayList;
                        } else {
                            list = null;
                        }
                    }
                    if (list == null) {
                        return null;
                    }
                    if (Build.VERSION.SDK_INT > 27) {
                        split = C5868q.m12203b("086EdididhejigehdididhejigHbcic2edchPf1ch9h^chCe(egigNfe6cc<efBigdeci7eZcdcf:edb:cjigLbgcddefFfdchcb>hgIigGbedhe=ciekciVeBcdefig*bedhe8ciekci(e%cdgeig*h:chce;eLeg)hc0ce?i").split(",");
                        split2 = C5868q.m12203b("0318cc,ed,cf?eBdg-cUce1e$ighedc1ie+ciVch(dcciekcich1ed3cb0f)cjdg4c5ceZe").split(",");
                    } else {
                        split = "SSID,BSSID,hessid,anqpDomainId,capabilities,level,frequency,channelWidth,centerFreq0,centerFreq1,timestamp,seen,isAutoJoinCandidate,numIpConfigFailures,blackListTimestamp,untrusted,numConnection,numUsage,distanceCm,distanceSdCm,flags".split(",");
                        split2 = C5868q.m12203b("039Weechdechdiegchcbigcc9edFcf e(dg@c7ceIeVigdc8ieYci6ch dcciekcich2ed_cbRf?cjdgUcIce:e").split(",");
                    }
                    ArrayList<HashMap<String, Object>> arrayList2 = new ArrayList<>();
                    for (Object obj : list) {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        int length = split.length;
                        String str = null;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            String trim = split[i].trim();
                            if (C5868q.m12203b("004Xdididhej").equals(trim)) {
                                str = (String) ReflectHelper.getInstanceField(obj, trim, null);
                                if (TextUtils.isEmpty(str)) {
                                    break;
                                }
                                hashMap.put(trim, str);
                                i++;
                            } else {
                                if (C5868q.m12203b("012bcicBedch f[chPhVch_eBeg").equals(trim)) {
                                    String str2 = (String) ReflectHelper.getInstanceField(obj, trim, null);
                                    if (str2 != null && str2.contains("[IBSS]")) {
                                        str = null;
                                        break;
                                    }
                                    hashMap.put(trim, str2);
                                } else {
                                    hashMap.put(trim, ReflectHelper.getInstanceField(obj, trim, null));
                                }
                                i++;
                            }
                        }
                        if (!TextUtils.isEmpty(str)) {
                            for (String str3 : split2) {
                                try {
                                    String trim2 = str3.trim();
                                    Object instanceField = ReflectHelper.getInstanceField(obj, trim2);
                                    hashMap.put(trim2, instanceField == null ? null : instanceField.toString());
                                } catch (Throwable unused) {
                                }
                            }
                            try {
                                hashMap.put(C5868q.m12203b("021QcheggcefgdgegegbVbJfgdjdjfg]eUegNiWdc,d]cb=eCci"), ReflectHelper.invokeInstanceMethod(obj, C5868q.m12203b("018?cheggcefgdgegeceGbNfgYe'eg i2dc^dBcbVe8ci"), new Object[0]));
                            } catch (Throwable unused2) {
                            }
                            try {
                                if (Build.VERSION.SDK_INT < 28) {
                                    List list2 = (List) ReflectHelper.getInstanceField(obj, C5868q.m12203b("009cd0cdSiWebchAde+eg"));
                                    hashMap.put(C5868q.m12203b("009cd-cd(iVebchVde(eg"), list2 == null ? null : new ArrayList(list2));
                                }
                            } catch (Throwable unused3) {
                            }
                            arrayList2.add(hashMap);
                        }
                    }
                    return arrayList2;
                }
            } catch (Throwable th) {
                MobLog.getInstance().m11325w(th);
            }
        }
        return null;
    }

    /* renamed from: A */
    public boolean m11809A() {
        Object systemServiceSafe;
        if (C5829d.m12328d() && CSCenter.getInstance().isWifiDataEnable() && C6152DH.SyncMtd.checkPermission(C5868q.m12203b("036cdEcbcidcchcbec]ie*cicechegegchdc$d+ecfieidkdghcffcgfddhekdhcgdidjdkdjff")) && (systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("004%eechdech"))) != null) {
            return ((Boolean) ReflectHelper.invokeInstanceMethodNoThrow(systemServiceSafe, C5868q.m12203b("009,egAhc?ciThPdi7bcd"), false, new Object[0])).booleanValue();
        }
        return false;
    }

    /* renamed from: e */
    public boolean m11733e(String str) {
        return C6031c.m11708a(this.f14831a).m11704d().mo11573a(true, str, 0) != null;
    }

    /* renamed from: B */
    public HashMap<String, Object> m11808B() {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            FileReader fileReader = new FileReader(C5868q.m12203b("013kiCcidc<bkbi?cfch,d2dedc"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList arrayList = new ArrayList();
            hashMap.put(C5868q.m12203b("010i5cidcDbeMegegdccieg"), arrayList);
            HashMap hashMap2 = null;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (TextUtils.isEmpty(readLine)) {
                    if (hashMap2 != null) {
                        arrayList.add(hashMap2);
                    }
                    hashMap2 = null;
                } else {
                    String trim = readLine.trim();
                    if (trim.startsWith(C5868q.m12203b("009i8cidc8beJegegdcci"))) {
                        if (hashMap2 != null) {
                            arrayList.add(hashMap2);
                        }
                        hashMap2 = new HashMap();
                    }
                    String[] split = trim.split(":");
                    if (split.length > 1) {
                        if (hashMap2 == null) {
                            hashMap.put(split[0].trim(), split[1].trim());
                        } else {
                            hashMap2.put(split[0].trim(), split[1].trim());
                        }
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return hashMap;
    }

    /* renamed from: C */
    public ArrayList<ArrayList<String>> m11807C() {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        if (Build.VERSION.SDK_INT < 28) {
            try {
                FileReader fileReader = new FileReader(C5868q.m12203b("017ki2cidc0bkhh;cj6k!cbcichcc%e>cieg"));
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (!TextUtils.isEmpty(readLine)) {
                        String[] split = readLine.trim().split(" ");
                        if (split.length > 1) {
                            ArrayList<String> arrayList2 = new ArrayList<>();
                            for (String str : split) {
                                if (!TextUtils.isEmpty(str)) {
                                    arrayList2.add(str.trim());
                                }
                            }
                            arrayList.add(arrayList2);
                        }
                    }
                }
                bufferedReader.close();
                fileReader.close();
            } catch (Throwable th) {
                MobLog.getInstance().m11342d(th.getMessage(), new Object[0]);
            }
        }
        return arrayList;
    }

    /* renamed from: D */
    public String m11806D() {
        String m11659a = C6034e.m11667a(this.f14831a).m11659a(C5868q.m12203b("014LcidcecckNeFci1def+eccdSeLcecf"), "0");
        return m11659a == null ? "0" : m11659a;
    }

    /* renamed from: E */
    public HashMap<String, HashMap<String, Long>> m11805E() {
        String[] strArr;
        long availableBlocksLong;
        long freeBlocksLong;
        long blockCountLong;
        long blockSizeLong;
        HashMap<String, HashMap<String, Long>> hashMap = new HashMap<>();
        for (String str : new String[]{C5868q.m12203b("006-egcbObcBcicb"), C5868q.m12203b("004@cb'chc")}) {
            HashMap<String, Long> hashMap2 = new HashMap<>();
            hashMap2.put("available", -1L);
            hashMap2.put(C5868q.m12203b("004Pdeci7ee"), -1L);
            hashMap2.put(C5868q.m12203b("005h<dc(hcf"), -1L);
            hashMap.put(str, hashMap2);
        }
        HashMap hashMap3 = new HashMap();
        String m11716t = m11716t();
        if (m11716t != null) {
            hashMap3.put(C5868q.m12203b("006_egcb4bcOcicb"), new StatFs(m11716t));
        }
        File dataDirectory = Environment.getDataDirectory();
        if (dataDirectory != null) {
            hashMap3.put(C5868q.m12203b("004NcbCchc"), new StatFs(dataDirectory.getPath()));
        }
        for (Map.Entry entry : hashMap3.entrySet()) {
            StatFs statFs = (StatFs) entry.getValue();
            if (Build.VERSION.SDK_INT <= 18) {
                availableBlocksLong = statFs.getAvailableBlocks() * statFs.getBlockSize();
                freeBlocksLong = statFs.getFreeBlocks() * statFs.getBlockSize();
                blockCountLong = statFs.getBlockCount();
                blockSizeLong = statFs.getBlockSize();
            } else {
                availableBlocksLong = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
                freeBlocksLong = statFs.getFreeBlocksLong() * statFs.getBlockSizeLong();
                blockCountLong = statFs.getBlockCountLong();
                blockSizeLong = statFs.getBlockSizeLong();
            }
            HashMap<String, Long> hashMap4 = hashMap.get(entry.getKey());
            hashMap4.put("available", Long.valueOf(availableBlocksLong));
            hashMap4.put(C5868q.m12203b("004[deciBee"), Long.valueOf(freeBlocksLong));
            hashMap4.put(C5868q.m12203b("005hFdcOhcf"), Long.valueOf(blockCountLong * blockSizeLong));
        }
        return hashMap;
    }

    /* renamed from: F */
    public HashMap<String, Long> m11804F() {
        HashMap<String, Long> hashMap = new HashMap<>();
        hashMap.put("available", -1L);
        hashMap.put(C5868q.m12203b("005h)dcDhcf"), -1L);
        hashMap.put(C5868q.m12203b("005:chegebdcee"), -1L);
        hashMap.put(C5868q.m12203b("009hg?ciEe=egWgEdcOfVcb"), -1L);
        Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("008cbh3chccchChTcj"));
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ReflectHelper.invokeInstanceMethodNoThrow(systemServiceSafe, C5868q.m12203b("013)ddCeh.gbSeIcedccicjdh>d:dedc"), null, memoryInfo);
        hashMap.put("available", Long.valueOf(memoryInfo.availMem));
        if (Build.VERSION.SDK_INT >= 16) {
            hashMap.put(C5868q.m12203b("005h%dc(hcf"), Long.valueOf(memoryInfo.totalMem));
        }
        hashMap.put(C5868q.m12203b("005Uchegebdcee"), Long.valueOf(memoryInfo.lowMemory ? 1L : 0L));
        hashMap.put(C5868q.m12203b("009hgMci@e[eg1g<dc@fCcb"), Long.valueOf(memoryInfo.threshold));
        return hashMap;
    }

    /* renamed from: G */
    public String m11803G() {
        return C6213f.m11046a().m11044b();
    }

    /* renamed from: H */
    public boolean m11802H() {
        for (String str : new String[]{C5868q.m12203b("020b$dcceecBh@dcHi[ggdc]gd^eecfecce7cEddchegck"), C5868q.m12203b("024Dchdcecddch$hgEcfedecLg8cfegckcjcbddecce>c4ddchegck"), C5868q.m12203b("032HcbLeKeccidcedccec cd0cbcidcchcbecdbZiHdcegKe0cbecch@d^eg'hcffe<ci"), C5868q.m12203b("028:dcciddecce2eRdceeWbch$ecZe3cbdb;iXdceg,eGcbecce cdcUddEeFci"), C5868q.m12203b("027_cedcRe0ecegYg1chfbcfckcfecci!eHcbchci7ebh1eg(h+dcci?cFdd2e"), C5868q.m12203b("018Rce?e%eceeOeVchegVg+cfecck%e^ciIdef(egcf"), C5868q.m12203b("027$chdcecddch!hg6cfedecccccedgdefgfefecceZcg$dceg+g0dcggdc"), C5868q.m12203b("013Zeidcdcckecfcchcffdcfecfe=i"), "club.youppgd.adhook", C5868q.m12203b("027Zch1b6cfec d6cf2ffihKciecNciif[chegThXcbXehebhMdcci"), C5868q.m12203b("032)chdcecddch)hgQcfedec.gVcfegckcjcbddecceCeCcedccicjcbHehebh8dcci"), C5868q.m12203b("034bHdcceecddchThg]cfedec@bcidhQcichWiFegecckVeGci^def<de)fc(egHgeMci")}) {
            if (C6031c.m11708a(this.f14831a).m11704d().mo11581a(str, 0) != null) {
                return true;
            }
        }
        try {
            throw new Exception("msk");
        } catch (Throwable th) {
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (stackTraceElement.getClassName().contains(C5868q.m12203b("035+cb9e=eccidcedccecIcdQcbcidcchcbecdb iUdceg(eLcbecfe8i_dceg2e+cbehcichcbddOe"))) {
                    return true;
                }
            }
            try {
                try {
                    ClassLoader.getSystemClassLoader().loadClass(C5868q.m12203b("036(cb]e6eccidcedccecScd!cbcidcchcbecdbEi dceg6eHcbecfe4iPdceg;e8cbeiUefieBcieg")).newInstance();
                    try {
                        ClassLoader.getSystemClassLoader().loadClass(C5868q.m12203b("035FcbQe_eccidcedccecAcd^cbcidcchcbecdb]i?dceg]e?cbecfeVi*dcegBe1cbehcichcbddDe")).newInstance();
                        return true;
                    } catch (IllegalAccessException unused) {
                        return true;
                    } catch (InstantiationException unused2) {
                        return true;
                    }
                } catch (IllegalAccessException unused3) {
                    return true;
                } catch (InstantiationException unused4) {
                    return true;
                }
            } catch (Throwable unused5) {
                BufferedReader bufferedReader = null;
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(C5868q.m12203b("006kiJcidcEbk") + Process.myPid() + C5868q.m12203b("005kMceRci4eg")));
                    boolean z = false;
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null || z) {
                                break;
                            }
                            z = readLine.toLowerCase().contains(C5868q.m12203b("006Wdb)i%dceg8e>cb"));
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            try {
                                MobLog.getInstance().m11341d(th);
                                C5873u.m12179a(bufferedReader);
                                return false;
                            } catch (Throwable th3) {
                                C5873u.m12179a(bufferedReader);
                                throw th3;
                            }
                        }
                    }
                    C5873u.m12179a(bufferedReader2);
                    return z;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }
    }

    /* renamed from: I */
    public boolean m11801I() {
        return (this.f14831a.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    /* renamed from: J */
    public boolean m11800J() {
        try {
            return Build.VERSION.SDK_INT >= 17 ? Settings.Secure.getInt(this.f14831a.getContentResolver(), "adb_enabled", 0) > 0 : Settings.Secure.getInt(this.f14831a.getContentResolver(), "adb_enabled", 0) > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: K */
    public boolean m11799K() {
        try {
            return Build.VERSION.SDK_INT >= 17 ? Settings.Secure.getInt(this.f14831a.getContentResolver(), "development_settings_enabled", 0) > 0 : Settings.Secure.getInt(this.f14831a.getContentResolver(), "development_settings_enabled", 0) > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: L */
    public boolean m11798L() {
        Intent m12185a = C5873u.m12185a((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return m12185a != null && m12185a.getIntExtra("plugged", -1) == 2;
    }

    /* renamed from: N */
    public boolean m11796N() {
        ApplicationInfo mo11573a = C6031c.m11708a(this.f14831a).m11704d().mo11573a(false, C6152DH.SyncMtd.getPackageName(), 1);
        return (mo11573a == null || (mo11573a.flags & 2) == 0) ? false : true;
    }

    /* renamed from: O */
    public boolean m11795O() {
        String host;
        int port;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                host = System.getProperty(C5868q.m12203b("014ghhi9ec2i2cidcdbcjeidceg'h"));
                String property = System.getProperty(C5868q.m12203b("014ghhi5ec^i]cidcdbcjfjdcci$h"));
                if (property == null) {
                    property = "-1";
                }
                try {
                    port = Integer.parseInt(property);
                } catch (Throwable unused) {
                    port = -1;
                }
            } else {
                host = Proxy.getHost(this.f14831a);
                port = Proxy.getPort(this.f14831a);
            }
            return (TextUtils.isEmpty(host) || port == -1) ? false : true;
        } catch (Throwable unused2) {
            return false;
        }
    }

    /* renamed from: P */
    public boolean m11794P() {
        return (Build.VERSION.SDK_INT >= 29) && (C6031c.m11708a(this.f14831a).m11704d().mo11560ak().targetSdkVersion >= 29);
    }

    /* renamed from: Q */
    public String m11793Q() {
        try {
            String id = TimeZone.getDefault().getID();
            if (TextUtils.isEmpty(id)) {
                Configuration configuration = new Configuration();
                configuration.setToDefaults();
                Settings.System.getConfiguration(this.f14831a.getContentResolver(), configuration);
                Locale locale = configuration.locale;
                if (locale == null) {
                    locale = Locale.getDefault();
                }
                return Calendar.getInstance(locale).getTimeZone().getID();
            }
            return id;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: R */
    public String m11792R() {
        return C6031c.m11708a(this.f14831a).m11704d().mo11582a(C5868q.m12203b("015[cidcecedcfch.fBcbecde'fcJccdcci"));
    }

    /* renamed from: S */
    public String m11791S() {
        return C6031c.m11708a(this.f14831a).m11704d().mo11582a(C5868q.m12203b("0201ddegceecccPe-ciegchdcQd?ecedLc<eg,eVed?cd*cb"));
    }

    /* renamed from: T */
    public String m11790T() {
        return C6031c.m11708a(this.f14831a).m11704d().mo11582a(C5868q.m12203b("016(cidcecFiCcidccbcf'bh?eceddc(c@cicb"));
    }

    /* renamed from: U */
    public String m11789U() {
        return C6031c.m11708a(this.f14831a).m11704d().mo11582a(C5868q.m12203b("017-cidceceddc>c9cicbec*ifchZdedccice"));
    }

    /* renamed from: V */
    public int m11788V() {
        if (C5829d.m12323i()) {
            return NtFetcher.getInstance(this.f14831a).getDtNtType();
        }
        return -1;
    }

    /* renamed from: W */
    public String m11787W() {
        return Build.BRAND;
    }

    /* renamed from: a */
    public void m11772a(final BlockingQueue<Boolean> blockingQueue) {
        if (C5829d.m12328d() && CSCenter.getInstance().isWifiDataEnable()) {
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.mob.tools.a.b.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    try {
                        C5873u.m12186a(this);
                        if (C5868q.m12203b("029cd=cbcidcchcbec!deh%eceechdechecdifidkdgcgfgffdidfebdjdi").equals(intent.getAction())) {
                            blockingQueue.put(true);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().m11341d(th);
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(C5868q.m12203b("029cdAcbcidcchcbecWdeh-eceechdechecdifidkdgcgfgffdidfebdjdi"));
            C5873u.m12185a(broadcastReceiver, intentFilter);
        }
    }

    /* renamed from: X */
    public boolean m11786X() {
        return m11741b(this.f14831a) != 0;
    }

    /* renamed from: Y */
    public String m11785Y() {
        String str = "";
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                str = Application.getProcessName();
            } else {
                Method declaredMethod = Class.forName(C5868q.m12203b("026cd(cbcidcchcbec>cii+ecdk,bhSchccch3h*cjdjIgOci<ecTcb"), false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(null, new Object[0]);
                if (invoke instanceof String) {
                    str = (String) invoke;
                }
            }
        } catch (Throwable th) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("getProcessName: " + th, new Object[0]);
        }
        return str;
    }

    /* renamed from: Z */
    public long m11784Z() {
        Object mo11539b = C6031c.m11708a(this.f14831a).m11704d().mo11539b(false, 0, m11721o(), 0);
        if (mo11539b != null) {
            return C6122c.m11351e(mo11539b, C6152DH.SyncMtd.getPackageName());
        }
        return 0L;
    }

    /* renamed from: aa */
    public String m11769aa() {
        return Build.DEVICE;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: ab */
    public java.lang.String m11768ab() {
        /*
            r9 = this;
            r0 = 1
            r1 = 2
            r2 = 0
            r3 = 0
            java.lang.String r4 = "021bch_he<kiXcidc_bk!egXef3de%kbJddcidccf(i"
            java.lang.String r4 = com.mob.commons.C5868q.m12203b(r4)     // Catch: java.lang.Throwable -> L5a
            java.lang.Object r4 = com.mob.commons.C5873u.m12173c(r4)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r5 = "014Ndd3eh1dhOdi9cf9hAdi]h9ci3ecQce"
            java.lang.String r5 = com.mob.commons.C5868q.m12203b(r5)     // Catch: java.lang.Throwable -> L52
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L52
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r4, r5, r3, r6)     // Catch: java.lang.Throwable -> L52
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch: java.lang.Throwable -> L52
            if (r5 == 0) goto L35
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L32
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L32
            r7.<init>(r5)     // Catch: java.lang.Throwable -> L32
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L32
            java.lang.String r7 = r6.readLine()     // Catch: java.lang.Throwable -> L2d
            goto L37
        L2d:
            r7 = move-exception
            goto L5e
        L2f:
            r7 = move-exception
            r6 = r3
            goto L7d
        L32:
            r7 = move-exception
            r6 = r3
            goto L5e
        L35:
            r6 = r3
            r7 = r6
        L37:
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r2] = r6
            r1[r0] = r5
            com.mob.commons.C5873u.m12179a(r1)
            if (r4 == 0) goto L4d
            java.lang.String r0 = "0071cb:eWegAhCcidccj"
            java.lang.String r0 = com.mob.commons.C5868q.m12203b(r0)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r4, r0, r3, r1)
        L4d:
            r3 = r7
            goto L7b
        L4f:
            r7 = move-exception
            r5 = r3
            goto L58
        L52:
            r7 = move-exception
            r5 = r3
            goto L5d
        L55:
            r7 = move-exception
            r4 = r3
            r5 = r4
        L58:
            r6 = r5
            goto L7d
        L5a:
            r7 = move-exception
            r4 = r3
            r5 = r4
        L5d:
            r6 = r5
        L5e:
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L7c
            r8.m11341d(r7)     // Catch: java.lang.Throwable -> L7c
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r2] = r6
            r1[r0] = r5
            com.mob.commons.C5873u.m12179a(r1)
            if (r4 == 0) goto L7b
            java.lang.String r0 = "0071cb:eWegAhCcidccj"
            java.lang.String r0 = com.mob.commons.C5868q.m12203b(r0)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r4, r0, r3, r1)
        L7b:
            return r3
        L7c:
            r7 = move-exception
        L7d:
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r2] = r6
            r1[r0] = r5
            com.mob.commons.C5873u.m12179a(r1)
            if (r4 == 0) goto L93
            java.lang.String r0 = "0071cb:eWegAhCcidccj"
            java.lang.String r0 = com.mob.commons.C5868q.m12203b(r0)
            java.lang.Object[] r1 = new java.lang.Object[r2]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r4, r0, r3, r1)
        L93:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11768ab():java.lang.String");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: ac */
    public java.lang.String m11767ac() {
        /*
            r10 = this;
            r0 = 1
            r1 = 2
            r2 = 0
            r3 = 0
            java.lang.String r4 = "017bch^he8ki(cidc2bkbiUcfch-d*dedc"
            java.lang.String r4 = com.mob.commons.C5868q.m12203b(r4)     // Catch: java.lang.Throwable -> L7b
            java.lang.Object r4 = com.mob.commons.C5873u.m12173c(r4)     // Catch: java.lang.Throwable -> L7b
            java.lang.String r5 = "014<dd$eh^dhHdiWcf!hAdiYhJciAec2ce"
            java.lang.String r5 = com.mob.commons.C5868q.m12203b(r5)     // Catch: java.lang.Throwable -> L73
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L73
            java.lang.Object r5 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r4, r5, r2, r6)     // Catch: java.lang.Throwable -> L73
            java.io.InputStream r5 = (java.io.InputStream) r5     // Catch: java.lang.Throwable -> L73
            if (r5 == 0) goto L64
            java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L61
            r6.<init>()     // Catch: java.lang.Throwable -> L61
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L61
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L61
            java.lang.String r9 = "utf-8"
            r8.<init>(r5, r9)     // Catch: java.lang.Throwable -> L61
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L61
        L30:
            java.lang.String r8 = r7.readLine()     // Catch: java.lang.Throwable -> L5c
            if (r8 == 0) goto L3a
            r6.append(r8)     // Catch: java.lang.Throwable -> L5c
            goto L30
        L3a:
            r7.close()     // Catch: java.lang.Throwable -> L5c
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L5c
            java.lang.String r6 = r6.toLowerCase()     // Catch: java.lang.Throwable -> L5c
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r3] = r7
            r1[r0] = r5
            com.mob.commons.C5873u.m12179a(r1)
            if (r4 == 0) goto L5b
            java.lang.String r0 = "007GcbUeAegPhIcidccj"
            java.lang.String r0 = com.mob.commons.C5868q.m12203b(r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r4, r0, r2, r1)
        L5b:
            return r6
        L5c:
            r6 = move-exception
            goto L7f
        L5e:
            r6 = move-exception
            r7 = r2
            goto La0
        L61:
            r6 = move-exception
            r7 = r2
            goto L7f
        L64:
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r3] = r2
            r1[r0] = r5
            com.mob.commons.C5873u.m12179a(r1)
            if (r4 == 0) goto L9c
            goto L91
        L70:
            r6 = move-exception
            r5 = r2
            goto L79
        L73:
            r6 = move-exception
            r5 = r2
            goto L7e
        L76:
            r6 = move-exception
            r4 = r2
            r5 = r4
        L79:
            r7 = r5
            goto La0
        L7b:
            r6 = move-exception
            r4 = r2
            r5 = r4
        L7e:
            r7 = r5
        L7f:
            com.mob.tools.log.NLog r8 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L9f
            r8.m11341d(r6)     // Catch: java.lang.Throwable -> L9f
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r3] = r7
            r1[r0] = r5
            com.mob.commons.C5873u.m12179a(r1)
            if (r4 == 0) goto L9c
        L91:
            java.lang.String r0 = "007GcbUeAegPhIcidccj"
            java.lang.String r0 = com.mob.commons.C5868q.m12203b(r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r4, r0, r2, r1)
        L9c:
            java.lang.String r0 = ""
            return r0
        L9f:
            r6 = move-exception
        La0:
            java.io.Closeable[] r1 = new java.io.Closeable[r1]
            r1[r3] = r7
            r1[r0] = r5
            com.mob.commons.C5873u.m12179a(r1)
            if (r4 == 0) goto Lb6
            java.lang.String r0 = "007GcbUeAegPhIcidccj"
            java.lang.String r0 = com.mob.commons.C5868q.m12203b(r0)
            java.lang.Object[] r1 = new java.lang.Object[r3]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r4, r0, r2, r1)
        Lb6:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11767ac():java.lang.String");
    }

    /* renamed from: ad */
    public String m11766ad() {
        return C5759b.m12532c(this.f14831a);
    }

    /* renamed from: ae */
    public HashMap<String, Object> m11765ae() {
        return C5759b.m12535a(this.f14831a);
    }

    /* renamed from: af */
    public long m11764af() {
        return Build.TIME;
    }

    /* renamed from: ag */
    public double m11763ag() {
        return ResHelper.getScreenInch(this.f14831a);
    }

    /* renamed from: ah */
    public int m11762ah() {
        return ResHelper.getScreenPpi(this.f14831a);
    }

    /* renamed from: ai */
    public boolean m11761ai() {
        return C5868q.m12203b("007+eiSc)cicedcEdAcj").equalsIgnoreCase((String) ReflectHelper.invokeStaticMethodNoThrow(ReflectHelper.importClassNoThrow(C5868q.m12203b("025bYdcceecYg1cf$c=ee5e-checegcjeg?heSceecehcfchIfQcbffdb"), null), C5868q.m12203b("010FddMeh-fkegehciAcd<cb"), null, new Object[0]));
    }

    /* renamed from: aj */
    public String m11760aj() {
        return C6031c.m11708a(this.f14831a).m11704d().mo11582a(C5868q.m12203b("028gCeecgegMbCecedcfchQf:cbecAifch3dedcciceeccc-eHciegchdcDd"));
    }

    /* renamed from: ak */
    public String m11759ak() {
        String str = null;
        try {
            String mo11554aq = C6031c.m11708a(this.f14831a).m11704d().mo11554aq();
            String mo11582a = C6031c.m11708a(this.f14831a).m11704d().mo11582a("ro.build.ver.physical");
            if (!TextUtils.isEmpty(mo11582a) && mo11582a.contains(mo11554aq)) {
                Matcher matcher = Pattern.compile(mo11554aq + "(\\.\\d+)?").matcher(mo11582a);
                while (matcher.find()) {
                    str = matcher.group();
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return str;
    }

    /* renamed from: al */
    public int m11758al() {
        try {
            return Settings.Secure.getInt(this.f14831a.getContentResolver(), C5868q.m12203b("015iIcfciZe:cgcedccbGe4cgegThche"));
        } catch (Settings.SettingNotFoundException unused) {
            return -1;
        }
    }

    /* renamed from: am */
    public int m11757am() {
        try {
            return Settings.Secure.getInt(this.f14831a.getContentResolver(), C5868q.m12203b("024i-cfci:eFcgXedgcdbeGcbcgcedccbVe=cgegEhche"));
        } catch (Settings.SettingNotFoundException unused) {
            return -1;
        }
    }

    /* renamed from: b */
    private int m11741b(Context context) {
        String m11785Y = m11785Y();
        if (TextUtils.isEmpty(m11785Y)) {
            return -1;
        }
        return m11785Y.equals(C6122c.m11350f(C6031c.m11708a(context).m11704d().mo11581a(m11721o(), 0), m11721o())) ? 1 : 0;
    }

    /* renamed from: an */
    public Object m11756an() {
        Object systemServiceSafe;
        if (C5829d.m12324h()) {
            if (CSCenter.getInstance().isCellLocationDataEnable()) {
                if (C6152DH.SyncMtd.checkPermission(C5868q.m12203b("041cdTcbcidcchcbec;ieBcicechegegchdcUdZecdkfififfdidicgfifkdkfgdiffcgebfkfidkdjdhfkdg")) && (systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe(C5868q.m12203b("005ig6dcRde"))) != null) {
                    return ReflectHelper.invokeInstanceMethodNoThrow(systemServiceSafe, C5868q.m12203b("015Qdd1eh%fiZeffKebdc[bch%chdcFd"), null, new Object[0]);
                }
            } else {
                return CSCenter.getInstance().getCellLocation();
            }
        }
        return null;
    }

    /* renamed from: ao */
    public String m11755ao() {
        LocaleList localeList;
        Locale locale;
        if (Build.VERSION.SDK_INT < 33 || (localeList = (LocaleList) ReflectHelper.invokeInstanceMethodNoThrow(C6152DH.SyncMtd.getSystemServiceSafe("locale"), "getApplicationLocales", null, new Object[0])) == null || localeList.isEmpty() || (locale = localeList.get(0)) == null) {
            return null;
        }
        return locale.getLanguage();
    }

    /* renamed from: ap */
    public int m11754ap() {
        if (Build.VERSION.SDK_INT >= 34) {
            try {
                return ((Integer) ReflectHelper.invokeInstanceMethod(this.f14831a.getSystemService(Class.forName("android.app.GrammaticalInflectionManager")), "getApplicationGrammaticalGender", new Object[0])).intValue();
            } catch (Throwable unused) {
                return 0;
            }
        }
        return 0;
    }

    /* renamed from: aq */
    public boolean m11753aq() {
        Closeable[] closeableArr;
        int myPid = Process.myPid();
        String str = "0";
        RandomAccessFile randomAccessFile = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(C5868q.m12203b("006ki@cidcHbk") + myPid + C5868q.m12203b("007k1egGhch!cfeg"), "r");
            while (true) {
                try {
                    String readLine = randomAccessFile2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    String replace = readLine.trim().replace("\t", "").trim().replace(" ", "");
                    if (replace.contains(C5868q.m12203b("010SdjciKcbe_cifjchcbCj"))) {
                        str = replace.substring(10);
                    }
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    try {
                        MobLog.getInstance().m11341d(th);
                        closeableArr = new Closeable[]{randomAccessFile};
                        C5873u.m12179a(closeableArr);
                        if (TextUtils.isEmpty(str)) {
                        }
                        return false;
                    } catch (Throwable th2) {
                        C5873u.m12179a(randomAccessFile);
                        throw th2;
                    }
                }
            }
            closeableArr = new Closeable[]{randomAccessFile2};
        } catch (Throwable th3) {
            th = th3;
        }
        C5873u.m12179a(closeableArr);
        if (!TextUtils.isEmpty(str) || TextUtils.equals("0", str)) {
            return false;
        }
        return m11729g(str);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: g */
    private boolean m11729g(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 224
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6029b.m11729g(java.lang.String):boolean");
    }

    /* renamed from: aA */
    private boolean m11770aA() {
        try {
            return ((Boolean) ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(C5868q.m12203b("016cd-cbcidcchcbecdcegecej5e%edcfdd")), C5868q.m12203b("019.chegejFeOedcfddddTe2cifidcSddebheLcb"), new Object[0])).booleanValue();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: ar */
    public boolean m11752ar() {
        return m11770aA() || m11753aq();
    }
}
