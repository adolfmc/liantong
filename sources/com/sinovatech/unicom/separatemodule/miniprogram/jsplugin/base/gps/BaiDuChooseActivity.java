package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BaiDuLoacationEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiduChooseLocationAdapter;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiDuChooseActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;
    private BaiduChooseLocationAdapter adapter;
    private BaiduMap baiduMap;
    private UiSettings baiduMapUiSettings;
    private String city;
    private ImageView ivBack;
    private ImageView ivLoaction;
    private double latitude1;
    private LinearLayout llSearch;
    private LocationClient locationClient;
    private double longitude1;
    private ArrayList<BaiDuLoacationEntity> mLocations;
    private MarkerOptions mOption;
    private RxPermissions mRxPermissions;
    private MapView mapview;
    private Marker markers;
    private RecyclerView rclLoactions;
    private TextView tvOk;
    private LatLng currentLocation = null;
    private int RESULR_CODE = 100;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 97);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
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

    private void initView() {
        try {
            this.mapview = (MapView) findViewById(2131298034);
            this.ivLoaction = (ImageView) findViewById(2131297424);
            this.rclLoactions = (RecyclerView) findViewById(2131298255);
            this.ivBack = (ImageView) findViewById(2131297344);
            this.llSearch = (LinearLayout) findViewById(2131297774);
            this.tvOk = (TextView) findViewById(2131299031);
            SDKInitializer.setCoordType(CoordType.GCJ02);
            this.baiduMap = this.mapview.getMap();
            this.baiduMapUiSettings = this.baiduMap.getUiSettings();
            this.baiduMapUiSettings.setOverlookingGesturesEnabled(false);
            this.baiduMapUiSettings.setRotateGesturesEnabled(true);
            this.baiduMapUiSettings.setScrollGesturesEnabled(true);
            this.mapview.showZoomControls(false);
            this.baiduMap.setMyLocationEnabled(true);
            this.locationClient = new LocationClient(this);
            LocationClientOption locationClientOption = new LocationClientOption();
            locationClientOption.setOpenGps(true);
            locationClientOption.setScanSpan(1000);
            this.locationClient.setLocOption(locationClientOption);
            this.baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, BitmapDescriptorFactory.fromResource(2131231023)));
            double parseDouble = Double.parseDouble(getIntent().getStringExtra("latitude"));
            double parseDouble2 = Double.parseDouble(getIntent().getStringExtra("longitude"));
            latlngToAddress(new LatLng(parseDouble, parseDouble2), "");
            markersLocation(new LatLng(parseDouble, parseDouble2));
            this.baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target(new LatLng(parseDouble, parseDouble2)).zoom(18.0f).build()));
            this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Intent intent = new Intent();
                    intent.putExtra("name", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getName());
                    intent.putExtra("address", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getAddress());
                    intent.putExtra("latitude", String.valueOf(((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getLatitude()));
                    intent.putExtra("longitude", String.valueOf(((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getLongitude()));
                    intent.putExtra("city", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getCity());
                    intent.putExtra("province", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getProvince());
                    intent.putExtra("district", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getDistrict());
                    intent.putExtra("town", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getTown());
                    intent.putExtra("streetName", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getStreetName());
                    intent.putExtra("streetNumber", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getStreetNumber());
                    intent.putExtra("adCode", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(0)).getAdCode());
                    BaiDuChooseActivity.this.setResult(-1, intent);
                    BaiDuChooseActivity.this.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Intent intent = new Intent();
                    intent.putExtra("isNoSelect", true);
                    BaiDuChooseActivity.this.setResult(-1, intent);
                    BaiDuChooseActivity.this.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.3
                @Override // com.baidu.mapapi.map.BaiduMap.OnMapClickListener
                public void onMapClick(LatLng latLng) {
                }

                @Override // com.baidu.mapapi.map.BaiduMap.OnMapClickListener
                public void onMapPoiClick(MapPoi mapPoi) {
                    BaiDuChooseActivity.this.baiduMap.setMyLocationEnabled(false);
                    LocationManager.getInstance().stopLocaiton();
                    BaiDuChooseActivity.this.latlngToAddress(mapPoi.getPosition(), mapPoi.getName());
                    BaiDuChooseActivity.this.currentLocation = null;
                    BaiDuChooseActivity.this.currentLocation = mapPoi.getPosition();
                    BaiDuChooseActivity baiDuChooseActivity = BaiDuChooseActivity.this;
                    baiDuChooseActivity.markersLocation(baiDuChooseActivity.currentLocation);
                }
            });
            this.baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.4
                @Override // com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener
                public void onMapStatusChange(MapStatus mapStatus) {
                }

                @Override // com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener
                public void onMapStatusChangeStart(MapStatus mapStatus) {
                }

                @Override // com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener
                public void onMapStatusChangeStart(MapStatus mapStatus, int i) {
                }

                @Override // com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener
                public void onMapStatusChangeFinish(MapStatus mapStatus) {
                    LatLng latLng = mapStatus.target;
                    BaiDuChooseActivity.this.currentLocation = null;
                    BaiDuChooseActivity.this.currentLocation = latLng;
                    BaiDuChooseActivity.this.latlngToAddress(latLng, "");
                    BaiDuChooseActivity baiDuChooseActivity = BaiDuChooseActivity.this;
                    baiDuChooseActivity.markersLocation(baiDuChooseActivity.currentLocation);
                }
            });
            this.ivLoaction.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    BaiDuChooseActivity.this.startLocation();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.llSearch.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Intent intent = new Intent(BaiDuChooseActivity.this, BaiDuSearchPoiActivity.class);
                    intent.putExtra("latitude", BaiDuChooseActivity.this.latitude1 + "");
                    intent.putExtra("longitude", BaiDuChooseActivity.this.longitude1 + "");
                    intent.putExtra("cityLimit", BaiDuChooseActivity.this.getIntent().getBooleanExtra("cityLimit", false));
                    intent.putExtra("isNeedDetilInfo", BaiDuChooseActivity.this.getIntent().getBooleanExtra("isNeedDetilInfo", false));
                    BaiDuChooseActivity baiDuChooseActivity = BaiDuChooseActivity.this;
                    baiDuChooseActivity.startActivityForResult(intent, baiDuChooseActivity.RESULR_CODE);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markersLocation(LatLng latLng) {
        Marker marker = this.markers;
        if (marker != null) {
            marker.remove();
        }
        this.mOption = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(2131231023)).clickable(false);
        this.markers = (Marker) this.baiduMap.addOverlay(this.mOption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void latlngToAddress(LatLng latLng, final String str) {
        this.latitude1 = latLng.latitude;
        this.longitude1 = latLng.longitude;
        GeoCodManager.getInstance().inverseGeocode(latLng, new OnGetGeoCoderResultListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.7
            @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }

            @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                BaiDuChooseActivity.this.mLocations = new ArrayList();
                List<PoiInfo> poiList = reverseGeoCodeResult.getPoiList();
                if (poiList != null) {
                    if (BaiDuChooseActivity.this.mLocations != null) {
                        BaiDuChooseActivity.this.mLocations.clear();
                    }
                    BaiDuChooseActivity.this.city = poiList.get(0).getCity();
                    if (!TextUtils.isEmpty(reverseGeoCodeResult.getAddress()) && !TextUtils.isEmpty(str) && !str.equals(reverseGeoCodeResult.getPoiList().get(0).getName())) {
                        BaiDuLoacationEntity baiDuLoacationEntity = new BaiDuLoacationEntity();
                        baiDuLoacationEntity.setName(str);
                        baiDuLoacationEntity.setAddress(reverseGeoCodeResult.getAddress());
                        baiDuLoacationEntity.setLatitude(reverseGeoCodeResult.getLocation().latitude);
                        baiDuLoacationEntity.setLongitude(reverseGeoCodeResult.getLocation().longitude);
                        baiDuLoacationEntity.setCity(BaiDuChooseActivity.this.city);
                        baiDuLoacationEntity.setAdCode(reverseGeoCodeResult.getAdcode());
                        baiDuLoacationEntity.setDistrict(reverseGeoCodeResult.getAddressDetail().district);
                        baiDuLoacationEntity.setProvince(reverseGeoCodeResult.getAddressDetail().province);
                        baiDuLoacationEntity.setTown(reverseGeoCodeResult.getAddressDetail().town);
                        baiDuLoacationEntity.setDistance(reverseGeoCodeResult.getAddressDetail().distance);
                        baiDuLoacationEntity.setStreetName(reverseGeoCodeResult.getAddressDetail().street);
                        baiDuLoacationEntity.setStreetNumber(reverseGeoCodeResult.getAddressDetail().streetNumber);
                        BaiDuChooseActivity.this.mLocations.add(baiDuLoacationEntity);
                    }
                    for (int i = 0; i < poiList.size(); i++) {
                        BaiDuLoacationEntity baiDuLoacationEntity2 = new BaiDuLoacationEntity();
                        baiDuLoacationEntity2.setAddress(poiList.get(i).address);
                        baiDuLoacationEntity2.setName(poiList.get(i).name);
                        baiDuLoacationEntity2.setLatitude(poiList.get(i).location.latitude);
                        baiDuLoacationEntity2.setLongitude(poiList.get(i).location.longitude);
                        baiDuLoacationEntity2.setCity(poiList.get(i).getCity());
                        baiDuLoacationEntity2.setAdCode(reverseGeoCodeResult.getAdcode());
                        baiDuLoacationEntity2.setDistance(reverseGeoCodeResult.getPoiList().get(i).getDistance() + "");
                        baiDuLoacationEntity2.setDistrict(reverseGeoCodeResult.getAddressDetail().district);
                        baiDuLoacationEntity2.setProvince(reverseGeoCodeResult.getAddressDetail().province);
                        baiDuLoacationEntity2.setTown(reverseGeoCodeResult.getAddressDetail().town);
                        baiDuLoacationEntity2.setStreetName(reverseGeoCodeResult.getAddressDetail().street);
                        baiDuLoacationEntity2.setStreetNumber(reverseGeoCodeResult.getAddressDetail().streetNumber);
                        BaiDuChooseActivity.this.mLocations.add(baiDuLoacationEntity2);
                    }
                    BaiDuChooseActivity.this.rclLoactions.setLayoutManager(new LinearLayoutManager(BaiDuChooseActivity.this, 1, false));
                    BaiDuChooseActivity.this.rclLoactions.setNestedScrollingEnabled(true);
                    BaiDuChooseActivity baiDuChooseActivity = BaiDuChooseActivity.this;
                    baiDuChooseActivity.adapter = new BaiduChooseLocationAdapter(baiDuChooseActivity, baiDuChooseActivity.mLocations, new BaiduChooseLocationAdapter.setOnClick() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.7.1
                        @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiduChooseLocationAdapter.setOnClick
                        public void setOnPosition(int i2) {
                            Intent intent = new Intent();
                            intent.putExtra("name", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getName());
                            intent.putExtra("address", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getAddress());
                            intent.putExtra("latitude", String.valueOf(((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getLatitude()));
                            intent.putExtra("longitude", String.valueOf(((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getLongitude()));
                            intent.putExtra("city", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getCity());
                            intent.putExtra("province", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getProvince());
                            intent.putExtra("district", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getDistrict());
                            intent.putExtra("town", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getTown());
                            intent.putExtra("streetName", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getStreetName());
                            intent.putExtra("streetNumber", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getStreetNumber());
                            intent.putExtra("adCode", ((BaiDuLoacationEntity) BaiDuChooseActivity.this.mLocations.get(i2)).getAdCode() + "");
                            BaiDuChooseActivity.this.setResult(-1, intent);
                            BaiDuChooseActivity.this.finish();
                        }
                    });
                    BaiDuChooseActivity.this.rclLoactions.setAdapter(BaiDuChooseActivity.this.adapter);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLocation() {
        LocationManager.getInstance().startLocation(this, 500L, new LocationManager.LocationListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuChooseActivity.8
            @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager.LocationListener
            public void onSuccess(BDLocation bDLocation) {
                if (bDLocation == null || BaiDuChooseActivity.this.mapview == null) {
                    return;
                }
                BaiDuChooseActivity.this.baiduMap.setMyLocationData(new MyLocationData.Builder().accuracy(bDLocation.getRadius()).direction(bDLocation.getDirection()).latitude(bDLocation.getLatitude()).longitude(bDLocation.getLongitude()).build());
                LatLng latLng = new LatLng(bDLocation.getLatitude(), bDLocation.getLongitude());
                BaiDuChooseActivity.this.latlngToAddress(latLng, "");
                BaiDuChooseActivity.this.baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target(latLng).zoom(18.0f).build()));
                BaiDuChooseActivity.this.baiduMap.setMyLocationEnabled(false);
            }

            @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.LocationManager.LocationListener
            public void onFail(String str, String str2) {
                LocationManager.getInstance().stopLocaiton();
                if (TextUtils.equals("12", str2)) {
                    str2 = "请先开启定位权限，授权APP使用定位功能";
                } else if (TextUtils.equals("13", str2)) {
                    str2 = "请先开启定位权限，授权APP使用定位功能";
                }
                UIUtils.toast(str2);
            }
        });
        Marker marker = this.markers;
        if (marker != null) {
            marker.remove();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        this.mapview.onResume();
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.mapview.onPause();
        super.onPause();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.baiduMap.setMyLocationEnabled(false);
        LocationManager.getInstance().stopLocaiton();
        this.mapview.onDestroy();
        GeoCodManager.getInstance().GeoCodeStop();
        super.onDestroy();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != this.RESULR_CODE || intent == null || intent.getBooleanExtra("isNoSelect", false)) {
            return;
        }
        LatLng latLng = new LatLng(Double.parseDouble(intent.getStringExtra("latitude")), Double.parseDouble(intent.getStringExtra("longitude")));
        latlngToAddress(latLng, "");
        markersLocation(latLng);
        this.baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target(latLng).zoom(18.0f).build()));
    }
}
