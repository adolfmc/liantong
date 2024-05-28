package com.xiaomi.push;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.fg */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C11363fg {

    /* renamed from: a */
    private static int f22267a = 5000;

    /* renamed from: a */
    private static Vector<String> f22268a = new Vector<>();

    /* renamed from: b */
    private static int f22269b = 330000;

    /* renamed from: c */
    private static int f22270c = 600000;

    /* renamed from: d */
    private static int f22271d = 330000;

    /* renamed from: a */
    public static String m3859a() {
        return "3.1.0";
    }

    static {
        try {
            for (ClassLoader classLoader : m3858a()) {
                Enumeration<URL> resources = classLoader.getResources("META-INF/smack-config.xml");
                while (resources.hasMoreElements()) {
                    InputStream inputStream = null;
                    try {
                        inputStream = resources.nextElement().openStream();
                        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                        newPullParser.setInput(inputStream, "UTF-8");
                        int eventType = newPullParser.getEventType();
                        do {
                            if (eventType == 2) {
                                if (newPullParser.getName().equals("className")) {
                                    m3857a(newPullParser);
                                } else if (newPullParser.getName().equals("packetReplyTimeout")) {
                                    f22267a = m3856a(newPullParser, f22267a);
                                } else if (newPullParser.getName().equals("keepAliveInterval")) {
                                    f22269b = m3856a(newPullParser, f22269b);
                                } else if (newPullParser.getName().equals("mechName")) {
                                    f22268a.add(newPullParser.nextText());
                                }
                            }
                            eventType = newPullParser.next();
                        } while (eventType != 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        inputStream.close();
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private C11363fg() {
    }

    /* renamed from: a */
    public static int m3860a() {
        return f22269b;
    }

    /* renamed from: b */
    public static int m3855b() {
        return f22270c;
    }

    /* renamed from: a */
    private static void m3857a(XmlPullParser xmlPullParser) {
        String nextText = xmlPullParser.nextText();
        try {
            Class.forName(nextText);
        } catch (ClassNotFoundException unused) {
            PrintStream printStream = System.err;
            printStream.println("Error! A startup class specified in smack-config.xml could not be loaded: " + nextText);
        }
    }

    /* renamed from: a */
    private static int m3856a(XmlPullParser xmlPullParser, int i) {
        try {
            return Integer.parseInt(xmlPullParser.nextText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return i;
        }
    }

    /* renamed from: a */
    private static ClassLoader[] m3858a() {
        ClassLoader[] classLoaderArr = {C11363fg.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }
}
