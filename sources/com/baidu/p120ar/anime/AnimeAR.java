package com.baidu.p120ar.anime;

import android.text.TextUtils;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.anime.AnimeDetector;
import com.baidu.p120ar.anime.AnimeRequestController;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.databasic.ReserveHandleData;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.lua.LuaMsgListener;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.BitmapUtils;
import com.baidu.p120ar.utils.ImageUtils;
import com.baidu.p120ar.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.anime.AnimeAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AnimeAR extends AbstractAR {
    private static final String LUA_ANIME_NETWORK_STATUS = "anime_network_status";
    private static final String LUA_CANCEL_ANIME_REQUEST = "cancel_anime_request";
    private static final String LUA_FILTER_ID = "filter_id";
    private static final String LUA_FRAME_TYPE = "frame_type";
    private static final String LUA_FRAME_TYPE_FRAME_ONLY = "get_frame_only";
    private static final String LUA_FRAME_TYPE_FRAME_REQUEST = "get_frame_and_request";
    private static final String LUA_GET_PIXEL_FRAME = "get_pixel_frame";
    private static final String LUA_RETRY_ANIME_EFFECT = "retry_anime_effect";
    private static final String TAG = "AnimeAR";
    private LuaMsgListener mLuaMsgListener;
    private String mAbilityName = null;
    private AlgoHandleController mAlgoHandleController = null;
    private AnimeRequestController mRequestController = null;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setup(luaParams):");
        sb.append(hashMap != null ? hashMap.toString() : null);
        ARLog.m20417i(str, sb.toString());
        if (this.mAlgoHandleController == null) {
            this.mAlgoHandleController = new AlgoHandleController();
        }
        if (hashMap != null) {
            this.mAbilityName = MsgParamsUtil.obj2String(hashMap.get("ability_name"), null);
        }
        if (TextUtils.isEmpty(this.mAbilityName) || !"ability_anime".equals(this.mAbilityName)) {
            String str2 = TAG;
            ARLog.m20419e(str2, "mAbilityName error: " + this.mAbilityName);
        }
        if (this.mRequestController == null) {
            this.mRequestController = new AnimeRequestController(new AnimeRequestController.RequestCallBack() { // from class: com.baidu.ar.anime.AnimeAR.1
                @Override // com.baidu.p120ar.anime.AnimeRequestController.RequestCallBack
                public void onResult(int i, String str3, long j) {
                    if (i == 200) {
                        IARRenderer renderer = AnimeAR.this.getRenderer();
                        if (renderer == null || j <= 0) {
                            return;
                        }
                        String str4 = AnimeAR.TAG;
                        ARLog.m20419e(str4, "result setAlgoHandleData:" + j);
                        renderer.setAlgoHandleData(j, AnimeAR.this.mAbilityName);
                        AnimeAR.this.onAlgoHandleDestory(j);
                        return;
                    }
                    String str5 = AnimeAR.TAG;
                    ARLog.m20419e(str5, "request error! code:" + i + " msg:" + str3);
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("event_name", "anime_network_status");
                    hashMap2.put("error_code", Integer.valueOf(i));
                    hashMap2.put("error_msg", str3);
                    AnimeAR.this.sendMsg2Lua(hashMap2);
                }
            });
            this.mRequestController.setAlgoHandleController(this.mAlgoHandleController);
            this.mRequestController.setOrientationLandscapeModel(ScreenUtils.isScreenOrientationLandscape(getContext()));
        }
        addLuaMsgListener();
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.addAlgoCache(21, false);
        }
    }

    private void addLuaMsgListener() {
        if (this.mLuaMsgListener == null) {
            this.mLuaMsgListener = new LuaMsgListener() { // from class: com.baidu.ar.anime.AnimeAR.2
                @Override // com.baidu.p120ar.lua.LuaMsgListener
                public List<String> getMsgKeyListened() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("event_name");
                    return arrayList;
                }

                @Override // com.baidu.p120ar.lua.LuaMsgListener
                public void onLuaMessage(HashMap<String, Object> hashMap) {
                    String str = (String) hashMap.get("event_name");
                    String str2 = (String) hashMap.get("type_name");
                    if ("get_pixel_frame".equals(str)) {
                        String str3 = (String) hashMap.get("filter_id");
                        String str4 = (String) hashMap.get("frame_type");
                        ARLog.m20420e("event_name: " + str + ", filterId: " + str3 + ", frameType:" + str4 + "type_name: " + str2);
                        boolean z = true;
                        if ("get_frame_only".equals(str4)) {
                            z = false;
                        } else {
                            "get_frame_and_request".equals(str4);
                        }
                        AnimeAR.this.getPixelAndRequest(str3, z, str2);
                    }
                    if ("retry_anime_effect".equals(str)) {
                        ARLog.m20420e("event_name: " + str + " and mRequestController.retryChangeStyle()");
                        if (AnimeAR.this.mRequestController != null) {
                            AnimeAR.this.mRequestController.retryChangeStyle(str2);
                        }
                    }
                    if ("cancel_anime_request".equals(str)) {
                        ARLog.m20420e("event_name: " + str + " and mRequestController.cancelAllRequest()");
                        if (AnimeAR.this.mRequestController != null) {
                            AnimeAR.this.mRequestController.cancelAllRequest();
                        }
                    }
                }
            };
        }
        addLuaMsgListener(this.mLuaMsgListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPixelAndRequest(String str, final boolean z, final String str2) {
        final AnimeDetector animeDetector = new AnimeDetector();
        if (TextUtils.isEmpty(str2) && ScreenUtils.isScreenOrientationLandscape(getContext())) {
            animeDetector.setOrientationLandscapeOutput();
        }
        animeDetector.setPixelCallback(new AnimeDetector.PixelCallback() { // from class: com.baidu.ar.anime.AnimeAR.3
            @Override // com.baidu.p120ar.anime.AnimeDetector.PixelCallback
            public void onFramePixelRead(FramePixels framePixels) {
                AnimeAR.this.createHandleSendToEngine(framePixels);
                if (z && AnimeAR.this.mRequestController != null) {
                    ARLog.m20419e(AnimeAR.TAG, "onFramePixelRead and mRequestController.request");
                    AnimeAR.this.mRequestController.request(framePixels, str2);
                }
                new Thread(new Runnable() { // from class: com.baidu.ar.anime.AnimeAR.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnimeAR.this.removeDetector(animeDetector);
                    }
                }).start();
            }
        });
        animeDetector.setReadParamFilterId(str);
        addDetector(animeDetector, new DetectorCallback() { // from class: com.baidu.ar.anime.AnimeAR.4
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createHandleSendToEngine(FramePixels framePixels) {
        AlgoHandleController algoHandleController;
        if (framePixels == null || (algoHandleController = this.mAlgoHandleController) == null) {
            return;
        }
        long createHandle = algoHandleController.createHandle();
        this.mAlgoHandleController.setHandleInput(createHandle, 21, framePixels.getTimestamp(), 4, framePixels.getWidth(), framePixels.getHeight(), framePixels.isFrontCamera(), framePixels.getSegOrientation().getValue(), false, null);
        byte[] pixelData = framePixels.getPixelData();
        ReserveHandleData reserveHandleData = new ReserveHandleData();
        reserveHandleData.setByteDataSize(1);
        reserveHandleData.setByteWidths(new int[]{framePixels.getWidth()});
        reserveHandleData.setByteHeights(new int[]{framePixels.getHeight()});
        if (ScreenUtils.isScreenOrientationLandscape(getContext())) {
            pixelData = ImageUtils.getRgbaFromBitmap(BitmapUtils.adjustPhotoRotation(ImageUtils.rgba2Bitmap(framePixels.getWidth(), framePixels.getHeight(), pixelData), -90));
            reserveHandleData.setByteWidths(new int[]{framePixels.getHeight()});
            reserveHandleData.setByteHeights(new int[]{framePixels.getWidth()});
        }
        reserveHandleData.setByteFormats(new int[]{2});
        ArrayList arrayList = new ArrayList();
        arrayList.add(pixelData);
        reserveHandleData.setByteArrayListData(arrayList);
        AlgoHandleAdapter.setHandleReserveData(createHandle, reserveHandleData);
        IARRenderer renderer = getRenderer();
        if (renderer == null || createHandle <= 0) {
            return;
        }
        String str = TAG;
        ARLog.m20419e(str, "origin image setAlgoHandleData:" + createHandle);
        renderer.setAlgoHandleData(createHandle, this.mAbilityName);
        onAlgoHandleDestory(createHandle);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onAlgoHandleDestory(long j) {
        AlgoHandleController algoHandleController;
        super.onAlgoHandleDestory(j);
        if (j <= 0 || (algoHandleController = this.mAlgoHandleController) == null || algoHandleController.getHandleType(j) != 21) {
            return;
        }
        String str = TAG;
        ARLog.m20419e(str, "destroyHandle:" + j);
        this.mAlgoHandleController.destroyHandle(j);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        removeLuaMsgListener(this.mLuaMsgListener);
        AnimeRequestController animeRequestController = this.mRequestController;
        if (animeRequestController != null) {
            animeRequestController.release();
            this.mRequestController = null;
        }
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController != null) {
            algoHandleController.release();
            this.mAlgoHandleController = null;
        }
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.removeAlgoCache(21);
        }
        super.release();
    }
}
