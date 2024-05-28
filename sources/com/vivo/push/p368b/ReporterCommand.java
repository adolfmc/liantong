package com.vivo.push.p368b;

import android.text.TextUtils;
import com.vivo.push.BundleWapper;
import com.vivo.push.PushCommand;
import com.vivo.push.util.LogUtil;
import java.util.HashMap;

/* renamed from: com.vivo.push.b.x */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ReporterCommand extends PushCommand {

    /* renamed from: a */
    private HashMap<String, String> f20920a;

    /* renamed from: b */
    private long f20921b;

    public ReporterCommand() {
        super(2012);
    }

    public ReporterCommand(long j) {
        this();
        this.f20921b = j;
    }

    /* renamed from: a */
    public final void m5761a(HashMap<String, String> hashMap) {
        this.f20920a = hashMap;
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        bundleWapper.m5731a("ReporterCommand.EXTRA_PARAMS", this.f20920a);
        bundleWapper.m5732a("ReporterCommand.EXTRA_REPORTER_TYPE", this.f20921b);
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        this.f20920a = (HashMap) bundleWapper.m5721d("ReporterCommand.EXTRA_PARAMS");
        this.f20921b = bundleWapper.m5723b("ReporterCommand.EXTRA_REPORTER_TYPE", this.f20921b);
    }

    @Override // com.vivo.push.PushCommand
    public final String toString() {
        return "ReporterCommand（" + this.f20921b + ")";
    }

    /* renamed from: d */
    public final void m5760d() {
        if (this.f20920a == null) {
            LogUtil.m5341d("ReporterCommand", "reportParams is empty");
            return;
        }
        StringBuilder sb = new StringBuilder("report message reportType:");
        sb.append(this.f20921b);
        sb.append(",msgId:");
        String str = this.f20920a.get("messageID");
        if (TextUtils.isEmpty(str)) {
            str = this.f20920a.get("message_id");
        }
        sb.append(str);
        LogUtil.m5341d("ReporterCommand", sb.toString());
    }
}
