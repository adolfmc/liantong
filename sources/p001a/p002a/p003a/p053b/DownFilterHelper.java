package p001a.p002a.p003a.p053b;

import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.cloud.download.exception.DownloadException;
import com.baidu.license.filter.DownFilterHelper;
import java.io.File;
import p001a.p002a.p003a.p004a.RequestParameterUtil;
import p001a.p002a.p003a.p055d.EffectInfoHelp;

/* renamed from: a.a.a.b.oi */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class DownFilterHelper extends DownloadCallback {

    /* renamed from: a */
    public final /* synthetic */ File f2072a;

    /* renamed from: b */
    public final /* synthetic */ File f2073b;

    /* renamed from: c */
    public final /* synthetic */ String f2074c;

    /* renamed from: d */
    public final /* synthetic */ DownFilterHelper.OnDownFilterListener f2075d;

    public DownFilterHelper(com.baidu.license.filter.DownFilterHelper downFilterHelper, File file, File file2, String str, DownFilterHelper.OnDownFilterListener onDownFilterListener) {
        this.f2072a = file;
        this.f2073b = file2;
        this.f2074c = str;
        this.f2075d = onDownFilterListener;
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onCompleted(String str) {
        super.onCompleted(str);
        try {
            this.f2072a.renameTo(this.f2073b);
            EffectInfoHelp.m22359a(this.f2073b, this.f2074c);
            RequestParameterUtil.m22366a(this.f2073b.getAbsolutePath());
            if (this.f2075d != null) {
                this.f2075d.onDownSuccess(this.f2074c);
            }
        } catch (Exception unused) {
            RequestParameterUtil.m22366a(this.f2073b.getAbsolutePath());
            if (RequestParameterUtil.m22363b(this.f2074c)) {
                RequestParameterUtil.m22367a(new File(this.f2074c));
            }
            DownFilterHelper.OnDownFilterListener onDownFilterListener = this.f2075d;
            if (onDownFilterListener != null) {
                onDownFilterListener.onDownFail(new DownloadException("unzip exception"));
            }
        }
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onFailed(DownloadException downloadException) {
        super.onFailed(downloadException);
        DownFilterHelper.OnDownFilterListener onDownFilterListener = this.f2075d;
        if (onDownFilterListener != null) {
            onDownFilterListener.onDownFail(downloadException);
        }
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onStarted() {
        super.onStarted();
    }
}
