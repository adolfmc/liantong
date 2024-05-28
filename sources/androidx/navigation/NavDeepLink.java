package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
class NavDeepLink {
    private static final Pattern SCHEME_PATTERN = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");
    private final ArrayList<String> mArguments = new ArrayList<>();
    private final boolean mExactDeepLink;
    private final Pattern mPattern;

    NavDeepLink(@NonNull String str) {
        StringBuilder sb = new StringBuilder("^");
        if (!SCHEME_PATTERN.matcher(str).find()) {
            sb.append("http[s]?://");
        }
        Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(str);
        boolean z = !str.contains(".*");
        int i = 0;
        while (matcher.find()) {
            this.mArguments.add(matcher.group(1));
            sb.append(Pattern.quote(str.substring(i, matcher.start())));
            sb.append("(.+?)");
            i = matcher.end();
            z = false;
        }
        if (i < str.length()) {
            sb.append(Pattern.quote(str.substring(i)));
        }
        this.mPattern = Pattern.compile(sb.toString().replace(".*", "\\E.*\\Q"));
        this.mExactDeepLink = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean matches(@NonNull Uri uri) {
        ((NavDeepLinkBuilder) this).mContext = uri;
        Context context = ((NavDeepLinkBuilder) this).mContext;
        if (context instanceof Activity) {
            ((NavDeepLinkBuilder) this).mIntent = new Intent(context, context.getClass());
        } else {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(((NavDeepLinkBuilder) this).mContext.getPackageName());
            if (launchIntentForPackage == null) {
                launchIntentForPackage = new Intent();
            }
            ((NavDeepLinkBuilder) this).mIntent = launchIntentForPackage;
        }
        ((NavDeepLinkBuilder) this).mIntent.addFlags(268468224);
        return;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.Nullable
    android.os.Bundle getMatchingArguments(@android.support.annotation.NonNull android.net.Uri r0, @android.support.annotation.NonNull java.util.Map<java.lang.String, androidx.navigation.NavArgument> r1) {
        /*
            r-1 = this;
            boolean r0 = r1.mExactDeepLink
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavDeepLink.getMatchingArguments(android.net.Uri, java.util.Map):android.os.Bundle");
    }

    boolean isExactDeepLink() {
        return this.mExactDeepLink;
    }
}
