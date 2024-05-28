package com.baidu.mapapi.map;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.p083v4.util.LongSparseArray;
import android.util.SparseIntArray;
import com.baidu.mapapi.map.HeatMapAnimation;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.util.SysOSUtil;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HeatMap {

    /* renamed from: A */
    private static int f6068A = 0;
    public static final Gradient DEFAULT_GRADIENT;
    public static final int DEFAULT_MAX_HIGH = 200;
    public static final double DEFAULT_OPACITY = 0.6d;
    public static final int DEFAULT_RADIUS = 12;

    /* renamed from: b */
    private static final String f6069b = "HeatMap";

    /* renamed from: c */
    private static final SparseIntArray f6070c = new SparseIntArray();

    /* renamed from: d */
    private static final int[] f6071d;

    /* renamed from: e */
    private static final float[] f6072e;

    /* renamed from: a */
    BaiduMap f6073a;

    /* renamed from: f */
    private int f6074f;

    /* renamed from: g */
    private C2781v<WeightedLatLng> f6075g;

    /* renamed from: h */
    private Collection<WeightedLatLng> f6076h;

    /* renamed from: i */
    private ArrayList<Collection<WeightedLatLng>> f6077i;

    /* renamed from: j */
    private float f6078j;

    /* renamed from: k */
    private float f6079k;

    /* renamed from: l */
    private HeatMapAnimation f6080l;

    /* renamed from: m */
    private HeatMapAnimation f6081m;
    protected boolean mIsSetMaxIntensity;

    /* renamed from: n */
    private int f6082n;

    /* renamed from: o */
    private Gradient f6083o;

    /* renamed from: p */
    private double f6084p;

    /* renamed from: q */
    private C2771m f6085q;

    /* renamed from: r */
    private int[] f6086r;

    /* renamed from: s */
    private float[] f6087s;

    /* renamed from: t */
    private double[] f6088t;

    /* renamed from: u */
    private double[] f6089u;

    /* renamed from: v */
    private List<double[]> f6090v;

    /* renamed from: w */
    private HashMap<String, Tile> f6091w;

    /* renamed from: x */
    private ExecutorService f6092x;

    /* renamed from: y */
    private HashSet<String> f6093y;

    /* renamed from: z */
    private int f6094z;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Builder {

        /* renamed from: a */
        private Collection<WeightedLatLng> f6095a;

        /* renamed from: b */
        private ArrayList<Collection<WeightedLatLng>> f6096b;

        /* renamed from: c */
        private int f6097c = 12;

        /* renamed from: d */
        private Gradient f6098d = HeatMap.DEFAULT_GRADIENT;

        /* renamed from: e */
        private double f6099e = 0.6d;

        /* renamed from: f */
        private int f6100f = 200;

        /* renamed from: g */
        private float f6101g = 1.0f;

        /* renamed from: h */
        private float f6102h = 0.0f;

        /* renamed from: i */
        private boolean f6103i = false;

        /* renamed from: j */
        private HeatMapAnimation f6104j = new HeatMapAnimation(false, 100, HeatMapAnimation.AnimationType.Linear);

        /* renamed from: k */
        private HeatMapAnimation f6105k = new HeatMapAnimation(false, 100, HeatMapAnimation.AnimationType.Linear);

        public HeatMap build() {
            if (this.f6095a == null && this.f6096b == null) {
                throw new IllegalStateException("BDMapSDKException: No input data: you must use either .data or .weightedData before building");
            }
            return new HeatMap(this, null);
        }

        public Builder data(Collection<LatLng> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("BDMapSDKException: No input points.");
            }
            if (collection.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: input points can not contain null.");
            }
            return weightedData(HeatMap.m18946c(collection));
        }

        public Builder datas(List<List<LatLng>> list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("BDMapSDKException: No input datas.");
            }
            if (list.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: input points can not contain null.");
            }
            return weightedDatas(list);
        }

        public Builder frameAnimation(HeatMapAnimation heatMapAnimation) {
            this.f6105k = heatMapAnimation;
            return this;
        }

        public Builder gradient(Gradient gradient) {
            if (gradient != null) {
                this.f6098d = gradient;
                return this;
            }
            throw new IllegalArgumentException("BDMapSDKException: gradient can not be null");
        }

        public Builder initAnimation(HeatMapAnimation heatMapAnimation) {
            this.f6104j = heatMapAnimation;
            return this;
        }

        public Builder maxHigh(int i) {
            int i2 = this.f6100f;
            if (i2 < 0 || i2 > 200) {
                throw new IllegalArgumentException("BDMapSDKException: max_high not within bounds.");
            }
            this.f6100f = i;
            return this;
        }

        public Builder maxIntensity(float f) {
            this.f6101g = f;
            this.f6103i = true;
            return this;
        }

        public Builder minIntensity(float f) {
            this.f6102h = f;
            return this;
        }

        public Builder opacity(double d) {
            this.f6099e = d;
            double d2 = this.f6099e;
            if (d2 < 0.0d || d2 > 1.0d) {
                throw new IllegalArgumentException("BDMapSDKException: Opacity must be in range [0, 1]");
            }
            return this;
        }

        public Builder radius(int i) {
            int i2 = this.f6097c;
            if (i2 < 10 || i2 > 50) {
                throw new IllegalArgumentException("BDMapSDKException: Radius not within bounds.");
            }
            this.f6097c = i;
            return this;
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            if (collection == null || collection.isEmpty()) {
                throw new IllegalArgumentException("BDMapSDKException: No input points.");
            }
            if (collection.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: input points can not contain null.");
            }
            ArrayList arrayList = new ArrayList();
            for (WeightedLatLng weightedLatLng : collection) {
                LatLng latLng = weightedLatLng.mLatLng;
                if (latLng.latitude < 0.37532d || latLng.latitude > 54.562495d || latLng.longitude < 72.508319d || latLng.longitude > 135.942198d) {
                    arrayList.add(weightedLatLng);
                }
            }
            collection.removeAll(arrayList);
            this.f6095a = collection;
            return this;
        }

        public Builder weightedDatas(List<List<LatLng>> list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("BDMapSDKException: No input points.");
            }
            if (list.contains(null)) {
                throw new IllegalArgumentException("BDMapSDKException: input points can not contain null.");
            }
            ArrayList<Collection<WeightedLatLng>> arrayList = new ArrayList<>();
            for (List<LatLng> list2 : list) {
                Collection<WeightedLatLng> m18946c = HeatMap.m18946c(list2);
                ArrayList arrayList2 = new ArrayList();
                for (WeightedLatLng weightedLatLng : m18946c) {
                    LatLng latLng = weightedLatLng.mLatLng;
                    if (latLng.latitude < 0.37532d || latLng.latitude > 54.562495d || latLng.longitude < 72.508319d || latLng.longitude > 135.942198d) {
                        arrayList2.add(weightedLatLng);
                    }
                }
                m18946c.removeAll(arrayList2);
                arrayList.add(m18946c);
                this.f6096b = arrayList;
            }
            return this;
        }
    }

    static {
        f6070c.put(3, 8388608);
        f6070c.put(4, 4194304);
        f6070c.put(5, 2097152);
        f6070c.put(6, 1048576);
        f6070c.put(7, 524288);
        f6070c.put(8, 262144);
        f6070c.put(9, 131072);
        f6070c.put(10, 65536);
        f6070c.put(11, 32768);
        f6070c.put(12, 16384);
        f6070c.put(13, 8192);
        f6070c.put(14, 4096);
        f6070c.put(15, 2048);
        f6070c.put(16, 1024);
        f6070c.put(17, 512);
        f6070c.put(18, 256);
        f6070c.put(19, 128);
        f6070c.put(20, 64);
        f6071d = new int[]{Color.rgb(255, 0, 0), Color.rgb(0, 225, 0), Color.rgb(0, 0, 200)};
        f6072e = new float[]{0.08f, 0.4f, 1.0f};
        DEFAULT_GRADIENT = new Gradient(f6071d, f6072e);
        f6068A = 0;
    }

    private HeatMap(Builder builder) {
        Collection<WeightedLatLng> collection;
        this.f6074f = 200;
        this.f6094z = SysOSUtil.getInstance().getScreenWidth() / 2;
        this.f6091w = new HashMap<>();
        this.f6092x = Executors.newFixedThreadPool(1);
        this.f6093y = new HashSet<>();
        this.f6077i = builder.f6096b;
        this.f6082n = builder.f6097c;
        this.mIsSetMaxIntensity = builder.f6103i;
        if (!this.mIsSetMaxIntensity && this.f6077i != null) {
            this.f6090v = new ArrayList();
            for (int i = 0; i < this.f6077i.size(); i++) {
                this.f6076h = this.f6077i.get(i);
                this.f6085q = m18944d(this.f6076h);
                this.f6090v.add(m18962a(this.f6082n));
            }
        }
        this.f6076h = builder.f6095a;
        if (!this.mIsSetMaxIntensity && (collection = this.f6076h) != null) {
            m18948b(collection);
        }
        this.f6081m = builder.f6104j;
        this.f6080l = builder.f6105k;
        this.f6074f = builder.f6100f;
        this.f6078j = builder.f6101g;
        this.f6079k = builder.f6102h;
        this.f6083o = builder.f6098d;
        this.f6084p = builder.f6099e;
        int i2 = this.f6082n;
        this.f6088t = m18961a(i2, i2 / 3.0d);
        m18957a(this.f6083o);
    }

    /* synthetic */ HeatMap(Builder builder, RunnableC2773o runnableC2773o) {
        this(builder);
    }

    /* renamed from: a */
    private static double m18953a(Collection<WeightedLatLng> collection, C2771m c2771m, int i, int i2) {
        double d = c2771m.f6551a;
        double d2 = c2771m.f6553c;
        double d3 = c2771m.f6552b;
        double d4 = d2 - d;
        double d5 = c2771m.f6554d - d3;
        if (d4 <= d5) {
            d4 = d5;
        }
        double d6 = ((int) ((i2 / (i * 2)) + 0.5d)) / d4;
        LongSparseArray longSparseArray = new LongSparseArray();
        double d7 = 0.0d;
        for (WeightedLatLng weightedLatLng : collection) {
            int i3 = (int) ((weightedLatLng.getPoint().y - d3) * d6);
            long j = (int) ((weightedLatLng.getPoint().x - d) * d6);
            LongSparseArray longSparseArray2 = (LongSparseArray) longSparseArray.get(j);
            if (longSparseArray2 == null) {
                longSparseArray2 = new LongSparseArray();
                longSparseArray.put(j, longSparseArray2);
            }
            long j2 = i3;
            Double d8 = (Double) longSparseArray2.get(j2);
            if (d8 == null) {
                d8 = Double.valueOf(0.0d);
            }
            LongSparseArray longSparseArray3 = longSparseArray;
            double d9 = d;
            Double valueOf = Double.valueOf(d8.doubleValue() + weightedLatLng.intensity);
            longSparseArray2.put(j2, valueOf);
            if (valueOf.doubleValue() > d7) {
                d7 = valueOf.doubleValue();
            }
            longSparseArray = longSparseArray3;
            d = d9;
        }
        return d7;
    }

    /* renamed from: a */
    private static Bitmap m18951a(double[][] dArr, int[] iArr, double d) {
        int i = iArr[iArr.length - 1];
        double length = (iArr.length - 1) / d;
        int length2 = dArr.length;
        int[] iArr2 = new int[length2 * length2];
        for (int i2 = 0; i2 < length2; i2++) {
            for (int i3 = 0; i3 < length2; i3++) {
                double d2 = dArr[i3][i2];
                int i4 = (i2 * length2) + i3;
                int i5 = (int) (d2 * length);
                if (d2 == 0.0d) {
                    iArr2[i4] = 0;
                } else if (i5 < iArr.length) {
                    iArr2[i4] = iArr[i5];
                } else {
                    iArr2[i4] = i;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    /* renamed from: a */
    private HeatMapData m18960a(int i, int i2) {
        if (this.f6076h == null) {
            return null;
        }
        double[] dArr = this.f6089u;
        return new HeatMapData(this.f6076h, dArr != null ? (float) dArr[i2] : 0.0f);
    }

    /* renamed from: a */
    private static Tile m18958a(Bitmap bitmap) {
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getWidth() * bitmap.getHeight() * 4);
        bitmap.copyPixelsToBuffer(allocate);
        return new Tile(256, 256, allocate.array());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18959a(int i, int i2, int i3) {
        int i4;
        double d = f6070c.get(i3);
        double d2 = (this.f6082n * d) / 256.0d;
        double d3 = ((2.0d * d2) + d) / ((i4 * 2) + 256);
        if (i < 0 || i2 < 0) {
            return;
        }
        double d4 = (i * d) - d2;
        double d5 = ((i + 1) * d) + d2;
        double d6 = (i2 * d) - d2;
        double d7 = ((i2 + 1) * d) + d2;
        C2771m c2771m = new C2771m(d4, d5, d6, d7);
        if (c2771m.m18787a(new C2771m(this.f6085q.f6551a - d2, this.f6085q.f6553c + d2, this.f6085q.f6552b - d2, this.f6085q.f6554d + d2))) {
            Collection<WeightedLatLng> m18779a = this.f6075g.m18779a(c2771m);
            if (m18779a.isEmpty()) {
                return;
            }
            int i5 = this.f6082n;
            double[][] dArr = (double[][]) Array.newInstance(double.class, (i5 * 2) + 256, (i5 * 2) + 256);
            for (WeightedLatLng weightedLatLng : m18779a) {
                Point point = weightedLatLng.getPoint();
                int i6 = (int) ((point.x - d4) / d3);
                double d8 = d4;
                int i7 = (int) ((d7 - point.y) / d3);
                int i8 = this.f6082n;
                if (i6 >= (i8 * 2) + 256) {
                    i6 = ((i8 * 2) + 256) - 1;
                }
                int i9 = this.f6082n;
                if (i7 >= (i9 * 2) + 256) {
                    i7 = ((i9 * 2) + 256) - 1;
                }
                double[] dArr2 = dArr[i6];
                dArr2[i7] = dArr2[i7] + weightedLatLng.intensity;
                d4 = d8;
                d7 = d7;
            }
            Bitmap m18951a = m18951a(m18952a(dArr, this.f6088t), this.f6086r, this.f6089u[i3 - 1]);
            Tile m18958a = m18958a(m18951a);
            m18951a.recycle();
            m18955a(i + "_" + i2 + "_" + i3, m18958a);
            if (this.f6091w.size() > f6068A) {
                m18963a();
            }
            BaiduMap baiduMap = this.f6073a;
            if (baiduMap != null) {
                baiduMap.m19022a();
            }
        }
    }

    /* renamed from: a */
    private void m18957a(Gradient gradient) {
        this.f6083o = gradient;
        this.f6086r = gradient.m18972a(this.f6084p);
        this.f6087s = gradient.m18973a();
    }

    /* renamed from: a */
    private synchronized void m18955a(String str, Tile tile) {
        this.f6091w.put(str, tile);
    }

    /* renamed from: a */
    private double[] m18962a(int i) {
        int i2;
        double[] dArr = new double[22];
        int i3 = 4;
        while (true) {
            if (i3 >= 11) {
                break;
            }
            dArr[i3] = m18953a(this.f6076h, this.f6085q, i, (int) (Math.pow(2.0d, i3 - 3) * 1280.0d));
            if (i3 == 4) {
                for (int i4 = 0; i4 < i3; i4++) {
                    dArr[i4] = dArr[i3];
                }
            }
            i3++;
        }
        for (i2 = 11; i2 < 22; i2++) {
            dArr[i2] = dArr[10];
        }
        return dArr;
    }

    /* renamed from: a */
    private static double[] m18961a(int i, double d) {
        double[] dArr = new double[(i * 2) + 1];
        for (int i2 = -i; i2 <= i; i2++) {
            dArr[i2 + i] = Math.exp(((-i2) * i2) / ((2.0d * d) * d));
        }
        return dArr;
    }

    /* renamed from: a */
    private static double[][] m18952a(double[][] dArr, double[] dArr2) {
        int floor = (int) Math.floor(dArr2.length / 2.0d);
        int length = dArr.length;
        int i = length - (floor * 2);
        int i2 = (floor + i) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance(double.class, length, length);
        for (int i3 = 0; i3 < length; i3++) {
            for (int i4 = 0; i4 < length; i4++) {
                double d = dArr[i3][i4];
                if (d != 0.0d) {
                    int i5 = i3 + floor;
                    if (i2 < i5) {
                        i5 = i2;
                    }
                    int i6 = i5 + 1;
                    int i7 = i3 - floor;
                    for (int i8 = floor > i7 ? floor : i7; i8 < i6; i8++) {
                        double[] dArr4 = dArr3[i8];
                        dArr4[i4] = dArr4[i4] + (dArr2[i8 - i7] * d);
                    }
                }
            }
        }
        double[][] dArr5 = (double[][]) Array.newInstance(double.class, i, i);
        for (int i9 = floor; i9 < i2 + 1; i9++) {
            for (int i10 = 0; i10 < length; i10++) {
                double d2 = dArr3[i9][i10];
                if (d2 != 0.0d) {
                    int i11 = i10 + floor;
                    if (i2 < i11) {
                        i11 = i2;
                    }
                    int i12 = i11 + 1;
                    int i13 = i10 - floor;
                    for (int i14 = floor > i13 ? floor : i13; i14 < i12; i14++) {
                        double[] dArr6 = dArr5[i9 - floor];
                        int i15 = i14 - floor;
                        dArr6[i15] = dArr6[i15] + (dArr2[i14 - i13] * d2);
                    }
                }
            }
        }
        return dArr5;
    }

    /* renamed from: b */
    private HeatMapData m18949b(int i, int i2) {
        ArrayList<Collection<WeightedLatLng>> arrayList = this.f6077i;
        if (arrayList != null && i < arrayList.size()) {
            Collection<WeightedLatLng> collection = this.f6077i.get(i);
            float f = 0.0f;
            List<double[]> list = this.f6090v;
            if (list != null && list.size() > i) {
                f = (float) this.f6090v.get(i)[i2];
            }
            return new HeatMapData(collection, f);
        }
        return null;
    }

    /* renamed from: b */
    private void m18948b(Collection<WeightedLatLng> collection) {
        this.f6076h = collection;
        if (this.f6076h.isEmpty()) {
            throw new IllegalArgumentException("BDMapSDKException: No input points.");
        }
        this.f6085q = m18944d(this.f6076h);
        this.f6075g = new C2781v<>(this.f6085q);
        for (WeightedLatLng weightedLatLng : this.f6076h) {
            this.f6075g.m18777a((C2781v<WeightedLatLng>) weightedLatLng);
        }
        this.f6089u = m18962a(this.f6082n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static Collection<WeightedLatLng> m18946c(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : collection) {
            arrayList.add(new WeightedLatLng(latLng));
        }
        return arrayList;
    }

    /* renamed from: d */
    private static C2771m m18944d(Collection<WeightedLatLng> collection) {
        Iterator<WeightedLatLng> it = collection.iterator();
        WeightedLatLng next = it.next();
        double d = next.getPoint().x;
        double d2 = next.getPoint().x;
        double d3 = next.getPoint().y;
        double d4 = next.getPoint().y;
        while (it.hasNext()) {
            WeightedLatLng next2 = it.next();
            double d5 = next2.getPoint().x;
            double d6 = next2.getPoint().y;
            if (d5 < d) {
                d = d5;
            }
            if (d5 > d2) {
                d2 = d5;
            }
            if (d6 < d3) {
                d3 = d6;
            }
            if (d6 > d4) {
                d4 = d6;
            }
        }
        return new C2771m(d, d2, d3, d4);
    }

    /* renamed from: d */
    private synchronized void m18945d() {
        this.f6091w.clear();
    }

    /* renamed from: a */
    synchronized void m18963a() {
        this.f6093y.clear();
        this.f6091w.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m18950b() {
        m18945d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public void m18947c() {
        this.f6092x.shutdownNow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HeatMapData getData(int i, int i2) {
        if (i2 > 22 || i2 < 4 || (this.f6077i == null && this.f6076h == null)) {
            return null;
        }
        if (this.f6077i != null) {
            return m18949b(i, i2);
        }
        if (this.f6076h != null) {
            return m18960a(i, i2);
        }
        return null;
    }

    public void removeHeatMap() {
        BaiduMap baiduMap = this.f6073a;
        if (baiduMap != null) {
            baiduMap.m19014a(this);
        }
        ArrayList<Collection<WeightedLatLng>> arrayList = this.f6077i;
        if (arrayList != null) {
            arrayList.clear();
        }
        Collection<WeightedLatLng> collection = this.f6076h;
        if (collection != null) {
            collection.clear();
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("grid_size", this.f6094z);
        bundle.putFloat("point_size", this.f6082n * 2);
        bundle.putFloat("max_hight", this.f6074f);
        bundle.putFloat("alpha", (float) this.f6084p);
        ArrayList<Collection<WeightedLatLng>> arrayList = this.f6077i;
        if (arrayList != null) {
            bundle.putInt("frame_count", arrayList.size());
        } else if (this.f6076h != null) {
            bundle.putInt("frame_count", 1);
        }
        bundle.putIntArray("color_array", this.f6086r);
        bundle.putFloatArray("color_start_points", this.f6087s);
        bundle.putBoolean("is_need_init_animation", this.f6081m.getIsAnimation());
        bundle.putBoolean("is_need_frame_animation", this.f6080l.getIsAnimation());
        bundle.putInt("init_animation_duration", this.f6081m.getDuration());
        bundle.putInt("init_animation_type", this.f6081m.getAnimationType());
        bundle.putInt("frame_animation_duration", this.f6080l.getDuration());
        bundle.putInt("frame_animation_type", this.f6080l.getAnimationType());
        bundle.putFloat("max_intentity", this.f6078j);
        bundle.putFloat("min_intentity", this.f6079k);
        return bundle;
    }
}
