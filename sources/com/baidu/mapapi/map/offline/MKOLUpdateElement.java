package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MKOLUpdateElement {
    public static final int DOWNLOADING = 1;
    public static final int FINISHED = 4;
    public static final int SUSPENDED = 3;
    public static final int UNDEFINED = 0;
    public static final int WAITING = 2;
    public static final int eOLDSFormatError = 9;
    public static final int eOLDSIOError = 7;
    public static final int eOLDSInstalling = 10;
    public static final int eOLDSMd5Error = 5;
    public static final int eOLDSNetError = 6;
    public static final int eOLDSWifiError = 8;
    public int cityID;
    public String cityName;
    public LatLng geoPt;
    public int level;
    public int ratio;
    public int serversize;
    public int size;
    public int status;
    public boolean update;
}
