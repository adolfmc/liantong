package com.baidu.minivideo.arface.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AssetsCopyToSdcard {
    private Context mContext;

    public AssetsCopyToSdcard(Context context) {
        this.mContext = context;
    }

    public boolean cpAssetToSDcard(String str, File file) {
        boolean assetToSD = assetToSD(str, file);
        if (file.isDirectory()) {
            File file2 = new File(file, ".nomedia");
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return assetToSD;
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x00cb A[Catch: IOException -> 0x00c7, TRY_LEAVE, TryCatch #2 {IOException -> 0x00c7, blocks: (B:68:0x00c3, B:72:0x00cb), top: B:80:0x00c3 }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean assetToSD(java.lang.String r10, java.io.File r11) {
        /*
            Method dump skipped, instructions count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.minivideo.arface.utils.AssetsCopyToSdcard.assetToSD(java.lang.String, java.io.File):boolean");
    }

    private void getDirectory(String str) {
        String[] split = str.split("/");
        String file = Environment.getExternalStorageDirectory().toString();
        for (int i = 0; i < split.length; i++) {
            file = file + "/" + split[i];
            File file2 = new File(file);
            if (!file2.exists()) {
                file2.mkdir();
            }
        }
    }
}
