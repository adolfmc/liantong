package com.baidu.p120ar.arplay.core.engine3d;

import android.util.Log;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode;
import com.baidu.p120ar.arplay.representation.Matrixf4x4;
import com.baidu.p120ar.arplay.representation.Vector3f;
import com.baidu.p120ar.arplay.representation.Vector4f;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine3d.ARPNode */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPNode implements IARPNode {
    protected Lock lock;
    protected long mNativeNodeAddr = -1;
    private HashMap<String, ARPNode> mNodeCache;

    private native void nativeInit();

    native long nativeGetChildNodeByName(long j, String str);

    native float[] nativeGetEulerAngles(long j);

    native String nativeGetName(long j);

    native long nativeGetParentNodePtr(long j);

    native float[] nativeGetPosition(long j);

    native float[] nativeGetRotateWorldAxis(long j);

    native float[] nativeGetRotation(long j);

    native float[] nativeGetScale(long j);

    native float[] nativeGetTransform(long j);

    native float[] nativeGetWorldPosition(long j);

    native float[] nativeGetWorldTransform(long j);

    native float[] nativeInitialTransform(long j);

    native void nativeSetEulerAngles(long j, float[] fArr);

    native void nativeSetName(long j, String str);

    native void nativeSetPosition(long j, float[] fArr);

    native void nativeSetRotateWorldAxis(long j, float[] fArr);

    native void nativeSetRotation(long j, float[] fArr);

    native void nativeSetTransform(long j, float[] fArr);

    native void nativeSetWorldPosition(long j, float[] fArr);

    native void nativeSetWorldTransForm(long j, float[] fArr);

    native void nativeUpdateUniform(long j, String str, Object obj);

    public ARPNode() {
        init();
    }

    private void init() {
        this.lock = new ReentrantLock();
        nativeInit();
        this.mNodeCache = new HashMap<>();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void bindInternal(long j) {
        this.mNativeNodeAddr = j;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public String getName() {
        return nativeGetName(this.mNativeNodeAddr);
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void setName(String str) {
        long j = this.mNativeNodeAddr;
        if (j == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
        } else {
            nativeSetName(j, str);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public Matrixf4x4 getTransform() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeGetTransform = nativeGetTransform(this.mNativeNodeAddr);
        this.lock.unlock();
        Matrixf4x4 matrixf4x4 = new Matrixf4x4();
        if (nativeGetTransform != null) {
            matrixf4x4.setMatrix(nativeGetTransform);
        }
        return matrixf4x4;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void setTransform(Matrixf4x4 matrixf4x4) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
        } else if (matrixf4x4 != null) {
            this.lock.lock();
            nativeSetTransform(this.mNativeNodeAddr, matrixf4x4.getMatrix());
            this.lock.unlock();
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public Matrixf4x4 getInitialTransform() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeInitialTransform = nativeInitialTransform(this.mNativeNodeAddr);
        this.lock.unlock();
        if (nativeInitialTransform == null || nativeInitialTransform.length < 16) {
            return new Matrixf4x4();
        }
        Matrixf4x4 matrixf4x4 = new Matrixf4x4();
        matrixf4x4.setMatrix(nativeInitialTransform);
        return matrixf4x4;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void setPosition(Vector3f vector3f) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
        } else if (vector3f != null) {
            this.lock.lock();
            nativeSetPosition(this.mNativeNodeAddr, vector3f.toArray());
            this.lock.unlock();
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public Vector3f getPosition() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeGetPosition = nativeGetPosition(this.mNativeNodeAddr);
        this.lock.unlock();
        if (nativeGetPosition == null || nativeGetPosition.length < 3) {
            return new Vector3f();
        }
        Vector3f vector3f = new Vector3f();
        vector3f.setXYZ(nativeGetPosition[0], nativeGetPosition[1], nativeGetPosition[2]);
        return vector3f;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void setWorldPosition(Vector3f vector3f) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
        } else if (vector3f != null) {
            this.lock.lock();
            nativeSetWorldPosition(this.mNativeNodeAddr, vector3f.toArray());
            this.lock.unlock();
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public Vector3f getWorldPosition() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeGetWorldPosition = nativeGetWorldPosition(this.mNativeNodeAddr);
        this.lock.unlock();
        if (nativeGetWorldPosition == null || nativeGetWorldPosition.length < 3) {
            return new Vector3f();
        }
        Vector3f vector3f = new Vector3f();
        vector3f.setXYZ(nativeGetWorldPosition[0], nativeGetWorldPosition[1], nativeGetWorldPosition[2]);
        return vector3f;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void setRotation(Vector4f vector4f) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
        } else if (vector4f != null) {
            this.lock.lock();
            nativeSetRotation(this.mNativeNodeAddr, vector4f.toArray());
            this.lock.unlock();
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public Vector4f getRotation() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeGetRotation = nativeGetRotation(this.mNativeNodeAddr);
        this.lock.unlock();
        if (nativeGetRotation == null || nativeGetRotation.length < 4) {
            return new Vector4f();
        }
        Vector4f vector4f = new Vector4f();
        vector4f.setXYZW(nativeGetRotation[0], nativeGetRotation[1], nativeGetRotation[2], nativeGetRotation[3]);
        return vector4f;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void setEulerAnges(Vector3f vector3f) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
        } else if (vector3f != null) {
            this.lock.lock();
            nativeSetEulerAngles(this.mNativeNodeAddr, vector3f.toArray());
            this.lock.unlock();
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public Vector3f getEulerAnges() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeGetEulerAngles = nativeGetEulerAngles(this.mNativeNodeAddr);
        this.lock.unlock();
        if (nativeGetEulerAngles == null || nativeGetEulerAngles.length < 3) {
            return new Vector3f();
        }
        Vector3f vector3f = new Vector3f();
        vector3f.setXYZ(nativeGetEulerAngles[0], nativeGetEulerAngles[1], nativeGetEulerAngles[2]);
        return vector3f;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void setRotateWorldAxis(Vector3f vector3f) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
        } else if (vector3f != null) {
            this.lock.lock();
            nativeSetRotateWorldAxis(this.mNativeNodeAddr, vector3f.toArray());
            this.lock.unlock();
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public Vector3f getRotateWorldAxis() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeGetRotateWorldAxis = nativeGetRotateWorldAxis(this.mNativeNodeAddr);
        this.lock.unlock();
        if (nativeGetRotateWorldAxis == null || nativeGetRotateWorldAxis.length < 3) {
            return new Vector3f();
        }
        Vector3f vector3f = new Vector3f();
        vector3f.setXYZ(nativeGetRotateWorldAxis[0], nativeGetRotateWorldAxis[1], nativeGetRotateWorldAxis[2]);
        return vector3f;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public Vector3f getScale() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeGetScale = nativeGetScale(this.mNativeNodeAddr);
        this.lock.unlock();
        if (nativeGetScale == null || nativeGetScale.length < 3) {
            return new Vector3f();
        }
        Vector3f vector3f = new Vector3f();
        vector3f.setXYZ(nativeGetScale[0], nativeGetScale[1], nativeGetScale[2]);
        return vector3f;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public ARPNode getParentARPNode() {
        long j = this.mNativeNodeAddr;
        if (j == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        long nativeGetParentNodePtr = nativeGetParentNodePtr(j);
        String nativeGetName = nativeGetName(nativeGetParentNodePtr);
        HashMap<String, ARPNode> hashMap = this.mNodeCache;
        ARPNode aRPNode = hashMap != null ? hashMap.get(nativeGetName) : null;
        if (aRPNode == null && nativeGetParentNodePtr != -1) {
            aRPNode = new ARPNode();
            aRPNode.bindInternal(nativeGetParentNodePtr);
            HashMap<String, ARPNode> hashMap2 = this.mNodeCache;
            if (hashMap2 != null) {
                hashMap2.put(nativeGetName, aRPNode);
            }
        }
        return aRPNode;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public ARPNode getChildARPNode(String str) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPNode.class.getSimpleName(), "node addr is error");
            return null;
        }
        HashMap<String, ARPNode> hashMap = this.mNodeCache;
        ARPNode aRPNode = hashMap != null ? hashMap.get(str) : null;
        if (aRPNode == null) {
            long nativeGetChildNodeByName = nativeGetChildNodeByName(this.mNativeNodeAddr, str);
            if (nativeGetChildNodeByName != -1) {
                aRPNode = new ARPNode();
                aRPNode.bindInternal(nativeGetChildNodeByName);
                HashMap<String, ARPNode> hashMap2 = this.mNodeCache;
                if (hashMap2 != null) {
                    hashMap2.put(str, aRPNode);
                }
            }
        }
        return aRPNode;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode
    public void updateUniform(String str, Object obj) {
        nativeUpdateUniform(this.mNativeNodeAddr, str, obj);
    }
}
