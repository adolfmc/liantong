package com.sinovatech.unicom.separatemodule.tongyicaiji;

import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJImageParseUtils;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYImageDataEntity;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYNetWorkEntity;
import io.objectbox.Box;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TYInsertDataManager {
    private static TYInsertDataManager dataManager;
    private final Box<TYImageDataEntity> imageBox = App.getBoxStore().boxFor(TYImageDataEntity.class);
    private final Box<TYNetWorkEntity> netBox = App.getBoxStore().boxFor(TYNetWorkEntity.class);

    private TYInsertDataManager() {
    }

    public static TYInsertDataManager getInstance() {
        if (dataManager == null) {
            synchronized (TYInsertDataManager.class) {
                if (dataManager == null) {
                    dataManager = new TYInsertDataManager();
                }
            }
        }
        return dataManager;
    }

    public synchronized void insertImageData(String str, String str2, String str3, String str4) {
        TYImageDataEntity tYImageDataEntity = new TYImageDataEntity();
        if (TYCJImageParseUtils.ImageChannelType.fiveEnter.equals(str4)) {
            tYImageDataEntity.setBusinessname("联通APP-五入口图片加载指标");
            tYImageDataEntity.setBusinessCode("unicomApp_five_entrance_image");
        } else if (TYCJImageParseUtils.ImageChannelType.pubuliu.equals(str4)) {
            tYImageDataEntity.setBusinessname("联通APP-瀑布流图片加载指标");
            tYImageDataEntity.setBusinessCode("unicomApp_waterfall_flow_image");
        }
        tYImageDataEntity.setIsError(str2);
        tYImageDataEntity.setTimeStamp(str3);
        tYImageDataEntity.setUrl(str);
        this.imageBox.put((Box<TYImageDataEntity>) tYImageDataEntity);
    }

    public synchronized void insertNetData(String str, String str2) {
        TYNetWorkEntity tYNetWorkEntity = new TYNetWorkEntity();
        tYNetWorkEntity.setIsError(str2);
        tYNetWorkEntity.setUrl(str);
        this.netBox.put((Box<TYNetWorkEntity>) tYNetWorkEntity);
    }

    public synchronized JSONArray getNetWorkData() throws Exception {
        JSONArray jSONArray;
        jSONArray = new JSONArray();
        List<TYNetWorkEntity> all = this.netBox.getAll();
        if (all != null && all.size() > 0) {
            for (int i = 0; i < all.size(); i++) {
                TYNetWorkEntity tYNetWorkEntity = all.get(i);
                String isError = tYNetWorkEntity.getIsError();
                String url = tYNetWorkEntity.getUrl();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("url", url);
                jSONObject.put("is_error", isError);
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    public synchronized JSONArray getImageLoadData() throws Exception {
        JSONArray jSONArray;
        List<TYImageDataEntity> all;
        jSONArray = new JSONArray();
        if (this.imageBox != null && (all = this.imageBox.getAll()) != null && all.size() > 0) {
            for (int i = 0; i < all.size(); i++) {
                TYImageDataEntity tYImageDataEntity = all.get(i);
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("business_name", tYImageDataEntity.getBusinessname());
                jSONObject2.put("business_code", tYImageDataEntity.getBusinessCode());
                JSONArray jSONArray2 = new JSONArray();
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("item_code", "url");
                jSONObject3.put("item_name", "页面url");
                jSONObject3.put("item_remark", tYImageDataEntity.getUrl());
                jSONObject3.put("item_value", tYImageDataEntity.getUrl());
                jSONArray2.put(jSONObject3);
                JSONObject jSONObject4 = new JSONObject();
                String isError = tYImageDataEntity.getIsError();
                if ("0".equals(isError)) {
                    jSONObject4.put("item_remark", "失败");
                } else {
                    jSONObject4.put("item_remark", "成功");
                }
                jSONObject4.put("item_code", "is_error");
                jSONObject4.put("item_name", "失败编码");
                jSONObject4.put("item_value", "0".equals(isError) ? "1" : "0");
                jSONArray2.put(jSONObject4);
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("item_code", MenuDataCenter.timestamp);
                jSONObject5.put("item_name", MenuDataCenter.timestamp);
                jSONObject5.put("item_remark", tYImageDataEntity.getTimeStamp());
                jSONObject5.put("item_value", tYImageDataEntity.getTimeStamp());
                jSONArray2.put(jSONObject5);
                jSONObject2.put("item_infos", jSONArray2);
                jSONObject.put("business", jSONObject2);
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    public void clearTYBox() {
        Box<TYImageDataEntity> box = this.imageBox;
        if (box != null) {
            box.removeAll();
        }
        Box<TYNetWorkEntity> box2 = this.netBox;
        if (box2 != null) {
            box2.removeAll();
        }
    }
}
