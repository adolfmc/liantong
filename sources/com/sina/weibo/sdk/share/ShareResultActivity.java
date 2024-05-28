package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import com.bytedance.applog.tracker.Tracker;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShareResultActivity extends Activity {
    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Intent intent;
        super.onCreate(bundle);
        try {
            intent = getIntent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (intent == null) {
            finish();
        } else if (intent.getIntExtra("start_flag", -1) != 1002) {
            finish();
        } else {
            String action = intent.getAction();
            if ("com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY".equals(action)) {
                intent.setClassName(this, "com.sina.weibo.sdk.share.ShareTransActivity");
            } else if ("com.sina.weibo.sdk.action.ACTION_SDK_REQ_STORY".equals(action)) {
                intent.setClassName(this, "com.sina.weibo.sdk.share.ShareStoryActivity");
            }
            startActivity(intent);
            finish();
        }
    }
}
