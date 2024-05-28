package androidx.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NavigationRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class NavInflater {
    private static final String APPLICATION_ID_PLACEHOLDER = "${applicationId}";
    private static final String TAG_ACTION = "action";
    private static final String TAG_ARGUMENT = "argument";
    private static final String TAG_DEEP_LINK = "deepLink";
    private static final String TAG_INCLUDE = "include";
    private static final ThreadLocal<TypedValue> sTmpValue = new ThreadLocal<>();
    private Context mContext;
    private NavigatorProvider mNavigatorProvider;

    public NavInflater(@NonNull Context context, @NonNull NavigatorProvider navigatorProvider) {
        this.mContext = context;
        this.mNavigatorProvider = navigatorProvider;
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.NonNull
    private androidx.navigation.NavDestination inflate(@android.support.annotation.NonNull android.content.res.Resources r-2, @android.support.annotation.NonNull android.content.res.XmlResourceParser r-1, @android.support.annotation.NonNull android.util.AttributeSet r0, int r1) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r-3 = this;
            r1.<init>()
            r0 = -1
            r1.mPopUpTo = r0
            r1.mEnterAnim = r0
            r1.mExitAnim = r0
            r1.mPopEnterAnim = r0
            r1.mPopExitAnim = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavInflater.inflate(android.content.res.Resources, android.content.res.XmlResourceParser, android.util.AttributeSet, int):androidx.navigation.NavDestination");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    private void inflateAction(@android.support.annotation.NonNull android.content.res.Resources r-3, @android.support.annotation.NonNull androidx.navigation.NavDestination r-2, @android.support.annotation.NonNull android.util.AttributeSet r-1, android.content.res.XmlResourceParser r0, int r1) throws java.io.IOException, org.xmlpull.v1.XmlPullParserException {
        /*
            r-4 = this;
            r1.<init>()
            r0 = -1
            r1.mPopUpTo = r0
            r1.mEnterAnim = r0
            r1.mExitAnim = r0
            r1.mPopEnterAnim = r0
            r1.mPopExitAnim = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavInflater.inflateAction(android.content.res.Resources, androidx.navigation.NavDestination, android.util.AttributeSet, android.content.res.XmlResourceParser, int):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    @android.support.annotation.NonNull
    private androidx.navigation.NavArgument inflateArgument(@android.support.annotation.NonNull android.content.res.TypedArray r-1, @android.support.annotation.NonNull android.content.res.Resources r0, int r1) throws org.xmlpull.v1.XmlPullParserException {
        /*
            r-2 = this;
            r1.<init>()
            r0 = -1
            r1.mPopUpTo = r0
            r1.mEnterAnim = r0
            r1.mExitAnim = r0
            r1.mPopEnterAnim = r0
            r1.mPopExitAnim = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavInflater.inflateArgument(android.content.res.TypedArray, android.content.res.Resources, int):androidx.navigation.NavArgument");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    private void inflateArgumentForBundle(@android.support.annotation.NonNull android.content.res.Resources r-2, @android.support.annotation.NonNull android.os.Bundle r-1, @android.support.annotation.NonNull android.util.AttributeSet r0, int r1) throws org.xmlpull.v1.XmlPullParserException {
        /*
            r-3 = this;
            r1.<init>()
            r0 = -1
            r1.mPopUpTo = r0
            r1.mEnterAnim = r0
            r1.mExitAnim = r0
            r1.mPopEnterAnim = r0
            r1.mPopExitAnim = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavInflater.inflateArgumentForBundle(android.content.res.Resources, android.os.Bundle, android.util.AttributeSet, int):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    private void inflateArgumentForDestination(@android.support.annotation.NonNull android.content.res.Resources r-2, @android.support.annotation.NonNull androidx.navigation.NavDestination r-1, @android.support.annotation.NonNull android.util.AttributeSet r0, int r1) throws org.xmlpull.v1.XmlPullParserException {
        /*
            r-3 = this;
            r1.<init>()
            r0 = -1
            r1.mPopUpTo = r0
            r1.mEnterAnim = r0
            r1.mExitAnim = r0
            r1.mPopEnterAnim = r0
            r1.mPopExitAnim = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavInflater.inflateArgumentForDestination(android.content.res.Resources, androidx.navigation.NavDestination, android.util.AttributeSet, int):void");
    }

    /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
        java.lang.ArrayIndexOutOfBoundsException
        */
    private void inflateDeepLink(@android.support.annotation.NonNull android.content.res.Resources r-1, @android.support.annotation.NonNull androidx.navigation.NavDestination r0, @android.support.annotation.NonNull android.util.AttributeSet r1) {
        /*
            r-2 = this;
            r1.<init>()
            r0 = -1
            r1.mPopUpTo = r0
            r1.mEnterAnim = r0
            r1.mExitAnim = r0
            r1.mPopEnterAnim = r0
            r1.mPopExitAnim = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavInflater.inflateDeepLink(android.content.res.Resources, androidx.navigation.NavDestination, android.util.AttributeSet):void");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.navigation.NavOptions$Builder, java.lang.Object] */
    @NonNull
    @SuppressLint({"ResourceType"})
    public NavGraph inflate(@NavigationRes int obj) {
        ?? obj2 = new Object();
        obj2.mPopUpTo = -1;
        obj2.mEnterAnim = -1;
        obj2.mExitAnim = -1;
        obj2.mPopEnterAnim = -1;
        obj2.mPopExitAnim = -1;
        return;
    }

    private static NavType checkNavType(TypedValue typedValue, NavType navType, NavType navType2, String str, String str2) throws XmlPullParserException {
        if (navType == null || navType == navType2) {
            return navType != null ? navType : navType2;
        }
        throw new XmlPullParserException("Type is " + str + " but found " + str2 + ": " + typedValue.data);
    }
}
