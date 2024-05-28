package com.sinovatech.unicom.hub.media.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.ExifInterface;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.sinovatech.unicom.hub.media.gallery.BucketEntity;
import com.sinovatech.unicom.hub.media.gallery.MediaEntity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MediaStoreUtils {
    public static final String CacheDir_ImageThumbnail = "imagethumbnail";
    public static final String CacheDir_VideoThumbnail = "videothumbnail";
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    public static List<BucketEntity> getMediaStore(Context context, String str) {
        List<MediaEntity> list;
        ArrayList arrayList = new ArrayList();
        try {
            if ("image".equals(str)) {
                list = getMediaStoreImage(context);
            } else if ("video".equals(str)) {
                list = getMediaStoreVideo(context);
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(getMediaStoreImage(context));
                arrayList2.addAll(getMediaStoreVideo(context));
                Collections.sort(arrayList2);
                list = arrayList2;
            }
            if (list.size() > 0) {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (int i = 0; i < list.size(); i++) {
                    String bucketId = list.get(i).getBucketId();
                    String bucketName = list.get(i).getBucketName();
                    if (linkedHashMap.get(bucketId) == null) {
                        BucketEntity bucketEntity = new BucketEntity();
                        bucketEntity.setBucketId(bucketId);
                        bucketEntity.setBucketName(bucketName);
                        bucketEntity.setMediaList(new ArrayList());
                        linkedHashMap.put(bucketId, bucketEntity);
                    } else {
                        ((BucketEntity) linkedHashMap.get(bucketId)).getMediaList().add(list.get(i));
                    }
                }
                BucketEntity bucketEntity2 = new BucketEntity();
                bucketEntity2.setBucketId("ALL");
                bucketEntity2.setBucketName("全部");
                bucketEntity2.setMediaList(list);
                arrayList.add(bucketEntity2);
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    BucketEntity bucketEntity3 = (BucketEntity) entry.getValue();
                    if (bucketEntity3.getMediaList().size() > 0) {
                        arrayList.add(bucketEntity3);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static List<MediaEntity> getMediaStoreImage(Context context) {
        Cursor query;
        ArrayList arrayList = new ArrayList();
        try {
            ContentResolver contentResolver = context.getContentResolver();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("_id");
            arrayList2.add("title");
            arrayList2.add("_data");
            arrayList2.add("bucket_id");
            arrayList2.add("bucket_display_name");
            arrayList2.add("mime_type");
            arrayList2.add("date_added");
            arrayList2.add("date_modified");
            arrayList2.add("latitude");
            arrayList2.add("longitude");
            arrayList2.add("orientation");
            arrayList2.add("_size");
            if (Build.VERSION.SDK_INT >= 16) {
                arrayList2.add("width");
                arrayList2.add("height");
            }
            query = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (String[]) arrayList2.toArray(new String[arrayList2.size()]), null, null, "date_added DESC");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (query != null && query.getCount() > 0) {
            query.moveToFirst();
            do {
                MediaEntity parseImageCursor = parseImageCursor(context, query);
                if (parseImageCursor != null) {
                    arrayList.add(parseImageCursor);
                }
            } while (query.moveToNext());
            if (query != null) {
                query.close();
            }
            return arrayList;
        }
        if (query != null && !query.isClosed()) {
            query.close();
        }
        return arrayList;
    }

    private static MediaEntity parseImageCursor(Context context, Cursor cursor) {
        int i;
        long j = cursor.getLong(cursor.getColumnIndex("_size"));
        String string = cursor.getString(cursor.getColumnIndex("_data"));
        if (TextUtils.isEmpty(string) || j <= 0 || !new File(string).exists()) {
            return null;
        }
        long j2 = cursor.getLong(cursor.getColumnIndex("_id"));
        String string2 = cursor.getString(cursor.getColumnIndex("title"));
        String string3 = cursor.getString(cursor.getColumnIndex("bucket_id"));
        String string4 = cursor.getString(cursor.getColumnIndex("bucket_display_name"));
        String string5 = cursor.getString(cursor.getColumnIndex("mime_type"));
        long j3 = cursor.getLong(cursor.getColumnIndex("date_added"));
        long j4 = cursor.getLong(cursor.getColumnIndex("date_modified"));
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setMediaId(j2);
        mediaEntity.setTitle(string2);
        mediaEntity.setOriginalPath(string);
        mediaEntity.setBucketId(string3);
        mediaEntity.setBucketName(string4);
        mediaEntity.setMimeType(string5);
        mediaEntity.setCreateDate(j3);
        mediaEntity.setModifiedDate(j4);
        int i2 = 0;
        if (Build.VERSION.SDK_INT >= 16) {
            int i3 = cursor.getInt(cursor.getColumnIndex("width"));
            i2 = cursor.getInt(cursor.getColumnIndex("height"));
            i = i3;
        } else {
            try {
                ExifInterface exifInterface = new ExifInterface(string);
                i = exifInterface.getAttributeInt("ImageWidth", 0);
                try {
                    i2 = exifInterface.getAttributeInt("ImageLength", 0);
                } catch (IOException e) {
                    e = e;
                    e.printStackTrace();
                    mediaEntity.setWidth(i);
                    mediaEntity.setHeight(i2);
                    mediaEntity.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
                    mediaEntity.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
                    mediaEntity.setOrientation(cursor.getInt(cursor.getColumnIndex("orientation")));
                    mediaEntity.setLength(cursor.getLong(cursor.getColumnIndex("_size")));
                    mediaEntity.setImagePath(string);
                    mediaEntity.setMediaType("image");
                    mediaEntity.setVideoThumbnail("");
                    return mediaEntity;
                }
            } catch (IOException e2) {
                e = e2;
                i = 0;
            }
        }
        mediaEntity.setWidth(i);
        mediaEntity.setHeight(i2);
        mediaEntity.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
        mediaEntity.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
        mediaEntity.setOrientation(cursor.getInt(cursor.getColumnIndex("orientation")));
        mediaEntity.setLength(cursor.getLong(cursor.getColumnIndex("_size")));
        mediaEntity.setImagePath(string);
        mediaEntity.setMediaType("image");
        mediaEntity.setVideoThumbnail("");
        return mediaEntity;
    }

    private static List<MediaEntity> getMediaStoreVideo(Context context) {
        ArrayList arrayList = new ArrayList();
        ContentResolver contentResolver = context.getContentResolver();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("_id");
        arrayList2.add("title");
        arrayList2.add("_data");
        arrayList2.add("bucket_id");
        arrayList2.add("bucket_display_name");
        arrayList2.add("mime_type");
        arrayList2.add("date_added");
        arrayList2.add("date_modified");
        arrayList2.add("latitude");
        arrayList2.add("longitude");
        arrayList2.add("_size");
        if (Build.VERSION.SDK_INT >= 16) {
            arrayList2.add("width");
            arrayList2.add("height");
        }
        arrayList2.add("duration");
        Cursor query = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, (String[]) arrayList2.toArray(new String[arrayList2.size()]), null, null, "date_added DESC");
        if (query != null && query.getCount() > 0) {
            query.moveToFirst();
            do {
                MediaEntity parseVideoCursor = parseVideoCursor(context, query);
                if (parseVideoCursor != null) {
                    arrayList.add(parseVideoCursor);
                }
            } while (query.moveToNext());
            if (query != null) {
                query.close();
            }
            return arrayList;
        }
        if (query != null && !query.isClosed()) {
            query.close();
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.sinovatech.unicom.hub.media.gallery.MediaEntity parseVideoCursor(android.content.Context r23, android.database.Cursor r24) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.hub.media.utils.MediaStoreUtils.parseVideoCursor(android.content.Context, android.database.Cursor):com.sinovatech.unicom.hub.media.gallery.MediaEntity");
    }

    private static String parseFileName(String str) {
        return str == null ? "null" : str.substring(Math.max(str.lastIndexOf(47), str.lastIndexOf(92)) + 1);
    }

    public static File getAppMediaHubCache(Context context, String str) {
        File file = new File(context.getFilesDir().getAbsolutePath() + File.separator + "unicom_hub_mediastore" + File.separator + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void clearThumbnailCache(Context context, String str) {
        try {
            deleteDir(getAppMediaHubCache(context, str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteDir(File file) throws Exception {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (String str : file.list()) {
                    deleteDir(new File(file, str));
                }
            }
            file.delete();
        }
    }
}
