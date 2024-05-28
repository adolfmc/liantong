package com.baidu.p120ar.arplay.core.engine.engine3d;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.engine3d.IARPEngine3D */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IARPEngine3D {
    IARPScene getCurrentScene();

    void initWorldAxis();

    void sceneRotateToCamera();

    void sceneWorldPositionToOrigin();

    void updateNodeUniform(String str, HashMap<String, Object> hashMap);
}
