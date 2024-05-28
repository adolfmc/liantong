package org.p415a.p445f;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.p415a.p424c.C12581ac;
import org.p415a.p424c.C12587ai;
import org.p415a.p424c.C12588aj;
import org.p415a.p424c.C12591am;
import org.p415a.p424c.C12592b;
import org.p415a.p424c.C12624u;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12935g {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C12587ai m461a(C12592b c12592b) {
        if (c12592b.m1574b() == 12) {
            return (C12587ai) c12592b.m1573c();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C12592b m462a(InputStream inputStream) {
        return inputStream instanceof C12592b ? (C12592b) inputStream : new C12592b(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m460a(C12592b c12592b, List list, List list2, List list3) {
        while (true) {
            if (c12592b.m1574b() != 13 && c12592b.m1574b() != 17) {
                return;
            }
            C12624u m1573c = c12592b.m1573c();
            if (m1573c instanceof C12591am) {
                list.add((C12591am) m1573c);
            } else {
                list.add(new C12952w(((C12588aj) m1573c).m1581a()));
            }
            list2.add(m461a(c12592b));
            list3.add(m459b(c12592b));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static List m459b(C12592b c12592b) {
        try {
            ArrayList arrayList = new ArrayList();
            while (c12592b.m1574b() == 2) {
                arrayList.add(new C12950u((C12581ac) c12592b.m1573c(), m461a(c12592b)));
            }
            return arrayList;
        } catch (C12934f e) {
            throw new IOException("can't create signature object: " + e.getMessage() + ", cause: " + e.m463a().toString());
        }
    }
}
