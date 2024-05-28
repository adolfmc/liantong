package com.baidu.mapapi.map;

import com.baidu.mapapi.model.LatLng;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GradientLineOptions extends OverlayOptions {

    /* renamed from: a */
    private List<LatLng> f6040a;

    /* renamed from: b */
    private List<Integer> f6041b;

    /* renamed from: c */
    private List<Integer> f6042c;

    /* renamed from: d */
    private int f6043d = 5;

    /* renamed from: e */
    private boolean f6044e = true;

    /* renamed from: f */
    private LineDirectionCross180 f6045f = LineDirectionCross180.NONE;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum LineDirectionCross180 {
        NONE,
        FROM_EAST_TO_WEST,
        FROM_WEST_TO_EAST
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.baidu.mapapi.map.OverlayOptions
    /* renamed from: a */
    public Overlay mo18866a() {
        GradientLine gradientLine = new GradientLine();
        gradientLine.f6038d = this.f6043d;
        gradientLine.f6299H = this.f6044e;
        gradientLine.f6039e = this.f6045f;
        List<LatLng> list = this.f6040a;
        if (list == null || list.size() < 2) {
            throw new IllegalStateException("BDMapSDKException: when you add GradientLine, you must at least supply 2 points");
        }
        gradientLine.f6035a = this.f6040a;
        List<Integer> list2 = this.f6042c;
        if (list2 != null) {
            if (list2.size() != 0) {
                int[] iArr = new int[this.f6042c.size()];
                int i = 0;
                int i2 = 0;
                for (Integer num : this.f6042c) {
                    iArr[i2] = num.intValue();
                    i2++;
                }
                gradientLine.f6037c = iArr;
                List<Integer> list3 = this.f6041b;
                if (list3 != null) {
                    if (list3.size() != 0) {
                        int[] iArr2 = new int[this.f6041b.size()];
                        for (Integer num2 : this.f6041b) {
                            iArr2[i] = num2.intValue();
                            i++;
                        }
                        gradientLine.f6036b = iArr2;
                        return gradientLine;
                    }
                    throw new IllegalStateException("BDMapSDKException: Indexs list size can not be Equal to zero");
                }
                throw new IllegalStateException("BDMapSDKException: Indexs list can not be null");
            }
            throw new IllegalStateException("BDMapSDKException: colors list size can not be Equal to zero");
        }
        throw new IllegalStateException("BDMapSDKException: colors list can not be null");
    }

    public List<Integer> getColors() {
        return this.f6042c;
    }

    public List<Integer> getIndexs() {
        return this.f6041b;
    }

    public LineDirectionCross180 getLineDirectionCross180() {
        return this.f6045f;
    }

    public List<LatLng> getPoints() {
        return this.f6040a;
    }

    public int getWidth() {
        return this.f6043d;
    }

    public boolean isVisible() {
        return this.f6044e;
    }

    public GradientLineOptions setColorIndex(List<Integer> list) {
        if (list != null) {
            if (list.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: indexs list can not contains null");
            }
            if (list.size() != 0) {
                this.f6041b = list;
                return this;
            }
            throw new IllegalStateException("BDMapSDKException: indexs list size can not be Equal to zero");
        }
        throw new IllegalArgumentException("BDMapSDKException: indexs list can not be null");
    }

    public GradientLineOptions setColorsValues(List<Integer> list) {
        if (list != null) {
            if (list.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: colors list can not contains null");
            }
            if (list.size() != 0) {
                this.f6042c = list;
                return this;
            }
            throw new IllegalStateException("BDMapSDKException: colors list list size can not be Equal to zero");
        }
        throw new IllegalArgumentException("BDMapSDKException: colors list can not be null");
    }

    public GradientLineOptions setLineDirectionCross180(LineDirectionCross180 lineDirectionCross180) {
        this.f6045f = lineDirectionCross180;
        return this;
    }

    public GradientLineOptions setPoints(List<LatLng> list) {
        if (list != null) {
            if (list.size() >= 2) {
                if (list.contains(null)) {
                    throw new IllegalArgumentException("BDMapSDKException: points list can not contains null");
                }
                this.f6040a = list;
                return this;
            }
            throw new IllegalArgumentException("BDMapSDKException: points count can not less than 2");
        }
        throw new IllegalArgumentException("BDMapSDKException: points list can not be null");
    }

    public GradientLineOptions setWidth(int i) {
        if (i > 0) {
            this.f6043d = i;
        }
        return this;
    }

    public GradientLineOptions visible(boolean z) {
        this.f6044e = z;
        return this;
    }
}
