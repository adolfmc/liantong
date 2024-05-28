package com.sinovatech.unicom.separatemodule.cropimg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CuttingImageActivity extends Activity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private TextView mCancel;
    private TextView mClip;
    private ClipImageView mClipImageView;
    private int mDegree;
    private ProgressDialog mDialog;
    private String mInput;
    private int mMaxWidth;
    private String mOutput;
    private int mSampleSize;
    private int mSourceHeight;
    private int mSourceWidth;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 80);
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
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
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

    private void setImageAndClipParams() {
        this.mClipImageView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.cropimg.CuttingImageActivity.1
            @Override // java.lang.Runnable
            public void run() {
                CuttingImageActivity.this.mClipImageView.setMaxOutputWidth(CuttingImageActivity.this.mMaxWidth);
                CuttingImageActivity cuttingImageActivity = CuttingImageActivity.this;
                cuttingImageActivity.mDegree = CuttingImageActivity.readPictureDegree(cuttingImageActivity.mInput);
                boolean z = CuttingImageActivity.this.mDegree == 90 || CuttingImageActivity.this.mDegree == 270;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(CuttingImageActivity.this.mInput, options);
                CuttingImageActivity.this.mSourceWidth = options.outWidth;
                CuttingImageActivity.this.mSourceHeight = options.outHeight;
                int i = z ? options.outHeight : options.outWidth;
                CuttingImageActivity cuttingImageActivity2 = CuttingImageActivity.this;
                cuttingImageActivity2.mSampleSize = CuttingImageActivity.findBestSample(i, cuttingImageActivity2.mClipImageView.getClipBorder().width());
                options.inJustDecodeBounds = false;
                options.inSampleSize = CuttingImageActivity.this.mSampleSize;
                Bitmap decodeFile = BitmapFactory.decodeFile(CuttingImageActivity.this.mInput, options);
                if (CuttingImageActivity.this.mDegree != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(CuttingImageActivity.this.mDegree);
                    Bitmap createBitmap = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, false);
                    if (createBitmap != decodeFile && !decodeFile.isRecycled()) {
                        decodeFile.recycle();
                    }
                    decodeFile = createBitmap;
                }
                CuttingImageActivity.this.mClipImageView.setImageBitmap(decodeFile);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int findBestSample(int i, int i2) {
        int i3 = 1;
        for (int i4 = i / 2; i4 > i2; i4 /= 2) {
            i3 *= 2;
        }
        return i3;
    }

    public static int readPictureDegree(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    if (attributeInt != 8) {
                        return 0;
                    }
                    return SubsamplingScaleImageView.ORIENTATION_270;
                }
                return 90;
            }
            return 180;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131296569) {
            onBackPressed();
        }
        if (id == 2131296670) {
            clipImage();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private void clipImage() {
        try {
            if (this.mOutput != null) {
                this.mDialog.show();
                new AsyncTask<Void, Void, Void>() { // from class: com.sinovatech.unicom.separatemodule.cropimg.CuttingImageActivity.2
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public Void doInBackground(Void... voidArr) {
                        ByteArrayOutputStream byteArrayOutputStream;
                        FileOutputStream fileOutputStream;
                        ByteArrayOutputStream byteArrayOutputStream2;
                        try {
                            try {
                                try {
                                    fileOutputStream = new FileOutputStream(new File(CuttingImageActivity.this.mOutput));
                                } catch (Exception e) {
                                    e = e;
                                    fileOutputStream = null;
                                    byteArrayOutputStream2 = null;
                                } catch (Throwable th) {
                                    byteArrayOutputStream = null;
                                    th = th;
                                    fileOutputStream = null;
                                }
                                try {
                                    byteArrayOutputStream2 = new ByteArrayOutputStream();
                                } catch (Exception e2) {
                                    e = e2;
                                    byteArrayOutputStream2 = null;
                                } catch (Throwable th2) {
                                    byteArrayOutputStream = null;
                                    th = th2;
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                            throw th;
                                        }
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    throw th;
                                }
                                try {
                                    Bitmap createClippedBitmap = CuttingImageActivity.this.createClippedBitmap();
                                    createClippedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream2);
                                    if (!createClippedBitmap.isRecycled()) {
                                        createClippedBitmap.recycle();
                                    }
                                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                                    fileOutputStream.write(byteArray);
                                    byteArrayOutputStream2.flush();
                                    fileOutputStream.flush();
                                    Base64.encodeToString(byteArray, 0);
                                    CuttingImageActivity.this.getIntent();
                                    CuttingImageActivity.this.setResult(-1, CuttingImageActivity.this.getIntent());
                                    byteArrayOutputStream2.close();
                                    fileOutputStream.close();
                                } catch (Exception e4) {
                                    e = e4;
                                    Looper.prepare();
                                    UIUtils.toast("裁剪失败" + e.getMessage());
                                    Looper.loop();
                                    if (byteArrayOutputStream2 != null) {
                                        byteArrayOutputStream2.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    return null;
                                }
                            } catch (Exception e5) {
                                e5.printStackTrace();
                            }
                            return null;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(Void r1) {
                        CuttingImageActivity.this.mDialog.dismiss();
                        CuttingImageActivity.this.finish();
                    }
                }.executeOnExecutor(Executors.newCachedThreadPool(), new Void[0]);
            } else {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap createClippedBitmap() {
        if (this.mSampleSize <= 1) {
            return this.mClipImageView.clip();
        }
        float[] clipMatrixValues = this.mClipImageView.getClipMatrixValues();
        float f = clipMatrixValues[0];
        float f2 = clipMatrixValues[2];
        float f3 = clipMatrixValues[5];
        Rect clipBorder = this.mClipImageView.getClipBorder();
        float f4 = (((-f2) + clipBorder.left) / f) * this.mSampleSize;
        float f5 = (((-f3) + clipBorder.top) / f) * this.mSampleSize;
        float width = (clipBorder.width() / f) * this.mSampleSize;
        Rect realRect = getRealRect(new RectF(f4, f5, f4 + width, ((clipBorder.height() / f) * this.mSampleSize) + f5));
        BitmapFactory.Options options = new BitmapFactory.Options();
        Matrix matrix = new Matrix();
        matrix.setRotate(this.mDegree);
        int i = this.mMaxWidth;
        if (i > 0 && width > i) {
            options.inSampleSize = findBestSample((int) width, i);
            float f6 = this.mMaxWidth / (width / options.inSampleSize);
            matrix.postScale(f6, f6);
        }
        BitmapRegionDecoder bitmapRegionDecoder = null;
        try {
            try {
                bitmapRegionDecoder = BitmapRegionDecoder.newInstance(this.mInput, false);
                Bitmap decodeRegion = bitmapRegionDecoder.decodeRegion(realRect, options);
                recycleImageViewBitmap();
                Bitmap createBitmap = Bitmap.createBitmap(decodeRegion, 0, 0, decodeRegion.getWidth(), decodeRegion.getHeight(), matrix, false);
                if (bitmapRegionDecoder != null && !bitmapRegionDecoder.isRecycled()) {
                    bitmapRegionDecoder.recycle();
                }
                return createBitmap;
            } catch (Exception unused) {
                Bitmap clip = this.mClipImageView.clip();
                if (bitmapRegionDecoder != null && !bitmapRegionDecoder.isRecycled()) {
                    bitmapRegionDecoder.recycle();
                }
                return clip;
            }
        } catch (Throwable th) {
            if (bitmapRegionDecoder != null && !bitmapRegionDecoder.isRecycled()) {
                bitmapRegionDecoder.recycle();
            }
            throw th;
        }
    }

    private Rect getRealRect(RectF rectF) {
        int i = this.mDegree;
        if (i != 90) {
            if (i != 180) {
                if (i == 270) {
                    return new Rect((int) (this.mSourceWidth - rectF.bottom), (int) rectF.left, (int) (this.mSourceWidth - rectF.top), (int) rectF.right);
                }
                return new Rect((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            }
            return new Rect((int) (this.mSourceWidth - rectF.right), (int) (this.mSourceHeight - rectF.bottom), (int) (this.mSourceWidth - rectF.left), (int) (this.mSourceHeight - rectF.top));
        }
        return new Rect((int) rectF.top, (int) (this.mSourceHeight - rectF.right), (int) rectF.bottom, (int) (this.mSourceHeight - rectF.left));
    }

    private void recycleImageViewBitmap() {
        this.mClipImageView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.cropimg.CuttingImageActivity.3
            @Override // java.lang.Runnable
            public void run() {
                CuttingImageActivity.this.mClipImageView.setImageBitmap(null);
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        setResult(0, getIntent());
        super.onBackPressed();
    }

    public static ClipOptions prepare() {
        return new ClipOptions();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ClipOptions {
        private int aspectX;
        private int aspectY;
        private String inputPath;
        private int maxWidth;
        private String outputPath;
        private String tip;

        private ClipOptions() {
        }

        public ClipOptions aspectX(int i) {
            this.aspectX = i;
            return this;
        }

        public ClipOptions aspectY(int i) {
            this.aspectY = i;
            return this;
        }

        public ClipOptions maxWidth(int i) {
            this.maxWidth = i;
            return this;
        }

        public ClipOptions tip(String str) {
            this.tip = str;
            return this;
        }

        public ClipOptions inputPath(String str) {
            this.inputPath = str;
            return this;
        }

        public ClipOptions outputPath(String str) {
            this.outputPath = str;
            return this;
        }

        public int getAspectX() {
            return this.aspectX;
        }

        public int getAspectY() {
            return this.aspectY;
        }

        public int getMaxWidth() {
            return this.maxWidth;
        }

        public String getTip() {
            return this.tip;
        }

        public String getInputPath() {
            return this.inputPath;
        }

        public String getOutputPath() {
            return this.outputPath;
        }

        public void startForResult(Activity activity, int i) {
            checkValues();
            Intent intent = new Intent(activity, CuttingImageActivity.class);
            intent.putExtra("aspectX", this.aspectX);
            intent.putExtra("aspectY", this.aspectY);
            intent.putExtra("maxWidth", this.maxWidth);
            intent.putExtra("tip", this.tip);
            intent.putExtra("inputPath", this.inputPath);
            intent.putExtra("outputPath", this.outputPath);
            activity.startActivityForResult(intent, i);
        }

        public Intent getIntent(Activity activity) {
            checkValues();
            Intent intent = new Intent(activity, CuttingImageActivity.class);
            intent.putExtra("aspectX", this.aspectX);
            intent.putExtra("aspectY", this.aspectY);
            intent.putExtra("maxWidth", this.maxWidth);
            intent.putExtra("tip", this.tip);
            intent.putExtra("inputPath", this.inputPath);
            intent.putExtra("outputPath", this.outputPath);
            return intent;
        }

        private void checkValues() {
            if (TextUtils.isEmpty(this.inputPath)) {
                throw new IllegalArgumentException("The input path could not be empty");
            }
            if (TextUtils.isEmpty(this.outputPath)) {
                throw new IllegalArgumentException("The output path could not be empty");
            }
        }

        public static ClipOptions createFromBundle(Intent intent) {
            return new ClipOptions().aspectX(intent.getIntExtra("aspectX", 1)).aspectY(intent.getIntExtra("aspectY", 1)).maxWidth(intent.getIntExtra("maxWidth", 0)).tip(intent.getStringExtra("tip")).inputPath(intent.getStringExtra("inputPath")).outputPath(intent.getStringExtra("outputPath"));
        }
    }
}
