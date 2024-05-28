package com.fido.android.framework.p197tm;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;
import com.gmrz.android.client.utils.ActivityStarter;
import com.gmrz.android.client.utils.Logger;

/* renamed from: com.fido.android.framework.tm.AsmIntentHelperActivity */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AsmIntentHelperActivity extends Activity {

    /* renamed from: a */
    private RetainedFragment f10114a;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    /* renamed from: com.fido.android.framework.tm.AsmIntentHelperActivity$RetainedFragment */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RetainedFragment extends Fragment {

        /* renamed from: a */
        private Boolean f10115a = Boolean.FALSE;

        /* renamed from: b */
        private Boolean f10116b = Boolean.FALSE;

        @Override // android.app.Fragment
        public void onHiddenChanged(boolean z) {
            Tracker.onHiddenChanged(this, z);
            super.onHiddenChanged(z);
        }

        @Override // android.app.Fragment
        public void onPause() {
            Tracker.onPause(this);
            super.onPause();
        }

        @Override // android.app.Fragment
        public void onResume() {
            Tracker.onResume(this);
            super.onResume();
        }

        @Override // android.app.Fragment
        public void setUserVisibleHint(boolean z) {
            Tracker.setUserVisibleHint(this, z);
            super.setUserVisibleHint(z);
        }

        @Override // android.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setRetainInstance(true);
            Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity Fragment onCreate ");
            setInitialized(Boolean.TRUE);
            Intent intent = new Intent("org.fidoalliance.intent.FIDO_OPERATION");
            intent.setComponent((ComponentName) getActivity().getIntent().getParcelableExtra("COMPONENT_NAME"));
            intent.setType("application/fido.uaf_asm+json");
            intent.putExtra("message", getActivity().getIntent().getStringExtra("IN_PARAM"));
            try {
                startActivityForResult(intent, 0);
            } catch (ActivityNotFoundException | SecurityException unused) {
                ActivityStarter.setResult(getActivity().getIntent(), null);
                getActivity().finish();
            }
        }

        @Override // android.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity Fragment onDestroy");
        }

        @Override // android.app.Fragment
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity Fragment onAttach");
        }

        @Override // android.app.Fragment
        public void onDetach() {
            super.onDetach();
            Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity Fragment onDetach");
        }

        public void setInitialized(Boolean bool) {
            Logger.m15895d("AsmIntentHelperActivity", "SetInitialized " + this.f10116b.toString());
            this.f10116b = bool;
        }

        public Boolean getInitialized() {
            Logger.m15895d("AsmIntentHelperActivity", "GetInitialized " + this.f10116b.toString());
            return this.f10116b;
        }

        public void setData(Boolean bool) {
            this.f10115a = bool;
        }

        public Boolean getData() {
            return this.f10115a;
        }

        @Override // android.app.Fragment
        public void onActivityResult(int i, int i2, Intent intent) {
            StringBuilder sb = new StringBuilder();
            sb.append("AsmIntentHelperActivity Fragment onActivityResult");
            sb.append(i2 == 0 ? " RESULT_CANCELED" : "");
            Logger.m15895d("AsmIntentHelperActivity", sb.toString());
            this.f10116b = false;
            String str = null;
            if (i2 == -1 && intent != null) {
                str = intent.getStringExtra("message");
            }
            ActivityStarter.setResult(getActivity().getIntent(), str);
            getActivity().finish();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityStarter.setActivity(this, getIntent());
        Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity onCreate");
        FragmentManager fragmentManager = getFragmentManager();
        this.f10114a = (RetainedFragment) fragmentManager.findFragmentByTag("asmIntentHelperFragment");
        if (this.f10114a == null) {
            this.f10114a = new RetainedFragment();
            fragmentManager.beginTransaction().add(this.f10114a, "asmIntentHelperFragment").commit();
            Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity seting up Fragment");
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity onConfigurationChanged");
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity onActivityResult");
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity onDestroy");
        this.f10114a = (RetainedFragment) getFragmentManager().findFragmentByTag("asmIntentHelperFragment");
        RetainedFragment retainedFragment = this.f10114a;
        if (retainedFragment == null) {
            Logger.m15895d("AsmIntentHelperActivity", "acitivityFragment is null");
        } else if (retainedFragment.getInitialized().booleanValue()) {
        } else {
            Logger.m15895d("AsmIntentHelperActivity", "AsmIntentHelperActivity resultreceived is TRUE, finishing activity ======");
        }
    }
}
