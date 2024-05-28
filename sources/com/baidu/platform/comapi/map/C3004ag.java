package com.baidu.platform.comapi.map;

import com.baidu.platform.comapi.basestruct.GeoPoint;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.ag */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3004ag extends AbstractC3068j {

    /* renamed from: k */
    private List<GeoPoint> f7735k;

    public C3004ag(C3014ap c3014ap) {
        super(c3014ap);
        this.f7735k = new ArrayList();
        this.f7978i = 0;
        this.f7979j = 2;
    }

    /* renamed from: b */
    private boolean m17978b() {
        synchronized (this.f7735k) {
            if (this.f7735k.size() < 2) {
                return false;
            }
            int size = this.f7735k.size();
            this.f7973d = new double[(this.f7735k.size() * 2) + 5];
            if (m17977c()) {
                this.f7973d[0] = this.f7974e.getLongitude();
                this.f7973d[1] = this.f7974e.getLatitude();
                this.f7973d[2] = this.f7975f.getLongitude();
                this.f7973d[3] = this.f7975f.getLatitude();
            }
            this.f7973d[4] = 2.0d;
            this.f7973d[5] = this.f7735k.get(0).getLongitude();
            this.f7973d[6] = this.f7735k.get(0).getLatitude();
            for (int i = 1; i < size; i++) {
                int i2 = (i * 2) + 5;
                int i3 = i - 1;
                this.f7973d[i2] = this.f7735k.get(i).getLongitude() - this.f7735k.get(i3).getLongitude();
                this.f7973d[i2 + 1] = this.f7735k.get(i).getLatitude() - this.f7735k.get(i3).getLatitude();
            }
            return true;
        }
    }

    /* renamed from: c */
    private boolean m17977c() {
        synchronized (this.f7735k) {
            if (this.f7735k.size() < 2) {
                return false;
            }
            this.f7974e.setLatitude(this.f7735k.get(0).getLatitude());
            this.f7974e.setLongitude(this.f7735k.get(0).getLongitude());
            this.f7975f.setLatitude(this.f7735k.get(0).getLatitude());
            this.f7975f.setLongitude(this.f7735k.get(0).getLongitude());
            for (GeoPoint geoPoint : this.f7735k) {
                if (this.f7974e.getLatitude() >= geoPoint.getLatitude()) {
                    this.f7974e.setLatitude(geoPoint.getLatitude());
                }
                if (this.f7974e.getLongitude() >= geoPoint.getLongitude()) {
                    this.f7974e.setLongitude(geoPoint.getLongitude());
                }
                if (this.f7975f.getLatitude() <= geoPoint.getLatitude()) {
                    this.f7975f.setLatitude(geoPoint.getLatitude());
                }
                if (this.f7975f.getLongitude() <= geoPoint.getLongitude()) {
                    this.f7975f.setLongitude(geoPoint.getLongitude());
                }
            }
            return true;
        }
    }

    @Override // com.baidu.platform.comapi.map.AbstractC3068j
    /* renamed from: a */
    public String mo17737a() {
        String a;
        synchronized (this.f7735k) {
            if (this.f7976g) {
                this.f7976g = !m17978b();
            }
            a = m17736a(this.f7978i);
        }
        return a;
    }

    /* renamed from: a */
    public void m17980a(C3014ap c3014ap) {
        this.f7970a = c3014ap;
    }

    /* renamed from: a */
    public void m17979a(List<GeoPoint> list) {
        if (list == null) {
            throw new IllegalArgumentException("points list can not be null!");
        }
        if (list.size() < 2) {
            throw new IllegalArgumentException("points count can not be less than two!");
        }
        synchronized (this.f7735k) {
            this.f7735k.clear();
            this.f7735k.addAll(list);
            this.f7976g = true;
        }
    }
}
