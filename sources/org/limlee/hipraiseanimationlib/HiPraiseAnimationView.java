package org.limlee.hipraiseanimationlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import org.limlee.hipraiseanimationlib.base.IDrawTask;
import org.limlee.hipraiseanimationlib.base.IDrawable;
import org.limlee.hipraiseanimationlib.base.IPraise;
import org.limlee.hipraiseanimationlib.base.IPraiseView;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class HiPraiseAnimationView extends SurfaceView implements SurfaceHolder.Callback, IPraiseView {
    private static final int MAX_UPDATE_RATE = 25;
    private static final String TAG = "HiPraiseAnimationView";
    private IDrawTask mDrawTask;
    private boolean mIsAttached;
    private volatile boolean mIsSurfaceCreated;
    private boolean mIsUpdateThreadStarted;
    private int mSurfaceHeight;
    private int mSurfaceWidth;
    private int mUpdateRate;
    private UpdateThread mUpdateThread;

    public HiPraiseAnimationView(Context context) {
        this(context, null);
    }

    public HiPraiseAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUpdateRate = 25;
        if (!isInEditMode()) {
            setZOrderMediaOverlay(true);
            setZOrderOnTop(true);
        }
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        getHolder().setFormat(-2);
        getHolder().addCallback(this);
        this.mDrawTask = new SimpleDrawTask(new Handler(Looper.getMainLooper()));
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttached = true;
        this.mDrawTask.start();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttached = false;
        this.mDrawTask.stop();
        stop();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mIsSurfaceCreated = true;
        clearSurface();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mSurfaceWidth = i2;
        this.mSurfaceHeight = i3;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mIsSurfaceCreated = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long drawSurface() {
        if (!this.mIsSurfaceCreated || this.mSurfaceWidth == 0 || this.mSurfaceHeight == 0) {
            return 0L;
        }
        if (!isShown()) {
            this.mDrawTask.clearDrawable();
            clearSurface();
            return 0L;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        Canvas lockCanvas = getHolder().lockCanvas();
        if (lockCanvas != null) {
            this.mDrawTask.draw(lockCanvas);
            if (this.mIsSurfaceCreated) {
                getHolder().unlockCanvasAndPost(lockCanvas);
            }
        }
        return SystemClock.uptimeMillis() - uptimeMillis;
    }

    private void clearSurface() {
        Canvas lockCanvas;
        if (!this.mIsSurfaceCreated || (lockCanvas = getHolder().lockCanvas()) == null) {
            return;
        }
        lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        if (this.mIsSurfaceCreated) {
            getHolder().unlockCanvasAndPost(lockCanvas);
        }
    }

    public synchronized void start() {
        if (this.mIsUpdateThreadStarted) {
            return;
        }
        if (this.mUpdateThread == null) {
            this.mUpdateThread = new UpdateThread("Update Thread") { // from class: org.limlee.hipraiseanimationlib.HiPraiseAnimationView.1
                @Override // org.limlee.hipraiseanimationlib.UpdateThread, java.lang.Thread, java.lang.Runnable
                public void run() {
                    while (!isQuited() && !Thread.currentThread().isInterrupted()) {
                        try {
                            try {
                                long drawSurface = HiPraiseAnimationView.this.mUpdateRate - HiPraiseAnimationView.this.drawSurface();
                                if (isQuited()) {
                                    break;
                                } else if (drawSurface > 0) {
                                    SystemClock.sleep(drawSurface);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } finally {
                            HiPraiseAnimationView.this.stop();
                        }
                    }
                }
            };
        }
        this.mIsUpdateThreadStarted = true;
        this.mUpdateThread.start();
    }

    public synchronized void stop() {
        this.mIsUpdateThreadStarted = false;
        this.mDrawTask.clearDrawable();
        if (this.mUpdateThread != null) {
            UpdateThread updateThread = this.mUpdateThread;
            this.mUpdateThread = null;
            updateThread.quit();
            updateThread.interrupt();
        }
    }

    @Override // org.limlee.hipraiseanimationlib.base.IPraiseView
    public void addPraise(IPraise iPraise) {
        IDrawable drawable;
        if (this.mIsAttached && this.mIsUpdateThreadStarted && (drawable = iPraise.toDrawable()) != null) {
            this.mDrawTask.addDrawable(drawable);
        }
    }
}
