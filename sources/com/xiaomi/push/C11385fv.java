package com.xiaomi.push;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.push.fv */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11385fv {

    /* renamed from: a */
    private static C11385fv f22386a;

    /* renamed from: a */
    private Map<String, Object> f22387a = new ConcurrentHashMap();

    /* renamed from: b */
    private Map<String, Object> f22388b = new ConcurrentHashMap();

    /* renamed from: a */
    public static synchronized C11385fv m3765a() {
        C11385fv c11385fv;
        synchronized (C11385fv.class) {
            if (f22386a == null) {
                f22386a = new C11385fv();
            }
            c11385fv = f22386a;
        }
        return c11385fv;
    }

    /* renamed from: a */
    protected void m3764a() {
        try {
            for (ClassLoader classLoader : m3763a()) {
                Enumeration<URL> resources = classLoader.getResources("META-INF/smack.providers");
                while (resources.hasMoreElements()) {
                    InputStream openStream = resources.nextElement().openStream();
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                    newPullParser.setInput(openStream, "UTF-8");
                    int eventType = newPullParser.getEventType();
                    do {
                        if (eventType == 2) {
                            if (newPullParser.getName().equals("iqProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                String nextText = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                String nextText2 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                String nextText3 = newPullParser.nextText();
                                String m3761a = m3761a(nextText, nextText2);
                                if (!this.f22388b.containsKey(m3761a)) {
                                    try {
                                        Class<?> cls = Class.forName(nextText3);
                                        if (InterfaceC11383ft.class.isAssignableFrom(cls)) {
                                            this.f22388b.put(m3761a, cls.newInstance());
                                        } else if (C11372fm.class.isAssignableFrom(cls)) {
                                            this.f22388b.put(m3761a, cls);
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (newPullParser.getName().equals("extensionProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                String nextText4 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                String nextText5 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                String nextText6 = newPullParser.nextText();
                                String m3761a2 = m3761a(nextText4, nextText5);
                                if (!this.f22387a.containsKey(m3761a2)) {
                                    try {
                                        Class<?> cls2 = Class.forName(nextText6);
                                        if (InterfaceC11384fu.class.isAssignableFrom(cls2)) {
                                            this.f22387a.put(m3761a2, cls2.newInstance());
                                        } else if (InterfaceC11376fp.class.isAssignableFrom(cls2)) {
                                            this.f22387a.put(m3761a2, cls2);
                                        }
                                    } catch (ClassNotFoundException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                        }
                        eventType = newPullParser.next();
                    } while (eventType != 1);
                    try {
                        openStream.close();
                    } catch (Exception unused) {
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    /* renamed from: a */
    public Object m3762a(String str, String str2) {
        return this.f22387a.get(m3761a(str, str2));
    }

    /* renamed from: a */
    public void m3760a(String str, String str2, Object obj) {
        if (!(obj instanceof InterfaceC11384fu) && !(obj instanceof Class)) {
            throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
        }
        this.f22387a.put(m3761a(str, str2), obj);
    }

    /* renamed from: a */
    private String m3761a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(str);
        sb.append("/>");
        if (str != null) {
            sb.append("<");
            sb.append(str2);
            sb.append("/>");
        }
        return sb.toString();
    }

    /* renamed from: a */
    private ClassLoader[] m3763a() {
        ClassLoader[] classLoaderArr = {C11385fv.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (ClassLoader classLoader : classLoaderArr) {
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    private C11385fv() {
        m3764a();
    }
}
