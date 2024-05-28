package com.baidu.cloud.frameprocessor.p133ar;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.baidu.arface.draw.ARRenderCallback;
import com.baidu.cloud.frameprocessor.gles.FullFrameRect;
import com.baidu.cloud.frameprocessor.gles.GlUtil;
import com.baidu.cloud.frameprocessor.gles.OpenGlUtils;
import com.baidu.cloud.frameprocessor.processor.BaseFrameProcessor;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.baidu.minivideo.arface.ARControllerProxy;
import com.baidu.minivideo.arface.ArFaceSdk;
import com.baidu.minivideo.arface.DuMixCallbackAdapter;
import com.baidu.minivideo.arface.bean.BeautyEnableStatus;
import com.baidu.p120ar.DefinedLuaListener;
import com.baidu.p120ar.DuMixCallback;
import com.baidu.p120ar.arrender.Texture;
import com.baidu.p120ar.face.FaceListener;
import com.baidu.p120ar.face.FaceResultData;
import com.baidu.p120ar.filter.FilterNode;
import com.baidu.p120ar.filter.FilterStateListener;
import com.baidu.p120ar.lua.LuaMsgListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.frameprocessor.ar.ARBaseProcessor */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARBaseProcessor extends BaseFrameProcessor implements ARRenderCallback {
    public static final float GENDER_MALE_DEFAULT = 0.8f;
    private static final String TAG = "com.baidu.cloud.frameprocessor.ar.ARBaseProcessor";
    private WeakReference<Context> mContext;
    protected DuArProcessorCallback mDuArProcessorCallback;
    protected ARControllerProxy mEffect;
    private Texture mInputTexture;
    protected boolean mIsSetup;
    private CountDownLatch mLatch;
    private Texture mOutputTexture;
    protected boolean mMale = true;
    private BeautyEnableStatus beautyEnableStatus = new BeautyEnableStatus();
    private volatile boolean mSyncInputContent = false;
    private int mInputFrameBuffer = -1;
    private int mFboInputTexId = -1;
    private boolean mSkipARFrame = true;
    private ARControllerProxy.IDumixHolder mEffectHolder = new ARControllerProxy.IDumixHolder() { // from class: com.baidu.cloud.frameprocessor.ar.ARBaseProcessor.1
        @Override // com.baidu.minivideo.arface.ARControllerProxy.IDumixHolder
        public void onHolderChanged(ARControllerProxy.IDumixHolder iDumixHolder) {
        }
    };
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    private boolean isFrontCamera() {
        return false;
    }

    protected boolean checkEffectData() {
        return false;
    }

    @Override // com.baidu.arface.draw.ARRenderCallback
    public void onARDrawerChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public ARBaseProcessor(Context context) {
        this.mContext = new WeakReference<>(context.getApplicationContext());
    }

    @Override // com.baidu.cloud.frameprocessor.processor.BaseFrameProcessor, com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void init(FullFrameRect fullFrameRect, FullFrameRect fullFrameRect2) {
        super.init(fullFrameRect, fullFrameRect2);
        createArInstance();
    }

    private void createArInstance() {
        if (ARControllerProxy.checkProxy(this.mEffect, this.mEffectHolder)) {
            return;
        }
        this.mEffect = ARControllerProxy.getInstance(this.mContext.get(), this.mEffectHolder, EGL14.eglGetCurrentContext(), ArFaceSdk.getArLicense());
        ARControllerProxy.setSyncInputContent(this.mSyncInputContent);
        this.mEffect.setRenderFinishListener();
        this.mEffect.setFaceListener(new FaceListener() { // from class: com.baidu.cloud.frameprocessor.ar.ARBaseProcessor.2
            @Override // com.baidu.p120ar.face.FaceListener
            public void onStickerLoadingFinished(List<String> list) {
            }

            @Override // com.baidu.p120ar.face.FaceListener
            public void onTriggerFired(String str) {
            }

            @Override // com.baidu.p120ar.face.FaceListener
            public void onFaceResult(Object obj) {
                FaceResultData faceResultData;
                float[] genders;
                if (obj == null || !(obj instanceof FaceResultData) || (genders = (faceResultData = (FaceResultData) obj).getGenders()) == null) {
                    return;
                }
                boolean z = genders[0] > 0.8f;
                ARBaseProcessor aRBaseProcessor = ARBaseProcessor.this;
                aRBaseProcessor.mMale = z;
                if (aRBaseProcessor.mDuArProcessorCallback != null) {
                    ARBaseProcessor.this.mDuArProcessorCallback.onChangeGender(z);
                }
                if (faceResultData.getFaceIds() == null) {
                }
            }
        });
    }

    @Override // com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public VideoFrameBuffer onFrame(VideoFrameBuffer videoFrameBuffer) {
        if (!this.mEnable || videoFrameBuffer == null || videoFrameBuffer.textureBuffer == null) {
            return videoFrameBuffer;
        }
        int width = getWidth(videoFrameBuffer);
        int height = getHeight(videoFrameBuffer);
        if (this.mInputTexture == null) {
            initInputTexture(width, height);
            setupArInstance(this.mInputTexture, width, height);
            if (this.mSyncInputContent && !this.mIsSetup) {
                try {
                    Log.d(TAG, "----- waiting -----");
                    this.mLatch = new CountDownLatch(1);
                    try {
                        this.mLatch.await();
                    } catch (Exception unused) {
                    }
                    Log.d(TAG, "----- notified -----");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.d(TAG, "----- initAr started -----");
            }
        }
        if (!this.mSyncInputContent && this.mSkipARFrame && !checkEffectData()) {
            Log.d(TAG, "skip AR --- no ar effect");
            return videoFrameBuffer;
        } else if (!this.mIsSetup) {
            Log.d(TAG, "skip AR --- AR not setup or fail to setup");
            return videoFrameBuffer;
        } else {
            ARControllerProxy aRControllerProxy = this.mEffect;
            int outputTexId = aRControllerProxy != null ? aRControllerProxy.getOutputTexId() : 0;
            if (outputTexId == 0 || this.mEffect == null) {
                String str = TAG;
                Log.d(str, "skip AR --- AR output texture : " + outputTexId);
                return videoFrameBuffer;
            }
            bindFbo(this.mInputFrameBuffer, this.mFboInputTexId);
            drawInputFrame(width, height, videoFrameBuffer);
            unbindFbo();
            if (!this.mSyncInputContent && this.mSkipARFrame) {
                GLES20.glFinish();
            }
            GLES20.glBindTexture(3553, outputTexId);
            GLES20.glViewport(0, 0, width, height);
            glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            this.mEffect.render();
            GLES20.glBindTexture(3553, 0);
            if (!this.mSyncInputContent && this.mSkipARFrame) {
                Log.d(TAG, "skip AR --- ar first frame when ar is async");
                this.mSkipARFrame = false;
                return videoFrameBuffer;
            } else if (this.mSwitchCameraFlag) {
                Log.d(TAG, "skip AR --- switch camera");
                this.mSkipARFrame = true;
                return videoFrameBuffer;
            } else {
                return getOutputData(videoFrameBuffer, outputTexId);
            }
        }
    }

    private void initInputTexture(int i, int i2) {
        if (this.mFboInputTexId == -1) {
            this.mFboInputTexId = OpenGlUtils.createTexture2DObject();
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
            GLES20.glBindTexture(3553, 0);
            this.mInputFrameBuffer = GlUtil.createFrameBufferObject();
        }
        if (this.mInputTexture == null) {
            this.mInputTexture = new Texture();
            this.mInputTexture.setId(this.mFboInputTexId);
            this.mInputTexture.setHandle(-1L);
            this.mInputTexture.setType(3553);
        }
    }

    private void setupArInstance(Texture texture, int i, int i2) {
        if (texture == null) {
            return;
        }
        ARControllerProxy aRControllerProxy = this.mEffect;
        if (aRControllerProxy != null) {
            aRControllerProxy.setInputTexture(texture);
            String str = TAG;
            Log.d(str, "check ar size ：width = " + i + " , height = " + i2);
            onCameraDrawerCreated(null, i, i2);
            onARDrawerCreated(null, null, i, i2);
            return;
        }
        Log.d(TAG, "setInput Effect == null");
    }

    @Override // com.baidu.cloud.frameprocessor.processor.BaseFrameProcessor, com.baidu.cloud.frameprocessor.processor.IFrameProcessor
    public void release() {
        super.release();
        releaseArTex();
        ARControllerProxy aRControllerProxy = this.mEffect;
        if (aRControllerProxy != null) {
            aRControllerProxy.release();
            this.mEffect = null;
        }
        this.mSkipARFrame = true;
    }

    private void releaseArTex() {
        if (this.mInputTexture != null) {
            this.mInputTexture = null;
        }
        if (this.mFboInputTexId != -1) {
            GlUtil.destroyFramebufferObject(this.mInputFrameBuffer);
            GlUtil.destroyTextureObject(this.mFboInputTexId);
            this.mFboInputTexId = -1;
            this.mInputFrameBuffer = -1;
        }
        Texture texture = this.mOutputTexture;
        if (texture != null) {
            GlUtil.destroyTextureObject(texture.getId());
            this.mOutputTexture = null;
        }
    }

    public void onResume() {
        ARControllerProxy aRControllerProxy = this.mEffect;
        if (aRControllerProxy != null) {
            aRControllerProxy.resume();
        }
    }

    public void onPause() {
        ARControllerProxy aRControllerProxy = this.mEffect;
        if (aRControllerProxy != null) {
            aRControllerProxy.pause();
        }
    }

    public void setSyncInputContent(boolean z) {
        this.mSyncInputContent = z;
    }

    public boolean isSetup() {
        return this.mIsSetup && ARControllerProxy.checkProxy(this.mEffect, this.mEffectHolder);
    }

    public void setCallback(DuArProcessorCallback duArProcessorCallback) {
        this.mDuArProcessorCallback = duArProcessorCallback;
    }

    @Override // com.baidu.arface.draw.ARRenderCallback
    public void onCameraDrawerCreated(SurfaceTexture surfaceTexture, int i, int i2) {
        ARControllerProxy aRControllerProxy = this.mEffect;
        if (aRControllerProxy != null) {
            aRControllerProxy.onCameraDrawerCreated(surfaceTexture, i, i2);
        } else {
            Log.d(TAG, "onCameraDrawerCreated Effect == null");
        }
    }

    @Override // com.baidu.arface.draw.ARRenderCallback
    public void onARDrawerCreated(SurfaceTexture surfaceTexture, SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener, int i, int i2) {
        if (this.mEffect != null) {
            initOutputTexture(i, i2);
            this.mEffect.setOutputTexture(this.mOutputTexture);
            this.mEffect.setDefinedLuaListener(new DefinedLuaListener() { // from class: com.baidu.cloud.frameprocessor.ar.ARBaseProcessor.3
                @Override // com.baidu.p120ar.DefinedLuaListener
                public void onOpenUrl(String str, int i3, HashMap<String, Object> hashMap) {
                }

                @Override // com.baidu.p120ar.DefinedLuaListener
                public void onRequireSwitchCamera(int i3) {
                    if (ARBaseProcessor.this.mDuArProcessorCallback != null) {
                        ARBaseProcessor.this.mDuArProcessorCallback.onStickerSwitchCamera(i3);
                    }
                }
            });
            this.mEffect.addLuaMsgListener(new LuaMsgListener() { // from class: com.baidu.cloud.frameprocessor.ar.ARBaseProcessor.4
                @Override // com.baidu.p120ar.lua.LuaMsgListener
                public List<String> getMsgKeyListened() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("event_name");
                    return arrayList;
                }

                @Override // com.baidu.p120ar.lua.LuaMsgListener
                public void onLuaMessage(HashMap<String, Object> hashMap) {
                    if (ARBaseProcessor.this.mDuArProcessorCallback != null) {
                        ARBaseProcessor.this.mDuArProcessorCallback.onLuaMessage(hashMap);
                    }
                }
            });
            this.mEffect.onARDrawerCreated(surfaceTexture, onFrameAvailableListener, isFrontCamera(), i, i2, generateDuMixCallback());
            this.mEffect.setFilterStateListener(new FilterStateListener() { // from class: com.baidu.cloud.frameprocessor.ar.ARBaseProcessor.5
                @Override // com.baidu.p120ar.filter.FilterStateListener
                public void onFilterStateChanged(HashMap<FilterNode, Boolean> hashMap, String str) {
                    Boolean bool;
                    Boolean bool2;
                    Boolean bool3;
                    Boolean bool4 = null;
                    if (hashMap != null) {
                        bool4 = hashMap.get(FilterNode.makeupFilter);
                        bool2 = hashMap.get(FilterNode.lutFilter);
                        bool3 = hashMap.get(FilterNode.skinFilter);
                        bool = hashMap.get(FilterNode.faceFilter);
                    } else {
                        bool = null;
                        bool2 = null;
                        bool3 = null;
                    }
                    boolean z = false;
                    ARBaseProcessor.this.beautyEnableStatus.setMakeupEnable(bool4 == null || bool4.booleanValue());
                    ARBaseProcessor.this.beautyEnableStatus.setFilterLutEnable(bool2 == null || bool2.booleanValue());
                    ARBaseProcessor.this.beautyEnableStatus.setFaceSkinEnable(bool3 == null || bool3.booleanValue());
                    BeautyEnableStatus beautyEnableStatus = ARBaseProcessor.this.beautyEnableStatus;
                    if (bool == null || bool.booleanValue()) {
                        z = true;
                    }
                    beautyEnableStatus.setFeaturesEnable(z);
                    if (ARBaseProcessor.this.mDuArProcessorCallback != null) {
                        ARBaseProcessor.this.mMainHandler.post(new Runnable() { // from class: com.baidu.cloud.frameprocessor.ar.ARBaseProcessor.5.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ARBaseProcessor.this.mDuArProcessorCallback.onBeautyEnableChanged(ARBaseProcessor.this.beautyEnableStatus);
                            }
                        });
                    }
                }
            });
            return;
        }
        Log.d(TAG, "onARDrawerCreated Effect == null");
    }

    private Texture initOutputTexture(int i, int i2) {
        if (this.mOutputTexture == null) {
            int createTexture2DObject = OpenGlUtils.createTexture2DObject();
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
            GLES20.glBindTexture(3553, 0);
            this.mOutputTexture = new Texture();
            this.mOutputTexture.setId(createTexture2DObject);
            this.mOutputTexture.setType(3553);
        }
        return this.mOutputTexture;
    }

    protected DuMixCallback generateDuMixCallback() {
        return new DuMixCallbackAdapter();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSetup(boolean z) {
        this.mIsSetup = z;
        CountDownLatch countDownLatch = this.mLatch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}
