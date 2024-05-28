package com.baidu.p120ar.arplay.core.pixel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.pixel.IPixelReader */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IPixelReader {
    void createPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener);

    void destroyPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener);

    void updatePixelReader(PixelReadParams pixelReadParams, PixelRotation pixelRotation);
}
