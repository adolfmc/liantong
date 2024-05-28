package cn.sharesdk.framework.utils;

import android.text.TextUtils;
import android.util.Xml;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class XmlParser {
    /* renamed from: a */
    public HashMap<String, Object> m21665a(String str) throws Throwable {
        C1786a c1786a = new C1786a();
        Xml.parse(str, c1786a);
        return c1786a.m21664a();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: XmlParser.java */
    /* renamed from: cn.sharesdk.framework.utils.n$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C1786a extends DefaultHandler {

        /* renamed from: a */
        private HashMap<String, Object> f2981a = new HashMap<>();

        /* renamed from: b */
        private HashMap<String, Object> f2982b;

        /* renamed from: a */
        public HashMap<String, Object> m21664a() {
            return this.f2981a;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (this.f2982b != null) {
                HashMap<String, Object> hashMap = new HashMap<>();
                this.f2982b.put(str2, hashMap);
                this.f2982b = hashMap;
            } else {
                this.f2982b = new HashMap<>();
                this.f2981a.put(str2, this.f2982b);
            }
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                this.f2982b.put(attributes.getLocalName(i), attributes.getValue(i));
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            this.f2982b = null;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i, int i2) {
            HashMap<String, Object> hashMap;
            String trim = String.valueOf(cArr, i, i2).trim();
            if (TextUtils.isEmpty(trim) || (hashMap = this.f2982b) == null) {
                return;
            }
            hashMap.put("value", trim);
        }
    }
}
