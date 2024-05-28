package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11372fm;
import com.xiaomi.push.C11377fq;
import com.xiaomi.push.C11381fs;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.C11561ar;
import com.xiaomi.push.service.C11599i;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.fw */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11386fw {

    /* renamed from: a */
    private static XmlPullParser f22389a;

    /* renamed from: a */
    public static AbstractC11375fo m3758a(XmlPullParser xmlPullParser) {
        String str;
        boolean z = false;
        String str2 = null;
        if ("1".equals(xmlPullParser.getAttributeValue("", "s"))) {
            String attributeValue = xmlPullParser.getAttributeValue("", "chid");
            String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
            String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
            String attributeValue4 = xmlPullParser.getAttributeValue("", "to");
            String attributeValue5 = xmlPullParser.getAttributeValue("", "type");
            C11545am.C11547b m2681a = C11545am.m2692a().m2681a(attributeValue, attributeValue4);
            if (m2681a == null) {
                m2681a = C11545am.m2692a().m2681a(attributeValue, attributeValue3);
            }
            if (m2681a != null) {
                AbstractC11375fo abstractC11375fo = null;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2) {
                        if (!"s".equals(xmlPullParser.getName())) {
                            throw new C11368fi("error while receiving a encrypted message with wrong format");
                        }
                        if (xmlPullParser.next() != 4) {
                            throw new C11368fi("error while receiving a encrypted message with wrong format");
                        }
                        String text = xmlPullParser.getText();
                        if ("5".equals(attributeValue) || "6".equals(attributeValue)) {
                            C11374fn c11374fn = new C11374fn();
                            c11374fn.m3786l(attributeValue);
                            c11374fn.m3814b(true);
                            c11374fn.m3782n(attributeValue3);
                            c11374fn.m3784m(attributeValue4);
                            c11374fn.m3788k(attributeValue2);
                            c11374fn.m3806f(attributeValue5);
                            String[] strArr = null;
                            C11371fl c11371fl = new C11371fl("s", null, strArr, strArr);
                            c11371fl.m3828a(text);
                            c11374fn.m3797a(c11371fl);
                            return c11374fn;
                        }
                        m3752a(C11561ar.m2645a(C11561ar.m2648a(m2681a.f23540h, attributeValue2), text));
                        f22389a.next();
                        abstractC11375fo = m3758a(f22389a);
                    } else if (next == 3 && xmlPullParser.getName().equals("message")) {
                        z = true;
                    }
                }
                if (abstractC11375fo != null) {
                    return abstractC11375fo;
                }
                throw new C11368fi("error while receiving a encrypted message with wrong format");
            }
            throw new C11368fi("the channel id is wrong while receiving a encrypted message");
        }
        C11374fn c11374fn2 = new C11374fn();
        String attributeValue6 = xmlPullParser.getAttributeValue("", "id");
        if (attributeValue6 == null) {
            attributeValue6 = "ID_NOT_AVAILABLE";
        }
        c11374fn2.m3788k(attributeValue6);
        c11374fn2.m3784m(xmlPullParser.getAttributeValue("", "to"));
        c11374fn2.m3782n(xmlPullParser.getAttributeValue("", "from"));
        c11374fn2.m3786l(xmlPullParser.getAttributeValue("", "chid"));
        c11374fn2.m3819a(xmlPullParser.getAttributeValue("", "appid"));
        try {
            str = xmlPullParser.getAttributeValue("", "transient");
        } catch (Exception unused) {
            str = null;
        }
        try {
            String attributeValue7 = xmlPullParser.getAttributeValue("", "seq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                c11374fn2.m3815b(attributeValue7);
            }
        } catch (Exception unused2) {
        }
        try {
            String attributeValue8 = xmlPullParser.getAttributeValue("", "mseq");
            if (!TextUtils.isEmpty(attributeValue8)) {
                c11374fn2.m3812c(attributeValue8);
            }
        } catch (Exception unused3) {
        }
        try {
            String attributeValue9 = xmlPullParser.getAttributeValue("", "fseq");
            if (!TextUtils.isEmpty(attributeValue9)) {
                c11374fn2.m3810d(attributeValue9);
            }
        } catch (Exception unused4) {
        }
        try {
            String attributeValue10 = xmlPullParser.getAttributeValue("", "status");
            if (!TextUtils.isEmpty(attributeValue10)) {
                c11374fn2.m3808e(attributeValue10);
            }
        } catch (Exception unused5) {
        }
        c11374fn2.m3817a(!TextUtils.isEmpty(str) && str.equalsIgnoreCase("true"));
        c11374fn2.m3806f(xmlPullParser.getAttributeValue("", "type"));
        String m3751b = m3751b(xmlPullParser);
        if (m3751b != null && !"".equals(m3751b.trim())) {
            c11374fn2.m3800j(m3751b);
        } else {
            AbstractC11375fo.m3778q();
        }
        while (!z) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (TextUtils.isEmpty(namespace)) {
                    namespace = "xm";
                }
                if (name.equals("subject")) {
                    m3751b(xmlPullParser);
                    c11374fn2.m3804g(m3754a(xmlPullParser));
                } else if (name.equals("body")) {
                    String attributeValue11 = xmlPullParser.getAttributeValue("", "encode");
                    String m3754a = m3754a(xmlPullParser);
                    if (!TextUtils.isEmpty(attributeValue11)) {
                        c11374fn2.m3818a(m3754a, attributeValue11);
                    } else {
                        c11374fn2.m3802h(m3754a);
                    }
                } else if (name.equals("thread")) {
                    if (str2 == null) {
                        str2 = xmlPullParser.nextText();
                    }
                } else if (name.equals("error")) {
                    c11374fn2.m3796a(m3755a(xmlPullParser));
                } else {
                    c11374fn2.m3797a(m3759a(name, namespace, xmlPullParser));
                }
            } else if (next2 == 3 && xmlPullParser.getName().equals("message")) {
                z = true;
            }
        }
        c11374fn2.m3801i(str2);
        return c11374fn2;
    }

    /* renamed from: a */
    private static void m3752a(byte[] bArr) {
        if (f22389a == null) {
            try {
                f22389a = XmlPullParserFactory.newInstance().newPullParser();
                f22389a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        f22389a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }

    /* renamed from: a */
    private static String m3754a(XmlPullParser xmlPullParser) {
        String str = "";
        int depth = xmlPullParser.getDepth();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    /* renamed from: a */
    public static C11377fq m3757a(XmlPullParser xmlPullParser) {
        C11377fq.EnumC11379b enumC11379b = C11377fq.EnumC11379b.available;
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        if (attributeValue != null && !attributeValue.equals("")) {
            try {
                enumC11379b = C11377fq.EnumC11379b.valueOf(attributeValue);
            } catch (IllegalArgumentException unused) {
                System.err.println("Found invalid presence type " + attributeValue);
            }
        }
        C11377fq c11377fq = new C11377fq(enumC11379b);
        c11377fq.m3784m(xmlPullParser.getAttributeValue("", "to"));
        c11377fq.m3782n(xmlPullParser.getAttributeValue("", "from"));
        c11377fq.m3786l(xmlPullParser.getAttributeValue("", "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        c11377fq.m3788k(attributeValue2);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("status")) {
                    c11377fq.m3771a(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        c11377fq.m3774a(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException unused2) {
                    } catch (IllegalArgumentException unused3) {
                        c11377fq.m3774a(0);
                    }
                } else if (name.equals("show")) {
                    String nextText = xmlPullParser.nextText();
                    try {
                        c11377fq.m3773a(C11377fq.EnumC11378a.valueOf(nextText));
                    } catch (IllegalArgumentException unused4) {
                        System.err.println("Found invalid presence mode " + nextText);
                    }
                } else if (name.equals("error")) {
                    c11377fq.m3796a(m3755a(xmlPullParser));
                } else {
                    c11377fq.m3797a(m3759a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                z = true;
            }
        }
        return c11377fq;
    }

    /* renamed from: a */
    public static C11372fm m3753a(XmlPullParser xmlPullParser, AbstractC11356fa abstractC11356fa) {
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "to");
        String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
        String attributeValue4 = xmlPullParser.getAttributeValue("", "chid");
        C11372fm.C11373a m3820a = C11372fm.C11373a.m3820a(xmlPullParser.getAttributeValue("", "type"));
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            hashMap.put(attributeName, xmlPullParser.getAttributeValue("", attributeName));
        }
        C11372fm c11372fm = null;
        C11381fs c11381fs = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("error")) {
                    c11381fs = m3755a(xmlPullParser);
                } else {
                    c11372fm = new C11372fm();
                    c11372fm.m3797a(m3759a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                z = true;
            }
        }
        if (c11372fm == null) {
            if (C11372fm.C11373a.f22301a == m3820a || C11372fm.C11373a.f22302b == m3820a) {
                C11372fm c11372fm2 = new C11372fm() { // from class: com.xiaomi.push.fw.1
                    @Override // com.xiaomi.push.C11372fm
                    /* renamed from: b */
                    public String mo3750b() {
                        return null;
                    }
                };
                c11372fm2.m3788k(attributeValue);
                c11372fm2.m3784m(attributeValue3);
                c11372fm2.m3782n(attributeValue2);
                c11372fm2.m3822a(C11372fm.C11373a.f22304d);
                c11372fm2.m3786l(attributeValue4);
                c11372fm2.m3796a(new C11381fs(C11381fs.C11382a.f22365e));
                abstractC11356fa.mo3888a(c11372fm2);
                AbstractC11049b.m5268d("iq usage error. send packet in packet parser.");
                return null;
            }
            c11372fm = new C11372fm() { // from class: com.xiaomi.push.fw.2
                @Override // com.xiaomi.push.C11372fm
                /* renamed from: b */
                public String mo3750b() {
                    return null;
                }
            };
        }
        c11372fm.m3788k(attributeValue);
        c11372fm.m3784m(attributeValue2);
        c11372fm.m3786l(attributeValue4);
        c11372fm.m3782n(attributeValue3);
        c11372fm.m3822a(m3820a);
        c11372fm.m3796a(c11381fs);
        c11372fm.m3821a(hashMap);
        return c11372fm;
    }

    /* renamed from: a */
    public static C11380fr m3756a(XmlPullParser xmlPullParser) {
        C11380fr c11380fr = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                c11380fr = new C11380fr(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals("error")) {
                z = true;
            }
        }
        return c11380fr;
    }

    /* renamed from: a */
    public static C11381fs m3755a(XmlPullParser xmlPullParser) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        String str = "-1";
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            if (xmlPullParser.getAttributeName(i).equals("code")) {
                str = xmlPullParser.getAttributeValue("", "code");
            }
            if (xmlPullParser.getAttributeName(i).equals("type")) {
                str2 = xmlPullParser.getAttributeValue("", "type");
            }
            if (xmlPullParser.getAttributeName(i).equals("reason")) {
                str3 = xmlPullParser.getAttributeValue("", "reason");
            }
        }
        String str4 = null;
        String str5 = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("text")) {
                    str5 = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        str4 = name;
                    } else {
                        arrayList.add(m3759a(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("error")) {
                    z = true;
                }
            } else if (next == 4) {
                str5 = xmlPullParser.getText();
            }
        }
        return new C11381fs(Integer.parseInt(str), str2 == null ? "cancel" : str2, str3, str4, str5, arrayList);
    }

    /* renamed from: a */
    public static C11371fl m3759a(String str, String str2, XmlPullParser xmlPullParser) {
        Object m3762a = C11385fv.m3765a().m3762a("all", "xm:chat");
        if (m3762a == null || !(m3762a instanceof C11599i)) {
            return null;
        }
        return ((C11599i) m3762a).m2526b(xmlPullParser);
    }

    /* renamed from: b */
    private static String m3751b(XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                return xmlPullParser.getAttributeValue(i);
            }
        }
        return null;
    }
}
