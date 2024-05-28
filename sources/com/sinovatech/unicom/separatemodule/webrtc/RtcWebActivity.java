package com.sinovatech.unicom.separatemodule.webrtc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.common.UIUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RtcWebActivity extends AppCompatActivity {
    String entranceURL;
    private RtcWebFragment fragment;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131492961);
        UIUtils.setStatusBarMode(this, true, true);
        this.entranceURL = getIntent().getStringExtra("entranceURL");
        this.fragment = RtcWebFragment.newInstance(this.entranceURL);
        getSupportFragmentManager().beginTransaction().replace(2131298417, this.fragment, "RtcWebFragment").commit();
    }
}
