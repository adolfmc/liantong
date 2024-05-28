package com.baidu.p120ar.arrender;

import com.baidu.p120ar.DuMixInput;
import com.baidu.p120ar.DuMixOutput;
import com.baidu.p120ar.arplay.core.pixel.PixelReadListener;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.IRendererLifecycle */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IRendererLifecycle {
    void changeInput(DuMixInput duMixInput);

    void changeInput(Object obj, int i, int i2);

    void changeInputSize(int i, int i2);

    void changeOutput(DuMixOutput duMixOutput);

    void changeOutput(Object obj, int i, int i2);

    void changeOutputSize(int i, int i2);

    void createCase(String str);

    void createPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener);

    void destroyAllPixelReaders();

    void destroyCase();

    void destroyPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener);

    void pause();

    void release();

    void resume();

    void setup(DuMixInput duMixInput, DuMixOutput duMixOutput);

    void startARPEngine();

    void stopARPEngine();

    void updatePixelReader(PixelReadParams pixelReadParams, PixelRotation pixelRotation);
}
