package jp.wasabeef.glide.transformations.internal;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SupportRSBlur {
    /* JADX WARN: Removed duplicated region for block: B:29:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0077  */
    @android.annotation.TargetApi(18)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap blur(android.content.Context r5, android.graphics.Bitmap r6, int r7) throws androidx.renderscript.RSRuntimeException {
        /*
            r0 = 23
            r1 = 0
            androidx.renderscript.RenderScript r5 = androidx.renderscript.RenderScript.create(r5)     // Catch: java.lang.Throwable -> L5a
            androidx.renderscript.RenderScript$RSMessageHandler r2 = new androidx.renderscript.RenderScript$RSMessageHandler     // Catch: java.lang.Throwable -> L57
            r2.<init>()     // Catch: java.lang.Throwable -> L57
            r5.setMessageHandler(r2)     // Catch: java.lang.Throwable -> L57
            androidx.renderscript.Allocation$MipmapControl r2 = androidx.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch: java.lang.Throwable -> L57
            r3 = 1
            androidx.renderscript.Allocation r2 = androidx.renderscript.Allocation.createFromBitmap(r5, r6, r2, r3)     // Catch: java.lang.Throwable -> L57
            androidx.renderscript.Type r3 = r2.getType()     // Catch: java.lang.Throwable -> L54
            androidx.renderscript.Allocation r3 = androidx.renderscript.Allocation.createTyped(r5, r3)     // Catch: java.lang.Throwable -> L54
            androidx.renderscript.Element r4 = androidx.renderscript.Element.U8_4(r5)     // Catch: java.lang.Throwable -> L50
            androidx.renderscript.ScriptIntrinsicBlur r1 = androidx.renderscript.ScriptIntrinsicBlur.create(r5, r4)     // Catch: java.lang.Throwable -> L50
            r1.setInput(r2)     // Catch: java.lang.Throwable -> L50
            float r7 = (float) r7     // Catch: java.lang.Throwable -> L50
            r1.setRadius(r7)     // Catch: java.lang.Throwable -> L50
            r1.forEach(r3)     // Catch: java.lang.Throwable -> L50
            r3.copyTo(r6)     // Catch: java.lang.Throwable -> L50
            if (r5 == 0) goto L40
            int r7 = android.os.Build.VERSION.SDK_INT
            if (r7 < r0) goto L3d
            androidx.renderscript.RenderScript.releaseAllContexts()
            goto L40
        L3d:
            r5.destroy()
        L40:
            if (r2 == 0) goto L45
            r2.destroy()
        L45:
            if (r3 == 0) goto L4a
            r3.destroy()
        L4a:
            if (r1 == 0) goto L4f
            r1.destroy()
        L4f:
            return r6
        L50:
            r6 = move-exception
            r7 = r1
            r1 = r3
            goto L5e
        L54:
            r6 = move-exception
            r7 = r1
            goto L5e
        L57:
            r6 = move-exception
            r7 = r1
            goto L5d
        L5a:
            r6 = move-exception
            r5 = r1
            r7 = r5
        L5d:
            r2 = r7
        L5e:
            if (r5 == 0) goto L6b
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 < r0) goto L68
            androidx.renderscript.RenderScript.releaseAllContexts()
            goto L6b
        L68:
            r5.destroy()
        L6b:
            if (r2 == 0) goto L70
            r2.destroy()
        L70:
            if (r1 == 0) goto L75
            r1.destroy()
        L75:
            if (r7 == 0) goto L7a
            r7.destroy()
        L7a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.wasabeef.glide.transformations.internal.SupportRSBlur.blur(android.content.Context, android.graphics.Bitmap, int):android.graphics.Bitmap");
    }
}
