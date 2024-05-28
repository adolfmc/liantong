package com.baidu.mapapi.map;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.p083v4.app.Fragment;
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
public class SupportMapFragment extends Fragment {

    /* renamed from: a */
    private static final String f6386a = "SupportMapFragment";
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private TextureMapView f6387b;

    /* renamed from: c */
    private BaiduMapOptions f6388c;

    public SupportMapFragment() {
    }

    private SupportMapFragment(BaiduMapOptions baiduMapOptions) {
        this.f6388c = baiduMapOptions;
    }

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(BaiduMapOptions baiduMapOptions) {
        return new SupportMapFragment(baiduMapOptions);
    }

    public BaiduMap getBaiduMap() {
        TextureMapView textureMapView = this.f6387b;
        if (textureMapView == null) {
            return null;
        }
        return textureMapView.getMap();
    }

    public TextureMapView getMapView() {
        return this.f6387b;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // android.support.p083v4.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.baidu.mapapi.map.SupportMapFragment", viewGroup);
        this.f6387b = new TextureMapView(getActivity(), this.f6388c);
        TextureMapView textureMapView = this.f6387b;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.baidu.mapapi.map.SupportMapFragment");
        return textureMapView;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.f6387b.onDestroy();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
        this.f6387b.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.baidu.mapapi.map.SupportMapFragment");
        Tracker.onResume(this);
        super.onResume();
        this.f6387b.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.baidu.mapapi.map.SupportMapFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.baidu.mapapi.map.SupportMapFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.baidu.mapapi.map.SupportMapFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStop() {
        super.onStop();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle == null) {
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }
}
