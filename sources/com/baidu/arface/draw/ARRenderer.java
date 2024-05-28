package com.baidu.arface.draw;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARRenderer implements GLSurfaceView.Renderer {
    private static final String TAG = "ARRenderer";
    private ARDrawer mARDrawer;
    private SurfaceTexture.OnFrameAvailableListener mARFrameListener;
    private int mARHeight;
    private ARRenderCallback mARRenderCallback;
    private SurfaceTexture mARTexture;
    private int mARTextureID;
    private int mARWidth;
    private ARDrawer mCameraDrawer;
    private SurfaceTexture.OnFrameAvailableListener mCameraFrameListener;
    private int mCameraHeight;
    private SurfaceTexture mCameraTexture;
    private int mCameraTextureID;
    private int mCameraWidth;
    private volatile boolean mDrawAR = true;
    private boolean mDrawerChanged = false;
    private boolean mScreenLandscape;

    public ARRenderer(boolean z) {
        this.mScreenLandscape = z;
        if (this.mCameraTexture == null) {
            this.mCameraTexture = new SurfaceTexture(createTexture(3553));
        }
        if (this.mARTexture == null) {
            this.mARTexture = new SurfaceTexture(createTexture(3553));
        }
    }

    public SurfaceTexture getCameraTexture() {
        return this.mCameraTexture;
    }

    public void setDrawAR(boolean z) {
        this.mDrawAR = z;
        this.mDrawerChanged = true;
    }

    public void setCameraFrameListener(SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener) {
        this.mCameraFrameListener = onFrameAvailableListener;
        setCameraFrameListener();
    }

    private void setCameraFrameListener() {
        SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener;
        SurfaceTexture surfaceTexture = this.mCameraTexture;
        if (surfaceTexture == null || (onFrameAvailableListener = this.mCameraFrameListener) == null) {
            return;
        }
        surfaceTexture.setOnFrameAvailableListener(onFrameAvailableListener);
    }

    public void setARRenderCallback(ARRenderCallback aRRenderCallback) {
        this.mARRenderCallback = aRRenderCallback;
        ARRenderCallback aRRenderCallback2 = this.mARRenderCallback;
        if (aRRenderCallback2 != null) {
            aRRenderCallback2.onCameraDrawerCreated(this.mCameraTexture, 1280, 720);
        }
        ARRenderCallback aRRenderCallback3 = this.mARRenderCallback;
        if (aRRenderCallback3 != null) {
            aRRenderCallback3.onARDrawerCreated(this.mARTexture, this.mARFrameListener, this.mARWidth, this.mARHeight);
        }
    }

    public void setARFrameListener(SurfaceTexture.OnFrameAvailableListener onFrameAvailableListener) {
        this.mARFrameListener = onFrameAvailableListener;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        this.mCameraTextureID = createTexture(3553);
        this.mARTextureID = createTexture(3553);
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        GLES20.glViewport(0, 0, i, i2);
        this.mARWidth = i;
        this.mARHeight = i2;
        if (this.mCameraDrawer == null) {
            this.mCameraDrawer = new ARDrawer(this.mCameraTextureID, 3553, this.mScreenLandscape);
        }
        if (this.mARDrawer == null) {
            this.mARDrawer = new ARDrawer(this.mARTextureID, 3553, this.mScreenLandscape);
        }
        ARRenderCallback aRRenderCallback = this.mARRenderCallback;
        if (aRRenderCallback != null) {
            aRRenderCallback.onARDrawerChanged(this.mARTexture, this.mARWidth, this.mARHeight);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        updateDrawer();
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        GLES20.glClear(16640);
        try {
            if (this.mDrawAR) {
                if (this.mARTexture != null) {
                    Log.d("ARRenderer", "mARTexture.updateTexImage(); ");
                    this.mARTexture.updateTexImage();
                    float[] fArr = new float[16];
                    this.mARTexture.getTransformMatrix(fArr);
                    this.mARDrawer.draw(fArr);
                }
            } else if (this.mCameraTexture != null) {
                this.mCameraTexture.updateTexImage();
                float[] fArr2 = new float[16];
                this.mCameraTexture.getTransformMatrix(fArr2);
                this.mCameraDrawer.draw(fArr2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDrawer() {
        if (this.mDrawerChanged) {
            try {
                this.mARTexture.detachFromGLContext();
            } catch (Exception e) {
                Log.e("ARRenderer", "onSurfaceChanged attachToGLContext error!!!");
                e.printStackTrace();
            }
            try {
                this.mCameraTexture.detachFromGLContext();
            } catch (Exception e2) {
                Log.e("ARRenderer", "onSurfaceChanged attachToGLContext error!!!");
                e2.printStackTrace();
            }
            try {
                if (this.mDrawAR) {
                    this.mARTexture.attachToGLContext(this.mARTextureID);
                } else {
                    this.mCameraTexture.attachToGLContext(this.mCameraTextureID);
                }
            } catch (Exception e3) {
                Log.e("ARRenderer", "onSurfaceChanged attachToGLContext error!!!");
                e3.printStackTrace();
            }
            this.mDrawerChanged = false;
        }
    }

    public void release() {
        if (this.mARDrawer != null) {
            this.mARDrawer = null;
        }
        SurfaceTexture surfaceTexture = this.mARTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mARTexture = null;
        }
        this.mARRenderCallback = null;
    }

    private int createTexture(int i) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(i, iArr[0]);
        GLES20.glTexParameterf(i, 10241, 9729.0f);
        GLES20.glTexParameterf(i, 10240, 9729.0f);
        GLES20.glTexParameteri(i, 10242, 33071);
        GLES20.glTexParameteri(i, 10243, 33071);
        return iArr[0];
    }
}
