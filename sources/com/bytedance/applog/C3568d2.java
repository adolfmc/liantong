package com.bytedance.applog;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.d2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3568d2 {

    /* renamed from: a */
    public static final C3570b f8425a;

    /* renamed from: com.bytedance.applog.d2$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3570b {
        public /* synthetic */ C3570b(C3569a c3569a) {
        }

        /* renamed from: a */
        public JSONArray mo17315a(Context context) {
            if (context == null) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
            JSONArray jSONArray = new JSONArray();
            try {
                List neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
                ArrayList arrayList = new ArrayList();
                if (neighboringCellInfo != null) {
                    for (int i = 0; i < neighboringCellInfo.size(); i++) {
                        try {
                            NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) neighboringCellInfo.get(i);
                            if (!arrayList.contains(Integer.valueOf(neighboringCellInfo2.getCid()))) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("cellId", neighboringCellInfo2.getCid());
                                jSONObject.put("lac", neighboringCellInfo2.getLac());
                                jSONObject.put("rssi", neighboringCellInfo2.getRssi());
                                jSONObject.put("psc", neighboringCellInfo2.getPsc());
                                jSONObject.put("networktype", neighboringCellInfo2.getNetworkType());
                                arrayList.add(Integer.valueOf(neighboringCellInfo2.getCid()));
                                jSONArray.put(jSONObject);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SecurityException e2) {
                e2.printStackTrace();
            }
            return jSONArray;
        }
    }

    @TargetApi(23)
    /* renamed from: com.bytedance.applog.d2$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3571c extends C3570b {
        public /* synthetic */ C3571c(C3569a c3569a) {
            super(null);
        }

        @Override // com.bytedance.applog.C3568d2.C3570b
        /* renamed from: a */
        public JSONArray mo17315a(Context context) {
            CellInfo cellInfo;
            CellIdentityCdma cellIdentityCdma;
            if (context == null) {
                return null;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService("phone");
            JSONArray jSONArray = new JSONArray();
            try {
                List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < allCellInfo.size(); i++) {
                    try {
                        cellInfo = allCellInfo.get(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (cellInfo instanceof CellInfoGsm) {
                        CellSignalStrengthGsm cellSignalStrength = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                        CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
                        if (!arrayList.contains(cellIdentity)) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("cellId", cellIdentity.getCid());
                            jSONObject.put("lac", cellIdentity.getLac());
                            jSONObject.put("dbm", cellSignalStrength.getDbm());
                            jSONObject.put("mcc", cellIdentity.getMcc());
                            jSONObject.put("mnc", cellIdentity.getMnc());
                            jSONArray.put(jSONObject);
                            cellIdentityCdma = cellIdentity;
                        }
                    } else if (cellInfo instanceof CellInfoLte) {
                        CellSignalStrengthLte cellSignalStrength2 = ((CellInfoLte) cellInfo).getCellSignalStrength();
                        CellIdentityLte cellIdentity2 = ((CellInfoLte) cellInfo).getCellIdentity();
                        if (!arrayList.contains(cellIdentity2)) {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("cellId", cellIdentity2.getCi());
                            jSONObject2.put("tac", cellIdentity2.getTac());
                            jSONObject2.put("dbm", cellSignalStrength2.getDbm());
                            jSONObject2.put("mcc", cellIdentity2.getMcc());
                            jSONObject2.put("mnc", cellIdentity2.getMnc());
                            jSONArray.put(jSONObject2);
                            cellIdentityCdma = cellIdentity2;
                        }
                    } else if (cellInfo instanceof CellInfoWcdma) {
                        CellSignalStrengthWcdma cellSignalStrength3 = ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                        CellIdentityWcdma cellIdentity3 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                        if (!arrayList.contains(cellIdentity3)) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("cellId", cellIdentity3.getCid());
                            jSONObject3.put("lac", cellIdentity3.getLac());
                            jSONObject3.put("dbm", cellSignalStrength3.getDbm());
                            jSONObject3.put("psc", cellIdentity3.getPsc());
                            jSONObject3.put("mcc", cellIdentity3.getMcc());
                            jSONObject3.put("mnc", cellIdentity3.getMnc());
                            jSONArray.put(jSONObject3);
                            cellIdentityCdma = cellIdentity3;
                        }
                    } else {
                        if (cellInfo instanceof CellInfoCdma) {
                            CellSignalStrengthCdma cellSignalStrength4 = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                            CellIdentityCdma cellIdentity4 = ((CellInfoCdma) cellInfo).getCellIdentity();
                            if (!arrayList.contains(cellIdentity4)) {
                                JSONObject jSONObject4 = new JSONObject();
                                jSONObject4.put("nid", cellIdentity4.getNetworkId());
                                jSONObject4.put("sid", cellIdentity4.getSystemId());
                                jSONObject4.put("bid", cellIdentity4.getBasestationId());
                                jSONObject4.put("dbm", cellSignalStrength4.getDbm());
                                jSONArray.put(jSONObject4);
                                cellIdentityCdma = cellIdentity4;
                            }
                        }
                    }
                    arrayList.add(cellIdentityCdma);
                }
            } catch (SecurityException e2) {
                e2.printStackTrace();
            }
            return jSONArray;
        }
    }

    static {
        f8425a = Build.VERSION.SDK_INT >= 23 ? new C3571c(null) : new C3570b(null);
    }
}
