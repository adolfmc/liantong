package p001a.p002a.p003a.p056e;

import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.cloud.download.exception.DownloadException;
import com.baidu.license.transitions.DownTransitionsHelper;
import java.io.File;
import p001a.p002a.p003a.p004a.RequestParameterUtil;
import p001a.p002a.p003a.p055d.EffectInfoHelp;

/* renamed from: a.a.a.e.oi */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class DownTransitionsHelper extends DownloadCallback {

    /* renamed from: a */
    public final /* synthetic */ File f2081a;

    /* renamed from: b */
    public final /* synthetic */ File f2082b;

    /* renamed from: c */
    public final /* synthetic */ String f2083c;

    /* renamed from: d */
    public final /* synthetic */ DownTransitionsHelper.OnDownTransitionsListener f2084d;

    public DownTransitionsHelper(com.baidu.license.transitions.DownTransitionsHelper downTransitionsHelper, File file, File file2, String str, DownTransitionsHelper.OnDownTransitionsListener onDownTransitionsListener) {
        this.f2081a = file;
        this.f2082b = file2;
        this.f2083c = str;
        this.f2084d = onDownTransitionsListener;
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onCompleted(String str) {
        super.onCompleted(str);
        try {
            this.f2081a.renameTo(this.f2082b);
            EffectInfoHelp.m22359a(this.f2082b, this.f2083c);
            RequestParameterUtil.m22366a(this.f2082b.getAbsolutePath());
            if (this.f2084d != null) {
                this.f2084d.onDownSuccess(this.f2083c);
            }
        } catch (Exception unused) {
            RequestParameterUtil.m22366a(this.f2082b.getAbsolutePath());
            if (RequestParameterUtil.m22363b(this.f2083c)) {
                RequestParameterUtil.m22367a(new File(this.f2083c));
            }
            DownTransitionsHelper.OnDownTransitionsListener onDownTransitionsListener = this.f2084d;
            if (onDownTransitionsListener != null) {
                onDownTransitionsListener.onDownFail(new DownloadException("unzip exception"));
            }
        }
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onFailed(DownloadException downloadException) {
        super.onFailed(downloadException);
        DownTransitionsHelper.OnDownTransitionsListener onDownTransitionsListener = this.f2084d;
        if (onDownTransitionsListener != null) {
            onDownTransitionsListener.onDownFail(downloadException);
        }
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onStarted() {
        super.onStarted();
    }
}
