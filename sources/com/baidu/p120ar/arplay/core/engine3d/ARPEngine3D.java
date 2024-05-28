package com.baidu.p120ar.arplay.core.engine3d;

import android.text.TextUtils;
import android.util.Log;
import com.baidu.p120ar.arplay.core.engine.engine3d.AbstractARPEngine3D;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene;
import com.baidu.p120ar.arplay.representation.Matrix;
import com.baidu.p120ar.arplay.representation.Matrixf4x4;
import com.baidu.p120ar.arplay.representation.Quaternion;
import com.baidu.p120ar.arplay.representation.Vector3f;
import com.baidu.p120ar.arplay.representation.Vector4f;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine3d.ARPEngine3D */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPEngine3D extends AbstractARPEngine3D {
    private static final String LOWEST_VERSION_KEY = "compatible_version";
    private static final String TAG = "ARPEngine3D";

    private static native void nativeSetMovePlane(float f, float f2, float f3, float f4);

    native long nativeGetCurrentScene();

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public void pause() {
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public void resume() {
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPEngine3D
    public void sceneWorldPositionToOrigin() {
        IARPScene currentScene = getCurrentScene();
        if (currentScene == null) {
            return;
        }
        IARPNode rootNode = currentScene.getRootNode();
        Vector3f vector3f = new Vector3f(0.0f, 0.0f, 0.0f);
        if (rootNode != null) {
            rootNode.setWorldPosition(vector3f);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPEngine3D
    public void sceneRotateToCamera() {
        IARPScene currentScene = getCurrentScene();
        if (currentScene == null) {
            return;
        }
        IARPCamera activeCamera = currentScene.getActiveCamera();
        Matrixf4x4 matrixf4x4 = new Matrixf4x4();
        matrixf4x4.setMatrixValues(activeCamera.getViewMatrix());
        Matrixf4x4 matrixf4x42 = new Matrixf4x4();
        Matrix.invertM(matrixf4x42.getMatrix(), 0, matrixf4x4.getMatrix(), 0);
        IARPNode rootNode = currentScene.getRootNode();
        if (rootNode == null) {
            Log.e("sceneRotateToCamera", "current scene root node is null!");
            return;
        }
        Vector3f vector3f = new Vector3f(0.0f, -1.0f, 0.0f);
        if (this.mIsActiveByARPlayVersionCase) {
            vector3f.setXYZ(0.0f, 0.0f, 1.0f);
        }
        Vector4f rotation = rootNode.getRotation();
        Quaternion quaternion = new Quaternion();
        quaternion.setAxisAngleRad(new Vector3f(rotation.m20430x(), rotation.m20428y(), rotation.m20426z()), rotation.getW());
        Matrixf4x4 matrixf4x43 = new Matrixf4x4();
        matrixf4x43.setMatrixValues(quaternion.getMatrix4x4().getMatrix());
        Vector3f vector3f2 = new Vector3f();
        Matrix.multiplyMV3(vector3f2.toArray(), matrixf4x43.getMatrix(), vector3f.toArray(), 1.0f);
        Vector3f vector3f3 = new Vector3f();
        Vector3f vector3f4 = new Vector3f(0.0f, 0.0f, -1.0f);
        matrixf4x42.setW0(0.0f);
        matrixf4x42.setW1(0.0f);
        matrixf4x42.setW2(0.0f);
        matrixf4x42.setW3(1.0f);
        Matrix.multiplyMV3(vector3f3.toArray(), matrixf4x42.getMatrix(), vector3f4.toArray(), 1.0f);
        Vector3f vector3f5 = new Vector3f();
        if (this.mIsActiveByARPlayVersionCase) {
            vector3f3.setY(0.0f);
            vector3f5.setXYZ(0.0f, 1.0f, 0.0f);
        } else {
            vector3f3.setZ(0.0f);
            vector3f5.setXYZ(0.0f, 0.0f, 1.0f);
        }
        vector3f3.normalize();
        vector3f3.multiplyByScalar(-1.0f);
        Quaternion rotationBetweenVector = ARPNumber.rotationBetweenVector(vector3f2, vector3f3);
        rotationBetweenVector.multiplyByQuat(quaternion);
        Vector4f vector4f = new Vector4f();
        rotationBetweenVector.toAxisAngle(vector4f);
        vector4f.setW((float) Math.toRadians(vector4f.m20432w()));
        rootNode.setRotation(vector4f);
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPEngine3D
    public IARPScene getCurrentScene() {
        return getCurrentSceneInner();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPEngine3D
    public void updateNodeUniform(String str, HashMap<String, Object> hashMap) {
        IARPScene currentScene = getCurrentScene();
        if (currentScene == null || TextUtils.isEmpty(str) || hashMap == null) {
            return;
        }
        IARPNode nodeByName = currentScene.getNodeByName(str);
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            nodeByName.updateUniform(entry.getKey(), entry.getValue());
        }
    }

    private IARPScene getCurrentSceneInner() {
        long nativeGetCurrentScene = nativeGetCurrentScene();
        if (nativeGetCurrentScene == -1) {
            return null;
        }
        ARPScene aRPScene = new ARPScene();
        aRPScene.setInternal(nativeGetCurrentScene);
        return aRPScene;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public void destroy() {
        ARPCamera.destory();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPEngine3D
    public void initWorldAxis() {
        IARPScene currentSceneInner = getCurrentSceneInner();
        if (currentSceneInner != null) {
            IARPNode rootNode = currentSceneInner.getRootNode();
            if (!this.mIsActiveByARPlayVersionCase) {
                setMovePlane(new Vector3f(0.0f, 0.0f, 1.0f), 0.0f);
                Vector3f vector3f = new Vector3f(0.0f, 0.0f, 1.0f);
                if (rootNode != null) {
                    rootNode.setRotateWorldAxis(vector3f);
                    return;
                }
                return;
            }
            Vector3f vector3f2 = new Vector3f(0.0f, 1.0f, 0.0f);
            if (rootNode != null) {
                rootNode.setRotateWorldAxis(vector3f2);
            }
        }
    }

    static void setMovePlane(Vector3f vector3f, float f) {
        nativeSetMovePlane(vector3f.getX(), vector3f.getY(), vector3f.getZ(), f);
    }
}
