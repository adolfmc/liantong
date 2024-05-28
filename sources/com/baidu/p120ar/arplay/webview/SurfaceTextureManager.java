package com.baidu.p120ar.arplay.webview;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.webview.SurfaceTextureManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SurfaceTextureManager {
    private static volatile SurfaceTextureManager mInstance;
    private List<SurfaceTextureHolder> mHolderList = new ArrayList();

    private SurfaceTextureManager() {
    }

    public static SurfaceTextureManager getInstance() {
        if (mInstance == null) {
            synchronized (SurfaceTextureManager.class) {
                if (mInstance == null) {
                    mInstance = new SurfaceTextureManager();
                }
            }
        }
        return mInstance;
    }

    public SurfaceTextureHolder generateSurfaceTextureHolder(int i, int i2, int i3) {
        List<SurfaceTextureHolder> list = this.mHolderList;
        if (list != null) {
            for (SurfaceTextureHolder surfaceTextureHolder : list) {
                if (surfaceTextureHolder != null && surfaceTextureHolder.mTextureId == i) {
                    surfaceTextureHolder.updateSurfaceSize(i2, i3);
                    return surfaceTextureHolder;
                }
            }
        }
        SurfaceTextureHolder surfaceTextureHolder2 = new SurfaceTextureHolder();
        surfaceTextureHolder2.createSurface(i, i2, i3);
        List<SurfaceTextureHolder> list2 = this.mHolderList;
        if (list2 != null) {
            list2.add(surfaceTextureHolder2);
        }
        return surfaceTextureHolder2;
    }

    public SurfaceTextureHolder getWebViewTextureHolder(int i) {
        List<SurfaceTextureHolder> list = this.mHolderList;
        if (list != null) {
            for (SurfaceTextureHolder surfaceTextureHolder : list) {
                if (surfaceTextureHolder != null && surfaceTextureHolder.mTextureId == i) {
                    return surfaceTextureHolder;
                }
            }
            return null;
        }
        return null;
    }

    public void update() {
        List<SurfaceTextureHolder> list = this.mHolderList;
        if (list != null) {
            for (SurfaceTextureHolder surfaceTextureHolder : list) {
                if (surfaceTextureHolder != null) {
                    surfaceTextureHolder.update();
                }
            }
        }
    }

    public void update(int i) {
        List<SurfaceTextureHolder> list = this.mHolderList;
        if (list != null) {
            for (SurfaceTextureHolder surfaceTextureHolder : list) {
                if (surfaceTextureHolder != null && surfaceTextureHolder.mTextureId == i) {
                    surfaceTextureHolder.update();
                }
            }
        }
    }

    public void release() {
        List<SurfaceTextureHolder> list = this.mHolderList;
        if (list != null) {
            for (SurfaceTextureHolder surfaceTextureHolder : list) {
                if (surfaceTextureHolder != null) {
                    surfaceTextureHolder.release();
                }
            }
            this.mHolderList.clear();
        }
        if (mInstance != null) {
            mInstance = null;
        }
    }
}
