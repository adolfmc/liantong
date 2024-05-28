package com.xiaomi.push;

import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.hr */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C11443hr {

    /* renamed from: a */
    private static final Comparator f23285a = new C11445a();

    /* renamed from: a */
    public static int m3080a(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        return b2 < b ? 1 : 0;
    }

    /* renamed from: a */
    public static int m3079a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i2 < i ? 1 : 0;
    }

    /* renamed from: a */
    public static int m3078a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j2 < j ? 1 : 0;
    }

    /* renamed from: a */
    public static int m3067a(short s, short s2) {
        if (s < s2) {
            return -1;
        }
        return s2 < s ? 1 : 0;
    }

    /* renamed from: a */
    public static int m3066a(boolean z, boolean z2) {
        return Boolean.valueOf(z).compareTo(Boolean.valueOf(z2));
    }

    /* renamed from: a */
    public static int m3076a(String str, String str2) {
        return str.compareTo(str2);
    }

    /* renamed from: a */
    public static int m3065a(byte[] bArr, byte[] bArr2) {
        int m3079a = m3079a(bArr.length, bArr2.length);
        if (m3079a != 0) {
            return m3079a;
        }
        for (int i = 0; i < bArr.length; i++) {
            int m3080a = m3080a(bArr[i], bArr2[i]);
            if (m3080a != 0) {
                return m3080a;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m3077a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    /* renamed from: a */
    public static int m3070a(List list, List list2) {
        int m3079a = m3079a(list.size(), list2.size());
        if (m3079a != 0) {
            return m3079a;
        }
        for (int i = 0; i < list.size(); i++) {
            int compare = f23285a.compare(list.get(i), list2.get(i));
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m3068a(Set set, Set set2) {
        int m3079a = m3079a(set.size(), set2.size());
        if (m3079a != 0) {
            return m3079a;
        }
        TreeSet treeSet = new TreeSet(f23285a);
        treeSet.addAll(set);
        TreeSet treeSet2 = new TreeSet(f23285a);
        treeSet2.addAll(set2);
        Iterator it = treeSet.iterator();
        Iterator it2 = treeSet2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int compare = f23285a.compare(it.next(), it2.next());
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static int m3069a(Map map, Map map2) {
        int m3079a = m3079a(map.size(), map2.size());
        if (m3079a != 0) {
            return m3079a;
        }
        TreeMap treeMap = new TreeMap(f23285a);
        treeMap.putAll(map);
        Iterator it = treeMap.entrySet().iterator();
        TreeMap treeMap2 = new TreeMap(f23285a);
        treeMap2.putAll(map2);
        Iterator it2 = treeMap2.entrySet().iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Map.Entry entry2 = (Map.Entry) it2.next();
            int compare = f23285a.compare(entry.getKey(), entry2.getKey());
            if (compare != 0) {
                return compare;
            }
            int compare2 = f23285a.compare(entry.getValue(), entry2.getValue());
            if (compare2 != 0) {
                return compare2;
            }
        }
        return 0;
    }

    /* renamed from: com.xiaomi.push.hr$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class C11445a implements Comparator {
        private C11445a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == null && obj2 == null) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            if (obj instanceof List) {
                return C11443hr.m3070a((List) obj, (List) obj2);
            }
            if (obj instanceof Set) {
                return C11443hr.m3068a((Set) obj, (Set) obj2);
            }
            if (obj instanceof Map) {
                return C11443hr.m3069a((Map) obj, (Map) obj2);
            }
            if (obj instanceof byte[]) {
                return C11443hr.m3065a((byte[]) obj, (byte[]) obj2);
            }
            return C11443hr.m3077a((Comparable) obj, (Comparable) obj2);
        }
    }

    /* renamed from: a */
    public static void m3072a(ByteBuffer byteBuffer, StringBuilder sb) {
        byte[] array = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset();
        int limit = byteBuffer.limit();
        int i = limit - arrayOffset > 128 ? arrayOffset + 128 : limit;
        for (int i2 = arrayOffset; i2 < i; i2++) {
            if (i2 > arrayOffset) {
                sb.append(" ");
            }
            sb.append(m3081a(array[i2]));
        }
        if (limit != i) {
            sb.append("...");
        }
    }

    /* renamed from: a */
    public static String m3081a(byte b) {
        return Integer.toHexString((b | 256) & 511).toUpperCase().substring(1);
    }

    /* renamed from: a */
    public static byte[] m3073a(ByteBuffer byteBuffer) {
        if (m3074a(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        m3071a(byteBuffer, bArr, 0);
        return bArr;
    }

    /* renamed from: a */
    public static boolean m3074a(ByteBuffer byteBuffer) {
        return byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity();
    }

    /* renamed from: a */
    public static int m3071a(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int remaining = byteBuffer.remaining();
        System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), bArr, i, remaining);
        return remaining;
    }

    /* renamed from: a */
    public static ByteBuffer m3075a(ByteBuffer byteBuffer) {
        return m3074a(byteBuffer) ? byteBuffer : ByteBuffer.wrap(m3073a(byteBuffer));
    }
}
