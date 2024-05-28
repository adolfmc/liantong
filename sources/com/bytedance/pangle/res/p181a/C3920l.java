package com.bytedance.pangle.res.p181a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.res.a.l */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3920l {
    /* renamed from: a */
    public static void m16684a(C3915g c3915g) {
        c3915g.m16696b(0);
        int readInt = c3915g.readInt();
        int readInt2 = c3915g.readInt();
        int readInt3 = c3915g.readInt();
        c3915g.skipBytes(4);
        int readInt4 = c3915g.readInt();
        int readInt5 = c3915g.readInt();
        c3915g.skipBytes(readInt2 * 4);
        if (readInt3 != 0) {
            c3915g.skipBytes(readInt3 * 4);
        }
        c3915g.skipBytes((readInt5 == 0 ? readInt : readInt5) - readInt4);
        if (readInt5 == 0) {
            return;
        }
        int i = readInt - readInt5;
        c3915g.skipBytes(i);
        int i2 = i % 4;
        if (i2 <= 0) {
            return;
        }
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return;
            }
            c3915g.readByte();
            i2 = i3;
        }
    }
}
