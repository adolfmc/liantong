package com.vivo.push.p373f;

import com.vivo.push.PushClientManager;
import com.vivo.push.PushClientThread;
import com.vivo.push.PushCommand;
import com.vivo.push.p368b.OnTagsReceiveCommand;
import com.vivo.push.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.vivo.push.f.ab */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class OnSetTagsReceiveTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnSetTagsReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        OnTagsReceiveCommand onTagsReceiveCommand = (OnTagsReceiveCommand) pushCommand;
        ArrayList<String> m5768d = onTagsReceiveCommand.m5768d();
        List<String> m5767e = onTagsReceiveCommand.m5767e();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        int i = onTagsReceiveCommand.m5769i();
        LogUtil.m5342c("OnSetTagsTask", "doTask,删除成功的标签 = " + m5768d + " 删除失败的= " + m5767e + " 错误码= " + i);
        String h = onTagsReceiveCommand.m5770h();
        if (m5768d != null) {
            for (String str : m5768d) {
                if (str.startsWith("ali/")) {
                    arrayList2.add(str.replace("ali/", ""));
                } else if (str.startsWith("tag/")) {
                    arrayList.add(str.replace("tag/", ""));
                }
            }
        }
        if (m5767e != null) {
            for (String str2 : m5767e) {
                if (str2.startsWith("ali/")) {
                    arrayList4.add(str2.replace("ali/", ""));
                } else if (str2.startsWith("tag/")) {
                    arrayList3.add(str2.replace("tag/", ""));
                }
            }
        }
        if (arrayList.size() > 0 || arrayList3.size() > 0) {
            LogUtil.m5342c("OnSetTagsTask", "doTask1,订阅成功的标签 = " + arrayList + " 订阅失败的标签= " + arrayList3 + " 错误码= " + i);
            if (arrayList.size() > 0) {
                PushClientManager.m5648a();
                PushClientManager.m5631a(arrayList);
            }
            PushClientManager.m5648a().m5636a(onTagsReceiveCommand.m5770h(), i);
            PushClientThread.m5481b(new RunnableC10945ac(this, i, arrayList, arrayList3, h));
        }
        if (arrayList2.size() > 0 || arrayList4.size() > 0) {
            LogUtil.m5342c("OnSetTagsTask", "doTask1,订阅成功的别名 = " + arrayList + " 订阅失败的别名= " + arrayList3 + " 错误码= " + i);
            if (arrayList2.size() > 0) {
                PushClientManager.m5648a().m5637a((String) arrayList2.get(0));
            }
            PushClientManager.m5648a().m5636a(onTagsReceiveCommand.m5770h(), i);
            PushClientThread.m5481b(new RunnableC10946ad(this, i, arrayList2, arrayList4, h));
        }
    }
}
