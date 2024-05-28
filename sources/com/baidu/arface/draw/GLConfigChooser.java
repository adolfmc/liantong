package com.baidu.arface.draw;

import android.opengl.GLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GLConfigChooser implements GLSurfaceView.EGLConfigChooser {
    @Override // android.opengl.GLSurfaceView.EGLConfigChooser
    public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr = new int[1];
        egl10.eglChooseConfig(eGLDisplay, new int[]{12329, 0, 12352, 4, 12351, 12430, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12338, 1, 12337, 4, 12344}, eGLConfigArr, 1, iArr);
        if (iArr[0] == 0) {
            return null;
        }
        return eGLConfigArr[0];
    }
}
