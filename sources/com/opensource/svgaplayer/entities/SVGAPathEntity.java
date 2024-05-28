package com.opensource.svgaplayer.entities;

import android.graphics.Path;
import com.opensource.svgaplayer.utils.SVGAStructs;
import java.util.Set;
import java.util.StringTokenizer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: SVGAPathEntity.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006J \u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m1890d2 = {"Lcom/opensource/svgaplayer/entities/SVGAPathEntity;", "", "originValue", "", "(Ljava/lang/String;)V", "cachedPath", "Landroid/graphics/Path;", "replacedValue", "buildPath", "", "toPath", "operate", "finalPath", "method", "args", "Ljava/util/StringTokenizer;", "com.opensource.svgaplayer"}, m1889k = 1, m1888mv = {1, 1, 15})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SVGAPathEntity {
    private Path cachedPath;
    private final String replacedValue;

    public SVGAPathEntity(@NotNull String originValue) {
        Intrinsics.checkParameterIsNotNull(originValue, "originValue");
        this.replacedValue = StringsKt.contains$default((CharSequence) originValue, (CharSequence) ",", false, 2, (Object) null) ? StringsKt.replace$default(originValue, ",", " ", false, 4, (Object) null) : originValue;
    }

    public final void buildPath(@NotNull Path toPath) {
        Set set;
        Intrinsics.checkParameterIsNotNull(toPath, "toPath");
        Path path = this.cachedPath;
        if (path != null) {
            toPath.set(path);
            return;
        }
        Path path2 = new Path();
        StringTokenizer stringTokenizer = new StringTokenizer(this.replacedValue, "MLHVCSQRAZmlhvcsqraz", true);
        String str = "";
        while (stringTokenizer.hasMoreTokens()) {
            String segment = stringTokenizer.nextToken();
            Intrinsics.checkExpressionValueIsNotNull(segment, "segment");
            if (!(segment.length() == 0)) {
                set = SVGAPathEntityKt.VALID_METHODS;
                if (set.contains(segment)) {
                    if (Intrinsics.areEqual(segment, "Z") || Intrinsics.areEqual(segment, "z")) {
                        operate(path2, segment, new StringTokenizer("", ""));
                    }
                    str = segment;
                } else {
                    operate(path2, str, new StringTokenizer(segment, " "));
                }
            }
        }
        this.cachedPath = path2;
        toPath.set(path2);
    }

    private final void operate(Path path, String str, StringTokenizer stringTokenizer) {
        float f;
        float f2;
        float f3;
        float f4;
        SVGAStructs sVGAStructs;
        int i = 0;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        while (stringTokenizer.hasMoreTokens()) {
            try {
                String s = stringTokenizer.nextToken();
                Intrinsics.checkExpressionValueIsNotNull(s, "s");
                if (!(s.length() == 0)) {
                    if (i == 0) {
                        f5 = Float.parseFloat(s);
                    }
                    if (i == 1) {
                        f6 = Float.parseFloat(s);
                    }
                    if (i == 2) {
                        f7 = Float.parseFloat(s);
                    }
                    if (i == 3) {
                        f8 = Float.parseFloat(s);
                    }
                    if (i == 4) {
                        f9 = Float.parseFloat(s);
                    }
                    if (i == 5) {
                        f10 = Float.parseFloat(s);
                    }
                    i++;
                }
            } catch (Exception unused) {
                f = f5;
                f2 = f6;
                f3 = f7;
                f4 = f8;
            }
        }
        f = f5;
        f2 = f6;
        f3 = f7;
        f4 = f8;
        SVGAStructs sVGAStructs2 = new SVGAStructs(0.0f, 0.0f, 0.0f);
        if (Intrinsics.areEqual(str, "M")) {
            path.moveTo(f, f2);
            sVGAStructs = new SVGAStructs(f, f2, 0.0f);
        } else if (Intrinsics.areEqual(str, "m")) {
            path.rMoveTo(f, f2);
            sVGAStructs = new SVGAStructs(sVGAStructs2.getX() + f, sVGAStructs2.getY() + f2, 0.0f);
        } else {
            sVGAStructs = sVGAStructs2;
        }
        if (Intrinsics.areEqual(str, "L")) {
            path.lineTo(f, f2);
        } else if (Intrinsics.areEqual(str, "l")) {
            path.rLineTo(f, f2);
        }
        if (Intrinsics.areEqual(str, "C")) {
            path.cubicTo(f, f2, f3, f4, f9, f10);
        } else if (Intrinsics.areEqual(str, "c")) {
            path.rCubicTo(f, f2, f3, f4, f9, f10);
        }
        if (Intrinsics.areEqual(str, "Q")) {
            path.quadTo(f, f2, f3, f4);
        } else if (Intrinsics.areEqual(str, "q")) {
            path.rQuadTo(f, f2, f3, f4);
        }
        if (Intrinsics.areEqual(str, "H")) {
            path.lineTo(f, sVGAStructs.getY());
        } else if (Intrinsics.areEqual(str, "h")) {
            path.rLineTo(f, 0.0f);
        }
        if (Intrinsics.areEqual(str, "V")) {
            path.lineTo(sVGAStructs.getX(), f);
        } else if (Intrinsics.areEqual(str, "v")) {
            path.rLineTo(0.0f, f);
        }
        if (Intrinsics.areEqual(str, "Z")) {
            path.close();
        } else if (Intrinsics.areEqual(str, "z")) {
            path.close();
        }
    }
}
