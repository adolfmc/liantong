package com.sinovatech.unicom.separatemodule.keyboard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SpanStringUtils {
    public static SpannableString getEmotionContent(int i, Context context, TextView textView, String str) {
        SpannableString spannableString = new SpannableString(str);
        Resources resources = context.getResources();
        Matcher matcher = Pattern.compile("\\[([一-龥\\w])+\\]").matcher(spannableString);
        while (matcher.find()) {
            String group2 = matcher.group();
            int start = matcher.start();
            Integer valueOf = Integer.valueOf(EmotionUtils.getImgByName(i, group2));
            if (valueOf != null) {
                int textSize = (((int) textView.getTextSize()) * 13) / 10;
                spannableString.setSpan(new ImageSpan(context, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, valueOf.intValue()), textSize, textSize, true)), start, group2.length() + start, 33);
            }
        }
        return spannableString;
    }
}
