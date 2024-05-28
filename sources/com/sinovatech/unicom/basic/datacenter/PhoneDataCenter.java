package com.sinovatech.unicom.basic.datacenter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PhoneDataCenter {
    private final String TAG = "PhoneDataCenter";
    private Context context;

    public PhoneDataCenter(Context context) {
        this.context = context;
    }

    public String getContactJsonData(String str) {
        Map<String, String> contactList = getContactList();
        if (contactList.size() < 1) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("telinfo");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                String trim = jSONObject2.getString("phone").replace("+86", "").replace(" ", "").replace("-", "").trim();
                if (contactList.containsKey(trim)) {
                    jSONObject2.put("name", contactList.get(trim));
                }
            }
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0069, code lost:
        if (r1.isClosed() == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0078, code lost:
        if (r1.isClosed() == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007a, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Map<java.lang.String, java.lang.String> getContactList() {
        /*
            r8 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            android.content.Context r1 = r8.context
            android.content.ContentResolver r2 = r1.getContentResolver()
            r1 = 0
            java.lang.String r3 = "display_name"
            java.lang.String r4 = "data1"
            java.lang.String r5 = "lookup"
            java.lang.String[] r4 = new java.lang.String[]{r3, r4, r5}     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            android.net.Uri r3 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
        L1f:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            if (r2 == 0) goto L63
            java.lang.String r2 = "display_name"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r3 = "data1"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            if (r4 != 0) goto L1f
            java.lang.String r4 = "+86"
            java.lang.String r5 = ""
            java.lang.String r3 = r3.replace(r4, r5)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r4 = " "
            java.lang.String r5 = ""
            java.lang.String r3 = r3.replace(r4, r5)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r4 = "-"
            java.lang.String r5 = ""
            java.lang.String r3 = r3.replace(r4, r5)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r0.put(r3, r2)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            goto L1f
        L63:
            if (r1 == 0) goto L7d
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L7d
            goto L7a
        L6c:
            r0 = move-exception
            goto L7e
        L6e:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L6c
            if (r1 == 0) goto L7d
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L7d
        L7a:
            r1.close()
        L7d:
            return r0
        L7e:
            if (r1 == 0) goto L89
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L89
            r1.close()
        L89:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.datacenter.PhoneDataCenter.getContactList():java.util.Map");
    }

    public String getContactInfoByJSInterface(Intent intent) {
        String str = "";
        if (intent != null) {
            Cursor cursor = null;
            try {
                try {
                    if (intent.getData() != null) {
                        JSONArray jSONArray = new JSONArray();
                        cursor = this.context.getContentResolver().query(intent.getData(), null, null, null, null);
                        if (cursor.moveToFirst()) {
                            String string = cursor.getString(cursor.getColumnIndex("_id"));
                            String string2 = cursor.getString(cursor.getColumnIndex("display_name"));
                            if (Integer.parseInt(cursor.getString(cursor.getColumnIndex("has_phone_number"))) > 0) {
                                ContentResolver contentResolver = this.context.getContentResolver();
                                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                                Cursor query = contentResolver.query(uri, null, "contact_id = " + string, null, null);
                                while (query.moveToNext()) {
                                    str = query.getString(query.getColumnIndex("data1"));
                                    jSONArray.put(new JSONObject("{\"phone\":\"" + str.replace("+86", "").replace(" ", "").replace("-", "").trim() + "\",\"name\":\"" + string2.trim() + "\"}"));
                                }
                                query.close();
                            }
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("{\"telinfo\":");
                        sb.append(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
                        sb.append("}");
                        String sb2 = sb.toString();
                        if (cursor == null || cursor.isClosed()) {
                            return sb2;
                        }
                        cursor.close();
                        return sb2;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return str;
                }
            } catch (Throwable th) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                throw th;
            }
        }
        return "";
    }
}
