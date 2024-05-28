package com.baidu.mapapi.map;

import android.util.Log;
import com.baidu.mapapi.common.Logger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class TileOverlay {

    /* renamed from: b */
    private static final String f6460b = "TileOverlay";

    /* renamed from: f */
    private static int f6461f;

    /* renamed from: a */
    BaiduMap f6462a;

    /* renamed from: g */
    private TileProvider f6466g;

    /* renamed from: d */
    private HashMap<String, Tile> f6464d = new HashMap<>();

    /* renamed from: e */
    private HashSet<String> f6465e = new HashSet<>();

    /* renamed from: c */
    private ExecutorService f6463c = Executors.newFixedThreadPool(1);

    public TileOverlay(BaiduMap baiduMap, TileProvider tileProvider) {
        this.f6462a = baiduMap;
        this.f6466g = tileProvider;
    }

    /* renamed from: a */
    private synchronized Tile m18839a(String str) {
        if (this.f6464d.containsKey(str)) {
            Tile tile = this.f6464d.get(str);
            this.f6464d.remove(str);
            return tile;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void m18838a(String str, Tile tile) {
        this.f6464d.put(str, tile);
    }

    /* renamed from: b */
    private synchronized boolean m18835b(String str) {
        return this.f6465e.contains(str);
    }

    /* renamed from: c */
    private synchronized void m18833c(String str) {
        this.f6465e.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Tile m18842a(int i, int i2, int i3) {
        String str;
        String str2;
        String str3 = i + "_" + i2 + "_" + i3;
        Tile m18839a = m18839a(str3);
        if (m18839a != null) {
            return m18839a;
        }
        BaiduMap baiduMap = this.f6462a;
        if (baiduMap != null && f6461f == 0) {
            MapStatus mapStatus = baiduMap.getMapStatus();
            f6461f = (((mapStatus.f6144a.f7378j.right - mapStatus.f6144a.f7378j.left) / 256) + 2) * (((mapStatus.f6144a.f7378j.bottom - mapStatus.f6144a.f7378j.top) / 256) + 2);
        }
        if (this.f6464d.size() > f6461f) {
            m18843a();
        }
        if (m18835b(str3) || this.f6463c.isShutdown()) {
            return null;
        }
        try {
            m18833c(str3);
            this.f6463c.execute(new RunnableC2754af(this, i, i2, i3, str3));
            return null;
        } catch (RejectedExecutionException unused) {
            str = f6460b;
            str2 = "ThreadPool excepiton";
            Log.e(str, str2);
            return null;
        } catch (Exception unused2) {
            str = f6460b;
            str2 = "fileDir is not legal";
            Log.e(str, str2);
            return null;
        }
    }

    /* renamed from: a */
    synchronized void m18843a() {
        Logger.logE(f6460b, "clearTaskSet");
        this.f6465e.clear();
        this.f6464d.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m18837b() {
        this.f6463c.shutdownNow();
    }

    public boolean clearTileCache() {
        BaiduMap baiduMap = this.f6462a;
        if (baiduMap == null) {
            return false;
        }
        return baiduMap.m19008b();
    }

    public void removeTileOverlay() {
        BaiduMap baiduMap = this.f6462a;
        if (baiduMap == null) {
            return;
        }
        baiduMap.m19010a(this);
    }
}
