package com.gmrz.android.client.asm.api;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public enum Color {
    RED("红色", 1),
    GREEN("绿色", 2),
    BLANK("白色", 3),
    YELLO("黄色", 4);
    
    private int index;
    private String name;

    Color(String str, int i) {
        this.name = str;
        this.index = i;
    }

    public static String getName(int i) {
        Color[] values;
        for (Color color : values()) {
            if (color.getIndex() == i) {
                return color.name;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }
}
