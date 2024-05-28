package com.sinovatech.unicom.separatemodule.templateholder;

import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedHashMap;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RVItemViewHolderFactory {
    private static final String configFileName = "sn_rvconfig";
    private static RVItemViewHolderFactory instance;
    private final String TAG = getClass().getSimpleName();
    private LinkedHashMap<String, RVConfigEntity> templateConfigMap = new LinkedHashMap<>();
    private LinkedHashMap<Integer, RVConfigEntity> viewTypeConfigMap = new LinkedHashMap<>();

    private RVItemViewHolderFactory(Context context) {
        parseConfig(context);
    }

    public static RVItemViewHolderFactory getFactory(Context context) {
        synchronized (RVItemViewHolderFactory.class) {
            if (instance == null) {
                instance = new RVItemViewHolderFactory(context);
            }
        }
        return instance;
    }

    private void parseConfig(Context context) {
        try {
            int identifier = context.getResources().getIdentifier(configFileName, "xml", context.getPackageName());
            if (identifier == 0) {
                throw new RuntimeException("找不到文件 res/xml/sn_rvconfig.xml");
            }
            XmlResourceParser xml = context.getResources().getXml(identifier);
            int i = 1;
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (!TextUtils.isEmpty(name) && eventType == 2 && name.equalsIgnoreCase("viewholder")) {
                    RVConfigEntity rVConfigEntity = new RVConfigEntity();
                    String attributeValue = xml.getAttributeValue(null, "template");
                    String attributeValue2 = xml.getAttributeValue(null, "holder");
                    String attributeValue3 = xml.getAttributeValue(null, "layout");
                    rVConfigEntity.setTemplateName(attributeValue);
                    rVConfigEntity.setViewHolder(attributeValue2);
                    rVConfigEntity.setViewType(i);
                    rVConfigEntity.setViewLayout(attributeValue3);
                    this.templateConfigMap.put(attributeValue, rVConfigEntity);
                    this.viewTypeConfigMap.put(Integer.valueOf(i), rVConfigEntity);
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            RVLogUtil.error(this.TAG, "加载ViewHolder配置文件错误：" + e.getMessage());
        }
    }

    public RVItemViewHolder createViewHolderByViewType(Activity activity, ViewGroup viewGroup, int i) {
        try {
            RVConfigEntity rVConfigEntity = this.viewTypeConfigMap.get(Integer.valueOf(i));
            String str = this.TAG;
            Log.d(str, "createViewHolderByViewType: viewTypeConfigMap=" + this.viewTypeConfigMap.toString());
            if (rVConfigEntity == null) {
                throw new RuntimeException("没有配置对应的楼层模板，viewType=" + i);
            }
            String viewHolder = rVConfigEntity.getViewHolder();
            String str2 = rVConfigEntity.getViewLayout().split(".xml")[0];
            String str3 = this.TAG;
            Log.d(str3, "createViewHolderByViewType: layout==" + str2);
            String str4 = this.TAG;
            Log.d(str4, "createViewHolderByViewType: holder==" + viewHolder);
            int identifier = activity.getResources().getIdentifier(str2, "layout", activity.getPackageName());
            if (identifier == 0) {
                throw new RuntimeException("找不到文件 res/layout/" + str2 + ".xml");
            }
            Class<?> cls = Class.forName(viewHolder);
            String str5 = this.TAG;
            Log.d(str5, "createViewHolderByViewType: vhClass=" + cls.getSimpleName() + "=viewType=" + i);
            return (RVItemViewHolder) cls.getDeclaredConstructor(Activity.class, View.class).newInstance(activity, activity.getLayoutInflater().inflate(identifier, viewGroup, false));
        } catch (Exception e) {
            RVEmptyVH rVEmptyVH = new RVEmptyVH(activity, new View(activity));
            e.printStackTrace();
            String str6 = this.TAG;
            RVLogUtil.error(str6, "createViewHolderByViewType错误(holder=;layout=;viewType=" + i + "):" + e.getMessage());
            return rVEmptyVH;
        }
    }

    public int getViewTypeByTemplate(String str) {
        RVConfigEntity rVConfigEntity;
        LinkedHashMap<String, RVConfigEntity> linkedHashMap = this.templateConfigMap;
        if (linkedHashMap == null || (rVConfigEntity = linkedHashMap.get(str)) == null) {
            return -1;
        }
        return rVConfigEntity.getViewType();
    }
}
