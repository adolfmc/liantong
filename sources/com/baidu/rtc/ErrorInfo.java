package com.baidu.rtc;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ErrorInfo {
    ErrorType errorType;
    List<Long> feedIds;
    long handleId;
    String msg;
    int statusCode;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    enum ErrorType {
        ERROR_SUBSCRIBER_JOIN_ROOM,
        ERROR_USER_SUBSCRIBE,
        ERROR_USER_UNSUBSCRIBE
    }
}
