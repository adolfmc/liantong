package cn.finalteam.galleryfinal.widget.zoonview;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;

@TargetApi(5)
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class EclairGestureDetector extends CupcakeGestureDetector {
    private static final int INVALID_POINTER_ID = -1;
    private int mActivePointerId;
    private int mActivePointerIndex;

    public EclairGestureDetector(Context context) {
        super(context);
        this.mActivePointerId = -1;
        this.mActivePointerIndex = 0;
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.CupcakeGestureDetector
    float getActiveX(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getX();
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.zoonview.CupcakeGestureDetector
    float getActiveY(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.mActivePointerIndex);
        } catch (Exception unused) {
            return motionEvent.getY();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0048  */
    @Override // cn.finalteam.galleryfinal.widget.zoonview.CupcakeGestureDetector, cn.finalteam.galleryfinal.widget.zoonview.GestureDetector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 3
            r2 = -1
            r3 = 0
            if (r0 == r1) goto L41
            r1 = 6
            if (r0 == r1) goto L19
            switch(r0) {
                case 0: goto L12;
                case 1: goto L41;
                default: goto L11;
            }
        L11:
            goto L43
        L12:
            int r0 = r6.getPointerId(r3)
            r5.mActivePointerId = r0
            goto L43
        L19:
            int r0 = r6.getAction()
            int r0 = cn.finalteam.galleryfinal.widget.zoonview.Compat.getPointerIndex(r0)
            int r1 = r6.getPointerId(r0)
            int r4 = r5.mActivePointerId
            if (r1 != r4) goto L43
            if (r0 != 0) goto L2d
            r0 = 1
            goto L2e
        L2d:
            r0 = r3
        L2e:
            int r1 = r6.getPointerId(r0)
            r5.mActivePointerId = r1
            float r1 = r6.getX(r0)
            r5.mLastTouchX = r1
            float r0 = r6.getY(r0)
            r5.mLastTouchY = r0
            goto L43
        L41:
            r5.mActivePointerId = r2
        L43:
            int r0 = r5.mActivePointerId
            if (r0 == r2) goto L48
            goto L49
        L48:
            r0 = r3
        L49:
            int r0 = r6.findPointerIndex(r0)
            r5.mActivePointerIndex = r0
            boolean r6 = super.onTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.galleryfinal.widget.zoonview.EclairGestureDetector.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
