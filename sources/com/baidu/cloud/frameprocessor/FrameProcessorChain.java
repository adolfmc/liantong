package com.baidu.cloud.frameprocessor;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import com.baidu.cloud.frameprocessor.gles.FullFrameRect;
import com.baidu.cloud.frameprocessor.gles.Texture2dProgram;
import com.baidu.cloud.frameprocessor.processor.IFrameProcessor;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FrameProcessorChain {
    private List<IFrameProcessor> iFrameProcessors;
    private FullFrameRect mFullScreen2D;
    private FullFrameRect mFullScreenEXT;
    private boolean mHasInit;
    private float[] mLastMatrix;
    private int mLastRotation = -1;
    private final LinkedList<Runnable> runOnDraw = new LinkedList<>();

    public void setProcessors(List<IFrameProcessor> list) {
        this.iFrameProcessors = list;
    }

    public List<IFrameProcessor> getProcessors() {
        return this.iFrameProcessors;
    }

    public void init() {
        if (this.mHasInit) {
            return;
        }
        this.mFullScreenEXT = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_EXT));
        this.mFullScreen2D = new FullFrameRect(new Texture2dProgram(Texture2dProgram.ProgramType.TEXTURE_2D));
        this.mHasInit = true;
        List<IFrameProcessor> list = this.iFrameProcessors;
        if (list != null) {
            for (IFrameProcessor iFrameProcessor : list) {
                iFrameProcessor.init(this.mFullScreenEXT, this.mFullScreen2D);
            }
        }
    }

    public void release() {
        FullFrameRect fullFrameRect = this.mFullScreenEXT;
        if (fullFrameRect != null) {
            fullFrameRect.release(true);
            this.mFullScreenEXT = null;
        }
        FullFrameRect fullFrameRect2 = this.mFullScreen2D;
        if (fullFrameRect2 != null) {
            fullFrameRect2.release(true);
            this.mFullScreen2D = null;
        }
        this.mHasInit = false;
        this.mLastRotation = -1;
        this.mLastMatrix = null;
        List<IFrameProcessor> list = this.iFrameProcessors;
        if (list != null) {
            for (IFrameProcessor iFrameProcessor : list) {
                iFrameProcessor.release();
            }
        }
    }

    private void runPendingOnDrawTasks() {
        synchronized (this.runOnDraw) {
            while (!this.runOnDraw.isEmpty()) {
                this.runOnDraw.removeFirst().run();
            }
        }
    }

    public void runOnDraw(Runnable runnable) {
        synchronized (this.runOnDraw) {
            this.runOnDraw.addLast(runnable);
        }
    }

    public void runPendingTasks() {
        runPendingOnDrawTasks();
    }

    public VideoFrameBuffer onFrame(VideoFrameBuffer videoFrameBuffer) {
        if (this.iFrameProcessors == null) {
            return videoFrameBuffer;
        }
        if (this.mLastMatrix == null && videoFrameBuffer.transformMatrix != null) {
            this.mLastMatrix = new float[16];
            System.arraycopy(videoFrameBuffer.transformMatrix, 0, this.mLastMatrix, 0, videoFrameBuffer.transformMatrix.length);
        }
        if (this.mLastRotation == -1) {
            this.mLastRotation = videoFrameBuffer.rotation;
        }
        boolean checkSwitchFrame = checkSwitchFrame(videoFrameBuffer);
        System.currentTimeMillis();
        VideoFrameBuffer videoFrameBuffer2 = null;
        VideoFrameBuffer videoFrameBuffer3 = null;
        for (int i = 0; i < this.iFrameProcessors.size(); i++) {
            IFrameProcessor iFrameProcessor = this.iFrameProcessors.get(i);
            iFrameProcessor.setSwitchCameraFlag(checkSwitchFrame);
            System.currentTimeMillis();
            videoFrameBuffer3 = iFrameProcessor.onFrame(videoFrameBuffer2 == null ? videoFrameBuffer : videoFrameBuffer2);
            if (i != this.iFrameProcessors.size() - 1) {
                videoFrameBuffer2 = videoFrameBuffer3;
            }
        }
        if (checkSwitchFrame) {
            GLES20.glFinish();
        }
        return videoFrameBuffer3 != null ? videoFrameBuffer3 : videoFrameBuffer;
    }

    private boolean checkSwitchFrame(VideoFrameBuffer videoFrameBuffer) {
        if (this.mLastRotation != videoFrameBuffer.rotation) {
            this.mLastRotation = videoFrameBuffer.rotation;
            return true;
        } else if (videoFrameBuffer.transformMatrix == null || this.mLastMatrix == null || Arrays.equals(videoFrameBuffer.transformMatrix, this.mLastMatrix)) {
            return false;
        } else {
            System.arraycopy(videoFrameBuffer.transformMatrix, 0, this.mLastMatrix, 0, videoFrameBuffer.transformMatrix.length);
            return true;
        }
    }

    public void setSurfaceTexture(SurfaceTexture surfaceTexture) {
        List<IFrameProcessor> list = this.iFrameProcessors;
        if (list == null) {
            return;
        }
        for (IFrameProcessor iFrameProcessor : list) {
            iFrameProcessor.setSurfaceTexture(surfaceTexture);
        }
    }
}
