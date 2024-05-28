package com.huawei.hms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.common.internal.safeparcel.AbstractSafeParcelable;
import com.huawei.hms.common.internal.safeparcel.SafeParcelWriter;
import com.huawei.hms.common.sqlite.HMSCursorWrapper;
import com.huawei.hms.support.log.HMSLog;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    private static final String TAG = "DataHolder";
    public static final String TYPE_BOOLEAN = "type_boolean";
    public static final String TYPE_BYTE_ARRAY = "type_byte_array";
    public static final String TYPE_DOUBLE = "type_double";
    public static final String TYPE_FLOAT = "type_float";
    public static final String TYPE_INT = "type_int";
    public static final String TYPE_LONG = "type_long";
    public static final String TYPE_STRING = "type_string";
    private String[] columns;
    private Bundle columnsBundle;
    private CursorWindow[] cursorWindows;
    private int dataCount;
    private boolean isInstance;
    private boolean mClosed;
    private Bundle metadata;
    private int[] perCursorCounts;
    private int statusCode;
    private int version;
    public static final Parcelable.Creator<DataHolder> CREATOR = new DataHolderCreator();
    private static final Builder BUILDER = new DataHolderBuilderCreator(new String[0], null);

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Builder {

        /* renamed from: a */
        private String[] f11108a;

        /* renamed from: b */
        private final ArrayList<HashMap<String, Object>> f11109b;

        /* renamed from: c */
        private final String f11110c;

        /* renamed from: d */
        private final HashMap<Object, Integer> f11111d;

        public DataHolder build(int i) {
            return new DataHolder(this, i, (Bundle) null);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
        /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.huawei.hms.common.data.DataHolder.Builder setDataForContentValuesHashMap(java.util.HashMap<java.lang.String, java.lang.Object> r5) {
            /*
                r4 = this;
                java.lang.String r0 = "contentValuesHashMap cannot be null"
                com.huawei.hms.common.internal.Preconditions.checkNotNull(r5, r0)
                java.lang.String r0 = r4.f11110c
                r1 = 0
                if (r0 == 0) goto L2f
                java.lang.Object r0 = r5.get(r0)
                if (r0 == 0) goto L2f
                java.util.HashMap<java.lang.Object, java.lang.Integer> r2 = r4.f11111d
                java.lang.Object r2 = r2.get(r0)
                java.lang.Integer r2 = (java.lang.Integer) r2
                if (r2 == 0) goto L20
                int r1 = r2.intValue()
                r0 = 1
                goto L30
            L20:
                java.util.HashMap<java.lang.Object, java.lang.Integer> r2 = r4.f11111d
                java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> r3 = r4.f11109b
                int r3 = r3.size()
                java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
                r2.put(r0, r3)
            L2f:
                r0 = r1
            L30:
                if (r0 == 0) goto L3d
                java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> r0 = r4.f11109b
                r0.remove(r1)
                java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> r0 = r4.f11109b
                r0.add(r1, r5)
                goto L42
            L3d:
                java.util.ArrayList<java.util.HashMap<java.lang.String, java.lang.Object>> r0 = r4.f11109b
                r0.add(r5)
            L42:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.data.DataHolder.Builder.setDataForContentValuesHashMap(java.util.HashMap):com.huawei.hms.common.data.DataHolder$Builder");
        }

        public Builder withRow(ContentValues contentValues) {
            Preconditions.checkNotNull(contentValues, "contentValues cannot be null");
            HashMap<String, Object> hashMap = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return setDataForContentValuesHashMap(hashMap);
        }

        private Builder(String[] strArr, String str) {
            Preconditions.checkNotNull(strArr, "builderColumnsP cannot be null");
            this.f11108a = strArr;
            this.f11109b = new ArrayList<>();
            this.f11110c = str;
            this.f11111d = new HashMap<>();
        }

        public DataHolder build(int i, Bundle bundle) {
            return new DataHolder(this, i, bundle, -1);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(String[] strArr, String str, DataHolderBuilderCreator dataHolderBuilderCreator) {
            this(strArr, null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class DataHolderException extends RuntimeException {
        public DataHolderException(String str) {
            super(str);
        }
    }

    public static Builder builder(String[] strArr) {
        return new Builder(strArr, (String) null);
    }

    public static DataHolder empty(int i) {
        return new DataHolder(BUILDER, i, (Bundle) null);
    }

    private static CursorWindow[] getCursorWindows(HMSCursorWrapper hMSCursorWrapper) {
        int i;
        ArrayList arrayList = new ArrayList();
        try {
            int count = hMSCursorWrapper.getCount();
            CursorWindow window = hMSCursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                i = 0;
            } else {
                window.acquireReference();
                hMSCursorWrapper.setWindow(null);
                arrayList.add(window);
                i = window.getNumRows();
            }
            arrayList.addAll(iterCursorWrapper(hMSCursorWrapper, i, count));
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            try {
                HMSLog.m14112e(TAG, "fail to getCursorWindows: " + th.getMessage());
                return new CursorWindow[0];
            } finally {
                hMSCursorWrapper.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b6, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.ArrayList<android.database.CursorWindow> iterCursorWindow(com.huawei.hms.common.data.DataHolder.Builder r9, int r10, java.util.List r11) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.database.CursorWindow r1 = new android.database.CursorWindow
            r2 = 0
            r1.<init>(r2)
            java.lang.String[] r3 = com.huawei.hms.common.data.DataHolder.Builder.m15131a(r9)
            int r3 = r3.length
            r1.setNumColumns(r3)
            r0.add(r1)
            r3 = 0
            r4 = r1
            r1 = r3
        L19:
            if (r1 >= r10) goto Lb6
            boolean r5 = r4.allocRow()     // Catch: java.lang.RuntimeException -> La0
            if (r5 != 0) goto L49
            java.lang.String r4 = "DataHolder"
            java.lang.String r5 = "Failed to allocate a row"
            com.huawei.hms.support.log.HMSLog.m14115d(r4, r5)     // Catch: java.lang.RuntimeException -> La0
            android.database.CursorWindow r4 = new android.database.CursorWindow     // Catch: java.lang.RuntimeException -> La0
            r4.<init>(r2)     // Catch: java.lang.RuntimeException -> La0
            r4.setStartPosition(r1)     // Catch: java.lang.RuntimeException -> La0
            java.lang.String[] r5 = com.huawei.hms.common.data.DataHolder.Builder.m15131a(r9)     // Catch: java.lang.RuntimeException -> La0
            int r5 = r5.length     // Catch: java.lang.RuntimeException -> La0
            r4.setNumColumns(r5)     // Catch: java.lang.RuntimeException -> La0
            boolean r5 = r4.allocRow()     // Catch: java.lang.RuntimeException -> La0
            if (r5 != 0) goto L46
            java.lang.String r9 = "DataHolder"
            java.lang.String r10 = "Failed to retry to allocate a row"
            com.huawei.hms.support.log.HMSLog.m14112e(r9, r10)     // Catch: java.lang.RuntimeException -> La0
            return r0
        L46:
            r0.add(r4)     // Catch: java.lang.RuntimeException -> La0
        L49:
            java.lang.Object r5 = r11.get(r1)     // Catch: java.lang.RuntimeException -> La0
            java.util.HashMap r5 = (java.util.HashMap) r5     // Catch: java.lang.RuntimeException -> La0
            r6 = 1
            r7 = r6
            r6 = r3
        L52:
            java.lang.String[] r8 = com.huawei.hms.common.data.DataHolder.Builder.m15131a(r9)     // Catch: java.lang.RuntimeException -> La0
            int r8 = r8.length     // Catch: java.lang.RuntimeException -> La0
            if (r6 >= r8) goto L6d
            java.lang.String[] r7 = com.huawei.hms.common.data.DataHolder.Builder.m15131a(r9)     // Catch: java.lang.RuntimeException -> La0
            r7 = r7[r6]     // Catch: java.lang.RuntimeException -> La0
            java.lang.Object r7 = r5.get(r7)     // Catch: java.lang.RuntimeException -> La0
            boolean r7 = putValue(r4, r7, r1, r6)     // Catch: java.lang.RuntimeException -> La0
            if (r7 != 0) goto L6a
            goto L6d
        L6a:
            int r6 = r6 + 1
            goto L52
        L6d:
            if (r7 != 0) goto L9c
            java.lang.String r10 = "DataHolder"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch: java.lang.RuntimeException -> La0
            r11.<init>()     // Catch: java.lang.RuntimeException -> La0
            java.lang.String r3 = "fail to put data for row "
            r11.append(r3)     // Catch: java.lang.RuntimeException -> La0
            r11.append(r1)     // Catch: java.lang.RuntimeException -> La0
            java.lang.String r11 = r11.toString()     // Catch: java.lang.RuntimeException -> La0
            com.huawei.hms.support.log.HMSLog.m14115d(r10, r11)     // Catch: java.lang.RuntimeException -> La0
            r4.freeLastRow()     // Catch: java.lang.RuntimeException -> La0
            android.database.CursorWindow r10 = new android.database.CursorWindow     // Catch: java.lang.RuntimeException -> La0
            r10.<init>(r2)     // Catch: java.lang.RuntimeException -> La0
            r10.setStartPosition(r1)     // Catch: java.lang.RuntimeException -> La0
            java.lang.String[] r9 = com.huawei.hms.common.data.DataHolder.Builder.m15131a(r9)     // Catch: java.lang.RuntimeException -> La0
            int r9 = r9.length     // Catch: java.lang.RuntimeException -> La0
            r10.setNumColumns(r9)     // Catch: java.lang.RuntimeException -> La0
            r0.add(r10)     // Catch: java.lang.RuntimeException -> La0
            goto Lb6
        L9c:
            int r1 = r1 + 1
            goto L19
        La0:
            r9 = move-exception
            java.util.Iterator r10 = r0.iterator()
        La5:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto Lb5
            java.lang.Object r11 = r10.next()
            android.database.CursorWindow r11 = (android.database.CursorWindow) r11
            r11.close()
            goto La5
        Lb5:
            throw r9
        Lb6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.data.DataHolder.iterCursorWindow(com.huawei.hms.common.data.DataHolder$Builder, int, java.util.List):java.util.ArrayList");
    }

    private static ArrayList<CursorWindow> iterCursorWrapper(HMSCursorWrapper hMSCursorWrapper, int i, int i2) {
        ArrayList<CursorWindow> arrayList = new ArrayList<>();
        while (i < i2 && hMSCursorWrapper.moveToPosition(i)) {
            CursorWindow window = hMSCursorWrapper.getWindow();
            if (window == null) {
                window = new CursorWindow((String) null);
                window.setStartPosition(i);
                hMSCursorWrapper.fillWindow(i, window);
            } else {
                window.acquireReference();
                hMSCursorWrapper.setWindow(null);
            }
            if (window.getNumRows() == 0) {
                break;
            }
            arrayList.add(window);
            i = window.getNumRows() + window.getStartPosition();
        }
        return arrayList;
    }

    private static boolean putValue(CursorWindow cursorWindow, Object obj, int i, int i2) throws IllegalArgumentException {
        if (obj == null) {
            return cursorWindow.putNull(i, i2);
        }
        if (obj instanceof Boolean) {
            return cursorWindow.putLong(((Boolean) obj).booleanValue() ? 1L : 0L, i, i2);
        } else if (obj instanceof Integer) {
            return cursorWindow.putLong(((Integer) obj).intValue(), i, i2);
        } else {
            if (obj instanceof Long) {
                return cursorWindow.putLong(((Long) obj).longValue(), i, i2);
            }
            if (obj instanceof Float) {
                return cursorWindow.putDouble(((Float) obj).floatValue(), i, i2);
            }
            if (obj instanceof Double) {
                return cursorWindow.putDouble(((Double) obj).doubleValue(), i, i2);
            }
            if (obj instanceof String) {
                return cursorWindow.putString((String) obj, i, i2);
            }
            if (obj instanceof byte[]) {
                return cursorWindow.putBlob((byte[]) obj, i, i2);
            }
            throw new IllegalArgumentException("unsupported type for column: " + obj);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (!this.mClosed) {
            for (CursorWindow cursorWindow : this.cursorWindows) {
                cursorWindow.close();
            }
            this.mClosed = true;
        }
    }

    public final void collectColumsAndCount() {
        this.columnsBundle = new Bundle();
        String[] strArr = this.columns;
        int i = 0;
        if (strArr != null && strArr.length != 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.columns;
                if (i2 >= strArr2.length) {
                    break;
                }
                this.columnsBundle.putInt(strArr2[i2], i2);
                i2++;
            }
            CursorWindow[] cursorWindowArr = this.cursorWindows;
            if (cursorWindowArr != null && cursorWindowArr.length != 0) {
                this.perCursorCounts = new int[cursorWindowArr.length];
                int i3 = 0;
                while (true) {
                    CursorWindow[] cursorWindowArr2 = this.cursorWindows;
                    if (i < cursorWindowArr2.length) {
                        this.perCursorCounts[i] = i3;
                        i3 = cursorWindowArr2[i].getStartPosition() + this.cursorWindows[i].getNumRows();
                        i++;
                    } else {
                        this.dataCount = i3;
                        return;
                    }
                }
            } else {
                this.dataCount = 0;
            }
        } else {
            this.dataCount = 0;
        }
    }

    public final void copyToBuffer(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        checkAvailable(str, i);
        this.cursorWindows[i2].copyStringToBuffer(i, this.columnsBundle.getInt(str), charArrayBuffer);
    }

    protected final void finalize() throws Throwable {
        if (this.isInstance && this.cursorWindows.length > 0 && !isClosed()) {
            close();
        }
        super.finalize();
    }

    public final int getCount() {
        return this.dataCount;
    }

    public final Bundle getMetadata() {
        return this.metadata;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final Object getValue(String str, int i, int i2, String str2) {
        char c;
        str2.hashCode();
        switch (str2.hashCode()) {
            case -1092271849:
                if (str2.equals(TYPE_FLOAT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -870070237:
                if (str2.equals(TYPE_BOOLEAN)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -675993238:
                if (str2.equals(TYPE_INT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 445002870:
                if (str2.equals(TYPE_DOUBLE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 519136353:
                if (str2.equals(TYPE_LONG)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 878975158:
                if (str2.equals(TYPE_STRING)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1300508295:
                if (str2.equals(TYPE_BYTE_ARRAY)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                checkAvailable(str, i);
                return Float.valueOf(this.cursorWindows[i2].getFloat(i, this.columnsBundle.getInt(str)));
            case 1:
                checkAvailable(str, i);
                return Boolean.valueOf(this.cursorWindows[i2].getLong(i, this.columnsBundle.getInt(str)) == 1);
            case 2:
                checkAvailable(str, i);
                return Integer.valueOf(this.cursorWindows[i2].getInt(i, this.columnsBundle.getInt(str)));
            case 3:
                checkAvailable(str, i);
                return Double.valueOf(this.cursorWindows[i2].getDouble(i, this.columnsBundle.getInt(str)));
            case 4:
                checkAvailable(str, i);
                return Long.valueOf(this.cursorWindows[i2].getLong(i, this.columnsBundle.getInt(str)));
            case 5:
                checkAvailable(str, i);
                return this.cursorWindows[i2].getString(i, this.columnsBundle.getInt(str));
            case 6:
                checkAvailable(str, i);
                return this.cursorWindows[i2].getBlob(i, this.columnsBundle.getInt(str));
            default:
                return null;
        }
    }

    public final int getWindowIndex(int i) {
        int[] iArr;
        int i2 = 0;
        Preconditions.checkArgument(i >= 0 || i < this.dataCount, "rowIndex is out of index:" + i);
        while (true) {
            iArr = this.perCursorCounts;
            if (i2 >= iArr.length) {
                break;
            } else if (i < iArr[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == iArr.length ? i2 - 1 : i2;
    }

    public final boolean hasColumn(String str) {
        return this.columnsBundle.containsKey(str);
    }

    public final boolean hasNull(String str, int i, int i2) {
        checkAvailable(str, i);
        return this.cursorWindows[i2].getType(i, this.columnsBundle.getInt(str)) == 0;
    }

    public final synchronized boolean isClosed() {
        return this.mClosed;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.columns, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.cursorWindows, i, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.version);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }

    private void checkAvailable(String str, int i) {
        String str2 = "";
        Bundle bundle = this.columnsBundle;
        if (bundle != null && bundle.containsKey(str)) {
            if (isClosed()) {
                str2 = "buffer has been closed";
            } else if (i < 0 || i >= this.dataCount) {
                str2 = "row is out of index:" + i;
            }
        } else {
            str2 = "cannot find column: " + str;
        }
        Preconditions.checkArgument(str2.isEmpty(), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.mClosed = false;
        this.isInstance = true;
        this.version = i;
        this.columns = strArr;
        this.cursorWindows = cursorWindowArr;
        this.statusCode = i2;
        this.metadata = bundle;
        collectColumsAndCount();
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        Preconditions.checkNotNull(strArr, "columnsP cannot be null");
        Preconditions.checkNotNull(cursorWindowArr, "cursorWindowP cannot be null");
        this.mClosed = false;
        this.isInstance = true;
        this.version = 1;
        this.columns = strArr;
        this.cursorWindows = cursorWindowArr;
        this.statusCode = i;
        this.metadata = bundle;
        collectColumsAndCount();
    }

    private DataHolder(HMSCursorWrapper hMSCursorWrapper, int i, Bundle bundle) {
        this(hMSCursorWrapper.getColumnNames(), getCursorWindows(hMSCursorWrapper), i, bundle);
    }

    public DataHolder(Cursor cursor, int i, Bundle bundle) {
        this(new HMSCursorWrapper(cursor), i, bundle);
    }

    private DataHolder(Builder builder, int i, Bundle bundle) {
        this(builder.f11108a, getCursorWindows(builder, -1), i, (Bundle) null);
    }

    private DataHolder(Builder builder, int i, Bundle bundle, int i2) {
        this(builder.f11108a, getCursorWindows(builder, -1), i, bundle);
    }

    private static CursorWindow[] getCursorWindows(Builder builder, int i) {
        if (builder.f11108a.length != 0) {
            if (i < 0 || i >= builder.f11109b.size()) {
                i = builder.f11109b.size();
            }
            ArrayList<CursorWindow> iterCursorWindow = iterCursorWindow(builder, i, builder.f11109b.subList(0, i));
            return (CursorWindow[]) iterCursorWindow.toArray(new CursorWindow[iterCursorWindow.size()]);
        }
        return new CursorWindow[0];
    }
}
