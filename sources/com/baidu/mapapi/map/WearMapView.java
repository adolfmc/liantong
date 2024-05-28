package com.baidu.mapapi.map;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
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
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(20)
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WearMapView extends ViewGroup implements View.OnApplyWindowInsetsListener {
    public static final int BT_INVIEW = 1;

    /* renamed from: c */
    private static String f6479c;

    /* renamed from: A */
    private float f6486A;

    /* renamed from: B */
    private int f6487B;

    /* renamed from: C */
    private int f6488C;

    /* renamed from: D */
    private int f6489D;

    /* renamed from: E */
    private int f6490E;

    /* renamed from: F */
    private int f6491F;

    /* renamed from: G */
    private int f6492G;

    /* renamed from: H */
    private boolean f6493H;

    /* renamed from: a */
    ScreenShape f6494a;

    /* renamed from: f */
    private MapSurfaceView f6495f;

    /* renamed from: g */
    private BaiduMap f6496g;

    /* renamed from: h */
    private ImageView f6497h;

    /* renamed from: i */
    private Bitmap f6498i;

    /* renamed from: j */
    private View$OnTouchListenerC2922ad f6499j;

    /* renamed from: k */
    private boolean f6500k;

    /* renamed from: l */
    private Point f6501l;

    /* renamed from: m */
    private Point f6502m;
    public AnimationTask mTask;
    public Timer mTimer;
    public HandlerC2747a mTimerHandler;

    /* renamed from: n */
    private RelativeLayout f6503n;

    /* renamed from: o */
    private SwipeDismissView f6504o;

    /* renamed from: p */
    private TextView f6505p;

    /* renamed from: q */
    private TextView f6506q;

    /* renamed from: r */
    private ImageView f6507r;

    /* renamed from: v */
    private boolean f6508v;

    /* renamed from: w */
    private Context f6509w;

    /* renamed from: y */
    private boolean f6510y;

    /* renamed from: z */
    private boolean f6511z;

    /* renamed from: b */
    private static final String f6478b = MapView.class.getSimpleName();

    /* renamed from: d */
    private static int f6480d = 0;

    /* renamed from: e */
    private static int f6481e = 0;

    /* renamed from: s */
    private static int f6482s = 0;

    /* renamed from: t */
    private static int f6483t = 0;

    /* renamed from: u */
    private static int f6484u = 10;

    /* renamed from: x */
    private static final SparseArray<Integer> f6485x = new SparseArray<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AnimationTask extends TimerTask {
        public AnimationTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Message message = new Message();
            message.what = 1;
            WearMapView.this.mTimerHandler.sendMessage(message);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDismissCallback {
        void onDismiss();

        void onNotify();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum ScreenShape {
        ROUND,
        RECTANGLE,
        UNDETECTED
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapapi.map.WearMapView$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class HandlerC2747a extends Handler {

        /* renamed from: b */
        private final WeakReference<Context> f6515b;

        public HandlerC2747a(Context context) {
            this.f6515b = new WeakReference<>(context);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f6515b.get() == null) {
                return;
            }
            super.handleMessage(message);
            if (message.what == 1 && WearMapView.this.f6499j != null) {
                WearMapView.this.m18814a(true);
            }
        }
    }

    static {
        f6485x.append(3, 2000000);
        f6485x.append(4, 1000000);
        f6485x.append(5, 500000);
        f6485x.append(6, 200000);
        f6485x.append(7, 100000);
        f6485x.append(8, 50000);
        f6485x.append(9, 25000);
        f6485x.append(10, 20000);
        f6485x.append(11, 10000);
        f6485x.append(12, 5000);
        f6485x.append(13, 2000);
        f6485x.append(14, 1000);
        f6485x.append(15, 500);
        f6485x.append(16, 200);
        f6485x.append(17, 100);
        f6485x.append(18, 50);
        f6485x.append(19, 20);
        f6485x.append(20, 10);
        f6485x.append(21, 5);
        f6485x.append(22, 2);
    }

    public WearMapView(Context context) {
        super(context);
        this.f6500k = true;
        this.f6508v = true;
        this.f6494a = ScreenShape.ROUND;
        this.f6510y = true;
        this.f6511z = true;
        this.f6493H = false;
        m18825a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6500k = true;
        this.f6508v = true;
        this.f6494a = ScreenShape.ROUND;
        this.f6510y = true;
        this.f6511z = true;
        this.f6493H = false;
        m18825a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6500k = true;
        this.f6508v = true;
        this.f6494a = ScreenShape.ROUND;
        this.f6510y = true;
        this.f6511z = true;
        this.f6493H = false;
        m18825a(context, (BaiduMapOptions) null);
    }

    public WearMapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        this.f6500k = true;
        this.f6508v = true;
        this.f6494a = ScreenShape.ROUND;
        this.f6510y = true;
        this.f6511z = true;
        this.f6493H = false;
        m18825a(context, baiduMapOptions);
    }

    /* renamed from: a */
    private int m18827a(int i, int i2) {
        return i - ((int) Math.sqrt(Math.pow(i, 2.0d) - Math.pow(i2, 2.0d)));
    }

    /* renamed from: a */
    private void m18828a(int i) {
        MapSurfaceView mapSurfaceView = this.f6495f;
        if (mapSurfaceView == null) {
            return;
        }
        switch (i) {
            case 0:
                mapSurfaceView.onPause();
                m18813b();
                return;
            case 1:
                mapSurfaceView.onResume();
                m18809c();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private static void m18826a(Context context) {
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    /* renamed from: a */
    private void m18825a(Context context, BaiduMapOptions baiduMapOptions) {
        AnimationTask animationTask;
        m18826a(context);
        setOnApplyWindowInsetsListener(this);
        this.f6509w = context;
        this.mTimerHandler = new HandlerC2747a(context);
        this.mTimer = new Timer();
        if (this.mTimer != null && (animationTask = this.mTask) != null) {
            animationTask.cancel();
        }
        this.mTask = new AnimationTask();
        this.mTimer.schedule(this.mTask, 5000L);
        C2934j.m18235a();
        BMapManager.init();
        m18824a(context, baiduMapOptions, f6479c);
        this.f6495f.getController().set3DGestureEnable(false);
        this.f6495f.getController().setOverlookGestureEnable(false);
        m18808c(context);
        m18805d(context);
        m18812b(context);
        if (baiduMapOptions != null && !baiduMapOptions.f5985h) {
            this.f6499j.setVisibility(4);
        }
        m18802e(context);
        if (baiduMapOptions != null && !baiduMapOptions.f5986i) {
            this.f6503n.setVisibility(4);
        }
        if (baiduMapOptions != null && baiduMapOptions.f5989l != null) {
            this.f6502m = baiduMapOptions.f5989l;
        }
        if (baiduMapOptions == null || baiduMapOptions.f5988k == null) {
            return;
        }
        this.f6501l = baiduMapOptions.f5988k;
    }

    /* renamed from: a */
    private void m18824a(Context context, BaiduMapOptions baiduMapOptions, String str) {
        this.f6495f = new MapSurfaceView(context);
        if (baiduMapOptions != null) {
            this.f6496g = new BaiduMap(context, this.f6495f, baiduMapOptions.m18979a());
        } else {
            this.f6496g = new BaiduMap(context, this.f6495f, (C2946v) null);
        }
        addView(this.f6495f);
        this.f6495f.getBaseMap().m18335a(new C2756ah(this));
    }

    /* renamed from: a */
    private void m18823a(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i = layoutParams.width;
        int makeMeasureSpec = i > 0 ? View.MeasureSpec.makeMeasureSpec(i, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = layoutParams.height;
        view.measure(makeMeasureSpec, i2 > 0 ? View.MeasureSpec.makeMeasureSpec(i2, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* renamed from: a */
    private void m18822a(View view, boolean z) {
        AnimatorSet animatorSet;
        if (z) {
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationY", 0.0f, -50.0f), ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f));
            animatorSet.addListener(new C2759ak(this, view));
        } else {
            view.setVisibility(0);
            animatorSet = new AnimatorSet();
            animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationY", -50.0f, 0.0f), ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f));
        }
        animatorSet.setDuration(1200L);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18816a(String str, MapCustomStyleOptions mapCustomStyleOptions) {
        if (TextUtils.isEmpty(str)) {
            str = mapCustomStyleOptions.getLocalCustomStyleFilePath();
            if (TextUtils.isEmpty(str)) {
                return;
            }
        }
        m18815a(str, "");
        setMapCustomStyleEnable(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18815a(String str, String str2) {
        MapSurfaceView mapSurfaceView = this.f6495f;
        if (mapSurfaceView == null || mapSurfaceView.getBaseMap() == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            Log.e(f6478b, "customStyleFilePath is empty or null, please check!");
        } else if (!str.endsWith(".sty")) {
            Log.e(f6478b, "customStyleFile format is incorrect , please check!");
        } else if (new File(str).exists()) {
            this.f6495f.getBaseMap().m18323b(str, "");
        } else {
            Log.e(f6478b, "customStyleFile does not exist , please check!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18814a(boolean z) {
        if (this.f6500k) {
            m18822a(this.f6499j, z);
        }
    }

    /* renamed from: b */
    private void m18813b() {
        if (this.f6495f == null || this.f6508v) {
            return;
        }
        m18806d();
        this.f6508v = true;
    }

    /* renamed from: b */
    private void m18812b(Context context) {
        this.f6504o = new SwipeDismissView(context, this);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) ((context.getResources().getDisplayMetrics().density * 34.0f) + 0.5f), f6483t);
        this.f6504o.setBackgroundColor(Color.argb(0, 0, 0, 0));
        this.f6504o.setLayoutParams(layoutParams);
        addView(this.f6504o);
    }

    /* renamed from: c */
    private void m18809c() {
        if (this.f6495f != null && this.f6508v) {
            m18803e();
            this.f6508v = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m18808c(android.content.Context r10) {
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
            r9.f6498i = r0
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
            r9.f6498i = r2
        L40:
            android.graphics.Bitmap r0 = r9.f6498i
            if (r0 == 0) goto L57
            android.widget.ImageView r0 = new android.widget.ImageView
            r0.<init>(r10)
            r9.f6497h = r0
            android.widget.ImageView r10 = r9.f6497h
            android.graphics.Bitmap r0 = r9.f6498i
            r10.setImageBitmap(r0)
            android.widget.ImageView r10 = r9.f6497h
            r9.addView(r10)
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapapi.map.WearMapView.m18808c(android.content.Context):void");
    }

    /* renamed from: d */
    private void m18806d() {
        MapSurfaceView mapSurfaceView = this.f6495f;
        if (mapSurfaceView == null) {
            return;
        }
        mapSurfaceView.onBackground();
    }

    /* renamed from: d */
    private void m18805d(Context context) {
        this.f6499j = new View$OnTouchListenerC2922ad(context, true);
        if (this.f6499j.m18397a()) {
            this.f6499j.m18390b(new View$OnClickListenerC2757ai(this));
            this.f6499j.m18395a(new View$OnClickListenerC2758aj(this));
            addView(this.f6499j);
        }
    }

    /* renamed from: e */
    private void m18803e() {
        MapSurfaceView mapSurfaceView = this.f6495f;
        if (mapSurfaceView == null) {
            return;
        }
        mapSurfaceView.onForeground();
    }

    /* renamed from: e */
    private void m18802e(Context context) {
        this.f6503n = new RelativeLayout(context);
        this.f6503n.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.f6505p = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f6505p.setTextColor(Color.parseColor("#FFFFFF"));
        this.f6505p.setTextSize(2, 11.0f);
        TextView textView = this.f6505p;
        textView.setTypeface(textView.getTypeface(), 1);
        this.f6505p.setLayoutParams(layoutParams);
        this.f6505p.setId(Integer.MAX_VALUE);
        this.f6503n.addView(this.f6505p);
        this.f6506q = new TextView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.width = -2;
        layoutParams2.height = -2;
        layoutParams2.addRule(14);
        this.f6506q.setTextColor(Color.parseColor("#000000"));
        this.f6506q.setTextSize(2, 11.0f);
        this.f6506q.setLayoutParams(layoutParams2);
        this.f6503n.addView(this.f6506q);
        this.f6507r = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.width = -2;
        layoutParams3.height = -2;
        layoutParams3.addRule(14);
        layoutParams3.addRule(3, this.f6505p.getId());
        this.f6507r.setLayoutParams(layoutParams3);
        Bitmap m18473a = C2888a.m18473a("icon_scale.9.png", context);
        byte[] ninePatchChunk = m18473a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.f6507r.setBackgroundDrawable(new NinePatchDrawable(m18473a, ninePatchChunk, new Rect(), null));
        this.f6503n.addView(this.f6507r);
        addView(this.f6503n);
    }

    @Deprecated
    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("BDMapSDKException: customMapStylePath String is illegal");
        }
        if (!new File(str).exists()) {
            throw new RuntimeException("BDMapSDKException: please check whether the customMapStylePath file exits");
        }
        f6479c = str;
    }

    @Deprecated
    public static void setIconCustom(int i) {
        f6481e = i;
    }

    @Deprecated
    public static void setLoadCustomMapStyleFileMode(int i) {
        f6480d = i;
    }

    @Deprecated
    public static void setMapCustomEnable(boolean z) {
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof MapViewLayoutParams) {
            super.addView(view, layoutParams);
        }
    }

    public final BaiduMap getMap() {
        BaiduMap baiduMap = this.f6496g;
        baiduMap.f5955c = this;
        return baiduMap;
    }

    public final int getMapLevel() {
        return f6485x.get((int) this.f6495f.getZoomLevel()).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.f6491F;
    }

    public int getScaleControlViewWidth() {
        return this.f6492G;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.f6494a = windowInsets.isRound() ? ScreenShape.ROUND : ScreenShape.RECTANGLE;
        return windowInsets;
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
        if (this.f6501l != null) {
            this.f6501l = (Point) bundle.getParcelable("scalePosition");
        }
        if (this.f6502m != null) {
            this.f6502m = (Point) bundle.getParcelable("zoomPosition");
        }
        this.f6510y = bundle.getBoolean("mZoomControlEnabled");
        this.f6511z = bundle.getBoolean("mScaleControlEnabled");
        setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
        m18825a(context, new BaiduMapOptions().mapStatus(mapStatus));
    }

    public final void onDestroy() {
        if (this.f6509w != null) {
            this.f6495f.unInit();
        }
        Bitmap bitmap = this.f6498i;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f6498i.recycle();
            this.f6498i = null;
        }
        this.f6499j.m18391b();
        BMapManager.destroy();
        C2934j.m18234b();
        AnimationTask animationTask = this.mTask;
        if (animationTask != null) {
            animationTask.cancel();
        }
        this.f6509w = null;
    }

    public final void onDismiss() {
        removeAllViews();
    }

    public final void onEnterAmbient(Bundle bundle) {
        m18828a(0);
    }

    public void onExitAmbient() {
        m18828a(1);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        AnimationTask animationTask;
        switch (motionEvent.getAction()) {
            case 0:
                if (this.f6499j.getVisibility() != 0) {
                    if (this.f6499j.getVisibility() == 4) {
                        if (this.mTimer != null) {
                            AnimationTask animationTask2 = this.mTask;
                            if (animationTask2 != null) {
                                animationTask2.cancel();
                            }
                            this.mTimer.cancel();
                            this.mTask = null;
                            this.mTimer = null;
                        }
                        m18814a(false);
                        break;
                    }
                } else {
                    Timer timer = this.mTimer;
                    if (timer != null) {
                        if (this.mTask != null) {
                            timer.cancel();
                            this.mTask.cancel();
                        }
                        this.mTimer = null;
                        this.mTask = null;
                        break;
                    }
                }
                break;
            case 1:
                this.mTimer = new Timer();
                if (this.mTimer != null && (animationTask = this.mTask) != null) {
                    animationTask.cancel();
                }
                this.mTask = new AnimationTask();
                this.mTimer.schedule(this.mTask, 5000L);
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(20)
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int i5;
        int i6;
        int i7;
        int i8;
        int childCount = getChildCount();
        m18823a(this.f6497h);
        float f2 = 1.0f;
        if (((getWidth() - this.f6487B) - this.f6488C) - this.f6497h.getMeasuredWidth() <= 0 || ((getHeight() - this.f6489D) - this.f6490E) - this.f6497h.getMeasuredHeight() <= 0) {
            this.f6487B = 0;
            this.f6488C = 0;
            this.f6490E = 0;
            this.f6489D = 0;
            f = 1.0f;
        } else {
            f2 = ((getWidth() - this.f6487B) - this.f6488C) / getWidth();
            f = ((getHeight() - this.f6489D) - this.f6490E) / getHeight();
        }
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            MapSurfaceView mapSurfaceView = this.f6495f;
            if (childAt == mapSurfaceView) {
                mapSurfaceView.layout(0, 0, getWidth(), getHeight());
            } else if (childAt == this.f6497h) {
                int i10 = (int) (this.f6490E + (12.0f * f));
                if (this.f6494a == ScreenShape.ROUND) {
                    m18823a(this.f6499j);
                    int i11 = f6482s / 2;
                    i8 = m18827a(i11, this.f6499j.getMeasuredWidth() / 2);
                    i7 = f6484u + ((f6482s / 2) - m18827a(i11, i11 - i8));
                } else {
                    i7 = 0;
                    i8 = 0;
                }
                int i12 = (f6483t - i8) - i10;
                int i13 = f6482s - i7;
                this.f6497h.layout(i13 - this.f6497h.getMeasuredWidth(), i12 - this.f6497h.getMeasuredHeight(), i13, i12);
            } else {
                View$OnTouchListenerC2922ad view$OnTouchListenerC2922ad = this.f6499j;
                if (childAt == view$OnTouchListenerC2922ad) {
                    if (view$OnTouchListenerC2922ad.m18397a()) {
                        m18823a(this.f6499j);
                        Point point = this.f6502m;
                        if (point == null) {
                            int m18827a = (int) ((12.0f * f) + this.f6489D + (this.f6494a == ScreenShape.ROUND ? m18827a(f6483t / 2, this.f6499j.getMeasuredWidth() / 2) : 0));
                            int measuredWidth = (f6482s - this.f6499j.getMeasuredWidth()) / 2;
                            this.f6499j.layout(measuredWidth, m18827a, this.f6499j.getMeasuredWidth() + measuredWidth, this.f6499j.getMeasuredHeight() + m18827a);
                        } else {
                            this.f6499j.layout(point.x, this.f6502m.y, this.f6502m.x + this.f6499j.getMeasuredWidth(), this.f6502m.y + this.f6499j.getMeasuredHeight());
                        }
                    }
                } else if (childAt == this.f6503n) {
                    if (this.f6494a == ScreenShape.ROUND) {
                        m18823a(this.f6499j);
                        int i14 = f6482s / 2;
                        i6 = m18827a(i14, this.f6499j.getMeasuredWidth() / 2);
                        i5 = f6484u + ((f6482s / 2) - m18827a(i14, i14 - i6));
                    } else {
                        i5 = 0;
                        i6 = 0;
                    }
                    m18823a(this.f6503n);
                    Point point2 = this.f6501l;
                    if (point2 == null) {
                        this.f6492G = this.f6503n.getMeasuredWidth();
                        this.f6491F = this.f6503n.getMeasuredHeight();
                        int i15 = (int) (this.f6487B + (5.0f * f2) + i5);
                        int i16 = (f6483t - ((int) (this.f6490E + (12.0f * f)))) - i6;
                        this.f6503n.layout(i15, i16 - this.f6503n.getMeasuredHeight(), this.f6492G + i15, i16);
                    } else {
                        this.f6503n.layout(point2.x, this.f6501l.y, this.f6501l.x + this.f6503n.getMeasuredWidth(), this.f6501l.y + this.f6503n.getMeasuredHeight());
                    }
                } else {
                    SwipeDismissView swipeDismissView = this.f6504o;
                    if (childAt == swipeDismissView) {
                        m18823a(swipeDismissView);
                        this.f6504o.layout(0, 0, this.f6504o.getMeasuredWidth(), f6483t);
                    } else {
                        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                        if (layoutParams instanceof MapViewLayoutParams) {
                            MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                            Point m18338a = mapViewLayoutParams.f6202c == MapViewLayoutParams.ELayoutMode.absoluteMode ? mapViewLayoutParams.f6201b : this.f6495f.getBaseMap() != null ? this.f6495f.getBaseMap().m18338a(CoordUtil.ll2mc(mapViewLayoutParams.f6200a)) : new Point();
                            m18823a(childAt);
                            int measuredWidth2 = childAt.getMeasuredWidth();
                            int measuredHeight = childAt.getMeasuredHeight();
                            int i17 = (int) (m18338a.x - (mapViewLayoutParams.f6203d * measuredWidth2));
                            int i18 = ((int) (m18338a.y - (mapViewLayoutParams.f6204e * measuredHeight))) + mapViewLayoutParams.f6205f;
                            childAt.layout(i17, i18, measuredWidth2 + i17, measuredHeight + i18);
                        }
                    }
                }
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaiduMap baiduMap;
        if (bundle == null || (baiduMap = this.f6496g) == null) {
            return;
        }
        bundle.putParcelable("mapstatus", baiduMap.getMapStatus());
        Point point = this.f6501l;
        if (point != null) {
            bundle.putParcelable("scalePosition", point);
        }
        Point point2 = this.f6502m;
        if (point2 != null) {
            bundle.putParcelable("zoomPosition", point2);
        }
        bundle.putBoolean("mZoomControlEnabled", this.f6510y);
        bundle.putBoolean("mScaleControlEnabled", this.f6511z);
        bundle.putInt("paddingLeft", this.f6487B);
        bundle.putInt("paddingTop", this.f6489D);
        bundle.putInt("paddingRight", this.f6488C);
        bundle.putInt("paddingBottom", this.f6490E);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (view == this.f6497h) {
            return;
        }
        super.removeView(view);
    }

    public void setCustomStyleFilePathAndMode(String str, int i) {
    }

    public void setMapCustomStyle(MapCustomStyleOptions mapCustomStyleOptions, CustomMapStyleCallBack customMapStyleCallBack) {
        if (mapCustomStyleOptions == null) {
            return;
        }
        String customMapStyleId = mapCustomStyleOptions.getCustomMapStyleId();
        if (customMapStyleId != null && !customMapStyleId.isEmpty()) {
            C2928f.m18263a().m18258a(this.f6509w, customMapStyleId, new C2755ag(this, customMapStyleCallBack, mapCustomStyleOptions));
            return;
        }
        String localCustomStyleFilePath = mapCustomStyleOptions.getLocalCustomStyleFilePath();
        if (localCustomStyleFilePath == null || localCustomStyleFilePath.isEmpty()) {
            return;
        }
        m18815a(localCustomStyleFilePath, "");
    }

    public void setMapCustomStyleEnable(boolean z) {
    }

    public void setMapCustomStylePath(String str) {
        m18815a(str, "");
    }

    public void setOnDismissCallbackListener(OnDismissCallback onDismissCallback) {
        SwipeDismissView swipeDismissView = this.f6504o;
        if (swipeDismissView == null) {
            return;
        }
        swipeDismissView.setCallback(onDismissCallback);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        this.f6487B = i;
        this.f6489D = i2;
        this.f6488C = i3;
        this.f6490E = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f6501l = point;
            requestLayout();
        }
    }

    public void setShape(ScreenShape screenShape) {
        this.f6494a = screenShape;
    }

    public void setViewAnimitionEnable(boolean z) {
        this.f6500k = z;
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f6502m = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f6503n.setVisibility(z ? 0 : 8);
        this.f6511z = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f6499j.m18397a()) {
            this.f6499j.setVisibility(z ? 0 : 8);
            this.f6510y = z;
        }
    }
}
