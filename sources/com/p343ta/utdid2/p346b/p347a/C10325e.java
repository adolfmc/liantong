package com.p343ta.utdid2.p346b.p347a;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.b.a.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C10325e {
    /* renamed from: a */
    public static final void m6395a(Map map, OutputStream outputStream) throws XmlPullParserException, IOException {
        C10317a c10317a = new C10317a();
        c10317a.setOutput(outputStream, "utf-8");
        c10317a.startDocument(null, true);
        c10317a.setFeature(C10317a.m6430d(), true);
        m6394a(map, (String) null, (XmlSerializer) c10317a);
        c10317a.endDocument();
    }

    /* renamed from: a */
    public static final void m6394a(Map map, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (map == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "map");
        if (str != null) {
            xmlSerializer.attribute(null, "name", str);
        }
        for (Map.Entry entry : map.entrySet()) {
            m6397a(entry.getValue(), (String) entry.getKey(), xmlSerializer);
        }
        xmlSerializer.endTag(null, "map");
    }

    /* renamed from: a */
    public static final void m6396a(List list, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (list == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "list");
        if (str != null) {
            xmlSerializer.attribute(null, "name", str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            m6397a(list.get(i), (String) null, xmlSerializer);
        }
        xmlSerializer.endTag(null, "list");
    }

    /* renamed from: a */
    public static final void m6389a(byte[] bArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (bArr == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "byte-array");
        if (str != null) {
            xmlSerializer.attribute(null, "name", str);
        }
        xmlSerializer.attribute(null, "num", Integer.toString(bArr.length));
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b >> 4;
            sb.append(i >= 10 ? (i + 97) - 10 : i + 48);
            int i2 = b & 255;
            sb.append(i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
        }
        xmlSerializer.text(sb.toString());
        xmlSerializer.endTag(null, "byte-array");
    }

    /* renamed from: a */
    public static final void m6388a(int[] iArr, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        if (iArr == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "int-array");
        if (str != null) {
            xmlSerializer.attribute(null, "name", str);
        }
        xmlSerializer.attribute(null, "num", Integer.toString(iArr.length));
        for (int i : iArr) {
            xmlSerializer.startTag(null, "item");
            xmlSerializer.attribute(null, "value", Integer.toString(i));
            xmlSerializer.endTag(null, "item");
        }
        xmlSerializer.endTag(null, "int-array");
    }

    /* renamed from: a */
    public static final void m6397a(Object obj, String str, XmlSerializer xmlSerializer) throws XmlPullParserException, IOException {
        String str2;
        if (obj == null) {
            xmlSerializer.startTag(null, "null");
            if (str != null) {
                xmlSerializer.attribute(null, "name", str);
            }
            xmlSerializer.endTag(null, "null");
        } else if (obj instanceof String) {
            xmlSerializer.startTag(null, "string");
            if (str != null) {
                xmlSerializer.attribute(null, "name", str);
            }
            xmlSerializer.text(obj.toString());
            xmlSerializer.endTag(null, "string");
        } else {
            if (obj instanceof Integer) {
                str2 = "int";
            } else if (obj instanceof Long) {
                str2 = "long";
            } else if (obj instanceof Float) {
                str2 = "float";
            } else if (obj instanceof Double) {
                str2 = "double";
            } else if (!(obj instanceof Boolean)) {
                if (obj instanceof byte[]) {
                    m6389a((byte[]) obj, str, xmlSerializer);
                    return;
                } else if (obj instanceof int[]) {
                    m6388a((int[]) obj, str, xmlSerializer);
                    return;
                } else if (obj instanceof Map) {
                    m6394a((Map) obj, str, xmlSerializer);
                    return;
                } else if (obj instanceof List) {
                    m6396a((List) obj, str, xmlSerializer);
                    return;
                } else if (obj instanceof CharSequence) {
                    xmlSerializer.startTag(null, "string");
                    if (str != null) {
                        xmlSerializer.attribute(null, "name", str);
                    }
                    xmlSerializer.text(obj.toString());
                    xmlSerializer.endTag(null, "string");
                    return;
                } else {
                    throw new RuntimeException("writeValueXml: unable to write value " + obj);
                }
            } else {
                str2 = "boolean";
            }
            xmlSerializer.startTag(null, str2);
            if (str != null) {
                xmlSerializer.attribute(null, "name", str);
            }
            xmlSerializer.attribute(null, "value", obj.toString());
            xmlSerializer.endTag(null, str2);
        }
    }

    /* renamed from: a */
    public static final HashMap m6398a(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, null);
        return (HashMap) m6390a(newPullParser, new String[1]);
    }

    /* renamed from: a */
    public static final HashMap m6392a(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        HashMap hashMap = new HashMap();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                Object m6387b = m6387b(xmlPullParser, strArr);
                if (strArr[0] != null) {
                    hashMap.put(strArr[0], m6387b);
                } else {
                    throw new XmlPullParserException("Map value without name attribute: " + xmlPullParser.getName());
                }
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return hashMap;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException("Document ended before " + str + " end tag");
    }

    /* renamed from: a */
    public static final ArrayList m6393a(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                arrayList.add(m6387b(xmlPullParser, strArr));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return arrayList;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException("Document ended before " + str + " end tag");
    }

    /* renamed from: a */
    public static final int[] m6391a(XmlPullParser xmlPullParser, String str, String[] strArr) throws XmlPullParserException, IOException {
        try {
            int[] iArr = new int[Integer.parseInt(xmlPullParser.getAttributeValue(null, "num"))];
            int i = 0;
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("item")) {
                        try {
                            iArr[i] = Integer.parseInt(xmlPullParser.getAttributeValue(null, "value"));
                        } catch (NullPointerException unused) {
                            throw new XmlPullParserException("Need value attribute in item");
                        } catch (NumberFormatException unused2) {
                            throw new XmlPullParserException("Not a number in value attribute in item");
                        }
                    } else {
                        throw new XmlPullParserException("Expected item tag at: " + xmlPullParser.getName());
                    }
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return iArr;
                    }
                    if (!xmlPullParser.getName().equals("item")) {
                        throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
                    }
                    i++;
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException("Document ended before " + str + " end tag");
        } catch (NullPointerException unused3) {
            throw new XmlPullParserException("Need num attribute in byte-array");
        } catch (NumberFormatException unused4) {
            throw new XmlPullParserException("Not a number in num attribute in byte-array");
        }
    }

    /* renamed from: a */
    public static final Object m6390a(XmlPullParser xmlPullParser, String[] strArr) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 2) {
            if (eventType == 3) {
                throw new XmlPullParserException("Unexpected end tag at: " + xmlPullParser.getName());
            } else if (eventType == 4) {
                throw new XmlPullParserException("Unexpected text: " + xmlPullParser.getText());
            } else {
                try {
                    eventType = xmlPullParser.next();
                    if (eventType == 1) {
                        throw new XmlPullParserException("Unexpected end of document");
                    }
                } catch (Exception unused) {
                    throw new XmlPullParserException("Unexpected call next(): " + xmlPullParser.getName());
                }
            }
        }
        return m6387b(xmlPullParser, strArr);
    }

    /* renamed from: b */
    private static Object m6387b(XmlPullParser xmlPullParser, String[] strArr) throws XmlPullParserException, IOException {
        int next;
        Object obj = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "name");
        String name = xmlPullParser.getName();
        if (!name.equals("null")) {
            if (name.equals("string")) {
                String str = "";
                while (true) {
                    int next2 = xmlPullParser.next();
                    if (next2 == 1) {
                        throw new XmlPullParserException("Unexpected end of document in <string>");
                    }
                    if (next2 == 3) {
                        if (xmlPullParser.getName().equals("string")) {
                            strArr[0] = attributeValue;
                            return str;
                        }
                        throw new XmlPullParserException("Unexpected end tag in <string>: " + xmlPullParser.getName());
                    } else if (next2 == 4) {
                        str = str + xmlPullParser.getText();
                    } else if (next2 == 2) {
                        throw new XmlPullParserException("Unexpected start tag in <string>: " + xmlPullParser.getName());
                    }
                }
            } else if (name.equals("int")) {
                obj = Integer.valueOf(Integer.parseInt(xmlPullParser.getAttributeValue(null, "value")));
            } else if (name.equals("long")) {
                obj = Long.valueOf(xmlPullParser.getAttributeValue(null, "value"));
            } else if (name.equals("float")) {
                obj = Float.valueOf(xmlPullParser.getAttributeValue(null, "value"));
            } else if (name.equals("double")) {
                obj = Double.valueOf(xmlPullParser.getAttributeValue(null, "value"));
            } else if (name.equals("boolean")) {
                obj = Boolean.valueOf(xmlPullParser.getAttributeValue(null, "value"));
            } else if (name.equals("int-array")) {
                xmlPullParser.next();
                int[] m6391a = m6391a(xmlPullParser, "int-array", strArr);
                strArr[0] = attributeValue;
                return m6391a;
            } else if (name.equals("map")) {
                xmlPullParser.next();
                HashMap m6392a = m6392a(xmlPullParser, "map", strArr);
                strArr[0] = attributeValue;
                return m6392a;
            } else if (name.equals("list")) {
                xmlPullParser.next();
                ArrayList m6393a = m6393a(xmlPullParser, "list", strArr);
                strArr[0] = attributeValue;
                return m6393a;
            } else {
                throw new XmlPullParserException("Unknown tag: " + name);
            }
        }
        do {
            next = xmlPullParser.next();
            if (next == 1) {
                throw new XmlPullParserException("Unexpected end of document in <" + name + ">");
            } else if (next == 3) {
                if (xmlPullParser.getName().equals(name)) {
                    strArr[0] = attributeValue;
                    return obj;
                }
                throw new XmlPullParserException("Unexpected end tag in <" + name + ">: " + xmlPullParser.getName());
            } else if (next == 4) {
                throw new XmlPullParserException("Unexpected text in <" + name + ">: " + xmlPullParser.getName());
            }
        } while (next != 2);
        throw new XmlPullParserException("Unexpected start tag in <" + name + ">: " + xmlPullParser.getName());
    }
}
