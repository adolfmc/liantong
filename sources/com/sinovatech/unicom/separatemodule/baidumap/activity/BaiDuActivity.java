package com.sinovatech.unicom.separatemodule.baidumap.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiChildrenInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiDuActivity extends AppCompatActivity implements OnGetGeoCoderResultListener, OnGetPoiSearchResultListener, OnGetSuggestionResultListener {
    public NBSTraceUnit _nbs_trace;
    private Activity activityContext = this;
    private BaiduMap baiduMap;
    private UiSettings baiduMapUiSettings;
    private TextureMapView mapView;
    private PoiSearch poiSearch;
    private SuggestionSearch suggestionSearch;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 73);
    }

    @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
    }

    @Override // com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
    }

    @Override // com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
    }

    @Override // com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    private void initMapView() {
        this.mapView = (TextureMapView) findViewById(2131298033);
        SDKInitializer.setCoordType(CoordType.GCJ02);
        this.baiduMap = this.mapView.getMap();
        this.mapView.showScaleControl(false);
        this.mapView.showZoomControls(true);
        this.baiduMap.setMapType(1);
        this.baiduMap.setMyLocationEnabled(true);
        this.baiduMapUiSettings = this.baiduMap.getUiSettings();
        this.baiduMapUiSettings.setCompassEnabled(false);
        this.baiduMapUiSettings.setOverlookingGesturesEnabled(false);
        this.baiduMapUiSettings.setRotateGesturesEnabled(true);
        this.baiduMapUiSettings.setScrollGesturesEnabled(true);
        this.baiduMapUiSettings.setZoomGesturesEnabled(true);
        this.baiduMap.setOnMapClickListener(new C85391());
        this.suggestionSearch = SuggestionSearch.newInstance();
        this.suggestionSearch.setOnGetSuggestionResultListener(this);
        ((EditText) findViewById(2131296917)).addTextChangedListener(new C85402());
        this.poiSearch = PoiSearch.newInstance();
        this.poiSearch.setOnGetPoiSearchResultListener(this);
        ((EditText) findViewById(2131296918)).addTextChangedListener(new C85413());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiDuActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C85391 implements BaiduMap.OnMapClickListener {
        @Override // com.baidu.mapapi.map.BaiduMap.OnMapClickListener
        public void onMapPoiClick(MapPoi mapPoi) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C85391() {
        }

        @Override // com.baidu.mapapi.map.BaiduMap.OnMapClickListener
        public void onMapClick(LatLng latLng) {
            Log.d("zhangcan", "onMapClick: " + latLng.latitude + "   " + latLng.longitude);
            BaiDuActivity.this.latlngToAddress(latLng);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiDuActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class C85402 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C85402() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            BaiDuActivity.this.suggestionSearch.requestSuggestion(new SuggestionSearchOption().city(charSequence.toString()).keyword(charSequence.toString()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiDuActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class C85413 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C85413() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            BaiDuActivity.this.poiSearch.searchInCity(new PoiCitySearchOption().city(charSequence.toString()).keyword(charSequence.toString()).pageNum(0));
        }
    }

    @Override // com.baidu.mapapi.search.sug.OnGetSuggestionResultListener
    public void onGetSuggestionResult(SuggestionResult suggestionResult) {
        List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
        for (int i = 0; i < allSuggestions.size(); i++) {
            allSuggestions.get(i).getCity();
            allSuggestions.get(i).getAddress();
            List<PoiChildrenInfo> poiChildrenInfoList = allSuggestions.get(i).getPoiChildrenInfoList();
            poiChildrenInfoList.get(i).getName();
            poiChildrenInfoList.get(i).describeContents();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void latlngToAddress(LatLng latLng) {
        GeoCoder newInstance = GeoCoder.newInstance();
        newInstance.setOnGetGeoCodeResultListener(this);
        newInstance.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.mapView.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mapView.onDestroy();
        this.suggestionSearch.destroy();
    }

    @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        reverseGeoCodeResult.getPoiList();
        Log.d("zhangcan", reverseGeoCodeResult.getAddress());
    }

    @Override // com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener
    public void onGetPoiResult(PoiResult poiResult) {
        poiResult.getAllAddr();
    }
}
