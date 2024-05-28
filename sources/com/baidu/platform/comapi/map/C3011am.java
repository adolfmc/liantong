package com.baidu.platform.comapi.map;

import android.view.GestureDetector;
import android.view.MotionEvent;
import com.baidu.platform.comapi.map.MapController;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.am */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3011am extends GestureDetector.SimpleOnGestureListener {

    /* renamed from: a */
    private MapController f7741a;

    /* renamed from: b */
    private OnLongPressListener f7742b;

    /* renamed from: c */
    private volatile Set<GestureDetector.SimpleOnGestureListener> f7743c = new CopyOnWriteArraySet();

    /* renamed from: d */
    private Object f7744d = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public OnLongPressListener m17949a() {
        return this.f7742b;
    }

    /* renamed from: a */
    public void m17948a(GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        synchronized (this.f7744d) {
            this.f7743c.add(simpleOnGestureListener);
        }
    }

    /* renamed from: a */
    public void m17947a(MapController mapController) {
        this.f7741a = mapController;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17946a(OnLongPressListener onLongPressListener) {
        this.f7742b = onLongPressListener;
    }

    /* renamed from: b */
    public void m17945b(GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        synchronized (this.f7744d) {
            this.f7743c.remove(simpleOnGestureListener);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(MotionEvent motionEvent) {
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    simpleOnGestureListener.onDoubleTap(motionEvent);
                }
            }
        }
        MapController mapController = this.f7741a;
        if (mapController != null) {
            mapController.handleDoubleDownClick(motionEvent);
            return true;
        }
        return true;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        MapController mapController;
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    simpleOnGestureListener.onDoubleTapEvent(motionEvent);
                }
            }
        }
        if (motionEvent.getAction() == 1 && (mapController = this.f7741a) != null) {
            mapController.handleDoubleTouch(motionEvent);
        }
        return super.onDoubleTapEvent(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    simpleOnGestureListener.onDown(motionEvent);
                }
            }
        }
        return super.onDown(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    simpleOnGestureListener.onFling(motionEvent, motionEvent2, f, f2);
                }
            }
        }
        MapController mapController = this.f7741a;
        if (mapController == null) {
            return false;
        }
        if (mapController.getMapControlMode() == MapController.MapControlMode.STREET) {
            this.f7741a.handleTouchUp(motionEvent2);
        }
        return this.f7741a.handleFling(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        OnLongPressListener onLongPressListener;
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    if (simpleOnGestureListener != null) {
                        simpleOnGestureListener.onLongPress(motionEvent);
                    }
                }
            }
        }
        MapController mapController = this.f7741a;
        if (mapController == null || mapController.isEnableDMoveZoom() || this.f7741a.isNaviMode() || (onLongPressListener = this.f7742b) == null) {
            return;
        }
        onLongPressListener.onLongPress(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    simpleOnGestureListener.onScroll(motionEvent, motionEvent2, f, f2);
                }
            }
        }
        return super.onScroll(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    if (simpleOnGestureListener != null) {
                        simpleOnGestureListener.onShowPress(motionEvent);
                    }
                }
            }
        }
        super.onShowPress(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    if (simpleOnGestureListener != null) {
                        simpleOnGestureListener.onSingleTapConfirmed(motionEvent);
                    }
                }
            }
        }
        MapController mapController = this.f7741a;
        return mapController != null && mapController.handleTouchSingleClick(motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        synchronized (this.f7744d) {
            Set<GestureDetector.SimpleOnGestureListener> set = this.f7743c;
            if (set != null) {
                for (GestureDetector.SimpleOnGestureListener simpleOnGestureListener : set) {
                    simpleOnGestureListener.onSingleTapUp(motionEvent);
                }
            }
        }
        return super.onSingleTapUp(motionEvent);
    }
}
