package com.sinovatech.unicom.separatemodule.search;

import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchManager {
    private Context context;
    private Box<SearchEntity> searchBox = App.getBoxStore().boxFor(SearchEntity.class);
    private Box<SearchHuoDongEntity> searchHuoDongBox = App.getBoxStore().boxFor(SearchHuoDongEntity.class);

    public SearchManager(Context context) {
        this.context = context;
    }

    public Box<SearchEntity> getSearchBox() {
        return this.searchBox;
    }

    public void loadScrollKeywordFromBox(Observer<List<SearchEntity>> observer) {
        Observable.create(new ObservableOnSubscribe<List<SearchEntity>>() { // from class: com.sinovatech.unicom.separatemodule.search.SearchManager.1
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<List<SearchEntity>> observableEmitter) throws Exception {
                String locateProvinceCode;
                String locateCityCode;
                new ArrayList();
                if (App.hasLogined()) {
                    locateProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
                    locateCityCode = UserManager.getInstance().getUserAreaid();
                } else {
                    locateProvinceCode = UserManager.getInstance().getLocateProvinceCode();
                    locateCityCode = UserManager.getInstance().getLocateCityCode();
                }
                observableEmitter.onNext(SearchManager.this.searchBox.query().equal(SearchEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).equal(SearchEntity_.proviceCode, locateProvinceCode).equal(SearchEntity_.cityCode, locateCityCode).build().find());
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public Observable loadScrollKeywordFromBox() {
        return Observable.create(new ObservableOnSubscribe<List<SearchEntity>>() { // from class: com.sinovatech.unicom.separatemodule.search.SearchManager.2
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<List<SearchEntity>> observableEmitter) throws Exception {
                String locateProvinceCode;
                String locateCityCode;
                new ArrayList();
                if (App.hasLogined()) {
                    locateProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
                    locateCityCode = UserManager.getInstance().getUserAreaid();
                } else {
                    locateProvinceCode = UserManager.getInstance().getLocateProvinceCode();
                    locateCityCode = UserManager.getInstance().getLocateCityCode();
                }
                observableEmitter.onNext(SearchManager.this.searchBox.query().equal(SearchEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).equal(SearchEntity_.proviceCode, locateProvinceCode).equal(SearchEntity_.cityCode, locateCityCode).build().find());
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable loadScrollHuoDongFromBox() {
        return Observable.create(new ObservableOnSubscribe<List<SearchHuoDongEntity>>() { // from class: com.sinovatech.unicom.separatemodule.search.SearchManager.3
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<List<SearchHuoDongEntity>> observableEmitter) throws Exception {
                String locateProvinceCode;
                String locateCityCode;
                new ArrayList();
                if (App.hasLogined()) {
                    locateProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
                    locateCityCode = UserManager.getInstance().getUserAreaid();
                } else {
                    locateProvinceCode = UserManager.getInstance().getLocateProvinceCode();
                    locateCityCode = UserManager.getInstance().getLocateCityCode();
                }
                observableEmitter.onNext(SearchManager.this.searchHuoDongBox.query().equal(SearchHuoDongEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).equal(SearchHuoDongEntity_.proviceCode, locateProvinceCode).equal(SearchHuoDongEntity_.cityCode, locateCityCode).build().find());
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable loadScrollKeywordFromNetwork() {
        return Observable.create(new ObservableOnSubscribe<List<SearchEntity>>() { // from class: com.sinovatech.unicom.separatemodule.search.SearchManager.4
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<List<SearchEntity>> observableEmitter) throws Exception {
                String locateProvinceCode;
                String locateCityCode;
                String str;
                if (App.hasLogined()) {
                    locateProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
                    locateCityCode = UserManager.getInstance().getUserAreaid();
                    str = "Y";
                } else {
                    locateProvinceCode = UserManager.getInstance().getLocateProvinceCode();
                    locateCityCode = UserManager.getInstance().getLocateCityCode();
                    str = "N";
                }
                HashMap hashMap = new HashMap();
                hashMap.put("methodType", "searchScroll");
                hashMap.put("channelCode", "113000005");
                hashMap.put("version", SearchManager.this.context.getString(2131886969));
                hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
                hashMap.put("loginType", str);
                hashMap.put("provinceCode", locateProvinceCode);
                hashMap.put("cityCode", locateCityCode);
                List<SearchEntity> parseHotKeywordJsonData = SearchManager.this.parseHotKeywordJsonData(App.getAsyncHttpClient().syncGet(URLSet.getDataFromService(), hashMap), locateProvinceCode, locateCityCode);
                if (parseHotKeywordJsonData != null) {
                    SearchManager.this.searchBox.remove((Collection) SearchManager.this.searchBox.query().equal(SearchEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).equal(SearchEntity_.proviceCode, locateProvinceCode).equal(SearchEntity_.cityCode, locateCityCode).build().find());
                    SearchManager.this.searchBox.put((Collection) parseHotKeywordJsonData);
                }
                observableEmitter.onNext(parseHotKeywordJsonData);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable loadHuoDongFromNetwork(final ManagerLocation.LocationEntity locationEntity) {
        return Observable.create(new ObservableOnSubscribe<List<SearchHuoDongEntity>>() { // from class: com.sinovatech.unicom.separatemodule.search.SearchManager.5
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<List<SearchHuoDongEntity>> observableEmitter) throws Exception {
                String locateProvinceCode;
                String locateCityCode;
                List<SearchHuoDongEntity> arrayList = new ArrayList<>();
                ManagerLocation.LocationEntity locationEntity2 = locationEntity;
                if (locationEntity2 != null && locationEntity2.isLocationSuccess()) {
                    if (App.hasLogined()) {
                        locateProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
                        locateCityCode = UserManager.getInstance().getUserAreaid();
                    } else {
                        locateProvinceCode = UserManager.getInstance().getLocateProvinceCode();
                        locateCityCode = UserManager.getInstance().getLocateCityCode();
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("longitude", locationEntity.getBdLocation().getLongitude() + "");
                    hashMap.put("latitude", locationEntity.getBdLocation().getLatitude() + "");
                    hashMap.put("version", SearchManager.this.context.getString(2131886969));
                    hashMap.put("provinceCode", locateProvinceCode);
                    hashMap.put("cityCode", locateCityCode);
                    UIUtils.logD("HomeFragment url = ", URLSet.getHomeHuoDongData());
                    UIUtils.logD("HomeFragment params = ", hashMap.toString());
                    List<SearchHuoDongEntity> parseHuoDongJsonData = SearchManager.this.parseHuoDongJsonData(App.getAsyncHttpClient().syncGet(URLSet.getHomeHuoDongData(), hashMap), locateProvinceCode, locateCityCode);
                    if (parseHuoDongJsonData != null) {
                        SearchManager.this.searchHuoDongBox.remove((Collection) SearchManager.this.searchHuoDongBox.query().equal(SearchHuoDongEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).equal(SearchHuoDongEntity_.proviceCode, locateProvinceCode).equal(SearchHuoDongEntity_.cityCode, locateCityCode).build().find());
                        SearchManager.this.searchHuoDongBox.put((Collection) parseHuoDongJsonData);
                    }
                    arrayList = parseHuoDongJsonData;
                }
                observableEmitter.onNext(arrayList);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<SearchEntity> parseHotKeywordJsonData(String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("113000005");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray optJSONArray = jSONArray.optJSONArray(i);
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    String optString = optJSONObject.optString("searchWordName");
                    String optString2 = optJSONObject.optString("imgUrl");
                    SearchEntity searchEntity = new SearchEntity();
                    searchEntity.setTitle(optString);
                    searchEntity.setImageURL(optString2);
                    searchEntity.setUrl(optJSONObject.optString("linkUrl"));
                    searchEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
                    searchEntity.setProviceCode(str2);
                    searchEntity.setCityCode(str3);
                    arrayList.add(searchEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<SearchHuoDongEntity> parseHuoDongJsonData(String str, String str2, String str3) {
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (TextUtils.equals("0000", jSONObject.optString("code")) && (optJSONArray = jSONObject.optJSONArray("dataList")) != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    String optString = optJSONObject.optString("searchWordName");
                    String optString2 = optJSONObject.optString("time");
                    String optString3 = optJSONObject.optString("id");
                    String optString4 = optJSONObject.optString("hallId");
                    String optString5 = optJSONObject.optString("actType");
                    String optString6 = optJSONObject.optString("linkUrl");
                    String optString7 = optJSONObject.optString("isNeedLogin");
                    SearchHuoDongEntity searchHuoDongEntity = new SearchHuoDongEntity();
                    searchHuoDongEntity.setActType(optString5);
                    searchHuoDongEntity.setSearchWordName(optString);
                    searchHuoDongEntity.setTime(optString2);
                    searchHuoDongEntity.setHallId(optString4);
                    searchHuoDongEntity.setHuodongId(optString3);
                    searchHuoDongEntity.setLinkUrl(optString6);
                    searchHuoDongEntity.setIsNeedLogin(optString7);
                    searchHuoDongEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
                    searchHuoDongEntity.setProviceCode(str2);
                    searchHuoDongEntity.setCityCode(str3);
                    arrayList.add(searchHuoDongEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
