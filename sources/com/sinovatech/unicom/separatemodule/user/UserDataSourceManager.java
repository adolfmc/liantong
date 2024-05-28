package com.sinovatech.unicom.separatemodule.user;

import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserDataSourceManager {
    public static final String HEADER = "user_itema_header";
    public static final String HUAFEI = "user_huafei";
    public static final String LIBAO = "user_item_libao";
    public static final String USERACTIVITY = "user_item_activity";
    public static final String USERDEVICE = "user_device";
    public static final String USERFOOT = "user_foot";
    public static final String USERSERVICEORDER = "user_serviceorder";
    private List<RVItemEntity> dataSource = new ArrayList();

    public List<RVItemEntity> getDataSource() {
        this.dataSource.add(new RVItemEntity(HEADER, null, 2));
        this.dataSource.add(new RVItemEntity(LIBAO, null, 2));
        this.dataSource.add(new RVItemEntity(USERSERVICEORDER, null, 2));
        this.dataSource.add(new RVItemEntity(USERDEVICE, null, 2));
        this.dataSource.add(new RVItemEntity(HUAFEI, null, 2));
        this.dataSource.add(new RVItemEntity(USERFOOT, null, 2));
        this.dataSource.add(new RVItemEntity(USERACTIVITY, null, 2));
        return this.dataSource;
    }

    public RVItemEntity getRVItemEntity(String str) {
        for (RVItemEntity rVItemEntity : this.dataSource) {
            if (str.equals(rVItemEntity.templateName)) {
                return rVItemEntity;
            }
        }
        return null;
    }
}
