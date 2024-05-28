package com.baidu.p120ar.arplay.core.engine.rotate;

import android.content.Context;
import android.view.OrientationEventListener;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.rotate.OrientationManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OrientationManager extends OrientationEventListener {
    private Orientation mCurrentOrientation;
    private List<OrientationListener> mListeners;
    private static Orientation sGlobalOrientation = Orientation.UNKNOWN;
    private static boolean isScreenOrientationLandscape = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.rotate.OrientationManager$OrientationListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OrientationListener {
        void onRotateOrientation(Orientation orientation);
    }

    public static Orientation getGlobalOrientation() {
        return sGlobalOrientation;
    }

    public static void setGlobalOrientation(Orientation orientation) {
        sGlobalOrientation = orientation;
    }

    public OrientationManager(Context context) {
        super(context);
        this.mCurrentOrientation = Orientation.PORTRAIT;
        this.mListeners = new ArrayList();
    }

    public void setScreenOrientationLandscape(boolean z) {
        isScreenOrientationLandscape = z;
    }

    public void addOrientationListener(OrientationListener orientationListener) {
        if (orientationListener == null || this.mListeners.contains(orientationListener)) {
            return;
        }
        this.mListeners.add(orientationListener);
    }

    public void removeOrientationListener(OrientationListener orientationListener) {
        if (orientationListener == null || !this.mListeners.contains(orientationListener)) {
            return;
        }
        this.mListeners.remove(orientationListener);
    }

    @Override // android.view.OrientationEventListener
    public void enable() {
        try {
            super.enable();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.OrientationEventListener
    public void disable() {
        try {
            super.disable();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i) {
        Orientation calcOrientation;
        if (i == -1 || (calcOrientation = calcOrientation(i, this.mCurrentOrientation)) == null) {
            return;
        }
        if (sGlobalOrientation == Orientation.UNKNOWN) {
            sGlobalOrientation = calcOrientation;
        }
        if (this.mCurrentOrientation != calcOrientation) {
            this.mCurrentOrientation = calcOrientation;
            notifyOrientationChanged(this.mCurrentOrientation);
        }
    }

    public void notifyOrientationChanged() {
        notifyOrientationChanged(this.mCurrentOrientation);
    }

    public void notifyOrientationChanged(Orientation orientation) {
        setGlobalOrientation(orientation);
        for (OrientationListener orientationListener : this.mListeners) {
            orientationListener.onRotateOrientation(orientation);
        }
    }

    public void destroy() {
        sGlobalOrientation = Orientation.UNKNOWN;
        this.mListeners.clear();
    }

    public static Orientation calcOrientation(int i, Orientation orientation) {
        if (isScreenOrientationLandscape) {
            i = (i + 90) % 360;
        }
        Orientation certainOrientation = certainOrientation(i);
        if (certainOrientation != null) {
            return certainOrientation;
        }
        Orientation[] probablyOrientation = probablyOrientation(i);
        return (orientation == probablyOrientation[0] || orientation == probablyOrientation[1]) ? orientation : nearOrientation(i);
    }

    private static Orientation certainOrientation(int i) {
        if ((i < 0 || i > 10) && (i < 350 || i > 359)) {
            if (i < 80 || i > 100) {
                if (i < 170 || i > 190) {
                    if (i < 260 || i > 280) {
                        return null;
                    }
                    return Orientation.LANDSCAPE_REVERSE;
                }
                return Orientation.PORTRAIT_REVERSE;
            }
            return Orientation.LANDSCAPE;
        }
        return Orientation.PORTRAIT;
    }

    private static Orientation[] probablyOrientation(int i) {
        return (i <= 0 || i >= 90) ? (i <= 90 || i >= 180) ? (i <= 180 || i >= 270) ? new Orientation[]{Orientation.LANDSCAPE_REVERSE, Orientation.PORTRAIT} : new Orientation[]{Orientation.PORTRAIT_REVERSE, Orientation.LANDSCAPE_REVERSE} : new Orientation[]{Orientation.LANDSCAPE, Orientation.PORTRAIT} : new Orientation[]{Orientation.PORTRAIT, Orientation.LANDSCAPE};
    }

    private static Orientation nearOrientation(int i) {
        if ((i < 0 || i > 45) && (i < 315 || i >= 360)) {
            if (i <= 45 || i >= 135) {
                if (i < 135 || i > 225) {
                    if (i > 225 && i < 315) {
                        return Orientation.LANDSCAPE_REVERSE;
                    }
                    return Orientation.PORTRAIT;
                }
                return Orientation.PORTRAIT_REVERSE;
            }
            return Orientation.LANDSCAPE;
        }
        return Orientation.PORTRAIT;
    }
}
