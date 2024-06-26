package com.baidu.mapapi.model.inner;

import com.baidu.platform.comapi.basestruct.Point;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapBound implements Serializable {
    public Point ptLB;
    public Point ptRT;

    public MapBound() {
        if (this.ptLB == null) {
            this.ptLB = new Point();
        }
        if (this.ptRT == null) {
            this.ptRT = new Point();
        }
    }

    public Point getPtLB() {
        return this.ptLB;
    }

    public void setPtLB(Point point) {
        this.ptLB = point;
    }

    public Point getPtRT() {
        return this.ptRT;
    }

    public void setPtRT(Point point) {
        this.ptRT = point;
    }
}
