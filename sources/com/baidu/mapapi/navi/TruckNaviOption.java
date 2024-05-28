package com.baidu.mapapi.navi;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.NaviParaOption;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TruckNaviOption extends NaviParaOption {

    /* renamed from: g */
    int f6618g;

    /* renamed from: h */
    double f6619h;

    /* renamed from: i */
    double f6620i;

    /* renamed from: j */
    double f6621j;

    /* renamed from: k */
    double f6622k;

    /* renamed from: l */
    double f6623l;

    /* renamed from: m */
    int f6624m;

    /* renamed from: n */
    boolean f6625n;

    /* renamed from: o */
    String f6626o;

    /* renamed from: p */
    int f6627p;

    /* renamed from: q */
    int f6628q;

    /* renamed from: r */
    int f6629r;

    /* renamed from: s */
    int f6630s;

    /* renamed from: t */
    int f6631t;

    @Override // com.baidu.mapapi.navi.NaviParaOption
    public TruckNaviOption setNaviRoutePolicy(NaviParaOption.NaviRoutePolicy naviRoutePolicy) {
        return (TruckNaviOption) super.setNaviRoutePolicy(naviRoutePolicy);
    }

    @Override // com.baidu.mapapi.navi.NaviParaOption
    public TruckNaviOption setWayPoint(WayPoint wayPoint) {
        return (TruckNaviOption) super.setWayPoint(wayPoint);
    }

    @Override // com.baidu.mapapi.navi.NaviParaOption
    public String getNaviRoutePolicy() {
        return super.getNaviRoutePolicy();
    }

    @Override // com.baidu.mapapi.navi.NaviParaOption
    public JSONArray getWayPoint() {
        return super.getWayPoint();
    }

    @Override // com.baidu.mapapi.navi.NaviParaOption
    public TruckNaviOption endPoint(LatLng latLng) {
        return (TruckNaviOption) super.endPoint(latLng);
    }

    @Override // com.baidu.mapapi.navi.NaviParaOption
    public TruckNaviOption endName(String str) {
        return (TruckNaviOption) super.endName(str);
    }

    @Override // com.baidu.mapapi.navi.NaviParaOption
    public LatLng getEndPoint() {
        return super.getEndPoint();
    }

    @Override // com.baidu.mapapi.navi.NaviParaOption
    public String getEndName() {
        return super.getEndName();
    }

    public TruckNaviOption setTruckType(int i) {
        this.f6618g = i;
        return this;
    }

    public int getTruckType() {
        return this.f6618g;
    }

    public TruckNaviOption setHeight(double d) {
        this.f6619h = d;
        return this;
    }

    public TruckNaviOption setWidth(double d) {
        this.f6620i = d;
        return this;
    }

    public TruckNaviOption setWeight(double d) {
        this.f6621j = d;
        return this;
    }

    public TruckNaviOption setLength(double d) {
        this.f6622k = d;
        return this;
    }

    public TruckNaviOption setAxleWeight(double d) {
        this.f6623l = d;
        return this;
    }

    public TruckNaviOption setAxleCount(int i) {
        this.f6624m = i;
        return this;
    }

    public TruckNaviOption setIsTrailer(boolean z) {
        this.f6625n = z;
        return this;
    }

    public TruckNaviOption setPlateNumber(String str) {
        this.f6626o = str;
        return this;
    }

    public TruckNaviOption setPlateColor(int i) {
        this.f6627p = i;
        return this;
    }

    public TruckNaviOption setDisplacement(int i) {
        this.f6628q = i;
        return this;
    }

    public TruckNaviOption setPowerType(int i) {
        this.f6629r = i;
        return this;
    }

    public TruckNaviOption setEmissionLimit(int i) {
        this.f6630s = i;
        return this;
    }

    public TruckNaviOption setLoadWeight(int i) {
        this.f6631t = i;
        return this;
    }

    public double getHeight() {
        return this.f6619h;
    }

    public double getWidth() {
        return this.f6620i;
    }

    public double getWeight() {
        return this.f6621j;
    }

    public double getLength() {
        return this.f6622k;
    }

    public double getAxleWeight() {
        return this.f6623l;
    }

    public int getAxleCount() {
        return this.f6624m;
    }

    public boolean getIsTrailer() {
        return this.f6625n;
    }

    public String getPlateNumber() {
        return this.f6626o;
    }

    public int getPlateColor() {
        return this.f6627p;
    }

    public int getDisplacement() {
        return this.f6628q;
    }

    public int getPowerType() {
        return this.f6629r;
    }

    public int getEmissionLimit() {
        return this.f6630s;
    }

    public int getLoadWeight() {
        return this.f6631t;
    }
}
