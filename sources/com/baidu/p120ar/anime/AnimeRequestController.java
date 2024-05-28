package com.baidu.p120ar.anime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.databasic.ReserveHandleData;
import com.baidu.p120ar.ihttp.HttpException;
import com.baidu.p120ar.ihttp.HttpFactory;
import com.baidu.p120ar.ihttp.IHttpCallback;
import com.baidu.p120ar.ihttp.IHttpResponse;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.BitmapUtils;
import com.baidu.p120ar.utils.ImageUtils;
import com.baidu.p120ar.utils.UrlUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.anime.AnimeRequestController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AnimeRequestController {
    public static final int CODE_CANCEL = 201;
    public static final int CODE_NET_EXCEPTION = 202;
    public static final int CODE_RESULT_ERROR = 203;
    public static final int CODE_SUCCESS = 200;
    private AnimeHandler mAnimeHandler;
    private HandlerThread mAnimeHandlerThread;
    private boolean mOrientationLandscapeModel;
    private RequestCallBack mRequestCallback;
    private FramePixels mFramePixels = null;
    private byte[] mCurrentBuffer = null;
    private boolean enable = true;
    private AlgoHandleController mAlgoHandleController = null;
    private ArrayList<String> mTaskTimeStampList = new ArrayList<>();
    private boolean mNeedCropBitmap = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.anime.AnimeRequestController$RequestCallBack */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface RequestCallBack {
        void onResult(int i, String str, long j);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.anime.AnimeRequestController$AnimeHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class AnimeHandler extends Handler {
        private static final int MSG_RELEASE = 1002;
        private static final int MSG_REQUEST = 1001;
        private boolean mRelease;

        public AnimeHandler(Looper looper) {
            super(looper);
            this.mRelease = false;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1002) {
                this.mRelease = true;
            }
            Runnable runnable = (Runnable) message.obj;
            if (runnable != null) {
                runnable.run();
            }
        }

        public void sendMessage(int i, Runnable runnable) {
            if (this.mRelease) {
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.obj = runnable;
            sendMessage(obtain);
        }
    }

    public AnimeRequestController(RequestCallBack requestCallBack) {
        this.mRequestCallback = null;
        this.mRequestCallback = requestCallBack;
        if (this.mAnimeHandlerThread == null) {
            this.mAnimeHandlerThread = new HandlerThread("AnimeHandlerThread");
            this.mAnimeHandlerThread.start();
        }
        if (this.mAnimeHandler == null) {
            this.mAnimeHandler = new AnimeHandler(this.mAnimeHandlerThread.getLooper());
        }
    }

    public void setOrientationLandscapeModel(boolean z) {
        this.mOrientationLandscapeModel = z;
    }

    public void setAlgoHandleController(AlgoHandleController algoHandleController) {
        this.mAlgoHandleController = algoHandleController;
    }

    public void request(final FramePixels framePixels, final String str) {
        HandlerThread handlerThread = this.mAnimeHandlerThread;
        if (handlerThread == null || !handlerThread.isAlive() || this.mAnimeHandler == null) {
            return;
        }
        final String valueOf = String.valueOf(framePixels.getTimestamp());
        ArrayList<String> arrayList = this.mTaskTimeStampList;
        if (arrayList == null) {
            return;
        }
        arrayList.add(valueOf);
        this.mAnimeHandler.sendMessage(1001, new Runnable() { // from class: com.baidu.ar.anime.AnimeRequestController.1
            @Override // java.lang.Runnable
            public void run() {
                AnimeRequestController.this.mFramePixels = framePixels;
                FramePixels framePixels2 = framePixels;
                if (framePixels2 == null || framePixels2.getPixelsAddress() == null) {
                    ARLog.m20420e("framePixels data error!");
                    return;
                }
                byte[] pixelData = framePixels.getPixelData();
                long currentTimeMillis = System.currentTimeMillis();
                Bitmap rgba2Bitmap = ImageUtils.rgba2Bitmap(framePixels.getWidth(), framePixels.getHeight(), pixelData);
                byte[] bitmap2Bytes = ImageUtils.bitmap2Bytes(rgba2Bitmap, 50);
                rgba2Bitmap.recycle();
                AnimeRequestController.this.mCurrentBuffer = bitmap2Bytes;
                ARLog.m20420e("rgba2Bitmap and bitmap2Bytes cost:" + (System.currentTimeMillis() - currentTimeMillis));
                if (AnimeRequestController.this.checkCancelStatus(valueOf)) {
                    return;
                }
                final long currentTimeMillis2 = System.currentTimeMillis();
                AnimeRequestController.this.executeRequest(new IHttpCallback() { // from class: com.baidu.ar.anime.AnimeRequestController.1.1
                    @Override // com.baidu.p120ar.ihttp.IHttpCallback
                    public void onResponse(IHttpResponse iHttpResponse) {
                        ARLog.m20420e("request selfie2anime_quanmin cost: " + (System.currentTimeMillis() - currentTimeMillis2));
                        AnimeRequestController.this.processResponse(iHttpResponse, valueOf);
                    }

                    @Override // com.baidu.p120ar.ihttp.IHttpCallback
                    public void onFail(HttpException httpException) {
                        AnimeRequestController animeRequestController = AnimeRequestController.this;
                        animeRequestController.processRequestResult(202, httpException.getCode() + "/" + httpException.getMessage(), null);
                    }
                }, bitmap2Bytes, 0, str);
            }
        });
    }

    public void retryChangeStyle(final String str) {
        AnimeHandler animeHandler;
        if (this.mCurrentBuffer == null || this.mFramePixels == null) {
            ARLog.m20420e("retryChangeStyle input data error!");
            RequestCallBack requestCallBack = this.mRequestCallback;
            if (requestCallBack != null) {
                requestCallBack.onResult(203, "retryChangeStyle input data error", 0L);
                return;
            }
            return;
        }
        final String str2 = String.valueOf(this.mFramePixels.getTimestamp()) + String.valueOf(System.currentTimeMillis());
        ArrayList<String> arrayList = this.mTaskTimeStampList;
        if (arrayList == null) {
            return;
        }
        arrayList.add(str2);
        HandlerThread handlerThread = this.mAnimeHandlerThread;
        if (handlerThread == null || !handlerThread.isAlive() || (animeHandler = this.mAnimeHandler) == null) {
            return;
        }
        animeHandler.sendMessage(1001, new Runnable() { // from class: com.baidu.ar.anime.AnimeRequestController.2
            @Override // java.lang.Runnable
            public void run() {
                final long currentTimeMillis = System.currentTimeMillis();
                AnimeRequestController.this.executeRequest(new IHttpCallback() { // from class: com.baidu.ar.anime.AnimeRequestController.2.1
                    @Override // com.baidu.p120ar.ihttp.IHttpCallback
                    public void onResponse(IHttpResponse iHttpResponse) {
                        ARLog.m20420e("request selfie2anime_quanmin_rand cost: " + (System.currentTimeMillis() - currentTimeMillis));
                        AnimeRequestController.this.processResponse(iHttpResponse, str2);
                    }

                    @Override // com.baidu.p120ar.ihttp.IHttpCallback
                    public void onFail(HttpException httpException) {
                        AnimeRequestController animeRequestController = AnimeRequestController.this;
                        animeRequestController.processRequestResult(202, httpException.getCode() + "/" + httpException.getMessage(), null);
                    }
                }, AnimeRequestController.this.mCurrentBuffer, 1, str);
            }
        });
    }

    public void cancelAllRequest() {
        ArrayList<String> arrayList = this.mTaskTimeStampList;
        if (arrayList != null) {
            arrayList.clear();
        }
        AnimeHandler animeHandler = this.mAnimeHandler;
        if (animeHandler != null) {
            animeHandler.removeMessages(1001);
        }
    }

    public void release() {
        AnimeHandler animeHandler;
        this.enable = false;
        this.mRequestCallback = null;
        ArrayList<String> arrayList = this.mTaskTimeStampList;
        if (arrayList != null) {
            arrayList.clear();
            this.mTaskTimeStampList = null;
        }
        HandlerThread handlerThread = this.mAnimeHandlerThread;
        if (handlerThread == null || !handlerThread.isAlive() || (animeHandler = this.mAnimeHandler) == null) {
            return;
        }
        animeHandler.removeMessages(1001);
        this.mAnimeHandler.sendMessage(1002, new Runnable() { // from class: com.baidu.ar.anime.AnimeRequestController.3
            @Override // java.lang.Runnable
            public void run() {
                if (AnimeRequestController.this.mAnimeHandler != null) {
                    AnimeRequestController.this.mAnimeHandler = null;
                }
                if (AnimeRequestController.this.mAnimeHandlerThread != null) {
                    AnimeRequestController.this.mAnimeHandlerThread.quit();
                    AnimeRequestController.this.mAnimeHandlerThread = null;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkCancelStatus(String str) {
        ArrayList<String> arrayList = this.mTaskTimeStampList;
        if (arrayList == null || arrayList.size() <= 0 || !this.mTaskTimeStampList.contains(str)) {
            ARLog.m20420e("checkCancelStatus mTaskTimeStampList:" + this.mTaskTimeStampList);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeRequest(IHttpCallback iHttpCallback, byte[] bArr, int i, String str) {
        String xvisionUrl = UrlUtils.getXvisionUrl();
        try {
            String encodeToString = Base64.encodeToString(bArr, 0);
            JSONObject jSONObject = new JSONObject();
            if (TextUtils.isEmpty(str)) {
                this.mNeedCropBitmap = true;
                str = i == 0 ? "selfie2anime_quanmin" : "selfie2anime_quanmin_rand";
                if (this.mOrientationLandscapeModel) {
                    str = i == 0 ? "selfie2anime" : "selfie2anime_rand";
                }
            } else {
                this.mNeedCropBitmap = false;
            }
            jSONObject.put("type_name", str);
            jSONObject.put("image", encodeToString);
            HttpFactory.newRequest().setUrl(xvisionUrl).setReadTimeout(10000).addHeader("Content-Type:text/plain").setMethod("POST").setBody(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).enqueue(iHttpCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processResponse(IHttpResponse iHttpResponse, String str) {
        if (!this.enable) {
            processRequestResult(201, "cancel ability", null);
        } else if (checkCancelStatus(str)) {
        } else {
            try {
                String content = iHttpResponse.getContent();
                ARLog.m20420e("response.getContent():" + content);
                if (TextUtils.isEmpty(content)) {
                    processRequestResult(203, "response json error", null);
                    return;
                }
                JSONObject jSONObject = new JSONObject(content);
                int optInt = jSONObject.optInt("err_no", -1);
                String optString = jSONObject.optString("err_msg", null);
                String optString2 = jSONObject.optString("result", null);
                if (optInt != 0) {
                    processRequestResult(203, "service error, errorNum:" + optInt + " errorMsg:" + optString, null);
                } else if (checkCancelStatus(str)) {
                } else {
                    ArrayList<byte[]> parseResponseJson = parseResponseJson(optString2);
                    if (checkCancelStatus(str)) {
                        return;
                    }
                    processRequestResult(200, "success", parseResponseJson);
                    StatisticApi.onEvent("event_face2comic_caseuse");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ARLog.m20420e("processResponse Exception:" + e.getMessage());
                processRequestResult(203, "Exception:" + e.getMessage(), null);
            }
        }
    }

    private ArrayList<byte[]> parseResponseJson(String str) throws Exception {
        Bitmap decodeByteArray;
        Bitmap decodeByteArray2;
        ArrayList<byte[]> arrayList = new ArrayList<>();
        String str2 = new String(Base64.decode(str, 0), "UTF-8");
        ARLog.m20420e("result:" + str2);
        JSONObject jSONObject = new JSONObject(str2);
        String optString = jSONObject.optString("anime", null);
        String optString2 = jSONObject.optString("selfie", null);
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(optString2)) {
            byte[] decode = Base64.decode(optString2, 0);
            if (this.mOrientationLandscapeModel) {
                decodeByteArray2 = BitmapUtils.cropBitmap(BitmapUtils.adjustPhotoRotation(BitmapFactory.decodeByteArray(decode, 0, decode.length), 90), 180);
            } else {
                decodeByteArray2 = BitmapFactory.decodeByteArray(decode, 0, decode.length);
            }
            if (decodeByteArray2 != null) {
                byte[] rgbaFromBitmap = ImageUtils.getRgbaFromBitmap(decodeByteArray2);
                decodeByteArray2.recycle();
                arrayList.add(rgbaFromBitmap);
            }
        }
        if (!TextUtils.isEmpty(optString)) {
            byte[] decode2 = Base64.decode(optString, 0);
            if (this.mOrientationLandscapeModel) {
                decodeByteArray = BitmapUtils.cropBitmap(BitmapUtils.adjustPhotoRotation(BitmapFactory.decodeByteArray(decode2, 0, decode2.length), 90), 180);
            } else {
                decodeByteArray = BitmapFactory.decodeByteArray(decode2, 0, decode2.length);
            }
            if (decodeByteArray != null) {
                byte[] rgbaFromBitmap2 = ImageUtils.getRgbaFromBitmap(decodeByteArray);
                decodeByteArray.recycle();
                arrayList.add(rgbaFromBitmap2);
            }
        }
        ARLog.m20420e("decodeByteArray and getRgbaFromBitmap cost:" + (System.currentTimeMillis() - currentTimeMillis));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processRequestResult(int i, String str, ArrayList<byte[]> arrayList) {
        if (this.mRequestCallback == null) {
            ARLog.m20420e("mRequestCallback is null!");
        } else if (i != 201 && !this.enable) {
            ARLog.m20420e("task cancel, not process errorCode:" + i);
            this.mRequestCallback.onResult(201, "cancel ability", 0L);
        } else {
            long handle = getHandle(arrayList);
            if (i == 200 && handle <= 0) {
                this.mRequestCallback.onResult(203, "result invalid", handle);
            } else {
                this.mRequestCallback.onResult(i, str, handle);
            }
        }
    }

    private long getHandle(ArrayList<byte[]> arrayList) {
        if (this.mFramePixels == null || arrayList == null || arrayList.size() <= 0 || this.mAlgoHandleController == null) {
            return 0L;
        }
        if (arrayList.size() < 2) {
            ARLog.m20420e("result List size < 2!");
            return 0L;
        }
        long createHandle = this.mAlgoHandleController.createHandle();
        this.mAlgoHandleController.setHandleInput(createHandle, 21, this.mFramePixels.getTimestamp(), 4, this.mFramePixels.getWidth(), this.mFramePixels.getHeight(), this.mFramePixels.isFrontCamera(), this.mFramePixels.getSegOrientation().getValue(), false, this.mFramePixels.getPixelsAddress());
        ReserveHandleData reserveHandleData = new ReserveHandleData();
        reserveHandleData.setByteDataSize(arrayList.size());
        int width = this.mFramePixels.getWidth();
        int height = this.mFramePixels.getHeight();
        if (this.mNeedCropBitmap) {
            height = this.mFramePixels.getHeight() / 2;
        }
        if (this.mOrientationLandscapeModel) {
            width = this.mFramePixels.getHeight();
            height = this.mFramePixels.getWidth() / 2;
        }
        reserveHandleData.setByteWidths(new int[]{width, width});
        reserveHandleData.setByteHeights(new int[]{height, height});
        reserveHandleData.setByteFormats(new int[]{2, 2});
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(arrayList.get(i));
        }
        reserveHandleData.setByteArrayListData(arrayList2);
        AlgoHandleAdapter.setHandleReserveData(createHandle, reserveHandleData);
        return createHandle;
    }
}
