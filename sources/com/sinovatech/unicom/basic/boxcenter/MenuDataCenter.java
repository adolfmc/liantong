package com.sinovatech.unicom.basic.boxcenter;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.datacenter.HistoryDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuBox;
import com.sinovatech.unicom.basic.p314po.MenuBox_;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p314po.ServiceListItem;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsEntity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MenuDataCenter {
    public static final String all_service = "allService";
    public static final String all_smenu = "allMenu";
    public static final String anquanzhongxin = "anquanzhongxin";
    public static final String chagnyong = "changyong";
    public static final String custom = "customTwo";
    public static final String home = "hometwo";
    public static final String homeBox = "homebox";
    private static final String homediff = "homediff";
    public static final String homefour = "homeFour";
    public static final String homesix = "homesix";
    public static final String hometop = "hometop";
    public static final String main = "main";
    private static MenuDataCenter menuDataCenter = null;
    public static final String myUnicom = "myUnicom";
    private static final String newhome = "homeMenuItems";
    public static final String query = "query";
    public static final String timestamp = "timeStamp";
    private String TAG = MenuDataCenter.class.getSimpleName();
    private Box<MenuBox> box = App.getBoxStore().boxFor(MenuBox.class);
    private UserManager userManager = UserManager.getInstance();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface JGQSkinListener {
        void onSuccess();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LoadMenuInterface {
        void onSuccess();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LoadMenuYuYue {
        void onSuccess(String str);
    }

    public static synchronized MenuDataCenter getInstance() {
        MenuDataCenter menuDataCenter2;
        synchronized (MenuDataCenter.class) {
            if (menuDataCenter == null) {
                menuDataCenter = new MenuDataCenter();
            }
            menuDataCenter2 = menuDataCenter;
        }
        return menuDataCenter2;
    }

    public String getVersion() {
        MenuBox findFirst = this.box.query().equal(MenuBox_.mobile, this.userManager.getCurrentPhoneNumber() + LanguageUtil.getInstance().getMoileSuffix()).and().equal(MenuBox_.proCode, this.userManager.getCurrentProvinceCode()).build().findFirst();
        if (findFirst == null || TextUtils.isEmpty(findFirst.getContent())) {
            return String.valueOf(System.currentTimeMillis());
        }
        try {
            List<MenuEntity> pageTagList = getPageTagList(new JSONObject(findFirst.getContent()), timestamp);
            MenuEntity menuEntity = pageTagList.get(0);
            if (menuEntity.isGroup()) {
                menuEntity = pageTagList.get(1);
            }
            return menuEntity.getTimeStamp();
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(System.currentTimeMillis());
        }
    }

    public void updateData(String str, String str2, String str3) {
        try {
            new JSONObject(str);
            String str4 = str2 + LanguageUtil.getInstance().getMoileSuffix();
            MenuBox findFirst = this.box.query().equal(MenuBox_.mobile, str4).and().equal(MenuBox_.proCode, str3).build().findFirst();
            if (findFirst != null) {
                findFirst.setContent(str);
            } else {
                findFirst = new MenuBox();
                findFirst.setContent(str);
                findFirst.setMobile(str4);
                findFirst.setProCode(str3);
            }
            this.box.put((Box<MenuBox>) findFirst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<MenuEntity> getHomeTopData() {
        return getMenuData(homeBox);
    }

    public List<MenuEntity> getHomeData() {
        List<MenuEntity> newHomeList;
        new ArrayList();
        if (UserManager.getInstance().isYiwang()) {
            newHomeList = getMenuData(home);
        } else {
            newHomeList = getNewHomeList(newhome, newhome);
        }
        return App.hasLogined() ? !UserManager.getInstance().isYiwang() ? newHomeList.size() > 10 ? newHomeList.subList(0, 10) : newHomeList : newHomeList.size() > 20 ? newHomeList.subList(0, 20) : newHomeList : newHomeList.size() > 10 ? newHomeList.subList(0, 10) : newHomeList;
    }

    public void showShaiXuan() {
        try {
            Gson gson = new Gson();
            List<MenuEntity> allMenuList = getAllMenuList();
            String fuWuTopList = CacheDataCenter.getInstance().getFuWuTopList(UserManager.getInstance().getCurrentPhoneNumber());
            if (TextUtils.isEmpty(fuWuTopList)) {
                return;
            }
            Type type = new TypeToken<List<MenuEntity>>() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.1
            }.getType();
            Object fromJson = !(gson instanceof Gson) ? gson.fromJson(fuWuTopList, type) : NBSGsonInstrumentation.fromJson(gson, fuWuTopList, type);
            ArrayList arrayList = new ArrayList();
            ListIterator listIterator = ((List) fromJson).listIterator();
            while (listIterator.hasNext()) {
                String menuTitle = ((MenuEntity) listIterator.next()).getMenuTitle();
                boolean z = false;
                Iterator<MenuEntity> it = allMenuList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MenuEntity next = it.next();
                    if (menuTitle.equals(next.getMenuTitle())) {
                        next.setCustom(true);
                        arrayList.add(next);
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    listIterator.remove();
                    StatisticsUploadUtils.uploadRealTimeBeiDong(App.getInstance().getApplicationContext(), "menuDelete", "", "删除导航", "", menuTitle, "");
                }
            }
            if (arrayList.size() > 0) {
                CacheDataCenter.getInstance().setFuWuTopList(!(gson instanceof Gson) ? gson.toJson(arrayList) : NBSGsonInstrumentation.toJson(gson, arrayList), UserManager.getInstance().getCurrentPhoneNumber());
            } else {
                CacheDataCenter.getInstance().setFuWuTopList("", UserManager.getInstance().getCurrentPhoneNumber());
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(this.TAG, "全量菜单数据筛选出现异常：" + e.getMessage());
        }
    }

    public List<MenuEntity> getHomeDataNew() {
        new ArrayList();
        List<MenuEntity> homeDataNew = getHomeDataNew(true);
        return (App.hasLogined() && UserManager.getInstance().isYiwang()) ? homeDataNew.size() > 5 ? homeDataNew.subList(0, 5) : homeDataNew : homeDataNew.size() > 8 ? homeDataNew.subList(0, 8) : homeDataNew;
    }

    public List<MenuEntity> getHomeDataNew(boolean z) {
        String fuWuTopList = CacheDataCenter.getInstance().getFuWuTopList(UserManager.getInstance().getCurrentPhoneNumber());
        if (!TextUtils.isEmpty(fuWuTopList) && z) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<MenuEntity>>() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.2
            }.getType();
            List<MenuEntity> list = (List) (!(gson instanceof Gson) ? gson.fromJson(fuWuTopList, type) : NBSGsonInstrumentation.fromJson(gson, fuWuTopList, type));
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    MenuEntity menuEntity = list.get(i);
                    if (menuEntity != null) {
                        menuEntity.setCustom(true);
                        list.set(i, menuEntity);
                    }
                }
            }
            return (!(App.hasLogined() && UserManager.getInstance().isYiwang()) && list.size() > 8) ? list.subList(0, 8) : list;
        }
        List<MenuEntity> newHomeList = getNewHomeList(newhome, newhome);
        return (!(App.hasLogined() && UserManager.getInstance().isYiwang()) && newHomeList.size() > 8) ? newHomeList.subList(0, 8) : newHomeList;
    }

    public List<MenuEntity> getServiceByTag(String str) {
        return getMenuData(str);
    }

    public List<MenuEntity> getUserList() {
        return getMenuDataByGroup(this.userManager.getCurrentPhoneNumber(), this.userManager.getCurrentProvinceCode(), myUnicom, "我的业务");
    }

    public List<MenuEntity> getSettingList2() {
        return getMenuDataByGroup(this.userManager.getCurrentPhoneNumber(), this.userManager.getCurrentProvinceCode(), myUnicom, "11.1版本后设置配置");
    }

    public List<MenuEntity> getAnquanzhongxin() {
        return getMenuData(anquanzhongxin);
    }

    public List<MenuEntity> getAllMenuList() {
        List<MenuEntity> menuData = getMenuData(all_smenu);
        menuData.addAll(getUserList());
        return menuData;
    }

    public List<MenuEntity> getMenuData(String str) {
        return getMenuData(str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0215 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.sinovatech.unicom.basic.p314po.MenuEntity> getMenuData(java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.getMenuData(java.lang.String, boolean):java.util.List");
    }

    public List<MenuEntity> getMenuDataByGroup(String str, String str2, String str3, String str4) {
        ArrayList arrayList = new ArrayList();
        List<MenuEntity> menuData = getMenuData(str3, true);
        int i = 0;
        while (true) {
            if (i >= menuData.size()) {
                break;
            } else if (menuData.get(i).isGroup() && menuData.get(i).getMenuTitle().equals(str4)) {
                for (int i2 = i + 1; i2 < menuData.size() && !menuData.get(i2).isGroup(); i2++) {
                    arrayList.add(menuData.get(i2));
                }
            } else {
                i++;
            }
        }
        return arrayList;
    }

    private List<MenuEntity> getPageTagList(JSONObject jSONObject, String str, String str2) {
        ArrayList arrayList;
        JSONArray optJSONArray;
        JSONObject optJSONObject;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        String str3;
        int i;
        boolean z;
        String str4;
        ArrayList arrayList2 = new ArrayList();
        try {
            if (TextUtils.isEmpty(str) || jSONObject == null) {
                return arrayList2;
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject(str);
            if (optJSONObject2 != null && (optJSONArray = optJSONObject2.optJSONArray("sections")) != null) {
                int i2 = 0;
                while (i2 < optJSONArray.length()) {
                    JSONObject optJSONObject3 = optJSONArray.optJSONObject(i2);
                    if (optJSONObject3 != null && (optJSONObject = optJSONObject3.optJSONObject("section")) != null) {
                        String optString = optJSONObject.optString("title");
                        MenuEntity menuEntity = new MenuEntity();
                        menuEntity.setMenuTitle(optString);
                        boolean z2 = true;
                        menuEntity.setGroup(true);
                        menuEntity.setViewType(1);
                        menuEntity.setMenuId("-999999999");
                        if (!TextUtils.isEmpty(str2)) {
                            menuEntity.setGroupTitle(str2);
                            if (i2 == 0) {
                                menuEntity.setFirst(true);
                            }
                        }
                        arrayList2.add(menuEntity);
                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("menuItems");
                        if (optJSONArray2 != null) {
                            int i3 = 0;
                            while (i3 < optJSONArray2.length()) {
                                JSONObject optJSONObject4 = optJSONArray2.optJSONObject(i3);
                                if (optJSONObject4 != null) {
                                    String optString2 = optJSONObject4.optString("id");
                                    String optString3 = optJSONObject4.optString("title");
                                    String optString4 = optJSONObject4.optString("iconInCollection");
                                    String optString5 = optJSONObject4.optString("iconUrl");
                                    String optString6 = optJSONObject4.optString("isNeedLogin");
                                    String optString7 = optJSONObject4.optString("url");
                                    String optString8 = optJSONObject4.optString("backMode");
                                    String optString9 = optJSONObject4.optString("showLace");
                                    jSONArray = optJSONArray;
                                    String optString10 = optJSONObject4.optString("menuLaceIcon");
                                    jSONArray2 = optJSONArray2;
                                    String optString11 = optJSONObject4.optString("isShowRedPoint");
                                    i = i3;
                                    String optString12 = optJSONObject4.optString("showRedPointValue");
                                    ArrayList arrayList3 = arrayList2;
                                    try {
                                        String optString13 = optJSONObject4.optString(timestamp);
                                        int i4 = i2;
                                        String optString14 = optJSONObject4.optString("default");
                                        String str5 = optString;
                                        String optString15 = optJSONObject4.optString("broadband");
                                        String optString16 = optJSONObject4.optString("navJson");
                                        String optString17 = optJSONObject4.optString("isFixedPhone");
                                        String optString18 = optJSONObject4.optString("menuAliasName");
                                        MenuEntity menuEntity2 = new MenuEntity();
                                        menuEntity2.setMenuAliasName(optString18);
                                        menuEntity2.setGroup(false);
                                        menuEntity2.setMenuId(optString2);
                                        menuEntity2.setCaidaiMenuId("caidai" + optString2);
                                        menuEntity2.setMenuTitle(optString3);
                                        menuEntity2.setMenuIconURL(optString5);
                                        menuEntity2.setMenuIconURLInCollection(optString4);
                                        menuEntity2.setTimeStamp(optString13);
                                        menuEntity2.setMenuDefault(optString14);
                                        menuEntity2.setNavJson(optString16);
                                        menuEntity2.setGroupTitle(str2);
                                        if ("YES".equals(optString6)) {
                                            menuEntity2.setNeedLogin(true);
                                        } else {
                                            menuEntity2.setNeedLogin(false);
                                        }
                                        if ("YES".equalsIgnoreCase(optString9)) {
                                            menuEntity2.setShowCaiDai(true);
                                        } else {
                                            menuEntity2.setShowCaiDai(false);
                                        }
                                        menuEntity2.setCaiDaiIcon(optString10);
                                        menuEntity2.setMenuURL(optString7);
                                        menuEntity2.setBackMode(optString8);
                                        menuEntity2.setGuhua(optString17);
                                        if ("YES".equals(optString11)) {
                                            z = true;
                                            menuEntity2.setNewItem(true);
                                            str4 = optString12;
                                        } else {
                                            z = true;
                                            menuEntity2.setNewItem(false);
                                            str4 = optString12;
                                        }
                                        menuEntity2.setNewItemTag(str4);
                                        str3 = str5;
                                        menuEntity2.setSectionsTitle(str3);
                                        i2 = i4;
                                        menuEntity2.setSectionsIndex(i2);
                                        menuEntity2.setBroadband(optString15);
                                        menuEntity2.setDiffType(optJSONObject4.optString("diffType"));
                                        menuEntity2.setLaceContextTime(optJSONObject4.optLong("laceContextTime"));
                                        menuEntity2.setLaceContext(optJSONObject4.optString("laceContext"));
                                        arrayList = arrayList3;
                                        try {
                                            arrayList.add(menuEntity2);
                                        } catch (Exception e) {
                                            e = e;
                                            e.printStackTrace();
                                            return arrayList;
                                        }
                                    } catch (Exception e2) {
                                        e = e2;
                                        arrayList = arrayList3;
                                    }
                                } else {
                                    jSONArray = optJSONArray;
                                    jSONArray2 = optJSONArray2;
                                    str3 = optString;
                                    i = i3;
                                    arrayList = arrayList2;
                                    z = z2;
                                }
                                i3 = i + 1;
                                z2 = z;
                                optString = str3;
                                arrayList2 = arrayList;
                                optJSONArray = jSONArray;
                                optJSONArray2 = jSONArray2;
                            }
                            continue;
                        } else {
                            continue;
                        }
                    }
                    i2++;
                    arrayList2 = arrayList2;
                    optJSONArray = optJSONArray;
                }
                return arrayList2;
            }
            return arrayList2;
        } catch (Exception e3) {
            e = e3;
            arrayList = arrayList2;
        }
    }

    private List<MenuEntity> getPageTagList(JSONObject jSONObject, String str) {
        return getPageTagList(jSONObject, str, "");
    }

    public MenuBox getMenuEntity() {
        String str = this.userManager.getCurrentPhoneNumber() + LanguageUtil.getInstance().getMoileSuffix();
        String currentProvinceCode = this.userManager.getCurrentProvinceCode();
        MenuBox findFirst = this.box.query().equal(MenuBox_.mobile, str).and().equal(MenuBox_.proCode, currentProvinceCode).build().findFirst();
        if (findFirst == null) {
            findFirst = this.box.query().equal(MenuBox_.mobile, "0_").and().equal(MenuBox_.proCode, currentProvinceCode).build().findFirst();
        }
        if (findFirst == null) {
            findFirst = this.box.query().equal(MenuBox_.mobile, "0_").and().equal(MenuBox_.proCode, "098").build().findFirst();
        }
        if (findFirst == null) {
            MenuBox menuBox = new MenuBox();
            menuBox.setMobile(str);
            menuBox.setProCode(currentProvinceCode);
            return menuBox;
        }
        return findFirst;
    }

    private List<MenuEntity> changeList(List<StatisticsEntity> list, List<MenuEntity> list2, HistoryDataCenter historyDataCenter) {
        ArrayList arrayList = new ArrayList();
        try {
            MenuEntity menuEntity = new MenuEntity();
            menuEntity.setMenuTitle("最近使用");
            menuEntity.setGroupTitle("常用");
            menuEntity.setGroup(true);
            menuEntity.setViewType(1);
            menuEntity.setFirst(true);
            arrayList.add(menuEntity);
            for (StatisticsEntity statisticsEntity : list) {
                boolean z = false;
                String titleName = statisticsEntity.getTitleName();
                String str = "";
                try {
                    if (!TextUtils.isEmpty(statisticsEntity.getMenuId())) {
                        str = new JSONObject(statisticsEntity.getMenuId()).optString("id");
                    }
                } catch (Exception e) {
                    String menuId = statisticsEntity.getMenuId();
                    UIUtils.logE(e.getMessage());
                    str = menuId;
                }
                if (TextUtils.isEmpty(str)) {
                    Iterator<MenuEntity> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        MenuEntity next = it.next();
                        if (TextUtils.equals("交费充值", titleName) && TextUtils.equals(next.getMenuTitle(), titleName)) {
                            next.setGroupTitle("常用");
                            arrayList.add(next);
                            z = true;
                            break;
                        }
                    }
                } else {
                    Iterator<MenuEntity> it2 = list2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        MenuEntity next2 = it2.next();
                        if (str.equals(next2.getMenuId())) {
                            next2.setGroupTitle("常用");
                            arrayList.add(next2);
                            z = true;
                            break;
                        }
                    }
                }
                if (!z) {
                    new HistoryDataCenter(App.getInstance()).deleteStatisticsRecord(statisticsEntity);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public List<MenuEntity> getCustomList(String str) {
        boolean z;
        List<MenuEntity> allMenuList = getAllMenuList();
        List<MenuEntity> arrayList = new ArrayList<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("customArray");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString = optJSONArray.optJSONObject(i).optString("title");
                    Iterator<MenuEntity> it = allMenuList.iterator();
                    while (true) {
                        z = true;
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        MenuEntity next = it.next();
                        if (optString.equals(next.getMenuTitle())) {
                            next.setCustom(true);
                            arrayList.add(next);
                            break;
                        }
                    }
                    if (!z) {
                        optJSONArray.remove(i);
                        StatisticsUploadUtils.uploadRealTimeBeiDong(App.getInstance().getApplicationContext(), "menuDelete", "", "删除导航", "", optString, "");
                        jSONObject.put("customArray", optJSONArray);
                        updateCustom(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        UIUtils.logD("getCustomList", "--->" + System.currentTimeMillis());
        if (arrayList.size() > 14) {
            arrayList = arrayList.subList(0, 14);
        }
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setMenuTitle("更多");
        arrayList.add(menuEntity);
        UIUtils.logD("getCustomList", "list--->" + arrayList.size());
        return arrayList;
    }

    public void loadData(int i, final LoadMenuInterface loadMenuInterface) {
        MsLogUtil.m7979d("quanliang", "更新全量导航 ====> " + i);
        try {
            final RequestParams requestParams = new RequestParams();
            if (App.hasLogined()) {
                requestParams.put("mobile", this.userManager.getCurrentPhoneNumber());
                requestParams.put("provinceCode", this.userManager.getCurrentProvinceCode());
                requestParams.put("netPayType", this.userManager.getMenuNetType());
                requestParams.put("birthdayMonth", this.userManager.getBirthdayMonth());
            } else {
                requestParams.put("mobile", "");
                requestParams.put("provinceCode", this.userManager.getLocateProvinceCode());
                requestParams.put("netPayType", "");
                requestParams.put("birthdayMonth", "0");
            }
            requestParams.put("miniVersion", "N");
            if (LanguageUtil.getInstance().getConfigLanguageSwitch()) {
                requestParams.put("lang", App.getSharePreferenceUtil().getString(LanguageUtil.LOCALE_KEY_NOW));
            } else {
                requestParams.put("lang", LanguageUtil.CHN_CN);
            }
            requestParams.put("version", App.getInstance().getString(2131886969));
            String version = menuDataCenter.getVersion();
            if (URLSet.Debug_mode) {
                requestParams.put("channelType", URLSet.huanjingType);
            }
            if (URLEnvironmentConfig.isForPublish()) {
                if (TextUtils.isEmpty(version)) {
                    version = String.valueOf(System.currentTimeMillis());
                }
                requestParams.put("timestamp", version);
            } else {
                requestParams.put("timestamp", String.valueOf(System.currentTimeMillis()));
            }
            UIUtils.logD("LanguageUtils", requestParams.toString());
            App.getAsyncHttpClient().get(URLSet.getNewNavigation(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.3
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i2, String str) {
                    super.onSuccess(i2, str);
                    try {
                        UIUtils.logD("LanguageUtils", "----" + str);
                        if (App.isSuccessful(i2)) {
                            App.getSharePreferenceUtil().putString("home_menu_icon_click", "");
                            new JSONObject(str);
                            String str2 = requestParams.getRealParams().get("mobile");
                            if (TextUtils.isEmpty(str2)) {
                                str2 = "0";
                            }
                            FuWuConstant.isRefreshList = true;
                            MenuDataCenter.menuDataCenter.updateData(str, str2, requestParams.getRealParams().get("provinceCode"));
                            MenuDataCenter.getInstance().showShaiXuan();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFinish() {
                    super.onFinish();
                    loadMenuInterface.onSuccess();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCustom(String str) {
        CacheDataCenter.getInstance().setCustomContentNew(str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public void loadMenuSkin(AppCompatActivity appCompatActivity, final JGQSkinListener jGQSkinListener) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("version", appCompatActivity.getString(2131886969));
            hashMap.put("netType", this.userManager.isYiwang() ? "1" : "0");
            hashMap.put("isLogin", App.hasLogined() ? "Y" : "N");
            hashMap.put("provinceCode", this.userManager.getCurrentProvinceCode());
            hashMap.put("cityCode", this.userManager.getCurrentCityCode());
            App.getAsyncHttpClient().rxGet(URLSet.getMenuSkin(), hashMap).subscribeOn(Schedulers.m1934io()).map(new Function<String, Boolean>() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.6
                @Override // io.reactivex.functions.Function
                public Boolean apply(@NonNull String str) throws Exception {
                    try {
                        if (!TextUtils.isEmpty(str) && TextUtils.equals("0000", new JSONObject(str).optString("code"))) {
                            CacheDataCenter.getInstance().setJGQSkinData(str);
                            return true;
                        }
                    } catch (Exception e) {
                        String str2 = MenuDataCenter.this.TAG;
                        MsLogUtil.m7977e(str2, "解析金刚区换肤数据异常:" + e.getMessage());
                    }
                    return false;
                }
            }).observeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.4
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    if (!bool.booleanValue()) {
                        CacheDataCenter.getInstance().setJGQSkinData("");
                    }
                    JGQSkinListener jGQSkinListener2 = jGQSkinListener;
                    if (jGQSkinListener2 != null) {
                        jGQSkinListener2.onSuccess();
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.5
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str = MenuDataCenter.this.TAG;
                    MsLogUtil.m7977e(str, "金刚区接口请求异常:" + th.getMessage());
                    CacheDataCenter.getInstance().setJGQSkinData("");
                    JGQSkinListener jGQSkinListener2 = jGQSkinListener;
                    if (jGQSkinListener2 != null) {
                        jGQSkinListener2.onSuccess();
                    }
                }
            });
        } catch (Exception e) {
            String str = this.TAG;
            MsLogUtil.m7977e(str, "金刚区请求异常:" + e.getMessage());
        }
    }

    public void loadMenuJinGangQu(AppCompatActivity appCompatActivity, final LoadMenuInterface loadMenuInterface) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("version", appCompatActivity.getString(2131886969));
            hashMap.put("siteCode", "home");
            hashMap.put("timestamp", getNewTemp("home"));
            hashMap.put("provinceCode", this.userManager.getCurrentProvinceCode());
            hashMap.put("cityCode", this.userManager.getCurrentCityCode());
            hashMap.put("netPayType", this.userManager.getMenuNetType());
            App.getAsyncHttpClient().rxGet(URLSet.getMenuUrl(), hashMap).subscribeOn(Schedulers.m1934io()).map(new Function<String, Boolean>() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.9
                @Override // io.reactivex.functions.Function
                public Boolean apply(@NonNull String str) throws Exception {
                    JSONObject jSONObject = new JSONObject(str);
                    String optString = jSONObject.optString("code");
                    JSONObject optJSONObject = jSONObject.optJSONObject("datas");
                    if ("0000".equals(optString) && optJSONObject != null) {
                        JSONArray optJSONArray = optJSONObject.optJSONArray(MenuDataCenter.newhome);
                        if (optJSONArray == null || optJSONArray.length() == 0) {
                            MenuDataCenter.this.setNewHomeData(MenuDataCenter.newhome, str);
                            return false;
                        }
                        if ((!App.hasLogined() || !UserManager.getInstance().isYiwang() || optJSONArray.length() >= 5) && optJSONArray.length() >= 4) {
                            MenuDataCenter.this.setNewHomeData(MenuDataCenter.newhome, str);
                            MenuDataCenter.getInstance().showShaiXuan();
                        }
                        return false;
                    }
                    return false;
                }
            }).observeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Boolean bool) throws Exception {
                    loadMenuInterface.onSuccess();
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.boxcenter.MenuDataCenter.8
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    String str = MenuDataCenter.this.TAG;
                    MsLogUtil.m7977e(str, "金刚区接口请求异常:" + th.getMessage());
                    loadMenuInterface.onSuccess();
                }
            });
        } catch (Exception e) {
            String str = this.TAG;
            MsLogUtil.m7977e(str, "金刚区请求异常:" + e.getMessage());
        }
    }

    public List<MenuEntity> getUserMenuData(String str) {
        return getNewHomeList("myPage", str);
    }

    public List<MenuEntity> getNewHomeTopData() {
        return getNewHomeList("home", "top");
    }

    public void setNewHomeData(String str, String str2) {
        String str3 = this.userManager.getCurrentPhoneNumber() + str + "_new_home";
        try {
            new JSONObject(str2);
            MenuBox findFirst = this.box.query().equal(MenuBox_.mobile, str3).and().equal(MenuBox_.proCode, "0").build().findFirst();
            if (findFirst != null) {
                findFirst.setContent(str2);
            } else {
                findFirst = new MenuBox();
                findFirst.setContent(str2);
                findFirst.setMobile(str3);
                findFirst.setProCode("0");
            }
            this.box.put((Box<MenuBox>) findFirst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<MenuEntity> getNewHomeList(String str, String str2) {
        JSONObject optJSONObject;
        List<MenuEntity> arrayList = new ArrayList<>();
        try {
            String str3 = this.userManager.getCurrentPhoneNumber() + str + "_new_home";
            MenuBox findFirst = this.box.query().equal(MenuBox_.mobile, str3).and().equal(MenuBox_.proCode, "0").build().findFirst();
            if (findFirst == null) {
                findFirst = new MenuBox();
                findFirst.setMobile(str3);
                findFirst.setProCode("0");
            }
            String content = findFirst.getContent();
            JSONArray jSONArray = null;
            if (!TextUtils.isEmpty(content) && (optJSONObject = new JSONObject(content).optJSONObject("datas")) != null) {
                jSONArray = optJSONObject.optJSONArray(str2);
            }
            if (jSONArray != null && jSONArray.length() > 0) {
                arrayList = getJingangquList(jSONArray);
            }
            if (UserManager.getInstance().isYiwang() && (arrayList == null || arrayList.size() < 5)) {
                jSONArray = new JSONObject(FileTools.readInputStream(App.getInstance().getResources().getAssets().open("new_menu_ijngangqu_yw.json"))).optJSONObject("datas").optJSONArray(str2);
            } else if (arrayList == null || arrayList.size() < 4) {
                String str4 = "new_menu_ijngangqu_bw.json";
                if (TextUtils.equals("0", LoginManager.getAccountTypeUser())) {
                    str4 = "new_menu_ijngangqu_unlogin.json";
                } else {
                    if (!TextUtils.equals("1", LoginManager.getAccountTypeUser()) && !TextUtils.equals("5", LoginManager.getAccountTypeUser())) {
                        if (TextUtils.equals("2", LoginManager.getAccountTypeUser())) {
                            str4 = "new_menu_ijngangqu_yw.json";
                        } else if (TextUtils.equals("3", LoginManager.getAccountTypeUser())) {
                            str4 = "new_menu_ijngangqu_kd.json";
                        } else if (TextUtils.equals("4", LoginManager.getAccountTypeUser())) {
                            str4 = "new_menu_ijngangqu_gh.json";
                        }
                    }
                    str4 = "new_menu_ijngangqu_bw.json";
                }
                jSONArray = new JSONObject(FileTools.readInputStream(App.getInstance().getResources().getAssets().open(str4))).optJSONObject("datas").optJSONArray(str2);
            }
            return jSONArray != null ? getJingangquList(jSONArray) : arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    private List<MenuEntity> getJingangquList(JSONArray jSONArray) {
        int i;
        ArrayList arrayList;
        boolean z;
        String str;
        ArrayList arrayList2 = new ArrayList();
        int i2 = 0;
        while (i2 < jSONArray.length()) {
            MenuEntity menuEntity = new MenuEntity();
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("id");
                String optString2 = optJSONObject.optString("title", "");
                if (TextUtils.isEmpty(optString2.trim())) {
                    i = i2;
                    arrayList = arrayList2;
                } else {
                    String optString3 = optJSONObject.optString("iconInCollection");
                    String optString4 = optJSONObject.optString("iconUrl", "");
                    if (TextUtils.isEmpty(optString4.trim())) {
                        i = i2;
                        arrayList = arrayList2;
                    } else {
                        String optString5 = optJSONObject.optString("isNeedLogin");
                        String optString6 = optJSONObject.optString("url", "");
                        if (TextUtils.isEmpty(optString6.trim())) {
                            i = i2;
                            arrayList = arrayList2;
                        } else {
                            String optString7 = optJSONObject.optString("backMode");
                            String optString8 = optJSONObject.optString("showLace");
                            String optString9 = optJSONObject.optString("menuLaceIcon");
                            String optString10 = optJSONObject.optString("isShowRedPoint");
                            String optString11 = optJSONObject.optString("showRedPointValue");
                            String optString12 = optJSONObject.optString(timestamp);
                            ArrayList arrayList3 = arrayList2;
                            String optString13 = optJSONObject.optString("broadband");
                            String optString14 = optJSONObject.optString("navJson");
                            int i3 = i2;
                            String optString15 = optJSONObject.optString("iconStarUrl");
                            String optString16 = optJSONObject.optString("isFixedPhone");
                            String optString17 = optJSONObject.optString("default");
                            String optString18 = optJSONObject.optString("isEdit");
                            menuEntity.setMenuAliasName(optJSONObject.optString("menuAliasName"));
                            menuEntity.setIsEdit(optString18);
                            menuEntity.setGroup(false);
                            menuEntity.setMenuId(optString);
                            menuEntity.setCaidaiMenuId("caidai" + optString);
                            menuEntity.setMenuTitle(optString2);
                            menuEntity.setMenuIconURL(optString4);
                            menuEntity.setMenuIconURLInCollection(optString3);
                            menuEntity.setTimeStamp(optString12);
                            menuEntity.setMenuDefault(optString17);
                            menuEntity.setNavJson(optString14);
                            menuEntity.setIconStarUrl(optString15);
                            if ("YES".equals(optString5)) {
                                menuEntity.setNeedLogin(true);
                                z = false;
                            } else {
                                z = false;
                                menuEntity.setNeedLogin(false);
                            }
                            if ("YES".equalsIgnoreCase(optString8)) {
                                menuEntity.setShowCaiDai(true);
                            } else {
                                menuEntity.setShowCaiDai(z);
                            }
                            menuEntity.setCaiDaiIcon(optString9);
                            menuEntity.setMenuURL(optString6);
                            menuEntity.setBackMode(optString7);
                            menuEntity.setGuhua(optString16);
                            if ("YES".equals(optString10)) {
                                menuEntity.setNewItem(true);
                                str = optString11;
                            } else {
                                menuEntity.setNewItem(z);
                                str = optString11;
                            }
                            menuEntity.setNewItemTag(str);
                            i = i3;
                            menuEntity.setSectionsIndex(i);
                            menuEntity.setBroadband(optString13);
                            menuEntity.setDiffType(optJSONObject.optString("diffType"));
                            menuEntity.setLaceContextTime(optJSONObject.optLong("laceContextTime"));
                            menuEntity.setLaceContext(optJSONObject.optString("laceContext"));
                            arrayList = arrayList3;
                            arrayList.add(menuEntity);
                        }
                    }
                }
            } else {
                i = i2;
                arrayList = arrayList2;
            }
            arrayList2 = arrayList;
            i2 = i + 1;
        }
        return arrayList2;
    }

    private String getNewTemp(String str) {
        String str2 = "0";
        try {
            String str3 = this.userManager.getCurrentPhoneNumber() + str + "_new_home";
            MenuBox findFirst = this.box.query().equal(MenuBox_.mobile, str3).and().equal(MenuBox_.proCode, "0").build().findFirst();
            if (findFirst == null) {
                findFirst = this.box.query().equal(MenuBox_.mobile, "0_new_home").and().equal(MenuBox_.proCode, "0").build().findFirst();
            }
            if (findFirst == null) {
                findFirst = this.box.query().equal(MenuBox_.mobile, "0_new_home").and().equal(MenuBox_.proCode, "098").build().findFirst();
            }
            if (findFirst == null) {
                findFirst = new MenuBox();
                findFirst.setMobile(str3);
                findFirst.setProCode("0");
            }
            str2 = new JSONObject(findFirst.getContent()).optString("timestamp");
            return TextUtils.isEmpty(str2) ? "0" : str2;
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public List<MenuEntity> getZuiJinShiYongMenu(int i) {
        List<MenuEntity> menuData = getMenuData(all_smenu);
        HistoryDataCenter historyDataCenter = new HistoryDataCenter(App.getInstance());
        return changeYwList(historyDataCenter.getAllStatisticsRecord(UserManager.getInstance().getCurrentPhoneNumber(), i), menuData, historyDataCenter);
    }

    private List<MenuEntity> changeYwList(List<StatisticsEntity> list, List<MenuEntity> list2, HistoryDataCenter historyDataCenter) {
        ArrayList arrayList = new ArrayList();
        try {
            for (StatisticsEntity statisticsEntity : list) {
                boolean z = false;
                String titleName = statisticsEntity.getTitleName();
                String str = "";
                try {
                    if (!TextUtils.isEmpty(statisticsEntity.getMenuId())) {
                        str = new JSONObject(statisticsEntity.getMenuId()).optString("id");
                    }
                } catch (Exception e) {
                    String menuId = statisticsEntity.getMenuId();
                    UIUtils.logE(e.getMessage());
                    str = menuId;
                }
                if (TextUtils.isEmpty(str)) {
                    Iterator<MenuEntity> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        MenuEntity next = it.next();
                        if (TextUtils.equals("交费充值", titleName) && TextUtils.equals(next.getMenuTitle(), titleName)) {
                            z = true;
                            break;
                        }
                    }
                } else {
                    Iterator<MenuEntity> it2 = list2.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        MenuEntity next2 = it2.next();
                        if (str.equals(next2.getMenuId())) {
                            next2.setGroupTitle("常用");
                            arrayList.add(next2);
                            z = true;
                            break;
                        }
                    }
                }
                if (!z) {
                    new HistoryDataCenter(App.getInstance()).deleteStatisticsRecord(statisticsEntity);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public void addHomeJingGangQuCache(String str) {
        CacheDataCenter.getInstance().setCustomContentNew(str, UserManager.getInstance().getCurrentPhoneNumber());
    }

    public String getHomeJingGangQuCache() {
        return CacheDataCenter.getInstance().getCustomContentNew(UserManager.getInstance().getCurrentPhoneNumber());
    }

    public List<ServiceListItem> getServiceListData() {
        return getServiceList();
    }

    private List<ServiceListItem> getServiceList() {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(getMenuEntity().getContent());
            JSONArray optJSONArray = jSONObject.optJSONObject("node").optJSONArray("sections").optJSONObject(0).optJSONObject("section").optJSONArray("menuItems");
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                ServiceListItem serviceListItem = new ServiceListItem();
                serviceListItem.setTitle(optJSONObject.optString("nodeName"));
                serviceListItem.setTag(optJSONObject.optString("nodeCode"));
                if (jSONObject.has(optJSONObject.optString("nodeCode"))) {
                    arrayList.add(serviceListItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public List<MenuEntity> getServiceRecylerData() {
        return getMenuData(all_service, true);
    }

    public boolean isCunZai(String str) {
        try {
            List<MenuEntity> list = FuWuConstant.topList;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    String menuTitle = list.get(i).getMenuTitle();
                    if (!TextUtils.isEmpty(menuTitle) && menuTitle.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("通过标题判断当前金刚区和服务页是否存在此数据", e.getMessage());
        }
        return false;
    }

    public boolean isMaxSize() {
        try {
            List<MenuEntity> list = FuWuConstant.topList;
            if (list != null) {
                return list.size() == 8;
            }
            return false;
        } catch (Exception e) {
            MsLogUtil.m7977e("通过标题判断当前金刚区和服务页是否已经8个数据了", e.getMessage());
            return false;
        }
    }

    public void logList(String str) {
        MsLogUtil.m7979d("查看服务上方数据：" + str, "===========start===============");
        try {
            List<MenuEntity> list = FuWuConstant.topList;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    MsLogUtil.m7980d("查看服务上方数据:标题 = " + list.get(i).getMenuTitle());
                }
            } else {
                MsLogUtil.m7979d("查看服务上方数据:", "没有数据");
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("查看服务上方数据报错：", e.getMessage());
        }
        MsLogUtil.m7979d("查看服务上方数据：" + str, "============end================");
    }
}
