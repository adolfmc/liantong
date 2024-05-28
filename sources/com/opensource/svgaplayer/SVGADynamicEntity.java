package com.opensource.svgaplayer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.text.BoringLayout;
import android.text.StaticLayout;
import android.text.TextPaint;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: SVGADynamicEntity.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020=2\u0006\u0010?\u001a\u00020\u0005J\u0014\u0010>\u001a\u00020=2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00050@JF\u0010A\u001a\u00020=26\u0010B\u001a2\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\r2\u0006\u0010C\u001a\u00020\u0005Jp\u0010D\u001a\u00020=2`\u0010B\u001a\\\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00140\u00182\u0006\u0010C\u001a\u00020\u0005J\u0016\u0010E\u001a\u00020=2\u0006\u0010F\u001a\u00020%2\u0006\u0010C\u001a\u00020\u0005J\u0016\u0010E\u001a\u00020=2\u0006\u0010G\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u0005J\u0016\u0010H\u001a\u00020=2\u0006\u0010I\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u0005J\u0016\u0010H\u001a\u00020=2\u0006\u0010I\u001a\u00020)2\u0006\u0010C\u001a\u00020\u0005J\u001e\u0010H\u001a\u00020=2\u0006\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u0002002\u0006\u0010C\u001a\u00020\u0005J\u0016\u0010L\u001a\u00020=2\u0006\u0010M\u001a\u00020\u00142\u0006\u0010C\u001a\u00020\u0005R6\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0096\u0001\u0010\f\u001a~\u0012\u0004\u0012\u00020\u0005\u00124\u00122\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\r0\u0004j>\u0012\u0004\u0012\u00020\u0005\u00124\u00122\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\r`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\t\"\u0004\b\u0016\u0010\u000bRë\u0001\u0010\u0017\u001aÒ\u0001\u0012\u0004\u0012\u00020\u0005\u0012^\u0012\\\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00140\u00180\u0004jh\u0012\u0004\u0012\u00020\u0005\u0012^\u0012\\\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00140\u0018`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\t\"\u0004\b\u001c\u0010\u000bR6\u0010\u001d\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0014`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\t\"\u0004\b\u001f\u0010\u000bR6\u0010 \u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020!0\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020!`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR6\u0010$\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020%0\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020%`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\t\"\u0004\b'\u0010\u000bR6\u0010(\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)0\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020)`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\t\"\u0004\b+\u0010\u000bR6\u0010,\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\t\"\u0004\b.\u0010\u000bR6\u0010/\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002000\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000200`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\t\"\u0004\b2\u0010\u000bR\u001a\u00103\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R6\u00108\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002090\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000209`\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\t\"\u0004\b;\u0010\u000b¨\u0006N"}, m1890d2 = {"Lcom/opensource/svgaplayer/SVGADynamicEntity;", "", "()V", "dynamicBoringLayoutText", "Ljava/util/HashMap;", "", "Landroid/text/BoringLayout;", "Lkotlin/collections/HashMap;", "getDynamicBoringLayoutText$com_opensource_svgaplayer", "()Ljava/util/HashMap;", "setDynamicBoringLayoutText$com_opensource_svgaplayer", "(Ljava/util/HashMap;)V", "dynamicDrawer", "Lkotlin/Function2;", "Landroid/graphics/Canvas;", "Lkotlin/ParameterName;", "name", "canvas", "", "frameIndex", "", "getDynamicDrawer$com_opensource_svgaplayer", "setDynamicDrawer$com_opensource_svgaplayer", "dynamicDrawerSized", "Lkotlin/Function4;", "width", "height", "getDynamicDrawerSized$com_opensource_svgaplayer", "setDynamicDrawerSized$com_opensource_svgaplayer", "dynamicHidden", "getDynamicHidden$com_opensource_svgaplayer", "setDynamicHidden$com_opensource_svgaplayer", "dynamicIClickArea", "Lcom/opensource/svgaplayer/IClickAreaListener;", "getDynamicIClickArea$com_opensource_svgaplayer", "setDynamicIClickArea$com_opensource_svgaplayer", "dynamicImage", "Landroid/graphics/Bitmap;", "getDynamicImage$com_opensource_svgaplayer", "setDynamicImage$com_opensource_svgaplayer", "dynamicStaticLayoutText", "Landroid/text/StaticLayout;", "getDynamicStaticLayoutText$com_opensource_svgaplayer", "setDynamicStaticLayoutText$com_opensource_svgaplayer", "dynamicText", "getDynamicText$com_opensource_svgaplayer", "setDynamicText$com_opensource_svgaplayer", "dynamicTextPaint", "Landroid/text/TextPaint;", "getDynamicTextPaint$com_opensource_svgaplayer", "setDynamicTextPaint$com_opensource_svgaplayer", "isTextDirty", "isTextDirty$com_opensource_svgaplayer", "()Z", "setTextDirty$com_opensource_svgaplayer", "(Z)V", "mClickMap", "", "getMClickMap$com_opensource_svgaplayer", "setMClickMap$com_opensource_svgaplayer", "clearDynamicObjects", "", "setClickArea", "clickKey", "", "setDynamicDrawer", "drawer", "forKey", "setDynamicDrawerSized", "setDynamicImage", "bitmap", "url", "setDynamicText", "layoutText", "text", "textPaint", "setHidden", "value", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SVGADynamicEntity {
    private boolean isTextDirty;
    @NotNull
    private HashMap<String, Boolean> dynamicHidden = new HashMap<>();
    @NotNull
    private HashMap<String, Bitmap> dynamicImage = new HashMap<>();
    @NotNull
    private HashMap<String, String> dynamicText = new HashMap<>();
    @NotNull
    private HashMap<String, TextPaint> dynamicTextPaint = new HashMap<>();
    @NotNull
    private HashMap<String, StaticLayout> dynamicStaticLayoutText = new HashMap<>();
    @NotNull
    private HashMap<String, BoringLayout> dynamicBoringLayoutText = new HashMap<>();
    @NotNull
    private HashMap<String, Function2<Canvas, Integer, Boolean>> dynamicDrawer = new HashMap<>();
    @NotNull
    private HashMap<String, int[]> mClickMap = new HashMap<>();
    @NotNull
    private HashMap<String, IClickAreaListener> dynamicIClickArea = new HashMap<>();
    @NotNull
    private HashMap<String, Function4<Canvas, Integer, Integer, Integer, Boolean>> dynamicDrawerSized = new HashMap<>();

    @NotNull
    public final HashMap<String, Boolean> getDynamicHidden$com_opensource_svgaplayer() {
        return this.dynamicHidden;
    }

    public final void setDynamicHidden$com_opensource_svgaplayer(@NotNull HashMap<String, Boolean> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicHidden = hashMap;
    }

    @NotNull
    public final HashMap<String, Bitmap> getDynamicImage$com_opensource_svgaplayer() {
        return this.dynamicImage;
    }

    public final void setDynamicImage$com_opensource_svgaplayer(@NotNull HashMap<String, Bitmap> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicImage = hashMap;
    }

    @NotNull
    public final HashMap<String, String> getDynamicText$com_opensource_svgaplayer() {
        return this.dynamicText;
    }

    public final void setDynamicText$com_opensource_svgaplayer(@NotNull HashMap<String, String> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicText = hashMap;
    }

    @NotNull
    public final HashMap<String, TextPaint> getDynamicTextPaint$com_opensource_svgaplayer() {
        return this.dynamicTextPaint;
    }

    public final void setDynamicTextPaint$com_opensource_svgaplayer(@NotNull HashMap<String, TextPaint> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicTextPaint = hashMap;
    }

    @NotNull
    public final HashMap<String, StaticLayout> getDynamicStaticLayoutText$com_opensource_svgaplayer() {
        return this.dynamicStaticLayoutText;
    }

    public final void setDynamicStaticLayoutText$com_opensource_svgaplayer(@NotNull HashMap<String, StaticLayout> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicStaticLayoutText = hashMap;
    }

    @NotNull
    public final HashMap<String, BoringLayout> getDynamicBoringLayoutText$com_opensource_svgaplayer() {
        return this.dynamicBoringLayoutText;
    }

    public final void setDynamicBoringLayoutText$com_opensource_svgaplayer(@NotNull HashMap<String, BoringLayout> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicBoringLayoutText = hashMap;
    }

    @NotNull
    public final HashMap<String, Function2<Canvas, Integer, Boolean>> getDynamicDrawer$com_opensource_svgaplayer() {
        return this.dynamicDrawer;
    }

    public final void setDynamicDrawer$com_opensource_svgaplayer(@NotNull HashMap<String, Function2<Canvas, Integer, Boolean>> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicDrawer = hashMap;
    }

    @NotNull
    public final HashMap<String, int[]> getMClickMap$com_opensource_svgaplayer() {
        return this.mClickMap;
    }

    public final void setMClickMap$com_opensource_svgaplayer(@NotNull HashMap<String, int[]> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.mClickMap = hashMap;
    }

    @NotNull
    public final HashMap<String, IClickAreaListener> getDynamicIClickArea$com_opensource_svgaplayer() {
        return this.dynamicIClickArea;
    }

    public final void setDynamicIClickArea$com_opensource_svgaplayer(@NotNull HashMap<String, IClickAreaListener> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicIClickArea = hashMap;
    }

    @NotNull
    public final HashMap<String, Function4<Canvas, Integer, Integer, Integer, Boolean>> getDynamicDrawerSized$com_opensource_svgaplayer() {
        return this.dynamicDrawerSized;
    }

    public final void setDynamicDrawerSized$com_opensource_svgaplayer(@NotNull HashMap<String, Function4<Canvas, Integer, Integer, Integer, Boolean>> hashMap) {
        Intrinsics.checkParameterIsNotNull(hashMap, "<set-?>");
        this.dynamicDrawerSized = hashMap;
    }

    public final boolean isTextDirty$com_opensource_svgaplayer() {
        return this.isTextDirty;
    }

    public final void setTextDirty$com_opensource_svgaplayer(boolean z) {
        this.isTextDirty = z;
    }

    public final void setHidden(boolean z, @NotNull String forKey) {
        Intrinsics.checkParameterIsNotNull(forKey, "forKey");
        this.dynamicHidden.put(forKey, Boolean.valueOf(z));
    }

    public final void setDynamicImage(@NotNull Bitmap bitmap, @NotNull String forKey) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        Intrinsics.checkParameterIsNotNull(forKey, "forKey");
        this.dynamicImage.put(forKey, bitmap);
    }

    public final void setDynamicImage(@NotNull String url, @NotNull String forKey) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(forKey, "forKey");
        SVGAParser.Companion.getThreadPoolExecutor$com_opensource_svgaplayer().execute(new SVGADynamicEntity$setDynamicImage$1(this, url, new Handler(), forKey));
    }

    public final void setDynamicText(@NotNull String text, @NotNull TextPaint textPaint, @NotNull String forKey) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(textPaint, "textPaint");
        Intrinsics.checkParameterIsNotNull(forKey, "forKey");
        this.isTextDirty = true;
        this.dynamicText.put(forKey, text);
        this.dynamicTextPaint.put(forKey, textPaint);
    }

    public final void setDynamicText(@NotNull StaticLayout layoutText, @NotNull String forKey) {
        Intrinsics.checkParameterIsNotNull(layoutText, "layoutText");
        Intrinsics.checkParameterIsNotNull(forKey, "forKey");
        this.isTextDirty = true;
        this.dynamicStaticLayoutText.put(forKey, layoutText);
    }

    public final void setDynamicText(@NotNull BoringLayout layoutText, @NotNull String forKey) {
        Intrinsics.checkParameterIsNotNull(layoutText, "layoutText");
        Intrinsics.checkParameterIsNotNull(forKey, "forKey");
        this.isTextDirty = true;
        if (BoringLayout.isBoring(layoutText.getText(), layoutText.getPaint()) != null) {
            this.dynamicBoringLayoutText.put(forKey, layoutText);
        }
    }

    public final void setDynamicDrawer(@NotNull Function2<? super Canvas, ? super Integer, Boolean> drawer, @NotNull String forKey) {
        Intrinsics.checkParameterIsNotNull(drawer, "drawer");
        Intrinsics.checkParameterIsNotNull(forKey, "forKey");
        this.dynamicDrawer.put(forKey, drawer);
    }

    public final void setClickArea(@NotNull List<String> clickKey) {
        Intrinsics.checkParameterIsNotNull(clickKey, "clickKey");
        for (String str : clickKey) {
            this.dynamicIClickArea.put(str, new IClickAreaListener() { // from class: com.opensource.svgaplayer.SVGADynamicEntity$setClickArea$1
                @Override // com.opensource.svgaplayer.IClickAreaListener
                public void onResponseArea(@NotNull String key, int i, int i2, int i3, int i4) {
                    Intrinsics.checkParameterIsNotNull(key, "key");
                    HashMap<String, int[]> mClickMap$com_opensource_svgaplayer = SVGADynamicEntity.this.getMClickMap$com_opensource_svgaplayer();
                    if (mClickMap$com_opensource_svgaplayer.get(key) == null) {
                        mClickMap$com_opensource_svgaplayer.put(key, new int[]{i, i2, i3, i4});
                        return;
                    }
                    int[] iArr = mClickMap$com_opensource_svgaplayer.get(key);
                    if (iArr != null) {
                        iArr[0] = i;
                        iArr[1] = i2;
                        iArr[2] = i3;
                        iArr[3] = i4;
                    }
                }
            });
        }
    }

    public final void setClickArea(@NotNull String clickKey) {
        Intrinsics.checkParameterIsNotNull(clickKey, "clickKey");
        this.dynamicIClickArea.put(clickKey, new IClickAreaListener() { // from class: com.opensource.svgaplayer.SVGADynamicEntity$setClickArea$2
            @Override // com.opensource.svgaplayer.IClickAreaListener
            public void onResponseArea(@NotNull String key, int i, int i2, int i3, int i4) {
                Intrinsics.checkParameterIsNotNull(key, "key");
                HashMap<String, int[]> mClickMap$com_opensource_svgaplayer = SVGADynamicEntity.this.getMClickMap$com_opensource_svgaplayer();
                if (mClickMap$com_opensource_svgaplayer.get(key) == null) {
                    mClickMap$com_opensource_svgaplayer.put(key, new int[]{i, i2, i3, i4});
                    return;
                }
                int[] iArr = mClickMap$com_opensource_svgaplayer.get(key);
                if (iArr != null) {
                    iArr[0] = i;
                    iArr[1] = i2;
                    iArr[2] = i3;
                    iArr[3] = i4;
                }
            }
        });
    }

    public final void setDynamicDrawerSized(@NotNull Function4<? super Canvas, ? super Integer, ? super Integer, ? super Integer, Boolean> drawer, @NotNull String forKey) {
        Intrinsics.checkParameterIsNotNull(drawer, "drawer");
        Intrinsics.checkParameterIsNotNull(forKey, "forKey");
        this.dynamicDrawerSized.put(forKey, drawer);
    }

    public final void clearDynamicObjects() {
        this.isTextDirty = true;
        this.dynamicHidden.clear();
        this.dynamicImage.clear();
        this.dynamicText.clear();
        this.dynamicTextPaint.clear();
        this.dynamicStaticLayoutText.clear();
        this.dynamicBoringLayoutText.clear();
        this.dynamicDrawer.clear();
        this.dynamicIClickArea.clear();
        this.mClickMap.clear();
        this.dynamicDrawerSized.clear();
    }
}
