package com.baidu.p122b.p124b;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import com.baidu.p122b.C2426h;
import com.baidu.p122b.p123a.C2361d;
import com.baidu.p122b.p123a.C2362e;
import com.baidu.p122b.p124b.AbstractC2372a;
import com.baidu.p122b.p130d.C2416b;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.b.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2384e extends AbstractC2372a {

    /* renamed from: d */
    private Context f4160d;

    /* renamed from: e */
    private C2391f f4161e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.e$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C2385a implements Comparable<C2385a> {

        /* renamed from: a */
        private static final String[] f4162a = {"read0", "read1", "read2", "read3", "access0", "access1", "access2", "access3", "sync0", "sync1", "sync2", "sync3", "open0", "open1", "open2", "open3"};

        /* renamed from: b */
        private final int f4163b;

        private C2385a(int i) {
            this.f4163b = i;
        }

        /* renamed from: a */
        public static C2385a m20317a(byte b, boolean z) {
            int i = b & 255;
            return m20316a(z ? i >> 4 : i & 15);
        }

        /* renamed from: a */
        public static C2385a m20316a(int i) {
            if (i < 0 || i >= 16) {
                throw new IllegalArgumentException("invalid idx " + i);
            }
            return new C2385a(i);
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(C2385a c2385a) {
            return this.f4163b - c2385a.f4163b;
        }

        /* renamed from: a */
        public String m20318a() {
            return f4162a[this.f4163b];
        }

        /* renamed from: b */
        public byte m20314b() {
            return (byte) this.f4163b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.f4163b == ((C2385a) obj).f4163b;
        }

        public int hashCode() {
            return this.f4163b;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.e$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2386b {

        /* renamed from: b */
        private int f4165b = 33;

        /* renamed from: c */
        private C2385a[] f4166c = new C2385a[this.f4165b];

        /* renamed from: d */
        private int f4167d;

        public C2386b() {
        }

        /* renamed from: b */
        private void m20309b(int i) {
            C2385a[] c2385aArr = this.f4166c;
            if (i - c2385aArr.length > 0) {
                int length = c2385aArr.length;
                int i2 = length + (length >> 1);
                if (i2 - i >= 0) {
                    i = i2;
                }
                this.f4166c = (C2385a[]) Arrays.copyOf(this.f4166c, i);
            }
        }

        /* renamed from: a */
        public int m20313a() {
            return this.f4167d;
        }

        /* renamed from: a */
        public C2385a m20312a(int i) {
            if (i < this.f4167d) {
                return this.f4166c[i];
            }
            throw new IndexOutOfBoundsException("idx " + i + " size " + this.f4167d);
        }

        /* renamed from: a */
        public void m20311a(C2385a c2385a) {
            m20309b(this.f4167d + 1);
            C2385a[] c2385aArr = this.f4166c;
            int i = this.f4167d;
            this.f4167d = i + 1;
            c2385aArr[i] = c2385a;
        }

        /* renamed from: b */
        public byte[] m20310b() {
            int i;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i2 = 0;
            while (true) {
                i = this.f4167d;
                if (i2 >= i / 2) {
                    break;
                }
                int i3 = i2 * 2;
                byteArrayOutputStream.write((byte) (((m20312a(i3 + 1).m20314b() & 255) << 4) | (m20312a(i3).m20314b() & 255)));
                i2++;
            }
            if (i % 2 != 0) {
                byteArrayOutputStream.write((byte) (m20312a(i - 1).m20314b() & 255));
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.e$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2387c {

        /* renamed from: a */
        private List<C2388a> f4168a = new ArrayList();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.baidu.b.b.e$c$a */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class C2388a {

            /* renamed from: a */
            private int f4169a;

            /* renamed from: b */
            private C2385a f4170b;

            public C2388a(C2385a c2385a) {
                this.f4170b = c2385a;
            }

            /* renamed from: a */
            public void m20306a() {
                this.f4169a++;
            }
        }

        C2387c() {
        }

        /* renamed from: a */
        public List<C2388a> m20308a() {
            ArrayList arrayList = new ArrayList(this.f4168a);
            Collections.sort(arrayList, new C2392f(this));
            return arrayList;
        }

        /* renamed from: a */
        public void m20307a(C2385a c2385a) {
            this.f4168a.add(new C2388a(c2385a));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.e$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2389d {

        /* renamed from: a */
        byte[] f4171a;

        /* renamed from: b */
        byte f4172b;

        /* renamed from: c */
        byte[] f4173c;

        public C2389d(byte[] bArr, byte b, byte[] bArr2) {
            this.f4171a = bArr;
            this.f4172b = b;
            this.f4173c = bArr2;
        }

        /* renamed from: a */
        public C2426h.C2427a m20303a() {
            try {
                return C2426h.m20163a(C2416b.m20210a(this.f4171a, "", true), new String(new byte[]{this.f4172b}, "UTF-8"), this.f4173c != null ? new String(this.f4173c, "UTF-8") : null);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.e$e */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2390e {

        /* renamed from: a */
        public int f4174a;

        /* renamed from: b */
        public int f4175b;

        /* renamed from: c */
        public int f4176c = 16;

        C2390e() {
        }

        public String toString() {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.e$f */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2391f {

        /* renamed from: a */
        private Method f4177a;

        /* renamed from: b */
        private Method f4178b;

        /* renamed from: c */
        private Method f4179c;

        /* renamed from: d */
        private Method f4180d;

        /* renamed from: e */
        private Method f4181e;

        C2391f() {
        }

        /* renamed from: a */
        public int m20301a(Context context, Uri uri, int i, int i2, int i3) {
            try {
                return ((Integer) this.f4177a.invoke(context, uri, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3))).intValue();
            } catch (Exception e) {
                throw new C2362e.C2363a(e);
            }
        }

        /* renamed from: a */
        void m20302a() {
            try {
                this.f4177a = C2362e.m20375a(Context.class, C2362e.m20374a(C2361d.m20380d()), new Class[]{Uri.class, Integer.TYPE, Integer.TYPE, Integer.TYPE});
                this.f4178b = C2362e.m20375a(Context.class, C2362e.m20374a(C2361d.m20379e()), new Class[]{String.class, Uri.class, Integer.TYPE});
                this.f4179c = C2362e.m20375a(ContentResolver.class, C2362e.m20374a(C2361d.m20378f()), new Class[]{Uri.class, Integer.TYPE});
                this.f4180d = C2362e.m20375a(Context.class, C2362e.m20374a(C2361d.m20377g()), new Class[]{Uri.class, Integer.TYPE});
                this.f4181e = C2362e.m20375a(ContentResolver.class, C2362e.m20374a(C2361d.m20376h()), new Class[]{Uri.class, Integer.TYPE});
            } catch (Exception unused) {
            }
        }
    }

    public C2384e() {
        super("upc", 9000000L);
        this.f4161e = new C2391f();
        this.f4161e.m20302a();
    }

    /* renamed from: a */
    private C2385a m20322a(String str, int i, List<C2387c.C2388a> list, int i2, C2390e c2390e) {
        for (C2387c.C2388a c2388a : list) {
            if (m20323a(str, i, c2388a.f4170b, i2, c2390e)) {
                c2388a.m20306a();
                return c2388a.f4170b;
            }
        }
        return null;
    }

    /* renamed from: a */
    private String m20325a(String str) {
        return str + ".cesium";
    }

    /* renamed from: a */
    private String m20324a(String str, int i, C2385a c2385a) {
        return String.format("content://%s/dat/v1/i%d/%s", m20325a(str), Integer.valueOf(i), c2385a.m20318a());
    }

    /* renamed from: a */
    private String m20320a(String str, C2385a c2385a) {
        return String.format("content://%s/dic/v1/%s", m20325a(str), c2385a.m20318a());
    }

    /* renamed from: a */
    private boolean m20323a(String str, int i, C2385a c2385a, int i2, C2390e c2390e) {
        int i3;
        Uri parse = Uri.parse(m20324a(str, i, c2385a));
        int i4 = 0;
        while (true) {
            if (i4 >= 2) {
                i3 = -1;
                break;
            }
            if (c2390e != null) {
                try {
                    c2390e.f4174a++;
                } catch (Throwable unused) {
                    try {
                        Thread.sleep(5L);
                    } catch (Exception unused2) {
                    }
                    i4++;
                }
            }
            i3 = this.f4161e.m20301a(this.f4160d, parse, 0, i2, 1);
            break;
        }
        if (i3 == 0) {
            return true;
        }
        if (c2390e != null) {
            c2390e.f4175b++;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m20319a(String str, C2385a c2385a, int i) {
        int i2;
        Uri parse = Uri.parse(m20320a(str, c2385a));
        int i3 = 0;
        while (true) {
            if (i3 >= 2) {
                i2 = -1;
                break;
            }
            try {
                i2 = this.f4161e.m20301a(this.f4160d, parse, 0, i, 1);
                break;
            } catch (Throwable unused) {
                try {
                    Thread.sleep(5L);
                } catch (Exception unused2) {
                }
                i3++;
            }
        }
        return i2 == 0;
    }

    @Override // com.baidu.p122b.p124b.AbstractC2372a
    /* renamed from: a */
    public AbstractC2372a.C2377e mo20321a(String str, AbstractC2372a.C2376d c2376d) {
        byte[] bArr;
        boolean z;
        Byte b;
        boolean z2;
        if (Build.VERSION.SDK_INT < 26) {
            return AbstractC2372a.C2377e.m20345b();
        }
        int i = -1;
        boolean z3 = false;
        try {
            i = this.f4160d.getPackageManager().getPackageUid(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        int i2 = i;
        if (i2 < 0) {
            return AbstractC2372a.C2377e.m20345b();
        }
        C2390e c2390e = new C2390e();
        C2386b c2386b = new C2386b();
        C2387c c2387c = new C2387c();
        C2387c c2387c2 = new C2387c();
        for (int i3 = 0; i3 < 16; i3++) {
            C2385a m20316a = C2385a.m20316a(i3);
            if (m20319a(str, m20316a, i2)) {
                c2387c.m20307a(m20316a);
            } else {
                c2387c2.m20307a(m20316a);
            }
        }
        for (int i4 = 0; i4 < 32; i4++) {
            C2385a m20322a = m20322a(str, i4, c2387c.m20308a(), i2, c2390e);
            if (m20322a == null) {
                m20322a = m20322a(str, i4, c2387c2.m20308a(), i2, c2390e);
            }
            if (m20322a == null) {
                return AbstractC2372a.C2377e.m20345b();
            }
            c2386b.m20311a(m20322a);
        }
        byte[] m20310b = c2386b.m20310b();
        boolean z4 = true;
        byte[] bArr2 = {"0".getBytes()[0], "O".getBytes()[0], "V".getBytes()[0]};
        int length = bArr2.length;
        int i5 = 0;
        while (true) {
            bArr = null;
            if (i5 >= length) {
                z = z4;
                b = null;
                break;
            }
            byte b2 = bArr2[i5];
            C2385a m20317a = C2385a.m20317a(b2, z3);
            int i6 = i5;
            int i7 = length;
            z = z4;
            if (m20323a(str, 32, m20317a, i2, c2390e)) {
                C2385a m20317a2 = C2385a.m20317a(b2, z);
                if (m20323a(str, 33, m20317a2, i2, c2390e)) {
                    C2386b c2386b2 = new C2386b();
                    c2386b2.m20311a(m20317a);
                    c2386b2.m20311a(m20317a2);
                    b = Byte.valueOf(c2386b2.m20310b()[0]);
                    break;
                }
            }
            i5 = i6 + 1;
            z4 = z;
            length = i7;
            z3 = false;
        }
        if (b == null) {
            C2386b c2386b3 = new C2386b();
            int i8 = 32;
            while (i8 < 34) {
                int i9 = i8;
                C2385a m20322a2 = m20322a(str, i8, c2387c.m20308a(), i2, c2390e);
                if (m20322a2 == null) {
                    m20322a2 = m20322a(str, i9, c2387c2.m20308a(), i2, c2390e);
                }
                if (m20322a2 == null) {
                    return AbstractC2372a.C2377e.m20345b();
                }
                c2386b3.m20311a(m20322a2);
                i8 = i9 + 1;
            }
            b = Byte.valueOf(c2386b3.m20310b()[0]);
            z2 = z;
        } else {
            z2 = false;
        }
        Byte b3 = b;
        if (z2) {
            C2386b c2386b4 = new C2386b();
            for (int i10 = 34; i10 < 94; i10++) {
                C2385a m20322a3 = m20322a(str, i10, c2387c.m20308a(), i2, c2390e);
                if (m20322a3 == null) {
                    m20322a3 = m20322a(str, i10, c2387c2.m20308a(), i2, c2390e);
                }
                if (m20322a3 == null) {
                    break;
                }
                c2386b4.m20311a(m20322a3);
            }
            if (c2386b4.m20313a() > 0) {
                bArr = c2386b4.m20310b();
            }
        }
        return AbstractC2372a.C2377e.m20346a(new C2389d(m20310b, b3.byteValue(), bArr).m20303a());
    }

    @Override // com.baidu.p122b.p124b.AbstractC2372a
    /* renamed from: a */
    public void mo20326a(AbstractC2372a.C2375c c2375c) {
        this.f4160d = this.f4128a.f4132a;
    }
}
