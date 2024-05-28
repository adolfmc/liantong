package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.p052d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.C0679a;

/* renamed from: a.a.a.a.a.e.a.e.l.d.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0697e extends BufferedReader {

    /* renamed from: C */
    public static final String f2060C = "-----BEGIN ";

    /* renamed from: D */
    public static final String f2061D = "-----END ";

    public C0697e(Reader reader) {
        super(reader);
    }

    /* renamed from: a */
    public C0694b m22378a() {
        String readLine = readLine();
        while (readLine != null && !readLine.startsWith(f2060C)) {
            readLine = readLine();
        }
        if (readLine != null) {
            String substring = readLine.substring(11);
            int indexOf = substring.indexOf(45);
            String substring2 = substring.substring(0, indexOf);
            if (indexOf > 0) {
                return m22377a(substring2);
            }
            return null;
        }
        return null;
    }

    /* renamed from: a */
    private C0694b m22377a(String str) {
        String readLine;
        String str2 = f2061D + str;
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        while (true) {
            readLine = readLine();
            if (readLine == null) {
                break;
            } else if (readLine.indexOf(":") >= 0) {
                int indexOf = readLine.indexOf(58);
                arrayList.add(new C0693a(readLine.substring(0, indexOf), readLine.substring(indexOf + 1).trim()));
            } else if (readLine.indexOf(str2) != -1) {
                break;
            } else {
                stringBuffer.append(readLine.trim());
            }
        }
        if (readLine != null) {
            return new C0694b(str, arrayList, C0679a.m22437a(stringBuffer.toString()));
        }
        throw new IOException(str2 + " not found");
    }
}
