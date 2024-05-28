package com.baidu.mapapi.map;

import android.graphics.Point;
import android.view.ViewGroup;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MapViewLayoutParams extends ViewGroup.LayoutParams {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;

    /* renamed from: a */
    LatLng f6200a;

    /* renamed from: b */
    Point f6201b;

    /* renamed from: c */
    ELayoutMode f6202c;

    /* renamed from: d */
    float f6203d;

    /* renamed from: e */
    float f6204e;

    /* renamed from: f */
    int f6205f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class Builder {

        /* renamed from: a */
        private int f6206a;

        /* renamed from: b */
        private int f6207b;

        /* renamed from: c */
        private LatLng f6208c;

        /* renamed from: d */
        private Point f6209d;

        /* renamed from: e */
        private ELayoutMode f6210e = ELayoutMode.absoluteMode;

        /* renamed from: f */
        private int f6211f = 4;

        /* renamed from: g */
        private int f6212g = 16;

        /* renamed from: h */
        private int f6213h;

        public Builder align(int i, int i2) {
            if (i == 1 || i == 2 || i == 4) {
                this.f6211f = i;
            }
            if (i2 == 8 || i2 == 16 || i2 == 32) {
                this.f6212g = i2;
            }
            return this;
        }

        public MapViewLayoutParams build() {
            boolean z = true;
            if (this.f6210e != ELayoutMode.mapMode ? this.f6210e != ELayoutMode.absoluteMode || this.f6209d != null : this.f6208c != null) {
                z = false;
            }
            if (z) {
                throw new IllegalStateException("BDMapSDKException: if it is map mode, you must supply position info; else if it is absolute mode, you must supply the point info");
            }
            return new MapViewLayoutParams(this.f6206a, this.f6207b, this.f6208c, this.f6209d, this.f6210e, this.f6211f, this.f6212g, this.f6213h);
        }

        public Builder height(int i) {
            this.f6207b = i;
            return this;
        }

        public Builder layoutMode(ELayoutMode eLayoutMode) {
            this.f6210e = eLayoutMode;
            return this;
        }

        public Builder point(Point point) {
            this.f6209d = point;
            return this;
        }

        public Builder position(LatLng latLng) {
            this.f6208c = latLng;
            return this;
        }

        public Builder width(int i) {
            this.f6206a = i;
            return this;
        }

        public Builder yOffset(int i) {
            this.f6213h = i;
            return this;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum ELayoutMode {
        mapMode,
        absoluteMode
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    MapViewLayoutParams(int r1, int r2, com.baidu.mapapi.model.LatLng r3, android.graphics.Point r4, com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode r5, int r6, int r7, int r8) {
        /*
            r0 = this;
            r0.<init>(r1, r2)
            r0.f6200a = r3
            r0.f6201b = r4
            r0.f6202c = r5
            r1 = 4
            r2 = 0
            r3 = 1065353216(0x3f800000, float:1.0)
            r4 = 1056964608(0x3f000000, float:0.5)
            if (r6 == r1) goto L14
            switch(r6) {
                case 1: goto L1a;
                case 2: goto L17;
                default: goto L14;
            }
        L14:
            r0.f6203d = r4
            goto L1c
        L17:
            r0.f6203d = r3
            goto L1c
        L1a:
            r0.f6203d = r2
        L1c:
            r1 = 8
            if (r7 == r1) goto L2e
            r1 = 16
            if (r7 == r1) goto L28
            r1 = 32
            if (r7 == r1) goto L2b
        L28:
            r0.f6204e = r3
            goto L30
        L2b:
            r0.f6204e = r4
            goto L30
        L2e:
            r0.f6204e = r2
        L30:
            r0.f6205f = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.MapViewLayoutParams.<init>(int, int, com.baidu.mapapi.model.LatLng, android.graphics.Point, com.baidu.mapapi.map.MapViewLayoutParams$ELayoutMode, int, int, int):void");
    }
}
