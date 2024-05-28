package com.sinovatech.unicom.separatemodule.keyboard;

import android.support.p083v4.util.ArrayMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EmotionUtils {
    public static final int EMOTION_CLASSIC_TYPE = 1;
    public static ArrayMap<String, Integer> EMPTY_MAP = new ArrayMap<>();
    public static ArrayMap<String, Integer> EMOTION_CLASSIC_MAP = new ArrayMap<>();

    static {
        EMOTION_CLASSIC_MAP.put("[哈哈]", 2131231226);
        EMOTION_CLASSIC_MAP.put("[笑哭]", 2131231227);
        EMOTION_CLASSIC_MAP.put("[奸诈]", 2131231228);
        EMOTION_CLASSIC_MAP.put("[难过]", 2131231229);
        EMOTION_CLASSIC_MAP.put("[色]", 2131231230);
        EMOTION_CLASSIC_MAP.put("[捂脸]", 2131231231);
        EMOTION_CLASSIC_MAP.put("[皱眉]", 2131231232);
        EMOTION_CLASSIC_MAP.put("[发呆]", 2131231233);
        EMOTION_CLASSIC_MAP.put("[坏笑]", 2131231234);
        EMOTION_CLASSIC_MAP.put("[委屈]", 2131231235);
        EMOTION_CLASSIC_MAP.put("[可爱]", 2131231236);
        EMOTION_CLASSIC_MAP.put("[爱心]", 2131231237);
        EMOTION_CLASSIC_MAP.put("[赞]", 2131231238);
    }

    public static int getImgByName(int i, String str) {
        Integer num = i != 1 ? null : EMOTION_CLASSIC_MAP.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public static ArrayMap<String, Integer> getEmojiMap(int i) {
        if (i == 1) {
            return EMOTION_CLASSIC_MAP;
        }
        return EMPTY_MAP;
    }
}
