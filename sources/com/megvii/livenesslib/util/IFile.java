package com.megvii.livenesslib.util;

import java.io.File;
import java.io.FileOutputStream;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IFile {
    public boolean saveLog(String str, String str2) {
        try {
            File file = new File(Constant.dirName + File.separator + str);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "Log.txt"), true);
            fileOutputStream.write(("\n" + str + ",  " + str2).getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
