package com.baidu.p120ar.marker;

import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.AbstractARProxy;
import com.baidu.p120ar.marker.model.LocationMarkerData;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.marker.MarkerARProxy */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MarkerARProxy extends AbstractARProxy implements IMarker {
    private IMakerAxisCallback iMakerAxisZCallback;
    private IMarkerStateListener iMarkerStateListener;
    private WeakReference<IMarker> iMarkerWeakReferenceAR;
    private OnCompassCallback mCompassCallback;
    private OnTrackerSessionCallback mOnTrackerSessionCallback;

    @Override // com.baidu.p120ar.AbstractARProxy
    public void setARCase(AbstractAR abstractAR) {
        if (abstractAR instanceof IMarker) {
            this.iMarkerWeakReferenceAR = new WeakReference<>((IMarker) abstractAR);
            if (this.iMarkerStateListener != null) {
                this.iMarkerWeakReferenceAR.get().setMarkerStateListener(this.iMarkerStateListener);
            }
            if (this.mOnTrackerSessionCallback != null) {
                this.iMarkerWeakReferenceAR.get().setTrackerSessionCallback(this.mOnTrackerSessionCallback);
            }
            if (this.iMakerAxisZCallback != null) {
                this.iMarkerWeakReferenceAR.get().setAxisCallback(this.iMakerAxisZCallback);
            }
            if (this.mCompassCallback != null) {
                this.iMarkerWeakReferenceAR.get().setMarkerCompassCallback(this.mCompassCallback);
            }
        }
    }

    @Override // com.baidu.p120ar.AbstractARProxy
    public void release() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.clear();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void setAvailableFrame(MarkerFrameInfo markerFrameInfo) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().setAvailableFrame(markerFrameInfo);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void setMarkerStateListener(IMarkerStateListener iMarkerStateListener) {
        this.iMarkerStateListener = iMarkerStateListener;
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().setMarkerStateListener(iMarkerStateListener);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void resetMarker() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().resetMarker();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void chioceOneCoordinate(LocationMarkerData locationMarkerData) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().chioceOneCoordinate(locationMarkerData);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public float[] location2ScreenPoint(float[] fArr) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference != null && weakReference.get() != null) {
            return this.iMarkerWeakReferenceAR.get().location2ScreenPoint(fArr);
        }
        return new float[0];
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void createSession() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().createSession();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void initMarkerByTrackerType(TrackerType trackerType) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().initMarkerByTrackerType(trackerType);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void postArrow(String str, int i, double[] dArr, double[] dArr2, double[] dArr3, float f, float f2) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().postArrow(str, i, dArr, dArr2, dArr3, f, f2);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void postArrow(float f, float f2, float f3, float f4, int i) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().postArrow(f, f2, f3, f4, i);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void postLiftUp(double[] dArr, float f) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().postLiftUp(dArr, f);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void postLiftDown(double[] dArr, float f) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().postLiftDown(dArr, f);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void removeLiftUp() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().removeLiftUp();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void removeLiftDown() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().removeLiftDown();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void setAxisCallback(IMakerAxisCallback iMakerAxisCallback) {
        this.iMakerAxisZCallback = iMakerAxisCallback;
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().setAxisCallback(iMakerAxisCallback);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void postFinalArrow(String str, double[] dArr) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().postFinalArrow(str, dArr);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void removeArrowByArrowId(String str) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().removeArrowByArrowId(str);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void removeAllArrow() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().removeAllArrow();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void postRoute(List<double[]> list) {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().postRoute(list);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void clearRoute() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().clearRoute();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void setTrackerSessionCallback(OnTrackerSessionCallback onTrackerSessionCallback) {
        this.mOnTrackerSessionCallback = onTrackerSessionCallback;
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().setTrackerSessionCallback(onTrackerSessionCallback);
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void hideFinalPoint() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().hideFinalPoint();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void hideNavigationContent() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().hideNavigationContent();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void showNavigationContent() {
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().showNavigationContent();
    }

    @Override // com.baidu.p120ar.marker.IMarker
    public void setMarkerCompassCallback(OnCompassCallback onCompassCallback) {
        this.mCompassCallback = onCompassCallback;
        WeakReference<IMarker> weakReference = this.iMarkerWeakReferenceAR;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.iMarkerWeakReferenceAR.get().setMarkerCompassCallback(onCompassCallback);
    }
}
