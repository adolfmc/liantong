package com.liulishuo.okdownload.core.breakpoint;

import android.database.Cursor;
import java.io.File;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BreakpointInfoRow {
    private final boolean chunked;
    private final String etag;
    private final String filename;

    /* renamed from: id */
    private final int f12215id;
    private final String parentPath;
    private final boolean taskOnlyProvidedParentPath;
    private final String url;

    public BreakpointInfoRow(Cursor cursor) {
        this.f12215id = cursor.getInt(cursor.getColumnIndex("id"));
        this.url = cursor.getString(cursor.getColumnIndex("url"));
        this.etag = cursor.getString(cursor.getColumnIndex("etag"));
        this.parentPath = cursor.getString(cursor.getColumnIndex("parent_path"));
        this.filename = cursor.getString(cursor.getColumnIndex("filename"));
        this.taskOnlyProvidedParentPath = cursor.getInt(cursor.getColumnIndex("task_only_parent_path")) == 1;
        this.chunked = cursor.getInt(cursor.getColumnIndex("chunked")) == 1;
    }

    public int getId() {
        return this.f12215id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getEtag() {
        return this.etag;
    }

    public String getParentPath() {
        return this.parentPath;
    }

    public String getFilename() {
        return this.filename;
    }

    public boolean isTaskOnlyProvidedParentPath() {
        return this.taskOnlyProvidedParentPath;
    }

    public boolean isChunked() {
        return this.chunked;
    }

    public BreakpointInfo toInfo() {
        BreakpointInfo breakpointInfo = new BreakpointInfo(this.f12215id, this.url, new File(this.parentPath), this.filename, this.taskOnlyProvidedParentPath);
        breakpointInfo.setEtag(this.etag);
        breakpointInfo.setChunked(this.chunked);
        return breakpointInfo;
    }
}
