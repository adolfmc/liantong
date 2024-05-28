package com.baidu.mapapi.map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MyLocationData {
    public final float accuracy;
    public final float direction;
    public final double latitude;
    public final double longitude;
    public final int satellitesNum;
    public final float speed;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Builder {

        /* renamed from: a */
        private double f6291a;

        /* renamed from: b */
        private double f6292b;

        /* renamed from: c */
        private float f6293c;

        /* renamed from: d */
        private float f6294d;

        /* renamed from: e */
        private float f6295e;

        /* renamed from: f */
        private int f6296f;

        public Builder accuracy(float f) {
            this.f6295e = f;
            return this;
        }

        public MyLocationData build() {
            return new MyLocationData(this.f6291a, this.f6292b, this.f6293c, this.f6294d, this.f6295e, this.f6296f);
        }

        public Builder direction(float f) {
            this.f6294d = f;
            return this;
        }

        public Builder latitude(double d) {
            this.f6291a = d;
            return this;
        }

        public Builder longitude(double d) {
            this.f6292b = d;
            return this;
        }

        public Builder satellitesNum(int i) {
            this.f6296f = i;
            return this;
        }

        public Builder speed(float f) {
            this.f6293c = f;
            return this;
        }
    }

    MyLocationData(double d, double d2, float f, float f2, float f3, int i) {
        this.latitude = d;
        this.longitude = d2;
        this.speed = f;
        this.direction = f2;
        this.accuracy = f3;
        this.satellitesNum = i;
    }
}
