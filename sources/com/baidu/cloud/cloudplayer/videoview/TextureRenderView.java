package com.baidu.cloud.cloudplayer.videoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.baidu.cloud.cloudplayer.videoview.IRenderView;
import com.baidu.cloud.media.player.IMediaPlayer;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@TargetApi(14)
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TextureRenderView extends TextureView implements IRenderView {
    private static final String TAG = "TextureRenderView";
    private SurfaceTexture lastSurfaceTexture;
    private MeasureHelper mMeasureHelper;
    private int mMediaPlayerCode;
    private SurfaceCallback mSurfaceCallback;

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public View getView() {
        return this;
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public boolean shouldWaitForResize() {
        return false;
    }

    public TextureRenderView(Context context) {
        super(context);
        this.mMediaPlayerCode = 0;
        initView(context);
    }

    public TextureRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMediaPlayerCode = 0;
        initView(context);
    }

    public TextureRenderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMediaPlayerCode = 0;
        initView(context);
    }

    @TargetApi(21)
    public TextureRenderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mMediaPlayerCode = 0;
        initView(context);
    }

    private void initView(Context context) {
        this.mMeasureHelper = new MeasureHelper(this);
        this.mSurfaceCallback = new SurfaceCallback(this);
        setSurfaceTextureListener(this.mSurfaceCallback);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("TextureRenderView", "onDetachedFromWindow");
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void setVideoSize(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        this.mMeasureHelper.setVideoSize(i, i2);
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
        this.mMeasureHelper.setVideoRotation(i);
        setRotation(i);
    }

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    public void setAspectRatio(int i) {
        this.mMeasureHelper.setAspectRatio(i);
        requestLayout();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.mMeasureHelper.doMeasure(i, i2);
        setMeasuredDimension(this.mMeasureHelper.getMeasuredWidth(), this.mMeasureHelper.getMeasuredHeight());
    }

    public void setCurrentMediaPlayerCode(int i) {
        this.mMediaPlayerCode = i;
    }

    public int getCurrentMediaPlayerCode() {
        return this.mMediaPlayerCode;
    }

    public SurfaceTexture getLastSurfaceTexture() {
        return this.lastSurfaceTexture;
    }

    public void setLastSurfaceTexture(SurfaceTexture surfaceTexture) {
        this.lastSurfaceTexture = surfaceTexture;
    }

    public IRenderView.ISurfaceHolder getSurfaceHolder() {
        return new InternalSurfaceHolder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class InternalSurfaceHolder implements IRenderView.ISurfaceHolder {
        private TextureRenderView mTextureView;

        public InternalSurfaceHolder(TextureRenderView textureRenderView) {
            this.mTextureView = textureRenderView;
        }

        @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.ISurfaceHolder
        @TargetApi(16)
        public void bindToMediaPlayer(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null) {
                try {
                    if (this.mTextureView.getSurfaceTexture() == null) {
                        return;
                    }
                    if (iMediaPlayer.hashCode() != this.mTextureView.getCurrentMediaPlayerCode()) {
                        iMediaPlayer.setSurface(openSurface());
                    } else if (!this.mTextureView.getLastSurfaceTexture().equals(this.mTextureView.getSurfaceTexture())) {
                        this.mTextureView.setSurfaceTexture(this.mTextureView.getLastSurfaceTexture());
                    }
                    this.mTextureView.setCurrentMediaPlayerCode(iMediaPlayer.hashCode());
                } catch (Exception e) {
                    MsLogUtil.m7977e("TextureRenderView", "播放器异常:" + e.getMessage());
                }
            }
        }

        @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.ISurfaceHolder
        public IRenderView getRenderView() {
            return this.mTextureView;
        }

        @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView.ISurfaceHolder
        public Surface openSurface() {
            return new Surface(this.mTextureView.getSurfaceTexture());
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

    @Override // com.baidu.cloud.cloudplayer.videoview.IRenderView
    @TargetApi(16)
    public void release() {
        if (this.lastSurfaceTexture != null) {
            if (isAvailable()) {
                this.mSurfaceCallback.setOwnSurfaceTexture(true);
                return;
            }
            this.lastSurfaceTexture.release();
            this.lastSurfaceTexture = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class SurfaceCallback implements TextureView.SurfaceTextureListener {
        private int mHeight;
        private boolean mIsFormatChanged;
        private volatile boolean mOwnSurfaceTexture = false;
        private Map<IRenderView.IRenderCallback, Object> mRenderCallbackMap = new ConcurrentHashMap();
        private SurfaceTexture mSurfaceTexture;
        private WeakReference<TextureRenderView> mWeakRenderView;
        private int mWidth;

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        public SurfaceCallback(TextureRenderView textureRenderView) {
            this.mWeakRenderView = new WeakReference<>(textureRenderView);
        }

        public void setOwnSurfaceTexture(boolean z) {
            this.mOwnSurfaceTexture = z;
        }

        public void addRenderCallback(IRenderView.IRenderCallback iRenderCallback) {
            InternalSurfaceHolder internalSurfaceHolder;
            this.mRenderCallbackMap.put(iRenderCallback, iRenderCallback);
            if (this.mSurfaceTexture != null) {
                internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get());
                iRenderCallback.onSurfaceCreated(internalSurfaceHolder, this.mWidth, this.mHeight);
            } else {
                internalSurfaceHolder = null;
            }
            if (this.mIsFormatChanged) {
                if (internalSurfaceHolder == null) {
                    internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get());
                }
                iRenderCallback.onSurfaceChanged(internalSurfaceHolder, 0, this.mWidth, this.mHeight);
            }
        }

        public void removeRenderCallback(IRenderView.IRenderCallback iRenderCallback) {
            this.mRenderCallbackMap.remove(iRenderCallback);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        @TargetApi(16)
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            this.mSurfaceTexture = surfaceTexture;
            if (this.mWeakRenderView.get() == null) {
                Log.e("TextureRenderView", "!!!!!Too bad, textureview in callback is released. function will not work normally");
            } else if (this.mWeakRenderView.get().getLastSurfaceTexture() == null) {
                this.mWeakRenderView.get().setLastSurfaceTexture(surfaceTexture);
            }
            this.mIsFormatChanged = false;
            this.mWidth = 0;
            this.mHeight = 0;
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get());
            for (IRenderView.IRenderCallback iRenderCallback : this.mRenderCallbackMap.keySet()) {
                iRenderCallback.onSurfaceCreated(internalSurfaceHolder, 0, 0);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            this.mSurfaceTexture = surfaceTexture;
            this.mIsFormatChanged = true;
            this.mWidth = i;
            this.mHeight = i2;
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get());
            for (IRenderView.IRenderCallback iRenderCallback : this.mRenderCallbackMap.keySet()) {
                iRenderCallback.onSurfaceChanged(internalSurfaceHolder, 0, i, i2);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            this.mSurfaceTexture = surfaceTexture;
            this.mIsFormatChanged = false;
            this.mWidth = 0;
            this.mHeight = 0;
            InternalSurfaceHolder internalSurfaceHolder = new InternalSurfaceHolder(this.mWeakRenderView.get());
            for (IRenderView.IRenderCallback iRenderCallback : this.mRenderCallbackMap.keySet()) {
                iRenderCallback.onSurfaceDestroyed(internalSurfaceHolder);
            }
            return this.mOwnSurfaceTexture;
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(TextureRenderView.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(TextureRenderView.class.getName());
    }
}
