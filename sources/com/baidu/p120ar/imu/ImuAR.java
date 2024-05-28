package com.baidu.p120ar.imu;

import com.baidu.p120ar.ARType;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.arplay.representation.Matrix;
import com.baidu.p120ar.arplay.representation.Matrixf4x4;
import com.baidu.p120ar.arplay.representation.Quaternion;
import com.baidu.p120ar.arplay.representation.Vector3f;
import com.baidu.p120ar.arplay.representation.Vector4f;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.arrender.RenderCameraData;
import com.baidu.p120ar.bean.ARConfig;
import com.baidu.p120ar.lua.EngineMsgListener;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.ImuAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImuAR extends AbstractAR implements ImuListener {
    private static final String IMU_MIRRORED = "is_mirrored";
    private static final String MSG_EXTRA_IMU_INIT_POS = "init_pos";
    private static final String MSG_EXTRA_IMU_TYPE = "type";
    private static final int SUCCEEDED_CODE = 1;
    private static final String SUCCEEDED_FLAG = "succeeded";
    private static final String TAG = "ImuAR";
    private static final String WITH_INTERACTION = "with_interaction";
    private EngineMsgListener mEngineMsgListener;
    private ImuParams mImuParams;
    private Matrixf4x4 mCurrentCameraPos = new Matrixf4x4();
    private Matrixf4x4 mFirstRMat = new Matrixf4x4();
    private Matrixf4x4 mFirstViewMat = new Matrixf4x4();
    private Matrixf4x4 mBaseMat = new Matrixf4x4();
    private boolean isFirstUpdate = true;
    private float[] mMatrixArray = new float[16];
    private boolean isDataReady = false;
    private int mInitSceneCenter = 0;
    private boolean hasInitImuFirst = false;
    private boolean mIsActiveARPlayVersionCase = false;
    private boolean isSceneRelocate = false;
    private boolean mIsDrivenByIMUMirror = false;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        int floatValue;
        super.setup(hashMap);
        if (hashMap != null && hashMap.containsKey("type")) {
            String str = TAG;
            ARLog.m20421d(str, "setup() luaParams = " + hashMap.toString());
            Object obj = hashMap.get("type");
            int i = 0;
            if (obj instanceof Integer) {
                floatValue = ((Integer) obj).intValue();
            } else {
                floatValue = obj instanceof Float ? (int) ((Float) obj).floatValue() : 0;
            }
            if (hashMap.containsKey("init_pos")) {
                Object obj2 = hashMap.get("init_pos");
                if (obj2 instanceof Integer) {
                    i = ((Integer) obj2).intValue();
                } else if (obj2 instanceof Float) {
                    i = (int) ((Float) obj2).floatValue();
                }
            }
            this.mImuParams = new ImuParams();
            this.mImuParams.setCoordinate(Coordinate.valueOf(floatValue));
            this.mImuParams.setInitPosition(i);
            addImuListener(this.mImuParams, this);
            addEngineMsgListener();
            IARRenderer renderer = getRenderer();
            if (renderer != null) {
                renderer.updateDeviceOrientation();
                if (this.mImuParams.getCoordinate() == Coordinate.RELATIVE) {
                    renderer.calibrationTouchAngle();
                }
                renderer.setImuType(this.mImuParams.getCoordinate());
            }
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("succeeded", 1);
            sendMsg2Engine(302, hashMap2);
        }
        if (ARConfig.getARType() == ARType.IMU.getTypeValue()) {
            StatisticApi.onEvent("imu_set_success");
        }
    }

    @Override // com.baidu.p120ar.imu.ImuListener
    public void onImuUpdate(ImuInfo imuInfo) {
        if (imuInfo != null) {
            updateIMURData(imuInfo.getMatrix(), imuInfo.getInitPos());
        }
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        ARLog.m20421d(TAG, "release()");
        removeImuListener(this);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("succeeded", 1);
        sendMsg2Engine(304, hashMap);
        removeEngineMsgListener(this.mEngineMsgListener);
        this.mImuParams = null;
        super.release();
    }

    private void addEngineMsgListener() {
        if (this.mEngineMsgListener == null) {
            this.mEngineMsgListener = new EngineMsgListener() { // from class: com.baidu.ar.imu.ImuAR.1
                @Override // com.baidu.p120ar.lua.EngineMsgListener
                public List<Integer> getMsgTypesListened() {
                    return Arrays.asList(306, 305);
                }

                @Override // com.baidu.p120ar.lua.EngineMsgListener
                public void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
                    switch (i) {
                        case 305:
                            if (hashMap != null && hashMap.containsKey("with_interaction") && ((Integer) hashMap.get("with_interaction")).intValue() != 0) {
                                r3 = true;
                            }
                            ImuAR.this.isSceneRelocate = true;
                            if (r3 || ImuAR.this.getRenderer() == null) {
                                return;
                            }
                            ImuAR.this.getRenderer().sceneRelocate();
                            return;
                        case 306:
                            if (hashMap.get("is_mirrored") instanceof Integer) {
                                ImuAR.this.mIsDrivenByIMUMirror = ((Integer) hashMap.get("is_mirrored")).intValue() == 1;
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            };
        }
        addEngineMsgListener(this.mEngineMsgListener);
    }

    private void updateIMURData(float[] fArr, int i) {
        if (fArr == null || fArr.length != 16) {
            return;
        }
        System.arraycopy(fArr, 0, this.mMatrixArray, 0, 16);
        this.mCurrentCameraPos.setMatrixValues(this.mMatrixArray);
        this.isDataReady = true;
        this.mInitSceneCenter = i;
        System.arraycopy(fArr, 0, this.mMatrixArray, 0, 16);
        this.mCurrentCameraPos.setMatrixValues(this.mMatrixArray);
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.setEnvironmentDataPipKV("imu", this.mCurrentCameraPos);
        }
        this.isDataReady = true;
        this.mInitSceneCenter = i;
        updateImuData2Renderer();
    }

    private void updateImuData2Renderer() {
        IARRenderer renderer = getRenderer();
        if (this.isDataReady && !this.hasInitImuFirst) {
            this.hasInitImuFirst = true;
            sendMsg2Engine(7001, null);
            if (renderer != null) {
                this.mIsActiveARPlayVersionCase = renderer.isDriverdByARPVersion();
            }
        }
        if (renderer != null) {
            Matrixf4x4 initialTransform = renderer.getInitialTransform();
            Vector3f defaultCameraMatrix = getDefaultCameraMatrix(initialTransform);
            Matrixf4x4 calculateViewMat = calculateViewMat(getRMatrix(defaultCameraMatrix), initialTransform, defaultCameraMatrix);
            if (this.mIsDrivenByIMUMirror) {
                setViewMatMirror(calculateViewMat);
            }
            ImuParams imuParams = this.mImuParams;
            if (imuParams != null && imuParams.getCoordinate() == Coordinate.WORLD && this.mIsActiveARPlayVersionCase) {
                calculateViewMat = transforWorldCoordinate(calculateViewMat);
            }
            RenderCameraData renderCameraData = new RenderCameraData();
            renderCameraData.setAbilityName("ability_imu");
            renderCameraData.setMatrix(calculateViewMat.getMatrix());
            renderer.setOffScreenGuideWork(true);
            renderer.updateRenderCameraData(renderCameraData);
        }
    }

    private Matrixf4x4 getRMatrix(Vector3f vector3f) {
        Matrixf4x4 matrixf4x4 = new Matrixf4x4();
        matrixf4x4.setMatrixValues(this.mCurrentCameraPos.getMatrix());
        matrixf4x4.transpose();
        if (this.isSceneRelocate) {
            relocateScene(matrixf4x4, vector3f);
        }
        float[] fArr = new float[16];
        Matrix.multiplyMM(fArr, this.mBaseMat.getMatrix(), matrixf4x4.getMatrix());
        matrixf4x4.setMatrixValues(fArr);
        return matrixf4x4;
    }

    private Vector3f getDefaultCameraMatrix(Matrixf4x4 matrixf4x4) {
        float[] matrix = matrixf4x4.getMatrix();
        Vector3f vector3f = new Vector3f(matrix[12], matrix[13], matrix[14]);
        Matrixf4x4 matrixf4x42 = new Matrixf4x4();
        matrix[14] = 0.0f;
        matrix[13] = 0.0f;
        matrix[12] = 0.0f;
        matrixf4x42.setMatrixValues(matrix);
        Matrixf4x4 matrixf4x43 = new Matrixf4x4();
        Matrix.invertM(matrixf4x43.getMatrix(), 0, matrixf4x42.getMatrix(), 0);
        Vector4f vector4f = new Vector4f();
        Matrix.multiplyMV3(vector4f.toArray(), matrixf4x43.getMatrix(), vector3f.toArray(), 1.0f);
        vector3f.setXYZ(vector4f.m20430x(), vector4f.m20428y(), vector4f.m20426z());
        return vector3f;
    }

    private Matrixf4x4 calculateViewMat(Matrixf4x4 matrixf4x4, Matrixf4x4 matrixf4x42, Vector3f vector3f) {
        Matrixf4x4 matrixf4x43 = new Matrixf4x4();
        if (this.mInitSceneCenter == 1) {
            if (this.isFirstUpdate) {
                this.isFirstUpdate = false;
                this.mFirstRMat.setMatrixValues(matrixf4x4.getMatrix());
                this.mFirstViewMat.loadIndentity();
                Matrix.translateM(this.mFirstViewMat.getMatrix(), 0, vector3f.getX(), vector3f.getY(), vector3f.getZ());
                float[] fArr = new float[16];
                Matrix.multiplyMM(fArr, matrixf4x4.getMatrix(), this.mFirstViewMat.getMatrix());
                float[] fArr2 = new float[16];
                Matrix.invertM(fArr2, 0, fArr, 0);
                this.mFirstViewMat.setMatrixValues(fArr2);
                matrixf4x43.setMatrixValues(matrixf4x42.getMatrix());
            } else {
                Matrixf4x4 matrixf4x44 = new Matrixf4x4();
                float[] fArr3 = new float[16];
                Matrix.transposeM(fArr3, 0, this.mFirstRMat.getMatrix(), 0);
                float[] fArr4 = new float[16];
                Matrix.multiplyMM(fArr4, fArr3, matrixf4x4.getMatrix());
                matrixf4x44.setMatrixValues(fArr4);
                Matrix.transposeM(fArr4, 0, matrixf4x44.getMatrix(), 0);
                float[] fArr5 = new float[16];
                Matrix.multiplyMM(fArr5, fArr4, this.mFirstViewMat.getMatrix());
                matrixf4x43.setMatrixValues(fArr5);
            }
        } else {
            Matrixf4x4 matrixf4x45 = new Matrixf4x4();
            matrixf4x45.setW0(vector3f.m20438x());
            matrixf4x45.setW1(vector3f.m20436y());
            matrixf4x45.setW2(vector3f.m20434z());
            float[] fArr6 = new float[16];
            Matrix.multiplyMM(fArr6, matrixf4x45.getMatrix(), matrixf4x4.getMatrix());
            float[] fArr7 = new float[16];
            Matrix.invertM(fArr7, 0, fArr6, 0);
            matrixf4x43.setMatrixValues(fArr7);
        }
        return matrixf4x43;
    }

    private void relocateScene(Matrixf4x4 matrixf4x4, Vector3f vector3f) {
        this.isSceneRelocate = false;
        if (this.mInitSceneCenter == 1) {
            this.mFirstRMat.setMatrixValues(matrixf4x4.getMatrix());
            Matrixf4x4 matrixf4x42 = new Matrixf4x4();
            matrixf4x42.setW0(vector3f.m20438x());
            matrixf4x42.setW1(vector3f.m20436y());
            matrixf4x42.setW2(vector3f.m20434z());
            float[] fArr = new float[16];
            Matrix.multiplyMM(fArr, matrixf4x4.getMatrix(), matrixf4x42.getMatrix());
            float[] fArr2 = new float[16];
            Matrix.invertM(fArr2, 0, fArr, 0);
            this.mFirstViewMat.setMatrixValues(fArr2);
            return;
        }
        float[] fArr3 = new float[16];
        Matrix.transposeM(fArr3, 0, matrixf4x4.getMatrix(), 0);
        this.mBaseMat.setMatrixValues(fArr3);
    }

    private void setViewMatMirror(Matrixf4x4 matrixf4x4) {
        float[] fArr = new float[16];
        Matrix.invertM(fArr, 0, matrixf4x4.getMatrix(), 0);
        Matrixf4x4 matrixf4x42 = new Matrixf4x4();
        matrixf4x42.setMatrix(fArr);
        matrixf4x42.setW0(0.0f);
        matrixf4x42.setW1(0.0f);
        matrixf4x42.setW2(0.0f);
        matrixf4x42.setW3(1.0f);
        Vector3f vector3f = new Vector3f(-fArr[12], -fArr[13], -fArr[14]);
        Vector4f vector4f = new Vector4f(0.0f, 0.0f, 1.0f, 1.0f);
        matrixf4x42.multiplyVector4fByMatrix(vector4f);
        Vector3f vector3f2 = new Vector3f(vector4f.m20430x(), vector4f.m20428y(), vector4f.m20426z());
        vector3f2.add(vector3f);
        Vector4f vector4f2 = new Vector4f(0.0f, -1.0f, 0.0f, 1.0f);
        matrixf4x42.multiplyVector4fByMatrix(vector4f2);
        Matrixf4x4 lookAtLH = Matrixf4x4.lookAtLH(vector3f, vector3f2, new Vector3f(vector4f2.m20430x(), vector4f2.m20428y(), vector4f2.m20426z()));
        Matrixf4x4 matrixf4x43 = new Matrixf4x4();
        matrixf4x43.setX0(-1.0f);
        Matrix.multiplyMM(matrixf4x4.getMatrix(), matrixf4x43.getMatrix(), lookAtLH.getMatrix());
    }

    private Matrixf4x4 transforWorldCoordinate(Matrixf4x4 matrixf4x4) {
        if (matrixf4x4 == null) {
            return null;
        }
        Matrixf4x4 matrixf4x42 = new Matrixf4x4();
        Matrixf4x4 matrixf4x43 = new Matrixf4x4();
        Matrix.invertM(matrixf4x42.getMatrix(), 0, matrixf4x4.getMatrix(), 0);
        matrixf4x43.setMatrixValues(matrixf4x42.getMatrix());
        Quaternion quaternion = new Quaternion();
        quaternion.setAxisAngle(new Vector3f(1.0f, 0.0f, 0.0f), -90.0f);
        Matrix.multiplyMM(matrixf4x42.getMatrix(), quaternion.getMatrix4x4().getMatrix(), matrixf4x43.getMatrix());
        Matrixf4x4 matrixf4x44 = new Matrixf4x4();
        Matrix.invertM(matrixf4x44.getMatrix(), 0, matrixf4x42.getMatrix(), 0);
        return matrixf4x44;
    }
}
