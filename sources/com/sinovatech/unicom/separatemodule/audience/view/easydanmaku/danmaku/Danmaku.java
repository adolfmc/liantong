package com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku;

import android.text.SpannableStringBuilder;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class Danmaku {
    public static final String COLOR_BLUE = "#ff0000ff";
    public static final String COLOR_GREEN = "#ff00ff00";
    public static final String COLOR_PURPLE = "#ffff00ff";
    public static final String COLOR_RED = "#ffff0000";
    public static final String COLOR_WHITE = "#ffffffff";
    public static final String COLOR_YELLOW = "#ffffff00";
    public static final int DEFAULT_TEXT_SIZE = 24;
    public String color;
    public Mode mode;
    public int size;
    public SpannableStringBuilder spanText;
    public String text;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum Mode {
        scroll,
        top,
        bottom
    }

    public Danmaku() {
        this.size = 24;
        this.mode = Mode.scroll;
        this.color = COLOR_WHITE;
    }

    public Danmaku(String str, int i, Mode mode, String str2) {
        this.size = 24;
        this.mode = Mode.scroll;
        this.color = COLOR_WHITE;
        this.text = str;
        this.size = i;
        this.mode = mode;
        this.color = str2;
    }

    public String toString() {
        return "Danmaku{text='" + this.text + "', textSize=" + this.size + ", mode=" + this.mode + ", color='" + this.color + "'}";
    }
}
