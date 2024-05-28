package com.sinovatech.unicom.separatemodule.idcard.camera1.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.megvii.idcardlib.util.Util;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.idcard.camera1.utils.CameraScreenUtil;
import com.sinovatech.unicom.separatemodule.idcard.newaction.activity.ShangChuanIdCardActivity;
import com.sinovatech.unicom.separatemodule.idcard.newaction.utils.NewCamera2Utils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PhotographActivity extends Activity implements SurfaceHolder.Callback, View.OnClickListener {
    private static final int IMAGE_SELECT = 1;
    private static final int MIN_DELAY_TIME = 1000;
    private static final String TAG = "【相机Camera1】";
    private static long lastClickTime = 0;
    public static String serviceName = "";
    public static String url = "";
    public NBSTraceUnit _nbs_trace;
    private Activity activityContext;
    private Camera camera;
    private Point cameraResolution;
    private ImageView camera_back;
    private int centerHeight;
    private int centerLeftWidth;
    private int centerWidth;
    private FrameLayout control;
    private int height;
    private ImageView lin_center;
    private LinearLayout lin_left;
    private ImageView paizhao;
    private Camera.Parameters parameters;

    /* renamed from: pd */
    private ProgressDialog f18540pd;
    private RelativeLayout rel_top;
    private Point screenResolution;
    private SurfaceView surfaceView;
    private int topHeight;
    private int width;
    private ImageView xiangce;
    private SurfaceHolder surfaceHolder = null;
    private int mPreviewWidth = 0;
    private int mPreviewHeight = 0;
    private boolean isZhedie = false;
    Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.7
        @Override // android.hardware.Camera.ShutterCallback
        public void onShutter() {
        }
    };
    Camera.PictureCallback rawCallback = new Camera.PictureCallback() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.8
        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
        }
    };
    Camera.PictureCallback jpegCallback = new Camera.PictureCallback() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.9
        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            Bitmap createScaledBitmap;
            Bitmap Bytes2Bimap = bArr != null ? PhotographActivity.Bytes2Bimap(bArr) : null;
            if (Bytes2Bimap != null) {
                try {
                    int width = Bytes2Bimap.getWidth();
                    int height = Bytes2Bimap.getHeight();
                    MsLogUtil.m7979d(PhotographActivity.TAG, "图片原图，的bitmap宽：" + width + ",bitmap高：" + height);
                    if (height > PhotographActivity.this.width && width > PhotographActivity.this.height) {
                        int i = (int) (height * (PhotographActivity.this.height / width));
                        createScaledBitmap = Bitmap.createScaledBitmap(Bytes2Bimap, PhotographActivity.this.height, i, true);
                        if (i > PhotographActivity.this.width) {
                            createScaledBitmap = Bitmap.createBitmap(createScaledBitmap, 0, (i - PhotographActivity.this.width) / 2, PhotographActivity.this.height, PhotographActivity.this.width);
                        } else {
                            PhotographActivity.this.centerLeftWidth = (i - PhotographActivity.this.centerWidth) / 2;
                        }
                    } else {
                        Bitmap big = NewCamera2Utils.big(Bytes2Bimap, PhotographActivity.this.height / width);
                        int height2 = (int) (big.getHeight() * (PhotographActivity.this.height / big.getWidth()));
                        createScaledBitmap = Bitmap.createScaledBitmap(big, PhotographActivity.this.height, height2, true);
                        if (height2 > PhotographActivity.this.width) {
                            createScaledBitmap = Bitmap.createBitmap(createScaledBitmap, 0, (height2 - PhotographActivity.this.width) / 2, PhotographActivity.this.height, PhotographActivity.this.width);
                        } else {
                            PhotographActivity.this.centerLeftWidth = (height2 - PhotographActivity.this.centerWidth) / 2;
                        }
                    }
                    Bytes2Bimap = Bitmap.createBitmap(createScaledBitmap, PhotographActivity.this.topHeight, PhotographActivity.this.centerLeftWidth, PhotographActivity.this.centerHeight, PhotographActivity.this.centerWidth);
                } catch (Exception e) {
                    MsLogUtil.m7977e(PhotographActivity.TAG, e.getMessage());
                    int width2 = Bytes2Bimap.getWidth();
                    int height3 = Bytes2Bimap.getHeight();
                    if (height3 > PhotographActivity.this.height && width2 > PhotographActivity.this.width) {
                        Bytes2Bimap = Bitmap.createScaledBitmap(Bytes2Bimap, (int) (width2 * (PhotographActivity.this.height / height3)), PhotographActivity.this.height, true);
                    }
                }
                Bitmap rotateBitmap = NewCamera2Utils.rotateBitmap(Bytes2Bimap, 90);
                int width3 = rotateBitmap.getWidth();
                int height4 = rotateBitmap.getHeight();
                while (width3 * height4 < 480000) {
                    rotateBitmap = NewCamera2Utils.big(rotateBitmap);
                    width3 = rotateBitmap.getWidth();
                    height4 = rotateBitmap.getHeight();
                }
                NewCamera2Utils.bitmap = PhotographActivity.Bytes2Bimap(NewCamera2Utils.compressImage(rotateBitmap));
                MsLogUtil.m7979d(PhotographActivity.TAG, "最终bitmap宽：" + NewCamera2Utils.bitmap.getWidth() + ",bitmap高：" + NewCamera2Utils.bitmap.getHeight());
                UIUtils.dismissDialog(PhotographActivity.this.activityContext, PhotographActivity.this.f18540pd);
                PhotographActivity photographActivity = PhotographActivity.this;
                photographActivity.startActivityForResult(new Intent(photographActivity.activityContext, ShangChuanIdCardActivity.class), 2357);
            }
        }
    };

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 87);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Camera camera = this.camera;
        if (camera != null) {
            camera.stopPreview();
            this.camera.release();
            this.camera = null;
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        initCamera();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void initView() {
        this.mPreviewWidth = CameraScreenUtil.getScreenWidth();
        this.mPreviewHeight = CameraScreenUtil.getRealScreenHeight() + CameraScreenUtil.getStatusBarHeight();
        MsLogUtil.m7979d(TAG, "当前设置的屏幕宽高是:宽：" + this.mPreviewWidth + ",高：" + this.mPreviewHeight);
        this.screenResolution = new Point(this.mPreviewWidth, this.mPreviewHeight);
        if (UIUtils.getScreenWidth(this.activityContext) / UIUtils.getScreenHeight(this.activityContext) > 0.7d) {
            setContentView(2131493041);
            MsLogUtil.m7979d(TAG, "onCreateView--> ：当前是折叠屏");
            this.isZhedie = true;
        } else {
            setContentView(2131493040);
            MsLogUtil.m7979d(TAG, "onCreateView--> ：当前是正常手机");
            this.isZhedie = false;
        }
        this.control = (FrameLayout) findViewById(2131296709);
        this.rel_top = (RelativeLayout) findViewById(2131298277);
        this.lin_left = (LinearLayout) findViewById(2131297609);
        this.lin_center = (ImageView) findViewById(2131297602);
        this.camera_back = (ImageView) findViewById(2131296567);
        this.camera_back.setOnClickListener(this);
        this.paizhao = (ImageView) findViewById(2131298204);
        this.paizhao.setOnClickListener(this);
        this.xiangce = (ImageView) findViewById(2131299849);
        this.xiangce.setOnClickListener(this);
        this.surfaceView = (SurfaceView) findViewById(2131298715);
        this.surfaceView.setFocusable(true);
        this.surfaceView.setOnClickListener(this);
        this.surfaceView.setBackgroundColor(40);
        this.surfaceHolder = this.surfaceView.getHolder();
        this.surfaceHolder.setType(3);
        this.surfaceHolder.setKeepScreenOn(true);
        this.surfaceHolder.setFixedSize(this.mPreviewWidth, this.mPreviewHeight);
        this.surfaceHolder.addCallback(this);
        this.control = (FrameLayout) findViewById(2131296709);
        this.rel_top = (RelativeLayout) findViewById(2131298277);
        this.lin_left = (LinearLayout) findViewById(2131297609);
        this.lin_center = (ImageView) findViewById(2131297602);
        this.control.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.1
            @Override // java.lang.Runnable
            public void run() {
                PhotographActivity photographActivity = PhotographActivity.this;
                photographActivity.width = photographActivity.control.getMeasuredWidth();
                PhotographActivity photographActivity2 = PhotographActivity.this;
                photographActivity2.height = photographActivity2.control.getMeasuredHeight();
                PhotographActivity photographActivity3 = PhotographActivity.this;
                photographActivity3.logHeightWidth("整体页面：", photographActivity3.width, PhotographActivity.this.height);
            }
        });
        this.rel_top.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.2
            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = PhotographActivity.this.rel_top.getMeasuredWidth();
                int measuredHeight = PhotographActivity.this.rel_top.getMeasuredHeight();
                PhotographActivity photographActivity = PhotographActivity.this;
                photographActivity.topHeight = photographActivity.rel_top.getMeasuredHeight();
                PhotographActivity.this.logHeightWidth("上半部分UI：", measuredWidth, measuredHeight);
            }
        });
        this.lin_left.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.3
            @Override // java.lang.Runnable
            public void run() {
                int measuredWidth = PhotographActivity.this.lin_left.getMeasuredWidth();
                int measuredHeight = PhotographActivity.this.lin_left.getMeasuredHeight();
                PhotographActivity photographActivity = PhotographActivity.this;
                photographActivity.centerLeftWidth = photographActivity.lin_left.getMeasuredWidth();
                PhotographActivity.this.logHeightWidth("中间左边：", measuredWidth, measuredHeight);
            }
        });
        this.lin_center.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.4
            @Override // java.lang.Runnable
            public void run() {
                PhotographActivity photographActivity = PhotographActivity.this;
                photographActivity.centerHeight = photographActivity.lin_center.getMeasuredHeight();
                PhotographActivity photographActivity2 = PhotographActivity.this;
                photographActivity2.centerWidth = photographActivity2.lin_center.getMeasuredWidth();
                PhotographActivity.this.logHeightWidth("中间部分：", PhotographActivity.this.lin_center.getMeasuredWidth(), PhotographActivity.this.lin_center.getMeasuredHeight());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logHeightWidth(String str, int i, int i2) {
        MsLogUtil.m7979d(TAG, str + "，打印宽高:宽：" + i + "，高:" + i2);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            if (this.camera != null) {
                this.camera.stopPreview();
                this.camera.release();
                this.camera = null;
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            if (this.camera == null || this.surfaceHolder == null) {
                return;
            }
            this.camera.setPreviewDisplay(this.surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        try {
            this.camera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.5
                @Override // android.hardware.Camera.AutoFocusCallback
                public void onAutoFocus(boolean z, Camera camera) {
                    if (z) {
                        try {
                            camera.cancelAutoFocus();
                            PhotographActivity.this.doAutoFocus();
                        } catch (Exception unused) {
                        }
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    private void initCamera() {
        try {
            if (this.mPreviewWidth == 0 || this.mPreviewHeight == 0) {
                this.mPreviewWidth = CameraScreenUtil.getScreenWidth();
                this.mPreviewHeight = CameraScreenUtil.getRealScreenHeight() + CameraScreenUtil.getStatusBarHeight();
                MsLogUtil.m7979d(TAG, "当前设置的屏幕宽高是:宽：" + this.mPreviewWidth + ",高：" + this.mPreviewHeight);
                this.screenResolution = new Point(this.mPreviewWidth, this.mPreviewHeight);
            }
            if (this.camera == null) {
                this.camera = Camera.open();
            }
            this.parameters = this.camera.getParameters();
            this.parameters.setPictureFormat(256);
            this.cameraResolution = getCameraResolution(this.parameters, this.screenResolution);
            this.parameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
            if (!Build.MODEL.equals("KORIDY H30")) {
                this.parameters.setFocusMode("continuous-picture");
            } else {
                this.parameters.setFocusMode("auto");
            }
            this.camera.setParameters(this.parameters);
            setDispaly(this.camera);
            this.camera.startPreview();
            this.camera.cancelAutoFocus();
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, e.getMessage());
        }
    }

    private void setDispaly(Camera camera) {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
            setDisplayOrientation(camera, 90);
        } else {
            this.parameters.setRotation(90);
        }
    }

    private void setDisplayOrientation(Camera camera, int i) {
        try {
            Method method = camera.getClass().getMethod("setDisplayOrientation", Integer.TYPE);
            if (method != null) {
                method.invoke(camera, Integer.valueOf(i));
            }
        } catch (Exception unused) {
            MsLogUtil.m7977e(TAG, "图像出错");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131296567) {
            handleFail(0);
        } else if (id != 2131298204) {
            if (id == 2131298715) {
                if (!isFastClick()) {
                    doAutoFocus();
                }
            } else if (id == 2131299849 && !isFastClick()) {
                PvCurrencyLogUtils.pvLogDJCamera2Api("相册按钮", "新方法", "拍摄身份证原件页面");
                openImg();
            }
        } else if (!isFastClick()) {
            PvCurrencyLogUtils.pvLogDJCamera2Api("拍照按钮", "新方法", "拍摄身份证原件页面");
            takePicture();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAutoFocus() {
        this.parameters = this.camera.getParameters();
        this.parameters.setFocusMode("auto");
        this.camera.setParameters(this.parameters);
        this.camera.autoFocus(new Camera.AutoFocusCallback() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.6
            @Override // android.hardware.Camera.AutoFocusCallback
            public void onAutoFocus(boolean z, Camera camera) {
                if (z) {
                    camera.cancelAutoFocus();
                    if (!Build.MODEL.equals("KORIDY H30")) {
                        PhotographActivity.this.parameters = camera.getParameters();
                        PhotographActivity.this.parameters.setFocusMode("continuous-picture");
                        camera.setParameters(PhotographActivity.this.parameters);
                        return;
                    }
                    PhotographActivity.this.parameters = camera.getParameters();
                    PhotographActivity.this.parameters.setFocusMode("auto");
                    camera.setParameters(PhotographActivity.this.parameters);
                }
            }
        });
    }

    private void takePicture() {
        try {
            if (this.f18540pd == null) {
                this.f18540pd = new CustomePorgressDialog(this.activityContext);
            }
            this.f18540pd.setMessage("裁剪中，请耐心等待");
            this.f18540pd.setCanceledOnTouchOutside(false);
            UIUtils.showDialog(this.activityContext, this.f18540pd);
            if (this.camera != null) {
                this.camera.takePicture(this.shutterCallback, this.rawCallback, this.jpegCallback);
            } else {
                UIUtils.dismissDialog(this.activityContext, this.f18540pd);
            }
        } catch (Exception unused) {
            UIUtils.dismissDialog(this.activityContext, this.f18540pd);
        }
    }

    public static Bitmap Bytes2Bimap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < 1000;
        lastClickTime = currentTimeMillis;
        return z;
    }

    private void openImg() {
        PermissionDialog.show("选择相册为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
        SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.10
            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onAllPermissionOk(Permission[] permissionArr) {
                PermissionDialog.dimissDialog();
                try {
                    Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    PhotographActivity.this.startActivityForResult(intent, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onPermissionDenied(Permission[] permissionArr) {
                try {
                    PermissionDialog.dimissDialog();
                    if (permissionArr.length > 0) {
                        if (TextUtils.equals("android.permission.READ_EXTERNAL_STORAGE", permissionArr[0].permissionName) || TextUtils.equals("android.permission.WRITE_EXTERNAL_STORAGE", permissionArr[0].permissionName)) {
                            UIUtils.toast("需要开启存储卡权限");
                        }
                    }
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                    UIUtils.toast("需要开启存储卡权限");
                }
            }
        });
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, final Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 1) {
                new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.idcard.camera1.activity.PhotographActivity.11
                    @Override // java.lang.Runnable
                    public void run() {
                        Uri data = intent.getData();
                        Bitmap ImageSizeCompress = NewCamera2Utils.ImageSizeCompress(PhotographActivity.this.activityContext, data);
                        PhotographActivity photographActivity = PhotographActivity.this;
                        String path = photographActivity.getPath(photographActivity.activityContext, data);
                        if (!TextUtils.isEmpty(path)) {
                            ImageSizeCompress = NewCamera2Utils.rotateBitmap(ImageSizeCompress, NewCamera2Utils.getExifOrientation(new File(path).getPath()));
                        }
                        if (ImageSizeCompress != null) {
                            NewCamera2Utils.bitmap = NewCamera2Utils.Bytes2Bimap(NewCamera2Utils.compressImage(ImageSizeCompress));
                        } else {
                            NewCamera2Utils.bitmap = null;
                        }
                        if (NewCamera2Utils.bitmap != null) {
                            PhotographActivity photographActivity2 = PhotographActivity.this;
                            photographActivity2.startActivityForResult(new Intent(photographActivity2.activityContext, ShangChuanIdCardActivity.class), 2357);
                        }
                    }
                });
            } else if (i == 2357) {
                if (i2 == -2) {
                    handleFail(-2);
                } else {
                    handleSuccess();
                }
            }
        }
    }

    @RequiresApi(api = 19)
    public String getPath(Context context, Uri uri) {
        try {
            if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
                if (FileTools.isExternalStorageDocument(uri)) {
                    String[] split = DocumentsContract.getDocumentId(uri).split(":");
                    if ("primary".equalsIgnoreCase(split[0])) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }
                } else if (!FileTools.isDownloadsDocument(uri)) {
                    FileTools.isMediaDocument(uri);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return FileTools.getDataColumn(context, uri, null, null);
            } else {
                "file".equalsIgnoreCase(uri.getScheme());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void handleSuccess() {
        if (NewCamera2Utils.bitmap != null) {
            Intent intent = new Intent();
            NewCamera2Utils.photoData = Util.bmp2byteArr(NewCamera2Utils.bitmap);
            setResult(-1, intent);
            finish();
        } else {
            handleFail(-2);
        }
        NewCamera2Utils.bitmap = null;
    }

    public void handleFail(int i) {
        setResult(i, getIntent());
        finish();
    }

    private static Point getCameraResolution(Camera.Parameters parameters, Point point) {
        String str = parameters.get("preview-size-values");
        if (str == null) {
            str = parameters.get("preview-size-value");
        }
        Point point2 = null;
        if (str != null) {
            MsLogUtil.m7979d(TAG, "preview-size-values parameter: " + str);
            point2 = getOptimalPreviewSize(true, parameters.getSupportedPreviewSizes(), point.x, point.y);
        }
        return point2 == null ? new Point((point.x >> 3) << 3, (point.y >> 3) << 3) : point2;
    }

    public static Point getOptimalPreviewSize(boolean z, List<Camera.Size> list, int i, int i2) {
        if (z) {
            i2 = i;
            i = i2;
        }
        for (Camera.Size size : list) {
            if (size.width == i && size.height == i2) {
                return new Point(size.width, size.height);
            }
        }
        float f = i / i2;
        float f2 = Float.MAX_VALUE;
        Camera.Size size2 = null;
        for (Camera.Size size3 : list) {
            float abs = Math.abs(f - (size3.width / size3.height));
            if (abs < f2) {
                size2 = size3;
                f2 = abs;
            }
        }
        return new Point(size2.width, size2.height);
    }
}
