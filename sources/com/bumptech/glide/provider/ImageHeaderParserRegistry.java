package com.bumptech.glide.provider;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class ImageHeaderParserRegistry {
    private final List<ImageHeaderParser> parsers = new ArrayList();

    @NonNull
    public synchronized List<ImageHeaderParser> getParsers() {
        return this.parsers;
    }

    public synchronized void add(@NonNull ImageHeaderParser imageHeaderParser) {
        this.parsers.add(imageHeaderParser);
    }
}
