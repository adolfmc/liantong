package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.util.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class StorageSettings {

    /* renamed from: a */
    private static volatile StorageSettings f7434a;

    /* renamed from: b */
    private boolean f7435b = false;

    /* renamed from: c */
    private boolean f7436c = true;

    /* renamed from: d */
    private final List<StorageInformation> f7437d = new ArrayList();

    /* renamed from: e */
    private StorageInformation f7438e = null;

    /* renamed from: a */
    public static StorageSettings m18149a() {
        if (f7434a == null) {
            synchronized (StorageSettings.class) {
                if (f7434a == null) {
                    f7434a = new StorageSettings();
                }
            }
        }
        return f7434a;
    }

    private StorageSettings() {
    }

    /* renamed from: a */
    public void m18148a(Context context) {
        if (this.f7435b) {
            return;
        }
        this.f7435b = true;
        try {
            this.f7436c = false;
            this.f7438e = new StorageInformation(context);
            this.f7437d.clear();
            this.f7437d.add(this.f7438e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.f7437d.size() > 0) {
                StorageInformation storageInformation = null;
                int i = 0;
                for (StorageInformation storageInformation2 : this.f7437d) {
                    if (new File(storageInformation2.m18152b()).exists()) {
                        i++;
                        storageInformation = storageInformation2;
                    }
                }
                if (i == 0) {
                    this.f7438e = m18144b(context);
                    if (this.f7438e == null) {
                        Iterator<StorageInformation> it = this.f7437d.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            StorageInformation next = it.next();
                            if (m18147a(context, next)) {
                                this.f7438e = next;
                                break;
                            }
                        }
                    }
                } else if (i == 1) {
                    if (m18147a(context, storageInformation)) {
                        this.f7438e = storageInformation;
                    }
                } else {
                    this.f7438e = m18144b(context);
                }
                if (this.f7438e == null) {
                    this.f7438e = this.f7437d.get(0);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            if (this.f7438e != null && m18146a(this.f7438e.m18153a())) {
                File file = new File(this.f7438e.m18152b());
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(this.f7438e.m18151c());
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                File file3 = new File(file2, ".nomedia");
                if (file3.exists()) {
                    return;
                }
                file3.createNewFile();
                return;
            }
            this.f7436c = false;
            this.f7438e = new StorageInformation(context);
            this.f7437d.clear();
            this.f7437d.add(this.f7438e);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* renamed from: b */
    public StorageInformation m18145b() {
        return this.f7438e;
    }

    /* renamed from: b */
    public StorageInformation m18144b(Context context) {
        String string = context.getSharedPreferences("map_pref", 0).getString("PREFFERED_SD_CARD", "");
        if (string == null || string.length() <= 0) {
            return null;
        }
        for (StorageInformation storageInformation : this.f7437d) {
            if (storageInformation.m18153a().equals(string)) {
                return storageInformation;
            }
        }
        return null;
    }

    /* renamed from: a */
    public boolean m18147a(Context context, StorageInformation storageInformation) {
        String m18153a = storageInformation.m18153a();
        if (m18146a(m18153a)) {
            SharedPreferences.Editor edit = context.getSharedPreferences("map_pref", 0).edit();
            edit.putString("PREFFERED_SD_CARD", m18153a);
            return edit.commit();
        }
        return false;
    }

    /* renamed from: a */
    private boolean m18146a(String str) {
        boolean z = false;
        try {
            File file = new File(str + "/test.0");
            if (file.exists()) {
                file.delete();
            }
            z = file.createNewFile();
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }
}
