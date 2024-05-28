package com.opensource.svgaplayer;

import android.content.Context;
import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SVGACache.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0018\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGACache;", "", "()V", "cacheDir", "", "type", "Lcom/opensource/svgaplayer/SVGACache$Type;", "buildAudioFile", "Ljava/io/File;", "audio", "buildCacheDir", "cacheKey", "buildCacheKey", "url", "Ljava/net/URL;", "str", "buildSvgaFile", "isCached", "", "isDefaultCache", "isInitialized", "onCreate", "", "context", "Landroid/content/Context;", "Type", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SVGACache {
    public static final SVGACache INSTANCE = new SVGACache();
    private static Type type = Type.DEFAULT;
    private static String cacheDir = "/";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: SVGACache.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGACache$Type;", "", "(Ljava/lang/String;I)V", "DEFAULT", "FILE", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum Type {
        DEFAULT,
        FILE
    }

    private SVGACache() {
    }

    public final void onCreate(@Nullable Context context) {
        onCreate(context, Type.DEFAULT);
    }

    public final void onCreate(@Nullable Context context, @NotNull Type type2) {
        Intrinsics.checkParameterIsNotNull(type2, "type");
        if (isInitialized() || context == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        File cacheDir2 = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir2, "context.cacheDir");
        sb.append(cacheDir2.getAbsolutePath());
        sb.append("/svga/");
        cacheDir = sb.toString();
        File file = new File(cacheDir);
        if (!(!file.exists())) {
            file = null;
        }
        if (file != null) {
            file.mkdirs();
        }
        type = type2;
    }

    public final boolean isInitialized() {
        return !Intrinsics.areEqual("/", cacheDir);
    }

    public final boolean isDefaultCache() {
        return type == Type.DEFAULT;
    }

    public final boolean isCached(@NotNull String cacheKey) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        return (isDefaultCache() ? buildCacheDir(cacheKey) : buildSvgaFile(cacheKey)).exists();
    }

    @NotNull
    public final String buildCacheKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "str");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
        byte[] bytes = str.getBytes(forName);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        messageDigest.update(bytes);
        byte[] digest = messageDigest.digest();
        String str2 = "";
        for (byte b : digest) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Byte.valueOf(b)};
            String format = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            sb.append(format);
            str2 = sb.toString();
        }
        return str2;
    }

    @NotNull
    public final String buildCacheKey(@NotNull URL url) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        String url2 = url.toString();
        Intrinsics.checkExpressionValueIsNotNull(url2, "url.toString()");
        return buildCacheKey(url2);
    }

    @NotNull
    public final File buildCacheDir(@NotNull String cacheKey) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        return new File(cacheDir + cacheKey + '/');
    }

    @NotNull
    public final File buildSvgaFile(@NotNull String cacheKey) {
        Intrinsics.checkParameterIsNotNull(cacheKey, "cacheKey");
        return new File(cacheDir + cacheKey + ".svga");
    }

    @NotNull
    public final File buildAudioFile(@NotNull String audio) {
        Intrinsics.checkParameterIsNotNull(audio, "audio");
        return new File(cacheDir + audio + ".mp3");
    }
}
