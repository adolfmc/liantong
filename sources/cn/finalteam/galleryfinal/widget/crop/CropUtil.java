package cn.finalteam.galleryfinal.widget.crop;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import cn.finalteam.galleryfinal.utils.ILogger;
import cn.finalteam.galleryfinal.widget.crop.MonitoredActivity;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.megvii.livenesslib.util.SDCardUtil;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class CropUtil {
    private static final String SCHEME_CONTENT = "content";
    private static final String SCHEME_FILE = "file";

    CropUtil() {
    }

    public static void closeSilently(@Nullable Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Throwable unused) {
        }
    }

    public static int getExifRotation(File file) {
        if (file == null) {
            return 0;
        }
        try {
            int attributeInt = new ExifInterface(file.getAbsolutePath()).getAttributeInt("Orientation", 0);
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
            ILogger.m22037e(e);
            return 0;
        }
    }

    public static boolean copyExifRotation(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
            ExifInterface exifInterface2 = new ExifInterface(file2.getAbsolutePath());
            exifInterface2.setAttribute("Orientation", exifInterface.getAttribute("Orientation"));
            exifInterface2.saveAttributes();
            return true;
        } catch (IOException e) {
            ILogger.m22037e(e);
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r1 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007b, code lost:
        if (r1 == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007d, code lost:
        r1.close();
     */
    @android.support.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File getFromMediaUri(android.content.Context r9, android.content.ContentResolver r10, android.net.Uri r11) {
        /*
            r0 = 0
            if (r11 != 0) goto L4
            return r0
        L4:
            java.lang.String r1 = "file"
            java.lang.String r2 = r11.getScheme()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L1a
            java.io.File r9 = new java.io.File
            java.lang.String r10 = r11.getPath()
            r9.<init>(r10)
            return r9
        L1a:
            java.lang.String r1 = "content"
            java.lang.String r2 = r11.getScheme()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.String r1 = "_data"
            java.lang.String r2 = "_display_name"
            java.lang.String[] r5 = new java.lang.String[]{r1, r2}
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r10
            r4 = r11
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L77 java.lang.SecurityException -> L7a java.lang.IllegalArgumentException -> L81
            if (r1 == 0) goto L74
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
            if (r2 == 0) goto L74
            java.lang.String r2 = r11.toString()     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
            java.lang.String r3 = "content://com.google.android.gallery3d"
            boolean r2 = r2.startsWith(r3)     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
            if (r2 == 0) goto L52
            java.lang.String r2 = "_display_name"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
            goto L58
        L52:
            java.lang.String r2 = "_data"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
        L58:
            r3 = -1
            if (r2 == r3) goto L74
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
            if (r3 != 0) goto L74
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L70 java.lang.IllegalArgumentException -> L72 java.lang.SecurityException -> L7b
            if (r1 == 0) goto L6f
            r1.close()
        L6f:
            return r3
        L70:
            r9 = move-exception
            goto L8b
        L72:
            r0 = r1
            goto L81
        L74:
            if (r1 == 0) goto L91
            goto L7d
        L77:
            r9 = move-exception
            r1 = r0
            goto L8b
        L7a:
            r1 = r0
        L7b:
            if (r1 == 0) goto L91
        L7d:
            r1.close()
            goto L91
        L81:
            java.io.File r9 = getFromMediaUriPfd(r9, r10, r11)     // Catch: java.lang.Throwable -> L77
            if (r0 == 0) goto L8a
            r0.close()
        L8a:
            return r9
        L8b:
            if (r1 == 0) goto L90
            r1.close()
        L90:
            throw r9
        L91:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.galleryfinal.widget.crop.CropUtil.getFromMediaUri(android.content.Context, android.content.ContentResolver, android.net.Uri):java.io.File");
    }

    private static String getTempFilename(Context context) throws IOException {
        return File.createTempFile("image", "tmp", new File(SDCardUtil.getOwnFileUrl(""))).getAbsolutePath();
    }

    @Nullable
    private static File getFromMediaUriPfd(Context context, ContentResolver contentResolver, Uri uri) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        String tempFilename;
        FileOutputStream fileOutputStream2 = null;
        if (uri == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(contentResolver.openFileDescriptor(uri, "r").getFileDescriptor());
            try {
                tempFilename = getTempFilename(context);
                fileOutputStream = new FileOutputStream(tempFilename);
            } catch (IOException unused) {
                fileOutputStream = null;
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException unused2) {
            fileOutputStream = null;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    File file = new File(tempFilename);
                    closeSilently(fileInputStream);
                    closeSilently(fileOutputStream);
                    return file;
                }
            }
        } catch (IOException unused3) {
            closeSilently(fileInputStream);
            closeSilently(fileOutputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream2 = fileOutputStream;
            closeSilently(fileInputStream);
            closeSilently(fileOutputStream2);
            throw th;
        }
    }

    public static void startBackgroundJob(MonitoredActivity monitoredActivity, String str, String str2, Runnable runnable, Handler handler) {
        new Thread(new BackgroundJob(monitoredActivity, runnable, ProgressDialog.show(monitoredActivity, str, str2, true, false), handler)).start();
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    static class BackgroundJob extends MonitoredActivity.LifeCycleAdapter implements Runnable {
        private final MonitoredActivity activity;
        private final Runnable cleanupRunner = new Runnable() { // from class: cn.finalteam.galleryfinal.widget.crop.CropUtil.BackgroundJob.1
            @Override // java.lang.Runnable
            public void run() {
                BackgroundJob.this.activity.removeLifeCycleListener(BackgroundJob.this);
                if (BackgroundJob.this.dialog.getWindow() != null) {
                    BackgroundJob.this.dialog.dismiss();
                }
            }
        };
        private final ProgressDialog dialog;
        private final Handler handler;
        private final Runnable job;

        public BackgroundJob(MonitoredActivity monitoredActivity, Runnable runnable, ProgressDialog progressDialog, Handler handler) {
            this.activity = monitoredActivity;
            this.dialog = progressDialog;
            this.job = runnable;
            this.activity.addLifeCycleListener(this);
            this.handler = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.job.run();
            } finally {
                this.handler.post(this.cleanupRunner);
            }
        }

        @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleAdapter, cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleListener
        public void onActivityDestroyed(MonitoredActivity monitoredActivity) {
            this.cleanupRunner.run();
            this.handler.removeCallbacks(this.cleanupRunner);
        }

        @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleAdapter, cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleListener
        public void onActivityStopped(MonitoredActivity monitoredActivity) {
            this.dialog.hide();
        }

        @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleAdapter, cn.finalteam.galleryfinal.widget.crop.MonitoredActivity.LifeCycleListener
        public void onActivityStarted(MonitoredActivity monitoredActivity) {
            this.dialog.show();
        }
    }
}
