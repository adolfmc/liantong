package com.sinovatech.unicom.common;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.sinovatech.unicom.basic.p314po.PeopleEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ContectUtil {
    private Context context;
    private String[] array = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    /* renamed from: sp */
    public String f18446sp = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";

    public ContectUtil(Context context) {
        this.context = context;
    }

    public List<PeopleEntity> getPhoneNumList() {
        char[] charArray;
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = this.context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"display_name", "data1", "lookup"}, null, null, null);
            while (query.moveToNext()) {
                PeopleEntity peopleEntity = new PeopleEntity();
                String string = query.getString(query.getColumnIndex("display_name"));
                if (!TextUtils.isEmpty(string)) {
                    StringBuilder sb = new StringBuilder();
                    for (char c : string.toCharArray()) {
                        if (Pattern.matches(this.f18446sp, String.valueOf(c))) {
                            sb.append(c);
                        }
                    }
                    string = sb.toString();
                }
                String replace = query.getString(query.getColumnIndex("data1")).replace("'", "").replace("\"", "").replace("”", "").replace("‘", "");
                peopleEntity.setName(string);
                String upperCase = getPinYinHeadChar(string).toUpperCase();
                if (Arrays.asList(this.array).contains(upperCase)) {
                    peopleEntity.setPingyin(upperCase);
                } else {
                    peopleEntity.setPingyin("#");
                }
                ArrayList arrayList2 = new ArrayList();
                if (!TextUtils.isEmpty(replace)) {
                    arrayList2.add(replace.replace("+86", "").replace(" ", "").replace("-", ""));
                }
                peopleEntity.setList(arrayList2);
                arrayList.add(peopleEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public List<PeopleEntity> getPhoneNumList2() {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = this.context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id", "display_name", "photo_id"}, null, null, null);
            while (query.moveToNext()) {
                PeopleEntity peopleEntity = new PeopleEntity();
                String string = query.getString(query.getColumnIndex("display_name"));
                peopleEntity.setName(string);
                String upperCase = getPinYinHeadChar(string).toUpperCase();
                if (Arrays.asList(this.array).contains(upperCase)) {
                    peopleEntity.setPingyin(upperCase);
                } else {
                    peopleEntity.setPingyin("#");
                }
                String string2 = query.getString(query.getColumnIndex("_id"));
                ContentResolver contentResolver = this.context.getContentResolver();
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Cursor query2 = contentResolver.query(uri, null, "contact_id = " + string2, null, null);
                ArrayList arrayList2 = new ArrayList();
                while (query2.moveToNext()) {
                    String string3 = query2.getString(query2.getColumnIndex("data1"));
                    if (!TextUtils.isEmpty(string3)) {
                        string3 = string3.replace(" ", "").replace("-", "");
                    }
                    arrayList2.add(string3);
                }
                peopleEntity.setList(arrayList2);
                arrayList.add(peopleEntity);
                query2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public String getJson() {
        String[] strArr;
        JSONArray jSONArray = new JSONArray();
        try {
            List<PeopleEntity> phoneNumList = getPhoneNumList();
            Collections.sort(phoneNumList, new Comparator<PeopleEntity>() { // from class: com.sinovatech.unicom.common.ContectUtil.1
                @Override // java.util.Comparator
                public int compare(PeopleEntity peopleEntity, PeopleEntity peopleEntity2) {
                    String name = peopleEntity.getName();
                    String name2 = peopleEntity2.getName();
                    int i = 0;
                    while (i < name.length() && i < name2.length()) {
                        char charAt = name.charAt(i);
                        char charAt2 = name2.charAt(i);
                        if (Character.isSupplementaryCodePoint(charAt) || Character.isSupplementaryCodePoint(charAt2)) {
                            i++;
                        }
                        if (charAt != charAt2) {
                            if (Character.isSupplementaryCodePoint(charAt) || Character.isSupplementaryCodePoint(charAt2)) {
                                return charAt - charAt2;
                            }
                            char c = charAt;
                            String str = PinyinHelper.toHanyuPinyinStringArray(c) == null ? null : PinyinHelper.toHanyuPinyinStringArray(c)[0];
                            char c2 = charAt2;
                            String str2 = PinyinHelper.toHanyuPinyinStringArray(c2) != null ? PinyinHelper.toHanyuPinyinStringArray(c2)[0] : null;
                            if (str == null || str2 == null) {
                                return charAt - charAt2;
                            }
                            if (!str.equals(str2)) {
                                return str.compareTo(str2);
                            }
                        }
                        i++;
                    }
                    return name.length() - name2.length();
                }
            });
            for (String str : this.array) {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < phoneNumList.size(); i++) {
                    PeopleEntity peopleEntity = phoneNumList.get(i);
                    if (str.equals(peopleEntity.getPingyin())) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("name", peopleEntity.getName());
                        JSONArray jSONArray3 = new JSONArray();
                        for (int i2 = 0; i2 < peopleEntity.getList().size(); i2++) {
                            jSONArray3.put(peopleEntity.getList().get(i2));
                        }
                        jSONObject2.put("phoneList ", jSONArray3);
                        jSONArray2.put(jSONObject2);
                    }
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("index ", str);
                    jSONObject.put("letter ", jSONArray2);
                    jSONArray.put(jSONObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
    }

    public static String getPinYinHeadChar(String str) {
        String[] hanyuPinyinStringArray;
        String str2 = "";
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            str2 = PinyinHelper.toHanyuPinyinStringArray(charAt) != null ? str2 + hanyuPinyinStringArray[0].charAt(0) : str2 + charAt;
        }
        return (str2 == null || str2.length() <= 1) ? str2 : str2.subSequence(0, 1).toString();
    }

    private void sort(List<PeopleEntity> list) {
        Collections.sort(list, new Comparator<PeopleEntity>() { // from class: com.sinovatech.unicom.common.ContectUtil.2
            @Override // java.util.Comparator
            public int compare(PeopleEntity peopleEntity, PeopleEntity peopleEntity2) {
                String name = peopleEntity.getName();
                String name2 = peopleEntity2.getName();
                int i = 0;
                while (i < name.length() && i < name2.length()) {
                    char charAt = name.charAt(i);
                    char charAt2 = name2.charAt(i);
                    if (Character.isSupplementaryCodePoint(charAt) || Character.isSupplementaryCodePoint(charAt2)) {
                        i++;
                    }
                    if (charAt != charAt2) {
                        if (Character.isSupplementaryCodePoint(charAt) || Character.isSupplementaryCodePoint(charAt2)) {
                            return charAt - charAt2;
                        }
                        char c = charAt;
                        String str = PinyinHelper.toHanyuPinyinStringArray(c) == null ? null : PinyinHelper.toHanyuPinyinStringArray(c)[0];
                        char c2 = charAt2;
                        String str2 = PinyinHelper.toHanyuPinyinStringArray(c2) != null ? PinyinHelper.toHanyuPinyinStringArray(c2)[0] : null;
                        if (str == null || str2 == null) {
                            return charAt - charAt2;
                        }
                        if (!str.equals(str2)) {
                            return str.compareTo(str2);
                        }
                    }
                    i++;
                }
                return name.length() - name2.length();
            }
        });
    }

    public List<PeopleEntity> getPhoneNumList3() {
        char[] charArray;
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = this.context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id", "display_name", "photo_id"}, null, null, null);
            while (query.moveToNext()) {
                PeopleEntity peopleEntity = new PeopleEntity();
                String string = query.getString(query.getColumnIndex("display_name"));
                if (!TextUtils.isEmpty(string)) {
                    StringBuilder sb = new StringBuilder();
                    for (char c : string.toCharArray()) {
                        if (Pattern.matches(this.f18446sp, String.valueOf(c))) {
                            sb.append(c);
                        }
                    }
                    string = sb.toString();
                }
                peopleEntity.setName(string);
                String upperCase = getPinYinHeadChar(string).toUpperCase();
                if (Arrays.asList(this.array).contains(upperCase)) {
                    peopleEntity.setPingyin(upperCase);
                } else {
                    peopleEntity.setPingyin("#");
                }
                String string2 = query.getString(query.getColumnIndex("_id"));
                Cursor query2 = this.context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = " + string2, null, null);
                ArrayList arrayList2 = new ArrayList();
                while (query2.moveToNext()) {
                    String string3 = query2.getString(query2.getColumnIndex("data1"));
                    if (!TextUtils.isEmpty(string3)) {
                        arrayList2.add(string3.replace(" ", "").replace("-", ""));
                    }
                }
                if (arrayList2.size() > 0) {
                    peopleEntity.setList(arrayList2);
                    arrayList.add(peopleEntity);
                }
                query2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public JSONArray getGroupContect() {
        String[] strArr;
        JSONArray jSONArray = new JSONArray();
        try {
            List<PeopleEntity> phoneNumList3 = getPhoneNumList3();
            Collections.sort(phoneNumList3, new Comparator<PeopleEntity>() { // from class: com.sinovatech.unicom.common.ContectUtil.3
                @Override // java.util.Comparator
                public int compare(PeopleEntity peopleEntity, PeopleEntity peopleEntity2) {
                    String name = peopleEntity.getName();
                    String name2 = peopleEntity2.getName();
                    int i = 0;
                    while (i < name.length() && i < name2.length()) {
                        char charAt = name.charAt(i);
                        char charAt2 = name2.charAt(i);
                        if (Character.isSupplementaryCodePoint(charAt) || Character.isSupplementaryCodePoint(charAt2)) {
                            i++;
                        }
                        if (charAt != charAt2) {
                            if (Character.isSupplementaryCodePoint(charAt) || Character.isSupplementaryCodePoint(charAt2)) {
                                return charAt - charAt2;
                            }
                            char c = charAt;
                            String str = PinyinHelper.toHanyuPinyinStringArray(c) == null ? null : PinyinHelper.toHanyuPinyinStringArray(c)[0];
                            char c2 = charAt2;
                            String str2 = PinyinHelper.toHanyuPinyinStringArray(c2) != null ? PinyinHelper.toHanyuPinyinStringArray(c2)[0] : null;
                            if (str == null || str2 == null) {
                                return charAt - charAt2;
                            }
                            if (!str.equals(str2)) {
                                return str.compareTo(str2);
                            }
                        }
                        i++;
                    }
                    return name.length() - name2.length();
                }
            });
            for (String str : this.array) {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < phoneNumList3.size(); i++) {
                    PeopleEntity peopleEntity = phoneNumList3.get(i);
                    if (str.equals(peopleEntity.getPingyin())) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("name", peopleEntity.getName());
                        JSONArray jSONArray3 = new JSONArray();
                        for (int i2 = 0; i2 < peopleEntity.getList().size(); i2++) {
                            jSONArray3.put(peopleEntity.getList().get(i2));
                        }
                        jSONObject2.put("phoneList ", jSONArray3);
                        jSONArray2.put(jSONObject2);
                    }
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("index ", str);
                    jSONObject.put("userPhoneInfo ", jSONArray2);
                    jSONArray.put(jSONObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONArray;
    }
}
