package com.huawei.hms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class HMSCursorWrapper extends CursorWrapper implements CrossProcessCursor {

    /* renamed from: a */
    private AbstractWindowedCursor f11179a;

    public HMSCursorWrapper(Cursor cursor) {
        super(cursor);
        if (cursor != null) {
            if (cursor instanceof CursorWrapper) {
                Cursor wrappedCursor = ((CursorWrapper) cursor).getWrappedCursor();
                if (wrappedCursor != null) {
                    if (wrappedCursor instanceof AbstractWindowedCursor) {
                        this.f11179a = (AbstractWindowedCursor) wrappedCursor;
                        return;
                    }
                    throw new IllegalArgumentException("getWrappedCursor:" + wrappedCursor + " is not a subclass for CursorWrapper");
                }
                throw new IllegalArgumentException("getWrappedCursor cannot be null");
            }
            throw new IllegalArgumentException("cursor:" + cursor + " is not a subclass for CursorWrapper");
        }
        throw new IllegalArgumentException("cursor cannot be null");
    }

    @Override // android.database.CrossProcessCursor
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.f11179a.fillWindow(i, cursorWindow);
    }

    @Override // android.database.CrossProcessCursor
    public CursorWindow getWindow() {
        return this.f11179a.getWindow();
    }

    @Override // android.database.CursorWrapper
    public Cursor getWrappedCursor() {
        return this.f11179a;
    }

    @Override // android.database.CrossProcessCursor
    public boolean onMove(int i, int i2) {
        return this.f11179a.onMove(i, i2);
    }

    public void setWindow(CursorWindow cursorWindow) {
        this.f11179a.setWindow(cursorWindow);
    }
}
