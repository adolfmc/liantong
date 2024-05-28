package com.baidu.rtc.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.webrtc.Logging;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static boolean isSDMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static final String getPrefix(String str) {
        return str.substring(0, str.lastIndexOf("."));
    }

    public static final String getSuffix(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static final String getFileFullName(String str) {
        return str.substring(str.lastIndexOf("/") + 1, str.length());
    }

    public static final String getFileNameWithOutExtention(String str) {
        return removeExtention(getFileFullName(str));
    }

    public static boolean isExists(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
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

    public static boolean saveText(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    fileOutputStream2.write(str2.getBytes());
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                } catch (Exception e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void zipDirectory(File file, String str) {
        try {
            ArrayList<String> arrayList = new ArrayList();
            populateFilesList(file, arrayList);
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            for (String str2 : arrayList) {
                PrintStream printStream = System.out;
                printStream.println("Zipping " + str2);
                zipOutputStream.putNextEntry(new ZipEntry(str2.substring(file.getAbsolutePath().length() + 1, str2.length())));
                FileInputStream fileInputStream = new FileInputStream(str2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        zipOutputStream.write(bArr, 0, read);
                    }
                }
                zipOutputStream.closeEntry();
                fileInputStream.close();
            }
            zipOutputStream.close();
            fileOutputStream.close();
            arrayList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void populateFilesList(File file, List<String> list) throws IOException {
        File[] listFiles;
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                list.add(file2.getAbsolutePath());
            } else {
                populateFilesList(file2, list);
            }
        }
    }

    public static void zipSingleFile(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    zipOutputStream.closeEntry();
                    zipOutputStream.close();
                    fileInputStream.close();
                    fileOutputStream.close();
                    PrintStream printStream = System.out;
                    printStream.println(file.getCanonicalPath() + " is zipped to " + str);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFileOrDir(File file) {
        if (file != null) {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        int length = listFiles.length;
                        for (int i = 0; i < length; i++) {
                            if (listFiles[i].isFile()) {
                                listFiles[i].delete();
                            } else {
                                deleteFileOrDir(listFiles[i]);
                            }
                        }
                    }
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static boolean checkFile(String str) {
        if (!checkSD() || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new File(str).exists();
        } catch (Exception e) {
            Logging.m5304e("FileUtils", e.getMessage());
            return false;
        }
    }

    public static boolean checkSD() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static long getFileSize(File file) {
        long j = 0;
        FileInputStream fileInputStream = null;
        try {
            try {
                if (file.exists()) {
                    FileInputStream fileInputStream2 = new FileInputStream(file);
                    try {
                        j = fileInputStream2.available();
                        fileInputStream = fileInputStream2;
                    } catch (Exception e) {
                        e = e;
                        fileInputStream = fileInputStream2;
                        Logging.m5304e("FileUtils", e.getMessage());
                        CloseHelper.close(fileInputStream);
                        return j;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        CloseHelper.close(fileInputStream);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        CloseHelper.close(fileInputStream);
        return j;
    }

    public static long getFileSize(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0L;
            }
            return new File(str).length();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getParentDir(String str) {
        File parentFile;
        if (TextUtils.isEmpty(str) || !new File(str).exists() || (parentFile = new File(str).getParentFile()) == null) {
            return "";
        }
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return parentFile.getAbsolutePath() + File.separator;
    }

    public static boolean deleteFile(File file) {
        try {
            return file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile(String str) {
        if (TextUtils.isEmpty(str) || !checkFile(str)) {
            return true;
        }
        return deleteFile(new File(str));
    }

    public static long getAvailableSize() {
        String absolutePath;
        if (checkSD()) {
            absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            absolutePath = Environment.getRootDirectory().getAbsolutePath();
        }
        if (absolutePath == null) {
            return 0L;
        }
        StatFs statFs = new StatFs(absolutePath);
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public static long calculateFileSize(String str) {
        File file = new File(str);
        if (file.exists()) {
            return getSize(file);
        }
        return 0L;
    }

    public static long getSize(File file) {
        long j = 0;
        if (file == null || !file.exists()) {
            return 0L;
        }
        if (!file.isDirectory()) {
            if (file != null) {
                return file.length();
            }
            return 0L;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return 0L;
        }
        for (File file2 : listFiles) {
            j += getSize(file2);
        }
        return j;
    }

    public static String saveBitmap2JPG(String str, String str2, Bitmap bitmap, int i) {
        return saveBitmap(str, str2, bitmap, i, Bitmap.CompressFormat.JPEG);
    }

    public static String saveBitmap2PNG(String str, String str2, Bitmap bitmap, int i) {
        return saveBitmap(str, str2, bitmap, i, Bitmap.CompressFormat.PNG);
    }

    public static String saveBitmap(String str, String str2, Bitmap bitmap, int i, Bitmap.CompressFormat compressFormat) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        if (bitmap == null) {
            CloseHelper.close((Closeable) null);
            return null;
        }
        try {
            File file = new File(str);
            if (!file.exists() && !file.mkdirs()) {
                CloseHelper.close((Closeable) null);
                return null;
            }
            File file2 = new File(str, str2);
            if (file2.exists() && !file2.delete()) {
                CloseHelper.close((Closeable) null);
                return null;
            } else if (!file2.createNewFile()) {
                CloseHelper.close((Closeable) null);
                return null;
            } else {
                FileOutputStream fileOutputStream3 = new FileOutputStream(file2);
                try {
                    bitmap.compress(compressFormat, i, fileOutputStream3);
                    String absolutePath = file2.getAbsolutePath();
                    CloseHelper.close(fileOutputStream3);
                    return absolutePath;
                } catch (IOException e) {
                    fileOutputStream = fileOutputStream3;
                    e = e;
                    try {
                        Logging.m5304e("FileUtils", e.getMessage());
                        CloseHelper.close(fileOutputStream);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream2 = fileOutputStream;
                        CloseHelper.close(fileOutputStream2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    fileOutputStream2 = fileOutputStream3;
                    th = th2;
                    CloseHelper.close(fileOutputStream2);
                    throw th;
                }
            }
        } catch (IOException e2) {
            e = e2;
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static boolean copyFile(String str, String str2) throws IOException {
        if (!checkFile(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return copyFile(new File(str), new File(str2));
    }

    public static boolean copyFile(File file, File file2) throws IOException {
        if (file == null || file2 == null || !file.exists() || file.isDirectory() || file.getCanonicalPath().equals(file2.getCanonicalPath())) {
            return false;
        }
        File parentFile = file2.getParentFile();
        if (parentFile == null || parentFile.mkdirs() || parentFile.isDirectory()) {
            if (!file2.exists() || file2.canWrite()) {
                return doCopyFile(file, file2);
            }
            return false;
        }
        return false;
    }

    private static boolean doCopyFile(File file, File file2) throws IOException {
        FileOutputStream fileOutputStream;
        FileChannel fileChannel;
        FileInputStream fileInputStream;
        if (file2.exists() && file2.isDirectory()) {
            return false;
        }
        FileChannel fileChannel2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Exception e) {
                e = e;
                fileOutputStream = null;
                fileChannel = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
                fileChannel = null;
            }
        } catch (Exception e2) {
            e = e2;
            fileOutputStream = null;
            fileChannel = null;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            fileChannel = null;
            fileInputStream = null;
        }
        try {
            fileChannel = fileInputStream.getChannel();
        } catch (Exception e3) {
            e = e3;
            fileChannel = null;
        } catch (Throwable th3) {
            th = th3;
            fileChannel = null;
            CloseHelper.close(fileChannel2);
            CloseHelper.close(fileOutputStream);
            CloseHelper.close(fileChannel);
            CloseHelper.close(fileInputStream);
            throw th;
        }
        try {
            try {
                fileChannel2 = fileOutputStream.getChannel();
                long size = fileChannel.size();
                long j = 0;
                while (j < size) {
                    long j2 = size - j;
                    j += fileChannel2.transferFrom(fileChannel, j, j2 > 31457280 ? 31457280L : j2);
                }
                CloseHelper.close(fileChannel2);
                CloseHelper.close(fileOutputStream);
                CloseHelper.close(fileChannel);
                CloseHelper.close(fileInputStream);
                return true;
            } catch (Throwable th4) {
                th = th4;
                CloseHelper.close(fileChannel2);
                CloseHelper.close(fileOutputStream);
                CloseHelper.close(fileChannel);
                CloseHelper.close(fileInputStream);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            Logging.m5304e("FileUtils", e.getMessage());
            CloseHelper.close(fileChannel2);
            CloseHelper.close(fileOutputStream);
            CloseHelper.close(fileChannel);
            CloseHelper.close(fileInputStream);
            return false;
        }
    }

    public static boolean writeFile(File file, String str, boolean z) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, z);
                try {
                    fileOutputStream2.write(str.getBytes());
                    fileOutputStream2.flush();
                    CloseHelper.close(fileOutputStream2);
                    return true;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    CloseHelper.close(fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    CloseHelper.close(fileOutputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static byte[] loadDataFromStream(InputStream inputStream) {
        try {
            if (inputStream == null) {
                return null;
            }
            try {
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bArr;
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public static void unzipFile(File file, String str) throws ZipException, IOException {
        unzipFile(file, new File(str));
    }

    public static void unzipFile(File file, File file2) throws ZipException, IOException {
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String absolutePath = file2.getAbsolutePath();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            String name = nextElement.getName();
            if (!"./".equals(name) && !".".equals(name) && !name.endsWith("/")) {
                InputStream inputStream = zipFile.getInputStream(nextElement);
                File file3 = new File(absolutePath + File.separator + name);
                if (!file3.exists()) {
                    File parentFile = file3.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file3.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                byte[] bArr = new byte[10240];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
    }

    public static void zipFiles(Collection<File> collection, File file, String str) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file), 10240));
        for (File file2 : collection) {
            zipFile(file2, zipOutputStream, "");
        }
        zipOutputStream.setComment(str);
        zipOutputStream.close();
    }

    private static void zipFile(File file, ZipOutputStream zipOutputStream, String str) throws IOException {
        String name;
        if (str != null && str.length() > 0) {
            String trim = str.trim();
            name = trim.length() > 0 ? trim + File.separator + file.getName() : file.getName();
        } else {
            name = file.getName();
        }
        String str2 = new String(name.getBytes("8859_1"), "GBK");
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                zipFile(file2, zipOutputStream, str2);
            }
            return;
        }
        byte[] bArr = new byte[10240];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 10240);
        zipOutputStream.putNextEntry(new ZipEntry(str2));
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                zipOutputStream.write(bArr, 0, read);
            } else {
                bufferedInputStream.close();
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
                return;
            }
        }
    }

    public static byte[] loadDataFromFile(String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
        } catch (Exception e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        return loadDataFromStream(fileInputStream);
    }

    public static byte[] loadDataFromAssets(Context context, String str) {
        InputStream inputStream = null;
        if (context == null) {
            return null;
        }
        try {
            inputStream = context.getAssets().open(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadDataFromStream(inputStream);
    }

    public static void unZipAssetsFolder(Context context, String str, String str2) throws Exception {
        ZipInputStream zipInputStream = new ZipInputStream(context.getAssets().open(str));
        zipInputStream.available();
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    String substring = name.substring(0, name.length() - 1);
                    new File(str2 + File.separator + substring).mkdirs();
                } else {
                    File file = new File(str2 + File.separator + name);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            } else {
                zipInputStream.close();
                return;
            }
        }
    }

    public static String removeExtention(String str) {
        String name;
        int lastIndexOf;
        File file = new File(str);
        return (!file.isDirectory() && (lastIndexOf = (name = file.getName()).lastIndexOf(46)) > 0) ? new File(file.getParent(), name.substring(0, lastIndexOf)).getPath() : str;
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        deleteNotCheck(file);
        return !file.exists();
    }

    private static void deleteNotCheck(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            file.delete();
            return;
        }
        for (File file2 : listFiles) {
            deleteNotCheck(file2);
            file.delete();
        }
    }

    public static boolean mkdirs(String str) {
        File file = new File(str);
        return file.exists() || file.mkdirs();
    }

    public static void deleteAllFilesInDir(File file) {
        File[] listFiles;
        if (file == null || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        for (File file2 : listFiles) {
            if (!file2.isDirectory()) {
                file2.delete();
            }
        }
    }

    public static String getFileExt(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf == -1 ? "" : str.substring(lastIndexOf + 1).toLowerCase();
    }
}
