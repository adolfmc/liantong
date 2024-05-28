package com.baidu.p120ar.arplay.core.engine.engine3d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.engine3d.IARPScene */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IARPScene {
    IARPCamera getActiveCamera();

    String getName(long j);

    IARPNode getNodeByName(String str);

    IARPNode getRootNode();

    void relocate();

    float[] sceneProject(float[] fArr);

    void setInternal(long j);

    void setOffScreenGuideWork(boolean z);

    boolean setVisible(boolean z);
}
