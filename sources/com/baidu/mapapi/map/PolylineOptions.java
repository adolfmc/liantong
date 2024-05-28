package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.map.EnumC2933i;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class PolylineOptions extends OverlayOptions {

    /* renamed from: a */
    int f6342a;

    /* renamed from: c */
    Bundle f6344c;

    /* renamed from: e */
    private List<LatLng> f6346e;

    /* renamed from: f */
    private List<Integer> f6347f;

    /* renamed from: g */
    private List<Integer> f6348g;

    /* renamed from: i */
    private BitmapDescriptor f6350i;

    /* renamed from: j */
    private List<BitmapDescriptor> f6351j;

    /* renamed from: d */
    private int f6345d = -16777216;

    /* renamed from: h */
    private int f6349h = 5;

    /* renamed from: k */
    private boolean f6352k = true;

    /* renamed from: l */
    private boolean f6353l = false;

    /* renamed from: b */
    boolean f6343b = true;

    /* renamed from: m */
    private boolean f6354m = false;

    /* renamed from: n */
    private boolean f6355n = true;

    /* renamed from: o */
    private int f6356o = 0;

    /* renamed from: p */
    private LineJoinType f6357p = LineJoinType.LineJoinRound;

    /* renamed from: q */
    private LineCapType f6358q = LineCapType.LineCapButt;

    /* renamed from: r */
    private boolean f6359r = true;

    /* renamed from: s */
    private boolean f6360s = false;

    /* renamed from: t */
    private boolean f6361t = false;

    /* renamed from: u */
    private LineDirectionCross180 f6362u = LineDirectionCross180.NONE;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum LineCapType {
        LineCapButt,
        LineCapRound
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum LineDirectionCross180 {
        NONE,
        FROM_EAST_TO_WEST,
        FROM_WEST_TO_EAST
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum LineJoinType {
        LineJoinBevel,
        LineJoinMiter,
        LineJoinRound,
        LineJoinBerzier
    }

    /* renamed from: a */
    private Polyline m18876a(Polyline polyline) {
        polyline.f6299H = this.f6343b;
        polyline.f6340r = this.f6362u;
        polyline.f6324b = this.f6346e;
        polyline.f6337o = this.f6361t;
        List<Integer> list = this.f6348g;
        if (list == null || list.size() == 0) {
            throw new IllegalStateException("BDMapSDKException: colors array can not be null");
        }
        int[] iArr = new int[this.f6348g.size()];
        int i = 0;
        for (Integer num : this.f6348g) {
            iArr[i] = num.intValue();
            i++;
        }
        polyline.f6326d = iArr;
        return polyline;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        Polyline polyline = new Polyline();
        List<LatLng> list = this.f6346e;
        if (list == null || list.size() < 2) {
            throw new IllegalStateException("BDMapSDKException: when you add polyline, you must at least supply 2 points");
        }
        boolean z = this.f6361t;
        if (z) {
            polyline.type = EnumC2933i.gradientLine;
            return m18876a(polyline);
        }
        polyline.f6299H = this.f6343b;
        polyline.f6328f = this.f6354m;
        polyline.f6298G = this.f6342a;
        polyline.f6300I = this.f6344c;
        polyline.f6324b = this.f6346e;
        polyline.f6323a = this.f6345d;
        polyline.f6327e = this.f6349h;
        polyline.f6332j = this.f6350i;
        polyline.f6333k = this.f6351j;
        polyline.f6329g = this.f6352k;
        polyline.f6330h = this.f6353l;
        polyline.f6331i = this.f6355n;
        polyline.f6335m = this.f6359r;
        polyline.f6336n = this.f6360s;
        polyline.f6337o = z;
        polyline.f6334l = this.f6356o;
        polyline.f6339q = this.f6357p;
        polyline.f6338p = this.f6358q;
        polyline.f6340r = this.f6362u;
        List<Integer> list2 = this.f6347f;
        if (list2 != null && list2.size() < this.f6346e.size() - 1) {
            ArrayList arrayList = new ArrayList((this.f6346e.size() - 1) - this.f6347f.size());
            List<Integer> list3 = this.f6347f;
            list3.addAll(list3.size(), arrayList);
        }
        List<Integer> list4 = this.f6347f;
        int i = 0;
        if (list4 != null && list4.size() > 0) {
            int[] iArr = new int[this.f6347f.size()];
            int i2 = 0;
            for (Integer num : this.f6347f) {
                iArr[i2] = num.intValue();
                i2++;
            }
            polyline.f6325c = iArr;
        }
        List<Integer> list5 = this.f6348g;
        if (list5 != null && list5.size() < this.f6346e.size() - 1) {
            ArrayList arrayList2 = new ArrayList((this.f6346e.size() - 1) - this.f6348g.size());
            List<Integer> list6 = this.f6348g;
            list6.addAll(list6.size(), arrayList2);
        }
        List<Integer> list7 = this.f6348g;
        if (list7 != null && list7.size() > 0) {
            int[] iArr2 = new int[this.f6348g.size()];
            for (Integer num2 : this.f6348g) {
                iArr2[i] = num2.intValue();
                i++;
            }
            polyline.f6326d = iArr2;
        }
        return polyline;
    }

    public PolylineOptions clickable(boolean z) {
        this.f6355n = z;
        return this;
    }

    public PolylineOptions color(int i) {
        this.f6345d = i;
        return this;
    }

    public PolylineOptions colorsValues(List<Integer> list) {
        if (list != null) {
            if (list.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: colors list can not contains null");
            }
            this.f6348g = list;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: colors list can not be null");
    }

    public PolylineOptions customTexture(BitmapDescriptor bitmapDescriptor) {
        this.f6350i = bitmapDescriptor;
        return this;
    }

    public PolylineOptions customTextureList(List<BitmapDescriptor> list) {
        if (list != null) {
            if (list.size() == 0) {
                Log.e("baidumapsdk", "custom texture list is empty,the texture will not work");
            }
            for (BitmapDescriptor bitmapDescriptor : list) {
                if (bitmapDescriptor == null) {
                    Log.e("baidumapsdk", "the custom texture item is null,it will be discard");
                }
            }
            this.f6351j = list;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: customTexture list can not be null");
    }

    public PolylineOptions dottedLine(boolean z) {
        this.f6354m = z;
        return this;
    }

    public PolylineOptions dottedLineType(PolylineDottedLineType polylineDottedLineType) {
        this.f6356o = polylineDottedLineType.ordinal();
        return this;
    }

    public PolylineOptions extraInfo(Bundle bundle) {
        this.f6344c = bundle;
        return this;
    }

    public PolylineOptions focus(boolean z) {
        this.f6352k = z;
        return this;
    }

    public int getColor() {
        return this.f6345d;
    }

    public BitmapDescriptor getCustomTexture() {
        return this.f6350i;
    }

    public List<BitmapDescriptor> getCustomTextureList() {
        return this.f6351j;
    }

    public Bundle getExtraInfo() {
        return this.f6344c;
    }

    public List<LatLng> getPoints() {
        return this.f6346e;
    }

    public List<Integer> getTextureIndexs() {
        return this.f6347f;
    }

    public int getWidth() {
        return this.f6349h;
    }

    public int getZIndex() {
        return this.f6342a;
    }

    public boolean isDottedLine() {
        return this.f6354m;
    }

    public boolean isFocus() {
        return this.f6352k;
    }

    public PolylineOptions isGeodesic(boolean z) {
        this.f6360s = z;
        return this;
    }

    public PolylineOptions isGradient(boolean z) {
        this.f6361t = z;
        return this;
    }

    public PolylineOptions isThined(boolean z) {
        this.f6359r = z;
        return this;
    }

    public boolean isVisible() {
        return this.f6343b;
    }

    public PolylineOptions keepScale(boolean z) {
        this.f6353l = z;
        return this;
    }

    public PolylineOptions lineCapType(LineCapType lineCapType) {
        this.f6358q = lineCapType;
        return this;
    }

    public PolylineOptions lineDirectionCross180(LineDirectionCross180 lineDirectionCross180) {
        this.f6362u = lineDirectionCross180;
        return this;
    }

    public PolylineOptions lineJoinType(LineJoinType lineJoinType) {
        this.f6357p = lineJoinType;
        return this;
    }

    public PolylineOptions points(List<LatLng> list) {
        if (list != null) {
            if (list.size() >= 2) {
                if (list.contains(null)) {
                    throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
                }
                this.f6346e = list;
                return this;
            }
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than 2");
        }
        throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
    }

    public PolylineOptions textureIndex(List<Integer> list) {
        if (list != null) {
            if (list.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: index list can not contains null");
            }
            this.f6347f = list;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: indexs list can not be null");
    }

    public PolylineOptions visible(boolean z) {
        this.f6343b = z;
        return this;
    }

    public PolylineOptions width(int i) {
        if (i > 0) {
            this.f6349h = i;
        }
        return this;
    }

    public PolylineOptions zIndex(int i) {
        this.f6342a = i;
        return this;
    }
}
