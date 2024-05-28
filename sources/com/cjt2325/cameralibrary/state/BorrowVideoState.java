package com.cjt2325.cameralibrary.state;

import android.view.Surface;
import android.view.SurfaceHolder;
import com.cjt2325.cameralibrary.CameraInterface;
import com.cjt2325.cameralibrary.util.LogUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BorrowVideoState implements State {
    private final String TAG = "BorrowVideoState";
    private CameraMachine machine;

    @Override // com.cjt2325.cameralibrary.state.State
    public void capture() {
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void flash(String str) {
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void foucs(float f, float f2, CameraInterface.FocusCallback focusCallback) {
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void record(Surface surface, float f) {
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void restart() {
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void stop() {
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void stopRecord(boolean z, long j) {
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void swtich(SurfaceHolder surfaceHolder, float f) {
    }

    public BorrowVideoState(CameraMachine cameraMachine) {
        this.machine = cameraMachine;
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void start(SurfaceHolder surfaceHolder, float f) {
        CameraInterface.getInstance().doStartPreview(surfaceHolder, f);
        CameraMachine cameraMachine = this.machine;
        cameraMachine.setState(cameraMachine.getPreviewState());
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void cancle(SurfaceHolder surfaceHolder, float f) {
        this.machine.getView().resetState(2);
        CameraMachine cameraMachine = this.machine;
        cameraMachine.setState(cameraMachine.getPreviewState());
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void confirm() {
        this.machine.getView().confirmState(2);
        CameraMachine cameraMachine = this.machine;
        cameraMachine.setState(cameraMachine.getPreviewState());
    }

    @Override // com.cjt2325.cameralibrary.state.State
    public void zoom(float f, int i) {
        LogUtil.m16330i("BorrowVideoState", "zoom");
    }
}
