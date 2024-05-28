package com.king.zxing;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.king.zxing.camera.CameraManager;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CaptureFragment extends Fragment implements OnCaptureCallback {
    public static final String KEY_RESULT = "SCAN_RESULT";
    public NBSTraceUnit _nbs_trace;
    private View ivTorch;
    private CaptureHelper mCaptureHelper;
    private View mRootView;
    private SurfaceView surfaceView;
    private ViewfinderView viewfinderView;

    public boolean isContentView(@LayoutRes int i) {
        return true;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // com.king.zxing.OnCaptureCallback
    public boolean onResultCallback(String str) {
        return false;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.king.zxing.CaptureFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.king.zxing.CaptureFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    public static CaptureFragment newInstance() {
        Bundle bundle = new Bundle();
        CaptureFragment captureFragment = new CaptureFragment();
        captureFragment.setArguments(bundle);
        return captureFragment;
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.king.zxing.CaptureFragment", viewGroup);
        if (isContentView(getLayoutId())) {
            this.mRootView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
        }
        initUI();
        View view = this.mRootView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.king.zxing.CaptureFragment");
        return view;
    }

    public void initUI() {
        this.surfaceView = (SurfaceView) this.mRootView.findViewById(getSurfaceViewId());
        this.viewfinderView = (ViewfinderView) this.mRootView.findViewById(getViewfinderViewId());
        int ivTorchId = getIvTorchId();
        if (ivTorchId != 0) {
            this.ivTorch = this.mRootView.findViewById(ivTorchId);
            this.ivTorch.setVisibility(4);
        }
        this.mCaptureHelper = new CaptureHelper(this, this.surfaceView, this.viewfinderView, this.ivTorch);
        this.mCaptureHelper.setOnCaptureCallback(this);
    }

    public int getLayoutId() {
        return C5188R.C5192layout.zxl_capture;
    }

    public int getViewfinderViewId() {
        return C5188R.C5191id.viewfinderView;
    }

    public int getSurfaceViewId() {
        return C5188R.C5191id.surfaceView;
    }

    public int getIvTorchId() {
        return C5188R.C5191id.ivTorch;
    }

    public CaptureHelper getCaptureHelper() {
        return this.mCaptureHelper;
    }

    @Deprecated
    public CameraManager getCameraManager() {
        return this.mCaptureHelper.getCameraManager();
    }

    public View getRootView() {
        return this.mRootView;
    }

    public void setRootView(View view) {
        this.mRootView = view;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mCaptureHelper.onCreate();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.king.zxing.CaptureFragment");
        Tracker.onResume(this);
        super.onResume();
        this.mCaptureHelper.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.king.zxing.CaptureFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
        this.mCaptureHelper.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCaptureHelper.onDestroy();
    }
}
