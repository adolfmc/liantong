package com.sinovatech.unicom.hub.media.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.sinovatech.unicom.hub.media.gallery.MediaEntity;
import com.sinovatech.unicom.hub.utils.MsLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnMultiCompressListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MediaCompressUtils {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnCompressListener {
        void onCompressComplete();
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
        if (r7 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0064, code lost:
        com.sinovatech.unicom.hub.media.utils.IOUtils.flush(r7);
        com.sinovatech.unicom.hub.media.utils.IOUtils.close(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0082, code lost:
        if (r7 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0085, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File createVideoThumbnail(java.lang.String r6, java.lang.String r7) {
        /*
            r0 = 0
            java.lang.String r1 = com.sinovatech.unicom.hub.media.utils.FilenameUtils.getExtension(r7)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            java.lang.String r2 = r2.getName()     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            java.lang.String r4 = "jpg"
            java.lang.String r1 = r2.replace(r1, r4)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            r3.<init>(r6, r1)     // Catch: java.lang.Throwable -> L6e java.lang.Exception -> L71
            boolean r6 = r3.exists()     // Catch: java.lang.Exception -> L6b java.lang.Throwable -> L6e
            if (r6 != 0) goto L55
            r6 = 1
            android.graphics.Bitmap r6 = android.media.ThumbnailUtils.createVideoThumbnail(r7, r6)     // Catch: java.lang.Exception -> L6b java.lang.Throwable -> L6e
            if (r6 != 0) goto L32
            if (r6 == 0) goto L31
            boolean r7 = r6.isRecycled()
            if (r7 != 0) goto L31
            r6.recycle()
        L31:
            return r0
        L32:
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4f
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4f
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L44
            r1 = 100
            r6.compress(r0, r1, r7)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L44
            goto L57
        L3f:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L87
        L44:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L74
        L49:
            r7 = move-exception
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
            goto L87
        L4f:
            r7 = move-exception
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
            goto L74
        L55:
            r6 = r0
            r7 = r6
        L57:
            if (r6 == 0) goto L62
            boolean r0 = r6.isRecycled()
            if (r0 != 0) goto L62
            r6.recycle()
        L62:
            if (r7 == 0) goto L85
        L64:
            com.sinovatech.unicom.hub.media.utils.IOUtils.flush(r7)
            com.sinovatech.unicom.hub.media.utils.IOUtils.close(r7)
            goto L85
        L6b:
            r6 = move-exception
            r7 = r0
            goto L74
        L6e:
            r6 = move-exception
            r7 = r0
            goto L87
        L71:
            r6 = move-exception
            r7 = r0
            r3 = r7
        L74:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L86
            if (r0 == 0) goto L82
            boolean r6 = r0.isRecycled()
            if (r6 != 0) goto L82
            r0.recycle()
        L82:
            if (r7 == 0) goto L85
            goto L64
        L85:
            return r3
        L86:
            r6 = move-exception
        L87:
            if (r0 == 0) goto L92
            boolean r1 = r0.isRecycled()
            if (r1 != 0) goto L92
            r0.recycle()
        L92:
            if (r7 == 0) goto L9a
            com.sinovatech.unicom.hub.media.utils.IOUtils.flush(r7)
            com.sinovatech.unicom.hub.media.utils.IOUtils.close(r7)
        L9a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.hub.media.utils.MediaCompressUtils.createVideoThumbnail(java.lang.String, java.lang.String):java.io.File");
    }

    public static String videoDurationParse(long j) {
        String str = "";
        try {
            long j2 = j / 60000;
            long round = Math.round(((float) (j % 60000)) / 1000.0f);
            if (j2 < 10) {
                str = "0";
            }
            str = str + j2 + ":";
            if (round < 10) {
                str = str + "0";
            }
            return str + round;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static void compressMediaImage(Context context, List<MediaEntity> list, int i, final OnCompressListener onCompressListener) {
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            MediaEntity mediaEntity = list.get(i2);
            if (!mediaEntity.getMediaType().equals("video") && ((mediaEntity.getMimeType() == null || (!mediaEntity.getMimeType().contains("gif") && !mediaEntity.getMimeType().contains("GIF") && !mediaEntity.getMimeType().contains("Gif"))) && new File(mediaEntity.getOriginalPath()).length() > i * 1024)) {
                arrayList.add(mediaEntity);
                arrayList2.add(new File(mediaEntity.getOriginalPath()));
            }
        }
        if (arrayList2.size() > 0) {
            Luban.compress(arrayList2, MediaStoreUtils.getAppMediaHubCache(context, "imagethumbnail")).setCompressFormat(Bitmap.CompressFormat.JPEG).putGear(3).launch(new OnMultiCompressListener() { // from class: com.sinovatech.unicom.hub.media.utils.MediaCompressUtils.1
                @Override // me.shaohui.advancedluban.OnMultiCompressListener
                public void onStart() {
                    MsLogUtil.m8000d("Luban压缩 onStart ");
                }

                @Override // me.shaohui.advancedluban.OnMultiCompressListener
                public void onSuccess(List<File> list2) {
                    for (int i3 = 0; i3 < list2.size(); i3++) {
                        try {
                            try {
                                File file = list2.get(i3);
                                int[] computeSize = MediaCompressUtils.computeSize(file);
                                MsLogUtil.m8000d("Luban压缩 onSuccess " + file.getAbsolutePath() + " " + String.format(Locale.CHINA, "Luban onSuccess压缩后参数：%d*%d, %dk", Integer.valueOf(computeSize[0]), Integer.valueOf(computeSize[1]), Long.valueOf(file.length() >> 10)));
                                ((MediaEntity) arrayList.get(i3)).setImagePath(file.getAbsolutePath());
                                ((MediaEntity) arrayList.get(i3)).setMimeType("image/jpeg");
                                ((MediaEntity) arrayList.get(i3)).setLength(file.length());
                            } catch (Exception e) {
                                e.printStackTrace();
                                MsLogUtil.m8000d("Luban压缩 onSuccess发生错误 " + e.getMessage());
                            }
                        } finally {
                            onCompressListener.onCompressComplete();
                        }
                    }
                }

                @Override // me.shaohui.advancedluban.OnMultiCompressListener
                public void onError(Throwable th) {
                    MsLogUtil.m8000d("Luban压缩 onError " + th.getMessage());
                    onCompressListener.onCompressComplete();
                }
            });
        } else {
            onCompressListener.onCompressComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int[] computeSize(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return new int[]{options.outWidth, options.outHeight};
    }
}
