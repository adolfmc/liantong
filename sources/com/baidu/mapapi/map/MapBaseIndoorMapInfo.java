package com.baidu.mapapi.map;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MapBaseIndoorMapInfo {

    /* renamed from: d */
    private static final String f6128d = "MapBaseIndoorMapInfo";

    /* renamed from: a */
    String f6129a;

    /* renamed from: b */
    String f6130b;

    /* renamed from: c */
    ArrayList<String> f6131c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum SwitchFloorError {
        SWITCH_OK,
        FLOOR_INFO_ERROR,
        FLOOR_OVERLFLOW,
        FOCUSED_ID_ERROR,
        SWITCH_ERROR
    }

    public MapBaseIndoorMapInfo() {
    }

    public MapBaseIndoorMapInfo(MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
        this.f6129a = mapBaseIndoorMapInfo.f6129a;
        this.f6130b = mapBaseIndoorMapInfo.f6130b;
        this.f6131c = mapBaseIndoorMapInfo.f6131c;
    }

    public MapBaseIndoorMapInfo(String str, String str2, ArrayList<String> arrayList) {
        this.f6129a = str;
        this.f6130b = str2;
        this.f6131c = arrayList;
    }

    public String getCurFloor() {
        return this.f6130b;
    }

    public ArrayList<String> getFloors() {
        return this.f6131c;
    }

    public String getID() {
        return this.f6129a;
    }
}
