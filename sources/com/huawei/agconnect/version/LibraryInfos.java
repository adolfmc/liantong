package com.huawei.agconnect.version;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class LibraryInfos {
    private static final LibraryInfos INSTANCE = new LibraryInfos();
    private String libraryType = "Java";

    LibraryInfos() {
    }

    public static LibraryInfos getInstance() {
        return INSTANCE;
    }

    public String getLibraryType() {
        return this.libraryType;
    }

    public void registerLibraryType(String str) {
        this.libraryType = str;
    }
}
