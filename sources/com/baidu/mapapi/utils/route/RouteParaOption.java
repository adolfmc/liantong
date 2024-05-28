package com.baidu.mapapi.utils.route;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RouteParaOption {

    /* renamed from: a */
    LatLng f7035a;

    /* renamed from: b */
    LatLng f7036b;

    /* renamed from: c */
    String f7037c;

    /* renamed from: d */
    String f7038d;

    /* renamed from: e */
    String f7039e;

    /* renamed from: f */
    EBusStrategyType f7040f = EBusStrategyType.bus_recommend_way;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EBusStrategyType {
        bus_time_first,
        bus_transfer_little,
        bus_walk_little,
        bus_no_subway,
        bus_recommend_way
    }

    public RouteParaOption startPoint(LatLng latLng) {
        this.f7035a = latLng;
        return this;
    }

    public LatLng getStartPoint() {
        return this.f7035a;
    }

    public RouteParaOption endPoint(LatLng latLng) {
        this.f7036b = latLng;
        return this;
    }

    public LatLng getEndPoint() {
        return this.f7036b;
    }

    public RouteParaOption startName(String str) {
        this.f7037c = str;
        return this;
    }

    public String getStartName() {
        return this.f7037c;
    }

    public RouteParaOption endName(String str) {
        this.f7038d = str;
        return this;
    }

    public String getEndName() {
        return this.f7038d;
    }

    public RouteParaOption busStrategyType(EBusStrategyType eBusStrategyType) {
        this.f7040f = eBusStrategyType;
        return this;
    }

    public EBusStrategyType getBusStrategyType() {
        return this.f7040f;
    }

    public RouteParaOption cityName(String str) {
        this.f7039e = str;
        return this;
    }

    public String getCityName() {
        return this.f7039e;
    }
}
