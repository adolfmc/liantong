package com.sinovatech.unicom.basic.eventbus;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PopWebViewEvent {
    public static int currentType = 4;
    private int action;
    private int tabType;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class Action {
        public static final int HideWebView = 2;
        public static final int None = 0;
        public static final int OpenDetail = 3;
        public static final int ShowWebView = 1;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class TabType {

        /* renamed from: Ad */
        public static final int f18370Ad = 7;
        public static final int Home = 4;
        public static final int Service = 5;
        public static final int Shop = 6;
        public static final int User = 8;
    }

    public static String typeEnumToString(int i) {
        switch (i) {
            case 4:
                return "1000239999";
            case 5:
                return "1000239989";
            case 6:
                return "1000239988";
            case 7:
                return "1000239987";
            case 8:
                return "1000239986";
            default:
                return "";
        }
    }

    public PopWebViewEvent(int i) {
        this.action = i;
    }

    public PopWebViewEvent(int i, String str) {
        this.action = i;
        if ("1000239999".equals(str)) {
            this.tabType = 4;
        } else if ("1000239989".equals(str)) {
            this.tabType = 5;
        } else if ("1000239988".equals(str)) {
            this.tabType = 6;
        } else if ("1000239987".equals(str)) {
            this.tabType = 7;
        } else if ("1000239986".equals(str)) {
            this.tabType = 8;
        }
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int i) {
        this.action = i;
    }

    public int getTabType() {
        return this.tabType;
    }

    public void setTabType(int i) {
        this.tabType = i;
    }
}
