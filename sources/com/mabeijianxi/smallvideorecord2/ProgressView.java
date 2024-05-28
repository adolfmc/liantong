package com.mabeijianxi.smallvideorecord2;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import com.mabeijianxi.smallvideorecord2.model.MediaObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ProgressView extends View {
    private static final int HANDLER_INVALIDATE_ACTIVE = 0;
    private static final int HANDLER_INVALIDATE_RECORDING = 1;
    private Paint mActivePaint;
    private boolean mActiveState;
    private Handler mHandler;
    private int mMaxDuration;
    private MediaObject mMediaObject;
    private Paint mOverflowPaint;
    private Paint mPausePaint;
    private boolean mProgressChanged;
    private Paint mProgressPaint;
    private int mRecordTimeMin;
    private Paint mRemovePaint;
    private boolean mStop;
    private Paint mThreePaint;
    private int mVLineWidth;

    public ProgressView(Context context) {
        super(context);
        this.mRecordTimeMin = 1500;
        this.mHandler = new Handler() { // from class: com.mabeijianxi.smallvideorecord2.ProgressView.1
            @Override // android.os.Handler
            public void dispatchMessage(Message message) {
                switch (message.what) {
                    case 0:
                        ProgressView.this.invalidate();
                        ProgressView progressView = ProgressView.this;
                        progressView.mActiveState = !progressView.mActiveState;
                        if (!ProgressView.this.mStop) {
                            sendEmptyMessageDelayed(0, 300L);
                            break;
                        }
                        break;
                    case 1:
                        ProgressView.this.invalidate();
                        if (ProgressView.this.mProgressChanged) {
                            sendEmptyMessageDelayed(0, 50L);
                            break;
                        }
                        break;
                }
                super.dispatchMessage(message);
            }
        };
        init();
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRecordTimeMin = 1500;
        this.mHandler = new Handler() { // from class: com.mabeijianxi.smallvideorecord2.ProgressView.1
            @Override // android.os.Handler
            public void dispatchMessage(Message message) {
                switch (message.what) {
                    case 0:
                        ProgressView.this.invalidate();
                        ProgressView progressView = ProgressView.this;
                        progressView.mActiveState = !progressView.mActiveState;
                        if (!ProgressView.this.mStop) {
                            sendEmptyMessageDelayed(0, 300L);
                            break;
                        }
                        break;
                    case 1:
                        ProgressView.this.invalidate();
                        if (ProgressView.this.mProgressChanged) {
                            sendEmptyMessageDelayed(0, 50L);
                            break;
                        }
                        break;
                }
                super.dispatchMessage(message);
            }
        };
        init();
    }

    public ProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRecordTimeMin = 1500;
        this.mHandler = new Handler() { // from class: com.mabeijianxi.smallvideorecord2.ProgressView.1
            @Override // android.os.Handler
            public void dispatchMessage(Message message) {
                switch (message.what) {
                    case 0:
                        ProgressView.this.invalidate();
                        ProgressView progressView = ProgressView.this;
                        progressView.mActiveState = !progressView.mActiveState;
                        if (!ProgressView.this.mStop) {
                            sendEmptyMessageDelayed(0, 300L);
                            break;
                        }
                        break;
                    case 1:
                        ProgressView.this.invalidate();
                        if (ProgressView.this.mProgressChanged) {
                            sendEmptyMessageDelayed(0, 50L);
                            break;
                        }
                        break;
                }
                super.dispatchMessage(message);
            }
        };
        init();
    }

    private void init() {
        this.mProgressPaint = new Paint();
        this.mActivePaint = new Paint();
        this.mPausePaint = new Paint();
        this.mRemovePaint = new Paint();
        this.mThreePaint = new Paint();
        this.mOverflowPaint = new Paint();
        this.mVLineWidth = DeviceUtils.dipToPX(getContext(), 1.0f);
        setBackgroundColor(getResources().getColor(C5263R.C5264color.camera_bg));
        this.mProgressPaint.setColor(-12206054);
        this.mProgressPaint.setStyle(Paint.Style.FILL);
        this.mActivePaint.setColor(getResources().getColor(17170443));
        this.mActivePaint.setStyle(Paint.Style.FILL);
        this.mPausePaint.setColor(getResources().getColor(C5263R.C5264color.camera_progress_split));
        this.mPausePaint.setStyle(Paint.Style.FILL);
        this.mRemovePaint.setColor(getResources().getColor(C5263R.C5264color.camera_progress_delete));
        this.mRemovePaint.setStyle(Paint.Style.FILL);
        this.mThreePaint.setColor(getResources().getColor(C5263R.C5264color.camera_progress_three));
        this.mThreePaint.setStyle(Paint.Style.FILL);
        this.mOverflowPaint.setColor(getResources().getColor(C5263R.C5264color.camera_progress_overflow));
        this.mOverflowPaint.setStyle(Paint.Style.FILL);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d2 A[SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onDraw(android.graphics.Canvas r27) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mabeijianxi.smallvideorecord2.ProgressView.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mStop = false;
        this.mHandler.sendEmptyMessage(0);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mStop = true;
        this.mHandler.removeMessages(0);
    }

    public void setData(MediaObject mediaObject) {
        this.mMediaObject = mediaObject;
    }

    public void setMaxDuration(int i) {
        this.mMaxDuration = i;
    }

    public void start() {
        this.mProgressChanged = true;
    }

    public void stop() {
        this.mProgressChanged = false;
    }

    public void setMinTime(int i) {
        this.mRecordTimeMin = i;
    }
}
