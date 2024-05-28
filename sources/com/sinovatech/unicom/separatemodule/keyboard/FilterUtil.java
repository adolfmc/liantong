package com.sinovatech.unicom.separatemodule.keyboard;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import com.sinovatech.unicom.common.UIUtils;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FilterUtil {
    public static final String EMOJI = "[🀀-\u1f3ff]|[🐀-\u1f7ff]|[☀-⟿]";
    public static final String MATCHER_CN = "[\\u4e00-\\u9fa5]";
    public static final String MATCHER_DEFAULT = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5\\p{P}]+";
    public static final String MATCHER_MONEY = "(^[1-9]\\d*(\\.\\d{1,2})?$)|(^[0]{1}(\\.\\d{1,2})?$)";
    public static final String MATCHER_NICKNAME = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";

    public static InputFilter getInputFilter(Context context, final String str, final String str2) {
        return new InputFilter() { // from class: com.sinovatech.unicom.separatemodule.keyboard.FilterUtil.1
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (charSequence.length() <= 0 || !FilterUtil.isMatcher(charSequence.toString(), str2)) {
                    return null;
                }
                UIUtils.toast(str);
                return "";
            }
        };
    }

    public static boolean isMatcher(String str, String str2) {
        return Pattern.compile(str2).matcher(str).find();
    }
}
