package me.shaohui.advancedluban;

import android.graphics.Bitmap;
import java.io.File;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class LubanBuilder {
    File cacheDir;
    Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
    int gear = 3;
    int maxHeight;
    int maxSize;
    int maxWidth;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LubanBuilder(File file) {
        this.cacheDir = file;
    }
}
