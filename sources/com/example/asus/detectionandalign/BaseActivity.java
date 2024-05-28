package com.example.asus.detectionandalign;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.bytedance.applog.tracker.Tracker;
import com.example.asus.detectionandalign.utils.C4290b;
import com.example.asus.detectionandalign.utils.C4293e;
import com.example.asus.detectionandalign.utils.C4295g;
import comp.android.app.face.p381sz.camera.util.ScreenUtils;
import java.io.InputStream;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BaseActivity extends Activity {

    /* renamed from: e */
    private static String f9736e;

    /* renamed from: f */
    private SensorManager f9741f;

    /* renamed from: g */
    private Sensor f9742g;

    /* renamed from: h */
    private C4295g f9743h;

    /* renamed from: a */
    public final String f9737a = getClass().getSimpleName();

    /* renamed from: d */
    private final boolean f9740d = true;

    /* renamed from: b */
    public boolean f9738b = true;

    /* renamed from: c */
    public boolean f9739c = false;

    /* renamed from: i */
    private Handler f9744i = new Handler() { // from class: com.example.asus.detectionandalign.BaseActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            String str2;
            BaseActivity baseActivity;
            BaseActivity.this.f9739c = false;
            if (message.what == 888) {
                int i = message.arg1;
                if (i > 45 && i < 135) {
                    str = BaseActivity.this.f9737a;
                    str2 = "横屏翻转: ";
                } else if (i > 135 && i < 225) {
                    Log.e(BaseActivity.this.f9737a, "竖屏翻转: ");
                    baseActivity = BaseActivity.this;
                    baseActivity.f9739c = true;
                    baseActivity.f9738b = false;
                } else if (i > 225 && i < 315) {
                    str = BaseActivity.this.f9737a;
                    str2 = "横屏: ";
                } else if ((i > 315 && i < 360) || (i > 0 && i < 45)) {
                    Log.e(BaseActivity.this.f9737a, "竖屏: ");
                    BaseActivity.this.f9738b = true;
                }
                Log.e(str, str2);
                baseActivity = BaseActivity.this;
                baseActivity.f9738b = false;
            }
            super.handleMessage(message);
        }
    };

    /* renamed from: a */
    public String m16293a(Context context) {
        String path = (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir() : context.getCacheDir()).getPath();
        Log.e("getDiskCacheDir", path);
        return path;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.example.asus.detectionandalign.BaseActivity$2] */
    /* renamed from: a */
    public void m16294a() {
        try {
            final InputStream inputStream = Runtime.getRuntime().exec("logcat").getInputStream();
            new Thread() { // from class: com.example.asus.detectionandalign.BaseActivity.2
                /* JADX WARN: Removed duplicated region for block: B:30:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // java.lang.Thread, java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        r6 = this;
                        r0 = 0
                        java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L28
                        java.lang.String r2 = "/sdcard/writelogcat.txt"
                        r1.<init>(r2)     // Catch: java.lang.Throwable -> L23 java.lang.Exception -> L28
                        r0 = 1024(0x400, float:1.435E-42)
                        byte[] r0 = new byte[r0]     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4b
                    Lc:
                        r2 = -1
                        java.io.InputStream r3 = r2     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4b
                        int r3 = r3.read(r0)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4b
                        if (r2 == r3) goto L1d
                        r2 = 0
                        r1.write(r0, r2, r3)     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4b
                        r1.flush()     // Catch: java.lang.Exception -> L21 java.lang.Throwable -> L4b
                        goto Lc
                    L1d:
                        r1.close()     // Catch: java.io.IOException -> L4a
                        goto L4a
                    L21:
                        r0 = move-exception
                        goto L2c
                    L23:
                        r1 = move-exception
                        r5 = r1
                        r1 = r0
                        r0 = r5
                        goto L4c
                    L28:
                        r1 = move-exception
                        r5 = r1
                        r1 = r0
                        r0 = r5
                    L2c:
                        java.lang.String r2 = "writelog"
                        java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4b
                        r3.<init>()     // Catch: java.lang.Throwable -> L4b
                        java.lang.String r4 = "read logcat process failed. message: "
                        r3.append(r4)     // Catch: java.lang.Throwable -> L4b
                        java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L4b
                        r3.append(r0)     // Catch: java.lang.Throwable -> L4b
                        java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L4b
                        android.util.Log.d(r2, r0)     // Catch: java.lang.Throwable -> L4b
                        if (r1 == 0) goto L4a
                        goto L1d
                    L4a:
                        return
                    L4b:
                        r0 = move-exception
                    L4c:
                        if (r1 == 0) goto L51
                        r1.close()     // Catch: java.io.IOException -> L51
                    L51:
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.BaseActivity.C42372.run():void");
                }
            }.start();
        } catch (Exception e) {
            Log.d("writelog", "open logcat process failed. message: " + e.getMessage());
        }
    }

    public void clickClose(View view) {
        finish();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        f9736e = absolutePath + "/FailedLiveness";
        String str = this.f9737a;
        Log.e(str, "######################" + getClass().getSimpleName() + "###################");
        String str2 = this.f9737a;
        Log.e(str2, "=====================    " + C4290b.m15959a((Activity) this));
        this.f9741f = (SensorManager) getSystemService("sensor");
        this.f9742g = this.f9741f.getDefaultSensor(1);
        this.f9743h = new C4295g(this.f9744i);
        this.f9741f.registerListener(this.f9743h, this.f9742g, 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        C4293e.m15951a(19501634);
        System.gc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.f9741f.unregisterListener(this.f9743h);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.f9741f.registerListener(this.f9743h, this.f9742g, 2);
    }

    public void setLayoutParams(View view) {
        int navigationHeight = !C4290b.m15959a((Activity) this) ? ScreenUtils.getNavigationHeight(this) : 0;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        int screenHeight = ScreenUtils.getScreenHeight(this) + navigationHeight;
        int screenWidth = ScreenUtils.getScreenWidth(this);
        int i = (int) (screenWidth * 1.7777777f);
        if (i < screenHeight) {
            screenWidth = (int) (screenHeight / 1.7777777f);
        } else {
            screenHeight = i;
        }
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        view.setLayoutParams(layoutParams);
    }

    public void setLayoutParams1(View view) {
        int navigationHeight = !C4290b.m15959a((Activity) this) ? ScreenUtils.getNavigationHeight(this) : 0;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = ScreenUtils.getScreenWidth(this);
        layoutParams.height = ScreenUtils.getScreenHeight(this) + (navigationHeight * 2);
        view.setLayoutParams(layoutParams);
    }
}
