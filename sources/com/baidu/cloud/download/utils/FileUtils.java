package com.baidu.cloud.download.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FileUtils {
    private static final String DOWNLOAD_DIR = "UgcDownload";

    public static final File getDefaultDownloadDir(Context context) {
        if (isSDMounted()) {
            return new File(context.getExternalCacheDir(), "UgcDownload");
        }
        return new File(context.getCacheDir(), "UgcDownload");
    }

    public static boolean isSDMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static final String getPrefix(@NonNull String str) {
        return str.substring(0, str.lastIndexOf("."));
    }

    public static final String getSuffix(@NonNull String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static final String getFileFullName(String str) {
        return str.substring(str.lastIndexOf("/") + 1, str.length());
    }

    public static boolean isExists(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    public static boolean deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.delete();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readText(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            return readText(new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readText(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                }
                String sb2 = sb.toString();
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return sb2;
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            return null;
        }
    }

    public static boolean copyFile(String str, String str2, OnVideoMergeProgressListener onVideoMergeProgressListener) throws IOException {
        return copyFile(new FileInputStream(str), str2, onVideoMergeProgressListener);
    }

    public static boolean copyFile(InputStream inputStream, String str, OnVideoMergeProgressListener onVideoMergeProgressListener) throws IOException {
        try {
            double size = inputStream instanceof FileInputStream ? ((FileInputStream) inputStream).getChannel().size() : 0.0d;
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            byte[] bArr = new byte[1444];
            int i = 0;
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                i += read;
                if (onVideoMergeProgressListener != null && size != 0.0d) {
                    onVideoMergeProgressListener.onUpdateProgress((int) ((i / size) * 100.0d));
                } else if (onVideoMergeProgressListener != null && size == 0.0d) {
                    onVideoMergeProgressListener.onUpdateProgress(80);
                }
                fileOutputStream.write(bArr, 0, read);
            }
            return true;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
