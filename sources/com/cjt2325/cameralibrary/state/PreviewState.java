package com.cjt2325.cameralibrary.state;

import android.graphics.Bitmap;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.cjt2325.cameralibrary.CameraInterface;
import com.cjt2325.cameralibrary.util.LogUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class PreviewState implements State {
    public static final String TAG = "PreviewState";
    private CameraMachine machine;

    @Override // com.cjt2325.cameralibrary.state.State
    public void restart() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PreviewState(CameraMachine cameraMachine) {
        this.machine = cameraMachine;
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void start(SurfaceHolder surfaceHolder, float f) {
        CameraInterface.getInstance().doStartPreview(surfaceHolder, f);
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void stop() {
        CameraInterface.getInstance().doStopPreview();
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void foucs(float f, float f2, CameraInterface.FocusCallback focusCallback) {
        LogUtil.m16331i("preview state foucs");
        if (this.machine.getView().handlerFoucs(f, f2)) {
            CameraInterface.getInstance().handleFocus(this.machine.getContext(), f, f2, focusCallback);
        }
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void swtich(SurfaceHolder surfaceHolder, float f) {
        CameraInterface.getInstance().switchCamera(surfaceHolder, f);
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void capture() {
        CameraInterface.getInstance().takePicture(new CameraInterface.TakePictureCallback() { // from class: com.cjt2325.cameralibrary.state.PreviewState.1
            @Override // com.cjt2325.cameralibrary.CameraInterface.TakePictureCallback
            public void captureResult(Bitmap bitmap, boolean z) {
                PreviewState.this.machine.getView().showPicture(bitmap, z);
                PreviewState.this.machine.setState(PreviewState.this.machine.getBorrowPictureState());
                LogUtil.m16331i("capture");
            }
        });
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void record(Surface surface, float f) {
        CameraInterface.getInstance().startRecord(surface, f, null);
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void stopRecord(final boolean z, long j) {
        CameraInterface.getInstance().stopRecord(z, new CameraInterface.StopRecordCallback() { // from class: com.cjt2325.cameralibrary.state.PreviewState.2
            @Override // com.cjt2325.cameralibrary.CameraInterface.StopRecordCallback
            public void recordResult(String str, Bitmap bitmap) {
                if (z) {
                    PreviewState.this.machine.getView().resetState(3);
                    return;
                }
                PreviewState.this.machine.getView().playVideo(bitmap, str);
                PreviewState.this.machine.setState(PreviewState.this.machine.getBorrowVideoState());
            }
        });
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void cancle(SurfaceHolder surfaceHolder, float f) {
        LogUtil.m16331i("浏览状态下,没有 cancle 事件");
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void confirm() {
        LogUtil.m16331i("浏览状态下,没有 confirm 事件");
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void zoom(float f, int i) {
        LogUtil.m16330i(TAG, "zoom");
        CameraInterface.getInstance().setZoom(f, i);
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void flash(String str) {
        CameraInterface.getInstance().setFlashMode(str);
    }
}
