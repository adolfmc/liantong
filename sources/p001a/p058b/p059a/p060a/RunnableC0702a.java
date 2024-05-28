package p001a.p058b.p059a.p060a;

import com.baidu.cloud.statistics.StatisticLiveImpl;
import com.baidu.uaq.agent.android.customtransmission.APMAgent;
import com.baidu.uaq.agent.android.customtransmission.APMUploadHandler;
import p001a.p002a.p003a.p004a.outline;

/* renamed from: a.b.a.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class RunnableC0702a implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ String f2097a;

    /* renamed from: b */
    public final /* synthetic */ StatisticLiveImpl f2098b;

    public RunnableC0702a(StatisticLiveImpl statisticLiveImpl, String str) {
        this.f2098b = statisticLiveImpl;
        this.f2097a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        APMAgent aPMAgent;
        APMUploadHandler aPMUploadHandler;
        try {
            aPMAgent = this.f2098b.apmAgent;
            aPMUploadHandler = this.f2098b.apmUploadHandler;
            aPMAgent.addLogWithHandler(aPMUploadHandler, this.f2097a);
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a(""), "LiveStatistics");
        }
    }
}
