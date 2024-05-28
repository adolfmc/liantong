package com.sinovatech.unicom.separatemodule.idcard.camera1.view;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.IOException;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "main";
    private Camera mCamera;
    private SurfaceHolder mHolder;

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.mCamera = camera;
        this.mHolder = getHolder();
        this.mHolder.addCallback(this);
        this.mHolder.setType(3);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            this.mCamera.setPreviewDisplay(surfaceHolder);
            this.mCamera.startPreview();
        } catch (IOException unused) {
            Log.d("main", "预览失败");
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.mHolder.getSurface() == null) {
            return;
        }
        try {
            this.mCamera.stopPreview();
        } catch (Exception unused) {
            Log.d("main", "当Surface改变后，停止预览出错");
        }
        try {
            this.mCamera.setPreviewDisplay(this.mHolder);
            this.mCamera.startPreview();
        } catch (Exception unused2) {
            Log.d("main", "预览Camera出错");
        }
    }
}
