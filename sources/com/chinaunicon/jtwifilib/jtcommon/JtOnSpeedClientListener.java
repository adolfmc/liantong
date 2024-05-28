package com.chinaunicon.jtwifilib.jtcommon;

import android.content.Context;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.jtcommon.model.Register1Bean;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtOnSpeedClientListener implements JtOnSpeedListener {
    private Context context;

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
    public void onAverageSpeed(String str) {
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
    public void onCurrentSpeed(String str) {
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
    public void onMaxSpeed(String str) {
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
    public void onMinSpeed(String str) {
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
    public void onSpeed(float f, float f2, float f3, float f4) {
    }

    public JtOnSpeedClientListener(Context context) {
        this.context = context;
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
    public void onFinish(int i, int i2, int i3) {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("speed_finish");
        register1Bean.setStatus("0");
        register1Bean.setMsg(i + "," + i2 + "," + i3);
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "speed_finish");
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnSpeedListener
    public void onFiled(String str) {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("speed_error");
        register1Bean.setStatus("0");
        register1Bean.setMsg(str);
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "speed_error");
    }
}
