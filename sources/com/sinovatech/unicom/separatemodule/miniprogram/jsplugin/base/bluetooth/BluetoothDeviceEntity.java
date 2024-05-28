package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BluetoothDeviceEntity {
    private int RSSI;
    private boolean connectable;
    private String deviceId;
    private boolean isAdd;
    private String name;

    public boolean isAdd() {
        return this.isAdd;
    }

    public void setAdd(boolean z) {
        this.isAdd = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public int getRSSI() {
        return this.RSSI;
    }

    public void setRSSI(int i) {
        this.RSSI = i;
    }

    public boolean isConnectable() {
        return this.connectable;
    }

    public void setConnectable(boolean z) {
        this.connectable = z;
    }

    public String toString() {
        return "BluetoothDeviceEntity{name='" + this.name + "', deviceId='" + this.deviceId + "', RSSI='" + this.RSSI + "', connectable=" + this.connectable + '}';
    }
}
