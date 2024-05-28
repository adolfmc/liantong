package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ContactManager {
    private static String pattern1 = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
    private static String pattern2 = "^[0-9]+$";

    public static JSONArray searchContacts(Context context, String str) throws Exception {
        String filterPhoneNumber = filterPhoneNumber(str);
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, String> entry : getContactMap(context).entrySet()) {
            if (entry.getKey().contains(filterPhoneNumber)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("phoneNumber", entry.getKey());
                jSONObject.put("name", entry.getValue());
                jSONArray.put(jSONObject);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("根据手机号查询结果：");
        sb.append(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
        MsLogUtil.m7980d(sb.toString());
        return jSONArray;
    }

    public static JSONArray searchAllContacts(Context context) throws Exception {
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, String> entry : getContactMap(context).entrySet()) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("phoneNumber", entry.getKey());
            jSONObject.put("name", entry.getValue());
            jSONArray.put(jSONObject);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("所有联系人手机号查询：");
        sb.append(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
        MsLogUtil.m7980d(sb.toString());
        return jSONArray;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x00aa, code lost:
        if (r7.isClosed() == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00b9, code lost:
        if (r7.isClosed() == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00bb, code lost:
        r7.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.String, java.lang.String> getContactMap(android.content.Context r7) throws java.lang.Exception {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            android.content.ContentResolver r1 = r7.getContentResolver()
            r7 = 0
            java.lang.String r2 = "display_name"
            java.lang.String r3 = "data1"
            java.lang.String[] r3 = new java.lang.String[]{r2, r3}     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            android.net.Uri r2 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
        L1b:
            boolean r1 = r7.moveToNext()     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            if (r1 == 0) goto La4
            java.lang.String r1 = "display_name"
            int r1 = r7.getColumnIndex(r1)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r1 = r7.getString(r1)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r2 = "data1"
            int r2 = r7.getColumnIndex(r2)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r2 = r7.getString(r2)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            if (r3 != 0) goto L1b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            r3.<init>()     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r4 = "原始手机号："
            r3.append(r4)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            r3.append(r2)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r4 = "，姓名："
            r3.append(r4)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            r3.append(r1)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7980d(r3)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r3 = "+86"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.replace(r3, r4)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r3 = " "
            java.lang.String r4 = ""
            java.lang.String r2 = r2.replace(r3, r4)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r3 = "-"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.replace(r3, r4)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r2 = filterPhoneNumber(r2)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r1 = filterName(r1)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            r3.<init>()     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r4 = "过滤后手机号："
            r3.append(r4)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            r3.append(r2)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r4 = "，姓名："
            r3.append(r4)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            r3.append(r1)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7980d(r3)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            r0.put(r2, r1)     // Catch: java.lang.Throwable -> Lad java.lang.Exception -> Laf
            goto L1b
        La4:
            if (r7 == 0) goto Lbe
            boolean r1 = r7.isClosed()
            if (r1 != 0) goto Lbe
            goto Lbb
        Lad:
            r0 = move-exception
            goto Lbf
        Laf:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> Lad
            if (r7 == 0) goto Lbe
            boolean r1 = r7.isClosed()
            if (r1 != 0) goto Lbe
        Lbb:
            r7.close()
        Lbe:
            return r0
        Lbf:
            if (r7 == 0) goto Lca
            boolean r1 = r7.isClosed()
            if (r1 != 0) goto Lca
            r7.close()
        Lca:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.ContactManager.getContactMap(android.content.Context):java.util.Map");
    }

    public static JSONObject chooseContact(Context context, Uri uri) throws Exception {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("phoneNumber", "");
        jSONObject.put("displayName", "");
        jSONObject.put("phoneNumberList", jSONArray);
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        if (query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex("_id"));
            String filterName = filterName(query.getString(query.getColumnIndex("display_name")));
            if (Integer.parseInt(query.getString(query.getColumnIndex("has_phone_number"))) > 0) {
                ContentResolver contentResolver = context.getContentResolver();
                Uri uri2 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Cursor query2 = contentResolver.query(uri2, null, "contact_id = " + string, null, null);
                while (query2.moveToNext()) {
                    jSONArray.put(filterPhoneNumber(query2.getString(query2.getColumnIndex("data1"))));
                }
                query2.close();
            }
            jSONObject.put("displayName", filterName);
            jSONObject.put("phoneNumberList", jSONArray);
            if (jSONArray.length() > 0) {
                jSONObject.put("phoneNumber", jSONArray.getString(0));
            }
        }
        if (query != null && !query.isClosed()) {
            query.close();
        }
        return jSONObject;
    }

    public static String filterPhoneNumber(String str) {
        char[] charArray;
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Pattern.matches(pattern2, String.valueOf(c))) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String filterName(String str) {
        return str.replace("'", "").replace("\"", "").replace("”", "").replace("‘", "").replace("\n", "");
    }

    public static ArrayList<CallLogEntity> getAllCallLogs(Context context) throws Exception {
        String str;
        Cursor query = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, "date DESC");
        ArrayList<CallLogEntity> arrayList = new ArrayList<>();
        while (query.moveToNext()) {
            CallLogEntity callLogEntity = new CallLogEntity();
            callLogEntity.setCallNumber(filterPhoneNumber(query.getString(query.getColumnIndex("number"))));
            String string = query.getString(query.getColumnIndex("name"));
            if (string == null) {
                string = "";
            }
            callLogEntity.setCallName(filterName(string));
            int i = query.getInt(query.getColumnIndex("type"));
            if (i != 5) {
                switch (i) {
                    case 1:
                        str = "1";
                        continue;
                    case 2:
                        str = "2";
                        continue;
                    case 3:
                        str = "3";
                        continue;
                    default:
                        str = "5";
                        continue;
                }
            } else {
                str = "4";
            }
            callLogEntity.setCallType(str);
            callLogEntity.setCallTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(query.getLong(query.getColumnIndex("date")))));
            callLogEntity.setCallDuration(query.getInt(query.getColumnIndex("duration")));
            arrayList.add(callLogEntity);
        }
        return arrayList;
    }

    public static JSONArray searchAllCallLogs(Context context) throws Exception {
        ArrayList<CallLogEntity> allCallLogs = getAllCallLogs(context);
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < allCallLogs.size(); i++) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("callNumber", allCallLogs.get(i).getCallNumber());
            if (TextUtils.isEmpty(allCallLogs.get(i).getCallName())) {
                jSONObject.put("callName", "");
            } else {
                jSONObject.put("callName", allCallLogs.get(i).getCallName());
            }
            jSONObject.put("callType", allCallLogs.get(i).getCallType());
            jSONObject.put("callTime", allCallLogs.get(i).getCallTime());
            jSONObject.put("callDuration", allCallLogs.get(i).getCallDuration());
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public static JSONArray searchCallLogs(Context context, String str) throws Exception {
        ArrayList<CallLogEntity> allCallLogs = getAllCallLogs(context);
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < allCallLogs.size(); i++) {
            if (allCallLogs.get(i).getCallNumber().contains(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("callNumber", allCallLogs.get(i).getCallNumber());
                if (TextUtils.isEmpty(allCallLogs.get(i).getCallName())) {
                    jSONObject.put("callName", "");
                } else {
                    jSONObject.put("callName", allCallLogs.get(i).getCallName());
                }
                jSONObject.put("callType", allCallLogs.get(i).getCallType());
                jSONObject.put("callTime", allCallLogs.get(i).getCallTime());
                jSONObject.put("callDuration", allCallLogs.get(i).getCallDuration());
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x004e, code lost:
        if (r1.isClosed() == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x005d, code lost:
        if (r1.isClosed() == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.CallLogGroupEntity> getAllGroup(android.content.Context r8) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            android.net.Uri r3 = android.provider.ContactsContract.Groups.CONTENT_URI     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
        L14:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            if (r2 == 0) goto L45
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.CallLogGroupEntity r2 = new com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.CallLogGroupEntity     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.<init>()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r3 = "_id"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            int r3 = r1.getInt(r3)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r4 = "title"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.lang.String r4 = r1.getString(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            java.util.ArrayList r5 = getAllGroupsInfo(r8, r3)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.setGroupId(r3)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.setGroupName(r4)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r2.setArrayList(r5)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            r0.add(r2)     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            goto L14
        L45:
            r1.close()     // Catch: java.lang.Throwable -> L51 java.lang.Exception -> L53
            if (r1 == 0) goto L62
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto L62
            goto L5f
        L51:
            r8 = move-exception
            goto L63
        L53:
            r8 = move-exception
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L51
            if (r1 == 0) goto L62
            boolean r8 = r1.isClosed()
            if (r8 != 0) goto L62
        L5f:
            r1.close()
        L62:
            return r0
        L63:
            if (r1 == 0) goto L6e
            boolean r0 = r1.isClosed()
            if (r0 != 0) goto L6e
            r1.close()
        L6e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.ContactManager.getAllGroup(android.content.Context):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00b3, code lost:
        if (r2.isClosed() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00c2, code lost:
        if (r2.isClosed() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c4, code lost:
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.CallLogEntity> getAllGroupsInfo(android.content.Context r16, int r17) {
        /*
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.lang.String r0 = "raw_contact_id"
            java.lang.String[] r5 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r6 = "data1=? and mimetype='vnd.android.cursor.item/group_membership'"
            android.content.ContentResolver r3 = r16.getContentResolver()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            android.net.Uri r4 = android.provider.ContactsContract.Data.CONTENT_URI     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r0 = 1
            java.lang.String[] r7 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r8.<init>()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r9 = r17
            r8.append(r9)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r9 = ""
            r8.append(r9)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r9 = 0
            r7[r9] = r8     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r8 = "data1 asc"
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
        L33:
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            if (r3 == 0) goto Laa
            java.lang.String r3 = "raw_contact_id"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            int r3 = r2.getInt(r3)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.CallLogEntity r4 = new com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.CallLogEntity     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r4.<init>()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r5 = "content://com.android.contacts/data"
            android.net.Uri r11 = android.net.Uri.parse(r5)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            android.content.ContentResolver r10 = r16.getContentResolver()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r12 = 0
            java.lang.String r13 = "raw_contact_id=?"
            java.lang.String[] r14 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r5.<init>()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r5.append(r3)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r3 = ""
            r5.append(r3)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r14[r9] = r3     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r15 = 0
            android.database.Cursor r3 = r10.query(r11, r12, r13, r14, r15)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
        L6f:
            boolean r5 = r3.moveToNext()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            if (r5 == 0) goto La3
            java.lang.String r5 = "data1"
            int r5 = r3.getColumnIndex(r5)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r6 = "mimetype"
            int r6 = r3.getColumnIndex(r6)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r6 = r3.getString(r6)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            java.lang.String r7 = "vnd.android.cursor.item/phone_v2"
            boolean r7 = r7.equals(r6)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            if (r7 == 0) goto L96
            r4.setCallNumber(r5)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            goto L6f
        L96:
            java.lang.String r7 = "vnd.android.cursor.item/name"
            boolean r6 = r7.equals(r6)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            if (r6 == 0) goto L6f
            r4.setCallName(r5)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            goto L6f
        La3:
            r3.close()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            r1.add(r4)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            goto L33
        Laa:
            r2.close()     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lb8
            if (r2 == 0) goto Lc7
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto Lc7
            goto Lc4
        Lb6:
            r0 = move-exception
            goto Lc8
        Lb8:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Lb6
            if (r2 == 0) goto Lc7
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto Lc7
        Lc4:
            r2.close()
        Lc7:
            return r1
        Lc8:
            if (r2 == 0) goto Ld3
            boolean r1 = r2.isClosed()
            if (r1 != 0) goto Ld3
            r2.close()
        Ld3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact.ContactManager.getAllGroupsInfo(android.content.Context, int):java.util.ArrayList");
    }
}
