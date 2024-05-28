package com.tencent.p348mm.opensdk.openapi;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import com.tencent.p348mm.opensdk.channel.p350a.C10372a;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10385a;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.openapi.MMSharedPreferences */
/* loaded from: E:\11617560_dexfile_execute.dex */
class MMSharedPreferences implements SharedPreferences {
    private static final String TAG = "MicroMsg.SDK.SharedPreferences";

    /* renamed from: cr */
    private final ContentResolver f19985cr;
    private final String[] columns = {"_id", "key", "type", "value"};
    private final HashMap<String, Object> values = new HashMap<>();
    private REditor editor = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.openapi.MMSharedPreferences$REditor */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class REditor implements SharedPreferences.Editor {

        /* renamed from: cr */
        private ContentResolver f19986cr;
        private Map<String, Object> values = new HashMap();
        private Set<String> remove = new HashSet();
        private boolean clear = false;

        public REditor(ContentResolver contentResolver) {
            this.f19986cr = contentResolver;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.clear = true;
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00a1  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a3  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00ba A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x003f A[SYNTHETIC] */
        @Override // android.content.SharedPreferences.Editor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean commit() {
            /*
                r8 = this;
                android.content.ContentValues r0 = new android.content.ContentValues
                r0.<init>()
                boolean r1 = r8.clear
                r2 = 0
                if (r1 == 0) goto L14
                android.content.ContentResolver r1 = r8.f19986cr
                android.net.Uri r3 = com.tencent.p348mm.opensdk.utils.C10385a.f19987a
                r4 = 0
                r1.delete(r3, r4, r4)
                r8.clear = r2
            L14:
                java.util.Set<java.lang.String> r1 = r8.remove
                java.util.Iterator r1 = r1.iterator()
            L1a:
                boolean r3 = r1.hasNext()
                r4 = 1
                if (r3 == 0) goto L35
                java.lang.Object r3 = r1.next()
                java.lang.String r3 = (java.lang.String) r3
                android.content.ContentResolver r5 = r8.f19986cr
                android.net.Uri r6 = com.tencent.p348mm.opensdk.utils.C10385a.f19987a
                java.lang.String[] r4 = new java.lang.String[r4]
                r4[r2] = r3
                java.lang.String r3 = "key = ?"
                r5.delete(r6, r3, r4)
                goto L1a
            L35:
                java.util.Map<java.lang.String, java.lang.Object> r1 = r8.values
                java.util.Set r1 = r1.entrySet()
                java.util.Iterator r1 = r1.iterator()
            L3f:
                boolean r3 = r1.hasNext()
                if (r3 == 0) goto Lcf
                java.lang.Object r3 = r1.next()
                java.util.Map$Entry r3 = (java.util.Map.Entry) r3
                java.lang.Object r5 = r3.getValue()
                if (r5 != 0) goto L5b
                java.lang.String r6 = "MicroMsg.SDK.PluginProvider.Resolver"
                java.lang.String r7 = "unresolve failed, null value"
                com.tencent.p348mm.opensdk.utils.C10384Log.m6210e(r6, r7)
            L59:
                r6 = r2
                goto L9f
            L5b:
                boolean r6 = r5 instanceof java.lang.Integer
                if (r6 == 0) goto L61
                r6 = r4
                goto L9f
            L61:
                boolean r6 = r5 instanceof java.lang.Long
                if (r6 == 0) goto L67
                r6 = 2
                goto L9f
            L67:
                boolean r6 = r5 instanceof java.lang.String
                if (r6 == 0) goto L6d
                r6 = 3
                goto L9f
            L6d:
                boolean r6 = r5 instanceof java.lang.Boolean
                if (r6 == 0) goto L73
                r6 = 4
                goto L9f
            L73:
                boolean r6 = r5 instanceof java.lang.Float
                if (r6 == 0) goto L79
                r6 = 5
                goto L9f
            L79:
                boolean r6 = r5 instanceof java.lang.Double
                if (r6 == 0) goto L7f
                r6 = 6
                goto L9f
            L7f:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r7 = "unresolve failed, unknown type="
                r6.append(r7)
                java.lang.Class r7 = r5.getClass()
                java.lang.String r7 = r7.toString()
                r6.append(r7)
                java.lang.String r6 = r6.toString()
                java.lang.String r7 = "MicroMsg.SDK.PluginProvider.Resolver"
                com.tencent.p348mm.opensdk.utils.C10384Log.m6210e(r7, r6)
                goto L59
            L9f:
                if (r6 != 0) goto La3
                r5 = r2
                goto Lb8
            La3:
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                java.lang.String r7 = "type"
                r0.put(r7, r6)
                java.lang.String r5 = r5.toString()
                java.lang.String r6 = "value"
                r0.put(r6, r5)
                r5 = r4
            Lb8:
                if (r5 == 0) goto L3f
                android.content.ContentResolver r5 = r8.f19986cr
                android.net.Uri r6 = com.tencent.p348mm.opensdk.utils.C10385a.f19987a
                java.lang.String[] r7 = new java.lang.String[r4]
                java.lang.Object r3 = r3.getKey()
                java.lang.String r3 = (java.lang.String) r3
                r7[r2] = r3
                java.lang.String r3 = "key = ?"
                r5.update(r6, r0, r3, r7)
                goto L3f
            Lcf:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.p348mm.opensdk.openapi.MMSharedPreferences.REditor.commit():boolean");
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            this.values.put(str, Boolean.valueOf(z));
            this.remove.remove(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f) {
            this.values.put(str, Float.valueOf(f));
            this.remove.remove(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i) {
            this.values.put(str, Integer.valueOf(i));
            this.remove.remove(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j) {
            this.values.put(str, Long.valueOf(j));
            this.remove.remove(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            this.values.put(str, str2);
            this.remove.remove(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            return null;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            this.remove.add(str);
            return this;
        }
    }

    public MMSharedPreferences(Context context) {
        this.f19985cr = context.getContentResolver();
    }

    private Object getValue(String str) {
        try {
            Cursor query = this.f19985cr.query(C10385a.f19987a, this.columns, "key = ?", new String[]{str}, null);
            if (query == null) {
                return null;
            }
            Object m6228a = query.moveToFirst() ? C10372a.m6228a(query.getInt(query.getColumnIndex("type")), query.getString(query.getColumnIndex("value"))) : null;
            query.close();
            return m6228a;
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.SharedPreferences", "getValue exception:" + e.getMessage());
            return null;
        }
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        return getValue(str) != null;
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        if (this.editor == null) {
            this.editor = new REditor(this.f19985cr);
        }
        return this.editor;
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        try {
            Cursor query = this.f19985cr.query(C10385a.f19987a, this.columns, null, null, null);
            if (query == null) {
                return null;
            }
            int columnIndex = query.getColumnIndex("key");
            int columnIndex2 = query.getColumnIndex("type");
            int columnIndex3 = query.getColumnIndex("value");
            while (query.moveToNext()) {
                this.values.put(query.getString(columnIndex), C10372a.m6228a(query.getInt(columnIndex2), query.getString(columnIndex3)));
            }
            query.close();
            return this.values;
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.SharedPreferences", "getAll exception:" + e.getMessage());
            return this.values;
        }
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Boolean)) ? z : ((Boolean) value).booleanValue();
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Float)) ? f : ((Float) value).floatValue();
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Integer)) ? i : ((Integer) value).intValue();
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j) {
        Object value = getValue(str);
        return (value == null || !(value instanceof Long)) ? j : ((Long) value).longValue();
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        Object value = getValue(str);
        return (value == null || !(value instanceof String)) ? str2 : (String) value;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        return null;
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }
}
