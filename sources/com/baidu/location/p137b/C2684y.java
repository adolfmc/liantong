package com.baidu.location.p137b;

import android.location.Location;
import android.os.Build;
import com.baidu.location.p138c.C2697f;
import com.baidu.location.p138c.C2710k;
import com.baidu.location.p138c.C2711l;
import com.baidu.location.p140e.AbstractC2729f;
import com.baidu.location.p140e.C2720a;
import com.baidu.location.p140e.C2725d;
import com.baidu.location.p140e.C2734j;
import com.baidu.location.p140e.C2735k;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.y */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2684y {

    /* renamed from: B */
    private int f5474B;

    /* renamed from: a */
    long f5475a = 0;

    /* renamed from: z */
    private C2685a f5476z;

    /* renamed from: b */
    private static ArrayList<String> f5450b = new ArrayList<>();

    /* renamed from: c */
    private static ArrayList<String> f5451c = new ArrayList<>();

    /* renamed from: d */
    private static ArrayList<String> f5452d = new ArrayList<>();

    /* renamed from: e */
    private static String f5453e = C2734j.f5740a + "/yo.dat";

    /* renamed from: f */
    private static String f5454f = C2734j.f5740a + "/yoh.dat";

    /* renamed from: g */
    private static String f5455g = C2734j.f5740a + "/yom.dat";

    /* renamed from: h */
    private static String f5456h = C2734j.f5740a + "/yol.dat";

    /* renamed from: i */
    private static String f5457i = C2734j.f5740a + "/yor.dat";

    /* renamed from: j */
    private static File f5458j = null;

    /* renamed from: k */
    private static int f5459k = 8;

    /* renamed from: l */
    private static int f5460l = 8;

    /* renamed from: m */
    private static int f5461m = 16;

    /* renamed from: n */
    private static int f5462n = 2048;

    /* renamed from: o */
    private static double f5463o = 0.0d;

    /* renamed from: p */
    private static double f5464p = 0.1d;

    /* renamed from: q */
    private static double f5465q = 30.0d;

    /* renamed from: r */
    private static double f5466r = 100.0d;

    /* renamed from: s */
    private static int f5467s = 0;

    /* renamed from: t */
    private static int f5468t = 64;

    /* renamed from: u */
    private static int f5469u = 128;

    /* renamed from: v */
    private static Location f5470v = null;

    /* renamed from: w */
    private static Location f5471w = null;

    /* renamed from: x */
    private static Location f5472x = null;

    /* renamed from: y */
    private static C2710k f5473y = null;

    /* renamed from: A */
    private static C2684y f5448A = null;

    /* renamed from: C */
    private static long f5449C = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.y$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2685a extends AbstractC2729f {

        /* renamed from: a */
        boolean f5477a = false;

        /* renamed from: b */
        int f5478b = 0;

        /* renamed from: c */
        int f5479c = 0;

        /* renamed from: e */
        private ArrayList<String> f5481e = new ArrayList<>();

        /* renamed from: p */
        private boolean f5482p = true;

        public C2685a() {
            this.f5727j = new HashMap();
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19077a() {
            Map<String, Object> map;
            StringBuilder sb;
            String str;
            if (this.f5478b != 1) {
                this.f5724g = C2735k.m19048d();
            }
            this.f5725h = 2;
            if (this.f5481e != null) {
                for (int i = 0; i < this.f5481e.size(); i++) {
                    if (this.f5478b == 1) {
                        map = this.f5727j;
                        sb = new StringBuilder();
                        str = "cldc[";
                    } else {
                        map = this.f5727j;
                        sb = new StringBuilder();
                        str = "cltr[";
                    }
                    sb.append(str);
                    sb.append(i);
                    sb.append("]");
                    map.put(sb.toString(), this.f5481e.get(i));
                }
                this.f5727j.put("trtm", String.format(Locale.CHINA, "%d", Long.valueOf(System.currentTimeMillis())));
                if (this.f5478b != 1) {
                    this.f5727j.put("qt", "cltrg");
                }
            }
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19074a(boolean z) {
            if (z && this.f5726i != null) {
                ArrayList<String> arrayList = this.f5481e;
                if (arrayList != null) {
                    arrayList.clear();
                }
                try {
                    JSONObject jSONObject = new JSONObject(this.f5726i);
                    if (jSONObject.has("ison") && jSONObject.getInt("ison") == 0) {
                        this.f5482p = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.f5727j != null) {
                this.f5727j.clear();
            }
            this.f5477a = false;
        }

        /* renamed from: b */
        public synchronized void m19279b() {
            ExecutorService m19308c;
            String str;
            String str2;
            if (this.f5477a) {
                return;
            }
            if (f5723o > 4 && this.f5479c < f5723o) {
                this.f5479c++;
                return;
            }
            this.f5479c = 0;
            this.f5477a = true;
            this.f5478b = 0;
            try {
                if (this.f5481e == null || this.f5481e.size() < 1) {
                    if (this.f5481e == null) {
                        this.f5481e = new ArrayList<>();
                    }
                    this.f5478b = 0;
                    int i = 0;
                    while (true) {
                        String str3 = null;
                        String m19288b = this.f5478b < 2 ? C2684y.m19288b() : null;
                        if (m19288b == null && this.f5478b != 1 && this.f5482p) {
                            this.f5478b = 2;
                            try {
                                str3 = C2649k.m19443a();
                            } catch (Exception unused) {
                            }
                        } else {
                            this.f5478b = 1;
                            str3 = m19288b;
                        }
                        if (str3 == null) {
                            break;
                        } else if (!str3.contains("err!")) {
                            this.f5481e.add(str3);
                            i += str3.length();
                            if (i >= C2720a.f5676h) {
                                break;
                            }
                        }
                    }
                }
                if (this.f5481e == null || this.f5481e.size() < 1) {
                    if (this.f5481e != null) {
                        this.f5481e.clear();
                    }
                    this.f5477a = false;
                    return;
                }
                if (this.f5478b != 1) {
                    m19308c = C2678w.m19310a().m19308c();
                    if (m19308c != null) {
                        str2 = C2735k.m19048d();
                        m19076a(m19308c, str2);
                    } else {
                        str = C2735k.m19048d();
                        m19073b(str);
                    }
                } else {
                    m19308c = C2678w.m19310a().m19308c();
                    if (m19308c != null) {
                        str2 = C2725d.f5705c;
                        m19076a(m19308c, str2);
                    } else {
                        str = C2725d.f5705c;
                        m19073b(str);
                    }
                }
            } catch (Exception unused2) {
                if (this.f5481e != null) {
                    this.f5481e.clear();
                }
            }
        }
    }

    private C2684y() {
        String m19039i;
        this.f5476z = null;
        this.f5474B = 0;
        this.f5476z = new C2685a();
        this.f5474B = 0;
        if (Build.VERSION.SDK_INT <= 28 || (m19039i = C2735k.m19039i()) == null) {
            return;
        }
        f5453e = m19039i + "/yo2.dat";
        f5454f = m19039i + "/yoh2.dat";
        f5455g = m19039i + "/yom2.dat";
        f5456h = m19039i + "/yol2.dat";
        f5457i = m19039i + "/yor2.dat";
    }

    /* renamed from: a */
    private static synchronized int m19289a(List<String> list, int i) {
        synchronized (C2684y.class) {
            if (list != null && i <= 256) {
                if (i >= 0) {
                    try {
                        if (f5458j == null) {
                            f5458j = new File(f5453e);
                            if (!f5458j.exists()) {
                                f5458j = null;
                                return -2;
                            }
                        }
                        RandomAccessFile randomAccessFile = new RandomAccessFile(f5458j, "rw");
                        if (randomAccessFile.length() < 1) {
                            randomAccessFile.close();
                            return -3;
                        }
                        long j = i;
                        randomAccessFile.seek(j);
                        int readInt = randomAccessFile.readInt();
                        int readInt2 = randomAccessFile.readInt();
                        int readInt3 = randomAccessFile.readInt();
                        int readInt4 = randomAccessFile.readInt();
                        long readLong = randomAccessFile.readLong();
                        long j2 = readLong;
                        if (m19297a(readInt, readInt2, readInt3, readInt4, readLong)) {
                            int i2 = 1;
                            if (readInt2 >= 1) {
                                byte[] bArr = new byte[f5462n];
                                int i3 = f5459k;
                                while (i3 > 0 && readInt2 > 0) {
                                    byte[] bArr2 = bArr;
                                    long j3 = j2;
                                    randomAccessFile.seek(((((readInt + readInt2) - i2) % readInt3) * readInt4) + j3);
                                    int readInt5 = randomAccessFile.readInt();
                                    if (readInt5 > 0 && readInt5 < readInt4) {
                                        randomAccessFile.read(bArr2, 0, readInt5);
                                        int i4 = readInt5 - 1;
                                        if (bArr2[i4] == 0) {
                                            list.add(new String(bArr2, 0, i4));
                                        }
                                    }
                                    i3--;
                                    readInt2--;
                                    j2 = j3;
                                    bArr = bArr2;
                                    i2 = 1;
                                }
                                randomAccessFile.seek(j);
                                randomAccessFile.writeInt(readInt);
                                randomAccessFile.writeInt(readInt2);
                                randomAccessFile.writeInt(readInt3);
                                randomAccessFile.writeInt(readInt4);
                                randomAccessFile.writeLong(j2);
                                randomAccessFile.close();
                                return f5459k - i3;
                            }
                        }
                        randomAccessFile.close();
                        return -4;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return -5;
                    }
                }
            }
            return -1;
        }
    }

    /* renamed from: a */
    public static synchronized C2684y m19299a() {
        C2684y c2684y;
        synchronized (C2684y.class) {
            if (f5448A == null) {
                f5448A = new C2684y();
            }
            c2684y = f5448A;
        }
        return c2684y;
    }

    /* renamed from: a */
    private static String m19298a(int i) {
        String str;
        ArrayList<String> arrayList;
        String str2 = null;
        if (i == 1) {
            str = f5454f;
            arrayList = f5450b;
        } else if (i == 2) {
            str = f5455g;
            arrayList = f5451c;
        } else {
            if (i == 3) {
                str = f5456h;
            } else if (i != 4) {
                return null;
            } else {
                str = f5457i;
            }
            arrayList = f5452d;
        }
        if (arrayList == null) {
            return null;
        }
        if (arrayList.size() < 1) {
            m19290a(str, arrayList);
        }
        synchronized (C2684y.class) {
            int size = arrayList.size();
            if (size > 0) {
                int i2 = size - 1;
                try {
                    String str3 = arrayList.get(i2);
                    try {
                        arrayList.remove(i2);
                    } catch (Exception unused) {
                    }
                    str2 = str3;
                } catch (Exception unused2) {
                }
            }
        }
        return str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0011, code lost:
        if (r15 != false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x001c, code lost:
        if (r15 != false) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00df A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cc A[EDGE_INSN: B:49:0x00cc->B:40:0x00cc ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m19296a(int r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2684y.m19296a(int, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0171  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m19292a(com.baidu.location.p138c.C2688a r6, com.baidu.location.p138c.C2710k r7, android.location.Location r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2684y.m19292a(com.baidu.location.c.a, com.baidu.location.c.k, android.location.Location, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    private static void m19291a(String str) {
        m19281e(str);
    }

    /* renamed from: a */
    private static boolean m19297a(int i, int i2, int i3, int i4, long j) {
        return i >= 0 && i < i3 && i2 >= 0 && i2 <= i3 && i3 >= 0 && i3 <= 1024 && i4 >= 128 && i4 <= 1024;
    }

    /* renamed from: a */
    private static boolean m19295a(Location location) {
        if (location == null) {
            return false;
        }
        Location location2 = f5471w;
        if (location2 == null || f5470v == null) {
            f5471w = location;
            return true;
        }
        double distanceTo = location.distanceTo(location2);
        return ((double) location.distanceTo(f5470v)) > (((((double) C2735k.f5760S) * distanceTo) * distanceTo) + (((double) C2735k.f5761T) * distanceTo)) + ((double) C2735k.f5762U);
    }

    /* renamed from: a */
    private static boolean m19294a(Location location, C2710k c2710k) {
        boolean z = false;
        if (location != null && c2710k != null && c2710k.f5634a != null && !c2710k.f5634a.isEmpty()) {
            if (c2710k.m19148b(f5473y)) {
                return false;
            }
            z = true;
            if (f5472x == null) {
                f5472x = location;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static boolean m19293a(Location location, boolean z) {
        return C2697f.m19224a(f5470v, location, z);
    }

    /* renamed from: a */
    private static boolean m19290a(String str, List<String> list) {
        File file = new File(str);
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(8L);
                int readInt = randomAccessFile.readInt();
                int readInt2 = randomAccessFile.readInt();
                int readInt3 = randomAccessFile.readInt();
                byte[] bArr = new byte[f5462n];
                int i = f5460l + 1;
                boolean z = false;
                while (i > 0 && readInt2 > 0) {
                    if (readInt2 < readInt3) {
                        readInt3 = 0;
                    }
                    try {
                        randomAccessFile.seek(((readInt2 - 1) * readInt) + 128);
                        int readInt4 = randomAccessFile.readInt();
                        if (readInt4 > 0 && readInt4 < readInt) {
                            randomAccessFile.read(bArr, 0, readInt4);
                            int i2 = readInt4 - 1;
                            if (bArr[i2] == 0) {
                                list.add(0, new String(bArr, 0, i2));
                                z = true;
                            }
                        }
                        i--;
                        readInt2--;
                    } catch (Exception unused) {
                        return z;
                    }
                }
                randomAccessFile.seek(12L);
                randomAccessFile.writeInt(readInt2);
                randomAccessFile.writeInt(readInt3);
                randomAccessFile.close();
                return z;
            } catch (Exception unused2) {
                return false;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static String m19288b() {
        return m19280f();
    }

    /* renamed from: b */
    private static void m19287b(String str) {
        m19281e(str);
    }

    /* renamed from: c */
    private static void m19285c(String str) {
        m19281e(str);
    }

    /* renamed from: d */
    public static void m19284d() {
        f5460l = 0;
        m19296a(1, false);
        m19296a(2, false);
        m19296a(3, false);
        f5460l = 8;
    }

    /* renamed from: d */
    private static void m19283d(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            File file2 = new File(C2734j.f5740a);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!file.createNewFile()) {
                file = null;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(32);
            randomAccessFile.writeInt(2048);
            randomAccessFile.writeInt(5120);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    /* renamed from: e */
    public static String m19282e() {
        File file = new File(f5455g);
        String str = null;
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20L);
                int readInt = randomAccessFile.readInt();
                if (readInt > 128) {
                    String str2 = "&p1=" + readInt;
                    try {
                        randomAccessFile.seek(20L);
                        randomAccessFile.writeInt(0);
                        randomAccessFile.close();
                        return str2;
                    } catch (Exception unused) {
                        str = str2;
                    }
                } else {
                    randomAccessFile.close();
                }
            } catch (Exception unused2) {
            }
        }
        File file2 = new File(f5456h);
        if (file2.exists()) {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
                randomAccessFile2.seek(20L);
                int readInt2 = randomAccessFile2.readInt();
                if (readInt2 > 256) {
                    String str3 = "&p2=" + readInt2;
                    try {
                        randomAccessFile2.seek(20L);
                        randomAccessFile2.writeInt(0);
                        randomAccessFile2.close();
                        return str3;
                    } catch (Exception unused3) {
                        str = str3;
                    }
                } else {
                    randomAccessFile2.close();
                }
            } catch (Exception unused4) {
            }
        }
        File file3 = new File(f5457i);
        if (file3.exists()) {
            try {
                RandomAccessFile randomAccessFile3 = new RandomAccessFile(file3, "rw");
                randomAccessFile3.seek(20L);
                int readInt3 = randomAccessFile3.readInt();
                if (readInt3 > 512) {
                    String str4 = "&p3=" + readInt3;
                    try {
                        randomAccessFile3.seek(20L);
                        randomAccessFile3.writeInt(0);
                        randomAccessFile3.close();
                        return str4;
                    } catch (Exception unused5) {
                        str = str4;
                    }
                } else {
                    randomAccessFile3.close();
                }
            } catch (Exception unused6) {
            }
        }
        return str;
    }

    /* renamed from: e */
    private static synchronized void m19281e(String str) {
        ArrayList<String> arrayList;
        synchronized (C2684y.class) {
            if (str.contains("err!")) {
                return;
            }
            int i = C2735k.f5824p;
            if (i == 1) {
                arrayList = f5450b;
            } else if (i == 2) {
                arrayList = f5451c;
            } else if (i != 3) {
                return;
            } else {
                arrayList = f5452d;
            }
            if (arrayList == null) {
                return;
            }
            if (arrayList.size() <= f5461m) {
                arrayList.add(str);
            }
            if (arrayList.size() >= f5461m) {
                m19296a(i, false);
            }
            while (arrayList.size() > f5461m) {
                arrayList.remove(0);
            }
        }
    }

    /* renamed from: f */
    private static String m19280f() {
        String str = null;
        for (int i = 1; i < 5; i++) {
            str = m19298a(i);
            if (str != null) {
                return str;
            }
        }
        m19289a(f5452d, f5468t);
        if (f5452d.size() > 0) {
            str = f5452d.get(0);
            f5452d.remove(0);
        }
        if (str != null) {
            return str;
        }
        m19289a(f5452d, f5467s);
        if (f5452d.size() > 0) {
            str = f5452d.get(0);
            f5452d.remove(0);
        }
        if (str != null) {
            return str;
        }
        m19289a(f5452d, f5469u);
        if (f5452d.size() > 0) {
            String str2 = f5452d.get(0);
            f5452d.remove(0);
            return str2;
        }
        return str;
    }

    /* renamed from: c */
    public void m19286c() {
        if (C2711l.m19133a().m19117i() && !C2735k.m19056b()) {
            this.f5476z.m19279b();
        }
    }
}
