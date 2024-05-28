package com.baidu.mapapi.map;

import android.util.Log;
import java.util.HashSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.af */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2754af implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f6526a;

    /* renamed from: b */
    final /* synthetic */ int f6527b;

    /* renamed from: c */
    final /* synthetic */ int f6528c;

    /* renamed from: d */
    final /* synthetic */ String f6529d;

    /* renamed from: e */
    final /* synthetic */ TileOverlay f6530e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2754af(TileOverlay tileOverlay, int i, int i2, int i3, String str) {
        this.f6530e = tileOverlay;
        this.f6526a = i;
        this.f6527b = i2;
        this.f6528c = i3;
        this.f6529d = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        TileProvider tileProvider;
        String str;
        String str2;
        HashSet hashSet;
        tileProvider = this.f6530e.f6466g;
        Tile tile = ((FileTileProvider) tileProvider).getTile(this.f6526a, this.f6527b, this.f6528c);
        if (tile == null) {
            str = TileOverlay.f6460b;
            str2 = "FileTile pic is null";
        } else if (tile.width == 256 && tile.height == 256) {
            this.f6530e.m18838a(this.f6526a + "_" + this.f6527b + "_" + this.f6528c, tile);
            hashSet = this.f6530e.f6465e;
            hashSet.remove(this.f6529d);
        } else {
            str = TileOverlay.f6460b;
            str2 = "FileTile pic must be 256 * 256";
        }
        Log.e(str, str2);
        hashSet = this.f6530e.f6465e;
        hashSet.remove(this.f6529d);
    }
}
