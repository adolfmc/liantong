package comp.android.app.face.p381sz.camera.util;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.WindowManager;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: comp.android.app.face.sz.camera.util.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11782b {

    /* renamed from: b */
    private static C11782b f24005b;

    /* renamed from: a */
    private C11785a f24006a = new C11785a();

    /* renamed from: comp.android.app.face.sz.camera.util.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11785a implements Comparator<Camera.Size> {
        private C11785a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Camera.Size size, Camera.Size size2) {
            if (size.width == size2.width) {
                return 0;
            }
            return size.width > size2.width ? 1 : -1;
        }
    }

    private C11782b() {
    }

    /* renamed from: a */
    public static C11782b m2150a() {
        C11782b c11782b = f24005b;
        if (c11782b == null) {
            f24005b = new C11782b();
            return f24005b;
        }
        return c11782b;
    }

    /* renamed from: a */
    public int m2149a(Context context, int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        int i2 = 0;
        switch (((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()) {
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = SubsamplingScaleImageView.ORIENTATION_270;
                break;
        }
        return (cameraInfo.facing == 1 ? 360 - ((cameraInfo.orientation + i2) % 360) : (cameraInfo.orientation - i2) + 360) % 360;
    }

    /* renamed from: a */
    public Camera.Size m2148a(List<Camera.Size> list, float f) {
        Collections.sort(list, new Comparator<Camera.Size>() { // from class: comp.android.app.face.sz.camera.util.b.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Camera.Size size, Camera.Size size2) {
                int i = size2.width - size.width;
                return i == 0 ? size2.height - size.height : i;
            }
        });
        Camera.Size size = null;
        float f2 = Float.MAX_VALUE;
        Camera.Size size2 = null;
        float f3 = Float.MAX_VALUE;
        for (Camera.Size size3 : list) {
            if (size3.width <= 1280) {
                float abs = Math.abs(f - (size3.width / size3.height));
                if (abs < f3) {
                    size2 = size3;
                    f3 = abs;
                }
            }
        }
        if (size2 != null) {
            return size2;
        }
        for (Camera.Size size4 : list) {
            float abs2 = Math.abs(f - (size4.width / size4.height));
            if (abs2 < f2) {
                size = size4;
                f2 = abs2;
            }
        }
        return size;
    }

    /* renamed from: a */
    public boolean m2147a(List<Integer> list, int i) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i == list.get(i2).intValue()) {
                Log.i("JCameraView", "Formats supported " + i);
                return true;
            }
        }
        Log.i("JCameraView", "Formats not supported " + i);
        return false;
    }

    /* renamed from: a */
    public boolean m2146a(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i))) {
                Log.i("JCameraView", "FocusMode supported " + str);
                return true;
            }
        }
        Log.i("JCameraView", "FocusMode not supported " + str);
        return false;
    }

    /* renamed from: b */
    public Camera.Size m2145b(List<Camera.Size> list, float f) {
        Collections.sort(list, new Comparator<Camera.Size>() { // from class: comp.android.app.face.sz.camera.util.b.2
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Camera.Size size, Camera.Size size2) {
                int i = size2.width - size.width;
                return i == 0 ? size2.height - size.height : i;
            }
        });
        float f2 = Float.MAX_VALUE;
        Camera.Size size = null;
        for (Camera.Size size2 : list) {
            float abs = Math.abs(f - (size2.width / size2.height));
            if (abs < f2) {
                size = size2;
                f2 = abs;
            }
        }
        return size;
    }
}
