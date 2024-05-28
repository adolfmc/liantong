package com.baidu.platform.comapi.basestruct;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapBound implements Serializable {
    public Point leftBottomPt = new Point();
    public Point rightTopPt = new Point();

    public MapBound() {
    }

    public MapBound(int i, int i2, int i3, int i4) {
        setLeftBottomPt(i, i2);
        setRightTopPt(i3, i4);
    }

    public void setLeftBottomPt(int i, int i2) {
        this.leftBottomPt.setTo(i, i2);
    }

    public void setRightTopPt(int i, int i2) {
        this.rightTopPt.setTo(i, i2);
    }

    public void setLeftBottomPt(Point point) {
        this.leftBottomPt.setTo(point);
    }

    public void setRightTopPt(Point point) {
        this.rightTopPt.setTo(point);
    }

    public Point getCenterPt() {
        return new Point((this.leftBottomPt.getIntX() + this.rightTopPt.getIntX()) / 2, (this.leftBottomPt.getIntY() + this.rightTopPt.getIntY()) / 2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MapBound) {
            MapBound mapBound = (MapBound) obj;
            Point point = this.leftBottomPt;
            if (point == null ? mapBound.leftBottomPt == null : point.equals(mapBound.leftBottomPt)) {
                Point point2 = this.rightTopPt;
                if (point2 != null) {
                    if (point2.equals(mapBound.rightTopPt)) {
                        return true;
                    }
                } else if (mapBound.rightTopPt == null) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Point point = this.leftBottomPt;
        int hashCode = (point != null ? point.hashCode() : 0) * 31;
        Point point2 = this.rightTopPt;
        return hashCode + (point2 != null ? point2.hashCode() : 0);
    }

    public String toString() {
        return "MapBound{leftBottomPt=" + this.leftBottomPt + ", rightTopPt=" + this.rightTopPt + '}';
    }

    public String toQuery() {
        return String.format("(%d,%d;%d,%d)", Integer.valueOf(this.leftBottomPt.getIntX()), Integer.valueOf(this.leftBottomPt.getIntY()), Integer.valueOf(this.rightTopPt.getIntX()), Integer.valueOf(this.rightTopPt.getIntY()));
    }
}
