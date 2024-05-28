package com.baidu.location.p138c;

import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import com.baidu.location.p140e.C2735k;
import java.util.List;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.c.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2710k {

    /* renamed from: a */
    public List<ScanResult> f5634a;

    /* renamed from: b */
    private long f5635b;

    /* renamed from: c */
    private long f5636c;

    /* renamed from: d */
    private boolean f5637d = false;

    /* renamed from: e */
    private boolean f5638e;

    public C2710k(List<ScanResult> list, long j) {
        this.f5634a = null;
        this.f5635b = 0L;
        this.f5636c = 0L;
        this.f5635b = j;
        this.f5634a = list;
        this.f5636c = System.currentTimeMillis();
        try {
            m19134n();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private boolean m19151a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("wpa|wep", 2).matcher(str).find();
    }

    /* renamed from: b */
    private String m19147b(String str) {
        return str != null ? (str.contains("&") || str.contains(";")) ? str.replace("&", "_").replace(";", "_") : str : str;
    }

    /* renamed from: m */
    private int m19135m() {
        List<ScanResult> list = this.f5634a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /*  JADX ERROR: JadxOverflowException in pass: LoopRegionVisitor
        jadx.core.utils.exceptions.JadxOverflowException: LoopRegionVisitor.assignOnlyInLoop endless recursion
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* renamed from: n */
    private void m19134n() {
        /*
            r7 = this;
            int r0 = r7.m19135m()
            r1 = 1
            if (r0 >= r1) goto L8
            return
        L8:
            java.util.List<android.net.wifi.ScanResult> r0 = r7.f5634a
            int r0 = r0.size()
            int r0 = r0 - r1
            r2 = r1
        L10:
            if (r0 < r1) goto L5e
            if (r2 == 0) goto L5e
            r2 = 0
            r3 = r2
        L16:
            if (r2 >= r0) goto L5a
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f5634a
            java.lang.Object r4 = r4.get(r2)
            if (r4 == 0) goto L57
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f5634a
            int r5 = r2 + 1
            java.lang.Object r4 = r4.get(r5)
            if (r4 == 0) goto L57
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f5634a
            java.lang.Object r4 = r4.get(r2)
            android.net.wifi.ScanResult r4 = (android.net.wifi.ScanResult) r4
            int r4 = r4.level
            java.util.List<android.net.wifi.ScanResult> r6 = r7.f5634a
            java.lang.Object r6 = r6.get(r5)
            android.net.wifi.ScanResult r6 = (android.net.wifi.ScanResult) r6
            int r6 = r6.level
            if (r4 >= r6) goto L57
            java.util.List<android.net.wifi.ScanResult> r3 = r7.f5634a
            java.lang.Object r3 = r3.get(r5)
            android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f5634a
            java.lang.Object r6 = r4.get(r2)
            r4.set(r5, r6)
            java.util.List<android.net.wifi.ScanResult> r4 = r7.f5634a
            r4.set(r2, r3)
            r3 = r1
        L57:
            int r2 = r2 + 1
            goto L16
        L5a:
            int r0 = r0 + (-1)
            r2 = r3
            goto L10
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p138c.C2710k.m19134n():void");
    }

    /* renamed from: a */
    public int m19156a() {
        List<ScanResult> list = this.f5634a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: a */
    public String m19155a(int i) {
        return m19154a(i, false, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02a0 A[Catch: Exception -> 0x021e, Error -> 0x0221, TryCatch #0 {Error -> 0x0221, blocks: (B:40:0x0098, B:42:0x00a0, B:46:0x00b0, B:48:0x00ba, B:52:0x00c8, B:56:0x00d8, B:70:0x0108, B:72:0x010d, B:74:0x011f, B:76:0x012b, B:79:0x0143, B:81:0x0162, B:83:0x0168, B:90:0x0185, B:92:0x018b, B:94:0x0197, B:96:0x01a7, B:111:0x0210, B:99:0x01bd, B:101:0x01c5, B:103:0x01d1, B:105:0x01e1, B:108:0x01f5, B:73:0x0117, B:117:0x022f, B:119:0x0237, B:121:0x0241, B:125:0x0250, B:127:0x025d, B:129:0x0269, B:132:0x0281, B:133:0x0296, B:126:0x0257, B:136:0x029b, B:138:0x02a0, B:140:0x02b6, B:144:0x02c7, B:147:0x02e1, B:149:0x02e7, B:151:0x02f8, B:152:0x0310, B:154:0x0316, B:156:0x031e, B:160:0x034c, B:157:0x0329, B:159:0x0338, B:161:0x0353, B:163:0x035c, B:165:0x037c, B:168:0x0389, B:170:0x038e, B:171:0x0398), top: B:181:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x039d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x00de A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007e A[Catch: Exception -> 0x039f, Error -> 0x03a1, TRY_LEAVE, TryCatch #1 {Error -> 0x03a1, blocks: (B:5:0x000d, B:7:0x0028, B:9:0x002e, B:13:0x004d, B:30:0x0076, B:32:0x007e), top: B:183:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010d A[Catch: Exception -> 0x021e, Error -> 0x0221, TryCatch #0 {Error -> 0x0221, blocks: (B:40:0x0098, B:42:0x00a0, B:46:0x00b0, B:48:0x00ba, B:52:0x00c8, B:56:0x00d8, B:70:0x0108, B:72:0x010d, B:74:0x011f, B:76:0x012b, B:79:0x0143, B:81:0x0162, B:83:0x0168, B:90:0x0185, B:92:0x018b, B:94:0x0197, B:96:0x01a7, B:111:0x0210, B:99:0x01bd, B:101:0x01c5, B:103:0x01d1, B:105:0x01e1, B:108:0x01f5, B:73:0x0117, B:117:0x022f, B:119:0x0237, B:121:0x0241, B:125:0x0250, B:127:0x025d, B:129:0x0269, B:132:0x0281, B:133:0x0296, B:126:0x0257, B:136:0x029b, B:138:0x02a0, B:140:0x02b6, B:144:0x02c7, B:147:0x02e1, B:149:0x02e7, B:151:0x02f8, B:152:0x0310, B:154:0x0316, B:156:0x031e, B:160:0x034c, B:157:0x0329, B:159:0x0338, B:161:0x0353, B:163:0x035c, B:165:0x037c, B:168:0x0389, B:170:0x038e, B:171:0x0398), top: B:181:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0117 A[Catch: Exception -> 0x021e, Error -> 0x0221, TryCatch #0 {Error -> 0x0221, blocks: (B:40:0x0098, B:42:0x00a0, B:46:0x00b0, B:48:0x00ba, B:52:0x00c8, B:56:0x00d8, B:70:0x0108, B:72:0x010d, B:74:0x011f, B:76:0x012b, B:79:0x0143, B:81:0x0162, B:83:0x0168, B:90:0x0185, B:92:0x018b, B:94:0x0197, B:96:0x01a7, B:111:0x0210, B:99:0x01bd, B:101:0x01c5, B:103:0x01d1, B:105:0x01e1, B:108:0x01f5, B:73:0x0117, B:117:0x022f, B:119:0x0237, B:121:0x0241, B:125:0x0250, B:127:0x025d, B:129:0x0269, B:132:0x0281, B:133:0x0296, B:126:0x0257, B:136:0x029b, B:138:0x02a0, B:140:0x02b6, B:144:0x02c7, B:147:0x02e1, B:149:0x02e7, B:151:0x02f8, B:152:0x0310, B:154:0x0316, B:156:0x031e, B:160:0x034c, B:157:0x0329, B:159:0x0338, B:161:0x0353, B:163:0x035c, B:165:0x037c, B:168:0x0389, B:170:0x038e, B:171:0x0398), top: B:181:0x0098 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x012b A[Catch: Exception -> 0x021e, Error -> 0x0221, TryCatch #0 {Error -> 0x0221, blocks: (B:40:0x0098, B:42:0x00a0, B:46:0x00b0, B:48:0x00ba, B:52:0x00c8, B:56:0x00d8, B:70:0x0108, B:72:0x010d, B:74:0x011f, B:76:0x012b, B:79:0x0143, B:81:0x0162, B:83:0x0168, B:90:0x0185, B:92:0x018b, B:94:0x0197, B:96:0x01a7, B:111:0x0210, B:99:0x01bd, B:101:0x01c5, B:103:0x01d1, B:105:0x01e1, B:108:0x01f5, B:73:0x0117, B:117:0x022f, B:119:0x0237, B:121:0x0241, B:125:0x0250, B:127:0x025d, B:129:0x0269, B:132:0x0281, B:133:0x0296, B:126:0x0257, B:136:0x029b, B:138:0x02a0, B:140:0x02b6, B:144:0x02c7, B:147:0x02e1, B:149:0x02e7, B:151:0x02f8, B:152:0x0310, B:154:0x0316, B:156:0x031e, B:160:0x034c, B:157:0x0329, B:159:0x0338, B:161:0x0353, B:163:0x035c, B:165:0x037c, B:168:0x0389, B:170:0x038e, B:171:0x0398), top: B:181:0x0098 }] */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m19154a(int r28, boolean r29, boolean r30) {
        /*
            Method dump skipped, instructions count: 931
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p138c.C2710k.m19154a(int, boolean, boolean):java.lang.String");
    }

    /* renamed from: a */
    public boolean m19153a(long j) {
        long j2;
        boolean z;
        List<ScanResult> list;
        long j3;
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                j2 = SystemClock.elapsedRealtimeNanos() / 1000;
            } catch (Error | Exception unused) {
                j2 = 0;
            }
            z = j2 > 0;
        } else {
            z = false;
            j2 = 0;
        }
        if (!z || (list = this.f5634a) == null || list.size() == 0) {
            return false;
        }
        int size = this.f5634a.size();
        if (size > 16) {
            size = 16;
        }
        long j4 = 0;
        long j5 = 0;
        for (int i = 0; i < size; i++) {
            if (this.f5634a.get(i) != null && this.f5634a.get(i).level != 0 && z) {
                try {
                    j3 = (j2 - this.f5634a.get(i).timestamp) / 1000000;
                } catch (Error | Exception unused2) {
                    j3 = 0;
                }
                j4 += j3;
                if (j3 > j5) {
                    j5 = j3;
                }
            }
        }
        return j5 * 1000 > j || (j4 / ((long) size)) * 1000 > j;
    }

    /* renamed from: a */
    public boolean m19152a(C2710k c2710k) {
        List<ScanResult> list = this.f5634a;
        if (list == null || c2710k == null || c2710k.f5634a == null) {
            return false;
        }
        int size = (list.size() < c2710k.f5634a.size() ? this.f5634a : c2710k.f5634a).size();
        for (int i = 0; i < size; i++) {
            if (this.f5634a.get(i) != null) {
                String str = this.f5634a.get(i).BSSID;
                String str2 = c2710k.f5634a.get(i).BSSID;
                if (!TextUtils.isEmpty(str) && !str.equals(str2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: b */
    public String m19150b() {
        try {
            return m19154a(C2735k.f5755N, true, true);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: b */
    public String m19149b(int i) {
        if (i != 0) {
            int i2 = 1;
            if (m19156a() < 1) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(256);
            int size = this.f5634a.size();
            if (size > C2735k.f5755N) {
                size = C2735k.f5755N;
            }
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                if (this.f5634a.get(i4) != null) {
                    if ((i2 & i) != 0 && this.f5634a.get(i4).BSSID != null) {
                        stringBuffer.append(i3 == 0 ? "&ssid=" : "|");
                        stringBuffer.append(this.f5634a.get(i4).BSSID.replace(":", ""));
                        stringBuffer.append(";");
                        stringBuffer.append(m19147b(this.f5634a.get(i4).SSID));
                        i3++;
                    }
                    i2 <<= 1;
                }
            }
            return stringBuffer.toString();
        }
        return null;
    }

    /* renamed from: b */
    public boolean m19148b(C2710k c2710k) {
        List<ScanResult> list = this.f5634a;
        if (list == null || c2710k == null || c2710k.f5634a == null) {
            return false;
        }
        int size = (list.size() < c2710k.f5634a.size() ? this.f5634a : c2710k.f5634a).size();
        for (int i = 0; i < size; i++) {
            if (this.f5634a.get(i) != null) {
                String str = this.f5634a.get(i).BSSID;
                int i2 = this.f5634a.get(i).level;
                String str2 = c2710k.f5634a.get(i).BSSID;
                int i3 = c2710k.f5634a.get(i).level;
                if ((!TextUtils.isEmpty(str) && !str.equals(str2)) || i2 != i3) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: c */
    public String m19146c() {
        try {
            return m19154a(C2735k.f5755N, true, false);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: c */
    public boolean m19145c(C2710k c2710k) {
        return C2711l.m19130a(c2710k, this);
    }

    /* renamed from: d */
    public String m19144d() {
        try {
            return m19155a(15);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: e */
    public boolean m19143e() {
        return m19153a(C2735k.f5789af);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002c  */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long m19142f() {
        /*
            r13 = this;
            java.util.List<android.net.wifi.ScanResult> r0 = r13.f5634a
            r1 = 0
            if (r0 == 0) goto L74
            int r0 = r0.size()
            if (r0 != 0) goto Le
            goto L74
        Le:
            r3 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = 17
            r6 = 0
            if (r0 < r5) goto L27
            long r7 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L20
            r9 = 1000(0x3e8, double:4.94E-321)
            long r7 = r7 / r9
            goto L21
        L20:
            r7 = r1
        L21:
            int r0 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r0 <= 0) goto L28
            r0 = 1
            goto L29
        L27:
            r7 = r1
        L28:
            r0 = r6
        L29:
            if (r0 != 0) goto L2c
            return r1
        L2c:
            java.util.List<android.net.wifi.ScanResult> r5 = r13.f5634a
            int r5 = r5.size()
            r9 = 16
            if (r5 <= r9) goto L37
            r5 = r9
        L37:
            if (r6 >= r5) goto L6a
            java.util.List<android.net.wifi.ScanResult> r9 = r13.f5634a
            java.lang.Object r9 = r9.get(r6)
            if (r9 == 0) goto L67
            java.util.List<android.net.wifi.ScanResult> r9 = r13.f5634a
            java.lang.Object r9 = r9.get(r6)
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9
            int r9 = r9.level
            if (r9 != 0) goto L4e
            goto L67
        L4e:
            if (r0 == 0) goto L67
            java.util.List<android.net.wifi.ScanResult> r9 = r13.f5634a     // Catch: java.lang.Throwable -> L61
            java.lang.Object r9 = r9.get(r6)     // Catch: java.lang.Throwable -> L61
            android.net.wifi.ScanResult r9 = (android.net.wifi.ScanResult) r9     // Catch: java.lang.Throwable -> L61
            long r9 = r9.timestamp     // Catch: java.lang.Throwable -> L61
            long r9 = r7 - r9
            r11 = 1000000(0xf4240, double:4.940656E-318)
            long r9 = r9 / r11
            goto L62
        L61:
            r9 = r1
        L62:
            int r11 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r11 >= 0) goto L67
            r3 = r9
        L67:
            int r6 = r6 + 1
            goto L37
        L6a:
            if (r0 == 0) goto L6d
            goto L6e
        L6d:
            r3 = r1
        L6e:
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 >= 0) goto L73
            goto L74
        L73:
            r1 = r3
        L74:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p138c.C2710k.m19142f():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: g */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long m19141g() {
        /*
            r18 = this;
            r0 = r18
            java.util.List<android.net.wifi.ScanResult> r1 = r0.f5634a
            r2 = 0
            if (r1 == 0) goto L7d
            int r1 = r1.size()
            if (r1 != 0) goto L10
            goto L7d
        L10:
            int r1 = android.os.Build.VERSION.SDK_INT
            r4 = 17
            r5 = 0
            if (r1 < r4) goto L26
            long r6 = android.os.SystemClock.elapsedRealtimeNanos()     // Catch: java.lang.Throwable -> L1f
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            goto L20
        L1f:
            r6 = r2
        L20:
            int r1 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r1 <= 0) goto L27
            r1 = 1
            goto L28
        L26:
            r6 = r2
        L27:
            r1 = r5
        L28:
            if (r1 != 0) goto L2b
            return r2
        L2b:
            java.util.List<android.net.wifi.ScanResult> r4 = r0.f5634a
            int r4 = r4.size()
            r8 = 16
            if (r4 <= r8) goto L36
            r4 = r8
        L36:
            r8 = r2
            r10 = r8
            r12 = r10
        L39:
            r14 = 1
            if (r5 >= r4) goto L74
            java.util.List<android.net.wifi.ScanResult> r2 = r0.f5634a
            java.lang.Object r2 = r2.get(r5)
            if (r2 == 0) goto L6f
            java.util.List<android.net.wifi.ScanResult> r2 = r0.f5634a
            java.lang.Object r2 = r2.get(r5)
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
            int r2 = r2.level
            if (r2 != 0) goto L52
            goto L6f
        L52:
            if (r1 == 0) goto L6f
            java.util.List<android.net.wifi.ScanResult> r2 = r0.f5634a     // Catch: java.lang.Throwable -> L66
            java.lang.Object r2 = r2.get(r5)     // Catch: java.lang.Throwable -> L66
            android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2     // Catch: java.lang.Throwable -> L66
            long r2 = r2.timestamp     // Catch: java.lang.Throwable -> L66
            long r2 = r6 - r2
            r16 = 1000000(0xf4240, double:4.940656E-318)
            long r2 = r2 / r16
            goto L68
        L66:
            r2 = 0
        L68:
            long r12 = r12 + r2
            long r8 = r8 + r14
            int r14 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r14 <= 0) goto L6f
            r10 = r2
        L6f:
            int r5 = r5 + 1
            r2 = 0
            goto L39
        L74:
            int r1 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r1 <= 0) goto L7c
            long r12 = r12 - r10
            long r8 = r8 - r14
            long r10 = r12 / r8
        L7c:
            return r10
        L7d:
            r1 = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p138c.C2710k.m19141g():long");
    }

    /* renamed from: h */
    public int m19140h() {
        int i;
        for (int i2 = 0; i2 < m19156a(); i2++) {
            if (this.f5634a.get(i2) != null && (i = -this.f5634a.get(i2).level) > 0) {
                return i;
            }
        }
        return 0;
    }

    /* renamed from: i */
    public boolean m19139i() {
        return this.f5637d;
    }

    /* renamed from: j */
    public boolean m19138j() {
        return System.currentTimeMillis() - this.f5636c > 0 && System.currentTimeMillis() - this.f5636c < 5000;
    }

    /* renamed from: k */
    public boolean m19137k() {
        return System.currentTimeMillis() - this.f5636c > 0 && System.currentTimeMillis() - this.f5636c < 5000;
    }

    /* renamed from: l */
    public boolean m19136l() {
        return System.currentTimeMillis() - this.f5636c > 0 && System.currentTimeMillis() - this.f5635b < 5000;
    }
}
