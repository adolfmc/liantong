package com.baidu.p120ar.arrender;

import android.content.Context;
import android.graphics.PointF;
import android.view.ViewGroup;
import com.baidu.p120ar.ILuaApplicationState;
import com.baidu.p120ar.arplay.core.engine.ARPDataInteraction;
import com.baidu.p120ar.arplay.core.renderer.OnNeedCacheFrameListener;
import com.baidu.p120ar.arplay.core.renderer.TakePictureCallback;
import com.baidu.p120ar.arplay.representation.Matrixf4x4;
import com.baidu.p120ar.imu.Coordinate;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.IARRenderer */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IARRenderer extends IRenderer {
    void addAlgoCache(int i, boolean z);

    void calibrationTouchAngle();

    void clearAlgoCache();

    void clearCaseLutFilter();

    void convertAlgo2ScreenPoint(PointF pointF, boolean z);

    void enableSyncFaceLandmark(boolean z);

    void enableSyncRender(boolean z);

    String getCurrentCasePath();

    Matrixf4x4 getInitialTransform();

    ILuaApplicationState getLuaApplicationState();

    void getSnapShot(TakePictureCallback takePictureCallback);

    void initWorldAxis();

    boolean isDriverdByARPVersion();

    float[] location2ScreenPoint(float[] fArr);

    void pauseScene();

    void removeAlgoCache(int i);

    void render(long j);

    void resumeScene();

    void sceneRelocate();

    void sceneRotateToCamera();

    void sceneWorldPositionToOrigin();

    boolean set3DModelVisible(boolean z);

    void setAlgoHandleData(long j, String str);

    void setCacheFrameListener(OnNeedCacheFrameListener onNeedCacheFrameListener);

    void setEnabledAbilities(List<String> list);

    void setEnvironmentDataPipKV(String str, Object obj);

    void setFieldOfView(float f);

    void setGLWebViewUseable(Context context, ViewGroup viewGroup);

    void setImuType(Coordinate coordinate);

    void setInteractionCallback(ARPDataInteraction.IInteraction iInteraction);

    void setNativeWebViewUseable(Context context, ViewGroup viewGroup);

    void setOffScreenGuideWork(boolean z);

    void setRootNodeEulerAngle(float f, float f2, float f3);

    void setRootNodeRotation(float f, float f2, float f3);

    void setSyncFrameTimestamp(long j);

    void setTouchEnable(boolean z);

    void updateDeviceOrientation();

    String updateFilterCase(String str);

    void updateFilterData(FilterData filterData);

    void updateFilterNodeData(FilterNodeData filterNodeData);

    void updateRenderCameraData(RenderCameraData renderCameraData);

    void updateRenderNodeData(RenderNodeData renderNodeData, boolean z);

    void updateTransforms(Matrixf4x4 matrixf4x4);
}
