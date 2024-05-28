package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothConnectionFunction;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothPeripheralServerFunction;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/CUBLEPeripheralServer")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CUBLEPeripheralServerJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        char c;
        String optString = this.parameterJO.optString("type");
        final JSONObject jSONObject = new JSONObject();
        switch (optString.hashCode()) {
            case -2061245567:
                if (optString.equals("closeBLEConnection")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1896147748:
                if (optString.equals("bLEPeripheralServerStartAdvertising")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1687691512:
                if (optString.equals("bLEPeripheralServerStopAdvertising")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -1591615414:
                if (optString.equals("onBLECharacteristicValueChange")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1453299522:
                if (optString.equals("createBLEPeripheralServer")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1324466089:
                if (optString.equals("bLEPeripheralServerRemoveService")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -1292995619:
                if (optString.equals("getBLEDeviceCharacteristics")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1151797006:
                if (optString.equals("offBLEMTUChange")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -978573353:
                if (optString.equals("offBLEConnectionStateChange")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -579508608:
                if (optString.equals("bLEPeripheralServerOffCharacteristicWriteRequest")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -520432476:
                if (optString.equals("bLEPeripheralServerOnCharacteristicWriteRequest")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 253378825:
                if (optString.equals("offBLEPeripheralConnectionStateChanged")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 279024457:
                if (optString.equals("getBLEMTU")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 316455614:
                if (optString.equals("bLEPeripheralServerClose")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 457828274:
                if (optString.equals("getBLEDeviceRSSI")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 497825863:
                if (optString.equals("onBLEConnectionStateChange")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 791186073:
                if (optString.equals("onBLEPeripheralConnectionStateChanged")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 907511113:
                if (optString.equals("bLEPeripheralServerOffCharacteristicReadRequest")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 1635747802:
                if (optString.equals("offBLECharacteristicValueChange")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1641031421:
                if (optString.equals("createBLEConnection")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1735241305:
                if (optString.equals("getBLEDeviceServices")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1754484174:
                if (optString.equals("bLEPeripheralServerAddService")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 2060527537:
                if (optString.equals("bLEPeripheralServerWriteCharacteristicValue")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 2131134132:
                if (optString.equals("notifyBLECharacteristicValueChange")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                BluetoothConnectionFunction.getInstance().onBLEConnectionStateChange(new BluetoothConnectionFunction.BLEConnectionStateChangeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBLEPeripheralServerJSPlugin.1
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothConnectionFunction.BLEConnectionStateChangeListener
                    public void onBLEConnectionStateChange(BluetoothDevice bluetoothDevice, int i) {
                        boolean z = i == 2;
                        try {
                            jSONObject.put("deviceId", bluetoothDevice.getAddress());
                            jSONObject.put("connected", z);
                            CUBLEPeripheralServerJSPlugin.this.callbackSuccess(jSONObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            case 1:
                BluetoothConnectionFunction.getInstance().onBLECharacteristicValueChange(new BluetoothConnectionFunction.BLECharacteristicValueChangeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBLEPeripheralServerJSPlugin.2
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothConnectionFunction.BLECharacteristicValueChangeListener
                    public void onBLECharacteristicValueChange(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2) {
                        try {
                            jSONObject.put("deviceId", bluetoothDevice.getAddress());
                            jSONObject.put("serviceId", uuid.toString());
                            jSONObject.put("characteristicId", uuid2.toString());
                            CUBLEPeripheralServerJSPlugin.this.callbackSuccess(jSONObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            case 2:
                BluetoothConnectionFunction.getInstance().isMtuChange = false;
                return;
            case 3:
                BluetoothConnectionFunction.getInstance().isConnectionStateChange = false;
                return;
            case 4:
                BluetoothConnectionFunction.getInstance().isCharacteristicValueChange = false;
                return;
            case 5:
                String optString2 = this.parameterJO.optString("deviceId");
                String optString3 = this.parameterJO.optString("serviceId");
                String optString4 = this.parameterJO.optString("characteristicId");
                boolean optBoolean = this.parameterJO.optBoolean("state", false);
                this.parameterJO.optString("type");
                BluetoothConnectionFunction.getInstance().notifyBLECharacteristicValueChange(this.activityContext, optString2, optString3, optString4, optBoolean, optString);
                return;
            case 6:
                jSONObject.put("mtu", BluetoothConnectionFunction.getInstance().getMtu(this.parameterJO.optString("deviceId")));
                callbackSuccess(jSONObject);
                return;
            case 7:
                jSONObject.put("services", BluetoothConnectionFunction.getInstance().getBLEDeviceServices());
                callbackSuccess(jSONObject);
                return;
            case '\b':
                jSONObject.put("RSSI", BluetoothConnectionFunction.getInstance().getRssI(this.parameterJO.optString("deviceId")));
                callbackSuccess(jSONObject);
                return;
            case '\t':
                BluetoothConnectionFunction.getInstance().getBLEDeviceCharacteristics(this.parameterJO.optString("deviceId"), this.parameterJO.optString("serviceId"), this.activityContext);
                return;
            case '\n':
                String optString5 = this.parameterJO.optString("deviceId");
                this.parameterJO.optInt("timeout");
                BluetoothConnectionFunction.getInstance().connectBLE(optString5, this.activityContext);
                return;
            case 11:
                BluetoothConnectionFunction.getInstance().closeBLEConnection();
                return;
            case '\f':
                BluetoothPeripheralServerFunction.getInstance().onBLEPeripheralConnectionStateChanged(new BluetoothPeripheralServerFunction.BLEPeripheralConnectionStateChanged() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBLEPeripheralServerJSPlugin.3
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothPeripheralServerFunction.BLEPeripheralConnectionStateChanged
                    public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i) {
                        try {
                            jSONObject.put("deviceId", bluetoothDevice.getAddress());
                            jSONObject.put("connected", i == 2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            case '\r':
                BluetoothPeripheralServerFunction.getInstance().isConnectState = false;
                return;
            case 14:
                BluetoothPeripheralServerFunction.getInstance().createBLEPeripheralServer(this.activityContext);
                callbackSuccess(true);
                return;
            case 15:
                JSONObject optJSONObject = this.parameterJO.optJSONObject("service");
                if (optJSONObject != null) {
                    BluetoothPeripheralServerFunction.getInstance().addService(optJSONObject);
                }
                callbackSuccess(true);
                return;
            case 16:
                BluetoothPeripheralServerFunction.getInstance().closeService();
                callbackSuccess(true);
                return;
            case 17:
                BluetoothPeripheralServerFunction.getInstance().isReadRequest = false;
                return;
            case 18:
                BluetoothPeripheralServerFunction.getInstance().isWriteRequest = false;
                return;
            case 19:
                BluetoothPeripheralServerFunction.getInstance().onBLEPeripheralReadServerListener(new BluetoothPeripheralServerFunction.BLEPeripheralReadServerListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.CUBLEPeripheralServerJSPlugin.4
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothPeripheralServerFunction.BLEPeripheralReadServerListener
                    public void onCharacteristicReadRequest(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                        try {
                            jSONObject.put("serviceId", bluetoothGattCharacteristic.getService().getUuid().toString());
                            jSONObject.put("characteristicId", bluetoothGattCharacteristic.getUuid().toString());
                            jSONObject.put("callbackId", i);
                            CUBLEPeripheralServerJSPlugin.this.callbackSuccess(jSONObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            case 20:
                BluetoothPeripheralServerFunction.getInstance().removeService(this.parameterJO.optString("serviceId"));
                callbackSuccess(true);
                return;
            case 21:
                BluetoothPeripheralServerFunction.getInstance().startAdvertising(this.parameterJO.optJSONObject("advertiseRequest"), this.parameterJO.optString("powerLevel"));
                return;
            case 22:
                BluetoothPeripheralServerFunction.getInstance().stopAdvertising();
                return;
            case 23:
                BluetoothPeripheralServerFunction.getInstance().writeCharacteristicValue(this.parameterJO.optString("serviceId"), this.parameterJO.optString("characteristicId"), this.parameterJO.optString("value"), this.parameterJO.optBoolean("needNotify"));
                return;
            default:
                return;
        }
    }
}
