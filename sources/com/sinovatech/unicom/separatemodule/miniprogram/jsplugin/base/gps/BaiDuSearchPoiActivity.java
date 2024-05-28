package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BaiDuLoacationEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiduChooseLocationAdapter;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiDuSearchPoiActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;
    private BaiduChooseLocationAdapter adapter;
    private String city;
    private EditText editSearch;
    private Intent intent;
    private ImageView ivBack;
    private ArrayList<BaiDuLoacationEntity> mLocations;
    private RecyclerView rclPois;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 98);
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

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
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
        this.editSearch = (EditText) findViewById(2131296916);
        this.rclPois = (RecyclerView) findViewById(2131298256);
        this.ivBack = (ImageView) findViewById(2131297344);
        this.mLocations = new ArrayList<>();
        this.intent = new Intent();
        this.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuSearchPoiActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                BaiDuSearchPoiActivity.this.intent.putExtra("isNoSelect", true);
                BaiDuSearchPoiActivity baiDuSearchPoiActivity = BaiDuSearchPoiActivity.this;
                baiDuSearchPoiActivity.setResult(-1, baiDuSearchPoiActivity.intent);
                BaiDuSearchPoiActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (!TextUtils.isEmpty(getIntent().getStringExtra("latitude")) && !TextUtils.isEmpty(getIntent().getStringExtra("longitude"))) {
            latlngToAddress(new LatLng(Double.parseDouble(getIntent().getStringExtra("latitude")), Double.parseDouble(getIntent().getStringExtra("longitude"))));
        }
        this.editSearch.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuSearchPoiActivity.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LocationManager.getInstance().stopLocaiton();
                BaiDuSearchPoiActivity.this.sugSeacrchAddress(charSequence.toString());
            }
        });
    }

    private void latlngToAddress(LatLng latLng) {
        GeoCodManager.getInstance().inverseGeocode(latLng, new OnGetGeoCoderResultListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuSearchPoiActivity.3
            @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }

            @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                List<PoiInfo> poiList = reverseGeoCodeResult.getPoiList();
                if (BaiDuSearchPoiActivity.this.mLocations != null) {
                    BaiDuSearchPoiActivity.this.mLocations.clear();
                }
                if (poiList != null) {
                    BaiDuSearchPoiActivity.this.city = reverseGeoCodeResult.getPoiList().get(0).getCity();
                    for (int i = 0; i < poiList.size(); i++) {
                        BaiDuLoacationEntity baiDuLoacationEntity = new BaiDuLoacationEntity();
                        baiDuLoacationEntity.setAddress(poiList.get(i).address);
                        baiDuLoacationEntity.setName(poiList.get(i).name);
                        baiDuLoacationEntity.setLatitude(poiList.get(i).location.latitude);
                        baiDuLoacationEntity.setLongitude(poiList.get(i).location.longitude);
                        baiDuLoacationEntity.setCity(poiList.get(i).getCity());
                        baiDuLoacationEntity.setDistance(poiList.get(i).getDistance() + "");
                        baiDuLoacationEntity.setAdCode(reverseGeoCodeResult.getAdcode());
                        baiDuLoacationEntity.setDistrict(reverseGeoCodeResult.getAddressDetail().district);
                        baiDuLoacationEntity.setProvince(reverseGeoCodeResult.getAddressDetail().province);
                        baiDuLoacationEntity.setTown(reverseGeoCodeResult.getAddressDetail().town);
                        baiDuLoacationEntity.setStreetName(reverseGeoCodeResult.getAddressDetail().street);
                        baiDuLoacationEntity.setStreetNumber(reverseGeoCodeResult.getAddressDetail().streetNumber);
                        BaiDuSearchPoiActivity.this.mLocations.add(baiDuLoacationEntity);
                    }
                    BaiDuSearchPoiActivity.this.rclPois.setLayoutManager(new LinearLayoutManager(BaiDuSearchPoiActivity.this, 1, false));
                    BaiDuSearchPoiActivity baiDuSearchPoiActivity = BaiDuSearchPoiActivity.this;
                    baiDuSearchPoiActivity.adapter = new BaiduChooseLocationAdapter(baiDuSearchPoiActivity, baiDuSearchPoiActivity.mLocations, new BaiduChooseLocationAdapter.setOnClick() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuSearchPoiActivity.3.1
                        @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiduChooseLocationAdapter.setOnClick
                        public void setOnPosition(int i2) {
                            double latitude = ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getLatitude();
                            double longitude = ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getLongitude();
                            BaiDuSearchPoiActivity.this.intent.putExtra("latitude", String.valueOf(latitude));
                            BaiDuSearchPoiActivity.this.intent.putExtra("longitude", String.valueOf(longitude));
                            BaiDuSearchPoiActivity.this.intent.putExtra("name", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getName());
                            BaiDuSearchPoiActivity.this.intent.putExtra("address", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getAddress());
                            BaiDuSearchPoiActivity.this.intent.putExtra("city", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getCity());
                            BaiDuSearchPoiActivity.this.intent.putExtra("province", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getProvince());
                            BaiDuSearchPoiActivity.this.intent.putExtra("district", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getDistrict());
                            BaiDuSearchPoiActivity.this.intent.putExtra("town", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getTown());
                            BaiDuSearchPoiActivity.this.intent.putExtra("streetName", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getStreetName());
                            BaiDuSearchPoiActivity.this.intent.putExtra("streetNumber", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getStreetNumber());
                            Intent intent = BaiDuSearchPoiActivity.this.intent;
                            intent.putExtra("adCode", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getAdCode() + "");
                            BaiDuSearchPoiActivity.this.setResult(-1, BaiDuSearchPoiActivity.this.intent);
                            BaiDuSearchPoiActivity.this.finish();
                        }
                    });
                    BaiDuSearchPoiActivity.this.rclPois.setAdapter(BaiDuSearchPoiActivity.this.adapter);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sugSeacrchAddress(String str) {
        SuggestionSearchManager.suggestionSearch(this.city, str, getIntent().getBooleanExtra("cityLimit", false), new OnGetSuggestionResultListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuSearchPoiActivity.4
            @Override // com.baidu.mapapi.search.sug.OnGetSuggestionResultListener
            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
                if (allSuggestions != null) {
                    if (BaiDuSearchPoiActivity.this.mLocations != null) {
                        BaiDuSearchPoiActivity.this.mLocations.clear();
                    }
                    for (int i = 0; i < allSuggestions.size(); i++) {
                        BaiDuLoacationEntity baiDuLoacationEntity = new BaiDuLoacationEntity();
                        baiDuLoacationEntity.setName(allSuggestions.get(i).getKey());
                        baiDuLoacationEntity.setAddress(allSuggestions.get(i).address);
                        baiDuLoacationEntity.setDistrict(allSuggestions.get(i).getDistrict());
                        if (allSuggestions.get(i).getPt() != null) {
                            baiDuLoacationEntity.setLatitude(allSuggestions.get(i).getPt().latitude);
                            baiDuLoacationEntity.setLongitude(allSuggestions.get(i).getPt().longitude);
                        }
                        baiDuLoacationEntity.setCity(allSuggestions.get(i).city);
                        BaiDuSearchPoiActivity.this.mLocations.add(baiDuLoacationEntity);
                    }
                    if (BaiDuSearchPoiActivity.this.mLocations != null) {
                        BaiDuSearchPoiActivity.this.rclPois.setLayoutManager(new LinearLayoutManager(BaiDuSearchPoiActivity.this, 1, false));
                        BaiDuSearchPoiActivity baiDuSearchPoiActivity = BaiDuSearchPoiActivity.this;
                        baiDuSearchPoiActivity.adapter = new BaiduChooseLocationAdapter(baiDuSearchPoiActivity, baiDuSearchPoiActivity.mLocations, new BaiduChooseLocationAdapter.setOnClick() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiDuSearchPoiActivity.4.1
                            @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiduChooseLocationAdapter.setOnClick
                            public void setOnPosition(int i2) {
                                LocationManager.getInstance().stopLocaiton();
                                double latitude = ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getLatitude();
                                double longitude = ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getLongitude();
                                BaiDuSearchPoiActivity.this.intent.putExtra("latitude", String.valueOf(latitude));
                                BaiDuSearchPoiActivity.this.intent.putExtra("longitude", String.valueOf(longitude));
                                BaiDuSearchPoiActivity.this.intent.putExtra("name", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getName());
                                BaiDuSearchPoiActivity.this.intent.putExtra("address", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getAddress());
                                BaiDuSearchPoiActivity.this.intent.putExtra("city", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getCity());
                                BaiDuSearchPoiActivity.this.intent.putExtra("province", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getProvince());
                                BaiDuSearchPoiActivity.this.intent.putExtra("district", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getDistrict());
                                BaiDuSearchPoiActivity.this.intent.putExtra("town", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getTown());
                                BaiDuSearchPoiActivity.this.intent.putExtra("streetName", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getStreetName());
                                BaiDuSearchPoiActivity.this.intent.putExtra("streetNumber", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getStreetNumber());
                                Intent intent = BaiDuSearchPoiActivity.this.intent;
                                intent.putExtra("adCode", ((BaiDuLoacationEntity) BaiDuSearchPoiActivity.this.mLocations.get(i2)).getAdCode() + "");
                                BaiDuSearchPoiActivity.this.setResult(-1, BaiDuSearchPoiActivity.this.intent);
                                BaiDuSearchPoiActivity.this.finish();
                            }
                        });
                        BaiDuSearchPoiActivity.this.rclPois.setAdapter(BaiDuSearchPoiActivity.this.adapter);
                    }
                }
            }
        });
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        SuggestionSearchManager.suggestionSearchStop();
        GeoCodManager.getInstance().GeoCodeStop();
        super.onDestroy();
    }
}
