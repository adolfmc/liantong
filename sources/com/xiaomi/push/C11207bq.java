package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* renamed from: com.xiaomi.push.bq */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11207bq extends C11209bs {
    public C11207bq(String str, String str2, String[] strArr, String str3) {
        super(str, str2, strArr, str3);
    }

    /* renamed from: a */
    public static C11207bq m4690a(Context context, String str, int i) {
        AbstractC11049b.m5274b("delete  messages when db size is too bigger");
        String m4678a = C11213bw.m4683a(context).m4678a(str);
        if (TextUtils.isEmpty(m4678a)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("rowDataId in (select ");
        sb.append("rowDataId from " + m4678a);
        sb.append(" order by createTimeStamp asc");
        sb.append(" limit ?)");
        return new C11207bq(str, sb.toString(), new String[]{String.valueOf(i)}, "a job build to delete history message");
    }

    /* renamed from: a */
    private void m4691a(long j) {
        if (this.f21664a == null || this.f21664a.length <= 0) {
            return;
        }
        this.f21664a[0] = String.valueOf(j);
    }

    @Override // com.xiaomi.push.C11213bw.AbstractRunnableC11215a
    /* renamed from: a */
    public void mo4672a(Context context, Object obj) {
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            long m4609a = C11226ca.m4609a(mo4668a());
            long j = C11205bo.f21630a;
            if (m4609a > j) {
                long j2 = (long) ((((m4609a - j) * 1.2d) / j) * longValue);
                m4691a(j2);
                C11201bn m4703a = C11201bn.m4703a(context);
                m4703a.m4696a("begin delete " + j2 + "noUpload messages , because db size is " + m4609a + "B");
                super.mo4672a(context, obj);
                return;
            }
            AbstractC11049b.m5274b("db size is suitable");
        }
    }
}
