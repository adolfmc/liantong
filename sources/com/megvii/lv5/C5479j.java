package com.megvii.lv5;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;

/* compiled from: Proguard */
@TargetApi(21)
/* renamed from: com.megvii.lv5.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5479j {

    /* renamed from: a */
    public final Context f12808a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.j$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum EnumC5480a {
        FACING_BACK,
        FACING_FRONT,
        FACING_EXTERNAL,
        FACING_UNKNOWN
    }

    public C5479j(Context context) {
        this.f12808a = context;
    }

    /* renamed from: a */
    public boolean m13450a(int i) {
        CameraManager cameraManager = (CameraManager) this.f12808a.getSystemService("camera");
        try {
            int intValue = ((Integer) cameraManager.getCameraCharacteristics(cameraManager.getCameraIdList()[i]).get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
            if (intValue != 2) {
                if (intValue == 4) {
                    intValue = 0;
                }
                if (intValue < 0) {
                    return false;
                }
            } else if (intValue != 0) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
