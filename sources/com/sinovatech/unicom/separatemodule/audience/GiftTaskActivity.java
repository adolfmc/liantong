package com.sinovatech.unicom.separatemodule.audience;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GiftTaskActivity extends AppCompatActivity {
    private LiveGiftView giftView;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131492919);
        this.giftView = (LiveGiftView) findViewById(2131297078);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.giftView.stopAllGiftAnim();
    }
}
