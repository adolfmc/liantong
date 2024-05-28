package com.baidu.p120ar.arplay.core.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.arplay.core.engine.ARPTouchInput */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPTouchInput {
    private static final int CLICK_DISTANCE = 20;
    private static final int CLICK_TIME_IN_MILS = 300;
    private static final int DOUBLE_CLICK_MAX_DISTANCE = 100;
    private static final int DOUBLE_CLICK_TIME_IN_MILS = 400;
    private static final int LONG_PRESS_TIME_IN_MILS = 600;
    private static final int SCROLL_DISTANCE = 50;
    private static final int SCROLL_MIN_DISTANCE = 20;
    private static final double SWIPE_DISTANCE_THRESHOLD = 100.0d;
    private static final double SWIPE_VELOCITY_THRESHOLD = 20.0d;
    private static final String TAG = "ARPTouchInput";
    private static final double TWO_FINGER_SCROLL_ANGLE = 0.7853981633974483d;
    private GestureHandler mGestureHandler;
    private boolean mUserTouchEnable = true;
    private boolean mEnginSoLoaded = false;
    private boolean mReleased = false;
    private boolean isScreenOrientationLandscape = false;
    private int mScreenWidth = 0;
    private int mScreenHeight = 0;
    private GestureStatus mGestureStatus = GestureStatus.EStatSingFingerCandidate;
    private TouchInfo mLastClickInfo = null;
    private Map<Integer, Vector<Float>> mTouchPostionMap = new HashMap();
    private boolean mFirstFingnerInClickArea = true;
    private TouchInfo mFirstFingerTouch = new TouchInfo();
    private TouchInfo mFirstFingerLastTouch = new TouchInfo();
    private TouchInfo mSecFingerTouch = new TouchInfo();
    private TouchInfo mSecFingerLastTouch = new TouchInfo();
    private double mLastDistance = -1.0d;
    private boolean mPinchMove = true;
    private boolean mLastPinch = false;
    private SwipeDirection mSwipeDirection = SwipeDirection.ESWIPE_RIGHT;
    private GestureManager mGestureManager = new GestureManager();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPTouchInput$GestureEventType */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum GestureEventType {
        EGESTURE_CLICK,
        EGESTURE_DOUBLE_CLICK,
        EGESTURE_LONG_PRESS,
        EGESTURE_SWIPE,
        EGESTURE_SINGLE_FINGER_SCROLL,
        EGESTURE_TWO_FINGER_SCROLL,
        EGESTURE_TWO_FINGER_PINCH,
        EGESTURE_TWO_FINGER_UNPINCH,
        EGESTURE_TWO_FINGER_ROTATE,
        EGESTURE_CLEAR
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPTouchInput$GestureStatus */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum GestureStatus {
        EStatSingFingerCandidate,
        EStatTwoFingersCandidate,
        EStatLongPress,
        EStatScroll,
        EStatSwipe,
        EStatTwoFingersScroll,
        EStatPinch,
        EStatUnPinch,
        EScrollAfterLongPress,
        EStatPinchAndUnpinch,
        EStatTwoFingersRotate,
        EStatUnknown
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPTouchInput$SwipeDirection */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum SwipeDirection {
        ESWIPE_RIGHT,
        ESWIPE_LEFT,
        ESWIPE_UP,
        ESWIPE_DOWN
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPTouchInput$TouchPhase */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum TouchPhase {
        ETOUCH_BEGIN,
        ETOUCH_MOVE,
        ETOUCH_END,
        ETOUCH_CANCEL
    }

    public ARPTouchInput(Looper looper) {
        this.mGestureHandler = new GestureHandler(looper);
        onResume();
    }

    public void setEnginSoLoaded(boolean z) {
        this.mEnginSoLoaded = z;
    }

    public void setUserInteractionEnabled(boolean z) {
        this.mUserTouchEnable = z;
    }

    public void setScreenWidthHight(int i, int i2) {
        Log.d("ARPTouchInput", "setScreenWidthHight width * height = " + i + " * " + i2);
        if (this.isScreenOrientationLandscape) {
            this.mScreenWidth = i2;
            this.mScreenHeight = i;
            return;
        }
        this.mScreenWidth = i;
        this.mScreenHeight = i2;
    }

    public void setScreenOrientationLandscape(boolean z) {
        Log.d("ARPTouchInput", "setScreenOrientationLandscape landscape = " + z);
        this.isScreenOrientationLandscape = z;
    }

    public void onTouchEvent(MotionEvent motionEvent) {
        if (!this.mEnginSoLoaded || !this.mUserTouchEnable || this.mReleased) {
            Log.e("ARPTouchInput", "onTouchEvent mEnginSoLoaded = " + this.mEnginSoLoaded + " && mUserTouchEnable = " + this.mUserTouchEnable + " && mReleased = " + this.mReleased);
            return;
        }
        sendTouchUpdateEvent(motionEvent);
        sendGestureUpdateEvent(motionEvent);
    }

    public void onResume() {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager != null) {
            gestureManager.onResume();
        }
    }

    public void onPause() {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager != null) {
            gestureManager.onPause();
        }
    }

    public void release() {
        this.mReleased = true;
        GestureHandler gestureHandler = this.mGestureHandler;
        if (gestureHandler != null) {
            gestureHandler.removeCallbacksAndMessages(null);
            this.mGestureManager = null;
        }
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager != null) {
            gestureManager.release();
            this.mGestureManager = null;
        }
    }

    private void sendTouchUpdateEvent(MotionEvent motionEvent) {
        float f;
        int i;
        int i2;
        if (motionEvent.getPointerCount() <= 0) {
            return;
        }
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        float x = motionEvent.getX(actionIndex);
        float y = motionEvent.getY(actionIndex);
        long eventTime = motionEvent.getEventTime();
        float pressure = motionEvent.getPressure(actionIndex);
        float f2 = 0.0f;
        float f3 = 0.0f;
        switch (actionMasked) {
            case 0:
            case 5:
                Vector<Float> vector = new Vector<>(2);
                vector.add(Float.valueOf(x));
                vector.add(Float.valueOf(y));
                this.mTouchPostionMap.put(Integer.valueOf(pointerId), vector);
                sendTouchUpdate(pointerId, x, y, 0.0f, 0.0f, eventTime, TouchPhase.ETOUCH_BEGIN.ordinal(), pressure);
                return;
            case 1:
            case 6:
                if (this.mTouchPostionMap.containsKey(Integer.valueOf(pointerId))) {
                    Vector<Float> vector2 = this.mTouchPostionMap.get(Integer.valueOf(pointerId));
                    this.mTouchPostionMap.remove(Integer.valueOf(pointerId));
                    f3 = y - vector2.elementAt(1).floatValue();
                    f2 = x - vector2.elementAt(0).floatValue();
                }
                sendTouchUpdate(pointerId, x, y, f2, f3, eventTime, TouchPhase.ETOUCH_END.ordinal(), pressure);
                return;
            case 2:
                int pointerCount = motionEvent.getPointerCount();
                int i3 = 0;
                while (i3 < pointerCount) {
                    int pointerId2 = motionEvent.getPointerId(i3);
                    float x2 = motionEvent.getX(i3);
                    float y2 = motionEvent.getY(i3);
                    float f4 = 0.0f;
                    if (this.mTouchPostionMap.containsKey(Integer.valueOf(pointerId2))) {
                        Vector<Float> vector3 = this.mTouchPostionMap.get(Integer.valueOf(pointerId2));
                        f4 = x2 - vector3.firstElement().floatValue();
                        f = y2 - vector3.lastElement().floatValue();
                        vector3.setElementAt(Float.valueOf(x2), 0);
                        vector3.setElementAt(Float.valueOf(y2), 1);
                    } else {
                        f = 0.0f;
                    }
                    if (Math.abs(f4) > 0.1f || Math.abs(f) > 0.1f) {
                        i = i3;
                        i2 = pointerCount;
                        sendTouchUpdate(pointerId2, x2, y2, f4, f, eventTime, TouchPhase.ETOUCH_MOVE.ordinal(), motionEvent.getPressure(i3));
                    } else {
                        i = i3;
                        i2 = pointerCount;
                    }
                    i3 = i + 1;
                    pointerCount = i2;
                }
                return;
            case 3:
                if (this.mTouchPostionMap.containsKey(Integer.valueOf(pointerId))) {
                    Vector<Float> vector4 = this.mTouchPostionMap.get(Integer.valueOf(pointerId));
                    this.mTouchPostionMap.remove(Integer.valueOf(pointerId));
                    f3 = y - vector4.elementAt(1).floatValue();
                    f2 = x - vector4.elementAt(0).floatValue();
                }
                sendTouchUpdate(pointerId, x, y, f2, f3, eventTime, TouchPhase.ETOUCH_CANCEL.ordinal(), pressure);
                return;
            case 4:
            default:
                return;
        }
    }

    private void sendTouchUpdate(int i, float f, float f2, float f3, float f4, long j, int i2, float f5) {
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        int i3;
        int i4;
        if (this.isScreenOrientationLandscape) {
            f6 = this.mScreenWidth - f2;
            f9 = -f4;
            f7 = f;
            f8 = f3;
        } else {
            f6 = f;
            f7 = f2;
            f8 = f4;
            f9 = f3;
        }
        float[] windowSize = ARPEngine.getInstance().getWindowSize();
        if (windowSize.length != 2 || windowSize[0] <= 0.0f || windowSize[1] <= 0.0f || (i3 = this.mScreenWidth) <= 0 || (i4 = this.mScreenHeight) <= 0) {
            f10 = f6;
            f11 = f9;
            f12 = f7;
            f13 = f8;
        } else {
            float f14 = windowSize[0] / i3;
            float f15 = windowSize[1] / i4;
            f10 = f6 * f14;
            f11 = f9 * f14;
            f12 = f7 * f15;
            f13 = f8 * f15;
        }
        ARPEngine.getInstance().onTouchUpdate(i, f10, f12, f11, f13, j, i2, f5);
    }

    private void sendGestureUpdateEvent(MotionEvent motionEvent) {
        switch (this.mGestureStatus) {
            case EStatSingFingerCandidate:
                touchInSingleFingerCandidate(motionEvent);
                return;
            case EStatTwoFingersCandidate:
                touchInTwoFingersCandidate(motionEvent);
                return;
            case EStatScroll:
                touchInScroll(motionEvent);
                return;
            case EStatSwipe:
                touchInSwipe(motionEvent);
                return;
            case EStatTwoFingersScroll:
                touchInTwoFingersScroll(motionEvent);
                return;
            case EStatPinchAndUnpinch:
                touchInPinchAndUnpinch(motionEvent);
                return;
            case EStatTwoFingersRotate:
                touchInTwoFingersRotate(motionEvent);
                return;
            case EStatLongPress:
                touchInLongPress(motionEvent);
                return;
            case EScrollAfterLongPress:
                touchInScrollAfterLongPress(motionEvent);
                return;
            case EStatPinch:
            case EStatUnPinch:
            case EStatUnknown:
                touchInUnknownStatus(motionEvent);
                return;
            default:
                return;
        }
    }

    private void touchInSingleFingerCandidate(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 5) {
            switch (actionMasked) {
                case 0:
                    touchDownInSingleFingerCandidate(motionEvent);
                    return;
                case 1:
                    touchUpInSingleFingerCandidate(motionEvent);
                    return;
                case 2:
                    touchMoveInSingleFingerCandidate(motionEvent);
                    return;
                default:
                    return;
            }
        }
        touchPointerDownInSingleFingerCandidate(motionEvent);
    }

    private void touchDownInSingleFingerCandidate(MotionEvent motionEvent) {
        TouchInfo touchInfo;
        this.mFirstFingerTouch.init(motionEvent, motionEvent.getActionIndex());
        copyTouchInfo(this.mFirstFingerTouch, this.mFirstFingerLastTouch);
        Log.d("ARPTouchInput", "touchDownInSingleFingerCandidate() mFirstFingerTouch = " + this.mFirstFingerTouch.toString());
        if (this.mGestureHandler.hasMessages(2) && (touchInfo = this.mLastClickInfo) != null && getDistance(touchInfo.f4081x, this.mLastClickInfo.f4082y, this.mFirstFingerTouch.f4081x, this.mFirstFingerTouch.f4082y) > 100.0d) {
            sendDelayedClickEvent();
        }
        this.mGestureHandler.sendEmptyMessageDelayed(1, 600L);
    }

    private void touchMoveInSingleFingerCandidate(MotionEvent motionEvent) {
        double distance = getDistance(this.mFirstFingerTouch.f4081x, this.mFirstFingerTouch.f4082y, motionEvent.getX(), motionEvent.getY());
        Log.d("ARPTouchInput", "touchMoveInSingleFingerCandidate() distance = " + distance);
        if (distance < 20.0d) {
            if (motionEvent.getEventTime() - motionEvent.getDownTime() > 600) {
                this.mGestureStatus = GestureStatus.EStatLongPress;
                sendGestureLongPress(motionEvent.getDownTime(), this.mFirstFingerTouch.f4080id, motionEvent.getX(), motionEvent.getY());
                this.mGestureHandler.removeMessages(1);
            }
        } else if (distance < 50.0d) {
            this.mFirstFingnerInClickArea = false;
        } else {
            sendDelayedClickEvent();
            this.mGestureStatus = GestureStatus.EStatScroll;
            sendGestureScroll(motionEvent.getEventTime(), this.mFirstFingerTouch.f4080id, motionEvent.getX(), motionEvent.getY(), getFirstVelX(motionEvent), getFirstVelY(motionEvent));
            this.mGestureHandler.removeMessages(1);
        }
        this.mFirstFingerLastTouch.init(motionEvent, 0);
    }

    private void touchUpInSingleFingerCandidate(MotionEvent motionEvent) {
        if (getDistance(this.mFirstFingerTouch.f4081x, this.mFirstFingerTouch.f4082y, motionEvent.getX(), motionEvent.getY()) < 20.0d && motionEvent.getEventTime() - motionEvent.getDownTime() < 300) {
            GestureManager gestureManager = this.mGestureManager;
            if (gestureManager != null && gestureManager.isDoubleClickEnable()) {
                if (this.mGestureHandler.hasMessages(2)) {
                    this.mGestureHandler.removeMessages(2);
                    sendGestureClick(motionEvent.getDownTime(), motionEvent.getPointerId(0), motionEvent.getX(), motionEvent.getY());
                } else {
                    Message obtain = Message.obtain();
                    obtain.what = 2;
                    TouchInfo touchInfo = new TouchInfo();
                    touchInfo.init(motionEvent, 0);
                    obtain.obj = touchInfo;
                    this.mLastClickInfo = touchInfo;
                    this.mGestureHandler.sendMessageDelayed(obtain, 400L);
                }
            } else {
                sendGestureClick(motionEvent.getDownTime(), motionEvent.getPointerId(0), motionEvent.getX(), motionEvent.getY());
            }
        }
        clearStatus();
    }

    private void touchPointerDownInSingleFingerCandidate(MotionEvent motionEvent) {
        Log.d("ARPTouchInput", "touchPointerDownInSingleFingerCandidate() mFirstFingnerInClickArea = " + this.mFirstFingnerInClickArea);
        if (this.mFirstFingnerInClickArea) {
            this.mSecFingerTouch.init(motionEvent, motionEvent.getActionIndex());
            copyTouchInfo(this.mSecFingerTouch, this.mSecFingerLastTouch);
            this.mGestureStatus = GestureStatus.EStatTwoFingersCandidate;
        } else {
            this.mGestureStatus = GestureStatus.EStatUnknown;
        }
        sendDelayedClickEvent();
    }

    private void touchInTwoFingersCandidate(MotionEvent motionEvent) {
        float f;
        float f2;
        if (2 == motionEvent.getActionMasked()) {
            float firstX = getFirstX(motionEvent);
            float firstY = getFirstY(motionEvent);
            float secondX = getSecondX(motionEvent);
            float secondY = getSecondY(motionEvent);
            double distance = getDistance(this.mFirstFingerTouch.f4081x, this.mFirstFingerTouch.f4082y, firstX, firstY);
            double distance2 = getDistance(this.mSecFingerTouch.f4081x, this.mSecFingerTouch.f4082y, secondX, secondY);
            if ((distance > 50.0d || distance2 > 50.0d) && distance > 20.0d && distance2 > 20.0d) {
                double angleOfTwoLine = getAngleOfTwoLine(this.mFirstFingerTouch.f4081x, this.mFirstFingerTouch.f4082y, firstX, firstY, this.mSecFingerTouch.f4081x, this.mSecFingerTouch.f4082y, secondX, secondY);
                if (angleOfTwoLine < 0.7853981633974483d) {
                    this.mGestureStatus = GestureStatus.EStatTwoFingersScroll;
                    sendGesture2FingerScroll(motionEvent.getDownTime(), this.mFirstFingerTouch.f4080id, firstX, firstY, getFirstVelX(motionEvent), getFirstVelY(motionEvent), this.mSecFingerTouch.f4080id, secondX, secondY, getSecondVelX(motionEvent), getSecondVelY(motionEvent));
                    Log.d("ARPTouchInput", "EStatTwoFingersScroll");
                    f = secondX;
                    f2 = secondY;
                } else if (angleOfTwoLine <= 3.141592653589793d && angleOfTwoLine > 1.5707963267948966d) {
                    float f3 = (this.mFirstFingerTouch.f4081x + this.mSecFingerTouch.f4081x) / 2.0f;
                    float f4 = (this.mFirstFingerTouch.f4082y + this.mSecFingerTouch.f4082y) / 2.0f;
                    double angleOfTwoLine2 = getAngleOfTwoLine(f3, f4, this.mFirstFingerTouch.f4081x, this.mFirstFingerTouch.f4082y, this.mFirstFingerTouch.f4081x, this.mFirstFingerTouch.f4082y, firstX, firstY);
                    double angleOfTwoLine3 = getAngleOfTwoLine(f3, f4, this.mSecFingerTouch.f4081x, this.mSecFingerTouch.f4082y, this.mSecFingerTouch.f4081x, this.mSecFingerTouch.f4082y, secondX, secondY);
                    if (Math.abs(angleOfTwoLine2 - 1.5707963267948966d) < 0.6283185307179586d) {
                        f = secondX;
                        f2 = secondY;
                    } else if (Math.abs(angleOfTwoLine3 - 1.5707963267948966d) < 0.6283185307179586d) {
                        f = secondX;
                        f2 = secondY;
                    } else {
                        this.mGestureStatus = GestureStatus.EStatPinchAndUnpinch;
                        f = secondX;
                        f2 = secondY;
                        this.mLastDistance = getDistance(firstX, firstY, f, f2);
                    }
                    this.mGestureStatus = GestureStatus.EStatTwoFingersRotate;
                } else {
                    f = secondX;
                    f2 = secondY;
                    this.mGestureStatus = GestureStatus.EStatPinchAndUnpinch;
                    this.mLastDistance = getDistance(firstX, firstY, f, f2);
                }
            } else {
                f2 = secondY;
                f = secondX;
            }
            TouchInfo touchInfo = this.mFirstFingerLastTouch;
            touchInfo.f4081x = firstX;
            touchInfo.f4082y = firstY;
            touchInfo.time = motionEvent.getEventTime();
            TouchInfo touchInfo2 = this.mSecFingerLastTouch;
            touchInfo2.f4081x = f;
            touchInfo2.f4082y = f2;
            touchInfo2.time = motionEvent.getEventTime();
            return;
        }
        this.mGestureStatus = GestureStatus.EStatUnknown;
    }

    private void touchInScroll(MotionEvent motionEvent) {
        try {
            if (5 == motionEvent.getActionMasked()) {
                this.mFirstFingerTouch.init(motionEvent, this.mFirstFingerTouch.f4080id);
                copyTouchInfo(this.mFirstFingerTouch, this.mFirstFingerLastTouch);
                this.mSecFingerTouch.init(motionEvent, motionEvent.getActionIndex());
                copyTouchInfo(this.mSecFingerTouch, this.mSecFingerLastTouch);
                this.mGestureStatus = GestureStatus.EStatTwoFingersCandidate;
                if (this.mGestureManager == null || !this.mGestureManager.isScrollEnable()) {
                    return;
                }
                clearGesture();
            } else if (6 == motionEvent.getActionMasked()) {
                this.mGestureStatus = GestureStatus.EStatUnknown;
            } else if (1 == motionEvent.getActionMasked()) {
                clearStatus();
            } else if (2 == motionEvent.getActionMasked()) {
                if (motionEvent.getEventTime() - this.mFirstFingerLastTouch.time >= 1) {
                    float firstX = getFirstX(motionEvent);
                    float firstY = getFirstY(motionEvent);
                    sendGestureScroll(motionEvent.getEventTime(), this.mFirstFingerTouch.f4080id, firstX, firstY, getFirstVelX(motionEvent), getFirstVelY(motionEvent));
                    float f = firstX - this.mFirstFingerLastTouch.f4081x;
                    float f2 = firstY - this.mFirstFingerLastTouch.f4082y;
                    float eventTime = f / ((float) (motionEvent.getEventTime() - this.mFirstFingerLastTouch.time));
                    float eventTime2 = f2 / ((float) (motionEvent.getEventTime() - this.mFirstFingerLastTouch.time));
                    if (Math.abs(f) > Math.abs(f2) && Math.abs(f) > 100.0d && Math.abs(eventTime) > 20.0d) {
                        if (f > 0.0f) {
                            this.mSwipeDirection = SwipeDirection.ESWIPE_RIGHT;
                        } else {
                            this.mSwipeDirection = SwipeDirection.ESWIPE_LEFT;
                        }
                        this.mGestureStatus = GestureStatus.EStatSwipe;
                    } else if (Math.abs(f2) > Math.abs(f) && Math.abs(f2) > 100.0d && Math.abs(eventTime2) > 20.0d) {
                        if (f2 > 0.0f) {
                            this.mSwipeDirection = SwipeDirection.ESWIPE_DOWN;
                        } else {
                            this.mSwipeDirection = SwipeDirection.ESWIPE_UP;
                        }
                        this.mGestureStatus = GestureStatus.EStatSwipe;
                    }
                    this.mFirstFingerLastTouch.f4081x = firstX;
                    this.mFirstFingerLastTouch.f4082y = firstY;
                    this.mFirstFingerLastTouch.time = motionEvent.getEventTime();
                }
            } else {
                this.mGestureStatus = GestureStatus.EStatUnknown;
            }
        } catch (Exception unused) {
            Log.e("ARPTouchInput", "Monkey event.getX Exception");
        }
    }

    private void touchInSwipe(MotionEvent motionEvent) {
        if (1 == motionEvent.getActionMasked()) {
            sendGestureSwipe(motionEvent.getEventTime(), this.mFirstFingerTouch.f4080id, this.mSwipeDirection.ordinal());
        }
    }

    private void touchInTwoFingersScroll(MotionEvent motionEvent) {
        if (5 == motionEvent.getActionMasked()) {
            this.mGestureStatus = GestureStatus.EStatUnknown;
        } else if (6 == motionEvent.getActionMasked()) {
            this.mGestureStatus = GestureStatus.EStatScroll;
            if (((motionEvent.getAction() & 65280) >>> 8) == this.mFirstFingerTouch.f4080id) {
                copyTouchInfo(this.mSecFingerTouch, this.mFirstFingerTouch);
                copyTouchInfo(this.mSecFingerLastTouch, this.mFirstFingerLastTouch);
            }
            clearGesture();
        } else if (2 == motionEvent.getActionMasked()) {
            if (motionEvent.getEventTime() - this.mFirstFingerLastTouch.time < 1 || motionEvent.getEventTime() - this.mSecFingerLastTouch.time < 1) {
                return;
            }
            float firstX = getFirstX(motionEvent);
            float firstY = getFirstY(motionEvent);
            float secondX = getSecondX(motionEvent);
            float secondY = getSecondY(motionEvent);
            sendGesture2FingerScroll(motionEvent.getEventTime(), this.mFirstFingerTouch.f4080id, firstX, firstY, getFirstVelX(motionEvent), getFirstVelY(motionEvent), this.mSecFingerTouch.f4080id, secondX, secondY, getSecondVelX(motionEvent), getSecondVelY(motionEvent));
            TouchInfo touchInfo = this.mFirstFingerTouch;
            touchInfo.f4081x = firstX;
            touchInfo.f4082y = firstY;
            this.mFirstFingerLastTouch.time = motionEvent.getEventTime();
            TouchInfo touchInfo2 = this.mSecFingerLastTouch;
            touchInfo2.f4081x = secondX;
            touchInfo2.f4082y = secondY;
            touchInfo2.time = motionEvent.getEventTime();
        } else {
            this.mGestureStatus = GestureStatus.EStatUnknown;
        }
    }

    private void touchInPinchAndUnpinch(MotionEvent motionEvent) {
        if (5 == motionEvent.getActionMasked()) {
            this.mGestureStatus = GestureStatus.EStatUnknown;
            this.mPinchMove = true;
        } else if (6 == motionEvent.getActionMasked()) {
            this.mGestureStatus = GestureStatus.EStatScroll;
            if (((motionEvent.getAction() & 65280) >>> 8) == this.mFirstFingerTouch.f4080id) {
                this.mFirstFingerTouch.f4080id = this.mSecFingerTouch.f4080id;
            }
            this.mPinchMove = true;
            clearScrollGesture();
        } else if (2 == motionEvent.getActionMasked()) {
            if (motionEvent.getEventTime() - this.mFirstFingerLastTouch.time >= 1 && motionEvent.getEventTime() - this.mSecFingerLastTouch.time >= 1) {
                float firstX = getFirstX(motionEvent);
                float firstY = getFirstY(motionEvent);
                float secondX = getSecondX(motionEvent);
                float secondY = getSecondY(motionEvent);
                double distance = getDistance(firstX, firstY, secondX, secondY);
                sendGesturePinch(motionEvent.getEventTime(), this.mFirstFingerTouch.f4080id, firstX, firstY, getFirstVelX(motionEvent), getFirstVelY(motionEvent), this.mSecFingerTouch.f4080id, secondX, secondY, getSecondVelX(motionEvent), getSecondVelY(motionEvent), distance < this.mLastDistance);
                this.mLastDistance = distance;
                TouchInfo touchInfo = this.mFirstFingerTouch;
                touchInfo.f4081x = firstX;
                touchInfo.f4082y = firstY;
                this.mFirstFingerLastTouch.time = motionEvent.getEventTime();
                TouchInfo touchInfo2 = this.mSecFingerLastTouch;
                touchInfo2.f4081x = secondX;
                touchInfo2.f4082y = secondY;
                touchInfo2.time = motionEvent.getEventTime();
            }
        } else {
            this.mGestureStatus = GestureStatus.EStatUnknown;
        }
    }

    private void touchInTwoFingersRotate(MotionEvent motionEvent) {
        if (2 == motionEvent.getActionMasked()) {
            float firstX = getFirstX(motionEvent);
            float firstY = getFirstY(motionEvent);
            float secondX = getSecondX(motionEvent);
            float secondY = getSecondY(motionEvent);
            sendGestureRotate(motionEvent.getEventTime(), this.mFirstFingerTouch.f4080id, firstX, firstY, this.mSecFingerTouch.f4080id, secondX, secondY, (float) getSignedAngleBetweenVector2(this.mSecFingerLastTouch.f4081x - this.mFirstFingerTouch.f4081x, this.mSecFingerLastTouch.f4082y - this.mFirstFingerTouch.f4082y, secondX - firstX, secondY - firstY));
            TouchInfo touchInfo = this.mFirstFingerTouch;
            touchInfo.f4081x = firstX;
            touchInfo.f4082y = firstY;
            this.mFirstFingerLastTouch.time = motionEvent.getEventTime();
            TouchInfo touchInfo2 = this.mSecFingerLastTouch;
            touchInfo2.f4081x = secondX;
            touchInfo2.f4082y = secondY;
            touchInfo2.time = motionEvent.getEventTime();
        } else if (1 == motionEvent.getActionMasked()) {
            clearStatus();
        } else {
            this.mGestureStatus = GestureStatus.EStatUnknown;
        }
    }

    private void touchInLongPress(MotionEvent motionEvent) {
        if (2 == motionEvent.getActionMasked()) {
            if (getDistance(this.mFirstFingerTouch.f4081x, this.mFirstFingerTouch.f4082y, motionEvent.getX(), motionEvent.getY()) > 50.0d) {
                this.mGestureStatus = GestureStatus.EScrollAfterLongPress;
                sendGestureScroll(motionEvent.getEventTime(), this.mFirstFingerTouch.f4080id, motionEvent.getX(), motionEvent.getY(), getFirstVelX(motionEvent), getFirstVelY(motionEvent));
                this.mFirstFingerTouch.f4081x = getFirstX(motionEvent);
                this.mFirstFingerTouch.f4082y = getFirstY(motionEvent);
                this.mFirstFingerLastTouch.time = motionEvent.getEventTime();
            }
        } else if (1 == motionEvent.getActionMasked()) {
            clearStatus();
        } else {
            this.mGestureStatus = GestureStatus.EStatUnknown;
        }
    }

    private void touchInScrollAfterLongPress(MotionEvent motionEvent) {
        if (2 == motionEvent.getActionMasked()) {
            sendGestureScroll(motionEvent.getEventTime(), this.mFirstFingerTouch.f4080id, motionEvent.getX(), motionEvent.getY(), getFirstVelX(motionEvent), getFirstVelY(motionEvent));
            this.mFirstFingerTouch.f4081x = getFirstX(motionEvent);
            this.mFirstFingerTouch.f4082y = getFirstY(motionEvent);
            this.mFirstFingerLastTouch.time = motionEvent.getEventTime();
        } else if (1 == motionEvent.getActionMasked()) {
            clearStatus();
        } else {
            this.mGestureStatus = GestureStatus.EStatUnknown;
        }
    }

    private void touchInUnknownStatus(MotionEvent motionEvent) {
        if (1 == motionEvent.getActionMasked()) {
            clearStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendGestureClick(long j, int i, float f, float f2) {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager == null || !gestureManager.isClickEnable()) {
            return;
        }
        sendGestureUpdate(GestureEventType.EGESTURE_CLICK.ordinal(), j, i, f, f2, -1.0f, -1.0f, -1, -1.0f, -1.0f, -1.0f, -1.0f, -1, 0.0f);
        clearGesture();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendGestureLongPress(long j, int i, float f, float f2) {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager == null || !gestureManager.isLongPressEnable()) {
            return;
        }
        sendGestureUpdate(GestureEventType.EGESTURE_LONG_PRESS.ordinal(), j, i, f, f2, -1.0f, -1.0f, -1, -1.0f, -1.0f, -1.0f, -1.0f, -1, 0.0f);
    }

    private void sendGestureScroll(long j, int i, float f, float f2, float f3, float f4) {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager == null || !gestureManager.isScrollEnable()) {
            return;
        }
        sendGestureUpdate(GestureEventType.EGESTURE_SINGLE_FINGER_SCROLL.ordinal(), j, i, f, f2, f3, f4, -1, -1.0f, -1.0f, -1.0f, -1.0f, -1, 0.0f);
    }

    private void sendGesture2FingerScroll(long j, int i, float f, float f2, float f3, float f4, int i2, float f5, float f6, float f7, float f8) {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager == null || !gestureManager.is2FingerScrollEnable()) {
            return;
        }
        sendGestureUpdate(GestureEventType.EGESTURE_TWO_FINGER_SCROLL.ordinal(), j, i, f, f2, f3, f4, i2, f5, f6, f7, f8, -1, 0.0f);
    }

    private void sendGestureSwipe(long j, int i, int i2) {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager == null || !gestureManager.isSwipeEnable()) {
            return;
        }
        sendGestureUpdate(GestureEventType.EGESTURE_SWIPE.ordinal(), j, i, -1.0f, -1.0f, -1.0f, -1.0f, -1, -1.0f, -1.0f, -1.0f, -1.0f, i2, 0.0f);
        clearStatus();
    }

    private void sendGesturePinch(long j, int i, float f, float f2, float f3, float f4, int i2, float f5, float f6, float f7, float f8, boolean z) {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager == null || !gestureManager.isPinchEnable()) {
            return;
        }
        if (!this.mPinchMove) {
            if ((z && !this.mLastPinch) || (!z && this.mLastPinch)) {
                clearGesture();
            }
            this.mLastPinch = z;
        }
        sendGestureUpdate(z ? GestureEventType.EGESTURE_TWO_FINGER_PINCH.ordinal() : GestureEventType.EGESTURE_TWO_FINGER_UNPINCH.ordinal(), j, i, f, f2, f3, f4, i2, f5, f6, f7, f8, -1, 0.0f);
        if (this.mPinchMove) {
            this.mLastPinch = z;
        }
    }

    private void sendGestureRotate(long j, int i, float f, float f2, int i2, float f3, float f4, float f5) {
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager == null || !gestureManager.isRotateEnable()) {
            return;
        }
        sendGestureUpdate(GestureEventType.EGESTURE_TWO_FINGER_ROTATE.ordinal(), j, i, f, f2, -1.0f, -1.0f, i2, f3, f4, -1.0f, -1.0f, -1, f5);
    }

    private void clearGesture() {
        sendGestureUpdate(GestureEventType.EGESTURE_CLEAR.ordinal(), -1L, -1, -1.0f, -1.0f, -1.0f, -1.0f, -1, -1.0f, -1.0f, -1.0f, -1.0f, -1, 0.0f);
    }

    private void clearScrollGesture() {
        ARPEngine.getInstance().onGestureUpdateWithScaleFinish(GestureEventType.EGESTURE_CLEAR.ordinal(), -1L, -1, -1.0f, -1.0f, -1.0f, -1.0f, -1, -1.0f, -1.0f, -1.0f, -1.0f, -1, 0.0f, true);
    }

    private void sendGestureUpdate(int i, long j, int i2, float f, float f2, float f3, float f4, int i3, float f5, float f6, float f7, float f8, int i4, float f9) {
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        int i5;
        int i6;
        if (this.isScreenOrientationLandscape) {
            int i7 = this.mScreenWidth;
            f10 = i7 - f2;
            f12 = i7 - f6;
            f11 = f;
            f13 = f5;
        } else {
            f10 = f;
            f11 = f2;
            f12 = f5;
            f13 = f6;
        }
        float[] windowSize = ARPEngine.getInstance().getWindowSize();
        if (windowSize.length != 2 || windowSize[0] <= 0.0f || windowSize[1] <= 0.0f || (i5 = this.mScreenWidth) <= 0 || (i6 = this.mScreenHeight) <= 0) {
            f14 = f3;
            f15 = f4;
            f16 = f7;
            f17 = f8;
            f18 = f12;
            f19 = f10;
            f20 = f11;
            f21 = f13;
        } else {
            float f22 = windowSize[0] / i5;
            float f23 = windowSize[1] / i6;
            f18 = f12 * f22;
            f19 = f10 * f22;
            f20 = f11 * f23;
            f21 = f13 * f23;
            f17 = f23 * f8;
            f16 = f22 * f7;
            f14 = f3 * f22;
            f15 = f4 * f23;
        }
        ARPEngine.getInstance().onGestureUpdate(i, j, i2, f19, f20, f14, f15, i3, f18, f21, f16, f17, i4, f9);
    }

    private void clearStatus() {
        this.mGestureStatus = GestureStatus.EStatSingFingerCandidate;
        this.mFirstFingnerInClickArea = true;
        this.mLastDistance = -1.0d;
        this.mGestureHandler.removeMessages(1);
        if (this.mGestureHandler.hasMessages(2)) {
            return;
        }
        clearGesture();
    }

    private float getFirstVelX(MotionEvent motionEvent) {
        return (motionEvent.getX(motionEvent.findPointerIndex(this.mFirstFingerTouch.f4080id)) - this.mFirstFingerTouch.f4081x) / ((float) (motionEvent.getEventTime() - this.mFirstFingerLastTouch.time));
    }

    private float getFirstVelY(MotionEvent motionEvent) {
        return (motionEvent.getY(motionEvent.findPointerIndex(this.mFirstFingerTouch.f4080id)) - this.mFirstFingerTouch.f4082y) / ((float) (motionEvent.getEventTime() - this.mFirstFingerLastTouch.time));
    }

    private float getSecondVelX(MotionEvent motionEvent) {
        return (motionEvent.getX(motionEvent.findPointerIndex(this.mSecFingerTouch.f4080id)) - this.mSecFingerLastTouch.f4081x) / ((float) (motionEvent.getEventTime() - this.mSecFingerLastTouch.time));
    }

    private float getSecondVelY(MotionEvent motionEvent) {
        return (motionEvent.getY(motionEvent.findPointerIndex(this.mSecFingerTouch.f4080id)) - this.mSecFingerLastTouch.f4082y) / ((float) (motionEvent.getEventTime() - this.mSecFingerLastTouch.time));
    }

    private float getFirstX(MotionEvent motionEvent) {
        return motionEvent.getX(motionEvent.findPointerIndex(this.mFirstFingerTouch.f4080id));
    }

    private float getFirstY(MotionEvent motionEvent) {
        return motionEvent.getY(motionEvent.findPointerIndex(this.mFirstFingerTouch.f4080id));
    }

    private float getSecondX(MotionEvent motionEvent) {
        return motionEvent.getX(motionEvent.findPointerIndex(this.mSecFingerTouch.f4080id));
    }

    private float getSecondY(MotionEvent motionEvent) {
        return motionEvent.getY(motionEvent.findPointerIndex(this.mSecFingerTouch.f4080id));
    }

    private double getDistance(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        return Math.sqrt((f5 * f5) + (f6 * f6));
    }

    private double getAngleOfTwoLine(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float f9 = f3 - f;
        float f10 = f4 - f2;
        float f11 = f7 - f5;
        float f12 = f8 - f6;
        Log.d("ARPTouchInput", String.format("vx1 %1.3f vy1 %1.3f vx2 %1.3f vy2 %1.3f", Float.valueOf(f9), Float.valueOf(f10), Float.valueOf(f11), Float.valueOf(f12)));
        double sqrt = ((f9 * f11) + (f10 * f12)) / (Math.sqrt((f9 * f9) + (f10 * f10)) * Math.sqrt((f11 * f11) + (f12 * f12)));
        try {
            sqrt = Double.parseDouble(new DecimalFormat("#.00").format(sqrt));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        double acos = Math.acos(sqrt);
        Log.d("ARPTouchInput", String.format("angle is %1.3f", Double.valueOf(Math.toDegrees(acos))));
        return acos;
    }

    private double getSignedAngleBetweenVector2(double d, double d2, double d3, double d4) {
        double atan2 = Math.atan2(d4, d3) - Math.atan2(d2, d);
        return atan2 > 3.141592653589793d ? atan2 - 6.283185307179586d : atan2 < -3.141592653589793d ? atan2 + 6.283185307179586d : atan2;
    }

    private void sendDelayedClickEvent() {
        if (this.mGestureHandler.hasMessages(2)) {
            this.mGestureHandler.removeMessages(2);
            TouchInfo touchInfo = this.mLastClickInfo;
            if (touchInfo != null) {
                sendGestureClick(touchInfo.time, this.mLastClickInfo.f4080id, this.mLastClickInfo.f4081x, this.mLastClickInfo.f4082y);
                this.mLastClickInfo = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPTouchInput$GestureHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class GestureHandler extends Handler {
        public static final int MSG_LONG_PRESS = 1;
        public static final int MSG_SINGLE_CLICK = 2;

        public GestureHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (ARPTouchInput.this.mReleased) {
                return;
            }
            switch (message.what) {
                case 1:
                    if (ARPTouchInput.this.mGestureStatus == GestureStatus.EStatSingFingerCandidate && ARPTouchInput.this.mFirstFingnerInClickArea) {
                        ARPTouchInput.this.mGestureStatus = GestureStatus.EStatLongPress;
                        ARPTouchInput.this.sendGestureLongPress(System.currentTimeMillis(), ARPTouchInput.this.mFirstFingerTouch.f4080id, ARPTouchInput.this.mFirstFingerTouch.f4081x, ARPTouchInput.this.mFirstFingerTouch.f4082y);
                        return;
                    }
                    return;
                case 2:
                    if (message.obj != null) {
                        TouchInfo touchInfo = (TouchInfo) message.obj;
                        ARPTouchInput.this.sendGestureClick(touchInfo.time, touchInfo.f4080id, touchInfo.f4081x, touchInfo.f4082y);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private void copyTouchInfo(TouchInfo touchInfo, TouchInfo touchInfo2) {
        touchInfo2.f4080id = touchInfo.f4080id;
        touchInfo2.f4081x = touchInfo.f4081x;
        touchInfo2.f4082y = touchInfo.f4082y;
        touchInfo2.time = touchInfo.time;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPTouchInput$TouchInfo */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class TouchInfo {

        /* renamed from: id */
        int f4080id;
        long time;

        /* renamed from: x */
        float f4081x;

        /* renamed from: y */
        float f4082y;

        private TouchInfo() {
            this.f4080id = -1;
            this.f4081x = -1.0f;
            this.f4082y = -1.0f;
            this.time = -1L;
        }

        public void init(MotionEvent motionEvent, int i) {
            this.f4080id = motionEvent.getPointerId(i);
            this.f4081x = motionEvent.getX(i);
            this.f4082y = motionEvent.getY(i);
            this.time = motionEvent.getEventTime();
        }

        public String toString() {
            return "fingerId: " + this.f4080id + " && x,y: " + this.f4081x + "," + this.f4082y + " && time: " + this.time;
        }
    }
}
