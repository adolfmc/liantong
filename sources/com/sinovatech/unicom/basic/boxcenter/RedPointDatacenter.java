package com.sinovatech.unicom.basic.boxcenter;

import com.sinovatech.unicom.basic.p314po.PointEntity;
import com.sinovatech.unicom.basic.p314po.PointEntity_;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import io.objectbox.Box;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RedPointDatacenter {
    private static RedPointDatacenter datacenter;
    private Box<PointEntity> box = App.getBoxStore().boxFor(PointEntity.class);

    public static synchronized RedPointDatacenter getInstance() {
        RedPointDatacenter redPointDatacenter;
        synchronized (RedPointDatacenter.class) {
            if (datacenter == null) {
                datacenter = new RedPointDatacenter();
            }
            redPointDatacenter = datacenter;
        }
        return redPointDatacenter;
    }

    public void insertData(String str, String str2) {
        if (this.box.query().equal(PointEntity_.menuId, str).and().equal(PointEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).and().equal(PointEntity_.tag, str2).build().findFirst() == null) {
            PointEntity pointEntity = new PointEntity();
            pointEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
            pointEntity.setMenuId(str);
            pointEntity.setTag(str2);
            this.box.put((Box<PointEntity>) pointEntity);
        }
    }

    public List<PointEntity> getRedpointList() {
        return this.box.query().equal(PointEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find();
    }
}
