package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import android.text.TextUtils;
import com.sinovatech.unicom.p318ui.App;
import io.objectbox.Box;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class JSStorageBox {
    private static final String TAG = "JSStorageBox";
    private static Box<JSStorageEntity> boxInstance;

    private static Box<JSStorageEntity> getBox() {
        synchronized (JSStorageEntity.class) {
            if (boxInstance == null) {
                boxInstance = App.getBoxStore().boxFor(JSStorageEntity.class);
            }
        }
        return boxInstance;
    }

    public static void put(String str, String str2, String str3) throws Exception {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        JSStorageEntity findFirst = getBox().query().equal(JSStorageEntity_.appId, str).equal(JSStorageEntity_.key, str2).build().findFirst();
        if (findFirst == null) {
            findFirst = new JSStorageEntity();
        }
        findFirst.setAppId(str);
        findFirst.setKey(str2);
        findFirst.setValue(str3);
        getBox().put((Box<JSStorageEntity>) findFirst);
    }

    public static String get(String str, String str2) throws Exception {
        JSStorageEntity findFirst;
        return (TextUtils.isEmpty(str2) || (findFirst = getBox().query().equal(JSStorageEntity_.appId, str).equal(JSStorageEntity_.key, str2).build().findFirst()) == null) ? "" : findFirst.getValue();
    }

    public static void delete(String str, String str2) throws Exception {
        JSStorageEntity findFirst;
        if (TextUtils.isEmpty(str2) || (findFirst = getBox().query().equal(JSStorageEntity_.appId, str).equal(JSStorageEntity_.key, str2).build().findFirst()) == null) {
            return;
        }
        getBox().remove((Box<JSStorageEntity>) findFirst);
    }

    public static void clear(String str) throws Exception {
        long[] findIds;
        if (TextUtils.isEmpty(str) || (findIds = getBox().query().equal(JSStorageEntity_.appId, str).build().findIds()) == null) {
            return;
        }
        getBox().remove(findIds);
    }

    public static void clearAll() throws Exception {
        getBox().removeAll();
    }

    public static long querySizeForAppId(String str) throws Exception {
        List<JSStorageEntity> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str) && (arrayList = getBox().query().equal(JSStorageEntity_.appId, str).build().find()) == null) {
            arrayList = new ArrayList<>();
        }
        long j = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            j += arrayList.get(i).getValue().getBytes().length;
        }
        return j / 1024;
    }

    public static List<String> querykeysForAppId(String str) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            List<JSStorageEntity> find = getBox().query().equal(JSStorageEntity_.appId, str).build().find();
            if (find == null) {
                find = new ArrayList<>();
            }
            for (int i = 0; i < find.size(); i++) {
                arrayList.add(find.get(i).getKey());
            }
        }
        return arrayList;
    }
}
