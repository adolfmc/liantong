package com.baidu.p120ar.http;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.HttpUtil */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class HttpUtil {
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String UTF8_BOM = "\ufeff";

    HttpUtil() {
    }

    public static String[] parseHttpHeader(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf(58)) <= 0 || indexOf == str.length() - 1) {
            return null;
        }
        return new String[]{str.substring(0, indexOf), str.substring(indexOf + 1).trim()};
    }

    public static String encodeUrlComponent(String str, Charset charset) {
        String name;
        if (charset != null) {
            try {
                name = charset.name();
            } catch (UnsupportedEncodingException unused) {
                return str;
            }
        } else {
            name = null;
        }
        return URLEncoder.encode(str, name);
    }

    public static String getFileMimeType(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str);
        return TextUtils.isEmpty(contentTypeFor) ? "application/octet-stream" : contentTypeFor;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String clearUtf8BOM(String str) {
        return (str == null || !str.startsWith("\ufeff")) ? str : str.substring(1);
    }
}
