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
import android.text.TextUtils;
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
import java.io.IOException;
import java.util.concurrent.Executors;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ClipImageActivity extends Activity implements View.OnClickListener {
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
        JniLib.m15918cV(this, bundle, 79);
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
        this.mClipImageView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.1
            @Override // java.lang.Runnable
            public void run() {
                ClipImageActivity.this.mClipImageView.setMaxOutputWidth(ClipImageActivity.this.mMaxWidth);
                ClipImageActivity clipImageActivity = ClipImageActivity.this;
                clipImageActivity.mDegree = ClipImageActivity.readPictureDegree(clipImageActivity.mInput);
                boolean z = ClipImageActivity.this.mDegree == 90 || ClipImageActivity.this.mDegree == 270;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(ClipImageActivity.this.mInput, options);
                ClipImageActivity.this.mSourceWidth = options.outWidth;
                ClipImageActivity.this.mSourceHeight = options.outHeight;
                int i = z ? options.outHeight : options.outWidth;
                ClipImageActivity clipImageActivity2 = ClipImageActivity.this;
                clipImageActivity2.mSampleSize = ClipImageActivity.findBestSample(i, clipImageActivity2.mClipImageView.getClipBorder().width());
                options.inJustDecodeBounds = false;
                options.inSampleSize = ClipImageActivity.this.mSampleSize;
                Bitmap decodeFile = BitmapFactory.decodeFile(ClipImageActivity.this.mInput, options);
                if (ClipImageActivity.this.mDegree != 0) {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(ClipImageActivity.this.mDegree);
                    Bitmap createBitmap = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, false);
                    if (createBitmap != decodeFile && !decodeFile.isRecycled()) {
                        decodeFile.recycle();
                    }
                    decodeFile = createBitmap;
                }
                ClipImageActivity.this.mClipImageView.setImageBitmap(decodeFile);
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
                new AsyncTask<Void, Void, Void>() { // from class: com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.2
                    /* JADX INFO: Access modifiers changed from: protected */
                    /* JADX WARN: Removed duplicated region for block: B:37:0x009a A[Catch: Exception -> 0x0096, TRY_LEAVE, TryCatch #3 {Exception -> 0x0096, blocks: (B:33:0x0092, B:37:0x009a), top: B:43:0x0092 }] */
                    /* JADX WARN: Removed duplicated region for block: B:43:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                    @Override // android.os.AsyncTask
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public java.lang.Void doInBackground(java.lang.Void... r7) {
                        /*
                            r6 = this;
                            r7 = 0
                            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
                            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
                            com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity r2 = com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.this     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
                            java.lang.String r2 = com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.access$800(r2)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
                            r1.<init>(r2)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
                            r0.<init>(r1)     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L5b
                            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
                            r1.<init>()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L53
                            com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity r2 = com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.this     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            android.graphics.Bitmap r2 = com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.access$900(r2)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            r4 = 80
                            r2.compress(r3, r4, r1)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            boolean r3 = r2.isRecycled()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            if (r3 != 0) goto L2c
                            r2.recycle()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                        L2c:
                            byte[] r2 = r1.toByteArray()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            r0.write(r2)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            r1.flush()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            r0.flush()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity r2 = com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.this     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            r3 = -1
                            com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity r4 = com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.this     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            android.content.Intent r4 = r4.getIntent()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            r2.setResult(r3, r4)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L8f
                            r1.close()     // Catch: java.lang.Exception -> L83
                            r0.close()     // Catch: java.lang.Exception -> L83
                            goto L8e
                        L4c:
                            r2 = move-exception
                            goto L5e
                        L4e:
                            r1 = move-exception
                            r5 = r1
                            r1 = r7
                            r7 = r5
                            goto L90
                        L53:
                            r2 = move-exception
                            r1 = r7
                            goto L5e
                        L56:
                            r0 = move-exception
                            r1 = r7
                            r7 = r0
                            r0 = r1
                            goto L90
                        L5b:
                            r2 = move-exception
                            r0 = r7
                            r1 = r0
                        L5e:
                            android.os.Looper.prepare()     // Catch: java.lang.Throwable -> L8f
                            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8f
                            r3.<init>()     // Catch: java.lang.Throwable -> L8f
                            java.lang.String r4 = "裁剪失败"
                            r3.append(r4)     // Catch: java.lang.Throwable -> L8f
                            java.lang.String r2 = r2.getMessage()     // Catch: java.lang.Throwable -> L8f
                            r3.append(r2)     // Catch: java.lang.Throwable -> L8f
                            java.lang.String r2 = r3.toString()     // Catch: java.lang.Throwable -> L8f
                            com.sinovatech.unicom.common.UIUtils.toast(r2)     // Catch: java.lang.Throwable -> L8f
                            android.os.Looper.loop()     // Catch: java.lang.Throwable -> L8f
                            if (r1 == 0) goto L85
                            r1.close()     // Catch: java.lang.Exception -> L83
                            goto L85
                        L83:
                            r0 = move-exception
                            goto L8b
                        L85:
                            if (r0 == 0) goto L8e
                            r0.close()     // Catch: java.lang.Exception -> L83
                            goto L8e
                        L8b:
                            r0.printStackTrace()
                        L8e:
                            return r7
                        L8f:
                            r7 = move-exception
                        L90:
                            if (r1 == 0) goto L98
                            r1.close()     // Catch: java.lang.Exception -> L96
                            goto L98
                        L96:
                            r0 = move-exception
                            goto L9e
                        L98:
                            if (r0 == 0) goto La1
                            r0.close()     // Catch: java.lang.Exception -> L96
                            goto La1
                        L9e:
                            r0.printStackTrace()
                        La1:
                            throw r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.AsyncTaskC86472.doInBackground(java.lang.Void[]):java.lang.Void");
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(Void r1) {
                        ClipImageActivity.this.mDialog.dismiss();
                        ClipImageActivity.this.finish();
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
        this.mClipImageView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity.3
            @Override // java.lang.Runnable
            public void run() {
                ClipImageActivity.this.mClipImageView.setImageBitmap(null);
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
            Intent intent = new Intent(activity, ClipImageActivity.class);
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
            Intent intent = new Intent(activity, ClipImageActivity.class);
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
