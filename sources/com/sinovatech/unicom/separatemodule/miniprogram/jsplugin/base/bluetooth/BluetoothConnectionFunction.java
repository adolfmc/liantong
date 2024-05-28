package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.Hex;
import java.util.List;
import java.util.UUID;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BluetoothConnectionFunction {
    public static BluetoothConnectionFunction bluetoothConnectionFunction;
    private Number RSSI;
    private BLECharacteristicValueChangeListener bleCharacteristicValueChangeListener;
    private BLEConnectionStateChangeListener bleConnectionStateChangeListener;
    private BLEMTUChangeListener blemtuChangeListener;
    private BluetoothGatt bluetoothGatt;
    public boolean isCharacteristicValueChange;
    public boolean isConnectionStateChange;
    public boolean isMtuChange;
    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothConnectionFunction.1
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            if (BluetoothConnectionFunction.this.bleConnectionStateChangeListener != null && BluetoothConnectionFunction.this.isConnectionStateChange) {
                BluetoothConnectionFunction.this.bleConnectionStateChangeListener.onBLEConnectionStateChange(bluetoothGatt.getDevice(), i2);
            }
            if (i2 == 2) {
                BluetoothConnectionFunction.this.bluetoothGatt.discoverServices();
            }
            BluetoothConnectionFunction.this.bluetoothGatt.close();
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            if (i == 0) {
                BluetoothConnectionFunction.this.setGetBLEDeviceServices(bluetoothGatt.getServices());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (BluetoothConnectionFunction.this.bleCharacteristicValueChangeListener == null || !BluetoothConnectionFunction.this.isCharacteristicValueChange) {
                return;
            }
            BluetoothConnectionFunction.this.bleCharacteristicValueChangeListener.onBLECharacteristicValueChange(bluetoothGatt.getDevice(), bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic.getUuid());
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            if (i2 == 0) {
                BluetoothConnectionFunction.this.setRssi(Integer.valueOf(i));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            if (i2 == 0) {
                BluetoothConnectionFunction.this.setMtu(i);
                if (BluetoothConnectionFunction.this.blemtuChangeListener == null || !BluetoothConnectionFunction.this.isMtuChange) {
                    return;
                }
                BluetoothConnectionFunction.this.blemtuChangeListener.onBLEMTUChange(bluetoothGatt.getDevice(), i);
            }
        }
    };
    private int mtu;
    private List<BluetoothGattService> services;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface BLECharacteristicValueChangeListener {
        void onBLECharacteristicValueChange(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface BLEConnectionStateChangeListener {
        void onBLEConnectionStateChange(BluetoothDevice bluetoothDevice, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface BLEMTUChangeListener {
        void onBLEMTUChange(BluetoothDevice bluetoothDevice, int i);
    }

    public static BluetoothConnectionFunction getInstance() {
        if (bluetoothConnectionFunction == null) {
            bluetoothConnectionFunction = new BluetoothConnectionFunction();
        }
        return bluetoothConnectionFunction;
    }

    public void connectBLE(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.bluetoothGatt = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str).connectGatt(context, false, this.mGattCallback);
    }

    public void setGetBLEDeviceServices(List<BluetoothGattService> list) {
        this.services = list;
    }

    public List<BluetoothGattService> getBLEDeviceServices() {
        return this.services;
    }

    public void setMtu(int i) {
        this.mtu = i;
    }

    public Number getMtu(String str) {
        if (isSameBle(str)) {
            return Integer.valueOf(this.mtu);
        }
        return null;
    }

    public Number setBLEMTU(String str, int i) {
        if (isSameBle(str) && this.bluetoothGatt.requestMtu(i)) {
            return getMtu(str);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRssi(Number number) {
        this.RSSI = number;
    }

    public Number getRssI(String str) {
        if (isSameBle(str)) {
            return this.RSSI;
        }
        return null;
    }

    public List<BluetoothGattCharacteristic> getBLEDeviceCharacteristics(String str, String str2, Context context) {
        connectBLE(str, context);
        List<BluetoothGattService> bLEDeviceServices = getBLEDeviceServices();
        for (int i = 0; i < bLEDeviceServices.size(); i++) {
            if (str2.equals(bLEDeviceServices.get(i).getUuid().toString())) {
                return bLEDeviceServices.get(i).getCharacteristics();
            }
        }
        return null;
    }

    public void closeBLEConnection() {
        BluetoothGatt bluetoothGatt = this.bluetoothGatt;
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
        }
    }

    public void writeBLECharacteristicValue(String str, String str2, String str3, String str4, String str5, Context context) {
        BluetoothGattCharacteristic gattCharacteristic = getGattCharacteristic(str, str2, str3, context);
        if (gattCharacteristic.getProperties() == 8) {
            if (!TextUtils.isEmpty(str5)) {
                if (str5.equals("write")) {
                    gattCharacteristic.setWriteType(2);
                } else if (str5.equals("writeNoResponse")) {
                    gattCharacteristic.setWriteType(1);
                }
            } else {
                gattCharacteristic.setWriteType(1);
            }
            gattCharacteristic.setValue(Hex.hexStringToBytes(str4));
            this.bluetoothGatt.writeCharacteristic(gattCharacteristic);
        }
    }

    public byte[] readBLECharacteristicValue(String str, String str2, String str3, Context context) {
        return getGattCharacteristic(str, str2, str3, context).getValue();
    }

    public void notifyBLECharacteristicValueChange(Context context, String str, String str2, String str3, boolean z, String str4) {
        this.bluetoothGatt.setCharacteristicNotification(getGattCharacteristic(str, str2, str3, context), z);
    }

    public void onBLEMTUChange(BLEMTUChangeListener bLEMTUChangeListener) {
        this.isMtuChange = true;
        this.blemtuChangeListener = bLEMTUChangeListener;
    }

    public void onBLEConnectionStateChange(BLEConnectionStateChangeListener bLEConnectionStateChangeListener) {
        this.isConnectionStateChange = true;
        this.bleConnectionStateChangeListener = bLEConnectionStateChangeListener;
    }

    public void onBLECharacteristicValueChange(BLECharacteristicValueChangeListener bLECharacteristicValueChangeListener) {
        this.bleCharacteristicValueChangeListener = bLECharacteristicValueChangeListener;
        this.isCharacteristicValueChange = true;
    }

    public BluetoothGattCharacteristic getGattCharacteristic(String str, String str2, String str3, Context context) {
        connectBLE(str, context);
        List<BluetoothGattService> bLEDeviceServices = getBLEDeviceServices();
        for (int i = 0; i < bLEDeviceServices.size(); i++) {
            if (TextUtils.equals(bLEDeviceServices.get(i).getUuid().toString(), str2)) {
                List<BluetoothGattCharacteristic> characteristics = bLEDeviceServices.get(i).getCharacteristics();
                for (int i2 = 0; i2 < characteristics.size(); i2++) {
                    if (TextUtils.equals(characteristics.get(i2).getUuid().toString(), str3)) {
                        return characteristics.get(i2);
                    }
                }
                continue;
            }
        }
        return null;
    }

    public boolean isSameBle(String str) {
        BluetoothGatt bluetoothGatt = this.bluetoothGatt;
        return bluetoothGatt != null && TextUtils.equals(bluetoothGatt.getDevice().getAddress(), str);
    }
}
