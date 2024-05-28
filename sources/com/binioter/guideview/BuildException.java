package com.binioter.guideview;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class BuildException extends RuntimeException {
    private static final long serialVersionUID = 6208777692136933357L;
    private final String mDetailMessage;

    public BuildException() {
        this.mDetailMessage = "General error.";
    }

    public BuildException(String str) {
        this.mDetailMessage = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Build GuideFragment failed: " + this.mDetailMessage;
    }
}
