package com.xiaomi.push;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.xiaomi.push.ew */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11348ew {

    /* renamed from: a */
    private XmlPullParser f22216a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C11348ew() {
        try {
            this.f22216a = XmlPullParserFactory.newInstance().newPullParser();
            this.f22216a.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        } catch (XmlPullParserException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public AbstractC11375fo m3916a(byte[] bArr, AbstractC11356fa abstractC11356fa) {
        this.f22216a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
        this.f22216a.next();
        int eventType = this.f22216a.getEventType();
        String name = this.f22216a.getName();
        if (eventType == 2) {
            if (name.equals("message")) {
                return C11386fw.m3758a(this.f22216a);
            }
            if (name.equals("iq")) {
                return C11386fw.m3753a(this.f22216a, abstractC11356fa);
            }
            if (name.equals("presence")) {
                return C11386fw.m3757a(this.f22216a);
            }
            if (this.f22216a.getName().equals("stream")) {
                return null;
            }
            if (this.f22216a.getName().equals("error")) {
                throw new C11368fi(C11386fw.m3756a(this.f22216a));
            }
            if (this.f22216a.getName().equals("warning")) {
                this.f22216a.next();
                this.f22216a.getName().equals("multi-login");
                return null;
            }
            this.f22216a.getName().equals("bind");
            return null;
        }
        return null;
    }
}
