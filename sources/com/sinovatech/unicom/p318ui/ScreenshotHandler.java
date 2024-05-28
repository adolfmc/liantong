package com.sinovatech.unicom.p318ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.common.ThumbnailLoader;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import java.util.List;
import java.util.Timer;

/* renamed from: com.sinovatech.unicom.ui.ScreenshotHandler */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScreenshotHandler extends Handler {
    private Activity activityContext;

    /* renamed from: am */
    public ActivityManager f18715am;
    private Handler handler;
    private Handler handlerScreen;
    private PopupWindow popupScreenshotWindow;
    private PopupWindow popupWindow;
    private Runnable runnable;
    private Runnable runnableScreen;
    private ThumbnailLoader thumbnailLoader;
    private long time;
    private Timer timer = new Timer();

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.ui.ScreenshotHandler$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC97313 implements View.OnClickListener {
        final /* synthetic */ ScreenshotHandler this$0;
        final /* synthetic */ String val$screenshopPath;

        View$OnClickListenerC97313(ScreenshotHandler screenshotHandler, String str) {
            JniLib.m15918cV(this, screenshotHandler, str, 334);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JniLib.m15918cV(this, view, 333);
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.ScreenshotHandler$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97324 implements PopupWindow.OnDismissListener {
        final /* synthetic */ ScreenshotHandler this$0;

        C97324(ScreenshotHandler screenshotHandler) {
            JniLib.m15918cV(this, screenshotHandler, 336);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            JniLib.m15918cV(this, 335);
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.ScreenshotHandler$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnTouchListenerC97335 implements View.OnTouchListener {
        final /* synthetic */ ScreenshotHandler this$0;

        View$OnTouchListenerC97335(ScreenshotHandler screenshotHandler) {
            JniLib.m15918cV(this, screenshotHandler, 338);
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return JniLib.m15917cZ(this, view, motionEvent, 337);
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.ScreenshotHandler$6 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97346 implements PopupWindow.OnDismissListener {
        final /* synthetic */ ScreenshotHandler this$0;

        C97346(ScreenshotHandler screenshotHandler) {
            JniLib.m15918cV(this, screenshotHandler, 340);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            JniLib.m15918cV(this, 339);
        }
    }

    private void showScreenshotShareHintDialog(String str, ComponentName componentName) {
        JniLib.m15918cV(this, str, componentName, 344);
    }

    private void showToCao(ComponentName componentName) {
        JniLib.m15918cV(this, componentName, 345);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        JniLib.m15918cV(this, message, 341);
    }

    public void hide() {
        JniLib.m15918cV(this, 342);
    }

    public void hideScreenshot() {
        JniLib.m15918cV(this, 343);
    }

    public ScreenshotHandler(Activity activity) {
        this.handler = null;
        this.handlerScreen = null;
        this.runnable = null;
        this.runnableScreen = null;
        this.popupWindow = null;
        this.popupScreenshotWindow = null;
        this.activityContext = activity;
        this.thumbnailLoader = new ThumbnailLoader(activity);
        this.handler = new Handler();
        this.handlerScreen = new Handler();
        this.popupWindow = new PopupWindow();
        this.popupScreenshotWindow = new PopupWindow();
        this.f18715am = (ActivityManager) activity.getSystemService("activity");
        this.runnable = new Runnable(this) { // from class: com.sinovatech.unicom.ui.ScreenshotHandler.1
            final /* synthetic */ ScreenshotHandler this$0;

            {
                JniLib.m15918cV(this, this, 331);
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$0.hide();
            }
        };
        this.runnableScreen = new Runnable(this) { // from class: com.sinovatech.unicom.ui.ScreenshotHandler.2
            final /* synthetic */ ScreenshotHandler this$0;

            {
                JniLib.m15918cV(this, this, 332);
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$0.hideScreenshot();
            }
        };
    }

    public static String getRealPathFromUri(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_data");
            cursor.moveToFirst();
            return cursor.getString(columnIndexOrThrow);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void postEventToH5() {
        List<Fragment> fragments;
        try {
            if (!(this.activityContext instanceof FragmentActivity) || (fragments = ((FragmentActivity) this.activityContext).getSupportFragmentManager().getFragments()) == null) {
                return;
            }
            for (Fragment fragment : fragments) {
                if ((fragment instanceof BaseWebFragment) && fragment.isVisible() && fragment.isAdded()) {
                    ((BaseWebFragment) fragment).postUserCaptureScreenEvent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
