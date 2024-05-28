package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.ParcelUuid;
import android.text.TextUtils;
import com.huawei.hms.push.AttributionReporter;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.Hex;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BluetoothPeripheralServerFunction {
    public static BluetoothPeripheralServerFunction bluetoothPeripheralServerFunction;
    private AdvertiseData addServiceUuid;
    private BLEPeripheralConnectionStateChanged blePeripheralConnectionStateChanged;
    private BLEPeripheralReadServerListener blePeripheralReadServerListener;
    private BLEPeripheralWriteServerListener blePeripheralWriteServerListener;
    private BluetoothGattServer bluetoothGattServer;
    private BluetoothLeAdvertiser bluetoothLeAdvertiser;
    private AdvertiseCallback callback = new AdvertiseCallback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothPeripheralServerFunction.2
        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartFailure(int i) {
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartSuccess(AdvertiseSettings advertiseSettings) {
        }
    };
    private BluetoothGattDescriptor descriptor;
    public boolean isConnectState;
    public boolean isReadRequest;
    public boolean isWriteRequest;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface BLEPeripheralConnectionStateChanged {
        void onConnectionStateChanged(BluetoothDevice bluetoothDevice, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface BLEPeripheralReadServerListener {
        void onCharacteristicReadRequest(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface BLEPeripheralWriteServerListener {
        void onCharacteristicWriteRequest(int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);
    }

    public void writeCharacteristicValue(String str, String str2, String str3, boolean z) {
    }

    public static BluetoothPeripheralServerFunction getInstance() {
        if (bluetoothPeripheralServerFunction == null) {
            bluetoothPeripheralServerFunction = new BluetoothPeripheralServerFunction();
        }
        return bluetoothPeripheralServerFunction;
    }

    public BluetoothGattServer createBLEPeripheralServer(Context context) {
        this.bluetoothGattServer = ((BluetoothManager) context.getSystemService("bluetooth")).openGattServer(context, new BluetoothGattServerCallback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth.BluetoothPeripheralServerFunction.1
            @Override // android.bluetooth.BluetoothGattServerCallback
            public void onConnectionStateChange(BluetoothDevice bluetoothDevice, int i, int i2) {
                super.onConnectionStateChange(bluetoothDevice, i, i2);
                if (BluetoothPeripheralServerFunction.this.blePeripheralConnectionStateChanged == null || !BluetoothPeripheralServerFunction.this.isConnectState) {
                    return;
                }
                BluetoothPeripheralServerFunction.this.blePeripheralConnectionStateChanged.onConnectionStateChanged(bluetoothDevice, i);
            }

            @Override // android.bluetooth.BluetoothGattServerCallback
            public void onCharacteristicReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                super.onCharacteristicReadRequest(bluetoothDevice, i, i2, bluetoothGattCharacteristic);
                if (BluetoothPeripheralServerFunction.this.blePeripheralReadServerListener != null && BluetoothPeripheralServerFunction.this.isReadRequest) {
                    BluetoothPeripheralServerFunction.this.blePeripheralReadServerListener.onCharacteristicReadRequest(i, bluetoothGattCharacteristic);
                }
                BluetoothPeripheralServerFunction.this.bluetoothGattServer.sendResponse(bluetoothDevice, i, 0, i2, bluetoothGattCharacteristic.getValue());
            }

            @Override // android.bluetooth.BluetoothGattServerCallback
            public void onCharacteristicWriteRequest(BluetoothDevice bluetoothDevice, int i, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z, boolean z2, int i2, byte[] bArr) {
                super.onCharacteristicWriteRequest(bluetoothDevice, i, bluetoothGattCharacteristic, z, z2, i2, bArr);
                if (BluetoothPeripheralServerFunction.this.blePeripheralWriteServerListener != null && BluetoothPeripheralServerFunction.this.isWriteRequest) {
                    BluetoothPeripheralServerFunction.this.blePeripheralWriteServerListener.onCharacteristicWriteRequest(i, bluetoothGattCharacteristic, bArr);
                }
                BluetoothPeripheralServerFunction.this.bluetoothGattServer.sendResponse(bluetoothDevice, i, 0, i2, bluetoothGattCharacteristic.getValue());
            }

            @Override // android.bluetooth.BluetoothGattServerCallback
            public void onDescriptorReadRequest(BluetoothDevice bluetoothDevice, int i, int i2, BluetoothGattDescriptor bluetoothGattDescriptor) {
                super.onDescriptorReadRequest(bluetoothDevice, i, i2, bluetoothGattDescriptor);
            }
        });
        return this.bluetoothGattServer;
    }

    public void addService(JSONObject jSONObject) {
        int i;
        int i2;
        boolean z;
        BluetoothPeripheralServerFunction bluetoothPeripheralServerFunction2;
        BluetoothPeripheralServerFunction bluetoothPeripheralServerFunction3;
        BluetoothPeripheralServerFunction bluetoothPeripheralServerFunction4 = this;
        try {
            String optString = jSONObject.optString("uuid");
            JSONArray optJSONArray = jSONObject.optJSONArray("characteristics");
            BluetoothGattCharacteristic bluetoothGattCharacteristic = null;
            boolean z2 = false;
            BluetoothGattService bluetoothGattService = new BluetoothGattService(UUID.fromString(optString), 0);
            int i3 = 0;
            while (i3 < optJSONArray.length()) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                String optString2 = optJSONObject.optString("uuid");
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("properties");
                boolean optBoolean = optJSONObject2.optBoolean("write", z2);
                boolean optBoolean2 = optJSONObject2.optBoolean("writeNoResponse", z2);
                boolean optBoolean3 = optJSONObject2.optBoolean("read", z2);
                boolean optBoolean4 = optJSONObject2.optBoolean("notify", z2);
                boolean optBoolean5 = optJSONObject2.optBoolean("indicate", z2);
                JSONObject optJSONObject3 = optJSONObject.optJSONObject(AttributionReporter.SYSTEM_PERMISSION);
                boolean optBoolean6 = optJSONObject3.optBoolean("readable", z2);
                boolean optBoolean7 = optJSONObject3.optBoolean("writeable", z2);
                JSONArray jSONArray = optJSONArray;
                boolean optBoolean8 = optJSONObject3.optBoolean("readEncryptionRequired", z2);
                BluetoothGattCharacteristic bluetoothGattCharacteristic2 = bluetoothGattCharacteristic;
                boolean optBoolean9 = optJSONObject3.optBoolean("writeEncryptionRequired", z2);
                if (optBoolean && optBoolean7) {
                    try {
                        i = i3;
                        bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(UUID.fromString(optString2), 8, 16);
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        return;
                    }
                } else {
                    i = i3;
                }
                if (optBoolean2 && optBoolean7) {
                    bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(UUID.fromString(optString2), 4, 16);
                }
                if (optBoolean && optBoolean9) {
                    bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(UUID.fromString(optString2), 8, 32);
                }
                if (optBoolean2 && optBoolean9) {
                    bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(UUID.fromString(optString2), 4, 32);
                }
                if (optBoolean3 && optBoolean6) {
                    bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(UUID.fromString(optString2), 2, 1);
                }
                if (optBoolean3 && optBoolean8) {
                    bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(UUID.fromString(optString2), 2, 2);
                }
                if (optBoolean4 && optBoolean7) {
                    bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(UUID.fromString(optString2), 18, 16);
                }
                BluetoothGattCharacteristic bluetoothGattCharacteristic3 = (optBoolean5 && optBoolean7) ? new BluetoothGattCharacteristic(UUID.fromString(optString2), 32, 16) : bluetoothGattCharacteristic2;
                bluetoothGattService.addCharacteristic(bluetoothGattCharacteristic3);
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("descriptors");
                if (optJSONArray2 != null) {
                    int i4 = 0;
                    while (i4 < optJSONArray2.length()) {
                        int i5 = i;
                        JSONObject optJSONObject4 = optJSONArray2.optJSONObject(i5);
                        String optString3 = optJSONObject4.optString("uuid");
                        JSONObject optJSONObject5 = optJSONObject4.optJSONObject(AttributionReporter.SYSTEM_PERMISSION);
                        boolean optBoolean10 = optJSONObject5.optBoolean("write", false);
                        boolean optBoolean11 = optJSONObject5.optBoolean("read", false);
                        if (optBoolean10) {
                            bluetoothPeripheralServerFunction3 = this;
                            try {
                                bluetoothPeripheralServerFunction3.descriptor = new BluetoothGattDescriptor(UUID.fromString(optString3), 16);
                                if (bluetoothGattCharacteristic3 != null) {
                                    bluetoothGattCharacteristic3.addDescriptor(bluetoothPeripheralServerFunction3.descriptor);
                                }
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                return;
                            }
                        } else {
                            bluetoothPeripheralServerFunction3 = this;
                        }
                        if (optBoolean11) {
                            bluetoothPeripheralServerFunction3.descriptor = new BluetoothGattDescriptor(UUID.fromString(optString3), 1);
                        }
                        if (bluetoothGattCharacteristic3 != null) {
                            bluetoothGattCharacteristic3.addDescriptor(bluetoothPeripheralServerFunction3.descriptor);
                        }
                        i4++;
                        i = i5;
                    }
                    i2 = i;
                    z = false;
                    bluetoothPeripheralServerFunction2 = this;
                } else {
                    i2 = i;
                    z = false;
                    bluetoothPeripheralServerFunction2 = this;
                }
                bluetoothPeripheralServerFunction2.bluetoothGattServer.addService(bluetoothGattService);
                i3 = i2 + 1;
                bluetoothGattCharacteristic = bluetoothGattCharacteristic3;
                z2 = z;
                bluetoothPeripheralServerFunction4 = bluetoothPeripheralServerFunction2;
                optJSONArray = jSONArray;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public void closeService() {
        BluetoothGattServer bluetoothGattServer = this.bluetoothGattServer;
        if (bluetoothGattServer != null) {
            bluetoothGattServer.close();
        }
    }

    public void removeService(String str) {
        if (this.bluetoothGattServer != null) {
            this.bluetoothGattServer.removeService(this.bluetoothGattServer.getService(UUID.fromString(str)));
        }
    }

    public void startAdvertising(JSONObject jSONObject, String str) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            boolean optBoolean = jSONObject.optBoolean("connectable");
            jSONObject.optString("deviceName");
            JSONArray optJSONArray = jSONObject.optJSONArray("serviceUuids");
            JSONObject optJSONObject = jSONObject.optJSONObject("manufacturerData");
            jSONObject.optJSONObject("beacon");
            int i = 2;
            if (!TextUtils.isEmpty(str)) {
                if (str.equals("low")) {
                    i = 0;
                } else if (!str.equals("medium")) {
                    i = 3;
                }
            }
            AdvertiseSettings build = new AdvertiseSettings.Builder().setConnectable(optBoolean).setAdvertiseMode(i).build();
            AdvertiseData build2 = new AdvertiseData.Builder().addManufacturerData(Integer.parseInt(optJSONObject.optString("manufacturerId")), Hex.hexStringToBytes(optJSONObject.optString("manufacturerSpecificData"))).build();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                this.addServiceUuid = new AdvertiseData.Builder().addServiceUuid(new ParcelUuid(UUID.fromString(optJSONArray.getString(i2)))).build();
            }
            this.bluetoothLeAdvertiser = defaultAdapter.getBluetoothLeAdvertiser();
            this.bluetoothLeAdvertiser.startAdvertising(build, build2, this.addServiceUuid, this.callback);
        } catch (Exception unused) {
        }
    }

    public void stopAdvertising() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser = this.bluetoothLeAdvertiser;
        if (bluetoothLeAdvertiser != null) {
            bluetoothLeAdvertiser.stopAdvertising(this.callback);
        }
    }

    public void onBLEPeripheralWriteServerListener(BLEPeripheralWriteServerListener bLEPeripheralWriteServerListener) {
        this.blePeripheralWriteServerListener = bLEPeripheralWriteServerListener;
        this.isWriteRequest = true;
    }

    public void onBLEPeripheralReadServerListener(BLEPeripheralReadServerListener bLEPeripheralReadServerListener) {
        this.blePeripheralReadServerListener = bLEPeripheralReadServerListener;
        this.isReadRequest = true;
    }

    public void onBLEPeripheralConnectionStateChanged(BLEPeripheralConnectionStateChanged bLEPeripheralConnectionStateChanged) {
        this.blePeripheralConnectionStateChanged = bLEPeripheralConnectionStateChanged;
        this.isConnectState = true;
    }
}
