package cn.finalteam.galleryfinal.widget.zoonview;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

@TargetApi(8)
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class FroyoGestureDetector extends EclairGestureDetector {
    protected final ScaleGestureDetector mDetector;

    public FroyoGestureDetector(Context context) {
        super(context);
        this.mDetector = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() { // from class: cn.finalteam.galleryfinal.widget.zoonview.FroyoGestureDetector.1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                    return false;
                }
                FroyoGestureDetector.this.mListener.onScale(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                return true;
            }
        });
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.CupcakeGestureDetector, cn.finalteam.galleryfinal.widget.zoonview.GestureDetector
    public boolean isScaling() {
        return this.mDetector.isInProgress();
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.EclairGestureDetector, cn.finalteam.galleryfinal.widget.zoonview.CupcakeGestureDetector, cn.finalteam.galleryfinal.widget.zoonview.GestureDetector
    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mDetector.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
