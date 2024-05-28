package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyDisplayMod {
    private final Context context;
    private final Display display;

    public EasyDisplayMod(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null) {
            this.display = windowManager.getDefaultDisplay();
        } else {
            this.display = null;
        }
    }

    public final String getDensity() {
        int i = this.context.getResources().getDisplayMetrics().densityDpi;
        return CheckValidityUtil.checkValidData(i != 120 ? i != 160 ? i != 213 ? i != 240 ? i != 320 ? i != 400 ? i != 480 ? i != 640 ? null : "XXXHDPI" : "XXHDPI" : "XMHDPI" : "XHDPI" : "HDPI" : "TVDPI" : "MDPI" : "LDPI");
    }

    public final int[] getDisplayXYCoordinates(MotionEvent motionEvent) {
        int[] iArr = {0, 0};
        if (motionEvent.getAction() == 0) {
            iArr[0] = (int) motionEvent.getX();
            iArr[1] = (int) motionEvent.getY();
        }
        return iArr;
    }

    public final String getResolution() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display display = this.display;
        if (display != null) {
            display.getMetrics(displayMetrics);
            return CheckValidityUtil.checkValidData(displayMetrics.heightPixels + "x" + displayMetrics.widthPixels);
        }
        return CheckValidityUtil.checkValidData("");
    }

    public final float getRefreshRate() {
        return this.display.getRefreshRate();
    }

    public final float getPhysicalSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display display = this.display;
        if (display != null) {
            display.getMetrics(displayMetrics);
            return (float) Math.sqrt(((float) Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d)) + ((float) Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d)));
        }
        return 0.0f;
    }
}
