package comp.android.app.face.p381sz.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.bytedance.applog.tracker.Tracker;
import comp.android.app.face.p381sz.camera.util.BangAdaptive;
import comp.android.app.face.p381sz.camera.util.ScreenUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: comp.android.app.face.sz.camera.BaseActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BaseActivity extends Activity {
    private static final float ASPECT_RATIO = 1.7777777f;
    private static final float ASPECT_RATIO_16_9 = 1.7777777f;
    private static final float ASPECT_RATIO_4_3 = 1.3333333f;
    private static String cachePath_FailedLiveness;
    public final String TAG = getClass().getSimpleName();
    private final boolean isOutputLogCat = true;
    private Sensor sensor;

    /* renamed from: sm */
    private SensorManager f23833sm;

    public void clickClose(View view) {
        finish();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public void fullScreen() {
        View decorView;
        int i;
        if (Build.VERSION.SDK_INT >= 19) {
            decorView = getWindow().getDecorView();
            i = 5894;
        } else {
            decorView = getWindow().getDecorView();
            i = 4;
        }
        decorView.setSystemUiVisibility(i);
    }

    public String getDiskCacheDir(Context context) {
        String path = (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir() : context.getCacheDir()).getPath();
        Log.e("getDiskCacheDir", path);
        return path;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        cachePath_FailedLiveness = absolutePath + "/FailedLiveness";
        String str = this.TAG;
        Log.e(str, "######################" + getClass().getSimpleName() + "###################");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    public Bitmap onPreviewFrame(byte[] bArr, int i, int i2) {
        new BitmapFactory.Options().inJustDecodeBounds = true;
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        byteArrayOutputStream.reset();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [comp.android.app.face.sz.camera.BaseActivity$1] */
    public void outputLogCat() {
        try {
            final InputStream inputStream = Runtime.getRuntime().exec("logcat").getInputStream();
            new Thread() { // from class: comp.android.app.face.sz.camera.BaseActivity.1
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
                    throw new UnsupportedOperationException("Method not decompiled: comp.android.app.face.p381sz.camera.BaseActivity.C117231.run():void");
                }
            }.start();
        } catch (Exception e) {
            Log.d("writelog", "open logcat process failed. message: " + e.getMessage());
        }
    }

    public void saveFailedLivenessToLocal(boolean z, Bitmap bitmap, String str) {
        if (z) {
            try {
                String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System.currentTimeMillis()));
                new DecimalFormat("0.00");
                String str2 = cachePath_FailedLiveness;
                File file = new File(str2, format + "_" + str + ".jpg");
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmap.getByteCount());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                Log.e("bitmapCompress_length", String.valueOf(bitmap.getByteCount()));
                Log.e("bitmapCompress：", String.valueOf(bitmap.getByteCount() / 1024) + "kb");
                if (bitmap.getByteCount() / 1024 > 100) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                }
                byteArrayOutputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setLayoutParams(View view) {
        int navigationHeight = !BangAdaptive.hasNotchScreen(this) ? ScreenUtils.getNavigationHeight(this) : 0;
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
        int navigationHeight = !BangAdaptive.hasNotchScreen(this) ? ScreenUtils.getNavigationHeight(this) : 0;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        layoutParams.width = ScreenUtils.getScreenWidth(this);
        layoutParams.height = ScreenUtils.getScreenHeight(this) + (navigationHeight * 2);
        view.setLayoutParams(layoutParams);
    }
}
