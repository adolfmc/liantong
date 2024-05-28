package com.baidu.p120ar.arplay.core.engine3d;

import com.baidu.p120ar.arplay.representation.Quaternion;
import com.baidu.p120ar.arplay.representation.Vector3f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine3d.ARPNumber */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPNumber {
    private static native float[] nativeRotationBetweenVector(float f, float f2, float f3, float f4, float f5, float f6);

    public static Quaternion rotationBetweenVector(Vector3f vector3f, Vector3f vector3f2) {
        float[] nativeRotationBetweenVector = nativeRotationBetweenVector(vector3f.m20438x(), vector3f.m20436y(), vector3f.m20434z(), vector3f2.m20438x(), vector3f2.m20436y(), vector3f2.m20434z());
        if (nativeRotationBetweenVector.length != 4) {
            return new Quaternion();
        }
        Quaternion quaternion = new Quaternion();
        quaternion.setXYZW(nativeRotationBetweenVector[0], nativeRotationBetweenVector[1], nativeRotationBetweenVector[2], nativeRotationBetweenVector[3]);
        return quaternion;
    }
}
