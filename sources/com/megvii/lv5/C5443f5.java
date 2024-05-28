package com.megvii.lv5;

import android.content.Context;
import java.io.File;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.f5 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5443f5 {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f5$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5444a {

        /* renamed from: a */
        public static final C5443f5 f12607a = new C5443f5();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x005f, code lost:
        if (android.text.TextUtils.isEmpty(r5) == false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00be, code lost:
        if (r6.equals("/data/data/" + r5 + "/files") != false) goto L30;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<java.lang.String> m13531a(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = "PM_LIST_PACKAGE"
            boolean r1 = r6.equals(r1)
            r2 = 0
            if (r1 == 0) goto L3a
            com.megvii.lv5.e5 r5 = com.megvii.lv5.C5424e5.C5425a.f12546a
            java.lang.String r6 = "pm list package -3"
            java.lang.String r5 = r5.m13551a(r6)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 == 0) goto L22
            goto L29
        L22:
            java.lang.String r1 = "package:"
            java.lang.String[] r5 = r5.split(r1)
            int r2 = r5.length
        L29:
            r6.append(r2)
            java.lang.String r5 = ""
            r6.append(r5)
            java.lang.String r5 = r6.toString()
        L35:
            r0.add(r5)
            goto Lc8
        L3a:
            java.lang.String r1 = "FIND_SU_BY_WHICH"
            boolean r1 = r6.equals(r1)
            if (r1 == 0) goto L62
            com.megvii.lv5.e5 r5 = com.megvii.lv5.C5424e5.C5425a.f12546a
            java.lang.String r6 = "/system/bin/which su"
            java.lang.String r6 = r5.m13551a(r6)
            java.lang.String r1 = "which su"
            java.lang.String r5 = r5.m13551a(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 != 0) goto L5b
            r0.add(r6)
            goto Lc8
        L5b:
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto Lc8
            goto L35
        L62:
            java.lang.String r1 = "CHECK_DATA_DIR_PARENT_ACCESS"
            boolean r1 = r6.equals(r1)
            if (r1 == 0) goto L74
            boolean r5 = r4.m13532a(r5)
            if (r5 == 0) goto Lc8
            java.lang.String r5 = "true"
            goto L35
        L74:
            java.lang.String r1 = "CHECK_APP_PATH_EQUALS"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto Lc8
            java.io.File r6 = r5.getFilesDir()
            java.lang.String r6 = r6.getAbsolutePath()
            java.lang.String r5 = r5.getPackageName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "/data/user/0/"
            r1.append(r3)
            r1.append(r5)
            java.lang.String r3 = "/files"
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto Lc0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "/data/data/"
            r1.append(r3)
            r1.append(r5)
            java.lang.String r5 = "/files"
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto Lc1
        Lc0:
            r2 = 1
        Lc1:
            if (r2 != 0) goto Lc8
            java.lang.String r5 = "false"
            r0.add(r5)
        Lc8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5443f5.m13531a(android.content.Context, java.lang.String):java.util.List");
    }

    /* renamed from: a */
    public final boolean m13532a(Context context) {
        File file;
        try {
            String str = "isDualAppEx: path = " + file.toString();
            boolean canRead = new File(context.getFilesDir().getAbsolutePath() + "/../..").canRead();
            String str2 = "isDualAppEx: canread = " + canRead;
            return canRead;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0190 A[Catch: Exception -> 0x01aa, TRY_LEAVE, TryCatch #4 {Exception -> 0x01aa, blocks: (B:64:0x0165, B:66:0x016f, B:70:0x0177, B:72:0x017d, B:75:0x0188, B:79:0x0190, B:81:0x019b), top: B:174:0x0165 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<java.lang.String> m13530a(android.content.Context r9, java.lang.String r10, java.util.List<java.lang.String> r11) {
        /*
            Method dump skipped, instructions count: 831
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5443f5.m13530a(android.content.Context, java.lang.String, java.util.List):java.util.List");
    }
}
