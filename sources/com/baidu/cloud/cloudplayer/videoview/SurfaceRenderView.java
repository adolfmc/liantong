package com.baidu.cloud.cloudplayer.videoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.baidu.cloud.cloudplayer.videoview.IRenderView;
import com.baidu.cloud.media.player.IMediaPlayer;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SurfaceRenderView extends SurfaceView implements IRenderView {
    private MeasureHelper mMeasureHelper;
    private SurfaceCallback mSurfaceCallback;

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public Bitmap getBitmap() {
        return null;
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public View getView() {
        return this;
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void release() {
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public boolean shouldWaitForResize() {
        return true;
    }

    public SurfaceRenderView(Context context) {
        super(context);
        initView(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    @TargetApi(21)
    public SurfaceRenderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initView(context);
    }

    private void initView(Context context) {
        this.mMeasureHelper = new MeasureHelper(this);
        this.mSurfaceCallback = new SurfaceCallback(this);
        getHolder().addCallback(this.mSurfaceCallback);
        getHolder().setType(0);
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void setVideoSize(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        this.mMeasureHelper.setVideoSize(i, i2);
        getHolder().setFixedSize(i, i2);
        requestLayout();
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void setVideoSampleAspectRatio(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        this.mMeasureHelper.setVideoSampleAspectRatio(i, i2);
        requestLayout();
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void setVideoRotation(int i) {
        Log.e("", "SurfaceView doesn't support rotation (" + i + ")!\n");
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void setAspectRatio(int i) {
        this.mMeasureHelper.setAspectRatio(i);
        requestLayout();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        this.mMeasureHelper.doMeasure(i, i2);
        setMeasuredDimension(this.mMeasureHelper.getMeasuredWidth(), this.mMeasureHelper.getMeasuredHeight());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class InternalSurfaceHolder implements IRenderView.ISurfaceHolder {
        private SurfaceHolder mSurfaceHolder;
        private SurfaceRenderView mSurfaceView;

        public InternalSurfaceHolder(SurfaceRenderView surfaceRenderView, SurfaceHolder surfaceHolder) {
            this.mSurfaceView = surfaceRenderView;
            this.mSurfaceHolder = surfaceHolder;
        }

        @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.ISurfaceHolder
        public void bindToMediaPlayer(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null) {
                iMediaPlayer.setDisplay(this.mSurfaceHolder);
            }
        }

        @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.ISurfaceHolder
        public IRenderView getRenderView() {
            return this.mSurfaceView;
        }

        @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.ISurfaceHolder
        public Surface openSurface() {
            SurfaceHolder surfaceHolder = this.mSurfaceHolder;
            if (surfaceHolder == null) {
                return null;
            }
            return surfaceHolder.getSurface();
        }
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void addRenderCallback(IRenderView.IRenderCallback iRenderCallback) {
        this.mSurfaceCallback.addRenderCallback(iRenderCallback);
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void removeRenderCallback(IRenderView.IRenderCallback iRenderCallback) {
        this.mSurfaceCallback.removeRenderCallback(iRenderCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class SurfaceCallback implements SurfaceHolder.Callback {
        private int mFormat;
        private int mHeight;
        private boolean mIsFormatChanged;
        private Map<IRenderView.IRenderCallback, Object> mRenderCallbackMap = new ConcurrentHashMap();
        private SurfaceHolder mSurfaceHolder;
        private WeakReference<SurfaceRenderView> mWeakSurfaceView;
        private int mWidth;

        public SurfaceCallback(SurfaceRenderView surfaceRenderView) {
            this.mWeakSurfaceView = new WeakReference<>(surfaceRenderView);
        }

        public void addRenderCallback(IRenderView.IRenderCallback iRenderCallback) {
            InternalSurfaceHolder internalSurfaceHolder;
            this.mRenderCallbackMap.put(iRenderCallback, iRenderCallback);
            if (this.mSurfaceHolder != null) {
                internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakSurfaceView.get(), this.mSurfaceHolder);
                iRenderCallback.onSurfaceCreated(internalSurfaceHolder, this.mWidth, this.mHeight);
            } else {
                internalSurfaceHolder = null;
            }
            if (this.mIsFormatChanged) {
                if (internalSurfaceHolder == null) {
                    internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakSurfaceView.get(), this.mSurfaceHolder);
                }
                iRenderCallback.onSurfaceChanged(internalSurfaceHolder, this.mFormat, this.mWidth, this.mHeight);
            }
        }

        public void removeRenderCallback(IRenderView.IRenderCallback iRenderCallback) {
            this.mRenderCallbackMap.remove(iRenderCallback);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.mSurfaceHolder = surfaceHolder;
            this.mIsFormatChanged = false;
            this.mFormat = 0;
            this.mWidth = 0;
            this.mHeight = 0;
            Log.d("SurfaceRenderView", "surfaceCreated: " + surfaceHolder.getSurface());
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakSurfaceView.get(), this.mSurfaceHolder);
            for (IRenderView.IRenderCallback iRenderCallback : this.mRenderCallbackMap.keySet()) {
                iRenderCallback.onSurfaceCreated(internalSurfaceHolder, 0, 0);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.mSurfaceHolder = null;
            this.mIsFormatChanged = false;
            this.mFormat = 0;
            this.mWidth = 0;
            this.mHeight = 0;
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakSurfaceView.get(), this.mSurfaceHolder);
            for (IRenderView.IRenderCallback iRenderCallback : this.mRenderCallbackMap.keySet()) {
                iRenderCallback.onSurfaceDestroyed(internalSurfaceHolder);
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            this.mSurfaceHolder = surfaceHolder;
            this.mIsFormatChanged = true;
            this.mFormat = i;
            this.mWidth = i2;
            this.mHeight = i3;
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakSurfaceView.get(), this.mSurfaceHolder);
            for (IRenderView.IRenderCallback iRenderCallback : this.mRenderCallbackMap.keySet()) {
                iRenderCallback.onSurfaceChanged(internalSurfaceHolder, i, i2, i3);
            }
        }
    }

    @Override // android.view.View
    @TargetApi(14)
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(SurfaceRenderView.class.getName());
    }

    @Override // android.view.View
    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (Build.VERSION.SDK_INT >= 14) {
            accessibilityNodeInfo.setClassName(SurfaceRenderView.class.getName());
        }
    }
}
