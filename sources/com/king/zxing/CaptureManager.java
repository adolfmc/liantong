package com.king.zxing;

import com.king.zxing.camera.CameraManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface CaptureManager {
    AmbientLightManager getAmbientLightManager();

    BeepManager getBeepManager();

    CameraManager getCameraManager();

    InactivityTimer getInactivityTimer();
}
