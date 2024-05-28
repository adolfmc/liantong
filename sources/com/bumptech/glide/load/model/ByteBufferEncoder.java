package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ByteBufferEncoder implements Encoder<ByteBuffer> {
    private static final String TAG = "ByteBufferEncoder";

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(@NonNull ByteBuffer byteBuffer, @NonNull File file, @NonNull Options options) {
        try {
            ByteBufferUtil.toFile(byteBuffer, file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("ByteBufferEncoder", 3)) {
                Log.d("ByteBufferEncoder", "Failed to write data", e);
            }
            return false;
        }
    }
}
