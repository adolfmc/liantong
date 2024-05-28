package com.baidu.platform.comjni.map.basemap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Surface;
import com.baidu.mapsdkplatform.comjni.p146a.p147a.InterfaceC2960a;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AppBaseMap {

    /* renamed from: b */
    private NABaseMap f8079b;

    /* renamed from: a */
    private long f8078a = 0;

    /* renamed from: c */
    private final ReadWriteLock f8080c = new ReentrantReadWriteLock(true);

    public AppBaseMap() {
        this.f8079b = null;
        this.f8079b = new NABaseMap();
    }

    public static void renderClearShaderCache(String str) {
        NABaseMap.renderClearShaderCache(str);
    }

    public void AddItemData(Bundle bundle) {
        AddItemData(bundle, false);
    }

    public void AddItemData(Bundle bundle, boolean z) {
        this.f8079b.addItemData(bundle, z);
    }

    public long AddLayer(int i, int i2, String str) {
        return this.f8079b.addLayer(i, i2, str);
    }

    public void AddPopupData(Bundle bundle) {
        this.f8079b.addPopupData(bundle);
    }

    public void AddRtPopData(Bundle bundle) {
        this.f8079b.addRtPopData(bundle);
    }

    public void AddStreetCustomMarker(Bundle bundle, Bitmap bitmap) {
        if (this.f8078a != 0) {
            this.f8079b.addStreetCustomMarker(bundle, bitmap);
        }
    }

    public void BeginLocationLayerAnimation() {
        this.f8079b.beginLocationLayerAnimation();
    }

    public boolean CleanCache(int i) {
        return this.f8079b.cleanCache(i);
    }

    public void ClearLayer(long j) {
        this.f8079b.clearLayer(j);
    }

    public void ClearLocationLayerData(Bundle bundle) {
        this.f8079b.clearLocationLayerData(bundle);
    }

    public void ClearMistmapLayer() {
        this.f8079b.clearMistmapLayer();
    }

    public void ClearSDKLayer(long j) {
        this.f8079b.clearSDKLayer(j);
    }

    public boolean CloseCache() {
        return this.f8079b.closeCache();
    }

    public boolean Create() {
        try {
            this.f8080c.writeLock().lock();
            this.f8078a = this.f8079b.create();
            return true;
        } finally {
            this.f8080c.writeLock().unlock();
        }
    }

    public boolean CreateByDuplicate(long j) {
        this.f8078a = this.f8079b.createByDuplicate(j);
        return this.f8078a != 0;
    }

    public long CreateDuplicate() {
        return this.f8079b.createDuplicate();
    }

    public int Draw() {
        if (this.f8078a != 0) {
            return this.f8079b.draw();
        }
        return 0;
    }

    public String GeoPtToScrPoint(int i, int i2) {
        return this.f8079b.geoPtToScrPoint(i, i2);
    }

    public float GetAdapterZoomUnitsEx() {
        return this.f8079b.getAdapterZoomUnitsEx();
    }

    public int GetCacheSize(int i) {
        return this.f8079b.getCacheSize(i);
    }

    public String GetCityInfoByID(int i) {
        return this.f8079b.getCityInfoByID(i);
    }

    public Bundle GetDrawingMapStatus() {
        return this.f8079b.getDrawingMapStatus();
    }

    public float GetFZoomToBoundF(Bundle bundle, Bundle bundle2) {
        return this.f8079b.getFZoomToBoundF(bundle, bundle2);
    }

    public String GetFocusedBaseIndoorMapInfo() {
        if (this.f8078a != 0) {
            return this.f8079b.getFocusedBaseIndoorMapInfo();
        }
        return null;
    }

    public long GetId() {
        return this.f8078a;
    }

    public int GetMapRenderType() {
        return this.f8079b.getMapRenderType();
    }

    public Bundle GetMapStatus() {
        return this.f8079b.getMapStatus(true);
    }

    public Bundle GetMapStatus(boolean z) {
        return this.f8079b.getMapStatus(z);
    }

    public String GetNearlyObjID(long j, int i, int i2, int i3) {
        return this.f8079b.getNearlyObjID(j, i, i2, i3);
    }

    public int GetVMPMapCityInfo(Bundle bundle) {
        return this.f8079b.getVMPMapCityInfo(bundle);
    }

    public float GetZoomToBound(Bundle bundle, int i, int i2) {
        return this.f8079b.getZoomToBound(bundle, i, i2);
    }

    public float GetZoomToBoundF(Bundle bundle) {
        return this.f8079b.getZoomToBoundF(bundle);
    }

    @Deprecated
    public boolean Init(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z, boolean z2) {
        return this.f8078a != 0 && this.f8079b.init(str, str2, str3, str4, str5, str6, str7, i, i2, i3, i4, i5, i6, i7, z, z2);
    }

    public boolean IsBaseIndoorMapMode() {
        return this.f8078a != 0 && this.f8079b.isBaseIndoorMapMode();
    }

    public boolean IsPointInFocusBarBorder(double d, double d2, double d3) {
        return this.f8078a != 0 && this.f8079b.isPointInFocusBarBorder(d, d2, d3);
    }

    public boolean IsPointInFocusIDRBorder(double d, double d2) {
        return this.f8078a != 0 && this.f8079b.isPointInFocusIDRBorder(d, d2);
    }

    public boolean IsStreetArrowShown() {
        return this.f8079b.isStreetArrowShown();
    }

    public boolean IsStreetCustomMarkerShown() {
        return this.f8079b.isStreetCustomMarkerShown();
    }

    public boolean IsStreetPOIMarkerShown() {
        return this.f8078a != 0 && this.f8079b.isStreetPOIMarkerShown();
    }

    public boolean IsStreetRoadClickable() {
        return this.f8079b.isStreetRoadClickable();
    }

    public boolean LayersIsShow(long j) {
        return this.f8079b.layersIsShow(j);
    }

    public void MoveToScrPoint(int i, int i2) {
        this.f8079b.moveToScrPoint(i, i2);
    }

    public void OnBackground() {
        try {
            this.f8080c.readLock().lock();
            if (this.f8078a != 0) {
                this.f8079b.onBackground();
            }
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public void OnForeground() {
        try {
            this.f8080c.readLock().lock();
            if (this.f8078a != 0) {
                this.f8079b.onForeground();
            }
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public String OnHotcityGet() {
        return this.f8079b.onHotcityGet();
    }

    public void OnPause() {
        try {
            this.f8080c.readLock().lock();
            if (this.f8078a != 0) {
                this.f8079b.onPause();
            }
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public boolean OnRecordAdd(int i) {
        return this.f8079b.onRecordAdd(i);
    }

    public String OnRecordGetAll() {
        return this.f8079b.onRecordGetAll();
    }

    public String OnRecordGetAt(int i) {
        return this.f8079b.onRecordGetAt(i);
    }

    public boolean OnRecordImport(boolean z, boolean z2) {
        return this.f8079b.onRecordImport(z, z2);
    }

    public boolean OnRecordReload(int i, boolean z) {
        return this.f8079b.onRecordReload(i, z);
    }

    public boolean OnRecordRemove(int i, boolean z) {
        return this.f8079b.onRecordRemove(i, z);
    }

    public boolean OnRecordStart(int i, boolean z, int i2) {
        return this.f8079b.onRecordStart(i, z, i2);
    }

    public boolean OnRecordSuspend(int i, boolean z, int i2) {
        return this.f8079b.onRecordSuspend(i, z, i2);
    }

    public void OnResume() {
        try {
            this.f8080c.readLock().lock();
            if (this.f8078a != 0) {
                this.f8079b.onResume();
            }
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public String OnSchcityGet(String str) {
        return this.f8079b.onSchcityGet(str);
    }

    public boolean OnUsrcityMsgInterval(int i) {
        return this.f8079b.onUsrcityMsgInterval(i);
    }

    public int OnWifiRecordAdd(int i) {
        return this.f8079b.onWifiRecordAdd(i);
    }

    public boolean Release() {
        boolean z;
        try {
            this.f8080c.writeLock().lock();
            if (this.f8078a != 0) {
                BaseMapCallback.release(this.f8078a);
                this.f8079b.dispose();
                this.f8078a = 0L;
                z = true;
            } else {
                z = false;
            }
            return z;
        } finally {
            this.f8080c.writeLock().unlock();
        }
    }

    public void Remo() {
    }

    public boolean RemoveItemData(Bundle bundle) {
        return this.f8079b.removeItemData(bundle);
    }

    public void RemoveLayer(long j) {
        this.f8079b.removeLayer(j);
    }

    public void RemoveStreetAllCustomMarker() {
        this.f8079b.removeStreetAllCustomMarker();
    }

    public void RemoveStreetCustomMaker(String str) {
        this.f8079b.removeStreetCustomMaker(str);
    }

    public void ResetImageRes() {
        if (this.f8078a != 0) {
            this.f8079b.resetImageRes();
        }
    }

    public boolean ResumeCache() {
        return this.f8079b.resumeCache();
    }

    public boolean SaveCache() {
        try {
            return this.f8079b.saveCache();
        } catch (Throwable unused) {
            return false;
        }
    }

    public void SaveScreenToLocal(String str, String str2) {
        this.f8079b.saveScreenToLocal(str, str2);
    }

    public String ScrPtToGeoPoint(int i, int i2) {
        return this.f8079b.scrPtToGeoPoint(i, i2);
    }

    public void SetAllStreetCustomMarkerVisibility(boolean z) {
        if (this.f8078a != 0) {
            this.f8079b.setAllStreetCustomMarkerVisibility(z);
        }
    }

    public boolean SetCallback(InterfaceC3098a interfaceC3098a) {
        if (interfaceC3098a != null) {
            long j = this.f8078a;
            if (j != 0 && BaseMapCallback.setMapCallback(j, interfaceC3098a)) {
                return true;
            }
        }
        return false;
    }

    public void SetFocus(long j, long j2, boolean z, Bundle bundle) {
        this.f8079b.setFocus(j, j2, z, bundle);
    }

    public boolean SetItsPreTime(int i, int i2, int i3) {
        return this.f8079b.setItsPreTime(i, i2, i3);
    }

    public boolean SetLayerSceneMode(long j, int i) {
        return this.f8079b.setLayerSceneMode(j, i);
    }

    public void SetLayersClickable(long j, boolean z) {
        this.f8079b.setLayersClickable(j, z);
    }

    public void SetLocationLayerData(Bundle bundle) {
        this.f8079b.setLocationLayerData(bundle);
    }

    public int SetMapControlMode(int i) {
        return this.f8079b.setMapControlMode(i);
    }

    public void SetMapStatus(Bundle bundle) {
        this.f8079b.setMapStatus(bundle);
    }

    public void SetNewMapStatus(Bundle bundle) {
        this.f8079b.setNewMapStatus(bundle);
    }

    public boolean SetSDKLayerCallback(InterfaceC2960a interfaceC2960a) {
        if (interfaceC2960a != null) {
            long j = this.f8078a;
            if (j != 0 && BaseMapCallback.setMapSDKCallback(j, interfaceC2960a)) {
                return true;
            }
        }
        return false;
    }

    public void SetStreetArrowShow(boolean z) {
        this.f8079b.setStreetArrowShow(z);
    }

    public void SetStreetMarkerClickable(String str, boolean z) {
        this.f8079b.setStreetMarkerClickable(str, z);
    }

    public void SetStreetRoadClickable(boolean z) {
        this.f8079b.setStreetRoadClickable(z);
    }

    public void SetStyleMode(int i) {
        this.f8079b.setStyleMode(i);
    }

    public void SetTargetStreetCustomMarkerVisibility(boolean z, String str) {
        if (this.f8078a != 0) {
            this.f8079b.setTargetStreetCustomMarkerVisibility(z, str);
        }
    }

    public void ShowBaseIndoorMap(boolean z) {
        this.f8079b.showBaseIndoorMap(z);
    }

    public void ShowHotMap(boolean z, int i) {
        this.f8079b.showHotMap(z, i);
    }

    public void ShowHotMap(boolean z, int i, String str) {
        this.f8079b.showHotMap(z, i, str);
    }

    public void ShowLayers(long j, boolean z) {
        if (this.f8078a != 0) {
            this.f8079b.showLayers(j, z);
        }
    }

    public void ShowMistMap(boolean z, String str) {
        this.f8079b.showMistMap(z, str);
    }

    public void ShowSatelliteMap(boolean z) {
        this.f8079b.showSatelliteMap(z);
    }

    public void ShowStreetPOIMarker(boolean z) {
        if (this.f8078a != 0) {
            this.f8079b.showStreetPOIMarker(z);
        }
    }

    public void ShowStreetRoadMap(boolean z) {
        this.f8079b.showStreetRoadMap(z);
    }

    public void ShowTrafficMap(boolean z) {
        this.f8079b.showTrafficMap(z);
    }

    public void StartIndoorAnimation() {
        this.f8079b.startIndoorAnimation();
    }

    public boolean SwitchBaseIndoorMapFloor(String str, String str2) {
        return this.f8079b.switchBaseIndoorMapFloor(str, str2);
    }

    public boolean SwitchLayer(long j, long j2) {
        return this.f8079b.switchLayer(j, j2);
    }

    public void UpdateLayers(long j) {
        this.f8079b.updateLayers(j);
    }

    public boolean addBmLayerBelow(long j, long j2, int i, int i2) {
        return this.f8079b.addBmLayerBelow(j, j2, i, i2);
    }

    public void addOneOverlayItem(Bundle bundle) {
        this.f8079b.addOneOverlayItem(bundle);
    }

    public void addOverlayItems(Bundle[] bundleArr, int i) {
        this.f8079b.addOverlayItems(bundleArr, i);
    }

    public boolean addSDKTileData(Bundle bundle) {
        return this.f8079b.nativeAddTileOverlay(this.f8078a, bundle);
    }

    public boolean cleanSDKTileDataCache(long j) {
        return this.f8079b.nativeCleanSDKTileDataCache(this.f8078a, j);
    }

    public void clearHeatMapLayerCache(long j) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.clearHeatMapLayerCache(j);
    }

    public void clearUniversalLayer() {
        this.f8079b.clearUniversalLayer();
    }

    public void closeParticleEffect(String str) {
        this.f8079b.closeParticleEffect(str);
    }

    public void enablePOIAnimation(boolean z) {
        try {
            this.f8080c.readLock().lock();
            this.f8079b.enablePOIAnimation(z);
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public void entryFeedTopic(int i, String str, String str2) {
        this.f8079b.entrySearchTopic(i, str, str2);
    }

    public void entrySearchTopic(int i) {
        this.f8079b.entrySearchTopic(i, "", "");
    }

    public void exitSearchTopic() {
        this.f8079b.exitSearchTopic();
    }

    public void focusTrafficUGCLabel() {
        this.f8079b.focusTrafficUGCLabel();
    }

    public String geoPt3ToScrPoint(int i, int i2, int i3) {
        return this.f8079b.geoPt3ToScrPoint(i, i2, i3);
    }

    public boolean get3DModelEnable() {
        return this.f8079b.get3DModelEnable();
    }

    public boolean getDEMEnable() {
        return this.f8079b.getDEMEnable();
    }

    public boolean getDrawHouseHeightEnable() {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return false;
        }
        return nABaseMap.getDrawHouseHeightEnable();
    }

    public int getFontSizeLevel() {
        return this.f8079b.getFontSizeLevel();
    }

    public long getLayerIDByTag(String str) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return 0L;
        }
        return nABaseMap.getLayerIDByTag(str);
    }

    public boolean getMapBarData(Bundle bundle) {
        return this.f8079b.getMapBarData(bundle);
    }

    public int getMapLanguage() {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return 0;
        }
        return nABaseMap.getMapLanguage();
    }

    public int getMapScene() {
        return this.f8079b.getMapScene();
    }

    public Bundle getMapStatusLimits() {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return null;
        }
        return nABaseMap.getMapStatusLimits();
    }

    public boolean getMapStatusLimitsLevel(int[] iArr) {
        return this.f8079b.getMapStatusLimitsLevel(iArr);
    }

    public int getMapTheme() {
        return this.f8079b.getMapTheme();
    }

    public float[] getProjectionMatrix() {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return null;
        }
        float[] fArr = new float[16];
        nABaseMap.getProjectMatrix(fArr);
        return fArr;
    }

    public String getProjectionPt(String str) {
        return this.f8079b.getProjectionPt(str);
    }

    public int getScaleLevel(int i, int i2) {
        return this.f8079b.getScaleLevel(i, i2);
    }

    public int getSkyboxStyle() {
        return this.f8079b.getSkyboxStyle();
    }

    public float[] getViewMatrix() {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return null;
        }
        float[] fArr = new float[16];
        nABaseMap.getViewMatrix(fArr);
        return fArr;
    }

    public boolean importMapTheme(int i) {
        return this.f8079b.importMapTheme(i);
    }

    public boolean initCustomStyle(String str, String str2) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return false;
        }
        return nABaseMap.initCustomStyle(str, str2);
    }

    public void initHeatMapData(long j, Bundle bundle) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.initHeatMapData(j, bundle);
    }

    public boolean initWithOptions(Bundle bundle, boolean z) {
        return this.f8078a != 0 && this.f8079b.initWithOptions(bundle, z);
    }

    public boolean isAnimationRunning() {
        return this.f8079b.isAnimationRunning();
    }

    public boolean isEnableIndoor3D() {
        return this.f8079b.isEnableIndoor3D();
    }

    public boolean isNaviMode() {
        return this.f8079b.isNaviMode();
    }

    public boolean moveLayerBelowTo(long j, int i) {
        return this.f8079b.moveLayerBelowTo(j, i);
    }

    public boolean performAction(String str) {
        return this.f8079b.performAction(str);
    }

    public void recycleMemory(int i) {
        this.f8079b.recycleMemory(i);
    }

    public boolean releaseFromOfflineMap() {
        boolean z;
        try {
            this.f8080c.writeLock().lock();
            if (this.f8078a != 0) {
                this.f8079b.dispose();
                this.f8078a = 0L;
                z = true;
            } else {
                z = false;
            }
            return z;
        } finally {
            this.f8080c.writeLock().unlock();
        }
    }

    public void removeBmLayer(long j) {
        this.f8079b.removeBmLayer(j);
    }

    public void removeOneOverlayItem(Bundle bundle) {
        this.f8079b.removeOneOverlayItem(bundle);
    }

    public void removeOverlayItems(Bundle[] bundleArr) {
        this.f8079b.removeOneOverlayItems(bundleArr);
    }

    public void renderDone() {
        try {
            this.f8080c.readLock().lock();
            this.f8079b.renderDone();
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public void renderInit(int i, int i2, Surface surface, int i3) {
        try {
            this.f8080c.readLock().lock();
            this.f8079b.renderInit(i, i2, surface, i3);
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public int renderRender() {
        try {
            this.f8080c.readLock().lock();
            return this.f8079b.renderRender();
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public void renderResize(int i, int i2) {
        try {
            this.f8080c.readLock().lock();
            this.f8079b.renderResize(i, i2);
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public void resize(int i, int i2) {
        if (this.f8078a != 0) {
            this.f8079b.renderResize(i, i2);
        }
    }

    public void set3DModelEnable(boolean z) {
        this.f8079b.set3DModelEnable(z);
    }

    public void setCustomStyleEnable(boolean z) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.setCustomStyleEnable(z);
    }

    public void setDEMEnable(boolean z) {
        this.f8079b.setDEMEnable(z);
    }

    public void setDpiScale(float f) {
        this.f8079b.setDpiScale(f);
    }

    public void setDrawHouseHeightEnable(boolean z) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.setDrawHouseHeightEnable(z);
    }

    public void setEnableIndoor3D(boolean z) {
        this.f8079b.setEnableIndoor3D(z);
    }

    public void setFontSizeLevel(int i) {
        this.f8079b.setFontSizeLevel(i);
    }

    public void setHeatMapFrameAnimationIndex(long j, int i) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.setHeatMapFrameAnimationIndex(j, i);
    }

    public void setMapLanguage(int i) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.setMapLanguage(i);
    }

    public void setMapScene(int i) {
        this.f8079b.setMapScene(i);
    }

    public void setMapStatusLimits(Bundle bundle) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.setMapStatusLimits(bundle);
    }

    public boolean setMapStatusLimitsLevel(int i, int i2) {
        return this.f8079b.setMapStatusLimitsLevel(i, i2);
    }

    public boolean setMapTheme(int i, Bundle bundle) {
        return this.f8079b.setMapTheme(i, bundle);
    }

    public boolean setMapThemeScene(int i, int i2, Bundle bundle) {
        return this.f8079b.setMapThemeScene(i, i2, bundle);
    }

    public void setMaxAndMinZoomLevel(Bundle bundle) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.setMaxAndMinZoomLevel(bundle);
    }

    public void setRecommendPOIScene(int i) {
        this.f8079b.setRecommendPOIScene(i);
    }

    public void setSkyboxStyle(int i) {
        this.f8079b.setSkyboxStyle(i);
    }

    public boolean setTestSwitch(boolean z) {
        return this.f8079b.setTestSwitch(z);
    }

    public void setTrafficUGCData(String str) {
        this.f8079b.setTrafficUGCData(str);
    }

    public void setUniversalFilter(String str) {
        this.f8079b.setUniversalFilter(str);
    }

    public void showFootMarkGrid(boolean z, String str) {
        this.f8079b.showFootMarkGrid(z, str);
    }

    public boolean showParticleEffect(int i) {
        return this.f8079b.showParticleEffect(i);
    }

    public boolean showParticleEffectByName(String str, boolean z) {
        return this.f8079b.showParticleEffectByName(str, z);
    }

    public boolean showParticleEffectByType(int i) {
        return this.f8079b.showParticleEffectByType(i);
    }

    public void showTrafficUGCMap(boolean z) {
        this.f8079b.showTrafficUGCMap(z);
    }

    public void showUniversalLayer(Bundle bundle) {
        this.f8079b.showUniversalLayer(bundle);
    }

    public void startHeatMapFrameAnimation(long j) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.startHeatMapFrameAnimation(j);
    }

    public void stopHeatMapFrameAnimation(long j) {
        NABaseMap nABaseMap = this.f8079b;
        if (nABaseMap == null) {
            return;
        }
        nABaseMap.stopHeatMapFrameAnimation(j);
    }

    public void surfaceDestroyed(Surface surface) {
        try {
            this.f8080c.readLock().lock();
            this.f8079b.surfaceDestroyed(surface);
        } finally {
            this.f8080c.readLock().unlock();
        }
    }

    public void unFocusTrafficUGCLabel() {
        this.f8079b.unFocusTrafficUGCLabel();
    }

    public void updateBaseLayers() {
        this.f8079b.updateBaseLayers();
    }

    public void updateDrawFPS() {
        this.f8079b.updateDrawFPS();
    }

    public void updateFootMarkGrid() {
        this.f8079b.updateFootMarkGrid();
    }

    public void updateOneOverlayItem(Bundle bundle) {
        this.f8079b.updateOneOverlayItem(bundle);
    }

    public boolean updateSDKTile(Bundle bundle) {
        return this.f8079b.nativeUpdateSDKTile(this.f8078a, bundle);
    }

    public String worldPointToScreenPoint(float f, float f2, float f3) {
        return this.f8079b.worldPointToScreenPoint(f, f2, f3);
    }
}
