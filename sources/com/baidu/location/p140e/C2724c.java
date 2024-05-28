package com.baidu.location.p140e;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.e.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2724c {
    /* renamed from: a */
    public static int m19090a(ArrayList<ArrayList<Float>> arrayList) {
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2).get(2).floatValue() > 0.0f) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: b */
    public static int m19089b(ArrayList<ArrayList<Float>> arrayList) {
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2).get(2).floatValue() >= 15.0f) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: c */
    public static int m19088c(ArrayList<ArrayList<Float>> arrayList) {
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2).get(2).floatValue() >= 20.0f) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: d */
    public static int m19087d(ArrayList<ArrayList<Float>> arrayList) {
        float f = 0.0f;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).get(2).floatValue() > 0.0f) {
                f += arrayList.get(i).get(2).floatValue();
            }
        }
        return Math.round(f);
    }

    /* renamed from: e */
    public static int m19086e(ArrayList<ArrayList<Float>> arrayList) {
        return Math.round(m19087d(arrayList) / m19090a(arrayList));
    }

    /* renamed from: f */
    public static int m19085f(ArrayList<ArrayList<Float>> arrayList) {
        float f = 0.0f;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).get(2).floatValue() > 0.0f) {
                f += arrayList.get(i).get(2).floatValue() * arrayList.get(i).get(1).floatValue();
            }
        }
        return Math.round(f);
    }

    /* renamed from: g */
    public static int m19084g(ArrayList<ArrayList<Float>> arrayList) {
        return Math.round(m19085f(arrayList) / m19090a(arrayList));
    }

    /* renamed from: h */
    public static int m19083h(ArrayList<ArrayList<Float>> arrayList) {
        int i;
        int[] iArr = new int[37];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= arrayList.size()) {
                break;
            }
            if (arrayList.get(i3).get(0).floatValue() < 360.0d) {
                int ceil = (int) Math.ceil(arrayList.get(i3).get(0).floatValue() / 10.0f);
                iArr[ceil] = iArr[ceil] + 1;
            }
            i3++;
        }
        for (i = 1; i <= 36; i++) {
            if (iArr[i] > 0) {
                i2++;
            }
        }
        return Math.round((i2 / 36.0f) * 1000.0f);
    }
}
