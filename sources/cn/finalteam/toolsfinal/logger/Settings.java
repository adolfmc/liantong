package cn.finalteam.toolsfinal.logger;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class Settings {
    private LogTool logTool;
    private int methodCount = 2;
    private boolean showThreadInfo = true;
    private int methodOffset = 0;
    private LogLevel logLevel = LogLevel.FULL;

    public Settings hideThreadInfo() {
        this.showThreadInfo = false;
        return this;
    }

    @Deprecated
    public Settings setMethodCount(int i) {
        return methodCount(i);
    }

    public Settings methodCount(int i) {
        if (i < 0) {
            i = 0;
        }
        this.methodCount = i;
        return this;
    }

    @Deprecated
    public Settings setLogLevel(LogLevel logLevel) {
        return logLevel(logLevel);
    }

    public Settings logLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
        return this;
    }

    @Deprecated
    public Settings setMethodOffset(int i) {
        return methodOffset(i);
    }

    public Settings methodOffset(int i) {
        this.methodOffset = i;
        return this;
    }

    public Settings logTool(LogTool logTool) {
        this.logTool = logTool;
        return this;
    }

    public int getMethodCount() {
        return this.methodCount;
    }

    public boolean isShowThreadInfo() {
        return this.showThreadInfo;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public int getMethodOffset() {
        return this.methodOffset;
    }

    public LogTool getLogTool() {
        if (this.logTool == null) {
            this.logTool = new AndroidLogTool();
        }
        return this.logTool;
    }
}
