package com.sinovatech.unicom.separatemodule.recentmenu;

import android.text.TextUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity_;
import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecentBoxManager {
    public static final int MENU_TYPE_EDOP = 1;
    public static final int MENU_TYPE_GROUP = 0;
    public static final int MENU_TYPE_NAV = 2;
    public static final int MENU_TYPE_SHOP = 3;
    private static final String TAG = "RecentBoxManager";
    private static RecentBoxManager menuDataCenter;
    private Box<RecentMiniEntity> box = App.getBoxStore().boxFor(RecentMiniEntity.class);
    private UserManager userManager = UserManager.getInstance();

    public static synchronized RecentBoxManager getInstance() {
        RecentBoxManager recentBoxManager;
        synchronized (RecentBoxManager.class) {
            if (menuDataCenter == null) {
                menuDataCenter = new RecentBoxManager();
            }
            recentBoxManager = menuDataCenter;
        }
        return recentBoxManager;
    }

    public List<RecentMiniEntity> get() {
        return get("1", "");
    }

    public List<RecentMiniEntity> get(String str, String str2) {
        List<RecentMiniEntity> find;
        ArrayList arrayList = new ArrayList();
        try {
            new ArrayList();
            if (!TextUtils.isEmpty(str2)) {
                QueryBuilder<RecentMiniEntity> query = this.box.query();
                query.equal(RecentMiniEntity_.mobile, this.userManager.getCurrentPhoneNumber()).and().contains(RecentMiniEntity_.appName, str2).m1948or().contains(RecentMiniEntity_.appDesc, str2).orderDesc(RecentMiniEntity_.timeTemp);
                find = query.build().find();
            } else {
                find = this.box.query().equal(RecentMiniEntity_.mobile, this.userManager.getCurrentPhoneNumber()).orderDesc(RecentMiniEntity_.timeTemp).build().find();
            }
            Iterator<RecentMiniEntity> it = find.iterator();
            List<MenuEntity> allMenuList = MenuDataCenter.getInstance().getAllMenuList();
            HashSet hashSet = new HashSet();
            while (it.hasNext()) {
                RecentMiniEntity next = it.next();
                boolean z = true;
                if (miniEntityType(next) == 2) {
                    Iterator<MenuEntity> it2 = allMenuList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            z = false;
                            break;
                        }
                        MenuEntity next2 = it2.next();
                        if (TextUtils.equals(next.getAppName(), next2.getMenuTitle())) {
                            MsLogUtil.m7979d("JIA-NAV", String.format("名称=%s,本地id=%s,全量id=%s", next.getAppName(), next.getProductId(), next2.getMenuId()));
                        }
                        if (TextUtils.equals(next.getAppName(), next2.getMenuTitle()) && TextUtils.equals(next.getProductId(), next2.getMenuId())) {
                            next.setAppImg(next2.getMenuIconURL());
                            next.setAppName(next2.getMenuTitle());
                            next.setAppletUrl(next2.getMenuURL());
                            next.setProductId(next2.getMenuId());
                            next.setAppId(getCurrentNaviAppId());
                            next.setType(2);
                            MsLogUtil.m7979d("JIA-NAV", "添加成功" + next.getAppName());
                            break;
                        }
                    }
                } else if (miniEntityType(next) == 1) {
                    next.setType(1);
                } else if (miniEntityType(next) == 3) {
                    next.setType(3);
                } else {
                    z = false;
                }
                if (z) {
                    MsLogUtil.m7979d("JIA-NAV", "需要添加" + next.getAppName());
                } else {
                    MsLogUtil.m7979d("JIA-NAV", "不需要添加" + next.getAppName());
                }
                if (z && !TextUtils.isEmpty(next.getAppletUrl()) && !TextUtils.isEmpty(next.getAppName()) && !TextUtils.isEmpty(next.getAppImg()) && !TextUtils.isEmpty(next.getProductId())) {
                    String dateString = next.getDateString();
                    if (TextUtils.equals("2", str) && !hashSet.contains(dateString)) {
                        hashSet.add(dateString);
                        RecentMiniEntity recentMiniEntity = new RecentMiniEntity();
                        recentMiniEntity.setDateString(dateString);
                        recentMiniEntity.setType(0);
                        arrayList.add(recentMiniEntity);
                    }
                    arrayList.add(next);
                }
                it.remove();
                getInstance().reMove(next);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        MsLogUtil.m7979d(TAG, "获取列表：" + arrayList);
        return arrayList;
    }

    public void put(RecentMiniEntity recentMiniEntity) {
        try {
            List<RecentMiniEntity> find = this.box.query().equal(RecentMiniEntity_.mobile, this.userManager.getCurrentPhoneNumber()).and().equal(RecentMiniEntity_.appName, recentMiniEntity.getAppName()).build().find();
            if (find != null && find.size() > 0) {
                for (RecentMiniEntity recentMiniEntity2 : find) {
                    if (recentMiniEntity2 != null) {
                        int miniEntityType = miniEntityType(recentMiniEntity2);
                        int miniEntityType2 = miniEntityType(recentMiniEntity);
                        if (miniEntityType2 == 1) {
                            if (miniEntityType == 3) {
                                if (TextUtils.equals(recentMiniEntity2.getAppletUrl(), recentMiniEntity.getAppletUrl())) {
                                    this.box.remove(recentMiniEntity2.getId());
                                }
                            } else if (miniEntityType == 2 || miniEntityType == 1) {
                                this.box.remove(recentMiniEntity2.getId());
                            }
                        } else if (miniEntityType2 == 2) {
                            if (miniEntityType == 1) {
                                this.box.remove(recentMiniEntity2.getId());
                                recentMiniEntity = recentMiniEntity2;
                            } else if (miniEntityType == 3) {
                                if (TextUtils.equals(recentMiniEntity2.getAppletUrl(), recentMiniEntity.getAppletUrl())) {
                                    this.box.remove(recentMiniEntity2.getId());
                                }
                            } else if (miniEntityType == 2) {
                                this.box.remove(recentMiniEntity2.getId());
                            }
                        } else if (miniEntityType2 == 3) {
                            if (miniEntityType == 3) {
                                if (TextUtils.equals(recentMiniEntity2.getProductId(), recentMiniEntity.getProductId())) {
                                    this.box.remove(recentMiniEntity2.getId());
                                }
                            } else if (miniEntityType == 2 || miniEntityType == 1) {
                                if (TextUtils.equals(recentMiniEntity2.getAppletUrl(), recentMiniEntity.getAppletUrl())) {
                                    this.box.remove(recentMiniEntity2.getId());
                                }
                            }
                        }
                    }
                }
            }
            recentMiniEntity.setMobile(this.userManager.getCurrentPhoneNumber());
            long currentTimeMillis = System.currentTimeMillis();
            recentMiniEntity.setTimeTemp(currentTimeMillis);
            recentMiniEntity.setDateString(TimeUtils.millis2String(currentTimeMillis, "yyyy年MM月dd日"));
            this.box.put((Box<RecentMiniEntity>) recentMiniEntity);
            final List<RecentMiniEntity> find2 = this.box.query().equal(RecentMiniEntity_.mobile, this.userManager.getCurrentPhoneNumber()).build().find();
            if (find2.size() > 500) {
                App.getBoxStore().runInTx(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.RecentBoxManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RecentBoxManager.this.box.remove((Collection) RecentBoxManager.this.box.query().equal(RecentMiniEntity_.mobile, RecentBoxManager.this.userManager.getCurrentPhoneNumber()).build().find(0L, find2.size() - 500));
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void put(MenuEntity menuEntity) {
        RecentMiniEntity recentMiniEntity = new RecentMiniEntity();
        recentMiniEntity.setH5(true);
        recentMiniEntity.setAppName(menuEntity.getMenuTitle());
        recentMiniEntity.setAppId(getCurrentNaviAppId());
        recentMiniEntity.setProductId(menuEntity.getMenuId());
        recentMiniEntity.setAppletUrl(menuEntity.getMenuURL());
        put(recentMiniEntity);
    }

    public void reMove(RecentMiniEntity recentMiniEntity) {
        try {
            RecentMiniEntity findFirst = this.box.query().equal(RecentMiniEntity_.mobile, this.userManager.getCurrentPhoneNumber()).and().equal(RecentMiniEntity_.appId, recentMiniEntity.getAppId()).and().equal(RecentMiniEntity_.appName, recentMiniEntity.getAppName()).and().equal(RecentMiniEntity_.productId, recentMiniEntity.getProductId()).build().findFirst();
            if (findFirst != null) {
                this.box.remove(findFirst.getId());
            }
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void removeAppId(String str) {
        try {
            List<RecentMiniEntity> find = this.box.query().equal(RecentMiniEntity_.appId, str).build().find();
            for (int i = 0; i < find.size(); i++) {
                this.box.remove((Box<RecentMiniEntity>) find.get(i));
            }
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void batchRemoveBox(List<RecentMiniEntity> list) {
        try {
            this.box.remove(list);
            UIUtils.toastCenter("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentNaviAppId() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "h5_unicom_77dc0a4f" : (URLEnvironmentConfig.isPrepubEnvironment() || URLEnvironmentConfig.isPrepubEnvironment2()) ? "h5_unicom_1b13f9b2" : "h5_unicom_cdd6d91b";
    }

    public static int miniEntityType(RecentMiniEntity recentMiniEntity) {
        if (recentMiniEntity != null) {
            if (TextUtils.equals(getCurrentNaviAppId(), recentMiniEntity.getAppId())) {
                return 2;
            }
            return isEdopApp(recentMiniEntity.getAppletUrl()) ? 1 : 3;
        }
        return -1;
    }

    public static boolean isEdopApp(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("https://edop_unicom?") || str.startsWith("http://edop_unicom?") || str.startsWith("https://edop_unicom/?") || str.startsWith("http://edop_unicom/?");
    }
}
