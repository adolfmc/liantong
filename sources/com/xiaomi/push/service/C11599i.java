package com.xiaomi.push.service;

import com.xiaomi.push.C11371fl;
import com.xiaomi.push.C11385fv;
import com.xiaomi.push.C11389fx;
import com.xiaomi.push.InterfaceC11384fu;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: com.xiaomi.push.service.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11599i implements InterfaceC11384fu {
    /* renamed from: a */
    public void m2528a() {
        C11385fv.m3765a().m3760a("all", "xm:chat", this);
    }

    /* renamed from: a */
    public static C11371fl m2527a(XmlPullParser xmlPullParser) {
        String[] strArr;
        String[] strArr2;
        String str;
        ArrayList arrayList;
        if (xmlPullParser.getEventType() != 2) {
            return null;
        }
        String name = xmlPullParser.getName();
        String namespace = xmlPullParser.getNamespace();
        if (xmlPullParser.getAttributeCount() > 0) {
            String[] strArr3 = new String[xmlPullParser.getAttributeCount()];
            String[] strArr4 = new String[xmlPullParser.getAttributeCount()];
            for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                strArr3[i] = xmlPullParser.getAttributeName(i);
                strArr4[i] = C11389fx.m3745b(xmlPullParser.getAttributeValue(i));
            }
            strArr = strArr3;
            str = null;
            arrayList = null;
            strArr2 = strArr4;
        } else {
            strArr = null;
            strArr2 = null;
            str = null;
            arrayList = null;
        }
        while (true) {
            int next = xmlPullParser.next();
            if (next == 3) {
                return new C11371fl(name, namespace, strArr, strArr2, str, arrayList);
            }
            if (next == 4) {
                str = xmlPullParser.getText().trim();
            } else if (next == 2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                C11371fl m2527a = m2527a(xmlPullParser);
                if (m2527a != null) {
                    arrayList.add(m2527a);
                }
            }
        }
    }

    /* renamed from: b */
    public C11371fl m2526b(XmlPullParser xmlPullParser) {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1 && eventType != 2) {
            eventType = xmlPullParser.next();
        }
        if (eventType == 2) {
            return m2527a(xmlPullParser);
        }
        return null;
    }
}
