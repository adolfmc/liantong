package com.chinaunicon.jtwifilib.core.utils;

import android.os.Environment;
import android.os.StatFs;
import com.chinaunicon.jtwifilib.core.global.JtApp;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtFileUtils {
    private static JtFileUtils mInstance;

    public static JtFileUtils getInst() {
        if (mInstance == null) {
            synchronized (JtFileUtils.class) {
                if (mInstance == null) {
                    mInstance = new JtFileUtils();
                }
            }
        }
        return mInstance;
    }

    public static String isExistDir_html(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.mkdirs()) {
            file.createNewFile();
        }
        return file.getAbsolutePath();
    }

    public static List<File> getFilesInDirectory(String str) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    arrayList.add(file2);
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    public void delete(File file) {
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File file2 : listFiles) {
                delete(file2);
            }
            file.delete();
        }
    }

    public static boolean deleteDirectory(String str) {
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            boolean z = true;
            for (int i = 0; listFiles != null && i < listFiles.length; i++) {
                if (!listFiles[i].getName().equals("YJHtml.zip")) {
                    if (listFiles[i].isFile()) {
                        z = deleteFile(listFiles[i].getAbsolutePath());
                        if (!z) {
                            break;
                        }
                    } else {
                        z = deleteDirectory(listFiles[i].getAbsolutePath());
                        if (!z) {
                            break;
                        }
                    }
                }
            }
            return z && file.delete();
        }
        return false;
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    public static String getAppPath() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            String str = externalStorageDirectory.toString() + "/" + JtApp.getInstance().getContext().getPackageName() + "/";
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            return str;
        }
        return JtApp.getInstance().getCacheDirPath();
    }

    public static boolean isSDFree() {
        File file;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = Environment.getExternalStorageDirectory();
        } else {
            file = new File(JtApp.getInstance().getCacheDirPath());
        }
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocksLong = ((statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong()) / 1024) / 1024;
        JtL.m16342e("手机剩余内存（MB）：" + availableBlocksLong);
        return availableBlocksLong > 50;
    }

    public static String getFormatSize(double d) {
        double d2 = d / 1024.0d;
        if (d2 < 1.0d) {
            return "0K";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            BigDecimal bigDecimal = new BigDecimal(Double.toString(d2));
            return bigDecimal.setScale(2, 4).toPlainString() + "K";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            BigDecimal bigDecimal2 = new BigDecimal(Double.toString(d3));
            return bigDecimal2.setScale(2, 4).toPlainString() + "M";
        }
        double d5 = d4 / 1024.0d;
        if (d5 < 1.0d) {
            BigDecimal bigDecimal3 = new BigDecimal(Double.toString(d4));
            return bigDecimal3.setScale(2, 4).toPlainString() + "GB";
        }
        BigDecimal bigDecimal4 = new BigDecimal(d5);
        return bigDecimal4.setScale(2, 4).toPlainString() + "TB";
    }

    public static void createFolder(String str) {
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }
}
