package com.alibaba.android.arouter.utils;

import android.util.Log;
import com.alibaba.android.arouter.facade.template.ILogger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DefaultLogger implements ILogger {
    private static boolean isMonitorMode;
    private static boolean isShowLog;
    private static boolean isShowStackTrace;
    private String defaultTag;

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void showLog(boolean z) {
        isShowLog = z;
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void showStackTrace(boolean z) {
        isShowStackTrace = z;
    }

    public void showMonitor(boolean z) {
        isMonitorMode = z;
    }

    public DefaultLogger() {
        this.defaultTag = "ARouter";
    }

    public DefaultLogger(String str) {
        this.defaultTag = "ARouter";
        this.defaultTag = str;
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void debug(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.d(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void info(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.i(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void warning(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.w(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void error(String str, String str2) {
        if (isShowLog) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (android.text.TextUtils.isEmpty(str)) {
                str = getDefaultTag();
            }
            Log.e(str, str2 + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public void monitor(String str) {
        if (isShowLog && isMonitorMode()) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.d(this.defaultTag + "::monitor", str + getExtInfo(stackTraceElement));
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public boolean isMonitorMode() {
        return isMonitorMode;
    }

    @Override // com.alibaba.android.arouter.facade.template.ILogger
    public String getDefaultTag() {
        return this.defaultTag;
    }

    public static String getExtInfo(StackTraceElement stackTraceElement) {
        StringBuilder sb = new StringBuilder("[");
        if (isShowStackTrace) {
            String name = Thread.currentThread().getName();
            String fileName = stackTraceElement.getFileName();
            String className = stackTraceElement.getClassName();
            String methodName = stackTraceElement.getMethodName();
            long id = Thread.currentThread().getId();
            int lineNumber = stackTraceElement.getLineNumber();
            sb.append("ThreadId=");
            sb.append(id);
            sb.append(" & ");
            sb.append("ThreadName=");
            sb.append(name);
            sb.append(" & ");
            sb.append("FileName=");
            sb.append(fileName);
            sb.append(" & ");
            sb.append("ClassName=");
            sb.append(className);
            sb.append(" & ");
            sb.append("MethodName=");
            sb.append(methodName);
            sb.append(" & ");
            sb.append("LineNumber=");
            sb.append(lineNumber);
        }
        sb.append(" ] ");
        return sb.toString();
    }
}
