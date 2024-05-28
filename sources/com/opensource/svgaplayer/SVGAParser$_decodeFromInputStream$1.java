package com.opensource.svgaplayer;

import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.proto.MovieEntity;
import com.opensource.svgaplayer.utils.log.LogUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SVGAParser.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m1890d2 = {"<anonymous>", "", "run"}, m1889k = 3, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SVGAParser$_decodeFromInputStream$1 implements Runnable {
    final /* synthetic */ String $cacheKey;
    final /* synthetic */ SVGAParser.ParseCompletion $callback;
    final /* synthetic */ InputStream $inputStream;
    final /* synthetic */ SVGAParser this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SVGAParser$_decodeFromInputStream$1(SVGAParser sVGAParser, InputStream inputStream, String str, SVGAParser.ParseCompletion parseCompletion) {
        this.this$0 = sVGAParser;
        this.$inputStream = inputStream;
        this.$cacheKey = str;
        this.$callback = parseCompletion;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final byte[] readAsBytes;
        byte[] inflate;
        int i;
        int i2;
        try {
            try {
                LogUtils.INSTANCE.info("SVGAParser", "Input.binary change to entity");
                readAsBytes = this.this$0.readAsBytes(this.$inputStream);
                if (readAsBytes != null) {
                    SVGAParser.Companion.getThreadPoolExecutor$com_opensource_svgaplayer().execute(new Runnable() { // from class: com.opensource.svgaplayer.SVGAParser$_decodeFromInputStream$1$$special$$inlined$let$lambda$2
                        @Override // java.lang.Runnable
                        public final void run() {
                            File buildSvgaFile = SVGACache.INSTANCE.buildSvgaFile(this.$cacheKey);
                            try {
                                File file = buildSvgaFile.exists() ^ true ? buildSvgaFile : null;
                                if (file != null) {
                                    file.createNewFile();
                                }
                                new FileOutputStream(buildSvgaFile).write(readAsBytes);
                                Unit unit = Unit.INSTANCE;
                            } catch (Exception e) {
                                LogUtils.INSTANCE.error("SVGAParser", "create cache file fail.", e);
                                buildSvgaFile.delete();
                            }
                        }
                    });
                    LogUtils.INSTANCE.info("SVGAParser", "Input.inflate start");
                    inflate = this.this$0.inflate(readAsBytes);
                    if (inflate != null) {
                        LogUtils.INSTANCE.info("SVGAParser", "Input.inflate success");
                        MovieEntity decode = MovieEntity.ADAPTER.decode(inflate);
                        Intrinsics.checkExpressionValueIsNotNull(decode, "MovieEntity.ADAPTER.decode(inflateBytes)");
                        File file = new File(this.$cacheKey);
                        i = this.this$0.mFrameWidth;
                        i2 = this.this$0.mFrameHeight;
                        final SVGAVideoEntity sVGAVideoEntity = new SVGAVideoEntity(decode, file, i, i2);
                        sVGAVideoEntity.prepare$com_opensource_svgaplayer(new Functions<Unit>() { // from class: com.opensource.svgaplayer.SVGAParser$_decodeFromInputStream$1$$special$$inlined$let$lambda$3
                            /* JADX INFO: Access modifiers changed from: package-private */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Functions
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke  reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                LogUtils.INSTANCE.info("SVGAParser", "Input.prepare success");
                                this.this$0.invokeCompleteCallback(SVGAVideoEntity.this, this.$callback);
                            }
                        });
                    } else {
                        this.this$0.doError("Input.inflate(bytes) cause exception", this.$callback);
                    }
                } else {
                    this.this$0.doError("Input.readAsBytes(inputStream) cause exception", this.$callback);
                }
            } catch (Exception e) {
                this.this$0.invokeErrorCallback(e, this.$callback);
            }
        } finally {
            this.$inputStream.close();
        }
    }
}
