package com.baidu.platform.comjni.map.basemap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Surface;
import com.baidu.platform.comapi.map.C3003af;
import com.baidu.platform.comjni.NativeComponent;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NABaseMap extends NativeComponent {

    /* renamed from: b */
    private long f8084b;

    /* renamed from: a */
    private ThreadPoolExecutor f8083a = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());

    /* renamed from: c */
    private volatile boolean f8085c = false;

    /* renamed from: d */
    private final ReadWriteLock f8086d = new ReentrantReadWriteLock(true);

    /* renamed from: e */
    private final Set<Long> f8087e = new CopyOnWriteArraySet();

    /* renamed from: a */
    private void m17666a() {
        try {
            if (this.f8083a != null) {
                if (this.f8083a.getQueue() != null) {
                    this.f8083a.getQueue().clear();
                }
                this.f8083a.shutdown();
                this.f8083a.awaitTermination(100L, TimeUnit.MILLISECONDS);
                this.f8083a.shutdownNow();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m17665a(long j) {
        return this.f8087e.contains(Long.valueOf(j)) && j != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public boolean m17656b() {
        return (this.f8083a.isShutdown() || this.f8083a.isTerminated()) ? false : true;
    }

    private native boolean nativeAddBmLayerBelow(long j, long j2, long j3, int i, int i2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeAddItemData(long j, Bundle bundle, boolean z);

    private native long nativeAddLayer(long j, int i, int i2, String str);

    private native void nativeAddPopupData(long j, Bundle bundle);

    private native void nativeAddRtPopData(long j, Bundle bundle);

    private native void nativeAddStreetCustomMarker(long j, Bundle bundle, Bitmap bitmap);

    private native void nativeAttachDC(long j, long j2);

    private native boolean nativeBeginLocationLayerAnimation(long j);

    private native boolean nativeCleanCache(long j, int i);

    private native void nativeClearHeatMapLayerCache(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeClearLayer(long j, long j2);

    private native void nativeClearLocationLayerData(long j, Bundle bundle);

    private native void nativeClearMistmapLayer(long j);

    private native void nativeClearUniversalLayer(long j);

    private native boolean nativeCloseCache(long j);

    private native void nativeCloseParticleEffect(long j, String str);

    private native long nativeCreate();

    private native long nativeCreateDuplicate(long j);

    private native int nativeDraw(long j);

    private native void nativeEnablePOIAnimation(long j, boolean z);

    private native void nativeEntrySearchTopic(long j, int i, String str, String str2);

    private native void nativeExitSearchTopic(long j);

    private native void nativeFocusTrafficUGCLabel(long j);

    private native String nativeGeoPt3ToScrPoint(long j, int i, int i2, int i3);

    private native String nativeGeoPtToScrPoint(long j, int i, int i2);

    private static native boolean nativeGet3DModelEnable(long j);

    private native float nativeGetAdapterZoomUnitsEx(long j);

    private native int nativeGetCacheSize(long j, int i);

    private native String nativeGetCityInfoByID(long j, int i);

    private static native boolean nativeGetDEMEnable(long j);

    private static native boolean nativeGetDrawHouseHeightEnable(long j);

    private native Bundle nativeGetDrawingMapStatus(long j);

    private native float nativeGetFZoomToBoundF(long j, Bundle bundle, Bundle bundle2);

    private native String nativeGetFocusedBaseIndoorMapInfo(long j);

    private native int nativeGetFontSizeLevel(long j);

    private static native long nativeGetLayerIDByTag(long j, String str);

    private native int nativeGetLayerPos(long j, long j2);

    private native boolean nativeGetMapBarData(long j, Bundle bundle);

    private native int nativeGetMapLanguage(long j);

    private native int nativeGetMapRenderType(long j);

    private native int nativeGetMapScene(long j);

    private native Bundle nativeGetMapStatus(long j, boolean z);

    private static native Bundle nativeGetMapStatusLimits(long j);

    private native boolean nativeGetMapStatusLimitsLevel(long j, int[] iArr);

    private native int nativeGetMapTheme(long j);

    private native String nativeGetNearlyObjID(long j, long j2, int i, int i2, int i3);

    private static native void nativeGetProjectionMatrix(long j, float[] fArr);

    private native String nativeGetProjectionPt(long j, String str);

    private native int nativeGetScaleLevel(long j, int i, int i2);

    private static native int nativeGetSkyboxStyle(long j);

    private native int nativeGetVMPMapCityInfo(long j, Bundle bundle);

    private static native void nativeGetViewMatrix(long j, float[] fArr);

    private native float nativeGetZoomToBound(long j, Bundle bundle, int i, int i2);

    private native float nativeGetZoomToBoundF(long j, Bundle bundle);

    private native boolean nativeImportMapTheme(long j, int i);

    private native boolean nativeInit(long j, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2);

    private native boolean nativeInitCustomStyle(long j, String str, String str2);

    private native void nativeInitHeatMapData(long j, long j2, Bundle bundle);

    private native int nativeInitLayerCallback(long j);

    private native boolean nativeInitWithBundle(long j, Bundle bundle, boolean z);

    private native long nativeInsertLayerAt(long j, int i, int i2, int i3, String str);

    private native boolean nativeIsAnimationRunning(long j);

    private native boolean nativeIsBaseIndoorMapMode(long j);

    private native boolean nativeIsEnableIndoor3D(long j);

    private native boolean nativeIsNaviMode(long j);

    private native boolean nativeIsPointInFocusBarBorder(long j, double d, double d2, double d3);

    private native boolean nativeIsPointInFocusIDRBorder(long j, double d, double d2);

    private native boolean nativeIsStreetArrowShown(long j);

    private native boolean nativeIsStreetCustomMarkerShown(long j);

    private native boolean nativeIsStreetPOIMarkerShown(long j);

    private native boolean nativeIsStreetRoadClickable(long j);

    private native boolean nativeLayersIsShow(long j, long j2);

    private native boolean nativeMoveLayerBelowTo(long j, long j2, int i);

    private native void nativeMoveToScrPoint(long j, int i, int i2);

    private native void nativeNewSetMapStatus(long j, Bundle bundle);

    private native void nativeOnBackground(long j);

    private native void nativeOnForeground(long j);

    private native String nativeOnHotcityGet(long j);

    private native void nativeOnPause(long j);

    private native boolean nativeOnRecordAdd(long j, int i);

    private native String nativeOnRecordGetAll(long j);

    private native String nativeOnRecordGetAt(long j, int i);

    private native boolean nativeOnRecordImport(long j, boolean z, boolean z2);

    private native boolean nativeOnRecordReload(long j, int i, boolean z);

    private native boolean nativeOnRecordRemove(long j, int i, boolean z);

    private native boolean nativeOnRecordStart(long j, int i, boolean z, int i2);

    private native boolean nativeOnRecordSuspend(long j, int i, boolean z, int i2);

    private native void nativeOnResume(long j);

    private native String nativeOnSchcityGet(long j, String str);

    private native boolean nativeOnUsrcityMsgInterval(long j, int i);

    private native int nativeOnWifiRecordAdd(long j, int i);

    private native boolean nativePerformAction(long j, String str);

    private native int nativeQueryInterface(long j);

    private native void nativeRecycleMemory(long j, int i);

    private native int nativeRelease(long j);

    private native boolean nativeRemoveBmLayer(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeRemoveItemData(long j, Bundle bundle);

    /* JADX INFO: Access modifiers changed from: private */
    public native int nativeRemoveLayer(long j, long j2);

    private native void nativeRemoveStreetAllCustomMarker(long j);

    private native void nativeRemoveStreetCustomMaker(long j, String str);

    private static native void nativeRenderClearShaderCache(String str);

    private static native void nativeRenderInit(long j, int i, int i2, Surface surface, int i3);

    private native void nativeRenderResize(long j, int i, int i2);

    private native void nativeResetImageRes(long j);

    private native boolean nativeResumeCache(long j);

    private native boolean nativeSaveCache(long j);

    private native void nativeSaveScreenToLocal(long j, String str, String str2);

    private native String nativeScrPtToGeoPoint(long j, int i, int i2);

    private static native void nativeSet3DModelEnable(long j, boolean z);

    private native void nativeSetAllStreetCustomMarkerVisibility(long j, boolean z);

    private native void nativeSetCustomStyleEnable(long j, boolean z);

    private native void nativeSetCustomVMPDataRoot(long j, String str);

    private static native void nativeSetDEMEnable(long j, boolean z);

    private native void nativeSetDpiScale(long j, float f);

    private static native void nativeSetDrawHouseHeightEnable(long j, boolean z);

    private native void nativeSetEnableIndoor3D(long j, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native String nativeSetFocus(long j, long j2, long j3, boolean z, Bundle bundle);

    private native void nativeSetFontSizeLevel(long j, int i);

    private native void nativeSetHeatMapFrameAnimationIndex(long j, long j2, int i);

    private native boolean nativeSetItsPreTime(long j, int i, int i2, int i3);

    private native boolean nativeSetLayerSceneMode(long j, long j2, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSetLayersClickable(long j, long j2, boolean z);

    private native void nativeSetLocationLayerData(long j, Bundle bundle);

    private native int nativeSetMapControlMode(long j, int i);

    private native void nativeSetMapLanguage(long j, int i);

    private native boolean nativeSetMapScene(long j, int i);

    private native void nativeSetMapStatus(long j, Bundle bundle);

    private static native void nativeSetMapStatusLimits(long j, Bundle bundle);

    private native boolean nativeSetMapStatusLimitsLevel(long j, int i, int i2);

    private native boolean nativeSetMapTheme(long j, int i, Bundle bundle);

    private native boolean nativeSetMapThemeScene(long j, int i, int i2, Bundle bundle);

    private static native void nativeSetMaxAndMinZoomLevel(long j, Bundle bundle);

    private native void nativeSetRecommendPOIScene(long j, int i);

    private static native void nativeSetSkyboxStyle(long j, int i);

    private native void nativeSetStreetArrowShow(long j, boolean z);

    private native void nativeSetStreetMarkerClickable(long j, String str, boolean z);

    private native void nativeSetStreetRoadClickable(long j, boolean z);

    private native void nativeSetStyleMode(long j, int i);

    private native void nativeSetTargetStreetCustomMarkerVisibility(long j, boolean z, String str);

    private native boolean nativeSetTestSwitch(long j, boolean z);

    private native void nativeSetTrafficUGCData(long j, String str);

    private native void nativeSetUniversalFilter(long j, String str);

    private native void nativeSetUseCustomVMP(long j, boolean z);

    private native void nativeShowBaseIndoorMap(long j, boolean z);

    private native void nativeShowFootMarkGrid(long j, boolean z, String str);

    private native void nativeShowHotMap(long j, boolean z, int i);

    private native void nativeShowHotMapWithUid(long j, boolean z, int i, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeShowLayers(long j, long j2, boolean z);

    private native void nativeShowMistMap(long j, boolean z, String str);

    private native boolean nativeShowParticleEffect(long j, int i);

    private native boolean nativeShowParticleEffectByName(long j, String str, boolean z);

    private native boolean nativeShowParticleEffectByType(long j, int i);

    private native void nativeShowSatelliteMap(long j, boolean z);

    private native void nativeShowStreetPOIMarker(long j, boolean z);

    private native void nativeShowStreetRoadMap(long j, boolean z);

    private native void nativeShowTrafficMap(long j, boolean z);

    private native void nativeShowTrafficUGCMap(long j, boolean z);

    private native void nativeShowUniversalLayer(long j, Bundle bundle);

    private native void nativeStartHeatMapFrameAnimation(long j, long j2);

    private native void nativeStartIndoorAnimation(long j);

    private native void nativeStopHeatMapFrameAnimation(long j, long j2);

    private native void nativeSurfaceDestroyed(long j, Surface surface);

    private native boolean nativeSwitchBaseIndoorMapFloor(long j, String str, String str2);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeSwitchLayer(long j, long j2, long j3);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeSyncClearLayer(long j, long j2);

    private native void nativeUnFocusTrafficUGCLabel(long j);

    private native void nativeUpdateBaseLayers(long j);

    private native void nativeUpdateDrawFPS(long j);

    private native void nativeUpdateFootMarkGrid(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeUpdateLayers(long j, long j2);

    private native String nativeworldPointToScreenPoint(long j, float f, float f2, float f3);

    public static void renderClearShaderCache(String str) {
        nativeRenderClearShaderCache(str);
    }

    public boolean addBmLayerBelow(long j, long j2, int i, int i2) {
        return nativeAddBmLayerBelow(this.f8084b, j, j2, i, i2);
    }

    public void addItemData(Bundle bundle, boolean z) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3113p(this, bundle, z));
        }
    }

    public long addLayer(int i, int i2, String str) {
        long nativeAddLayer = nativeAddLayer(this.f8084b, i, i2, str);
        this.f8087e.remove(Long.valueOf(nativeAddLayer));
        return nativeAddLayer;
    }

    public void addOneOverlayItem(Bundle bundle) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3101d(this, bundle));
        }
    }

    public void addOverlayItems(Bundle[] bundleArr, int i) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3102e(this, bundleArr, i));
        }
    }

    public void addPopupData(Bundle bundle) {
        nativeAddPopupData(this.f8084b, bundle);
    }

    public void addRtPopData(Bundle bundle) {
        nativeAddRtPopData(this.f8084b, bundle);
    }

    public void addStreetCustomMarker(Bundle bundle, Bitmap bitmap) {
        long j = this.f8084b;
        if (j != 0) {
            nativeAddStreetCustomMarker(j, bundle, bitmap);
        }
    }

    public void beginLocationLayerAnimation() {
        nativeBeginLocationLayerAnimation(this.f8084b);
    }

    public boolean cleanCache(int i) {
        return nativeCleanCache(this.f8084b, i);
    }

    public void clearHeatMapLayerCache(long j) {
        long j2 = this.f8084b;
        if (j2 != 0) {
            nativeClearHeatMapLayerCache(j2, j);
        }
    }

    public void clearLayer(long j) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3111n(this, j));
        }
    }

    public void clearLocationLayerData(Bundle bundle) {
        nativeClearLocationLayerData(this.f8084b, bundle);
    }

    public void clearMistmapLayer() {
        nativeClearMistmapLayer(this.f8084b);
    }

    public void clearSDKLayer(long j) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3110m(this, j));
        }
    }

    public void clearUniversalLayer() {
        nativeClearUniversalLayer(this.f8084b);
    }

    public boolean closeCache() {
        return nativeCloseCache(this.f8084b);
    }

    public void closeParticleEffect(String str) {
        nativeCloseParticleEffect(this.f8084b, str);
    }

    @Override // com.baidu.platform.comjni.NativeComponent
    public long create() {
        this.f8084b = nativeCreate();
        nativeInitLayerCallback(this.f8084b);
        return this.f8084b;
    }

    public long createByDuplicate(long j) {
        this.f8084b = nativeCreateDuplicate(j);
        long j2 = this.f8084b;
        if (j2 != 0) {
            nativeInitLayerCallback(j2);
        }
        return this.f8084b;
    }

    public long createDuplicate() {
        return nativeCreateDuplicate(this.f8084b);
    }

    @Override // com.baidu.platform.comjni.NativeComponent
    public int dispose() {
        if (this.f8084b != 0) {
            this.f8085c = true;
            m17666a();
            int nativeRelease = nativeRelease(this.f8084b);
            this.f8084b = 0L;
            return nativeRelease;
        }
        return 0;
    }

    public int draw() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeDraw(j);
        }
        return 0;
    }

    public void enablePOIAnimation(boolean z) {
        long j = this.f8084b;
        if (j != 0) {
            nativeEnablePOIAnimation(j, z);
        }
    }

    public void entrySearchTopic(int i, String str, String str2) {
        long j = this.f8084b;
        if (j != 0) {
            nativeEntrySearchTopic(j, i, str, str2);
        }
    }

    public void exitSearchTopic() {
        long j = this.f8084b;
        if (j != 0) {
            nativeExitSearchTopic(j);
        }
    }

    public void focusTrafficUGCLabel() {
        nativeFocusTrafficUGCLabel(this.f8084b);
    }

    public String geoPt3ToScrPoint(int i, int i2, int i3) {
        return nativeGeoPt3ToScrPoint(this.f8084b, i, i2, i3);
    }

    public String geoPtToScrPoint(int i, int i2) {
        return nativeGeoPtToScrPoint(this.f8084b, i, i2);
    }

    public boolean get3DModelEnable() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGet3DModelEnable(j);
        }
        return false;
    }

    public float getAdapterZoomUnitsEx() {
        return nativeGetAdapterZoomUnitsEx(this.f8084b);
    }

    public int getCacheSize(int i) {
        return nativeGetCacheSize(this.f8084b, i);
    }

    public String getCityInfoByID(int i) {
        return nativeGetCityInfoByID(this.f8084b, i);
    }

    public boolean getDEMEnable() {
        long j = this.f8084b;
        if (j != 0) {
            nativeGetDEMEnable(j);
            return false;
        }
        return false;
    }

    public boolean getDrawHouseHeightEnable() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetDrawHouseHeightEnable(j);
        }
        return false;
    }

    public Bundle getDrawingMapStatus() {
        return nativeGetDrawingMapStatus(this.f8084b);
    }

    public float getFZoomToBoundF(Bundle bundle, Bundle bundle2) {
        return nativeGetFZoomToBoundF(this.f8084b, bundle, bundle2);
    }

    public String getFocusedBaseIndoorMapInfo() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetFocusedBaseIndoorMapInfo(j);
        }
        return null;
    }

    public int getFontSizeLevel() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetFontSizeLevel(j);
        }
        return 1;
    }

    public long getLayerIDByTag(String str) {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetLayerIDByTag(j, str);
        }
        return 0L;
    }

    public boolean getMapBarData(Bundle bundle) {
        return nativeGetMapBarData(this.f8084b, bundle);
    }

    public int getMapLanguage() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetMapLanguage(j);
        }
        return 0;
    }

    public int getMapRenderType() {
        return nativeGetMapRenderType(this.f8084b);
    }

    public int getMapScene() {
        return nativeGetMapScene(this.f8084b);
    }

    public Bundle getMapStatus(boolean z) {
        return nativeGetMapStatus(this.f8084b, z);
    }

    public Bundle getMapStatusLimits() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetMapStatusLimits(j);
        }
        return null;
    }

    public boolean getMapStatusLimitsLevel(int[] iArr) {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetMapStatusLimitsLevel(j, iArr);
        }
        return false;
    }

    public int getMapTheme() {
        return nativeGetMapTheme(this.f8084b);
    }

    public long getNativeMapPointer() {
        return this.f8084b;
    }

    public String getNearlyObjID(long j, int i, int i2, int i3) {
        boolean z = false;
        try {
            z = this.f8086d.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (!z) {
                if (z) {
                    this.f8086d.readLock().unlock();
                }
                return "";
            } else if (m17665a(j)) {
                if (z) {
                    this.f8086d.readLock().unlock();
                }
                return "";
            } else {
                String nativeGetNearlyObjID = nativeGetNearlyObjID(this.f8084b, j, i, i2, i3);
                if (z) {
                    this.f8086d.readLock().unlock();
                }
                return nativeGetNearlyObjID;
            }
        } catch (Exception unused) {
            if (z) {
                this.f8086d.readLock().unlock();
            }
            return "";
        } catch (Throwable th) {
            if (z) {
                this.f8086d.readLock().unlock();
            }
            throw th;
        }
    }

    public void getProjectMatrix(float[] fArr) {
        long j = this.f8084b;
        if (j != 0) {
            nativeGetProjectionMatrix(j, fArr);
        }
    }

    public String getProjectionPt(String str) {
        return nativeGetProjectionPt(this.f8084b, str);
    }

    public int getScaleLevel(int i, int i2) {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetScaleLevel(j, i, i2);
        }
        return -1;
    }

    public int getSkyboxStyle() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeGetSkyboxStyle(j);
        }
        return 0;
    }

    public int getVMPMapCityInfo(Bundle bundle) {
        return nativeGetVMPMapCityInfo(this.f8084b, bundle);
    }

    public void getViewMatrix(float[] fArr) {
        long j = this.f8084b;
        if (j != 0) {
            nativeGetViewMatrix(j, fArr);
        }
    }

    public float getZoomToBound(long j, Bundle bundle, int i, int i2) {
        return nativeGetZoomToBound(j, bundle, i, i2);
    }

    public float getZoomToBound(Bundle bundle, int i, int i2) {
        return nativeGetZoomToBound(this.f8084b, bundle, i, i2);
    }

    public float getZoomToBoundF(Bundle bundle) {
        return nativeGetZoomToBoundF(this.f8084b, bundle);
    }

    public boolean importMapTheme(int i) {
        return nativeImportMapTheme(this.f8084b, i);
    }

    public boolean init(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2) {
        long j = this.f8084b;
        return j != 0 && nativeInit(j, str, str2, str3, str4, str5, str6, str7, i, i2, i3, i4, i5, i6, i7, z, z2);
    }

    public boolean initCustomStyle(String str, String str2) {
        long j = this.f8084b;
        if (j != 0) {
            return nativeInitCustomStyle(j, str, str2);
        }
        return false;
    }

    public void initHeatMapData(long j, Bundle bundle) {
        long j2 = this.f8084b;
        if (j2 != 0) {
            nativeInitHeatMapData(j2, j, bundle);
        }
    }

    public boolean initWithOptions(Bundle bundle, boolean z) {
        long j = this.f8084b;
        return j != 0 && nativeInitWithBundle(j, bundle, z);
    }

    public boolean isAnimationRunning() {
        return nativeIsAnimationRunning(this.f8084b);
    }

    public boolean isBaseIndoorMapMode() {
        long j = this.f8084b;
        return j != 0 && nativeIsBaseIndoorMapMode(j);
    }

    public boolean isEnableIndoor3D() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeIsEnableIndoor3D(j);
        }
        return true;
    }

    public boolean isNaviMode() {
        return nativeIsNaviMode(this.f8084b);
    }

    public boolean isPointInFocusBarBorder(double d, double d2, double d3) {
        long j = this.f8084b;
        return j != 0 && nativeIsPointInFocusBarBorder(j, d, d2, d3);
    }

    public boolean isPointInFocusIDRBorder(double d, double d2) {
        long j = this.f8084b;
        return j != 0 && nativeIsPointInFocusIDRBorder(j, d, d2);
    }

    public boolean isStreetArrowShown() {
        return nativeIsStreetArrowShown(this.f8084b);
    }

    public boolean isStreetCustomMarkerShown() {
        return nativeIsStreetCustomMarkerShown(this.f8084b);
    }

    public boolean isStreetPOIMarkerShown() {
        long j = this.f8084b;
        return j != 0 && nativeIsStreetPOIMarkerShown(j);
    }

    public boolean isStreetRoadClickable() {
        return nativeIsStreetRoadClickable(this.f8084b);
    }

    public boolean layersIsShow(long j) {
        boolean z;
        boolean z2 = false;
        try {
            z = this.f8086d.readLock().tryLock(2000L, TimeUnit.MILLISECONDS);
            if (!z) {
                if (z) {
                    this.f8086d.readLock().unlock();
                }
                return false;
            }
            try {
                if (m17665a(j)) {
                    if (z) {
                        this.f8086d.readLock().unlock();
                    }
                    return false;
                }
                boolean nativeLayersIsShow = nativeLayersIsShow(this.f8084b, j);
                if (z) {
                    this.f8086d.readLock().unlock();
                }
                return nativeLayersIsShow;
            } catch (Exception unused) {
                if (z) {
                    this.f8086d.readLock().unlock();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                z2 = z;
                if (z2) {
                    this.f8086d.readLock().unlock();
                }
                throw th;
            }
        } catch (Exception unused2) {
            z = false;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public boolean moveLayerBelowTo(long j, int i) {
        long j2 = this.f8084b;
        if (j2 != 0) {
            return nativeMoveLayerBelowTo(j2, j, i);
        }
        return false;
    }

    public void moveToScrPoint(int i, int i2) {
        nativeMoveToScrPoint(this.f8084b, i, i2);
    }

    public native void nativeAddOneOverlayItem(long j, Bundle bundle);

    public native void nativeAddOverlayItems(long j, Bundle[] bundleArr, int i);

    public native boolean nativeAddTileOverlay(long j, Bundle bundle);

    public native boolean nativeCleanSDKTileDataCache(long j, long j2);

    public native void nativeRemoveOneOverlayItem(long j, Bundle bundle);

    public native void nativeUpdateOneOverlayItem(long j, Bundle bundle);

    public native boolean nativeUpdateSDKTile(long j, Bundle bundle);

    public void onBackground() {
        long j = this.f8084b;
        if (j != 0) {
            nativeOnBackground(j);
        }
    }

    public void onForeground() {
        long j = this.f8084b;
        if (j != 0) {
            nativeOnForeground(j);
        }
    }

    public String onHotcityGet() {
        return nativeOnHotcityGet(this.f8084b);
    }

    public void onPause() {
        long j = this.f8084b;
        if (j != 0) {
            nativeOnPause(j);
        }
    }

    public boolean onRecordAdd(int i) {
        return nativeOnRecordAdd(this.f8084b, i);
    }

    public String onRecordGetAll() {
        return nativeOnRecordGetAll(this.f8084b);
    }

    public String onRecordGetAt(int i) {
        return nativeOnRecordGetAt(this.f8084b, i);
    }

    public boolean onRecordImport(boolean z, boolean z2) {
        return nativeOnRecordImport(this.f8084b, z, z2);
    }

    public boolean onRecordReload(int i, boolean z) {
        return nativeOnRecordReload(this.f8084b, i, z);
    }

    public boolean onRecordRemove(int i, boolean z) {
        return nativeOnRecordRemove(this.f8084b, i, z);
    }

    public boolean onRecordStart(int i, boolean z, int i2) {
        return nativeOnRecordStart(this.f8084b, i, z, i2);
    }

    public boolean onRecordSuspend(int i, boolean z, int i2) {
        return nativeOnRecordSuspend(this.f8084b, i, z, i2);
    }

    public void onResume() {
        long j = this.f8084b;
        if (j != 0) {
            nativeOnResume(j);
        }
    }

    public String onSchcityGet(String str) {
        return nativeOnSchcityGet(this.f8084b, str);
    }

    public boolean onUsrcityMsgInterval(int i) {
        return nativeOnUsrcityMsgInterval(this.f8084b, i);
    }

    public int onWifiRecordAdd(int i) {
        return nativeOnWifiRecordAdd(this.f8084b, i);
    }

    public boolean performAction(String str) {
        return nativePerformAction(this.f8084b, str);
    }

    public void recycleMemory(int i) {
        long j = this.f8084b;
        if (j != 0) {
            nativeRecycleMemory(j, i);
        }
    }

    public void removeBmLayer(long j) {
        nativeRemoveBmLayer(this.f8084b, j);
    }

    public boolean removeItemData(Bundle bundle) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3100c(this, bundle));
            return true;
        }
        return false;
    }

    public void removeLayer(long j) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3108k(this, j));
        }
    }

    public void removeOneOverlayItem(Bundle bundle) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3104g(this, bundle));
        }
    }

    public void removeOneOverlayItems(Bundle[] bundleArr) {
        if (bundleArr == null || !m17656b()) {
            return;
        }
        this.f8083a.submit(new RunnableC3105h(this, bundleArr));
    }

    public void removeStreetAllCustomMarker() {
        nativeRemoveStreetAllCustomMarker(this.f8084b);
    }

    public void removeStreetCustomMaker(String str) {
        nativeRemoveStreetCustomMaker(this.f8084b, str);
    }

    @Deprecated
    public void renderDone() {
    }

    public void renderInit(int i, int i2, Surface surface, int i3) {
        long j = this.f8084b;
        if (j != 0) {
            nativeRenderInit(j, i, i2, surface, i3);
        }
    }

    @Deprecated
    public int renderRender() {
        long j = this.f8084b;
        if (j != 0) {
            return nativeDraw(j);
        }
        return 0;
    }

    public void renderResize(int i, int i2) {
        long j = this.f8084b;
        if (j != 0) {
            nativeRenderResize(j, i, i2);
        }
    }

    public void resetImageRes() {
        long j = this.f8084b;
        if (j != 0) {
            nativeResetImageRes(j);
        }
    }

    public boolean resumeCache() {
        return nativeResumeCache(this.f8084b);
    }

    public boolean saveCache() {
        try {
            return nativeSaveCache(this.f8084b);
        } catch (Throwable unused) {
            return false;
        }
    }

    public void saveScreenToLocal(String str, String str2) {
        nativeSaveScreenToLocal(this.f8084b, str, str2);
    }

    public String scrPtToGeoPoint(int i, int i2) {
        return nativeScrPtToGeoPoint(this.f8084b, i, i2);
    }

    public void set3DModelEnable(boolean z) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSet3DModelEnable(j, z);
        }
    }

    public void setAllStreetCustomMarkerVisibility(boolean z) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetAllStreetCustomMarkerVisibility(j, z);
        }
    }

    public void setCallback(C3003af c3003af) {
        BaseMapCallback.setMapCallback(this.f8084b, c3003af);
    }

    public void setCustomStyleEnable(boolean z) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetCustomStyleEnable(j, z);
        }
    }

    public void setDEMEnable(boolean z) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetDEMEnable(j, z);
        }
    }

    public void setDpiScale(float f) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetDpiScale(j, f);
        }
    }

    public void setDrawHouseHeightEnable(boolean z) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetDrawHouseHeightEnable(j, z);
        }
    }

    public void setEnableIndoor3D(boolean z) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetEnableIndoor3D(j, z);
        }
    }

    public void setFocus(long j, long j2, boolean z, Bundle bundle) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3112o(this, j, j2, z, bundle));
        }
    }

    public void setFontSizeLevel(int i) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetFontSizeLevel(j, i);
        }
    }

    public void setHeatMapFrameAnimationIndex(long j, int i) {
        long j2 = this.f8084b;
        if (j2 != 0) {
            nativeSetHeatMapFrameAnimationIndex(j2, j, i);
        }
    }

    public boolean setItsPreTime(int i, int i2, int i3) {
        return nativeSetItsPreTime(this.f8084b, i, i2, i3);
    }

    public boolean setLayerSceneMode(long j, int i) {
        return nativeSetLayerSceneMode(this.f8084b, j, i);
    }

    public void setLayersClickable(long j, boolean z) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3106i(this, j, z));
        }
    }

    public void setLocationLayerData(Bundle bundle) {
        nativeSetLocationLayerData(this.f8084b, bundle);
    }

    public int setMapControlMode(int i) {
        return nativeSetMapControlMode(this.f8084b, i);
    }

    public void setMapLanguage(int i) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetMapLanguage(j, i);
        }
    }

    public void setMapScene(int i) {
        nativeSetMapScene(this.f8084b, i);
    }

    public void setMapStatus(Bundle bundle) {
        nativeSetMapStatus(this.f8084b, bundle);
    }

    public void setMapStatusLimits(Bundle bundle) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetMapStatusLimits(j, bundle);
        }
    }

    public boolean setMapStatusLimitsLevel(int i, int i2) {
        long j = this.f8084b;
        if (j != 0) {
            return nativeSetMapStatusLimitsLevel(j, i, i2);
        }
        return false;
    }

    public boolean setMapTheme(int i, Bundle bundle) {
        return nativeSetMapTheme(this.f8084b, i, bundle);
    }

    public boolean setMapThemeScene(int i, int i2, Bundle bundle) {
        return nativeSetMapThemeScene(this.f8084b, i, i2, bundle);
    }

    public void setMaxAndMinZoomLevel(Bundle bundle) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetMaxAndMinZoomLevel(j, bundle);
        }
    }

    public void setNewMapStatus(Bundle bundle) {
        nativeNewSetMapStatus(this.f8084b, bundle);
    }

    public void setRecommendPOIScene(int i) {
        nativeSetRecommendPOIScene(this.f8084b, i);
    }

    public void setSkyboxStyle(int i) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetSkyboxStyle(j, i);
        }
    }

    public void setStreetArrowShow(boolean z) {
        nativeSetStreetArrowShow(this.f8084b, z);
    }

    public void setStreetMarkerClickable(String str, boolean z) {
        nativeSetStreetMarkerClickable(this.f8084b, str, z);
    }

    public void setStreetRoadClickable(boolean z) {
        nativeSetStreetRoadClickable(this.f8084b, z);
    }

    public void setStyleMode(int i) {
        nativeSetStyleMode(this.f8084b, i);
    }

    public void setTargetStreetCustomMarkerVisibility(boolean z, String str) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSetTargetStreetCustomMarkerVisibility(j, z, str);
        }
    }

    public boolean setTestSwitch(boolean z) {
        return nativeSetTestSwitch(this.f8084b, z);
    }

    public void setTrafficUGCData(String str) {
        nativeSetTrafficUGCData(this.f8084b, str);
    }

    public void setUniversalFilter(String str) {
        nativeSetUniversalFilter(this.f8084b, str);
    }

    public void showBaseIndoorMap(boolean z) {
        nativeShowBaseIndoorMap(this.f8084b, z);
    }

    public void showFootMarkGrid(boolean z, String str) {
        long j = this.f8084b;
        if (j != 0) {
            nativeShowFootMarkGrid(j, z, str);
        }
    }

    public void showHotMap(boolean z, int i) {
        nativeShowHotMap(this.f8084b, z, i);
    }

    public void showHotMap(boolean z, int i, String str) {
        nativeShowHotMapWithUid(this.f8084b, z, i, str);
    }

    public void showLayers(long j, boolean z) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3099b(this, j, z));
        }
    }

    public void showMistMap(boolean z, String str) {
        nativeShowMistMap(this.f8084b, z, str);
    }

    public boolean showParticleEffect(int i) {
        return nativeShowParticleEffect(this.f8084b, i);
    }

    public boolean showParticleEffectByName(String str, boolean z) {
        return nativeShowParticleEffectByName(this.f8084b, str, z);
    }

    public boolean showParticleEffectByType(int i) {
        return nativeShowParticleEffectByType(this.f8084b, i);
    }

    public void showSatelliteMap(boolean z) {
        nativeShowSatelliteMap(this.f8084b, z);
    }

    public void showStreetPOIMarker(boolean z) {
        long j = this.f8084b;
        if (j != 0) {
            nativeShowStreetPOIMarker(j, z);
        }
    }

    public void showStreetRoadMap(boolean z) {
        nativeShowStreetRoadMap(this.f8084b, z);
    }

    public void showTrafficMap(boolean z) {
        nativeShowTrafficMap(this.f8084b, z);
    }

    public void showTrafficUGCMap(boolean z) {
        nativeShowTrafficUGCMap(this.f8084b, z);
    }

    public void showUniversalLayer(Bundle bundle) {
        nativeShowUniversalLayer(this.f8084b, bundle);
    }

    public void startHeatMapFrameAnimation(long j) {
        long j2 = this.f8084b;
        if (j2 != 0) {
            nativeStartHeatMapFrameAnimation(j2, j);
        }
    }

    public void startIndoorAnimation() {
        nativeStartIndoorAnimation(this.f8084b);
    }

    public void stopHeatMapFrameAnimation(long j) {
        long j2 = this.f8084b;
        if (j2 != 0) {
            nativeStopHeatMapFrameAnimation(j2, j);
        }
    }

    public void surfaceDestroyed(Surface surface) {
        long j = this.f8084b;
        if (j != 0) {
            nativeSurfaceDestroyed(j, surface);
        }
    }

    public boolean switchBaseIndoorMapFloor(String str, String str2) {
        return nativeSwitchBaseIndoorMapFloor(this.f8084b, str, str2);
    }

    public boolean switchLayer(long j, long j2) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3109l(this, j, j2));
            return true;
        }
        return false;
    }

    public void unFocusTrafficUGCLabel() {
        nativeUnFocusTrafficUGCLabel(this.f8084b);
    }

    public void updateBaseLayers() {
        nativeUpdateBaseLayers(this.f8084b);
    }

    public void updateDrawFPS() {
        long j = this.f8084b;
        if (j != 0) {
            nativeUpdateDrawFPS(j);
        }
    }

    public void updateFootMarkGrid() {
        nativeUpdateFootMarkGrid(this.f8084b);
    }

    public void updateLayers(long j) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3107j(this, j));
        }
    }

    public void updateOneOverlayItem(Bundle bundle) {
        if (m17656b()) {
            this.f8083a.submit(new RunnableC3103f(this, bundle));
        }
    }

    public String worldPointToScreenPoint(float f, float f2, float f3) {
        return nativeworldPointToScreenPoint(this.f8084b, f, f2, f3);
    }
}
