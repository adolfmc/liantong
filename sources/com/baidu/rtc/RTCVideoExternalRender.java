package com.baidu.rtc;

import android.view.Surface;
import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.logreport.SLIReportInterface;
import com.baidu.rtc.logreport.StuckDataCalculator;
import com.webrtc.VideoFrame;
import com.webrtc.VideoSink;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class RTCVideoExternalRender implements IRTCVideoSink, VideoSink {
    private static final String TAG = "RTCVideoExternalRender";
    protected long mUserId;
    private BaiduRtcRoom.BaiduRtcRoomVideoObserver mVideoObserver;
    public boolean mIsRenderInited = false;
    private StuckDataCalculator stuckDataCalculator = new StuckDataCalculator(600);
    private boolean isEnableSLIDataReport = false;
    private boolean isAttached = false;
    private Runnable mOnFirstFrameEvent = null;

    public abstract void changeSurfaceSize(int i, int i2);

    public abstract void clearImage();

    public abstract Surface getSurface();

    public abstract boolean hasSurface();

    public abstract void init();

    public abstract void release();

    public abstract void releaseSurface();

    public abstract void setSurface(Surface surface);

    public RTCVideoExternalRender(BaiduRtcRoom.BaiduRtcRoomVideoObserver baiduRtcRoomVideoObserver, long j) {
        this.mUserId = 0L;
        this.mVideoObserver = null;
        this.mUserId = j;
        this.mVideoObserver = baiduRtcRoomVideoObserver;
    }

    @Override // com.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        if (this.isAttached) {
            if (this.isEnableSLIDataReport) {
                this.stuckDataCalculator.calculateStuck();
            }
            Runnable runnable = this.mOnFirstFrameEvent;
            if (runnable != null) {
                runnable.run();
                this.mOnFirstFrameEvent = null;
            }
            BaiduRtcRoom.BaiduRtcRoomVideoObserver baiduRtcRoomVideoObserver = this.mVideoObserver;
            if (baiduRtcRoomVideoObserver != null) {
                baiduRtcRoomVideoObserver.onVideoFrame(RTCVideoFrame.newfromVideoFrame(videoFrame), this.mUserId);
            }
        }
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

    @Override // com.baidu.rtc.IRTCVideoSink
    public void attach() {
        this.isAttached = true;
    }

    @Override // com.baidu.rtc.IRTCVideoSink
    public void deatach() {
        this.stuckDataCalculator.reset();
        this.isAttached = false;
    }

    public boolean isRenderInited() {
        return this.mIsRenderInited;
    }

    public void setRenderInited(boolean z) {
        this.mIsRenderInited = z;
    }
}
