package com.cjt2325.cameralibrary.state;

import android.view.Surface;
import android.view.SurfaceHolder;
import com.cjt2325.cameralibrary.CameraInterface;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface State {
    void cancle(SurfaceHolder surfaceHolder, float f);

    void capture();

    void confirm();

    void flash(String str);

    void foucs(float f, float f2, CameraInterface.FocusCallback focusCallback);

    void record(Surface surface, float f);

    void restart();

    void start(SurfaceHolder surfaceHolder, float f);

    void stop();

    void stopRecord(boolean z, long j);

    void swtich(SurfaceHolder surfaceHolder, float f);

    void zoom(float f, int i);
}
