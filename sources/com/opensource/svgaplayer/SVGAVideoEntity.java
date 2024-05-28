package com.opensource.svgaplayer;

import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import com.opensource.svgaplayer.bitmap.SVGABitmapByteArrayDecoder;
import com.opensource.svgaplayer.bitmap.SVGABitmapFileDecoder;
import com.opensource.svgaplayer.entities.SVGAAudioEntity;
import com.opensource.svgaplayer.entities.SVGAVideoSpriteEntity;
import com.opensource.svgaplayer.proto.AudioEntity;
import com.opensource.svgaplayer.proto.MovieEntity;
import com.opensource.svgaplayer.proto.MovieParams;
import com.opensource.svgaplayer.proto.SpriteEntity;
import com.opensource.svgaplayer.utils.SVGARect;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.p401io.Closeable;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SVGAVideoEntity.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nB\u0017\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\rB'\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ\u0006\u0010@\u001a\u00020AJ\u001a\u0010B\u001a\u0004\u0018\u00010%2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020$H\u0002J\u0012\u0010B\u001a\u0004\u0018\u00010%2\u0006\u0010E\u001a\u00020$H\u0002J$\u0010F\u001a\u00020\u001b2\u0006\u0010G\u001a\u00020H2\u0012\u0010I\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00050#H\u0002J\u0018\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u00020\u00052\u0006\u0010L\u001a\u00020DH\u0002J\u001c\u0010M\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u00050#2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001c\u0010N\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020D0#2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010O\u001a\u00020$2\u0006\u0010P\u001a\u00020$2\u0006\u0010Q\u001a\u00020$H\u0002J\u0018\u0010R\u001a\n S*\u0004\u0018\u000103032\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010T\u001a\u00020A2\u0006\u0010U\u001a\u00020\fH\u0002J\u0010\u0010T\u001a\u00020A2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001b\u0010V\u001a\u00020A2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020A0XH\u0000¢\u0006\u0002\bYJ\u0010\u0010Z\u001a\u00020A2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010Z\u001a\u00020A2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001e\u0010[\u001a\u00020A2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020A0XH\u0002J\u0010\u0010]\u001a\u00020A2\u0006\u0010^\u001a\u00020\u0003H\u0002J\u0010\u0010_\u001a\u00020A2\u0006\u0010`\u001a\u00020aH\u0002J\u001e\u0010b\u001a\u00020A2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020A0XH\u0002R\u001e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0012R&\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010*\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010-\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u00102\u001a\u0004\u0018\u000103X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R \u00108\u001a\b\u0012\u0004\u0012\u0002090\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001d\"\u0004\b;\u0010\u001fR\u001e\u0010=\u001a\u00020<2\u0006\u0010\u000f\u001a\u00020<@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?¨\u0006c"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGAVideoEntity;", "", "json", "Lorg/json/JSONObject;", "cacheDir", "Ljava/io/File;", "(Lorg/json/JSONObject;Ljava/io/File;)V", "frameWidth", "", "frameHeight", "(Lorg/json/JSONObject;Ljava/io/File;II)V", "entity", "Lcom/opensource/svgaplayer/proto/MovieEntity;", "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;)V", "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;II)V", "<set-?>", "FPS", "getFPS", "()I", "antiAlias", "", "getAntiAlias", "()Z", "setAntiAlias", "(Z)V", "audioList", "", "Lcom/opensource/svgaplayer/entities/SVGAAudioEntity;", "getAudioList$com_opensource_svgaplayer", "()Ljava/util/List;", "setAudioList$com_opensource_svgaplayer", "(Ljava/util/List;)V", "frames", "getFrames", "imageMap", "Ljava/util/HashMap;", "", "Landroid/graphics/Bitmap;", "getImageMap$com_opensource_svgaplayer", "()Ljava/util/HashMap;", "setImageMap$com_opensource_svgaplayer", "(Ljava/util/HashMap;)V", "mCacheDir", "mFrameHeight", "mFrameWidth", "movieItem", "getMovieItem", "()Lcom/opensource/svgaplayer/proto/MovieEntity;", "setMovieItem", "(Lcom/opensource/svgaplayer/proto/MovieEntity;)V", "soundPool", "Landroid/media/SoundPool;", "getSoundPool$com_opensource_svgaplayer", "()Landroid/media/SoundPool;", "setSoundPool$com_opensource_svgaplayer", "(Landroid/media/SoundPool;)V", "spriteList", "Lcom/opensource/svgaplayer/entities/SVGAVideoSpriteEntity;", "getSpriteList$com_opensource_svgaplayer", "setSpriteList$com_opensource_svgaplayer", "Lcom/opensource/svgaplayer/utils/SVGARect;", "videoSize", "getVideoSize", "()Lcom/opensource/svgaplayer/utils/SVGARect;", "clear", "", "createBitmap", "byteArray", "", "filePath", "createSvgaAudioEntity", "audio", "Lcom/opensource/svgaplayer/proto/AudioEntity;", "audiosFileMap", "generateAudioFile", "audioCache", "value", "generateAudioFileMap", "generateAudioMap", "generateBitmapFilePath", "imgName", "imgKey", "generateSoundPool", "kotlin.jvm.PlatformType", "parserImages", "obj", "prepare", "callback", "Lkotlin/Function0;", "prepare$com_opensource_svgaplayer", "resetSprites", "setupAudios", "completionBlock", "setupByJson", "movieObject", "setupByMovie", "movieParams", "Lcom/opensource/svgaplayer/proto/MovieParams;", "setupSoundPool", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SVGAVideoEntity {
    private int FPS;
    private boolean antiAlias;
    @NotNull
    private List<SVGAAudioEntity> audioList;
    private int frames;
    @NotNull
    private HashMap<String, Bitmap> imageMap;
    private File mCacheDir;
    private int mFrameHeight;
    private int mFrameWidth;
    @Nullable
    private MovieEntity movieItem;
    @Nullable
    private SoundPool soundPool;
    @NotNull
    private List<SVGAVideoSpriteEntity> spriteList;
    @NotNull
    private SVGARect videoSize;

    public final boolean getAntiAlias() {
        return this.antiAlias;
    }

    public final void setAntiAlias(boolean z) {
        this.antiAlias = z;
    }

    @Nullable
    public final MovieEntity getMovieItem() {
        return this.movieItem;
    }

    public final void setMovieItem(@Nullable MovieEntity movieEntity) {
        this.movieItem = movieEntity;
    }

    @NotNull
    public final SVGARect getVideoSize() {
        return this.videoSize;
    }

    public final int getFPS() {
        return this.FPS;
    }

    public final int getFrames() {
        return this.frames;
    }

    @NotNull
    public final List<SVGAVideoSpriteEntity> getSpriteList$com_opensource_svgaplayer() {
        return this.spriteList;
    }

    public final void setSpriteList$com_opensource_svgaplayer(@NotNull List<SVGAVideoSpriteEntity> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.spriteList = list;
    }

    @NotNull
    public final List<SVGAAudioEntity> getAudioList$com_opensource_svgaplayer() {
        return this.audioList;
    }

    public final void setAudioList$com_opensource_svgaplayer(@NotNull List<SVGAAudioEntity> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.audioList = list;
    }

    @Nullable
    public final SoundPool getSoundPool$com_opensource_svgaplayer() {
        return this.soundPool;
    }

    public final void setSoundPool$com_opensource_svgaplayer(@Nullable SoundPool soundPool) {
        this.soundPool = soundPool;
    }

    @NotNull
    public final HashMap<String, Bitmap> getImageMap$com_opensource_svgaplayer() {
        return this.imageMap;
    }

    public final void setImageMap$com_opensource_svgaplayer(@NotNull HashMap<String, Bitmap> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.imageMap = hashMap;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SVGAVideoEntity(@NotNull JSONObject json, @NotNull File cacheDir) {
        this(json, cacheDir, 0, 0);
        Intrinsics.checkParameterIsNotNull(json, "json");
        Intrinsics.checkParameterIsNotNull(cacheDir, "cacheDir");
    }

    public SVGAVideoEntity(@NotNull JSONObject json, @NotNull File cacheDir, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(json, "json");
        Intrinsics.checkParameterIsNotNull(cacheDir, "cacheDir");
        this.antiAlias = true;
        this.videoSize = new SVGARect(0.0d, 0.0d, 0.0d, 0.0d);
        this.FPS = 15;
        this.spriteList = CollectionsKt.emptyList();
        this.audioList = CollectionsKt.emptyList();
        this.imageMap = new HashMap<>();
        this.mFrameWidth = i;
        this.mFrameHeight = i2;
        this.mCacheDir = cacheDir;
        JSONObject optJSONObject = json.optJSONObject("movie");
        if (optJSONObject != null) {
            setupByJson(optJSONObject);
            try {
                parserImages(json);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
            }
            resetSprites(json);
        }
    }

    private final void setupByJson(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("viewBox");
        if (optJSONObject != null) {
            this.videoSize = new SVGARect(0.0d, 0.0d, optJSONObject.optDouble("width", 0.0d), optJSONObject.optDouble("height", 0.0d));
        }
        this.FPS = jSONObject.optInt("fps", 20);
        this.frames = jSONObject.optInt("frames", 0);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SVGAVideoEntity(@NotNull MovieEntity entity, @NotNull File cacheDir) {
        this(entity, cacheDir, 0, 0);
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        Intrinsics.checkParameterIsNotNull(cacheDir, "cacheDir");
    }

    public SVGAVideoEntity(@NotNull MovieEntity entity, @NotNull File cacheDir, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(entity, "entity");
        Intrinsics.checkParameterIsNotNull(cacheDir, "cacheDir");
        this.antiAlias = true;
        this.videoSize = new SVGARect(0.0d, 0.0d, 0.0d, 0.0d);
        this.FPS = 15;
        this.spriteList = CollectionsKt.emptyList();
        this.audioList = CollectionsKt.emptyList();
        this.imageMap = new HashMap<>();
        this.mFrameWidth = i;
        this.mFrameHeight = i2;
        this.mCacheDir = cacheDir;
        this.movieItem = entity;
        MovieParams movieParams = entity.params;
        if (movieParams != null) {
            setupByMovie(movieParams);
        }
        try {
            parserImages(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
        }
        resetSprites(entity);
    }

    private final void setupByMovie(MovieParams movieParams) {
        Float f;
        Float f2 = movieParams.viewBoxWidth;
        this.videoSize = new SVGARect(0.0d, 0.0d, f2 != null ? f2.floatValue() : 0.0f, movieParams.viewBoxHeight != null ? f.floatValue() : 0.0f);
        Integer num = movieParams.fps;
        this.FPS = num != null ? num.intValue() : 20;
        Integer num2 = movieParams.frames;
        this.frames = num2 != null ? num2.intValue() : 0;
    }

    public final void prepare$com_opensource_svgaplayer(@NotNull final Functions<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        MovieEntity movieEntity = this.movieItem;
        if (movieEntity == null) {
            callback.invoke();
            return;
        }
        if (movieEntity == null) {
            Intrinsics.throwNpe();
        }
        setupAudios(movieEntity, new Functions<Unit>() { // from class: com.opensource.svgaplayer.SVGAVideoEntity$prepare$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                Functions.this.invoke();
            }
        });
    }

    private final void parserImages(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("images");
        if (optJSONObject != null) {
            Iterator<String> keys = optJSONObject.keys();
            Intrinsics.checkExpressionValueIsNotNull(keys, "imgJson.keys()");
            while (keys.hasNext()) {
                String imgKey = keys.next();
                String obj = optJSONObject.get(imgKey).toString();
                Intrinsics.checkExpressionValueIsNotNull(imgKey, "imgKey");
                String generateBitmapFilePath = generateBitmapFilePath(obj, imgKey);
                if (generateBitmapFilePath.length() == 0) {
                    return;
                }
                String replace$default = StringsKt.replace$default(imgKey, ".matte", "", false, 4, (Object) null);
                Bitmap createBitmap = createBitmap(generateBitmapFilePath);
                if (createBitmap != null) {
                    this.imageMap.put(replace$default, createBitmap);
                }
            }
        }
    }

    private final String generateBitmapFilePath(String str, String str2) {
        String str3 = this.mCacheDir.getAbsolutePath() + "/" + str;
        String str4 = str3 + ".png";
        String str5 = this.mCacheDir.getAbsolutePath() + "/" + str2 + ".png";
        return new File(str3).exists() ? str3 : new File(str4).exists() ? str4 : new File(str5).exists() ? str5 : "";
    }

    private final Bitmap createBitmap(String str) {
        return SVGABitmapFileDecoder.INSTANCE.decodeBitmapFrom(str, this.mFrameWidth, this.mFrameHeight);
    }

    private final void parserImages(MovieEntity movieEntity) {
        Set<Map.Entry<String, ByteString>> entrySet;
        Map<String, ByteString> map = movieEntity.images;
        if (map == null || (entrySet = map.entrySet()) == null) {
            return;
        }
        Iterator<T> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            byte[] byteArray = ((ByteString) entry.getValue()).toByteArray();
            Intrinsics.checkExpressionValueIsNotNull(byteArray, "byteArray");
            if (byteArray.length >= 4) {
                List<Byte> slice = ArraysKt.slice(byteArray, new IntRange(0, 3));
                if (slice.get(0).byteValue() != 73 || slice.get(1).byteValue() != 68 || slice.get(2).byteValue() != 51) {
                    String utf8 = ((ByteString) entry.getValue()).utf8();
                    Intrinsics.checkExpressionValueIsNotNull(utf8, "entry.value.utf8()");
                    Object key = entry.getKey();
                    Intrinsics.checkExpressionValueIsNotNull(key, "entry.key");
                    Bitmap createBitmap = createBitmap(byteArray, generateBitmapFilePath(utf8, (String) key));
                    if (createBitmap != null) {
                        Object key2 = entry.getKey();
                        Intrinsics.checkExpressionValueIsNotNull(key2, "entry.key");
                        this.imageMap.put(key2, createBitmap);
                    }
                }
            }
        }
    }

    private final Bitmap createBitmap(byte[] bArr, String str) {
        Bitmap decodeBitmapFrom = SVGABitmapByteArrayDecoder.INSTANCE.decodeBitmapFrom(bArr, this.mFrameWidth, this.mFrameHeight);
        return decodeBitmapFrom != null ? decodeBitmapFrom : createBitmap(str);
    }

    private final void resetSprites(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("sprites");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(new SVGAVideoSpriteEntity(optJSONObject));
                }
            }
        }
        this.spriteList = CollectionsKt.toList(arrayList);
    }

    private final void resetSprites(MovieEntity movieEntity) {
        ArrayList emptyList;
        List<SpriteEntity> list = movieEntity.sprites;
        if (list == null) {
            emptyList = CollectionsKt.emptyList();
        } else {
            List<SpriteEntity> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (SpriteEntity it : list2) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                arrayList.add(new SVGAVideoSpriteEntity(it));
            }
            emptyList = arrayList;
        }
        this.spriteList = emptyList;
    }

    private final void setupAudios(MovieEntity movieEntity, Functions<Unit> functions) {
        if (movieEntity.audios == null || movieEntity.audios.isEmpty()) {
            functions.invoke();
            return;
        }
        setupSoundPool(movieEntity, functions);
        HashMap<String, File> generateAudioFileMap = generateAudioFileMap(movieEntity);
        List<AudioEntity> list = movieEntity.audios;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (AudioEntity audio : list) {
            Intrinsics.checkExpressionValueIsNotNull(audio, "audio");
            arrayList.add(createSvgaAudioEntity(audio, generateAudioFileMap));
        }
        this.audioList = arrayList;
    }

    private final SVGAAudioEntity createSvgaAudioEntity(AudioEntity audioEntity, HashMap<String, File> hashMap) {
        File file;
        FileInputStream fileInputStream;
        double available;
        long j;
        SVGAAudioEntity sVGAAudioEntity = new SVGAAudioEntity(audioEntity);
        Integer num = audioEntity.startTime;
        double intValue = num != null ? num.intValue() : 0;
        Integer num2 = audioEntity.totalTime;
        double intValue2 = num2 != null ? num2.intValue() : 0;
        if (((int) intValue2) != 0 && (file = hashMap.get(audioEntity.audioKey)) != null) {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            Throwable th = null;
            try {
                fileInputStream = fileInputStream2;
                available = fileInputStream.available();
                j = (long) ((intValue / intValue2) * available);
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                SoundPool soundPool = this.soundPool;
                sVGAAudioEntity.setSoundID(soundPool != null ? Integer.valueOf(soundPool.load(fileInputStream.getFD(), j, (long) available, 1)) : null);
                Unit unit = Unit.INSTANCE;
                Closeable.closeFinally(fileInputStream2, th);
            } catch (Throwable th3) {
                th = th3;
                Throwable th4 = th;
                try {
                    throw th4;
                } catch (Throwable th5) {
                    Closeable.closeFinally(fileInputStream2, th4);
                    throw th5;
                }
            }
        }
        return sVGAAudioEntity;
    }

    private final File generateAudioFile(File file, byte[] bArr) {
        file.createNewFile();
        new FileOutputStream(file).write(bArr);
        return file;
    }

    private final HashMap<String, File> generateAudioFileMap(MovieEntity movieEntity) {
        HashMap<String, byte[]> generateAudioMap = generateAudioMap(movieEntity);
        HashMap<String, File> hashMap = new HashMap<>();
        HashMap<String, byte[]> hashMap2 = generateAudioMap;
        if (hashMap2.size() > 0) {
            for (Map.Entry<String, byte[]> entry : hashMap2.entrySet()) {
                File buildAudioFile = SVGACache.INSTANCE.buildAudioFile(entry.getKey());
                HashMap<String, File> hashMap3 = hashMap;
                String key = entry.getKey();
                File file = buildAudioFile.exists() ? buildAudioFile : null;
                if (file == null) {
                    file = generateAudioFile(buildAudioFile, entry.getValue());
                }
                hashMap3.put(key, file);
            }
        }
        return hashMap;
    }

    private final HashMap<String, byte[]> generateAudioMap(MovieEntity movieEntity) {
        Set<Map.Entry<String, ByteString>> entrySet;
        HashMap<String, byte[]> hashMap = new HashMap<>();
        Map<String, ByteString> map = movieEntity.images;
        if (map != null && (entrySet = map.entrySet()) != null) {
            Iterator<T> it = entrySet.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String imageKey = (String) entry.getKey();
                byte[] byteArray = ((ByteString) entry.getValue()).toByteArray();
                Intrinsics.checkExpressionValueIsNotNull(byteArray, "byteArray");
                if (byteArray.length >= 4) {
                    List<Byte> slice = ArraysKt.slice(byteArray, new IntRange(0, 3));
                    if (slice.get(0).byteValue() == 73 && slice.get(1).byteValue() == 68 && slice.get(2).byteValue() == 51) {
                        Intrinsics.checkExpressionValueIsNotNull(imageKey, "imageKey");
                        hashMap.put(imageKey, byteArray);
                    }
                }
            }
        }
        return hashMap;
    }

    private final void setupSoundPool(final MovieEntity movieEntity, final Functions<Unit> functions) {
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        this.soundPool = generateSoundPool(movieEntity);
        SoundPool soundPool = this.soundPool;
        if (soundPool != null) {
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.opensource.svgaplayer.SVGAVideoEntity$setupSoundPool$1
                @Override // android.media.SoundPool.OnLoadCompleteListener
                public final void onLoadComplete(SoundPool soundPool2, int i, int i2) {
                    Ref.IntRef.this.element++;
                    int i3 = Ref.IntRef.this.element;
                    List<AudioEntity> list = movieEntity.audios;
                    Intrinsics.checkExpressionValueIsNotNull(list, "entity.audios");
                    if (i3 >= list.size()) {
                        functions.invoke();
                    }
                }
            });
        }
    }

    private final SoundPool generateSoundPool(MovieEntity movieEntity) {
        if (Build.VERSION.SDK_INT >= 21) {
            SoundPool.Builder audioAttributes = new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(1).build());
            List<AudioEntity> list = movieEntity.audios;
            Intrinsics.checkExpressionValueIsNotNull(list, "entity.audios");
            return audioAttributes.setMaxStreams(RangesKt.coerceAtMost(12, list.size())).build();
        }
        List<AudioEntity> list2 = movieEntity.audios;
        Intrinsics.checkExpressionValueIsNotNull(list2, "entity.audios");
        return new SoundPool(RangesKt.coerceAtMost(12, list2.size()), 3, 0);
    }

    public final void clear() {
        SoundPool soundPool = this.soundPool;
        if (soundPool != null) {
            soundPool.release();
        }
        this.soundPool = null;
        this.audioList = CollectionsKt.emptyList();
        this.spriteList = CollectionsKt.emptyList();
        this.imageMap.clear();
    }
}
