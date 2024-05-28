package com.sinovatech.unicom.hub.media.gallery;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.sinovatech.unicom.hub.media.utils.MediaStoreUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MediaStoreViewModel extends ViewModel {
    private MutableLiveData mediaStoreLiveData = new MutableLiveData();
    private MutableLiveData checkMediaLiveData = new MutableLiveData();
    private MutableLiveData currentBucketLiveData = new MutableLiveData();

    public LiveData<List<BucketEntity>> getMediaStoreLiveData() {
        return this.mediaStoreLiveData;
    }

    public LiveData<List<MediaEntity>> getCheckMediaLiveData() {
        return this.checkMediaLiveData;
    }

    public LiveData<BucketEntity> getCurrentBucketLiveData() {
        return this.currentBucketLiveData;
    }

    public void loadMediaStore(final Activity activity, final String str) {
        Observable.create(new ObservableOnSubscribe<Object>() { // from class: com.sinovatech.unicom.hub.media.gallery.MediaStoreViewModel.3
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(@NonNull ObservableEmitter<Object> observableEmitter) throws Exception {
                observableEmitter.onNext(MediaStoreUtils.getMediaStore(activity, str));
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.hub.media.gallery.MediaStoreViewModel.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                MediaStoreViewModel.this.mediaStoreLiveData.setValue(obj);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.hub.media.gallery.MediaStoreViewModel.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                MediaStoreViewModel.this.mediaStoreLiveData.setValue(null);
            }
        });
    }

    public void updateCheckMediaLiveData(List<MediaEntity> list) {
        this.checkMediaLiveData.setValue(list);
    }

    public void updateCurrentBucketLiveData(BucketEntity bucketEntity) {
        this.currentBucketLiveData.setValue(bucketEntity);
    }

    public JSONObject parseDataToJSON(List<MediaEntity> list) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            try {
                MediaEntity mediaEntity = list.get(i);
                JSONObject jSONObject2 = new JSONObject();
                if ("video".equals(mediaEntity.getMediaType())) {
                    jSONObject2.put("tempFilePath", mediaEntity.getOriginalPath());
                    jSONObject2.put("size", mediaEntity.getLength());
                    long j = 1;
                    if (mediaEntity.getVideoDuration() / 1000 >= 1) {
                        j = mediaEntity.getVideoDuration() / 1000;
                    }
                    jSONObject2.put("duration", j);
                    jSONObject2.put("height", mediaEntity.getHeight());
                    jSONObject2.put("width", mediaEntity.getWidth());
                    jSONObject2.put("videoThumbTempFilePath", mediaEntity.getVideoThumbnail());
                    jSONObject2.put("fileType", mediaEntity.getMediaType());
                } else {
                    jSONObject2.put("tempFilePath", mediaEntity.getImagePath());
                    jSONObject2.put("size", mediaEntity.getLength());
                    jSONObject2.put("fileType", mediaEntity.getMediaType());
                }
                jSONArray.put(jSONObject2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        jSONObject.put("tempFiles", jSONArray);
        return jSONObject;
    }
}
