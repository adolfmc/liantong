package com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin;

import android.text.TextUtils;
import com.sinovatech.unicom.p318ui.App;
import io.objectbox.Box;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LowcodeJSStorageBox {
    private static final String TAG = "LowcodeJSStorageBox";
    private static Box<LowcodeJSStorageEntity> boxInstance;

    private static Box<LowcodeJSStorageEntity> getBox() {
        synchronized (LowcodeJSStorageEntity.class) {
            if (boxInstance == null) {
                boxInstance = App.getBoxStore().boxFor(LowcodeJSStorageEntity.class);
            }
        }
        return boxInstance;
    }

    public static void put(String str, String str2, String str3) throws Exception {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        LowcodeJSStorageEntity findFirst = getBox().query().equal(LowcodeJSStorageEntity_.appId, str).equal(LowcodeJSStorageEntity_.key, str2).build().findFirst();
        if (findFirst == null) {
            findFirst = new LowcodeJSStorageEntity();
        }
        findFirst.setAppId(str);
        findFirst.setKey(str2);
        findFirst.setValue(str3);
        getBox().put((Box<LowcodeJSStorageEntity>) findFirst);
    }

    public static String get(String str, String str2) throws Exception {
        LowcodeJSStorageEntity findFirst;
        return (TextUtils.isEmpty(str2) || (findFirst = getBox().query().equal(LowcodeJSStorageEntity_.appId, str).equal(LowcodeJSStorageEntity_.key, str2).build().findFirst()) == null) ? "" : findFirst.getValue();
    }

    public static void delete(String str, String str2) throws Exception {
        LowcodeJSStorageEntity findFirst;
        if (TextUtils.isEmpty(str2) || (findFirst = getBox().query().equal(LowcodeJSStorageEntity_.appId, str).equal(LowcodeJSStorageEntity_.key, str2).build().findFirst()) == null) {
            return;
        }
        getBox().remove((Box<LowcodeJSStorageEntity>) findFirst);
    }

    public static void clear(String str) throws Exception {
        long[] findIds;
        if (TextUtils.isEmpty(str) || (findIds = getBox().query().equal(LowcodeJSStorageEntity_.appId, str).build().findIds()) == null) {
            return;
        }
        getBox().remove(findIds);
    }

    public static void clearAll() throws Exception {
        getBox().removeAll();
    }
}
