package com.baidu.p120ar.arplay.core.engine;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import com.baidu.p120ar.arplay.component.DataStore;
import com.baidu.p120ar.arplay.core.engine.ARPContent;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.ARPDataInteraction */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPDataInteraction implements ARPContent.ARPCaseLoadListener {
    private DataStore mDataStore;
    private HtmlUpdateCallback mHtmlCallback;
    private IInteraction mInteraction = null;
    boolean mIsCaseCreated = false;
    public ByteBuffer mMaskBuffer;
    private VideoUpdateCallback mVideoCallback;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPDataInteraction$CaptureCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface CaptureCallback {
        void onSucceed(Bitmap bitmap);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPDataInteraction$HtmlUpdateCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface HtmlUpdateCallback {
        boolean onUpdateHtmlFrame(int i, int i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPDataInteraction$IInteraction */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IInteraction {
        void onFinish(float f, float f2, float f3);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPDataInteraction$UpdateCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface UpdateCallback {
        void onUpdateCallBack();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPDataInteraction$VideoUpdateCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface VideoUpdateCallback {
        void onUpdateVideoFrame(String str, int i, String str2, String str3);
    }

    native void nativeAddAlgoType(int[] iArr, int i);

    native void nativeClearAlgoCache();

    native void nativeDestoryMockFaceAlgoHandle(long j);

    native long nativeMockFaceAlgoHandle(long j, float[] fArr);

    native void nativeReleaseALgoCacheInstance();

    native void nativeRemoveAlgoType(int[] iArr);

    native void nativeSetAlgoDataHandle(long j);

    native void nativeSetAlgoHandle(long j);

    native void nativeSetFaceLandMark(long j, int i);

    native void nativeSetFaceLandMarkFrameAcheMode(int i);

    native void nativeSetup(Object obj);

    native void nativeUpdateAlgoDataToNode(int i, int i2, ByteBuffer byteBuffer);

    native void onGestureUpdateNative(int i, long j, int i2, float f, float f2, float f3, float f4, int i3, float f5, float f6, float f7, float f8, int i4, float f9, boolean z);

    native void onTouchUpdateNative(int i, float f, float f2, float f3, float f4, long j, int i2, float f5);

    public void setup() {
        nativeSetup(new WeakReference(this));
    }

    public synchronized void setVideoUpdateCallback(VideoUpdateCallback videoUpdateCallback) {
        this.mVideoCallback = videoUpdateCallback;
    }

    public synchronized void setHtmlUpdateCallback(HtmlUpdateCallback htmlUpdateCallback) {
        this.mHtmlCallback = htmlUpdateCallback;
    }

    public void updateVideoFrame(String str, int i, String str2, String str3) {
        VideoUpdateCallback videoUpdateCallback = this.mVideoCallback;
        if (videoUpdateCallback == null || !this.mIsCaseCreated) {
            return;
        }
        videoUpdateCallback.onUpdateVideoFrame(str, i, str2, str3);
    }

    private static void updateVideoFrame(Object obj, String str, int i, String str2, String str3) {
        ARPDataInteraction aRPDataInteraction = (ARPDataInteraction) ((WeakReference) obj).get();
        if (aRPDataInteraction == null) {
            return;
        }
        aRPDataInteraction.updateVideoFrame(str, i, str2, str3);
    }

    public boolean updateWebViewFrame(int i, int i2) {
        HtmlUpdateCallback htmlUpdateCallback = this.mHtmlCallback;
        if (htmlUpdateCallback == null || !this.mIsCaseCreated) {
            return false;
        }
        return htmlUpdateCallback.onUpdateHtmlFrame(i, i2);
    }

    public static boolean updateWebViewFrame(Object obj, int i, int i2) {
        ARPDataInteraction aRPDataInteraction = (ARPDataInteraction) ((WeakReference) obj).get();
        if (aRPDataInteraction == null) {
            return false;
        }
        return aRPDataInteraction.updateWebViewFrame(i, i2);
    }

    public void onTouchUpdate(int i, float f, float f2, float f3, float f4, long j, int i2, float f5) {
        onTouchUpdateNative(i, f, f2, f3, f4, j, i2, f5);
    }

    public void onGestureUpdate(int i, long j, int i2, float f, float f2, float f3, float f4, int i3, float f5, float f6, float f7, float f8, int i4, float f9) {
        onGestureUpdateNative(i, j, i2, f, f2, f3, f4, i3, f5, f6, f7, f8, i4, f9, false);
    }

    public void onGestureUpdateWithScaleFinish(int i, long j, int i2, float f, float f2, float f3, float f4, int i3, float f5, float f6, float f7, float f8, int i4, float f9, boolean z) {
        onGestureUpdateNative(i, j, i2, f, f2, f3, f4, i3, f5, f6, f7, f8, i4, f9, z);
    }

    public void initDataStore(SharedPreferences sharedPreferences) {
        if (this.mDataStore == null) {
            this.mDataStore = new DataStore();
            this.mDataStore.setPrefs(sharedPreferences);
        }
    }

    public void clearARMemory() {
        DataStore dataStore = this.mDataStore;
        if (dataStore != null) {
            dataStore.clearARMemory();
        }
    }

    public void setValue(int i, String str, String str2) {
        DataStore dataStore = this.mDataStore;
        if (dataStore != null) {
            dataStore.setValue(i, str, str2);
        } else {
            Log.e("ARPDataInteraction", "set value error!");
        }
    }

    private static void setValue(Object obj, int i, String str, String str2) {
        ARPDataInteraction aRPDataInteraction = (ARPDataInteraction) ((WeakReference) obj).get();
        if (aRPDataInteraction == null) {
            return;
        }
        aRPDataInteraction.setValue(i, str, str2);
    }

    public String getValue(int i, String str) {
        DataStore dataStore = this.mDataStore;
        if (dataStore != null) {
            return dataStore.getValue(i, str);
        }
        Log.e("ARPDataInteraction", "get value error!");
        return "";
    }

    private static String getValue(Object obj, int i, String str) {
        ARPDataInteraction aRPDataInteraction = (ARPDataInteraction) ((WeakReference) obj).get();
        return aRPDataInteraction == null ? "" : aRPDataInteraction.getValue(i, str);
    }

    public static void onInteractionFinish(Object obj, float f, float f2, float f3) {
        ARPDataInteraction aRPDataInteraction = (ARPDataInteraction) ((WeakReference) obj).get();
        if (aRPDataInteraction == null) {
            return;
        }
        aRPDataInteraction.onInteractionFinish(f, f2, f3);
    }

    public void onInteractionFinish(float f, float f2, float f3) {
        IInteraction iInteraction = this.mInteraction;
        if (iInteraction != null) {
            iInteraction.onFinish(f, f2, f3);
        }
    }

    public void setInteraction(IInteraction iInteraction) {
        this.mInteraction = iInteraction;
    }

    public synchronized void setAlgoDataHandle(long j) {
        nativeSetAlgoHandle(j);
    }

    public void addAlgoType(int[] iArr, int i) {
        nativeAddAlgoType(iArr, i);
    }

    public void removeAlgoType(int[] iArr) {
        nativeRemoveAlgoType(iArr);
    }

    public void clearAlgoCache() {
        nativeClearAlgoCache();
    }

    public void setFaceLandMarkFrameAcheMode(int i) {
        nativeSetFaceLandMarkFrameAcheMode(i);
    }

    public void updateAlgoDataToNode(int i, int i2, byte[] bArr) {
        this.mMaskBuffer = ByteBuffer.allocateDirect(bArr.length);
        this.mMaskBuffer.put(bArr);
        nativeUpdateAlgoDataToNode(i, i2, this.mMaskBuffer);
    }

    public long mockFaceAlgoHandle(long j, float[] fArr) {
        return nativeMockFaceAlgoHandle(j, fArr);
    }

    public void destroyMockAlgoHandle(long j) {
        nativeDestoryMockFaceAlgoHandle(j);
    }

    public void destroy() {
        nativeReleaseALgoCacheInstance();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.ARPContent.ARPCaseLoadListener
    public void onCaseLoaded(boolean z) {
        this.mIsCaseCreated = z;
    }
}
