package com.blankj.utilcode.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CacheDiskStaticUtils {
    private static CacheDiskUtils sDefaultCacheDiskUtils;

    public static void setDefaultCacheDiskUtils(@Nullable CacheDiskUtils cacheDiskUtils) {
        sDefaultCacheDiskUtils = cacheDiskUtils;
    }

    public static void put(@NonNull String str, @Nullable byte[] bArr) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bArr, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable byte[] bArr, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bArr, i, getDefaultCacheDiskUtils());
    }

    public static byte[] getBytes(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getBytes(str, getDefaultCacheDiskUtils());
    }

    public static byte[] getBytes(@NonNull String str, @Nullable byte[] bArr) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getBytes(str, bArr, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, str2, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable String str2, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, str2, i, getDefaultCacheDiskUtils());
    }

    public static String getString(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getString(str, getDefaultCacheDiskUtils());
    }

    public static String getString(@NonNull String str, @Nullable String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getString(str, str2, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable JSONObject jSONObject) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, jSONObject, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable JSONObject jSONObject, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, jSONObject, i, getDefaultCacheDiskUtils());
    }

    public static JSONObject getJSONObject(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getJSONObject(str, getDefaultCacheDiskUtils());
    }

    public static JSONObject getJSONObject(@NonNull String str, @Nullable JSONObject jSONObject) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getJSONObject(str, jSONObject, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable JSONArray jSONArray) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, jSONArray, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable JSONArray jSONArray, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, jSONArray, i, getDefaultCacheDiskUtils());
    }

    public static JSONArray getJSONArray(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getJSONArray(str, getDefaultCacheDiskUtils());
    }

    public static JSONArray getJSONArray(@NonNull String str, @Nullable JSONArray jSONArray) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getJSONArray(str, jSONArray, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Bitmap bitmap) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bitmap, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Bitmap bitmap, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, bitmap, i, getDefaultCacheDiskUtils());
    }

    public static Bitmap getBitmap(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getBitmap(str, getDefaultCacheDiskUtils());
    }

    public static Bitmap getBitmap(@NonNull String str, @Nullable Bitmap bitmap) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getBitmap(str, bitmap, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Drawable drawable) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, drawable, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Drawable drawable, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, drawable, i, getDefaultCacheDiskUtils());
    }

    public static Drawable getDrawable(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getDrawable(str, getDefaultCacheDiskUtils());
    }

    public static Drawable getDrawable(@NonNull String str, @Nullable Drawable drawable) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getDrawable(str, drawable, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Parcelable parcelable) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, parcelable, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Parcelable parcelable, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, parcelable, i, getDefaultCacheDiskUtils());
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        if (str != null) {
            if (creator == null) {
                throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return (T) getParcelable(str, (Parcelable.Creator<Object>) creator, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @Nullable T t) {
        if (str != null) {
            if (creator == null) {
                throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return (T) getParcelable(str, creator, t, getDefaultCacheDiskUtils());
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(@NonNull String str, @Nullable Serializable serializable) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, serializable, getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable Serializable serializable, int i) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        put(str, serializable, i, getDefaultCacheDiskUtils());
    }

    public static Object getSerializable(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getSerializable(str, getDefaultCacheDiskUtils());
    }

    public static Object getSerializable(@NonNull String str, @Nullable Object obj) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return getSerializable(str, obj, getDefaultCacheDiskUtils());
    }

    public static long getCacheSize() {
        return getCacheSize(getDefaultCacheDiskUtils());
    }

    public static int getCacheCount() {
        return getCacheCount(getDefaultCacheDiskUtils());
    }

    public static boolean remove(@NonNull String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return remove(str, getDefaultCacheDiskUtils());
    }

    public static boolean clear() {
        return clear(getDefaultCacheDiskUtils());
    }

    public static void put(@NonNull String str, @Nullable byte[] bArr, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, bArr);
    }

    public static void put(@NonNull String str, @Nullable byte[] bArr, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, bArr, i);
    }

    public static byte[] getBytes(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getBytes(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static byte[] getBytes(@NonNull String str, @Nullable byte[] bArr, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getBytes(str, bArr);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(@NonNull String str, @Nullable String str2, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, str2);
    }

    public static void put(@NonNull String str, @Nullable String str2, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, str2, i);
    }

    public static String getString(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getString(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getString(@NonNull String str, @Nullable String str2, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getString(str, str2);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(@NonNull String str, @Nullable JSONObject jSONObject, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, jSONObject);
    }

    public static void put(@NonNull String str, @Nullable JSONObject jSONObject, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, jSONObject, i);
    }

    public static JSONObject getJSONObject(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getJSONObject(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONObject getJSONObject(@NonNull String str, @Nullable JSONObject jSONObject, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getJSONObject(str, jSONObject);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(@NonNull String str, @Nullable JSONArray jSONArray, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, jSONArray);
    }

    public static void put(@NonNull String str, @Nullable JSONArray jSONArray, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, jSONArray, i);
    }

    public static JSONArray getJSONArray(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getJSONArray(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static JSONArray getJSONArray(@NonNull String str, @Nullable JSONArray jSONArray, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getJSONArray(str, jSONArray);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(@NonNull String str, @Nullable Bitmap bitmap, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, bitmap);
    }

    public static void put(@NonNull String str, @Nullable Bitmap bitmap, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, bitmap, i);
    }

    public static Bitmap getBitmap(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getBitmap(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Bitmap getBitmap(@NonNull String str, @Nullable Bitmap bitmap, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getBitmap(str, bitmap);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(@NonNull String str, @Nullable Drawable drawable, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, drawable);
    }

    public static void put(@NonNull String str, @Nullable Drawable drawable, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, drawable, i);
    }

    public static Drawable getDrawable(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getDrawable(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getDrawable(@NonNull String str, @Nullable Drawable drawable, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getDrawable(str, drawable);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(@NonNull String str, @Nullable Parcelable parcelable, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, parcelable);
    }

    public static void put(@NonNull String str, @Nullable Parcelable parcelable, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, parcelable, i);
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (creator != null) {
                if (cacheDiskUtils == null) {
                    throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
                }
                return (T) cacheDiskUtils.getParcelable(str, creator);
            }
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static <T> T getParcelable(@NonNull String str, @NonNull Parcelable.Creator<T> creator, @Nullable T t, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (creator != null) {
                if (cacheDiskUtils == null) {
                    throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
                }
                return (T) cacheDiskUtils.getParcelable(str, creator, t);
            }
            throw new NullPointerException("Argument 'creator' of type Parcelable.Creator<T> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void put(@NonNull String str, @Nullable Serializable serializable, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, serializable);
    }

    public static void put(@NonNull String str, @Nullable Serializable serializable, int i, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str == null) {
            throw new NullPointerException("Argument 'key' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        cacheDiskUtils.put(str, serializable, i);
    }

    public static Object getSerializable(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getSerializable(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Object getSerializable(@NonNull String str, @Nullable Object obj, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.getSerializable(str, obj);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static long getCacheSize(@NonNull CacheDiskUtils cacheDiskUtils) {
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return cacheDiskUtils.getCacheSize();
    }

    public static int getCacheCount(@NonNull CacheDiskUtils cacheDiskUtils) {
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return cacheDiskUtils.getCacheCount();
    }

    public static boolean remove(@NonNull String str, @NonNull CacheDiskUtils cacheDiskUtils) {
        if (str != null) {
            if (cacheDiskUtils == null) {
                throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
            return cacheDiskUtils.remove(str);
        }
        throw new NullPointerException("Argument 'key' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean clear(@NonNull CacheDiskUtils cacheDiskUtils) {
        if (cacheDiskUtils == null) {
            throw new NullPointerException("Argument 'cacheDiskUtils' of type CacheDiskUtils (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return cacheDiskUtils.clear();
    }

    @NonNull
    private static CacheDiskUtils getDefaultCacheDiskUtils() {
        CacheDiskUtils cacheDiskUtils = sDefaultCacheDiskUtils;
        if (cacheDiskUtils == null) {
            cacheDiskUtils = CacheDiskUtils.getInstance();
        }
        if (cacheDiskUtils != null) {
            return cacheDiskUtils;
        }
        throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.CacheDiskStaticUtils.getDefaultCacheDiskUtils() marked by @android.support.annotation.NonNull");
    }
}
