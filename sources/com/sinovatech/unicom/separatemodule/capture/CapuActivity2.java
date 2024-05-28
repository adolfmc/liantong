package com.sinovatech.unicom.separatemodule.capture;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.king.zxing.CaptureActivity;
import com.king.zxing.camera.CameraManager;
import com.king.zxing.util.QRCodeParseUtils;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.FinishActivityEvent;
import com.sinovatech.unicom.basic.server.WoPayScanManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.capture.history.HistoryActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CapuActivity2 extends CaptureActivity {
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private ImageButton backButton;
    private CameraManager cameraManager;
    private boolean fromweb;
    protected Dialog helpDialog;
    private ImageView iv_info;
    private ImageView iv_light;
    private String source;
    private boolean torchState;
    private WoPayScanManager woPayScanManager;

    @Override // com.king.zxing.CaptureActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 77);
    }

    @Override // com.king.zxing.CaptureActivity, android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.king.zxing.CaptureActivity, android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // com.king.zxing.CaptureActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.king.zxing.CaptureActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.king.zxing.CaptureActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.capture.CapuActivity2$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC86191 implements View.OnClickListener {
        View$OnClickListenerC86191() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (CapuActivity2.this.helpDialog == null) {
                CapuActivity2 capuActivity2 = CapuActivity2.this;
                capuActivity2.helpDialog = new Dialog(capuActivity2.activityContext, 2131951913);
                CapuActivity2.this.helpDialog.setContentView(2131493411);
                CapuActivity2.this.helpDialog.setCanceledOnTouchOutside(true);
            }
            CapuActivity2.this.helpDialog.show();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.capture.CapuActivity2$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC86202 implements View.OnClickListener {
        View$OnClickListenerC86202() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            try {
                PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
                new RxPermissions(CapuActivity2.this.activityContext).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.capture.CapuActivity2.2.1
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        PermissionDialog.dimissDialog();
                        if (bool.booleanValue()) {
                            CapuActivity2.this.startPhotoCode();
                        } else {
                            UIUtils.toast("未开启存储卡权限");
                        }
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.capture.CapuActivity2.2.2
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        PermissionDialog.dimissDialog();
                        UIUtils.toast("未开启存储卡权限");
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.capture.CapuActivity2$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC86233 implements View.OnClickListener {
        View$OnClickListenerC86233() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (CapuActivity2.this.torchState) {
                CapuActivity2.this.torchState = false;
                CapuActivity2.this.cameraManager.setTorch(false);
                CapuActivity2.this.iv_light.setImageResource(2131232135);
            } else {
                CapuActivity2.this.torchState = true;
                CapuActivity2.this.cameraManager.setTorch(true);
                CapuActivity2.this.iv_light.setImageResource(2131232134);
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.capture.CapuActivity2$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC86244 implements View.OnClickListener {
        View$OnClickListenerC86244() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            CapuActivity2.this.startActivity(new Intent(CapuActivity2.this.activityContext, HistoryActivity.class));
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.capture.CapuActivity2$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC86255 implements View.OnClickListener {
        View$OnClickListenerC86255() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (CapuActivity2.this.fromweb) {
                CapuActivity2.this.setResult(0);
            }
            CapuActivity2.this.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPhotoCode() {
        try {
            Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.capture.CapuActivity2.6
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent2) {
                    if (intent2 != null) {
                        CapuActivity2.this.parsePhoto(intent2);
                    }
                }
            });
        } catch (Exception unused) {
            UIUtils.toast("打开相册失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parsePhoto(Intent intent) {
        try {
            String imagePath = UriUtils.getImagePath(this, intent);
            Log.d("Jenly", "path:" + imagePath);
            if (TextUtils.isEmpty(imagePath)) {
                UIUtils.toast("您选择的二维码有误");
            } else {
                parsePhoto(imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void asyncThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    @Override // com.king.zxing.CaptureActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            this.torchState = false;
            this.cameraManager.setTorch(false);
            this.iv_light.setImageResource(2131232135);
        } catch (Exception e) {
            e.printStackTrace();
            this.torchState = false;
            this.iv_light.setImageResource(2131232135);
        }
    }

    @Override // com.king.zxing.CaptureActivity
    public int getLayoutId() {
        if ((getResources().getConfiguration().screenLayout & 15) == 3) {
            return (("HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) || "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER) || "HONOR".equalsIgnoreCase(Build.MANUFACTURER) || "samsung".equalsIgnoreCase(Build.MANUFACTURER) || "OPPO".equalsIgnoreCase(Build.MANUFACTURER)) && Build.VERSION.SDK_INT >= 24 && !isInMultiWindowMode()) ? 2131493415 : 2131493414;
        }
        return 2131493414;
    }

    private void parsePhoto(String str) {
        try {
            new QrCodeAsyncTask(this, str).execute(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class QrCodeAsyncTask extends AsyncTask<String, Integer, String> {
        private WeakReference<Activity> mWeakReference;
        private String path;

        public QrCodeAsyncTask(Activity activity, String str) {
            this.mWeakReference = new WeakReference<>(activity);
            this.path = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(String... strArr) {
            return QRCodeParseUtils.syncDecodeQRCode(this.path);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            super.onPostExecute((QrCodeAsyncTask) str);
            try {
                CapuActivity2 capuActivity2 = (CapuActivity2) this.mWeakReference.get();
                if (capuActivity2 != null) {
                    capuActivity2.handleQrCode(str);
                }
            } catch (Exception e) {
                UIUtils.logE(e.getMessage());
            }
        }
    }

    public void handleQrCode(String str) {
        try {
            if (str == null) {
                UIUtils.toast("该图片无法识别二维码");
            } else {
                handleResult(str);
            }
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    private void handleResult(String str) {
        try {
            Intent intent = new Intent();
            intent.putExtra("SCAN_RESULT", str.trim());
            this.activityContext.setResult(-1, intent);
            this.activityContext.finish();
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    @Override // com.king.zxing.CaptureActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFinishEvent(FinishActivityEvent finishActivityEvent) {
        try {
            finish();
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }
}
