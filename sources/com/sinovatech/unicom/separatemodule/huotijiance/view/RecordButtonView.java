package com.sinovatech.unicom.separatemodule.huotijiance.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecordButtonView extends View {
    private Paint bigCirclePaint;
    private int endBigCirSize;
    private int endMinCirSize;
    private Handler handler;
    private int height;
    private boolean isAnimStart;
    private long lastTime;
    private int maxTime;
    private Paint minCirclePaint;
    private int minTime;
    private int nowBigCirSize;
    private int nowMinCirSize;
    private OnTimeOverListener onTimeOverListener;
    private ExecutorService pool;
    private Runnable runnable;
    private int startBigCirSize;
    private int startMinCirSize;
    private final int stepCount;
    private float sweepAngle;
    private int width;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnTimeOverListener {
        void onTimeOver();

        void onTimeShort();
    }

    public RecordButtonView(Context context) {
        super(context);
        this.stepCount = 10;
        this.sweepAngle = 0.0f;
        this.maxTime = 6;
        this.minTime = 1;
        this.isAnimStart = false;
        this.lastTime = 0L;
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException unused) {
                    }
                    if (RecordButtonView.this.isAnimStart) {
                        if (RecordButtonView.this.addStep() && RecordButtonView.this.addTimeAnim()) {
                            RecordButtonView.this.isAnimStart = false;
                        }
                    } else {
                        RecordButtonView.this.sweepAngle = 0.0f;
                        if (RecordButtonView.this.cutStep()) {
                            break;
                        }
                    }
                    RecordButtonView.this.handler.sendEmptyMessage(1);
                }
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                Log.d("dddd", "===============================1:" + (timeInMillis - RecordButtonView.this.lastTime));
                if (timeInMillis - RecordButtonView.this.lastTime > RecordButtonView.this.getMinTime()) {
                    if (RecordButtonView.this.onTimeOverListener != null) {
                        RecordButtonView.this.onTimeOverListener.onTimeOver();
                    }
                } else if (RecordButtonView.this.onTimeOverListener != null) {
                    RecordButtonView.this.onTimeOverListener.onTimeShort();
                }
            }
        };
        this.pool = Executors.newSingleThreadExecutor();
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                RecordButtonView.this.invalidate();
            }
        };
        init(context);
    }

    public RecordButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.stepCount = 10;
        this.sweepAngle = 0.0f;
        this.maxTime = 6;
        this.minTime = 1;
        this.isAnimStart = false;
        this.lastTime = 0L;
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException unused) {
                    }
                    if (RecordButtonView.this.isAnimStart) {
                        if (RecordButtonView.this.addStep() && RecordButtonView.this.addTimeAnim()) {
                            RecordButtonView.this.isAnimStart = false;
                        }
                    } else {
                        RecordButtonView.this.sweepAngle = 0.0f;
                        if (RecordButtonView.this.cutStep()) {
                            break;
                        }
                    }
                    RecordButtonView.this.handler.sendEmptyMessage(1);
                }
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                Log.d("dddd", "===============================1:" + (timeInMillis - RecordButtonView.this.lastTime));
                if (timeInMillis - RecordButtonView.this.lastTime > RecordButtonView.this.getMinTime()) {
                    if (RecordButtonView.this.onTimeOverListener != null) {
                        RecordButtonView.this.onTimeOverListener.onTimeOver();
                    }
                } else if (RecordButtonView.this.onTimeOverListener != null) {
                    RecordButtonView.this.onTimeOverListener.onTimeShort();
                }
            }
        };
        this.pool = Executors.newSingleThreadExecutor();
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                RecordButtonView.this.invalidate();
            }
        };
        init(context);
    }

    public RecordButtonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.stepCount = 10;
        this.sweepAngle = 0.0f;
        this.maxTime = 6;
        this.minTime = 1;
        this.isAnimStart = false;
        this.lastTime = 0L;
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException unused) {
                    }
                    if (RecordButtonView.this.isAnimStart) {
                        if (RecordButtonView.this.addStep() && RecordButtonView.this.addTimeAnim()) {
                            RecordButtonView.this.isAnimStart = false;
                        }
                    } else {
                        RecordButtonView.this.sweepAngle = 0.0f;
                        if (RecordButtonView.this.cutStep()) {
                            break;
                        }
                    }
                    RecordButtonView.this.handler.sendEmptyMessage(1);
                }
                long timeInMillis = Calendar.getInstance().getTimeInMillis();
                Log.d("dddd", "===============================1:" + (timeInMillis - RecordButtonView.this.lastTime));
                if (timeInMillis - RecordButtonView.this.lastTime > RecordButtonView.this.getMinTime()) {
                    if (RecordButtonView.this.onTimeOverListener != null) {
                        RecordButtonView.this.onTimeOverListener.onTimeOver();
                    }
                } else if (RecordButtonView.this.onTimeOverListener != null) {
                    RecordButtonView.this.onTimeOverListener.onTimeShort();
                }
            }
        };
        this.pool = Executors.newSingleThreadExecutor();
        this.handler = new Handler(Looper.getMainLooper()) { // from class: com.sinovatech.unicom.separatemodule.huotijiance.view.RecordButtonView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                RecordButtonView.this.invalidate();
            }
        };
        init(context);
    }

    public int getMaxTime() {
        return this.maxTime * 1000;
    }

    public void setMaxTime(int i) {
        this.maxTime = i;
    }

    public int getMinTime() {
        return this.minTime * 1000;
    }

    public void setMinTime(int i) {
        this.minTime = i;
    }

    private void init(Context context) {
        Resources resources = context.getResources();
        this.startBigCirSize = (int) resources.getDimension(2131165274);
        this.startMinCirSize = (int) resources.getDimension(2131165998);
        this.endMinCirSize = (int) resources.getDimension(2131165927);
        this.endBigCirSize = (int) resources.getDimension(2131165926);
        this.nowMinCirSize = this.startMinCirSize;
        this.nowBigCirSize = this.startBigCirSize;
        this.bigCirclePaint = new Paint();
        this.minCirclePaint = new Paint();
        this.minCirclePaint.setColor(Color.parseColor("#e60028"));
        this.bigCirclePaint.setColor(Color.parseColor("#ffffff"));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.width == 0 || this.height == 0) {
            this.width = getWidth();
            this.height = getHeight();
        }
        if (!this.isAnimStart) {
            this.bigCirclePaint.setStyle(Paint.Style.STROKE);
            this.bigCirclePaint.setStrokeWidth(15.0f);
            this.bigCirclePaint.setColor(-1);
            canvas.drawCircle(this.width / 2, this.height / 2, (this.nowBigCirSize - 15) / 2, this.bigCirclePaint);
            canvas.drawCircle(this.width / 2, this.height / 2, (this.nowMinCirSize - 10) / 2, this.minCirclePaint);
            return;
        }
        this.bigCirclePaint.setStyle(Paint.Style.STROKE);
        this.bigCirclePaint.setStrokeWidth(55.0f);
        this.bigCirclePaint.setColor(Color.parseColor("#55ffffff"));
        canvas.drawCircle(this.width / 2, this.height / 2, (this.nowBigCirSize - 55) / 2, this.bigCirclePaint);
        canvas.drawCircle(this.width / 2, this.height / 2, (this.nowMinCirSize - 15) / 2, this.minCirclePaint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean cutStep() {
        int i;
        int i2;
        int i3 = this.nowMinCirSize;
        int i4 = this.startMinCirSize;
        if (i3 >= i4 || (i = this.nowBigCirSize) <= (i2 = this.startBigCirSize)) {
            return true;
        }
        this.nowMinCirSize = (int) (i3 - ((this.endMinCirSize - i4) / 10));
        this.nowBigCirSize = (int) (i - ((this.endBigCirSize - i2) / 10));
        if (this.nowMinCirSize >= i4 || this.nowBigCirSize <= i2) {
            this.nowMinCirSize = this.startMinCirSize;
            this.nowBigCirSize = this.startBigCirSize;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean addStep() {
        int i;
        int i2;
        int i3 = this.nowMinCirSize;
        int i4 = this.endMinCirSize;
        if (i3 <= i4 || (i = this.nowBigCirSize) >= (i2 = this.endBigCirSize)) {
            return true;
        }
        this.nowMinCirSize = (int) (i3 + ((i4 - this.startMinCirSize) / 10));
        this.nowBigCirSize = (int) (i + ((i2 - this.startBigCirSize) / 10));
        if (this.nowMinCirSize <= i4 || this.nowBigCirSize >= i2) {
            this.nowMinCirSize = this.endMinCirSize;
            this.nowBigCirSize = this.endBigCirSize;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean addTimeAnim() {
        this.sweepAngle += 360.0f / (getMaxTime() / 10);
        if (this.sweepAngle > 359.0f) {
            this.sweepAngle = 0.0f;
            return true;
        }
        return false;
    }

    public void doStartAnim() {
        this.lastTime = Calendar.getInstance().getTimeInMillis();
        doAnim(true);
    }

    public void doStopAnim() {
        doAnim(false);
    }

    private void doAnim(boolean z) {
        this.isAnimStart = z;
        if (z) {
            this.pool.execute(this.runnable);
        }
    }

    public void setOnTimeOverListener(OnTimeOverListener onTimeOverListener) {
        this.onTimeOverListener = onTimeOverListener;
    }
}
