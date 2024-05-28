package com.sinovatech.unicom.separatemodule.audience.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.AlivcLiveMessageInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ChatTextUtil {
    public static SpannableStringBuilder getChat(Context context, AlivcLiveMessageInfo alivcLiveMessageInfo, String str) {
        int dip2px = (UIUtils.dip2px((Activity) context, 11.0f) * 13) / 10;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        Bitmap levelBitMap = getLevelBitMap(context, dip2px, alivcLiveMessageInfo.getLevel());
        int i = dip2px * 2;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), 2131230939), i, dip2px, true);
        spannableStringBuilder.append((CharSequence) (alivcLiveMessageInfo.getSendName() + ": ")).append((CharSequence) str).append((CharSequence) " ");
        spannableStringBuilder.setSpan(new RoundedBackgroundSpan(context, getLevelColor(alivcLiveMessageInfo.getLevel()), 0, alivcLiveMessageInfo.getSendName().length() + 1, createScaledBitmap, (float) i, levelBitMap, (float) dip2px), 0, spannableStringBuilder.length() - 1, 33);
        return spannableStringBuilder;
    }

    public static SpannableStringBuilder getChat(Context context, AlivcLiveMessageInfo alivcLiveMessageInfo) {
        int dip2px = (UIUtils.dip2px((Activity) context, 11.0f) * 13) / 10;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), 2131230939), dip2px * 2, dip2px, true);
        SpannableString spannableString = new SpannableString("1");
        spannableString.setSpan(new ImageSpan(context, createScaledBitmap), 0, 1, 33);
        SpannableString spannableString2 = new SpannableString("1");
        spannableString2.setSpan(new ImageSpan(context, getLevelBitMap(context, dip2px, alivcLiveMessageInfo.getLevel())), 0, 1, 33);
        SpannableString spannableString3 = new SpannableString(alivcLiveMessageInfo.getSendName() + ": ");
        spannableString3.setSpan(new ForegroundColorSpan(getLevelColor(alivcLiveMessageInfo.getLevel())), 0, alivcLiveMessageInfo.getSendName().length() + 1, 33);
        SpannableString spannableString4 = new SpannableString(alivcLiveMessageInfo.getDataContent());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (!"1".equals(alivcLiveMessageInfo.getLevel())) {
            spannableStringBuilder.append((CharSequence) spannableString2).append((CharSequence) "  ");
        }
        if (alivcLiveMessageInfo.isMgr()) {
            spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) "  ");
        }
        spannableStringBuilder.append((CharSequence) spannableString3).append((CharSequence) spannableString4);
        return spannableStringBuilder;
    }

    private static Bitmap getLevelBitMap(Context context, int i, String str) {
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), 2131230924), i, i, true);
        if ("2".equals(str)) {
            createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), 2131230925), i, i, true);
        }
        if ("3".equals(str)) {
            createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), 2131230926), i, i, true);
        }
        if ("4".equals(str)) {
            createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), 2131230927), i, i, true);
        }
        return "5".equals(str) ? Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), 2131230928), i, i, true) : createScaledBitmap;
    }

    private static int getLevelColor(String str) {
        int i = "3".equals(str) ? -6070 : -9240582;
        if ("4".equals(str)) {
            i = -4859649;
        }
        if ("5".equals(str)) {
            return -27919;
        }
        return i;
    }
}
