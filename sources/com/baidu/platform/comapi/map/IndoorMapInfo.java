package com.baidu.platform.comapi.map;

import android.text.TextUtils;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class IndoorMapInfo implements Serializable {

    /* renamed from: a */
    private String f7558a;

    /* renamed from: b */
    private String f7559b;

    /* renamed from: c */
    private String[] f7560c;

    /* renamed from: d */
    private int[] f7561d;

    /* renamed from: e */
    private int f7562e;

    /* renamed from: f */
    private int f7563f;

    /* renamed from: g */
    private String f7564g;

    public IndoorMapInfo(String str, String str2) {
        this.f7558a = str;
        this.f7559b = str2;
    }

    public IndoorMapInfo(String str, String str2, String[] strArr, int[] iArr, int i) {
        this(str, str2, strArr, iArr, i, 0, "");
    }

    public IndoorMapInfo(String str, String str2, String[] strArr, int[] iArr, int i, int i2) {
        this(str, str2, strArr, iArr, i, i2, "");
    }

    public IndoorMapInfo(String str, String str2, String[] strArr, int[] iArr, int i, int i2, String str3) {
        this.f7558a = str;
        this.f7559b = str2;
        this.f7562e = i;
        this.f7563f = i2;
        if (strArr != null) {
            this.f7560c = (String[]) Array.newInstance(String.class, strArr.length);
            System.arraycopy(strArr, 0, this.f7560c, 0, strArr.length);
        }
        if (iArr != null) {
            this.f7561d = new int[iArr.length];
            System.arraycopy(iArr, 0, this.f7561d, 0, iArr.length);
        }
        this.f7564g = str3;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof IndoorMapInfo)) {
            IndoorMapInfo indoorMapInfo = (IndoorMapInfo) obj;
            if (TextUtils.equals(this.f7558a, indoorMapInfo.f7558a) && TextUtils.equals(this.f7559b, indoorMapInfo.f7559b) && Arrays.equals(this.f7560c, indoorMapInfo.f7560c)) {
                return Arrays.equals(this.f7561d, indoorMapInfo.f7561d);
            }
            return false;
        }
        return false;
    }

    public String getBuildingId() {
        return this.f7558a;
    }

    public final int[] getFloorAttribute() {
        return this.f7561d;
    }

    public String getFloorId() {
        return this.f7559b;
    }

    public final String[] getFloorList() {
        return this.f7560c;
    }

    public String getIdrSearch() {
        return this.f7564g;
    }

    public int getIdrguide() {
        return this.f7563f;
    }

    public int getIndoorType() {
        return this.f7562e;
    }

    public String toString() {
        return "IndoorMapInfo:building_id:" + this.f7558a + ";floor_id:" + this.f7559b + ";indoor_type:" + this.f7562e + ";floor_list:" + Arrays.toString(this.f7560c) + ";floor_attribute:" + Arrays.toString(this.f7561d);
    }
}
