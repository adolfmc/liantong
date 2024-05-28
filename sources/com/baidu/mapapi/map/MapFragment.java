package com.baidu.mapapi.map;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MapFragment extends Fragment {

    /* renamed from: a */
    private static final String f6135a = "MapFragment";
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private MapView f6136b;

    /* renamed from: c */
    private BaiduMapOptions f6137c;

    public MapFragment() {
    }

    private MapFragment(BaiduMapOptions baiduMapOptions) {
        this.f6137c = baiduMapOptions;
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static MapFragment newInstance(BaiduMapOptions baiduMapOptions) {
        return new MapFragment(baiduMapOptions);
    }

    public BaiduMap getBaiduMap() {
        MapView mapView = this.f6136b;
        if (mapView == null) {
            return null;
        }
        return mapView.getMap();
    }

    public MapView getMapView() {
        return this.f6136b;
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.baidu.mapapi.map.MapFragment", viewGroup);
        this.f6136b = new MapView(getActivity(), this.f6137c);
        MapView mapView = this.f6136b;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.baidu.mapapi.map.MapFragment");
        return mapView;
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f6136b.onDestroy();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // android.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
        this.f6136b.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.baidu.mapapi.map.MapFragment");
        Tracker.onResume(this);
        super.onResume();
        this.f6136b.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.baidu.mapapi.map.MapFragment");
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.baidu.mapapi.map.MapFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.baidu.mapapi.map.MapFragment");
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // android.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }
}
