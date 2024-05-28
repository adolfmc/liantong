package org.limlee.hipraiseanimationlib;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.limlee.hipraiseanimationlib.base.IDrawTask;
import org.limlee.hipraiseanimationlib.base.IDrawable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SimpleDrawTask implements IDrawTask {
    private static final int MAX_DRAWABLES = 128;
    private static final String TAG = "SimpleDrawTask";
    private Handler mCallbackHandler;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mIsStarted;
    private static RectF RECT = new RectF();
    private static Paint PAINT = new Paint();
    private int mDrawables = 128;
    private BlockingQueue<IDrawable> mDrawableQueue = new ArrayBlockingQueue(this.mDrawables);

    static {
        PAINT.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        PAINT.setColor(0);
    }

    public SimpleDrawTask(Handler handler) {
        this.mCallbackHandler = handler;
    }

    @Override // org.limlee.hipraiseanimationlib.base.IDrawTask
    public void start() {
        if (this.mIsStarted) {
            return;
        }
        if (this.mHandlerThread == null) {
            this.mHandlerThread = new HandlerThread("DrawTask HandlerThread");
            this.mHandlerThread.start();
        }
        if (this.mHandler == null) {
            this.mHandler = new Handler(this.mHandlerThread.getLooper());
        }
        this.mIsStarted = true;
    }

    @Override // org.limlee.hipraiseanimationlib.base.IDrawTask
    public void stop() {
        this.mIsStarted = false;
        Handler handler = this.mCallbackHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mHandler.removeCallbacksAndMessages(null);
        HandlerThread handlerThread = this.mHandlerThread;
        this.mHandlerThread = null;
        handlerThread.quit();
        try {
            handlerThread.join(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handlerThread.interrupt();
    }

    @Override // org.limlee.hipraiseanimationlib.base.IDrawTask
    public void draw(Canvas canvas) {
        clearCanvas(canvas);
        consumeDrawableQueue(canvas);
    }

    private void clearCanvas(Canvas canvas) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        RECT.set(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        canvas.drawRect(RECT, PAINT);
    }

    @Override // org.limlee.hipraiseanimationlib.base.IDrawTask
    public void addDrawable(final IDrawable iDrawable) {
        Handler handler;
        if (!this.mIsStarted || (handler = this.mHandler) == null) {
            return;
        }
        handler.post(new Runnable() { // from class: org.limlee.hipraiseanimationlib.SimpleDrawTask.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SimpleDrawTask.this.mDrawableQueue.offer(iDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // org.limlee.hipraiseanimationlib.base.IDrawTask
    public void clearDrawable() {
        this.mDrawableQueue.clear();
    }

    private void consumeDrawableQueue(Canvas canvas) {
        Iterator it = this.mDrawableQueue.iterator();
        while (it.hasNext()) {
            final IDrawable iDrawable = (IDrawable) it.next();
            if (iDrawable != null) {
                iDrawable.draw(canvas, SystemClock.uptimeMillis());
                if (iDrawable.isFinished()) {
                    Handler handler = this.mCallbackHandler;
                    if (handler != null && (iDrawable instanceof OnDrawCallback)) {
                        handler.post(new Runnable() { // from class: org.limlee.hipraiseanimationlib.SimpleDrawTask.2
                            @Override // java.lang.Runnable
                            public void run() {
                                ((OnDrawCallback) iDrawable).onFinish();
                            }
                        });
                    }
                    it.remove();
                }
            }
        }
    }
}
