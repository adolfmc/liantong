package android.support.p083v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.widget.ResourceCursorAdapter */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ResourceCursorAdapter extends CursorAdapter {
    private int mDropDownLayout;
    private LayoutInflater mInflater;
    private int mLayout;

    @Deprecated
    public ResourceCursorAdapter(Context context, int i, Cursor cursor) {
        super(context, cursor);
        this.mDropDownLayout = i;
        this.mLayout = i;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Deprecated
    public ResourceCursorAdapter(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.mDropDownLayout = i;
        this.mLayout = i;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public ResourceCursorAdapter(Context context, int i, Cursor cursor, int i2) {
        super(context, cursor, i2);
        this.mDropDownLayout = i;
        this.mLayout = i;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // android.support.p083v4.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mLayout, viewGroup, false);
    }

    @Override // android.support.p083v4.widget.CursorAdapter
    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.mInflater.inflate(this.mDropDownLayout, viewGroup, false);
    }

    public void setViewResource(int i) {
        this.mLayout = i;
    }

    public void setDropDownViewResource(int i) {
        this.mDropDownLayout = i;
    }
}
