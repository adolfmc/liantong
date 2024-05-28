package com.opensource.svgaplayer;

import com.opensource.svgaplayer.SVGAParser;
import java.io.InputStream;
import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SVGAParser.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m1890d2 = {"<anonymous>", "", "run"}, m1889k = 3, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SVGAParser$decodeFromInputStream$1 implements Runnable {
    final /* synthetic */ String $cacheKey;
    final /* synthetic */ SVGAParser.ParseCompletion $callback;
    final /* synthetic */ boolean $closeInputStream;
    final /* synthetic */ InputStream $inputStream;
    final /* synthetic */ SVGAParser this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SVGAParser$decodeFromInputStream$1(SVGAParser sVGAParser, InputStream inputStream, String str, SVGAParser.ParseCompletion parseCompletion, boolean z) {
        this.this$0 = sVGAParser;
        this.$inputStream = inputStream;
        this.$cacheKey = str;
        this.$callback = parseCompletion;
        this.$closeInputStream = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0041, code lost:
        if (r2 != false) goto L25;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.opensource.svgaplayer.SVGAParser$decodeFromInputStream$1.run():void");
    }
}
