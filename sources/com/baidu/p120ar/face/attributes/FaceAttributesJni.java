package com.baidu.p120ar.face.attributes;

import android.content.res.AssetManager;
import com.baidu.p120ar.libloader.LibLoader;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.attributes.FaceAttributesJni */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceAttributesJni {
    protected static boolean loadSuccess;

    public static native int getVersion();

    public static native synchronized int initGenderDetect(String str);

    public static native synchronized int initGenderDetectFromAssets(String str);

    public static native synchronized int predictGenderDetect(ByteBuffer byteBuffer, int i, int i2, float[] fArr, float[] fArr2);

    public static native synchronized int releaseGenderDetect();

    public static native int setAssetManager(AssetManager assetManager);

    static {
        try {
            LibLoader.require("FaceAttributes");
            loadSuccess = true;
        } catch (Throwable th) {
            loadSuccess = false;
            th.printStackTrace();
        }
    }
}
