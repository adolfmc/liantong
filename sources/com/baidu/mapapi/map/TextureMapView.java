package com.baidu.mapapi.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapsdkplatform.comapi.commonutils.C2888a;
import com.baidu.mapsdkplatform.comapi.map.C2928f;
import com.baidu.mapsdkplatform.comapi.map.C2934j;
import com.baidu.mapsdkplatform.comapi.map.C2946v;
import com.baidu.mapsdkplatform.comapi.map.View$OnTouchListenerC2922ad;
import com.baidu.platform.comapi.map.MapTextureView;
import com.baidu.platform.comapi.util.C3097i;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class TextureMapView extends ViewGroup {

    /* renamed from: a */
    private static final String f6432a = "TextureMapView";

    /* renamed from: i */
    private static String f6433i;

    /* renamed from: j */
    private static int f6434j;

    /* renamed from: k */
    private static int f6435k;

    /* renamed from: q */
    private static final SparseArray<Integer> f6436q = new SparseArray<>();

    /* renamed from: A */
    private int f6437A;

    /* renamed from: B */
    private boolean f6438B;

    /* renamed from: b */
    private MapTextureView f6439b;

    /* renamed from: c */
    private BaiduMap f6440c;

    /* renamed from: d */
    private ImageView f6441d;

    /* renamed from: e */
    private Bitmap f6442e;

    /* renamed from: f */
    private View$OnTouchListenerC2922ad f6443f;

    /* renamed from: g */
    private Point f6444g;

    /* renamed from: h */
    private Point f6445h;

    /* renamed from: l */
    private RelativeLayout f6446l;

    /* renamed from: m */
    private TextView f6447m;

    /* renamed from: n */
    private TextView f6448n;

    /* renamed from: o */
    private ImageView f6449o;

    /* renamed from: p */
    private Context f6450p;

    /* renamed from: r */
    private float f6451r;

    /* renamed from: s */
    private int f6452s;

    /* renamed from: t */
    private boolean f6453t;

    /* renamed from: u */
    private boolean f6454u;

    /* renamed from: v */
    private int f6455v;

    /* renamed from: w */
    private int f6456w;

    /* renamed from: x */
    private int f6457x;

    /* renamed from: y */
    private int f6458y;

    /* renamed from: z */
    private int f6459z;

    static {
        f6436q.append(3, 2000000);
        f6436q.append(4, 1000000);
        f6436q.append(5, 500000);
        f6436q.append(6, 200000);
        f6436q.append(7, 100000);
        f6436q.append(8, 50000);
        f6436q.append(9, 25000);
        f6436q.append(10, 20000);
        f6436q.append(11, 10000);
        f6436q.append(12, 5000);
        f6436q.append(13, 2000);
        f6436q.append(14, 1000);
        f6436q.append(15, 500);
        f6436q.append(16, 200);
        f6436q.append(17, 100);
        f6436q.append(18, 50);
        f6436q.append(19, 20);
        f6436q.append(20, 10);
        f6436q.append(21, 5);
        f6436q.append(22, 2);
    }

    public TextureMapView(Context context) {
        super(context);
        this.f6452s = LogoPosition.logoPostionleftBottom.ordinal();
        this.f6453t = true;
        this.f6454u = true;
        this.f6438B = false;
        m18863a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6452s = LogoPosition.logoPostionleftBottom.ordinal();
        this.f6453t = true;
        this.f6454u = true;
        this.f6438B = false;
        m18863a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6452s = LogoPosition.logoPostionleftBottom.ordinal();
        this.f6453t = true;
        this.f6454u = true;
        this.f6438B = false;
        m18863a(context, (BaiduMapOptions) null);
    }

    public TextureMapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.f6452s = LogoPosition.logoPostionleftBottom.ordinal();
        this.f6453t = true;
        this.f6454u = true;
        this.f6438B = false;
        m18863a(context, baiduMapOptions);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m18864a(android.content.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = "logo_h.png"
            int r1 = com.baidu.mapapi.common.SysOSUtil.getDensityDpi()
            r2 = 180(0xb4, float:2.52E-43)
            if (r1 >= r2) goto Lc
            java.lang.String r0 = "logo_l.png"
        Lc:
            android.graphics.Bitmap r2 = com.baidu.mapsdkplatform.comapi.commonutils.C2888a.m18473a(r0, r10)
            r0 = 480(0x1e0, float:6.73E-43)
            if (r1 <= r0) goto L30
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r0 = 1073741824(0x40000000, float:2.0)
        L1b:
            r7.postScale(r0, r0)
            r3 = 0
            r4 = 0
            int r5 = r2.getWidth()
            int r6 = r2.getHeight()
            r8 = 1
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)
            r9.f6442e = r0
            goto L40
        L30:
            r3 = 320(0x140, float:4.48E-43)
            if (r1 <= r3) goto L3e
            if (r1 > r0) goto L3e
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r0 = 1069547520(0x3fc00000, float:1.5)
            goto L1b
        L3e:
            r9.f6442e = r2
        L40:
            android.graphics.Bitmap r0 = r9.f6442e
            if (r0 == 0) goto L57
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f6441d = r0
            android.widget.ImageView r10 = r9.f6441d
            android.graphics.Bitmap r0 = r9.f6442e
            r10.setImageBitmap(r0)
            android.widget.ImageView r10 = r9.f6441d
            r9.addView(r10)
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.TextureMapView.m18864a(android.content.Context):void");
    }

    /* renamed from: a */
    private void m18863a(Context context, BaiduMapOptions baiduMapOptions) {
        setBackgroundColor(-1);
        this.f6450p = context;
        C2934j.m18235a();
        BMapManager.init();
        m18862a(context, baiduMapOptions, f6433i, f6435k);
        m18864a(context);
        m18852b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f5985h) {
            this.f6443f.setVisibility(4);
        }
        m18849c(context);
        if (baiduMapOptions != null && !baiduMapOptions.f5986i) {
            this.f6446l.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.f5987j != null) {
            this.f6452s = baiduMapOptions.f5987j.ordinal();
        }
        if (baiduMapOptions != null && baiduMapOptions.f5989l != null) {
            this.f6445h = baiduMapOptions.f5989l;
        }
        if (baiduMapOptions == null || baiduMapOptions.f5988k == null) {
            return;
        }
        this.f6444g = baiduMapOptions.f5988k;
    }

    /* renamed from: a */
    private void m18862a(Context context, BaiduMapOptions baiduMapOptions, String str, int i) {
        this.f6439b = new MapTextureView(context);
        addView(this.f6439b);
        if (baiduMapOptions != null) {
            this.f6440c = new BaiduMap(context, this.f6439b, baiduMapOptions.m18979a());
        } else {
            this.f6440c = new BaiduMap(context, this.f6439b, (C2946v) null);
        }
        this.f6439b.getBaseMap().m18335a(new C2750ab(this));
    }

    /* renamed from: a */
    private void m18861a(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i = layoutParams.width;
        int makeMeasureSpec = i > 0 ? View.MeasureSpec.makeMeasureSpec(i, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = layoutParams.height;
        view.measure(makeMeasureSpec, i2 > 0 ? View.MeasureSpec.makeMeasureSpec(i2, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18855a(String str, MapCustomStyleOptions mapCustomStyleOptions) {
        if (TextUtils.isEmpty(str)) {
            str = mapCustomStyleOptions.getLocalCustomStyleFilePath();
            if (TextUtils.isEmpty(str)) {
                return;
            }
        }
        m18854a(str, "");
        setMapCustomStyleEnable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18854a(String str, String str2) {
        MapTextureView mapTextureView = this.f6439b;
        if (mapTextureView == null || mapTextureView.getBaseMap() == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e(f6432a, "customStyleFilePath is empty or null, please check!");
        } else if (!str.endsWith(".sty")) {
            Log.e(f6432a, "customStyleFile format is incorrect , please check!");
        } else if (new File(str).exists()) {
            this.f6439b.getBaseMap().m18323b(str, str2);
        } else {
            Log.e(f6432a, "customStyleFile does not exist , please check!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m18853b() {
        View$OnTouchListenerC2922ad view$OnTouchListenerC2922ad = this.f6443f;
        if (view$OnTouchListenerC2922ad == null || !view$OnTouchListenerC2922ad.m18397a()) {
            return;
        }
        float f = this.f6439b.getBaseMap().m18378E().f7369a;
        this.f6443f.m18389b(f > this.f6439b.getBaseMap().f7272b);
        this.f6443f.m18392a(f < this.f6439b.getBaseMap().f7271a);
    }

    /* renamed from: b */
    private void m18852b(Context context) {
        this.f6443f = new View$OnTouchListenerC2922ad(context);
        if (this.f6443f.m18397a()) {
            this.f6443f.m18390b(new View$OnClickListenerC2751ac(this));
            this.f6443f.m18395a(new View$OnClickListenerC2752ad(this));
            addView(this.f6443f);
        }
    }

    /* renamed from: c */
    private void m18849c(Context context) {
        this.f6446l = new RelativeLayout(context);
        this.f6446l.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.f6447m = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f6447m.setTextColor(Color.parseColor("#FFFFFF"));
        this.f6447m.setTextSize(2, 11.0f);
        TextView textView = this.f6447m;
        textView.setTypeface(textView.getTypeface(), 1);
        this.f6447m.setLayoutParams(layoutParams);
        this.f6447m.setId(Integer.MAX_VALUE);
        this.f6446l.addView(this.f6447m);
        this.f6448n = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.addRule(14);
        this.f6448n.setTextColor(Color.parseColor("#000000"));
        this.f6448n.setTextSize(2, 11.0f);
        this.f6448n.setLayoutParams(layoutParams2);
        this.f6446l.addView(this.f6448n);
        this.f6449o = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.width = -2;
        layoutParams3.height = -2;
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.f6447m.getId());
        this.f6449o.setLayoutParams(layoutParams3);
        Bitmap m18473a = C2888a.m18473a("icon_scale.9.png", context);
        byte[] ninePatchChunk = m18473a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.f6449o.setBackgroundDrawable(new NinePatchDrawable(m18473a, ninePatchChunk, new Rect(), null));
        this.f6446l.addView(this.f6449o);
        addView(this.f6446l);
    }

    /* renamed from: c */
    private boolean m18850c() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Deprecated
    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("BDMapSDKException: customMapStylePath String is illegal");
        }
        if (!new File(str).exists()) {
            throw new RuntimeException("BDMapSDKException: please check whether the customMapStylePath file exits");
        }
        f6433i = str;
    }

    @Deprecated
    public static void setIconCustom(int i) {
        f6435k = i;
    }

    @Deprecated
    public static void setLoadCustomMapStyleFileMode(int i) {
        f6434j = i;
    }

    @Deprecated
    public static void setMapCustomEnable(boolean z) {
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MapViewLayoutParams) {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            super.addView(view, layoutParams);
        }
    }

    public final LogoPosition getLogoPosition() {
        switch (this.f6452s) {
            case 1:
                return LogoPosition.logoPostionleftTop;
            case 2:
                return LogoPosition.logoPostionCenterBottom;
            case 3:
                return LogoPosition.logoPostionCenterTop;
            case 4:
                return LogoPosition.logoPostionRightBottom;
            case 5:
                return LogoPosition.logoPostionRightTop;
            default:
                return LogoPosition.logoPostionleftBottom;
        }
    }

    public final BaiduMap getMap() {
        BaiduMap baiduMap = this.f6440c;
        baiduMap.f5954b = this;
        return baiduMap;
    }

    public final int getMapLevel() {
        return f6436q.get((int) this.f6439b.getBaseMap().m18378E().f7369a).intValue();
    }

    public Point getScaleControlPosition() {
        return this.f6444g;
    }

    public int getScaleControlViewHeight() {
        return this.f6437A;
    }

    public int getScaleControlViewWidth() {
        return this.f6437A;
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
        if (this.f6444g != null) {
            this.f6444g = (Point) bundle.getParcelable("scalePosition");
        }
        if (this.f6445h != null) {
            this.f6445h = (Point) bundle.getParcelable("zoomPosition");
        }
        this.f6453t = bundle.getBoolean("mZoomControlEnabled");
        this.f6454u = bundle.getBoolean("mScaleControlEnabled");
        this.f6452s = bundle.getInt("logoPosition");
        setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
        m18863a(context, new BaiduMapOptions().mapStatus(mapStatus));
    }

    public final void onDestroy() {
        if (this.f6450p != null) {
            this.f6439b.onDestroy();
        }
        Bitmap bitmap = this.f6442e;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f6442e.recycle();
        }
        this.f6443f.m18391b();
        BMapManager.destroy();
        C2934j.m18234b();
        this.f6450p = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    @SuppressLint({"NewApi"})
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int measuredHeight;
        int measuredWidth;
        int height;
        int height2;
        int childCount = getChildCount();
        m18861a(this.f6441d);
        float f2 = 1.0f;
        if (((getWidth() - this.f6455v) - this.f6456w) - this.f6441d.getMeasuredWidth() <= 0 || ((getHeight() - this.f6457x) - this.f6458y) - this.f6441d.getMeasuredHeight() <= 0) {
            this.f6455v = 0;
            this.f6456w = 0;
            this.f6458y = 0;
            this.f6457x = 0;
            f = 1.0f;
        } else {
            f2 = ((getWidth() - this.f6455v) - this.f6456w) / getWidth();
            f = ((getHeight() - this.f6457x) - this.f6458y) / getHeight();
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null) {
                MapTextureView mapTextureView = this.f6439b;
                if (childAt == mapTextureView) {
                    mapTextureView.layout(0, 0, getWidth(), getHeight());
                } else {
                    ImageView imageView = this.f6441d;
                    if (childAt == imageView) {
                        float f3 = f2 * 5.0f;
                        int i6 = (int) (this.f6455v + f3);
                        int i7 = (int) (this.f6456w + f3);
                        float f4 = 5.0f * f;
                        int i8 = (int) (this.f6457x + f4);
                        int i9 = (int) (this.f6458y + f4);
                        switch (this.f6452s) {
                            case 1:
                                measuredHeight = imageView.getMeasuredHeight() + i8;
                                measuredWidth = this.f6441d.getMeasuredWidth() + i6;
                                break;
                            case 2:
                                height = getHeight() - i9;
                                i8 = height - this.f6441d.getMeasuredHeight();
                                int width = (((getWidth() - this.f6441d.getMeasuredWidth()) + this.f6455v) - this.f6456w) / 2;
                                measuredWidth = (((getWidth() + this.f6441d.getMeasuredWidth()) + this.f6455v) - this.f6456w) / 2;
                                measuredHeight = height;
                                i6 = width;
                                break;
                            case 3:
                                height = imageView.getMeasuredHeight() + i8;
                                int width2 = (((getWidth() - this.f6441d.getMeasuredWidth()) + this.f6455v) - this.f6456w) / 2;
                                measuredWidth = (((getWidth() + this.f6441d.getMeasuredWidth()) + this.f6455v) - this.f6456w) / 2;
                                measuredHeight = height;
                                i6 = width2;
                                break;
                            case 4:
                                height2 = getHeight() - i9;
                                i8 = height2 - this.f6441d.getMeasuredHeight();
                                int width3 = getWidth() - i7;
                                int measuredWidth2 = width3 - this.f6441d.getMeasuredWidth();
                                measuredHeight = height2;
                                i6 = measuredWidth2;
                                measuredWidth = width3;
                                break;
                            case 5:
                                height2 = imageView.getMeasuredHeight() + i8;
                                int width32 = getWidth() - i7;
                                int measuredWidth22 = width32 - this.f6441d.getMeasuredWidth();
                                measuredHeight = height2;
                                i6 = measuredWidth22;
                                measuredWidth = width32;
                                break;
                            default:
                                measuredHeight = getHeight() - i9;
                                measuredWidth = this.f6441d.getMeasuredWidth() + i6;
                                i8 = measuredHeight - this.f6441d.getMeasuredHeight();
                                break;
                        }
                        this.f6441d.layout(i6, i8, measuredWidth, measuredHeight);
                    } else {
                        View$OnTouchListenerC2922ad view$OnTouchListenerC2922ad = this.f6443f;
                        if (childAt != view$OnTouchListenerC2922ad) {
                            RelativeLayout relativeLayout = this.f6446l;
                            if (childAt == relativeLayout) {
                                m18861a(relativeLayout);
                                Point point = this.f6444g;
                                if (point == null) {
                                    this.f6437A = this.f6446l.getMeasuredWidth();
                                    this.f6459z = this.f6446l.getMeasuredHeight();
                                    int i10 = (int) (this.f6455v + (5.0f * f2));
                                    int height3 = (getHeight() - ((int) ((this.f6458y + (f * 5.0f)) + 56.0f))) - this.f6441d.getMeasuredHeight();
                                    this.f6446l.layout(i10, height3, this.f6437A + i10, this.f6459z + height3);
                                } else {
                                    this.f6446l.layout(point.x, this.f6444g.y, this.f6444g.x + this.f6446l.getMeasuredWidth(), this.f6444g.y + this.f6446l.getMeasuredHeight());
                                }
                            } else {
                                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                                if (layoutParams instanceof MapViewLayoutParams) {
                                    MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                                    Point m18338a = mapViewLayoutParams.f6202c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f6201b : this.f6439b.getBaseMap() != null ? this.f6439b.getBaseMap().m18338a(CoordUtil.ll2mc(mapViewLayoutParams.f6200a)) : new Point();
                                    m18861a(childAt);
                                    int measuredWidth3 = childAt.getMeasuredWidth();
                                    int measuredHeight2 = childAt.getMeasuredHeight();
                                    int i11 = (int) (m18338a.x - (mapViewLayoutParams.f6203d * measuredWidth3));
                                    int i12 = ((int) (m18338a.y - (mapViewLayoutParams.f6204e * measuredHeight2))) + mapViewLayoutParams.f6205f;
                                    childAt.layout(i11, i12, measuredWidth3 + i11, measuredHeight2 + i12);
                                }
                            }
                        } else if (view$OnTouchListenerC2922ad.m18397a()) {
                            m18861a(this.f6443f);
                            Point point2 = this.f6445h;
                            if (point2 == null) {
                                int height4 = (int) (((getHeight() - 15) * f) + this.f6457x);
                                int width4 = (int) (((getWidth() - 15) * f2) + this.f6455v);
                                int measuredWidth4 = width4 - this.f6443f.getMeasuredWidth();
                                int measuredHeight3 = height4 - this.f6443f.getMeasuredHeight();
                                if (this.f6452s == 4) {
                                    height4 -= this.f6441d.getMeasuredHeight();
                                    measuredHeight3 -= this.f6441d.getMeasuredHeight();
                                }
                                this.f6443f.layout(measuredWidth4, measuredHeight3, width4, height4);
                            } else {
                                this.f6443f.layout(point2.x, this.f6445h.y, this.f6445h.x + this.f6443f.getMeasuredWidth(), this.f6445h.y + this.f6443f.getMeasuredHeight());
                            }
                        }
                    }
                }
            }
        }
    }

    public void onPause() {
        this.f6439b.onPause();
    }

    public void onResume() {
        this.f6439b.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f6440c) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.f6444g;
        if (point != null) {
            bundle.putParcelable("scalePosition", point);
        }
        Point point2 = this.f6445h;
        if (point2 != null) {
            bundle.putParcelable("zoomPosition", point2);
        }
        bundle.putBoolean("mZoomControlEnabled", this.f6453t);
        bundle.putBoolean("mScaleControlEnabled", this.f6454u);
        bundle.putInt("logoPosition", this.f6452s);
        bundle.putInt("paddingLeft", this.f6455v);
        bundle.putInt("paddingTop", this.f6457x);
        bundle.putInt("paddingRight", this.f6456w);
        bundle.putInt("paddingBottom", this.f6458y);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f6441d) {
            return;
        }
        if (m18850c()) {
            super.removeView(view);
        } else {
            C3097i.m17679a(new RunnableC2753ae(this, view), 0L);
        }
    }

    public void setCustomStyleFilePathAndMode(String str, int i) {
    }

    public final void setLogoPosition(LogoPosition logoPosition) {
        if (logoPosition == null) {
            logoPosition = LogoPosition.logoPostionleftBottom;
        }
        this.f6452s = logoPosition.ordinal();
        requestLayout();
    }

    public void setMapCustomStyle(MapCustomStyleOptions mapCustomStyleOptions, CustomMapStyleCallBack customMapStyleCallBack) {
        if (mapCustomStyleOptions == null) {
            return;
        }
        String customMapStyleId = mapCustomStyleOptions.getCustomMapStyleId();
        if (customMapStyleId != null && !customMapStyleId.isEmpty()) {
            C2928f.m18263a().m18258a(this.f6450p, customMapStyleId, new C2749aa(this, customMapStyleCallBack, mapCustomStyleOptions));
            return;
        }
        String localCustomStyleFilePath = mapCustomStyleOptions.getLocalCustomStyleFilePath();
        if (localCustomStyleFilePath == null || localCustomStyleFilePath.isEmpty()) {
            return;
        }
        m18854a(localCustomStyleFilePath, "");
    }

    public void setMapCustomStyleEnable(boolean z) {
        MapTextureView mapTextureView = this.f6439b;
        if (mapTextureView == null) {
            return;
        }
        mapTextureView.getBaseMap().m18284p(z);
    }

    public void setMapCustomStylePath(String str) {
        m18854a(str, "");
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.f6455v = i;
        this.f6457x = i2;
        this.f6456w = i3;
        this.f6458y = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f6444g = point;
            requestLayout();
        }
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f6445h = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f6446l.setVisibility(z ? 0 : 8);
        this.f6454u = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f6443f.m18397a()) {
            this.f6443f.setVisibility(z ? 0 : 8);
            this.f6453t = z;
        }
    }
}
