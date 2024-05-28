package com.sinovatech.unicom.separatemodule.dianziqianming.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.sinovatech.unicom.separatemodule.dianziqianming.SignatureActivity;
import com.sinovatech.unicom.separatemodule.dianziqianming.entity.SignatureEntity;
import com.sinovatech.unicom.separatemodule.dianziqianming.utils.SignatureUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PathView extends View {
    private static final float HALF_STROKE_WIDTH = 7.5f;
    private static final float STROKE_WIDTH = 15.0f;
    private final RectF dirtyRect;
    private boolean isStartDown;
    private float lastTouchX;
    private float lastTouchY;
    private boolean mIsEmpty;
    private OnSignedListener mOnSignedListener;
    private Paint paint;
    private Path path;
    private List<String> xList;
    private List<String> yList;
    private List<String> zList;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnSignedListener {
        void onClear();

        void onSigned();

        void onStartSigning();
    }

    public PathView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
        this.path = new Path();
        this.dirtyRect = new RectF();
        this.isStartDown = false;
        this.paint.setAntiAlias(true);
        this.paint.setColor(-16777216);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeWidth(STROKE_WIDTH);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void clear() {
        this.path.reset();
        setIsEmpty(true);
        invalidate();
    }

    public void setOnSignedListener(OnSignedListener onSignedListener) {
        this.mOnSignedListener = onSignedListener;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(this.path, this.paint);
    }

    public void clearData() {
        try {
            if (this.xList != null) {
                this.xList.clear();
            }
            if (this.yList != null) {
                this.yList.clear();
            }
            if (this.zList != null) {
                this.zList.clear();
            }
            this.isStartDown = false;
            SignatureUtils.signatureEntities.clear();
        } catch (Exception unused) {
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                Display(motionEvent);
                OnSignedListener onSignedListener = this.mOnSignedListener;
                if (onSignedListener != null) {
                    onSignedListener.onStartSigning();
                }
                this.path.moveTo(x, y);
                this.lastTouchX = x;
                this.lastTouchY = y;
                return true;
            case 1:
            case 2:
                Display(motionEvent);
                setIsEmpty(false);
                resetDirtyRect(x, y);
                int historySize = motionEvent.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    float historicalX = motionEvent.getHistoricalX(i);
                    float historicalY = motionEvent.getHistoricalY(i);
                    expandDirtyRect(historicalX, historicalY);
                    this.path.lineTo(historicalX, historicalY);
                }
                this.path.lineTo(x, y);
                invalidate((int) (this.dirtyRect.left - HALF_STROKE_WIDTH), (int) (this.dirtyRect.top - HALF_STROKE_WIDTH), (int) (this.dirtyRect.right + HALF_STROKE_WIDTH), (int) (this.dirtyRect.bottom + HALF_STROKE_WIDTH));
                this.lastTouchX = x;
                this.lastTouchY = y;
                return true;
            default:
                return false;
        }
    }

    public void Display(MotionEvent motionEvent) {
        try {
            if (SignatureActivity.isNeedYasuo) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (SignatureUtils.isYaSuo) {
                    double d = SignatureUtils.signaturePadsWidth / SignatureUtils.oldSignaturePadsWidth;
                    double d2 = SignatureUtils.signaturePadsHeight / SignatureUtils.oldSignaturePadsHeight;
                    if (SignatureUtils.yaSuoXY == 2) {
                        x = (int) (x * d2);
                        y = (int) (y * d2);
                    } else if (SignatureUtils.yaSuoXY == 1) {
                        x = (int) (x * d);
                        y = (int) (y * d);
                    } else if (SignatureUtils.yaSuoXY == 3) {
                        x = (int) (x * d * d2);
                        y = (int) (y * d * d2);
                    }
                }
                if (motionEvent.getAction() == 1) {
                    this.isStartDown = false;
                    SignatureEntity signatureEntity = new SignatureEntity();
                    signatureEntity.setxList(this.xList);
                    signatureEntity.setyList(this.yList);
                    signatureEntity.setzList(this.zList);
                    SignatureUtils.signatureEntities.add(signatureEntity);
                } else if (motionEvent.getAction() == 2) {
                    if (this.isStartDown) {
                        this.xList.add(String.valueOf(x));
                        this.yList.add(String.valueOf(y));
                        this.zList.add(String.valueOf(0));
                    }
                } else if (motionEvent.getAction() == 0) {
                    this.xList = new ArrayList();
                    this.yList = new ArrayList();
                    this.zList = new ArrayList();
                    this.isStartDown = true;
                    this.xList.add(String.valueOf(x));
                    this.yList.add(String.valueOf(y));
                    this.zList.add(String.valueOf(0));
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    private void expandDirtyRect(float f, float f2) {
        if (f < this.dirtyRect.left) {
            this.dirtyRect.left = f;
        } else if (f > this.dirtyRect.right) {
            this.dirtyRect.right = f;
        }
        if (f2 < this.dirtyRect.top) {
            this.dirtyRect.top = f2;
        } else if (f2 > this.dirtyRect.bottom) {
            this.dirtyRect.bottom = f2;
        }
    }

    private void resetDirtyRect(float f, float f2) {
        this.dirtyRect.left = Math.min(this.lastTouchX, f);
        this.dirtyRect.right = Math.max(this.lastTouchX, f);
        this.dirtyRect.top = Math.min(this.lastTouchY, f2);
        this.dirtyRect.bottom = Math.max(this.lastTouchY, f2);
    }

    private void setIsEmpty(boolean z) {
        this.mIsEmpty = z;
        OnSignedListener onSignedListener = this.mOnSignedListener;
        if (onSignedListener != null) {
            if (this.mIsEmpty) {
                onSignedListener.onClear();
            } else {
                onSignedListener.onSigned();
            }
        }
    }
}
