package com.baidu.p120ar.seg;

import android.text.TextUtils;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arplay.representation.Vector4f;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.arrender.FilterNodeData;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.arrender.RenderNodeData;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.lua.LuaMsgListener;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.seg.SegAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SegAR extends AbstractAR {
    private static final String TAG = "SegAR";
    private String mCurrentBlendNodeName;
    private DetectorCallback mDetectorCallback;
    private LuaMsgListener mLuaMsgListener;
    private SegDetector mSegDetector;
    private static final float[] VEC_ROTATE_RIGHT = {0.0f, -1.0f, 1.0f, 0.0f};
    private static final float[] VEC_ROTATE_LEFT = {0.0f, 1.0f, -1.0f, 0.0f};
    private static final float[] VEC_FLIP_VERTICAL = {1.0f, 0.0f, 0.0f, -1.0f};
    private static final float[] VEC_FLIP_HORIZONTAL = {-1.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] VEC_ROTATE_RIGHT_FLIP_VERTICAL = {0.0f, 1.0f, 1.0f, 0.0f};
    private static final float[] VEC_ROTATE_RIGHT_FLIP_HORIZONTAL = {0.0f, -1.0f, -1.0f, 0.0f};
    private static final float[] VEC_ROTATE_180 = {-1.0f, 0.0f, 0.0f, -1.0f};
    private static final float[] VEC_NO_ROTATE = {1.0f, 0.0f, 0.0f, 1.0f};
    private int mMdlType = 2;
    private String mAbilityName = "ability_image_segmentation";
    private boolean firstFrameResult = true;
    private boolean isFrontCamera = true;
    private int mDeviceOrientation = 0;
    private Vector4f mMaskRotateVec = new Vector4f();
    private float mHairThreshold = 0.0f;
    private float mHairBlendAlpha = 0.0f;
    private int mHairBlendType = 0;
    private float mHairBottomColorR = 0.0f;
    private float mHairBottomColorG = 0.0f;
    private float mHairBottomColorB = 0.0f;
    private float mHairTopColorR = 0.0f;
    private float mHairTopColorG = 0.0f;
    private float mHairTopColorB = 0.0f;
    private float mHairTopPos = 0.0f;
    private float mHairBottomPos = 1.0f;
    private int mMaskWidth = 0;
    private int mMaskHeight = 0;
    private byte[] mMaskData = null;
    private AlgoHandleController mAlgoHandleController = null;
    private int mCurrentHandleType = -1;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        String str = TAG;
        ARLog.m20421d(str, "setup(luaParams):" + hashMap.toString());
        if (this.mAlgoHandleController == null) {
            this.mAlgoHandleController = new AlgoHandleController();
        }
        parseLuaParam(hashMap);
        if (this.mMdlType < -1) {
            String str2 = TAG;
            ARLog.m20419e(str2, "无法解析能力类型 mMdlType:" + this.mMdlType);
            return;
        }
        createSegDetector();
        this.mSegDetector.setAlgoHandleController(this.mAlgoHandleController);
        this.mSegDetector.setAbilityScheme(getAbilityScheme());
        createDetectorCallback();
        setDetectSync(true);
        ARLog.m20421d(TAG, "enableSyncRender true");
        SegDetector segDetector = this.mSegDetector;
        if (segDetector != null) {
            segDetector.setEnableSyncRender(true);
        }
        addDetector(this.mSegDetector, this.mDetectorCallback);
        ARMdlManager.getInstance().setConfigs(getContext(), getMdlConfigs());
        SegDetector segDetector2 = this.mSegDetector;
        if (segDetector2 != null) {
            segDetector2.enableMdl(null);
        }
        addLuaMsgListener();
    }

    private void createDetectorCallback() {
        this.mDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.seg.SegAR.1
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
                String str;
                String str2 = SegAR.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("SegDetector onSetup result:");
                if (resultModel != null) {
                    str = resultModel.getDetectorName() + "," + resultModel.getAlgoType();
                } else {
                    str = null;
                }
                sb.append(str);
                ARLog.m20417i(str2, sb.toString());
                IARRenderer renderer = SegAR.this.getRenderer();
                if (renderer == null || SegAR.this.mSegDetector == null || resultModel == null) {
                    return;
                }
                renderer.addAlgoCache(resultModel.getAlgoType(), SegAR.this.mSegDetector.isDetectSync());
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                if (detectResult instanceof SegResult) {
                    SegResult segResult = (SegResult) detectResult;
                    if (segResult.getModel() != null) {
                        if (SegAR.this.firstFrameResult) {
                            SegAR.this.firstFrameResult = false;
                            HashMap hashMap = new HashMap();
                            hashMap.put("event_name", "first_bgseg_detect");
                            SegAR.this.sendMsg2Lua(hashMap);
                        }
                        IARRenderer renderer = SegAR.this.getRenderer();
                        long resultHandle = detectResult.getResultHandle();
                        if (resultHandle > 0 && renderer != null) {
                            renderer.setAlgoHandleData(resultHandle, SegAR.this.mAbilityName);
                            SegAR.this.onAlgoHandleDestory(resultHandle);
                        }
                        SegModel model = segResult.getModel();
                        if (model != null) {
                            SegAR.this.isFrontCamera = model.isFrontCamera();
                            int width = model.getWidth();
                            int height = model.getHeight();
                            if (SegAR.this.mMdlType == 4 && (model.getOrientation() == 0 || model.getOrientation() == 180 || model.getOrientation() == 2)) {
                                model.setWidth(height);
                                model.setHeight(width);
                            }
                            if (renderer != null) {
                                SegAR.this.mMaskWidth = model.getWidth();
                                SegAR.this.mMaskHeight = model.getHeight();
                                SegAR.this.mMaskData = model.getResult();
                                if (SegAR.this.mMaskWidth <= 0 || SegAR.this.mMaskHeight <= 0 || SegAR.this.mMaskData == null) {
                                    return;
                                }
                                FilterNodeData filterNodeData = new FilterNodeData();
                                filterNodeData.setAbilityName(SegAR.this.mAbilityName);
                                filterNodeData.setNodeName(SegAR.this.mCurrentBlendNodeName);
                                filterNodeData.setValueMap(SegAR.this.updateSegShaderParams(model));
                                renderer.updateFilterNodeData(filterNodeData);
                                if (detectResult.getResultData() instanceof RenderNodeData) {
                                    renderer.updateRenderNodeData((RenderNodeData) detectResult.getResultData(), true);
                                }
                            }
                        }
                    }
                }
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
                String str = SegAR.TAG;
                ARLog.m20417i(str, "SegDetector onRelease aogoTyope = " + resultModel.getAlgoType());
            }
        };
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onAlgoHandleDestory(long j) {
        AlgoHandleController algoHandleController;
        super.onAlgoHandleDestory(j);
        if (j <= 0 || (algoHandleController = this.mAlgoHandleController) == null || algoHandleController.getHandleType(j) != this.mCurrentHandleType) {
            return;
        }
        destroyHandle(j);
    }

    private void destroyHandle(long j) {
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController == null || j <= 0) {
            return;
        }
        long handleType = algoHandleController.getHandleType(j);
        SegDetector segDetector = this.mSegDetector;
        if (segDetector == null || handleType != this.mCurrentHandleType) {
            return;
        }
        segDetector.destroyHandle(j);
    }

    private void parseLuaParam(HashMap<String, Object> hashMap) {
        if (MsgParamsUtil.obj2Int(hashMap.get("id"), -1) == 5011) {
            StatisticApi.onEvent("event_seg_on");
            this.mMdlType = 2;
            this.mCurrentHandleType = 11;
            this.mAbilityName = "ability_image_segmentation";
            String obj2String = MsgParamsUtil.obj2String(hashMap.get("node_name"), "");
            if (TextUtils.isEmpty(obj2String)) {
                return;
            }
            this.mCurrentBlendNodeName = obj2String;
            return;
        }
        String obj2String2 = MsgParamsUtil.obj2String(hashMap.get("event_name"), null);
        if (obj2String2 == null) {
            return;
        }
        if (obj2String2.equals("start_hair_segmentation")) {
            StatisticApi.onEvent("event_hairseg_on");
            setHairSegShaderParams(hashMap);
            this.mMdlType = 4;
            this.mAbilityName = "ability_hair_segmentation";
        } else if (obj2String2.equals("start_sky_segmentation")) {
            StatisticApi.onEvent("event_skyseg_on");
            String obj2String3 = MsgParamsUtil.obj2String(hashMap.get("node_name"), "");
            if (!TextUtils.isEmpty(obj2String3)) {
                this.mCurrentBlendNodeName = obj2String3;
            }
            this.mMdlType = 5;
            this.mAbilityName = "ability_sky_segmentation";
        } else if (obj2String2.equals("start_image_segmentation")) {
            StatisticApi.onEvent("event_seg_on");
            String obj2String4 = MsgParamsUtil.obj2String(hashMap.get("node_name"), "");
            if (!TextUtils.isEmpty(obj2String4)) {
                this.mCurrentBlendNodeName = obj2String4;
            }
            this.mMdlType = 2;
            this.mAbilityName = "ability_image_segmentation";
        } else {
            this.mMdlType = -10;
        }
        switch (this.mMdlType) {
            case 4:
                this.mCurrentHandleType = 13;
                return;
            case 5:
                this.mCurrentHandleType = 12;
                return;
            default:
                this.mCurrentHandleType = 11;
                return;
        }
    }

    private void addLuaMsgListener() {
        if (this.mLuaMsgListener == null) {
            this.mLuaMsgListener = new LuaMsgListener() { // from class: com.baidu.ar.seg.SegAR.2
                @Override // com.baidu.p120ar.lua.LuaMsgListener
                public List<String> getMsgKeyListened() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("event_name");
                    return arrayList;
                }

                @Override // com.baidu.p120ar.lua.LuaMsgListener
                public void onLuaMessage(HashMap<String, Object> hashMap) {
                    if ("adjust_hair_segmentation".equals((String) hashMap.get("event_name"))) {
                        SegAR.this.setHairSegShaderParams(hashMap);
                    }
                }
            };
        }
        addLuaMsgListener(this.mLuaMsgListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHairSegShaderParams(HashMap<String, Object> hashMap) {
        String obj2String = MsgParamsUtil.obj2String(hashMap.get("node_name"), "");
        this.mHairThreshold = MsgParamsUtil.obj2Float(hashMap.get("hair_threshold"), 0.0f);
        this.mHairBlendAlpha = MsgParamsUtil.obj2Float(hashMap.get("hair_blend_alpha"), 0.0f);
        this.mHairBlendType = MsgParamsUtil.obj2Int(hashMap.get("hair_blend_type"), 0);
        this.mHairBottomColorR = MsgParamsUtil.obj2Float(hashMap.get("hair_bottom_color_r"), 0.0f);
        this.mHairBottomColorG = MsgParamsUtil.obj2Float(hashMap.get("hair_bottom_color_g"), 0.0f);
        this.mHairBottomColorB = MsgParamsUtil.obj2Float(hashMap.get("hair_bottom_color_b"), 0.0f);
        this.mHairTopColorR = MsgParamsUtil.obj2Float(hashMap.get("hair_top_color_r"), 0.0f);
        this.mHairTopColorG = MsgParamsUtil.obj2Float(hashMap.get("hair_top_color_g"), 0.0f);
        this.mHairTopColorB = MsgParamsUtil.obj2Float(hashMap.get("hair_top_color_b"), 0.0f);
        if (!TextUtils.isEmpty(obj2String)) {
            this.mCurrentBlendNodeName = obj2String;
        }
        SegDetector segDetector = this.mSegDetector;
        if (segDetector != null) {
            segDetector.setMaskThreshold(this.mHairThreshold);
        }
    }

    private void createSegDetector() {
        switch (this.mMdlType) {
            case 4:
                this.mSegDetector = new HairSegDetector();
                return;
            case 5:
                this.mSegDetector = new SkySegDetector();
                return;
            default:
                this.mSegDetector = new ImgSegDetector();
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, Object> updateSegShaderParams(SegModel segModel) {
        int i = this.mMdlType;
        if (i != 4) {
            if (i == 2) {
                getImgSegOrientation(segModel.getOrientation());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("u_maskRotate", this.mMaskRotateVec);
                return hashMap;
            } else if (i == 5) {
                getSkySegOrientation(segModel.getOrientation());
                HashMap<String, Object> hashMap2 = new HashMap<>();
                hashMap2.put("u_maskRotate", this.mMaskRotateVec);
                return hashMap2;
            } else {
                return null;
            }
        }
        getHairSegOrientation(segModel.getOrientation());
        calculateHairTopAndBottom(segModel.getResult(), segModel.getWidth(), segModel.getHeight());
        HashMap<String, Object> hashMap3 = new HashMap<>();
        hashMap3.put("enableSeq", Float.valueOf(1.0f));
        hashMap3.put("cameraFront", Integer.valueOf(this.isFrontCamera ? 1 : 0));
        hashMap3.put("deviceOrientation", Integer.valueOf(this.mDeviceOrientation));
        hashMap3.put("u_maskRotate", this.mMaskRotateVec);
        hashMap3.put("hairBlendType", Integer.valueOf(this.mHairBlendType));
        hashMap3.put("hairThreshold", Float.valueOf(this.mHairThreshold));
        hashMap3.put("hairBlendAlpha", Float.valueOf(this.mHairBlendAlpha));
        hashMap3.put("hairTopPos", Float.valueOf(this.mHairTopPos));
        hashMap3.put("hairBottomPos", Float.valueOf(this.mHairBottomPos));
        hashMap3.put("hairTop", new Vector4f(this.mHairTopColorR, this.mHairTopColorG, this.mHairTopColorB, 1.0f));
        hashMap3.put("hairBottom", new Vector4f(this.mHairBottomColorR, this.mHairBottomColorG, this.mHairBottomColorB, 1.0f));
        return hashMap3;
    }

    public void getHairSegOrientation(int i) {
        if (i == 0) {
            this.mDeviceOrientation = 1;
            updateRenderRotateVector(this.isFrontCamera ? PixelRotation.RotateRightFlipHorizontal : PixelRotation.RotateRight);
            return;
        }
        if (i == 90) {
            this.mDeviceOrientation = this.isFrontCamera ? 2 : 0;
            updateRenderRotateVector(this.isFrontCamera ? PixelRotation.FlipVertical : PixelRotation.NoRotation);
        } else if (i == 180) {
            this.mDeviceOrientation = 3;
            updateRenderRotateVector(this.isFrontCamera ? PixelRotation.RotateRightFlipVertical : PixelRotation.RotateLeft);
        } else {
            this.mDeviceOrientation = this.isFrontCamera ? 0 : 2;
            updateRenderRotateVector(this.isFrontCamera ? PixelRotation.FlipHorizontal : PixelRotation.Rotate180);
        }
    }

    public void getSkySegOrientation(int i) {
        switch (i) {
            case 0:
                this.mDeviceOrientation = this.isFrontCamera ? 1 : 3;
                updateRenderRotateVector(this.isFrontCamera ? PixelRotation.RotateRightFlipHorizontal : PixelRotation.RotateRight);
                return;
            case 1:
                this.mDeviceOrientation = this.isFrontCamera ? 2 : 0;
                updateRenderRotateVector(this.isFrontCamera ? PixelRotation.FlipVertical : PixelRotation.NoRotation);
                return;
            case 2:
                this.mDeviceOrientation = this.isFrontCamera ? 3 : 1;
                updateRenderRotateVector(this.isFrontCamera ? PixelRotation.RotateRightFlipVertical : PixelRotation.RotateLeft);
                return;
            default:
                this.mDeviceOrientation = this.isFrontCamera ? 0 : 2;
                updateRenderRotateVector(this.isFrontCamera ? PixelRotation.FlipHorizontal : PixelRotation.Rotate180);
                return;
        }
    }

    public void getImgSegOrientation(int i) {
        switch (i) {
            case 0:
                this.mDeviceOrientation = this.isFrontCamera ? 1 : 3;
                updateRenderRotateVector(this.isFrontCamera ? PixelRotation.RotateRightFlipHorizontal : PixelRotation.RotateRight);
                return;
            case 1:
                this.mDeviceOrientation = this.isFrontCamera ? 2 : 0;
                updateRenderRotateVector(this.isFrontCamera ? PixelRotation.FlipVertical : PixelRotation.NoRotation);
                return;
            case 2:
                this.mDeviceOrientation = this.isFrontCamera ? 3 : 1;
                updateRenderRotateVector(this.isFrontCamera ? PixelRotation.RotateRightFlipVertical : PixelRotation.RotateLeft);
                return;
            default:
                this.mDeviceOrientation = this.isFrontCamera ? 0 : 2;
                updateRenderRotateVector(this.isFrontCamera ? PixelRotation.FlipHorizontal : PixelRotation.Rotate180);
                return;
        }
    }

    private void calculateHairTopAndBottom(byte[] bArr, int i, int i2) {
        if (bArr == null || i <= 0 || i2 <= 0 || bArr.length < i * i2) {
            return;
        }
        this.mHairTopPos = findTop(bArr, i, i2);
        this.mHairBottomPos = findBottom(bArr, i, i2);
        int i3 = this.mDeviceOrientation;
        if (i3 == 1 || i3 == 3) {
            float f = i2;
            this.mHairTopPos -= 16.0f / f;
            this.mHairBottomPos += 12.0f / f;
        }
    }

    private float findTop(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            for (int i4 = 0; i4 < i; i4++) {
                if ((bArr[(i3 * i) + i4] & 255) > ((int) (this.mHairThreshold * 255.0f))) {
                    return (i3 >= 3 ? i3 - 3 : 0) / i2;
                }
            }
            i3 += 3;
        }
        return 0.0f;
    }

    private float findBottom(byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        for (int i4 = i3; i4 > 0; i4 -= 3) {
            for (int i5 = 0; i5 < i; i5++) {
                if ((bArr[(i4 * i) + i5] & 255) > ((int) (this.mHairThreshold * 255.0f))) {
                    int i6 = i4 + 3;
                    if (i6 > i3) {
                        i6 = i3;
                    }
                    return i6 / i2;
                }
            }
        }
        return 1.0f;
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        setDetectSync(false);
        ARLog.m20417i(TAG, "enableSyncRender false");
        SegDetector segDetector = this.mSegDetector;
        if (segDetector != null) {
            segDetector.setEnableSyncRender(false);
        }
        removeLuaMsgListener(this.mLuaMsgListener);
        SegDetector segDetector2 = this.mSegDetector;
        if (segDetector2 != null) {
            segDetector2.setAlgoHandleController(null);
            this.mSegDetector.disableMdl();
            removeDetector(this.mSegDetector);
        }
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController != null) {
            algoHandleController.release();
            this.mAlgoHandleController = null;
        }
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.removeAlgoCache(this.mCurrentHandleType);
        }
        super.release();
    }

    private void updateRenderRotateVector(PixelRotation pixelRotation) {
        float[] fArr;
        switch (pixelRotation) {
            case RotateRight:
                fArr = VEC_ROTATE_RIGHT;
                break;
            case RotateLeft:
                fArr = VEC_ROTATE_LEFT;
                break;
            case FlipVertical:
                fArr = VEC_FLIP_VERTICAL;
                break;
            case FlipHorizontal:
                fArr = VEC_FLIP_HORIZONTAL;
                break;
            case RotateRightFlipVertical:
                fArr = VEC_ROTATE_RIGHT_FLIP_VERTICAL;
                break;
            case RotateRightFlipHorizontal:
                fArr = VEC_ROTATE_RIGHT_FLIP_HORIZONTAL;
                break;
            case Rotate180:
                fArr = VEC_ROTATE_180;
                break;
            default:
                fArr = VEC_NO_ROTATE;
                break;
        }
        this.mMaskRotateVec.setXYZW(fArr[0], fArr[1], fArr[2], fArr[3]);
    }
}
