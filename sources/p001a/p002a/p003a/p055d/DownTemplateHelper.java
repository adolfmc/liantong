package p001a.p002a.p003a.p055d;

import com.baidu.cloud.download.base.DownloadCallback;
import com.baidu.cloud.download.exception.DownloadException;
import com.baidu.license.template.DownTemplateHelper;
import java.io.File;
import p001a.p002a.p003a.p004a.RequestParameterUtil;

/* renamed from: a.a.a.d.oi */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class DownTemplateHelper extends DownloadCallback {

    /* renamed from: a */
    public final /* synthetic */ File f2077a;

    /* renamed from: b */
    public final /* synthetic */ File f2078b;

    /* renamed from: c */
    public final /* synthetic */ String f2079c;

    /* renamed from: d */
    public final /* synthetic */ DownTemplateHelper.OnDownTemplateListener f2080d;

    public DownTemplateHelper(com.baidu.license.template.DownTemplateHelper downTemplateHelper, File file, File file2, String str, DownTemplateHelper.OnDownTemplateListener onDownTemplateListener) {
        this.f2077a = file;
        this.f2078b = file2;
        this.f2079c = str;
        this.f2080d = onDownTemplateListener;
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onCompleted(String str) {
        super.onCompleted(str);
        try {
            this.f2077a.renameTo(this.f2078b);
            EffectInfoHelp.m22359a(this.f2078b, this.f2079c);
            RequestParameterUtil.m22366a(this.f2078b.getAbsolutePath());
            if (this.f2080d != null) {
                this.f2080d.onDownSuccess(this.f2079c);
            }
        } catch (Exception unused) {
            RequestParameterUtil.m22366a(this.f2078b.getAbsolutePath());
            if (RequestParameterUtil.m22363b(this.f2079c)) {
                RequestParameterUtil.m22367a(new File(this.f2079c));
            }
            DownTemplateHelper.OnDownTemplateListener onDownTemplateListener = this.f2080d;
            if (onDownTemplateListener != null) {
                onDownTemplateListener.onDownFail(new DownloadException("unzip exception"));
            }
        }
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onFailed(DownloadException downloadException) {
        super.onFailed(downloadException);
        DownTemplateHelper.OnDownTemplateListener onDownTemplateListener = this.f2080d;
        if (onDownTemplateListener != null) {
            onDownTemplateListener.onDownFail(downloadException);
        }
    }

    @Override // com.baidu.cloud.download.base.DownloadCallback
    public void onStarted() {
        super.onStarted();
    }
}
