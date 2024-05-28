package com.baidu.p120ar.arplay.core.engine.engine3d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.engine3d.IARPCamera */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IARPCamera extends IARPNode {
    float getFieldOfView();

    float[] getViewMatrix();

    float getZFar();

    float getZNear();

    void setFieldOfView(float f);

    void setViewMatrix(float[] fArr);

    void setZFar(float f);

    void setZNear(float f);
}
