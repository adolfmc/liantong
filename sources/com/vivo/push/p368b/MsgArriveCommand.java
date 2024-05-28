package com.vivo.push.p368b;

import android.text.TextUtils;
import com.vivo.push.BundleWapper;
import com.vivo.push.PushCommand;

/* renamed from: com.vivo.push.b.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class MsgArriveCommand extends PushCommand {

    /* renamed from: a */
    private String f20884a;

    /* renamed from: b */
    private String f20885b;

    public MsgArriveCommand() {
        super(2013);
    }

    public MsgArriveCommand(String str) {
        this();
        this.f20884a = str;
    }

    public MsgArriveCommand(String str, String str2) {
        this(str);
        this.f20885b = str2;
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: c */
    public final void mo5322c(BundleWapper bundleWapper) {
        bundleWapper.m5730a("MsgArriveCommand.MSG_TAG", this.f20884a);
        if (TextUtils.isEmpty(this.f20885b)) {
            return;
        }
        bundleWapper.m5730a("MsgArriveCommand.NODE_INFO", this.f20885b);
    }

    @Override // com.vivo.push.PushCommand
    /* renamed from: d */
    public final void mo5321d(BundleWapper bundleWapper) {
        this.f20884a = bundleWapper.m5734a("MsgArriveCommand.MSG_TAG");
        this.f20885b = bundleWapper.m5734a("MsgArriveCommand.NODE_INFO");
    }
}
