package com.baidu.p120ar.http;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.HttpDefaultConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
class HttpDefaultConfig {
    public static final int CONNECTION_TIMEOUT = 2000;
    public static final int READ_TIMEOUT = 30000;
    public static final int REQUEST_QUEUE_CAPACITY = 1000;
    public static final int THREAD_CORE_POOL_SIZE = 2;
    public static final long THREAD_KEEP_ALIVE_SECONDS = 60;
    public static final int THREAD_MAX_POOL_SIZE = 5;
    public static final boolean USE_CACHE = false;
    public static final Charset CHARSET = StandardCharsets.UTF_8;
    public static String MULTIPART_BOUNDARY = "------baiduARFormBoundary--";
    public static String[] COMMON_HEADERS = {"accept:*/*", "connection:Keep-Alive"};

    HttpDefaultConfig() {
    }
}
