package com.huawei.secure.android.common.xml;

import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class XmlNewPullParserSecurity {
    public static XmlPullParser getInstance() throws XmlPullParserException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", false);
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        return newPullParser;
    }
}
