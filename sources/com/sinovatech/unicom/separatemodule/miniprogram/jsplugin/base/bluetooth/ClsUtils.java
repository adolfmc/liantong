package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.bluetooth;

import android.bluetooth.BluetoothDevice;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ClsUtils {
    public static boolean createBond(Class cls, BluetoothDevice bluetoothDevice) throws Exception {
        return ((Boolean) cls.getMethod("createBond", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
    }

    public static boolean removeBond(Class<?> cls, BluetoothDevice bluetoothDevice) throws Exception {
        return ((Boolean) cls.getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
    }

    public static boolean setPin(Class<? extends BluetoothDevice> cls, BluetoothDevice bluetoothDevice, String str) throws Exception {
        try {
            Boolean bool = (Boolean) cls.getDeclaredMethod("setPin", byte[].class).invoke(bluetoothDevice, str.getBytes());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return true;
    }

    public static boolean cancelPairingUserInput(Class<?> cls, BluetoothDevice bluetoothDevice) throws Exception {
        return ((Boolean) cls.getMethod("cancelPairingUserInput", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
    }

    public static boolean cancelBondProcess(Class<?> cls, BluetoothDevice bluetoothDevice) throws Exception {
        return ((Boolean) cls.getMethod("cancelBondProcess", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
    }

    public static void setPairingConfirmation(Class<?> cls, BluetoothDevice bluetoothDevice, boolean z) throws Exception {
        cls.getDeclaredMethod("setPairingConfirmation", Boolean.TYPE).invoke(bluetoothDevice, Boolean.valueOf(z));
    }

    public static void printAllInform(Class cls) {
        try {
            for (int i = 0; i < cls.getMethods().length; i++) {
            }
            for (int i2 = 0; i2 < cls.getFields().length; i2++) {
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
