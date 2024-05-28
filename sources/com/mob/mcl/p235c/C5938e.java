package com.mob.mcl.p235c;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.mob.mcl.c.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5938e implements Serializable {

    /* renamed from: a */
    public final int f14605a;

    /* renamed from: b */
    public final int f14606b;

    /* renamed from: c */
    public long f14607c;

    /* renamed from: d */
    public String f14608d;

    public C5938e(int i) {
        this(i, null);
    }

    public C5938e(int i, String str) {
        this(str != null ? str.length() : 0, i, 0L, str);
    }

    C5938e(int i, int i2, long j, String str) {
        this.f14605a = i;
        this.f14606b = i2;
        this.f14607c = j;
        this.f14608d = str;
    }

    /* renamed from: a */
    public byte[] m12031a() {
        ByteBuffer allocate = ByteBuffer.allocate(m12029b());
        allocate.put((byte) 1);
        allocate.putInt(this.f14605a);
        allocate.putInt(this.f14606b);
        allocate.putLong(this.f14607c);
        String str = this.f14608d;
        if (str != null) {
            allocate.put(str.getBytes(Charset.forName("UTF-8")));
        }
        return allocate.array();
    }

    /* renamed from: a */
    public static List<C5938e> m12030a(ByteBuffer byteBuffer) {
        C5938e m12028b;
        ArrayList arrayList = new ArrayList();
        while (byteBuffer.remaining() >= 17 && (m12028b = m12028b(byteBuffer)) != null) {
            arrayList.add(m12028b);
        }
        return arrayList;
    }

    /* renamed from: b */
    static C5938e m12028b(ByteBuffer byteBuffer) {
        int i;
        C5938e m12027c = m12027c(byteBuffer);
        if (m12027c != null && (i = m12027c.f14605a) > 0) {
            if (i > byteBuffer.remaining()) {
                return null;
            }
            byte[] bArr = new byte[m12027c.f14605a];
            byteBuffer.get(bArr);
            m12027c.f14608d = new String(bArr);
        }
        return m12027c;
    }

    /* renamed from: c */
    static C5938e m12027c(ByteBuffer byteBuffer) {
        if (byteBuffer.get() != 1) {
            return null;
        }
        int i = byteBuffer.getInt();
        int i2 = byteBuffer.getInt();
        if (i2 > 9999) {
            return null;
        }
        return new C5938e(i, i2, byteBuffer.getLong(), null);
    }

    /* renamed from: b */
    public int m12029b() {
        return this.f14605a + 17;
    }
}
