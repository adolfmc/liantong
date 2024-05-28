package com.sinovatech.unicom.separatemodule.idcard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.megvii.idcardlib.util.DialogUtil;
import com.megvii.idcardlib.util.ICamera;
import com.megvii.idcardlib.util.IDCardIndicator;
import com.megvii.idcardlib.util.IDCardNewIndicator;
import com.megvii.idcardlib.util.RotaterUtil;
import com.megvii.idcardlib.util.Util;
import com.megvii.idcardquality.IDCardQualityAssessment;
import com.megvii.idcardquality.IDCardQualityResult;
import com.megvii.idcardquality.bean.IDCardAttr;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class IDCardScanActivity extends Activity implements Camera.PreviewCallback, TextureView.SurfaceTextureListener {
    public NBSTraceUnit _nbs_trace;
    private TextView horizontalTitle;
    private ImageView imgBack;
    private DialogUtil mDialogUtil;
    private BlockingQueue<byte[]> mFrameDataQueue;
    private ICamera mICamera;
    private IDCardIndicator mIdCardIndicator;
    private float mInBound;
    private float mIsIDCard;
    private IDCardNewIndicator mNewIndicatorView;
    private IDCardAttr.IDCardSide mSide;
    private TextureView textureView;
    private TextView verticalTitle;
    private Vibrator vibrator;
    private IDCardQualityAssessment idCardQualityAssessment = null;
    private DecodeThread mDecoder = null;
    private boolean mIsVertical = false;
    int continuousClickCount = 0;
    long lastClickMillis = 0;
    private boolean mHasSurface = false;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 86);
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

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    private void init() {
        this.vibrator = (Vibrator) getSystemService("vibrator");
        this.mSide = getIntent().getIntExtra("side", 0) == 0 ? IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT : IDCardAttr.IDCardSide.IDCARD_SIDE_BACK;
        this.mIsVertical = getIntent().getBooleanExtra("isvertical", false);
        this.mICamera = new ICamera(this.mIsVertical);
        this.mDialogUtil = new DialogUtil(this);
        this.textureView = (TextureView) findViewById(2131297252);
        this.textureView.setSurfaceTextureListener(this);
        this.textureView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.idcard.IDCardScanActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IDCardScanActivity.this.mICamera.autoFocus();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.horizontalTitle = (TextView) findViewById(2131297246);
        this.verticalTitle = (TextView) findViewById(2131297253);
        this.mFrameDataQueue = new LinkedBlockingDeque(1);
        this.mNewIndicatorView = (IDCardNewIndicator) findViewById(2131297251);
        this.mIdCardIndicator = (IDCardIndicator) findViewById(2131297250);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.idcard.IDCardScanActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IDCardScanActivity.this.mICamera.autoFocus();
                NBSActionInstrumentation.onClickEventExit();
            }
        };
        this.mNewIndicatorView.setOnClickListener(onClickListener);
        this.mIdCardIndicator.setOnClickListener(onClickListener);
        if (this.mIsVertical) {
            this.horizontalTitle.setVisibility(8);
            this.verticalTitle.setVisibility(0);
            this.mIdCardIndicator.setVisibility(0);
            this.mNewIndicatorView.setVisibility(8);
            this.mIdCardIndicator.setCardSideAndOrientation(this.mIsVertical, this.mSide);
            this.mNewIndicatorView.setCardSideAndOrientation(this.mIsVertical, this.mSide);
            setRequestedOrientation(1);
        } else {
            this.horizontalTitle.setVisibility(0);
            this.verticalTitle.setVisibility(8);
            this.mIdCardIndicator.setVisibility(8);
            this.mNewIndicatorView.setVisibility(0);
            this.mIdCardIndicator.setCardSideAndOrientation(this.mIsVertical, this.mSide);
            this.mNewIndicatorView.setCardSideAndOrientation(this.mIsVertical, this.mSide);
            setRequestedOrientation(0);
        }
        if (this.mDecoder == null) {
            this.mDecoder = new DecodeThread();
        }
        if (!this.mDecoder.isAlive()) {
            this.mDecoder.start();
        }
        this.imgBack = (ImageView) findViewById(2131297245);
        this.imgBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.idcard.IDCardScanActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IDCardScanActivity iDCardScanActivity = IDCardScanActivity.this;
                iDCardScanActivity.setResult(0, iDCardScanActivity.getIntent());
                Util.cancleToast(IDCardScanActivity.this);
                IDCardScanActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    private void initData() {
        this.idCardQualityAssessment = new IDCardQualityAssessment.Builder().setIsIgnoreShadow(true).setIsIgnoreHighlight(false).build();
        if (!this.idCardQualityAssessment.init(this, Util.readModel(this))) {
            this.mDialogUtil.showDialog("检测器初始化失败");
            return;
        }
        this.mInBound = this.idCardQualityAssessment.mInBound;
        this.mIsIDCard = this.idCardQualityAssessment.mIsIdcard;
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        try {
            if (this.mICamera != null) {
                this.mICamera.closeCamera();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("释放相机：" + e.getMessage());
        }
        try {
            setResult(0, getIntent());
            Util.cancleToast(this);
            finish();
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        DialogUtil dialogUtil = this.mDialogUtil;
        if (dialogUtil != null) {
            dialogUtil.onDestory();
        }
        try {
            if (this.mDecoder != null) {
                this.mDecoder.interrupt();
                this.mDecoder.join();
                this.mDecoder = null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (this.idCardQualityAssessment != null) {
                this.idCardQualityAssessment.release();
                this.idCardQualityAssessment = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (this.mICamera != null) {
                this.mICamera.closeCamera();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            MsLogUtil.m7978e("释放相机：" + e3.getMessage());
        }
    }

    private void doPreview() {
        if (this.mHasSurface) {
            this.mICamera.startPreview(this.textureView.getSurfaceTexture());
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.mICamera.openCamera(this) != null) {
            RelativeLayout.LayoutParams layoutParam = this.mICamera.getLayoutParam(this);
            this.textureView.setLayoutParams(layoutParam);
            this.mNewIndicatorView.setLayoutParams(layoutParam);
            this.mIdCardIndicator.setLayoutParams(layoutParam);
            this.mHasSurface = true;
            doPreview();
            this.mICamera.actionDetect(this);
            return;
        }
        this.mDialogUtil.showDialog("打开摄像头失败");
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mICamera.closeCamera();
        this.mHasSurface = false;
        return false;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.mFrameDataQueue.offer(bArr);
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class DecodeThread extends Thread {
        private Handler handler;
        boolean mHasSuccess;

        private DecodeThread() {
            this.mHasSuccess = false;
            this.handler = new Handler(Looper.getMainLooper());
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i;
            int i2;
            while (true) {
                try {
                    byte[] bArr = (byte[]) IDCardScanActivity.this.mFrameDataQueue.take();
                    if (bArr == null || this.mHasSuccess) {
                        return;
                    }
                    int i3 = IDCardScanActivity.this.mICamera.cameraWidth;
                    int i4 = IDCardScanActivity.this.mICamera.cameraHeight;
                    byte[] rotate = RotaterUtil.rotate(bArr, i3, i4, IDCardScanActivity.this.mICamera.getCameraAngle(IDCardScanActivity.this));
                    if (IDCardScanActivity.this.mIsVertical) {
                        i = IDCardScanActivity.this.mICamera.cameraHeight;
                        i2 = IDCardScanActivity.this.mICamera.cameraWidth;
                    } else {
                        i = i3;
                        i2 = i4;
                    }
                    RectF position = !IDCardScanActivity.this.mIsVertical ? IDCardScanActivity.this.mNewIndicatorView.getPosition() : IDCardScanActivity.this.mIdCardIndicator.getPosition();
                    Rect rect = new Rect();
                    float f = i;
                    rect.left = (int) (position.left * f);
                    float f2 = i2;
                    rect.top = (int) (position.top * f2);
                    rect.right = (int) (position.right * f);
                    rect.bottom = (int) (position.bottom * f2);
                    if (!IDCardScanActivity.this.isEven01(rect.left)) {
                        rect.left++;
                    }
                    if (!IDCardScanActivity.this.isEven01(rect.top)) {
                        rect.top++;
                    }
                    if (!IDCardScanActivity.this.isEven01(rect.right)) {
                        rect.right--;
                    }
                    if (!IDCardScanActivity.this.isEven01(rect.bottom)) {
                        rect.bottom--;
                    }
                    final IDCardQualityResult quality = IDCardScanActivity.this.idCardQualityAssessment.getQuality(rotate, i, i2, IDCardScanActivity.this.mSide, rect);
                    if (quality != null) {
                        if (quality.attr != null) {
                            float f3 = quality.attr.inBound;
                            if (quality.attr.isIdcard <= IDCardScanActivity.this.mIsIDCard || f3 <= 0.0f) {
                                if (!IDCardScanActivity.this.mIsVertical) {
                                    IDCardScanActivity.this.mNewIndicatorView.setBackColor(IDCardScanActivity.this, 0);
                                } else {
                                    IDCardScanActivity.this.mIdCardIndicator.setBackColor(IDCardScanActivity.this, 0);
                                }
                            } else if (!IDCardScanActivity.this.mIsVertical) {
                                IDCardScanActivity.this.mNewIndicatorView.setBackColor(IDCardScanActivity.this, -1442840576);
                            } else {
                                IDCardScanActivity.this.mIdCardIndicator.setBackColor(IDCardScanActivity.this, -1442840576);
                            }
                        }
                        if (quality.isValid()) {
                            IDCardScanActivity.this.vibrator.vibrate(new long[]{0, 50, 50, 100, 50}, -1);
                            this.mHasSuccess = true;
                            handleSuccess(quality);
                            return;
                        }
                        if (!IDCardScanActivity.this.mIsVertical) {
                            IDCardScanActivity.this.mNewIndicatorView.setBackColor(IDCardScanActivity.this, 0);
                        } else {
                            IDCardScanActivity.this.mIdCardIndicator.setBackColor(IDCardScanActivity.this, 0);
                        }
                        this.handler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.idcard.IDCardScanActivity.DecodeThread.1
                            @Override // java.lang.Runnable
                            public void run() {
                                IDCardQualityResult iDCardQualityResult = quality;
                                List<IDCardQualityResult.IDCardFailedType> list = iDCardQualityResult == null ? null : iDCardQualityResult.fails;
                                if (list != null) {
                                    if (IDCardScanActivity.this.mIsVertical) {
                                        IDCardScanActivity.this.verticalTitle.setText(Util.errorType2HumanStr(list.get(0), IDCardScanActivity.this.mSide));
                                        return;
                                    } else {
                                        IDCardScanActivity.this.horizontalTitle.setText(Util.errorType2HumanStr(list.get(0), IDCardScanActivity.this.mSide));
                                        return;
                                    }
                                }
                                IDCardScanActivity.this.verticalTitle.setText("");
                                IDCardScanActivity.this.horizontalTitle.setText("");
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        private void handleSuccess(IDCardQualityResult iDCardQualityResult) {
            Intent intent = new Intent();
            intent.putExtra("side", IDCardScanActivity.this.mSide == IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT ? 0 : 1);
            intent.putExtra("idcardImg", Util.bmp2byteArr(iDCardQualityResult.croppedImageOfIDCard()));
            IDCardScanActivity.this.setResult(-1, intent);
            Util.cancleToast(IDCardScanActivity.this);
            IDCardScanActivity.this.finish();
        }
    }

    public boolean isEven01(int i) {
        return i % 2 == 0;
    }
}
