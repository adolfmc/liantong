package com.baidu.p120ar.arplay.core.engine3d;

import android.util.Log;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine3d.ARPScene */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPScene implements IARPScene {
    private HashMap<String, ARPNode> mNodeCache;
    private long mScenePtr = -1;

    native long nativeGetActiveCamera(long j);

    native String nativeGetName(long j);

    native long nativeGetNodeByName(long j, String str);

    native String nativeGetNodeName(long j);

    native long nativeGetRootNode(long j);

    native void nativeRelocate(long j);

    native float[] nativeSceneProject(long j, float[] fArr);

    native void nativeSetOffScreenGuideWork(long j, boolean z);

    native void nativeSetVisible(long j, boolean z);

    public ARPScene() {
        init();
    }

    private void init() {
        this.mNodeCache = new HashMap<>();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public IARPCamera getActiveCamera() {
        long nativeGetActiveCamera = nativeGetActiveCamera(this.mScenePtr);
        ARPCamera defaultCamera = ARPCamera.getDefaultCamera();
        defaultCamera.bindInternal(nativeGetActiveCamera);
        return defaultCamera;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public void setInternal(long j) {
        this.mScenePtr = j;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public IARPNode getRootNode() {
        long j = this.mScenePtr;
        if (j == -1) {
            Log.e(ARPScene.class.getSimpleName(), "node addr is error");
            return null;
        }
        return arpNodeFromInternalNode(nativeGetRootNode(j));
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public IARPNode getNodeByName(String str) {
        long j = this.mScenePtr;
        if (j == -1) {
            Log.e(ARPScene.class.getSimpleName(), "node addr is error");
            return null;
        }
        return arpNodeFromInternalNode(nativeGetNodeByName(j, str));
    }

    private IARPNode arpNodeFromInternalNode(long j) {
        String nativeGetNodeName = nativeGetNodeName(j);
        ARPNode aRPNode = this.mNodeCache.get(nativeGetNodeName);
        if (aRPNode == null) {
            ARPNode aRPNode2 = new ARPNode();
            aRPNode2.bindInternal(j);
            this.mNodeCache.put(nativeGetNodeName, aRPNode2);
            return aRPNode2;
        }
        return aRPNode;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public boolean setVisible(boolean z) {
        long j = this.mScenePtr;
        if (j == -1) {
            Log.e(ARPScene.class.getSimpleName(), "node addr is error");
            return false;
        }
        nativeSetVisible(j, z);
        return true;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public void relocate() {
        long j = this.mScenePtr;
        if (j == -1) {
            Log.e(ARPScene.class.getSimpleName(), "node addr is error");
        } else {
            nativeRelocate(j);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public void setOffScreenGuideWork(boolean z) {
        long j = this.mScenePtr;
        if (j == -1) {
            Log.e(ARPScene.class.getSimpleName(), "node addr is error");
        } else {
            nativeSetOffScreenGuideWork(j, z);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public float[] sceneProject(float[] fArr) {
        long j = this.mScenePtr;
        if (j == -1) {
            Log.e(ARPScene.class.getSimpleName(), "node addr is error");
            return null;
        }
        return nativeSceneProject(j, fArr);
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene
    public String getName(long j) {
        return nativeGetName(j);
    }
}
