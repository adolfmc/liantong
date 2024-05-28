package com.example.asus.detectionandalign.videocompress;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileFilter;
import java.text.DecimalFormat;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileUtils {
    private static final boolean DEBUG = false;
    public static final String HIDDEN_PREFIX = ".";
    public static final String MIME_TYPE_APP = "application/*";
    public static final String MIME_TYPE_AUDIO = "audio/*";
    public static final String MIME_TYPE_IMAGE = "image/*";
    public static final String MIME_TYPE_TEXT = "text/*";
    public static final String MIME_TYPE_VIDEO = "video/*";
    static final String TAG = "FileUtils";
    public static Comparator<File> sComparator = new Comparator<File>() { // from class: com.example.asus.detectionandalign.videocompress.FileUtils.1
        @Override // java.util.Comparator
        public int compare(File file, File file2) {
            return file.getName().toLowerCase().compareTo(file2.getName().toLowerCase());
        }
    };
    public static FileFilter sFileFilter = new FileFilter() { // from class: com.example.asus.detectionandalign.videocompress.FileUtils.2
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isFile() && !file.getName().startsWith(".");
        }
    };
    public static FileFilter sDirFilter = new FileFilter() { // from class: com.example.asus.detectionandalign.videocompress.FileUtils.3
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isDirectory() && !file.getName().startsWith(".");
        }
    };

    private FileUtils() {
    }

    public static Intent createGetContentIntent() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        return intent;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf >= 0 ? str.substring(lastIndexOf) : "";
    }

    public static File getFile(Context context, Uri uri) {
        String path;
        if (uri == null || (path = getPath(context, uri)) == null || !isLocal(path)) {
            return null;
        }
        return new File(path);
    }

    public static String getMimeType(Context context, Uri uri) {
        return getMimeType(new File(getPath(context, uri)));
    }

    public static String getMimeType(File file) {
        String extension = getExtension(file.getName());
        return extension.length() > 0 ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1)) : "application/octet-stream";
    }

    public static String getPath(Context context, Uri uri) {
        int i = Build.VERSION.SDK_INT;
        Uri uri2 = null;
        if (Build.VERSION.SDK_INT < 19 || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                return isGooglePhotosUri(uri) ? uri.getLastPathSegment() : getDataColumn(context, uri, null, null);
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else {
            if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = split2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
            }
        }
        return null;
    }

    public static File getPathWithoutFilename(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                return file;
            }
            String name = file.getName();
            String absolutePath = file.getAbsolutePath();
            String substring = absolutePath.substring(0, absolutePath.length() - name.length());
            if (substring.endsWith("/")) {
                substring = substring.substring(0, substring.length() - 1);
            }
            return new File(substring);
        }
        return null;
    }

    public static String getReadableFileSize(int i) {
        float f;
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        String str = " KB";
        if (i > 1024) {
            f = i / 1024;
            if (f > 1024.0f) {
                f /= 1024.0f;
                if (f > 1024.0f) {
                    f /= 1024.0f;
                    str = " GB";
                } else {
                    str = " MB";
                }
            }
        } else {
            f = 0.0f;
        }
        return String.valueOf(decimalFormat.format(f) + str);
    }

    public static Bitmap getThumbnail(Context context, Uri uri) {
        return getThumbnail(context, uri, getMimeType(context, uri));
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0049, code lost:
        if (r9 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004b, code lost:
        r9.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005a, code lost:
        if (r9 == null) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap getThumbnail(android.content.Context r8, android.net.Uri r9, java.lang.String r10) {
        /*
            boolean r0 = isMediaUri(r9)
            r1 = 0
            if (r0 != 0) goto Lf
            java.lang.String r8 = "FileUtils"
            java.lang.String r9 = "You can only retrieve thumbnails for images and videos."
            android.util.Log.e(r8, r9)
            return r1
        Lf:
            if (r9 == 0) goto L5d
            android.content.ContentResolver r8 = r8.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r8
            r3 = r9
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L59
            boolean r0 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5a
            if (r0 == 0) goto L49
            r0 = 0
            int r0 = r9.getInt(r0)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5a
            java.lang.String r2 = "video"
            boolean r2 = r10.contains(r2)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5a
            r3 = 1
            if (r2 == 0) goto L3b
            long r4 = (long) r0     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5a
            android.graphics.Bitmap r8 = android.provider.MediaStore.Video.Thumbnails.getThumbnail(r8, r4, r3, r1)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5a
        L39:
            r1 = r8
            goto L49
        L3b:
            java.lang.String r2 = "image/*"
            boolean r10 = r10.contains(r2)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5a
            if (r10 == 0) goto L49
            long r4 = (long) r0     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5a
            android.graphics.Bitmap r8 = android.provider.MediaStore.Images.Thumbnails.getThumbnail(r8, r4, r3, r1)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L5a
            goto L39
        L49:
            if (r9 == 0) goto L5d
        L4b:
            r9.close()
            goto L5d
        L4f:
            r8 = move-exception
            goto L53
        L51:
            r8 = move-exception
            r9 = r1
        L53:
            if (r9 == 0) goto L58
            r9.close()
        L58:
            throw r8
        L59:
            r9 = r1
        L5a:
            if (r9 == 0) goto L5d
            goto L4b
        L5d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.videocompress.FileUtils.getThumbnail(android.content.Context, android.net.Uri, java.lang.String):android.graphics.Bitmap");
    }

    public static Bitmap getThumbnail(Context context, File file) {
        return getThumbnail(context, getUri(file), getMimeType(file));
    }

    public static Uri getUri(File file) {
        if (file != null) {
            return Uri.fromFile(file);
        }
        return null;
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static boolean isLocal(String str) {
        return (str == null || str.startsWith("http://") || str.startsWith("https://")) ? false : true;
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaUri(Uri uri) {
        return "media".equalsIgnoreCase(uri.getAuthority());
    }
}
