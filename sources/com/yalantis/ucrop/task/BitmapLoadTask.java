package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult> {
    private static final String TAG = "BitmapWorkerTask";
    private final BitmapLoadCallback mBitmapLoadCallback;
    private final Context mContext;
    private Uri mInputUri;
    private Uri mOutputUri;
    private final int mRequiredHeight;
    private final int mRequiredWidth;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class BitmapWorkerResult {
        Bitmap mBitmapResult;
        Exception mBitmapWorkerException;
        ExifInfo mExifInfo;

        public BitmapWorkerResult(@NonNull Bitmap bitmap, @NonNull ExifInfo exifInfo) {
            this.mBitmapResult = bitmap;
            this.mExifInfo = exifInfo;
        }

        public BitmapWorkerResult(@NonNull Exception exc) {
            this.mBitmapWorkerException = exc;
        }
    }

    public BitmapLoadTask(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        this.mContext = context;
        this.mInputUri = uri;
        this.mOutputUri = uri2;
        this.mRequiredWidth = i;
        this.mRequiredHeight = i2;
        this.mBitmapLoadCallback = bitmapLoadCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    @NonNull
    public BitmapWorkerResult doInBackground(Void... voidArr) {
        if (this.mInputUri == null) {
            return new BitmapWorkerResult(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            processInputUri();
            try {
                ParcelFileDescriptor openFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(this.mInputUri, "r");
                if (openFileDescriptor != null) {
                    FileDescriptor fileDescriptor = openFileDescriptor.getFileDescriptor();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                    if (options.outWidth == -1 || options.outHeight == -1) {
                        return new BitmapWorkerResult(new IllegalArgumentException("Bounds for bitmap could not be retrieved from the Uri: [" + this.mInputUri + "]"));
                    }
                    options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options, this.mRequiredWidth, this.mRequiredHeight);
                    boolean z = false;
                    options.inJustDecodeBounds = false;
                    Bitmap bitmap = null;
                    while (!z) {
                        try {
                            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
                            z = true;
                        } catch (OutOfMemoryError e) {
                            Log.e(TAG, "doInBackground: BitmapFactory.decodeFileDescriptor: ", e);
                            options.inSampleSize *= 2;
                        }
                    }
                    if (bitmap == null) {
                        return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.mInputUri + "]"));
                    }
                    if (Build.VERSION.SDK_INT >= 16) {
                        BitmapLoadUtils.close(openFileDescriptor);
                    }
                    int exifOrientation = BitmapLoadUtils.getExifOrientation(this.mContext, this.mInputUri);
                    int exifToDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
                    int exifToTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
                    ExifInfo exifInfo = new ExifInfo(exifOrientation, exifToDegrees, exifToTranslation);
                    Matrix matrix = new Matrix();
                    if (exifToDegrees != 0) {
                        matrix.preRotate(exifToDegrees);
                    }
                    if (exifToTranslation != 1) {
                        matrix.postScale(exifToTranslation, 1.0f);
                    }
                    if (!matrix.isIdentity()) {
                        return new BitmapWorkerResult(BitmapLoadUtils.transformBitmap(bitmap, matrix), exifInfo);
                    }
                    return new BitmapWorkerResult(bitmap, exifInfo);
                }
                return new BitmapWorkerResult(new NullPointerException("ParcelFileDescriptor was null for given Uri: [" + this.mInputUri + "]"));
            } catch (FileNotFoundException e2) {
                return new BitmapWorkerResult(e2);
            }
        } catch (IOException | NullPointerException e3) {
            return new BitmapWorkerResult(e3);
        }
    }

    private void processInputUri() throws NullPointerException, IOException {
        String scheme = this.mInputUri.getScheme();
        Log.d(TAG, "Uri scheme: " + scheme);
        if ("http".equals(scheme) || "https".equals(scheme)) {
            try {
                downloadFile(this.mInputUri, this.mOutputUri);
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "Downloading failed", e);
                throw e;
            }
        } else if ("content".equals(scheme)) {
            String filePath = getFilePath();
            if (!TextUtils.isEmpty(filePath) && new File(filePath).exists()) {
                this.mInputUri = Uri.fromFile(new File(filePath));
                return;
            }
            try {
                copyFile(this.mInputUri, this.mOutputUri);
            } catch (IOException | NullPointerException e2) {
                Log.e(TAG, "Copying failed", e2);
                throw e2;
            }
        } else if ("file".equals(scheme)) {
        } else {
            Log.e(TAG, "Invalid Uri scheme " + scheme);
            throw new IllegalArgumentException("Invalid Uri scheme" + scheme);
        }
    }

    private String getFilePath() {
        if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
            return FileUtils.getPath(this.mContext, this.mInputUri);
        }
        return null;
    }

    private void copyFile(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        InputStream inputStream;
        Log.d(TAG, "copyFile");
        if (uri2 == null) {
            throw new NullPointerException("Output Uri is null - cannot copy image");
        }
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = this.mContext.getContentResolver().openInputStream(uri);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(new File(uri2.getPath()));
                try {
                    if (inputStream == null) {
                        throw new NullPointerException("InputStream for given input Uri is null");
                    }
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            BitmapLoadUtils.close(fileOutputStream2);
                            BitmapLoadUtils.close(inputStream);
                            this.mInputUri = this.mOutputUri;
                            return;
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    BitmapLoadUtils.close(fileOutputStream);
                    BitmapLoadUtils.close(inputStream);
                    this.mInputUri = this.mOutputUri;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    private void downloadFile(@NonNull Uri uri, @Nullable Uri uri2) throws NullPointerException, IOException {
        Closeable closeable;
        Response response;
        BufferedSource source;
        Log.d(TAG, "downloadFile");
        if (uri2 == null) {
            throw new NullPointerException("Output Uri is null - cannot download image");
        }
        OkHttpClient init = NBSOkHttp3Instrumentation.init();
        BufferedSource bufferedSource = null;
        try {
            Response execute = init.newCall(new Request.Builder().url(uri.toString()).build()).execute();
            try {
                source = execute.body().source();
            } catch (Throwable th) {
                th = th;
                response = execute;
                closeable = null;
            }
            try {
                OutputStream openOutputStream = this.mContext.getContentResolver().openOutputStream(uri2);
                if (openOutputStream != null) {
                    Sink sink = Okio.sink(openOutputStream);
                    source.readAll(sink);
                    BitmapLoadUtils.close(source);
                    BitmapLoadUtils.close(sink);
                    if (execute != null) {
                        BitmapLoadUtils.close(execute.body());
                    }
                    init.dispatcher().cancelAll();
                    this.mInputUri = this.mOutputUri;
                    return;
                }
                throw new NullPointerException("OutputStream for given output Uri is null");
            } catch (Throwable th2) {
                th = th2;
                response = execute;
                closeable = null;
                bufferedSource = source;
                BitmapLoadUtils.close(bufferedSource);
                BitmapLoadUtils.close(closeable);
                if (response != null) {
                    BitmapLoadUtils.close(response.body());
                }
                init.dispatcher().cancelAll();
                this.mInputUri = this.mOutputUri;
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            closeable = null;
            response = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(@NonNull BitmapWorkerResult bitmapWorkerResult) {
        if (bitmapWorkerResult.mBitmapWorkerException == null) {
            BitmapLoadCallback bitmapLoadCallback = this.mBitmapLoadCallback;
            Bitmap bitmap = bitmapWorkerResult.mBitmapResult;
            ExifInfo exifInfo = bitmapWorkerResult.mExifInfo;
            String path = this.mInputUri.getPath();
            Uri uri = this.mOutputUri;
            bitmapLoadCallback.onBitmapLoaded(bitmap, exifInfo, path, uri == null ? null : uri.getPath());
            return;
        }
        this.mBitmapLoadCallback.onFailure(bitmapWorkerResult.mBitmapWorkerException);
    }
}
