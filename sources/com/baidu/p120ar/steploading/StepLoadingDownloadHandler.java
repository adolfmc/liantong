package com.baidu.p120ar.steploading;

import android.text.TextUtils;
import com.baidu.p120ar.bean.ARCaseBundleInfo;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.callback.IError;
import com.baidu.p120ar.ihttp.Downloader;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.IProgressCallback;
import com.baidu.p120ar.pipeline.AbstractChannelHandler;
import com.baidu.p120ar.utils.ZipUtils;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.steploading.StepLoadingDownloadHandler */
/* loaded from: E:\10201592_dexfile_execute.dex */
class StepLoadingDownloadHandler extends AbstractChannelHandler<String, Void> {
    private ARCaseBundleInfo mARBundle;
    private IProgressCallback mProgressCallback;
    private String mResPath;
    private BundleStepResReader mStepResReader;

    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    public void doCancel() {
    }

    public StepLoadingDownloadHandler(ARCaseBundleInfo aRCaseBundleInfo, String str, BundleStepResReader bundleStepResReader, IProgressCallback iProgressCallback) {
        this.mARBundle = aRCaseBundleInfo;
        this.mResPath = str;
        this.mStepResReader = bundleStepResReader;
        this.mProgressCallback = iProgressCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.baidu.p120ar.pipeline.AbstractChannelHandler
    public void run(String str, ICallbackWith<Void> iCallbackWith, IError iError) {
        if (TextUtils.isEmpty(str)) {
            iError.onError(2, "res url is not exists", null);
        } else if ("local".equals(str)) {
            iCallbackWith.run(null);
        } else {
            StepResInfo resInfo = this.mStepResReader.getResInfo(this.mResPath);
            if (resInfo == null) {
                iError.onError(2, "res is not exists", null);
                return;
            }
            String downloadSavePath = getDownloadSavePath(resInfo);
            if (downloadSavePath == null) {
                iError.onError(2, "未知的资源encoding", null);
                return;
            }
            Downloader downloader = new Downloader(str);
            try {
                int fileSize = downloader.getFileSize();
                if (tryUnzipRes(downloadSavePath, resInfo, fileSize)) {
                    iCallbackWith.run(null);
                    return;
                }
                try {
                    downloader.download(downloadSavePath, this.mProgressCallback);
                    if (tryUnzipRes(downloadSavePath, resInfo, fileSize)) {
                        iCallbackWith.run(null);
                    } else {
                        iError.onError(2, "download fail", null);
                    }
                } catch (Exception e) {
                    iError.onError(2, e.getMessage(), e);
                }
            } catch (HttpException e2) {
                iError.onError(2, e2.getMessage(), e2);
            }
        }
    }

    private String getDownloadSavePath(StepResInfo stepResInfo) {
        String parent = new File(this.mARBundle.caseDir).getParent();
        if ("gzip".equalsIgnoreCase(stepResInfo.encoding)) {
            String format = String.format("/temp/%s.zip", stepResInfo.resId);
            return parent + format;
        } else if ("identity".equalsIgnoreCase(stepResInfo.encoding)) {
            return parent + File.separator + stepResInfo.resPath;
        } else {
            return null;
        }
    }

    private boolean tryUnzipRes(String str, StepResInfo stepResInfo, int i) {
        File file = new File(str);
        if (file.exists() && file.length() == i) {
            if ("gzip".equalsIgnoreCase(stepResInfo.encoding)) {
                return ZipUtils.unzip(new File(str), new File(this.mARBundle.caseDir).getParentFile());
            }
            return true;
        }
        return false;
    }
}
