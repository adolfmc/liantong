package com.baidu.rtc;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.baidu.rtc.logreport.SLIReportInterface;
import com.baidu.rtc.logreport.StuckDataCalculator;
import com.webrtc.RendererCommon;
import com.webrtc.SurfaceViewRenderer;
import com.webrtc.VideoFrame;
import com.webrtc.VideoSink;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RTCVideoView extends SurfaceViewRenderer implements IRTCVideoSink {
    private boolean isAttached;
    private boolean isEnableSLIDataReport;
    private boolean isVideoTrackChanged;
    private volatile boolean mExtSinkNeedRender;
    private VideoSink mExtVideoSink;
    private Runnable mOnFirstFrameEvent;
    private String mRoomName;
    private StuckDataCalculator stuckDataCalculator;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ExtVideoSink extends VideoSink {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum ScalingType {
        SCALE_ASPECT_FIT,
        SCALE_ASPECT_FILL,
        SCALE_ASPECT_BALANCED,
        SCALE_ASPECT_FILL_WITHOUT_CROP
    }

    public void onRTCVideoFrame(RTCVideoFrame rTCVideoFrame) {
    }

    public boolean onlyforVideoCallbackdata() {
        return false;
    }

    public RTCVideoView(Context context) {
        super(context);
        this.mExtSinkNeedRender = false;
        this.stuckDataCalculator = new StuckDataCalculator(600);
        this.isEnableSLIDataReport = false;
        this.isAttached = false;
        this.isVideoTrackChanged = false;
        this.mOnFirstFrameEvent = null;
        this.mExtVideoSink = null;
    }

    public RTCVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mExtSinkNeedRender = false;
        this.stuckDataCalculator = new StuckDataCalculator(600);
        this.isEnableSLIDataReport = false;
        this.isAttached = false;
        this.isVideoTrackChanged = false;
        this.mOnFirstFrameEvent = null;
        this.mExtVideoSink = null;
    }

    public void setRoomName(String str) {
        this.mRoomName = str;
    }

    public void changeSurfaceSize(int i, int i2) {
        onFrameResolutionChanged(i, i2, 0);
    }

    @Override // com.webrtc.SurfaceViewRenderer, com.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        if (this.isAttached) {
            updateReportEvents(videoFrame);
            if (this.isEnableSLIDataReport) {
                this.stuckDataCalculator.calculateStuck();
            }
            Runnable runnable = this.mOnFirstFrameEvent;
            if (runnable != null) {
                runnable.run();
                this.mOnFirstFrameEvent = null;
            }
            if (this.mExtVideoSink != null) {
                if (!this.mExtSinkNeedRender) {
                    this.mExtVideoSink.onFrame(videoFrame);
                    return;
                }
                this.mExtVideoSink.onFrame(RTCVideoFrame.newfromVideoFrame(videoFrame));
            }
            if (!onlyforVideoCallbackdata()) {
                super.onFrame(videoFrame);
            } else {
                onRTCVideoFrame(RTCVideoFrame.newfromVideoFrame(videoFrame));
            }
        }
    }

    @Override // com.baidu.rtc.IRTCVideoSink
    public void attach() {
        this.isAttached = true;
    }

    @Override // com.baidu.rtc.IRTCVideoSink
    public void deatach() {
        StuckDataCalculator stuckDataCalculator = this.stuckDataCalculator;
        if (stuckDataCalculator != null) {
            stuckDataCalculator.reset();
        }
        this.isAttached = false;
    }

    @Override // com.baidu.rtc.IRTCVideoSink
    public void setFirstFrameEventListener(Runnable runnable) {
        this.mOnFirstFrameEvent = runnable;
    }

    @Override // com.baidu.rtc.IRTCVideoSink
    public void setStuckEventListener(SLIReportInterface sLIReportInterface) {
        this.stuckDataCalculator.setStuckEventListener(sLIReportInterface);
    }

    @Override // com.baidu.rtc.IRTCVideoSink
    public void setEnableSLIDataReport(boolean z) {
        this.isEnableSLIDataReport = z;
    }

    public void setExtVideoSink(ExtVideoSink extVideoSink) {
        this.mExtVideoSink = extVideoSink;
    }

    public void setExtVideoSink(VideoSink videoSink, boolean z) {
        this.mExtVideoSink = videoSink;
        this.mExtSinkNeedRender = z;
    }

    @Override // com.webrtc.SurfaceViewRenderer
    public void setEnableHardwareScaler(boolean z) {
        if (this.mExtVideoSink != null) {
            return;
        }
        super.setEnableHardwareScaler(z);
    }

    @Override // com.webrtc.SurfaceViewRenderer
    public void clearImage() {
        StuckDataCalculator stuckDataCalculator = this.stuckDataCalculator;
        if (stuckDataCalculator != null) {
            stuckDataCalculator.reset();
        }
        if (this.mExtVideoSink != null) {
            return;
        }
        super.clearImage();
    }

    @Override // com.webrtc.SurfaceViewRenderer
    public void setMirror(boolean z) {
        if (this.mExtVideoSink != null) {
            return;
        }
        super.setMirror(z);
    }

    public void setVideoTrackChanged(boolean z) {
        this.isVideoTrackChanged = z;
    }

    private void updateReportEvents(VideoFrame videoFrame) {
        if (this.isVideoTrackChanged) {
            this.isVideoTrackChanged = false;
            onFirstFrameRendered();
        }
    }

    public void setScalingType(ScalingType scalingType) {
        super.setScalingType(RendererCommon.ScalingType.values()[scalingType.ordinal()]);
    }

    public void setScalingType(ScalingType scalingType, ScalingType scalingType2) {
        super.setScalingType(RendererCommon.ScalingType.values()[scalingType.ordinal()], RendererCommon.ScalingType.values()[scalingType2.ordinal()]);
    }

    public boolean isFullVisible() {
        Rect rect = new Rect();
        return getGlobalVisibleRect(rect) && rect.width() >= getMeasuredWidth() && rect.height() >= getMeasuredHeight();
    }
}
