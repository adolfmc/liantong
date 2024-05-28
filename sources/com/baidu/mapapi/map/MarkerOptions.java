package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MarkerOptions extends OverlayOptions {

    /* renamed from: a */
    int f6248a;

    /* renamed from: c */
    Bundle f6250c;

    /* renamed from: d */
    private LatLng f6251d;

    /* renamed from: e */
    private BitmapDescriptor f6252e;

    /* renamed from: j */
    private float f6257j;

    /* renamed from: k */
    private String f6258k;

    /* renamed from: l */
    private int f6259l;

    /* renamed from: n */
    private ArrayList<BitmapDescriptor> f6261n;

    /* renamed from: v */
    private Point f6269v;

    /* renamed from: x */
    private InfoWindow f6271x;

    /* renamed from: f */
    private float f6253f = 0.5f;

    /* renamed from: g */
    private float f6254g = 1.0f;

    /* renamed from: h */
    private boolean f6255h = true;

    /* renamed from: i */
    private boolean f6256i = false;

    /* renamed from: m */
    private boolean f6260m = false;

    /* renamed from: o */
    private int f6262o = 20;

    /* renamed from: p */
    private float f6263p = 1.0f;

    /* renamed from: q */
    private float f6264q = 1.0f;

    /* renamed from: r */
    private float f6265r = 1.0f;

    /* renamed from: s */
    private int f6266s = 0;

    /* renamed from: t */
    private int f6267t = MarkerAnimateType.none.ordinal();

    /* renamed from: u */
    private boolean f6268u = false;

    /* renamed from: w */
    private boolean f6270w = true;

    /* renamed from: y */
    private int f6272y = Integer.MAX_VALUE;

    /* renamed from: z */
    private boolean f6273z = false;

    /* renamed from: A */
    private int f6245A = 4;

    /* renamed from: B */
    private int f6246B = 22;

    /* renamed from: C */
    private boolean f6247C = false;

    /* renamed from: b */
    boolean f6249b = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum MarkerAnimateType {
        none,
        drop,
        grow,
        jump
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        Marker marker = new Marker();
        marker.f6299H = this.f6249b;
        marker.f6298G = this.f6248a;
        marker.f6300I = this.f6250c;
        LatLng latLng = this.f6251d;
        if (latLng != null) {
            marker.f6219a = latLng;
            if (this.f6252e == null && this.f6261n == null) {
                throw new IllegalStateException("BDMapSDKException: when you add marker, you must set the icon or icons");
            }
            marker.f6220b = this.f6252e;
            marker.f6221c = this.f6253f;
            marker.f6222d = this.f6254g;
            marker.f6223e = this.f6255h;
            marker.f6224f = this.f6256i;
            marker.f6225g = this.f6257j;
            marker.f6226h = this.f6258k;
            marker.f6227i = this.f6259l;
            marker.f6228j = this.f6260m;
            marker.f6237s = this.f6261n;
            marker.f6238t = this.f6262o;
            marker.f6230l = this.f6265r;
            marker.f6236r = this.f6266s;
            marker.f6240v = this.f6263p;
            marker.f6241w = this.f6264q;
            marker.f6231m = this.f6267t;
            marker.f6232n = this.f6268u;
            marker.f6244z = this.f6271x;
            marker.f6233o = this.f6270w;
            marker.f6216C = this.f6272y;
            marker.f6235q = this.f6273z;
            marker.f6217D = this.f6245A;
            marker.f6218E = this.f6246B;
            marker.f6234p = this.f6247C;
            Point point = this.f6269v;
            if (point != null) {
                marker.f6243y = point;
            }
            return marker;
        }
        throw new IllegalStateException("BDMapSDKException: when you add marker, you must set the position");
    }

    public MarkerOptions alpha(float f) {
        if (f < 0.0f || f > 1.0f) {
            this.f6265r = 1.0f;
            return this;
        }
        this.f6265r = f;
        return this;
    }

    public MarkerOptions anchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f6253f = f;
            this.f6254g = f2;
        }
        return this;
    }

    public MarkerOptions animateType(MarkerAnimateType markerAnimateType) {
        if (markerAnimateType == null) {
            markerAnimateType = MarkerAnimateType.none;
        }
        this.f6267t = markerAnimateType.ordinal();
        return this;
    }

    public MarkerOptions clickable(boolean z) {
        this.f6270w = z;
        return this;
    }

    public MarkerOptions draggable(boolean z) {
        this.f6256i = z;
        return this;
    }

    public MarkerOptions endLevel(int i) {
        this.f6246B = i;
        return this;
    }

    public MarkerOptions extraInfo(Bundle bundle) {
        this.f6250c = bundle;
        return this;
    }

    public MarkerOptions fixedScreenPosition(Point point) {
        this.f6269v = point;
        this.f6268u = true;
        return this;
    }

    public MarkerOptions flat(boolean z) {
        this.f6260m = z;
        return this;
    }

    public float getAlpha() {
        return this.f6265r;
    }

    public float getAnchorX() {
        return this.f6253f;
    }

    public float getAnchorY() {
        return this.f6254g;
    }

    public MarkerAnimateType getAnimateType() {
        switch (this.f6267t) {
            case 1:
                return MarkerAnimateType.drop;
            case 2:
                return MarkerAnimateType.grow;
            case 3:
                return MarkerAnimateType.jump;
            default:
                return MarkerAnimateType.none;
        }
    }

    public int getEndLevel() {
        return this.f6246B;
    }

    public Bundle getExtraInfo() {
        return this.f6250c;
    }

    public boolean getForceDisPlay() {
        return this.f6273z;
    }

    public int getHeight() {
        return this.f6266s;
    }

    public BitmapDescriptor getIcon() {
        return this.f6252e;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f6261n;
    }

    public boolean getIsClickable() {
        return this.f6270w;
    }

    public boolean getJoinCollision() {
        return this.f6247C;
    }

    public int getPeriod() {
        return this.f6262o;
    }

    public LatLng getPosition() {
        return this.f6251d;
    }

    public int getPriority() {
        return this.f6272y;
    }

    public float getRotate() {
        return this.f6257j;
    }

    public int getStartLevel() {
        return this.f6245A;
    }

    @Deprecated
    public String getTitle() {
        return this.f6258k;
    }

    public int getZIndex() {
        return this.f6248a;
    }

    public MarkerOptions height(int i) {
        if (i < 0) {
            this.f6266s = 0;
            return this;
        }
        this.f6266s = i;
        return this;
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.f6252e = bitmapDescriptor;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: marker's icon can not be null");
    }

    public MarkerOptions icons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList != null) {
            if (arrayList.size() == 0) {
                return this;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) == null || arrayList.get(i).f5990a == null) {
                    return this;
                }
            }
            this.f6261n = arrayList;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: marker's icons can not be null");
    }

    public MarkerOptions infoWindow(InfoWindow infoWindow) {
        this.f6271x = infoWindow;
        return this;
    }

    public boolean isDraggable() {
        return this.f6256i;
    }

    public boolean isFlat() {
        return this.f6260m;
    }

    public MarkerOptions isForceDisPlay(boolean z) {
        this.f6273z = z;
        return this;
    }

    public MarkerOptions isJoinCollision(boolean z) {
        this.f6247C = z;
        return this;
    }

    public boolean isPerspective() {
        return this.f6255h;
    }

    public boolean isVisible() {
        return this.f6249b;
    }

    public MarkerOptions period(int i) {
        if (i > 0) {
            this.f6262o = i;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: marker's period must be greater than zero ");
    }

    public MarkerOptions perspective(boolean z) {
        this.f6255h = z;
        return this;
    }

    public MarkerOptions position(LatLng latLng) {
        if (latLng != null) {
            this.f6251d = latLng;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: marker's position can not be null");
    }

    public MarkerOptions priority(int i) {
        this.f6272y = i;
        return this;
    }

    public MarkerOptions rotate(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        this.f6257j = f % 360.0f;
        return this;
    }

    public MarkerOptions scaleX(float f) {
        if (f < 0.0f) {
            return this;
        }
        this.f6263p = f;
        return this;
    }

    public MarkerOptions scaleY(float f) {
        if (f < 0.0f) {
            return this;
        }
        this.f6264q = f;
        return this;
    }

    public MarkerOptions startLevel(int i) {
        this.f6245A = i;
        return this;
    }

    @Deprecated
    public MarkerOptions title(String str) {
        this.f6258k = str;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.f6249b = z;
        return this;
    }

    public MarkerOptions yOffset(int i) {
        this.f6259l = i;
        return this;
    }

    public MarkerOptions zIndex(int i) {
        this.f6248a = i;
        return this;
    }
}
