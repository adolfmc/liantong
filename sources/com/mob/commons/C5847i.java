package com.mob.commons;

import android.text.TextUtils;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.mob.commons.i */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5847i {

    /* renamed from: e */
    private static C5847i f14393e;

    /* renamed from: h */
    private ArrayList<String> f14396h;

    /* renamed from: i */
    private volatile HashMap<String, Long> f14397i;

    /* renamed from: j */
    private ReentrantReadWriteLock f14398j;

    /* renamed from: k */
    private ReentrantReadWriteLock f14399k;

    /* renamed from: c */
    private static final CountDownLatch f14391c = new CountDownLatch(1);

    /* renamed from: d */
    private static final String f14392d = C5855l.m12238a("0067ilinjejeingj");

    /* renamed from: a */
    public static HashMap<String, String> f14389a = new HashMap<>();

    /* renamed from: b */
    private static final ArrayList<String> f14390b = new ArrayList<>(Arrays.asList(C5855l.m12238a("017fl]fkjmYl0hihffmfnhjfefnhjhfFeIgffh"), C5855l.m12238a("017fl3fkjmSl'jhhffmfnhjfefnhjhf.eTgffh"), C5855l.m12238a("017fl%fkjm!l=jghffmfnhjfefnhjhf_eJgffh"), C5855l.m12238a("017fl5fkjmZl%lhhffmfnhjfefnhjhfPeAgffh"), C5855l.m12238a("017flSfkjm+lMjnhffmfnhjfefnhjhf*eUgffh"), C5855l.m12238a("018fl+fkjm0lYhihffhgfhghjfiflUi^hf8e%gffh"), C5855l.m12238a("018flXfkjm;lYjhhffhgfhghjfifl9i>hfUeWgffh"), C5855l.m12238a("018fl@fkjmZl$jghffhgfhghjfifl6iDhf<e.gffh"), C5855l.m12238a("018fl(fkjm_lMlhhffhgfhghjfifl_i hf9e:gffh"), C5855l.m12238a("018fl7fkjmCl)jnhffhgfhghjfiflFi'hf;e>gffh")));

    /* renamed from: l */
    private volatile CountDownLatch f14400l = f14391c;

    /* renamed from: f */
    private volatile HashMap<String, HashMap<String, ArrayList<String>>> f14394f = C5741aa.m12650a().m12611m();

    /* renamed from: g */
    private volatile HashMap<String, String> f14395g = C5741aa.m12650a().m12610n();

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fa: MOVE  (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r47504 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), expected to be less than 12
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    /* renamed from: a */
    public java.lang.String m12269a(java.lang.String r8, java.lang.String r9, java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 1124
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5847i.m12269a(java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    static {
        f14389a.put("gcfg", C5855l.m12238a("016flGfkjmXe_hffefiWk*fkFi?hjhf)eSgffh"));
        f14389a.put("gclg", C5855l.m12238a("016flXfkjmfehffefiTkOfkYiBhjhf(e=gffh"));
        f14389a.put("el", C5855l.m12238a("018fl-fkjmNhLge:e4hffefi9k7fk iDhjhf[eLgffh"));
        f14389a.put("dg", C5855l.m12238a("020'feAh^ffhjjmfeEfkfOhffefi$k1fk[i0hjhfKe%gffh"));
        f14389a.put("dtc", "api-df.dutils.com");
        f14389a.put("tcig", "m.mpl.dutils.com");
        f14389a.put("gdg", "api-gd.dutils.com");
    }

    private C5847i() {
        this.f14396h = C5741aa.m12650a().m12609o();
        ArrayList<String> arrayList = this.f14396h;
        if (arrayList == null || arrayList.isEmpty()) {
            this.f14396h = f14390b;
        }
        this.f14397i = C5741aa.m12650a().m12608p();
        this.f14398j = new ReentrantReadWriteLock();
        this.f14399k = new ReentrantReadWriteLock();
    }

    /* renamed from: a */
    public static C5847i m12275a() {
        if (f14393e == null) {
            synchronized (C5847i.class) {
                if (f14393e == null) {
                    f14393e = new C5847i();
                }
            }
        }
        return f14393e;
    }

    /* renamed from: a */
    public String m12272a(String str) {
        return C5873u.m12181a(m12275a().m12269a(f14392d, str, f14389a.get(str), false));
    }

    /* renamed from: b */
    public void m12267b() {
        if (this.f14400l == f14391c || this.f14400l.getCount() == 0) {
            MobLog.getInstance().m11342d("DM obt start", new Object[0]);
            this.f14400l = new CountDownLatch(1);
            C5892y.f14525c.execute(new RunnableC58481());
            return;
        }
        MobLog.getInstance().m11342d("DM obt abort", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.i$1 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC58481 implements Runnable {
        RunnableC58481() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C5847i c5847i = C5847i.this;
            c5847i.m12268a(c5847i.f14400l, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:115:0x034a A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x026b A[Catch: Throwable -> 0x02d0, TryCatch #9 {Throwable -> 0x031d, blocks: (B:3:0x0009, B:5:0x000d, B:7:0x0015, B:9:0x007b, B:11:0x0081, B:13:0x008d, B:15:0x0097, B:17:0x00a6, B:75:0x023a, B:101:0x02f4, B:102:0x02fa, B:103:0x0300, B:104:0x0306, B:19:0x00ac, B:21:0x00ba, B:23:0x00c0, B:24:0x00cd, B:26:0x00d3, B:28:0x00ec, B:30:0x00f2, B:31:0x00fa, B:33:0x0100, B:35:0x0119, B:37:0x011f, B:38:0x0123, B:40:0x0129, B:42:0x0135, B:43:0x0139, B:45:0x013f, B:46:0x0143, B:48:0x0149, B:49:0x014d, B:57:0x019c, B:58:0x01b7, B:74:0x022f, B:67:0x01e9, B:73:0x022e, B:72:0x0210, B:82:0x0261, B:84:0x026b, B:86:0x0271, B:87:0x027a, B:89:0x0280, B:91:0x028c, B:92:0x0290, B:94:0x0296, B:95:0x02c4), top: B:130:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0280 A[Catch: Throwable -> 0x02d0, TryCatch #9 {Throwable -> 0x031d, blocks: (B:3:0x0009, B:5:0x000d, B:7:0x0015, B:9:0x007b, B:11:0x0081, B:13:0x008d, B:15:0x0097, B:17:0x00a6, B:75:0x023a, B:101:0x02f4, B:102:0x02fa, B:103:0x0300, B:104:0x0306, B:19:0x00ac, B:21:0x00ba, B:23:0x00c0, B:24:0x00cd, B:26:0x00d3, B:28:0x00ec, B:30:0x00f2, B:31:0x00fa, B:33:0x0100, B:35:0x0119, B:37:0x011f, B:38:0x0123, B:40:0x0129, B:42:0x0135, B:43:0x0139, B:45:0x013f, B:46:0x0143, B:48:0x0149, B:49:0x014d, B:57:0x019c, B:58:0x01b7, B:74:0x022f, B:67:0x01e9, B:73:0x022e, B:72:0x0210, B:82:0x0261, B:84:0x026b, B:86:0x0271, B:87:0x027a, B:89:0x0280, B:91:0x028c, B:92:0x0290, B:94:0x0296, B:95:0x02c4), top: B:130:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0296 A[Catch: Throwable -> 0x02d0, TryCatch #9 {Throwable -> 0x031d, blocks: (B:3:0x0009, B:5:0x000d, B:7:0x0015, B:9:0x007b, B:11:0x0081, B:13:0x008d, B:15:0x0097, B:17:0x00a6, B:75:0x023a, B:101:0x02f4, B:102:0x02fa, B:103:0x0300, B:104:0x0306, B:19:0x00ac, B:21:0x00ba, B:23:0x00c0, B:24:0x00cd, B:26:0x00d3, B:28:0x00ec, B:30:0x00f2, B:31:0x00fa, B:33:0x0100, B:35:0x0119, B:37:0x011f, B:38:0x0123, B:40:0x0129, B:42:0x0135, B:43:0x0139, B:45:0x013f, B:46:0x0143, B:48:0x0149, B:49:0x014d, B:57:0x019c, B:58:0x01b7, B:74:0x022f, B:67:0x01e9, B:73:0x022e, B:72:0x0210, B:82:0x0261, B:84:0x026b, B:86:0x0271, B:87:0x027a, B:89:0x0280, B:91:0x028c, B:92:0x0290, B:94:0x0296, B:95:0x02c4), top: B:130:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02c4 A[Catch: Throwable -> 0x02d0, TRY_LEAVE, TryCatch #9 {Throwable -> 0x031d, blocks: (B:3:0x0009, B:5:0x000d, B:7:0x0015, B:9:0x007b, B:11:0x0081, B:13:0x008d, B:15:0x0097, B:17:0x00a6, B:75:0x023a, B:101:0x02f4, B:102:0x02fa, B:103:0x0300, B:104:0x0306, B:19:0x00ac, B:21:0x00ba, B:23:0x00c0, B:24:0x00cd, B:26:0x00d3, B:28:0x00ec, B:30:0x00f2, B:31:0x00fa, B:33:0x0100, B:35:0x0119, B:37:0x011f, B:38:0x0123, B:40:0x0129, B:42:0x0135, B:43:0x0139, B:45:0x013f, B:46:0x0143, B:48:0x0149, B:49:0x014d, B:57:0x019c, B:58:0x01b7, B:74:0x022f, B:67:0x01e9, B:73:0x022e, B:72:0x0210, B:82:0x0261, B:84:0x026b, B:86:0x0271, B:87:0x027a, B:89:0x0280, B:91:0x028c, B:92:0x0290, B:94:0x0296, B:95:0x02c4), top: B:130:0x0009 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m12268a(java.util.concurrent.CountDownLatch r18, int r19) {
        /*
            Method dump skipped, instructions count: 855
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5847i.m12268a(java.util.concurrent.CountDownLatch, int):void");
    }

    /* renamed from: a */
    private boolean m12271a(String str, String str2) {
        NLog mobLog;
        StringBuilder sb;
        Long l;
        boolean z = true;
        try {
            if (this.f14399k.readLock().tryLock(3000L, TimeUnit.MILLISECONDS)) {
                String str3 = str + "_" + str2;
                if (this.f14397i != null && this.f14397i.containsKey(str3) && (l = this.f14397i.get(str3)) != null) {
                    if (System.currentTimeMillis() - l.longValue() < 1800000) {
                        z = false;
                    }
                }
            }
            try {
                this.f14399k.readLock().unlock();
            } catch (Throwable th) {
                th = th;
                mobLog = MobLog.getInstance();
                sb = new StringBuilder();
                sb.append("DM ");
                sb.append(th.getMessage());
                mobLog.m11340d(th, sb.toString(), new Object[0]);
                MobLog.getInstance().m11342d("DM ck dur: " + str + "-" + str2 + ", pass: " + z, new Object[0]);
                return z;
            }
        } catch (Throwable th2) {
            try {
                MobLog.getInstance().m11340d(th2, "DM " + th2.getMessage(), new Object[0]);
                try {
                    this.f14399k.readLock().unlock();
                } catch (Throwable th3) {
                    th = th3;
                    mobLog = MobLog.getInstance();
                    sb = new StringBuilder();
                    sb.append("DM ");
                    sb.append(th.getMessage());
                    mobLog.m11340d(th, sb.toString(), new Object[0]);
                    MobLog.getInstance().m11342d("DM ck dur: " + str + "-" + str2 + ", pass: " + z, new Object[0]);
                    return z;
                }
            } catch (Throwable th4) {
                try {
                    this.f14399k.readLock().unlock();
                } catch (Throwable th5) {
                    MobLog.getInstance().m11340d(th5, "DM " + th5.getMessage(), new Object[0]);
                }
                throw th4;
            }
        }
        MobLog.getInstance().m11342d("DM ck dur: " + str + "-" + str2 + ", pass: " + z, new Object[0]);
        return z;
    }

    /* renamed from: b */
    private boolean m12266b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                InetAddress[] allByName = InetAddress.getAllByName(str);
                if (allByName != null) {
                    for (InetAddress inetAddress : allByName) {
                        if (!m12265c(inetAddress.getHostAddress())) {
                            MobLog.getInstance().m11342d("DM ck ht: " + str + ", fai", new Object[0]);
                            return false;
                        }
                    }
                }
                MobLog.getInstance().m11342d("DM ck ht: " + str + ", suc", new Object[0]);
                return true;
            } catch (Throwable th) {
                MobLog.getInstance().m11340d(th, "DM " + th.getMessage(), new Object[0]);
            }
        }
        MobLog.getInstance().m11342d("DM ck ht: " + str + ", fai_emp|exp", new Object[0]);
        return false;
    }

    /* renamed from: a */
    private boolean m12270a(String str, String str2, String str3) {
        NLog mobLog;
        StringBuilder sb;
        boolean m12266b = m12266b(str3);
        if (m12266b) {
            try {
                if (this.f14399k.writeLock().tryLock(3000L, TimeUnit.MILLISECONDS)) {
                    this.f14397i.put(str + "_" + str2, Long.valueOf(System.currentTimeMillis()));
                    C5741aa.m12650a().m12620e(this.f14397i);
                }
            } catch (Throwable th) {
                try {
                    NLog mobLog2 = MobLog.getInstance();
                    mobLog2.m11340d(th, "DM " + th.getMessage(), new Object[0]);
                    try {
                        this.f14399k.writeLock().unlock();
                    } catch (Throwable th2) {
                        th = th2;
                        mobLog = MobLog.getInstance();
                        sb = new StringBuilder();
                        sb.append("DM ");
                        sb.append(th.getMessage());
                        mobLog.m11340d(th, sb.toString(), new Object[0]);
                        return m12266b;
                    }
                } catch (Throwable th3) {
                    try {
                        this.f14399k.writeLock().unlock();
                    } catch (Throwable th4) {
                        NLog mobLog3 = MobLog.getInstance();
                        mobLog3.m11340d(th4, "DM " + th4.getMessage(), new Object[0]);
                    }
                    throw th3;
                }
            }
            try {
                this.f14399k.writeLock().unlock();
            } catch (Throwable th5) {
                th = th5;
                mobLog = MobLog.getInstance();
                sb = new StringBuilder();
                sb.append("DM ");
                sb.append(th.getMessage());
                mobLog.m11340d(th, sb.toString(), new Object[0]);
                return m12266b;
            }
        }
        return m12266b;
    }

    /* renamed from: c */
    private static boolean m12265c(String str) {
        if (TextUtils.isEmpty(str) || str.equals("127.0.0.1") || str.startsWith("10.") || str.startsWith("192.168")) {
            return false;
        }
        if (str.startsWith("172.")) {
            String[] split = str.split("\\.");
            if (split.length > 1) {
                try {
                    int parseInt = Integer.parseInt(split[1]);
                    return parseInt < 16 || parseInt > 31;
                } catch (Throwable th) {
                    NLog mobLog = MobLog.getInstance();
                    mobLog.m11340d(th, "DM " + th.getMessage(), new Object[0]);
                }
            }
        }
        return true;
    }
}
