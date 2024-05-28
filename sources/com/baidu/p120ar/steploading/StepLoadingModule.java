package com.baidu.p120ar.steploading;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.p120ar.arplay.core.message.ARPMessage;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.bean.ARCaseBundleInfo;
import com.baidu.p120ar.bean.ARConfig;
import com.baidu.p120ar.callback.ICallback;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.callback.ICancellable;
import com.baidu.p120ar.callback.IError;
import com.baidu.p120ar.ihttp.IProgressCallback;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.lua.EngineMsgListener;
import com.baidu.p120ar.pipeline.Pipeline;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.steploading.StepLoadingModule */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StepLoadingModule implements EngineMsgListener, IStepLoading {
    private static final String ENGINE_TO_SDK_DOWNLOAD_MSG_NEED_PROGRESS = "need_progress";
    private static final String ENGINE_TO_SDK_DOWNLOAD_MSG_PROGRESS = "progress";
    private static final String ENGINE_TO_SDK_DOWNLOAD_MSG_REQUEST_ID = "request_id";
    private static final String ENGINE_TO_SDK_DOWNLOAD_MSG_RES_PATH = "res_path";
    private static final String ENGINE_TO_SDK_DOWNLOAD_MSG_RET = "ret";
    private static final int LOAD_STATUS_DOWNLOAD_ANSWER = 3021;
    private static final int LOAD_STATUS_DOWNLOAD_RETRY_SHOWDIALOG = 3010;
    private static final String SDK_TO_LUA_DOWNLOAD_FAILED_RETRY_IF_DOWNLOAD = "if_download";
    private static final String SDK_TO_LUA_DOWNLOAD_FAILED_RETRY_RES_PATH = "download_batchid";
    private ARCaseBundleInfo mCase;
    private Context mContext;
    private ICallbackWith<IStepLoading> mErrorCallback;
    private boolean mIsPreFail;
    private String mPreResPath;
    private BundleStepResReader mResReader;

    public StepLoadingModule(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void init(EngineMsgBridge engineMsgBridge) {
        engineMsgBridge.addEngineMsgListener(this);
    }

    public void switchCase(String str) {
        this.mCase = new ARCaseBundleInfo();
        ARCaseBundleInfo aRCaseBundleInfo = this.mCase;
        aRCaseBundleInfo.caseDir = str;
        aRCaseBundleInfo.arKey = ARConfig.getARKey();
        this.mResReader = new BundleStepResReader(this.mCase.caseDir);
    }

    private ICancellable downloadStepRes(String str, ICallback iCallback, IError iError, IProgressCallback iProgressCallback) {
        return Pipeline.run(new StepLoadingQueryHandler(this.mContext, this.mCase, this.mResReader), str).next(new StepLoadingDownloadHandler(this.mCase, str, this.mResReader, iProgressCallback)).next(new CallbackHandler(iCallback, iError));
    }

    private void handleResDownloadMsg(HashMap<String, Object> hashMap) {
        String str = (String) hashMap.get("res_path");
        final String str2 = (String) hashMap.get("request_id");
        final boolean z = ((Integer) hashMap.get("need_progress")).intValue() != 0;
        this.mPreResPath = str;
        downloadStepRes(str, new ICallback() { // from class: com.baidu.ar.steploading.StepLoadingModule.1
            @Override // com.baidu.p120ar.callback.ICallback
            public void run() {
                if (z) {
                    StepLoadingModule.responseEngineDownloadProgress(str2, 100);
                }
                StepLoadingModule.responseEngineDownload(str2, 0);
            }
        }, new IError() { // from class: com.baidu.ar.steploading.StepLoadingModule.2
            @Override // com.baidu.p120ar.callback.IError
            public void onError(int i, String str3, Exception exc) {
                StepLoadingModule.responseEngineDownload(str2, -1);
            }
        }, new IProgressCallback() { // from class: com.baidu.ar.steploading.StepLoadingModule.3
            @Override // com.baidu.p120ar.ihttp.IProgressCallback
            public void onProgress(int i, int i2) {
                if (!z || i2 <= 0) {
                    return;
                }
                StepLoadingModule.responseEngineDownloadProgress(str2, (int) (((i * 90) * 1.0f) / i2));
            }
        });
    }

    @Override // com.baidu.p120ar.lua.EngineMsgListener
    public List<Integer> getMsgTypesListened() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(5001);
        arrayList.add(1901);
        return arrayList;
    }

    @Override // com.baidu.p120ar.lua.EngineMsgListener
    public void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
        if (i == 5001) {
            if (hashMap != null) {
                this.mIsPreFail = false;
                handleResDownloadMsg(hashMap);
            }
        } else if (i == 1901 && MsgParamsUtil.obj2Int(hashMap.get("id"), -1) == 3010) {
            this.mIsPreFail = true;
            ICallbackWith<IStepLoading> iCallbackWith = this.mErrorCallback;
            if (iCallbackWith != null) {
                iCallbackWith.run(this);
            } else {
                cancel();
            }
        }
    }

    public void release() {
        this.mIsPreFail = false;
        this.mPreResPath = null;
        this.mErrorCallback = null;
        this.mContext = null;
        this.mCase = null;
        this.mResReader = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void responseEngineDownload(String str, int i) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("request_id", str);
        hashMap.put("ret", Integer.valueOf(i));
        ARPMessage.getInstance().sendMessage(5003, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void responseEngineDownloadProgress(String str, int i) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("request_id", str);
        hashMap.put("progress", Integer.valueOf(i));
        ARPMessage.getInstance().sendMessage(5002, hashMap);
    }

    @Override // com.baidu.p120ar.steploading.IStepLoading
    public void setLoadErrorListener(ICallbackWith<IStepLoading> iCallbackWith) {
        this.mErrorCallback = iCallbackWith;
    }

    @Override // com.baidu.p120ar.steploading.IStepLoading
    public void retry() {
        if (!this.mIsPreFail || TextUtils.isEmpty(this.mPreResPath)) {
            return;
        }
        this.mIsPreFail = false;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 3021);
        hashMap.put("if_download", 1);
        hashMap.put("download_batchid", this.mPreResPath);
        ARPMessage.getInstance().sendMessage(1902, hashMap);
    }

    @Override // com.baidu.p120ar.steploading.IStepLoading
    public void cancel() {
        if (!this.mIsPreFail || TextUtils.isEmpty(this.mPreResPath)) {
            return;
        }
        this.mIsPreFail = false;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 3021);
        hashMap.put("if_download", 0);
        hashMap.put("download_batchid", this.mPreResPath);
        ARPMessage.getInstance().sendMessage(1902, hashMap);
    }
}
