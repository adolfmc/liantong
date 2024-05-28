package com.baidu.platform.comapi.map;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.util.JsonBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC3068j {

    /* renamed from: a */
    protected C3014ap f7970a;

    /* renamed from: b */
    public boolean f7971b;

    /* renamed from: c */
    public boolean f7972c;

    /* renamed from: d */
    protected double[] f7973d;

    /* renamed from: h */
    protected JsonBuilder f7977h;

    /* renamed from: e */
    protected GeoPoint f7974e = new GeoPoint(0, 0);

    /* renamed from: f */
    protected GeoPoint f7975f = new GeoPoint(0, 0);

    /* renamed from: g */
    protected boolean f7976g = true;

    /* renamed from: i */
    protected int f7978i = -1;

    /* renamed from: j */
    protected int f7979j = 0;

    public AbstractC3068j(C3014ap c3014ap) {
        this.f7970a = c3014ap;
    }

    /* renamed from: a */
    public abstract String mo17737a();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public String m17736a(int i) {
        JsonBuilder key;
        int i2;
        this.f7977h = new JsonBuilder();
        this.f7977h.object();
        if (i == 0) {
            this.f7977h.key("path").arrayValue();
            if (this.f7973d != null) {
                int i3 = 0;
                while (true) {
                    double[] dArr = this.f7973d;
                    if (i3 >= dArr.length) {
                        break;
                    }
                    this.f7977h.value(dArr[i3]);
                    i3++;
                }
            }
            this.f7977h.endArrayValue();
        } else if (i == 1) {
            this.f7977h.key("sgeo");
            this.f7977h.object();
            this.f7977h.key("bound").arrayValue();
            GeoPoint geoPoint = this.f7974e;
            if (geoPoint != null && this.f7975f != null) {
                this.f7977h.value(geoPoint.getLongitude());
                this.f7977h.value(this.f7974e.getLatitude());
                this.f7977h.value(this.f7975f.getLongitude());
                this.f7977h.value(this.f7975f.getLatitude());
            }
            this.f7977h.endArrayValue();
            if (this.f7979j == 4) {
                this.f7977h.key("type").value(3);
            } else {
                this.f7977h.key("type").value(this.f7979j);
            }
            this.f7977h.key("elements").arrayValue();
            this.f7977h.object();
            this.f7977h.key("points").arrayValue();
            if (this.f7973d != null) {
                int i4 = 0;
                while (true) {
                    double[] dArr2 = this.f7973d;
                    if (i4 >= dArr2.length) {
                        break;
                    }
                    this.f7977h.value(dArr2[i4]);
                    i4++;
                }
            }
            this.f7977h.endArrayValue();
            this.f7977h.endObject();
            this.f7977h.endArrayValue();
            this.f7977h.endObject();
        }
        this.f7977h.key("ud").value(String.valueOf(hashCode()));
        this.f7977h.key("dir").value(0);
        C3014ap c3014ap = this.f7970a;
        if (c3014ap == null || c3014ap.m17944a() == 0) {
            int i5 = this.f7979j;
            if (i5 == 3) {
                key = this.f7977h.key("ty");
                i2 = 3100;
            } else if (i5 == 4) {
                key = this.f7977h.key("ty");
                i2 = 3200;
            } else {
                key = this.f7977h.key("ty");
                i2 = -1;
            }
        } else {
            this.f7977h.key("nst").value(this.f7970a.m17944a());
            this.f7977h.key("fst").value(this.f7970a.m17944a());
            key = this.f7977h.key("ty");
            i2 = 32;
        }
        key.value(i2);
        this.f7977h.key("of").value(0);
        this.f7977h.key("in").value(0);
        this.f7977h.key("tx").value("");
        this.f7977h.key("dis").value(0);
        this.f7977h.key("align").value(0);
        if (this.f7971b) {
            this.f7977h.key("dash").value(1);
            this.f7977h.key("ty").value(this.f7979j);
        }
        if (this.f7972c) {
            this.f7977h.key("trackMove").object();
            this.f7977h.key("pointStyle").value(((C3016ar) this.f7970a).m17936e());
            this.f7977h.endObject();
        }
        this.f7977h.key("style").object();
        if (this.f7970a != null) {
            this.f7977h.key("width").value(this.f7970a.m17940c());
            this.f7977h.key("color").value(C3014ap.m17939c(this.f7970a.m17942b()));
            int i6 = this.f7979j;
            if (i6 == 3 || i6 == 4) {
                this.f7977h.key("scolor").value(C3014ap.m17939c(this.f7970a.m17938d()));
            }
        }
        this.f7977h.endObject();
        this.f7977h.endObject();
        return this.f7977h.toString();
    }
}
