package com.baidu.mapapi.map;

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
import android.util.SparseIntArray;
import android.view.MotionEvent;
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
import com.baidu.platform.comapi.map.MapSurfaceView;
import com.baidu.platform.comapi.util.C3097i;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MapView extends ViewGroup {

    /* renamed from: a */
    private static final String f6172a = "MapView";

    /* renamed from: b */
    private static String f6173b;

    /* renamed from: c */
    private static int f6174c;

    /* renamed from: d */
    private static int f6175d;

    /* renamed from: q */
    private static final SparseIntArray f6176q = new SparseIntArray();

    /* renamed from: A */
    private int f6177A;

    /* renamed from: B */
    private boolean f6178B;

    /* renamed from: e */
    private MapSurfaceView f6179e;

    /* renamed from: f */
    private BaiduMap f6180f;

    /* renamed from: g */
    private ImageView f6181g;

    /* renamed from: h */
    private Bitmap f6182h;

    /* renamed from: i */
    private View$OnTouchListenerC2922ad f6183i;

    /* renamed from: j */
    private Point f6184j;

    /* renamed from: k */
    private Point f6185k;

    /* renamed from: l */
    private RelativeLayout f6186l;

    /* renamed from: m */
    private TextView f6187m;

    /* renamed from: n */
    private TextView f6188n;

    /* renamed from: o */
    private ImageView f6189o;

    /* renamed from: p */
    private Context f6190p;

    /* renamed from: r */
    private int f6191r;

    /* renamed from: s */
    private boolean f6192s;

    /* renamed from: t */
    private boolean f6193t;

    /* renamed from: u */
    private float f6194u;

    /* renamed from: v */
    private int f6195v;

    /* renamed from: w */
    private int f6196w;

    /* renamed from: x */
    private int f6197x;

    /* renamed from: y */
    private int f6198y;

    /* renamed from: z */
    private int f6199z;

    static {
        f6176q.append(3, 2000000);
        f6176q.append(4, 1000000);
        f6176q.append(5, 500000);
        f6176q.append(6, 200000);
        f6176q.append(7, 100000);
        f6176q.append(8, 50000);
        f6176q.append(9, 25000);
        f6176q.append(10, 20000);
        f6176q.append(11, 10000);
        f6176q.append(12, 5000);
        f6176q.append(13, 2000);
        f6176q.append(14, 1000);
        f6176q.append(15, 500);
        f6176q.append(16, 200);
        f6176q.append(17, 100);
        f6176q.append(18, 50);
        f6176q.append(19, 20);
        f6176q.append(20, 10);
        f6176q.append(21, 5);
        f6176q.append(22, 2);
        f6176q.append(23, 2);
        f6176q.append(24, 2);
        f6176q.append(25, 2);
        f6176q.append(26, 2);
    }

    public MapView(Context context) {
        super(context);
        this.f6191r = LogoPosition.logoPostionleftBottom.ordinal();
        this.f6192s = true;
        this.f6193t = true;
        this.f6178B = false;
        m18916a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6191r = LogoPosition.logoPostionleftBottom.ordinal();
        this.f6192s = true;
        this.f6193t = true;
        this.f6178B = false;
        m18916a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6191r = LogoPosition.logoPostionleftBottom.ordinal();
        this.f6192s = true;
        this.f6193t = true;
        this.f6178B = false;
        m18916a(context, (BaiduMapOptions) null);
    }

    public MapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.f6191r = LogoPosition.logoPostionleftBottom.ordinal();
        this.f6192s = true;
        this.f6193t = true;
        this.f6178B = false;
        m18916a(context, baiduMapOptions);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m18917a(android.content.Context r10) {
        /*
            r9 = this;
            java.lang.String r0 = "logo_h.png"
            int r1 = com.baidu.mapapi.common.SysOSUtil.getDensityDpi()
            r2 = 180(0xb4, float:2.52E-43)
            if (r1 >= r2) goto Lc
            java.lang.String r0 = "logo_l.png"
        Lc:
            android.graphics.Bitmap r2 = com.baidu.mapsdkplatform.comapi.commonutils.C2888a.m18473a(r0, r10)
            if (r2 != 0) goto L13
            return
        L13:
            r0 = 480(0x1e0, float:6.73E-43)
            if (r1 <= r0) goto L33
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r0 = 1073741824(0x40000000, float:2.0)
        L1e:
            r7.postScale(r0, r0)
            r3 = 0
            r4 = 0
            int r5 = r2.getWidth()
            int r6 = r2.getHeight()
            r8 = 1
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r2, r3, r4, r5, r6, r7, r8)
            r9.f6182h = r0
            goto L41
        L33:
            r0 = 320(0x140, float:4.48E-43)
            if (r1 <= r0) goto L3f
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r0 = 1069547520(0x3fc00000, float:1.5)
            goto L1e
        L3f:
            r9.f6182h = r2
        L41:
            android.graphics.Bitmap r0 = r9.f6182h
            if (r0 == 0) goto L58
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f6181g = r0
            android.widget.ImageView r10 = r9.f6181g
            android.graphics.Bitmap r0 = r9.f6182h
            r10.setImageBitmap(r0)
            android.widget.ImageView r10 = r9.f6181g
            r9.addView(r10)
        L58:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.MapView.m18917a(android.content.Context):void");
    }

    /* renamed from: a */
    private void m18916a(Context context, BaiduMapOptions baiduMapOptions) {
        this.f6190p = context;
        C2934j.m18235a();
        BMapManager.init();
        m18915a(context, baiduMapOptions, f6173b, f6174c);
        m18917a(context);
        m18905b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f5985h) {
            this.f6183i.setVisibility(4);
        }
        m18902c(context);
        if (baiduMapOptions != null && !baiduMapOptions.f5986i) {
            this.f6186l.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.f5987j != null) {
            this.f6191r = baiduMapOptions.f5987j.ordinal();
        }
        if (baiduMapOptions != null && baiduMapOptions.f5989l != null) {
            this.f6185k = baiduMapOptions.f5989l;
        }
        if (baiduMapOptions == null || baiduMapOptions.f5988k == null) {
            return;
        }
        this.f6184j = baiduMapOptions.f5988k;
    }

    /* renamed from: a */
    private void m18915a(Context context, BaiduMapOptions baiduMapOptions, String str, int i) {
        this.f6179e = new MapSurfaceView(context);
        if (baiduMapOptions != null) {
            this.f6180f = new BaiduMap(context, this.f6179e, baiduMapOptions.m18979a());
        } else {
            this.f6180f = new BaiduMap(context, this.f6179e, (C2946v) null);
        }
        addView(this.f6179e);
        C2777r c2777r = new C2777r(this);
        if (this.f6179e.getBaseMap() != null) {
            this.f6179e.getBaseMap().m18335a(c2777r);
        }
    }

    /* renamed from: a */
    private void m18914a(View view) {
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
    public void m18908a(String str, MapCustomStyleOptions mapCustomStyleOptions) {
        if (TextUtils.isEmpty(str)) {
            str = mapCustomStyleOptions.getLocalCustomStyleFilePath();
            if (TextUtils.isEmpty(str)) {
                return;
            }
        }
        m18907a(str, "");
        setMapCustomStyleEnable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18907a(String str, String str2) {
        MapSurfaceView mapSurfaceView = this.f6179e;
        if (mapSurfaceView == null || mapSurfaceView.getBaseMap() == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e(f6172a, "customStyleFilePath is empty or null, please check!");
        } else if (!str.endsWith(".sty")) {
            Log.e(f6172a, "customStyleFile format is incorrect , please check!");
        } else if (new File(str).exists()) {
            this.f6179e.getBaseMap().m18323b(str, str2);
        } else {
            Log.e(f6172a, "customStyleFile does not exist , please check!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m18906b() {
        View$OnTouchListenerC2922ad view$OnTouchListenerC2922ad = this.f6183i;
        if (view$OnTouchListenerC2922ad == null || !view$OnTouchListenerC2922ad.m18397a() || this.f6179e.getBaseMap() == null) {
            return;
        }
        float f = this.f6179e.getBaseMap().m18378E().f7369a;
        this.f6183i.m18389b(f > this.f6179e.getBaseMap().f7272b);
        this.f6183i.m18392a(f < this.f6179e.getBaseMap().f7271a);
    }

    /* renamed from: b */
    private void m18905b(Context context) {
        this.f6183i = new View$OnTouchListenerC2922ad(context, false);
        if (this.f6183i.m18397a()) {
            this.f6183i.m18390b(new View$OnClickListenerC2778s(this));
            this.f6183i.m18395a(new View$OnClickListenerC2779t(this));
            addView(this.f6183i);
        }
    }

    /* renamed from: c */
    private void m18902c(Context context) {
        this.f6186l = new RelativeLayout(context);
        this.f6186l.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.f6187m = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f6187m.setTextColor(Color.parseColor("#FFFFFF"));
        this.f6187m.setTextSize(2, 11.0f);
        TextView textView = this.f6187m;
        textView.setTypeface(textView.getTypeface(), 1);
        this.f6187m.setLayoutParams(layoutParams);
        this.f6187m.setId(Integer.MAX_VALUE);
        this.f6186l.addView(this.f6187m);
        this.f6188n = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.addRule(14);
        this.f6188n.setTextColor(Color.parseColor("#000000"));
        this.f6188n.setTextSize(2, 11.0f);
        this.f6188n.setLayoutParams(layoutParams2);
        this.f6186l.addView(this.f6188n);
        this.f6189o = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.width = -2;
        layoutParams3.height = -2;
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.f6187m.getId());
        ImageView imageView = this.f6189o;
        if (imageView != null) {
            imageView.setLayoutParams(layoutParams3);
            Bitmap m18473a = C2888a.m18473a("icon_scale.9.png", context);
            if (m18473a != null) {
                byte[] ninePatchChunk = m18473a.getNinePatchChunk();
                if (NinePatch.isNinePatchChunk(ninePatchChunk)) {
                    this.f6189o.setBackgroundDrawable(new NinePatchDrawable(m18473a, ninePatchChunk, new Rect(), null));
                }
            }
            this.f6186l.addView(this.f6189o);
        }
        addView(this.f6186l);
    }

    /* renamed from: c */
    private boolean m18903c() {
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
        f6173b = str;
    }

    @Deprecated
    public static void setIconCustom(int i) {
        f6175d = i;
    }

    @Deprecated
    public static void setLoadCustomMapStyleFileMode(int i) {
        f6174c = i;
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

    public void cancelRenderMap() {
    }

    public final LogoPosition getLogoPosition() {
        switch (this.f6191r) {
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
        BaiduMap baiduMap = this.f6180f;
        baiduMap.f5953a = this;
        return baiduMap;
    }

    public final int getMapLevel() {
        return f6176q.get(Math.round(this.f6179e.getZoomLevel()));
    }

    public Point getScaleControlPosition() {
        return this.f6184j;
    }

    public int getScaleControlViewHeight() {
        return this.f6199z;
    }

    public int getScaleControlViewWidth() {
        return this.f6177A;
    }

    public Point getZoomControlsPosition() {
        return this.f6185k;
    }

    public boolean handleMultiTouch(float f, float f2, float f3, float f4) {
        return false;
    }

    public void handleTouchDown(float f, float f2) {
        if (this.f6179e == null) {
        }
    }

    public boolean handleTouchMove(float f, float f2) {
        return false;
    }

    public boolean handleTouchUp(float f, float f2) {
        if (this.f6179e == null) {
        }
        return false;
    }

    public boolean inRangeOfView(float f, float f2) {
        MapSurfaceView mapSurfaceView = this.f6179e;
        return mapSurfaceView != null && mapSurfaceView.inRangeOfView(f, f2);
    }

    public boolean isShowScaleControl() {
        return this.f6193t;
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
        if (this.f6184j != null) {
            this.f6184j = (Point) bundle.getParcelable("scalePosition");
        }
        if (this.f6185k != null) {
            this.f6185k = (Point) bundle.getParcelable("zoomPosition");
        }
        this.f6192s = bundle.getBoolean("mZoomControlEnabled");
        this.f6193t = bundle.getBoolean("mScaleControlEnabled");
        this.f6191r = bundle.getInt("logoPosition");
        setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
        m18916a(context, new BaiduMapOptions().mapStatus(mapStatus));
    }

    public final void onDestroy() {
        BaiduMap baiduMap = this.f6180f;
        if (baiduMap != null) {
            baiduMap.m19005c();
        }
        MapSurfaceView mapSurfaceView = this.f6179e;
        if (mapSurfaceView != null) {
            mapSurfaceView.unInit();
        }
        Bitmap bitmap = this.f6182h;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f6182h.recycle();
            this.f6182h = null;
        }
        if (f6173b != null) {
            f6173b = null;
        }
        this.f6183i.m18391b();
        BMapManager.destroy();
        C2934j.m18234b();
        this.f6190p = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int measuredHeight;
        int measuredWidth;
        int height;
        int height2;
        int childCount = getChildCount();
        m18914a(this.f6181g);
        float f2 = 1.0f;
        if (((getWidth() - this.f6195v) - this.f6196w) - this.f6181g.getMeasuredWidth() <= 0 || ((getHeight() - this.f6197x) - this.f6198y) - this.f6181g.getMeasuredHeight() <= 0) {
            this.f6195v = 0;
            this.f6196w = 0;
            this.f6198y = 0;
            this.f6197x = 0;
            f = 1.0f;
        } else {
            f2 = ((getWidth() - this.f6195v) - this.f6196w) / getWidth();
            f = ((getHeight() - this.f6197x) - this.f6198y) / getHeight();
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null) {
                MapSurfaceView mapSurfaceView = this.f6179e;
                if (childAt == mapSurfaceView) {
                    mapSurfaceView.layout(0, 0, getWidth(), getHeight());
                } else {
                    ImageView imageView = this.f6181g;
                    if (childAt == imageView) {
                        float f3 = f2 * 5.0f;
                        int i6 = (int) (this.f6195v + f3);
                        int i7 = (int) (this.f6196w + f3);
                        float f4 = 5.0f * f;
                        int i8 = (int) (this.f6197x + f4);
                        int i9 = (int) (this.f6198y + f4);
                        switch (this.f6191r) {
                            case 1:
                                measuredHeight = imageView.getMeasuredHeight() + i8;
                                measuredWidth = this.f6181g.getMeasuredWidth() + i6;
                                break;
                            case 2:
                                height = getHeight() - i9;
                                i8 = height - this.f6181g.getMeasuredHeight();
                                int width = (((getWidth() - this.f6181g.getMeasuredWidth()) + this.f6195v) - this.f6196w) / 2;
                                measuredWidth = (((getWidth() + this.f6181g.getMeasuredWidth()) + this.f6195v) - this.f6196w) / 2;
                                measuredHeight = height;
                                i6 = width;
                                break;
                            case 3:
                                height = imageView.getMeasuredHeight() + i8;
                                int width2 = (((getWidth() - this.f6181g.getMeasuredWidth()) + this.f6195v) - this.f6196w) / 2;
                                measuredWidth = (((getWidth() + this.f6181g.getMeasuredWidth()) + this.f6195v) - this.f6196w) / 2;
                                measuredHeight = height;
                                i6 = width2;
                                break;
                            case 4:
                                height2 = getHeight() - i9;
                                i8 = height2 - this.f6181g.getMeasuredHeight();
                                int width3 = getWidth() - i7;
                                int measuredWidth2 = width3 - this.f6181g.getMeasuredWidth();
                                measuredHeight = height2;
                                i6 = measuredWidth2;
                                measuredWidth = width3;
                                break;
                            case 5:
                                height2 = imageView.getMeasuredHeight() + i8;
                                int width32 = getWidth() - i7;
                                int measuredWidth22 = width32 - this.f6181g.getMeasuredWidth();
                                measuredHeight = height2;
                                i6 = measuredWidth22;
                                measuredWidth = width32;
                                break;
                            default:
                                measuredHeight = getHeight() - i9;
                                measuredWidth = this.f6181g.getMeasuredWidth() + i6;
                                i8 = measuredHeight - this.f6181g.getMeasuredHeight();
                                break;
                        }
                        this.f6181g.layout(i6, i8, measuredWidth, measuredHeight);
                    } else {
                        View$OnTouchListenerC2922ad view$OnTouchListenerC2922ad = this.f6183i;
                        if (childAt != view$OnTouchListenerC2922ad) {
                            RelativeLayout relativeLayout = this.f6186l;
                            if (childAt == relativeLayout) {
                                m18914a(relativeLayout);
                                Point point = this.f6184j;
                                if (point == null) {
                                    this.f6177A = this.f6186l.getMeasuredWidth();
                                    this.f6199z = this.f6186l.getMeasuredHeight();
                                    int i10 = (int) (this.f6195v + (5.0f * f2));
                                    int height3 = (getHeight() - ((int) ((this.f6198y + (f * 5.0f)) + 56.0f))) - this.f6181g.getMeasuredHeight();
                                    this.f6186l.layout(i10, height3, this.f6177A + i10, this.f6199z + height3);
                                } else {
                                    this.f6186l.layout(point.x, this.f6184j.y, this.f6184j.x + this.f6186l.getMeasuredWidth(), this.f6184j.y + this.f6186l.getMeasuredHeight());
                                }
                            } else {
                                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                                if (layoutParams instanceof MapViewLayoutParams) {
                                    MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                                    Point m18338a = mapViewLayoutParams.f6202c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f6201b : this.f6179e.getBaseMap() != null ? this.f6179e.getBaseMap().m18338a(CoordUtil.ll2mc(mapViewLayoutParams.f6200a)) : new Point();
                                    m18914a(childAt);
                                    int measuredWidth3 = childAt.getMeasuredWidth();
                                    int measuredHeight2 = childAt.getMeasuredHeight();
                                    int i11 = (int) (m18338a.x - (mapViewLayoutParams.f6203d * measuredWidth3));
                                    int i12 = ((int) (m18338a.y - (mapViewLayoutParams.f6204e * measuredHeight2))) + mapViewLayoutParams.f6205f;
                                    childAt.layout(i11, i12, measuredWidth3 + i11, measuredHeight2 + i12);
                                }
                            }
                        } else if (view$OnTouchListenerC2922ad.m18397a()) {
                            m18914a(this.f6183i);
                            Point point2 = this.f6185k;
                            if (point2 == null) {
                                int height4 = (int) (((getHeight() - 15) * f) + this.f6197x);
                                int width4 = (int) (((getWidth() - 15) * f2) + this.f6195v);
                                int measuredWidth4 = width4 - this.f6183i.getMeasuredWidth();
                                int measuredHeight3 = height4 - this.f6183i.getMeasuredHeight();
                                if (this.f6191r == 4) {
                                    height4 -= this.f6181g.getMeasuredHeight();
                                    measuredHeight3 -= this.f6181g.getMeasuredHeight();
                                }
                                this.f6183i.layout(measuredWidth4, measuredHeight3, width4, height4);
                            } else {
                                this.f6183i.layout(point2.x, this.f6185k.y, this.f6185k.x + this.f6183i.getMeasuredWidth(), this.f6185k.y + this.f6183i.getMeasuredHeight());
                            }
                        }
                    }
                }
            }
        }
    }

    public void onPause() {
        this.f6179e.onPause();
    }

    public void onResume() {
        this.f6179e.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f6180f) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.f6184j;
        Point point2 = this.f6185k;
        bundle.putBoolean("mZoomControlEnabled", this.f6192s);
        bundle.putBoolean("mScaleControlEnabled", this.f6193t);
        bundle.putInt("logoPosition", this.f6191r);
        bundle.putInt("paddingLeft", this.f6195v);
        bundle.putInt("paddingTop", this.f6197x);
        bundle.putInt("paddingRight", this.f6196w);
        bundle.putInt("paddingBottom", this.f6198y);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f6181g) {
            return;
        }
        if (m18903c()) {
            super.removeView(view);
        } else {
            C3097i.m17679a(new RunnableC2780u(this, view), 0L);
        }
    }

    public void renderMap() {
    }

    public void setCustomStyleFilePathAndMode(String str, int i) {
    }

    public final void setLogoPosition(LogoPosition logoPosition) {
        if (logoPosition == null) {
            logoPosition = LogoPosition.logoPostionleftBottom;
        }
        this.f6191r = logoPosition.ordinal();
        requestLayout();
    }

    public void setMapCustomStyle(MapCustomStyleOptions mapCustomStyleOptions, CustomMapStyleCallBack customMapStyleCallBack) {
        if (mapCustomStyleOptions == null) {
            return;
        }
        String customMapStyleId = mapCustomStyleOptions.getCustomMapStyleId();
        if (customMapStyleId != null && !customMapStyleId.isEmpty()) {
            C2928f.m18263a().m18258a(this.f6190p, customMapStyleId, new C2776q(this, customMapStyleCallBack, mapCustomStyleOptions));
            return;
        }
        String localCustomStyleFilePath = mapCustomStyleOptions.getLocalCustomStyleFilePath();
        if (localCustomStyleFilePath == null || localCustomStyleFilePath.isEmpty()) {
            return;
        }
        m18907a(localCustomStyleFilePath, "");
        setMapCustomStyleEnable(true);
    }

    public void setMapCustomStyleEnable(boolean z) {
        MapSurfaceView mapSurfaceView = this.f6179e;
        if (mapSurfaceView == null || mapSurfaceView.getBaseMap() == null) {
            return;
        }
        this.f6179e.getBaseMap().m18284p(z);
    }

    public void setMapCustomStylePath(String str) {
        m18907a(str, "");
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.f6195v = i;
        this.f6197x = i2;
        this.f6196w = i3;
        this.f6198y = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f6184j = point;
            requestLayout();
        }
    }

    public void setUpViewEventToMapView(MotionEvent motionEvent) {
        this.f6179e.onTouchEvent(motionEvent);
    }

    public final void setZOrderMediaOverlay(boolean z) {
        MapSurfaceView mapSurfaceView = this.f6179e;
        if (mapSurfaceView == null) {
            return;
        }
        mapSurfaceView.setZOrderMediaOverlay(z);
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f6185k = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f6186l.setVisibility(z ? 0 : 8);
        this.f6193t = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f6183i.m18397a()) {
            this.f6183i.setVisibility(z ? 0 : 8);
            this.f6192s = z;
        }
    }
}
