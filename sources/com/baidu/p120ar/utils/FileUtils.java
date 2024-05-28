package com.baidu.p120ar.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.FileUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class FileUtils {
    private static String sTempStoragePath;

    private FileUtils() {
    }

    public static String getDuMixTempStoragePath(Context context) {
        String str = sTempStoragePath;
        if (str != null) {
            return str;
        }
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir == null) {
            sTempStoragePath = context.getFilesDir().getAbsolutePath();
        } else {
            sTempStoragePath = externalFilesDir.getAbsolutePath();
        }
        return sTempStoragePath;
    }

    public static void ensureDir(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                file.mkdirs();
                return;
            }
            return;
        }
        file.mkdirs();
    }

    public static void ensureParent(File file) {
        File parentFile;
        if (file == null || (parentFile = file.getParentFile()) == null || parentFile.exists()) {
            return;
        }
        parentFile.mkdirs();
    }

    public static boolean ensureDirectoryExist(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        try {
            file.mkdirs();
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static void deleteDir(File file) {
        deleteDir(file, true);
    }

    public static void deleteDir(File file, boolean z) {
        if (file == null || !file.isDirectory()) {
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    deleteDir(file2, z);
                } else {
                    file2.delete();
                }
            }
        }
        if (z) {
            file.delete();
        }
    }

    public static boolean deleteFileIfExist(File file) {
        if (file != null && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static FileOutputStream openFileOutputStream(File file) throws IOException {
        deleteFileIfExist(file);
        ensureParent(file);
        file.createNewFile();
        return new FileOutputStream(file);
    }

    public static String readFileText(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return readFileText(new File(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.Closeable, java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public static String readFileText(File file) {
        FileInputStream fileInputStream;
        ?? existsFile = existsFile(file);
        try {
            if (existsFile != 0) {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e) {
                    e = e;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    existsFile = 0;
                    IoUtils.closeQuietly(existsFile);
                    if (existsFile != 0) {
                        try {
                            existsFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    String loadContent = IoUtils.loadContent(fileInputStream);
                    IoUtils.closeQuietly(fileInputStream);
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return loadContent;
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    IoUtils.closeQuietly(fileInputStream);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static List<String> readFileLineText(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return readFileLineText(new File(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.Closeable, java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public static List<String> readFileLineText(File file) {
        FileInputStream fileInputStream;
        ?? existsFile = existsFile(file);
        try {
            if (existsFile != 0) {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (IOException e) {
                    e = e;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    existsFile = 0;
                    IoUtils.closeQuietly(existsFile);
                    if (existsFile != 0) {
                        try {
                            existsFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    List<String> loadLineContent = IoUtils.loadLineContent(fileInputStream);
                    IoUtils.closeQuietly(fileInputStream);
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    return loadLineContent;
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    IoUtils.closeQuietly(fileInputStream);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static ArrayList<File> getChildFiles(File file) {
        File[] listFiles = file.listFiles();
        ArrayList<File> arrayList = new ArrayList<>();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    arrayList.add(file2);
                } else {
                    getChildFiles(file2);
                }
            }
        }
        return arrayList;
    }

    public static String[] fileArray2Paths(ArrayList<File> arrayList) {
        String[] strArr = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            strArr[i] = arrayList.get(i).getPath();
        }
        return strArr;
    }

    public static boolean existsFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean existsFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return existsFile(new File(str));
    }

    public static boolean existsDir(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return existsDir(new File(str));
    }

    public static boolean existsDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static String getFileExtention(String str) {
        int lastIndexOf;
        if (str == null || str.length() == 0 || (lastIndexOf = str.lastIndexOf(".")) <= -1 || lastIndexOf >= str.length() - 1) {
            return null;
        }
        return str.substring(lastIndexOf + 1);
    }

    public static File[] getFlleListAsPath(String str) {
        if (str == null || !new File(str).exists()) {
            return null;
        }
        return new File(str).listFiles();
    }

    public static String saveBitmap(String str, Bitmap bitmap, int i) {
        if (TextUtils.isEmpty(str) || bitmap == null) {
            return str;
        }
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            saveBitmap(bitmap, file, i);
            return file.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File saveBitmap(Bitmap bitmap, File file, int i) throws NullPointerException, IllegalArgumentException {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, i, fileOutputStream2);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                        return file;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return file;
    }

    public static void copyDir(final File file, final File file2, boolean z) {
        if (file.exists()) {
            if (z) {
                new AsyncTask<Void, Void, Void>() { // from class: com.baidu.ar.utils.FileUtils.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public Void doInBackground(Void... voidArr) {
                        FileUtils.copyDir(file, file2, false);
                        return null;
                    }
                }.execute(new Void[0]);
                return;
            }
            ensureDir(file2);
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return;
            }
            for (File file3 : listFiles) {
                if (file3.isFile()) {
                    copyfile(file3, new File(file2, file3.getName()), true);
                } else {
                    copyDir(file3, new File(file2, file3.getName()), false);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.io.OutputStream, java.io.FileOutputStream] */
    public static boolean copyfile(File file, File file2, Boolean bool) {
        FileInputStream fileInputStream;
        ?? r2;
        Closeable closeable;
        if (file != null && file.exists() && file.isFile() && file.canRead()) {
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (file2.exists() && bool.booleanValue()) {
                file2.delete();
            }
            FileInputStream fileInputStream2 = null;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Exception e) {
                e = e;
                r2 = 0;
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
            try {
                r2 = new FileOutputStream(file2);
            } catch (Exception e2) {
                e = e2;
                r2 = 0;
            } catch (Throwable th2) {
                th = th2;
                IoUtils.closeQuietly(fileInputStream);
                IoUtils.closeQuietly(fileInputStream2);
                throw th;
            }
            try {
                IoUtils.copyStream(fileInputStream, (OutputStream) r2);
                r2.flush();
                IoUtils.closeQuietly(fileInputStream);
                closeable = r2;
            } catch (Exception e3) {
                e = e3;
                fileInputStream2 = fileInputStream;
                r2 = r2;
                try {
                    e.printStackTrace();
                    IoUtils.closeQuietly(fileInputStream2);
                    closeable = r2;
                    IoUtils.closeQuietly(closeable);
                    return true;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    fileInputStream2 = r2;
                    IoUtils.closeQuietly(fileInputStream);
                    IoUtils.closeQuietly(fileInputStream2);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                fileInputStream2 = r2;
                IoUtils.closeQuietly(fileInputStream);
                IoUtils.closeQuietly(fileInputStream2);
                throw th;
            }
            IoUtils.closeQuietly(closeable);
            return true;
        }
        return false;
    }
}
